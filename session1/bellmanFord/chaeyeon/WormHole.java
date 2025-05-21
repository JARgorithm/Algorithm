package bellmanFord.chaeyeon;

import java.util.*;

public class WormHole {

    static class Edge{
        int from, to, cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<TC; t++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int w = sc.nextInt();

            List<Edge> edges = new ArrayList<>();

            for(int i=0; i<m;i++){
                int s = sc.nextInt();
                int e=sc.nextInt();
                int tCost=sc.nextInt();
                edges.add(new Edge(s,e,tCost));
                edges.add(new Edge(e,s,tCost));
            }

            //웜홀 -> 음수 가중치
            for(int i=0; i<w; i++){
                int s=sc.nextInt();
                int e=sc.nextInt();
                int tCost=sc.nextInt();
                edges.add(new Edge(s,e,-tCost));
            }


            if (bellmanFord(n, edges)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

    static  boolean  bellmanFord(int n, List<Edge> edges){
        int[] dist = new int[n+1];
        Arrays.fill(dist, 0);

        for(int i=1;i<=n;i++){
            for(Edge edge : edges){
                if( dist[edge.to] > dist[edge.from] + edge.cost){
                    dist[edge.to] = dist[edge.from] +edge.cost;
                    if (i==n){
                        return true;
                    }
                }
            }
        }


        return false;
    }
}
