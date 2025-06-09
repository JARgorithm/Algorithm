package week5.chaeyeon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionN {
    public int expressionN(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();

        if(N==number)
            return 1;

        for(int i=0; i<=8;i++){
            dp.add(new HashSet<>());
        }

        for(int i=1; i<=8;i++){
            int repeatN = Integer.parseInt(String.valueOf(N).repeat(i));
            dp.get(i).add(repeatN);

            for(int j =1; j<i;j++){
                for(int a : dp.get(j)){
                    for(int b : dp.get(i-j)){
                        dp.get(i).add(a+b);
                        dp.get(i).add(a-b);
                        dp.get(i).add(a*b);
                        if(b!=0)
                            dp.get(i).add(a/b);
                    }
                }
            }

            if(dp.get(i).contains(number)) {
                return i;
            }
        }
        return -1;
    }
}