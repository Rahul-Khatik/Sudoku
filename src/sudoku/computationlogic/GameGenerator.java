package sudoku.computationlogic;
import sudoku.SudokuApplication;
import sudoku.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] getNewGameGrid(){
        return unsolveGame(getSolvedGame());
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());
        boolean solvable = false;
        int[][] solveableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false){
            SudokuUtilities.copySudokuArrayValue(solvedGame, solveableArray);
            int index = 0;

            while (index<40){
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if(solveableArray[xCoordinate][yCoordinate] != 0){
                    solveableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValue(solveableArray, toBeSolved);

            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);
        }
        return solveableArray;
    }

    private static int[][] getSolvedGame(){
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for (int value=1;value<=GRID_BOUNDARY;value++){
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> alloctracker = new ArrayList<>();
            int attempts = 0;
            while(allocations<GRID_BOUNDARY){
                if(interrupt>200){
                    alloctracker.forEach(coord->{
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });
                    interrupt =0;
                    allocations = 0;
                    alloctracker.clear();
                    attempts++;

                    if(attempts>500){
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if(newGrid[xCoordinate][yCoordinate] == 0){
                    newGrid[xCoordinate][yCoordinate] = value;
                    if(GameLogic.sudokuIsInvalid(newGrid)){
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    }else{
                        alloctracker.add(new Coordinates(xCoordinate,yCoordinate));
                        allocations++;
                    }
                }
            }
        }
        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {
        for(int xIndex=0;xIndex<9;xIndex++){
            for(int yIndex=0;yIndex<9;yIndex++){
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
