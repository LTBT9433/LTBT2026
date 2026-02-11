// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
 
  private SparkMax feed;
  private SparkMax shooter;
 
  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
    feed = new SparkMax(ShooterConstants.FEEDER_ID, MotorType.kBrushless);
    shooter = new SparkMax(ShooterConstants.SHOOTER_ID, MotorType.kBrushless);
  }

  public Command shootCommand(){
    return this.runOnce(() -> shooter.set(0.5));
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
