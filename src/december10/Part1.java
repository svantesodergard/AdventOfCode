package december10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1 {
    BufferedReader reader = Files.newBufferedReader(Paths.get("src/december10/input"));
    String nextLine;

    int cycle = 0;
    int signalStrengthSum = 0;
    int x = 1;

    Part1() throws IOException {
        while ((nextLine = reader.readLine()) != null) {
            incrementCycle();
            if (nextLine.contains("noop") || !nextLine.contains("addx")) {
                continue;
            }

            int term = Integer.parseInt(nextLine.substring(nextLine.indexOf(' ') + 1));
            incrementCycle();
            x += term;
        }
        System.out.println(signalStrengthSum);
    }

    public void incrementCycle() {
        cycle++;
        if (cycle % 40 == 20) {
            int signalStrength = cycle*x;
            signalStrengthSum += signalStrength;
        }
    }

    public static void main(String[] args) throws IOException {
        new Part1();
    }


}
