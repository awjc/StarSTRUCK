package animatables;

import interfaces.Animatable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



import utilities.GeneralUtilities;

public class Text implements Animatable {
	/**
	 * The Color of the Text
	 */
	private Color textColor;
	
	/**
	 * The Position of the center of the Text 
	 */
	private Position textCenter;
	
	/**
	 * The size of the Text
	 */
	private double fontSize;
	
	/**
	 * The message contained in the Text
	 */
	private String textMessage;
	
	/**
	 * The rotation of the Text in degrees
	 */
	private double textAngle;
	
	private Font font;
	
	/**
	 * Constructs a Text object with the initial properties
	 * @param initialColor
	 * The initial Color of the Text
	 * @param initialCenter
	 * The initial Position of the Text's center
	 * @param initialFontSize
	 * The initial font size, as a double
	 * @param message
	 * The message to be contained in the Text
	 */
	public Text(Color initialColor, Position initialCenter, double initialFontSize, String message){
		textColor = initialColor;
		textCenter = initialCenter;
		fontSize = initialFontSize;
		textMessage = message;
		textAngle = 0;
		
		font = new Font(Font.MONOSPACED, Font.PLAIN, (int)fontSize);
	}
	
	/**
	 * Gets the Color of the Text
	 * @return
	 * The Text's Color
	 */
	public Color getColor(){
		return textColor;
	}
	
	/**
	 * Gets the Position of the center of the Text
	 * @return
	 * The Position of the Text's center
	 */
	public Position getCenter(){
		return textCenter;
	}
	
	/**
	 * Gets the font size of the Text
	 * @return
	 * The Text's font size, as a double
	 */
	public double getSize(){
		return fontSize;
	}
	
	/**
	 * Gets the message contained in the Text
	 * @return
	 * The Text's message
	 */
	public String getText(){
		return textMessage;
	}
	
	/**
	 * Gets the rotation angle of the Text
	 * @return
	 * The rotation angle of the Text, in degrees, as a double
	 */
	public double getAngle(){
		return textAngle;
	}
	
	/**
	 * Changes the Color of the Text
	 * @param newColor
	 * The new Color
	 */
	public Text setColor(Color newColor){
		textColor = newColor;
		return this;
	}
	
	/**
	 * Changes the center of the Text
	 * @param newCenter
	 * The Position of the new center
	 */
	public Text setCenter(Position newCenter){
		textCenter = newCenter;
		return this;
	}
	
	/**
	 * Changes the font size of the Text
	 * @param newSize
	 * The new font size
	 */
	public Text setSize(double newFontSize){
		fontSize = newFontSize;
		return this;
	}
	
	/**
	 * Changes the angle of the Text
	 * @param newAngle
	 * The new angle, in degrees
	 */
	public Text setAngle(double newAngle){
		textAngle = newAngle;
		return this;
	}
	
	public Text setFont(Font font){
		this.font = font;
		return this;
	}
	
	public String toString(){
		String tempString = textColor.getRed() + " " + textColor.getGreen() + " " + textColor.getBlue();
		tempString += " " + textCenter.toString();
		tempString += " " + fontSize + " " + textAngle;
		
		return tempString;
	}

	@Override
	public Animatable interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		Text textA = (Text) timeA.getItem();
		Text textZ = (Text) timeZ.getItem();
		
		Color color = GeneralUtilities.interpolate(timeA, textA.getColor(), timeZ, textZ.getColor(), timeM);
		Position position = GeneralUtilities.interpolate(timeA, textA.getCenter(), timeZ, textZ.getCenter(), timeM);
		double fontSize = GeneralUtilities.interpolate(timeA, textA.getSize(), timeZ, textZ.getSize(), timeM);
		double textAngle = GeneralUtilities.interpolate(timeA, textA.getAngle(), timeZ, textZ.getAngle(), timeM);
		
		return this.setColor(color).setCenter(position).setSize(fontSize).setAngle(textAngle);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(textColor);
		g.setFont(font);
		g.drawString(textMessage, textCenter.getX(), textCenter.getY());
	}
	
	public Animatable clone(){
		return new Text(textColor, textCenter, fontSize, textMessage).setAngle(textAngle);
	}
}