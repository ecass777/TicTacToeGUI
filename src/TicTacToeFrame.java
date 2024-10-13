import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeButton[][] buttons;
    private char currentPlayer;
    private TicTacToeLogic gameLogic;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        currentPlayer = 'X';
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        buttons = new TicTacToeButton[3][3];
        gameLogic = new TicTacToeLogic();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeButton(row, col);
                buttons[row][col].addActionListener(new ButtonListener());
                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeButton clickedButton = (TicTacToeButton) e.getSource();

            if (clickedButton.getState() == '-') {  // Valid move
                clickedButton.setState(currentPlayer);  // Set player mark
                gameLogic.updateBoard(clickedButton.getRow(), clickedButton.getCol(), currentPlayer);

                if (gameLogic.checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                    resetBoard();
                } else if (gameLogic.isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a tie!");
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
            }
        }
    }

    private void resetBoard() {
        currentPlayer = 'X';  // Reset to player X
        gameLogic.resetBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].reset();
            }
        }
    }
}
