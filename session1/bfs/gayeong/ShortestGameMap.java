package bfs.gayeong;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestGameMap {
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] visited;
    private static Queue<Position> pq;

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        pq = new LinkedList<>();
        visited = new int[n][m];

        pq.offer(new Position(0, 0));
        visited[0][0] = 1;

        while(!pq.isEmpty()) {
            Position now = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] == 0 && maps[nx][ny] != 0) {
                    pq.offer(new Position(nx, ny));
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                }
            }

        }
        return visited[n - 1][m - 1] == 0 ? -1 : visited[n - 1][m - 1];
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}