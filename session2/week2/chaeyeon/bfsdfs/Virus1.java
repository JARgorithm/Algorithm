package week2.chaeyeon.bfsdfs;

import java.util.*;

public class Virus1 {

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void virus1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n + 1];

        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(bfs(1));
    }

    private static int bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int cnt = 0;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int next : graph.get(now)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    cnt++;
                }
            }
        }
        return  cnt;
    }
}
