package bfs.gayeong;

import java.util.*;

public class ElectricityDivider {
    private ArrayList<Integer>[] trees;
    int n;

    public int solution(int n, int[][] wires) {
        this.n = n;

        trees  = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            trees[wire[0]].add(wire[1]);
            trees[wire[1]].add(wire[0]);
        }

        int result = Integer.MAX_VALUE;

        for (int[] wire : wires) {

            trees[wire[0]].remove(Integer.valueOf(wire[1]));
            trees[wire[1]].remove(Integer.valueOf(wire[0]));

            int diff = Math.abs(n - 2 * countWire(1));

            result = Math.min(result, diff);

            trees[wire[0]].add(wire[1]);
            trees[wire[1]].add(wire[0]);
        }

        return result;
    }

    private int countWire(int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            count++;

            for (int next : trees[current]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] wires = {
                {1, 3},
                {2, 3},
                {3, 4},
                {4, 5},
                {4, 6},
                {4, 7},
                {7, 8},
                {7, 9}
        };

        ElectricityDivider ed = new ElectricityDivider();

        int result = ed.solution(9, wires);

        System.out.println(result);
    }
}
