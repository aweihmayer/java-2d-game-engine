package engine.core;

import java.io.InputStream;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.resources.SpriteAnimation;
import engine.resources.SpriteSheet;

public class ResourceRegistry {
	protected static HashMap<String, SpriteSheet> spriteSheets = new HashMap<String, SpriteSheet>();
	
// Get resource
	
	public static SpriteSheet getSpriteSheet(String name) { return spriteSheets.get(name); }
	
// Static methods
	
	public static void loadSpriteSheets(String configFile) {
		InputStream stream;
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document document;
		
		NodeList spriteSheetNodes;
		Element spriteSheetNode;
		SpriteSheet spriteSheet;
		String name;
		String source;
		int frameWidth;
		int frameHeight;
		
		NodeList animationNodes;
		Element animationNode;
		SpriteAnimation animation;
		String  animationName;
		int startCol;
		int startRow;
		int endCol;
		int endRow;
		int speed;
		boolean repeat;
		String temp;
		String[] tempArr;
		
		try {
			stream = ResourceRegistry.class.getResourceAsStream(configFile);
			documentBuilderFactory = DocumentBuilderFactory.newInstance();

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(stream);
			spriteSheetNodes = document.getElementsByTagName("sheet");

			for(int i = 0; i < spriteSheetNodes.getLength(); i++) {
				spriteSheetNode = (Element) spriteSheetNodes.item(i);
				
				name = spriteSheetNode.getElementsByTagName("name").item(0).getTextContent();
				source = spriteSheetNode.getElementsByTagName("source").item(0).getTextContent();
				frameWidth = Integer.parseInt(spriteSheetNode.getElementsByTagName("frameWidth").item(0).getTextContent());
				frameHeight = Integer.parseInt(spriteSheetNode.getElementsByTagName("frameHeight").item(0).getTextContent());

				spriteSheet = new SpriteSheet(name, source, frameWidth, frameHeight);
				
				animationNodes = ((Element) spriteSheetNode.getElementsByTagName("animations").item(0)).getElementsByTagName("animation");
				
				for(int y = 0; y < animationNodes.getLength(); y++) {
					animationNode = (Element) animationNodes.item(y);
					
					animationName = animationNode.getElementsByTagName("name").item(0).getTextContent();
					
					temp = animationNode.getElementsByTagName("start").item(0).getTextContent();
					tempArr = temp.split(",");
					startCol = Integer.parseInt(tempArr[0].trim());
					startRow = Integer.parseInt(tempArr[1].trim());
	
					temp = animationNode.getElementsByTagName("end").item(0).getTextContent();
					tempArr = temp.split(",");
					endCol = Integer.parseInt(tempArr[0].trim());
					endRow = Integer.parseInt(tempArr[1].trim());
					
					speed = Integer.parseInt(animationNode.getElementsByTagName("speed").item(0).getTextContent());
					repeat = Boolean.parseBoolean(animationNode.getElementsByTagName("repeat").item(0).getTextContent());
					
					animation = new SpriteAnimation(animationName, startCol, startRow, endCol, endRow, speed, repeat);
					spriteSheet.addAnimation(animation);
				}
				
				spriteSheets.put(spriteSheet.getName(), spriteSheet);
			}		
		} catch (Exception ex) {		
			JOptionPane.showMessageDialog(null, ex.toString());
			ex.printStackTrace();
		}
	}
}
