package jp.sourceforge.andjong;

/**
 * �̔v���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class SuteHai extends Hai {
	/** ���t���O */
	private boolean naki;

	/** ���[�`�t���O */
	private boolean reach;

	/** ��o���t���O */
	private boolean tedashi;

	/**
	 * �̔v���쐬����B
	 */
	public SuteHai() {
		super();
	}

	/**
	 * ID����̔v���쐬����B
	 *
	 * @param id
	 *            ID
	 */
	public SuteHai(int id) {
		super(id);
	}

	/**
	 * �v����̔v���쐬����B
	 *
	 * @param hai
	 *            �v
	 */
	public SuteHai(Hai hai) {
		super(hai);
	}

	/**
	 * �̔v���R�s�[����B
	 *
	 * @param destSuteHai
	 *            �R�s�[��̎̔v
	 * @param srcSuteHai
	 *            �R�s�[���̎̔v
	 */
	public static void copy(SuteHai destSuteHai, SuteHai srcSuteHai) {
		Hai.copy(destSuteHai, srcSuteHai);
		destSuteHai.naki = srcSuteHai.naki;
		destSuteHai.reach = srcSuteHai.reach;
		destSuteHai.tedashi = srcSuteHai.tedashi;
	}

	/**
	 * ���t���O��ݒ肷��B
	 *
	 * @param naki
	 *            ���t���O
	 */
	public void setNaki(boolean naki) {
		this.naki = naki;
	}

	/**
	 * ���t���O���擾����B
	 *
	 * @return ���t���O
	 */
	public boolean isNaki() {
		return naki;
	}

	/**
	 * ���[�`�t���O��ݒ肷��B
	 *
	 * @param reach
	 *            ���[�`�t���O
	 */
	public void setReach(boolean reach) {
		this.reach = reach;
	}

	/**
	 * ���[�`�t���O���擾����B
	 *
	 * @return ���[�`�t���O
	 */
	public boolean isReach() {
		return reach;
	}

	/**
	 * ��o���t���O��ݒ肷��B
	 *
	 * @param tedashi
	 *            ��o���t���O
	 */
	public void setTedashi(boolean tedashi) {
		this.tedashi = tedashi;
	}

	/**
	 * ��o���t���O���擾����B
	 *
	 * @return ��o���t���O
	 */
	public boolean isTedashi() {
		return tedashi;
	}
}
