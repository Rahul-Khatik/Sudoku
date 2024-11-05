package sudoku.computationlogic;

import sudoku.problemdomain.SudokuGame;

public class SudokuUtilities {
    public static void copySudokuArrayValue(int[][] oldArray,int [][] newArray){
        for(int xIndex=0;xIndex<9;xIndex++){
            for(int yIndex=0;yIndex<9;yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    public static int[][] copyToNewArray(int[][] oldArray){
        int [][] newArray = new int[SudokuGame.GRID_BOUNDARY][SudokuGame.GRID_BOUNDARY];
        for(int xIndex=0;xIndex<9;xIndex++){
            for(int yIndex=0;yIndex<9;yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
        return newArray;
    }
}
