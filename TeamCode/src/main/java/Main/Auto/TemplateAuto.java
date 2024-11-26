package Main.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import Main.Base.AutoRobot;

public class TemplateAuto extends AutoRobot {

    double speed = 0.5;

    double error = 4;

    public void runAutonomous(){

        try {


        }catch(Exception e){
            autoThread.interrupt();
            telemetry.addData("AUTONOMOUS WAS INTERRUPTED", e);
        }
    }
}
