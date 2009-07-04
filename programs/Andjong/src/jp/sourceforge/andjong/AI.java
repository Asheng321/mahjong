package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Info.*;
import jp.sourceforge.andjong.Tehai.CountFormat;

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
		int returnEvent = EVENTID_NAGASHI;

		switch (eventId) {
		case EVENTID_TSUMO:
			returnEvent = eventTsumo();
			break;
		case EVENTID_SUTEHAI:
			returnEvent = EVENTID_NAGASHI;
		default:
			break;
		}

		return returnEvent;
	}

	public int eventTsumo() {
		info.copyTehai(tehai, 0);

		Hai tsumoHai = new Hai();
		info.copyTsumoHai(tsumoHai);

		tehai.addJyunTehai(tsumoHai);
		CountFormat countFormat = tehai.getCountFormat(tsumoHai);
		int score;
		int minScore = 0;
		Hai hai = new Hai();
		for (int i = tehai.jyunTehaiLength - 1; i >= 0; i--) {
			hai.copy(tehai.jyunTehai[i]);
			tehai.removeJyunTehai(i);
			countFormat = tehai.getCountFormat(null);
			score = getCountFormatScore(countFormat);
			if (score < minScore) {
				minScore = score;
				info.sutehaiIdx = i;
			}
			//System.out.println("i = " + i + ", score = " + score);
			//for (int j = 0; j < tehai.jyunTehaiLength; j++)
			//	System.out.print(UI.idToString(tehai.jyunTehai[j].id));
			//System.out.println();
			tehai.addJyunTehai(hai);
		}
		/*
		 * for (int i = 0; i < countFormat.length; i++) {
		 * countFormat.counts[i].length--; score =
		 * getCountFormatScore(countFormat); System.out.println("score = " +
		 * score); countFormat.counts[i].length++; }
		 */

		return EVENTID_SUTEHAI;
	}

	private int getCountFormatScore(CountFormat countFormat) {
		int score = 0;

		for (int i = 0; i < countFormat.length; i++) {
			if (countFormat.counts[i].length == 2) {
				score += 4;
			}

			if (countFormat.counts[i].length >= 3) {
				score += 8;
			}

			if ((countFormat.counts[i].id & Hai.KIND_SHUU) > 0) {
				//System.out.println("[" + (countFormat.counts[i].id + 1) + "][" + countFormat.counts[i + 1].id + "]");
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
