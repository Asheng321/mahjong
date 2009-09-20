package jp.sourceforge.andjong.mahjong;

/**
 * �͂��Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Kawa {
	/**
	 * �̔v�̔z��̒����̍ő�l
	 * <p>
	 * TODO ���_��̍ő�l���s���ł��B
	 * </p>
	 */
	public final static int SUTEHAIS_LENGTH_MAX = 32;

	/** �̔v�̔z�� */
	private SuteHai[] suteHais = new SuteHai[SUTEHAIS_LENGTH_MAX];

	/** �̔v�̔z��̒��� */
	private int suteHaisLength;

	/**
	 * �͂��쐬����B
	 */
	public Kawa() {
		for (int i = 0; i < SUTEHAIS_LENGTH_MAX; i++) {
			suteHais[i] = new SuteHai();
		}

		initialize();
	}

	/**
	 * �͂�����������B
	 */
	public void initialize() {
		suteHaisLength = 0;
	}

	/**
	 * �͂��R�s�[����B
	 *
	 * @param destKawa
	 *            �R�s�[��̉�
	 * @param srcKawa
	 *            �R�s�[���̉�
	 */
	public static void copy(Kawa destKawa, Kawa srcKawa) {
		destKawa.suteHaisLength = srcKawa.suteHaisLength;
		copySuteHai(destKawa.suteHais, srcKawa.suteHais,
				destKawa.suteHaisLength);
	}

	/**
	 * �̔v�̔z����擾����B
	 *
	 * @return �̔v�̔z��
	 */
	public SuteHai[] getSuteHais() {
		return suteHais;
	}

	/**
	 * �̔v�̔z��̒������擾����B
	 *
	 * @return �̔v�̔z��̒���
	 */
	public int getSuteHaisLength() {
		return suteHaisLength;
	}

	/**
	 * �̔v�̔z��ɔv��ǉ�����B
	 *
	 * @param hai
	 *            �ǉ�����v
	 */
	public boolean add(Hai hai) {
		if (suteHaisLength >= SUTEHAIS_LENGTH_MAX) {
			return false;
		}

		Hai.copy(suteHais[suteHaisLength], hai);
		suteHaisLength++;

		return true;
	}

	/**
	 * �̔v�̔z��̍Ō�̔v�ɁA���t���O��ݒ肷��B
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
	 * �̔v�̔z��̍Ō�̔v�ɁA���[�`�t���O��ݒ肷��B
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
	 * �̔v�̔z��̍Ō�̔v�ɁA��o���t���O��ݒ肷��B
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
	 * �̔v�̔z����R�s�[����B
	 *
	 * @param destSuteHais
	 *            �R�s�[��̎̔v�̔z��
	 * @param srcSuteHais
	 *            �R�s�[���̎̔v�̔z��
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copySuteHai(SuteHai[] destSuteHais,
			SuteHai[] srcSuteHais, int length) {
		if (length >= SUTEHAIS_LENGTH_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			Hai.copy(destSuteHais[i], srcSuteHais[i]);
		}

		return true;
	}
}
