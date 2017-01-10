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

    private int k = 5; //TODO Double verwenden?

    public static void main(String[] args){
        List<double[][]> evalAlonePositions = Reader.readPositionsFromFile(pathToEvalAlone);
        List<double[][]> evalGroupPositions = Reader.readPositionsFromFile(pathToEvalGroup);
        List<double[][]> trainAlonePositions = Reader.readPositionsFromFile(pathToTrainAlone);
        List<double[][]> trainGroupsPositions = Reader.readPositionsFromFile(pathToTrainGroup);

        Calculator calc = new Calculator(5);
        double[] categoriesAlone = calc.getChanceForEachCategoryInPercent(evalAlonePositions);
        for(int i = 0; i < categoriesAlone.length; i++){
            System.out.println(i+1 + ": " + categoriesAlone[i]);
        }

        System.out.println();
        System.out.println("-------------------------");
        System.out.println();


        double[] categoriesGroup = calc.getChanceForEachCategoryInPercent(evalGroupPositions);
        for(int i = 0; i < categoriesGroup.length; i++){
            System.out.println(i+1 + ": " + categoriesGroup[i]);
        }

        //train-files auslesen und damit algorithmus "fuettern".

        // dann k wert bestimmen anhand der werte fuer eval-files



    }




}
