import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static final String INSERT = "Insert";
    public static final String DELETE = "Delete";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = new ArrayList<Integer>();
        
        int size = Integer.parseInt(scanner.nextLine());
        String strInput = scanner.nextLine();
        input = parseStringInput(strInput);
        int numQueries = Integer.parseInt(scanner.nextLine());

        start(scanner, input);
        
        System.out.println(print(input));
    }
    
    public static void start(Scanner scanner, List<Integer> input){
        
        while(scanner.hasNext()){
            String operation = scanner.nextLine();
            String params = scanner.nextLine();
            switch(operation){
                case Solution.INSERT:
                    String[] arrayParams = params.split(" ");
                    insert(input, Integer.parseInt(arrayParams[0]), Integer.parseInt(arrayParams[1]));
                break;
                case Solution.DELETE:
                    delete(input, Integer.parseInt(params));
            }
        }
    }
    
    public static void insert (List<Integer> input, int index, int value){
        input.add(index, value);
    }
    
    public static void delete(List<Integer> input, int index){
        input.remove(index);    
    }
    
    public static List<Integer> parseStringInput(String strInput){
        String[] arrayInput = strInput.split(" ");

        //Map an array of Strings to an array of ints
        Integer[] integerTemp = 
        Arrays.stream(arrayInput).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);        
        
        //Convert an array of Integers to a list of Integers
        List<Integer> input = Arrays.stream(integerTemp).collect(Collectors.toList());
        
        return input;
    }
    
    public static String print(List<Integer> input){
        //Convert a list of integers to an array of integers.
        Integer[] integerTemp = input.stream().toArray(Integer[]::new);
        
        //Map an array of integers to an array of ints.
        int[] outcome = Arrays.stream(integerTemp).mapToInt(Integer::intValue).toArray();
        
        return Arrays.toString(outcome).replaceAll("\\[|\\]|,","");
    }
}