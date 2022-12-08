package year2021.december2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part2 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december2/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        String nextLine;
        int horizontalPosition = 0, depth = 0;
        int aim = 0;
        while ((nextLine = reader.readLine()) != null) {
            int fromFile = Integer.parseInt(nextLine.substring(nextLine.indexOf(' ') + 1));
            if (nextLine.startsWith("forward")) {
                horizontalPosition += fromFile;
                depth += fromFile * aim;
            } else if (nextLine.startsWith("down")) {
                aim += fromFile;
            } else if (nextLine.startsWith("up")) {
                aim -= fromFile;
            }
        }
        System.out.println(horizontalPosition * depth);
    }
}
