package jp.sourceforge.andjong;

/**
 * �v���C���[�̏����Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Player {
	/** EventIF */
	public EventIF eventIf;

	/** ��v */
	public Tehai tehai = new Tehai();

	/** �� */
	public Kawa kawa = new Kawa();

	/** ���� */
	private int jikaze;

	public int getJikaze() {
		return jikaze;
	}

	public void setJikaze(int jikaze) {
		this.jikaze = jikaze;
	}

	/** �_�_ */
	public int tenbou;

	/**
	 * �v���C���[������������B
	 * 
	 * @param eventIf
	 *            EventIF
	 */
	public Player(EventIF eventIf) {
		this.eventIf = eventIf;
	}

	public void init() {
		tehai.init();
		kawa.init();
	}
}
