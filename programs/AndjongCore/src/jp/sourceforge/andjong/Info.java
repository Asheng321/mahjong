package jp.sourceforge.andjong;

/**
 * �v���C���[�ɒ񋟂�������Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Info {
	/** Game */
	protected Game game;

	/**
	 * �C���X�^���X�����������܂��B
	 * 
	 * @param game
	 *            Game
	 */
	public Info(Game game) {
		this.game = game;
	}

	/**
	 * �T�C�R���̔z����擾���܂��B
	 * 
	 * @return �T�C�R���̔z��
	 */
	public Sai[] getSais() {
		return game.getSais();
	}

	/**
	 * �\�h���A�ȃh���̔z����擾���܂��B
	 * 
	 * @return �\�h���A�ȃh���̔z��
	 */
	public Hai[] getDoras() {
		return game.getDoras();
	}

	/**
	 * �������擾���܂��B
	 * 
	 * @return ����
	 */
	public int getJikaze() {
		return game.getJikaze();
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
	 * �͂��R�s�[���܂��B
	 * 
	 * @param kawa
	 *            ��
	 * @param kaze
	 *            ��
	 */
	public void copyKawa(Kawa kawa, int kaze) {
		game.copyKawa(kawa, kaze);
	}

	/**
	 * �c���v���擾���܂��B
	 * 
	 * @return �c���v
	 */
	public Hai getTsumoHai() {
		return new Hai(game.getTsumoHai());
	}

	/**
	 * �̔v���擾���܂��B
	 * 
	 * @return �̔v
	 */
	public Hai getSuteHai() {
		return new Hai(game.getSuteHai());
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

	/**
	 * ���[�`���擾���܂��B
	 * 
	 * @param kaze
	 *            ��
	 * @return ���[�`
	 */
	public boolean isReach(int kaze) {
		return game.isReach(kaze);
	}
}
