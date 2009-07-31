package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.OLD_KIND_FON;
import static jp.sourceforge.andjong.Hai.OLD_KIND_MASK;
import static jp.sourceforge.andjong.Hai.OLD_KIND_SANGEN;
import static jp.sourceforge.andjong.Hai.OLD_KIND_TON;
import static jp.sourceforge.andjong.Hai.OLD_KIND_TSUU;
import jp.sourceforge.andjong.Tehai.Combi;
import jp.sourceforge.andjong.Tehai.CountFormat;

public class AgariScore {
	/** �J�E���g�t�H�[�}�b�g */
	private CountFormat countFormat = new CountFormat();
	
	/**
	 * �����v�Z���܂��B
	 * 
	 * @param tehai
	 *            ��v addHai �a�������v combi ��v�̑g�ݍ��킹�@
	 * 
	 * @return int ��
	 * 
	 */
	private int countHu(Tehai tehai, Hai addHai, Combi combi, Yaku yaku,AgariSetting setting) {
		int countHu = 20;
		int id;
		Hai checkHai[][];
		
		//���Ύq�̏ꍇ�͂Q�T��
		if(yaku.checkTeetoitu() == true){
			return 25;
		}

		id = combi.atamaId;

		// �R���v�Ȃ�Q���ǉ�
		if ((id & OLD_KIND_SANGEN) != 0) {
			countHu += 2;
		}

		// �ꕗ�Ȃ�Q���ǉ�
		if (id == OLD_KIND_TON) {
			countHu += 2;
		}

		// �����Ȃ�Q���ǉ�
		if (((id & OLD_KIND_FON) != 0)
				&& (id & OLD_KIND_MASK) == (setting.getJikaze())) {
			countHu += 2;
		}
		
		//���a����������ꍇ�́A�҂��ɂ��Q���ǉ������D�悳���
		if(yaku.checkPinfu() == false){
			// �P�R�҂��̏ꍇ�Q���ǉ�
			if(id == combi.atamaId){
				countHu += 2;
			}
			
			// �ƒ��҂��̏ꍇ�Q���ǉ�
			id = addHai.getId();
			//���v�̂Q�`�W���ǂ�������
			if(((id & OLD_KIND_TSUU) == 0 )
					&& ( ((id & OLD_KIND_MASK) >=2) || ((id & OLD_KIND_MASK) <= 8))){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if( (id-1) == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}
	
			// �Ӓ��҂�(3)�̏ꍇ�Q���ǉ�
			if(((id & OLD_KIND_TSUU) == 0 ) && ((id & OLD_KIND_MASK) ==3) ){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if( (id-2) == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}
	
			// �Ӓ��҂�(7)�̏ꍇ�Q���ǉ�
			if(((id & OLD_KIND_TSUU) == 0 ) && ((id & OLD_KIND_MASK) ==7) ){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if( id == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}
		}

		// �Í��ɂ����_
		for (int i = 0; i < combi.kouCount; i++) {
			id = combi.kouIds[i];
			// �v�����v��������1,9
			if (((id & OLD_KIND_TSUU) != 0) || ((id & OLD_KIND_MASK) == 1)
					|| ((id & OLD_KIND_MASK) == 9)) {
				countHu += 8;
			} else {
				countHu += 4;
			}
		}

		// �����ɂ����_
		for (int i = 0; i < tehai.getMinkousLength(); i++) {
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getOldId();
			// �v�����v��������1,9
			if (((id & OLD_KIND_TSUU) != 0) || ((id & OLD_KIND_MASK) == 1)
					|| ((id & OLD_KIND_MASK) == 9)) {
				countHu += 4;
			} else {
				countHu += 2;
			}
		}

		// ���Ȃɂ����_
		for (int i = 0; i < tehai.getMinkansLength(); i++) {
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			// �v�����v��������1,9
			if (((id & OLD_KIND_TSUU) != 0) || ((id & OLD_KIND_MASK) == 1)
					|| ((id & OLD_KIND_MASK) == 9)) {
				countHu += 16;
			} else {
				countHu += 8;
			}
		}

		// �ÞȂɂ����_
		for (int i = 0; i < tehai.getAnkansLength(); i++) {
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
			// �v�����v��������1,9
			if (((id & OLD_KIND_TSUU) != 0) || ((id & OLD_KIND_MASK) == 1)
					|| ((id & OLD_KIND_MASK) == 9)) {
				countHu += 32;
			} else {
				countHu += 16;
			}
		}

		// �c���オ��ŕ��a���������Ă��Ȃ���΂Q���ǉ�
		if(setting.getYakuflg(AgariSetting.YakuflgName.TUMO.ordinal() )== true){
			if(yaku.checkPinfu() == false){
				countHu += 2;
			}
		}
		
		// �ʑO�����オ��̏ꍇ�͂P�O���ǉ�
		if(setting.getYakuflg(AgariSetting.YakuflgName.TUMO.ordinal() )== false){
			if (yaku.nakiflg == false) {
				countHu += 10;
			}
		}

		// ��̈ʂ�����ꍇ�́A�؂�グ
		if (countHu % 10 != 0) {
			countHu = countHu - (countHu % 10) + 10;
		}

		return countHu;
	}

	/**
	 * �オ��_�����擾���܂��B
	 * 
	 * @param tehai
	 *            ��v addHai �a�������v combi ��v�̑g�ݍ��킹�@
	 * 
	 * @return int �a����_
	 */
	public int getScore(int hanSuu, int huSuu) {
		int score;
		// ���@�~ �Q�́@�i�|���@+�@��]����2�|)��
		score = huSuu * (int) Math.pow(2, hanSuu + 2);
		// �q�͏��4�{����{�_(�e��6�{)
		score *= 4;

		// 100�Ŋ���؂�Ȃ���������ꍇ100�_�J�グ
		if (score % 100 != 0) {
			score = score - (score % 100) + 100;
		}
		// 7700�ȏ��8000�Ƃ���
		if (score >= 7700) {
			score = 8000;
		}

		if (hanSuu >= 13) { // 13�|�ȏ�͖�
			score = 32000;
		} else if (hanSuu >= 11) { // 11�|�ȏ��3�{��
			score = 24000;
		} else if (hanSuu >= 8) { // 8�|�ȏ�͔{��
			score = 16000;
		} else if (hanSuu >= 6) { // 6�|�ȏ�͒���
			score = 12000;
		} else if (hanSuu >= 5) { // 5�|�ȏ�͖���
			score = 8000;
		}

		return score;
	}

	public int getAgariScore(Tehai tehai, Hai addHai, Combi[] combis,AgariSetting setting) {
		// �J�E���g�t�H�[�}�b�g���擾���܂��B
		tehai.getCountFormat(countFormat, addHai);

		// ������̑g�ݍ��킹���擾���܂��B
		int combisCount = tehai.getCombi(combis, countFormat);

		// ������̑g�ݍ��킹���Ȃ��ꍇ��0�_
		if (combisCount == 0)
			return 0;

		// ��
		int hanSuu[] = new int[combisCount];
		// ��
		int huSuu[] = new int[combisCount];
		// �_���i�q�̃����オ��j
		int agariScore[] = new int[combisCount];
		// �ő�̓_��
		int maxagariScore = 0;

		for (int i = 0; i < combisCount; i++) {
			Yaku yaku = new Yaku(tehai, addHai, combis[i], setting);
			hanSuu[i] = yaku.getHanSuu();
			huSuu[i] = countHu(tehai, addHai, combis[i],yaku,setting);
			agariScore[i] = getScore(hanSuu[i], huSuu[i]);
		}

		// �ő�l��T��
		maxagariScore = agariScore[0];
		for (int i = 0; i < combisCount; i++) {
			maxagariScore = Math.max(maxagariScore, agariScore[i]);
		}
		return maxagariScore;
	}
}
