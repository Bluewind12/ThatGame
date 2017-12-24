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
    //���C����ʁi�{�^���j
    private Button computermodeButton = new Button("��CPU");
    private Button pvpmodeButton = new Button("�ΐl");
    private Button recordButton = new Button("���܂ł̐��");
    private Button endButton = new Button("�I��");

    //�R���s���[�^��p
    //CPU�i���x���A���ߐ��A�摜�A���ߐ��\���j
    private int cpuLevel;
    private static int cpuChage;
    private static ImageView cpuStateImage = new ImageView("/stay.png");
    private static Text cpuChageText = new Text();
    //�v���C���[�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int playerChage;
    private static ImageView playerStateImage = new ImageView("/stay.png");
    private static Text playerChageText = new Text();
    private static Button playerAttackButton = new Button("����");
    private static Button playerChargeButton = new Button("���߂�");
    private static Button playerGuardButton = new Button("�h��");

    //�ΐ�p
    //�v���C���[1�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int p1_Chage;
    private String p1_State;
    private static ImageView p1_StateImage = new ImageView("/stay.png");
    private static Text p1_ChageText = new Text();
    private static Button p1_AttackButton = new Button("����");
    private static Button p1_ChargeButton = new Button("���߂�");
    private static Button p1_GuardButton = new Button("�h��");
    //�v���C���[2�i���ߐ��A�摜�A���ߐ��\���A�{�^���j
    private int p2_Chage;
    private String p2_State;
    private static ImageView p2_StateImage = new ImageView("/stay.png");
    private static Text p2_ChageText = new Text();
    private static Button p2_AttackButton = new Button("����");
    private static Button p2_ChargeButton = new Button("���߂�");
    private static Button p2_GuardButton = new Button("�h��");

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
        stage.setTitle("That Game");
        stage.setHeight(600);
        stage.setWidth(600);

        //�S�̂܂Ƃߗp���C�A�E�g
        VBox topPane = new VBox(20); //�c����
        topPane.setAlignment(Pos.CENTER); //��������

        //�摜�z�u�p���C�A�E�g
        Group pictureGroup = new Group();

        //�I��p�{�^�����C�A�E�g
        HBox buttonlayoutBox = new HBox(20);//������
        buttonlayoutBox.setAlignment(Pos.CENTER);//��������

        //�^�C�g��
        Text titleText = new Text("���߂��茂������h�䂵���肷��E�A��");
        titleText.setFont(new Font(40));//�t�H���g�T�C�Y40

        //�{�^���쐬�A�C�x���g�ݒ�
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
        topPane.getChildren().addAll(titleText, pictureGroup, buttonlayoutBox);

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
     * �΃R���s���[�^�p
     * ��ʍ쐬�A�{�^���ݒ�Ȃ�
     */
    private void vsCpuBattleStage() {

        final Provisional kari = new Provisional();
        stopButton();//���C����ʂ̃{�^����~

        Stage stage = new Stage();

        //���C�A�E�g�p�ꊇ�Ǘ��萔
        final int buttonHeight = 50;
        final int buttonWidth = 125;

        //���ߐ��̏�����
        cpuChage = 0;
        playerChage = 0;

        //�摜�̏�����
        cpuStateImage.setImage(new Image("./stay.png"));
        playerStateImage.setImage(new Image("./stay.png"));

        //��ʂ̐ݒ�
        stage.setTitle("CPU�@BATTLE");
        stage.setHeight(600);
        stage.setWidth(600);
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
        playerAttackButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "attack"));
        playerChargeButton.setPrefSize(buttonWidth, buttonHeight);
        playerChargeButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "charge"));
        playerGuardButton.setPrefSize(buttonWidth, buttonHeight);
        playerGuardButton.setOnAction(event -> kari.provisional(cpuChage, cpuLevel, playerChage, "guard"));
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
    private void vsPlayerBattleStage() {
        stopButton();
        Stage stage = new Stage();

        int buttonHeight = 50;
        int buttonWidth = 125;

        //���ߐ��̏�����
        p1_Chage = 0;
        p2_Chage = 0;
        //�摜�̏�����
        p1_StateImage.setImage(new Image("./stay.png"));
        p2_StateImage.setImage(new Image("./stay.png"));

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("Player�@BATTLE");
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

        //p1_����GUI
        Text p1_Text = new Text("Player1");//���O
        p1_Text.setFont(new Font(40));

        p1_StateImage.setFitWidth(200);
        p1_StateImage.setFitHeight(200);
        setChargeText("p1");
        p1_ChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(p1_Text, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(p1_StateImage, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(p1_ChageText, 2, 0, 1, 1, HPos.LEFT, VPos.CENTER);

        //�d�؂��
        Text[] adjustTexts = { new Text("===================="), new Text("====================") };
        Button fightButton = new Button("VS");
        fightButton.setOnAction(event -> fight());
        //�O���b�h�z�u
        GridPane.setConstraints(adjustTexts[0], 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fightButton, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(adjustTexts[1], 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        //PL����GUI
        Text p2_Text = new Text("Player2");//���O
        p2_Text.setFont(new Font(40));

        p2_StateImage.setFitWidth(200);
        p2_StateImage.setFitHeight(200);
        setChargeText("p2");
        p2_ChageText.setFont(new Font(20));

        //�O���b�h�z�u
        GridPane.setConstraints(p2_Text, 2, 2, 1, 1, HPos.CENTER, VPos.TOP);
        GridPane.setConstraints(p2_StateImage, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(p2_ChageText, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        gridPane.getChildren().addAll(p1_Text, p1_StateImage, p1_ChageText, p2_Text, p2_StateImage, p2_ChageText,
                adjustTexts[0], adjustTexts[1], fightButton);

        //�{�^���ݒ�
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
     * ���ߐ��̕ω����e�L�X�g��ɏo�͂���
     * CUI,GUI�Ƃ��ɏo��
     * CUI�͊m�F�p
     */
    protected void setChargeText(String who) {
        switch (who) {
        case "cpu":
            System.out.println(who + "�F���߁F" + cpuChage);
            cpuChageText.setText("����:" + cpuChage);
            break;

        case "player":
            System.out.println(who + "�F���߁F" + playerChage);
            playerChageText.setText("����:" + playerChage);
            break;

        case "p1":
            System.out.println(who + "�F���߁F" + p1_Chage);
            p1_ChageText.setText("����:" + p1_Chage);
            break;

        case "p2":
            System.out.println(who + "�F���߁F" + p2_Chage);
            p2_ChageText.setText("����:" + p2_Chage);
            break;
        }
    }

    /**
     * �摜�̕ύX�A���ߐ��̑������s��
     * @param who �ǂ̃v���C���[�ɑ΂��Ă̓����Ȃ̂�
     * @param state �ǂ̓���̓�����������̂�
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
     * �ΐl��p
     * �t�@�C�g�{�^���������ꂽ�Ƃ��ɏ������J�n����
     */
    private void fight() {
        //�퓬����

        //
        changeState("p1", p1_State);//���ݒu�F���ۂɂ͏����ŌĂяo�����
        changeState("p2", p2_State);//���ݒu�F���ۂɂ͏����ŌĂяo�����
        setChargeText("p1");
        setChargeText("p2");
    }

    /**
     * �ΐl��p
     * ���݂̑I����ݒ肵�Ă���
     * @param who �ǂ̃v���C���[�̑I���Ȃ̂�
     * @param state �ǂ̓����I�������̂�
     */
    private void setState(String who, String state) {
        switch (who) {
        case "p1":
            p1_State = state;
            System.out.println("P1:" + p1_State);//���ݒu�F���ۂɂ͏���
            break;
        case "p2":
            p2_State = state;
            System.out.println("P2:" + p2_State);//���ݒu�F���ۂɂ͏���
            break;
        }
    }
}