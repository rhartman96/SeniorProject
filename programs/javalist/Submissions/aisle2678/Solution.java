import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.nextLine();
        LinkedList<Integer> list = Stream.of(input.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        int operationCnt = input.nextInt();
        while (operationCnt > 0){
            operationCnt--;
            String operationName = input.next();
            int index = input.nextInt();
            if (operationName.equals("Insert")){
                int value = input.nextInt();
                list.add(index, value);
            }else{
                list.remove(index);
            }
        }
        list.stream().forEach(value -> System.out.print(value + " "));
    }
}