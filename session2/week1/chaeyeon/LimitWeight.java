package week1.chaeyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LimitWeight {
    static class Node{
        int to;
        int weight;
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int n;
    static int m;
    static List<Node>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList[n+1];

        for(int i=1; i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            int a= sc.nextInt();
            int b= sc.nextInt();
            int c= sc.nextInt();

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        System.out.println(dijkstra(graph, start, end, n));
    }

    static int dijkstra(List<Node>[] graph, int start, int end, int n){
        int[] maxWeight = new int[n+1];
        boolean[] visited = new boolean[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) ->b.weight - a.weight);
        pq.offer(new Node(start, Integer.MAX_VALUE));
        maxWeight[start] = Integer.MAX_VALUE;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int now = current.to;

            if(visited[now])
                continue;
            visited[now] = true;

            for(Node next : graph[now]){
                int nextNode = next.to;
                int weightLimit = Math.min(maxWeight[now], next.weight);

                if (maxWeight[nextNode] < weightLimit){
                    maxWeight[nextNode] = weightLimit;
                    pq.offer(new Node(nextNode, weightLimit));
                }
            }
        }
        return maxWeight[end];
    }
}
