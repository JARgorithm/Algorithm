package dijkstra.chaeyeon;

import java.util.*;
public class Delivery {
    static int cnt = 0;
    static final int INF = Integer.MAX_VALUE;


    static class Node{
        int index;
        int cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }

    public int delivery(int N, int[][] road, int K) {
        List<List<Node>> graph = new ArrayList<>();
        int[] dist = new int[N+1];

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] r : road){ //양방향 그래프 연결
            int a = r[0];
            int b = r[1];
            int c = r[2];

            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));

        }

        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(1,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int nowNode = current.index;
            int cost = current.cost;

            if (dist[nowNode] < cost)
                continue;

            for(Node next : graph.get(nowNode)){
                int newCost = dist[nowNode] + next.cost;
                if(newCost < dist[next.index]) {
                    dist[next.index] = newCost;
                    pq.offer(new Node(next.index, newCost));

                }
            }
        }

        for(int i=1; i<=N;i++){
            if(dist[i] <=K)
                cnt++;
        }

        return cnt;
    }
}
