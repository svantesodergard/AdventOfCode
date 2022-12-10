package december10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part2 {
    BufferedReader reader = Files.newBufferedReader(Paths.get("src/december10/input"));
    String nextLine;

    int cycle = 0;
    StringBuilder currentRow = new StringBuilder();
    int x = 1;

    Part2() throws IOException {
        while ((nextLine = reader.readLine()) != null) {
            incrementCycle();
            if (nextLine.contains("noop") || !nextLine.contains("addx")) {
                continue;
            }

            int term = Integer.parseInt(nextLine.substring(nextLine.indexOf(' ') + 1));
            incrementCycle();
            x += term;
        }
        System.out.println(currentRow);
    }

    public void incrementCycle() {
        cycle++;
        int drawPixelAt = cycle % 40 - 1;
        if (drawPixelAt == 0) {
            System.out.println(currentRow);
            currentRow = new StringBuilder();
        }

        if (Math.abs(drawPixelAt - x) <= 1) {
            currentRow.append('#');
        } else {
            currentRow.append(' ');
        }
    }

    public static void main(String[] args) throws IOException {
        new Part2();
    }


}
