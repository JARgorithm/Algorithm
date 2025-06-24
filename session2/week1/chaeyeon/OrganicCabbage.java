package week1.chaeyeon;

import java.util.Scanner;

public class OrganicCabbage {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] field;
    static boolean[][] visited;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();


        for(int t=0; t<T; t++){
            int M = sc.nextInt();
            int N = sc.nextInt();
            int K = sc.nextInt();

            field = new int[N][M];
            visited= new boolean[N][M];

            for(int i=0; i<K;i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                field[y][x] = 1; //해당 부분 유의!!
            }

            int cnt=0;

            for(int i=0; i<N;i++){
                for(int j=0; j<M;j++){
                    if(field[i][j] == 1 && !visited[i][j]){
                        dfs(i,j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);

        }
    }

    static void dfs(int x, int y){
        visited[x][y] = true;

        for(int d=0; d<4;d++){
            int nx = x+dx[d];
            int ny = y+dy[d];

            if(nx>=0 && nx< field.length && ny>=0 &&ny<field[0].length){
                if(field[nx][ny] == 1 && !visited[nx][ny]){
                    dfs(nx,ny);
                }
            }
        }
    }
}
