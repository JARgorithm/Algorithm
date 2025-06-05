package week3.backtracking.chaeyeon;

import java.util.Scanner;

public class Election {
    static int n;
    static int[] votes;
    static int cnt=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        votes = new int[n];

        for(int i=0;i<n;i++){
            votes[i] = sc.nextInt();
        }

        if(n==1){
            System.out.println(cnt);
            return;
        }

        while(true){
            int maxIndex = getMaxIndex();

            if(votes[0] > votes[maxIndex]){
                break;
            }

            votes[maxIndex]--;
            votes[0]++;
            cnt++;
        }
        System.out.println(cnt);
    }

    public static int getMaxIndex(){
        int max = 0;
        int index = 1;

        for(int i=1;i<n;i++){
            if(votes[i] >max) {
                max = votes[i];
                index = i;
            }
        }
        return index;
    }
}
