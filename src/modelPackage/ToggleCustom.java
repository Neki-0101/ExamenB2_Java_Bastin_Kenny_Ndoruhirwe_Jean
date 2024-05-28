package modelPackage;

import javax.swing.*;
import java.awt.*;

public class ToggleCustom extends JToggleButton {
    private Color selectedBackgroundColor = Color.white;
    private Color borderColor = Color.black;
    private int circleX = 5;
    private Integer circleDiameter = null;
    private Timer timer;
    private String[] options = new String[2];

    public ToggleCustom() {
        super();
        setPreferredSize(new Dimension(75, 30));
        setFocusPainted(false);
        setBorder(null);
        addActionListener(e -> startAnimation());
        timer = new Timer(1, e -> updateCirclePosition());
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    private void startAnimation() {
        if (!timer.isRunning()) {
            circleDiameter -= 5;
            timer.start();
        }
    }

    private void updateCirclePosition() {
        int maxX = getWidth() - (Math.min(getWidth(), getHeight()) - 5);
        int minX = 5;
        circleDiameter += 2;
        if (isSelected()) {
            circleX += (getWidth() / 15);
            if (circleX >= maxX) {
                circleX = maxX;
                timer.stop();
            }
        } else {
            circleX -= (getWidth() / 15);
            if (circleX <= minX) {
                circleX = minX;
                timer.stop();
            }
        }
        if (circleDiameter >= Math.min(getWidth(), getHeight()) - 10) {
            circleDiameter = Math.min(getWidth(), getHeight()) - 10;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics fontMetrics = g2.getFontMetrics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(isSelected() ? selectedBackgroundColor : getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getWidth() / 3, getWidth() / 3);

        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, getWidth() / 3, getWidth() / 3);
        if (circleDiameter == null) {
            circleDiameter = Math.min(getWidth(), getHeight()) - 10;
        }

        int circleY = (getHeight() - 2 - circleDiameter) / 2;
        g2.setColor(getParent().getBackground());
        g2.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        if (isSelected()) {
            g2.drawString(options[1], 5, (getHeight() - 1) / 2 + fontMetrics.getHeight() / 4);
        } else {
            g2.drawString(options[0], (getWidth() - 5) - fontMetrics.stringWidth(options[0]), (getHeight() - 1) / 2 + fontMetrics.getHeight() / 4);
        }
    }


    public void setSelectedBackground(Color color) {
        this.selectedBackgroundColor = color;
    }

    public Color getSelectedBackground() {
        return this.selectedBackgroundColor;
    }
}
