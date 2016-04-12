package org.usfirst.frc.team5115.robot.triggers;

import org.usfirst.frc.team5115.robot.OI;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class CDFTrigger extends Trigger {
    
    public boolean get() {
        return OI.getHat() > 90 && OI.getHat() < 270;
    }
}
