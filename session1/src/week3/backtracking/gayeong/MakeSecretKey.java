package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakeSecretKey {
    private static int L, C;
    private static String[] alphabets;
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lc = br.readLine().split(" ");
        L = Integer.parseInt(lc[0]);
        C = Integer.parseInt(lc[1]);

        alphabets = br.readLine().split(" ");
        Arrays.sort(alphabets);

        dfs(0, 0, "", 0, 0);

        System.out.print(sb);
    }

    static void dfs(int start, int depth, String cur, int vowelCnt, int consonantCnt) {
        if (depth == L) {
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                sb.append(cur).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char c = alphabets[i].charAt(0);
            if (VOWELS.contains(c)) {
                dfs(i + 1, depth + 1, cur + c, vowelCnt + 1, consonantCnt);
            } else {
                dfs(i + 1, depth + 1, cur + c, vowelCnt, consonantCnt + 1);
            }
        }
    }
}
