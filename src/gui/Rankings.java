package gui;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class Rankings {
	
	public static void main(String[] args){
		writeXML();
	}
	
	private static void  writeXML(){
		try{
		Document document = new Document();
		Element root = new Element ("Jugadores");
		document.setRootElement(root);
		
		Element nombre = new Element("Nombre");
		nombre.setAttribute("Nombre", "Player 1");
		nombre.addContent(new Text("Esteban Quito"));
	
		Element score = new Element ("Score");
		score.setAttribute("Score", "Player Score");
		score.addContent("2048");
		
		root.addContent(nombre);
		root.addContent(score);
		
		XMLOutputter out = new XMLOutputter (Format.getPrettyFormat());
		out.output(document, new FileOutputStream(new File("C:/Users/Max/Desktop/prueba.txt")));
		System.out.println("escribido");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
