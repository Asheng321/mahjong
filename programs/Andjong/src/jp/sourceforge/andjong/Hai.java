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
	 * <dd>KIND_WAN & 1-9</dd>
	 * <dt>���q</dt>
	 * <dd>KIND_PIN & 1-9</dd>
	 * <dt>���q</dt>
	 * <dd>KIND_SOU & 1-9</dd>
	 * <dt>���v</dt>
	 * <dd>KIND_FON & 1-4</dd>
	 * <dt>�O���v</dt>
	 * <dd>KIND_SANGEN & 1-3</dd>
	 * </dl>
	 * </p>
	 */
	public int id;

	/** �Ԕv�t���O */
	public boolean aka;

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
	 * �v�ԍ��ƐԔv�t���O��n����Hai�I�u�W�F�N�g�����܂��B
	 * 
	 * @param id
	 *            �v�ԍ�
	 * @param aka
	 *            �Ԕv�t���O
	 */
	public Hai(int id, boolean aka) {
		this.id = id;
		this.aka = aka;
	}

	/**
	 * Hai�I�u�W�F�N�g��n����Hai�I�u�W�F�N�g�����܂��B
	 * 
	 * @param hai
	 *            Hai�I�u�W�F�N�g
	 */
	public Hai(Hai hai) {
		this.id = hai.id;
		this.aka = hai.aka;
	}
}
