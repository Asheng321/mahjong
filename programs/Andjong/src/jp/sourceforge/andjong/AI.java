package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Info.*;

/**
 * AI����������N���X�ł��B<br>
 * TODO �I�[�o�[���C�h���₷���݌v�ɂ���B
 * 
 * @author Yuji Urushibara
 * 
 */
public class AI {
	private Info info;
	
	private Tehai tehai = new Tehai();

	public AI(Info info) {
		this.info = info;
	}

	public int event(int eventCallPlayerIdx, int eventTargetPlayerIdx,
			int eventId) {
		switch (eventId) {
		case EVENTID_TSUMO:
			info.copyTehai(tehai, 0);
			info.sutehaiIdx = 13;
			return EVENTID_SUTEHAI;
		case EVENTID_SUTEHAI:
			return EVENTID_NAGASHI;
		default:
			break;
		}

		return EVENTID_NAGASHI;
	}
}
