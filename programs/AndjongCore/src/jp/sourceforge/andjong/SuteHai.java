package jp.sourceforge.andjong;

/**
 * �̔v���Ǘ�����N���X
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
	 * �ԍ�����̔v���쐬����B
	 * 
	 * @param id
	 *            �ԍ�
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
