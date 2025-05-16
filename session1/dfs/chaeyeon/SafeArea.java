package dfs.chaeyeon;

import java.util.*;

public class SafeArea {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static void dfs(int x, int y, int height){
        visited[x][y] = true;

        for(int dir=0; dir<4;dir++){
            int nx = x+dx[dir];
            int ny = y+dy[dir];

            if(nx>=0 && nx<N && ny>=0 && ny<N){
                if(!visited[nx][ny] && map[nx][ny] > height){
                    dfs(nx,ny,height);
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        int height =0;
        int result = 0; //안전 영역

        for(int i=0; i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
                height = Math.max(height, map[i][j]);
            }
        }

        for (int h=0; h<=height;h++){
            visited = new boolean[N][N];
            int cnt = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j] && map[i][j] > h){
                        dfs(i,j,h);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }
}
