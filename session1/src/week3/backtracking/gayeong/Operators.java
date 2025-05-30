package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Operators {
    private static int N;
    private static int[] numbers;
    private static int[] operators;
    private static int maxNum = Integer.MIN_VALUE;
    private static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(1, numbers[0], operators[0], operators[1], operators[2], operators[3]);

        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int depth, int sum, int plus, int minus, int multiply, int divide) {
        if (depth == N) {
            maxNum = Math.max(maxNum, sum);
            minNum = Math.min(minNum, sum);
            return;
        }

        if (plus > 0) {
            dfs(depth + 1, sum + numbers[depth], plus - 1, minus, multiply, divide);
        }
        if (minus > 0) {
            dfs(depth + 1, sum - numbers[depth], plus, minus - 1, multiply, divide);
        }
        if (multiply > 0) {
            dfs(depth + 1, sum * numbers[depth], plus, minus, multiply - 1, divide);
        }
        if (divide > 0) {
            int result;
            if (sum < 0) {
                result = -(-sum / numbers[depth]);
            } else {
                result = sum / numbers[depth];
            }
            dfs(depth + 1, result, plus, minus, multiply, divide - 1);
        }
    }
}
