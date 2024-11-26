package Main.Base.Odometry;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Main.Base.Robot;


@Disabled
@TeleOp(name = "Odometry OpMode")
public class MyOdometryOpmode extends Robot {

    OdometryGlobalCoordinatePosition globalPositionUpdate;
    Thread positionThread;

    final double TICKS_PER_REV = 8192;
    final double WHEEL_DIAMETER = 38/25.4;

    final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER);


//    @Override
//    public void init() {
//
//
//
//
//    }

    @Override
    public void start() {

        //Create and start GlobalCoordinatePosition thread to constantly update the global coordinate positions
        globalPositionUpdate = new OdometryGlobalCoordinatePosition(left, right, mid, TICKS_PER_INCH, 75);
        positionThread = new Thread(globalPositionUpdate);
        positionThread.start();

        
        goToPosition(0, 24, 0.5, 0, 2);

    }


    @Override
    public void loop() {


        //Display Global (x, y, theta) coordinates
        telemetry.addData("X Position", globalPositionUpdate.returnXCoordinate() / TICKS_PER_INCH);
        telemetry.addData("Y Position", globalPositionUpdate.returnYCoordinate() / TICKS_PER_INCH);
        telemetry.addData("Orientation (Degrees)", globalPositionUpdate.returnOrientation());

        telemetry.addData("Vertical left encoder position", left.getCurrentPosition());
        telemetry.addData("Vertical right encoder position", right.getCurrentPosition());
        telemetry.addData("horizontal encoder position", mid.getCurrentPosition());

        telemetry.addData("Thread Active", positionThread.isAlive());
        telemetry.update();
    }

    @Override
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

        while(distance > error) { //|| Math.abs(desiredRobotOrientation - globalPositionUpdate.returnOrientation()) > 4) {

            //if(distance - error < 4*error) robotPower /= 4;

            distance = Math.hypot(distanceToXTarget, distanceToYTarget);
            distanceToXTarget = targetX - globalPositionUpdate.returnXCoordinate();
            distanceToYTarget = targetY - globalPositionUpdate.returnYCoordinate();

            double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));

            double robotMovementXComponent = calculateX(robotMovementAngle, robotPower);
            double robotMovementYComponent = calculateY(robotMovementAngle, robotPower);
            double pivotCorrection = desiredRobotOrientation - globalPositionUpdate.returnOrientation();

            if (pivotCorrection > 3) pivotCorrection = pivotCorrection / 180;
            else if(pivotCorrection < 3) pivotCorrection = pivotCorrection / 180;
            else pivotCorrection = 0;

            wheelBase.mecanumDrive(robotMovementXComponent, robotMovementYComponent, 0, false);



//            telemetry.addData("X: ", globalPositionUpdate.returnXCoordinate());
//            telemetry.addData("Y: ", globalPositionUpdate.returnYCoordinate());
//            telemetry.update();


        }

        wheelBase.setMotorPowers(0, 0, 0, 0);
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


