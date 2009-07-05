package jp.sourceforge.andjong;

/**
 * �͂��Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Kawa {
	/** �v���p�e�B�i���[�`�j */
	public final static int PROPERTY_REACH = 0x00000002;
	/** �v���p�e�B�i���j */
	public final static int PROPERTY_NAKI = 0x00000004;
	/** �v���p�e�B�i��o���j */
	public final static int PROPERTY_TEDASHI = 0x00000008;

	/** �͂̍ő吔 */
	private final static int KAWA_MAX = 25;

	/** �� */
	private KawaHai[] hais = new KawaHai[KAWA_MAX];

	/** �͂̒��� */
	private int kawaLength;

	{
		for (int i = 0; i < hais.length; i++)
			hais[i] = new KawaHai();
	}

	/**
	 * �͂�����������B
	 */
	void init() {
		kawaLength = 0;
	}

	/**
	 * �͂ɔv��ǉ�����B
	 * 
	 * @param addHai
	 *            �ǉ�����v
	 */
	void add(Hai addHai) {
		hais[kawaLength].copy(addHai);
		kawaLength++;
	}

	/**
	 * �͂ɔv��ǉ�����B
	 * 
	 * @param addHai
	 *            �ǉ�����v
	 * @param property
	 *            �ǉ�����v���p�e�B
	 */
	void add(Hai addHai, int property) {
		hais[kawaLength].copy(addHai);
		addProperty(property);
		kawaLength++;
	}

	/**
	 * �͂̍Ō�̔v�Ƀv���p�e�B��ǉ�����B
	 * 
	 * @param property
	 *            �ǉ�����v���p�e�B
	 */
	void addProperty(int property) {
		hais[kawaLength].addKawaProperty(property);
	}

	/**
	 * �͂��R�s�[����B
	 * 
	 * @param kawa
	 *            ��
	 */
	void copyKawa(Kawa kawa) {
		this.kawaLength = kawa.kawaLength;
		for (int i = 0; i < kawa.kawaLength; i++)
			this.hais[i].copy(kawa.hais[i]);
	}
}
