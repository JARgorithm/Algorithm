package bfs.chaeyeon;
import java.util.*;

public class CrushWall {

    static int N,M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx={-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map=new int[N][M];
        visited = new boolean[N][M][2];

        for(int i=0;i<N;i++){
            String row = sc.next();
            for(int j=0;j<M;j++){
                map[i][j] = row.charAt(j) - '0';
            }
        }
        int result = bfs();
        System.out.println(result);
    }

    static class Node{
        int x,y,distance, crush;
        Node(int x, int y, int distance, int crush){
            this.x=x;
            this.y=y;
            this.distance=distance;
            this.crush=crush;
        }
    }

    static int bfs(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0,1,0));
        visited[0][0][0] = true; 
        
        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(current.x == N-1 && current.y ==M-1){
                return current.distance;
            }
            
            for(int dir=0;dir<4;dir++){
                int nx = current.x+dx[dir];
                int ny = current.y+dy[dir];
                
                if(nx<0 || nx>=N || ny<0 || ny>=M){
                    continue;
                }
                
                //-> 다음 위치가 빈칸일 경우
                if(map[nx][ny] == 0 && !visited[nx][ny][current.crush]){
                    visited[nx][ny][current.crush] = true;
                    queue.offer(new Node(nx,ny, current.distance+1, current.crush));
                }

                //->다음 위치가 벽이고 한번도 부수지 않은 상태일 경우
                if(map[nx][ny]==1 && current.crush==0 &&!visited[nx][ny][1]){
                    visited[nx][ny][1] = true;
                    queue.offer(new Node(nx,ny, current.distance+1,1));
                }
            }
        }
        return -1;
    }
}
