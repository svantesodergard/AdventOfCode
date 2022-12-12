package december12;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    List<String> lines = Files.readAllLines(Paths.get("src/december12/input"));
    char[][] map;
    int[][] shortestKnownPath;
    Point start = new Point();
    Point end = new Point();
    public Part1() throws IOException {
        map = new char[lines.get(0).length()][lines.size()];
        shortestKnownPath = new int[lines.get(0).length()][lines.size()];

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                map[x][y] = lines.get(y).charAt(x);
                shortestKnownPath[x][y] = Integer.MAX_VALUE;
                if (map[x][y] == 'S') {
                    map[x][y] = 'a';
                    shortestKnownPath[x][y] = 0;
                    start = new Point(x, y);
                } else if (map[x][y] == 'E') {
                    map[x][y] = 'z';
                    end = new Point(x, y);
                }
            }
        }

        setShortestPathForAdjacentNodes(start);
        System.out.println(shortestKnownPath[end.x][end.y]);
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
        new Part1();
    }
}