// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.PrintStream;
import java.io.PrintWriter;

// import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmCostants;

import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.*;;

public class ArmSubsystem extends SubsystemBase {
 
  private final SparkMax armMotor = new SparkMax(ArmCostants.ARM_ID, MotorType.kBrushless);

  private final Encoder m_armEncoder = 
       new Encoder(
        ArmCostants.ARM_ENCODER_ID[0],
        ArmCostants.ARM_ENCODER_ID[1],
        ArmCostants.ARM_ENCODER_REVERSED);
  // private final DutyCycleEncoder m_armEncoder = 
  //      new DutyCycleEncoder(ArmCostants.ARM_ENCODER_ID)

  private final ArmFeedforward m_armFeedforward = 
        new ArmFeedforward(ArmCostants.ARM_SVOLTS,ArmCostants.ARM_G_GAIN , ArmCostants.ARM_V_PER_RAD_SEC);
 
  private final PIDController m_armFeedback = new PIDController(ArmCostants.ARM_P_VALUE, 0, 0);

  private double desiredAngle = 5;
  private double measuredAngle = 0;

  // private final DoublePublisher encoderAngle;

  public ArmSubsystem() {
    m_armFeedback.setTolerance(ArmCostants.ARM_TOLERANCE_DEG);
    m_armEncoder.setDistancePerPulse(ArmCostants.ENCODER_DIST_PER_PULSE);
    // this.desiredAngle = 0;

    // // Set default command to turn off both the shooter and feeder motors, and then idle
    // setDefaultCommand(
    //     runOnce(
    //             () -> {
    //               m_shooterMotor.disable();
    //               m_feederMotor.disable();
    //             })
    //         .andThen(run(() -> {}))
    //         .withName("Idle"));
  }

  // public Command armUp(){
  //   return this.desiredAngle = 0;
  //   // return this.run(() -> armMotor.set(-ArmCostants.ARM_SPEED));
  // }

  // public Command armDown(){
  //   this.desiredAngle = Units.degreesToRadians(45);
  //   // return this.run(() -> armMotor.set(ArmCostants.ARM_SPEED));
  // }

  // change to run until from combining motion profiling and pid command based
  public Command armToAngle(){
    this.measuredAngle = m_armEncoder.getDistance();
    // check if you can just use ARM_ENCODER_REVERSE instead of negating the PID
    return run(() -> {
        armMotor.setVoltage(
          m_armFeedforward.calculate(this.desiredAngle, ArmCostants.ARM_SPEED)
          - m_armFeedback.calculate(m_armEncoder.getDistance(), 
          this.desiredAngle)
        );
    });
  }

  public Command stopArm(){
    return run(() -> armMotor.set(0));
  }


  @Override
  public void periodic() {
  
    this.measuredAngle = m_armEncoder.getDistance();
    System.out.printf("Angle: %.2f\n",this.measuredAngle);
    // armMotor.setVoltage(
    //   m_armFeedforward.calculate(this.desiredAngle, ArmCostants.ARM_SPEED)
    //   + m_armFeedback.calculate(Units.degreesToRadians(m_armEncoder.getDistance()), 
    //   this.desiredAngle)
    // );
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
