package Main.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Main.Base.AutoRobot2;

@Autonomous(name = "AutoBlueLeft", group = "Competition")
public class CSAutoBlueLeft extends AutoRobot2 {

    double speed = 0.3;

    double error = 4;
//    int cycles = 1;
    public void runAutonomous(){

        try {
            viper2.clawOpen();
            wheelBase.goToPosition(odometry,gyro,telemetry,0,-25,0,0.7,5);
            tray.setPosition(0.35);
            //pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,-30,-25,0,0.7,5);

            //wheelBase.goToPosition(odometry,gyro,telemetry,-28,-55,0,0.7,5);
            //wheelBase.turnBotNew(odometry,gyro,-90,5);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-18,-56,-90,0.3,3);
            //viper2.clawMid();
            pause(250);
            tray.setPosition(.35);
            pause(200);
            viper2.extend(true, 900);
            pause(200);
            tray.setPosition(.22);
            pause(200);
            viper2.extend(true, 10
            );
            viper2.clawClose();
            pause(200);
            tray.setPosition(0.35);
            pause(200);
            viper2.clawClose();

            wheelBase.goToPosition(odometry,gyro,telemetry,-12,-12,0,0.85,3);
            wheelBase.goToPosition(odometry,gyro,telemetry,8,-12,0,0.85,3);
            wheelBase.turnBotNew(odometry,gyro,90,5);
            //pause(300);
            wheelBase.turnBotNew(odometry,gyro,180,5);
            //pause(300);
            tray.setPosition(.96);
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,10.5,-12,180,0.7,3);
            wheelBase.goToPosition(odometry,gyro,telemetry,10.5,-20.5,180,0.7,3);
            pause(300);
            viper2.clawOpen();
            pause(300);
            //pause(300);
//            wheelBase.goToPosition(odometry,gyro,telemetry,16,-22,180,0.7,3);
            wheelBase.goToPosition(odometry,gyro,telemetry,8,-12,180,0.5,3);
            pause(300);
//            wheelBase.goToPosition(odometry,gyro,telemetry,35,-38,180,0.7,5);
            tray.setPosition(0.35);
//            pause(300);
            pause(300);
            wheelBase.turnBotNew(odometry,gyro,-130,5);
            //pause(1000);


            pause(300);

            tray.setPosition(0.2);
            viper2.extend(true, 2310);

//            wheelBase.turnBotNew(odometry,gyro,-128,5);


//            wheelBase.goToPosition(odometry,gyro,telemetry,35,-6,128,0.5,3);
            pause(1800);
            arm.setPosition(0.15);
            pause(300);
            viper2.clawClose();
            pause(200);
            tray.setPosition(.35);
            //pause(300);
            viper2.extend(true, 10);
            arm.setPosition(0.43);

            //viper2.armIn();
            tray.setPosition(.7);
//
            wheelBase.turnBotNew(odometry,gyro,180,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,20,-12,180,0.5,3);
            //pause(300);
            tray.setPosition(.96);

            //second basket
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,21,-19,180,0.7,3);
            viper2.clawOpen();
            pause(300);
            tray.setPosition(0.35);

            wheelBase.goToPosition(odometry,gyro,telemetry,20,-17,180,0.5,3);
            wheelBase.turnBotNew(odometry,gyro,-130,5);

            wheelBase.goToPosition(odometry,gyro,telemetry,8,-12,-129,0.5,3);
            pause(300);
            tray.setPosition(0.2);

            viper2.extend(true, 2310);
//            wheelBase.goToPosition(odometry,gyro,telemetry,35,-6,128,0.5,3);
            pause(1700);
            arm.setPosition(0.13);

            pause(200);
            viper2.clawClose();
            pause(300);
            tray.setPosition(.35);
            //pause(300);
            viper2.extend(true, 10);
            arm.setPosition(0.43);
            //viper2.armIn();

            tray.setPosition(0.35);
            wheelBase.turnBotNew(odometry,gyro,180,5);
//            wheelBase.turnBotNew(odometry,gyro,172,5);
//            wheelBase.turnBotNew(odometry,gyro,180,5);
//            wheelBase.goToPosition(odometry,gyro,telemetry,10,-51,180,0.5,3);
//            wheelBase.goToPosition(odometry,gyro,telemetry,16,-51,180,0.5,3);
//            wheelBase.goToPosition(odometry,gyro,telemetry,16,-12,180,0.5,3);
            //pushes otjher specimne to area if we have enough time

            //srvond stage auto
            //move back x -20
            //move back y 16
            //turn 180
            //move back y 30
            //wheelBase.goToPosition(odometry,gyro,telemetry,-35,-55,-90,0.7,5);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-35,-39,-90,0.7,5);
//            wheelBase.turnBotNew(odometry,gyro,-180,5);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-35,-11,90,0.7,3);
//            pause(300);
//            tray.setPosition(0.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-20,-12,90,0.4,3);
//            pause(300);
//            viper2.clawClose();
//            pause(300);
//            tray.setPosition(0.35);
//            wheelBase.turnBotNew(odometry,gyro,128,5);
//            viper2.extend(true, 1250);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-39,-6,128,0.5,3);
//            pause(300);
//            tray.setPosition(0.13);
//            pause(500);
//           viper2.clawOpen();
//            pause(300);
//            tray.setPosition(.35);
//            pause(400);
//            viper2.extend(true, 10);
//            tray.setPosition(.7);
//
////            wheelBase.goToPosition(odometry,gyro,telemetry,-39,-7,135,0.5,3);
//            wheelBase.turnBotNew(odometry,gyro,90,5);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-38,-6,90,0.5,3);
//
//            viper2.extend(true, 0);
//            tray.setPosition(0.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-35,-1,90,0.6,3);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-21,-2,90,0.6,3);
//            pause(500);
//            viper2.clawClose();
//            pause(500);
//            tray.setPosition(.35);
//            wheelBase.turnBotNew(odometry,gyro,128,5);
//            viper2.extend(true, 1250);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-39,-6,128,0.5,3);
//            pause(300);
//            tray.setPosition(0.13);
//            pause(500);
//            viper2.clawOpen();
//            pause(400);
//            tray.setPosition(.35);
//            pause(300);
//            viper2.extend(true, 10);
//            tray.setPosition(.7);
//
//
//
//
//






            //wheelBase.goToPosition(odometry,gyro,telemetry,0,-24,0,0.5,7);
//            wheelBase.turnBotNew(odometry,gyro,-90,5);
//            tray.setPosition(0.692);
//            viper.extendFast(true, 600);






            telemetry.addData("status: ", "done");
            telemetry.update();

        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}