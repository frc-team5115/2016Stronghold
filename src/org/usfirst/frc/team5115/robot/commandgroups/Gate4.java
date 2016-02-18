package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gate4 extends CommandGroup {
    
    public  Gate4() {
    	addSequential(new Turn(-25));
        addSequential(new StraightLine(14));
        addSequential(new Turn(85));
        addSequential(new StopFondling());
    }
}
