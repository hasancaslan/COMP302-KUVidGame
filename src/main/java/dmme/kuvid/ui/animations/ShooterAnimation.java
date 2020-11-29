package dmme.kuvid.ui.animations;

import dmme.kuvid.ui.ShooterUI;

public class ShooterAnimation implements Animation {
    private final double ANIMATION_STEP_RATE;

    private final ShooterUI shooterToAnimate;
    private final double from;
    private final double to;
    private final ShooterAnimationType type;
    private double progress;


    public ShooterAnimation(ShooterUI shooter, double from, double to, ShooterAnimationType type) {
        this(shooter, from, to, 0.1, type);
    }

    public ShooterAnimation(ShooterUI shooter, double from, double to, double step, ShooterAnimationType type) {
        this.from = from;
        this.to = to;
        this.progress = 0;
        this.type = type;
        shooterToAnimate = shooter;
        ANIMATION_STEP_RATE = step;
    }

    private void increaseProgress() {
        this.progress += ANIMATION_STEP_RATE;
        if (this.progress > 1)
            this.progress = 1;
    }

    @Override
    public String toString() {
        return "ShooterMoveAnimation{" +
                "ANIMATION_STEP_RATE=" + ANIMATION_STEP_RATE +
                ", shooterToAnimate=" + shooterToAnimate +
                ", from=" + from +
                ", to=" + to +
                ", progress=" + progress +
                '}';
    }

    @Override
    public void animate() {
        increaseProgress();
        switch (type) {
            case ANGLE:
                shooterToAnimate.changeAngle(from, to, progress);
            case LOCATION:
                shooterToAnimate.changeLocation(from, to, progress);
        }
    }

    @Override
    public boolean isFinished() {
        return this.progress >= 1;
    }
}
