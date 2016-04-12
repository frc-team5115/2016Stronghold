package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;
import org.usfirst.frc.team5115.robot.commands.TurnGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Test extends CommandGroup {
    
    public  Test() {
       // addSequential(new StraightLine(5));
        addSequential(new TurnGyro(-180));
    }
}
