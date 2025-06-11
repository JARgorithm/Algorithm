package week5.chaeyeon.dp.gayeong;

public class School {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            dp[y][x] = -1;
        }

        dp[1][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (dp[y][x] == -1) {
                    dp[y][x] = 0;
                    continue;
                }
                if (x > 1) dp[y][x] = (dp[y][x] + dp[y][x - 1]) % MOD;
                if (y > 1) dp[y][x] = (dp[y][x] + dp[y - 1][x]) % MOD;
            }
        }

        return dp[n][m];
    }
}
