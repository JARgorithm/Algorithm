package dfs.gayeong;

import java.util.*;

public class TravelRoute {
    private List<String[]> routes;
    private String[][] tickets;
    private boolean[] used;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.used = new boolean[tickets.length + 1];

        routes = new ArrayList<>();

        String[] route = new String[tickets.length];
        route[0] = "ICN";

        dfs("ICN", route, 0);

        routes.sort((a, b) -> {
            for (int i = 0; i < a.length; i++) {
                int temp = a[i].compareTo(b[i]);
                if (temp != 0) return temp;
            }
            return 0;
        });

        return routes.get(0);
    }

    private void dfs(String current, String[] route, int depth) {
        route[depth] = current;
        if (depth == tickets.length) {
            routes.add(Arrays.copyOf(route, route.length));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!used[i] && current.equals(tickets[i][0])) {
                used[i] = true;
                dfs(tickets[i][1], route, depth + 1);
                used[i] = false;
            }
        }
    }

}
