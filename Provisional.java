import java.util.Random;

/**
 * Provisional
 */
public class Provisional extends GuiPlayMain {
    public void provisional(int cpuChage, int cpuLevel, int playerChage, String playerState) {
        String cpuState;
        Random rnd = new Random();
        int ran;
        if (cpuChage <= 0) {
            ran = rnd.nextInt(2);
        } else {
            ran = rnd.nextInt(4);
        }

        if (ran == 0) {
            cpuState = "guard";
        } else if (ran == 1) {
            cpuState = "charge";
        } else {
            cpuState = "attack";
        }
        changeState("player", playerState);
        changeState("cpu", cpuState);
        setChargeText("player");
        setChargeText("cpu");
    }
}