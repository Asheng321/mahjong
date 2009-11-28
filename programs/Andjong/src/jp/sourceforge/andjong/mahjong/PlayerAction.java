package jp.sourceforge.andjong.mahjong;

public class PlayerAction {
	public static final int STATE_NONE = 0;
	public static final int STATE_ACTION_WAIT = 1;

	private int mState = STATE_NONE;

	public void setState(int state) {
		this.mState = state;
	}

	public int getState() {
		return mState;
	}

	public synchronized void actionWait() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
	}

	public synchronized void actionNotifyAll() {
		notifyAll();
	}

	public void setSutehaiIdx(int sutehaiIdx) {
		this.mSutehaiIdx = sutehaiIdx;
	}

	public int getSutehaiIdx() {
		return mSutehaiIdx;
	}

	private int mSutehaiIdx = Integer.MAX_VALUE;
}
