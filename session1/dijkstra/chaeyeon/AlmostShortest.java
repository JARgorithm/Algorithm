package dijkstra.chaeyeon;

import java.util.*;

public class AlmostShortest {

    static class Node{
        int to;
        int cost;
        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    static int n,m,s,d;
    static List<Node>[] graph;
    static List<Integer>[] prevNodes; //역추적용 -> 이전 정점들의 리스트
    static int[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0)
                break;

            s = sc.nextInt();
            d = sc.nextInt();
            graph = new ArrayList[n];
            prevNodes = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                prevNodes[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int p = sc.nextInt();
                graph[u].add(new Node(v, p));
            }


            dijkstra(s,graph,prevNodes); // 최단 경로 구함
            removeShortestPath(d);
            dist = dijkstra(s, graph, null); //2번째 최단 경로 구함
            System.out.println(dist[d] == Integer.MAX_VALUE ? -1 : dist[d]);


        }
    }
    static int[] dijkstra(int start, List<Node>[] graph, List<Integer>[] prevNodes) {
        int n = graph.length;
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node->node.cost));
        pq.offer(new Node(start,0));

        while ((!pq.isEmpty())){
            Node current = pq.poll();
            if (current.cost > dist[current.to])
                continue;

            for(Node next : graph[current.to]){
                int newCost = dist[current.to]+next.cost;
                if(newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                    if(prevNodes != null){
                        prevNodes[next.to].clear();
                        prevNodes[next.to].add(current.to);
                    }
                }
                else if(newCost == dist[next.to] && prevNodes !=null){ //같은 최단 거리 경로가 여러개일 경우
                    prevNodes[next.to].add(current.to);
                }
            }
        }
        return dist;
    }

    static void removeShortestPath(int end){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(end);
        boolean[][] visited = new boolean[n][n];

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int prev : prevNodes[current]){
                if(!visited[prev][current]){
                    visited[prev][current] = true;
                    graph[prev].removeIf(node -> node.to == current);
                    queue.offer(prev);
                }
            }
        }
    }
}
