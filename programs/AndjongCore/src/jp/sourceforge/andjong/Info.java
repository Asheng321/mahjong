package jp.sourceforge.andjong;

import jp.sourceforge.andjong.Game.SAI;

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

	/**
	 * ��v���R�s�[���܂��B
	 * 
	 * @param tehai
	 *            ��v
	 * @param kaze
	 *            ��
	 */
	public void copyTehai(Tehai tehai, int kaze) {
		game.copyTehai(tehai, kaze);
	}

	/**
	 * �c���v���擾���܂��B
	 * 
	 * @return �c���v
	 */
	public Hai getTsumoHai() {
		return new Hai(game.tsumoHai);
	}

	/**
	 * ������_���擾���܂��B
	 * 
	 * @param tehai
	 *            ��v
	 * @param addHai
	 *            ��v�ɒǉ�����v
	 * @return
	 */
	public int getAgariScore(Tehai tehai, Hai addHai) {
		return game.getAgariScore(tehai, addHai);
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

	public Hai getSuteHai() {
		return new Hai(game.suteHai);
	}

	public void copyKawa(Kawa kawa, int kaze) {
		game.copyKawa(kawa, kaze);
	}

	public int getJikaze() {
		return game.getJikaze();
	}
}
