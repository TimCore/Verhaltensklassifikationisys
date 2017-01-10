import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tim on 06.01.2017.
 */
public class Main {

    private static String pathToEvalAlone = Paths.get("", "eval_alone.txt").toAbsolutePath().toString();
    private static String pathToEvalGroup = Paths.get("", "eval_group.txt").toAbsolutePath().toString();
    private static String pathToTrainAlone = Paths.get("", "train_alone.txt").toAbsolutePath().toString();
    private static String pathToTrainGroup = Paths.get("", "train_group.txt").toAbsolutePath().toString();

    //private int k = 5; //TODO Double verwenden?

    public static void main(String[] args){
        List<double[][]> evalAlonePositions = Reader.readPositionsFromFile(pathToEvalAlone);
        List<double[][]> evalGroupPositions = Reader.readPositionsFromFile(pathToEvalGroup);
        List<double[][]> trainAlonePositions = Reader.readPositionsFromFile(pathToTrainAlone);
        List<double[][]> trainGroupsPositions = Reader.readPositionsFromFile(pathToTrainGroup);

        for(double k = -4; k <= 20; k+= 0.1){

            Calculator calc = new Calculator(k);

            double[][] categoriesAlone = calc.getChanceForEachCategoryInPercent(trainAlonePositions);
            double[][] categoriesGroup = calc.getChanceForEachCategoryInPercent(trainGroupsPositions);

            LinkedList<int[]> statesForSingle = calc.findStatesForEachIndividuum(evalAlonePositions);
            LinkedList<int[]> statesForGroup = calc.findStatesForEachIndividuum(evalGroupPositions);

            Eval eval = new Eval(categoriesAlone, categoriesGroup);

            double alone = eval.checkAccuracy(eval.detectSocialForm(statesForSingle), 0);
            double group = eval.checkAccuracy(eval.detectSocialForm(statesForGroup), 1);

            if(alone >= 0.8 && group >= 0.8){
                System.out.println(k);
                System.out.println("Alone: " + alone);
                System.out.println("Group: " + group);
            }
        }





    }




}
