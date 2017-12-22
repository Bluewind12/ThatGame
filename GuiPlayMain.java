import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
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

    public void start(Stage stage) {
        int buttonHeight = 50;
        int buttonWidth = 125;
        int buttonTextSize = 15;

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

        recordButton.setPrefSize(buttonWidth, buttonHeight);
        recordButton.setFont(new Font(buttonTextSize));

        endButton.setPrefSize(buttonWidth, buttonHeight);
        endButton.setFont(new Font(buttonTextSize));
        endButton.setOnAction(event -> System.exit(0));

        ImageView chargeImage = new ImageView("/charge.png");
        chargeImage.setFitWidth(100);
        chargeImage.setLayoutX(150);
        chargeImage.setLayoutY(0);

        ImageView attackImage = new ImageView("/attack.png");
        attackImage.setFitWidth(100);
        attackImage.setLayoutX(0);
        attackImage.setLayoutY(150);

        ImageView guardImage = new ImageView("/guard.png");
        guardImage.setFitWidth(100);
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
        int buttonTextSize = 15;

        stage.setOnCloseRequest(event -> restartButton());
        stage.setTitle("CPU　BATTLE");
        stage.setHeight(600);
        stage.setWidth(600);

        VBox battleMainPane = new VBox(20);
        battleMainPane.setAlignment(Pos.CENTER);

        Button testButton = new Button("Test");
        testButton.setPrefSize(buttonWidth, buttonHeight);

        battleMainPane.getChildren().addAll(testButton);
        Scene battleScene = new Scene(battleMainPane);
        stage.setScene(battleScene);
        stage.show();
    }

}