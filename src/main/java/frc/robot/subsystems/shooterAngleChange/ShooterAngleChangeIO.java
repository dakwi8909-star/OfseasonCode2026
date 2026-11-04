public interface ShooterAngleChangeIO {
    @AutoLog
    public static class ShooterAngleChangeIOInputs {
        public boolean shooterAngleChangeConnected = false;
        public double shooterAngleChangePositionRad = 0.0;
        public double shooterAngleChangeVelocityRadPerSec = 0.0;
        public double shooterAngleChangeAppliedVolts = 0.0;
        public double shooterAngleChangeCurrentAmps = 0.0;
    }

    public default void updateInputs(ShooterAngleChangeIOInputs inputs) {}

    public default void setShooterAngleChangeVelocity(double velocityRadPerSec) {}

    public default void setShooterAngleChangeVoltage(double voltage) {}

    public default void setShooterAngleChangePosition(double positionRad) {}
}