package dijkstra.chaeyeon;

import java.util.*;

public class Party {

    static class Node{
        int to;
        int time;
        public Node(int to, int time){
            this.to = to;
            this.time = time;
        }
    }

    static int n,m,x;
    static List<Node>[] graph;
    static int[] distFromX, distToX;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();

        graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            int from = sc.nextInt();
            int to  = sc.nextInt();
            int time = sc.nextInt();
            graph[from].add(new Node(to, time));
        }


        distFromX = dijkstra(x,graph);

        //양방향이 아니기에
        List<Node>[] reverseGraph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            reverseGraph[i] = new ArrayList<>();
        }
        for(int from = 1; from<=n;from++){
            for(Node node : graph[from]){
                reverseGraph[node.to].add(new Node(from, node.time));
            }
        }

        distToX = dijkstra(x,reverseGraph);

        int result = 0;
        for (int i=1; i<=n; i++){
            int cnt = distFromX[i] +distToX[i];
            result = Math.max(result, cnt);
        }

        System.out.println(result);





    }

    static int[] dijkstra(int start, List<Node>[] graph){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n->n.time));
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(current.time > dist[current.to])
                continue;
            for(Node n :graph[current.to]){
                if(dist[n.to] > dist[current.to] + n.time){
                    dist[n.to] = dist[current.to] + n.time;
                    pq.offer(new Node(n.to, dist[n.to]));
                }
            }
        }
        return dist;
    }
}
