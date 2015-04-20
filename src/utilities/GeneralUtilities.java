package utilities;

import java.awt.Color;

import animatables.Position;
import animatables.TimeFrame;


public class GeneralUtilities {
	/**
	 * Gets the value of an event at a certain time, based on the data of two previous events
	 * @param timeA
	 * The time of the first event
	 * @param valueA
	 * The value of the first event
	 * @param timeZ
	 * The time of the second event
	 * @param valueZ
	 * The value of the second event
	 * @param timeM
	 * The time of the desired event
	 * @return
	 * The value of the desired event
	 */
	public static double interpolate(double timeA, double valueA, double timeZ, double valueZ, double timeM){
		return (valueA + (timeZ == timeA ? 0 : (timeM - timeA) / (timeZ - timeA) ) * (valueZ - valueA));
	}
	
	public static double interpolate(TimeFrame timeA, double valueA, TimeFrame timeZ, double valueZ, double timeM){
		return interpolate(timeA.getTime(), valueA, timeZ.getTime(), valueZ, timeM);
	}
	
	/**
	 * Blends two Colors
	 * @param timeA
	 * The time of the first Color
	 * @param colorA
	 * The first Color to be blended
	 * @param timeZ
	 * The time of the second Color
	 * @param colorZ
	 * The second Color to be blended
	 * @param timeM
	 * The time of the desired Color
	 * @return
	 * The Color at the desired time
	 */
	public static Color interpolate(double timeA, Color colorA, double timeZ, Color colorZ, double timeM){
		int newGreen = (int) interpolate(timeA, colorA.getGreen(), timeZ, colorZ.getGreen(), timeM);
		int newRed = (int) interpolate(timeA, colorA.getRed(), timeZ, colorZ.getRed(), timeM);
		int newBlue = (int) interpolate(timeA, colorA.getBlue(), timeZ, colorZ.getBlue(), timeM);
		
		return new Color(newRed, newGreen, newBlue);
	}
	
	public static Color interpolate(TimeFrame timeA, Color colorA, TimeFrame timeZ, Color colorZ, double timeM){
		return interpolate(timeA.getTime(), colorA, timeZ.getTime(), colorZ, timeM);
	}

	/**
	 * Blends two Positions
	 * @param timeA
	 * The time of the first Position
	 * @param positionA
	 * The first Position to be blended
	 * @param timeZ
	 * The time of the second Position
	 * @param positionB
	 * The second Positino to be blended
	 * @param timeM
	 * The time of the desired Position
	 * @return
	 * The Position at the desired time
	 */
	public static Position interpolate(double timeA, Position positionA, double timeZ, Position positionB, double timeM){
		double newX = interpolate(timeA, positionA.getDoubleX(), timeZ, positionB.getDoubleX(), timeM);
		double newY = interpolate(timeA, positionA.getDoubleY(), timeZ, positionB.getDoubleY(), timeM);
		
		return new Position(newX, newY);
	}
	
	public static Position interpolate(TimeFrame timeA, Position positionA, TimeFrame timeZ, Position positionZ, double timeM){
		Position p = interpolate(timeA.getTime(), positionA, timeZ.getTime(), positionZ, timeM);
		return p;
	}
	
	public static boolean between(double value, double min, double max){
		return value >= min && value <= max || value <= min && value >= max;
	}
}
