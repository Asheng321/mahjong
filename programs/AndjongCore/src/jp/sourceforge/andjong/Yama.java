package jp.sourceforge.andjong;

import java.util.Random;

/**
 * �R���Ǘ�����N���X
 * 
 * @author Yuji Urushibara
 * 
 */
public class Yama {
	/** �v�̐� */
	private final static int HAIS_NUM = 136;

	/** �v */
	private Hai[] hais = new Hai[HAIS_NUM];

	/** �c���v�̐� */
	private final static int TSUMO_HAIS_NUM = 122;

	/** �c���v */
	private Hai[] tsumoHais = new Hai[TSUMO_HAIS_NUM];

	/** �c���v�̈ʒu */
	private int tsumoHaisIdx;

	/** �����V�����v�̐� */
	private final static int RINSHAN_HAIS_MAX = 4;

	/** �����V�����v */
	private Hai[] rinshanHais = new Hai[RINSHAN_HAIS_MAX];

	/** �����V�����v�̈ʒu */
	private int rinshanHaisIdx;

	/** �h���v�̐� */
	private final static int DORA_HAIS_MAX = RINSHAN_HAIS_MAX + 1;

	/** �h���v */
	private Hai[] doraHais = new Hai[DORA_HAIS_MAX];

	/** ���h���v */
	private Hai[] uraDoraHais = new Hai[DORA_HAIS_MAX];

	/**
	 * �R���쐬����B
	 */
	Yama() {
		initialize();
	}

	/**
	 * �v������������B
	 */
	private void initialize() {
		for (int id = Hai.ID_WAN_1, idx = 0; id <= Hai.ID_CYUN; id++) {
			for (int i = 0; i < 4; i++, idx++) {
				hais[idx] = new Hai(id);
			}
		}
	}

	/**
	 * ���v����B
	 */
	void xipai() {
		Random random = new Random();
		Hai temp;

		for (int i = 0, j; i < HAIS_NUM; i++) {
			j = random.nextInt(HAIS_NUM);
			temp = hais[i];
			hais[i] = hais[j];
			hais[j] = temp;
		}
	}

	/**
	 * �c���v���擾����B
	 * 
	 * @return �c���v
	 */
	Hai tsumo() {
		if (tsumoHaisIdx >= TSUMO_HAIS_NUM) {
			return null;
		}
		return new Hai(tsumoHais[tsumoHaisIdx++]);
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
		return new Hai(rinshanHais[rinshanHaisIdx++]);
	}

	/**
	 * �\�h���A�ȃh���̔z����擾����B
	 * 
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getDoraHais() {
		int doraHaisLength = rinshanHaisIdx + 1;
		Hai[] doraHais = new Hai[doraHaisLength];

		for (int i = 0; i < doraHaisLength; i++) {
			doraHais[i] = new Hai(this.doraHais[i]);
		}

		return doraHais;
	}

	/**
	 * ���h���A�ȃE���̔z����擾����B
	 * 
	 * @return ���h���A�ȃE���̔z��
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
	 * �c���v�̊J�n�ʒu��ݒ肵�܂��B
	 * 
	 * @param startTsumoHaiIdx
	 *            �c���v�̊J�n�ʒu
	 */
	void setStartTsumoHaisIdx(int startTsumoHaiIdx) {
		int haisIdx = startTsumoHaiIdx;

		for (int tsumoIdx = 0; tsumoIdx < TSUMO_HAIS_NUM; tsumoIdx++) {
			if (haisIdx >= HAIS_NUM) {
				haisIdx = 0;
			}
			tsumoHais[tsumoIdx] = hais[haisIdx++];
		}

		for (int rinshanHaisIdx = 0; rinshanHaisIdx < RINSHAN_HAIS_MAX; rinshanHaisIdx++) {
			if (haisIdx >= HAIS_NUM) {
				haisIdx = 0;
			}
			rinshanHais[rinshanHaisIdx] = hais[haisIdx++];
		}

		for (int doraHaisIdx = 0; doraHaisIdx < DORA_HAIS_MAX; doraHaisIdx++) {
			if (haisIdx >= HAIS_NUM) {
				haisIdx = 0;
			}
			doraHais[doraHaisIdx] = hais[haisIdx++];

			if (haisIdx >= HAIS_NUM) {
				haisIdx = 0;
			}
			uraDoraHais[doraHaisIdx] = hais[haisIdx++];
		}
	}

	/**
	 * �c���̎c�萔���擾����B
	 * 
	 * @return �c���̎c�萔
	 */
	int getTsumoNokori() {
		return TSUMO_HAIS_NUM - tsumoHaisIdx;
	}

	/**
	 * �R�Ɋ���ڂ�ݒ肵�܂��B
	 * 
	 * @param sais
	 *            �T�C�R���̔z��
	 */
	void setWareme(Sai[] sais) {
		int sum = sais[0].getNo() + sais[1].getNo() - 1;
		tsumoHaisIdx = ((sum % 4) * 36) + sum;

		rinshanHaisIdx = tsumoHaisIdx + TSUMO_HAIS_NUM;
		if (rinshanHaisIdx >= HAIS_NUM) {
			rinshanHaisIdx -= HAIS_NUM;
		}

		tsumoHaisIdx = 0;
		rinshanHaisIdx = 0;

		{
			int i;
			for (i = 0; i < TSUMO_HAIS_NUM; i++) {
				tsumoHais[i] = hais[i];
			}

			for (; i < TSUMO_HAIS_NUM + RINSHAN_HAIS_MAX; i++) {
				rinshanHais[i - TSUMO_HAIS_NUM] = hais[i];
			}

			for (int j = 0; j < DORA_HAIS_MAX; j++) {
				doraHais[j] = hais[i++];
				uraDoraHais[j] = hais[i++];
			}
		}
		setStartTsumoHaisIdx(0);
		setStartTsumoHaisIdx(120);
	}
}
