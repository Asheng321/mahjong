package jp.sourceforge.andjong;

import jp.sourceforge.andjong.Game.SAI;
import jp.sourceforge.andjong.Tehai.Combi;

/**
 * AI��UI��Game�N���X�̏���񋟂���N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Info {
	/** �Q�[���I�u�W�F�N�g */
	protected Game game;

	public SAI[] getSai() {
		return game.getSais();
	}

	public Hai[] getDora() {
		return game.getYama().getDora();
	}

	private int sutehaiIdx;

	public int getSutehaiIdx() {
		return sutehaiIdx;
	}

	public void setSutehaiIdx(int sutehaiIdx) {
		this.sutehaiIdx = sutehaiIdx;
	}

	public Info(Game game) {
		this.game = game;
	}

	public Hai getTsumoHai() {
		return new Hai(game.tsumoHai);
	}

	public Hai getSuteHai() {
		return new Hai(game.suteHai);
	}

	public void copyTehai(Tehai tehai, int kaze) {
		game.copyTehai(tehai, kaze);
	}

	public void copyKawa(Kawa kawa, int kaze) {
		game.copyKawa(kawa, kaze);
	}

	public int getJikaze() {
		return game.getJikaze();
	}

	public int getAgariScore(Tehai tehai, Hai addHai, int combisCount,
			Combi[] combis) {
		return game.getAgariScore(tehai, addHai, combisCount, combis);
	}
}
