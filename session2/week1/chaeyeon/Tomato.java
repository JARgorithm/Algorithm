package week1.chaeyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato {
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};
    static int cnt=0;
    static int n, m;
    static int[][] box;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        box = new int[n][m];
        visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0; i<n;i++){
            for(int j=0;j<m;j++){
                box[i][j] = sc.nextInt();
                if(box[i][j] == 1){
                    queue.offer(new int[]{i,j}); //1인 곳은 모두 시작점으로 큐에 삽입!
                    visited[i][j] = true;
                }
            }
        }

        bfs(queue);

        boolean hasZero = false;
        for(int i=0; i<n && !hasZero; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0 && !visited[i][j]) {
                    hasZero = true;
                    break;
                }
            }
        }
        System.out.println(hasZero ? -1 : cnt);

    }

    public static void bfs(Queue<int[]> queue){
        while(!queue.isEmpty()){
            int size = queue.size(); //현재 레벨에서 처리할 노드 수! -> 현재 큐에 들어있는 만큼 상하좌우 토마토 익게 확장시켜야 하니까!
            
            for(int i=0; i<size;i++){
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for(int d=0; d<4;d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        if (box[nx][ny] == 0 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            if(!queue.isEmpty()) //현재 날짜에서 처리할 토마토는 다 끝냈고 + 다음 날짜에 처리할게 남아있다면 -> cnt 증가
                cnt++;
        }
    }
}
