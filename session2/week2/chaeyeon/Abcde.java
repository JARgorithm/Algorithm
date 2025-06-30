package week2.chaeyeon;

import java.util.ArrayList;
import java.util.Scanner;

public class Abcde {
    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean found = false;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        graph = new ArrayList[n];

        for(int i=0; i<n;i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n];

        for(int i=0; i<n;i++){
            if (found)
                break;
            dfs(i,1);
        }

        System.out.println(found? 1 : 0);

    }
    public static void dfs (int current, int depth){
        if (depth ==5){
            found = true;
            return;
        }

        visited[current] = true;

        for(int next : graph[current]){
            if(!visited[next]){
                dfs(next,depth+1);
                if(found)
                    return;
            }
        }
        visited[current]= false;

    }
}
