package Main.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Main.Base.AutoRobot;

@Autonomous(name = "AutoDoor", group = "Testing")
public class CSAutoDoor extends AutoRobot {

    double speed = 0.5;

    double error = 4;

    public void runAutonomous(){

        try {


        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
