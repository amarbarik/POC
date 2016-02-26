package za.co.sample.webserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class Worker extends SimpleServer implements HttpConstants, Runnable {
    /*lets define the arguments required to handle multiple clients
     * as well as to serve them*/
    //1.
    /*Initialize the buffer size*/
    final static int BUF_SIZE = 2048;
    //2.
	  /*To represents the end of line we have here carriage return
	     *  and line end character*/
    static final byte[] EOL = {(byte) '\r', (byte) '\n'};
    //3.
	    /* buffer to use for requests */
    byte[] buf;
    //4.
	    /* Socket to client we're handling */
    private Socket s;

    /*initialize the buffer and client socket*/
    Worker() {
        buf = new byte[BUF_SIZE];
        s = null;
    }

    /*Used to wakes up the worker thread present in the Worker pool*/
    synchronized void setSocket(Socket s) {
        this.s = s;
        notify();//wakes up the thread
    }

    /*Here we start running the threads */
    public synchronized void run() {
        while (true) {
            if (s == null) {
	                /* nothing to do */
                try {
                    wait();
                } catch (InterruptedException e) {
	                    /* should not happen */
                    continue;
                }
            }
            try {
                handleClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
	            /* go back in wait queue if there's fewer
	             * than numHandler connections.
	             */
            s = null;
            Vector pool = SimpleServer.threads;
            synchronized (pool) {
                if (pool.size() >= SimpleServer.workers) {
	                    /* too many threads, exit this one */
                    return;
                } else {
                    pool.addElement(this);
                }
            }
        }
    }

    /*Here the worker reads the first line of the
     * clients HTTP request from the GET method
     * and there by getting the location and the name of the
     * file requested
     * */
    void handleClient() throws IOException {
        InputStream is = new BufferedInputStream(s.getInputStream());//get request from the client
        PrintStream ps = new PrintStream(s.getOutputStream());//response from the server
	        /* we will only block in read for this many milliseconds
	         * before we fail with java.io.InterruptedIOException,
	         * at which point we will abandon the connection.
	         */
        s.setSoTimeout(SimpleServer.timeout);//sets the timeout property
        s.setTcpNoDelay(true);//enables the TCP_NODELAY
	        /* zero out the buffer from last time */
        for (int i = 0; i < BUF_SIZE; i++) {
            buf[i] = 0;
        }
        try {
	            /* Here it only support HTTP GET/HEAD, and don't
	             * support any fancy HTTP options,
	             * so we're only interested really in
	             * the first line.
	             */
            int nread = 0, r = 0;

            outerloop:
            while (nread < BUF_SIZE) {
                r = is.read(buf, nread, BUF_SIZE - nread);
                if (r == -1) {
	                    /* EOF */
                    return;
                }
                int i = nread;
                nread += r;
                for (; i < nread; i++) {
                    if (buf[i] == (byte) '\n' || buf[i] == (byte) '\r') {
	                        /* read one line */
                        break outerloop;
                    }
                }
            }

	            /* are we doing a GET or just a HEAD */
            boolean doingGet;
	            /* beginning of file name */
            int index;
            if (buf[0] == (byte) 'G' &&
                    buf[1] == (byte) 'E' &&
                    buf[2] == (byte) 'T' &&
                    buf[3] == (byte) ' ') {
                doingGet = true;
                index = 4;
            } else if (buf[0] == (byte) 'H' &&
                    buf[1] == (byte) 'E' &&
                    buf[2] == (byte) 'A' &&
                    buf[3] == (byte) 'D' &&
                    buf[4] == (byte) ' ') {
                doingGet = false;
                index = 5;
            } else {
	                /* we don't support this method */
                ps.print("HTTP/1.0 " + HTTP_BAD_METHOD +
                        " unsupported method type: ");
                ps.write(buf, 0, 5);
                ps.write(EOL);
                ps.flush();
                s.close();
                return;
            }

            int i = 0;
	            /* find the file name, from:
	             * GET /amar/index.html HTTP/1.0
	             * extract "/amar/index.html"
	             */
            for (i = index; i < nread; i++) {
                if (buf[i] == (byte) ' ') {
                    break;
                }
            }
            String s1 = new String(buf, 0, index, i - index);//convert bytes into characters but it is @deprecated
            String fname = s1.replace('/', File.separatorChar);
            if (fname.startsWith(File.separator)) {
                fname = fname.substring(1);
            }
            File targ = new File(SimpleServer.root, fname);
            if (targ.isDirectory()) {
                File ind = new File(targ, "index.html");
                if (ind.exists()) {
                    targ = ind;
                }
            }
            boolean OK = printHeaders(targ, ps);
            if (doingGet) {
                if (OK) {
                    sendFile(targ, ps);
                } else {
                    send404(targ, ps);
                }
            }
        } finally {
            s.close();
        }
    }

    /*The following method checks the extension of the files
     * is to be served by the server
     */
    boolean printHeaders(File targ, PrintStream ps) throws IOException {
        boolean ret = false;
        int rCode = 0;
        if (!targ.exists()) {
            rCode = HTTP_NOT_FOUND;
            ps.print("HTTP/1.0 " + HTTP_NOT_FOUND + " not found");
            ps.write(EOL);
            ret = false;
        } else {
            rCode = HTTP_OK;
            ps.print("HTTP/1.0 " + HTTP_OK + " OK");
            ps.write(EOL);
            ret = true;
        }
        log("From " + s.getInetAddress().getHostAddress() + ": GET " +
                targ.getAbsolutePath() + "-->" + rCode);
        ps.print("Server: Simple java");
        ps.write(EOL);
        ps.print("Date: " + (new Date()));
        ps.write(EOL);
        if (ret) {
            if (!targ.isDirectory()) {
                ps.print("Content-length: " + targ.length());
                ps.write(EOL);
                ps.print("Last Modified: " + (new
                        Date(targ.lastModified())));
                ps.write(EOL);
                String name = targ.getName();
                int ind = name.lastIndexOf('.');
                String ct = null;
                if (ind > 0) {
                    ct = (String) map.get(name.substring(ind));
                }
                if (ct == null) {
                    ct = "unknown/unknown";
                }
                ps.print("Content-type: " + ct);
                ps.write(EOL);
            } else {
                ps.print("Content-type: text/html");
                ps.write(EOL);
            }
        }
        return ret;
    }

    void send404(File targ, PrintStream ps) throws IOException {
        ps.write(EOL);
        ps.write(EOL);
        ps.println("Not Found\n\n" +
                "The requested resource was not found.\n");
    }

    void sendFile(File targ, PrintStream ps) throws IOException {
        InputStream is = null;
        ps.write(EOL);
        if (targ.isDirectory()) {
            listDirectory(targ, ps);
            return;
        } else {
            is = new FileInputStream(targ.getAbsolutePath());
        }

        try {
            int n;
            while ((n = is.read(buf)) > 0) {
                ps.write(buf, 0, n);
            }
        } finally {
            is.close();
        }
    }

    /* mapping of file extensions to content-types */
    static Hashtable map = new Hashtable();

    static {
        fillMap();
    }

    static void setSuffix(String k, String v) {
        map.put(k, v);
    }

    static void fillMap() {
        setSuffix("", "content/unknown");
        setSuffix(".uu", "application/octet-stream");
        setSuffix(".exe", "application/octet-stream");
        setSuffix(".ps", "application/postscript");
        setSuffix(".zip", "application/zip");
        setSuffix(".sh", "application/x-shar");
        setSuffix(".tar", "application/x-tar");
        setSuffix(".snd", "audio/basic");
        setSuffix(".au", "audio/basic");
        setSuffix(".wav", "audio/x-wav");
        setSuffix(".gif", "image/gif");
        setSuffix(".jpg", "image/jpeg");
        setSuffix(".jpeg", "image/jpeg");
        setSuffix(".htm", "text/html");
        setSuffix(".html", "text/html");
        setSuffix(".text", "text/plain");
        setSuffix(".c", "text/plain");
        setSuffix(".cc", "text/plain");
        setSuffix(".c++", "text/plain");
        setSuffix(".h", "text/plain");
        setSuffix(".pl", "text/plain");
        setSuffix(".txt", "text/plain");
        setSuffix(".java", "text/plain");
    }

    void listDirectory(File dir, PrintStream ps) throws IOException {
        ps.println("<TITLE>Directory listing</TITLE><P>\n");
        ps.println("<A HREF=\"..\">Parent Directory</A><BR>\n");
        String[] list = dir.list();
        for (int i = 0; list != null && i < list.length; i++) {
            File f = new File(dir, list[i]);
            if (f.isDirectory()) {
                ps.println("<b><A HREF=\"" + list[i] + "/\">" + list[i] + "/</A></b><BR>");
            } else {
                // Commented by Manmay : start
                // ps.println("<A HREF=\""+list[i]+"\">"+list[i]+"</A><BR");

                // Added by Manmay : start
                // '>' symbol added at the end of <BR
                ps.println("<A HREF=\"" + list[i] + "\">" + list[i] + "</A><BR>");
            }
        }
        ps.println("<P><HR><BR><I>" + (new Date()) + "</I>");
    }

}