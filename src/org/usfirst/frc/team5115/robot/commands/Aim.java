package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {
	
	double angle;
	
	double last;
	double next;
	
	double accum = 0;
	
	double kp = 0.05;
	double ki = 0.0005;

    public Aim() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	last = Robot.angleOffset;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = Robot.angleOffset;
    	next = 2 * angle - last;
    	//Robot.chassis.tankDrive(3 * Math.pow(angle, 2) * Math.signum(angle), -3 * Math.pow(angle, 2) * Math.signum(angle), 1);
    	Robot.chassis.tankDrive(-(kp * next + ki * accum), kp * next + ki * accum, 1);
    	accum += next;
    	last = angle;
    	Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.angleOffset) < 1.5 || Robot.oi.aimPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.tankDrive(0, 0, 1);
    	Robot.chassis.inuse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.tankDrive(0, 0, 1);
    	Robot.chassis.inuse = false;
    }
}
