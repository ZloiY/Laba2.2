package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by NoRFolf on 05.04.2016.
 */
public class XmlParse {
    public static ObservableList<BookModel> allTable = FXCollections.observableArrayList();
    public void parse(File inputFile){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("book");
            for(int temp = 0; temp < nodeList.getLength(); temp++){
                Node node = nodeList.item(temp);
                if(node.getNodeType() == node.ELEMENT_NODE){
                    Element element = (Element) node;
                    BookModel tblItem = new BookModel();
                    tblItem.setNameBook(element
                            .getElementsByTagName("bookname")
                            .item(0)
                            .getTextContent());
                    String athourname = element
                            .getElementsByTagName("athuor")
                            .item(0)
                            .getTextContent();
                    tblItem.setAuthor(athourname);
                    tblItem.setPublisher(element
                            .getElementsByTagName("publisher")
                            .item(0)
                            .getTextContent());
                    tblItem.setNumberBook(Integer.parseInt(element
                            .getElementsByTagName("numberbooks")
                            .item(0)
                            .getTextContent()));
                    tblItem.setEdition(Integer.parseInt(element
                            .getElementsByTagName("edition")
                            .item(0)
                            .getTextContent()));
                    tblItem.setAll(Integer.parseInt(element
                            .getElementsByTagName("all")
                            .item(0)
                            .getTextContent()));
                    Controller.table.getItems().add(tblItem);
                }
            }
            for (int i = 0; i < Controller.table.getItems().size(); i++)
                allTable.add(Controller.table.getItems().get(i));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void toXml(File file){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("books");
            doc.appendChild(rootElement);
            for (Integer i = 0; i < Controller.table.getItems().size(); i++) {
                Element book = doc.createElement("book");
                doc.appendChild(book);
                Element bookname = doc.createElement("bookname");
                bookname.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getNameBook()));
                rootElement.appendChild(bookname);
                Element athuor = doc.createElement("athuor");
                athuor.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getAuthor()));
                rootElement.appendChild(athuor);
                Element publisher = doc.createElement("publisher");
                publisher.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getPublisher()));
                rootElement.appendChild(publisher);
                Element numberbooks = doc.createElement("numberbooks");
                numberbooks.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getNumberBook().toString()));
                rootElement.appendChild(numberbooks);
                Element edition = doc.createElement("edition");
                edition.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getEdition().toString()));
                rootElement.appendChild(edition);
                Element all = doc.createElement("all");
                all.appendChild(doc
                        .createTextNode(Controller.table.getItems().get(i).getAll().toString()));
                rootElement.appendChild(all);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
