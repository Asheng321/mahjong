package jp.sourceforge.andjong.mahjong;

import jp.sourceforge.andjong.mahjong.CountFormat.Combi;

/**
 * AI����������N���X�ł��B
 *
 * @author Yuji Urushibara
 *
 */
public class AI implements EventIF {
	private Info info;

	private Tehai tehai = new Tehai();

	/** �J�E���g�t�H�[�}�b�g */
	private CountFormat countFormat = new CountFormat();

	private int sutehaiIdx = 0;

	public int getSutehaiIdx() {
		return sutehaiIdx;
	}

	private String name;

	public String getName() {
		return name;
	}

	private final static int HYOUKA_SHUU = 1;

	private Combi[] combis = new Combi[10];
	{
		for (int i = 0; i < combis.length; i++)
			combis[i] = new Combi();
	}

	public AI(Info info, String name) {
		this.info = info;
		this.name = name;
	}

	public EID event(EID eid, int fromKaze, int toKaze) {
		EID returnEid = EID.NAGASHI;

		// �C�x���g����������B
		switch (eid) {
		case TSUMO:// �c��
			returnEid = eidTsumo(fromKaze, toKaze);
			break;
		case SUTEHAI:// �̔v
			returnEid = eidSutehai(fromKaze, toKaze);
			break;
		case SUTEHAISELECT:
			if (fromKaze != info.getJikaze()) {
				return EID.NAGASHI;
			}
			// �����̎�v���R�s�[���܂��B
			info.copyTehai(tehai, fromKaze);
			thinkSutehai(null);
			break;
		default:
			break;
		}

		return returnEid;
	}

	private EID eidSutehai(int fromKaze, int toKaze) {
		if (fromKaze == info.getJikaze()) {
			return EID.NAGASHI;
		}
		int agariScore = info.getAgariScore(tehai, info.getSuteHai());
		if (agariScore > 0) {
			return EID.RON;
		}

//		{
//			if (tehai.validChiiRight(info.getSuteHai())) {
//				System.out.println("validChiiRight�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I");
//				Hai[] jyunTehai = tehai.getJyunTehai();
//				int jyunTehaiLength = tehai.getJyunTehaiLength();
//				for (int i = 0; i < jyunTehaiLength; i++)
//					System.out.print(Console.idToString(jyunTehai[i].getId()));
//				System.out.println();
//				tehai.setChiiRight(info.getSuteHai());
//				jyunTehai = tehai.getJyunTehai();
//				jyunTehaiLength = tehai.getJyunTehaiLength();
//				for (int i = 0; i < jyunTehaiLength; i++)
//					System.out.print(Console.idToString(jyunTehai[i].getId()));
//				System.out.println();
//			}
//		}

		// �v�����Ȃ��Ȃ�������Ă݂܂��B
//		if (info.getTsumoRemain() < 16) {
//			// �|���ł��邩�`�F�b�N���܂��B
//			if (tehai.validPon(info.getSuteHai())) {
//				return EID.PON;
//			}
//		}

		return EID.NAGASHI;
	}

	/**
	 * �C�x���g�i�c���j����������B
	 *
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑ΏۂƂȂ�����
	 * @return �C�x���gID
	 */
	private EID eidTsumo(int fromKaze, int toKaze) {
		// �����̎�v���R�s�[���܂��B
		info.copyTehai(tehai, fromKaze);

		// �c���v���擾���܂��B
		Hai tsumoHai = info.getTsumoHai();

		// �c��������̏ꍇ�̓C�x���g�i�c��������j��Ԃ��܂��B
		int agariScore = info.getAgariScore(tehai, tsumoHai);
		if (agariScore > 0) {
			return EID.TSUMOAGARI;
		}

		// ���[�`�̏ꍇ�̓c���؂肵�܂��B
		if (info.isReach(info.getJikaze())) {
			sutehaiIdx = 13;
			return EID.SUTEHAI;
		}

		thinkSutehai(tsumoHai);

		// �̔v�����߂��̂Ŏ�v���X�V���܂��B
		if (sutehaiIdx != 13) {
			tehai.rmJyunTehai(sutehaiIdx);
			tehai.addJyunTehai(tsumoHai);
		}

		// ���[�`����ꍇ�̓C�x���g�i���[�`�j��Ԃ��܂��B
		if (thinkReach(tehai)) {
			return EID.REACH;
		}

		return EID.SUTEHAI;
	}

