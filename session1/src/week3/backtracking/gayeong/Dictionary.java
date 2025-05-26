package week3.backtracking.gayeong;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    static String[] alpahbets = {"A", "E", "I", "O", "U"};
    static ArrayList<String> dictionary;

    public int solution(String word) {
        dictionary = new ArrayList<>();

        dfs("", 0);

        return findIndex(word);
    }

    private void dfs(String word, int depth) {
        if (depth == alpahbets.length + 1) return;

        dictionary.add(word);

        for (String s : alpahbets) {
            String newWord = word + s;
            dfs(newWord, depth + 1);
        }
    }

    private int findIndex(String word) {

        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(word)) return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        Dictionary dic = new Dictionary();

        int result = dic.solution("AAAAE");

        System.out.println(result);
    }
}
