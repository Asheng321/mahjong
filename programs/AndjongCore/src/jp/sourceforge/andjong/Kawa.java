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
	public final static int KAWA_MAX = 25;

	/** �� */
	private KawaHai[] kawaHais = new KawaHai[KAWA_MAX];

	/** �͂̒��� */
	private int kawaLength;

	{
		for (int i = 0; i < kawaHais.length; i++)
			kawaHais[i] = new KawaHai();
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
		kawaHais[kawaLength].copy(addHai);
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
		kawaHais[kawaLength].copy(addHai);
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
		kawaHais[kawaLength].addKawaProperty(property);
	}

	/**
	 * �̓I�u�W�F�N�g���R�s�[����B
	 * 
	 * @param kawa
	 *            ��
	 */
	void copy(Kawa kawa) {
		this.kawaLength = kawa.copyKawaHai(this.kawaHais);
	}

	/**
	 * �͂��擾����B
	 * 
	 * @return ��
	 */
	KawaHai[] getKawaHai() {
		return kawaHais;
	}

	/**
	 * �͂̒������擾����B
	 * 
	 * @return �͂̒���
	 */
	int getKawaHaiLength() {
		return kawaLength;
	}

	/**
	 * �͂��R�s�[����B
	 * 
	 * @param kawaHais
	 *            ��
	 * @return �͂̒���
	 */
	int copyKawaHai(KawaHai[] kawaHais) {
		for (int i = 0; i < this.kawaLength; i++)
			kawaHais[i].copy(this.kawaHais[i]);
		return this.kawaLength;
	}
}
