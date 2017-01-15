import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * This algorithmn decides the social form of fish by calculating the chance for each category from data of fish who swim alone or in a group.
 * The categories are defined by their relation to k.
 * The perfect value for k is between 0.001 and 0.003
 */
public class Main {

    /**
     * Path to the file containing data of fish [alone]
     */
    private static String pathToEvalAlone = Paths.get("", "eval_alone.txt").toAbsolutePath().toString();

    /**
     * Path to the file containing data of fish [group]
     */
    private static String pathToEvalGroup = Paths.get("", "eval_group.txt").toAbsolutePath().toString();

    /**
     * Path to the file containing data of fish [alone]
     */
    private static String pathToTrainAlone = Paths.get("", "train_alone.txt").toAbsolutePath().toString();

    /**
     * Path to the file containing data of fish [group]
     */
    private static String pathToTrainGroup = Paths.get("", "train_group.txt").toAbsolutePath().toString();

    /**
     * Best value for k in the the algorithm
     */
    private static double k = 3.5967;

    /**
     * Calculates the success of the algorithm for fish alone and in a group.
     * @param args
     */
    public static void main(String[] args) {
        List<double[][]> evalAlonePositions = Reader.readPositionsFromFile(pathToEvalAlone);
        List<double[][]> evalGroupPositions = Reader.readPositionsFromFile(pathToEvalGroup);
        List<double[][]> trainAlonePositions = Reader.readPositionsFromFile(pathToTrainAlone);
        List<double[][]> trainGroupsPositions = Reader.readPositionsFromFile(pathToTrainGroup);

        for(double k = 0.0; k <= 20; k+= 0.5) {


            Calculator calc = new Calculator(k);

            double[][] categoriesAlone = calc.getChanceAsLogarithmForEachCategory(trainAlonePositions);
            double[][] categoriesGroup = calc.getChanceAsLogarithmForEachCategory(trainGroupsPositions);

            LinkedList<int[]> statesForSingle = calc.findStatesForEachIndividuum(evalAlonePositions);
            LinkedList<int[]> statesForGroup = calc.findStatesForEachIndividuum(evalGroupPositions);

            Eval eval = new Eval(categoriesAlone, categoriesGroup);

            double alone = eval.checkAccuracy(eval.detectSocialForm(statesForSingle), Eval.ALONE);
            double group = eval.checkAccuracy(eval.detectSocialForm(statesForGroup), Eval.GROUP);

            System.out.println(k);
            System.out.println("Alone: " + alone);
            System.out.println("Group: " + group);

        }


    }


}
