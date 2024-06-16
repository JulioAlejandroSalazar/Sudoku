package Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DifficultyOptions extends JFrame implements ActionListener{

    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private int width;
    private int height;
    private Font font;
    private int margin;
    private SudokuBoard sudokuBoard;

    DifficultyOptions(SudokuBoard sudokuBoard) {

        this.sudokuBoard = sudokuBoard;
        initializeComponents();
        createFrame();
        createEasyButton();
        createNormalButton();
        createHardButton();

    }

    public void initializeComponents() {

        width = 600;
        height = 400;
        margin = 5;
        font = new Font("Arial", Font.BOLD, 50);
        easyButton = new JButton();
        normalButton = new JButton();
        hardButton = new JButton();

    }

    public void createFrame() {

        
        this.setLocationRelativeTo(null);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(215,215,215));
        this.add(easyButton);
        this.add(normalButton);
        this.add(hardButton);

        this.setVisible(true);

    }

    public void createEasyButton() {

        easyButton.setText("Easy");
        easyButton.setFont(font);
        easyButton.setBounds(150, 15, (width/2), (height/4)-(margin*2));
        easyButton.setBackground(Color.WHITE);
        easyButton.setFocusable(false);
        easyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, margin));
        easyButton.addActionListener(this);

    }

    public void createNormalButton() {

        normalButton.setText("Normal");
        normalButton.setFont(font);
        normalButton.setBounds(150, 130, (width/2), (height/4)-(margin*2));
        normalButton.setBackground(Color.WHITE);
        normalButton.setFocusable(false);
        normalButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, margin));
        normalButton.addActionListener(this);

    }

    public void createHardButton() {

        hardButton.setText("Hard");
        hardButton.setFont(font);
        hardButton.setBounds(150, 245, (width/2), (height/4)-(margin*2));
        hardButton.setBackground(Color.WHITE);
        hardButton.setFocusable(false);
        hardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, margin));
        hardButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == easyButton) {
            sudokuBoard.setDifficulty(sudokuBoard.getOptionsBoard().getEasy());
        }
        else if(e.getSource() == normalButton) {
            sudokuBoard.setDifficulty(sudokuBoard.getOptionsBoard().getNormal());
        }
        else if(e.getSource() == hardButton) {
            sudokuBoard.setDifficulty(sudokuBoard.getOptionsBoard().getHard());
        }
        sudokuBoard.setInitialNumbers();
        sudokuBoard.setWhiteSquares();
        this.dispose();

    }

}
