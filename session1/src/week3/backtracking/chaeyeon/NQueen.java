package week3.backtracking.chaeyeon;

import java.util.*;

public class NQueen {
    static int n;
    static int cnt = 0;
    static boolean[] column;
    static boolean[] diag1;
    static boolean[] diag2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        column = new boolean[n];
        diag1=new boolean[2*n-1];
        diag2=new boolean[2*n-1];

        dfs(0);
        System.out.println(cnt);

    }

    static void dfs(int row){
        if (row == n){
            cnt++;
            return;
        }
        for(int c=0; c<n;c++){
            if(column[c] || diag1[row+c] || diag2[row-c+n-1]){
                continue;
            }
            column[c] = true;
            diag1[row+c] = true;
            diag2[row-c+n-1] = true;

            dfs(row+1);
            column[c] = false;
            diag1[row+c] = false;
            diag2[row-c+n-1] = false;


        }

    }

}
