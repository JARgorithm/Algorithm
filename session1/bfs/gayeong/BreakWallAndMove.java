package bfs.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BreakWallAndMove {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] visited;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    public int solution(int[][] map) {
        map = map;
        n = map.length;
        m = map[0].length;

        breakWall();

        return result;
    }

    private void breakWall() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    result = Math.min(result, move());
                    map[i][j] = 1;
                }
            }
        }
    }

    private int move() {
        Queue<Node> queue = new LinkedList<>();
        visited = new int[n][m];

        queue.offer(new Node(0, 0));
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] == 0 && map[nx][ny] == 0) {
                    queue.offer(new Node(nx ,ny));
                    visited[nx][ny] = visited[current.x][current.y] + 1;
                }
            }
        }

        int dist = visited[n - 1][m - 1];
        return (dist == 0 ? Integer.MAX_VALUE : dist);
    }

    public static void main(String[] args) throws IOException {
        BreakWallAndMove bwa = new BreakWallAndMove();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        int result = bwa.solution(map);
        System.out.println(result);
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}