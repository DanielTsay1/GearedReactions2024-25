package Main.Base.RobotUtilities;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class WheelBase {

    //We are using four drive train motors and a mecanum drive

    /**  lF  |-----|  rF

         lB  |-----|  rB**/

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    Double lF, rF, lB, rB, maxVector;

    static final double SLOMO_DIVIDER = 2;

    static final double ROBOT_DIAMETER = 20;

    static final double TICKS_PER_REVOLUTION = 8199;  //8192
    static final double WHEEL_DIAMETER = 1.5;  //1.45
    static final double TICKS_PER_INCH = (TICKS_PER_REVOLUTION / (WHEEL_DIAMETER * Math.PI));


    public WheelBase(DcMotor lF, DcMotor lB, DcMotor rF, DcMotor rB) {
        leftFront = lF;
        leftBack = lB;
        rightFront = rF;
        rightBack = rB;

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        setModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setModeAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setZeroPowerBehaviorAll(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void setModeAll(DcMotor.RunMode runMode) {
        leftFront.setMode(runMode);
        leftBack.setMode(runMode);
        rightFront.setMode(runMode);
        rightBack.setMode(runMode);
    }


    public void setZeroPowerBehaviorAll(DcMotor.ZeroPowerBehavior zpb) {
        leftFront.setZeroPowerBehavior(zpb);
        leftBack.setZeroPowerBehavior(zpb);
        rightFront.setZeroPowerBehavior(zpb);
        rightBack.setZeroPowerBehavior(zpb);
    }


    public void setMotorPowers(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower) {
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }

    public void mecanumDriveOrig(double leftX, double leftY, double rightX, boolean slomo) {
        lF = -leftX - leftY - rightX;
        rF = -leftX + leftY + rightX;
        lB = -leftX + leftY - rightX;
        rB = -leftX - leftY + rightX;


        maxVector = Math.max(Math.max(Math.abs(lF), Math.abs(rF)),
                Math.max(Math.abs(lB), Math.abs(rB)));

        maxVector = maxVector > 1 ? maxVector : 1;

        lF /= maxVector;
        rF /= maxVector;
        lB /= maxVector;
        rB /= maxVector;

        if(slomo) setMotorPowers(lF/SLOMO_DIVIDER, lB/SLOMO_DIVIDER, rF/SLOMO_DIVIDER, rB/SLOMO_DIVIDER);
        else setMotorPowers(lF, lB, rF, rB);

    }
    public void mecanumDrive(double leftX, double leftY, double rightX, boolean slomo) {
        lF = leftX - leftY + (rightX * SLOMO_DIVIDER);
        rF = leftX + leftY - (rightX * SLOMO_DIVIDER);
        lB = leftX + leftY + (rightX * SLOMO_DIVIDER);
        rB = leftX - leftY - (rightX * SLOMO_DIVIDER);


        maxVector = Math.max(Math.max(Math.abs(lF), Math.abs(rF)),
                Math.max(Math.abs(lB), Math.abs(rB)));

        maxVector = maxVector > 1 ? maxVector : 1;

        lF /= maxVector;
        rF /= maxVector;
        lB /= maxVector;
        rB /= maxVector;

        if(slomo) setMotorPowers(lF/SLOMO_DIVIDER, lB/SLOMO_DIVIDER, rF/SLOMO_DIVIDER, rB/SLOMO_DIVIDER);
        else setMotorPowers(lF, lB, rF, rB);

    }


    public void fieldOrientatedDrive (Gamepad gamepad, Gyro gyro, boolean slomo, boolean reverse, double reset) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double turn = gamepad.right_stick_x;

        double gamepadHpot = Range.clip(Math.hypot(x, y), 0, 1);

        double gamepadDegree = gyro.angleWrapRadians(Math.atan2(y, x));

        double heading = gyro.angleWrapRadians(-gyro.getHeadingRadians());

        double movementDegree = gamepadDegree - heading - reset;

        x = Math.cos(movementDegree) * gamepadHpot;

        y = Math.sin(movementDegree) * gamepadHpot;

        if(reverse) mecanumDrive(y, -x, turn, slomo);
        else mecanumDrive(x, y, turn, slomo);
    }

    public void odometryDrive (Gyro gyro, double x, double y, double turn, boolean slomo) {


        double gamepadHpot = Range.clip(Math.hypot(x, y), 0, 1);

        double gamepadDegree = angleWrapRadians(Math.atan2(y, x));

        double heading = angleWrapRadians(-gyro.getHeadingRadians());

        double movementDegree = gamepadDegree - heading;

        //double turn = turnHd;

        x = Math.cos(movementDegree) * gamepadHpot;

        y = Math.sin(movementDegree) * gamepadHpot;

//        turn = 1;
//
//        lF = leftX - leftY + rightX;
//        rF = leftX + leftY - rightX;
//        lB = leftX + leftY + rightX;
//        rB = leftX - leftY - rightX;

        lF = y + x + turn;
        rF = y - x - turn;
        lB = y - x + turn;
        rB = y + x - turn;

//        maxVector = Math.max(Math.max(Math.abs(lF), Math.abs(rF)),
//                Math.max(Math.abs(lB), Math.abs(rB)));
//
//        maxVector = maxVector < 1 ? maxVector : 1;
//
//        lF /= maxVector;
//        rF /= maxVector;
//        lB /= maxVector;
//        rB /= maxVector;

        if(slomo) setMotorPowers(lF/SLOMO_DIVIDER, lB/SLOMO_DIVIDER, rF/SLOMO_DIVIDER, rB/SLOMO_DIVIDER);
        else setMotorPowers(lF, lB, rF, rB);


    }

//    public void odometryDrive (Gyro gyro, double x, double y, double turn, boolean slomo, double slowDiv) {
//
//
//        double gamepadHpot = Range.clip(Math.hypot(x, y), 0, 1);
//
//        double gamepadDegree = angleWrapRadians(Math.atan2(y, x));
//
//        double heading = angleWrapRadians(-gyro.getHeadingRadians());
//
//        double movementDegree = gamepadDegree - heading;
//
//        //double turn = turnHd;
//
//        x = Math.cos(movementDegree) * gamepadHpot;
//
//        y = Math.sin(movementDegree) * gamepadHpot;
//
//        //turn = 1;
//
////        lF = leftX - leftY + rightX;
////        rF = leftX + leftY - rightX;
////        lB = leftX + leftY + rightX;
////        rB = leftX - leftY - rightX;
//
//        lF = y + x + turn;
//        rF = y - x - turn;
//        lB = y - x + turn;
//        rB = y + x - turn;
////
//        maxVector = Math.max(Math.max(Math.abs(lF), Math.abs(rF)),
//                Math.max(Math.abs(lB), Math.abs(rB)));
//
//        maxVector = maxVector < 1 ? maxVector : 1;
//
//        lF /= maxVector;
//        rF /= maxVector;
//        lB /= maxVector;
//        rB /= maxVector;
//
//        if(slomo) setMotorPowers(lF/slowDiv, lB/slowDiv, rF/slowDiv, rB/slowDiv);
//        else setMotorPowers(lF, lB, rF, rB);
//
//
//    }





    public void goToPosition(Odometry odometry, double targetX, double targetY, double robotPower, double error){
        targetX = targetX * TICKS_PER_INCH;
        targetY = targetY * TICKS_PER_INCH;

        error = error * TICKS_PER_INCH;
//        TICKS_PER_INCH;

        double distanceToXTarget = targetX - odometry.returnXCoordinate();
        double distanceToYTarget = targetY - odometry.returnYCoordinate();

        double distance = Math.hypot(distanceToXTarget, distanceToYTarget);

        while(distance > error) { //|| Math.abs(desiredRobotOrientation - globalPositionUpdate.returnOrientation()) > 4) {

            if(distance - error < 4*error) robotPower /= 4;

            distance = Math.hypot(distanceToXTarget, distanceToYTarget);
            distanceToXTarget = targetX - odometry.returnXCoordinate();
            distanceToYTarget = targetY - odometry.returnYCoordinate();

            double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, robotPower);
            double robotMovementYComponent = calculateY(robotMovementAngle, robotPower);

//            double pivotCorrection = desiredRobotOrientation - odometry.returnOrientation();
//
//            if (pivotCorrection > 3) pivotCorrection = pivotCorrection / 180;
//            else if(pivotCorrection < 3) pivotCorrection = pivotCorrection / 180;
//            else pivotCorrection = 0;

            mecanumDrive(robotMovementXComponent, robotMovementYComponent, 0, false);



//            telemetry.addData("X: ", globalPositionUpdate.returnXCoordinate());
//            telemetry.addData("Y: ", globalPositionUpdate.returnYCoordinate());
//            telemetry.update();


        }

        setMotorPowers(0, 0, 0, 0);
    }


    public void goToPosition(Odometry odometry, Gyro gyro, Telemetry tm, double targetX, double targetY, double desiredRobotOrientation, double robotPower, double error){
        boolean Slomo = false;
        //double slomoDivider = Range.clip(4*robotPower,1,4);

        targetX = targetX * TICKS_PER_INCH;
        targetY = targetY * TICKS_PER_INCH;

        double distanceToXTarget = targetX - odometry.returnXCoordinate();
        double distanceToYTarget = targetY - odometry.returnYCoordinate();

        double distance = Math.hypot(distanceToXTarget, distanceToYTarget);
        /**
         * Change this factor depending on how erratic the robot is
         */
        double slomoDist = 10*TICKS_PER_INCH;
        if (distance > 20*TICKS_PER_INCH){slomoDist *= 2;}

        error = error * TICKS_PER_INCH;


        double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToYTarget, distanceToXTarget));

        double robotTurnAngle;

        if(abs(distanceToYTarget) > abs(distanceToXTarget)) robotTurnAngle = Math.toDegrees(Math.atan2(distanceToYTarget, 0));
        else robotTurnAngle = Math.toDegrees(Math.atan2(abs(distanceToXTarget), 0)); //review



        while(distance > error || abs(desiredRobotOrientation - gyro.getHeading()) > 3) {


            distance = Math.hypot(distanceToXTarget, distanceToYTarget);
            distanceToXTarget = targetX - odometry.returnXCoordinate();
            distanceToYTarget = targetY - odometry.returnYCoordinate();

            robotMovementAngle = Math.toDegrees(Math.atan2(distanceToYTarget, distanceToXTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, robotPower);
            double robotMovementYComponent = calculateY(robotMovementAngle, robotPower);

//            if(desiredRobotOrientation == -90) robotTurnAngle = Math.toDegrees(Math.atan2(0, distanceToYTarget));
            if(abs(distanceToYTarget) > abs(distanceToXTarget)) robotTurnAngle = Math.toDegrees(Math.atan2(distanceToYTarget, 0));
            else robotTurnAngle = Math.toDegrees(Math.atan2(distanceToXTarget, 0)); //review
            //robotTurnAngle = 90;


            //double relativeAngleToTarget = angleWrapRadians(Math.toRadians(90) - (gyro.getHeadingRadians() - Math.toRadians(90) + desiredRobotOrientation));
            double relativeAngleToTarget = angleWrapDegrees(180 - gyro.getHeading()  + desiredRobotOrientation);
            double relativeTurnAngle = relativeAngleToTarget - 180;

            double turnPower = Range.clip(Math.toRadians(relativeTurnAngle), -1, 1) * 0.8;


            if(distance < slomoDist) Slomo = true;

//            mecanumDrive(robotMovementXComponent, robotMovementYComponent, turnPower, Slomo);
            odometryDrive(gyro, robotMovementXComponent, robotMovementYComponent, turnPower, Slomo);
//            odometryDrive(gyro, robotMovementXComponent, robotMovementYComponent, turnPower, Slomo, slomoDivider);

//            tm.addData("X Position", odometry.returnXCoordinateInches());
//            tm.addData("Y Position", odometry.returnYCoordinateInches());
//            tm.addData("Orientation (Degrees)", gyro.getHeading());
//            tm.addData("distX",distanceToXTarget);
//            tm.addData("distY",distanceToYTarget);
//            tm.addData("Robot Movement Angle: ", robotMovementAngle);
//            tm.addData("Relative Angle To Target: ", Math.toDegrees(relativeAngleToTarget));
//            tm.addData("Relative Turn Angle: ", Math.toDegrees(relativeTurnAngle));
//            tm.addData("Turn Power: ", turnPower);
//            tm.addData("ANGLE: ", odometry.returnOrientation());

        }

        setMotorPowers(0, 0, 0, 0);
    }

    public void turnBotNew(Odometry odometry, Gyro gyro, double desiredRobotOrientation, double error){

        double targetX = odometry.returnXCoordinate();
        double targetY = odometry.returnYCoordinate();

        while(abs(desiredRobotOrientation - gyro.getHeading()) > error+5) {

            //double relativeAngleToTarget = angleWrapRadians(Math.toRadians(90) - (gyro.getHeadingRadians() - Math.toRadians(90) + desiredRobotOrientation));
            double relativeAngleToTarget = angleWrapDegrees(180 - gyro.getHeading()  + desiredRobotOrientation);
            double relativeTurnAngle = relativeAngleToTarget - 180;


            double turnPower =  relativeTurnAngle;
            if(turnPower < 0) turnPower = -0.4;
            else turnPower = 0.4;

            if(abs(desiredRobotOrientation - gyro.getHeading()) < 15) turnPower /= 2;

            mecanumDrive(0, 0, turnPower, false);



//            tm.addData("Robot Movement Angle: ", robotMovementAngle);
//            tm.addData("Relative Angle To Target: ", Math.toDegrees(relativeAngleToTarget));
//            tm.addData("Relative Turn Angle: ", Math.toDegrees(relativeTurnAngle));
//            tm.addData("Turn Power: ", turnPower);
//            tm.addData("ANGLE: ", odometry.returnOrientation());
//            tm.update();

        }
        while(abs(desiredRobotOrientation - gyro.getHeading()) > error) {
            double distanceToXTarget = targetX - odometry.returnXCoordinate();
            double distanceToYTarget = targetY - odometry.returnYCoordinate();

            double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToYTarget, distanceToXTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, 0.5);
            double robotMovementYComponent = calculateY(robotMovementAngle, 0.5);

            double relativeAngleToTarget = angleWrapDegrees(180 - gyro.getHeading()  + desiredRobotOrientation);
            double relativeTurnAngle = relativeAngleToTarget - 180;

            double turnPower =  relativeTurnAngle;
            if(turnPower < 0) turnPower = -0.2;
            else turnPower = 0.1;

            odometryDrive(gyro, robotMovementXComponent, robotMovementYComponent, turnPower,true);
        }
        setMotorPowers(0, 0, 0, 0);
    }

    public void turnBot(Odometry odometry, Gyro gyro, double targetX, double targetY, double desiredRobotOrientation){
        boolean Slomo = false;

        targetX = targetX * TICKS_PER_INCH;
        targetY = targetY * TICKS_PER_INCH;

        double distanceToXTarget = targetX - odometry.returnXCoordinate();
        double distanceToYTarget = targetY - odometry.returnYCoordinate();

        double distance = Math.hypot(distanceToXTarget, distanceToYTarget);



        double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));;

        double robotTurnAngle;

        if(abs(distanceToYTarget) > abs(distanceToXTarget)) robotTurnAngle = Math.toDegrees(Math.atan2(distanceToYTarget, 0));
        else robotTurnAngle = Math.toDegrees(Math.atan2(abs(distanceToXTarget), 0));



        while(abs(desiredRobotOrientation - gyro.getHeading()) > 4) {


            distance = Math.hypot(distanceToXTarget, distanceToYTarget);
            distanceToXTarget = targetX - odometry.returnXCoordinate();
            distanceToYTarget = targetY - odometry.returnYCoordinate();

            robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, 1);
            double robotMovementYComponent = calculateY(robotMovementAngle, 1);

