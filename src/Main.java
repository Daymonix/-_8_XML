import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<Channel> listChannel = new ArrayList<>();

        try {
            String uri =
                    "https://naviny.by/rss/politic.xml";

            URL url = new URL(uri);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");

            InputStream xml = connection.getInputStream();

            //File inputFile = new File("input.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse( xml);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nListChannel = doc.getElementsByTagName("channel");
            for (int temp = 0; temp < nListChannel.getLength(); temp++) {
                Channel channel = new Channel();
                NodeList nNodeListChild = nListChannel.item(temp).getChildNodes();
                ArrayList<Item> listItem = new ArrayList<>();
                for (int i = 0; i < nNodeListChild.getLength(); i++) {

                    Node nNode = nNodeListChild.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                         if(eElement.getTagName().equals("title")){
                             channel.setTitle(eElement.getTextContent());
                         }
                         else if(eElement.getTagName().equals("link")){
                             channel.setLink(eElement.getTextContent());
                         }
                         else if(eElement.getTagName().equals("description")){
                             channel.setDescription(eElement.getTextContent());
                         }
                         else if(eElement.getTagName().equals("atom")){
                             channel.setAtom(eElement.getTextContent());
                         }
                         else if(eElement.getTagName().equals("item")){
                             Item item = new Item();
                             //System.out.println("Tag ="+eElement.getTagName()
                             //        +" context : "+ eElement.getTextContent());
                             //NodeList nList = doc.getElementsByTagName("item");
                             //System.out.println("----------------------------");
                             if (eElement.getNodeType() == Node.ELEMENT_NODE) {
                                 NodeList list= eElement.getChildNodes();
                                 for (int k = 0; k < list.getLength(); k++) {
                                     Node nNode2 = list.item(k);
                                     if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                                         Element eElement2 = (Element) nNode2;
                                         if(eElement2.getTagName().equals("title")){
                                             item.setTitle(eElement2.getTextContent());

                                         }
                                         else if(eElement2.getTagName().equals("link")){
                                             item.setLink(eElement2.getTextContent());

                                         }
                                         else if(eElement2.getTagName().equals("description")){
                                             item.setDescription(eElement2.getTextContent());

                                         }
                                         else if(eElement2.getTagName().equals("pubDate")){
                                             item.setPubDate(eElement2.getTextContent());

                                         }
                                         else if(eElement2.getTagName().equals("guid")){
                                             item.setGuid(Integer.parseInt( eElement2.getTextContent()));

                                         }
                                          /*System.out.println("Tag ="+eElement2.getTagName()
                                                  +" context : "+ eElement2.getTextContent());*/
                                     }
                                 }

                                 // System.out.println("Tag ="+eElement2.getTagName()
                                 //         +" context : "+ eElement2.getTextContent());
                             }
                             listItem.add(item);

                         }
                    }

                }
                channel.setItem(listItem);
                listChannel.add(channel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i=0;i<listChannel.size();i++){
            System.out.println(listChannel.get(i).toString());
            ArrayList<Item> listItem = listChannel.get(i).getItem();
            for (int k=0;k<listItem.size();k++){
                System.out.println(listItem.get(k).toString());
            }
        }
    }
}
