package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.*;

/**
 * ��v���Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Tehai {
	/** ����v�̍ő吔 */
	private final static int JYUNTEHAI_MAX = 13;

	/**
	 * ����v
	 * <p>
	 * �\�[�g����Ă��܂��B
	 * </p>
	 */
	public Hai[] jyunTehai = new Hai[JYUNTEHAI_MAX];

	/** ����v�̒��� */
	public int jyunTehaiLength;

	/** �����̔z��̒��� */
	public int minshunsLength;

	/** �����̔z�� */
	public Hai[][] minshuns = new Hai[4][3];

	/** �����̔z��̒��� */
	public int minkousLength;

	/** �����̔z�� */
	public Hai[][] minkous = new Hai[4][3];

	/** ���Ȃ̔z��̒��� */
	public int minkansLength;

	/** ���Ȃ̔z�� */
	public Hai[][] minkans = new Hai[4][4];

	/** �ÞȂ̔z��̒��� */
	public int ankansLength;

	/** �ÞȂ̔z�� */
	public Hai[][] ankans = new Hai[4][4];

	{
		for (int i = 0; i < JYUNTEHAI_MAX; i++)
			jyunTehai[i] = new Hai();

		for (int i = 0; i < minshuns.length; i++)
			for (int j = 0; j < minshuns[i].length; j++)
				minshuns[i][j] = new Hai();

		for (int i = 0; i < minkous.length; i++)
			for (int j = 0; j < minkous[i].length; j++)
				minkous[i][j] = new Hai();

		for (int i = 0; i < minkans.length; i++)
			for (int j = 0; j < minkans[i].length; j++)
				minkans[i][j] = new Hai();

		for (int i = 0; i < ankans.length; i++)
			for (int j = 0; j < ankans[i].length; j++)
				ankans[i][j] = new Hai();
	}

	/**
	 * ��v�����������܂��B
	 */
	public void init() {
		jyunTehaiLength = 0;
		minshunsLength = 0;
		minkousLength = 0;
		minkansLength = 0;
		ankansLength = 0;
	}

	/**
	 * ����v�ɔv��ǉ�����B
	 * <p>
	 * �͐s���Ń\�[�g���܂��B<br>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B<br>
	 * TODO �����ƃX�}�[�g�ȕ��@�����肻���ł��B
	 * </p>
	 * 
	 * @param hai
	 *            �ǉ�����v
	 */
	public void addJyunTehai(Hai hai) {
		if (jyunTehaiLength >= JYUNTEHAI_MAX)
			return;

		int i;
		for (i = jyunTehaiLength; i > 0; i--) {
			if (jyunTehai[i - 1].id == hai.id) {
				if (jyunTehai[i - 1].property < (hai.property & PROPERTY_AKA))
					break;
			} else if (jyunTehai[i - 1].id < hai.id)
				break;

			jyunTehai[i].copy(jyunTehai[i - 1]);
		}

		jyunTehai[i].copy(hai);
		jyunTehaiLength++;
	}

	/**
	 * ����v����w��ʒu�̔v���폜����B
	 * 
	 * @param idx
	 *            �w��ʒu
	 */
	public void removeJyunTehai(int idx) {
		if (idx >= JYUNTEHAI_MAX)
			return;

		for (int i = idx; i < jyunTehaiLength - 1; i++) {
			jyunTehai[i].copy(jyunTehai[i + 1]);
		}

		jyunTehaiLength--;
	}

	/**
	 * ������ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 * 
	 * @param minshun
	 *            ����
	 */
	public void addMinshun(Hai[] minshun) {
		if (minshunsLength >= 4)
			return;

		minshuns[minshunsLength][0].copy(minshun[0]);
		minshuns[minshunsLength][1].copy(minshun[1]);
		minshuns[minshunsLength++][2].copy(minshun[2]);
	}

	/**
	 * ������ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 * 
	 * @param minkou
	 *            ����
	 */
	public void addMinkou(Hai[] minkou) {
		if (minkousLength >= 4)
			return;

		minkous[minkousLength][0].copy(minkou[0]);
		minkous[minkousLength][1].copy(minkou[1]);
		minkous[minkousLength++][2].copy(minkou[2]);
	}

	/**
	 * ���Ȃ�ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 * 
	 * @param minkan
	 *            ����
	 */
	public void addMinkan(Hai[] minkan) {
		if (minkansLength >= 4)
			return;

		minkans[minkansLength][0].copy(minkan[0]);
		minkans[minkansLength][1].copy(minkan[1]);
		minkans[minkansLength][2].copy(minkan[2]);
		minkans[minkansLength++][3].copy(minkan[3]);
	}

	/**
	 * �ÞȂ�ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 * 
	 * @param ankan
	 *            �Þ�
	 */
	public void addankan(Hai[] ankan) {
		if (ankansLength >= 4)
			return;

		ankans[ankansLength][0].copy(ankan[0]);
		ankans[ankansLength][1].copy(ankan[1]);
		ankans[ankansLength][2].copy(ankan[2]);
		ankans[ankansLength++][3].copy(ankan[3]);
	}
}
