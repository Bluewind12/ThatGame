public class BattleJudge {
	public int battleJudge(String pc1, String pc2) {
		//0:ˆø‚«•ª‚¯,1:ãPlayer‚ÌŸ—˜,2:‰ºPlayer‚ÌŸ—˜
		if (pc1 == "charge" && pc2 == "attack") {
			return 2;
		} else if (pc2 == "charge" && pc1 == "attack") {
			return 1;
		} else
			return 0;
	}
}