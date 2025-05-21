package bfs.gayeong;

import java.util.*;

public class FurthestNode {
    static int n;
    static int maxDist = 0;
    static ArrayList<Integer>[] graph;
    static int[] visited;

    private void init(int n, int[][] edges) {
        this.n = n;
        graph = new ArrayList[n + 1];
        visited = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph[a].add(b);
            graph[b].add(a);
        }
    }

    public int solution(int n, int[][] edge) {
        init(n, edge);

        bfs();

        return findFurthestNode();
    }

    private void bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        visited[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph[cur]) {
                if (visited[next] == 0) {
                    visited[next] = visited[cur] + 1;
                    q.offer(next);
                    maxDist = Math.max(maxDist, visited[next]);
                }
            }
        }
    }

    private int findFurthestNode() {
        int result = 0;

        for (int i = 2; i < n + 1; i++) {
            if (visited[i] == maxDist)
                result++;
        }

        return result;
    }

}
