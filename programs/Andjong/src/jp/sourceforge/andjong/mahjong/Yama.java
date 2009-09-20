package jp.sourceforge.andjong.mahjong;

import java.util.Random;

/**
 * �R���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Yama {
	/** �R�v�̍ő吔 */
	private final static int YAMAHAIS_MAX = 136;

	/** �R�v�̔z�� */
	private Hai[] yamaHais = new Hai[YAMAHAIS_MAX];

	/** �c���v�̍ő吔 */
	private final static int TSUMO_HAIS_MAX = 122;

	/** �c���v�̔z�� */
	private Hai[] tsumoHais = new Hai[TSUMO_HAIS_MAX];

	/** �c���v�̈ʒu */
	private int tsumoHaisIdx;

	/** �����V�����v�̍ő吔 */
	private final static int RINSHAN_HAIS_MAX = 4;

	/** �����V�����v�̔z�� */
	private Hai[] rinshanHais = new Hai[RINSHAN_HAIS_MAX];

	/** �����V�����v�̈ʒu */
	private int rinshanHaisIdx;

	/** �e�h���v�̍ő吔 */
	private final static int DORA_HAIS_MAX = RINSHAN_HAIS_MAX + 1;

	/** �\�h���v�̔z�� */
	private Hai[] omoteDoraHais = new Hai[DORA_HAIS_MAX];

	/** ���h���v�̔z�� */
	private Hai[] uraDoraHais = new Hai[DORA_HAIS_MAX];

	/**
	 * �R���쐬����B
	 */
	Yama() {
		initialize();
		setTsumoHaisStartIdx(0);
	}

	/**
	 * �R�v������������B
	 */
	private void initialize() {
		for (int id = Hai.ID_WAN_1, idx = 0; id <= Hai.ID_CHUN; id++) {
			for (int i = 0; i < 4; i++, idx++) {
				yamaHais[idx] = new Hai(id);
			}
		}
	}

	/**
	 * ���v����B
	 */
	void xipai() {
		Random random = new Random();
		Hai temp;

		for (int i = 0, j; i < YAMAHAIS_MAX; i++) {
			j = random.nextInt(YAMAHAIS_MAX);
			temp = yamaHais[i];
			yamaHais[i] = yamaHais[j];
			yamaHais[j] = temp;
		}
	}

	/**
	 * �c���v���擾����B
	 *
	 * @return �c���v
	 */
	Hai tsumo() {
		if (tsumoHaisIdx >= TSUMO_HAIS_MAX) {
			return null;
		}

		Hai tsumoHai = new Hai(tsumoHais[tsumoHaisIdx]);
		tsumoHaisIdx++;

		return tsumoHai;
	}

	/**
	 * �����V�����v���擾����B
	 *
	 * @return �����V�����v
	 */
	Hai rinshanTsumo() {
		if (rinshanHaisIdx >= RINSHAN_HAIS_MAX) {
			return null;
		}

		Hai rinshanHai = new Hai(rinshanHais[rinshanHaisIdx]);
		rinshanHaisIdx++;

		return rinshanHai;
	}

	/**
	 * �\�h���̔z����擾����B
	 *
	 * @return �\�h���̔z��
	 */
	Hai[] getOmoteDoraHais() {
		int omoteDoraHaisLength = rinshanHaisIdx + 1;
		Hai[] omoteDoraHais = new Hai[omoteDoraHaisLength];

		for (int i = 0; i < omoteDoraHaisLength; i++) {
			omoteDoraHais[i] = new Hai(this.omoteDoraHais[i]);
		}

		return omoteDoraHais;
	}

	/**
	 * ���h���̔z����擾����B
	 *
	 * @return ���h���̔z��
	 */
	Hai[] getUraDoraHais() {
		int uraDoraHaisLength = rinshanHaisIdx + 1;
		Hai[] uraDoraHais = new Hai[uraDoraHaisLength];

		for (int i = 0; i < uraDoraHaisLength; i++) {
			uraDoraHais[i] = new Hai(this.uraDoraHais[i]);
		}

		return uraDoraHais;
	}

	/**
	 * �c���v�̊J�n�ʒu��ݒ肷��B
	 *
	 * @param tsumoHaiStartIdx
	 *            �c���v�̊J�n�ʒu
	 */
	boolean setTsumoHaisStartIdx(int tsumoHaiStartIdx) {
		if (tsumoHaiStartIdx >= YAMAHAIS_MAX) {
			return false;
		}

		int yamaHaisIdx = tsumoHaiStartIdx;

		for (int i = 0; i < TSUMO_HAIS_MAX; i++) {
			tsumoHais[i] = yamaHais[yamaHaisIdx];

			yamaHaisIdx++;
			if (yamaHaisIdx >= YAMAHAIS_MAX) {
				yamaHaisIdx = 0;
			}
		}

		for (int i = 0; i < RINSHAN_HAIS_MAX; i++) {
			rinshanHais[i] = yamaHais[yamaHaisIdx];

			yamaHaisIdx++;
			if (yamaHaisIdx >= YAMAHAIS_MAX) {
				yamaHaisIdx = 0;
			}
		}

		for (int i = 0; i < DORA_HAIS_MAX; i++) {
			omoteDoraHais[i] = yamaHais[yamaHaisIdx];

			yamaHaisIdx++;
			if (yamaHaisIdx >= YAMAHAIS_MAX) {
				yamaHaisIdx = 0;
			}

			uraDoraHais[i] = yamaHais[yamaHaisIdx];

			yamaHaisIdx++;
			if (yamaHaisIdx >= YAMAHAIS_MAX) {
				yamaHaisIdx = 0;
			}
		}

		tsumoHaisIdx = 0;
		rinshanHaisIdx = 0;

		return true;
	}

	/**
	 * �c���v�̎c�萔���擾����B
	 *
	 * @return �c���v�̎c�萔
	 */
	int getTsumoNokori() {
		return TSUMO_HAIS_MAX - tsumoHaisIdx;
	}
}
