package days;

import java.io.FileNotFoundException;

public class Day12 extends Day {
    char[] instructions;
    int currentDir = 0;
    int currentXpos = 0;
    int currentYpos = 0;
    int waypointX = 10;
    int waypointY = 1;
    int[] xDirections = new int[]{1,0,-1,0};
    int[] yDirections = new int[]{0,-1,0,1};
    int[] values;

    public Day12(String filename) throws FileNotFoundException {
        super(filename);
        instructions = new char[data.size()];
        values = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            instructions[i] = s.charAt(0);
            values[i] = Integer.parseInt(s.substring(1));
        }
    }

    public void move() {
        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i] == 'N') move(0, values[i]);
            else if (instructions[i] == 'S') move(0, -values[i]);
            else if (instructions[i] == 'E') move(values[i], 0);
            else if (instructions[i] == 'W') move(-values[i], 0);
            else if (instructions[i] == 'F') move(values[i]*xDirections[currentDir], values[i]*yDirections[currentDir]);
            else turn(instructions[i], values[i]);
        }
        System.out.printf("%d, %d\n", currentXpos, currentYpos);
    }

    public void moveWaypoint() {
        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i] == 'N') moveWaypoint(0, values[i]);
            else if (instructions[i] == 'S') moveWaypoint(0, -values[i]);
            else if (instructions[i] == 'E') moveWaypoint(values[i], 0);
            else if (instructions[i] == 'W') moveWaypoint(-values[i], 0);
            else if (instructions[i] == 'F') move(values[i]*waypointX, values[i]*waypointY);
            else {
                turnWaypoint(instructions[i], values[i]);
            }
            System.out.printf("%s waypoint: %d, %d position: %d, %d\n", data.get(i), waypointX, waypointY, currentXpos, currentYpos);
        }
        System.out.printf("%d, %d\n", currentXpos, currentYpos);
    }

    private void move(int x, int y) {
        currentXpos+=x;
        currentYpos+=y;
    }

    private void moveWaypoint(int x, int y) {
        waypointX+=x;
        waypointY+=y;
    }

    private void turn(char direction, int degrees) {
        int d = (degrees/90)%4;
        if (direction == 'L') d = -d;
        currentDir+=d;
        if (currentDir < 0) currentDir+=4;
        if (currentDir > 3) currentDir-=4;
    }

    private void turnWaypoint(char direction, int degrees) {
        int d = (degrees/90)%4;
        if (direction == 'L') d = -d;
        if (d < 0) d+=4;
        for (int i = 0; i < d; i++) {
            int temp = waypointX;
            waypointX = waypointY;
            waypointY = -temp;
        }
    }
}
