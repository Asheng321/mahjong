package jp.sourceforge.andjong;

import android.content.res.Resources;
import jp.sourceforge.andjong.mahjong.Hai;
import jp.sourceforge.andjong.mahjong.Kawa;
import jp.sourceforge.andjong.mahjong.Mahjong;
import jp.sourceforge.andjong.mahjong.Tehai;
import jp.sourceforge.andjong.mahjong.EventIF.EID;

/**
 * �`��A�C�e�����Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class DrawItem {
	/** �������҂� */
	public static final int STATE_INIT_WAIT = 0;
	/** ��ԂȂ� */
	public static final int STATE_NONE = 1;
	/** �ǂ̊J�n */
	public static final int STATE_KYOKU_START = 2;
	/** �v���C */
	public static final int STATE_PLAY = 3;
	public static final int STATE_SUTEHAI_MACHI = 4;
	/** ���� */
	public static final int STATE_RYUUKYOKU = 5;

	/** ��� */
	int mState = STATE_INIT_WAIT;

	/** �ǂ̕����� */
	private String mKyokuString = null;

	/** ���[�`�_�̐� */
	private int mReachbou = 0;

	/** �{�� */
	private int mHonba = 0;

	/** �N�� */
	private int mChiicha = 0;

	/**
	 * �ǂ̕������ݒ肷��B
	 *
	 * @param kyoku
	 *            ��
	 */
	public synchronized void setKyokuString(Resources resources, int kyoku) {
		if (kyoku > Mahjong.KYOKU_TON_4) {
			mKyokuString = null;
			return;
		}

		String[] kyokuStrings = resources.getStringArray(R.array.kyoku);
		mKyokuString = kyokuStrings[kyoku];
	}

	/**
	 * �ǂ̕�������擾����B
	 *
	 * @return �ǂ̕�����
	 */
	public synchronized String getKyokuString() {
		return mKyokuString;
	}

	/**
	 * ���[�`�_�̐���ݒ肷��B
	 *
	 * @param reachbou
	 *            ���[�`�_�̐�
	 */
	public synchronized void setReachbou(int reachbou) {
		this.mReachbou = reachbou;
	}

	/**
	 * ���[�`�_�̐����擾����B
	 *
	 * @return ���[�`�_�̐�
	 */
	public synchronized int getReachbou() {
		return mReachbou;
	}

	/**
	 * �{���ݒ肷��B
	 *
	 * @param honba
	 *            �{��
	 */
	public synchronized void setHonba(int honba) {
		this.mHonba = honba;
	}

	/**
	 * �{����擾����B
	 *
	 * @return �{��
	 */
	public synchronized int getHonba() {
		return mHonba;
	}

	/**
	 * �N�Ƃ�ݒ肷��B
	 *
	 * @param chiicha
	 *            �N��
	 */
	public synchronized void setChiicha(int chiicha) {
		this.mChiicha = chiicha;
	}

	/**
	 * �N�Ƃ��擾����B
	 *
	 * @return �N��
	 */
	public synchronized int getChiicha() {
		return mChiicha;
	}

	/**
	 * ��Ԃ�ݒ肷��B
	 *
	 * @param state
	 *            ���
	 */
	synchronized void setState(int state) {
		this.mState = state;
	}

	/**
	 * ��Ԃ��擾����B
	 *
	 * @return ���
	 */
	synchronized int getState() {
		return mState;
	}

	class PlayerInfo {
		/** ��v */
		Tehai mTehai = new Tehai();
		Kawa mKawa = new Kawa();
		Hai mTsumoHai;
		/** �_�_ */
		int mTenbo;
	}

	PlayerInfo mPlayerInfos[] = new PlayerInfo[4];

	boolean mIsDebug = false;

	/** �C�x���gID */
	EID eid;

	{
		for (int i = 0; i < 4; i++) {
			mPlayerInfos[i] = new PlayerInfo();
		}
	}

	/** �c���v */
	Hai tsumoHai = new Hai();
	int tsumoKaze = 5;

	Hai tsumoHais[] = new Hai[4];
}
