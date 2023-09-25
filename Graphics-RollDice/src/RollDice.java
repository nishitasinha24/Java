package PartIV;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollDice extends JFrame implements ActionListener {
	private ImagePanel panel1, panel2;
    private JButton rollDiceButton;
    private JLabel[][] grid;
    private JPanel gridPanel;
    private JLabel addLabel;
    private int curRow = 0;
    private int curCol = 0;
    private int rollCount = 0;
    private int stepsRem = 5*5;
    private int add = 0;
    
    public RollDice() {
    	//JFrame
        setTitle("Roll the Dice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        
        //Dice panels
        panel1 = new ImagePanel("die1.png");
        panel1.setBounds(50, 50, 500, 200);
        add(panel1);
        
        panel2 = new ImagePanel("die1.png");
        panel2.setBounds(500, 50, 500, 200);
        add(panel2);
        
        addLabel = new JLabel("Result Sum: " + add);
        addLabel.setBounds(350, 300, 100, 25);
		add(addLabel);
		
        //Roll button
		rollDiceButton = new JButton("Roll Dice");
		rollDiceButton.setBounds(350, 250, 100, 50);
		rollDiceButton.addActionListener(this);
        add(rollDiceButton);
        
        //Grid panel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 5));
        gridPanel.setBounds(150, 400, 500, 100);
        add(gridPanel);
        
        grid = new JLabel[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                grid[r][c] = new JLabel(".");
                grid[r][c].setBounds(c * 40, r * 40, 40, 40);
                gridPanel.add(grid[r][c]);
            }
        }
        
        setVisible(true);
    	
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollDiceButton) {
            int rollTotal = new Random().nextInt(11) + 2;
            int die1 = 0;
            int die2 = 0;
     
            if ((curRow > 4 || curCol > 4) || (curRow == 4 && curCol == 4)) {
            	rollDiceButton.setEnabled(false);
                gridPanel.add(new JLabel("Finished!"));
                gridPanel.revalidate();
                gridPanel.repaint();
            }
            else {
            	
	            for (int i = 0; i < rollTotal; i++) {
	            	die1 = (new Random().nextInt(6) + 1);
	            	die2 = (new Random().nextInt(6) + 1);
	            	panel1.setImage("die" + die1 + ".png");
	            	panel2.setImage("die" + die2 + ".png");
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException ex) {
	                    ex.printStackTrace();
	                }
	            }
	           
	            add = die1 + die2;
	            addLabel.setText("Result Sum: " + add);
	            
	            for (int i = 0; i < die1 + die2; i++) {
	                if (stepsRem > 0) {
	                    curCol++;
	                    if (curCol > 4) {
	                    	curRow++;
	                        curCol = 0;
	                    }
	                    stepsRem--;
	                    if(stepsRem >= 1) {
	                    	grid[curRow][curCol].setText("*");
	                    }
	                    rollCount++;
	                }
	            }
            }
            
        }
    }

    public static void main(String[] args) {
            RollDice rollDice = new RollDice();
    }
}