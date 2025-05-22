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
    static boolean[] reachable;
    static int[] earn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        start = sc.nextInt();
        end = sc.nextInt();
        M = sc.nextInt();

        dist = new long[N];
        earn = new int[N];

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(from, to, cost));
        }

        for (int i = 0; i < N; i++) {
            earn[i] = sc.nextInt();
            dist[i] = NEG_INF;
        }

        dist[start] = earn[start];

        // 벨만 포드
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] == NEG_INF) continue;
                if (dist[edge.to] < dist[edge.from] - edge.cost + earn[edge.to]) {
                    dist[edge.to] = dist[edge.from] - edge.cost + earn[edge.to];
                }
            }
        }

        // 양의 사이클 탐지
        boolean infinite = false;
        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] == NEG_INF) continue;
                if (dist[edge.to] < dist[edge.from] - edge.cost + earn[edge.to]) {
                    if (isReachable(edge.to, end)) {
                        infinite = true;
                        break;
                    }
                }
            }
            if (infinite) break;
        }

        if (dist[end] == NEG_INF) {
            System.out.println("gg");
        } else if (infinite) {
            System.out.println("Gee");
        } else {
            System.out.println(dist[end]);
        }
    }

    static boolean isReachable(int from, int target) {
        boolean[] visited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        q.offer(from);
        visited[from] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == target) return true;

            for (Edge edge : edges) {
                if (edge.from == curr && !visited[edge.to]) {
                    visited[edge.to] = true;
                    q.offer(edge.to);
                }
            }
        }

        return false;
    }
}