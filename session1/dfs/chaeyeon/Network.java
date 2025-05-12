class Solution {
    static int cnt = 0;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(n, computers, i);
                cnt++;
            }
        }
        return cnt;
    }

    public void dfs(int n, int[][] computers, int current) {
        visited[current] = true;

        for (int i = 0; i < n; i++) {
            if (computers[current][i] == 1 && !visited[i]) {
                dfs(n, computers, i);
            }
        }
    }
}
