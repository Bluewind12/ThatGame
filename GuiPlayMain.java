import java.io.File;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GuiMain
 */
public class GuiPlayMain extends Application {

    Button computermodeButton = new Button("対CPU");
    Button pvpmodeButton = new Button("対人");
    Button recordButton = new Button("今までの戦績");
    Button endButton = new Button("終了");

    //VsCpu用
    int cpuLevel;
    int cpuChage;
    ImageView cpuStateImage = new ImageView("/stay.png");
    Text cpuChageText = new Text();

    int playerChage;
    ImageView playerStateImage = new ImageView("/stay.png");
    Text playerChageText = new Text();
    Button playerAttackButton = new Button("撃つ");
    Button playerChargeButton = new Button("ためる");
    Button playerGuardButton = new Button("防御");

    // 
    int p1_Chage;
    String p1_State;
    ImageView p1_StateImage = new ImageView("/stay.png");
    Text p1_ChageText = new Text();
    Button p1_AttackButton = new Button("撃つ");
    Button p1_ChargeButton = new Button("ためる");
    Button p1_GuardButton = new Button("防御");

    int p2_Chage;
    String p2_State;
    ImageView p2_StateImage = new ImageView("/stay.png");
    Text p2_ChageText = new Text();
    Button p2_AttackButton = new Button("撃つ");
    Button p2_ChargeButton = new Button("ためる");
    Button p2_GuardButton = new Button("防御");

