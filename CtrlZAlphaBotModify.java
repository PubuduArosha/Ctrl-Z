import robocode.*;
import java.awt.Color;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import java.awt.*;

public class CtrlZAlphaBotModify extends AlphaBot
{
	int count = 0;
	double gunTurnAmt; 
	String trackName;
  boolean peek;
	double moveAmount; 

	public void run() {
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));

		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.black);

		trackName = null;
		setAdjustGunForRobotTurn(true);
		gunTurnAmt = 10;
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);

		peek = true;
		turnGunRight(110);
		turnRight(110);

		while (true) {
			count++;
      
			if (count > 2) {
				gunTurnAmt = -10;
			}
      
			if (count > 5) {
				gunTurnAmt = 10;
			}

			if (count > 11) {
				trackName = null;
			}

			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}

	public void onHitWall(HitWallEvent e) {
		if (e.getBearing() > -180 && e.getBearing() < 180) {
			back(200);
		} 
		else {
			ahead(200);
		}
	}	

	public void onHitRobot(HitRobotEvent e) {

		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(300);
		} 
			back(100);
		}
		else {
			ahead(300);
			ahead(100);
		}
	}


}
	public void onScannedRobot(ScannedRobotEvent e) {
			fire(1);
		if (peek) {
			scan();
		}
	}

}
