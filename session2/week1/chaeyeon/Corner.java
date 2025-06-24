package week1.chaeyeon;

import java.util.*;

public class Corner {
    static class Edge{
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Edge> edges = new ArrayList<>();
    static int N,M;
    static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for(int i=0; i<M;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(from,to,cost));
        }

        long[] dist = new long[N+1];
        int[] prev = new int[N+1];
        boolean hasCycle = false;

        Arrays.fill(dist, INF);
        dist[1] = 0;

        for(int i=1;i<=N;i++){
            for(Edge e : edges){
                if(dist[e.from] == INF)
                    continue;
                if(dist[e.to] < dist[e.from]+e.cost){
                    dist[e.to] = dist[e.from]+e.cost;
                    prev[e.to] = e.from;

                    if(i ==N){
                        hasCycle = true;
                    }
                }
            }
        }
        if (hasCycle){
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int current = N;
        while(current!=0){
            path.add(current);
            current = prev[current];

        }
        Collections.reverse(path);
        for (int node : path) {
            System.out.print(node + " ");
        }

    }
}
