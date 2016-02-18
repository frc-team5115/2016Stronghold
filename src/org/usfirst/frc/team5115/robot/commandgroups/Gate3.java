package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gate3 extends CommandGroup {
    
    public  Gate3() {
    	addSequential(new Turn(23));
        addSequential(new StraightLine(12));
        addSequential(new Turn(-80));
        addSequential(new StraightLine(3));
        addSequential(new StopFondling());
    }
}
