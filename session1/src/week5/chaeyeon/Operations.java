package week5.chaeyeon;

public class Operations {
    static int[][] maxDp;
    static int[][] minDp;
    public int opertaions(String arr[]) {
        int n = (arr.length+1)/2;
        maxDp = new int[n][n];
        minDp= new int[n][n];

        for(int i=0; i<n;i++){
            int num = Integer.parseInt(arr[i*2]);
            maxDp[i][i] = num;
            minDp[i][i] =num;
        }

        for (int len=1; len<n;len++){
            for(int i=0; i<n-len;i++){
                int j = i+len;
                maxDp[i][j]=Integer.MIN_VALUE;
                minDp[i][j]=Integer.MIN_VALUE;

                for(int k=i; k<j;k++){
                    String op = arr[k*2+1];
                    if(op.equals("+")){
                        maxDp[i][j] = Math.max(maxDp[i][j],maxDp[i][k] + maxDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] + minDp[k + 1][j]);
                    }
                    else if(op.equals("-")){
                        maxDp[i][j] = Math.max(maxDp[i][j],maxDp[i][k] - maxDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] - minDp[k + 1][j]);
                    }
                }
            }
        }
        return maxDp[0][n-1];

    }

}
