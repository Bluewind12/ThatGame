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
        // final 戦績のあれ kari = new 戦績のあれ();//仮（ジャッジ）
        //関数から数値を呼び出して頑張る
        int totalbattlenum = 0;
        int winbattlenum = 0;
        int losebattlenum = 0;

        Stage stage = new Stage();

        //画面の設定
        stage.setTitle("戦績");
        stage.setHeight(100);
        stage.setWidth(400);

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text("今までの戦績:");
        recoadTitleText.setFont(new Font(20));//フォントサイズ20

        Text recoadText = new Text();
        recoadText.setFont(new Font(15));//フォントサイズ15

        recoadText.setText("累計" + totalbattlenum + "戦" + winbattlenum + "勝" + losebattlenum + "敗");
        recoadLayout.getChildren().addAll(recoadTitleText, recoadText);
        //まとめ
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }
}