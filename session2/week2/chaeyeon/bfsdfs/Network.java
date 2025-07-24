package week2.chaeyeon.bfsdfs;

public class Network {
    public int network(int n, int[][] computers) {
        int cnt = 0; //네트워크 개수
        boolean[] visited = new boolean[n];

        //1. 방문 여부 체크
        //방문X -> cnt++; + 새로운 네트워크 시작점 (dfs 시작)
        for(int i=0; i< n; i++){
            if(!visited[i]){
                dfs(i, computers, visited, n);
                cnt++;
            }
        }

        return cnt;
    }

    //2. DFS -> 네트워크 간의 연결 여부를 확인
    private void dfs(int node, int[][] computers, boolean[] visited, int n){
        visited[node] = true;

        for(int i=0; i<n;i++){
            if(i!= node && computers[node][i] == 1 && !visited[i]){
                dfs(i,computers, visited,n);
            }
        }
    }

}