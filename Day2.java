import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Day2 {
    private static final String file = "input.txt";

    public static void main(String[] args) {
        System.out.println("The number of safe inputs is: " + part1());
        System.out.println("The number of modified safe inputs is: " + part2());
    }

    public static int part1(){
        Scanner input = null;
        try{
            input = new Scanner(new File(file));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        int numSafe = 0;
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] nums = line.split(" ");
            if(part1InOrder(nums) && part1SmallSteps(nums)){
                numSafe++;
            }
        }
        return numSafe;
    }
    private static boolean part1InOrder(String[] nums){
        boolean isIncreasing = Integer.valueOf(nums[0]) < Integer.valueOf(nums[1]);
        if(isIncreasing){
            for(int i = 0; i < nums.length - 1; i++){
                if(Integer.valueOf(nums[i]) >= Integer.valueOf(nums[i+1])){
                    return false;
                }
            }
        } else{
            for(int i = 0; i < nums.length - 1; i++){
                if(Integer.valueOf(nums[i]) <= Integer.valueOf(nums[i+1])){
                    return false;
                }
            }
        }

        return true;
    }
    private static boolean part1SmallSteps(String[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            int diff = Math.abs(Integer.valueOf(nums[i]) - Integer.valueOf(nums[i+1]));
            if(diff > 3 || diff <1){
                return false;
            }
        }
        return true;
    }

    // Part 2

    public static int part2(){
        Scanner input = null;
        try{
            input = new Scanner(new File(file));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        int numSafe = 0;
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] nums = line.split(" ");
            for(int i = 0; i < nums.length; i++){
                ArrayList<String> listNums = new ArrayList<>(Arrays.asList(nums));
                listNums.remove(i);
                if(part1InOrder(listNums.toArray(new String[0])) && part1SmallSteps(listNums.toArray(new String[0]))){
                    numSafe++;
                    break;
                }
            }
        }
        return numSafe;
    }

}
