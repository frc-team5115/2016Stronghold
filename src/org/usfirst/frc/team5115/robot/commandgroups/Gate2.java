package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gate2 extends CommandGroup {
    
    public  Gate2() {
        addSequential(new StraightLine(11));
        addSequential(new Turn(-60));
        addSequential(new StraightLine(3));
        addSequential(new StopFondling());
    }
}
