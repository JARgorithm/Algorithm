package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape {
    private static int R, C;
    private static char[][] map;
    private static int[][] water;
    private static int[][] animal;
    private static Queue<Node> wQ;
    private static Queue<Node> aQ;

    private static final char EMPTY = '.';
    private static final char WATER = '*';
    private static final char DESTINATION = 'D';
    private static final char START = 'S';
    private static final String FAILED = "KAKTUS";

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        water = new int[R][C];
        animal = new int[R][C];
        wQ = new LinkedList<>();
        aQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = line[j];
                water[i][j] = -1;
                animal[i][j] = -1;

                if (map[i][j] == WATER) {
                    wQ.offer(new Node(i, j));
                    water[i][j] = 0;
                }
                if (map[i][j] == START) {
                    aQ.offer(new Node(i, j));
                    animal[i][j] = 0;
                }
            }
        }

        bfs();
    }

    private static void bfs() {
        while (!wQ.isEmpty()) {
            Node cur = wQ.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && water[nx][ny] == -1 && map[nx][ny] == EMPTY) {
                    wQ.offer(new Node(nx, ny));
                    water[nx][ny] = water[cur.x][cur.y] + 1;
                }
            }
        }

        while (!aQ.isEmpty()) {
            Node cur = aQ.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && animal[nx][ny] == -1) {
                    if (map[nx][ny] == DESTINATION) {
                        System.out.println(animal[cur.x][cur.y] + 1);

                        return;
                    }

                    if (map[nx][ny] == EMPTY &&(water[nx][ny] == -1 || water[nx][ny] > animal[cur.x][cur.y] + 1)) {
                        aQ.offer(new Node(nx, ny));
                        animal[nx][ny] = animal[cur.x][cur.y] + 1;
                    }
                }
            }
        }
        System.out.println(FAILED);
    }

}
