import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class XmlWorker {
    public ArrayList<Ticket> work() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Ticket> list=new ArrayList<>();
        // Создается построитель документа
        DocumentBuilder ticket = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        Document document = ticket.parse("data.xml");

        // Получаем корневой элемент
        Node root = document.getDocumentElement();
        NodeList books = root.getChildNodes();
        for (int i = 0; i < books.getLength(); i++) {
            Node book = books.item(i);
            if (book.getNodeType() != Node.TEXT_NODE) {
                String t1=book.getAttributes().getNamedItem("DepartureTimeString").getTextContent();
                String t2=book.getAttributes().getNamedItem("ArrivalTimeString").getTextContent();
                double t3=Double.valueOf(book.getAttributes().getNamedItem("Price").getTextContent());
                int t4=Integer.valueOf(book.getAttributes().getNamedItem("ArrivalStationId").getTextContent());
                int t5=Integer.valueOf(book.getAttributes().getNamedItem("DepartureStationId").getTextContent());
                int t6=Integer.valueOf(book.getAttributes().getNamedItem("TrainId").getTextContent());
                Ticket t=new Ticket(LocalTime.parse(t1),LocalTime.parse(t2),t3,t4,t5,t6);
                list.add(t);
            }
        }
        return list;

    }
}
