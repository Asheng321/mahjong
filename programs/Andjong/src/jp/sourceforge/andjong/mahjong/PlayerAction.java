package jp.sourceforge.andjong.mahjong;

public class PlayerAction {
	public static final int STATE_NONE = 0;
	public static final int STATE_ACTION_WAIT = 1;

	private int mState = STATE_NONE;

	/** �̔v�̃C���f�b�N�X */
	private int mSutehaiIdx;

	/** �A�N�V�����v�� */
	//private boolean mActionRequest;

	/** �c�����\ */
	private boolean mValidTsumo;

	/** �|�����\ */
	private boolean mValidPon;

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
		//mActionRequest = false;
		mSutehaiIdx = Integer.MAX_VALUE;
		mValidTsumo = false;
		mValidPon = false;
	}

	public void setState(int state) {
		this.mState = state;
	}

	public int getState() {
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
	 * �A�N�V�����v����ݒ肷��B
	 *
	 * @param actionRequest
	 *            �A�N�V�����v��
	 */
	/*
	public synchronized void setActionRequest(boolean actionRequest) {
		this.mActionRequest = actionRequest;
	}
	*/

	/**
	 * �A�N�V�����v�����擾����B
	 *
	 * @return
	 */
	public synchronized boolean isActionRequest() {
		return mValidTsumo | mValidPon;
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
}
