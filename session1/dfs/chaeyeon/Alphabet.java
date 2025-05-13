package dfs.chaeyeon;

import java.util.*;

public class Alphabet {
    static int R,C;
    static char[][] map;
    static boolean[] visited = new boolean[26];
    static int result=0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map= new char[R][C];

        for(int i=0;i<R;i++){
            String row = sc.next();
            for(int j=0;j<C;j++){
                map[i][j] = row.charAt(j);
            }
        }

        visited[map[0][0] -'A'] = true;
        dfs(0, 0, 1);

        System.out.println(result);
    }

    static void dfs(int x, int y, int cnt){
        result = Math.max(result, cnt);

        for(int dir=0; dir<4;dir++){
            int nx = x+dx[dir];
            int ny = y+dy[dir];

            if(nx<0 || nx>=R || ny<0 || ny>=C){
                continue;
            }
            int alpha = map[nx][ny] - 'A';

            if(!visited[alpha]){
                visited[alpha] = true;
                dfs(nx,ny,cnt+1);
                visited[alpha] = false;
            }
        }
    }
}
