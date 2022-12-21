package december13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part1 {
    public static void main(String[] args) throws IOException {
        new Part1();
    }

    Part1() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src/december13/test"));
        for (int i = 1; i < allLines.size(); i+=3) {
            List<Object> left = listFrom(allLines.get(i));
            List<Object> right = listFrom(allLines.get(i - 1));
        }
    }


    List<Object> listFrom(String string) {
        List<Object> list = new ArrayList<>();

        string = string.substring(1, string.length()-1);

        while (!string.isBlank()) {
            int endOfObjectIndex = string.indexOf(',');

            if (string.charAt(0) == '[') {
                int openingBrackets = 1;
                //Find closing bracket
                for (int i = 1; i < string.length(); i++) {
                    if (string.charAt(i) == '[') {
                        openingBrackets++;
                    } else if (string.charAt(i) == ']') {
                        openingBrackets--;
                    }
                    if (openingBrackets == 0) {
                        endOfObjectIndex = i;
                        break;
                    }
                }
            }


            String object;
            if (endOfObjectIndex == -1) {
                object = string;
                string = "";
            } else {
                object = string.substring(0, endOfObjectIndex);
                string = string.substring(endOfObjectIndex + 1);
            }

            try {
                if (string.charAt(0) == ',') {
                    string = string.substring(1);
                }
            } catch (StringIndexOutOfBoundsException e) {
                string = string.trim();
            }


            list.add(objectFrom(object.trim()));
        }
        return list;
    }

    private Object objectFrom(String string) {
        if (string.charAt(0) == '[') {
            return listFrom(string + "]");
        } else {
            return Integer.parseInt(string);
        }
    }
}
