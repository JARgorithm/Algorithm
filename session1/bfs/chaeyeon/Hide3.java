package bfs.chaeyeon;

import java.util.*;

public class Hide3 {

    static final int MAX = 100001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] time = new int[MAX];
        Arrays.fill(time, -1);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        time[N] = 0;

        while(!deque.isEmpty()){
            int current = deque.poll();

            if(current*2 < MAX && time[current*2] ==-1){
                time[current*2] = time[current];
                deque.offerFirst(current*2);
            }

            if (current+1 < MAX && time[current+1] == -1) {
                time[current+1] = time[current]+1;
                deque.offerLast(current+1);
            }

            if(current-1 >= 0 && time[current - 1] == -1){
                time[current-1 ] = time[current] + 1;
                deque.offerLast(current-1 );
            }

        }
        System.out.println(time[K]);

    }
}
