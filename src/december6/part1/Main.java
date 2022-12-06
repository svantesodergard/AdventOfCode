package december6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("src/december6/input");
        BufferedReader reader = Files.newBufferedReader(file);
        String input = reader.readLine();
        input = "bvwbjplbgvbhsrlpgdmjqwftvncz";

        int characterCount = 3;
        for (int i = 3; i < input.length(); i++) {
            boolean isMarker = false;
            for (int j = 1; j < 3; j++) {
                if (input.charAt(i) == input.charAt(i - j)) {
                    isMarker = true;
                    break;
                }
            }
        }
        System.out.println(characterCount);
    }

}
