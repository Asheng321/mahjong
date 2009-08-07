package jp.sourceforge.andjong;

/**
 * �v���Ǘ�����N���X
 * 
 * @author Yuji Urushibara
 * 
 */
public class Hai {
	/** ID */
	private int id;

	/** ���� */
	public static final int ID_WAN_1 = 0;
	/** ���� */
	public static final int ID_WAN_2 = 1;
	/** �O�� */
	public static final int ID_WAN_3 = 2;
	/** �l�� */
	public static final int ID_WAN_4 = 3;
	/** ���� */
	public static final int ID_WAN_5 = 4;
	/** �Z�� */
	public static final int ID_WAN_6 = 5;
	/** ���� */
	public static final int ID_WAN_7 = 6;
	/** ���� */
	public static final int ID_WAN_8 = 7;
	/** ���� */
	public static final int ID_WAN_9 = 8;

	/** �ꓛ */
	public static final int ID_PIN_1 = 9;
	/** �� */
	public static final int ID_PIN_2 = 10;
	/** �O�� */
	public static final int ID_PIN_3 = 11;
	/** �l�� */
	public static final int ID_PIN_4 = 12;
	/** �ܓ� */
	public static final int ID_PIN_5 = 13;
	/** �Z�� */
	public static final int ID_PIN_6 = 14;
	/** ���� */
	public static final int ID_PIN_7 = 15;
	/** ���� */
	public static final int ID_PIN_8 = 16;
	/** �㓛 */
	public static final int ID_PIN_9 = 17;

	/** ��� */
	public static final int ID_SOU_1 = 18;
	/** ��� */
	public static final int ID_SOU_2 = 19;
	/** �O�� */
	public static final int ID_SOU_3 = 20;
	/** �l�� */
	public static final int ID_SOU_4 = 21;
	/** �܍� */
	public static final int ID_SOU_5 = 22;
	/** �Z�� */
	public static final int ID_SOU_6 = 23;
	/** ���� */
	public static final int ID_SOU_7 = 24;
	/** ���� */
	public static final int ID_SOU_8 = 25;
	/** ��� */
	public static final int ID_SOU_9 = 26;

	/** �� */
	public static final int ID_TON = 27;
	/** �� */
	public static final int ID_NAN = 28;
	/** �� */
	public static final int ID_SHA = 29;
	/** �k */
	public static final int ID_PE = 30;

	/** �� */
	public static final int ID_HAKU = 31;
	/** � */
	public static final int ID_HATSU = 32;
	/** �� */
	public static final int ID_CYUN = 33;

	/** �ԍ� */
	private int no;

	/** �� */
	public static final int NO_1 = 1;
	/** �� */
	public static final int NO_2 = 2;
	/** �O */
	public static final int NO_3 = 3;
	/** �l */
	public static final int NO_4 = 4;
	/** �� */
	public static final int NO_5 = 5;
	/** �Z */
	public static final int NO_6 = 6;
	/** �� */
	public static final int NO_7 = 7;
	/** �� */
	public static final int NO_8 = 8;
	/** �� */
	public static final int NO_9 = 9;

	/** ���� */
	public static final int NO_WAN_1 = 1;
	/** ���� */
	public static final int NO_WAN_2 = 2;
	/** �O�� */
	public static final int NO_WAN_3 = 3;
	/** �l�� */
	public static final int NO_WAN_4 = 4;
	/** ���� */
	public static final int NO_WAN_5 = 5;
	/** �Z�� */
	public static final int NO_WAN_6 = 6;
	/** ���� */
	public static final int NO_WAN_7 = 7;
	/** ���� */
	public static final int NO_WAN_8 = 8;
	/** ���� */
	public static final int NO_WAN_9 = 9;

	/** �ꓛ */
	public static final int NO_PIN_1 = 1;
	/** �� */
	public static final int NO_PIN_2 = 2;
	/** �O�� */
	public static final int NO_PIN_3 = 3;
	/** �l�� */
	public static final int NO_PIN_4 = 4;
	/** �ܓ� */
	public static final int NO_PIN_5 = 5;
	/** �Z�� */
	public static final int NO_PIN_6 = 6;
	/** ���� */
	public static final int NO_PIN_7 = 7;
	/** ���� */
	public static final int NO_PIN_8 = 8;
	/** �㓛 */
	public static final int NO_PIN_9 = 9;

	/** ��� */
	public static final int NO_SOU_1 = 1;
	/** ��� */
	public static final int NO_SOU_2 = 2;
	/** �O�� */
	public static final int NO_SOU_3 = 3;
	/** �l�� */
	public static final int NO_SOU_4 = 4;
	/** �܍� */
	public static final int NO_SOU_5 = 5;
	/** �Z�� */
	public static final int NO_SOU_6 = 6;
	/** ���� */
	public static final int NO_SOU_7 = 7;
	/** ���� */
	public static final int NO_SOU_8 = 8;
	/** ��� */
	public static final int NO_SOU_9 = 9;

	/** �� */
	public static final int NO_TON = 1;
	/** �� */
	public static final int NO_NAN = 2;
	/** �� */
	public static final int NO_SHA = 3;
	/** �k */
	public static final int NO_PE = 4;

