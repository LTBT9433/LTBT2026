// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmCostants;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {
 
  private Spark arm;
 
  /** Creates a new ExampleSubsystem. */
  public ArmSubsystem() {
    this.arm = new Spark(ArmCostants.ARM_ID);
  }

  public Command armUp(){
    return this.run(() -> arm.set(ArmCostants.ARM_SPEED));
  }

  public Command armDown(){
    return this.run(() -> arm.set(-ArmCostants.ARM_SPEED));
  }


  public Command stopArm(){
    return run(() -> arm.set(0));
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
