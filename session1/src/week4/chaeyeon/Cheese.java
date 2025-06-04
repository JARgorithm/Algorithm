package week4.chaeyeon;

import java.util.*;

public class Cheese {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map=new int[n][m];

        for(int i=0; i<n;i++){
            for (int j=0; j<m;j++){
                map[i][j] = sc.nextInt();
            }
        }

        int time = 0;
        while(true){

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                    }
                }
            }

            visited = new boolean[n][m];
            bfs();

            List<int[]> melt = new ArrayList<>();
            for(int i=0; i<n;i++){
                for(int j = 0; j<m;j++){
                    if(map[i][j] ==1 && shouldMelt(i, j)){
                        melt.add(new int[]{i,j});
                    }
                }
            }

            if(melt.isEmpty())
                break;

            for(int[] pos : melt){
                map[pos[0]][pos[1]] = 0;
            }
            time++;
        }
        System.out.println(time);
    }

    public static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0]=true;

        while(!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            map[x][y] =-1;

            for(int dir=0; dir<4;dir++){
                int nx = x+dx[dir];
                int ny = y+dy[dir];
                if(nx<0 || nx>=n || ny<0 || ny>=m)
                    continue;
                if(visited[nx][ny])
                    continue;
                if(map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
    }

    public static boolean shouldMelt(int x, int y){
        int cnt = 0;
        for(int dir=0; dir<4;dir++){
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if(nx<0 || nx>=n || ny<0 || ny>=m)
                continue;
            if(map[nx][ny]==-1)
                cnt++;
        }
        return cnt >= 2;
    }
}
