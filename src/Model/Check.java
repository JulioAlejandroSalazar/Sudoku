package Model;

import java.util.Random;

public class Check {

    private static final int SIZE = 9;
    private Random random;
    private int[][] sudoku;

    public Check() {

        

    }

    public int getCurrentSection(int rowOrCol) {

        if(rowOrCol <= 2) {
            rowOrCol = 0;
        }
        else if(rowOrCol <= 5) {
            rowOrCol = 3;
        }
        else {
            rowOrCol = 6;
        }

        return rowOrCol;

    }

    public boolean checkRow(int i, int tryN) {

        for(int j = 0; j < SIZE; j++) {
            if(sudoku[i][j] == tryN) {
                return false;
            }
        }
        return true;

    }

    public boolean checkCol(int j, int tryN) {
        
        for(int i = 0; i < SIZE; i++) {
            if(sudoku[i][j] == tryN) {
                return false;
            }
        }
        return true;

    }

    public boolean checkSection(int i, int j, int tryN) {

        int iSection = getCurrentSection(i);
        int jSection = getCurrentSection(j);

        for(int k = iSection; k < iSection+3; k++) {
            for(int l = jSection; l < jSection+3; l++) {
                if(sudoku[k][l] == tryN) {
                    return false;
                }
            }
        }
        return true;

    }

    public boolean solve() {
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (sudoku[i][j] == 0) {
                    for (int tryN = 1; tryN <= SIZE; tryN++) {
                        if (checkRow(i, tryN) && checkCol(j, tryN) && checkSection(i, j, tryN)) {
                            sudoku[i][j] = tryN;
                            if (solve()) {
                                return true;
                            }
                            sudoku[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkSolution() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int tempN = sudoku[i][j];
                sudoku[i][j] = 0;
                if(!(checkRow(i, tempN) && checkCol(j, tempN) && checkSection(i, j, tempN))) {
                    sudoku[i][j] = tempN;
                    return false;
                }
                sudoku[i][j] = tempN;
            }
        }
        return true;

    }
    
    
    public int[][] createSudoku(int difficulty) {

        sudoku = new int[SIZE][SIZE];
        random = new Random();
        int count = 0;

        while (count < 9) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            int num = random.nextInt(SIZE) + 1;
            if (sudoku[row][col] == 0 && checkRow(row, num) && checkCol(col, num) && checkSection(row, col, num)) {
                sudoku[row][col] = num;
                count++;
            }
        }
        
        while(!solve()) {
            createSudoku(difficulty);
        }

        for(int i = SIZE*SIZE; i > difficulty; i--) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if(sudoku[row][col] != 0) {
                sudoku[row][col] = 0;
            }
            else {
                i++;
            }
            
        }
        
        return sudoku;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }
    
}