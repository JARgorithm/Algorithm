package week1.chaeyeon;

import java.util.*;


public class Marry {
    static int cnt =0;
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList<>();
        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b); //양방향으로 연결!
            graph.get(b).add(a);
        }

        visited = new boolean[n+1];
        bfs(1); //시작점이 1이 되어야 한다.
        System.out.println(cnt);
    }
    public static void bfs(int start){
        Queue<int[]> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(new int[]{start,0}); //{현재 사람, 깊이}

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int person = current[0];
            int depth = current[1];

            if(depth>=1 && depth<=2){
                cnt++;
            }

            if(depth>=3)
                break;

            for(int next : graph.get(person)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(new int[]{next, depth+1});
                }
            }

        }



     }

}
