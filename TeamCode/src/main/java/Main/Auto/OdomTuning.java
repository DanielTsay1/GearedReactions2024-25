package Main.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Main.Base.AutoRobot;
@Autonomous(name = "Odom Tuning", group = "testing")
public class OdomTuning extends AutoRobot {

    double speed = 0.5;

    double error = 2;

    public void runAutonomous(){

        try {

//            wheelBase.goToPosition(odometry, gyro, 10, 0, 90, speed, error);
//            wheelBase.goToPosition(odometry, gyro, 0, 0, 0, speed, 3);



        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
