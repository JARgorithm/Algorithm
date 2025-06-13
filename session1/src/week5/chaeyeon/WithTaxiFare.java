package week5.chaeyeon;

public class WithTaxiFare {
    static final int INF = Integer.MAX_VALUE / 2;
    static int[][] dist;

    public int withTaxiFare(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
            }
        }

        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            dist[u][v] = cost;
            dist[v][u] = cost;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int result = INF;
        for (int k = 1; k <= n; k++) {
            if (dist[s][k] != INF && dist[k][a] != INF && dist[k][b] != INF) {
                result = Math.min(result, dist[s][k] + dist[k][a] + dist[k][b]);
            }
        }

        return result;
    }
}


