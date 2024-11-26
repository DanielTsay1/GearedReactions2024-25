package Main.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import Main.Base.AutoRobot2;
@Autonomous(name = "AutoRedRight", group = "Competition")

public class CSAutoRedRight extends AutoRobot2 {

    double speed = 0.5;

    double error = 4;

    int cycles = 1;

    int location = 1;
    public void runAutonomous(){

        //location = cameraBlue.detect();

       try {
            wheelBase.goToPosition(odometry,gyro,telemetry,8,-23,0,0.5,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,8,-29,0,0.5,5);
            pause(300);
            tray.setPosition(.15);
            pause(400);
            viper2.extend(true, 1000);
            pause(500);
            tray.setPosition(.23);
            pause(500);
            viper2.extend(true, 200);
            //pause(100);
            clawLeft.setPosition(0.5);
            clawRight.setPosition(0.5);
            //pause(500);
            viper2.extend(true, 0);
            pause(400);
            tray.setPosition(0.35);

            wheelBase.goToPosition(odometry,gyro,telemetry,6,-18,0,0.5,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-10,0,0.5,5);
            wheelBase.turnBotNew(odometry,gyro,175,3);
            pause(500);
            tray.setPosition(0.7);
            pause(300);
            tray.setPosition(0.9);
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-28  ,175,0.5,5);

            pause(500);
            viper2.clawClose();
            pause(500);
            wheelBase.turnBotNew(odometry,gyro,0,5);
//            tray.setPosition(.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-20,0,0.3,3);
//            viper2.clawClose();
//            tray.setPosition(.7);
            wheelBase.turnBotNew(odometry,gyro,-10,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-10,0,0.5,5);
            viper2.clawOpen();
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-15,0,0.5,5);
            wheelBase.turnBotNew(odometry,gyro,175,3);
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,-51,-15,175,0.5,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-51,-28,175,0.5,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-28,175,0.5,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-38,-28,175,0.5,5);
            viper2.clawClose();
            pause(500);
            wheelBase.turnBotNew(odometry,gyro,0,5);
//            tray.setPosition(.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-20,0,0.3,3);
//            viper2.clawClose();
//            tray.setPosition(.7);
            wheelBase.turnBotNew(odometry,gyro,-10,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-10,0,0.5,5);

            viper2.clawOpen();
            pause(300);
            tray.setPosition(0.35);
            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-5,0,0.5,5);



//            wheelBase.goToPosition(odometry,gyro,telemetry,-1,-31,0,0.8,3);
//            wheelBase.goToPosition(odometry,gyro,telemetry,0,-28,0,0.4,5);
            //wheelBase.goToPosition(odometry,gyro,telemetry,0,-24,0,0.5,7);
            //wheelBase.turnBotNew(odometry,gyro,-90,5);
            //picker.setPosition(0.6);
            //tray.setPosition(0.05);




            telemetry.addData("status: ", "done");
            telemetry.update();

        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
