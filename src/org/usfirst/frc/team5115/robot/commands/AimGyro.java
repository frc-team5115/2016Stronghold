package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimGyro extends Command {
	
	double error;
	
	double target;
	
	double accum = 0;
	
	double kp = 0.005;
	double ki = 0.01;

    public AimGyro() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Timer.delay(0.5);
    	target = Robot.angleOffset;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = target - Robot.chassis.getYaw();
    	//Robot.chassis.tankDrive(3 * Math.pow(angle, 2) * Math.signum(angle), -3 * Math.pow(angle, 2) * Math.signum(angle), 1);
    	Robot.chassis.tankDrive(-(kp * error + ki * accum), kp * error + ki * accum, 1);
    	accum += error;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(target - Robot.chassis.getYaw()) < 2.5;
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
