package bfs.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class HideAndSeek3 {
    static int n, k;
    static int[] visited;
    static final int MAX_LENGTH = 100001;

    static class Node {
        int idx, dist;
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);

        visited = new int[MAX_LENGTH];
        Arrays.fill(visited, Integer.MAX_VALUE);

        bfs();

        System.out.println(visited[k]);
    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        visited[n] = 0;
        pq.offer(new Node(n, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist > visited[cur.idx]) continue;

            // 순간이동: 비용 0
            int nxt = cur.idx * 2;
            if (nxt < MAX_LENGTH && cur.dist < visited[nxt]) {
                visited[nxt] = cur.dist;
                pq.offer(new Node(nxt, cur.dist));
            }

            // 앞으로/뒤로: 비용 1
            nxt = cur.idx + 1;
            if (nxt < MAX_LENGTH && cur.dist + 1 < visited[nxt]) {
                visited[nxt] = cur.dist + 1;
                pq.offer(new Node(nxt, cur.dist + 1));
            }

            nxt = cur.idx - 1;
            if (nxt >= 0 && cur.dist + 1 < visited[nxt]) {
                visited[nxt] = cur.dist + 1;
                pq.offer(new Node(nxt, cur.dist + 1));
            }
        }
    }
}
