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
	private Hai[] jyunTehai = new Hai[Tehai.JYUNTEHAI_MAX];
	private Kawa kawa = new Kawa();
	private KawaHai[] kawaHais = new KawaHai[Kawa.KAWA_MAX];

	{
		for (int i = 0; i < Tehai.JYUNTEHAI_MAX; i++)
			jyunTehai[i] = new Hai();

		for (int i = 0; i < Kawa.KAWA_MAX; i++)
			kawaHais[i] = new KawaHai();
	}

	public UI(Info info) {
		this.info = info;
	}

	static public String idToString(int id) {
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

	static public String jikazeToString(int jikaze) {
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
			System.out.print("[�c��]");

			// ����v��\�����܂��B
			info.copyTehai(tehai, 0);
			int jyunTehaiLength = tehai.copyJyunTehai(jyunTehai);
			for (int i = 0; i < jyunTehaiLength; i++)
				System.out.print(idToString(jyunTehai[i].getId()));
			System.out.println(":" + idToString((info.getTsumoHai()).getId()));
			break;
		case EVENTID_SUTEHAI:
			if (eventCallPlayerIdx == 0) {
				System.out.print("[" + jikazeToString(info.getJikaze()) + "]");
				System.out.print("[�̔v]");
				info.copyKawa(kawa, 0);
				int kawaLength = kawa.copyKawaHai(kawaHais);
				for (int i = 0; i < kawaLength; i++)
					System.out.print(idToString(kawaHais[i].getId()));
				System.out.println(":"
						+ idToString((info.getSuteHai()).getId()));
			}
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
