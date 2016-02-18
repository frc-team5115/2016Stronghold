package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Turn extends Command {
	
	double kp = 0.0025;
	double ki = 0;
	double kd = 0;
	
	double errorAccum = 0;
	double lastError = 0;
	
	double target;

    public Turn(double a) {
    	target = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetImu();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = target - Robot.chassis.getYaw();
    	if (Math.abs(error + 360) < Math.abs(error))
    		error += 360;
    	
    	if (lastError == 0)
    		lastError = error;
    	errorAccum += error;
    	
    	double speed = error * kp + errorAccum * ki + (error - lastError) * kd;
    	
    	lastError = error;
    	
    	Robot.chassis.tankDrive(-speed, speed, 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(lastError) < 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.inuse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
