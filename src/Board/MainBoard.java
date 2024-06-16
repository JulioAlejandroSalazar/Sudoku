package Board;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class MainBoard extends JFrame {

    private JPanel topPanel;    
    private JPanel centerPanel;  
    private JPanel westTopBorder;  
    private JPanel westCenterBorder;  
    private JPanel eastTopBorder;  
    private JPanel eastCenterBorder; 
    private JPanel bottomCenterBorder;
    private JLabel title;
    private Color backgroundColor;
    private ImageIcon icon = new ImageIcon("Images/puzzle_icon.png");
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private OptionsBoard optionsBoard;

    public MainBoard() {

        optionsBoard = new OptionsBoard(sudokuBoard);
        initializeComponents();
        createTopPanel();
        createCenterPanel();
        createWestCenterBorder();
        createWestTopBorder();
        createEastCenterBorder();
        createEastTopBorder();
        createBottomCenterBorder();
        createTitle();
        setBackgroundColor(backgroundColor);
        createBoard();

    }

    public void initializeComponents() {

        topPanel = new JPanel();
        centerPanel = new JPanel();
        westCenterBorder = new JPanel();
        westTopBorder = new JPanel();
        eastCenterBorder = new JPanel();
        eastTopBorder = new JPanel();
        bottomCenterBorder = new JPanel();
        title = new JLabel("Sudoku");
        backgroundColor = new Color(0,114,202);
        
    }

    public void createBoard() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(backgroundColor);
        this.setSize(1400,900);
        this.setResizable(false);
        this.setLayout(new BorderLayout(50,20));
        this.setLocationRelativeTo(null);
        this.setIconImage(icon.getImage());
        topPanel.add(title);
        topPanel.add(westTopBorder, BorderLayout.WEST);
        topPanel.add(eastTopBorder, BorderLayout.EAST);
        centerPanel.add(sudokuBoard);
        centerPanel.add(optionsBoard, BorderLayout.EAST);
        this.add(bottomCenterBorder, BorderLayout.SOUTH);
        this.add(westCenterBorder, BorderLayout.WEST);
        this.add(eastCenterBorder, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        this.setVisible(true);

    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel (JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public void createTopPanel() {
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel (JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public void createCenterPanel() {
        centerPanel.setPreferredSize(new Dimension(100,100));
        centerPanel.setLayout(new BorderLayout(50, 0));
        centerPanel.setOpaque(false);
    }

    public JPanel getWestTopBorder() {
        return westTopBorder;
    }

    public void setWestTopBorder (JPanel westTopBorder) {
        this.westTopBorder = westTopBorder;
    }

    public void createWestTopBorder() {
        westTopBorder.setPreferredSize(new Dimension(150,100));
        westTopBorder.setLayout(new BorderLayout());
        westTopBorder.setOpaque(false);
    }

    public JPanel getWestCenterBorder() {
        return westCenterBorder;
    }

    public void setWestCenterBorder (JPanel westCenterBorder) {
        this.westCenterBorder = westCenterBorder;
    }

    public void createWestCenterBorder() {
        westCenterBorder.setPreferredSize(new Dimension(100,100));
        westCenterBorder.setLayout(new BorderLayout());
        westCenterBorder.setOpaque(false);
    }

    public JPanel getEastTopBorder() {
        return eastTopBorder;
    }

    public void setEastTopBorder (JPanel eastTopBorder) {
        this.eastTopBorder = eastTopBorder;
    }

    public void createEastTopBorder() {
        eastTopBorder.setPreferredSize(new Dimension(100,100));
        eastTopBorder.setLayout(new BorderLayout());
        eastTopBorder.setOpaque(false);
    }

    public void createEastCenterBorder() {
        eastCenterBorder.setPreferredSize(new Dimension(250,100));
        eastCenterBorder.setLayout(new BorderLayout());
        eastCenterBorder.setOpaque(false);
    }

    public JPanel getEastCenterBorder() {
        return eastCenterBorder;
    }

    public void setEastCenterBorder (JPanel eastCenterBorder) {
        this.eastCenterBorder = eastCenterBorder;
    }

    public void createBottomCenterBorder() {
        bottomCenterBorder.setPreferredSize(new Dimension(100,25));
        bottomCenterBorder.setLayout(new BorderLayout());
        bottomCenterBorder.setOpaque(false);
    }

    public JPanel getBottomCenterBorder() {
        return this.bottomCenterBorder;
    }

    public void setBottomCenterBorder (JPanel bottomCenterBorder) {
        this.bottomCenterBorder = bottomCenterBorder;
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle (JLabel title) {
        this.title = title;
    }

    public void createTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setHorizontalTextPosition(JLabel.LEFT);
    }

    public Color getbackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor (Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

}
