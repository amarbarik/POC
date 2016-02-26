package za.co.sample.webserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleServer implements HttpConstants {

    /**
     * @param s
     */
    /*For configuring the server
	 * lets define some of the parameters*/
	 /* print to stdout
	  * used for default log information */
    protected static void p(String s) {
        System.out.println(s);
    }

    /* print to the log file */
    /*Stores the logging information*/
    /*Synchronized to avoid deadlock situation and other thread problems
     * like memory inconsistency problem, shared memory problem 
     */
    protected static void log(String s) {
        synchronized (log) {
            log.println(s);
            log.flush();
        }
    }

    static PrintStream log = null;//1 configuration
    /* our server's configuration information is stored
     * in these properties
     * ie; in props object
     */
    protected static Properties props = new Properties();

    /* Where worker threads stand idle 
     * used to handle multiple clients
     * */
    static Vector threads = new Vector();//because its size can vary as per the requirement

    /* the web server's virtual root 
     * from root it serves all the files that are requested*/
    static File root;//2 configuration

    /* timeout on client connections 
     * It implies,when there is no response from the clients
     * then session will be over*/
    static int timeout = 0;//3 configuration

    /* max # worker threads */
    static int workers = 5;//4 configuration

    /* load server.properties from java.home
     * this method loads all the configuration required before running the server*/
    static void loadProps() throws IOException {
        System.out.println("" + System.getProperty("java.home"));//prints the location of the java interpreter
        File f = new File
                (System.getProperty("java.home") + File.separator +
                        "lib" + File.separator + "server.properties");//this opens the configuration properties file

        if (f.exists()) {
            InputStream is = new BufferedInputStream(new FileInputStream(f));//read the file
            props.load(is);//loads the file into props object
            is.close();
            String r = props.getProperty("root");//get the root directory property
            if (r != null) {
                root = new File(r);
                if (!root.exists()) {
                    throw new Error(root + " doesn't exist as server root");
                }
            }
            r = props.getProperty("timeout");
            if (r != null) {
                timeout = Integer.parseInt(r);
            }
            r = props.getProperty("workers");
            if (r != null) {
                workers = Integer.parseInt(r);
            }
            r = props.getProperty("log");
            if (r != null) {
                p("opening log file: " + r);
                log = new PrintStream(new BufferedOutputStream(new FileOutputStream(r)));
            }
        }

        /* if no properties were specified, choose defaults */
        if (root == null) {
            root = new File(System.getProperty("user.dir"));//set the current directory as the root directory
        }
        if (timeout <= 1000) {
            timeout = 5000;
        }
        if (workers < 25) {
            workers = 5;
        }
        if (log == null) {
            p("logging to stdout");
            log = System.out;
        }
    }

    /*Now lets print the details of configuration on standard output*/
    static void printProps() {
        p("root=" + root);
        p("timeout=" + timeout);
        p("workers=" + workers);
    }

    //lets construct the method which will accept the request from th clients
    //Now come to main method
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int port = 80;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        loadProps();
        printProps();
        /* start worker threads */
        for (int i = 0; i < workers; ++i) {
            Worker w = new Worker();//initializes the buffer and  set Socket reference to null
            (new Thread(w, "worker #" + i)).start();
            threads.addElement(w);//adds each instance of the clients to the Vector
        }

        ServerSocket ss = new ServerSocket(port);
        while (true) {
            Socket s = ss.accept();
            Worker w = null;
            synchronized (threads) {
                if (threads.isEmpty()) {
                    Worker ws = new Worker();
                    ws.setSocket(s);
                    (new Thread(ws, "additional worker")).start();
                } else {
                    w = (Worker) threads.elementAt(0);
                    threads.removeElementAt(0);
                    w.setSocket(s);
                }
            }
        }
    }

}