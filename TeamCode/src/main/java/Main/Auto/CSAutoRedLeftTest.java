package Main.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Main.Base.AutoRobot;

@Autonomous(name = "AutoRedLeftTest", group = "Competition")
public class CSAutoRedLeftTest extends AutoRobot {

    double speed = 0.5;

    double error = 4;

    int cycles = 1;

    int location = 3;

    int[] tags = new int[6];
    boolean leftAlign = false;
    boolean rightAlign = false;



    public void runAutonomous() {
        try {
//            tray.
            wheelBase.goToPosition(odometry, gyro, telemetry, 0, -28, 0, 0.75, 5);


        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
