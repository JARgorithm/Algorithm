package week5.chaeyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeAndQuery {
    static List<Integer>[] tree;
    static int[] result; //서브트리 크기 저장하는 배열
    static boolean[] visited;
    static int n;
    static int r;
    static int q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r =sc.nextInt();
        q = sc.nextInt();
        tree = new ArrayList[n+1];

        for(int i=0; i<=n;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1;i++){
            int u = sc.nextInt();
            int v= sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        result=new int[n+1];
        visited=new boolean[n+1];
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q;i++){
            int query  = sc.nextInt();
            sb.append(result[query]).append("\n");
        }
        System.out.println(sb.toString());


    }

    static int dfs(int now){
        visited[now] = true;
        int cnt = 1;

        for(int next: tree[now]){
            if(!visited[next]){
                cnt+=dfs(next);
            }
        }
        result[now]=cnt;
        return cnt;
    }

}
