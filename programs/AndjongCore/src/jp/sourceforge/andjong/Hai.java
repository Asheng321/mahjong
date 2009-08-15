package jp.sourceforge.andjong;

/**
 * �v���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Hai {
	/** ID */
	private int id;

	/** ���� */
	public final static int ID_WAN_1 = 0;
	/** ���� */
	public final static int ID_WAN_2 = 1;
	/** �O�� */
	public final static int ID_WAN_3 = 2;
	/** �l�� */
	public final static int ID_WAN_4 = 3;
	/** ���� */
	public final static int ID_WAN_5 = 4;
	/** �Z�� */
	public final static int ID_WAN_6 = 5;
	/** ���� */
	public final static int ID_WAN_7 = 6;
	/** ���� */
	public final static int ID_WAN_8 = 7;
	/** ���� */
	public final static int ID_WAN_9 = 8;

	/** �ꓛ */
	public final static int ID_PIN_1 = 9;
	/** �� */
	public final static int ID_PIN_2 = 10;
	/** �O�� */
	public final static int ID_PIN_3 = 11;
	/** �l�� */
	public final static int ID_PIN_4 = 12;
	/** �ܓ� */
	public final static int ID_PIN_5 = 13;
	/** �Z�� */
	public final static int ID_PIN_6 = 14;
	/** ���� */
	public final static int ID_PIN_7 = 15;
	/** ���� */
	public final static int ID_PIN_8 = 16;
	/** �㓛 */
	public final static int ID_PIN_9 = 17;

	/** ��� */
	public final static int ID_SOU_1 = 18;
	/** ��� */
	public final static int ID_SOU_2 = 19;
	/** �O�� */
	public final static int ID_SOU_3 = 20;
	/** �l�� */
	public final static int ID_SOU_4 = 21;
	/** �܍� */
	public final static int ID_SOU_5 = 22;
	/** �Z�� */
	public final static int ID_SOU_6 = 23;
	/** ���� */
	public final static int ID_SOU_7 = 24;
	/** ���� */
	public final static int ID_SOU_8 = 25;
	/** ��� */
	public final static int ID_SOU_9 = 26;

	/** �� */
	public final static int ID_TON = 27;
	/** �� */
	public final static int ID_NAN = 28;
	/** �� */
	public final static int ID_SHA = 29;
	/** �k */
	public final static int ID_PE = 30;

	/** �� */
	public final static int ID_HAKU = 31;
	/** � */
	public final static int ID_HATSU = 32;
	/** �� */
	public final static int ID_CHUN = 33;

	/** ID�̍ő�l */
	public final static int ID_MAX = ID_CHUN;

	/** �� */
	public final static int NO_1 = 1;
	/** �� */
	public final static int NO_2 = 2;
	/** �O */
	public final static int NO_3 = 3;
	/** �l */
	public final static int NO_4 = 4;
	/** �� */
	public final static int NO_5 = 5;
	/** �Z */
	public final static int NO_6 = 6;
	/** �� */
	public final static int NO_7 = 7;
	/** �� */
	public final static int NO_8 = 8;
	/** �� */
	public final static int NO_9 = 9;

	/** ���� */
	public final static int NO_WAN_1 = 1;
	/** ���� */
	public final static int NO_WAN_2 = 2;
	/** �O�� */
	public final static int NO_WAN_3 = 3;
	/** �l�� */
	public final static int NO_WAN_4 = 4;
	/** ���� */
	public final static int NO_WAN_5 = 5;
	/** �Z�� */
	public final static int NO_WAN_6 = 6;
	/** ���� */
	public final static int NO_WAN_7 = 7;
	/** ���� */
	public final static int NO_WAN_8 = 8;
	/** ���� */
	public final static int NO_WAN_9 = 9;

	/** �ꓛ */
	public final static int NO_PIN_1 = 1;
	/** �� */
	public final static int NO_PIN_2 = 2;
	/** �O�� */
	public final static int NO_PIN_3 = 3;
	/** �l�� */
	public final static int NO_PIN_4 = 4;
	/** �ܓ� */
	public final static int NO_PIN_5 = 5;
	/** �Z�� */
	public final static int NO_PIN_6 = 6;
	/** ���� */
	public final static int NO_PIN_7 = 7;
	/** ���� */
	public final static int NO_PIN_8 = 8;
	/** �㓛 */
	public final static int NO_PIN_9 = 9;

	/** ��� */
	public final static int NO_SOU_1 = 1;
	/** ��� */
	public final static int NO_SOU_2 = 2;
	/** �O�� */
	public final static int NO_SOU_3 = 3;
	/** �l�� */
	public final static int NO_SOU_4 = 4;
	/** �܍� */
	public final static int NO_SOU_5 = 5;
	/** �Z�� */
	public final static int NO_SOU_6 = 6;
	/** ���� */
	public final static int NO_SOU_7 = 7;
	/** ���� */
	public final static int NO_SOU_8 = 8;
	/** ��� */
	public final static int NO_SOU_9 = 9;

	/** �� */
	public final static int NO_TON = 1;
	/** �� */
	public final static int NO_NAN = 2;
	/** �� */
	public final static int NO_SHA = 3;
	/** �k */
	public final static int NO_PE = 4;

	/** �� */
	public final static int NO_HAKU = 1;
	/** � */
	public final static int NO_HATSU = 2;
	/** �� */
	public final static int NO_CHUN = 3;

	/** �ݎq */
	public final static int ID_A_WAN = 0x00000010;
	/** ���q */
	public final static int ID_A_PIN = 0x00000020;
	/** ���q */
	public final static int ID_A_SOU = 0x00000040;
	/** ���v */
	public final static int ID_A_SHUU = ID_A_WAN | ID_A_PIN | ID_A_SOU;

	/** ���v */
	public final static int ID_A_FON = 0x00000100;
	/** �O���v */
	public final static int ID_A_SANGEN = 0x00000200;
	/** ���v */
	public final static int ID_A_TSUU = ID_A_FON | ID_A_SANGEN;

	/** �ݎq */
	public final static int KIND_WAN = 0;
	/** ���q */
	public final static int KIND_PIN = 1;
	/** ���q */
	public final static int KIND_SOU = 2;
	/** ���v */
	public final static int KIND_FON = 3;
	/** �O���v */
	public final static int KIND_SANGEN = 4;

	/** �ԍ��̔z�� */
	private final static int[] nos = {
	// �ݎq
	NO_WAN_1, NO_WAN_2, NO_WAN_3, NO_WAN_4, NO_WAN_5, NO_WAN_6, NO_WAN_7, NO_WAN_8, NO_WAN_9,
	// ���q
	NO_PIN_1, NO_PIN_2, NO_PIN_3, NO_PIN_4, NO_PIN_5, NO_PIN_6, NO_PIN_7, NO_PIN_8, NO_PIN_9,
	// ���q
	NO_SOU_1, NO_SOU_2, NO_SOU_3, NO_SOU_4, NO_SOU_5, NO_SOU_6, NO_SOU_7, NO_SOU_8, NO_SOU_9,
	// ���v
	NO_TON, NO_NAN, NO_SHA, NO_PE,
	// �O���v
	NO_HAKU, NO_HATSU, NO_CHUN };

	/** ID Format A�̔z�� */
	private final static int[] idAs = {
	// �ݎq
	ID_A_WAN | NO_WAN_1, ID_A_WAN | NO_WAN_2, ID_A_WAN | NO_WAN_3, ID_A_WAN | NO_WAN_4, ID_A_WAN | NO_WAN_5, ID_A_WAN | NO_WAN_6, ID_A_WAN | NO_WAN_7, ID_A_WAN | NO_WAN_8, ID_A_WAN | NO_WAN_9,
	// ���q
	ID_A_PIN | NO_PIN_1, ID_A_PIN | NO_PIN_2, ID_A_PIN | NO_PIN_3, ID_A_PIN | NO_PIN_4, ID_A_PIN | NO_PIN_5, ID_A_PIN | NO_PIN_6, ID_A_PIN | NO_PIN_7, ID_A_PIN | NO_PIN_8, ID_A_PIN | NO_PIN_9,
	// ���q
	ID_A_SOU | NO_SOU_1, ID_A_SOU | NO_SOU_2, ID_A_SOU | NO_SOU_3, ID_A_SOU | NO_SOU_4, ID_A_SOU | NO_SOU_5, ID_A_SOU | NO_SOU_6, ID_A_SOU | NO_SOU_7, ID_A_SOU | NO_SOU_8, ID_A_SOU | NO_SOU_9,
	// ���v
	ID_A_FON | NO_TON, ID_A_FON | NO_NAN, ID_A_FON | NO_SHA, ID_A_FON | NO_PE,
	// �O���v
	ID_A_SANGEN | NO_HAKU, ID_A_SANGEN | NO_HATSU, ID_A_SANGEN | NO_CHUN };

	/** ��ނ̔z�� */
	private final static int[] kinds = {
	// �ݎq
	KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN, KIND_WAN,
	// ���q
	KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN, KIND_PIN,
	// ���q
	KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU, KIND_SOU,
	// ���v
	KIND_FON, KIND_FON, KIND_FON, KIND_FON,
	// �O���v
	KIND_SANGEN, KIND_SANGEN, KIND_SANGEN };

	/** ���v�t���O�̔z�� */
	private final static boolean[] ichikyuus = {
	// �ݎq
	true, false, false, false, false, false, false, false, true,
	// ���q
	true, false, false, false, false, false, false, false, true,
	// ���q
	true, false, false, false, false, false, false, false, true,
	// ���v
	false, false, false, false,
	// �O���v
	false, false, false };

	/** ���v�t���O�̔z�� */
	private final static boolean[] tsuus = {
	// �ݎq
	false, false, false, false, false, false, false, false, false,
	// ���q
	false, false, false, false, false, false, false, false, false,
	// ���q
	false, false, false, false, false, false, false, false, false,
	// ���v
	true, true, true, true,
	// �O���v
	true, true, true };

	/**
	 * ��̔v���쐬����B
	 */
	public Hai() {

	}

	/**
	 * ID����v���쐬����B
	 *
	 * @param id
	 *            ID
	 */
	public Hai(int id) {
		this.id = id;
	}

	/**
	 * �v����v���쐬����B
	 *
	 * @param hai
	 *            �v
	 */
	public Hai(Hai hai) {
		copy(hai);
	}

	/**
	 * �v���R�s�[����B
	 *
	 * @param hai
	 *            �v
	 */
	public void copy(Hai hai) {
		this.id = hai.id;
	}

	/**
	 * ID���擾����B
	 *
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * �ԍ����擾����B
	 *
	 * @return �ԍ�
	 */
	public int getNo() {
		return nos[id];
	}

	/**
	 * ID Format A���擾����B
	 *
	 * @return ID Format A
	 */
	public int getIdA() {
		return idAs[id];
	}

	/**
	 * ��ނ��擾����B
	 *
	 * @return ���
	 */
	public int getKind() {
		return kinds[id];
	}

	/**
	 * ���v�t���O���擾����B
	 *
	 * @return ���v�t���O
	 */
	public boolean isIchikyuu() {
		return ichikyuus[id];
	}

	/**
	 * ���v�t���O���擾����B
	 *
	 * @return ���v�t���O
	 */
	public boolean isTsuu() {
		return tsuus[id];
	}

	/**
	 * ��㎚�v�t���O���擾����B
	 *
	 * @return ��㎚�v�t���O
	 */
	public boolean isYaotyuu() {
		return ichikyuus[id] | tsuus[id];
	}
}
