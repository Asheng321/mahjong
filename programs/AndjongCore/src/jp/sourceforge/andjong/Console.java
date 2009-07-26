package jp.sourceforge.andjong;

/**
 * Console����������N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Console implements EventIF {
	/** InfoUI�I�u�W�F�N�g */
	private InfoUI infoUi;

	/** ��v */
	private Tehai tehai = new Tehai();

	/** �� */
	private Kawa kawa = new Kawa();

	/** �̔v�̃C���f�b�N�X */
	private int sutehaiIdx = 0;

	/**
	 * UI�����������܂��B
	 * 
	 * @param infoUi
	 *            InfoUI�I�u�W�F�N�g
	 */
	public Console(InfoUI infoUi) {
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
		case BASHOGIME:// �ꏊ����
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case OYAGIME:// �e����
			// �T�C�U���\�����܂��B
			Sai[] sai = infoUi.getSais();
			System.out.println("[�T�C�U��][" + sai[0].getNo() + "]["
					+ sai[1].getNo() + "]");
			break;
		case SENPAI:// ���v
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case SAIFURI:// �T�C�U��
			// �h���\���v��\�����܂��B
			Hai[] doras = infoUi.getDoras();
			System.out.print("[�h���\���v]");
			for (Hai hai : doras) {
				System.out.print("[" + idToString(hai.getOldId()) + "]");
			}
			System.out.println();
			break;
		case RYUUKYOKU:// ����
			System.out.println("[����]");
			break;
		case NAGASHI:// ����
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case TSUMO:// �c��
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][�c��]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getOldId()));
			break;
		case TSUMOAGARI:// �c��������
			System.out.print("[" + jikazeToString(infoUi.getJikaze())
					+ "][�c��������]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getOldId()));
			break;
		case SUTEHAI:// �̔v
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case PON:// �|��
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�|��]");

				// ��v��\�����܂��B
				printJyunTehai(tehai);

				System.out.println();

				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case REACH:
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][���[�`]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case RON:// ����
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][����]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// ������v��\�����܂��B
			System.out.println(":" + idToString((infoUi.getSuteHai()).getOldId()));
			break;
		default:
			break;
		}

		return EID.NAGASHI;
	}

	public int getSutehaiIdx() {
		return sutehaiIdx;
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
		infoUi.copyTehai(tehai, infoUi.getJikaze());
		Hai[] jyunTehai = tehai.getJyunTehai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++)
			System.out.print(idToString(jyunTehai[i].getOldId()));

		int minkousLength = tehai.getMinkousLength();
		Hai[][] minkous = tehai.getMinkous();
		for (int i = 0; i < minkousLength; i++) {
			System.out.print("[");
			System.out.print(idToString(minkous[i][0].getOldId()));
			System.out.print(idToString(minkous[i][1].getOldId()));
			System.out.print(idToString(minkous[i][2].getOldId()));
			System.out.print("]");
		}
	}

	/**
	 * �͂�\�����܂��B
	 * 
	 * @param kawa
	 *            ��
	 */
	private void printKawa(Kawa kawa) {
		infoUi.copyKawa(kawa, infoUi.getJikaze());
		SuteHai[] SuteHai = kawa.getSuteHais();
		int kawaLength = kawa.getSuteHaiLength();
		for (int i = 0; i < kawaLength; i++)
			System.out.print(idToString(SuteHai[i].getOldId()));
	}

	/**
	 * �v�ԍ��𕶎���ɕϊ����܂�
	 * 
	 * @param id
	 *            �v�ԍ�
	 * @return ������
	 */
	static public String idToString(int id) {
		int kind = id & (Hai.OLD_KIND_SHUU | Hai.OLD_KIND_TSUU);
		id &= ~(Hai.OLD_KIND_SHUU | Hai.OLD_KIND_TSUU);

		switch (kind) {
		case Hai.OLD_KIND_WAN:
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
		case Hai.OLD_KIND_PIN:
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
		case Hai.OLD_KIND_SOU:
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
		case Hai.OLD_KIND_FON:
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
		case Hai.OLD_KIND_SANGEN:
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
