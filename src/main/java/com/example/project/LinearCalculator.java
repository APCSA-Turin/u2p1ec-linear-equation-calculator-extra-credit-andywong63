package com.example.project;

public class LinearCalculator {
    // FOR EXTRA CREDIT
    // you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

    // INSTANCE VARIABLES
    // 4 INTEGER variables (name them: x1,x2,y1,y2)
    int x1;
    int x2;
    int y1;
    int y2;

    // CONSTRUCTOR
    // 1 constructor with 2 String parameters. Each parameter represents a
    // coordinate.
    // For example, "(1,2)" and "(3,4)" would be two parameter values
    // You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2) {
        int openParenIndex = coord1.indexOf("(");
        int closeParenIndex = coord1.indexOf(")");
        int commaIndex = coord1.indexOf(",");
        x1 = Integer.parseInt(coord1.substring(openParenIndex + 1, commaIndex));
        y1 = Integer.parseInt(coord1.substring(commaIndex + 1, closeParenIndex));
        openParenIndex = coord2.indexOf("(");
        closeParenIndex = coord2.indexOf(")");
        commaIndex = coord2.indexOf(",");
        x2 = Integer.parseInt(coord2.substring(openParenIndex + 1, commaIndex));
        y2 = Integer.parseInt(coord2.substring(commaIndex + 1, closeParenIndex));
    }

    // METHODS
    // getters and setters for the 4 instance variables (8 methods total)
    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    // distance() -> returns a double.
    // calculates the distance between the two points to the nearest HUNDREDTH and
    // returns the value.
    public double distance() {
        // sqrt((x2-x1)^2 + (y2-y1)^2)
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        double xDiffSquared = Math.pow(xDiff, 2);
        double yDiffSquared = Math.pow(yDiff, 2);
        return roundedToHundredth(Math.sqrt(xDiffSquared + yDiffSquared));
    }

    // yInt() -> returns a double.
    // calculates the y intercept of the equation and returns the value to the
    // nearest HUNDREDTH
    // if y-int if undefined, should return -999.99
    public double yInt() {
        // y=mx+b
        // y-mx=b
        // y1 = slope() * x1 + b
        // b = y1 - slope() * x1
        if (x2 - x1 == 0) {
            return -999.99;
        }
        return roundedToHundredth(y1 - slope() * x1);
    }

    // slope() -> returns a double.
    // calculates the slope of the equations and returns the value to the nearest
    // HUNDREDTH
    // if slope is undefined, should return -999.99
    public double slope() {
        // rise/run
        double deltaY = y2 - y1;
        double deltaX = x2 - x1;
        if (deltaX == 0) {
            return -999.99;
        }
        return roundedToHundredth(deltaY / deltaX);
    }

    // equations() -> returns a String.
    // calculates the final equation in y=mx+b form and returns the string
    // if the equation has no slope, the equation should return -> "undefined"
    // HINT: You may need other custom methods to decrease the amount of code in the
    // equations() method
    public String equation() {
        if (x2 - x1 == 0) {
            return "undefined";
        }
        String equation = "y=";
        if (slope() != 0) {
            equation += slope() + "x";
            if (yInt() > 0) {
                equation += "+";
            }
        }
        if (yInt() != 0) {
            equation += yInt();
        }
        return equation;
    }

    // roundedToHundredth(double x)-> returns double
    // calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x) {
        return Math.round(x * 100) / 100.0;
    }

    // You will need to concatenate to the string
    // the results from findSymmetry() and Midpoint()
    public String printInfo() {
        String str = "The two points are: (" + x1 + "," + y1 + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n" + findSymmetry();
        str += "\n" + Midpoint();
        return str;
    }

    // findSymmetry()-> returns a string
    // the method should determine if there is symmetry between the two points
    // there should be 4 return statements
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    // return "Symmetric about the origin";
    // return "No symmetry";
    public String findSymmetry() {
        boolean sameX = x1 == x2;
        boolean sameY = y1 == y2;
        boolean oppositeX = x1 == -x2;
        boolean oppositeY = y1 == -y2;
        if (sameX && oppositeY) { // (4, 3) (4, -3)
            return "Symmetric about the x-axis";
        }
        if (oppositeX && sameY) { // (4, 3) (-4, 3)
            return "Symmetric about the y-axis";
        }
        if (oppositeX && oppositeY) { // (4, 3) (-4, -3)
            return "Symmetric about the origin";
        }
        return "No symmetry";
    }

    // Midpoint()->return a string
    // the method should calculate the midpoint between the two points
    // it should return "The midpoint of this line is: (0,0)";
    public String Midpoint() {
        double midpointX = (x1 + x2) / 2.0;
        double midpointY = (y1 + y2) / 2.0;
        return "The midpoint of this line is: (" + midpointX + "," + midpointY + ")";
    }

}