package backtracking.chaeyeon;

import java.util.*;

public class TripRoute {
    static List<String[]> path = new ArrayList<>();
    static boolean[] visited;

    public String[] tripRoute(String[][] tickets) {
        visited = new boolean[tickets.length];
        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1])); //도착지 기준 정렬
        List<String> route = new ArrayList<>();
        route.add("ICN");
        dfs("ICN", route, tickets,0);

        return path.get(0);
    }

    public void dfs(String current, List<String> route,String[][] tickets, int cnt){
        if (cnt == tickets.length){
            path.add(route.toArray(new String[0]));
            return;
        }

        for(int i=0; i< tickets.length; i++){
            if(!visited[i]&& tickets[i][0].equals(current)){
                visited[i] = true;
                route.add(tickets[i][1]);
                dfs(tickets[i][1], route, tickets, cnt+1);

                //백트래킹
                visited[i] = false;
                route.remove(route.size() - 1);
            }
        }
    }
}
