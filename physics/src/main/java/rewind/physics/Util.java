package rewind.physics;

/*********************************************************************************************************************
 *
 *  Util.java
 *
 *  Public API for various utility and helper classes 
 *
 *
 *
 *
 *
 *
 * *******************************************************************************************************************/

public class Util {

    public static double remap(double value, double start1, double stop1, double start2, double stop2) throws ArithmeticException {
        if (stop1 - start1 == 0 || (stop2 - start2) + start2 == 0)
            throw new ArithmeticException("Divide by zero exception!!");

        return ((value - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }

    /**
     * Limits a value to not exceed a given minimum or maximum value.
     *
     * @param i   Value to Limit.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return The value limited to a range.
     */
    public static double constrain(double i, double min, double max) {
        if (i > max) {
            return max;
        } else if (i < min) {
            return min;
        } else {
            return i;
        }
    }

    /**
     * Limits a value to not exceed a given minimum or maximum value.
     *
     * @param i   Value to Limit.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return The value limited to a range.
     */
    public static double constrain(int i, int min, int max) {
        return constrain((double) i, (double) min, (double) max);
    }
}