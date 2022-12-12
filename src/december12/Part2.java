package december12;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {
    List<String> lines = Files.readAllLines(Paths.get("src/december12/input"));
    char[][] map;
    int[][] shortestKnownPath;
    int[][] unknownPath;
    List<Point> possibleStartPoints = new ArrayList<>();
    Point end = new Point();
    public Part2() throws IOException {
        map = new char[lines.get(0).length()][lines.size()];
        unknownPath = new int[lines.get(0).length()][lines.size()];

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                map[x][y] = lines.get(y).charAt(x);
                unknownPath[x][y] = Integer.MAX_VALUE;
                if (map[x][y] == 'S') {
                    map[x][y] = 'a';
                } else if (map[x][y] == 'E') {
                    map[x][y] = 'z';
                    end = new Point(x, y);
                }

                if (map[x][y] == 'a') {
                    possibleStartPoints.add(new Point(x, y));
                }
            }
        }

        List<Integer> fewestStepsPerStartPoint = new ArrayList<>();
        for (Point possibleStartPoint : possibleStartPoints) {
            shortestKnownPath = unknownPath;
            shortestKnownPath[possibleStartPoint.x][possibleStartPoint.y] = 0;
            setShortestPathForAdjacentNodes(possibleStartPoint);
            fewestStepsPerStartPoint.add(shortestKnownPath[end.x][end.y]);
        }

        Collections.sort(fewestStepsPerStartPoint);
        System.out.println(fewestStepsPerStartPoint.get(0));
    }

    private void setShortestPathForAdjacentNodes(Point node) {
        int centerX = node.x;
        int centerY = node.y;

        int shortestPath = shortestKnownPath[centerX][centerY] + 1;

        for (int i = 0; i < 4; i++) {
            int adjacentX = centerX;
            int adjacentY = centerY;
            switch (i) {
                case 0 -> adjacentX += 1;
                case 1 -> adjacentX -= 1;
                case 2 -> adjacentY += 1;
                case 3 -> adjacentY -= 1;
            }

            if (adjacentX < 0 || adjacentX >= map.length || adjacentY < 0 || adjacentY >= map[0].length) {
                continue;
            }

            if (map[adjacentX][adjacentY] > map[centerX][centerY] + 1) {
                continue;
            }

            if (shortestPath < shortestKnownPath[adjacentX][adjacentY]) {
                shortestKnownPath[adjacentX][adjacentY] = shortestPath;
                setShortestPathForAdjacentNodes(new Point(adjacentX, adjacentY));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Part2();
    }
}