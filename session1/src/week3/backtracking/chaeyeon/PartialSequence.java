package week3.backtracking.chaeyeon;

import java.util.Scanner;

public class PartialSequence {

    static int n;
    static int s;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n;i++){
            arr[i] = sc.nextInt();
        }

        dfs(0, 0, 0);

        System.out.println(cnt);

    }

    static void dfs(int depth, int sum, int selected){
        if(depth == n){
            if(sum == s && selected > 0){
                cnt++;
            }
            return;
        }
        dfs(depth+1, sum+arr[depth], selected+1);
        dfs(depth+1, sum,selected);
    }
}
