package week3.backtracking.chaeyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliverChicken {
    static int n;
    static int m;
    static int[][] map;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int minResult = Integer.MAX_VALUE;
    static int[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];

        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1)
                    houses.add(new int[]{i, j});
                else if (map[i][j] == 2)
                    chickens.add(new int[]{i, j});
            }
        }

        selected= new int[m];
        dfs(0,0);
        System.out.println(minResult);
    }

    static void dfs(int start, int depth) {
        if(depth == m){
            int distSum = 0;

            for(int[] h : houses){
                int hx = h[0];
                int hy = h[1];
                int minDist = Integer.MAX_VALUE;

                for(int i=0; i<m;i++){
                    int[] c = chickens.get(selected[i]);
                    int cx = c[0];
                    int cy = c[1];
                    int distance = Math.abs(hx-cx) + Math.abs(hy-cy);
                    minDist = Math.min(minDist,distance);
                }
                distSum+= minDist;
            }

            minResult = Math.min(minResult, distSum);
            return;
        }
        for(int i= start; i<chickens.size(); i++){
            selected[depth]=i;
            dfs(i+1, depth+1);
        }

    }
}
