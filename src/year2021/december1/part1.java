package year2021.december1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part1 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december1/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        String nextLine = reader.readLine();
        int previousDepth = Integer.parseInt(nextLine);
        int depthIncreasedCounter = 0;
        while ((nextLine = reader.readLine()) != null) {
            int depth = Integer.parseInt(nextLine);
            if (depth > previousDepth) {
                depthIncreasedCounter++;
            }
            previousDepth = depth;
        }
        System.out.println(depthIncreasedCounter);
    }
}
