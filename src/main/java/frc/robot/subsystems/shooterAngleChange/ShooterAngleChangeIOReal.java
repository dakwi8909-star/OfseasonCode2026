public class ShooterAngleChangeIOReal implements ShooterAngleChangeIO {
    private final CANSparkMax shooterAngleChangeMotor;
    private final RelativeEncoder shooterAngleChangeEncoder;

    public ShooterAngleChangeIOReal(int motorId) {
        shooterAngleChangeMotor = new CANSparkMax(motorId, MotorType.kBrushless);
        shooterAngleChangeEncoder = shooterAngleChangeMotor.getEncoder();
    }

    @Override
    public void updateInputs(ShooterAngleChangeIOInputs inputs) {
        inputs.shooterAngleChangeConnected = shooterAngleChangeMotor.getFirmwareVersion() != 0;
        inputs.shooterAngleChangePositionRad = shooterAngleChangeEncoder.getPosition() * (2 * Math.PI);
        inputs.shooterAngleChangeVelocityRadPerSec = shooterAngleChangeEncoder.getVelocity() * (2 * Math.PI / 60);
        inputs.shooterAngleChangeAppliedVolts = shooterAngleChangeMotor.getAppliedOutput() * shooterAngleChangeMotor.getBusVoltage();
        inputs.shooterAngleChangeCurrentAmps = shooterAngleChangeMotor.getOutputCurrent();
    }

    @Override
    public void setShooterAngleChangeVelocity(double velocityRadPerSec) {
        double velocityRPM = velocityRadPerSec * (60 / (2 * Math.PI));
        shooterAngleChangeMotor.set(velocityRPM / 60);
    }

    @Override
    public void setShooterAngleChangeVoltage(double voltage) {
        shooterAngleChangeMotor.setVoltage(voltage);
    }

    @Override
    public void setShooterAngleChangePosition(double positionRad) {
        double positionRotations = positionRad / (2 * Math.PI);
        shooterAngleChangeMotor.set(positionRotations);
    }
}