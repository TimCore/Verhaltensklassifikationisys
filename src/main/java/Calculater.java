/**
 * Created by Tim on 08.01.2017.
 */
public class Calculater {

    /**
     * 1. category : A
     */
    public static final int A = 1;

    /**
     * 2. category : B
     */
    public static final int B = 2;

    /**
     * 3. category : C
     */
    public static final int C = 3;

    /**
     * 1. status, (A,A)
     */
    public static final int AA = 11;

    /**
     * 2. status, (A,B)
     */
    public static final int AB = 12;

    /**
     * 3. status, (A,C)
     */
    public static final int AC = 13;

    /**
     * 4. status, (B,A)
     */
    public static final int BA = 21;

    /**
     * 5. status, (B,B)
     */
    public static final int BB = 22;

    /**
     * 6. status, (B,C)
     */
    public static final int BC = 23;

    /**
     * 7. status, (C,A)
     */
    public static final int CA = 31;

    /**
     * 8. status, (C,B)
     */
    public static final int CB = 32;

    /**
     * 9. status, (C,C)
     */
    public static final int CC = 33;

    /**
     * The threshold value to categorize the difference of the vectors
     */
    private int k;

    /**
     * An array used to count the occurence of all states
     */
    private int[] categoryCounter;

    /**
     * Constructor
     * @param k: The threshold value to categorize the difference of the vectors
     */
    public Calculater(int k){
        this.k = k;
        categoryCounter = new int[9];
    }

    /**
     * calculates a vector from two positions
     * @param first     startposition
     * @param second    targetposition
     * @return  vector with [0] = x, [1] = y
     */
    public double[] getVector(double[] first, double[] second){
        double[] v = {second[0] - first[0], second[1] - first[1]};
        return v;
    }

    /**
     * Calculates the difference of two vectors
     * @param first     first vector
     * @param second    second vector
     * @return  difference (vector2_x - vector1_x, vector2_y - vector1_y)
     */
    public double[] getDifference(double[] first, double[] second){
        double[] v = {second[0] - first[0], second[1] - first[1]};
        return v;
    }

    /**
     *
     * @param difference
     */
    public void findStatusOfDifference(double[] difference){


    }

}
