package za.co.ipay;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.transform.JDOMSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * Created by F4742443 on 2016/02/24.
 */
public class Main {

    public static void main(String[] args) {
        CreateRequest createRequest =  new CreateRequest();
        Document document = createRequest.getRequestMessage();
        Socket socket;

        try {
            socket = CreateConnection.getSocket();
            System.out.println("Connection successful");

            JDOMSource jdomSource = new JDOMSource(document);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Result result = new StreamResult(byteArrayOutputStream);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(jdomSource, result);

            byte[] bytesXml =  byteArrayOutputStream.toByteArray();

            System.out.println("LENGTH >> " + bytesXml.length);

            OutputStream out = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(out);

            dataOutputStream.writeInt(bytesXml.length);
            dataOutputStream.write(bytesXml);

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream =
                    new FileOutputStream(new File("C:\\ipayResponse.xml"));

            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
                System.out.println("read value" + read);
            }

            System.out.println(" ------------------------------------- ");
            SAXBuilder builder = new SAXBuilder();
            Document anotherDocument = builder.build(new File("C:\\ipayResponse.xml"));

            System.out.println("--->>> " + anotherDocument.getRootElement().getName());

            /*
            String res = dataInputStream.readUTF();
            System.out.println("---->>>>>>>>>>>>" + res);*/


            /*String xml = "xml";
            InputStreamReader reader = new InputStreamReader(inputStream);
            System.out.println("--->" + reader.read());
            BufferedReader bufferedReader = new BufferedReader(reader);
            System.out.println("--->" + bufferedReader.read());
            System.out.println(xml.length());

            StringBuilder sb = new StringBuilder();

            *//*SAXBuilder parser = new SAXBuilder();
            Document response = parser.build(inputStream);

            System.out.println("-------- >> " + response);*//*

            int value = 0;
            while((value = bufferedReader.read()) != -1)
            {
                // converts int to character
                char c = (char)value;

                // prints character
                System.out.println(c);
            }
*/
            dataOutputStream.close();
            out.close();
            dataInputStream.close();
            inputStream.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Connection unsuccessful");
            e.printStackTrace();
        }
    }
}
