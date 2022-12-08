package year2021.december2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part1 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december2/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        String nextLine;
        int horizontalPosition = 0, depth = 0;
        while ((nextLine = reader.readLine()) != null) {
            int unitsToMove = Integer.parseInt(nextLine.substring(nextLine.indexOf(' ') + 1));
            if (nextLine.startsWith("forward")) {
                horizontalPosition += unitsToMove;
            } else if (nextLine.startsWith("down")) {
                depth += unitsToMove;
            } else if (nextLine.startsWith("up")) {
                depth -= unitsToMove;
            }
        }
        System.out.println(horizontalPosition * depth);
    }
}
