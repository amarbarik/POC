package za.co.ipay;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by F4742443 on 2016/02/24.
 */
public class CreateRequest {

    private static final SimpleDateFormat WS_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private Document doc;

    public Document getRequestMessage() {
        try {

            Element ipayMsg = new Element("ipayMsg");
            ipayMsg.setAttribute(new Attribute("time", WS_DATE_TIME_FORMAT.format(new Date())));
            ipayMsg.setAttribute(new Attribute("seqNum", "0"));
            ipayMsg.setAttribute(new Attribute("term", "1"));
            ipayMsg.setAttribute(new Attribute("client", "ipay"));
            doc = new Document(ipayMsg);
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

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("c:\\ipay.xml"));

            System.out.println("File Saved!");


        } catch (Exception io) {
            System.out.println("Error " + io.getMessage());
        }

        return doc;
    }
}
