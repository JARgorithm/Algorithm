package week3.backtracking.chaeyeon;

import java.util.Scanner;

public class Sudoku {

    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new int[9][9];

        for(int i=0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(0,0);
    }

    static void dfs (int row, int col){
        if(col==9){
            dfs(row+1, 0);
            return;
        }

        if(row==9){
            printMap();
            System.exit(0);
        }

        if(map[row][col] == 0){
            for (int n=1;n<=9;n++){
                if(isValid(row,col,n)){
                    map[row][col] = n;
                    dfs(row,col+1);
                    map[row][col] = 0;
                }
            }
        }
        else{
            dfs(row, col+1);
        }
    }

    static boolean isValid(int row, int col, int n) {
        for(int i=0; i<9;i++){
            if (map[row][i]==n)
                return false;
        }

        for (int i=0; i<9;i++){
            if (map[i][col] == n)
                return false;
        }

        int mapRow = (row/3)*3;
        int mapCol = (col/3)*3;

        for(int i= mapRow; i<mapRow+3;i++){
            for(int j= mapCol; j<mapCol+3; j++){
                if(map[i][j] == n)
                    return false;
            }
        }
        return true;

    }

    static void printMap(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.println(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