//            if(desiredRobotOrientation == -90) robotTurnAngle = Math.toDegrees(Math.atan2(0, distanceToYTarget));
            if(abs(distanceToYTarget) > abs(distanceToXTarget)) robotTurnAngle = Math.toDegrees(Math.atan2(distanceToYTarget, 0));
            else robotTurnAngle = Math.toDegrees(Math.atan2(distanceToXTarget, 0));
            //robotTurnAngle = 90;


//            double relativeAngleToTarget = angleWrapRadians(Math.toRadians(90) - (gyro.getHeadingRadians() - Math.toRadians(90) + desiredRobotOrientation));
            double relativeAngleToTarget = angleWrapDegrees(180 - gyro.getHeading()  + desiredRobotOrientation);
            double relativeTurnAngle = relativeAngleToTarget - 180;


            double turnPower =  relativeTurnAngle;
            if(turnPower < 0) turnPower = -0.4;
            else turnPower = 0.4;

            if(abs(desiredRobotOrientation - gyro.getHeading()) < 15) turnPower /= 2;



//            mecanumDrive(robotMovementXComponent, robotMovementYComponent, turnPower, Slomo);
            mecanumDrive(0, 0, turnPower, false);



//            tm.addData("Robot Movement Angle: ", robotMovementAngle);
//            tm.addData("Relative Angle To Target: ", Math.toDegrees(relativeAngleToTarget));
//            tm.addData("Relative Turn Angle: ", Math.toDegrees(relativeTurnAngle));
//            tm.addData("Turn Power: ", turnPower);
//            tm.addData("ANGLE: ", odometry.returnOrientation());
//            tm.update();

        }

        setMotorPowers(0, 0, 0, 0);
    }


    public double angleWrapRadians(double angle) {
        while (angle > 2 * PI ) {
            angle -= 2 * PI;
        } while(angle < 0){
            angle += 2 * PI;
        }

        return angle;
    }


    private double calculateX(double desiredAngle, double speed) {
        return Math.cos(Math.toRadians(desiredAngle)) * speed;
    }

    /**
     * Calculate the power in the y direction
     * @param desiredAngle angle on the y axis
     * @param speed robot's speed
     * @return the y vector
     */
    private double calculateY(double desiredAngle, double speed) {
        return Math.sin(Math.toRadians(desiredAngle)) * speed;
    }

    public double angleWrapDegrees(double angle) {
        while (angle > 360) {
            angle -= 360;
        } while(angle < 0){
            angle += 360;
        }

        return angle;
    }


}
