import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Day5 {
    private static final String rulesFile = "inputRules.txt";

    // Map where every index of the rules array corresponds to a list all the elements that can not proceed it
    private static ArrayList<Integer>[] rules = new ArrayList[100];

    private static final String updatesFile = "inputUpdates.txt";

    public static void main(String[] args) {
        for(int i = 0; i < rules.length; i++) {
            rules[i] = new ArrayList<>();
        }
        part1();
        part2();
    }

    private static void part1() {
        try{
            Scanner input = new Scanner(new File(rulesFile));

            while (input.hasNextLine()) {
                String line = input.nextLine();
                rules[Integer.parseInt(line.substring(0,2))].add(Integer.valueOf(line.substring(3)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            Scanner input = new Scanner(new File(updatesFile));
            int total = 0;
            while (input.hasNextLine()) {
                total += returnMiddleIfValid(input.nextLine());
            }
            System.out.println("The total for part 1 is: " + total);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static int returnMiddleIfValid(String line) {
        List<String> updates = Arrays.asList(line.split(","));
        for(int i = 1; i < updates.size(); i++) {
            for(int j = i-1; j >= 0; j--) {
                if(rules[Integer.parseInt(updates.get(i))].contains(Integer.valueOf(updates.get(j)))) {
                    invalidUpdates.add(updates);
                    return 0;
                }
            }
        }
        return Integer.parseInt(updates.get(updates.size()/2));
    }

    // part 2
    private static ArrayList<List<String>> invalidUpdates = new ArrayList<>();

    private static void part2(){
        int part2Total = 0;
        for(int i = 0; i < invalidUpdates.size(); i++) {
            int middle = invalidUpdates.get(i).size()/2;
            for(int j = 0; j < invalidUpdates.get(i).size(); j++) {
                int current = Integer.parseInt(invalidUpdates.get(i).get(j));
                // if numberThatCannotProceed is equal to middle, then current should be added to the total, because it is the middle value
                int numberThatCannotProceed = 0;
                for(int k = 0; k < invalidUpdates.get(i).size(); k++) {
                    if(rules[current].contains(Integer.valueOf(invalidUpdates.get(i).get(k)))) {
                        numberThatCannotProceed++;
                    }
                }
                if(numberThatCannotProceed == middle) {
                    part2Total += current;
                    break;
                }
            }
        }
        System.out.println("The total for part 2 is: " + part2Total);
    }
}
