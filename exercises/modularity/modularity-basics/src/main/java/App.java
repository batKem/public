import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {
        List<Double> numbers = readData();
        List<Double> normalized = normalize(numbers);

        System.out.println(normalized);

        try {
            writeData(normalized);
        } catch (Exception e) {
            System.out.println("Error writing output file");
        }
        System.out.println("Wrote output file.");
        return normalized;
    }

    private static List<Double> readData() throws FileNotFoundException{
        File file = new File("data");
        Scanner scanner = new Scanner(file);
        List<Double> numbers = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            numbers.add(number);
        }
        scanner.close();
        return numbers;
    }

    private static void writeData(List<Double> out) throws IOException {
        FileWriter fw = new FileWriter("output");
        for (double n : out) {
            fw.write(Double.toString(n));
            fw.write("\n");
        }
        fw.close();
    }

    private static List<Double> normalize( List<Double> numbers){
        List<Double> normalized = new ArrayList<>();
        double mean= meanOf(numbers);
        double sumSquare = sumSquare(numbers);

        double std = Math.sqrt(sumSquare / numbers.size());
        for (double f : numbers) {
            normalized.add((f - mean) / std);
        }
        return normalized;
    }

    private static double meanOf(List<Double> numbers){
        double sum = 0;
        for (double f : numbers) {
            sum += f;
        }
        double mean = sum / numbers.size();
        return mean;
    }

    private static double sumSquare( List<Double> numbers){
        double sumSquare = 0;
        for (double f : numbers) {
            double diff = f - meanOf(numbers);
            sumSquare += diff * diff;
        }
        return sumSquare;
    }
}
