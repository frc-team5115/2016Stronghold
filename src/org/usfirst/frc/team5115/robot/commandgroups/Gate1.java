package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.StraightLineGyro;
import org.usfirst.frc.team5115.robot.commands.SwitchDirection;
import org.usfirst.frc.team5115.robot.commands.Turn;
import org.usfirst.frc.team5115.robot.commands.TurnGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class Gate1 extends CommandGroup {
    
    public  Gate1() {
    	addSequential(new PrintCommand("Gate 1"));
        addSequential(new StraightLine(9.5, 0.4));
        addSequential(new Turn(-60));
        addSequential(new SwitchDirection());
        addSequential(new StraightLine(4, 0.4));
        addSequential(new Aim());
        addSequential(new StraightLine(4, 0.4));
        addSequential(new StopFondling());
    }
}
