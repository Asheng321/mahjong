package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Info.*;

/**
 * UI����������N���X�ł��B �Ƃ肠�����A�R���\�[���Ŏ������܂��B TODO �I�[�o�[���C�h���₷���݌v�ɂ���B
 * 
 * @author Yuji Urushibara
 * 
 */
public class UI {
	private Info info;

	private Tehai tehai = new Tehai();

	public UI(Info info) {
		this.info = info;
	}

	static private String idToString(int id) {
		int kind = id & (Hai.KIND_SHUU | Hai.KIND_TSUU);
		id &= ~(Hai.KIND_SHUU | Hai.KIND_TSUU);

		switch (kind) {
		case Hai.KIND_WAN:
			switch (id) {
			case 1:
				return "��";
			case 2:
				return "��";
			case 3:
				return "�O";
			case 4:
				return "�l";
			case 5:
				return "��";
			case 6:
				return "�Z";
			case 7:
				return "��";
			case 8:
				return "��";
			case 9:
				return "��";
			}
			break;
		case Hai.KIND_PIN:
			switch (id) {
			case 1:
				return "�@";
			case 2:
				return "�A";
			case 3:
				return "�B";
			case 4:
				return "�C";
			case 5:
				return "�D";
			case 6:
				return "�E";
			case 7:
				return "�F";
			case 8:
				return "�G";
			case 9:
				return "�H";
			}
			break;
		case Hai.KIND_SOU:
			switch (id) {
			case 1:
				return "�P";
			case 2:
				return "�Q";
			case 3:
				return "�R";
			case 4:
				return "�S";
			case 5:
				return "�T";
			case 6:
				return "�U";
			case 7:
				return "�V";
			case 8:
				return "�W";
			case 9:
				return "�X";
			}
			break;
		case Hai.KIND_FON:
			switch (id) {
			case 1:
				return "��";
			case 2:
				return "��";
			case 3:
				return "��";
			case 4:
				return "�k";
			}
			break;
		case Hai.KIND_SANGEN:
			switch (id) {
			case 1:
				return "��";
			case 2:
				return "�";
			case 3:
				return "��";
			}
			break;
		}

		return null;
	}
	
	static private String jikazeToString(int jikaze) {
		switch (jikaze) {
		case 0:
			return "��";
		case 1:
			return "��";
		case 2:
			return "��";
		case 3:
			return "�k";
		}
		
		return null;
	}

	public void event(int eventCallPlayerIdx, int eventTargetPlayerIdx,
			int eventId) {
		switch (eventId) {
		case EVENTID_TSUMO:
			System.out.print("[" + jikazeToString(info.getJikaze()) + "]");
			// System.out.println("[" + eventCallPlayerIdx + "]["
			// + eventTargetPlayerIdx + "]EVENTID_TSUMO");
			info.copyTehai(tehai, 0);

			Hai[] jyunTehai = new Hai[Tehai.JYUNTEHAI_MAX];
			for (int i = 0; i < Tehai.JYUNTEHAI_MAX; i++)
				jyunTehai[i] = new Hai();
			int jyunTehaiLength = tehai.copyJyunTehai(jyunTehai);

			// ����v��\�����܂��B
			for (int i = 0; i < jyunTehaiLength; i++)
				System.out.print(idToString(jyunTehai[i].getId()));
			Hai tsumoHai = info.getTsumoHai();
			System.out.println(":" + idToString(tsumoHai.getId()));
			break;
		case EVENTID_SUTEHAI:
			// System.out.println("[" + eventCallPlayerIdx + "]["
			// + eventTargetPlayerIdx + "]EVENTID_SUTEHAI");
			break;
		case EVENTID_NAGASHI:
			// System.out.println("[" + eventCallPlayerIdx + "]["
			// + eventTargetPlayerIdx + "]EVENTID_NAGASHI");
			break;
		default:
			break;
		}
	}
}
