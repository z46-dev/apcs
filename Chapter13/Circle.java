package Chapter13;

@SuppressWarnings("rawtypes")
public class Circle implements Comparable {

	private double radius;
	private static final double PI = 3.14;
	
	public Circle() {
		
	}
	
	public Circle(double radius) {
		this.radius = radius;
	}	
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double area() {
		return PI * radius * radius;
	}
		
	public double circumference() {
		return 2 * PI * radius;
	}

	public static void displayAreaFormula() {
		System.out.print("The area formula of a circle is PI * radius * radius");
	}
	
	public boolean equals(Object c) {
		Circle testObj = (Circle)c;
		
		if (testObj.getRadius() == radius) {
			return true;
		} else {
			return false;
		}		
	}

	public int compareTo(Object c) {
		Circle testCircle = (Circle)c;

		if (radius < testCircle.getRadius()) {
			return(-1);
		} else if (radius == testCircle.getRadius()) {
			return(0);
		} else {
			return(1);
		}
	}

	public String toString() {
		String information = "The radius of the circle is: " + radius;
		return information;
    }
}