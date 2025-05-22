package bellmanFord.chaeyeon;

import java.util.*;


public class TimeMachine {

    static class Edge{
        int from,to,cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N,M;
    static Edge[] edges;
    static long[] dist;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        edges = new Edge[M];
        dist = new long[N+1];

        Arrays.fill(dist,INF);
        dist[1] = 0;

        for(int i=0;i<M;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges[i] = new Edge(from, to, cost);
        }

        boolean updated;

        for (int i = 1; i <= N; i++) {
            updated = false;

            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                    updated = true;

                    if (i == N) {
                        System.out.println(-1);
                        return;
                    }
                }
            }

            if (!updated)
                break;
        }

        for(int i=2;i<=N;i++){
            if (dist[i] == INF){
                System.out.println(-1);
            }
            else{
                System.out.println(dist[i]);
            }
        }

    }


}
