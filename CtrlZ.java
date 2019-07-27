import robocode.*;
//import java.awt.*;
import java.awt.Color;
import robocode.HitRobotEvent;
//import robocode.Robot;
//import robocode.ScannedRobotEvent;
//import robocode.WinEvent;
//import static robocode.util.Utils.normalRelativeAngleDegrees;


// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * CtrlZ - a robot by (your name here)
 */
public class CtrlZ extends AlphaBot
{
	//declare variable
	int count = 0; // Keeps track of how long we've
	// been searching for our target
	double gunTurnAmt; // How much to turn our gun when searching
	String trackName; // Name of the robot we're currently tracking
	/**
	 * run: CtrlZ's default behavior
	 */
	public void run() {
		// Set colors
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));
		setScanColor(Color.red);
		setBulletColor(Color.black);

		// Prepare gun
		trackName = null; // Initialize to not tracking anyone
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		gunTurnAmt = 10; // Initialize gunTurn to 10

		// Loop forever
		while (true) {
			// turn the Gun (looks for enemy)
			turnGunRight(gunTurnAmt);
			// Keep track of how long we've been looking
			count++;
			// If we've haven't seen our target for 2 turns, look left
			if (count > 2) {
				gunTurnAmt = -10;
			}
			// If we still haven't seen our target for 5 turns, look right
			if (count > 5) {
				gunTurnAmt = 10;
			}
			// If we *still* haven't seen our target after 10 turns, find another target
			if (count > 11) {
				trackName = null;
			}
		}
	}

	/**


	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
			// If he's in front of us, set back up a bit.
		if (e.getBearing() > -180 && e.getBearing() < 180) {
			back(200);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(200);
		}
	}	
	
	/**
	 * onHitRobot:  Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(300);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(300);
		}
	}


}

