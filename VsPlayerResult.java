import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VsPlayerResult {

    public void battleResultStageCreate(int result) {
        String str = "2P�̏����ł�";
        if (result == 0) {
            return;
        }
        if (result == 1) {
            str = "1P�̏����ł�";
        }

        Stage stage = new Stage();

        //��ʂ̐ݒ�
        stage.setTitle("���s");
        stage.setHeight(100);
        stage.setWidth(400);

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text(str);
        recoadTitleText.setFont(new Font(20));//�t�H���g�T�C�Y20

        recoadLayout.getChildren().addAll(recoadTitleText);
        //�܂Ƃ�
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }

}
