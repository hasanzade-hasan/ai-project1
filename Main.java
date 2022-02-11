import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            // read data from file
            Scanner scanner = new Scanner(file);
            String[] tokens = scanner.nextLine().split(",");
            int[] pitchers = new int[tokens.length];

            for (int i = 0; i < tokens.length; i++) {
                pitchers[i] = Integer.parseInt(tokens[i]);
            }
            // target capacity of infinite pitcher
            int target = Integer.parseInt(scanner.nextLine());

            // define new search problem with given capacities
            SearchProblem problem = new SearchProblem(pitchers, target);

            // print solution
            System.out.println(problem.solve());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}