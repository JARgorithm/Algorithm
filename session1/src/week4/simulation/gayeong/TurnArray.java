package week4.simulation.gayeong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TurnArray {
    static int N, M, K;
    static int[][] origin;
    static int[][] rotateInfo;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateInfo = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotateInfo[i][0] = Integer.parseInt(st.nextToken());
            rotateInfo[i][1] = Integer.parseInt(st.nextToken());
            rotateInfo[i][2] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[K];
        dfs(0, new ArrayList<>());
        System.out.println(answer);
    }

    // 회전 순열 만들기
    static void dfs(int depth, List<Integer> sequence) {
        if (depth == K) {
            int[][] copied = deepCopy(origin);
            for (int idx : sequence) {
                rotate(copied, rotateInfo[idx]);
            }
            answer = Math.min(answer, getMinRowSum(copied));
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence.add(i);
                dfs(depth + 1, sequence);
                sequence.remove(sequence.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 배열 복사
    static int[][] deepCopy(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i].clone();
        }
        return newArr;
    }

    // 회전 연산
    static void rotate(int[][] arr, int[] op) {
        int r = op[0] - 1;
        int c = op[1] - 1;
        int s = op[2];

        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer;
            int left = c - layer;
            int bottom = r + layer;
            int right = c + layer;

            int temp = arr[top][left];
            // 왼쪽 열을 위로 당김
            for (int i = top; i < bottom; i++) arr[i][left] = arr[i + 1][left];
            // 아래쪽 행을 왼쪽으로 당김
            for (int i = left; i < right; i++) arr[bottom][i] = arr[bottom][i + 1];
            // 오른쪽 열을 아래로 당김
            for (int i = bottom; i > top; i--) arr[i][right] = arr[i - 1][right];
            // 위쪽 행을 오른쪽으로 당김
            for (int i = right; i > left + 1; i--) arr[top][i] = arr[top][i - 1];
            arr[top][left + 1] = temp;
        }
    }

    // 최솟값 계산
    static int getMinRowSum(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int[] row : arr) {
            int sum = 0;
            for (int val : row) sum += val;
            min = Math.min(min, sum);
        }
        return min;
    }
}
