package viewPackage;

import modelPackage.AnimationThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class AnimationPanel extends JPanel {
    private Image image = null;
    private int animationState;
    private boolean animationDirect = true;
    private final LinkedHashSet<String> animationFrameSet;

    public AnimationPanel() {
        super();
        animationFrameSet = new LinkedHashSet<>();
        this.animationState = 0;
        AnimationThread animation = new AnimationThread(this);
        animation.start();

    }

    public void addAnimationFrame(String framePath) {
        animationFrameSet.add(framePath);
    }

    public void deleteAnimationFrame(String framePath) {
        animationFrameSet.remove(framePath);
    }

    public void updateAnimation() {
        ArrayList<String> animationFrameList = new ArrayList<>(animationFrameSet);
        if (animationFrameList.isEmpty()) {
            return;
        }

        try {
            image = ImageIO.read(new File(animationFrameList.get(animationState)));
            if (animationDirect) {
                animationState++;
                if (animationState >= animationFrameList.size()) {
                    animationState = animationFrameList.size() - 2;
                    animationDirect = false;
                }
            } else {
                animationState--;
                if (animationState < 0) {
                    animationState = 1;
                    animationDirect = true;
                }
            }
        } catch (IOException e) {

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int imgWidth = image.getWidth(this);
            int imgHeight = image.getHeight(this);

            double imgAspect = (double) imgWidth / imgHeight;
            double componentAspect = (double) getWidth() / getHeight();

            int drawWidth;
            int drawHeight;

            if (imgAspect > componentAspect) {
                drawWidth = getWidth();
                drawHeight = (int) (getWidth() / imgAspect);
            } else {
                drawWidth = (int) (getHeight() * imgAspect);
                drawHeight = getHeight();
            }

            Image scaledImage = image.getScaledInstance(drawWidth, drawHeight, Image.SCALE_SMOOTH);


            g.drawImage(scaledImage, 0, 0, this);
        }
    }

}
