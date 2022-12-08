package year2021.december3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class part1 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december3/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        String nextLine = reader.readLine();
        int[] bitCounts = new int[nextLine.length()]; //if this is bigger than 0, 1s are more common

        do {
            for (int i = 0; i < bitCounts.length; i++) {
                bitCounts[i] += Integer.parseInt(nextLine.substring(i, i+1)) == 1 ? 1 : -1;
            }
        } while ((nextLine = reader.readLine()) != null);

        String gammaRate = "", epsilonRate = "";
        for (int i = 0; i < bitCounts.length; i++) {
            gammaRate += bitCounts[i] > 0 ? "1" : "0";
            epsilonRate += bitCounts[i] < 0 ? "1" : "0";
        }
        System.out.println(Integer.parseInt(gammaRate, 2)*Integer.parseInt(epsilonRate, 2));
    }
}
