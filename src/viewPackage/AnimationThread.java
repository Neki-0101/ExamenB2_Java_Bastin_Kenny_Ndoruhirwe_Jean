package viewPackage;

public class AnimationThread extends Thread {

    private AnimationPanel animationPanel;

    public AnimationThread(AnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
    }

    public void run() {
        while (true) {
            animationPanel.updateAnimation();
            animationPanel.repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
