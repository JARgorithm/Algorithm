package week3.backtracking.chaeyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Fire {
    static char[][] map;
    static boolean[][] visited;
    static int w;
    static int h;
    static Queue<Point> fireQueue;
    static Queue<Point> personQueue;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t=0; t<test;t++ ){
            w = sc.nextInt();
            h = sc.nextInt();
            sc.nextLine();
            map = new char[h][w];
            visited = new boolean[h][w];
            fireQueue = new LinkedList<>();
            personQueue = new LinkedList<>();

            for(int i=0; i<h;i++){
                String line = sc.nextLine();
                for(int j=0; j<w;j++){
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == '*'){
                        fireQueue.add(new Point(i, j));
                    }
                    else if(map[i][j] == '@'){
                        personQueue.add(new Point(i,j));
                        visited[i][j] = true;
                    }
                }
            }

            int result = bfs();
            if(result == -1){
                System.out.println("IMPOSSIBLE");
            }
            else{
                System.out.println(result);
            }
        }

    }

    static int bfs(){
        int time = 0;

        while(!personQueue.isEmpty()){
            int fireSize = fireQueue.size();
            for(int i=0; i<fireSize;i++){
                Point f = fireQueue.poll();
                for(int d=0; d<4; d++){
                    int nx = f.x +dx[d];
                    int ny = f.y+dy[d];
                    if(nx>= 0 && nx < h && ny>=0 && ny<w && map[nx][ny] == '.'){
                        map[nx][ny] = '*';
                        fireQueue.offer(new Point(nx,ny));
                    }
                }
            }

            int personSize = personQueue.size();
            for(int i=0; i<personSize;i++){
                Point p = personQueue.poll();
                for(int d = 0; d<4;d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(nx< 0 || nx >= h || ny<0 || ny>= w){
                        return time+1;
                    }
                    if(map[nx][ny] == '.' && !visited[nx][ny]){
                        visited[nx][ny]=true;
                        personQueue.add(new Point(nx,ny));
                    }
                }
            }
            time++;
        }
        return -1;
    }
}
