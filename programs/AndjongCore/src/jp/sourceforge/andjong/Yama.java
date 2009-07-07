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

	/** �c���ʒu�̍Ō� */
	private final static int TSUMO_END = 122;

	/** �c���ʒu */
	private int tsumoIdx;

	/** �����V�����c���ʒu�̍Ō� */
	private final static int RINSHAN_END = 126;

	/** �����V�����c���ʒu */
	private int rinshanIdx = TSUMO_END;

	/**
	 * �R�����������܂��B
	 */
	public Yama() {
		init();
	}

	/**
	 * �R�����������܂��B
	 * <p>
	 * �͐s���ŏ��������܂��B
	 * </p>
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
	 * <p>
	 * �����_���œ���ւ��܂��B
	 * </p>
	 */
	void xipai() {
		Random random = new Random();
		Hai temp;

		for (int i = 0, j, size = YAMA_MAX; i < YAMA_MAX; i++) {
			j = random.nextInt(size);
			temp = hais[i];
			hais[i] = hais[j];
			hais[j] = temp;
		}

		tsumoIdx = 0;
		rinshanIdx = TSUMO_END;
	}

	/**
	 * �c�����܂��B
	 * 
	 * @return �c���v
	 */
	Hai tsumo() {
		if (tsumoIdx < TSUMO_END)
			return new Hai(hais[tsumoIdx++]);

		return null;
	}

	/**
	 * �����V�����c�����܂��B
	 * 
	 * @return �����V�����c���v
	 */
	Hai rinshan() {
		if (rinshanIdx < RINSHAN_END)
			return new Hai(hais[rinshanIdx++]);

		return null;
	}

	/**
	 * �\�h���A�ȃh���̔z����擾���܂��B
	 * 
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getDora() {
		int doraNum = 1 + (rinshanIdx - TSUMO_END);
		Hai[] dora = new Hai[doraNum];

		for (int i = 0, doraIdx = RINSHAN_END; i < doraNum; i++, doraIdx += 2)
			dora[i] = new Hai(hais[doraIdx]);

		return dora;
	}

	/**
	 * �\�h���A�ȃh���A���h���A�ȃE���̔z����擾���܂��B
	 * 
	 * @return �\�h���A�ȃh���A���h���A�ȃE���̔z��
	 */
	Hai[] getDoraAll() {
		int doraNum = (1 + (rinshanIdx - TSUMO_END)) * 2;
		Hai[] dora = new Hai[doraNum];

		for (int i = 0, doraIdx = RINSHAN_END; i < doraNum; i++, doraIdx++)
			dora[i] = new Hai(hais[doraIdx]);

		return dora;
	}

	/**
	 * �c���̎c�萔���擾���܂��B
	 * 
	 * @return �c���̎c�萔
	 */
	int getTsumoRemain() {
		return TSUMO_END - tsumoIdx;
	}
}
