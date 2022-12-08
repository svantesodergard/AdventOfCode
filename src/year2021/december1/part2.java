package year2021.december1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part2 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december1/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        List<Integer> depths = new ArrayList<>();
        String nextLine;
        int depthIncreasedCounter = 0;

        for (int i = 0; i < 3; i++) {
            if ((nextLine = reader.readLine()) != null) {
                depths.add(Integer.parseInt(nextLine));
            }
        }

        while ((nextLine = reader.readLine()) != null) {
            depths.add(Integer.parseInt(nextLine));

            int currentWindowDepth = 0, previousWindowDepth = 0;
            for (int i = depths.size() - 1; i >= depths.size() - 3; i--) {
                currentWindowDepth += depths.get(i);
                previousWindowDepth += depths.get(i - 1);
            }

            if (currentWindowDepth > previousWindowDepth) {
                depthIncreasedCounter++;
            }
        }

        System.out.println(depthIncreasedCounter);
    }
}
