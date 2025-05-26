package dijkstra.chaeyeon;
import java.util.*;
class SharedTaxiFare {
    static class Node{
        int index,cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }

    public int sharedTaxiFare(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();

        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] fare : fares){
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];
            graph.get(from).add(new Node(to,cost));
            graph.get(to).add(new Node(from,cost));
        }

        int[] distS =  dijkstra(s, n, graph);
        int[] distA =  dijkstra(a, n, graph);
        int[] distB =  dijkstra(b, n, graph);

        int minCost = Integer.MAX_VALUE;
        for (int k=1; k<=n; k++){
            if(distS[k] == Integer.MAX_VALUE || distA[k] == Integer.MAX_VALUE || distB[k] == Integer.MAX_VALUE)
                continue;

            int result = distS[k] + distA[k] + distB[k];
            minCost = Math.min(minCost, result);

        }
        return minCost;

    }

    static int[] dijkstra(int start, int n, List<List<Node>> graph){
        int[] dist = new int[n+1];
        Arrays.fill(dist, 100_000_000);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing((Node node) -> node.cost));
        pq.offer(new Node(start,0));

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