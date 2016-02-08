package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Motion;
import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToGoal extends Command {
	
	int state = 0;
	
	int gate;
	
	int dist = 0;	//distance travelled

    public DriveToGoal(int g) {
        gate = g;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    	case 0:
    		// Correct angle
    		break;
    	case 1:
    		// Follow motion path to goal
    		Robot.chassis.tankDrive(Motion.left(gate, dist), Motion.right(gate, dist));
    		break;
    	case 2:
    		// Center around top goal
    		break;
    	case 3:
    		// Drive forward
    		break;
    	case 4:
    		// Shoot
    		break;
    	}
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
