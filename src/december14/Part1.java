package december14;

import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    Part1() throws IOException {
        List<Point> rocks = new ArrayList<>();
        List<String> readLines = Files.readAllLines(Paths.get("src/december14/input"));

        for (String line : readLines) {
            List<Point> coordinates = new ArrayList<>();
            while (!line.isEmpty()) {
                String coordinate = line;
                if (line.indexOf(' ') != -1) {
                    coordinate = line.substring(0, line.indexOf(' ')).trim();
                }

                if (coordinate.charAt(0) != '-') {
                    int x = Integer.parseInt(coordinate.substring(0, coordinate.indexOf(',')));
                    int y = Integer.parseInt(coordinate.substring(coordinate.indexOf(',') + 1));
                    coordinates.add(new Point(x, y));
                }

                if (line.indexOf(' ') != -1) {
                    line = line.substring(line.indexOf(' ')).trim();
                } else {
                    line = "";
                }
                System.out.println(line);
            }

            for (int i = 1; i < coordinates.size(); i++) {
                Point start = coordinates.get(i - 1);
                Point end = coordinates.get(i);

                if (start.x == end.x) {
                    int lowestY = Math.min(start.y, end.y);
                    int highestY = Math.max(start.y, end.y);
                    for (int j = lowestY; j <= highestY; j++) {
                        Point pointToAdd = new Point(start.x, j);
                        if (!rocks.contains(pointToAdd)) {
                            rocks.add(pointToAdd);
                        }
                    }
                }
                if (start.y == end.y) {
                    int lowestX = Math.min(start.x, end.x);
                    int highestX = Math.max(start.x, end.x);
                    for (int j = lowestX; j <= highestX; j++) {
                        Point pointToAdd = new Point(j, start.y);
                        if (!rocks.contains(pointToAdd)) {
                            rocks.add(pointToAdd);
                        }
                    }
                }
            }
        }

        //Sand is pouring from 500, 0
        Point sandStart = new Point(500 ,0);
        List<Point> sand = new ArrayList<>();
        while (!sand.contains(new Point(500, 1))) {
            Point location = new Point(500, 0);
            while (true) {
                location.y++;
                if (!rocks.contains(location) && !sand.contains(location)) {
                    continue;
                }
                location.x--;
                if (!rocks.contains(location) && !sand.contains(location)) {
                    continue;
                }
                location.x += 2;
                if (!rocks.contains(location) && !sand.contains(location)) {
                    continue;
                }

                location.x--;
                location.y--;
                sand.add(location);
                System.out.println(sand.size());
                System.out.println(sand.get(sand.size() -1));
                break;
            }
        }
        System.out.println(sand);
    }

    public static void main(String[] args) throws IOException {
        new Part1();
    }
}
