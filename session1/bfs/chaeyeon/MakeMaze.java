package bfs.chaeyeon;

import java.util.*;

public class MakeMaze {
    static int N;
    static int[][] map;
    static int[][] distatnce;
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};


    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        map = new int[N][N];
        distatnce = new int[N][N];

        for (int i=0;i<N;i++){
            String row = sc.nextLine();
            for(int j=0;j<N;j++){
                map[i][j] = row.charAt(j) - '0'; //검은방:0, 흰방:1
                distatnce[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(distatnce[N-1][N-1]);
        

    }

    public static void bfs() {
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(0, 0));
        distatnce[0][0] = 0;

        while (!deque.isEmpty()) {
            Node current = deque.poll();
            int x = current.x;
            int y = current.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int cnt = (map[nx][ny] == 1) ? 0 : 1;

                    if (distatnce[nx][ny] > distatnce[x][y] + cnt) {
                        distatnce[nx][ny] = distatnce[x][y] + cnt;
                        if (cnt == 0) {
                            deque.offerFirst(new Node(nx, ny));
                        } else {
                            deque.offerLast(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }
}
