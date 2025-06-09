package week4.simulation.chaeyeon;

import java.util.Scanner;

public class putBall {
    static int n;
    static int m;
    static int[] basket;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        basket = new int[n + 1];

        for (int a= 0; a < m; a++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();

            for (int b = i; b<=j; b++){
                basket[b] = k;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(basket[i] + " ");
        }


    }
}