	private void thinkSutehai(Hai addHai) {
		int score = 0;
		int maxScore = 0;

		CountFormat.getCountFormat(tehai, countFormat, null);
		maxScore = getCountFormatScore(countFormat);
		// System.out.println("score:" + score + ",maxScore:" + maxScore +
		// ",hai:" + UI.idToString(tsumoHai.getId()));
		Hai hai = new Hai();

		Hai[] jyunTehai = new Hai[Tehai.JYUN_TEHAI_LENGTH_MAX];
		for (int i = 0; i < Tehai.JYUN_TEHAI_LENGTH_MAX; i++)
			jyunTehai[i] = new Hai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		Tehai.copyJyunTehai(jyunTehai, tehai.getJyunTehai(), jyunTehaiLength);

		for (int i = 0; i < jyunTehaiLength; i++) {
			tehai.copyJyunTehaiIdx(hai, i);
			tehai.rmJyunTehai(i);
			CountFormat.getCountFormat(tehai, countFormat, addHai);
			score = getCountFormatScore(countFormat);
			// System.out.println("score:" + score + ",maxScore:" + maxScore +
			// ",hai:" + UI.idToString(hai.getId()));
			if (score > maxScore) {
				maxScore = score;
				// System.out.println("setSutehaiIdx:" + i);
				sutehaiIdx = i;
			}
			tehai.addJyunTehai(hai);
		}
	}

	private final static Hai[] haiTable = new Hai[] {
			new Hai(Hai.ID_WAN_1), new Hai(Hai.ID_WAN_2),
			new Hai(Hai.ID_WAN_3), new Hai(Hai.ID_WAN_4),
			new Hai(Hai.ID_WAN_5), new Hai(Hai.ID_WAN_6),
			new Hai(Hai.ID_WAN_7), new Hai(Hai.ID_WAN_8),
			new Hai(Hai.ID_WAN_9),
			new Hai(Hai.ID_PIN_1), new Hai(Hai.ID_PIN_2),
			new Hai(Hai.ID_PIN_3), new Hai(Hai.ID_PIN_4),
			new Hai(Hai.ID_PIN_5), new Hai(Hai.ID_PIN_6),
			new Hai(Hai.ID_PIN_7), new Hai(Hai.ID_PIN_8),
			new Hai(Hai.ID_PIN_9),
			new Hai(Hai.ID_SOU_1), new Hai(Hai.ID_SOU_2),
			new Hai(Hai.ID_SOU_3), new Hai(Hai.ID_SOU_4),
			new Hai(Hai.ID_SOU_5), new Hai(Hai.ID_SOU_6),
			new Hai(Hai.ID_SOU_7), new Hai(Hai.ID_SOU_8),
			new Hai(Hai.ID_SOU_9),
			new Hai(Hai.ID_TON), new Hai(Hai.ID_NAN),
			new Hai(Hai.ID_SHA), new Hai(Hai.ID_PE),
			new Hai(Hai.ID_HAKU), new Hai(Hai.ID_HATSU),
			new Hai(Hai.ID_CHUN) };

	private boolean thinkReach(Tehai tehai) {
		for (Hai hai : haiTable) {
			CountFormat.getCountFormat(tehai, countFormat, hai);
			if (countFormat.getCombi(combis) > 0) {
				return true;
			}
		}
		return false;
	}

	private int getCountFormatScore(CountFormat countFormat) {
		int score = 0;

		for (int i = 0; i < countFormat.length; i++) {
			if ((countFormat.counts[i].id & Hai.KIND_SHUU) != 0) {
				score += countFormat.counts[i].length * HYOUKA_SHUU;
			}

			if (countFormat.counts[i].length == 2) {
				score += 4;
			}

			if (countFormat.counts[i].length >= 3) {
				score += 8;
			}

			if ((countFormat.counts[i].id & Hai.KIND_SHUU) > 0) {
				if ((countFormat.counts[i].id + 1) == countFormat.counts[i + 1].id) {
					score += 4;
				}

				if ((countFormat.counts[i].id + 2) == countFormat.counts[i + 2].id) {
					score += 4;
				}
			}
		}

		return score;
	}
}
