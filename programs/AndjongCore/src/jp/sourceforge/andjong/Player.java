package jp.sourceforge.andjong;

/**
 * �v���C���[���Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
class Player {
	/** EventIF */
	private EventIF eventIf;

	/**
	 * EventIF���擾���܂��B�B
	 * 
	 * @return EventIF
	 */
	EventIF getEventIf() {
		return eventIf;
	}

	/** ��v */
	private Tehai tehai = new Tehai();

	/**
	 * ��v���擾���܂��B
	 * 
	 * @return ��v
	 */
	Tehai getTehai() {
		return tehai;
	}

	/** �� */
	private Kawa kawa = new Kawa();

	/**
	 * �͂��擾���܂��B
	 * 
	 * @return ��
	 */
	Kawa getKawa() {
		return kawa;
	}

	/** ���� */
	private int jikaze;

	/**
	 * �������擾���܂��B
	 * 
	 * @return ����
	 */
	int getJikaze() {
		return jikaze;
	}

	/**
	 * ������ݒ肵�܂��B
	 * 
	 * @param jikaze
	 *            ����
	 */
	void setJikaze(int jikaze) {
		this.jikaze = jikaze;
	}

	/** �_�_ */
	private int tenbou;

	/**
	 * �_�_���擾���܂��B
	 * 
	 * @return
	 */
	int getTenbou() {
		return tenbou;
	}

	/**
	 * �_�_��ݒ肵�܂��B
	 * 
	 * @param tenbou
	 *            �_�_
	 */
	void setTenbou(int tenbou) {
		this.tenbou = tenbou;
	}

	/**
	 * �_�_�𑝂₵�܂��B
	 * 
	 * @param ten
	 *            �_
	 */
	void increaseTenbou(int ten) {
		tenbou += ten;
	}

	/**
	 * �_�_�����炵�܂��B
	 * 
	 * @param ten
	 *            �_
	 */
	void reduceTenbou(int ten) {
		tenbou -= ten;
	}

	/** ���[�` */
	private boolean reach;

	/**
	 * ���[�`���擾���܂��B
	 * 
	 * @return ���[�`
	 */
	boolean isReach() {
		return reach;
	}

	/**
	 * ���[�`��ݒ肵�܂��B
	 * 
	 * @param reach
	 *            ���[�`
	 */
	void setReach(boolean reach) {
		this.reach = reach;
	}

	/**
	 * �v���C���[������������B
	 * 
	 * @param eventIf
	 *            EventIF
	 */
	Player(EventIF eventIf) {
		this.eventIf = eventIf;
	}

	/**
	 * �v���C���[�����������܂��B
	 */
	void init() {
		// ��v�����������܂��B
		tehai.init();

		// �͂����������܂��B
		kawa.init();

		// �_�_�����������܂��B
		tenbou = 25000;

		// ���[�`�����������܂��B
		reach = false;
	}
}
