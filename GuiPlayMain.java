
//1601776 2EP4-58 MomoseNaoto
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
 * GuiMain
 */
public class GuiPlayMain extends Application {
    //メイン画面（ボタン）
    private Button computermodeButton = new Button("対CPU");
    private Button pvpmodeButton = new Button("対人");
    private Button recordButton = new Button("今までの戦績");
    private Button endButton = new Button("終了");
    //メイン画面（スライダー）
    private static Slider levelSlider = new Slider(0, 2, 1);

    //コンピュータ戦用
    //CPU（レベル、ため数、画像、ため数表示）
    private int cpuLevel;
    private static int cpuChage;
    private String cpuState;
    private static ImageView cpuStateImage = new ImageView("./stay.png");
    private static Text cpuChageText = new Text();
    //プレイヤー（ため数、画像、ため数表示、ボタン）
    private int playerChage;
    private static ImageView playerStateImage = new ImageView("./stay.png");
    private static Text playerChageText = new Text();
    private static Button playerAttackButton = new Button("撃つ");
    private static Button playerChargeButton = new Button("ためる");
    private static Button playerGuardButton = new Button("防御");

    //対戦用
    //プレイヤー1（ため数、画像、ため数表示、ボタン）
    private int playerOneChage;
    private String playerOneState;
    private static ImageView playerOneStateImage = new ImageView("./stay.png");
    private static Text playerOneChageText = new Text();
    private static Button playerOneAttackButton = new Button("撃つ");
    private static Button playerOneChargeButton = new Button("ためる");
    private static Button playerOneGuardButton = new Button("防御");
    //プレイヤー2（ため数、画像、ため数表示、ボタン）
    private int playerTwoChage;
    private String playerTwoState;
    private static ImageView playerTwoStateImage = new ImageView("./stay.png");
    private static Text playerTwoChageText = new Text();
    private static Button playerTwoAttackButton = new Button("撃つ");
    private static Button playerTwoChargeButton = new Button("ためる");
    private static Button playerTwoGuardButton = new Button("防御");
    private Button fightButton = new Button("VS");

    private BattleJudge judge = new BattleJudge();
    private VsCpuMode cpuMode = new VsCpuMode();
    private RecordManage record = new RecordManage();
    private VsPlayerResult result = new VsPlayerResult();

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
        stage.setTitle("寿司じゃんけん");
        stage.setHeight(750);
        stage.setWidth(750);

        //全体まとめ用レイアウト
        VBox topPane = new VBox(20); //縦向き
        topPane.setAlignment(Pos.CENTER); //中央揃え

        //画像配置用レイアウト
        Group pictureGroup = new Group();

        //スライダー用
        HBox sliderBox = new HBox(50);
        sliderBox.setAlignment(Pos.CENTER);

        //選択用ボタンレイアウト
        HBox buttonlayoutBox = new HBox(20);//横向き
        buttonlayoutBox.setAlignment(Pos.CENTER);//中央揃え

        //タイトル
        Text titleText = new Text("☆寿司じゃんけん☆");
        titleText.setFont(new Font(60));//フォントサイズ40

        //スライダー説明
        Text levelText = new Text("CPUlevel:");
        levelText.setFont(new Font(20));//フォントサイズ40

        //スライダー
        levelSlider.setSnapToTicks(true);
        levelSlider.setMajorTickUnit(1);
        levelSlider.setMinorTickCount(0);
        levelSlider.setShowTickLabels(true);

        //ボタン作成、イベント設定
        computermodeButton.setPrefSize(buttonWidth, buttonHeight);
        computermodeButton.setFont(new Font(buttonTextSize));
        computermodeButton.setOnAction(event -> vsCpuBattleStageCreate());

        pvpmodeButton.setPrefSize(buttonWidth, buttonHeight);
        pvpmodeButton.setFont(new Font(buttonTextSize));
        pvpmodeButton.setOnAction(event -> vsPlayerBattleStageCreate());

        recordButton.setPrefSize(buttonWidth, buttonHeight);
        recordButton.setFont(new Font(buttonTextSize));
        recordButton.setOnAction(event -> recordStageCreate());

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
        sliderBox.getChildren().addAll(levelText, levelSlider);
        topPane.getChildren().addAll(titleText, pictureGroup, sliderBox, buttonlayoutBox);

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
    * CPU対戦時のボタン停止
    */
    public void stopCpuButton() {
        playerChargeButton.setDisable(true);
        playerAttackButton.setDisable(true);
        playerGuardButton.setDisable(true);
    }

    /**
    * ファイト停止
    */
    public void stopVsButton() {
        fightButton.setDisable(true);
    }

