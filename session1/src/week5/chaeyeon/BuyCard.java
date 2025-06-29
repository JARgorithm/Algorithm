package week5.chaeyeon;

import java.util.Scanner;

public class BuyCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[n+1];
        int[] dp = new int[n+1];

        for(int i=1; i<=n; i++){
            cost[i] = sc.nextInt();
        }

        for(int i=1; i<=n;i++){
            for(int j=1; j<=i;j++){
                dp[i] = Math.max(dp[i], dp[i-j]+cost[j]);
            }
        }
        System.out.println(dp[n]);
    }
}
