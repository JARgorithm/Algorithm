package week5.chaeyeon.dp.gayeong;

public class Triangle {
    private static int[][] moves = {{-1, 0}, {-1, -1}};

    public int solution(int[][] triangle) {
        int row = triangle.length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i + 1; j++) {
                int temp = 0;
                for (int[] move : moves) {
                    int nx = i + move[0];
                    int ny = j + move[1];

                    if (nx < 0 || ny < 0 || ny >= triangle[i].length - 1) continue;

                    temp = Math.max(temp, triangle[nx][ny]);
                }

                triangle[i][j] += temp;
            }
        }

        int answer = 0;
        for (int sum : triangle[row - 1]) {
            answer = Math.max(answer, sum);
        }

        return answer;
    }
}
