# Java Classes Handbook
By Evan Parker

<a href="#static-vs-non-static">Static vs Non-Static</a><br/>
<a href="#static-vs-non-static">Public vs Private</a><br/>
<a href="#constructors-and-method-overloading">Constructors and Method Overloading</a><br/>
<a href="#default-methods">Default Methods</a><br/>
<a href="#instance-variables-and-multiple-classes">Instance Variables and Multiple Classes</a><br/>
<a href="#inhereted-classes-and-polymorphism">Inhereted Classes and Polymorphism</a><br/>

## Static vs Non-Static
In a class, you can have static and non-static methods and variables. A <b>static</b> variable or method is globally accessible from other classes (if public) or instances of its own class (if public OR private). <b>Non-Static</b> methods and variables are unique to the instance of the class they are  written in. They can be accessed from other classes (if public) from the instance variable, or inside the class with `this.<name>`.

```java
public class Circle {
    // Declaring 2 static variables.
    public static int numberOfCirclesCreated = 0;
    public static final double PI = 3.14159;

    // Declaring 3 non-static variables, unique and modifiable by each instance of Circle.
    public double x, y, radius = 0;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;

        Circle.numberOfCirclesCreated += 1;
    }

    public double getDiameter() {
        return this.radius * 2;
    }

    public double getArea() {
        // We can see an example of a static and non-static variable here.
        return Circle.PI * (this.radius * this.radius);
    }
}

public class Client {
    public static void main(String[] args) {
        Circle c1 = new Circle(0, 0, 5);
        Circle c2 = new Circle(15, -25, 12.5);

        // We can view the number of circles created because it is static.
        System.out.println("There are " + Circle.numberOfCirclesCreated + " circles!");

        // Here, we can only see the radius for this circle through the "c1" variable.
        System.out.println(c1.radius + ": " + c1.getDiameter() + " - " + c1.getArea());
    }
}
```

## Public vs Private
## Constructors and Method Overloading
## Default Methods
## Instance Variables and Multiple Classes
## Inhereted Classes and Polymorphism