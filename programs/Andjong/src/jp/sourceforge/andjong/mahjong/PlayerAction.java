package jp.sourceforge.andjong.mahjong;

public class PlayerAction {
	public static final int STATE_NONE = 0;
	public static final int STATE_SUTEHAI_SELECT = 1;
	public static final int STATE_RON_SELECT= 2;
	public static final int STATE_TSUMO_SELECT= 3;
	public static final int STATE_ACTION_WAIT = 4;

	private int mState = STATE_NONE;

	/** �̔v�̃C���f�b�N�X */
	private int mSutehaiIdx;

	/** �������\ */
	private boolean mValidRon;

	/** �c�����\ */
	private boolean mValidTsumo;

	/** �|�����\ */
	private boolean mValidPon;

	/** ���j���[�I�� */
	private int mMenuSelect;

	public int m_indexs[];
	public int m_indexNum;

	/**
	 * �R���X�g���N�^�[
	 */
	public PlayerAction() {
		super();

		init();
	}

	/**
	 * ����������B
	 */
	public synchronized void init() {
		mState = STATE_NONE;
		mSutehaiIdx = Integer.MAX_VALUE;
		mValidRon = false;
		mValidTsumo = false;
		mValidPon = false;
		m_validReach = false;
		setMenuSelect(5);
	}

	public synchronized void setState(int state) {
		this.mState = state;
	}

	public synchronized int getState() {
		return mState;
	}

	/**
	 * �A�N�V������҂B
	 */
	public synchronized void actionWait() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �A�N�V������ʒm����B
	 */
	public synchronized void actionNotifyAll() {
		notifyAll();
	}

	/**
	 * �̔v�̃C���f�b�N�X��ݒ肷��B
	 *
	 * @param sutehaiIdx
	 *            �̔v�̃C���f�b�N�X
	 */
	public synchronized void setSutehaiIdx(int sutehaiIdx) {
		this.mSutehaiIdx = sutehaiIdx;
	}

	/**
	 * �̔v�̃C���f�b�N�X���擾����B
	 *
	 * @return �̔v�̃C���f�b�N�X
	 */
	public synchronized int getSutehaiIdx() {
		return mSutehaiIdx;
	}

	/**
	 * �A�N�V�����v�����擾����B
	 *
	 * @return
	 */
	public synchronized boolean isActionRequest() {
		return mValidRon | mValidTsumo | mValidPon | m_validReach;
	}

	/**
	 * �������\����ݒ肷��B
	 *
	 * @param validRon
	 *            ��
	 */
	public synchronized void setValidRon(boolean validRon) {
		this.mValidRon = validRon;
	}

	/**
	 * �������\�����擾����B
	 *
	 * @return ��
	 */
	public synchronized boolean isValidRon() {
		return mValidRon;
	}

	/**
	 * �c�����\����ݒ肷��B
	 *
	 * @param validTsumo
	 *            ��
	 */
	public synchronized void setValidTsumo(boolean validTsumo) {
		this.mValidTsumo = validTsumo;
	}

	/**
	 * �c�����\�����擾����B
	 *
	 * @return ��
	 */
	public synchronized boolean isValidTsumo() {
		return mValidTsumo;
	}

	/**
	 * �|�����\����ݒ肷��B
	 *
	 * @param validTsumo
	 *            ��
	 */
	public synchronized void setValidPon(boolean validPon) {
		this.mValidPon = validPon;
	}

	/**
	 * �|�����\�����擾����B
	 *
	 * @return ��
	 */
	public synchronized boolean isValidPon() {
		return mValidPon;
	}

	boolean m_validReach;

	public synchronized void setValidReach(boolean a_validReach) {
		this.m_validReach = a_validReach;
	}

	public synchronized boolean isValidReach() {
		return m_validReach;
	}

	public synchronized void setMenuSelect(int menuSelect) {
		this.mMenuSelect = menuSelect;
	}

	public synchronized int getMenuSelect() {
		return mMenuSelect;
	}
}
