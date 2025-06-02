package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SubsetSum {
    private static int N, S;
    private static int[] numbers;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, 0, 0);

        System.out.println(result);
    }

    private static void dfs(int depth, int sum, int count) {
        if (depth == N) {
            if (sum == S && count > 0) result++;
            return;
        }

        dfs(depth + 1, sum + numbers[depth], count + 1);
        dfs(depth + 1, sum, count);
    }
}
