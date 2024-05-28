package modelPackage;

import javax.swing.*;
import java.awt.*;

public class SemiCircleBar extends JComponent {
    private int percentage;
    private Color firstColor;
    private Color secondColor;
    private int borderWidth;

    @Override
    public Color getBackground() {
        return firstColor;
    }

    @Override
    public Color getForeground() {
        return secondColor;
    }

    public SemiCircleBar(int percentage) {
        super();
        setPercentage(percentage);
        setBackgroundColor(Color.green);
        setForegroundColor(Color.PINK);
        setBorderWidth(75);
    }

    public void setPercentage(int percentage) {
        if (percentage >= 0) {
            this.percentage = percentage;
            repaint();
        }
    }

    public void setBackgroundColor(Color firstColor) {
        if (firstColor != null) {
            this.firstColor = firstColor;
            repaint();
        }
    }

    public void setForegroundColor(Color secondColor) {
        if (secondColor != null) {
            this.secondColor = secondColor;
            repaint();
        }
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth >= 0) {
            this.borderWidth = borderWidth;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth() - 10;
        int height = getHeight() - 10;

        int miniCircleWidth = width - borderWidth;
        int miniCircleHeight = height - borderWidth;

        int originX = width / 2;
        int originY = height / 2;

        int startAngle = 180;
        double arcAngle = (int) (180 * (percentage / 100.0));

        g2.setColor(firstColor);
        g2.fillArc(0, 0, width, height, 0, 180);
        g2.setColor(secondColor);
        g2.fillArc(0, 0, width, height, startAngle, (int) -arcAngle);

        g2.setColor(Color.BLACK);
        g2.drawLine(0, originY, width, originY);

        g2.setColor(getParent().getBackground());
        g2.fillArc((width - miniCircleWidth) / 2, (height - miniCircleHeight) / 2 + 1, miniCircleWidth, miniCircleHeight + 1, 0, 180);

        g2.setColor(Color.BLACK);
        g2.drawArc(0, 0, width, height, 0, 180);
        g2.setColor(Color.BLACK);
        g2.drawArc((width - miniCircleWidth) / 2, (height - miniCircleHeight) / 2 + 1, miniCircleWidth, miniCircleHeight, 0, 180);

        arcAngle = arcAngle / 2.0;
        double arcAngleRad1 = Math.toRadians(arcAngle);
        double x1 = Math.sin(arcAngleRad1 + Math.PI / 2.0 + Math.PI);
        double y1 = Math.cos(arcAngleRad1 + Math.PI / 2.0);

        int lineEndX1 = originX + (int) (x1 * ((miniCircleWidth + borderWidth / 2) / 2.0));
        int lineEndY1 = originY + (int) (y1 * ((miniCircleHeight + borderWidth / 2) / 2.0));

        double arcAngleRad2 = Math.toRadians(180 - arcAngle);
        double x2 = Math.sin(arcAngleRad2);
        double y2 = Math.cos(arcAngleRad2);

        int lineEndX2 = originX + (int) (x2 * ((miniCircleWidth + borderWidth / 2) / 2.0));
        int lineEndY2 = originY + (int) (y2 * ((miniCircleHeight + borderWidth / 2) / 2.0));

        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        String percentageString1 = percentage + "%";
        String percentageString2 = 100 - percentage + "%";
        int stringHeight = fm.getHeight();
        int stringWidth1 = fm.stringWidth(percentageString1);
        int stringWidth2 = fm.stringWidth(percentageString2);

        g2.drawString(percentageString1, lineEndX1 - stringWidth1 / 2, lineEndY1 + stringHeight / 2);
        g2.drawString(percentageString2, lineEndX2 - stringWidth2 / 2, lineEndY2 + stringHeight / 2);
    }
}

