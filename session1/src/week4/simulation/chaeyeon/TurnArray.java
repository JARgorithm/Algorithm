package week4.simulation.chaeyeon;

import java.util.Scanner;

public class TurnArray {
    static int n;
    static int m;
    static int k;
    static int[][] arr;
    static int[][] oper;
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



}
