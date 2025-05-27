package backtracking.chaeyeon;

import java.util.*;

public class MakePassword {

    static int l, c;
    static String[] chars;
    static List<String> result = new ArrayList<>();
    static final Set<Character> vowels = Set.of('a','e','i','o','u');

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();
        chars =  sc.nextLine().split(" ");
        Arrays.sort(chars);

        dfs(0,0,0,"",0);
        for (String s : result){
            System.out.println(s);
        }
    }

    public static void dfs(int depth,int vowelCnt, int consonantCnt, String password, int start){
        if(depth == l){
            if(vowelCnt>=1 && consonantCnt>=2){
                result.add(password);
            }
            return;
        }

        for(int i = start; i<c;i++){
            char ch = chars[i].charAt(0);
            if(vowels.contains(ch)){
                dfs(depth+1, vowelCnt+1, consonantCnt, password+ch, i+1);
            }
            else{
                dfs(depth+1, vowelCnt, consonantCnt+1, password+ch, i+1);
            }
        }
    }
}
