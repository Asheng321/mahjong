package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.*;

import java.util.Random;

/**
 * �R���Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Yama {
	/** �R�v�̍ő吔 */
	private final static int YAMA_MAX = 136;

	/** �R�v */
	private Hai[] hais = new Hai[YAMA_MAX];

	/** �c���̍ő吔 */
	private final static int TSUMO_MAX = 122;

	/** �c���� */
	private int tsumoCount;

	/** �c���ʒu */
	private int tsumoIdx;

	/** �����V�����c���̍ő吔 */
	private final static int RINSHAN_MAX = 4;

	/** �����V�����c���� */
	private int rinshanCount;

	/** �����V�����c���ʒu */
	private int rinshanIdx = TSUMO_MAX;

	/**
	 * �R�����������܂��B
	 */
	public Yama() {
		init();
	}

	/**
	 * �R�����������܂��B
	 */
	private void init() {
		int haiIdx = 0;
		int id;
		int max;
		int loop;

		for (id = KIND_WAN + 1, max = id + 9; id < max; id++)
			for (loop = 0; loop < 4; loop++, haiIdx++)
				hais[haiIdx] = new Hai(id);

		for (id = KIND_PIN + 1, max = id + 9; id < max; id++)
			for (loop = 0; loop < 4; loop++, haiIdx++)
				hais[haiIdx] = new Hai(id);

		for (id = KIND_SOU + 1, max = id + 9; id < max; id++)
			for (loop = 0; loop < 4; loop++, haiIdx++)
				hais[haiIdx] = new Hai(id);

		for (id = KIND_FON + 1, max = id + 4; id < max; id++)
			for (loop = 0; loop < 4; loop++, haiIdx++)
				hais[haiIdx] = new Hai(id);

		for (id = KIND_SANGEN + 1, max = id + 3; id < max; id++)
			for (loop = 0; loop < 4; loop++, haiIdx++)
				hais[haiIdx] = new Hai(id);
	}

	/**
	 * ���v�����܂��B
	 */
	void xipai() {
		Random random = new Random();
		Hai temp;

		// �����_���œ���ւ��܂��B
		for (int i = 0, j, size = YAMA_MAX; i < YAMA_MAX; i++) {
			j = random.nextInt(size);
			temp = hais[i];
			hais[i] = hais[j];
			hais[j] = temp;
		}

		tsumoCount = 0;
		rinshanCount = 0;
	}

	/**
	 * �c�����܂��B
	 * 
	 * @return �c���v
	 */
	Hai tsumo() {
		if (tsumoCount >= TSUMO_MAX) {
			return null;
		}
		tsumoCount++;

		tsumoIdx++;
		if (tsumoIdx >= YAMA_MAX) {
			tsumoIdx = 0;
		}

		return new Hai(hais[tsumoIdx]);
	}

	/**
	 * �����V�����c�����܂��B
	 * 
	 * @return �����V�����c���v
	 */
	Hai rinshan() {
		if (rinshanCount >= RINSHAN_MAX) {
			return null;
		}
		rinshanCount++;

		rinshanIdx++;
		if (rinshanIdx >= YAMA_MAX) {
			rinshanIdx = 0;
		}

		return new Hai(hais[rinshanIdx]);
	}

	/**
	 * �\�h���A�ȃh���̔z����擾���܂��B
	 * 
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getDoras() {
		int doraNum = 1 + rinshanCount;
		Hai[] dora = new Hai[doraNum];

		for (int i = 0, doraIdx = rinshanIdx; i < doraNum; i++, doraIdx += 2) {
			if (doraIdx >= YAMA_MAX) {
				doraIdx = 0;
			}
			dora[i] = new Hai(hais[doraIdx]);
		}

		return dora;
	}

	/**
	 * �\�h���A�ȃh���A���h���A�ȃE���̔z����擾���܂��B
	 * 
	 * @return �\�h���A�ȃh���A���h���A�ȃE���̔z��
	 */
	Hai[] getDorasAll() {
		int doraNum = (1 + rinshanCount) * 2;
		Hai[] dora = new Hai[doraNum];

		for (int i = 0, doraIdx = rinshanIdx; i < doraNum; i++, doraIdx++) {
			if (doraIdx >= YAMA_MAX) {
				doraIdx = 0;
			}
			dora[i] = new Hai(hais[doraIdx]);
		}

		return dora;
	}

	/**
	 * �c���̎c�萔���擾���܂��B
	 * 
	 * @return �c���̎c�萔
	 */
	int getTsumoRemain() {
		return TSUMO_MAX - tsumoCount;
	}

	/**
	 * �R�Ɋ���ڂ�ݒ肵�܂��B
	 * 
	 * @param sais
	 *            �T�C�R���̔z��
	 */
	void setWareme(Sai[] sais) {
		int sum = sais[0].getNo() + sais[1].getNo() - 1;
		tsumoIdx = ((sum % 4) * 36) + sum;

		rinshanIdx = tsumoIdx + TSUMO_MAX;
		if (rinshanIdx >= YAMA_MAX) {
			rinshanIdx -= YAMA_MAX;
		}
	}
}
