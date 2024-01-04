import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtParser {

    public static Dataset parse(String filePath) {

        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        List<Double> output = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split("\t");

                x.add(Double.parseDouble(columns[0]));
                y.add(Double.parseDouble(columns[1]));
                output.add(Double.parseDouble(columns[2]));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Dataset(x, y, output);
    }
}

