package week3.backtracking.gayeong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class NQueen {
    static int N;
    static int[] board;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N];
        dfs(0);
        System.out.println(count);
    }

    static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            board[row] = col;
            if (isValid(row)) {
                dfs(row + 1);
            }
        }
    }

    static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            // 퀸은 칸의 수에 상관없이, 한 방향으로 쭉 움직일 수 있다.
            // 가로, 세로, 대각선으로 모두 움직일 수 있다.
            // 같은 열이거나, 대각선에 위치해 있는 경우
            if (board[i] == board[row] || Math.abs(row - i) == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}