package week2.chaeyeon.bfsdfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class Algospot1 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void algospot1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        dist[0][0] = 0;
        deque.add(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] now = deque.pollFirst();
            int x = now[0];
            int y = now[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                int cost = map[nx][ny];

                if (dist[nx][ny] > dist[x][y] + cost) {
                    dist[nx][ny] = dist[x][y] + cost;
                    if (cost == 0) { //빈벽으로 이동 가능하니까
                        deque.addFirst(new int[]{nx, ny});
                    } else {
                        deque.addLast(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println(dist[n - 1][m - 1]);
    }
}