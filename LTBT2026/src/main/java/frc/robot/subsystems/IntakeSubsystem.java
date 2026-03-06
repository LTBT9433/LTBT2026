// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {
 
  private SparkMax intake;
 
  /** Creates a new ExampleSubsystem. */
  public IntakeSubsystem() {
    intake = new SparkMax(Constants.IntakeConstants.INTAKE_ID, MotorType.kBrushless);
  }

  public Command intakeIn(){
    return this.run(() -> intake.set(-0.8));
  }

  public Command intakeOut(){
    return this.run(() -> intake.set(0.5));
  }

  public Command stopIntake(){
    return this.run(() -> intake.set(0));

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
