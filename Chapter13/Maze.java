package Chapter13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
    public static void main(String[] args) {
        char maze[][] = readMazeFromFile();

        // Print the maze out
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

        // Find index of $
        int x2 = -1, y2 = -1;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == '$') {
                    x2 = i;
                    y2 = j;
                    break;
                }
            }
        }

        System.out.println("x2: " + x2 + ", y2: " + y2);

        List<int[]> path = findPath(maze, 1, 1, x2, y2);

        for (int[] point : path) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }
    }

    public static char[][] readMazeFromFile() {
        char maze[][] = null;

        File file = new File(System.getProperty("user.dir"), "/Chapter13/maze.txt");

        int width = 0, height = 0;

        try (Scanner input = new Scanner(file)) {
            height = input.nextInt();
            width = input.nextInt();

            input.nextLine();

            maze = new char[height][width];

            for (int i = 0; i < height; i++) {
                if (!input.hasNextLine()) {
                    break;
                }
            
                String line = input.nextLine();
            
                if (line.isEmpty()) {
                    break;
                }
            
                for (int j = 0; j < width; j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return maze;
    }

    public static List<int[]> findPath(char[][] maze, int x1, int y1, int x2, int y2) {
        List<int[]> path = new ArrayList<>();
    
        if (x1 == x2 && y1 == y2) {
            path.add(new int[]{x1, y1});
            return path;
        }
    
        maze[x1][y1] = '*'; // no repeat lmao
    
        if (x1 < maze.length - 1 && maze[x1 + 1][y1] != '#' && maze[x1 + 1][y1] != '*') {
            List<int[]> subPath = findPath(maze, x1 + 1, y1, x2, y2);
            if (subPath != null) {
                path.add(new int[]{x1, y1});
                path.addAll(subPath);
                return path;
            }
        }
    
        if (x1 > 0 && maze[x1 - 1][y1] != '#' && maze[x1 - 1][y1] != '*') {
            List<int[]> subPath = findPath(maze, x1 - 1, y1, x2, y2);
            if (subPath != null) {
                path.add(new int[]{x1, y1});
                path.addAll(subPath);
                return path;
            }
        }
    
        if (y1 < maze[0].length - 1 && maze[x1][y1 + 1] != '#' && maze[x1][y1 + 1] != '*') {
            List<int[]> subPath = findPath(maze, x1, y1 + 1, x2, y2);
            if (subPath != null) {
                path.add(new int[]{x1, y1});
                path.addAll(subPath);
                return path;
            }
        }
    
        if (y1 > 0 && maze[x1][y1 - 1] != '#' && maze[x1][y1 - 1] != '*') {
            List<int[]> subPath = findPath(maze, x1, y1 - 1, x2, y2);
            if (subPath != null) {
                path.add(new int[]{x1, y1});
                path.addAll(subPath);
                return path;
            }
        }
    
        return null; // no path found
    }
}