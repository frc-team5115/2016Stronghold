package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	
	double angle;

    public Turn(double a) {
        angle = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetEncoders();
    	Timer.delay(0.1);
    	Robot.chassis.tankDrive(-0.6 * Math.signum(angle), 0.6 * Math.signum(angle), 1);
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
