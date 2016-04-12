package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Fondle extends Command {

    public Fondle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballfondler.fondle();
    	Robot.fhm.setHeight(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.ballfondler.isFondling()) {
    		Robot.ballfondler.fondle();
    	} else {
    		Robot.ballfondler.squeeze();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ballfondler.squeeze();
    	Robot.fhm.setHeight(1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ballfondler.stopFondling();
    	Timer.delay(0.5);
    	Robot.ballfondler.squeeze();
    }
}
