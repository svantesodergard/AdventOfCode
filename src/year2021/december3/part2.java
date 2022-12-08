package year2021.december3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class part2 {
    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src/year2021/december3/input");
        BufferedReader reader = Files.newBufferedReader(inputFile);

        String nextLine;
        List<String> binaryValues = new ArrayList<>();

        while ((nextLine = reader.readLine()) != null) {
            binaryValues.add(nextLine);
        }

        List<String> possibleOxygenRatings = new ArrayList<>(binaryValues);
        List<String> possibleScrubberRatings = new ArrayList<>(binaryValues);
        int bitsFiltered = 0;
        while (possibleOxygenRatings.size() != 1) {
            int bitCount = 0;
            for (String possibleOxygenRating : possibleOxygenRatings) {
                char currentBit = possibleOxygenRating.charAt(bitsFiltered);
                bitCount += currentBit == '1' ? 1 : -1;
            }

            char mostCommonBit = bitCount >= 0 ? '1' : '0';

            List<String> toBeRemoved = new ArrayList<>();
            for (String s : possibleOxygenRatings) {
                if (s.charAt(bitsFiltered) != mostCommonBit) {
                    toBeRemoved.add(s);
                }
            }
            possibleOxygenRatings.removeAll(toBeRemoved);
            bitsFiltered++;
        }
        bitsFiltered = 0;
        while (possibleScrubberRatings.size() != 1) {
            int bitCount = 0;
            for (String possibleScrubberRating : possibleScrubberRatings) {
                char currentBit = possibleScrubberRating.charAt(bitsFiltered);
                bitCount += currentBit == '1' ? 1 : -1;
            }

            char mostCommonBit = bitCount >= 0 ? '1' : '0';

            List<String> toBeRemoved = new ArrayList<>();
            for (String s : possibleScrubberRatings) {
                if (s.charAt(bitsFiltered) == mostCommonBit) {
                    toBeRemoved.add(s);
                }
            }
            possibleScrubberRatings.removeAll(toBeRemoved);
            bitsFiltered++;
        }

        System.out.println(Integer.parseInt(possibleScrubberRatings.get(0), 2)*Integer.parseInt(possibleOxygenRatings.get(0), 2));

    }
}
