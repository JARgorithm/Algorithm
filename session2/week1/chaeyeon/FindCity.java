package week1.chaeyeon;

import java.util.*;

public class FindCity {
    static int n;
    static int m;
    static int k;
    static int x;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] distance;

    static class Node{
        int city;
        int distance;
        Node(int city, int distance){
            this.city = city;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m= sc.nextInt();
        k= sc.nextInt();
        x= sc.nextInt();

        for(int i=0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph.get(from).add(to); //단방향
        }

        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        dijkstra(x);


        List<Integer> result = new ArrayList<>();
        for(int i=1; i<=n;i++){
            if(distance[i] == k){
                result.add(i);
            }
        }
        if(result.isEmpty()){
            System.out.println(-1);
        }
        else{
            for(int i : result){
                System.out.println(i);
            }
        }

    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.distance - b.distance);
        distance[start] = 0;
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int nowCity = current.city;
            int nowDist = current.distance;

            if(distance[nowCity] < nowDist)
                continue;

            for(int next : graph.get(nowCity)){
                if(distance[next] > nowDist+1){
                    distance[next] = nowDist+1;
                    pq.offer(new Node(next, distance[next]));
                }
            }
        }

    }
}
