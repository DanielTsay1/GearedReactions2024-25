package Main.Base.RobotUtilities;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Viper {

    private DcMotor viper;

//    private Servo tray;


    public Viper(DcMotor viper) {

        this.viper = viper;


    }

    public void extend(boolean up, int position) {
        if(up) {
            // set motors to run forward for x encoder counts.
            //-3100
            viper.setTargetPosition(position);

            // set motors to run to target encoder position and stop with brakes on.
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // set both motors to 25% power. Movement will start. Sign of power is
            // ignored as sign of target encoder position controls direction when
            // running to position.
            viper.setPower(1.0);

            // wait while opmode is active and left motor is busy running to position.
            while (viper.isBusy() && viper.getCurrentPosition() > viper.getTargetPosition())   //)
            {
//                telemetry.addData("encoder-fwd-left", viper.getCurrentPosition() + "  busy=" + viper.isBusy());
//                telemetry.update();

            }

            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

            viper.setPower(0.0);


        } else {
            // set motors to run forward for x encoder counts.
            //-3100
            viper.setTargetPosition(-100);

            // set motors to run to target encoder position and stop with brakes on.
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // set both motors to 25% power. Movement will start. Sign of power is
            // ignored as sign of target encoder position controls direction when
            // running to position.
            viper.setPower(1.0);

            // wait while opmode is active and left motor is busy running to position.
            while (viper.isBusy() && viper.getCurrentPosition() < viper.getTargetPosition())   //)
            {
//                telemetry.addData("encoder-fwd-left", viper.getCurrentPosition() + "  busy=" + viper.isBusy());
//                telemetry.update();

            }

            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

            viper.setPower(0.0);

        }
    }

    public void extendFast(boolean up, int position) {
        if(up) {
            viper.setTargetPosition(-position);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1.0);


        } else {
            viper.setTargetPosition(position >= 0 ? -100 : position);
            viper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viper.setPower(1.0);


        }
    }

    public void power(double pow) {
        viper.setPower(pow);
    }

    public void powerOff() {
        viper.setPower(0.0);

    }

//    public void drop(boolean up) {
//        if(up) {
//            viper.setPower(0.5);
//
//            //sleep
//            viper.setPower(0.0);
//
//            setTray(false);
//        } else {
//            viper.setPower(-0.5);
//
//            setTray(true);
//            //sleep
//            viper.setPower(0.0);
//
//
//
//        }
//
//    }
//chamge cordinates for tray
//    public void setTray(boolean in) {
//        if(in){
//            tray.setPosition(0.0);
//        }
//        else{
//            tray.setPosition(1.0);
//        }
//
//    }


//set servos
//    public void armIn() {
//        arm.setPosition(x);
//    }
//    public void armOut(){
//        arm.setPosition(x);
//    }

    //change cordinates for claw
//    public void clawOpen (){
//        clawL.setPosition(0.47);
//        clawR.setPosition(0.055);
//    }
//    public void clawClose(){
//        clawL.setPosition(0.5);
//        clawR.setPosition(0.0);
//    }


}
