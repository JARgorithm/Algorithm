package bfs.chaeyeon;

import java.util.*;

public class AlgoSpot {

    static int N,M;
    static int[][] map;
    static int[][] distatnce;
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};

    static class Node{
        int x,y;
        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        sc.nextLine();
        map = new int[N][M];
        distatnce = new int[N][M];

        for (int i=0;i<N;i++){
            String row = sc.nextLine();
            for(int j=0;j<M;j++){
                map[i][j] = row.charAt(j) - '0'; //벽:1, 빈칸:0
                distatnce[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(distatnce[N-1][M-1]);

    }

    public static void bfs(){
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(0,0));
        distatnce[0][0] = 0; //시작점=빈방=0

        while(!deque.isEmpty()){
            Node current = deque.poll();
            int x = current.x;
            int y = current.y;

            for(int dir = 0; dir<4;dir++){
                int nx = x+dx[dir];
                int ny = y+dy[dir];

                if(nx>=0 && nx<N && ny>=0 && ny<M){
                    int cnt = map[nx][ny];

                    if(distatnce[nx][ny] > distatnce[x][y]+cnt){
                        distatnce[nx][ny] = distatnce[x][y] +cnt;
                        if(cnt == 0){
                            deque.offerFirst(new Node(nx,ny));
                        }
                        else{
                            deque.offerLast(new Node(nx,ny));
                        }
                    }
                }
            }


        }
    }
}