    /**
     * 対コンピュータ用
     * 画面作成、ボタン設定など
     */
    private void vsCpuBattleStageCreate() {
        stopButton();//メイン画面のボタン停止

        Stage stage = new Stage();

        //レイアウト用一括管理定数
        final int buttonHeight = 50;
        final int buttonWidth = 125;

        //初期設定
        cpuChage = 0;
        playerChage = 0;

        //CPULevelの取得
        cpuLevel = (int) levelSlider.getValue();
        System.out.println("cpulevel : " + cpuLevel); //あとで消す

        //画像の初期化
        cpuStateImage.setImage(new Image("./stay.png"));
        playerStateImage.setImage(new Image("./stay.png"));

        //画面の設定
        stage.setTitle("CPU　BATTLE");
        stage.setHeight(750);
        stage.setWidth(750);
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
        playerAttackButton.setOnAction(event -> fightCpu("attack"));
        playerChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerChargeButton.setDisable(false);
        playerChargeButton.setOnAction(event -> fightCpu("charge"));
        playerGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerGuardButton.setDisable(false);
        playerGuardButton.setOnAction(event -> fightCpu("guard"));
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
    private void vsPlayerBattleStageCreate() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        //初期設定
        playerOneChage = 0;
        playerTwoChage = 0;
        playerOneState = "charge";
        playerTwoState = "charge";
        //画像の初期化
        playerOneStateImage.setImage(new Image("./stay.png"));
        playerTwoStateImage.setImage(new Image("./stay.png"));

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("Player　BATTLE");
        stage.setHeight(750);
        stage.setWidth(750);

        VBox battlePane = new VBox(10);
        battlePane.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        HBox playerOnebuttonLayout = new HBox(15);
        playerOnebuttonLayout.setAlignment(Pos.CENTER);
        HBox playerTwobuttonLayout = new HBox(15);
        playerTwobuttonLayout.setAlignment(Pos.CENTER);

        //playerOne側のGUI
        Text playerOneText = new Text("Player1");//名前
        playerOneText.setFont(new Font(40));

        playerOneStateImage.setFitWidth(200);
        playerOneStateImage.setFitHeight(200);
        setChargeText("playerOne");
        playerOneChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(playerOneText, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(playerOneStateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(playerOneChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER);

        //仕切り線
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        fightButton.setOnAction(event -> fightPlayer());
        fightButton.setDisable(false);
        //グリッド配置
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fightButton, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        //PL側のGUI
        Text playerTwoText = new Text("Player2");//名前
        playerTwoText.setFont(new Font(40));

        playerTwoStateImage.setFitWidth(200);
        playerTwoStateImage.setFitHeight(200);
        setChargeText("playerTwo");
        playerTwoChageText.setFont(new Font(20));

        //グリッド配置
        GridPane.setConstraints(playerTwoText, 2, 2, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(playerTwoStateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(playerTwoChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        gridPane.getChildren().addAll(playerOneText, playerOneStateImage, playerOneChageText, playerTwoText,
                playerTwoStateImage, playerTwoChageText, adjustTexts[0], adjustTexts[1], fightButton);

        //ボタン設定
        playerOneAttackButton.setPrefSize(buttonWidth, buttonHeight);
        playerOneAttackButton.setOnAction(event -> setState("playerOne", "attack"));
        playerOneAttackButton.setDisable(true);
        playerOneChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerOneChargeButton.setOnAction(event -> setState("playerOne", "charge"));
        playerOneGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerOneGuardButton.setOnAction(event -> setState("playerOne", "guard"));
        playerOnebuttonLayout.getChildren().addAll(playerOneAttackButton, playerOneChargeButton, playerOneGuardButton);

        playerTwoAttackButton.setPrefSize(buttonWidth, buttonHeight);
        playerTwoAttackButton.setOnAction(event -> setState("playerTwo", "attack"));
        playerTwoAttackButton.setDisable(true);
        playerTwoChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerTwoChargeButton.setOnAction(event -> setState("playerTwo", "charge"));
        playerTwoGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerTwoGuardButton.setOnAction(event -> setState("playerTwo", "guard"));
        playerTwobuttonLayout.getChildren().addAll(playerTwoAttackButton, playerTwoChargeButton, playerTwoGuardButton);

        battlePane.getChildren().addAll(playerOnebuttonLayout, gridPane, playerTwobuttonLayout);

        Scene battleScene = new Scene(battlePane);
        stage.setScene(battleScene);
        stage.show();
    }

    /**
     * ため数の変化をテキスト上に出力する
     * CUI,GUIともに出力
     * CUIは確認用
     */
    private void setChargeText(String who) {
        switch (who) {
        case "cpu":
            System.out.println(who + "：ため：" + cpuChage);
            cpuChageText.setText("ため:" + cpuChage);
            break;

        case "player":
            System.out.println(who + "：ため：" + playerChage);
            playerChageText.setText("ため:" + playerChage);
            break;

        case "playerOne":
            System.out.println(who + "：ため：" + playerOneChage);
            playerOneChageText.setText("ため:" + playerOneChage);
            break;

        case "playerTwo":
            System.out.println(who + "：ため：" + playerTwoChage);
            playerTwoChageText.setText("ため:" + playerTwoChage);
            break;
        }
    }

    /**
     * 画像の変更、ため数の増減を行う
     * @param who どのプレイヤーに対しての動きなのか
     * @param state どの動作の動きをさせるのか
     */
    private void changeState(String who, String state) {
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
                break;
            case "charge":
                playerChage++;
                if (playerChage > 0) {
                    playerAttackButton.setDisable(false);
                }
                playerStateImage.setImage(new Image("./charge.png"));
                break;
            case "guard":
                playerStateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        case "playerOne":
            switch (state) {

            case "attack":
                playerOneChage--;
                playerOneStateImage.setImage(new Image("./attack.png"));
                if (playerOneChage <= 0) {
                    playerOneAttackButton.setDisable(true);
                    playerOneState = "charge";
                }
                break;
            case "charge":
                playerOneChage++;
                playerOneStateImage.setImage(new Image("./charge.png"));
                if (playerOneChage > 0) {
                    playerOneAttackButton.setDisable(false);
                }
                break;
            case "guard":
                playerOneStateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        case "playerTwo":
            switch (state) {

            case "attack":
                playerTwoChage--;
                playerTwoStateImage.setImage(new Image("./attack.png"));
                if (playerTwoChage <= 0) {
                    playerTwoAttackButton.setDisable(true);
                    playerTwoState = "charge";
                }
                break;
            case "charge":
                playerTwoChage++;
                playerTwoStateImage.setImage(new Image("./charge.png"));
                if (playerTwoChage > 0) {
                    playerTwoAttackButton.setDisable(false);

                }
                break;
            case "guard":
                playerTwoStateImage.setImage(new Image("./guard.png"));
                break;
            }
            break;
        }
    }

    /**
     * 対CPU戦用
     * ボタンが押されたときに処理を開始する
     * @param playerState プレイヤーの選択した行動
     */
    private void fightCpu(String playerState) {
        //戦闘処理
        int judgeState = 0;

        setState("cpu", cpuMode.cpuThink(cpuChage, cpuLevel, playerChage));
        judgeState = judge.battleJudge(cpuState, playerState);
        cpuMode.battleResultStageCreate(judgeState);
        //画像、テキストの切り替え
        changeState("player", playerState);
        changeState("cpu", cpuState);
        setChargeText("player");
        setChargeText("cpu");
        //戦闘終了条件を満たしている場合、ボタンをすべて停止
        if (0 != judgeState) {
            stopCpuButton();
            record.changeRecord(judgeState);//戦績の書き換え
        }

    }

    /**
     * 対人戦用
     * ファイトボタンが押されたときに処理を開始する
     */
    private void fightPlayer() {
        //戦闘処理
        BattleJudge judge = new BattleJudge();
        //画像、テキストの切り替え
        result.battleResultStageCreate(judge.battleJudge(playerOneState, playerTwoState));
        changeState("playerOne", playerOneState);
        changeState("playerTwo", playerTwoState);
        setChargeText("playerOne");
        setChargeText("playerTwo");
        //戦闘終了条件を満たしている場合、ボタンをすべて停止
        if (0 != judge.battleJudge(playerOneState, playerTwoState)) {
            stopVsButton();
        }
    }

    /**
     * 対人戦用
     * 現在の選択を設定しておく
     * @param who どのプレイヤーの選択なのか
     * @param state どの動作を選択したのか
     */
    private void setState(String who, String state) {
        switch (who) {
        case "cpu":
            cpuState = state;
            System.out.println("Cpu:" + cpuState);
            break;
        case "playerOne":
            playerOneState = state;
            System.out.println("playerOne:" + playerOneState);
            break;
        case "playerTwo":
            playerTwoState = state;
            System.out.println("playerTwo:" + playerTwoState);
            break;
        }
    }

    private void recordStageCreate() {
        //関数から数値を呼び出す
        int totalbattlenum = record.getTotalBattleTimes();
        int winbattlenum = record.getTotalWinTimes();
        int losebattlenum = record.getTotalLoseTimes();

        stopButton();//メイン画面のボタン停止

        Stage stage = new Stage();

        //画面の設定
        stage.setTitle("戦績");
        stage.setHeight(100);
        stage.setWidth(400);
        //×ボタンが押されたときの動作設定
        stage.setOnCloseRequest(event -> restartButton());

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text("今までの戦績:");
        recoadTitleText.setFont(new Font(20));//フォントサイズ20

        Text recoadText = new Text();
        recoadText.setFont(new Font(25));//フォントサイズ15

        recoadText.setText("累計" + totalbattlenum + "戦" + winbattlenum + "勝" + losebattlenum + "敗");
        recoadLayout.getChildren().addAll(recoadTitleText, recoadText);
        //まとめ
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }
}