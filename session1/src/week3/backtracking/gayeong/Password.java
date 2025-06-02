package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Password {
    private static int L, C;
    private static Character[] alphabets;
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args)  throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = Arrays.stream(br.readLine().split(" "))
                .map(s -> s.charAt(0))
                .toArray(Character[]::new);
        Arrays.sort(alphabets);

        dfs(0, 0, "", 0, 0);

        System.out.println(sb);
    }

    private static void dfs(int start, int depth, String pwd, int vowelCnt, int consCnt) {
        if (depth == L) {
            if (vowelCnt >= 1 && consCnt >= 2) sb.append(pwd).append("\n");

            return;
        }

        for (int i = start; i < C; i++) {
            char c = alphabets[i];
            if (VOWELS.contains(c)) {
                dfs(i + 1, depth + 1, pwd + c, vowelCnt + 1, consCnt);
            } else {
                dfs(i + 1, depth + 1, pwd + c, vowelCnt, consCnt + 1);
            }
        }

    }
}
