package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnGyro extends Command {
	
	double target;
	double direction;

    public TurnGyro(double a) {
    	target = a;
    	direction = a > 0 ? 1 : -1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetGyro();
    	Timer.delay(0.05);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double yaw = Robot.chassis.getYaw();
    	
    	double error = target - yaw;
    	
    	if (Math.abs(error) > 30)
    		Robot.chassis.tankDrive(-0.6 * direction, 0.6 * direction, 1);
    	else
    		Robot.chassis.tankDrive(-0.6 * Math.pow(error / 30, 2) * direction, 0.6 * Math.pow(error / 30, 2) * direction, 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.chassis.getYaw() - target) < 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.inuse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
