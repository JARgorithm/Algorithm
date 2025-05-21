package week2.bellman_ford.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Timemachine {
    static int n, m;
    static ArrayList<Edge> graph;
    static long[] distance;

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        graph = new ArrayList<>();
        distance = new long[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        for (int i = 0; i < m; i++) {
            String[] route = br.readLine().split(" ");
            int a = Integer.parseInt(route[0]);
            int b = Integer.parseInt(route[1]);
            int c = Integer.parseInt(route[2]);

            graph.add(new Edge(a, b, c));
        }



        if (bellman_ford()) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) System.out.println(-1);
                else System.out.println(distance[i]);
            }
        }
    }

    static boolean bellman_ford() {
        for (int i = 1; i < n; i++) {
            for (Edge edge : graph) {
                if (distance[edge.from] != Integer.MAX_VALUE
                && distance[edge.to] > distance[edge.from] + edge.weight) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }

        for (Edge edge : graph) {
            if (distance[edge.from] != Integer.MAX_VALUE
            && distance[edge.to] > distance[edge.from] + edge.weight)
                return true;
        }
        return false;
    }
}
