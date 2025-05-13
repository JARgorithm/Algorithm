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
    static int[][][] visited;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    public int solution(int[][] map) {
        n = map.length;
        m = map[0].length;

        move();

        return result;
    }

    private int move() {
        Queue<Node> queue = new LinkedList<>();
        visited = new int[n][m][2];

        queue.offer(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            int broken = current.broken;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (map[nx][ny] == 0 && visited[nx][ny][broken] == 0) {
                    visited[nx][ny][broken] = visited[x][y][broken] + 1;
                    queue.offer(new Node(nx, ny, broken));
                }

                if (map[nx][ny] == 1 && broken == 0 && visited[nx][ny][1] == 0) {
                    visited[nx][ny][1] = visited[x][y][broken] + 1;
                    queue.offer(new Node(nx, ny, 1));
                }
            }
        }

        int noBreak = visited[n-1][m-1][0];
        int didBreak = visited[n-1][m-1][1];

        if (noBreak == 0) noBreak = Integer.MAX_VALUE;
        if (didBreak == 0) didBreak = Integer.MAX_VALUE;

        return Math.min(noBreak, didBreak);
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
    int broken;

    public Node(int x, int y, int broken) {
        this.x = x;
        this.y = y;
        this.broken =  broken;
    }
}