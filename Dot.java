//Ling Fang
//CSC221 Assignment 5
//Dot.java
package application;

//Dot class for storing records as objects
public class Dot {
	private double x;
	private double y;
	private double radius;

	// initializes a Dot with default values
	public Dot() {
		this(0.0, 0.0, 0.0);
	}

	// initializes a Dot with privided values
	public Dot(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	// 3 getters and 3 setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
