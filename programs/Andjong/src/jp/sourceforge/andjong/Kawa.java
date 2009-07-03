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
	public Hai[] hais = new Hai[KAWA_MAX];

	/** �͂̒��� */
	public int kawaLength;

	{
		for (int i = 0; i < hais.length; i++)
			hais[i] = new Hai();
	}

	/**
	 * �͂�����������B
	 */
	public void init() {
		kawaLength = 0;
	}

	/**
	 * �͂ɔv��ǉ�����B
	 * 
	 * @param addHai
	 *            �ǉ�����v
	 */
	public void add(Hai addHai) {
		hais[kawaLength++].copy(addHai);
	}

	/**
	 * �͂ɔv��ǉ�����B
	 * 
	 * @param addHai
	 *            �ǉ�����v
	 * @param property
	 *            �ǉ�����v���p�e�B
	 */
	public void add(Hai addHai, int property) {
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
	public void addProperty(int property) {
		hais[kawaLength].property |= property;
	}
}
