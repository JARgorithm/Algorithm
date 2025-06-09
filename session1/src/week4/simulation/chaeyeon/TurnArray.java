package week4.simulation.chaeyeon;

import java.util.Scanner;

public class TurnArray {
    static int n;
    static int m;
    static int k;
    static int[][] arr;
    static int[][] oper;
    static boolean[] visited;
    static int[] order;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        arr = new int [n][m];

        for(int i=0; i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = sc.nextInt();
            }
        }

        oper = new int[k][3];
        for(int i=0; i<k;i++) {
            oper[i][0] = sc.nextInt();
            oper[i][1] = sc.nextInt();
            oper[i][2] = sc.nextInt();
        }

        visited = new boolean[k];
        order = new int[k];
        dfs(0);
        System.out.println(result);



    }

    static void dfs(int depth){
        if(depth == k){
            int[][] copy = deepCopy(arr);

            for(int i=0; i<k;i++){
                int r = oper[order[i]][0] -1;
                int c =  oper[order[i]][1] -1;
                int s = oper[order[i]][2];
                rotate(copy,r,c,s);

            }
            result = Math.min(result, calculateMinRow(copy));
            return;
        }

        for(int i = 0; i < k; i++){
            if(!visited[i]){
                visited[i] = true;
                order[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int[][] deepCopy(int[][] arr){
        int[][] copy = new int[n][m];
        for(int i=0;i<n;i++){
            System.arraycopy(arr[i],0, copy[i],0, m);
        }
        return copy;
    }

    static int calculateMinRow(int[][] arr){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            int sum = 0;
            for (int j=0; j<m; j++){
                sum+=arr[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    static void rotate(int[][] arr, int r, int c, int s) {
        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer;
            int left = c - layer;
            int bottom = r + layer;
            int right = c + layer;

            int prev = arr[top][left]; // 첫 시작점 저장

            // ← 위쪽 줄: 오른쪽으로
            for (int j = left + 1; j <= right; j++) {
                int temp = arr[top][j];
                arr[top][j] = prev;
                prev = temp;
            }

            // ↓ 오른쪽 줄: 아래로
            for (int i = top + 1; i <= bottom; i++) {
                int temp = arr[i][right];
                arr[i][right] = prev;
                prev = temp;
            }

            // → 아래쪽 줄: 왼쪽으로
            for (int j = right - 1; j >= left; j--) {
                int temp = arr[bottom][j];
                arr[bottom][j] = prev;
                prev = temp;
            }

            // ↑ 왼쪽 줄: 위로
            for (int i = bottom - 1; i >= top; i--) {
                int temp = arr[i][left];
                arr[i][left] = prev;
                prev = temp;
            }
        }
    }
}
