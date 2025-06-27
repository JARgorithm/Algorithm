package week1.chaeyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m;
    static int[][] lab;
    static int safeArea = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        lab = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j =0; j<m;j++){
                lab[i][j] = sc.nextInt();
            }
        }
        
        buildWall(0);
        System.out.println(safeArea);
    }
    
    static void buildWall(int cnt){ //벽 3개 세우는 모든 조합
        if(cnt == 3){
            spreadVirus(); //벽 다 세웠으니까 이제 바이러스 확산됨
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    buildWall(cnt + 1);
                    lab[i][j] = 0; //백트래킹
                }
            }
        }
    }

    static void spreadVirus(){
        int[][] temp = new int[n][m];
        for(int i=0; i<n;i++){
            temp[i] = lab[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<n;i++){
            for (int j=0;j<m;j++){
                if(temp[i][j] == 2){
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d=0; d<4;d++){
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx>=0 && nx<n && ny>=0 && ny<m){
                    if(temp[nx][ny] == 0){
                        temp[nx][ny] =2;
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        countSafeArea(temp);

    }

    static void countSafeArea(int[][] map){
        int safe = 0;

        for(int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                if(map[i][j] == 0){
                    safe++;
                }
            }
        }
        safeArea = Math.max(safeArea, safe);
    }
}
