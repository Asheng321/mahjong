package jp.sourceforge.andjong;

/**
 * �͂��Ǘ�����N���X
 *
 * @author Yuji Urushibara
 *
 */
public class Kawa {
	/** �̔v�̒����̍ő�l */
	public final static int SUTEHAIS_LENGTH_MAX = 32;

	/** �̔v */
	private SuteHai[] suteHais = new SuteHai[SUTEHAIS_LENGTH_MAX];

	/** �̔v�̒��� */
	private int suteHaisLength;

	{
		for (int i = 0; i < SUTEHAIS_LENGTH_MAX; i++) {
			suteHais[i] = new SuteHai();
		}
	}

	/**
	 * �͂�����������B
	 */
	public void initialize() {
		suteHaisLength = 0;
	}

	/**
	 * �̔v�ɔv��ǉ�����B
	 *
	 * @param hai
	 *            �ǉ�����v
	 */
	public boolean add(Hai hai) {
		if (suteHaisLength >= SUTEHAIS_LENGTH_MAX) {
			return false;
		}

		Hai.copy(suteHais[suteHaisLength++], hai);
		return true;
	}

	/**
	 * �̔v�̍Ō�̔v�ɁA���t���O��ݒ肷��B
	 *
	 * @param naki
	 *            ���t���O
	 */
	public boolean setNaki(boolean naki) {
		if (suteHaisLength <= 0) {
			return false;
		}

		suteHais[suteHaisLength - 1].setNaki(naki);
		return true;
	}

	/**
	 * �̔v�̍Ō�̔v�ɁA���[�`�t���O��ݒ肷��B
	 *
	 * @param reach
	 *            ���[�`�t���O
	 */
	public boolean setReach(boolean reach) {
		if (suteHaisLength <= 0) {
			return false;
		}

		suteHais[suteHaisLength - 1].setReach(reach);
		return true;
	}

	/**
	 * �̔v�̍Ō�̔v�ɁA��o���t���O��ݒ肷��B
	 *
	 * @param tedashi
	 *            ��o���t���O
	 */
	public boolean setTedashi(boolean tedashi) {
		if (suteHaisLength <= 0) {
			return false;
		}

		suteHais[suteHaisLength - 1].setTedashi(tedashi);
		return true;
	}

	/**
	 * �̓I�u�W�F�N�g���R�s�[����B
	 *
	 * @param kawa
	 *            ��
	 */
	void copy(Kawa kawa) {
		this.suteHaisLength = kawa.copySuteHai(this.suteHais);
	}

	/**
	 * �͂��擾����B
	 *
	 * @return ��
	 */
	SuteHai[] getSuteHais() {
		return suteHais;
	}

	/**
	 * �͂̒������擾����B
	 *
	 * @return �͂̒���
	 */
	int getSuteHaiLength() {
		return suteHaisLength;
	}

	/**
	 * �͂��R�s�[����B
	 *
	 * @param SuteHais
	 *            ��
	 * @return �͂̒���
	 */
	int copySuteHai(SuteHai[] SuteHais) {
		for (int i = 0; i < this.suteHaisLength; i++)
			Hai.copy(SuteHais[i], this.suteHais[i]);
		return this.suteHaisLength;
	}
}
