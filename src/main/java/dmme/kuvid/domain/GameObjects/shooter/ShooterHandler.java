package dmme.kuvid.domain.GameObjects.shooter;

public class ShooterHandler {
    private Shooter shooter;

    public ShooterHandler(Shooter shooter) {
        this.shooter = shooter;
    }

    public void moveShooter(int displacement) {
    	this.shooter.setPosition(this.shooter.getPosition()+displacement);
    }

    public void rotateShooter(int angle) {
    	this.shooter.setAngle(this.shooter.getAngle()+angle);;

    }
}
