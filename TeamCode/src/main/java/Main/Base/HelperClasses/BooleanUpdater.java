package Main.Base.HelperClasses;



import static Main.Base.HelperClasses.Button.a;
import static Main.Base.HelperClasses.Button.b;
import static Main.Base.HelperClasses.Button.back;
import static Main.Base.HelperClasses.Button.dpad_down;
import static Main.Base.HelperClasses.Button.dpad_left;
import static Main.Base.HelperClasses.Button.dpad_right;
import static Main.Base.HelperClasses.Button.dpad_up;
import static Main.Base.HelperClasses.Button.left_bumper;
import static Main.Base.HelperClasses.Button.left_stick_button;
import static Main.Base.HelperClasses.Button.left_trigger;
import static Main.Base.HelperClasses.Button.right_bumper;
import static Main.Base.HelperClasses.Button.right_stick_button;
import static Main.Base.HelperClasses.Button.right_trigger;
import static Main.Base.HelperClasses.Button.start;
import static Main.Base.HelperClasses.Button.x;
import static Main.Base.HelperClasses.Button.y;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class BooleanUpdater {



    public static boolean lastA = false, lastB = false, lastX = false, lastY = false, lastRB = false, lastLB = false, lastRT = false, lastLT = false;
    public static boolean lastDLeft = false, lastDRight = false, lastDUp = false, lastDDown = false;
    public static boolean lastStart = false, lastBack = false;
    public static boolean lastLS = false, lastRS = false;

    public static boolean pressA = false, pressB = false, pressX = false, pressY = false, pressRB = false, pressLB = false, pressRT = false, pressLT = false;
    public static boolean pressDLeft = false, pressDRight = false, pressDUp = false, pressDDown = false;
    public static boolean pressStart = false, pressBack = false;
    public static boolean pressLS = false, pressRS = false;


    public static HashMap<Button, Boolean> updateBooleans(Gamepad gp, HashMap<Button, Boolean> hm) {



        pressA = gp.a && !lastA;

        lastA = gp.a;

        hm.put(a, pressA);


        pressB = gp.b && !lastB;

        lastB = gp.b;

        hm.put(b, pressB);


        pressX = gp.x && !lastX;

        lastX = gp.x;

        hm.put(x, pressX);


        pressY = gp.y && !lastY;

        lastY = gp.y;

        hm.put(y, pressY);


        pressLB = gp.left_bumper && !lastLB;

        lastLB = gp.left_bumper;

        hm.put(left_bumper, pressLB);


        pressRB = gp.right_bumper && !lastRB;

        lastRB = gp.right_bumper;

        hm.put(right_bumper, pressRB);


        pressLT = gp.left_trigger > 0.1 && !lastLT;

        lastLT = gp.left_trigger > 0.1;

        hm.put(left_trigger, pressLT);


        pressRT = gp.right_trigger > 0.1 && !lastRT;

        lastRT = gp.right_trigger > 0.1;

        hm.put(right_trigger, pressRT);


        pressDUp = gp.dpad_up && !lastDUp;

        lastDUp = gp.dpad_up;

        hm.put(dpad_up, pressDUp);


        pressDDown = gp.dpad_down && !lastDDown;

        lastDDown = gp.dpad_down;

        hm.put(dpad_down, pressDDown);


        pressDLeft = gp.dpad_left && !lastDLeft;

        lastDLeft = gp.dpad_left;

        hm.put(dpad_left, pressDLeft);


        pressDRight = gp.dpad_right && !lastDRight;

        lastDRight = gp.dpad_right;

        hm.put(dpad_right, pressDRight);


        pressStart = gp.start && !lastStart;

        lastStart = gp.start;

        hm.put(start, pressStart);


        pressBack = gp.back && !lastBack;

        lastBack = gp.back;

        hm.put(back, pressBack);


        pressLS = gp.left_stick_button && !lastLS;

        lastLS = gp.left_stick_button;

        hm.put(left_stick_button, pressLS);


        pressRS = gp.right_stick_button && !lastRS;

        lastRS = gp.right_stick_button;

        hm.put(right_stick_button, pressRS);



        return hm;
    }
}
