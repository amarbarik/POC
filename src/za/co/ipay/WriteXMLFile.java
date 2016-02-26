package za.co.ipay;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMSource;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by F4742443 on 2016/02/23.
 */
public class WriteXMLFile {

    private static final SimpleDateFormat WS_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    public static void main(String[] args) {
        System.out.println("Date Format >>" + WS_DATE_TIME_FORMAT.format(new Date()));

        try {

            Element ipayMsg = new Element("ipayMsg");
            ipayMsg.setAttribute(new Attribute("time", WS_DATE_TIME_FORMAT.format(new Date())));
            ipayMsg.setAttribute(new Attribute("seqNum", "0"));
            ipayMsg.setAttribute(new Attribute("term", "1"));
            ipayMsg.setAttribute(new Attribute("client", "ipay"));

            Document doc = new Document(ipayMsg);
            //doc.setRootElement(ipayMsg);

            Element elecMsg = new Element("elecMsg");
            elecMsg.setAttribute(new Attribute("ver", "2.6"));

            doc.getRootElement().addContent(elecMsg);

            Element vendReq = new Element("vendReq");
            vendReq.setAttribute(new Attribute("useAdv", "false"));

            vendReq.addContent(new Element("ref").setText("136105500001"));
            Element amt = new Element("amt");
            amt.setAttribute("cur", "ZAR");
            vendReq.addContent(amt.setText("11400"));
            vendReq.addContent(new Element("numTokens").setText("2"));
            vendReq.addContent(new Element("meter").setText("123456789"));
            vendReq.addContent(new Element("payType").setText("creditCard"));

            elecMsg.addContent(vendReq);


           /* Element vendReq2 = new Element("vendReq");
            vendReq2.setAttribute(new Attribute("id", "2"));
            vendReq2.addContent(new Element("firstname").setText("low"));
            vendReq2.addContent(new Element("lastname").setText("yin fong"));
            vendReq2.addContent(new Element("nickname").setText("fong fong"));
            vendReq2.addContent(new Element("salary").setText("188888"));

            elecMsg.addContent(vendReq2);*/

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("c:\\ipay.xml"));

            System.out.println("File Saved!");


            //sendMessageViaSocket(doc);
            sendMessage(doc);
        } catch (Exception io) {
            System.out.println("Error " + io.getMessage());
        }
    }

    private static void sendMessage(Document doc) throws IOException, TransformerException {
        System.out.println("Inside Send msg");
        URL u = new URL("http://www.bizswitch.net:8879");
        URLConnection uc = u.openConnection();
        HttpURLConnection connection = (HttpURLConnection) uc;
        connection.setDoOutput(true);
        connection.setDoInput(true);
       // connection.setRequestMethod("POST");

        System.out.println("before connection");

        OutputStream out = connection.getOutputStream();

        System.out.println("after connection");

        JDOMSource jdomSource = new JDOMSource(doc);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Result result = new StreamResult(byteArrayOutputStream);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.transform(jdomSource, result);
        byte[] bytesXml =  byteArrayOutputStream.toByteArray();

        XMLOutputter serializer = new XMLOutputter();
        System.out.println("Sending message");
        //serializer.output(doc, out);


        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.write(bytesXml.length);

        dataOutputStream.write(bytesXml);

        System.out.println("After Write");



        // Read the response
        InputStream in = connection.getInputStream();
        System.out.println("-------------- >>> " + new BufferedInputStream(in).toString());
        //receiveMessage(in);
        out.flush();
        out.close();
        connection.disconnect();
    }

    private static void receiveMessage(InputStream in) throws IOException {
        SAXBuilder parser = new SAXBuilder();
        System.out.println("Responding...");
        try {
            Document response = parser.build(in);
            System.out.println("Response Received >> " + response);
        } catch (JDOMException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        in.close();
    }

    public static void sendMessageViaSocket(Document doc) throws IOException, TransformerException {
        Socket socket = null;
        String host = "www.bizswitch.net";
        socket = new Socket(host, 8879);

        //File file = new File("C:\\ipay.xml");

        JDOMSource jdomSource = new JDOMSource(doc);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Result result = new StreamResult(byteArrayOutputStream);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(jdomSource, result);
        byte[] bytesXml =  byteArrayOutputStream.toByteArray();

        // Get the size of the file
        //long length = file.length();
        //byte[] bytes = new byte[16 * 1024];

        System.out.println("LENGTH>> " + bytesXml.length);

        //InputStream in = new FileInputStream(file);

        OutputStream out = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        dataOutputStream.writeInt(bytesXml.length);
        dataOutputStream.write(bytesXml);

        /*while ((count = in.read(bytes)) > 0) {
            dataOutputStream.write(bytes, 0, count);
        }*/

        InputStream inputStream = socket.getInputStream();

        System.out.println("Input Stream>>> " + inputStream.read());

        dataOutputStream.close();
        out.close();
        inputStream.close();
        socket.close();
    }


}
