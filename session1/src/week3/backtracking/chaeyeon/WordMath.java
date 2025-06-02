package week3.backtracking.chaeyeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordMath {

    static int n;
    static String[] words;
    static ArrayList<Character> letters = new ArrayList<>();
    static boolean[] visited = new boolean[10];
    static Map<Character, Integer> map = new HashMap<>();
    static int max  = 0;

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        words = new String[n];

        for(int i =0; i<n;i++){
            words[i] = sc.nextLine();
            for (char c : words[i].toCharArray()){
                if(!letters.contains(c)){
                    letters.add(c);
                }
            }
        }
        dfs(0);
        System.out.println(max);

    }

    static void dfs(int index) {
        if(index == letters.size()) {
            int sum = 0;
            for (String w : words) {
                int num = 0;
                for (char c : w.toCharArray()) {
                    num = num * 10 + map.get(c);
                }
                sum += num;
            }
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<=9;i++){
            if(!visited[i]){
                visited[i] = true;
                map.put(letters.get(index), i);
                dfs(index+1);
                visited[i] = false;
                map.remove(letters.get(index));
            }
        }
    }
}
