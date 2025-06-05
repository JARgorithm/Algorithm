package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Rain {
    private static int H, W;
    private static int[] rain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        rain = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getSum());
    }

    private static int getSum() {
        int result = 0;

        for (int now = 0; now < W; now++) {
            int left = Integer.MIN_VALUE;
            for (int i = 0; i <= now; i++) {
                left = Math.max(left, rain[i]);
            }

            int right = Integer.MIN_VALUE;
            for (int i = now; i < W; i++) {
                right = Math.max(right, rain[i]);
            }

            result += getWater(rain[now], left, right);
        }

        return result;
    }

    private static int getWater(int now, int left, int right)  {
        return Math.min(left, right) - now;
    }
}
