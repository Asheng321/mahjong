package jp.sourceforge.andjong;

/**
 * �v���Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Hai {
	/** �ݎq */
	public final static int KIND_WAN = 0x00000010;
	/** ���q */
	public final static int KIND_PIN = 0x00000020;
	/** ���q */
	public final static int KIND_SOU = 0x00000040;
	/** ���v */
	public final static int KIND_SHUU = 0x00000070;

	/** ���v */
	public final static int KIND_FON = 0x00000100;
	/** �O���v */
	public final static int KIND_SANGEN = 0x00000200;
	/** ���v */
	public final static int KIND_TSUU = 0x00000300;

	/**
	 * �v�ԍ�
	 * <p>
	 * <dl>
	 * <dt>�ݎq</dt>
	 * <dd>KIND_WAN | 1-9</dd>
	 * <dt>���q</dt>
	 * <dd>KIND_PIN | 1-9</dd>
	 * <dt>���q</dt>
	 * <dd>KIND_SOU | 1-9</dd>
	 * <dt>���v</dt>
	 * <dd>KIND_FON | 1-4</dd>
	 * <dt>�O���v</dt>
	 * <dd>KIND_SANGEN | 1-3</dd>
	 * </dl>
	 * </p>
	 */
	private int id;

	/**
	 * �v�ԍ����擾����B
	 * 
	 * @return �v�ԍ�
	 */
	public int getId() {
		return id;
	}

	/** �v���p�e�B�i�Ԕv�j */
	public final static int PROPERTY_AKA = 0x00000001;

	/** �v���p�e�B */
	private int property;

	/**
	 * �v���p�e�B���擾����B
	 * 
	 * @return �v���p�e�B
	 */
	public int getProperty() {
		return property;
	}

	/**
	 * ���Hai�I�u�W�F�N�g�����܂��B
	 */
	public Hai() {

	}

	/**
	 * �v�ԍ���n����Hai�I�u�W�F�N�g�����܂��B
	 * 
	 * @param id
	 *            �v�ԍ�
	 */
	public Hai(int id) {
		this.id = id;
	}

	/**
	 * �v�ԍ��ƃv���p�e�B��n����Hai�I�u�W�F�N�g�����܂��B
	 * 
	 * @param id
	 *            �v�ԍ�
	 * @param property
	 *            �v���p�e�B
	 */
	public Hai(int id, int property) {
		this.id = id;
		this.property = property;
	}

	/**
	 * Hai�I�u�W�F�N�g��n����Hai�I�u�W�F�N�g�����܂��B
	 * 
	 * @param hai
	 *            Hai�I�u�W�F�N�g
	 */
	public Hai(Hai hai) {
		this.id = hai.id;
		this.property = hai.property;
	}

	/**
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * 
	 * @param hai
	 *            Hai�I�u�W�F�N�g
	 */
	public void copy(Hai hai) {
		this.id = hai.id;
		this.property = hai.property;
	}
}
