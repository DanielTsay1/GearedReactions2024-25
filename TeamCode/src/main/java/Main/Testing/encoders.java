package Main.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class encoders extends LinearOpMode {
    DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motor = hardwareMap.dcMotor.get("viper");

        // You will need to set this based on your robot's
        // gearing to get forward control input to result in
        // forward motion.

        // reset encoder counts kept by motors.
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run forward for 5000 encoder counts.
        motor.setTargetPosition(-3100);

        // set motors to run to target encoder position and stop with brakes on.
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power. Movement will start. Sign of power is
        // ignored as sign of target encoder position controls direction when
        // running to position.

        motor.setPower(1.0);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && motor.isBusy())   //motor.getCurrentPosition() < motor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", motor.getCurrentPosition() + "  busy=" + motor.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        motor.setPower(0.0);

        // wait 5 sec to you can observe the final encoder position.

        resetRuntime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-end", motor.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // From current position back up to starting point. In this example instead of
        // having the motor monitor the encoder we will monitor the encoder ourselves.

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setTargetPosition(0);

        // Power sign matters again as we are running without encoder.
        motor.setPower(-0.25);

        while (opModeIsActive() && motor.getCurrentPosition() > motor.getTargetPosition())
        {
            telemetry.addData("encoder-back", motor.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        motor.setPower(0.0);

        resetRuntime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-left-end", motor.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }

}
