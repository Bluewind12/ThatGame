import java.util.Random;
import java.io.File;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        recordStageCreate();
    }

    private void recordStageCreate() {
        // final ��т̂��� kari = new ��т̂���();//���i�W���b�W�j
        //�֐����琔�l���Ăяo���Ċ撣��
        int totalbattlenum = 0;
        int winbattlenum = 0;
        int losebattlenum = 0;

        Stage stage = new Stage();

        //��ʂ̐ݒ�
        stage.setTitle("���");
        stage.setHeight(100);
        stage.setWidth(400);

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text("���܂ł̐��:");
        recoadTitleText.setFont(new Font(20));//�t�H���g�T�C�Y20

        Text recoadText = new Text();
        recoadText.setFont(new Font(15));//�t�H���g�T�C�Y15

        recoadText.setText("�݌v" + totalbattlenum + "��" + winbattlenum + "��" + losebattlenum + "�s");
        recoadLayout.getChildren().addAll(recoadTitleText, recoadText);
        //�܂Ƃ�
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }
}