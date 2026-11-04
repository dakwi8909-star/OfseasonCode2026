public class ShooterAngleChange extends SubsystemBase {
    private final ShooterAngleChangeIO io;
    private final ShooterAngleChangeIO.ShooterAngleChangeIOInputs inputs = new ShooterAngleChangeIO.ShooterAngleChangeIOInputs();

    public ShooterAngleChange(ShooterAngleChangeIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("ShooterAngleChange", inputs);
    }

    public void setVelocity(double velocityRadPerSec) {
        io.setShooterAngleChangeVelocity(velocityRadPerSec);
    }

    public void setVoltage(double voltage) {
        io.setShooterAngleChangeVoltage(voltage);
    }

    public void setPosition(double positionRad) {
        io.setShooterAngleChangePosition(positionRad);
    }

    public double getPositionRad() {
        return inputs.shooterAngleChangePositionRad;
    }

    public double getVelocityRadPerSec() {
        return inputs.shooterAngleChangeVelocityRadPerSec;
    }
}