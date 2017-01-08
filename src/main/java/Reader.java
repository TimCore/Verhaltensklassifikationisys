import java.io.FileReader;
import java.io.BufferedReader;
import java.util.LinkedList;

/**
 * Reads the files and returns the contained positions
 */
public class Reader {

    /**
     * Reads the file at filepath and returns its Positions as a list of two-dimensional double-arrays with [Position]and x at [0], y at [1]
     * @param filepath the path to the file to be read
     * @return  Positions as a list of two-dimensional double-arrays
     */
    public static LinkedList<double[][]> readPositionsFromFile(String filepath){

       LinkedList<double[][]> buffer = new LinkedList<double[][]>();

       try{
           FileReader fr = new FileReader(filepath);
           BufferedReader br = new BufferedReader(fr);
           for (String line = br.readLine(); line != null; line = br.readLine()){
                String[] sep = line.split(";");
                double[][] row = new double[sep.length][2];
                for(int i = 0; i < sep.length; i++){
                    String buf[] = sep[i].split(",");
                    row[i][0] = Double.parseDouble(buf[0]);
                    row[i][1] = Double.parseDouble(buf[1]);
                }
                buffer.add(row);
           }
       }catch (Exception e){
            System.out.println("File not found or problem while reading");
       }
       return buffer;
    }
}
