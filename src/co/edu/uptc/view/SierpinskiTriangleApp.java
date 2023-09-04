package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangleApp extends JFrame implements ActionListener {

    private JPanel controlPanel;
    private JPanel drawingPanel;
    private JSpinner levelSpinner;
    private JButton drawButton;

    public SierpinskiTriangleApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout());
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(0, 102, 204));
        controlPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel instructionLabel = new JLabel("Choose the level from 0-7");
        instructionLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        controlPanel.add(instructionLabel, constraints);

        levelSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 7, 1));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        controlPanel.add(levelSpinner, constraints);

        drawButton = new JButton("Draw");
        drawButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 1;
        controlPanel.add(drawButton, constraints);

        add(controlPanel, BorderLayout.NORTH);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int level = (int) levelSpinner.getValue();
                int size = 400;
                int x = getWidth() / 2;
                int y = 30;
                drawSierpinskiTriangle(g, x, y, size, level);
            }
        };
        drawingPanel.setBackground(new Color(255, 204, 0));
        add(drawingPanel, BorderLayout.CENTER);
    }

    private void drawSierpinskiTriangle(Graphics g, int x, int y, int size, int level) {
        if (level == 0) {
            int[] xPoints = { x - size / 2, x + size / 2, x };
            int[] yPoints = { y + (int) (size * Math.sqrt(3) / 2), y + (int) (size * Math.sqrt(3) / 2), y };
            g.setColor(Color.WHITE);
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            int newSize = size / 2;
            drawSierpinskiTriangle(g, x, y, newSize, level - 1);
            drawSierpinskiTriangle(g, x - newSize / 2, y + (int) (newSize * Math.sqrt(3) / 2), newSize, level - 1);
            drawSierpinskiTriangle(g, x + newSize / 2, y + (int) (newSize * Math.sqrt(3) / 2), newSize, level - 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawButton) {
            drawingPanel.repaint();
        }
    }
}
