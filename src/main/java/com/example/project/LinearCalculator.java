package com.example.project;

/**
 * A calculator to get information about two coordinates and the line formed between them
 * @author Andy
 */
public class LinearCalculator {
    // FOR EXTRA CREDIT
    // you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

    /** The x value of the first coordinate */
    private int x1;
    /** The y value of the first coordinate */
    private int x2;
    /** The x value of the second coordinate */    
    private int y1;
    /** The y value of the second coordinate */
    private int y2;

    /**
     * Creates a new LinearCalculator instance
     * @param coord1 The first coordinate as a string (no spaces, ex: "(0,1)")
     * @param coord2 The second coordinate as a string (no spaces, ex: "(0,1)")
     */
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
    /**
     * Getter method for the x value of the first coordinate
     * @return The x value
     */
    public int getX1() {
        return x1;
    }

    /**
     * Getter method for the y value of the first coordinate
     * @return The y value
     */
    public int getY1() {
        return y1;
    }

    /**
     * Getter method for the x value of the second coordinate
     * @return The x value
     */
    public int getX2() {
        return x2;
    }

    /**
     * Getter method for the y value of the second coordinate
     * @return The y value
     */
    public int getY2() {
        return y2;
    }

    /**
     * Setter method for the x value of the first coordinate
     * @param x1 The new x value
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * Setter method for the y value of the first coordinate
     * @param y1 The new y value
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * Setter method for the x value of the second coordinate
     * @param x2 The new x value
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Setter method for the y value of the second coordinate
     * @param y2 The new y value
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * Calculates and returns the distance between the two points
     * @return The distance between the points, rounded to the nearest hundredth
     */
    public double distance() {
        // sqrt((x2-x1)^2 + (y2-y1)^2)
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        double xDiffSquared = Math.pow(xDiff, 2);
        double yDiffSquared = Math.pow(yDiff, 2);
        return roundedToHundredth(Math.sqrt(xDiffSquared + yDiffSquared));
    }

    /**
     * Calculates and returns the y-intercept of the line produced from the two points
     * @return The y-intercept, rounded to the nearest hundredth, or <code>-999.99</code> if undefined
     */
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

    /**
     * Calculates and returns the slope of the line produced from the two points
     * @return The slope, rounded to the nearest hundredth, or <code>-999.99</code> if undefined
     */
    public double slope() {
        // rise/run
        double deltaY = y2 - y1;
        double deltaX = x2 - x1;
        if (deltaX == 0) {
            return -999.99;
        }
        return roundedToHundredth(deltaY / deltaX);
    }

    /**
     * Calculates and returns the equation of the line produced by the two points
     * @return The equation of the line, in the form y=mx+b, or <code>undefined</code> if the slope is undefined
     */
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

    /**
     * Rounds the input to the nearest hundredth
     * @param x The number to be rounded
     * @return The rounded number
     */
    public double roundedToHundredth(double x) {
        return Math.round(x * 100) / 100.0;
    }

    /**
     * Returns information about the two points and the line produced
     * <p>Information returned:
     * <ul>
     * <li>The two points
     * <li>The equation of the line produced
     * <li>The slope of the line
     * <li>The y-intercept of the line
     * <li>The distance between the points
     * <li>The symmetry between the points
     * <li>The midpoint of the points
     * </ul>
     * @return The string containing the information
     */
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

    /**
     * Finds and returns the symmetry between the two points, if any
     * @return String showing if the points are symmetric or not
     */
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

    /**
     * Finds and returns the midpoint of the two points, as a string
     * @return A sentence showing the midpoint
     */
    public String Midpoint() {
        double midpointX = (x1 + x2) / 2.0;
        double midpointY = (y1 + y2) / 2.0;
        return "The midpoint of this line is: (" + midpointX + "," + midpointY + ")";
    }

}