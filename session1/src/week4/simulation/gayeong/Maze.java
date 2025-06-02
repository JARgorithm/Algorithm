package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Maze {
    private static int n;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int[][] graph;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new int[n][n];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(input[j] + "");
            }
        }

        bfs();
        System.out.println(visited[n - 1][n - 1]);

    }

    private static void bfs() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {return o1.weight - o2.weight;});

        pq.offer(new Edge(0, 0, 0));
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.x == n - 1 && cur.y == n - 1) return;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == Integer.MAX_VALUE) {
                    // 검은 방인 경우
                    if (graph[nx][ny] == 0) {
                        pq.offer(new Edge(nx, ny, cur.weight + 1));
                        visited[nx][ny] =  cur.weight + 1;
                    } else {
                        pq.offer(new Edge(nx, ny, cur.weight));
                        visited[nx][ny] = cur.weight;
                    }
                }
            }
        }

    }

    static class Edge {
        int x, y, weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
