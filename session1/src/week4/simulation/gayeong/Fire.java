package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Fire {
    static int w, h;
    static char[][] map;
    static int[][] fireTime;
    static int[][] personTime;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static final char WALL = '#';
    private static final char EMPTY = '.';
    private static final char FIRE = '*';
    private static final char PERSON = '@';

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fireTime = new int[h][w];
            personTime = new int[h][w];

            Queue<Node> fireQ = new LinkedList<>();
            Queue<Node> personQ = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    fireTime[i][j] = -1;
                    personTime[i][j] = -1;

                    if (map[i][j] == FIRE) {
                        fireQ.offer(new Node(i, j));
                        fireTime[i][j] = 0;
                    }

                    if (map[i][j] == PERSON) {
                        personQ.offer(new Node(i, j));
                        personTime[i][j] = 0;
                    }
                }
            }

            // 1. 불
            while (!fireQ.isEmpty()) {
                Node cur = fireQ.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    if (map[nx][ny] == WALL || fireTime[nx][ny] != -1) continue;

                    fireTime[nx][ny] = fireTime[cur.x][cur.y] + 1;
                    fireQ.offer(new Node(nx, ny));
                }
            }

            // 2. 사람
            boolean escaped = false;
            while (!personQ.isEmpty() && !escaped) {
                Node cur = personQ.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        System.out.println(personTime[cur.x][cur.y] + 1);
                        escaped = true;
                        break;
                    }

                    if (map[nx][ny] == WALL || personTime[nx][ny] != -1) continue;

                    if (fireTime[nx][ny] != -1 && fireTime[nx][ny] <= personTime[cur.x][cur.y] + 1) continue;

                    personTime[nx][ny] = personTime[cur.x][cur.y] + 1;
                    personQ.offer(new Node(nx, ny));
                }
            }

            if (!escaped) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
