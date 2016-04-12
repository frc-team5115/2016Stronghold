package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *	Drives at ~NITRO-BOOOOST~ speeds by mega-super-charging the wheelz.
 */
public class StraightLineFast extends Command {

	double dist;
	
    public StraightLineFast(double d) {
        dist = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.inuse = true;
    	Robot.chassis.resetEncoders();
    	Timer.delay(0.1);
    	Robot.chassis.tankDrive(0.7, 0.7, 1);	// lucky number 0.7
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
