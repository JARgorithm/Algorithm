package week6.joochaeyeon;

import java.util.Arrays;

public class Camera {
    public int camera(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1],b[1]));
        int cnt = 0;
        int camera = Integer.MIN_VALUE;

        for(int[] route : routes){
            int start = route[0];
            int end = route[1];

            if(camera < start){
                cnt++;
                camera = end;
            }
        }

        return cnt;
    }
}