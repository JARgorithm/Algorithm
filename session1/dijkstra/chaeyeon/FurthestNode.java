package dijkstra.chaeyeon;

import java.util.*;

class FurthestNode {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        int[] distance = dijkstra(n,graph);

        int maxResult = 0;
        for(int i=1;i<=n;i++){
            if(distance[i] != Integer.MAX_VALUE){
                maxResult = Math.max(maxResult, distance[i]);
            }
        }

        int cnt = 0;
        for (int i=1; i<=n;i++){
            if(distance[i] == maxResult){
                cnt++;
            }
        }

        return cnt;
    }

    private int[] dijkstra(int n, List<List<Integer>> graph) {
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1,0});


        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];

            if(dist > distance[node])
                continue;

            for(int next : graph.get(node)){
                if(distance[next] > dist+1){
                    distance[next] = dist+1;
                    pq.offer(new int[]{next, dist+1});
                }
            }
        }
        return distance;

    }

}