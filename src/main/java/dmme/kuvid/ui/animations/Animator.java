package dmme.kuvid.ui.animations;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Animator implements Runnable {
    private static Animator instance;
    private final List<Animatable> elementsToAnimate;
    private final Container animationPanel;
    private boolean animatorStopped = false;

    private Animator(Container animationPanel) {
        this.animationPanel = animationPanel;
        elementsToAnimate = new ArrayList<>();
    }

    public static synchronized Animator getInstance(Container animationPanel) {
        if (instance == null) {
            instance = new Animator(animationPanel);
        }
        return instance;
    }

    public boolean isAnimatorStopped() {
        return animatorStopped;
    }

    public void stopAnimator(boolean animatorStopped) {
        this.animatorStopped = animatorStopped;
    }

    @Override
    public void run() {
        while (true) {
            try {
                long sleepTime = 50;
                synchronized (this) {
                    if (animatorStopped) {
                        Thread.sleep(sleepTime);
                        continue;
                    }
                }

                if (!animatorStopped)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Program Interrupted");
                System.exit(0);
            }
            for (Animatable animatable : elementsToAnimate) {
                LinkedList<Animation> animationQueue = animatable.getAnimationQueue();
                if (!animationQueue.isEmpty()) {
                    Animation current = animationQueue.getFirst();
                    current.animate();
                    if (current.isFinished())
                        animationQueue.removeFirst();

                }
            }
            if (animationPanel.getParent() == null)
                animationPanel.repaint();
            else
                animationPanel.getParent().repaint();
        }
    }

    public void addAnimatable(Animatable animatable) {
        elementsToAnimate.add(animatable);
    }

    public boolean removeAnimatable(Animatable animatable) {
        return elementsToAnimate.remove(animatable);
    }
}
