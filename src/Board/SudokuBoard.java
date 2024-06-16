package Board;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import Model.Check;

public class SudokuBoard extends JPanel implements MouseListener, KeyListener {

    private JTextField[][] numberList;
    private JTextField[][] noteList;
    private int squareDimension;
    private int noteDimension;
    private int margin;
    private Font numberFont;
    private Font noteFont;
    private Color squareBackground1;
    private Color squareBackground2;
    private Color squareBackground3;
    private Color squareBackground4;
    private Color marginColor;
    private Color numberForeground1;
    private Color numberForeground2;
    private ArrayList<JTextField> numberListTemp;
    private ArrayList<JTextField> noteListTemp;
    private Check check;
    private boolean isTakingNotes;
    private int[][] sudoku;
    private int difficulty;
    private OptionsBoard optionsBoard;

    public SudokuBoard() {

        initializeComponents();
        createBoard();
        setDifficulty(difficulty);
        setInitialNumbers();

    }

    public void initializeComponents() {

        numberList = new JTextField[9][9];
        noteList = new JTextField[27][27];
        isTakingNotes = false;
        squareDimension = 75;
        noteDimension = 25;
        margin = 5;
        numberFont = new Font("Arial", Font.BOLD, 70);
        noteFont = new Font("Arial", Font.BOLD, 22);
        squareBackground1 = Color.BLACK;
        squareBackground2 = Color.WHITE;
        squareBackground3 = new Color(200, 230, 255);
        squareBackground4 = new Color(100, 188, 255);
        numberForeground1 = Color.BLACK;
        numberForeground2 = new Color(0,114,202);
        numberListTemp = new ArrayList<>();
        noteListTemp = new ArrayList<>();

    }

    public void createBoard() {

        this.setSize((margin*4) + (squareDimension*9), (margin*4) + (squareDimension*9));
        this.setLayout(null);
        this.setBackground(squareBackground1);
        this.setBorder(BorderFactory.createLineBorder(squareBackground1, margin));
        createSudokuGrid();
        createNoteGrid();

    }

    public void setDifficulty(int difficulty) {

        for(int i = 0; i < noteList.length; i++) {
            for(int j = 0; j < noteList[0].length; j++) {
                noteList[i][j].setText("");
                noteList[i][j].setOpaque(false);
            }
        }

        optionsBoard = new OptionsBoard(this);
        int easy = optionsBoard.getEasy();
        int normal = optionsBoard.getNormal();
        int hard = optionsBoard.getHard();

        if(difficulty == easy) {
            this.difficulty = easy;
        }
        else if(difficulty == normal) {
            this.difficulty = normal;
        }
        else if(difficulty == hard) {
            this.difficulty = hard;
        }
        else {
            this.difficulty = normal;
        }

    }

