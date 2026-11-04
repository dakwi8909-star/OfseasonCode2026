public class ShooterAngleChangeIOSim implements ShooterAngleChangeIO {
    private final DCMotorSim shooterAngleChangeSim;

    private boolean isShooterAngleChangeClosedLoop = false;
    private PIDController shooterAngleChangePidController =
            new PIDController(SHOOTER_ANGLE_CHANGE_KP, SHOOTER_ANGLE_CHANGE_KI, SHOOTER_ANGLE_CHANGE_KD);
    private double shooterAngleChangeMotorVoltage = 0.0;

    public ShooterAngleChangeIOSim() {
        shooterAngleChangeSim =
            new DCMotorSim(LinearSystemId.createDCMotorSystem(
                DRIVE_GEARBOX, constants.DriveInertia, constants.DriveMotorGearRatio),
            DRIVE_GEARBOX);
    }

    @Override
    public void updateInputs(ShooterAngleChangeIOInputs inputs) {
        shooterAngleChangeSim.setInputVoltage(shooterAngleChangeMotorVoltage);
        shooterAngleChangeSim.update(0.02);
        inputs.shooterAngleChangePositionRad = shooterAngleChangeSim.getAngularPositionRad();
        inputs.shooterAngleChangeVelocityRadPerSec = shooterAngleChangeSim.getAngularVelocityRadPerSec();
        inputs.shooterAngleChangeAppliedVolts = shooterAngleChangeMotorVoltage;
        inputs.shooterAngleChangeCurrentAmps = shooterAngleChangeSim.getCurrentDrawAmps();
    }

    @Override
    public void setShooterAngleChangeVelocity(double velocityRadPerSec) {
        isShooterAngleChangeClosedLoop = true;
        shooterAngleChangePidController.setSetpoint(velocityRadPerSec);
    }
    @Override
    public void setShooterAngleChangeVoltage(double voltage) {
        isShooterAngleChangeClosedLoop = false;
        shooterAngleChangeMotorVoltage = voltage;
    }

    @Override
    public void setShooterAngleChangePosition(double positionRad) {
        isShooterAngleChangeClosedLoop = true;
        shooterAngleChangePidController.setSetpoint(positionRad);
    }
}