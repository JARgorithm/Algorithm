package dfs.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Alphabets {
    static int r, c;
    static char[][] map;
    static boolean[] visited;
    static int result;
    static final int LENGTH = 26;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);

        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(solution());
    }

    static int solution() {
        visited = new boolean[LENGTH];
        result = 1;

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        return result;
    }

    static void dfs(int x, int y, int depth) {
        if (depth > result) {
            result = depth;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

            int idx = map[nx][ny] - 'A';
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(nx, ny, depth + 1);
                visited[idx] = false;
            }
        }
    }
}
