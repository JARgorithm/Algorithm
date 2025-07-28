package week2.chaeyeon.bfsdfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Hide1 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int MAX = 100000;
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> deque = new ArrayDeque<>();
        dist[n] = 0;
        deque.add(n);

        while (!deque.isEmpty()) {
            int now = deque.pollFirst();

            if (now == k) {
                System.out.println(dist[now]);
                break;
            }

            int next = now * 2;
            if (next <= MAX && dist[next] == -1) {
                dist[next] = dist[now];
                deque.addFirst(next);
            }

            next = now - 1;
            if (next >= 0 && dist[next] == -1) {
                dist[next] = dist[now] + 1;
                deque.addLast(next);
            }

            next = now + 1;
            if (next <= MAX && dist[next] == -1) {
                dist[next] = dist[now] + 1;
                deque.addLast(next);
            }
        }
    }
}
