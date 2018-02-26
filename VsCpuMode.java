
//1600764 2EP4-10 IwamotoYu
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//cpu�̍s��
public class VsCpuMode {
	public String cpuThink(int cpuChage, int cpuLevel, int playerChage) {
		String cpuState;
		Random rnd = new Random();
		int ran;

		//CpuLebel�ɂ���čs���p�^�[����ς���
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

		//cpu�̍s������
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
		String str = "���Ȃ��̔s�k�ł��I�c�O�I�I";
		if (result == 0) {
			return;
		}
		if (result == 2) {
			str = "���Ȃ��̏����ł��I���߂łƂ��I�I";
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
