package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightLine extends Command {

	double dist;
	double speed;
	
    public StraightLine(double d, double s) {
        dist = d;
        speed = s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetEncoders();
    	Timer.delay(0.1);
    	Robot.chassis.tankDrive(speed, speed, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.chassis.distanceTraveled());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.chassis.distanceTraveled() >= dist;
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
