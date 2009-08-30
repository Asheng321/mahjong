package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.*;
import jp.sourceforge.andjong.CountFormat.Combi;

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
	int countHu(Tehai tehai, Hai addHai, Combi combi, Yaku yaku,AgariSetting setting) {
		int countHu = 20;
		Hai checkHais[][];

		//���Ύq�̏ꍇ�͂Q�T��
		if(yaku.checkTeetoitu() == true){
			return 25;
		}

		//���̔v���擾
		Hai atamaHai = new Hai(combi.atamaId);

		// �R���v�Ȃ�Q���ǉ�
		if (atamaHai.getKind() == KIND_SANGEN) {
			countHu += 2;
		}

		// �ꕗ�Ȃ�Q���ǉ�
		if (atamaHai.getId() == setting.getBakaze()){
			countHu += 2;
		}

		// �����Ȃ�Q���ǉ�
		if (atamaHai.getId() == setting.getJikaze()){
			countHu += 2;
		}

		//���a����������ꍇ�́A�҂��ɂ��Q���ǉ������D�悳���
		if(yaku.checkPinfu() == false){
			// �P�R�҂��̏ꍇ�Q���ǉ�
			if(addHai.getId() == combi.atamaId){
				countHu += 2;
			}

			// �ƒ��҂��̏ꍇ�Q���ǉ�
			//���v�̂Q�`�W���ǂ�������
			if(addHai.isYaotyuu() == false){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if((addHai.getNo()-1) == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}

			// �Ӓ��҂�(3)�̏ꍇ�Q���ǉ�
			if((addHai.isYaotyuu() == false) && (addHai.getNo() == NO_3)){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if( (addHai.getId()-2) == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}

			// �Ӓ��҂�(7)�̏ꍇ�Q���ǉ�
			if((addHai.isYaotyuu() == false) && (addHai.getNo() == NO_7)){
				for(int i = 0 ; i < combi.shunCount ; i++){
					if( addHai.getId() == combi.shunIds[i]){
						countHu += 2;
					}
				}
			}
		}

		// �Í��ɂ����_
		for (int i = 0; i < combi.kouCount; i++) {
			Hai checkHai = new Hai(combi.kouIds[i]);
			// �v�����v��������1,9
			if (checkHai.isYaotyuu() == true) {
				countHu += 8;
			} else {
				countHu += 4;
			}
		}

		// �����ɂ����_
		for (int i = 0; i < tehai.getMinKousLength(); i++) {
			checkHais = tehai.getMinKous();
			// �v�����v��������1,9
			if (checkHais[i][0].isYaotyuu() == true) {
				countHu += 4;
			} else {
				countHu += 2;
			}
		}

		// ���Ȃɂ����_
		for (int i = 0; i < tehai.getMinKansLength(); i++) {
			checkHais = tehai.getMinKans();
			// �v�����v��������1,9
			if (checkHais[i][0].isYaotyuu() == true) {
				countHu += 16;
			} else {
				countHu += 8;
			}
		}

		// �ÞȂɂ����_
		for (int i = 0; i < tehai.getAnKansLength(); i++) {
			checkHais = tehai.getAnKans();
			// �v�����v��������1,9
			if (checkHais[i][0].isYaotyuu() == true) {
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
		CountFormat.getCountFormat(tehai, countFormat, addHai);

		// ������̑g�ݍ��킹���擾���܂��B
		int combisCount = countFormat.getCombi(combis);

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
	
	public String[] getYakuName(Tehai tehai, Hai addHai, Combi[] combis,AgariSetting setting) {
		//�a������̖��O
		String[] yakuNames = {""};
		// �J�E���g�t�H�[�}�b�g���擾���܂��B
		CountFormat.getCountFormat(tehai, countFormat, addHai);

		// ������̑g�ݍ��킹���擾���܂��B
		int combisCount = countFormat.getCombi(combis);

		// ������̑g�ݍ��킹���Ȃ��ꍇ��0�_
		if (combisCount == 0){
			return yakuNames;
		}

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
			
			if(maxagariScore < agariScore[i]){
				maxagariScore = agariScore[i];
				yakuNames = yaku.getYakuName();
			}
		}

		return yakuNames;
	}
}
