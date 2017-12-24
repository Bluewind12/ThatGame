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
    //メイン画面（ボタン）
    private Button computermodeButton = new Button("対CPU");
    private Button pvpmodeButton = new Button("対人");
    private Button recordButton = new Button("今までの戦績");
    private Button endButton = new Button("終了");

    //コンピュータ戦用
    //CPU（レベル、ため数、画像、ため数表示）
    private int cpuLevel;
    private static int cpuChage;
    private static ImageView cpuStateImage = new ImageView("/stay.png");
    private static Text cpuChageText = new Text();
    //プレイヤー（ため数、画像、ため数表示、ボタン）
    private int playerChage;
    private static ImageView playerStateImage = new ImageView("/stay.png");
    private static Text playerChageText = new Text();
    private static Button playerAttackButton = new Button("撃つ");
    private static Button playerChargeButton = new Button("ためる");
    private static Button playerGuardButton = new Button("防御");

    //対戦用
    //プレイヤー1（ため数、画像、ため数表示、ボタン）
    private int p1_Chage;
    private String p1_State;
    private static ImageView p1_StateImage = new ImageView("/stay.png");
    private static Text p1_ChageText = new Text();
    private static Button p1_AttackButton = new Button("撃つ");
    private static Button p1_ChargeButton = new Button("ためる");
    private static Button p1_GuardButton = new Button("防御");
    //プレイヤー2（ため数、画像、ため数表示、ボタン）
    private int p2_Chage;
    private String p2_State;
    private static ImageView p2_StateImage = new ImageView("/stay.png");
    private static Text p2_ChageText = new Text();
    private static Button p2_AttackButton = new Button("撃つ");
    private static Button p2_ChargeButton = new Button("ためる");
    private static Button p2_GuardButton = new Button("防御");

    /**
     * メイン画面の作成
     */
    public void start(Stage stage) {
        //要素の一括管理用
        final int buttonHeight = 50;
        final int buttonWidth = 125;
        final int buttonTextSize = 15;
        final int pictureSize = 150;

        //タイトル、サイズ管理
        stage.setTitle("That Game");
        stage.setHeight(600);
        stage.setWidth(600);

        //全体まとめ用レイアウト
        VBox topPane = new VBox(20); //縦向き
        topPane.setAlignment(Pos.CENTER); //中央揃え

        //画像配置用レイアウト
        Group pictureGroup = new Group();

        //選択用ボタンレイアウト
        HBox buttonlayoutBox = new HBox(20);//横向き
        buttonlayoutBox.setAlignment(Pos.CENTER);//中央揃え

        //タイトル
        Text titleText = new Text("溜めたり撃ったり防御したりする・アレ");
        titleText.setFont(new Font(40));//フォントサイズ40

        //ボタン作成、イベント設定
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

        //画像設定
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

        //レイアウトにそれぞれ設置
        pictureGroup.getChildren().addAll(chargeImage, attackImage, guardImage);
        buttonlayoutBox.getChildren().addAll(computermodeButton, pvpmodeButton, recordButton, endButton);
        topPane.getChildren().addAll(titleText, pictureGroup, buttonlayoutBox);

        //ステージに設置
        Scene topScene = new Scene(topPane);
        stage.setScene(topScene);
        stage.show();

    }

    /**
     * 起動時
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * メイン画面のボタンをすべて押せなくする
     */
    private void stopButton() {
        computermodeButton.setDisable(true);
        pvpmodeButton.setDisable(true);
        recordButton.setDisable(true);
        endButton.setDisable(true);
    }

    /**
     * メイン画面のボタンをすべて押せるようにする
     */
    private void restartButton() {
        computermodeButton.setDisable(false);
        pvpmodeButton.setDisable(false);
        recordButton.setDisable(false);
        endButton.setDisable(false);
    }

    /**
     * 対コンピュータ用
     * 画面作成、ボタン設定など
     */
    private void vsCpuBattleStage() {

        final Provisional kari = new Provisional();
        stopButton();//メイン画面のボタン停止

        Stage stage = new Stage();

        //レイアウト用一括管理定数
        final int buttonHeight = 50;
        final int buttonWidth = 125;

        //ため数の初期化
        cpuChage = 0;
        playerChage = 0;

        //画像の初期化
        cpuStateImage.setImage(new Image("./stay.png"));
        playerStateImage.setImage(new Image("./stay.png"));

        //画面の設定
        stage.setTitle("CPU　BATTLE");
        stage.setHeight(600);
        stage.setWidth(600);
        //×ボタンが押されたときの動作設定
        stage.setOnCloseRequest(event -> restartButton());

        //全体まとめ用レイアウト
        VBox battlePane = new VBox(10); //縦向き
        battlePane.setAlignment(Pos.CENTER); //中央揃え

        //戦闘状況レイアウト
        GridPane gridPane = new GridPane(); //グリッドレイアウト
        gridPane.setAlignment(Pos.CENTER); //中央揃え
        gridPane.setHgap(10); //幅設定(横)
        gridPane.setVgap(10); //幅設定(縦)

        //ボタン配置用レイアウト
        HBox buttonLayout = new HBox(15);//横向き
        buttonLayout.setAlignment(Pos.CENTER);//中央揃え

        //cpu側のGUI
        Text cpuText = new Text("CPU");//名前
        cpuText.setFont(new Font(40));
        cpuStateImage.setFitWidth(200);
        cpuStateImage.setFitHeight(200);
        setChargeText("cpu");
        cpuChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(cpuText, 0, 0, 1, 1, HPos.CENTER, VPos.TOP); //横:中央 縦:上
        GridPane.setConstraints(cpuStateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER); //横:中央 縦:中央
        GridPane.setConstraints(cpuChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER); //横:左 縦:中央

        //仕切り線
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        Text vsText = new Text("VS");
        vsText.setFont(new Font(40));

        //グリッド配置
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER); //横:中央 縦:中央
        GridPane.setConstraints(vsText, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER); //横:中央 縦:中央
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER); //横:中央 縦:中央

        //PL側のGUI
        Text playerText = new Text("Player");//名前
        playerText.setFont(new Font(40));
        playerStateImage.setFitWidth(200);
        playerStateImage.setFitHeight(200);
        setChargeText("player");
        playerChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(playerText, 2, 2, 1, 1, HPos.CENTER, VPos.TOP); //横:中央 縦:上
        GridPane.setConstraints(playerStateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER); //横:中央 縦:中央
        GridPane.setConstraints(playerChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER); //横:左 縦:中央

        //レイアウト設置
        gridPane.getChildren().addAll(cpuText, cpuStateImage, cpuChageText, playerText, playerStateImage,
                playerChageText, adjustTexts[0], adjustTexts[1], vsText);

        //ボタン設定
        playerAttackButton.setPrefSize(buttonWidth, buttonHeight);
        playerAttackButton.setDisable(true); //初期はため数0の為、押せないようにあらかじめしておく
        playerAttackButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "attack"));
        playerChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerChargeButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "charge"));
        playerGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerGuardButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "guard"));
        buttonLayout.getChildren().addAll(playerAttackButton, playerChargeButton, playerGuardButton);

        //全体まとめ
        battlePane.getChildren().addAll(gridPane, buttonLayout);
        Scene battleScene = new Scene(battlePane);
        stage.setScene(battleScene);
        stage.show();
    }

    /**
     * 対人用
     * 画面作成、ボタン設定など
     * 内容はほとんどコンピュータ戦用と変わりない
     * 
     * 相違点
     * ・上下のボタン設置
     * ・VSテキスト　→　fightボタンの設置
     */
    private void vsPlayerBattleStage() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        //ため数の初期化
        p1_Chage = 0;
        p2_Chage = 0;
        //画像の初期化
        p1_StateImage.setImage(new Image("./stay.png"));
        p2_StateImage.setImage(new Image("./stay.png"));

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

    /**
     * ため数の変化をテキスト上に出力する
     * CUI,GUIともに出力
     * CUIは確認用
     */
    protected void setChargeText(String who) {
        switch (who) {
        case "cpu":
            System.out.println(who + "：ため：" + cpuChage);
            cpuChageText.setText("ため:" + cpuChage);
            break;

        case "player":
            System.out.println(who + "：ため：" + playerChage);
            playerChageText.setText("ため:" + playerChage);
            break;

        case "p1":
            System.out.println(who + "：ため：" + p1_Chage);
            p1_ChageText.setText("ため:" + p1_Chage);
            break;

        case "p2":
            System.out.println(who + "：ため：" + p2_Chage);
            p2_ChageText.setText("ため:" + p2_Chage);
            break;
        }
    }

    /**
     * 画像の変更、ため数の増減を行う
     * @param who どのプレイヤーに対しての動きなのか
     * @param state どの動作の動きをさせるのか
     */
    protected void changeState(String who, String state) {
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
                playerChage--;
                if (playerChage <= 0) {
                    playerAttackButton.setDisable(true);
                }
                playerStateImage.setImage(new Image("./attack.png"));
                System.out.println("?????");

                break;
            case "charge":
                playerChage++;
                if (playerChage > 0) {
                    playerAttackButton.setDisable(false);
                }
                playerStateImage.setImage(new Image("./charge.png"));
                System.out.println("?????");
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

    /**
     * 対人戦用
     * ファイトボタンが押されたときに処理を開始する
     */
    private void fight() {
        //戦闘処理

        //
        changeState("p1", p1_State);//仮設置：実際には処理で呼び出される
        changeState("p2", p2_State);//仮設置：実際には処理で呼び出される
        setChargeText("p1");
        setChargeText("p2");
    }

    /**
     * 対人戦用
     * 現在の選択を設定しておく
     * @param who どのプレイヤーの選択なのか
     * @param state どの動作を選択したのか
     */
    private void setState(String who, String state) {
        switch (who) {
        case "p1":
            p1_State = state;
            System.out.println("P1:" + p1_State);//仮設置：実際には消す
            break;
        case "p2":
            p2_State = state;
            System.out.println("P2:" + p2_State);//仮設置：実際には消す
            break;
        }
    }
}