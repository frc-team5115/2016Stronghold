package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {}

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.chassis.inuse)		// if nothing else is using the wheels, then allow the controller to control them
    		Robot.chassis.tankDrive(Robot.oi.getLeft(), Robot.oi.getRight());	// repeatedly update the drivetrain speed with new values from the controller
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;	// only ends if switched to automonous mode
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
