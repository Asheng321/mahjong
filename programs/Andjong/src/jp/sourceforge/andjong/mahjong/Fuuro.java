package jp.sourceforge.andjong.mahjong;

/**
 * ���I���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Fuuro {
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
	private Hai hais[] = new Hai[Mahjong.MENTSU_HAI_MEMBERS_4];

	{
		for (int i = 0; i < hais.length; i++) {
			hais[i] = new Hai();
		}
	}

	/**
	 * �\���v��ݒ肷��B
	 *
	 * @param hais
	 *            �\���v
	 */
	public void setHai(
			Hai hais[]) {
		this.hais = hais;
	}

	/**
	 * �\���v���擾����B
	 *
	 * @return �\���v
	 */
	public Hai[] getHai() {
		return hais;
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

	/**
	 * ���I���R�s�[����B
	 *
	 * @param destFuuro
	 *            �R�s�[��̕��I
	 * @param srcFuuro
	 *            �R�s�[���̕��I
	 */
	public static void copy(
			Fuuro destFuuro,
			Fuuro srcFuuro) {
		destFuuro.type = srcFuuro.type;

		for (int i = 0; i < Mahjong.MENTSU_HAI_MEMBERS_4; i++) {
			Hai.copy(destFuuro.hais[i], srcFuuro.hais[i]);
		}

		destFuuro.relation = destFuuro.relation;
	}
}
