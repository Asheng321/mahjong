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
	public Hai[] hai = new Hai[KAWA_MAX];

	/** �͂̒��� */
	public int kawaLength;

	{
		for (int i = 0; i < hai.length; i++)
			hai[i] = new Hai(0);
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
		hai[kawaLength].id = addHai.id;
		hai[kawaLength++].property = addHai.property;
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
		hai[kawaLength].id = addHai.id;
		hai[kawaLength++].property = addHai.property | property;
	}

	/**
	 * �͂̍Ō�̔v�Ƀv���p�e�B��ǉ�����B
	 * 
	 * @param property
	 *            �ǉ�����v���p�e�B
	 */
	public void addProperty(int property) {
		hai[kawaLength].property |= property;
	}
}
