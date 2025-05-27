package week3.backtracking.gayeong;

import java.util.HashSet;
import java.util.Set;

public class Prime {
    static String numbers;
    static Set<Integer> primeNumber;
    static boolean[] visited;

    public int solution(String numbers) {
        this.numbers = numbers;
        visited = new boolean[numbers.length()];
        primeNumber = new HashSet<>();

        dfs("", 0);

        return primeNumber.size();
    }

    private void dfs(String word, int depth) {
        if (depth > numbers.length()) return;

        if (!word.equals("") && isPrime(Integer.parseInt(word))){
            primeNumber.add(Integer.parseInt(word));
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(word + numbers.charAt(i), depth + 1);
                visited[i] = false;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number == 0 || number == 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Prime p = new Prime();

        System.out.println(p.solution("011"));
    }
}
