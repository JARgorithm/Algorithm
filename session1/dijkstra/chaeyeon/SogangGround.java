package dijkstra.chaeyeon;

import java.util.*;

public class SogangGround {
    static class Node{
        int to;
        int cost;
        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    static int n,m,r;
    static int[] items;
    static  List<List<Node>> graph;

    static int finalResult=0; //얻을 수 있는 최대 아이템 수


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //지역 개수
        m = sc.nextInt(); //수색 범위
        r = sc.nextInt(); //길 개수

        items = new int[n];
        for(int i=0; i<n;i++){
            items[i]= sc.nextInt();
        }

        graph = new ArrayList<>();
        for(int i=0; i<n;i++){
            graph.add(new ArrayList<>());
        }
        
        for (int i=0; i<r; i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1 ;
            int l = sc.nextInt();
            graph.get(a).add(new Node(b,l));
            graph.get(b).add(new Node(a,l));
        }



        for(int i=0;i<n;i++){
            int[] resultDist =dijkstra(i);
            int ItemCnt = 0;

            for (int j=0;j<n;j++){
                if(resultDist[j] <=m){
                    ItemCnt+=items[j];
                }
            }

            finalResult = Math.max(finalResult,ItemCnt);


        }

        System.out.println(finalResult);
    }

    static int[] dijkstra (int start){
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.to]<cur.cost)
                continue;

            for(Node next : graph.get(cur.to)){
                if(dist[next.to] > dist[cur.to] + next.cost){
                    dist[next.to]  = dist[cur.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }

            }

        }

        return dist;
    }

}

