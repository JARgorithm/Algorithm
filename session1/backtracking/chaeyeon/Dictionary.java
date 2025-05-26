package backtracking.chaeyeon;

public class Dictionary {
    static String[] vowels = {"A","E","I","O","U"};
    static int cnt = 0;

    public int dictionary(String word) {
        return dfs("",word);
    }

    public int dfs(String word, String target){
        if(word.equals(target))
            return cnt;

        if(word.length() == 5)
            return -1;

        for(int i=0;i<5;i++){
            cnt++;
            int result =dfs(word+vowels[i], target);
            if(result!=-1)
                return result;
        }
        return -1;
    }

    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        int result = dict.dictionary("AAAAE");
        System.out.println(result);
    }
}
