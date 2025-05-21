package week2.dijkstra.gayeong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Delievery {
    static int n, k;
    static ArrayList<Edge>[] graph;
    static int[] distance;

    static class Edge {
        int vertex, weight;
        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private void init(int n, int[][] road, int k) {
        this.n = n;
        this.k = k;
        this.distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        this.graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : road) {
            int a = edge[0];
            int b = edge[1];
            int weight = edge[2];

            graph[a].add(new Edge(b, weight));
            graph[b].add(new Edge(a, weight));
        }
    }

    public int solution(int N, int[][] road, int K) {
        init(N, road, K);

        dijkstra();

        int result = 0;
        for (int dis : distance) {
            if (dis <= k) result++;
        }

        return result;
    }

    public void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        pq.offer(new Edge(1, 0));
        distance[1] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge edge : graph[cur.vertex]) {
                if (distance[edge.vertex] <= distance[cur.vertex] + edge.weight) continue;
                distance[edge.vertex] = distance[cur.vertex] + edge.weight;
                pq.offer(new Edge(edge.vertex, distance[edge.vertex]));
            }
        }
    }
}
