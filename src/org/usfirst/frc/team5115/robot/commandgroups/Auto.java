package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.ArmMove;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.StraightLineGyro;
import org.usfirst.frc.team5115.robot.commands.SwitchDirection;
import org.usfirst.frc.team5115.robot.commands.Turn;
import org.usfirst.frc.team5115.robot.commands.TurnGyro;
import org.usfirst.frc.team5115.robot.commandgroups.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class Auto extends CommandGroup {
    
    public  Auto(int defense, int gate) {
    	addParallel(new StraightLine(2.5, 0.5));
    	
    	switch (defense) {
    	case 0:
    		addSequential(new PrintCommand("Low Bar"));
    		addSequential(new ArmMove(-1, 5));
            addSequential(new StraightLine(4, 0.6));
            addSequential(new Turn(30));
            addParallel(new StraightLine(9, 0.6));
            addParallel(new ArmMove(1, 3));
            addSequential(new WaitForChildren());
            addSequential(new Turn(-120));
            addSequential(new SwitchDirection());
            addSequential(new StraightLine(1, 0.5));
            addSequential(new AimScore());
    		break;
    	case 1:
    		addSequential(new PrintCommand("Cheval de Frise"));
    		addSequential(new CDF());
    		break;
    	case 2:
    		addSequential(new PrintCommand("Portcullis"));
    		addSequential(new Portcullis());
    		break;
    	case 3:
    		addSequential(new PrintCommand("Terrain Defense"));
    		addSequential(new StraightLine(8, 0.9));
    		break;
    	}
    	
    	switch (gate) {
    	case 2:
    		addSequential(new Gate2());
    		break;
    	case 3:
    		addSequential(new Gate3());
    		break;
    	case 4:
    		addSequential(new Gate4());
    		break;
    	case 5:
    		addSequential(new Gate5());
    		break;
    	}
    	
//    	 hello, I am Brian. I am the code. FEAR ME! hey computer, do the stuff. execute WIN(dontlose);
//    	 win(dontlose) {
//    	 if (dontlose == 1);
//    	 win
//    	 }
//    	 for (int i = 0; i >= 10; i++) {
//    	 do math to i;
//    	 }
    }
}
