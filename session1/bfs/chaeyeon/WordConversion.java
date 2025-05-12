import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin,0));

        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(current.word.equals(target)){
                return current.depth;
            }

            for(int i=0; i< words.length; i++){
                if(!visited[i] && canConvert(current.word, words[i])){
                    visited[i] = true;
                    queue.offer(new Node(words[i], current.depth+1));
                }
            }
        }

        return 0;
    }

    public boolean canConvert(String now, String next){
        int diff = 0;

        for(int i=0;i<now.length(); i++){
            if(now.charAt(i) != next.charAt(i)){
                diff++;
            }
        }

        if (diff == 1)
            return true;
        return false;

    }

    class Node {
        String word;
        int depth;

        Node(String word, int depth){
            this.word = word;
            this.depth = depth;
        }
    }
}