    public void start(Stage stage) {
        int buttonHeight = 50;
        int buttonWidth = 125;
        int buttonTextSize = 15;
        int pictureSize = 150;

        stage.setTitle("That Game");
        stage.setHeight(600);
        stage.setWidth(600);

        VBox topPane = new VBox(20);
        topPane.setAlignment(Pos.CENTER);

        Group pictureGroup = new Group();

        HBox buttonlayoutBox = new HBox(20);
        buttonlayoutBox.setAlignment(Pos.CENTER);

        Text titleText = new Text("溜めたり撃ったり防御したりする・あれ");
        titleText.setFont(new Font(40));

        computermodeButton.setPrefSize(buttonWidth, buttonHeight);
        computermodeButton.setFont(new Font(buttonTextSize));
        computermodeButton.setOnAction(event -> vsCpuBattleStage());

        pvpmodeButton.setPrefSize(buttonWidth, buttonHeight);
        pvpmodeButton.setFont(new Font(buttonTextSize));
        pvpmodeButton.setOnAction(event -> vsPlayerBattleStage());

        recordButton.setPrefSize(buttonWidth, buttonHeight);
        recordButton.setFont(new Font(buttonTextSize));

        endButton.setPrefSize(buttonWidth, buttonHeight);
        endButton.setFont(new Font(buttonTextSize));
        endButton.setOnAction(event -> System.exit(0));

        ImageView chargeImage = new ImageView("/charge.png");
        chargeImage.setFitWidth(pictureSize);
        chargeImage.setFitHeight(pictureSize);
        chargeImage.setLayoutX(150);
        chargeImage.setLayoutY(0);

        ImageView attackImage = new ImageView("/attack.png");
        attackImage.setFitWidth(pictureSize);
        attackImage.setFitHeight(pictureSize);
        attackImage.setLayoutX(0);
        attackImage.setLayoutY(150);

        ImageView guardImage = new ImageView("/guard.png");
        guardImage.setFitWidth(pictureSize);
        guardImage.setFitHeight(pictureSize);
        guardImage.setLayoutX(300);
        guardImage.setLayoutY(150);

        pictureGroup.getChildren().addAll(chargeImage, attackImage, guardImage);
        buttonlayoutBox.getChildren().addAll(computermodeButton, pvpmodeButton, recordButton, endButton);
        topPane.getChildren().addAll(titleText, pictureGroup, buttonlayoutBox);

        Scene topScene = new Scene(topPane);
        stage.setScene(topScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public void stopButton() {
        computermodeButton.setDisable(true);
        pvpmodeButton.setDisable(true);
        recordButton.setDisable(true);
        endButton.setDisable(true);
    }

    public void restartButton() {
        computermodeButton.setDisable(false);
        pvpmodeButton.setDisable(false);
        recordButton.setDisable(false);
        endButton.setDisable(false);
    }

    public void vsCpuBattleStage() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        cpuChage = 0;
        playerChage = 0;

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("CPU　BATTLE");
        stage.setHeight(600);
        stage.setWidth(600);

        VBox battlePane = new VBox(10);
        battlePane.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        HBox buttonLayout = new HBox(15);
        buttonLayout.setAlignment(Pos.CENTER);

        //cpu側のGUI
        Text cpuText = new Text("CPU");//名前
        cpuText.setFont(new Font(40));
        cpuStateImage.setFitWidth(200);
        cpuStateImage.setFitHeight(200);
        setChargeText("cpu");
        cpuChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(cpuText, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(cpuStateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(cpuChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER);

        //仕切り線
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        Text vsText = new Text("VS");
        vsText.setFont(new Font(40));
        //グリッド配置
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(vsText, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        //PL側のGUI
        Text playerText = new Text("Player");//名前
        playerText.setFont(new Font(40));
        playerStateImage.setFitWidth(200);
        playerStateImage.setFitHeight(200);
        setChargeText("player");
        playerChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(playerText, 2, 2, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(playerStateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(playerChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        gridPane.getChildren().addAll(cpuText, cpuStateImage, cpuChageText, playerText, playerStateImage,
                playerChageText, adjustTexts[0], adjustTexts[1], vsText);

        //ボタン設定
        playerAttackButton.setPrefSize(buttonWidth, buttonHeight);
        playerAttackButton.setDisable(true);
        playerAttackButton.setOnAction(event -> attack());
        playerChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerChargeButton.setOnAction(event -> charge());
        playerGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerGuardButton.setOnAction(event -> guard());
        buttonLayout.getChildren().addAll(playerAttackButton, playerChargeButton, playerGuardButton);

        battlePane.getChildren().addAll(gridPane, buttonLayout);

        Scene battleScene = new Scene(battlePane);
        stage.setScene(battleScene);
        stage.show();
    }

    public void vsPlayerBattleStage() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        p1_Chage = 0;
        p2_Chage = 0;

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("Player　BATTLE");
        stage.setHeight(600);
        stage.setWidth(600);

        VBox battlePane = new VBox(10);
        battlePane.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        HBox p1_buttonLayout = new HBox(15);
        p1_buttonLayout.setAlignment(Pos.CENTER);
        HBox p2_buttonLayout = new HBox(15);
        p2_buttonLayout.setAlignment(Pos.CENTER);

        //p1_側のGUI
        Text p1_Text = new Text("Player1");//名前
        p1_Text.setFont(new Font(40));

        p1_StateImage.setFitWidth(200);
        p1_StateImage.setFitHeight(200);
        setChargeText("p1");
        p1_ChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(p1_Text, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(p1_StateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(p1_ChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER);

        //仕切り線
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        Button fightButton = new Button("VS");
        fightButton.setOnAction(event -> fight());
        //グリッド配置
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fightButton, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        //PL側のGUI
        Text p2_Text = new Text("Player2");//名前
        p2_Text.setFont(new Font(40));

        p2_StateImage.setFitWidth(200);
        p2_StateImage.setFitHeight(200);
        setChargeText("p2");
        p2_ChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(p2_Text, 2, 2, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(p2_StateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(p2_ChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        gridPane.getChildren().addAll(p1_Text, p1_StateImage, p1_ChageText, p2_Text, p2_StateImage, p2_ChageText,
                adjustTexts[0], adjustTexts[1], fightButton);

        //ボタン設定
        p1_AttackButton.setPrefSize(buttonWidth, buttonHeight);
        p1_AttackButton.setOnAction(event -> setState("p1", "attack"));
        p1_AttackButton.setDisable(true);
        p1_ChargeButton.setPrefSize(buttonWidth, buttonHeight);
        p1_ChargeButton.setOnAction(event -> setState("p1", "charge"));
        p1_GuardButton.setPrefSize(buttonWidth, buttonHeight);
        p1_GuardButton.setOnAction(event -> setState("p1", "guard"));
        p1_buttonLayout.getChildren().addAll(p1_AttackButton, p1_ChargeButton, p1_GuardButton);

        p2_AttackButton.setPrefSize(buttonWidth, buttonHeight);
        p2_AttackButton.setOnAction(event -> setState("p2", "attack"));
        p2_AttackButton.setDisable(true);
        p2_ChargeButton.setPrefSize(buttonWidth, buttonHeight);
        p2_ChargeButton.setOnAction(event -> setState("p2", "charge"));
        p2_GuardButton.setPrefSize(buttonWidth, buttonHeight);
        p2_GuardButton.setOnAction(event -> setState("p2", "guard"));
        p2_buttonLayout.getChildren().addAll(p2_AttackButton, p2_ChargeButton, p2_GuardButton);

        battlePane.getChildren().addAll(p1_buttonLayout, gridPane, p2_buttonLayout);

        Scene battleScene = new Scene(battlePane);
        stage.setScene(battleScene);
        stage.show();
    }

    private void setChargeText(String who) {
        switch (who) {
        case "cpu":
            cpuChageText.setText("ため:" + cpuChage);
            break;

        case "player":
            playerChageText.setText("ため:" + playerChage);
            break;

        case "p1":
            p1_ChageText.setText("ため:" + p1_Chage);
            break;

        case "p2":
            p2_ChageText.setText("ため:" + p2_Chage);
            break;
        }
    }

    private void attack() {
        playerChage--;
        if (playerChage <= 0) {
            playerAttackButton.setDisable(true);
        }

        //戦闘処理

        //
        crease("player", "attack");//仮設置
        setChargeText("cpu");
        setChargeText("player");
    }

    private void charge() {
        playerChage++;
        if (playerChage > 0) {
            playerAttackButton.setDisable(false);
        }
        //戦闘処理

        //
        crease("player", "charge");//仮設置
        setChargeText("cpu");
        setChargeText("player");
    }

    private void guard() {
        //戦闘処理

        //
        crease("player", "guard");//仮設置
        setChargeText("cpu");
        setChargeText("player");
    }

    private void fight() {
        //戦闘処理

        //
        crease("p1", p1_State);//仮設置
        crease("p2", p2_State);//仮設置
        setChargeText("p1");
        setChargeText("p2");
    }

    public void crease(String who, String state) {
        switch (who) {
        case "cpu":
            switch (state) {

            case "attack":
                cpuChage--;
                cpuStateImage.setImage(new Image("./attack.png"));
                break;
            case "charge":
                cpuChage++;
                cpuStateImage.setImage(new Image("./charge.png"));
                break;
            case "guard":
                cpuStateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;

        case "player":
            switch (state) {

            case "attack":
                playerStateImage.setImage(new Image("./attack.png"));

                break;
            case "charge":
                playerStateImage.setImage(new Image("./charge.png"));

                break;
            case "guard":
                playerStateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        case "p1":
            switch (state) {

            case "attack":
                p1_Chage--;
                p1_StateImage.setImage(new Image("./attack.png"));
                if (p1_Chage <= 0) {
                    p1_AttackButton.setDisable(true);
                    p1_State = "charge";
                }
                break;
            case "charge":
                p1_Chage++;
                p1_StateImage.setImage(new Image("./charge.png"));
                if (p1_Chage > 0) {
                    p1_AttackButton.setDisable(false);
                }
                break;
            case "guard":
                p1_StateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        case "p2":
            switch (state) {

            case "attack":
                p2_Chage--;
                p2_StateImage.setImage(new Image("./attack.png"));
                if (p2_Chage <= 0) {
                    p2_AttackButton.setDisable(true);
                    p2_State = "charge";
                }
                break;
            case "charge":
                p2_Chage++;
                p2_StateImage.setImage(new Image("./charge.png"));
                if (p2_Chage > 0) {
                    p2_AttackButton.setDisable(false);

                }
                break;
            case "guard":
                p2_StateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        }
    }

    private void setState(String who, String state) {
        switch (who) {
        case "p1":
            p1_State = state;
            System.out.println("P1:" + p1_State);//仮設置
            break;
        case "p2":
            p2_State = state;
            System.out.println("P2:" + p2_State);//仮設置
            break;
        }
    }
}