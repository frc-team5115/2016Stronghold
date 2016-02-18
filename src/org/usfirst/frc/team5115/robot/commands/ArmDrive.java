package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmDrive extends Command {

    public ArmDrive() {}

    protected void initialize() {}

    protected void execute() {
    	if (!Robot.arm.inuse)
    		Robot.arm.move(Robot.oi.getArmDir());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
