package week5.chaeyeon;

import java.util.Arrays;
import java.util.Scanner;

public class DeliverSugar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt=0;
        int dp[] = new int[n+1];
        Arrays.fill(dp, 5001);
        dp[0]=0;

        for(int i=3;i<=n;i++){
            if(i>=3)
                dp[i] = Math.min(dp[i], dp[i-3]+1);
            if (i>=5)
                dp[i] = Math.min(dp[i], dp[i-5]+1);
        }

        System.out.println(dp[n] == 5001 ? -1 : dp[n]);

    }
}
