
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
    //���C����ʁi�{�^���j
    private Button computermodeButton = new Button("��CPU");
    private Button pvpmodeButton = new Button("�ΐl");
    private Button recordButton = new Button("���܂ł̐��");
    private Button endButton = new Button("�I��");
    //���C����ʁi�X���C�_�[�j
    private static Slider levelSlider = new Slider(0, 2, 1);

    //�R���s���[�^��p
    //CPU�i���x���A���ߐ��A�摜�A���ߐ��\���j
    private int cpuLevel;
    private static int cpuChage;
    private String cpuState;
    private static ImageView cpuStateImage = new ImageView("./stay.png");
    private static Text cpuChageText = new Text();
    //�v���C���[�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int playerChage;
    private static ImageView playerStateImage = new ImageView("./stay.png");
    private static Text playerChageText = new Text();
    private static Button playerAttackButton = new Button("����");
    private static Button playerChargeButton = new Button("���߂�");
    private static Button playerGuardButton = new Button("�h��");

    //�ΐ�p
    //�v���C���[1�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int playerOneChage;
    private String playerOneState;
    private static ImageView playerOneStateImage = new ImageView("./stay.png");
    private static Text playerOneChageText = new Text();
    private static Button playerOneAttackButton = new Button("����");
    private static Button playerOneChargeButton = new Button("���߂�");
    private static Button playerOneGuardButton = new Button("�h��");
    //�v���C���[2�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int playerTwoChage;
    private String playerTwoState;
    private static ImageView playerTwoStateImage = new ImageView("./stay.png");
    private static Text playerTwoChageText = new Text();
    private static Button playerTwoAttackButton = new Button("����");
    private static Button playerTwoChargeButton = new Button("���߂�");
    private static Button playerTwoGuardButton = new Button("�h��");
    private Button fightButton = new Button("VS");

    private BattleJudge judge = new BattleJudge();
    private VsCpuMode cpuMode = new VsCpuMode();
    private RecordManage record = new RecordManage();
    private VsPlayerResult result = new VsPlayerResult();

    /**
     * ���C����ʂ̍쐬
     */
    public void start(Stage stage) {
        //�v�f�̈ꊇ�Ǘ��p
        final int buttonHeight = 50;
        final int buttonWidth = 125;
        final int buttonTextSize = 15;
        final int pictureSize = 150;

        //�^�C�g���A�T�C�Y�Ǘ�
        stage.setTitle("���i����񂯂�");
        stage.setHeight(750);
        stage.setWidth(750);

        //�S�̂܂Ƃߗp���C�A�E�g
        VBox topPane = new VBox(20); //�c����
        topPane.setAlignment(Pos.CENTER); //��������

        //�摜�z�u�p���C�A�E�g
        Group pictureGroup = new Group();

        //�X���C�_�[�p
        HBox sliderBox = new HBox(50);
        sliderBox.setAlignment(Pos.CENTER);

        //�I��p�{�^�����C�A�E�g
        HBox buttonlayoutBox = new HBox(20);//������
        buttonlayoutBox.setAlignment(Pos.CENTER);//��������

        //�^�C�g��
        Text titleText = new Text("�����i����񂯂�");
        titleText.setFont(new Font(60));//�t�H���g�T�C�Y40

        //�X���C�_�[����
        Text levelText = new Text("CPUlevel:");
        levelText.setFont(new Font(20));//�t�H���g�T�C�Y40

        //�X���C�_�[
        levelSlider.setSnapToTicks(true);
        levelSlider.setMajorTickUnit(1);
        levelSlider.setMinorTickCount(0);
        levelSlider.setShowTickLabels(true);

        //�{�^���쐬�A�C�x���g�ݒ�
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

        //�摜�ݒ�
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

        //���C�A�E�g�ɂ��ꂼ��ݒu
        pictureGroup.getChildren().addAll(chargeImage, attackImage, guardImage);
        buttonlayoutBox.getChildren().addAll(computermodeButton, pvpmodeButton, recordButton, endButton);
        sliderBox.getChildren().addAll(levelText, levelSlider);
        topPane.getChildren().addAll(titleText, pictureGroup, sliderBox, buttonlayoutBox);

        //�X�e�[�W�ɐݒu
        Scene topScene = new Scene(topPane);
        stage.setScene(topScene);
        stage.show();

    }

    /**
     * �N����
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * ���C����ʂ̃{�^�������ׂĉ����Ȃ�����
     */
    private void stopButton() {
        computermodeButton.setDisable(true);
        pvpmodeButton.setDisable(true);
        recordButton.setDisable(true);
        endButton.setDisable(true);
    }

    /**
     * ���C����ʂ̃{�^�������ׂĉ�����悤�ɂ���
     */
    private void restartButton() {
        computermodeButton.setDisable(false);
        pvpmodeButton.setDisable(false);
        recordButton.setDisable(false);
        endButton.setDisable(false);
    }

    /**
    * CPU�ΐ펞�̃{�^����~
    */
    public void stopCpuButton() {
        playerChargeButton.setDisable(true);
        playerAttackButton.setDisable(true);
        playerGuardButton.setDisable(true);
    }

    /**
    * �t�@�C�g��~
    */
    public void stopVsButton() {
        fightButton.setDisable(true);
    }

    /**
     * �΃R���s���[�^�p
     * ��ʍ쐬�A�{�^���ݒ�Ȃ�
     */
    private void vsCpuBattleStageCreate() {
        stopButton();//���C����ʂ̃{�^����~

        Stage stage = new Stage();

        //���C�A�E�g�p�ꊇ�Ǘ��萔
        final int buttonHeight = 50;
        final int buttonWidth = 125;

        //�����ݒ�
        cpuChage = 0;
        playerChage = 0;

        //CPULevel�̎擾
        cpuLevel = (int) levelSlider.getValue();
        System.out.println("cpulevel : " + cpuLevel); //���Ƃŏ���

        //�摜�̏�����
        cpuStateImage.setImage(new Image("./stay.png"));
        playerStateImage.setImage(new Image("./stay.png"));

        //��ʂ̐ݒ�
        stage.setTitle("CPU�@BATTLE");
        stage.setHeight(750);
        stage.setWidth(750);
        //�~�{�^���������ꂽ�Ƃ��̓���ݒ�
        stage.setOnCloseRequest(event -> restartButton());

        //�S�̂܂Ƃߗp���C�A�E�g
        VBox battlePane = new VBox(10); //�c����
        battlePane.setAlignment(Pos.CENTER); //��������

        //�퓬�󋵃��C�A�E�g
        GridPane gridPane = new GridPane(); //�O���b�h���C�A�E�g
        gridPane.setAlignment(Pos.CENTER); //��������
        gridPane.setHgap(10); //���ݒ�(��)
        gridPane.setVgap(10); //���ݒ�(�c)

        //�{�^���z�u�p���C�A�E�g
        HBox buttonLayout = new HBox(15);//������
        buttonLayout.setAlignment(Pos.CENTER);//��������

        //cpu����GUI
        Text cpuText = new Text("CPU");//���O
        cpuText.setFont(new Font(40));
        cpuStateImage.setFitWidth(200);
        cpuStateImage.setFitHeight(200);
        setChargeText("cpu");
        cpuChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(cpuText, 0, 0, 1, 1, HPos.CENTER, VPos.TOP); //��:���� �c:��
        GridPane.setConstraints(cpuStateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER); //��:���� �c:����
        GridPane.setConstraints(cpuChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER); //��:�� �c:����

        //�d�؂��
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        Text vsText = new Text("VS");
        vsText.setFont(new Font(40));

        //�O���b�h�z�u
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER); //��:���� �c:����
        GridPane.setConstraints(vsText, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER); //��:���� �c:����
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER); //��:���� �c:����

        //PL����GUI
        Text playerText = new Text("Player");//���O
        playerText.setFont(new Font(40));
        playerStateImage.setFitWidth(200);
        playerStateImage.setFitHeight(200);
        setChargeText("player");
        playerChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(playerText, 2, 2, 1, 1, HPos.CENTER, VPos.TOP); //��:���� �c:��
        GridPane.setConstraints(playerStateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER); //��:���� �c:����
        GridPane.setConstraints(playerChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER); //��:�� �c:����

        //���C�A�E�g�ݒu
        gridPane.getChildren().addAll(cpuText, cpuStateImage, cpuChageText, playerText, playerStateImage,
                playerChageText, adjustTexts[0], adjustTexts[1], vsText);

        //�{�^���ݒ�
        playerAttackButton.setPrefSize(buttonWidth, buttonHeight);
        playerAttackButton.setDisable(true); //�����͂��ߐ�0�ׁ̈A�����Ȃ��悤�ɂ��炩���߂��Ă���
        playerAttackButton.setOnAction(event -> fightCpu("attack"));
        playerChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerChargeButton.setDisable(false);
        playerChargeButton.setOnAction(event -> fightCpu("charge"));
        playerGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerGuardButton.setDisable(false);
        playerGuardButton.setOnAction(event -> fightCpu("guard"));
        buttonLayout.getChildren().addAll(playerAttackButton, playerChargeButton, playerGuardButton);

        //�S�̂܂Ƃ�
        battlePane.getChildren().addAll(gridPane, buttonLayout);
        Scene battleScene = new Scene(battlePane);
        stage.setScene(battleScene);
        stage.show();
    }

    /**
     * �ΐl�p
     * ��ʍ쐬�A�{�^���ݒ�Ȃ�
     * ���e�͂قƂ�ǃR���s���[�^��p�ƕς��Ȃ�
     * 
     * ����_
     * �E�㉺�̃{�^���ݒu
     * �EVS�e�L�X�g�@���@fight�{�^���̐ݒu
     */
    private void vsPlayerBattleStageCreate() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        //�����ݒ�
        playerOneChage = 0;
        playerTwoChage = 0;
        playerOneState = "charge";
        playerTwoState = "charge";
        //�摜�̏�����
        playerOneStateImage.setImage(new Image("./stay.png"));
        playerTwoStateImage.setImage(new Image("./stay.png"));

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("Player�@BATTLE");
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

        //playerOne����GUI
        Text playerOneText = new Text("Player1");//���O
        playerOneText.setFont(new Font(40));

        playerOneStateImage.setFitWidth(200);
        playerOneStateImage.setFitHeight(200);
        setChargeText("playerOne");
        playerOneChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(playerOneText, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(playerOneStateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(playerOneChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER);

        //�d�؂��
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        fightButton.setOnAction(event -> fightPlayer());
        fightButton.setDisable(false);
        //�O���b�h�z�u
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fightButton, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        //PL����GUI
        Text playerTwoText = new Text("Player2");//���O
        playerTwoText.setFont(new Font(40));

        playerTwoStateImage.setFitWidth(200);
        playerTwoStateImage.setFitHeight(200);
        setChargeText("playerTwo");
        playerTwoChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(playerTwoText, 2, 2, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(playerTwoStateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(playerTwoChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        gridPane.getChildren().addAll(playerOneText, playerOneStateImage, playerOneChageText, playerTwoText,
                playerTwoStateImage, playerTwoChageText, adjustTexts[0], adjustTexts[1], fightButton);

        //�{�^���ݒ�
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
     * ���ߐ��̕ω����e�L�X�g��ɏo�͂���
     * CUI,GUI�Ƃ��ɏo��
     * CUI�͊m�F�p
     */
    private void setChargeText(String who) {
        switch (who) {
        case "cpu":
            System.out.println(who + "�F���߁F" + cpuChage);
            cpuChageText.setText("����:" + cpuChage);
            break;

        case "player":
            System.out.println(who + "�F���߁F" + playerChage);
            playerChageText.setText("����:" + playerChage);
            break;

        case "playerOne":
            System.out.println(who + "�F���߁F" + playerOneChage);
            playerOneChageText.setText("����:" + playerOneChage);
            break;

        case "playerTwo":
            System.out.println(who + "�F���߁F" + playerTwoChage);
            playerTwoChageText.setText("����:" + playerTwoChage);
            break;
        }
    }

    /**
     * �摜�̕ύX�A���ߐ��̑������s��
     * @param who �ǂ̃v���C���[�ɑ΂��Ă̓����Ȃ̂�
     * @param state �ǂ̓���̓�����������̂�
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
     * ��CPU��p
     * �{�^���������ꂽ�Ƃ��ɏ������J�n����
     * @param playerState �v���C���[�̑I�������s��
     */
    private void fightCpu(String playerState) {
        //�퓬����
        int judgeState = 0;

        setState("cpu", cpuMode.cpuThink(cpuChage, cpuLevel, playerChage));
        judgeState = judge.battleJudge(cpuState, playerState);
        cpuMode.battleResultStageCreate(judgeState);
        //�摜�A�e�L�X�g�̐؂�ւ�
        changeState("player", playerState);
        changeState("cpu", cpuState);
        setChargeText("player");
        setChargeText("cpu");
        //�퓬�I�������𖞂����Ă���ꍇ�A�{�^�������ׂĒ�~
        if (0 != judgeState) {
            stopCpuButton();
            record.changeRecord(judgeState);//��т̏�������
        }

    }

    /**
     * �ΐl��p
     * �t�@�C�g�{�^���������ꂽ�Ƃ��ɏ������J�n����
     */
    private void fightPlayer() {
        //�퓬����
        BattleJudge judge = new BattleJudge();
        //�摜�A�e�L�X�g�̐؂�ւ�
        result.battleResultStageCreate(judge.battleJudge(playerOneState, playerTwoState));
        changeState("playerOne", playerOneState);
        changeState("playerTwo", playerTwoState);
        setChargeText("playerOne");
        setChargeText("playerTwo");
        //�퓬�I�������𖞂����Ă���ꍇ�A�{�^�������ׂĒ�~
        if (0 != judge.battleJudge(playerOneState, playerTwoState)) {
            stopVsButton();
        }
    }

    /**
     * �ΐl��p
     * ���݂̑I����ݒ肵�Ă���
     * @param who �ǂ̃v���C���[�̑I���Ȃ̂�
     * @param state �ǂ̓����I�������̂�
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
        //�֐����琔�l���Ăяo��
        int totalbattlenum = record.getTotalBattleTimes();
        int winbattlenum = record.getTotalWinTimes();
        int losebattlenum = record.getTotalLoseTimes();

        stopButton();//���C����ʂ̃{�^����~

        Stage stage = new Stage();

        //��ʂ̐ݒ�
        stage.setTitle("���");
        stage.setHeight(100);
        stage.setWidth(400);
        //�~�{�^���������ꂽ�Ƃ��̓���ݒ�
        stage.setOnCloseRequest(event -> restartButton());

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text("���܂ł̐��:");
        recoadTitleText.setFont(new Font(20));//�t�H���g�T�C�Y20

        Text recoadText = new Text();
        recoadText.setFont(new Font(25));//�t�H���g�T�C�Y15

        recoadText.setText("�݌v" + totalbattlenum + "��" + winbattlenum + "��" + losebattlenum + "�s");
        recoadLayout.getChildren().addAll(recoadTitleText, recoadText);
        //�܂Ƃ�
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }
}