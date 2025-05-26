package dijkstra.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SGGround {
    static int n; // 지역 개수
    static int m; // 수색 가능 범위
    static int r; // 길의 개수
    static int[] items; // 각 구역에 있는 아이템 수
    static ArrayList<Edge>[] graph;

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);

        items = new int[n + 1];
        String[] itemStr = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            items[i + 1] = Integer.parseInt(itemStr[i]);
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < r; i++) {
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int cost = Integer.parseInt(edge[2]);

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        int result = 0;
        for (int i = 1; i < n + 1; i++) {
            result = Math.max(result, dijkstra(i));
        }

        System.out.println(result);
    }

    private static int dijkstra(int start) {

        int[] distance = new int [n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Edge> pq = new LinkedList<>();

        pq.offer(new Edge(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            for (Edge next : graph[now.vertex]) {
                int a = next.vertex;
                int cost = next.cost;

                if (distance[a] > distance[now.vertex] + cost && distance[now.vertex] + cost <= m) {
                    distance[a] = distance[now.vertex] + cost;
                    pq.offer(new Edge(a, distance[a]));
                }

            }
        }
        int result = 0;
        for (int i = 1; i < n + 1; i++)
        {
            if (distance[i] != Integer.MAX_VALUE) {
                result += items[i];
            }
        }

        return result;
    }
}
