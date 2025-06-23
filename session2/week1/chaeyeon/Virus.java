package week1.chaeyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Virus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int computer = sc.nextInt();
        int pair = sc.nextInt();
        int cnt = 0; //1과 연결된 네트워크 수


        int [][] network = new int[computer+1][computer+1];
        boolean[] visited = new boolean[computer+1];

        for(int i=0; i<pair;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            network[a][b] = 1;
            network[b][a] = 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int i=1; i<= computer; i++){
                if(network[current][i] == 1 && !visited[i]){
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
