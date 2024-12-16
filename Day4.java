import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    private static final String file = "input.txt";
    private static final ArrayList<ArrayList<Character>> map = new ArrayList<>();
    private static final String pattern = "MAS";

    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        Scanner input = null;
        try {
            input = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while (input.hasNextLine()) {
            String line = input.nextLine();
            ArrayList<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            map.add(row);
        }
        int numMatches = 0;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if (map.get(y).get(x) == 'X') {
                    if (wordSearcher(-1, -1, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(0, -1, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(1, -1, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(-1, 0, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(1, 0, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(-1, 1, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(0, 1, x, y)) {
                        numMatches++;
                    }
                    if (wordSearcher(1, 1, x, y)) {
                        numMatches++;
                    }
                }
            }
        }
        System.out.println("Number of matches for part 1: " + numMatches);
    }

    private static boolean wordSearcher(int xAdd, int yAdd, int startX, int startY) {
        for (int i = 0; i < pattern.length(); i++) {
            startX += xAdd;
            startY += yAdd;
            int maxX = map.get(0).size();
            if (startY < 0 || startY >= map.size() || startX < 0 || startX >= maxX) {
                return false;
            }
            char currentChar = map.get(startY).get(startX);
            if (currentChar != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    //part 2
    public static void part2() {
        int numMatches = 0;
        for (int y = 1; y < map.size()-1; y++) {
            for (int x = 1; x < map.get(y).size()-1; x++) {
                //check if A in middle
                if(map.get(y).get(x) == 'A') {
                    //Check if top right and bottom left are opposite and valid
                    if(map.get(y+1).get(x+1) != map.get(y-1).get(x-1) && "MS".indexOf(map.get(y+1).get(x+1)) != -1 && "MS".indexOf(map.get(y-1).get(x-1)) != -1) {
                        //check if top left and bottom right are opposite and valid
                        if(map.get(y+1).get(x-1) != map.get(y-1).get(x+1) && "MS".indexOf(map.get(y+1).get(x-1)) != -1 && "MS".indexOf(map.get(y-1).get(x+1)) != -1) {
                            numMatches++;
                        }
                    }
                }
            }

        }
        System.out.println("Number of matches for part 2: " + numMatches);
    }
}