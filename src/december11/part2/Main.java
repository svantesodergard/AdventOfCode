package december11.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december11/input"));
        String nextLine;
        List<String> lineGroup = new ArrayList<>();
        List<Monkey> monkeys = new ArrayList<>();
        int leastCommonDenominator = 1;

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
                    Queue<Long> startingItems = new LinkedList<>();
                    int colonIndex = line.indexOf(':');
                    line = line.substring(colonIndex + 2);
                    while (line.indexOf(',') != -1) {
                        startingItems.add(Long.parseLong(line.substring(0, line.indexOf(','))));
                        line = line.substring(line.indexOf(',') + 2);
                    }
                    startingItems.add(Long.parseLong(line));
                    monkey.setItems(startingItems);
                    continue;
                }
                if (line.startsWith("Operation: ")) {
                    monkey.operation = line.substring(line.lastIndexOf("=") + 6);
                    continue;
                }

                String lastWord = line.substring(line.lastIndexOf(' ') + 1);
                if (line.startsWith("Test: ")) {
                    monkey.testIsDivisibleBy = Integer.parseInt(lastWord);
                    leastCommonDenominator *= monkey.testIsDivisibleBy;
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

        for (int i = 0; i < 10_000; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.hasItems()) {
                    Long item = monkey.throwItem();
                    String lastWord = monkey.operation.substring(monkey.operation.lastIndexOf(' ') + 1);
                    Long secondInt = item;
                    if (!lastWord.contains("old")) {
                        secondInt = Long.parseLong(lastWord);
                    }

                    if (monkey.operation.contains("+")) {
                        item = item + secondInt;
                    } else {
                        item = item * secondInt;
                    }

                    int throwToMonkey = monkey.ifFalseThrowToMonkey;
                    if (item % monkey.testIsDivisibleBy == 0) {
                        throwToMonkey = monkey.ifTrueThrowToMonkey;
                    }

                    monkeys.get(throwToMonkey).catchItem(item, leastCommonDenominator);
                    monkey.itemsInspected++;
                }
            }
        }

        List<Long> mostItemsInspected = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            mostItemsInspected.add(monkey.itemsInspected);
        }
        Collections.sort(mostItemsInspected, Collections.reverseOrder());
        System.out.println(mostItemsInspected);
        System.out.println(mostItemsInspected.get(0)*mostItemsInspected.get(1));
    }
}

class Monkey {
    private Queue<Long> items;
    String operation;
    long itemsInspected = 0;
    int testIsDivisibleBy;
    int ifTrueThrowToMonkey;
    int ifFalseThrowToMonkey;

    public Queue<Long> getItems() {
        return items;
    }

    public boolean hasItems() {
        return this.items.size() > 0;
    }

    public void catchItem(Long worryLevel, int leastCommonDenominator) {
        if (worryLevel < 0 || leastCommonDenominator < 0) {
            throw new RuntimeException("You've got reason to be worried");
        }
        worryLevel %= leastCommonDenominator;
        this.items.add(worryLevel);
    }

    public Long throwItem() {
        return this.items.remove();
    }

    public void setItems(Queue<Long> startingItems) {
        this.items = startingItems;
    }
}
