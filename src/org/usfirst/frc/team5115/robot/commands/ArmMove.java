package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmMove extends Command {
	
	int dir;
	double timeout;
	double initTime;

    public ArmMove(int d, double t) {
    	dir = -d;
    	timeout = t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.inuse = true;
    	Robot.arm.move(dir);
    	initTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((dir == 1) ? Robot.arm.getTopSwitch() : Robot.arm.getBottomSwitch()) || Timer.getFPGATimestamp() - initTime >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.move(0);
    	Robot.arm.inuse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
