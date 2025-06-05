package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheese {
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {1, -1 ,0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static long cheeseCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            cheeseCount += Arrays.stream(map[i]).filter(n -> n > 0).count();
        }

        System.out.println(getTime());
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int getTime() {
        int time = 0;
        while (cheeseCount > 0) {
            time++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                    }
                }
            }
            checkOutside();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && isDisappear(i, j)) {
                        map[i][j] = 0;
                        cheeseCount--;
                    }
                }
            }
        }

        return time;
    }

    private static boolean isDisappear(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == -1) {
                count++;
            }
        }

        return count >= 2;
    }

    private static void checkOutside() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        boolean[][] visited = new boolean[N][M];
        map[0][0] = -1;
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] != 1) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = -1;
                        q.offer(new Node(nx, ny));
                    }

                    if (map[nx][ny] == 0 || map[nx][ny] == -1) {
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
