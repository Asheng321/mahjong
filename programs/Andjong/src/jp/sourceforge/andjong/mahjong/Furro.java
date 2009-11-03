package jp.sourceforge.andjong.mahjong;

/**
 * ���I���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Furro {
	/** ��� ���� */
	public static int TYPE_MINSHUN = 0;
	/** ��� ���� */
	public static int TYPE_MINKOU = 1;
	/** ��� �喾�� */
	public static int TYPE_DAIMINKAN = 2;
	/** ��� ���� */
	public static int TYPE_KAKAN = 3;
	/** ��� �Þ� */
	public static int TYPE_ANKAN = 4;
	/** ��� */
	private int type;

	/**
	 * ��ʂ�ݒ肷��B
	 *
	 * @param type
	 *            ���
	 */
	public void setType(
			int type) {
		this.type = type;
	}

	/**
	 * ��ʂ��擾����B
	 *
	 * @return ���
	 */
	public int getType() {
		return type;
	}

	/** �\���v */
	private Hai hai[] = new Hai[Mahjong.MENTSU_HAI_MEMBERS_4];

	/**
	 * �\���v��ݒ肷��B
	 *
	 * @param hai
	 *            �\���v
	 */
	public void setHai(
			Hai hai[]) {
		this.hai = hai;
	}

	/**
	 * �\���v���擾����B
	 *
	 * @return �\���v
	 */
	public Hai[] getHai() {
		return hai;
	}

	/** ���ƂƂ̊֌W */
	private int relation;

	/**
	 * ���ƂƂ̊֌W��ݒ肷��B
	 *
	 * @param relation
	 *            ���ƂƂ̊֌W
	 */
	public void setRelation(
			int relation) {
		this.relation = relation;
	}

	/**
	 * ���ƂƂ̊֌W���擾����B
	 *
	 * @return ���ƂƂ̊֌W
	 */
	public int getRelation() {
		return relation;
	}
}
