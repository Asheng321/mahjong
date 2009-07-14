package jp.sourceforge.andjong;

import jp.sourceforge.andjong.Tehai.Combi;
import jp.sourceforge.andjong.Tehai.CountFormat;

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

	private final static int HYOUKA_SHUU = 1;

	private Combi[] combis = new Combi[10];
	{
		for (int i = 0; i < combis.length; i++)
			combis[i] = new Combi();
	}

	public AI(Info info) {
		this.info = info;
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

		// �v�����Ȃ��Ȃ�������Ă݂܂��B
		if (info.getTsumoRemain() < 16) {
			// �|���ł��邩�`�F�b�N���܂��B
			if (tehai.validPon(info.getSuteHai())) {
				return EID.PON;
			}
		}

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
			tehai.removeJyunTehai(sutehaiIdx);
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

		tehai.getCountFormat(countFormat, null);
		maxScore = getCountFormatScore(countFormat);
		// System.out.println("score:" + score + ",maxScore:" + maxScore +
		// ",hai:" + UI.idToString(tsumoHai.getId()));
		Hai hai = new Hai();

		Hai[] jyunTehai = new Hai[Tehai.JYUNTEHAI_MAX];
		for (int i = 0; i < Tehai.JYUNTEHAI_MAX; i++)
			jyunTehai[i] = new Hai();
		int jyunTehaiLength = tehai.copyJyunTehai(jyunTehai);

		for (int i = 0; i < jyunTehaiLength; i++) {
			tehai.copyJyunTehaiIdx(hai, i);
			tehai.removeJyunTehai(i);
			tehai.getCountFormat(countFormat, addHai);
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
			new Hai(Hai.KIND_WAN | 1), new Hai(Hai.KIND_WAN | 2),
			new Hai(Hai.KIND_WAN | 3), new Hai(Hai.KIND_WAN | 4),
			new Hai(Hai.KIND_WAN | 5), new Hai(Hai.KIND_WAN | 6),
			new Hai(Hai.KIND_WAN | 7), new Hai(Hai.KIND_WAN | 8),
			new Hai(Hai.KIND_WAN | 9), new Hai(Hai.KIND_PIN | 1),
			new Hai(Hai.KIND_PIN | 2), new Hai(Hai.KIND_PIN | 3),
			new Hai(Hai.KIND_PIN | 4), new Hai(Hai.KIND_PIN | 5),
			new Hai(Hai.KIND_PIN | 6), new Hai(Hai.KIND_PIN | 7),
			new Hai(Hai.KIND_PIN | 8), new Hai(Hai.KIND_PIN | 9),
			new Hai(Hai.KIND_SOU | 1), new Hai(Hai.KIND_SOU | 2),
			new Hai(Hai.KIND_SOU | 3), new Hai(Hai.KIND_SOU | 4),
			new Hai(Hai.KIND_SOU | 5), new Hai(Hai.KIND_SOU | 6),
			new Hai(Hai.KIND_SOU | 7), new Hai(Hai.KIND_SOU | 8),
			new Hai(Hai.KIND_SOU | 9), new Hai(Hai.KIND_FON | 1),
			new Hai(Hai.KIND_FON | 2), new Hai(Hai.KIND_FON | 3),
			new Hai(Hai.KIND_SANGEN | 1), new Hai(Hai.KIND_SANGEN | 2),
			new Hai(Hai.KIND_SANGEN | 3), new Hai(Hai.KIND_SANGEN | 4), };

	private boolean thinkReach(Tehai tehai) {
		for (Hai hai : haiTable) {
			tehai.getCountFormat(countFormat, hai);
			if (tehai.getCombi(combis, countFormat) > 0) {
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
