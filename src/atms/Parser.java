package atms;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;

public class Parser {
	
	private Map nodes = new HashMap();

	public Parser(String xmldoc){
		
		xmlToNodes(xmldoc);
		
	}
	
	private void xmlToNodes(String xmldoc){
		
		DocumentBuilderFactory fabObj = DocumentBuilderFactory.newInstance();
		
		try
		{
			Document documenttoXML = fabObj.newDocumentBuilder().parse(new File(xmldoc));
			loadNodes(documenttoXML);
		
		} catch (Exception e)
		{
			// 
			e.printStackTrace();
		}
		
	}
	
	public void loadNodes(Document doc){
		
			NodeList elementsfromXML = doc.getElementsByTagName("Element");
			int noOfElements = elementsfromXML.getLength();
		//	Collection elementCollection = new ArrayList();
			String element_id, J1, J2, label, name;
			
			for (int i = 0; i < noOfElements; i++)
			{
				Element elementfromXML = (Element)elementsfromXML.item(i);
				element_id = elementfromXML.getAttribute("elementid");
				name = elementfromXML.getAttribute("id");
				label = elementfromXML.getAttribute("label");
				J1 = elementfromXML.getAttribute("J1");
				J2 = elementfromXML.getAttribute("J2");
				ATMSNode node = ATMSNode.getNode(name);
				
				String []parentTokens = label.split(":");
				
				for(String partToken : parentTokens){		
				String []tokens = partToken.split(" ");

				TreeSet<ATMSNode> en = new TreeSet<ATMSNode>();
				
					
					for(int j=0; j < tokens.length; j++){
				
						if(!tokens[j].startsWith("*")){
					
						ATMSNode partNode = ATMSNode.getNode(tokens[j]);	
						en.add(partNode);
						}
				
						else{
				
						String labelNode = tokens[j].substring(1);
						//System.out.println(array);
						node.addEnvironment(ATMSNode.getNode(labelNode).getLabel());
						}
					}
					Environment e = Environment.getEnvironment(en);
					node.addEnvironment(e);
				}
				
			
				
			
				TreeSet<ATMSNode> en1 = new TreeSet<ATMSNode>();
				
				String[] tokens = J1.split(" ");
				
					for(int j=0; j < tokens.length; j++){
						
							ATMSNode partNode = ATMSNode.getNode(tokens[j]);	
							en1.add(partNode);
							Environment e1 = Environment.getEnvironment(en1);
							node.addJustification(1, e1);
					
					}
					
				TreeSet<ATMSNode> en2 = new TreeSet<ATMSNode>();

				tokens = J2.split(" ");
					
					for(int j=0; j < tokens.length; j++){
						
							ATMSNode partNode = ATMSNode.getNode(tokens[j]);	
							en2.add(partNode);
							Environment e2 = Environment.getEnvironment(en2);
							node.addJustification(2, e2);
					
					}
				}
				
		}	
	
	
	public static void main(String[] args){
		
		Parser parser = new Parser("config.xml");
		System.out.println(ATMSNode.getNode("1"));
		System.out.println(ATMSNode.getNode("2"));
		System.out.println(ATMSNode.getNode("3"));
		System.out.println(ATMSNode.getNode("4"));
		System.out.println(ATMSNode.getNode("5"));
		
	
	}
}
