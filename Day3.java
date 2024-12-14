import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day3 {
    static File file = new File("input.txt");
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1(){
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Pattern p = Pattern.compile("mul\\((?<var1>[0-9]{1,3}),(?<var2>[0-9]{1,3})\\)");
        Matcher m = null;
        int total = 0;
        while (input.hasNextLine()) {
            m = p.matcher(input.nextLine());
            while(m.find()){
                total += Integer.parseInt(m.group("var1")) * Integer.parseInt(m.group("var2"));
            }
        }
        System.out.println("The total for part 1 is: " + total);

    }

    private static void part2(){
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String puzzleInput = "";
        while (input.hasNextLine()) {
            puzzleInput += (input.nextLine());
        }

        Pattern p = Pattern.compile("mul\\((?<var1>[0-9]{1,3}),(?<var2>[0-9]{1,3})\\)");
        Matcher m = null;
        int total = 0;

        int lowerRegionBounds = 0;
        int upperRegionBounds = puzzleInput.indexOf("don't()");

        m = p.matcher(puzzleInput);
        while (upperRegionBounds != -1 && lowerRegionBounds != -1) {
            m.region(lowerRegionBounds, upperRegionBounds);
            while(m.find()){
                total += Integer.parseInt(m.group("var1")) * Integer.parseInt(m.group("var2"));
            }
            lowerRegionBounds = puzzleInput.indexOf("do()", upperRegionBounds);
            upperRegionBounds = puzzleInput.indexOf("don't()", lowerRegionBounds);

        }
        if(lowerRegionBounds != -1){
            m.region(lowerRegionBounds, puzzleInput.length());
            while(m.find()){
                total += Integer.parseInt(m.group("var1")) * Integer.parseInt(m.group("var2"));
            }
        }
        System.out.println("The total for part 2 is: " + total);
    }
}
