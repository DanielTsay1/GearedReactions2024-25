package Main.Base.Odometry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import Main.Base.RobotUtilities.WheelBase;

@Disabled
@TeleOp(name = "Odometry Thread Test")
public class OdometryThreadTest extends OpMode {

    //Odometry encoder wheels
    DcMotor verticalRight, verticalLeft, horizontal;

    //The amount of encoder ticks for each inch the robot moves. THIS WILL CHANGE FOR EACH ROBOT AND NEEDS TO BE UPDATED HERE
    final double TICKS_PER_REV = 8192;
    final double WHEEL_DIAMETER = 38/25.4;

    final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER);

    //The amount of encoder ticks for each inch the robot moves. This will change for each robot and needs to be changed here
    final double COUNTS_PER_INCH = TICKS_PER_INCH;

    //Hardware map names for the encoder wheels. Again, these will change for each robot and need to be updated below
    String verticalLeftEncoderName = "leftFront", verticalRightEncoderName = "leftBack", horizontalEncoderName = "rightBack";

    WheelBase wheelbase;

    OdometryGlobalCoordinatePosition globalPositionUpdate;

    @Override
    public void init() {

        String leftFrontName = "leftFront", rightFrontName = "rightFront", leftBackName = "leftBack", rightBackName = "rightBack";

        DcMotor leftFront = hardwareMap.dcMotor.get(leftFrontName);
        DcMotor leftBack = hardwareMap.dcMotor.get(leftBackName);
        DcMotor rightBack = hardwareMap.dcMotor.get(rightBackName);
        DcMotor rightFront = hardwareMap.dcMotor.get(rightFrontName);

        wheelbase = new WheelBase(leftFront, leftBack, rightFront, rightBack);

        //Assign the hardware map to the odometry wheels
        verticalLeft = hardwareMap.dcMotor.get(verticalLeftEncoderName);
        verticalRight = hardwareMap.dcMotor.get(verticalRightEncoderName);
        horizontal = hardwareMap.dcMotor.get(horizontalEncoderName);

        //Reset the encoders
        verticalRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /*
        Reverse the direction of the odometry wheels. THIS WILL CHANGE FOR EACH ROBOT. Adjust the direction (as needed) of each encoder wheel
        such that when the verticalLeft and verticalRight encoders spin forward, they return positive values, and when the
        horizontal encoder travels to the right, it returns positive value
        */
//        verticalLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        verticalRight.setDirection(DcMotorSimple.Direction.REVERSE);
        horizontal.setDirection(DcMotorSimple.Direction.REVERSE);

        //Set the mode of the odometry encoders to RUN_WITHOUT_ENCODER
        verticalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        horizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Init complete
        telemetry.addData("Status", "Init Complete");
        telemetry.update();
    }

        /**
         * *****************
         * OpMode Begins Here
         * *****************
         */
    @Override
    public void start() {

        globalPositionUpdate = new OdometryGlobalCoordinatePosition(verticalLeft, verticalRight, horizontal, COUNTS_PER_INCH, 75);
        Thread positionThread = new Thread(globalPositionUpdate);
        positionThread.start();

        goToPosition(24, 0, 0.5, 90, 4);
    }

    //Create and start GlobalCoordinatePosition thread to constantly update the global coordinate positions\
//        goToPosition(0, 24, 0.5, 0, 4);


    public void loop(){
        //Display Global (x, y, theta) coordinates
        telemetry.addData("X Position", globalPositionUpdate.returnXCoordinate() / TICKS_PER_INCH);
        telemetry.addData("Y Position", globalPositionUpdate.returnYCoordinate() / TICKS_PER_INCH);
        telemetry.addData("Orientation (Degrees)", globalPositionUpdate.returnOrientation());
        telemetry.addData("Left: ", verticalLeft.getCurrentPosition());
        telemetry.addData("Mid: ", horizontal.getCurrentPosition());
        telemetry.addData("Right: ", verticalRight.getCurrentPosition());
        telemetry.update();
    }

    public void stop() {
        //Stop the thread
        globalPositionUpdate.stop();
    }



    public void goToPosition(double targetX, double targetY, double robotPower, double desiredRobotOrientation, double error){
        targetX = targetX * TICKS_PER_INCH;
        targetY = targetY * TICKS_PER_INCH;

        double distanceToXTarget = targetX - globalPositionUpdate.returnXCoordinate();
        double distanceToYTarget = targetY - globalPositionUpdate.returnYCoordinate();

        double distance = Math.hypot(distanceToXTarget, distanceToYTarget);

        error = error * TICKS_PER_INCH;

        while(distance > error) {

            //if(distance - error < 4*error) robotPower /= 4;

            distance = Math.hypot(distanceToXTarget, distanceToYTarget);
            distanceToXTarget = targetX - globalPositionUpdate.returnXCoordinate();
            distanceToYTarget = targetY - globalPositionUpdate.returnYCoordinate();

            double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, robotPower);
            double robotMovementYComponent = calculateY(robotMovementAngle, robotPower);
            double pivotCorrection = desiredRobotOrientation - globalPositionUpdate.returnOrientation();

            if (pivotCorrection > 5) pivotCorrection = 0.4;
            else if(pivotCorrection < 5) pivotCorrection = -0.4;
            else pivotCorrection = 0;

            wheelbase.mecanumDrive(robotMovementXComponent, robotMovementYComponent, pivotCorrection, false);



//            telemetry.addData("X: ", globalPositionUpdate.returnXCoordinate());
//            telemetry.addData("Y: ", globalPositionUpdate.returnYCoordinate());
//            telemetry.update();


        }

        wheelbase.setMotorPowers(0, 0, 0, 0);
    }



    /**
     * Calculate the power in the x direction
     * @param desiredAngle angle on the x axis
     * @param speed robot's speed
     * @return the x vector
     */
    private double calculateX(double desiredAngle, double speed) {
        return Math.sin(Math.toRadians(desiredAngle)) * speed;
    }

    /**
     * Calculate the power in the y direction
     * @param desiredAngle angle on the y axis
     * @param speed robot's speed
     * @return the y vector
     */
    private double calculateY(double desiredAngle, double speed) {
        return Math.cos(Math.toRadians(desiredAngle)) * speed;
    }
}

