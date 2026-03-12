// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutoArmCommand extends Command {
  @SuppressWarnings("PMD.UnusedPrivateField")
  private  ArmSubsystem m_ArmSubsystem;
  private BooleanSupplier done = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoArmCommand(ArmSubsystem armSubsystem) {
    this.m_ArmSubsystem = armSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.m_ArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = this.m_ArmSubsystem.getOffsetAngle();
    if (angle >= 1.3) {
      this.m_ArmSubsystem.setArmSpeed(-0.1);
    }
    else if (angle >= 0.1) {
      this.m_ArmSubsystem.setArmSpeed(0.05);
    }
    else {
      this.m_ArmSubsystem.stopArm();
      this.done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
