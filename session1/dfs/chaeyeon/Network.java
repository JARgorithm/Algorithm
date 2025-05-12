class Solution {
    static int cnt = 0;

    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];

        for (int i=0; i<n;i++){
            if(!visited[i]){
                dfs(n, computers, visited, i);
                cnt++;
            }
        }
        return cnt;
    }

    public void dfs(int n, int[][] computers, boolean[] visited, int current){
        visited[current] = true;

        for(int i=0;i<n;i++){
            if (computers[current][i] == 1 && !visited[i]){
                dfs(n, computers, visited, i);
            }
        }
    }
}