package Main.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Main.Base.AutoRobot;


@Autonomous(name = "AutoTestBlank", group = "Testing")
public class CSAutoBlank extends AutoRobot {

    double speed = 0.5;

    double error = 4;

    public void runAutonomous() {

        try {






//            wheelBase.turnBotNew(odometry,gyro,90,5);
//            for (int i = 1; i > 0; i--) {
//
//                wheelBase.goToPosition(odometry, gyro, telemetry, 72, -27, 87, 0.7, 5);
//                //intake.intakeOn(-1.0);
//                pause(500);
//                wheelBase.goToPosition(odometry, gyro, telemetry, 6, -27, 87, 0.7, 5);
//                //intake.stop();
//                //viper.extendFast(true, 3100);
//                wheelBase.goToPosition(odometry, gyro, telemetry, -16, -27, 87, 0.7, 5);
//                //tray.setPosition(.2);
//                pause(500);
//                //tray.setPosition(.75);
//                //viper.extendFast(false, -50);
//                wheelBase.goToPosition(odometry, gyro, telemetry, 72, -27, 87, 0.7, 5);
//                //intake.intakeOn(-1.0);
//                pause(500);
//                //intake.stop();
//                if (i == 1) {
//                    wheelBase.goToPosition(odometry, gyro, telemetry, 6, -27, 87, 0.7, 4);
//                    //viper.extendFast(true, 3100);
//                    wheelBase.goToPosition(odometry, gyro, telemetry, -16, -27, 87, 0.7, 4);
//                    //tray.setPosition(.2);
//                    pause(500);
//                    //tray.setPosition(.75);
//                    //viper.extendFast(false, 1550);
//                    wheelBase.goToPosition(odometry, gyro, telemetry, -32, -28, 87, 0.7, 4);
//




//                }

//            }
            //viper.powerOff();


            //telemetry.addData("signal: ", camera.detectSignal());
            //telemetry.update();


        } catch (Exception e) {
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
