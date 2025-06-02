package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoodSequence {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs("");
    }

    private static void dfs(String current) {
        if (!isGood(current)) return;

        if (current.length() == N) {
            System.out.println(current);
            System.exit(0); // 가장 먼저 찾은 좋은 수열이 정답
        }

        for (int i = 1; i <= 3; i++) {
            dfs(current + i);
        }
    }

    // 좋은 수열인지 판별 (인접한 같은 부분수열이 있는지 검사)
    private static boolean isGood(String s) {
        int len = s.length();
        for (int i = 1; i <= len / 2; i++) {
            String last = s.substring(len - i);
            String prev = s.substring(len - 2 * i, len - i);
            if (last.equals(prev)) {
                return false;
            }
        }
        return true;
    }
}
