public class BattleJudge {
	public int battleJudge(String pc1, String pc2) {
		//0:��������,1:��Player�̏���,2:��Player�̏���
		if (pc1 == "charge" && pc2 == "attack") {
			return 2;
		} else if (pc2 == "charge" && pc1 == "attack") {
			return 1;
		} else
			return 0;
	}
}