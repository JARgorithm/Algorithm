package week1.chaeyeon;

import java.util.*;

public class Corner {
    static class Edge{
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Edge> edges = new ArrayList<>();
    static int N,M;
    static final int INF = Integer.MIN_VALUE / 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        List<Integer>[] graph = new ArrayList[N+1];

        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();


        for(int i=0; i<M;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(from,to,cost));
            graph[from].add(to); //BFS용 그래프
        }

        long[] dist = new long[N+1];
        int[] prev = new int[N+1];

        Arrays.fill(dist, INF);
        dist[1] = 0;
        Set<Integer> updatedNodes = new HashSet<>();

        for(int i=1;i<=N;i++){
            for(Edge e : edges){
                if(dist[e.from] == INF)
                    continue;
                if(dist[e.to] < dist[e.from]+e.cost){
                    dist[e.to] = dist[e.from]+e.cost;
                    prev[e.to] = e.from;

                    if(i ==N){ // i == N일 때 갱신된다면 => 사이클 가능성 존재
                        updatedNodes.add(e.to);
                    }
                }
            }
        }

        // BFS로 updatedNodes에서 출발해 N에 도달 가능한지 확인
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int node : updatedNodes) {
            queue.offer(node);
            visited[node] = true;
        }

        boolean infinite = false;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == N) {
                infinite = true;
                break;
            }
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        if (infinite) {
            System.out.println(-1);
            return;
        }

        if (dist[N] == INF) {
            System.out.println(-1);
            return;
        }


        List<Integer> path = new ArrayList<>();
        int current = N; //역으로 가는 것
        while(current!=0){
            path.add(current);
            current = prev[current];
        }

        Collections.reverse(path);

        for (int node : path) {
            System.out.print(node + " ");
        }

    }
}
