package week1.chaeyeon;

import java.util.*;

public class MinimumCost {
    static class Edge{
        int to;
        int cost;
        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(from).add(new Edge(to, cost));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int now = current.to;
            int nowCost = current.cost;

            if(nowCost > dist[now])
                continue;

            for(Edge next : graph.get(now)){
                if(dist[next.to] > dist[now] + next.cost){
                    dist[next.to] = dist[now] + next.cost;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
        System.out.println(dist[end]);

    }




}
