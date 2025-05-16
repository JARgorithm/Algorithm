package bfs.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MakeMaze {
    static int n;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(line[j] + "");
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        bfs();
        System.out.println(visited[n - 1][n -1] == Integer.MAX_VALUE ? 0 : visited[n - 1][n -1]);

    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cnt - o2.cnt;
        });
        pq.offer(new Node(0, 0, 0));
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int i = 0; i < 4; i ++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    int temp;
                    if (map[nx][ny] == 1) {
                        temp = cur.cnt;
                    } else {
                        temp = cur.cnt + 1;
                    }

                    if (visited[nx][ny] <= temp) continue;

                    pq.offer(new Node(nx ,ny, temp));
                    visited[nx][ny] = temp;
                }
            }
        }
    }
}
