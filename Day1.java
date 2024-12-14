import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class Day1 {

    public static void main(String[] args){
        System.out.println("The solution to part 1 is: " + part1());
        System.out.println("The solution to part 2 is: " + part2());
    }


    public static int part1(){
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        Scanner input = null;
        try {
            input = new Scanner(new File("inputText.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(input.hasNextLine()){
            list1.add(input.nextInt());
            list2.add(input.nextInt());
        }
        list1.sort(null);
        list2.sort(null);
        int total = 0;
        while(list1.size() > 0){
            total += Math.abs(list1.removeFirst() - list2.removeFirst());
        }
        return total;
    }

    public static int part2(){
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(new File("inputText.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(input.hasNextLine()){
            list1.add(input.nextInt());
            list2.add(input.nextInt());
        }
        int similarityScore = 0;
        while(list1.size() > 0){
            int current = list1.removeLast();
            int count = 0;
            for(int i = 0; i < list2.size(); i++){
                if(current == list2.get(i)){
                    count ++;
                }
            }
            similarityScore += count*current;
        }
        return similarityScore;
    }
}
