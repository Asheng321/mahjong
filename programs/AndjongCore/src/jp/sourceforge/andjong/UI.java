package jp.sourceforge.andjong;

import jp.sourceforge.andjong.Game.SAI;

/**
 * UI����������N���X�ł��B
 * <p>
 * �R���\�[���œ����悤�Ɏ������Ă��܂��B<br>
 * UI�N���X���p�����A�ʂ�UI���������܂��B
 * </p>
 * 
 * @author Yuji Urushibara
 * 
 */
public class UI implements EventIF {
	/** InfoUI�I�u�W�F�N�g */
	private InfoUI infoUi;

	/** ��v */
	private Tehai tehai = new Tehai();

	/** �� */
	private Kawa kawa = new Kawa();

	/**
	 * UI�����������܂��B
	 * 
	 * @param infoUi
	 *            InfoUI�I�u�W�F�N�g
	 */
	public UI(InfoUI infoUi) {
		this.infoUi = infoUi;
	}

	/**
	 * �C�x���g���������܂��B
	 * 
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑Ώۂ̉�
	 * @param eid
	 *            �C�x���gID
	 */
	public EID event(EID eid, int fromKaze, int toKaze) {
		switch (eid) {
		// �ꏊ����
		case BASHOGIME:
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		// �e����
		case OYAGIME:
			// �T�C�U���\�����܂��B
			SAI[] sai = infoUi.getSai();
			System.out.println("[�T�C�U��][" + sai[0].getNo() + "]["
					+ sai[1].getNo() + "]");
			break;
		// ���v
		case SENPAI:
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		// �T�C�U��
		case SAIFURI:
			// �h���\���v��\�����܂��B
			Hai[] doras = infoUi.getDora();
			System.out.print("[�h���\���v]");
			for (Hai hai : doras) {
				System.out.print("[" + idToString(hai.getId()) + "]");
			}
			System.out.println();
			break;
		// ����
		case NAGASHI:
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		// �c��
		case TSUMO:
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][�c��]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getId()));
			break;
		// �c��������
		case TSUMOAGARI:
			System.out.print("[" + jikazeToString(infoUi.getJikaze())
					+ "][�c��������]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getId()));
			break;
		// �̔v
		case SUTEHAI:
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == 0) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				System.out.println(":"
						+ idToString((infoUi.getSuteHai()).getId()));
			}
			break;
		// ����
		case RON:
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][����]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// ������v��\�����܂��B
			System.out.println(":" + idToString((infoUi.getSuteHai()).getId()));
			break;
		default:
			break;
		}
		
		return EID.NAGASHI;
	}

	/**
	 * ��v��\�����܂��B
	 * <p>
	 * TODO ���v��\�����邱�ƁB
	 * </p>
	 * 
	 * @param tehai
	 *            ��v
	 */
	private void printJyunTehai(Tehai tehai) {
		infoUi.copyTehai(tehai, 0);
		Hai[] jyunTehai = tehai.getJyunTehai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++)
			System.out.print(idToString(jyunTehai[i].getId()));
	}

	/**
	 * �͂�\�����܂��B
	 * 
	 * @param kawa
	 *            ��
	 */
	private void printKawa(Kawa kawa) {
		infoUi.copyKawa(kawa, 0);
		KawaHai[] kawaHais = kawa.getKawaHai();
		int kawaLength = kawa.getKawaHaiLength();
		for (int i = 0; i < kawaLength; i++)
			System.out.print(idToString(kawaHais[i].getId()));
	}

	/**
	 * �v�ԍ��𕶎���ɕϊ����܂�
	 * 
	 * @param id
	 *            �v�ԍ�
	 * @return ������
	 */
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

	/**
	 * �����𕶎���ɕϊ����܂��B
	 * 
	 * @param jikaze
	 *            ����
	 * @return�@������
	 */
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
}
