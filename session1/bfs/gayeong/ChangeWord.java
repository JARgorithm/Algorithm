package bfs.gayeong;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChangeWord {
    private Map<String, Integer> visited;
    private String[] words;
    private String begin;
    private String target;

    public void init (String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        this.visited = new HashMap<>();
    }

    public int solution(String begin, String target, String[] words) {
        init(begin, target, words);

        bfs();

        int result = visited.getOrDefault(target, 0);

        return result == 0 ? 0 : result - 1;
    }

    private void bfs() {
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        visited.put(begin, 1);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) break;

            for (String next : words) {
                if (visited.getOrDefault(next, 0) == 0 && possible(current, next)) {
                    queue.offer(next);
                    visited.put(next, visited.get(current) + 1);
                }
            }
        }
    }

    private boolean possible(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) count++;
        }

        if (count == 1) return true;
        return false;
    }
}
