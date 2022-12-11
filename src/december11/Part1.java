package december11;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december11/input"));
        String nextLine;
        List<String> lineGroup = new ArrayList<>();
        List<Monkey> monkeys = new ArrayList<>();
        while ((nextLine = reader.readLine()) != null) {
            if (!nextLine.isBlank()) {
                lineGroup.add(nextLine.trim());
            }
            if (lineGroup.size() != 6) {
                continue;
            }

            Monkey monkey = new Monkey();
            for (String line : lineGroup) {
                if (line.startsWith("Starting items: ")) {
                    Queue<Integer> startingItems = new LinkedList<>();
                    int colonIndex = line.indexOf(':');
                    line = line.substring(colonIndex + 2);
                    while (line.indexOf(',') != -1) {
                        startingItems.add(Integer.parseInt(line.substring(0, line.indexOf(','))));
                        line = line.substring(line.indexOf(',') + 2);
                    }
                    startingItems.add(Integer.parseInt(line));
                    monkey.items = startingItems;
                    continue;
                }
                if (line.startsWith("Operation: ")) {
                    monkey.operation = line.substring(line.lastIndexOf("=") + 6);
                    continue;
                }

                String lastWord = line.substring(line.lastIndexOf(' ') + 1);
                if (line.startsWith("Test: ")) {
                    monkey.testIsDivisibleBy = Integer.parseInt(lastWord);
                }
                if (line.startsWith("If true")) {
                    monkey.ifTrueThrowToMonkey = Integer.parseInt(lastWord);
                }
                if (line.startsWith("If false")) {
                    monkey.ifFalseThrowToMonkey = Integer.parseInt(lastWord);
                }
            }
            monkeys.add(monkey);

            lineGroup.clear();
        }

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.items.size() > 0) {
                    int item = monkey.items.remove();
                    String lastWord = monkey.operation.substring(monkey.operation.lastIndexOf(' ') + 1);
                    int secondInt = item;
                    if (!lastWord.contains("old")) {
                        secondInt = Integer.parseInt(lastWord);
                    }

                    if (monkey.operation.contains("+")) {
                        item = item + secondInt;
                    } else {
                        item = item * secondInt;
                    }
                    monkey.itemsInspected++;

                    item = item / 3;

                    int throwToMonkey = monkey.ifFalseThrowToMonkey;
                    if (item % monkey.testIsDivisibleBy == 0) {
                        throwToMonkey = monkey.ifTrueThrowToMonkey;
                    }
                    monkeys.get(throwToMonkey).items.add(item);
                }
            }
        }

        List<Integer> mostItemsInspected = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            mostItemsInspected.add(monkey.itemsInspected);
        }
        Collections.sort(mostItemsInspected, Collections.reverseOrder());
        System.out.println(mostItemsInspected.get(0)*mostItemsInspected.get(1));
    }
}

class Monkey {
    Queue<Integer> items;
    String operation;
    int itemsInspected = 0;
    int testIsDivisibleBy;
    int ifTrueThrowToMonkey;
    int ifFalseThrowToMonkey;
}
