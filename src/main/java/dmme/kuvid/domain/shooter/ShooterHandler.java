package dmme.kuvid.domain.shooter;

public class ShooterHandler {
    private Shooter shooter;

    public ShooterHandler(Shooter shooter) {
        this.shooter = shooter;
    }

    public void moveShooter(double displacement) {
        shooter.setPosition(shooter.getPosition() + displacement);
    }

    public void rotateShooter(int angleChange) {
        shooter.setAngle(shooter.getAngle() + angleChange);
    }
}
