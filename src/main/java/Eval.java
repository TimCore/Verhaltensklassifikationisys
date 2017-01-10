import java.util.LinkedList;

/**
 * Created by Tim on 10.01.2017.
 */
public class Eval {

    private static final int SINGLE = 0;
    private static final int GROUP = 1;

    private double[][] chancesForCategoriesSingle;
    private double[][] chancesForCategoriesGroup;

    /**
     * Constructor
     * @param chancesForCategoriesSingle
     * @param chancesForCategoriesGroup
     */
    public Eval(double[][] chancesForCategoriesSingle, double[][] chancesForCategoriesGroup){
        this.chancesForCategoriesSingle = chancesForCategoriesSingle;
        this.chancesForCategoriesGroup = chancesForCategoriesGroup;
    }

    /**
     *
     * @param movements
     * @return
     */
    public int[] detectSocialForm(LinkedList<int[]> movements){
        int[] result = new int[movements.size()];
        for(int i = 0; i < movements.size(); i++){
            double single = 0;
            double group = 0;
            for(int j = 0; j < movements.get(i).length-1; j++){
                single += chancesForCategoriesSingle[movements.get(i)[j]][movements.get(i)[j+1]];
                group += chancesForCategoriesGroup[movements.get(i)[j]][movements.get(i)[j+1]];
            }
            if(single > group){
                result[i] = SINGLE;
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
