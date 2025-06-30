package week2.chaeyeon;

import java.util.Scanner;

public class Operation {
    static int n;
    static int[] num;
    static int[] oper;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n];
        oper = new int[4];

        for(int i=0; i<n;i++){
            num[i] = sc.nextInt();
        }

        for(int i=0;i<4;i++){
            oper[i] = sc.nextInt();
        }

        backtrack(1, num[0]);
        System.out.println(max);
        System.out.println(min);


    }
    static void backtrack(int index, int result) {
        if (index == n) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                oper[i]--;

                int nextResult = 0;
                int currentNum = num[index];

                switch (i) {
                    case 0:
                        nextResult = result + currentNum;
                        break;
                    case 1:
                        nextResult = result - currentNum;
                        break;
                    case 2:
                        nextResult = result * currentNum;
                        break;
                    case 3:
                        if (result < 0) {
                            nextResult = -(-result / currentNum);
                        } else {
                            nextResult = result / currentNum;
                        }
                        break;
                }

                backtrack(index + 1, nextResult);
                oper[i]++; // 연산자 개수 복구
            }
        }
    }
}
