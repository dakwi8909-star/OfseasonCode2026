package frc.robot.subsystems.drive.intake;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeIOSim implements IntakeIO {}
    private final DCMotorSim intakeSim;

    private boolean isIntakeClosedLoop = false;
    private PIDController intakePidController =
            new PIDController(INTAKE_KP, INTAKE_KI, INTAKE_KD);
    private double intakeMotorVoltage = 0.0;

    public IntakeIOSim() {
        intakeSim =
            new DCMotorSim(LinearSystemId.createDCMotorSystem(
                DRIVE_GEARBOX, constants.DriveInertia, constants.DriveMotorGearRatio),
            DRIVE_GEARBOX)
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        if (isIntakeClosedLoop) {
            intakeMotorVoltage =
                intakePidController.calculate(intakeSim.getAngularVelocityRadPerSec());
        } else {
            intakePidController.reset();
        }
    }