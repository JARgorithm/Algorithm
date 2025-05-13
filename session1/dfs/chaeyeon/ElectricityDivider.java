package dfs.chaeyeon;
import java.util.*;

class ElectricityDivider {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        //연결하기
        for(int[] wire : wires){
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }

        for(int[] wire:wires){
            visited = new boolean[n+1];

            int a = wire[0];
            int b = wire[1];
            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));

            int cnt = dfs(1); //1번 노드에서 시작이기에

            int diff = Math.abs(cnt-(n-cnt));
            answer = Math.min(answer,diff);

            graph[a].add(b);
            graph[b].add(a);
        }

        return answer;
    }

    static int dfs(int node){
        visited[node] = true;
        int cnt = 1;

        for(int next : graph[node]){
            if(!visited[next]){
                cnt+=dfs(next);
            }
        }
        return cnt;
    }
}