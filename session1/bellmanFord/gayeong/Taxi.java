package bellmanFord.gayeong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Taxi {
    static int n, s, a, b;
    static int[] midDistance;
    static int[] aDistance;
    static int[] bDistance;
    static ArrayList<Edge>[] graph;
    static int INF = Integer.MAX_VALUE;

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    private void init(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;

        midDistance = new int[n + 1];
        aDistance = new int[n + 1];
        bDistance = new int[n + 1];

        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int c = fare[0], d = fare[1], f = fare[2];
            graph[c].add(new Edge(d, f));
            graph[d].add(new Edge(c, f));
        }
    }


    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, s, a, b, fares);

        //시작 정점으로부터 모든 정점까지의 비용 계산
        dijkstra(s, midDistance);

        int result = INF;
        for (int i = 1; i < n + 1; i++) {
            //같이 탄 지점부터 각자 집으로 가는 비용 계산
            dijkstra(i, aDistance);
            dijkstra(i, bDistance);

            result = Math.min(result, midDistance[i] + aDistance[a] + bDistance[b]);
        }

        return result;
    }

    private void dijkstra(int start, int[] distance) {
        Arrays.fill(distance, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        pq.offer(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge next : graph[cur.vertex]) {
                if (distance[next.vertex] <= distance[cur.vertex] + next.cost) continue;

                distance[next.vertex] = distance[cur.vertex] + next.cost;
                pq.offer(new Edge(next.vertex, distance[next.vertex]));
            }
        }

    }
}
