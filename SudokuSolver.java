package com.anish;

import java.util.Arrays;
import java.util.HashSet;

public class SudokuSolver {
    public static HashSet<Integer> possible(int[][] grid, int row, int col){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            set.add(i);
        }
        for (int i = 0; i < 9; i++) {
            set.remove(grid[i][col]);
        }
        for (int i = 0; i < 9; i++) {
            set.remove(grid[row][i]);
        }
        for (int i = row - (row % 3); i < row - (row % 3) + 3; i++) {
            for (int j = col - (col % 3); j < col - (col % 3) + 3; j++) {
                set.remove(grid[i][j]);
            }
        }
        return set;
    }

    public static boolean isSolved(int[][] grid){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void solve(int[][] grid){
        if (isSolved(grid)) return;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0){
                    HashSet<Integer> sol = possible(grid,i,j);
                    if (sol.isEmpty()) return;
                    for (Integer possibility : sol){
                        grid[i][j] = possibility;
                        solve(grid);
                        if (isSolved(grid)) return;
                    }
                    grid[i][j] = 0;
                    return;
                }
            }
        }
    }


    public static void main(String[] args){
        int[][] grid =  {{5,3,0,0,7,0,0,0,0}
                        ,{6,0,0,1,9,5,0,0,0}
                        ,{0,9,8,0,0,0,0,6,0}
                        ,{8,0,0,0,6,0,0,0,3}
                        ,{4,0,0,8,0,3,0,0,1}
                        ,{7,0,0,0,2,0,0,0,6}
                        ,{0,6,0,0,0,0,2,8,0}
                        ,{0,0,0,4,1,9,0,0,5}
                        ,{0,0,0,0,8,0,0,7,9}};
        solve(grid);
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
