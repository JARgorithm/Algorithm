package bfs.gayeong;

public class TargetNumber {
    private int count = 0;

    public int solution(int[] numbers, int target) {
        dfs(0, numbers[0], numbers, target);
        dfs(0, -numbers[0], numbers, target);
        return count;
    }

    public void dfs(int depth, int sum, int[] numbers, int target) {
        if (depth == numbers.length - 1) {
            if (sum == target)
                count++;
            return;
        }

        depth++;
        dfs(depth, sum + numbers[depth], numbers, target);
        dfs(depth, sum - numbers[depth], numbers, target);

    }

    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};

        TargetNumber tn = new TargetNumber();
        int result = tn.solution(numbers, 4);
        System.out.println(result);
    }
}
