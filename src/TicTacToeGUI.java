/**
 *  `TicTacToe` class
 *   Extended from `TicTacToeCore` abstract class
 *   by Teerapat Kraisrisirikul
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends TicTacToeCore {
    private JFrame frame;
    private JPanel board, menuZone, menuBox, menu;
    private JTextField messageBox;
    private JButton[][] boardCell;
    private JButton startButton, restartButton, exitButton;

    public TicTacToeGUI() {
        // Constructor
        super();

        // Frame and Panels
        frame = new JFrame("Tic Tac Toe");
        board = new JPanel(new GridLayout(3, 3));
        menuZone = new JPanel(new GridLayout(2, 1));
        menuBox = new JPanel();
        menu = new JPanel(new GridLayout(1, 2));

        // Components
        messageBox = new JTextField("Welcome to Tic Tac Toe!");
        boardCell = new JButton[3][3];
        restartButton = new JButton("Start");
        exitButton = new JButton("Exit");

        // Board Cell Adding
        for (short i = 0; i < 3; i++) {
            for (short j = 0; j < 3; j++) {
                boardCell[i][j] = new JButton();
                boardCell[i][j].addActionListener(new BoardListener());
                boardCell[i][j].setFont(new Font("Arial", Font.BOLD, 48));
                board.add(boardCell[i][j]);
            }
        }

        // Menu Buttons Settings
        restartButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14));

        restartButton.addActionListener(new BoardListener());
        exitButton.addActionListener(new BoardListener());

        // Message Box Settings
        messageBox.setFont(new Font("Arial", Font.PLAIN, 18));
        messageBox.setForeground(new Color(23,199,35));
        messageBox.setHorizontalAlignment(JTextField.CENTER);
        messageBox.setEditable(false);

        // Menu Settings
        menu.add(restartButton);
        menu.add(exitButton);
        menuBox.add(menu, BorderLayout.CENTER);

        menuZone.add(messageBox);
        menuZone.add(menuBox);

        // Frame Components Adding
        frame.add(board);
        frame.add(menuZone, BorderLayout.SOUTH);
    }

    public void run() {
        // Object Method: Start Program
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void play(int i, int j) {
        // Object Method: Play a Move
        if (setBoardData(getTurnSymbol(), i, j)) {
            turn++;
            win = checkWin();
            updateBoard();
            updateTurnMessage();
        }

        if (win)
            updateWinMessage();
        else if (turn == 9)
            updateDrawMessage();
    }

    private void updateBoard() {
        // Object Method: Update Board
        for (short i = 0; i < 3; i++){
            for (short j = 0; j < 3; j++) {
                boardCell[i][j].setText(boardData[i][j]);
                if (boardData[i][j].equals("X"))
                    boardCell[i][j].setForeground(new Color(180,17,2));
                else if (boardData[i][j].equals("O"))
                    boardCell[i][j].setForeground(new Color(13,141,215));
            }
        }
    }

    private void updateBoard(int i, int j) {
        // Object Method: Update Board (Specific Cell)
        if (boardData[i][j].equals("X"))
            boardCell[i][j].setForeground(new Color(180,17,2));
        else if (boardData[i][j].equals("O"))
            boardCell[i][j].setForeground(new Color(13,141,215));
    }

    private void updateTurnMessage() {
        // Object Method: Update Player Turn Message
        messageBox.setText("Player " + getTurnSymbol() + "'s turn!");
        if (getTurnSymbol().equals("X"))
            messageBox.setForeground(new Color(180,17,2));
        else if (getTurnSymbol().equals("O"))
            messageBox.setForeground(new Color(13,141,215));
    }

    private void updateWinMessage() {
        // Object Method: Update Player Winning Message
        messageBox.setText("Player " + getWinPlayer() + " won!");
        if (getWinPlayer().equals("X"))
            messageBox.setForeground(new Color(180,17,2));
        else if (getWinPlayer().equals("O"))
            messageBox.setForeground(new Color(13,141,215));
    }

    private void updateDrawMessage() {
        // Object Method: Update Player Draw Message
        messageBox.setText("Draw!");
        messageBox.setForeground(new Color(23,199,35));
    }

    // `BoardListener` class with interface implemented
    private class BoardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (short i = 0; i < 3; i++)
                for (short j = 0; j < 3; j++)
                    if (e.getSource() == boardCell[i][j] && !win && started)
                        play(i, j);

            if (e.getSource() == restartButton) {
                if (started) {
                    restart();
                    updateBoard();
                    updateTurnMessage();
                }
                else {
                    start();
                    updateTurnMessage();
                    restartButton.setText("Restart");
                }
            }

            if (e.getSource() == exitButton)
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?") == 0)
                    exit();
        }
    }
}
