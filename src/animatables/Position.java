package animatables;

/**
 * A class representing an object's Position in 2-D space, to be used with the Animator class
 * @author Adam
 */
public class Position {
	/**
	 * The x-position of the object
	 */
	private double x;
	
	/**
	 * The y-position of the object
	 */
	private double y;
	
	/**
	 * Constructs a Position object with the given X- and Y- positions
	 * @param initialX
	 * The initial x-position of the object
	 * @param initialY
	 * The initial y-position of the object
	 */
	public Position(int initialX, int initialY){
		x = initialX;
		y = initialY;
	}
	
	public Position(double initialX, double initialY){
		this((int) initialX, (int) initialY);
	}
	
	/**
	 * Gets an object's x-position in 2-D space
	 * @return
	 * The x-position of the object
	 */
	public int getX(){
		return (int) x;
	}
	
	/**
	 * Gets an object's y-position in 2-D space
	 * @return
	 * The y-position of the object
	 */
	public int getY(){
		return (int) y;
	}
	
	public double getDoubleX(){
		return x;
	}
	
	public double getDoubleY(){
		return y;
	}
	
	/**
	 * Sets the x-position of the object to a new value
	 * @param newX
	 * The new x-position to be set
	 */
	public void setX(int newX){
		x = newX;
	}
	
	/**
	 * Sets the y-position of the object to a new value
	 * @param newY
	 * The new y-position to be set
	 */
	public void setY(int newY){
		y = newY;
	}
	
	public String toString(){
		return x + " " + y;
	}
}