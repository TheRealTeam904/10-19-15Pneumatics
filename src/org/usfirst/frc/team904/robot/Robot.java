
package org.usfirst.frc.team904.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	static DoubleSolenoid shift;
	static Joystick driverJoystick;
	
	double x, y,
			v, w,
			r, l;
	static CANTalon hawk,
			eagle,
			falcon,
			chicken,
			ostrich,
			emu;
				
	
    public void robotInit() {
    	//Solenoids
    	shift = new DoubleSolenoid(0, 1);
    	//Joysticks
    	driverJoystick = new Joystick(0);
    	//CANTalons
    	hawk = new CANTalon (2);
    	eagle = new CANTalon (3);
    	falcon = new CANTalon (4);
    	chicken = new CANTalon (5);
    	ostrich = new CANTalon (6);
    	emu = new CANTalon (7);
    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        shift();
        drive();
    }

    public void testPeriodic() {
    
    }
    public void drive() {
    	x = -driverJoystick.getX();
    	y = driverJoystick.getY();
    	v = ((100 - Math.abs(x)) * (y/100) + y);
    	w = ((100 - Math.abs(y)) * (x/100) + x);
    	r = (v+w)/2;
    	l = (v-w)/2;
    	hawk.set(r);
    	eagle.set(r);
    	falcon.set(-r);
    	chicken.set(l);
    	ostrich.set(l);
    	emu.set(-l);
    }
    public void shift() {
    	if(driverJoystick.getRawButton(1)){
    		shift.set(DoubleSolenoid.Value.kForward);
    		Timer.delay(.01);
    	}
    	else if(driverJoystick.getRawButton(2)){
    		shift.set(DoubleSolenoid.Value.kReverse);
    		Timer.delay(.01);
    	}
    	else{
    		shift.set(DoubleSolenoid.Value.kOff);
    	}
    }
}
