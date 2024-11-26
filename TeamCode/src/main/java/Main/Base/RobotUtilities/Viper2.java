package Main.Base.RobotUtilities;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Viper2 {

    private DcMotor viper2;

    private Servo tray;

//    Servo arm; //this is the thing that extends

    Servo clawLeft;

    Servo arm;

    Servo clawRight;

    public Viper2(DcMotor viper2, Servo arm, Servo tray, Servo clawLeft, Servo clawRight) {

        this.viper2 = viper2;
        this.tray = tray;
        this.clawLeft=clawLeft;
        this.clawRight=clawRight;
        this.arm= arm;

    }

    public void extend(boolean up, int position) {
        if(up) {
            // set motors to run forward for x encoder counts.
            //-3100
            viper2.setTargetPosition(position);

            // set motors to run to target encoder position and stop with brakes on.
            viper2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // set both motors to 25% power. Movement will start. Sign of power is
            // ignored as sign of target encoder position controls direction when
            // running to position.
            viper2.setPower(1.0);

            // wait while opmode is active and left motor is busy running to position.
            while (viper2.isBusy() && viper2.getCurrentPosition() > viper2.getTargetPosition())   //)
            {
//                telemetry.addData("encoder-fwd-left", viper.getCurrentPosition() + "  busy=" + viper.isBusy());
//                telemetry.update();

            }

            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

//            viper2.setPower(0.0);


        } else {
            // set motors to run forward for x encoder counts.
            //-3100
            viper2.setTargetPosition(100);

            // set motors to run to target encoder position and stop with brakes on.
            viper2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // set both motors to 25% power. Movement will start. Sign of power is
            // ignored as sign of target encoder position controls direction when
            // running to position.
            viper2.setPower(1.0);

            // wait while opmode is active and left motor is busy running to position.
            while (viper2.isBusy() && viper2.getCurrentPosition() < viper2.getTargetPosition())   //)
            {
//                telemetry.addData("encoder-fwd-left", viper.getCurrentPosition() + "  busy=" + viper.isBusy());
//                telemetry.update();

            }

            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

//            viper2.setPower(0.0);

        }
    }

    public void extendFast(boolean up, int position) {
        if(up) {
            viper2.setTargetPosition(-position);
            viper2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper2.setPower(1.0);


        } else {
            viper2.setTargetPosition(position >= 0 ? -100 : position);
            viper2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper2.setPower(1.0);


        }
    }

    public void power(double pow) {
        viper2.setPower(pow);
    }

    public void powerOff() {
        viper2.setPower(0.0);

    }

    public void drop(boolean up) {
        if (up) {
            viper2.setPower(0.5);

            //sleep
            viper2.setPower(0.0);

            setTray(false);
        } else {
            viper2.setPower(-0.5);

            setTray(true);
            //sleep
            viper2.setPower(0.0);


        }

    }

    //chamge cordinates for tray
    public void setTray(boolean in) {
        if (in) {
            tray.setPosition(0.0);
        } else {
            tray.setPosition(1.0);
        }

    }

//set servos
    public void armIn() {
        arm.setPosition(0.5);
    }
    public void armOut(){
        arm.setPosition(0.75);
    }

    //change cordinates for claw
    public void clawClose() {
        clawLeft.setPosition(0.44);
        clawRight.setPosition(0.6);
    }

    public void clawOpen() {
        clawLeft.setPosition(0.075);
        clawRight.setPosition(0.94);
    }
    public void clawStart() {
        clawLeft.setPosition(0);
        clawRight.setPosition(0);
    }
    public void clawMid(){
        clawLeft.setPosition(1);
        clawRight.setPosition(0);
    }

}
