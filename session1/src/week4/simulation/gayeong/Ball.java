package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ball {
    private static int N, M;
    private static int[] bucket;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bucket = new int[N + 1];

        for (int x = 0; x < M; x++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            putIntoBucket(i, j, k);
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.print(bucket[i] + " ");
        }
    }

    private static void putIntoBucket(int i , int j, int k) {
        for (int idx = i; idx < j + 1; idx++) {
            bucket[idx] = k;
        }
    }
}
