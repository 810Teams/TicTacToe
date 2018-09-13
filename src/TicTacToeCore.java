/**
 *  `TicTacToeCore` abstract class
 *   Core to build `TicTacToe` class with GUI
 *   by Teerapat Kraisrisirikul
 */

public abstract class TicTacToeCore {
    protected int turn;
    protected boolean started, win, reversed;
    protected String[][] boardData;

    protected TicTacToeCore() {
        // Constructor
        turn = 0;
        started = false;
        win = false;
        reversed = false;
        boardData = new String[3][3];

        for (short i = 0; i < 3; i++)
            for (short j = 0; j < 3; j++)
                boardData[i][j] = " ";
    }

    protected boolean setBoardData(String value, int i, int j) {
        // Object Method: Set Specific Board Data
        if ((value.equals("X") || value.equals("O")) && boardData[i][j].equals(" ") && started) {
            boardData[i][j] = value;
            return true;
        }
        return false;
    }

    protected String getBoardData(int i, int j) {
        // Object Method: Get Specific Board Data
        return boardData[i][j];
    }

    protected String getTurnSymbol() {
        // Object Method: Get Turn Symbol ('X', 'O')
        if (!reversed) {
            if (turn % 2 == 0)
                return "X";
            return "O";
        }
        else {
            if (turn % 2 == 0)
                return "O";
            return "X";
        }
    }

    protected boolean checkWin() {
        // Object Method: Check Winning Conditions
        for (short i = 0; i < 3; i++) {
            for (short j = 0; j < 3; j++) {
                if (boardData[0][i].equals(boardData[1][i]) &&
                        boardData[0][i].equals(boardData[2][i]) &&
                        !boardData[0][i].equals(" ")) {
                    return true;
                }
                if (boardData[i][0].equals(boardData[i][1]) &&
                        boardData[i][0].equals(boardData[i][2]) &&
                        !boardData[i][0].equals(" ")) {
                    return true;
                }
            }
        }

        if (boardData[0][0].equals(boardData[1][1]) &&
                boardData[0][0].equals(boardData[2][2]) &&
                !boardData[0][0].equals(" ")) {
            return true;
        }

        if (boardData[0][2].equals(boardData[1][1]) &&
                boardData[0][2].equals(boardData[2][0]) &&
                !boardData[0][2].equals(" ")) {
            return true;
        }

        return false;
    }

    protected String getWinPlayer() {
        // Object Method: Check Winning Player
        for (short i = 0; i < 3; i++) {
            for (short j = 0; j < 3; j++) {
                if (boardData[0][i].equals(boardData[1][i]) &&
                        boardData[0][i].equals(boardData[2][i]) &&
                        !boardData[0][i].equals(" ")) {
                    return boardData[0][i];
                }
                if (boardData[i][0].equals(boardData[i][1]) &&
                        boardData[i][0].equals(boardData[i][2]) &&
                        !boardData[i][0].equals(" ")) {
                    return boardData[i][0];
                }
            }
        }

        if (boardData[0][0].equals(boardData[1][1]) &&
                boardData[0][0].equals(boardData[2][2]) &&
                !boardData[0][0].equals(" ")) {
            return boardData[0][0];
        }

        if (boardData[0][2].equals(boardData[1][1]) &&
                boardData[0][2].equals(boardData[2][0]) &&
                !boardData[0][2].equals(" ")) {
            return boardData[0][2];
        }

        return null;
    }

    protected void start() {
        // Object Method: Start Game
        started = true;
    }

    protected void restart() {
        // Object Method: Restart Game
        win = false;
        turn = 0;
        reversed = !reversed;

        for (short i = 0; i < 3; i++)
            for (short j = 0; j < 3; j++)
                boardData[i][j] = " ";
    }

    protected void exit() {
        // Object Method: Exit Game
        System.exit(0);
    }
}
