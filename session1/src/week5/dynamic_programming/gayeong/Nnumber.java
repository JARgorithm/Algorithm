package week5.dynamic_programming.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Nnumber {
    private static int N;
    private static int number;
    private static Set<Integer>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        number = Integer.parseInt(br.readLine());

        Nnumber nnumber = new Nnumber();
        System.out.println(nnumber.solution(N, number));
    }

    public int solution(int N, int number) {
        dp = new HashSet[9]; // 1부터 8까지 사용하므로 index 1~8 사용

        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();
            // N, NN, NNN... 을 의미하는 숫자 추가
            int repeatedN = Integer.parseInt(String.valueOf(N).repeat(i));
            dp[i].add(repeatedN);

            // j개, i-j개로 나누어 조합
            for (int j = 1; j < i; j++) {
                for (int a : dp[j]) {
                    for (int b : dp[i - j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                    }
                }
            }

            // 목표 수를 찾았으면 바로 반환
            if (dp[i].contains(number)) return i;
        }

        return -1;
    }
}
