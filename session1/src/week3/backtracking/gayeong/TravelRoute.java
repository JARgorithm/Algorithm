package week3.backtracking.gayeong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class TravelRoute {
    private static String[][] tickets;
    private static PriorityQueue<String[]> routes;
    private static boolean[] used;
    private static int len;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.len = tickets.length;
        this.used = new boolean[len];
        this.routes = new PriorityQueue<>((o1, o2) -> {
            for (int i = 0; i < o1.length; i++) {
                int cmp = o1[i].compareTo(o2[i]);
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        String[] route = new String[len + 1];

        route[0] = "ICN";
        dfs(route[0], route, 0);

        return routes.poll();
    }

    private void dfs(String current, String[] route, int depth) {
        route[depth] = current;
        if (depth == len) {
            routes.offer(Arrays.copyOf(route, len + 1));
        }

        for (int i = 0; i < len; i++) {
            if (!used[i] && current.equals(tickets[i][0])) {
                used[i] = true;
                dfs(tickets[i][1], route, depth + 1);
                used[i] = false;
            }
        }
    }
}
