package december20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        //Initialize
        List<String> lines = Files.readAllLines(Paths.get("src/december20/test"));
        List<Integer> numbers = new ArrayList<>();
        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }

        List<Integer> mixedNumbers = new LinkedList<>(numbers);
        for (int j = 0; j < numbers.size(); j++) {
            Integer number = numbers.get(j);
            for (int i = 0; i < Math.abs(number); i++) {
                int index = mixedNumbers.indexOf(number);
                int indexToReplace = number > 0 ? index+1 : index-1;

                if (indexToReplace == -1) {
                    indexToReplace = numbers.size() - 1;
                } else if (indexToReplace == numbers.size()) {
                    indexToReplace = 0;
                }

                int integerToReplace = mixedNumbers.get(indexToReplace);
                mixedNumbers.set(index, integerToReplace);
                mixedNumbers.set(indexToReplace, number);
            }
            System.out.println(mixedNumbers);
        }
        System.out.println(mixedNumbers);
    }
}