    public void setInitialNumbers() {

        check = new Check();
        sudoku = check.createSudoku(this.difficulty);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(sudoku[i][j] == 0) {
                    numberList[i][j].setText("");
                    numberList[i][j].setForeground(numberForeground1);
                }
                else {
                    numberList[i][j].setText(String.valueOf((sudoku)[i][j]));
                    numberList[i][j].setForeground(numberForeground1);
                }
            }
        }

    }

    public void createSudokuGrid() {

        int x = margin;
        int y = margin;

        for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                JTextField number = new JTextField();
                this.add(number);
                number.setBounds(x, y, squareDimension, squareDimension);
                number.setFont(numberFont);
                number.setForeground(squareBackground1);
                number.setBackground(squareBackground2);
                number.setEditable(false);
                number.setHorizontalAlignment(JTextField.CENTER);
                number.setCursor(new Cursor(Cursor.HAND_CURSOR));
                number.setBorder(BorderFactory.createLineBorder(squareBackground1, 1));
                number.addMouseListener(this);
                number.addKeyListener(this);
                x+=squareDimension;

                if((j + 1) % 3 == 0) {
                    x+=margin;
                }

                numberList[i][j] = number;
            }
            y+=squareDimension;
            x=margin;

            if((i + 1) % 3 == 0) {
                y+=margin;
            }
        }

    }

    public int checkEmptyBoxes() {
        updateCheckSudoku();
        for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                if(numberList[i][j].getText().equals("")) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int checkSolution() {
        updateCheckSudoku();
        if(check.checkSolution()) {
            return 3;
        }
        else {
            return 2;
        }
    }

    public void checkSudoku() {
        
        setWhiteSquares();
        if(checkEmptyBoxes() == 1) {
            JOptionPane.showMessageDialog(null, "There are empty boxes still", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        int result = checkSolution();
        if(result == 2) {
            JOptionPane.showMessageDialog(null, "Incorrect solution", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else if(result == 3) {
            JOptionPane.showMessageDialog(null, "Correct solution!", "WELL DONE", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void updateCheckSudoku() {

        for(int i = 0; i < numberList.length; i ++) {
            for(int j = 0; j < numberList[0].length; j ++) {
                if(numberList[i][j].getText().equals("")) {
                    sudoku[i][j] = 0;
                }
                else {
                    sudoku[i][j] = Integer.parseInt(numberList[i][j].getText());
                }
            }
        }
        check.setSudoku(sudoku);

    }

    public void solveSudoku() {

        setWhiteSquares();
        updateCheckSudoku();

        if(check.solve()) {
            for(int i = 0; i < noteList.length; i++) {
                for(int j = 0; j < noteList[0].length; j++) {
                    noteList[i][j].setOpaque(false);
                    noteList[i][j].setText("");
                }
            }
            for(int i = 0; i < numberList.length; i++) {
                for(int j = 0; j < numberList[0].length; j++) {
                    numberList[i][j].setOpaque(true);
                    if(!numberList[i][j].getText().equals("")) {
                        continue;
                    }
                    else {
                        numberList[i][j].setText(String.valueOf(check.getSudoku()[i][j]));
                        numberList[i][j].setForeground(numberForeground2);
                    }
                    
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No posible solution", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setWhiteSquares() {

        for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                numberList[i][j].setBackground(squareBackground2);
            }
        }

    }

    public void createNoteGrid() {

        int x = margin;
        int y = margin;

        for(int i = 0; i < noteList.length; i++) {
            for(int j = 0; j < noteList[0].length; j++) {
                JTextField note = new JTextField();
                this.add(note);
                note.setBounds(x, y, noteDimension, noteDimension);
                note.setFont(noteFont);
                note.setForeground(Color.LIGHT_GRAY);
                note.setBackground(squareBackground2);
                note.setEditable(false);
                note.setHorizontalAlignment(JTextField.CENTER);
                note.setCursor(new Cursor(Cursor.HAND_CURSOR));
                note.setBorder(BorderFactory.createLineBorder(squareBackground1, 1));
                note.addMouseListener(this);
                note.addKeyListener(this);
                x+=noteDimension;

                if((j+1) % 9 == 0) {
                    x+=margin;
                }

                noteList[i][j] = note;
            }
            x=margin;
            y+=noteDimension;
            if((i+1) % 9 == 0) {
                y+=margin;
            }
        }

    }

    public void takeNote() {
        isTakingNotes = !isTakingNotes;
    }

    public void updateNoteList(int row, int col, char keyChar) {

        if(keyChar >= '1' && keyChar <= '9') {
            for(int i = (row*3); i < (row*3)+3; i++) {
                for(int j = (col*3); j < (col*3)+3; j++) {
                    noteList[i][j].setOpaque(true);
                }
            }

            int noteRow = (keyChar - '1') / 3;
            int noteCol = (keyChar - '1') % 3;

            if(noteList[row * 3 + noteRow][col * 3 + noteCol].getText().isEmpty()) {
                noteList[row * 3 + noteRow][col * 3 + noteCol].setText(String.valueOf(keyChar));
            }
            else {
                noteList[row * 3 + noteRow][col * 3 + noteCol].setText("");
            }
            
        }

    }

    public void paintNotes(int row, int col) {

        for(int i = (row*3); i < (row*3)+3; i++) {
            for(int j = (col*3); j < (col*3)+3; j++) {
                noteList[i][j].setBackground(squareBackground3);
                noteListTemp.add(noteList[i][j]);
            }
        }

    }

    public JTextField[][] getNumberList() {
        return numberList;
    }

    public void setNumberList (JTextField[][] numberList) {
        this.numberList = numberList;
    }
    
    public int getSquareDimension() {
        return squareDimension;
    }

    public void setSquareDimension (int squareDimension) {
        this.squareDimension = squareDimension;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin (int margin) {
        this.margin = margin;
    }

    public Font getNumberFont() {
        return numberFont;
    }

    public void setNumberFont (Font numberFont) {
        this.numberFont = numberFont;
    }

    public Color getSquareBackground1() {
        return squareBackground1;
    }

    public void setSquareBackground1 (Color squareBackground1) {
        this.squareBackground1 = squareBackground1;
    }

    public Color getSquareBackground2() {
        return squareBackground2;
    }

    public void setSquareBackground2 (Color squareBackground2) {
        this.squareBackground2 = squareBackground2;
    }

    public Color getSquareBackground3() {
        return squareBackground3;
    }

    public void setSquareBackground3 (Color squareBackground3) {
        this.squareBackground3 = squareBackground3;
    }

    public Color getSquareBackground4() {
        return squareBackground4;
    }

    public void setSquareBackground4 (Color squareBackground4) {
        this.squareBackground4 = squareBackground4;
    }

    public Color getMarginColor() {
        return marginColor;
    }

    public void setMarginColor (Color marginColor) {
        this.marginColor = marginColor;
    }

    public Color getNumberForeground1() {
        return numberForeground1;
    }

    public void setNumberForeground1 (Color numberForeground) {
        this.numberForeground1 = numberForeground;
    }

    public OptionsBoard getOptionsBoard() {
        return optionsBoard;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
       


    }

    @Override
    public void mousePressed(MouseEvent e) {

        check = new Check();

        for(JTextField temp : numberListTemp) {
            temp.setBackground(squareBackground2);
        }
        numberListTemp.clear();

        for(JTextField temp : noteListTemp) {
            temp.setBackground(squareBackground2);
        }
        noteListTemp.clear();
      
        for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                if(e.getSource() == numberList[i][j]) {

                    for(int k = 0; k < numberList[0].length; k++) {
                        if(!numberList[i][k].isOpaque()) {
                            paintNotes(i, k);
                        }
                        numberList[i][k].setBackground(squareBackground3);
                        numberListTemp.add(numberList[i][k]);
                    }

                    for(int k = 0; k < numberList.length; k++) {
                        if(!numberList[k][j].isOpaque()) {
                            paintNotes(k, j);
                        }
                        numberList[k][j].setBackground(squareBackground3);
                        numberListTemp.add(numberList[k][j]);
                    }

                    for(int row = check.getCurrentSection(i); row < check.getCurrentSection(i)+3; row++) {
                        for(int col = check.getCurrentSection(j); col < check.getCurrentSection(j)+3; col++) {
                            if(!numberList[row][col].isOpaque()) {
                                paintNotes(row, col);
                            }
                            numberList[row][col].setBackground(squareBackground3);
                            numberListTemp.add(numberList[row][col]);
                        }
                    }
                    
                    if(!numberList[i][j].isOpaque()) {
                        for(int k = (i*3); k < (i*3)+3; k++) {
                            for(int l = (j*3); l < (j*3)+3; l++) {
                                noteList[k][l].setBackground(squareBackground4);
                            }
                        }
                    } else {
                        numberList[i][j].setBackground(squareBackground4);
                    }
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
        

    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
        /*for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                if(e.getSource() == numberList[i][j]) {
                    if(numberList[i][j].getBackground().equals(squareBackground4)) {
                        continue;
                    } else {
                        numberList[i][j].setBackground(squareBackground3);
                    }
                }
            }
        }*/

    }

    @Override
    public void mouseExited(MouseEvent e) {
       
        /*for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList[0].length; j++) {
                if(e.getSource() == numberList[i][j]) {
                    if(!numberList[i][j].getBackground().equals(squareBackground4)) {
                        numberList[i][j].setBackground(squareBackground2); 
                    }                                  
                }
            }
        }*/

    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(isTakingNotes) {
            for(int i = 0; i < numberList.length; i++) {
                for(int j = 0; j < numberList[0].length; j++) {
                    if(e.getSource() == numberList[i][j]) {
                        numberList[i][j].setOpaque(false);
                        numberList[i][j].setText("");
                        updateNoteList(i, j, e.getKeyChar());
                    }
                }
            }
        }
        else {
            for(int i = 0; i < numberList.length; i++) {
                for(int j = 0; j < numberList[0].length; j++) {
                    if(e.getSource() == numberList[i][j]) {
                        if(e.getKeyChar() >= 49 && e.getKeyChar() <= 57) {
                            try {
                                for(int k = (i*3); k < (i*3)+3; k++) {
                                    for(int l = (j*3); l < (j*3)+3; l++) {
                                        noteList[k][l].setText("");
                                        noteList[k][l].setOpaque(false);
                                    }
                                }
                            } catch (Exception x) {
                                
                            }
                            numberList[i][j].setText(String.valueOf(e.getKeyChar()));
                            numberList[i][j].setOpaque(true);
                        } else if(e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                            numberList[i][j].setText("");
                        }
    
                    }
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }


}