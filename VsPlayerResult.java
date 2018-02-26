import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VsPlayerResult {

    public void battleResultStageCreate(int result) {
        String str = "2Pの勝利です";
        if (result == 0) {
            return;
        }
        if (result == 1) {
            str = "1Pの勝利です";
        }

        Stage stage = new Stage();

        //画面の設定
        stage.setTitle("勝敗");
        stage.setHeight(100);
        stage.setWidth(400);

        HBox recoadLayout = new HBox(10);

        Text recoadTitleText = new Text(str);
        recoadTitleText.setFont(new Font(20));//フォントサイズ20

        recoadLayout.getChildren().addAll(recoadTitleText);
        //まとめ
        Scene recordScene = new Scene(recoadLayout);
        stage.setScene(recordScene);
        stage.show();
    }

}