	/** �� */
	public static final int NO_HAKU = 1;
	/** � */
	public static final int NO_HATSU = 2;
	/** �� */
	public static final int NO_CYUN = 3;

	/** ��� */
	private int kind;

	/** �ݎq */
	public static final int KIND_WAN = 0;
	/** ���q */
	public static final int KIND_PIN = 1;
	/** ���q */
	public static final int KIND_SOU = 2;
	/** ���v */
	public static final int KIND_FON = 3;
	/** �O���v */
	public static final int KIND_SANGEN = 4;

	/** ���v�t���O */
	private boolean tsuu;

	/** ��㎚�v�t���O */
	private boolean yaotyuu;
	
	/**
	 * ��̔v���쐬����B
	 */
	public Hai() {

	}

	/**
	 * �ԍ�����v���쐬����B
	 * 
	 * @param id
	 *            �ԍ�
	 */
	public Hai(int id) {
		this.id = id;

		if (id > ID_PE) {
			this.no = id - ID_PE;
			this.kind = KIND_SANGEN;
			this.tsuu = true;
			this.yaotyuu = true;

			this.oldId = this.no | OLD_KIND_SANGEN;
		} else if (id > ID_SOU_9) {
			this.no = id - ID_SOU_9;
			this.kind = KIND_FON;
			this.tsuu = true;
			this.yaotyuu = true;

			this.oldId = this.no | OLD_KIND_FON;
		} else if (id > ID_PIN_9) {
			this.no = id - ID_PIN_9;
			this.kind = KIND_SOU;
			this.tsuu = false;
			if(this.no == NO_1 || this.no == NO_9){
				this.yaotyuu = true;
			}else{
				this.yaotyuu = false;
			}

			this.oldId = this.no | OLD_KIND_SOU;
		} else if (id > ID_WAN_9) {
			this.no = id - ID_WAN_9;
			this.kind = KIND_PIN;
			this.tsuu = false;
			if(this.no == NO_1 || this.no == NO_9){
				this.yaotyuu = true;
			}else{
				this.yaotyuu = false;
			}

			this.oldId = this.no | OLD_KIND_PIN;
		} else {
			this.no = id + 1;
			this.kind = KIND_WAN;
			this.tsuu = false;
			if(this.no == NO_1 || this.no == NO_9){
				this.yaotyuu = true;
			}else{
				this.yaotyuu = false;
			}

			this.oldId = this.no | OLD_KIND_WAN;
		}
	}

	/**
	 * �v����v���쐬����B
	 * 
	 * @param hai
	 *            �v
	 */
	public Hai(Hai hai) {
		this.id = hai.id;
		this.no = hai.no;
		this.kind = hai.kind;
		this.tsuu = hai.tsuu;
		this.yaotyuu = hai.yaotyuu;

		this.oldId = hai.oldId;
	}

	/**
	 * �v���R�s�[����B
	 * 
	 * @param hai
	 *            �v
	 */
	public void copy(Hai hai) {
		this.id = hai.id;
		this.no = hai.no;
		this.kind = hai.kind;
		this.tsuu = hai.tsuu;
		this.yaotyuu = hai.yaotyuu;

		this.oldId = hai.oldId;
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
		return no;
	}

	/**
	 * ��ނ��擾����B
	 * 
	 * @return ���
	 */
	public int getKind() {
		return kind;
	}

	/**
	 * ���v�t���O���擾����B
	 * 
	 * @return ���v�t���O
	 */
	public boolean isTsuu() {
		return tsuu;
	}

	/**
	 * �t���O���擾����B
	 * 
	 * @return ��㎚�v�t���O
	 */
	public boolean isYaotyuu() {
		return yaotyuu;
	}	
	

	/** �ݎq */
	public final static int OLD_KIND_WAN = 0x00000010;
	/** ���q */
	public final static int OLD_KIND_PIN = 0x00000020;
	/** ���q */
	public final static int OLD_KIND_SOU = 0x00000040;
	/** ���v */
	public final static int OLD_KIND_SHUU = 0x00000070;

	/** ���v */
	public final static int OLD_KIND_FON = 0x00000100;

	/** �� */
	public final static int OLD_KIND_TON = 0x00000101;
	/** �� */
	public final static int OLD_KIND_NAN = 0x00000102;
	/** �� */
	public final static int OLD_KIND_SYA = 0x00000103;
	/** �k */
	public final static int OLD_KIND_PEE = 0x00000104;

	/** �O���v */
	public final static int OLD_KIND_SANGEN = 0x00000200;

	/** �� */
	public final static int OLD_KIND_HAKU = 0x00000201;
	/** �� */
	public final static int OLD_KIND_HATU = 0x00000202;
	/** �� */
	public final static int OLD_KIND_CYUN = 0x00000203;

	/** ���v */
	public final static int OLD_KIND_TSUU = 0x00000300;

	/** �v�̎�ނ��}�X�N���� */
	public final static int OLD_KIND_MASK = 0x0000000F;

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
	private int oldId;

	/**
	 * �v�ԍ����擾����B
	 * 
	 * @return �v�ԍ�
	 */
	public int getOldId() {
		return oldId;
	}
}
