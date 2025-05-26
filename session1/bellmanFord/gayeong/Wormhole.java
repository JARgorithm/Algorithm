package bellmanFord.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Wormhole {
    static int t, n, m, w;
    static int[] distance;
    static ArrayList<Edge> graph;
    static int INF = Integer.MAX_VALUE;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static void init() {
        distance = new int[n + 1];
        Arrays.fill(distance, 0);

        // 가상 노드 0에서 모든 노드로 0-cost 간선 추가
        graph = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            graph.add(new Edge(0, i, 0));
        }
        //시작 정점을 가상 노드 0으로 지정
        distance[0] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            String[] line = br.readLine().split(" ");
            n = Integer.parseInt(line[0]);
            m = Integer.parseInt(line[1]);
            w = Integer.parseInt(line[2]);

            init();

            for (int j = 0; j < m; j++) {
                String[] set = br.readLine().split(" ");
                int s = Integer.parseInt(set[0]), e = Integer.parseInt(set[1]), t = Integer.parseInt(set[2]);

                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }

            for (int j = 0; j < w; j++) {
                String[] set = br.readLine().split(" ");
                int s = Integer.parseInt(set[0]), e = Integer.parseInt(set[1]), t = Integer.parseInt(set[2]);

                graph.add(new Edge(s, e, -t));
            }

            if (bellmanFord()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }

    private static boolean bellmanFord() {
        for (int i = 0; i <= n; i++) {
            for (Edge edge : graph) {
                if (distance[edge.from] != INF
                    && distance[edge.to] > distance[edge.from] + edge.cost) {
                    distance[edge.to] = distance[edge.from] + edge.cost;

                    if (i == n) return true;
                }
            }
        }

        return false;
    }
}
