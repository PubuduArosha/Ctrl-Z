import robocode.*;
//import robocode.HitRobotEvent;
//import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html


public class CtrlZ extends AlphaBot
{

	boolean peek;
	double moveAmount; 
	
	public void run() {
	
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.red);
		setScanColor(Color.red);

		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		
		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		
		peek = true;
		turnGunRight(110);
		turnRight(110);

		while (true) {
		
			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {
		
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		}
		else {
			ahead(100);
		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
			fire(1);
		if (peek) {
			scan();
		}
	}

}
