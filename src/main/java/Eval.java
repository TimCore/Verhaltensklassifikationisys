import java.util.LinkedList;

/**
 * Calculates the efficiency of the algorithmn
 */
public class Eval {

    public static final int ALONE = 0;
    public static final int GROUP = 1;

    /**
     * 9x9 Array containing the chance for mvoving from each categorie to another alone
     */
    private double[][] chancesForCategoriesAlone;

    /**
     * 9x9 Array containing the chance for mvoving from each categorie to another in a group
     */
    private double[][] chancesForCategoriesGroup;

    /**
     * Constructor
     * @param chancesForCategoriesAlone
     * @param chancesForCategoriesGroup
     */
    public Eval(double[][] chancesForCategoriesAlone, double[][] chancesForCategoriesGroup){
        this.chancesForCategoriesAlone = chancesForCategoriesAlone;
        this.chancesForCategoriesGroup = chancesForCategoriesGroup;
    }

    /**
     *  Calculates for each fish in the list if its more likely it is in a group or alone.
     *  Adds the chance for the status of each movement in both social forms and chooses the higher value afterwards.
     * @param movements List of movements
     * @return array containing alone or group
     */
    public int[] detectSocialForm(LinkedList<int[]> movements){
        int[] result = new int[movements.size()];
        for(int i = 0; i < movements.size(); i++){
            double alone = 0.0;
            double group = 0.0;
            for(int j = 1; j < movements.get(i).length-1; j++){

                alone += chancesForCategoriesAlone[movements.get(i)[j]][movements.get(i)[j+1]];
                group += chancesForCategoriesGroup[movements.get(i)[j]][movements.get(i)[j+1]];
            }
            if(alone > group){
                result[i] = ALONE;
            }else{
                result[i] = GROUP;
            }
        }
        return result;
    }

    /**
     * calculates the accuracy of the algorithm by dividing the correct results by the total amount
     * @param result        the detected social form for each fish
     * @param socialForm    single fish (0) or fish in a group (1)
     * @return              accuracy of the algorithm for the data
     */
    public double checkAccuracy(int[] result, int socialForm){
        int total = 0;
        int correct = 0;
        for(int r : result){
            total++;
            if(r == socialForm)correct++;
        }
        double accuracy = (double) correct / (double) total;
        return accuracy;
    }

}
