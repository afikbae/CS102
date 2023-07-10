import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * ImageSorter
 */
public class ImageSorter 
{
    public static void main(String[] args) {
        String fileName = "image.jpg";
        ImageSorter is = new ImageSorter(fileName);
        try {
            is.loadImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        is.startAnimatedBubbleSort();
    }

    private BufferedImage image;
    private JFrame frame;
    private int timerDelay;
    private int[][] imageMatrix;
    private String fileName;
    private Timer timer;
    private ActionListener listener;


    public ImageSorter(String fileName) {
        this.fileName = fileName;
        timerDelay = 10;
    }

    public void loadImage() throws IOException
    {
        this.image = ImageIO.read(new File(fileName));
        createMatrix();
        createFrame();
    }

    private void createMatrix() 
    {
        imageMatrix = new int[image.getWidth()][image.getHeight()];
        for (int i = 0; i < imageMatrix.length; i++)
        {
            for (int j = 0; j < imageMatrix[i].length; j++) {
                imageMatrix[i][j] = image.getRGB(i, j);
            }
        }
    }

    private void reloadImage() throws IOException
    {
        this.image = ImageIO.read(new File(fileName));
        createMatrix();
        frame.revalidate();
        frame.repaint();
    }

    private void createFrame ()
    {
        frame = new JFrame();
        frame.setSize(image.getWidth() + 16, image.getHeight() + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ImagePanel());
        frame.addKeyListener(new ImageKeyListener());
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    private class ImagePanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }
    
    public void displayImage ()
    {
        frame.repaint();
    }

    public void startAnimatedBubbleSort()
    {
        timer = new Timer(timerDelay, listener);
        timer.start();
    }

    private boolean horizontalStep ()
    {
        boolean allSorted = true;
        for (int i = 0; i < imageMatrix[0].length; i++)
        {
            int[] columnPixels = new int[imageMatrix.length];
            for (int j = 0; j < columnPixels.length; j++) {
                columnPixels[j] = imageMatrix[j][i];
            }
            if (!bubbleSortStep(columnPixels))
            {
                allSorted = false;
            }
            for (int j = 0; j < columnPixels.length; j++) {
                imageMatrix[j][i] = columnPixels[j];
            }
            setPixels(i, 0, i, image.getWidth()-1);
        }
        return allSorted;
    }

    private boolean verticalStep ()
    {
        boolean allSorted = true;
        for (int i = 0; i < imageMatrix.length; i++)
        {
            if (!bubbleSortStep(imageMatrix[i]))
            {
                allSorted = false;
            }
            setPixels(0, i, image.getHeight()-1, i);
        }
        return allSorted;
    }

    private boolean diagonalStep ()
    {
        boolean horizontalStatus = horizontalStep();
        boolean verticalStatus = verticalStep();
        return horizontalStatus && verticalStatus;
    }

    private void setPixels (int x1, int y1, int x2, int y2)
    {
        for (int i = y1; i <= y2; i++)
        {
            for (int j = x1; j <= x2; j++)
            {
                image.setRGB(i, j, imageMatrix[i][j]);
            }
        }
    }

    private static boolean bubbleSortStep (int arr[])
    {
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (brightness(arr[i]) < brightness(arr[i + 1]))
            {
                sorted = false;
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        return sorted;
    }

    private static double brightness (int pixel)
    {
        double R = (pixel & 0xff0000) >> 16;
        double G = (pixel & 0xff00) >> 8;
        double B = (pixel & 0xff);
        return (0.2126 * R + 0.7152 * G + 0.0722 * B);
    }

    private class ImageKeyListener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_V:
                    timer.stop();
                    try {
                        reloadImage();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    timer.removeActionListener(listener);
                    listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (verticalStep())
                            {
                                timer.stop();
                            }
                            displayImage();
                        }
                    };
                    timer.addActionListener(listener);
                    timer.start();
                    break;
                case KeyEvent.VK_D:
                    timer.stop();
                    try {
                        reloadImage();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    timer.removeActionListener(listener);
                    listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (diagonalStep())
                            {
                                timer.stop();
                            }
                            displayImage();
                        }
                    };
                    timer.addActionListener(listener);
                    timer.start();
                    break;
                case KeyEvent.VK_H:
                    try {
                        reloadImage();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    timer.removeActionListener(listener);
                        listener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (horizontalStep())
                                {
                                    timer.stop();
                                }
                                displayImage();
                            }
                        };
                    timer.addActionListener(listener);
                    timer.start();
                    break;
                case KeyEvent.VK_UP:
                    if (timerDelay > 0)
                    {
                        timerDelay -= 10;
                        timer.setDelay(timerDelay);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    timerDelay += 10;
                    timer.setDelay(timerDelay);
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}