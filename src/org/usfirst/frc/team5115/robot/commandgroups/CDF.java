package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.ArmMove;
import org.usfirst.frc.team5115.robot.commands.StraightLineFast;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CDF extends CommandGroup {
    
    public  CDF() {
    	addSequential(new WaitCommand(0.5));
        addSequential(new ArmMove(-1, 3));
        addParallel(new StraightLineFast(8));
        addSequential(new WaitCommand(0.4));
        addSequential(new ArmMove(1, 5));
    }
}
