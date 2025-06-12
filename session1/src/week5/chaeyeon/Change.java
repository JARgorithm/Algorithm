package week5.chaeyeon;

public class Change {
    public int change(int n, int[] money) {
        int dp[] = new int[n+1];
        dp[0]=1;

        for (int coin : money){
            for(int i=coin; i<=n;i++){
                dp[i] = (dp[i] + dp[i-coin]) % 1000000007;
            }
        }
        return dp[n];
    }
}
