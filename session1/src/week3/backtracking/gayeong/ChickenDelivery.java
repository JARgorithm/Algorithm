package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDelivery {
    static int N, M;
    static List<Point> houses = new ArrayList<>();
    static List<Point> chickens = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    houses.add(new Point(i, j));
                } else if (val == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }

        dfs(0, 0, new ArrayList<>());
        System.out.println(minDistance);
    }

    private static void dfs(int start, int depth, List<Point> selected) {
        if (depth == M) {
            int cityDist = getCityChickenDistance(selected);
            minDistance = Math.min(minDistance, cityDist);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            dfs(i + 1, depth + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static int getCityChickenDistance(List<Point> selectedChickens) {
        int total = 0;
        for (Point house : houses) {
            int dist = Integer.MAX_VALUE;
            for (Point chicken : selectedChickens) {
                dist = Math.min(dist, house.distance(chicken));
            }
            total += dist;
        }
        return total;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distance(Point p) {
            return  Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }
}


