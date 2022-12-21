package december21.part1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/december21/input"));
        for (String s : input) {
            String name = s.substring(0, s.indexOf(':'));
            String operation = s.substring(s.indexOf(' ')+1);
            MonkeyList.add(new Monkey(name, operation));
        }
            System.out.println(MonkeyList.get("root").yellNumber());
    }
}

class Monkey {
    String name;
    String operation;

    public Monkey(String name, String operation) {
        this.name = name;
        this.operation = operation;
    }

    public long yellNumber() {
        if (!this.operation.contains(" ")) {
            return Integer.parseInt(this.operation);
        }

        Monkey[] dependsOn = new Monkey[2];
        char operator;
        dependsOn[0] = MonkeyList.get(operation.substring(0, operation.indexOf(' ')));
        dependsOn[1] = MonkeyList.get(operation.substring(operation.lastIndexOf(' ')+1));
        operator = operation.charAt(operation.indexOf(' ')+1);

        long firstNumber = dependsOn[0].yellNumber();
        long secondNumber = dependsOn[1].yellNumber();
        switch (operator) {
            case '+' -> {
                return firstNumber + secondNumber;
            }
            case '-' -> {
                return firstNumber - secondNumber;
            }
            case '/' -> {
                return firstNumber / secondNumber;
            }
            case '*' -> {
                return firstNumber * secondNumber;
            }
        }

        throw new ArithmeticException("No operator Present");
    }
}

class MonkeyList {
    static List<Monkey> monkeys = new ArrayList<>();

    public static void add(Monkey monkey) {
        MonkeyList.monkeys.add(monkey);
    }

    public static Monkey get(String name) {
        for (Monkey monkey : MonkeyList.monkeys) {
            if (monkey.name.equals(name)) {
                return monkey;
            }
        }
        throw new NoSuchElementException("These aren't the monkeys you're looking for");
    }
}