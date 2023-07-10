import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchoolBusFrame extends JFrame
{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 500;
    private final String FRAME_TITLE = "Animated School Bus";


    private JTextField lengthField;
    private JTextField wheelField;

    private JButton updateButton;
    private JButton playButton;
    private JButton stopButton;

    private BusComponent busComponent;

    private Timer timer;

    public SchoolBusFrame ()
    {
        this.setTitle(FRAME_TITLE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        
        

        this.createInputPanel();
        this.createControlPanel();
        this.createBusComponent();

        this.setTimer();
    }

    private void createInputPanel()
    {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints childConstraints = new GridBagConstraints();
        childConstraints.gridx = GridBagConstraints.RELATIVE;
        childConstraints.insets = new Insets( 5, 5, 5, 5);
        
        lengthField = new JFormattedTextField(new NumberFormatter());
        lengthField.setText("300");
        lengthField.setColumns(5);
        JLabel lengthLabel = new JLabel("Length: ");
        childConstraints.gridy = 0;
        inputPanel.add(lengthLabel, childConstraints);
        inputPanel.add(lengthField, childConstraints);

        wheelField = new JFormattedTextField(new NumberFormatter());
        wheelField.setText("2");
        wheelField.setColumns(5);
        JLabel wheelLabel = new JLabel("Wheel: ");
        childConstraints.gridy = 1;
        inputPanel.add(wheelLabel, childConstraints);
        inputPanel.add(wheelField, childConstraints);
        
        updateButton = new JButton( "Update");
        updateButton.setBackground(new Color(232,228,228));
        updateButton.setBorderPainted(false);
        updateButton.addActionListener(new UpdateButtonListener());
        childConstraints.gridy = 2;
        childConstraints.gridwidth = 2;
        inputPanel.add(updateButton, childConstraints);
        
        GridBagConstraints inputPanelConstraints = new GridBagConstraints();
        inputPanelConstraints.gridy = 1;
        inputPanelConstraints.insets = new Insets( 5, 5, 5, 5);
        inputPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(inputPanel, inputPanelConstraints);
    }
    
    private void createControlPanel()
    {
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setLayout(new GridLayout(2, 1, 5, 5));
        
        playButton = new JButton("Play");
        playButton.setBackground(new Color(232,228,228));
        playButton.setBorderPainted(false);
        playButton.addActionListener(new PlayButtonListener());
        
        stopButton = new JButton("Stop");
        stopButton.setBackground(new Color(232,228,228));
        stopButton.setBorderPainted(false);
        stopButton.addActionListener(new StopButtonListener());
        
        controlPanel.add(playButton);
        controlPanel.add(stopButton);

        GridBagConstraints controlPanelConstraints = new GridBagConstraints();
        controlPanelConstraints.gridx = 0;
        controlPanelConstraints.gridy = 1;
        controlPanelConstraints.insets = new Insets( 5, 5, 5, 5);
        controlPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(controlPanel, controlPanelConstraints);
    }
    
    private void createBusComponent()
    {
        busComponent = new BusComponent(300, 2);
        GridBagConstraints busComponentConstraints = new GridBagConstraints();
        busComponentConstraints.gridx = 0;
        busComponentConstraints.gridy = 2;
        busComponentConstraints.weightx = 2;
        busComponentConstraints.anchor = GridBagConstraints.PAGE_END;
        busComponentConstraints.weighty = 0.2;
        busComponentConstraints.fill = GridBagConstraints.BOTH;

        JPanel busPanel = new JPanel();
        busPanel.add(busComponent);

        this.add(busComponent, busComponentConstraints);
    }
    
    private void setTimer()
    {
        timer = new Timer(1000/60, busComponent);
        timer.start();
    }

    class BusComponent extends JComponent implements ActionListener
    {
        private int busWidth;
        private int busHeight;
        private int busX;
        private int busY;
        private int busSpeed;
        private int noOfWheels;

        public BusComponent (int length, int wheel)
        {
            super();
            busWidth = length;
            busHeight = FRAME_HEIGHT / 3;
            busX = FRAME_WIDTH / 4;
            busY = FRAME_HEIGHT / 20;
            busSpeed = 5;
            noOfWheels = wheel;
        }
        
        public void updateBus (int length, int wheel)
        {
            busWidth = length;
            noOfWheels = wheel;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            //paint grass
            g.setColor(new Color(144,212,148));
             g.fillRect(0, getHeight() * 3 / 10, getWidth(), getHeight() * 7 / 10);

            //paint bus body
            g.setColor(new Color(255,204,4));
            int busBody = busWidth * 6 / 7;
            g.fillRect(busX, busY, busBody, busHeight);
            g.fillRect(busX + busBody, busY + busHeight / 2, busWidth - busBody, busHeight / 2);

            //paint windows
            g.setColor(new Color(232,252,252));
            int windowSize = (int) (busHeight * 0.35);
            int windowAmount = busBody / (windowSize * 4 / 3);
            int windowGap = (busBody - windowSize * 2 / 3 - windowAmount * windowSize) / (windowAmount - 1);
            for (int i = 0; i < windowAmount; i++) {
                g.fillRect(busX + windowSize / 3 + i * (windowSize + windowGap), busY + (busHeight/2 - windowSize) / 2, windowSize, windowSize);
            }

            //paint wheels
            g.setColor(new Color(104,36,12));
            int wheelSize = busWidth / (noOfWheels * 3 / 2 + 2);
            int wheelGap = (busWidth - wheelSize * noOfWheels) / (noOfWheels + 1); 
            for (int i = 0; i < noOfWheels; i++) {
                g.fillOval(busX + (i+1) * wheelGap + i * wheelSize, busY + busHeight - wheelSize / 2, wheelSize, wheelSize);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            busX += busSpeed;
            if (busX + busWidth > getWidth() && busSpeed > 0)
            {
                busSpeed *= -1;
            }
            else if (busX < 0 && busSpeed < 0)
            {
                busSpeed *= -1;
            }
            repaint();
        }   
    }

    class UpdateButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            busComponent.updateBus(Integer.parseInt(lengthField.getText()), Integer.parseInt(wheelField.getText()));   
        }    
    }
    
    class PlayButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            timer.start();
        }
    }
    
    class StopButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            timer.stop();
        }
    }
}