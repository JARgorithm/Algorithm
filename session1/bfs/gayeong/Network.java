package bfs.gayeong;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Network {
    private ArrayList<Integer>[] graph;
    private boolean[] visited;
    private int count;

    public void init(int n, int[][] computers) {
        this.graph = new ArrayList[n];
        this.visited = new boolean[n];
        this.count = 0;

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] != 0) {
                    graph[i].add(j);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {
        init(n, computers);

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            bfs(i);
        }

        return count;
    }

    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (visited[next]) continue;

                queue.offer(next);
                visited[next] = true;
            }
        }

        count++;
    }
}
