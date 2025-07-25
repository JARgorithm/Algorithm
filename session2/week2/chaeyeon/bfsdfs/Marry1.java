package week2.chaeyeon.bfsdfs;

import java.util.*;

public class Marry1 {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void marry1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n+1];

        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.println(bfs(1));

    }

    private static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start,0}); //{노드, 거리}
        visited[start] = true;

        int cnt = 0;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowNode = now[0];
            int depth = now[1];

            if(depth>=2)
                continue;

            for(int next : graph.get(nowNode)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(new int[]{next, depth+1});
                    cnt++;
                }
            }
        }
        return cnt;

    }

}
