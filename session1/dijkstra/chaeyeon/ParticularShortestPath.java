package dijkstra.chaeyeon;

import java.util.*;

public class ParticularShortestPath {
    static class Node{
        int index;
        int cost;
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<e;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(from).add(new Node(to,cost));
            graph.get(to).add(new Node(from,cost));
        }

        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        int[] dist1 = dijkstra(1, n);
        int[] distV1 = dijkstra(v1, n);
        int[] distV2 = dijkstra(v2, n);

        int route1 = dist1[v1] + distV1[v2]+distV2[n];
        int route2 = dist1[v2] + distV2[v1]+distV1[n];
        int result = Math.min(route1,route2);

        System.out.println((result >= INF) ? -1 : result);
    }

    static int[] dijkstra(int start, int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.cost));
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(dist[current.index] < current.cost)
                continue;
            for(Node next : graph.get(current.index)){
                int newCost = dist[current.index] + next.cost;
                if(newCost < dist[next.index]){
                    dist[next.index] = newCost;
                    pq.offer(new Node(next.index, newCost));
                }
            }
        }
        return dist;
    }

}
