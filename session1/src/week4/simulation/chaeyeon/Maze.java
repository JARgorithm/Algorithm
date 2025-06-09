package week4.simulation.chaeyeon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Maze {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx={-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point{
        int x;
        int y;
        int cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map=new int[n][n];
        visited = new boolean[n][n];

        for(int i=0;i<n;i++){
            String line = sc.next();
            for(int j=0;j<n;j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());

    }

    public static int bfs(){
        Deque<Point> dq = new LinkedList<>();
        visited = new boolean[n][n];
        dq.offerFirst(new Point(0,0,0));
        visited[0][0] = true;

        while(!dq.isEmpty()){
            Point p = dq.pollFirst();

            if(p.x == n-1 && p.y == n-1){
                return p.cost;
            }

            for(int dir=0; dir<4; dir++){
                int nx = p.x + dx[dir];
                int ny = p.y + dy[dir];

                if(nx <0 || nx >=n || ny<0 || ny>=n){
                    continue;
                }
                if(visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                if(map[nx][ny] == 1){
                    dq.offerFirst(new Point(nx,ny,p.cost));
                }
                else{
                    dq.offerLast(new Point(nx,ny,p.cost+1));
                }
            }
        }
        return -1;



    }
}
