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

	/** �Ƃ���v���C���[�ԍ����擾���� */
	public int[] PlayerToCha = new int[4];

	/** �v���C���[�ԍ�����Ƃ��擾���� */
	public int[] ChaToPlayer = new int[4];

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
	
	public void init() {
		tehai.init();
		kawa.init();
	}
}
