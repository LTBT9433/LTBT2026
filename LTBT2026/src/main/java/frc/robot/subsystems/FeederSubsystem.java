// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class FeederSubsystem extends SubsystemBase {
 
  private SparkMax feed;
 
  /** Creates a new ExampleSubsystem. */
  public FeederSubsystem() {
    feed = new SparkMax(ShooterConstants.FEEDER_ID, MotorType.kBrushed);
  }

  public Command feedCommand(){
    return this.run(() -> feed.set(-0.5));
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
