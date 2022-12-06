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
        //input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        //input = "nppdvjthqldpwncqszvftbrmjlhg";

        int characterCount = 14;
        for (int i = characterCount - 1; i < input.length(); i++) {
            String currentCharacters = input.substring(i - 13, i + 1);
            boolean isMarker = true;
            for (int j = 0; j < currentCharacters.length(); j++) {
                if (currentCharacters.lastIndexOf(currentCharacters.charAt(j)) != j) {
                    isMarker = false;
                    break;
                }
            }
            if (isMarker) {
                break;
            }
            characterCount++;
        }
        System.out.println(characterCount);
    }

}
