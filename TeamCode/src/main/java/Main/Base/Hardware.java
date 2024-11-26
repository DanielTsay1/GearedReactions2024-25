package Main.Base;

import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware extends OpMode {

    //Define all motors, servos, and sensors in this class as well as their names in the config
    public DcMotor leftFront, rightFront, leftBack, rightBack;
    String leftFrontName = "leftFront", rightFrontName = "rightFront", leftBackName = "leftBack", rightBackName = "rightBack";

    public DcMotor left;
    public DcMotor right;
    public DcMotor mid ;

    public DcMotor viperMotor;

    public DcMotor viperMotor2;
    public Servo tray;

    public Servo arm; //Added new
    public DcMotor intakeMotor;

    public DcMotor linearLeft;
    public DcMotor linearRight;
    public Servo hookLeft;
    public Servo hookRight;

    public Servo droneServo;

    public Servo clawLeft;
    public Servo clawRight;

//    public DistanceSensor leftClawSensor;
//    public DistanceSensor rightClawSensor;
//    public RevBlinkinLedDriver ledDriver;


    String viperName = "viper";

    String viperName2 = "viper2";
    String trayName = "tray";

    String armName = "arm"; //new code
    String leftName = leftFrontName, rightName = leftBackName, midName = rightBackName;
    String intakeName = "intakeMotor";

    String hookLeftName = "hookL";
    String hookRightName = "hookR";
    String droneServoName = "droneServo";
    String leftLinName = "leftLin";
    String rightLinName = "rightLin";

    String clawLeftName = "clawLeft";
    String clawRightName= "clawRight";
    BNO055IMU imu;
    String imuName = "imu";

//    String blinkenName = "led";




    public void init(){}

    public void start(){}

    public void loop(){}

    public void stop(){}

    public void init_loop(){}

}
