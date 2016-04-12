package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.ArmMove;
import org.usfirst.frc.team5115.robot.commands.StraightLine;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Portcullis extends CommandGroup {
    
    public  Portcullis() {
    	addSequential(new ArmMove(-1, 5));
    	addParallel(new StraightLine(8, 0.4));
    	addSequential(new WaitCommand(0.75));
    	addSequential(new ArmMove(1, 0.75));
    }
}
