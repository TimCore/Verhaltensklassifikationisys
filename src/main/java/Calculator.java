import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tim on 08.01.2017.
 */
public class Calculator {


    /**
     * 1. status, (A,A)
     */
    public static final int AA = 0;

    /**
     * 2. status, (A,B)
     */
    public static final int AB = 1;

    /**
     * 3. status, (A,C)
     */
    public static final int AC = 2;

    /**
     * 4. status, (B,A)
     */
    public static final int BA = 3;

    /**
     * 5. status, (B,B)
     */
    public static final int BB = 4;

    /**
     * 6. status, (B,C)
     */
    public static final int BC = 5;

    /**
     * 7. status, (C,A)
     */
    public static final int CA = 6;

    /**
     * 8. status, (C,B)
     */
    public static final int CB = 7;

    /**
     * 9. status, (C,C)
     */
    public static final int CC = 8;

    /**
     * The threshold value to categorize the difference of the vectors
     */
    private int k;

    /**
     * An array used to count the occurence of all states for all data in a file
     */
    private int[] categoryCounterAll;

    private int[][] categoryToCategory;

    /**
     * A list used to save the occurence of all states for every single fish-data
     */
    private List<int[]> categoryCounterForEachLine;

    /**
     * Used to count the number of differences
     */
    private double counter;

    /**
     * Constructor
     * @param k: The threshold value to categorize the difference of the vectors
     */
    public Calculator(int k){
        this.k = k;
        categoryCounterAll = new int[9];
        categoryCounterForEachLine = new LinkedList<>();
        this.counter = 0;
        categoryToCategory = new int[9][9];
    }

    // TODO Wahrscheinlichkeiten ausgeben fuer Uebergang von Differnence zu Difference
    // -> 9 x 9 Matrix, AA zu AA, AA zu AB, ...


    /**
     * Calculates the
     * @param positions
     * @return
     */
    public double[][] getChanceForEachCategoryInPercent(List<double[][]> positions){

        double[][] chanceForEachCategory = new double[9][9];
        findTransitionsBetweenCategories(positions);
        for(int i = 0; i < 9; i++ ){
            for(int j = 0; j < 9; j++ ){
                chanceForEachCategory[i][j] = categoryToCategory[i][j] / counter;
            }
        }
        return chanceForEachCategory;
    }

    /**
     * Initializes an array with the amount of all transitions from one status to another and increments a counter for the total amount of transitions
     * @param positions A list containing all positions
     */
    public void findTransitionsBetweenCategories(List<double[][]> positions){ //TODO hiermit 9 x 9 Array befuellen. status holen, in zeile gehen, anderen status holen und in spalte gehen. dann inc

        List<double[][]> differences = getDifferencesAsList(positions);

        for(int i = 0; i < differences.size(); i++){  //for all lines in the file (all fish)
            for(int j = 0; j < differences.get(i).length-1; j++){
                categoryToCategory[findStatusOfDifference(differences.get(i)[j])][findStatusOfDifference(differences.get(i)[j+1])]++;
                counter++;
            }
        }
    }


    /**
     * Calculates the differences for the whole txt-file from a list of positions
     * @param positions List of Positions
     * @return differences as List
     */
    private LinkedList<double[][]> getDifferencesAsList(List<double[][]> positions){

        //create list of vectors from list of positions
        LinkedList<double[][]> vectorList = new LinkedList<>();
        for(int i = 0; i < positions.size(); i++){ //for each line in the list of positions
            double[][] vectors = new double[positions.get(i).length-1][2];
            for(int j = 0; j < positions.get(i).length-1; j++){
                vectors[j] = getVector(positions.get(i)[j], positions.get(i)[j+1]);
            }
            vectorList.add(vectors);
        }
        //create list of differences from list of vectors
        LinkedList<double[][]> differenceList = new LinkedList<>();
        for(int i = 0; i < vectorList.size(); i++){ //for each line of the vectorList
            double[][] differences = new double[vectorList.get(i).length-1][2];
            for(int j = 0; j < vectorList.get(i).length-1; j++){
                differences[j] = getDifference(vectorList.get(i)[j], vectorList.get(i)[j+1]);
            }
            differenceList.add(differences);
        }

        return differenceList;
    }

    /**
     * calculates a vector from two positions
     * @param first     startposition
     * @param second    targetposition
     * @return  vector with [0] = x, [1] = y
     */
    private double[] getVector(double[] first, double[] second){
        double[] v = {second[0] - first[0], second[1] - first[1]};
        return v;
    }

    /**
     * Calculates the difference of two vectors
     * @param first     first vector
     * @param second    second vector
     * @return  difference (vector2_x - vector1_x, vector2_y - vector1_y)
     */
    private double[] getDifference(double[] first, double[] second){
        double[] v = {second[0] - first[0], second[1] - first[1]};
        return v;
    }

    /**
     *  Calculates the status of the difference by comparing them to k
     * @param difference    the difference to be calculated
     * @return the status as an integer
     */
    private int findStatusOfDifference(double[] difference){
        double x = difference[0];
        double y = difference[1];

        if(x < -k){
            if(y < -k) return AA;
            if(-k <= y && y <= k ) return AB;
            if(y > k)return AC;
        }else if(-k <= x && x <= k){
            if(y < -k) return BA;
            if(-k <= y && y <= k ) return BB;
            if(y > k)return BC;
        }else if(x > k){
            if(y < -k) return CA;
            if(-k <= y && y <= k ) return CB;
            if(y > k)return CC;
        }
        return 9;
    }

}
