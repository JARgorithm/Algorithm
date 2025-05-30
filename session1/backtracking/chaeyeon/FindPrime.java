package backtracking.chaeyeon;

import java.util.*;

public class FindPrime {
    static Set<Integer> result = new HashSet<>();
    static boolean[] visited;

    public int findPrime(String numbers) {
        visited = new boolean[numbers.length()];
        dfs(numbers, "",0);

        return result.size();

    }

    public void dfs(String numbers, String num, int depth){
        if (!num.isEmpty()){
            int value = Integer.parseInt(num);
            if (isPrime(value)) {
                result.add(value);
            }
        }

        if(depth == numbers.length())
            return;

        for(int i=0;i<numbers.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(numbers, num+ numbers.charAt(i), depth+1);
                visited[i] = false;
            }
        }
    }
    public boolean isPrime(int num){
        if (num<=1)
            return false;
        if(num==2)
            return true;
        for(int i=2;i<=Math.sqrt(num); i++){
            if (num%i==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        FindPrime fp = new FindPrime();
        String numbers = "17";
        int result = fp.findPrime(numbers);
        System.out.println(result);
    }
}
