package jp.sourceforge.andjong.mahjong;

import android.util.Log;

public class Man implements EventIf {
	/** �v���C���[�ɒ񋟂����� */
	private Info m_info;

	/** �̔v�̃C���f�b�N�X */
	private int sutehaiIdx = 0;

	private String name;

	public String getName() {
		return name;
	}

	private PlayerAction mPlayerAction;

	public Man(Info info, String name, PlayerAction playerAction) {
		this.m_info = info;
		this.name = name;
		this.mPlayerAction = playerAction;
	}

	/** ��v */
	private Tehai m_tehai = new Tehai();

	@Override
	public EventId event(EventId eid, int fromKaze, int toKaze) {
		int sutehaiIdx = 0;
		int agariScore = 0;
		Hai tsumoHai;
		int indexNum = 0;
		int[] indexs = new int[14];
		switch (eid) {
		case TSUMO:
			// ��v���R�s�[����B
			m_info.copyTehai(m_tehai, m_info.getJikaze());
			// �c���v���擾����B
			tsumoHai = m_info.getTsumoHai();

			Log.d("Man", "getReachIndexs in");
			indexNum = m_info.getReachIndexs(m_tehai, tsumoHai, indexs);
			Log.d("Man", "getReachIndexs = " + indexNum);

			agariScore = m_info.getAgariScore(m_tehai, tsumoHai);
			if (agariScore > 0) {
				Log.d("Man", "agariScore = " + agariScore);
				mPlayerAction.setValidTsumo(true);
				mPlayerAction.setMenuSelect(0);
				mPlayerAction.setState(PlayerAction.STATE_TSUMO_SELECT);
				m_info.postInvalidate();
				mPlayerAction.actionWait();
				if (mPlayerAction.getMenuSelect() == 0) {
					mPlayerAction.init();
					return EventId.TSUMO_AGARI;
				}
				mPlayerAction.init();
			}
			while (true) {
				// ���͂�҂B
				mPlayerAction.setState(PlayerAction.STATE_SUTEHAI_SELECT);
				mPlayerAction.actionWait();
				sutehaiIdx = mPlayerAction.getSutehaiIdx();
				if (sutehaiIdx != Integer.MAX_VALUE) {
					if (mPlayerAction.isValidTsumo()) {
						if (sutehaiIdx == 100) {
							mPlayerAction.init();
							return EventId.TSUMO_AGARI;
						}
					}
					if (sutehaiIdx >= 0 && sutehaiIdx <= 13) {
						break;
					}
				}
			}
			mPlayerAction.init();
			this.sutehaiIdx = sutehaiIdx;
			return EventId.SUTEHAI;
		case SELECT_SUTEHAI:
			while (true) {
				try {
					// ���͑҂�
					Thread.sleep(10, 0);
					sutehaiIdx = mPlayerAction.getSutehaiIdx();
					if (sutehaiIdx != Integer.MAX_VALUE) {
						mPlayerAction.setSutehaiIdx(Integer.MAX_VALUE);
						if (sutehaiIdx >= 0 && sutehaiIdx <= 14) {
							break;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.sutehaiIdx = sutehaiIdx;
			return EventId.SUTEHAI;
		case SUTEHAI:
			if (fromKaze == m_info.getJikaze()) {
				return EventId.NAGASHI;
			}
			m_info.copyTehai(m_tehai, m_info.getJikaze());
			agariScore = m_info.getAgariScore(m_tehai, m_info.getSuteHai());
			if (agariScore > 0) {
				Log.d("Man", "agariScore = " + agariScore);
				mPlayerAction.setValidRon(true);
				mPlayerAction.setMenuSelect(0);
				mPlayerAction.setState(PlayerAction.STATE_RON_SELECT);
				m_info.postInvalidate();
				mPlayerAction.actionWait();
				if (mPlayerAction.getMenuSelect() == 0) {
					mPlayerAction.init();
					return EventId.RON_AGARI;
				}
				mPlayerAction.init();
			}

//			if (mTehai.validPon(mInfo.getSuteHai())) {
//				mPlayerAction.setValidPon(true);
//				//mPlayerAction.setActionRequest(true);
//				while (true) {
//					mPlayerAction.actionWait();
//					sutehaiIdx = mPlayerAction.getSutehaiIdx();
//					if (sutehaiIdx != Integer.MAX_VALUE) {
//						synchronized (mPlayerAction) {
//							mPlayerAction.setState(PlayerAction.STATE_NONE);
//						}
//						mPlayerAction.setSutehaiIdx(Integer.MAX_VALUE);
//						if (sutehaiIdx == 100) {
//							mPlayerAction.init();
//							//mPlayerAction.setActionRequest(false);
//							return EID.PON;
//						}
//						break;
//					}
//				}
//				mPlayerAction.init();
//				//mPlayerAction.setActionRequest(false);
//			}
			break;
		default:
			break;
		}

		return EventId.NAGASHI;
	}

	public int getISutehai() {
		return sutehaiIdx;
	}
}
