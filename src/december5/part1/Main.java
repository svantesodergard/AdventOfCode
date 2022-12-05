package december5.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
    Path inputFile = Paths.get("src/december5/input");
    BufferedReader reader = Files.newBufferedReader(inputFile);
    String nextLine;
    List<Stack<Character>> stacks = new LinkedList<>();
    public Main() throws IOException {
        while ((nextLine = reader.readLine()) != null) {
            if (!nextLine.contains("move")) {
                readStack();
            } else {
                List<Integer> spaceIndexes = indexesOf(nextLine, ' ');
                int itemsToMove = Integer.parseInt(nextLine.substring(spaceIndexes.get(0) + 1, spaceIndexes.get(1)));
                int fromStackIndex = Integer.parseInt(nextLine.substring(spaceIndexes.get(2) + 1, spaceIndexes.get(3)));
                int toStackIndex = Integer.parseInt(nextLine.substring(spaceIndexes.get(4) + 1));

                for (int i = 0; i < itemsToMove; i++) {
                    stacks.get(toStackIndex).push(stacks.get(fromStackIndex).pop());
                }
            }
        }
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < stacks.size(); i++) {
            message.append(stacks.get(i).lastElement());
        }
        System.out.println(message);
    }

    public void readStack() {
        List<Integer> indexesOfSquareBraces = indexesOf(nextLine, '[');
        for (Integer indexOfSquareBrace : indexesOfSquareBraces) {
            int indexOfItemInStack = indexOfSquareBrace + 1;
            char itemToPlaceInStack = nextLine.charAt(indexOfItemInStack);
            int stackIndex = indexOfItemInStack / 4 + 1;

            Stack<Character> stackToPlaceItemIn;
            try {
                stackToPlaceItemIn = stacks.get(stackIndex);
            } catch (IndexOutOfBoundsException boundsException) {
                stackToPlaceItemIn = new Stack<>();
            }

            stackToPlaceItemIn.add(0, itemToPlaceInStack);

            while (stacks.size() <= stackIndex) {
                stacks.add(new Stack<>());
            }
            stacks.set(stackIndex, stackToPlaceItemIn);
        }
    }

    public List<Integer> indexesOf(String string, char character) {
        List<Integer> indexes = new ArrayList<>();
        int index = string.indexOf(character);
        while (index >= 0) {
            indexes.add(index);
            index = string.indexOf(character, index + 1);
        }
        return indexes;
    }
}