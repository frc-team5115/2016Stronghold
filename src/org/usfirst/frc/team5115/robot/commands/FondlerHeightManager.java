package org.usfirst.frc.team5115.robot.commands;


import org.usfirst.frc.team5115.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FondlerHeightManager extends Command {
	
	double lastTime = 0;

    public FondlerHeightManager() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public void setHeight(int height) {
    	if (height == 1)
    		Robot.ballfondler.raise();
    	else if (height == 0)
    		Robot.ballfondler.lower();
    	lastTime = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Timer.getFPGATimestamp() - lastTime >= 6)
    		Robot.ballfondler.holdHeight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
