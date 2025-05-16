package bfs.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AlgoSpot {
    static int n, m;
    static int[][] graph;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        m = Integer.parseInt(nm[0]);
        n = Integer.parseInt(nm[1]);
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }


        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(line[j] + "");
            }
        }

        bfs();

        System.out.println(visited[n - 1][m -1]);

    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.distance - o2.distance;
        });
        pq.offer(new Node(0, 0, 0));
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int temp;
                    if (graph[nx][ny] == 0) {
                        temp = cur.distance;
                    } else {
                        temp = cur.distance + 1;
                    }
                    if (temp >= visited[nx][ny]) continue;

                    pq.offer(new Node(nx, ny, temp));
                    visited[nx][ny] = temp;
                }
            }
        }
    }
}
