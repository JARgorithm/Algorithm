package week3.backtracking.chaeyeon;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LetCard {
    static int n;
    static int k;
    static String[] cards;
    static boolean[] visited;
    static Set<String> result = new HashSet<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        cards = new String[n];
        visited = new boolean[n];

        for(int i=0;i<n;i++){
            cards[i] = sc.next();
        }

        dfs("", 0);
        System.out.println(result.size());



    }

    static void dfs(String current, int depth){
        if(depth == k){
            result.add(current);
            return;
        }

        for(int i=0; i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(current+cards[i], depth+1);
                visited[i] = false;
            }
        }

    }
}
