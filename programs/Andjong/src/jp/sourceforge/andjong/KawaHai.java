package jp.sourceforge.andjong;

public class KawaHai extends Hai {
	/** �͔v�̃v���p�e�B */
	private int kawaProperty;

	/**
	 * �͔v�̃v���p�e�B���擾����B
	 * 
	 * @return�@�͔v�̃v���p�e�B
	 */
	public int getKawaProperty() {
		return kawaProperty;
	}

	/**
	 * �͔v�̃v���p�e�B��ݒ肷��B
	 * 
	 * @param kawaProperty
	 *            �@�͔v�̃v���p�e�B
	 */
	void setKawaProperty(int kawaProperty) {
		this.kawaProperty = kawaProperty;
	}

	/**
	 * �͔v�̃v���p�e�B��ǉ�����B
	 * 
	 * @param kawaProperty
	 *            �͔v�̃v���p�e�B
	 */
	void addKawaProperty(int kawaProperty) {
		this.kawaProperty |= kawaProperty;
	}
}
