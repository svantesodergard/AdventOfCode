package december9;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december9/input"));
        String nextLine;

        Point[] knots = new Point[10];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new Point();
        }

        List<Point> tailPositions = new ArrayList<>();
        tailPositions.add(new Point(knots[9]));

        while ((nextLine = reader.readLine()) != null) {
            char direction = nextLine.charAt(0);
            int spacesToMove = Integer.parseInt(nextLine.substring(2));
            for (int i = 0; i < spacesToMove; i++) {
                //Move Head
                Point headPosition = knots[0];
                switch (direction) {
                    case 'R' -> headPosition.x += 1;
                    case 'L' -> headPosition.x -= 1;
                    case 'U' -> headPosition.y += 1;
                    case 'D' -> headPosition.y -= 1;
                }

                for (int j = 1; j < knots.length; j++) {
                    Point currentKnotPosition = knots[j];
                    Point previousKnotPosition = knots[j - 1];
                    int differenceX = (previousKnotPosition.x - currentKnotPosition.x);
                    int differenceY = (previousKnotPosition.y - currentKnotPosition.y);

                    if (Math.abs(differenceX) <= 1 && Math.abs(differenceY) <= 1) {
                        //Adjacent to the previous knot do nothing
                        continue;
                    }

                    if (differenceX >= 1) {
                        currentKnotPosition.x++;
                    } else if (differenceX <= -1) {
                        currentKnotPosition.x--;
                    }

                    if (differenceY >= 1) {
                        currentKnotPosition.y++;
                    } else if (differenceY <= -1) {
                        currentKnotPosition.y--;
                    }
                }
                Point tailPosition = knots[9];

                if (!tailPositions.contains(tailPosition)) {
                    tailPositions.add(new Point(tailPosition.x, tailPosition.y));
                }
            }
        }

        System.out.println(tailPositions);
        System.out.println(tailPositions.size());
    }
}
