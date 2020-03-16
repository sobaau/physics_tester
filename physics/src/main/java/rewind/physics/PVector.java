package rewind.physics;

public class PVector {
    public double x;
    public double y;


    /**
     * Creates a vector at (0.0, 0.0)
     */
    public PVector() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Creates a new vector at the provided x and y.
     *
     * @param x the x-coordinates.
     * @param y the y-coordinates.
     */
    public PVector(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;

    }

    /**
     * Creates a new vector at the provided x and y.
     *
     * @param x the x-coordinates.
     * @param y the y-coordinates.
     */
    public PVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a 2D vector from an angle
     *
     * @param angle the angle to create the vector.
     * @return New PVector.
     */
    public static PVector fromAngle(float angle) {

        double x = Math.cos(angle) * 1;
        double y = Math.sin(angle) * 1;

        return new PVector(x, y);
    }

    /**
     * Adds two vectors and returns a new PVector
     *
     * @param vec1 Vector to add.
     * @param vec2 Vector to add.
     * @return new PVector.
     */
    public static PVector add(PVector vec1, PVector vec2) {

        return new PVector(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    /**
     * Adds two vectors and returns a new PVector
     *
     * @param vec1 Vector to sub.
     * @param vec2 Vector to sub.
     * @return new PVector.
     */
    public static PVector sub(PVector vec1, PVector vec2) {

        return new PVector(vec1.x - vec2.x, vec1.y - vec2.y);
    }

    /**
     * Calculates the distance between two vectors.
     *
     * @param vec1 the first PVector.
     * @param vec2 the second PVector.
     * @return The distance between two vectors.
     */
    public static double dist(PVector vec1, PVector vec2) {
        double x = vec1.x - vec2.x;
        x = x * x;

        double y = vec1.y - vec2.y;
        y = y * y;

        return Math.sqrt(x + y);
    }

    /**
     * Compares two PVectors
     *
     * @param vec1 the first PVector.
     * @param vec2 the second PVector.
     * @return returns true if two PVectors have the same x and y components.
     */
    public static boolean equals(PVector vec1, PVector vec2) {
        return ((vec1.x == vec2.x) && (vec1.y == vec2.y));
    }


    /**
     * Compares two PVectors
     *
     * @param vec the first PVector.
     * @return returns true if this and supplied PVector x and y components.
     */
    public boolean equals(PVector vec) {
        return ((this.x == vec.x) && (this.y == vec.y));
    }



    /**
     * Duplicate the vector supplied as the parameter, setting this.PVector to the same x,y as vec
     *
     * @param vec the Vector to be copied.
     */
    public void copy(PVector vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    /**
     * Returns the magnitude of the vector.
     *
     * @return magnitude of the vector.
     */
    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Return the magnitude squared of the vector.
     *
     * @return magnitude squared of the vector.
     */
    public double magSq() {

        return this.mag() * this.mag();
    }

    /**
     * Adds two vectors
     *
     * @param vec Vector to add.
     * @return new PVector.
     */
    public PVector add(PVector vec) {

        return new PVector(this.x + vec.x, this.y + vec.y);
    }

    /**
     * Adds x and y to a vector
     *
     * @param x Number to Add to x
     * @param y Number to Add to y
     * @return New PVector with new x and y.
     */
    // Adds x , y to a vector
    public PVector add(double x, double y) {
        return new PVector(this.x + x, this.y + y);
    }

    /**
     * Adds x and y to a vector
     *
     * @param x Number to Add to x
     * @param y Number to Add to y
     * @return New PVector with new x and y.
     */
    // Adds x , y to a vector
    public PVector add(int x, int y) {
        return this.add((double) x, (double) y);
    }

    /**
     * Subtracts two vectors.
     *
     * @param vec Vector to subtract.
     * @return New PVector.
     */
    public PVector sub(PVector vec) {

        return new PVector(this.x - vec.x, this.y - vec.y);

    }

    /**
     * Subtracts x and y from a vector
     *
     * @param x Number to subtract from x
     * @param y Number to subtract from y
     * @return New PVector with new x and y.
     */
    public PVector sub(double x, double y) {
        return new PVector(this.x - x, this.y - y);
    }

    /**
     * Subtracts x and y from a vector
     *
     * @param x Number to subtract from x
     * @param y Number to subtract from y
     * @return New PVector with new x and y.
     */
    public PVector sub(int x, int y) {
        return this.sub((double) x, (double) y);
    }

    /**
     * Multiplies a vector by a scalar
     *
     * @param scalar the scalar to multiply the vector by.
     */
    public void mult(double scalar) {
        this.x = this.x * scalar;
        this.y = this.y * scalar;
    }

    /**
     * Multiplies a vector by a scalar
     *
     * @param scalar the scalar to multiply the vector by.
     */
    public void mult(int scalar) {
        this.mult((double) scalar);
    }

    /**
     * Calculates the distance between two vectors.
     *
     * @param vec the vector to calculate the distance between.
     * @return The distance between two vectors.
     */
    public double dist(PVector vec) {
        double x = this.x - vec.x;
        x = x * x;

        double y = this.y - vec.y;
        y = y * y;

        return Math.sqrt(x + y);
    }

    /**
     * Calculates the dot product of two vectors
     *
     * @param vec the vector to calculate the dot product.
     * @return The dot product of two vectors
     */
    public double dot(PVector vec) {

        return this.x * vec.x + this.y * vec.y;
    }

    /**
     * Sets the magnitude of a vector
     *
     * @param mag the magnitude.
     */
    public void setMag(double mag) {
        if (this.mag() == 0) { // Ensures a divide by 0 doesn't occur if mag already == 0
            this.x = 0;
            this.y = 0;
        } else {
            double oldMag = this.mag();
            this.x = this.x * mag / oldMag;
            this.y = this.y * mag / oldMag;
        }
    }

    /**
     * Returns an angle of rotation of a vector.
     *
     * @return Angle of rotation.
     */
    public double heading() {
        double angle = Math.atan2(y, x);
        return angle;
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param n the number to divide the vector by.
     * @return Vector divided by scalar.
     * @throws IllegalArgumentException
     */
    public void div(double n) throws IllegalArgumentException {

        if (n == 0) {
            throw new IllegalArgumentException("Argument 'scalar' is 0");
        }

        this.x = this.x / n;
        this.y = this.y / n;
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param n the number to divide the vector by
     * @throws IllegalArgumentException
     */
    public void div(int n) throws IllegalArgumentException {
        if (n == 0) {
            throw new IllegalArgumentException("Argument 'scalar' is 0");
        }
        this.div((double) n);
    }

    /**
     * Normalise a vector to the length of 1.
     *
     * @return Normalised PVector
     */
    public void normalise() {

        if ((this.mag() != 0) && (this.mag() != 1)) {
            this.div(this.mag());
        }
    }

    /**
     * Limit the magnitude of the vector
     *
     * @param n the maximum magnitude
     */

    public void limit(double n) {
        if (this.magSq() > n * n) {
            this.setMag(1);
            this.mult(n);
        }
    }

    /**
     * Limit the magnitude of the vector
     *
     * @param n the maximum magnitude
     */
    public void limit(int n) {

        this.limit((double) n);
    }

    /**
     * Rotates the vector by theta radians
     * @param theta angle of rotation in radians
     * @return Vector of equal length rotated by theta radians
     */
    public PVector rot(double theta){
        double newX = Math.cos(theta)*x - Math.sin(theta)*y;
        double newY = Math.sin(theta)*x + Math.cos(theta)*y;
        return new PVector(newX,newY);
    }


}
