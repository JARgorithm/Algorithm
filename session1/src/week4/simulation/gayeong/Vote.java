package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Vote {
    private static int N;
    private static int dasom;
    private static PriorityQueue<Integer> voteResult = new PriorityQueue<>((o1, o2) -> {
        return o2 - o1;
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dasom = Integer.parseInt(br.readLine());

        for (int i = 0; i < N - 1; i++) {
            voteResult.offer(Integer.parseInt(br.readLine()));
        }

        System.out.println(solution());
    }

    private static int solution() {
        int count = 0;
        while (!voteResult.isEmpty()) {
            int now = voteResult.poll();

            if (now < dasom) return count;

            dasom++;
            count++;
            voteResult.offer(now - 1);
        }

        return 0;
    }

}
