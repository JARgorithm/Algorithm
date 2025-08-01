package week5.chaeyeon;

public class IntegerTriangle {
    public int integerTriangle(int[][] triangle) {
        int n = triangle.length;

        for (int i= n-2; i>=0; i--){
            for(int j=0; j<=i;j++){
                triangle[i][j] += Math.max(triangle[i+1][j],triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}