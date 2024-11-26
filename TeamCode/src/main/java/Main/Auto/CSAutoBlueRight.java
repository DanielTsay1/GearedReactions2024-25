package Main.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import Main.Base.AutoRobot2;
@Autonomous(name = "AutoBlueRight", group = "Competition")

public class CSAutoBlueRight extends AutoRobot2 {

    double speed = 0.7;

    double error = 4;

    int cycles = 1;

    int location = 1;
    public void runAutonomous(){

        //location = cameraBlue.detect();


        try {
            //first stage
            viper2.clawOpen();
            wheelBase.goToPosition(odometry,gyro,telemetry,7,-26,0,0.7,5);
            pause(200);
            //Third stage
            tray.setPosition(.35);
            pause(350);
            viper2.extend(true, 900);
            pause(350);
            tray.setPosition(.22);
            pause(300);
            viper2.extend(true, 10
            );
            viper2.clawClose();
            pause(300);
            tray.setPosition(0.35);
            pause(200);
//            wheelBase.goToPosition(odometry,gyro,telemetry,6,-23,0,0.7,5);
//            tray.setPosition(.35);
//            wheelBase.goToPosition(odometry,gyro,telemetry,6,-29,0,0.7,5);
//            //tray.setPosition(.35);
//            pause(200);
//            viper2.extend(true, 300);
//            pause(250);
//            tray.setPosition(.12);
//            pause(300);
//            viper2.extend(true, 1150);
//            pause(250);
//            tray.setPosition(.24);
//            pause(250);
//            viper2.extend(true, 200);
//            pause(200);
//            //viper2.extend(true, 400);
//            //pause(200);
//            tray.setPosition(0.12);
//            viper2.extend(true, 0);
            //viper2.extend(true, 50);
            //pause(200);
//            tray.setPosition(0.35);
//            pause(100);
//            viper2.extend(true, 10);
//            pause(100);
            tray.setPosition(.7);
            pause(200);
            tray.setPosition(0.97);
            pause(100);
            wheelBase.goToPosition(odometry,gyro,telemetry,6,-18,0,0.8,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,6,-14,0,0.8,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-40,-14,0,0.8,5);

            //second stage
//            clawLeft.setPosition(0.075);
//            clawRight.setPosition(0.94);
//            clawLeft.setPosition(0.075); //Open
//            clawRight.setPosition(0.94);
//            pause(100);
            wheelBase.turnBotNew(odometry,gyro,175,3);
            pause(100);
            //tray.setPosition(0.7);

            tray.setPosition(1);
            pause(300);
//            clawLeft.setPosition(0.308);
//            clawRight.setPosition(0.72);
            viper2.clawClose();
            pause(150);
//            viper2.clawClose();  maybe uncomment this
            wheelBase.goToPosition(odometry,gyro,telemetry,-40,-20, 175,0.7,5);

            pause(100);
//            clawLeft.setPosition(0.308);
//            clawRight.setPosition(0.72);
//            clawLeft.setPosition(0.54);
//            clawRight.setPosition(0.5);
            viper2.clawOpen();
            pause(400);
            wheelBase.turnBotNew(odometry,gyro,0,5);
//            tray.setPosition(.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-20,0,0.3,3);
//            viper2.clawClose();
//            tray.setPosition(.7);
//            wheelBase.turnBotNew(odometry,gyro,-10,5);
            pause(100);
            wheelBase.goToPosition(odometry,gyro,telemetry,-37,-15,0,0.5,2);
            pause(300);
//            clawLeft.setPosition(0.075);
//            clawRight.setPosition(0.94);
            viper2.clawClose();
            pause(300);
            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-30,0,0.5,2);
            //wheelBase.turnBotNew(odometry,gyro,175,3);
            //clawLeft.setPosition(0.54);
            //clawRight.setPosition(0.5);
            //pause(100);

            //clawLeft.setPosition(0.075);
            //clawRight.setPosition(0.94);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-44,-28,175,0.7,5);
            pause(100);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-39 ,0,0.7,5);
            pause(3000);
            wheelBase.goToPosition(odometry,gyro,telemetry,-36,-16,0,0.7,3);
            pause(100);

//            clawLeft.setPosition(0.54);
//            clawRight.setPosition(0.5);
            viper2.clawOpen();
            pause(250);
            tray.setPosition(.35);
            wheelBase.goToPosition(odometry,gyro,telemetry,6,-15,0,0.9,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,14,-29,0,0.9,5);
            pause(200);
            //Third stage
            tray.setPosition(.35);
            pause(300);
            viper2.extend(true, 750);
            pause(300);
            tray.setPosition(.22);
            pause(300);
            viper2.extend(true, 0);
            viper2.clawClose();
            pause(300);
            tray.setPosition(0.35);
            wheelBase.turnBotNew(odometry,gyro,180,5);
            wheelBase.goToPosition(odometry,gyro,telemetry,-47,-1,180,0.7,3);
            //viper2.clawOpen();
//     .
//     wheelBase.goToPosition(odometry,gyro,telemetry, 15,-10,0,0.7,3);
//            wheelBase.goToPosition(odometry,gyro,telemetry, -40,-10,0,0.9,3);
//            wheelBase.turnBotNew(odometry,gyro,180,5);

            //wheelBase.goToPosition(odometry,gyro,telemetry,-44,-15,175,0.7,5);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-44,-28,175,0.7,5);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-38,-28,175,0.7,5);
            //viper2.clawClose();
            //pause(200);
            //wheelBase.turnBotNew(odometry,gyro,0,5);
//            tray.setPosition(.9);
//            wheelBase.goToPosition(odometry,gyro,telemetry,-44,-20,0,0.3,3);
//            viper2.clawClose();
//            tray.setPosition(.7);
            //wheelBase.turnBotNew(odometry,gyro,-10,5);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-38,-10,0,0.7,5);

            //viper2.clawOpen();
            //pause(100);
            //tray.setPosition(0.35);
            //wheelBase.goToPosition(odometry,gyro,telemetry,-38,-5,0,0.7,5);



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
