package week4.simulation.chaeyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Escape {
    static int r;
    static int c;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<int[]> waterq = new LinkedList<>();
    static Queue<int[]> hedgehogq = new LinkedList<>();

    // . : 비어있는 곳
    // * : 물이 차 잇는 곳
    // X : 돌
    // D : 비버 굴
    // S : 고슴도치 위치
    // 물 -> 매 분 마다 비어있는 곳으로 확장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r=sc.nextInt();
        c =sc.nextInt();
        sc.nextLine();
        map=new char[r][c];

        for(int i=0; i<r;i++){
            String line = sc.nextLine();
            for(int j=0; j<c;j++){
                map[i][j] = line.charAt(j);

                if(map[i][j] == '*'){
                    waterq.offer(new int[]{i,j});
                }
                else if (map[i][j] =='S'){
                    hedgehogq.offer(new int[]{i,j,0});
                }
            }
        }

        int result = bfs();
        if (result == -1){
            System.out.println("KAKTUS");
        }
        else{
            System.out.println(result);
        }
    }

    public static int bfs(){
        while(!hedgehogq.isEmpty()){
            int waterSize = waterq.size();
            for(int i=0; i<waterSize;i++){
                int [] current = waterq.poll();
                for(int d=0; d<4;d++){
                    int nx = current[0] +dx[d];
                    int ny = current[1] + dy[d];
                    if(nx>=0&&nx <r&& ny>=0 && ny<c){
                        if(map[nx][ny] =='.'){
                            map[nx][ny]='*';
                            waterq.offer(new int[]{nx,ny});
                         }
                    }
                }
            }

            int hedgehogSize = hedgehogq.size();
            for(int i=0; i< hedgehogSize;i++){
                int[] current = hedgehogq.poll();
                for(int d=0; d<4;d++){
                    int nx = current[0] +dx[d];
                    int ny = current[1] + dy[d];
                    if(nx>=0&&nx <r&& ny>=0 && ny<c){
                        if(map[nx][ny] == 'D'){
                            return current[2]+1;
                        }

                        if(map[nx][ny] == '.'){
                            map[nx][ny] = 'S';
                            hedgehogq.offer(new int[] {nx,ny, current[2]+1});
                        }
                    }
                }
            }

            System.out.println();
            System.out.println("현재 map 상태:");
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.out.println("------------------------");
        }


        return -1;
    }


}
