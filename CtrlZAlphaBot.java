import robocode.*;
import java.awt.Color;
import robocode.HitRobotEvent;

public class CtrlZAlphaBot extends AlphaBot
{
	int count = 0;
	double gunTurnAmt;
	String trackName;
	
	public void run() {
		
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));
		setScanColor(Color.red);
		setBulletColor(Color.black);

		trackName = null; // Initialize to not tracking anyone
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		gunTurnAmt = 10; // Initialize gunTurn to 10

		while (true) {
			turnGunRight(gunTurnAmt);
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
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(300);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(300);
		}
	}


}
