import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GamePanel
 */
public class GamePanel extends JPanel{

    public static final Color[] colors = new Color[] {Color.red, Color.green, Color.blue};
    private static int score = 10;
    private static JFrame frame;
    private static int depth = 2;
    private static int buttonGroupCount = (int) Math.pow(4, depth);

    public static void main(String[] args) {
        frame = new JFrame("My Recursive Game Panel - Score: " + score);
        frame.add(new GamePanel(depth));
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    public GamePanel (int depth)
    {
        setLayout(new GridLayout(2, 2));
        
        if (depth > 0)
        {
            for (int i = 0; i < 4; i++)
            {
                add(new GamePanel(depth - 1));    
            }
        }
        else
        {
            Random rand = new Random();
            int[] buttonColors = new int[] {rand.nextInt(3), rand.nextInt(3), rand.nextInt(3), rand.nextInt(3)};

            JButton[] buttons = new JButton[4];
            
            for (int i = 0; i < buttons.length; i++) 
            {
                JButton button = new JButton();
                button.setBackground(colors[buttonColors[i]]);
                GameButtonListener gameButtonListener = new GameButtonListener(buttons, i, buttonColors);
                button.addActionListener(gameButtonListener);
                buttons[i] = button;
                gameButtonListener.checkColors();
                add(button);
            }
        }
    }

    public static void gameOver ()
    {
        int dialogReturn;
        if (getButtonGroupCount() == 0)
        {
            dialogReturn = JOptionPane.showConfirmDialog(frame, "WOW YOU WON!! WANT TO CONTINUE?", "YOU WON", JOptionPane.YES_NO_OPTION);
        }
        else
        {
            dialogReturn = JOptionPane.showConfirmDialog(frame, "YOU LOST!! WANT TO CONTINUE?", "YOU LOST", JOptionPane.YES_NO_OPTION);
        }
        if (dialogReturn == JOptionPane.YES_OPTION)
        {
            frame.getContentPane().removeAll();
            frame.add(new GamePanel(GamePanel.depth));
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
            setButtonGroupCount((int) Math.pow(4, depth));;
            resetScore();
        }
        else
        {
            frame.dispose();
        }
    }

    public static int getScore() {
        return score;
    }

    public static void resetScore() 
    {
        GamePanel.score = 10;
        GamePanel.frame.setTitle("My Recursive Game Panel - Score: " + GamePanel.score);
    }

    public static void decreseScore() {
        GamePanel.score--;
        GamePanel.frame.setTitle("My Recursive Game Panel - Score: " + GamePanel.score);
    }
    
    public static void increaseScore() {
        GamePanel.score += 10;
        GamePanel.frame.setTitle("My Recursive Game Panel - Score: " + GamePanel.score);
        if (GamePanel.getButtonGroupCount() == 0)
        {
            GamePanel.gameOver();
        }
    }

    public static int getButtonGroupCount() {
        return buttonGroupCount;
    }
    
    public static void setButtonGroupCount(int buttonGroupCount) {
        GamePanel.buttonGroupCount = buttonGroupCount;
    }
}

class GameButtonListener implements ActionListener
{
    private JButton[] buttons;
    private int[] buttonColors;
    private int buttonIndex;

    public GameButtonListener (JButton[] buttons, int buttonIndex, int[] buttonColors)
    {
        this.buttons = buttons;
        this.buttonColors = buttonColors;
        this.buttonIndex = buttonIndex;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (GamePanel.getScore() <= 1)
        {

            GamePanel.gameOver();
            return;
        }
        Random rand = new Random();
        
        int newColor = rand.nextInt(3);
        buttonColors[buttonIndex] = newColor;
        
        JButton button = buttons[buttonIndex];
        button.setBackground(GamePanel.colors[newColor]);

        if (checkColors())
        {
            GamePanel.increaseScore();
        }
        else
        {
            GamePanel.decreseScore();
        }
    }

    public boolean checkColors ()
    {
        boolean allSameColor = true;
        for (int i = 0; i < buttonColors.length - 1; i++)
        {
            if (buttonColors[i] != buttonColors[i + 1])
            {
                allSameColor = false;
            }    
        }

        if (allSameColor)
        {
            GamePanel.setButtonGroupCount(GamePanel.getButtonGroupCount() - 1);
            for (JButton b : buttons)
            {
                if (b != null)
                {
                    b.setBackground(Color.gray);
                    b.setEnabled(false);
                }
            }
        }


        return allSameColor;
    }
}