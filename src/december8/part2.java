package december8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december8/input"));
        String nextLine;
        int bestScenicScore = 0;
        List<String> lines = new ArrayList<>();

        while ((nextLine = reader.readLine()) != null) {
            lines.add(nextLine);
        }

        for (int y = 0; y < lines.size(); y++) {
            String row = lines.get(y);

            for (int x = 0; x < row.length(); x++) {
                char tree = row.charAt(x);
                int height = Integer.parseInt(tree+"");
                StringBuilder treesLeft = new StringBuilder(row.substring(0, x));
                treesLeft.reverse();
                String treesRight = row.substring(x + 1);

                StringBuilder treesUp = new StringBuilder();
                for (int i = y - 1; i >= 0; i--) {
                    treesUp.append(lines.get(i).charAt(x));
                }
                String treesDown = "";
                for (int i = y + 1; i < lines.size(); i++) {
                    treesDown += lines.get(i).charAt(x);
                }

                String[] treesArray = {treesLeft.toString(), treesUp.toString(), treesRight, treesDown};
                int scenicScore = 1;
                for (String trees : treesArray) {
                    int viewingDistance = trees.length();
                    for (int i = 0; i < trees.length(); i++) {
                        int treeThatMightBeBlockingVisionHeight = Integer.parseInt(trees.charAt(i)+"");
                        if (treeThatMightBeBlockingVisionHeight >= height) {
                            viewingDistance = i + 1;
                            System.out.println(viewingDistance);
                            break;
                        }
                    }
                    scenicScore *= viewingDistance;
                }
                System.out.println(scenicScore);
                if (scenicScore > bestScenicScore) {
                    bestScenicScore = scenicScore;
                }
            }
        }
        System.out.println(bestScenicScore);
    }
}