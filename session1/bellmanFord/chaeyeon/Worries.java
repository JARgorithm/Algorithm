package bellmanFord.chaeyeon;

import java.util.*;

public class Worries {

    static class Edge{
        int from,to,cost;
        Edge(int from, int to, int cost){
            this.from =from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long NEG_INF = Long.MIN_VALUE;
    static int N, start, end, M;
    static List<Edge> edges = new ArrayList<>();
    static long[] dist;
    static boolean[] visited;
    static int[] money;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        start = sc.nextInt();
        end = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(from, to, cost));
        }

        money  = new int[N];
        for(int i=0; i<N;i++){
            money[i] = sc.nextInt();
        }

        dist = new long[N];
        Arrays.fill(dist,NEG_INF);
        dist[start] = money[start];



        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] == NEG_INF)
                    continue;
                if (dist[edge.to] < dist[edge.from] - edge.cost + money[edge.to]) {
                    dist[edge.to] = dist[edge.from] - edge.cost + money[edge.to];
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] == NEG_INF)
                continue;
            if (dist[edge.to] < dist[edge.from] - edge.cost + money[edge.to]) {
                visited = new boolean[N];
                dfs(edge.to);
                if (visited[end]) {
                    System.out.println("Gee");
                    return;
                }
            }
        }


        if (dist[end] == NEG_INF) {
            System.out.println("gg");
        }
        else {
            System.out.println(dist[end]);
        }
    }
    static void dfs(int node){
        visited[node] = true;
        for(Edge edge : edges){
            if(edge.from == node && !visited[edge.to]){
                dfs(edge.to);
            }
        }

    }
}