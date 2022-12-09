package december9;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/december9/input"));
        String nextLine;

        Point headPosition = new Point(0, 0);
        Point tailPosition = new Point(0, 0);
        List<Point> tailPositions = new ArrayList<>();
        tailPositions.add(new Point(tailPosition));

        while ((nextLine = reader.readLine()) != null) {
            char direction = nextLine.charAt(0);
            int spacesToMove = Integer.parseInt(nextLine.substring(2));
            for (int i = 0; i < spacesToMove; i++) {
                //Move Head
                switch (direction) {
                    case 'R' -> headPosition.x += 1;
                    case 'L' -> headPosition.x -= 1;
                    case 'U' -> headPosition.y += 1;
                    case 'D' -> headPosition.y -= 1;
                }

                int differenceX = (headPosition.x - tailPosition.x);
                int differenceY = (headPosition.y - tailPosition.y);

                if (Math.abs(differenceX) <= 1 && Math.abs(differenceY) <= 1) {
                    //Adjacent to the head do nothing
                    continue;
                }

                if (differenceX >= 1) {
                    tailPosition.x++;
                } else if (differenceX <= -1) {
                    tailPosition.x--;
                }

                if (differenceY >= 1) {
                    tailPosition.y++;
                } else if (differenceY <= -1) {
                    tailPosition.y--;
                }

                if (!tailPositions.contains(tailPosition)) {
                    tailPositions.add(new Point(tailPosition));
                }
            }
        }

        System.out.println(tailPositions.size());
    }
}
