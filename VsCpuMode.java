
//1600764 2EP4-10 IwamotoYu
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//cpuの行動
public class VsCpuMode {
	public String cpuThink(int cpuChage, int cpuLevel, int playerChage) {
		String cpuState;
		Random rnd = new Random();
		int ran;

		//CpuLebelによって行動パターンを変える
		if (cpuLevel == 0) {
			//CpuLevel0
			if (cpuChage <= 0) {
				ran = rnd.nextInt(2);
			} else {
				ran = rnd.nextInt(3);
			}

		} else if (cpuLevel == 1) {
			//CpuLevel1
			if (cpuChage <= 0) {
				ran = 0;
			} else {
				ran = rnd.nextInt(4);
			}

		} else {
			//CpuLevel2
			if (cpuChage <= 0 && playerChage <= 0) {
				ran = 0;
			} else if (cpuChage <= 0 && playerChage > 0) {
				ran = rnd.nextInt(2);
			} else if (cpuChage > 0 && playerChage <= 0) {
				ran = rnd.nextInt(2);
				if (ran == 1) {
					ran = 2;
				}
			} else if (cpuChage > 0 && playerChage > 0) {
				ran = rnd.nextInt(3);
			} else {
				ran = rnd.nextInt(2);
			}
		}

		//cpuの行動決定
		if (ran == 0) {
			cpuState = "charge";
		} else if (ran == 1) {
			cpuState = "guard";
		} else {
			cpuState = "attack";
		}

		return cpuState;
	}

	public void battleResultStageCreate(int result) {
		String str = "あなたの敗北です！残念！！";
		if (result == 0) {
			return;
		}
		if (result == 2) {
			str = "あなたの勝利です！おめでとう！！";
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
