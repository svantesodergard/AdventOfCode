package december8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december8/input"));
        String nextLine;
        List<String> lines = new ArrayList<>();

        while ((nextLine = reader.readLine()) != null) {
            lines.add(nextLine);
        }

        int visibleTrees = 0;
        for (int y = 0; y < lines.size(); y++) {
            String row = lines.get(y);

            for (int x = 0; x < row.length(); x++) {
                int height = Integer.parseInt(row.substring(x, x+1));
                String treesLeft = row.substring(0, x);
                String treesRight = row.substring(x + 1);
                String treesUp = "";
                for (int i = y - 1; i >= 0; i--) {
                    treesUp += lines.get(i).charAt(x);
                }
                String treesDown = "";
                for (int i = y + 1; i < lines.size(); i++) {
                    treesDown += lines.get(i).charAt(x);
                }

                if (height > largestTreeInString(treesLeft) || height > largestTreeInString(treesRight)
                        || height > largestTreeInString(treesUp) || height > largestTreeInString(treesDown)) {
                    visibleTrees++;
                }
            }
        }
        System.out.println(visibleTrees);
    }
    public static int largestTreeInString(String string) {
        int largestTree = -1;
        for (int i = 0; i < string.length(); i++) {
            int height = Integer.parseInt(string.substring(i, i+1));
            if (height > largestTree) {
                largestTree = height;
            }
        }
        return largestTree;
    }
}