package jp.sourceforge.andjong;

/**
 * �v���C���[�̏����Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Player {
	/** AI */
	public AI ai;

	/** ��v */
	public Tehai tehai = new Tehai();

	/** �� */
	public Kawa kawa = new Kawa();

	/** ���� */
	public int jikaze;

	/** �_�_ */
	public int tenbou;

	/**
	 * �v���C���[������������B
	 */
	public Player() {

	}

	/**
	 * �v���C���[������������B
	 * 
	 * @param ai
	 *            AI
	 */
	public Player(AI ai) {
		this.ai = ai;
	}
}
