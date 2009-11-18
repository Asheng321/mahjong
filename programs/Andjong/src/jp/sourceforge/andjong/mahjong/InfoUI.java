package jp.sourceforge.andjong.mahjong;

public class InfoUI extends Info {
	public InfoUI(Mahjong game) {
		super(game);
	}

	public Hai[] getDoraAll() {
		return game.getYama().getUraDoraHais();
	}

	public int getManKaze() {
		return game.getManKaze();
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
		game.copyTehaiUi(tehai, kaze);
	}
}
