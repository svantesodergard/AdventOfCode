package december15;

import com.sun.nio.sctp.SendFailedNotification;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/december15/input"));
        List<Point> beacons = new ArrayList<>();
        List<Point> notBeacon = new ArrayList<>();
        final int interestingRow = 2_000_000;

        for (String line : lines) {
            Point sensor = getPoint(line.substring(0, line.indexOf(':')));
            Point beacon = getPoint(line.substring(line.indexOf(':') + 1));
            int distance = getDistanceBetween(sensor, beacon);

            if (beacon.y == interestingRow && !beacons.contains(beacon)) {
                beacons.add(beacon);
            }

            int shortestSignificantDistance = Math.abs(interestingRow - sensor.y);
            if (distance >= shortestSignificantDistance) {
                int width = distance - shortestSignificantDistance;
                for (int dx = -width; dx <= width; dx++) {
                    Point pointToAdd = new Point(sensor.x + dx, interestingRow);
                    if (!notBeacon.contains(pointToAdd)) {
                        notBeacon.add(pointToAdd);
                    }
                }
            }
        }

        for (Point beacon : beacons) {
            notBeacon.remove(beacon);
        }

        System.out.println(notBeacon);
        System.out.println(notBeacon.size());
    }

    public static int getDistanceBetween(Point start, Point end) {
        int distance = 0;
        Point current = new Point(start.x, start.y);

        while (!current.equals(end)) {
            distance++;
            if (current.x > end.x) current.x--;
            else if (current.x < end.x) current.x++;
            else if (current.y > end.y) current.y--;
            else if (current.y < end.y) current.y++;
        }
        return distance;
    }

    public static Point getPoint(String line) {
        line = line.trim();
        int x = Integer.parseInt(line.substring(line.indexOf('=') + 1, line.indexOf(',')));
        int y = Integer.parseInt(line.substring(line.lastIndexOf('=') + 1));
        return new Point(x, y);
    }
}
