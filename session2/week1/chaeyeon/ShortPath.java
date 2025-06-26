package week1.chaeyeon;

import java.util.*;

public class ShortPath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int K = sc.nextInt();

        List<int[]>[] graph  = new ArrayList[V+1];
        for(int i=1; i<=V;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            graph[u].add(new int[]{v,c});
        }

        int[] dist = dijkstra(K, graph, V);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }


    }
    public static int[] dijkstra(int start, List<int[]>[] graph, int V){
        int[] distance = new int[V+1];
        boolean[] visited = new boolean[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);
        pq.offer(new int[]{start,0});

        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int now = current[0];

            if(visited[now])
                continue;

            visited[now] = true;

            for(int[] edge : graph[now]){
                int next = edge[0];
                int weight = edge[1];

                if(distance[next] > distance[now] +weight){
                    distance[next] = distance[now] +weight;
                    pq.offer(new int[]{next, distance[next]});
                }
            }
        }
        return distance;
    }
}
