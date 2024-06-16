package Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsBoard extends JPanel implements MouseListener{

    private final int EASY = 36;
    private final int NORMAL = 23;
    private final int HARD = 9;
    private int width;
    private int height;
    private Font font;
    private JButton newGameButton;
    private JButton checkButton;
    private JButton solveButton;
    private JButton noteButton;
    private Color buttonpressed;
    private SudokuBoard sudokuBoard;
    private boolean isTakingNotes;

    public OptionsBoard(SudokuBoard sudokuBoard) {

        this.sudokuBoard = sudokuBoard;
        initializeComponents();
        createOptionPanel();
        createNewGameButton();
        createCheckButton();
        createSolveButton();
        createNoteButton();

    }

    public void initializeComponents() {

        width = 300;
        height = 600;
        font = new Font("Arial", Font.BOLD, 35);
        buttonpressed = new Color(200, 230, 254);
        newGameButton = new JButton();
        checkButton = new JButton();
        solveButton = new JButton();
        noteButton = new JButton();

    }

    public void createOptionPanel() {

        this.setSize(width,height);
        this.setLayout(new GridLayout(4,1,10,50));
        this.add(newGameButton);
        this.add(checkButton);
        this.add(solveButton);
        this.add(noteButton);
        this.setOpaque(false);

    }

    public void createNewGameButton() {

        newGameButton.setText("New Game");
        newGameButton.setSize(100,40);
        newGameButton.setFont(font);
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setFocusable(false);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        newGameButton.addMouseListener(this);

    }

    public void createCheckButton() {

        checkButton.setText("Check");
        checkButton.setSize(100,40);
        checkButton.setFont(font);
        checkButton.setBackground(Color.WHITE);
        checkButton.setFocusable(false);
        checkButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        checkButton.addMouseListener(this);

    }

    public void createSolveButton() {

        solveButton.setText("Solve");
        solveButton.setSize(100,40);
        solveButton.setFont(font);
        solveButton.setBackground(Color.WHITE);
        solveButton.setFocusable(false);
        solveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        solveButton.addMouseListener(this);

    }

    public void createNoteButton() {

        noteButton.setText("Note");
        noteButton.setSize(100,40);
        noteButton.setFont(font);
        noteButton.setBackground(Color.WHITE);
        noteButton.setFocusable(false);
        noteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        noteButton.addMouseListener(this);

    }

    public int getEasy() {
        return this.EASY;
    }

    public int getNormal() {
        return this.NORMAL;
    }

    public int getHard() {
        return this.HARD;
    }

    public JButton getCreateNoteButton() {
        return noteButton;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() == newGameButton) {
            new DifficultyOptions(sudokuBoard);
        }
        else if(e.getSource() == checkButton) {
            sudokuBoard.checkSudoku();
        }
        else if(e.getSource() == solveButton) {
            sudokuBoard.solveSudoku();
        }
        else if(e.getSource() == noteButton) {
            sudokuBoard.takeNote();
            isTakingNotes = !isTakingNotes;
            if(isTakingNotes) {
                getCreateNoteButton().setBackground(buttonpressed);
            }
            else {
                getCreateNoteButton().setBackground(Color.WHITE);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
