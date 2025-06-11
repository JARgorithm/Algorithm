package week5.chaeyeon;

public class Thief {
    public int thief(int[] money) {
        int n = money.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        //1. 첫번째 집 털기 -> 마지막 집은 못턴다.
        dp1[0] = money[0];
        dp1[1]=Math.max(money[0], money[1]);
        for (int i=2; i<n-1;i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
        }

        //2. 첫번째 집 안털기 -> 마지막 집을 털 수 있다.
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i=2; i<n;i++){
            dp2[i] = Math.max(dp2[i-1], dp2[i - 2] + money[i]);

        }
        return Math.max(dp1[n - 2], dp2[n - 1]);

    }
}
