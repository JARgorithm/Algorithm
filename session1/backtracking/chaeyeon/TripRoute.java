package backtracking.chaeyeon;

import java.util.*;

public class TripRoute {
    static boolean[] visited;

    public String[] tripRoute(String[][] tickets) {
        visited = new boolean[tickets.length];
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        List<String> route = new ArrayList<>();
        route.add("ICN");

        return dfs("ICN", route, tickets,0);
    }

    public String[] dfs(String current, List<String> route,String[][] tickets, int cnt){
        if (cnt == tickets.length){
            return route.toArray(new String[0]);
        }

        for(int i=0; i< tickets.length; i++){
            if(!visited[i]&& tickets[i][0].equals(current)){
                visited[i] = true;
                route.add(tickets[i][1]);

                String[] result = dfs(tickets[i][1], route, tickets, cnt + 1);
                if (result != null) {
                    return result;
                }

                //백트래킹
                visited[i] = false;
                route.remove(route.size() - 1);
            }
        }
        return null;
    }
}
