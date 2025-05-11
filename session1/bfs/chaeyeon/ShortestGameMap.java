import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point{
        int x,y, distance;

        Point(int x, int y, int distance){
            this.x=x;
            this.y=y;
            this.distance=distance;

        }
    }

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0,0,1));
        visited[0][0] = 1;

        while(!queue.isEmpty()){
            Point current = queue.poll();

            if (current.x ==n-1 && current.y == m-1){
                return current.distance;
            }

            for(int i=0; i<4;i++){
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx>=0 && nx<n && ny>=0 && ny<m){
                    if(maps[nx][ny] == 1 && visited[nx][ny] == 0){
                        visited[nx][ny] = current.distance+1;
                        queue.offer(new Point(nx,ny,current.distance+1));
                    }
                }
            }
        }
        return -1;

    }
}