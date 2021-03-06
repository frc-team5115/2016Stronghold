package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraTurn extends Command {
	
	double angle;

    public CameraTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	angle = SmartDashboard.getNumber("cam-angle");
    	
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetEncoders();
    	Robot.chassis.tankDrive(-0.3 * Math.signum(angle), 0.3 * Math.signum(angle), 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double arc = (Robot.chassis.rightDist() - Robot.chassis.leftDist()) / 2;
        return Math.abs(arc / (2.104 * Math.PI)) >= Math.abs(angle / 360);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.tankDrive(0, 0, 1);
    	Robot.chassis.inuse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
