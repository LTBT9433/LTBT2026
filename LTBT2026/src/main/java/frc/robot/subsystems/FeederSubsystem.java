// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class FeederSubsystem extends SubsystemBase {
 
  private SparkFlex feed;
 
  /** Creates a new ExampleSubsystem. */
  public FeederSubsystem() {
    feed = new SparkFlex(ShooterConstants.FEEDER_ID, MotorType.kBrushless);
  }

  public Command feedCommand(){
    return this.run(() -> feed.set(-0.8));
  }

  public Command feedOutCommand(){
    return this.run(() -> feed.set(0.8));
  }


  public Command stopFeeder(){
    return this.run(() -> feed.set(0));

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
