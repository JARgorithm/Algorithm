package week6.joochaeyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectLand {
    public int connectLand(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0; i<n;i++)
            graph.add(new ArrayList<>());

        for(int [] cost : costs){
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            graph.get(from).add(new int[]{to, weight});
            graph.get(to).add(new int[]{from,weight});
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        visited[0] = true;
        pq.addAll(graph.get(0));

        int answer = 0;
        int connected = 1;

        while(connected <n){
            int[] next= pq.poll();
            int node = next[0];
            int cost = next[1];

            if(visited[node])
                continue;

            visited[node] = true;
            answer+=cost;
            connected++;

            for(int[]neighbor :graph.get(node)){
                if(!visited[neighbor[0]]){
                    pq.offer(neighbor);
                }
            }
        }
        return answer;

    }
}
