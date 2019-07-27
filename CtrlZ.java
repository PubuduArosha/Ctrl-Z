import robocode.HitRobotEvent;
import robocode.*;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class CtrlZ extends CharlieBot {

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
        turnGunRight(90);
        turnRight(90);
		fire(1);
        while (true) {
			fire(1);
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

    public void onRobotDetected(ScannedRobotEvent e) {
		turnGunRight(180);
        fire(2);
        if (peek) {
            scan();
        }
    }
}
