public class BattleJudge {
	public int battleJudge(String pc1, String pc2) {
		//0:引き分け,1:上Playerの勝利,2:下Playerの勝利
		if (pc1 == "charge" && pc2 == "attack") {
			return 2;
		} else if (pc2 == "charge" && pc1 == "attack") {
			return 1;
		} else
			return 0;
	}
}