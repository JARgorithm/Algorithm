package week4.simulation.chaeyeon;

import java.util.Scanner;

public class RainDrop {
    static int h;
    static int w;
    static int[] blocks;
    static int cnt=0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();
        blocks=new int[w];

        for(int i=0; i<w; i++){
            blocks[i] = sc.nextInt();
        }

        for(int i=1;i<w;i++){
            int leftMax = 0;
            for(int j=0;j<i;j++){
                leftMax = Math.max(leftMax, blocks[j]);
            }

            int rightMax = 0;
            for(int j= i+1; j<w; j++){
                rightMax = Math.max(rightMax,blocks[j]);
            }

            int water = Math.min(leftMax, rightMax) - blocks[i];
            if(water>0){
                cnt+=water;
            }
        }

        System.out.println(cnt);
    }
}
