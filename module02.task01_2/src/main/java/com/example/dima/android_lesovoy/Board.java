package com.example.dima.android_lesovoy;

public class Board {
    public static int[][] sBoard;

    public Board() {
        sBoard = new int[3][3];
        cleanBoard();
    }

    private void cleanBoard() {
        for (int line = 0; line < sBoard.length; line++) {
            for (int column = 0; column < sBoard[0].length; column++) {
                sBoard[line][column] = 0;
            }
        }
    }

    public void setPosition(int[] attempt, boolean isCrossesTurn) throws Exception {
        if (isOccupied(attempt)) {
            throw new Exception("This position is occupied!");
        }
        if (isCrossesTurn) {
            sBoard[attempt[0]][attempt[1]] = 1;
        } else {
            sBoard[attempt[0]][attempt[1]] = -1;
        }
    }

    private boolean isOccupied(int[] attempt) {
        return sBoard[attempt[0]][attempt[1]] != 0;
    }

    public int checkLines() {
        for (int line = 0; line < 3; line++) {
            int result = sBoard[line][0] + sBoard[line][1] + sBoard[line][2];
            if (result == 3) {
                return 1;
            }
            if (result == -3) {
                return -1;
            }
        }
        return 0;
    }

    public int checkColumns() {
        for (int column = 0; column < 3; column++) {
            int result = sBoard[0][column] + sBoard[1][column] + sBoard[2][column];
            if (result == 3) {
                return 1;
            }
            if (result == -3) {
                return -1;
            }
        }
        return 0;
    }


    public int checkDiagonals() {
        if (sBoard[0][0] + sBoard[1][1] + sBoard[2][2] == 3) {
            return 1;
        }
        if (sBoard[0][0] + sBoard[1][1] + sBoard[2][2] == -3) {
            return -1;
        }
        if (sBoard[0][2] + sBoard[1][1] + sBoard[2][0] == 3) {
            return 1;
        }
        if (sBoard[2][0] + sBoard[1][1] + sBoard[0][2] == -3) {
            return -1;
        }
        return 0;
    }

    public boolean fullBoard(){
        for(int line=0 ; line<3 ; line++)
            for(int column=0 ; column<3 ; column++)
                if( sBoard[line][column]==0 )
                    return false;
        return true;
    }

}
