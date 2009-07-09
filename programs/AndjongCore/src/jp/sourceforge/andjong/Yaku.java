package jp.sourceforge.andjong;
import static jp.sourceforge.andjong.Hai.*;
import static jp.sourceforge.andjong.Tehai.JYUNTEHAI_MAX;
import jp.sourceforge.andjong.Tehai.Combi;

public class Yaku {
	static boolean checkTanyao(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			id = jyunTehai[i].getId();
			//���v�łȂ���Εs����
			if ((id & KIND_SHUU) == 0)
				return false;
			id &= KIND_MASK;
			//����1��9�Ȃ�Εs����
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			id &= KIND_MASK;
			//123 �Ɓ@789 �̏��q������Εs����
			if ((id == 1) || (id == 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			if ((id & KIND_SHUU) == 0)
				return false;
			id &= KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			if ((id & KIND_SHUU) == 0)
				return false;
			id &= KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			if ((id & KIND_SHUU) == 0)
				return false;
			id &= KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		return true;
	}

	static boolean checkPinfu(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		//���������Ă���ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		
		//�ʎq�����q�����ł͂Ȃ�
		if(combi.shunCount != 4){
			return false;
		}
		
		//�����O���v 
		id = combi.atamaId;
		if( (id & KIND_SANGEN) != 0){
			return false;
		}
		
		//�������v
		if( (id & KIND_FON) != 0){
			//if()//TODO �ꕗ�������͎����Ȃ��false	
				return false;
		}
		
		//�҂������ʑ҂���
		//TODO ���ʑ҂����ǂ�������
		
		//�����𖞂����Ă���΁A�񐬗�
		return true;
	}

	static boolean checkIpeikou(Tehai tehai, Hai addHai, Combi combi) {

		//���������Ă���ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		
		//���q�̑g�ݍ��킹���m�F����
		for (int i = 0; i < combi.shunCount -1; i++) {
			if(combi.shunIds[i] == combi.shunIds[i+1]){
				return true;
			}
		}
		
		return false;
	}
	//TODO ���[�`��ꔭ�n�̖�
	/*
	static boolean checkReach(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	static boolean checkIppatu(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	static boolean checkTumo(Tehai tehai, Hai addHai, Combi combi) {
		//���������Ă���ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		
		return true;
	}
*/

	//��v���ł��Ă��邩�ǂ����̔���Ɏg���⏕���\�b�h
	private static boolean checkYakuHai(Tehai tehai, Combi combi , int yakuHaiId) {
		int id;
		Hai checkHai[][]; 

		//����v���`�F�b�N
		for(int i = 0; i < combi.kouCount ; i++){
			//ID�Ɩ�v��ID���`�F�b�N
			if( combi.kouIds[i] == yakuHaiId ){
				return true;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		return false;
	}
	
	static boolean checkTon(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_TON);
	}

	static boolean checkNan(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_NAN);
	}

	static boolean checkSya(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_SYA);
	}

	static boolean checkPei(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_PEE);
	}

	static boolean checkHaku(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_HAKU);
	}

	static boolean checkHatu(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_HATU);
	}

	static boolean checkCyun(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_CYUN);
	}
	
	//TODO ����Ȗ��͌��
/*
	static boolean checkHaitei(Tehai tehai, Hai addHai, Combi combi) {

		return true;
	}
	
	static boolean checkRinsyan(Tehai tehai, Hai addHai, Combi combi) {

		return true;
	}
	static boolean checkCyankan(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
	
	static boolean checkDoubleReach(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
	
	static boolean checkTeetoitu(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	
	static boolean checkCyanta(Tehai tehai, Hai addHai, Combi combi) {
		
		int id;
		Hai checkHai[][]; 

		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.kouCount ; i++){
			id = combi.kouIds[i];
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
			
		}
		
		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 7))
					return false;
			}			
		}
		
		//����v�̓����`�F�b�N
		id = combi.atamaId;
		if ((id & KIND_SHUU) != 0){
			id &= KIND_MASK;
			if ((id > 1) && (id < 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			id &= KIND_MASK;
			//123 �Ɓ@789 �ȊO�̏��q������Εs����
			if ((id > 1) && (id < 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		return true;
	}

	static boolean checkIkkituukan(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai checkHai[][]; 
		boolean ikkituukanflg[]= {false,false,false,false,false,false,false,false,false};
		//�ݎq�A���q�A���q��1,4,7���`�F�b�N
		int checkId[] = {KIND_WAN+1,KIND_WAN+4,KIND_WAN+7,KIND_PIN+1,KIND_PIN+4,KIND_PIN+7,KIND_SOU+1,KIND_SOU+4,KIND_SOU+7};
		
		//��v�̏��q���`�F�b�N
		for(int i = 0 ; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			for(int j =0 ; j < checkId.length ; j++){
				if(id == checkId[j]){
					ikkituukanflg[j] = true;
				}
			}
		}
		
		//�����v���`�F�b�N
		for(int i = 0 ; i < tehai.getMinshunsLength() ; i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			for(int j =0 ; j < checkId.length ; j++){
				if(id == checkId[j]){
					ikkituukanflg[j] = true;
				}
			}
		}
		
		//��C�ʊт��o���Ă��邩�ǂ����`�F�b�N
		if(   (ikkituukanflg[0] == true && ikkituukanflg[1] == true && ikkituukanflg[2] == true )
			||(ikkituukanflg[3] == true && ikkituukanflg[4] == true && ikkituukanflg[5] == true )
			||(ikkituukanflg[6] == true && ikkituukanflg[7] == true && ikkituukanflg[8] == true )){
			return true;
		}else{
			return false;
		}
	}

	//�O�F���ł��Ă��邩�ǂ����̔���Ɏg���⏕���\�b�h
	private static void checkSansyoku(int id , boolean sansyokuflg[][]){
		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {KIND_WAN,KIND_PIN,KIND_SOU};
		for(int i =0 ; i < sansyokuflg.length ; i++){
			for(int j = 0; j < sansyokuflg[i].length ; j++){
				if(id == (checkId[i] + j+1) ){
					sansyokuflg[i][j] = true;
				}
			}
		}		
	}
	
	static boolean checkSansyokuDoujun(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai checkHai[][]; 
		boolean sansyokuflg[][]= new boolean[3][9];
		
		
		//�t���O�̏�����
		for(int i = 0 ; i<sansyokuflg.length; i++){
			for (int k = 0; k <sansyokuflg[i].length ; k++){
				sansyokuflg[i][k] = false;
			}
		}
		
		//��v�̏��q���`�F�b�N
		for(int i = 0 ; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			checkSansyoku(id,sansyokuflg);
		}
		
		//�����v���`�F�b�N
		for(int i = 0 ; i < tehai.getMinshunsLength() ; i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			checkSansyoku(id,sansyokuflg);
		}
		
		//�O�F�������o���Ă��邩�ǂ����`�F�b�N
		for(int i = 0 ; i < sansyokuflg[0].length ; i++){
			if( (sansyokuflg[0][i] == true) && (sansyokuflg[1][i] == true ) && (sansyokuflg[2][i] == true)){
				return true;
			}
		}
		//�o���Ă��Ȃ��ꍇ false��ԋp
		return false;
	}

	static boolean checkSansyokuDoukou(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai checkHai[][]; 
		boolean sansyokuflg[][]= new boolean[3][9];
		
		
		//�t���O�̏�����
		for(int i = 0 ; i<sansyokuflg.length; i++){
			for (int k = 0; k <sansyokuflg[i].length ; k++){
				sansyokuflg[i][k] = false;
			}
		}
		
		//��v�̍��q���`�F�b�N
		for(int i = 0 ; i < combi.kouCount ; i++){
			id = combi.kouIds[i];
			checkSansyoku(id,sansyokuflg);
		}
		
		//�����v�̖������`�F�b�N
		for(int i = 0 ; i < tehai.getMinkousLength() ; i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			checkSansyoku(id,sansyokuflg);
		}
		
		//�����v�̖��Ȃ��`�F�b�N
		for(int i = 0 ; i < tehai.getMinkansLength() ; i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			checkSansyoku(id,sansyokuflg);
		}

		//�����v�̈ÞȂ��`�F�b�N
		for(int i = 0 ; i < tehai.getAnkansLength() ; i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			checkSansyoku(id,sansyokuflg);
		}
		
		//�O�F�������o���Ă��邩�ǂ����`�F�b�N
		for(int i = 0 ; i < sansyokuflg[0].length ; i++){
			if( (sansyokuflg[0][i] == true) && (sansyokuflg[1][i] == true ) && (sansyokuflg[2][i] == true)){
				return true;
			}
		}
		
		//�o���Ă��Ȃ��ꍇ false��ԋp
		return false;
	}
	
	static boolean checkToitoi(Tehai tehai, Hai addHai, Combi combi) {
		//��v�ɏ��q������
		if((combi.shunCount != 0) || (tehai.getMinshunsLength() != 0) ){
			return false;
		}else{
			return true;
		}
	}
	
	static boolean checkSanankou(Tehai tehai, Hai addHai, Combi combi) {
		//��v�̈Í����R��
		//TODO �オ�����ۂ��o�オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 3){
			return true;
		}else{
			return true;
		}
	}
	
	static boolean checkSankantu(Tehai tehai, Hai addHai, Combi combi) {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 3){
			return true;
		}else{
			return false;
		}
	}

	static boolean checkRyanpeikou(Tehai tehai, Hai addHai, Combi combi) {
		//���������Ă���ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		
		//���q���S�ł���
		if(combi.shunCount < 4){
			return false;
		}
		
		//���q�̑g�ݍ��킹���m�F����
		if(combi.shunIds[0] == combi.shunIds[1]
		&& combi.shunIds[2] == combi.shunIds[3]){
			return true;
		}else{
			return false;
		}
	}
	
	static boolean checkHonitu(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {KIND_WAN,KIND_PIN,KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean honituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				id = jyunTehai[j].getId();
				//�v��(�ݎq�A���q�A���q)�������͎��v
				if (((id & checkId[i]) != 0) || ((id & KIND_TSUU) != 0) ){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinshunsLength(); j++){
				checkHai = tehai.getMinshuns();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)�������͎��v
				if (((id & checkId[i]) != 0) || ((id & KIND_TSUU) != 0) ){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkousLength(); j++){
				checkHai = tehai.getMinkous();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)�������͎��v
				if (((id & checkId[i]) != 0) || ((id & KIND_TSUU) != 0) ){
					honituflg = false;
				}
			}
			
			//���Ȃ̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkansLength(); j++){
				checkHai = tehai.getMinkans();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)�������͎��v
				if (((id & checkId[i]) != 0) || ((id & KIND_TSUU) != 0) ){
					honituflg = false;
				}
			}
			
			//�ÞȂ̔v���`�F�b�N
			for(int j = 0; j < tehai.getAnkansLength(); j++){
				checkHai = tehai.getAnkans();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)�������͎��v
				if (((id & checkId[i]) != 0) || ((id & KIND_TSUU) != 0) ){
					honituflg = false;
				}
			}
			
			//���ꂪ�������Ă���
			if(honituflg == true){
				return true;
			}
			
		}
		//�������Ă��Ȃ���Εs����
		return false;

	}
	
	static boolean checkJunCyan(Tehai tehai, Hai addHai, Combi combi) {
		
		int id;
		Hai checkHai[][]; 
		
		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.kouCount ; i++){
			id = combi.kouIds[i];
			//���v������Εs����
			if( (id & KIND_TSUU) != 0){
				return false;
			}

			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			//���v������Εs����
			if( (id & KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 7))
					return false;
			}			
		}
		
		//����v�̓����`�F�b�N
		id = combi.atamaId;
		//���v������Εs����
		if( (id & KIND_TSUU) != 0){
			return false;
		}
		if ((id & KIND_SHUU) != 0){
			id &= KIND_MASK;
			if ((id > 1) && (id < 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			id &= KIND_MASK;
			//123 �Ɓ@789 �ȊO�̏��q������Εs����
			if ((id > 1) && (id < 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			//���v������Εs����
			if( (id & KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			//���v������Εs����
			if( (id & KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			//���v������Εs����
			if( (id & KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & KIND_SHUU) != 0){
				id &= KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		return true;
	}
	
	static boolean checkSyousangen(Tehai tehai, Hai addHai, Combi combi) {
		//�O���v�����������Ă�����𒲂ׂ�
		int countSangen = 0;
		//�������q
		if(checkHaku(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�������q
		if(checkHatu(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�������q
		if(checkCyun(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�����O���v ���A�O���v����2����
		if(((combi.atamaId & KIND_SANGEN) != 0) && (countSangen == 2)){
			return true;
		}
		
		return false;
	}
	
	static boolean checkHonroutou(Tehai tehai, Hai addHai, Combi combi) {
		//�g�C�g�C���������Ă���
		if(checkToitoi(tehai,addHai,combi) == false){
			return false;
		}
		
		//�`�����^���������Ă���
		if(checkCyanta(tehai,addHai,combi) == true){
			return true;
		}else{
			return false;
		}
	}
/*	
	static boolean checkRenhou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	static boolean checkTinitu(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {KIND_WAN,KIND_PIN,KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean honituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				id = jyunTehai[j].getId();
				//�v��(�ݎq�A���q�A���q)
				if (((id & checkId[i]) != 0)){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinshunsLength(); j++){
				checkHai = tehai.getMinshuns();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)
				if (((id & checkId[i]) != 0)){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkousLength(); j++){
				checkHai = tehai.getMinkous();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)
				if (((id & checkId[i]) != 0)){
					honituflg = false;
				}
			}
			
			//���Ȃ̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkansLength(); j++){
				checkHai = tehai.getMinkans();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)
				if (((id & checkId[i]) != 0)){
					honituflg = false;
				}
			}
			
			//�ÞȂ̔v���`�F�b�N
			for(int j = 0; j < tehai.getAnkansLength(); j++){
				checkHai = tehai.getAnkans();
				id = checkHai[j][0].getId();
				//�v��(�ݎq�A���q�A���q)
				if (((id & checkId[i]) != 0)){
					honituflg = false;
				}
			}
			
			//���ꂪ�������Ă���
			if(honituflg == true){
				return true;
			}
			
		}
		//�������Ă��Ȃ���Εs����
		return false;

	}

	static boolean checkSuuankou(Tehai tehai, Hai addHai, Combi combi) {
		//��v�̈Í���4��
		//TODO �オ�����ۂ��o�オ��A�c���オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 4){
			return true;
		}else{
			return true;
		}
	}

	static boolean checkSuukantu(Tehai tehai, Hai addHai, Combi combi) {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 4){
			return true;
		}else{
			return false;
		}
	}

	static boolean checkDaisangen(Tehai tehai, Hai addHai, Combi combi) {
		//�O���v�����������Ă�����𒲂ׂ�
		int countSangen = 0;
		//�������q
		if(checkHaku(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�������q
		if(checkHatu(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�������q
		if(checkCyun(tehai,addHai,combi) == true){
			countSangen++;
		}
		//�R���v���R�����Ă���
		if(countSangen == 3){
			return true;
		}else{
			return false;
		}
	}
	//TODO �V�a�A�n�a�͌�ōl����B
/*
	static boolean checkTenhou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	static boolean checkTihou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	
	static boolean checkSyousuushi(Tehai tehai, Hai addHai, Combi combi) {
		//���v�����������Ă�����𒲂ׂ�
		int countFon = 0;
		//�������q
		if(checkTon(tehai,addHai,combi) == true){
			countFon++;
		}
		//�삪���q
		if(checkNan(tehai,addHai,combi) == true){
			countFon++;
		}
		//�������q
		if(checkSya(tehai,addHai,combi) == true){
			countFon++;
		}
		//�k�����q
		if(checkPei(tehai,addHai,combi) == true){
			countFon++;
		}

		//�������v ���A���v����3����
		if(((combi.atamaId & KIND_FON) != 0) && (countFon == 3)){
			return true;
		}else{
			return false;
		}
	}

	static boolean checkDaisuushi(Tehai tehai, Hai addHai, Combi combi) {
		//���v�����������Ă�����𒲂ׂ�
		int countFon = 0;
		//�������q
		if(checkTon(tehai,addHai,combi) == true){
			countFon++;
		}
		//�삪���q
		if(checkNan(tehai,addHai,combi) == true){
			countFon++;
		}
		//�������q
		if(checkSya(tehai,addHai,combi) == true){
			countFon++;
		}
		//�k�����q
		if(checkPei(tehai,addHai,combi) == true){
			countFon++;
		}
			//���v����4����
		if(countFon == 4){
			return true;
		}else{
			return false;
		}
	}

	static boolean checkTuuisou(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 
		
		//���q�����邩�ǂ����m�F
		if(checkToitoi(tehai,addHai,combi) == false){
			return false;
		}
		
		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int j = 0; j < jyunTehaiLength; j++) {
			id = jyunTehai[j].getId();
			//�v�����v
			if ((id & KIND_SHUU) != 0){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinshunsLength(); j++){
			checkHai = tehai.getMinshuns();
			id = checkHai[j][0].getId();
			//�v�����v
			if ((id & KIND_SHUU) != 0){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkousLength(); j++){
			checkHai = tehai.getMinkous();
			id = checkHai[j][0].getId();
			//�v�����v
			if ((id & KIND_SHUU) != 0){
				return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkansLength(); j++){
			checkHai = tehai.getMinkans();
			id = checkHai[j][0].getId();
			//�v�����v
			if ((id & KIND_SHUU) != 0){
				return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int j = 0; j < tehai.getAnkansLength(); j++){
			checkHai = tehai.getAnkans();
			id = checkHai[j][0].getId();
			//�v�����v
			if ((id & KIND_SHUU) != 0){
				return false;
			}
		}
		
		return true;
	}

	static boolean checkChinroutou(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 
		
		//���q�����邩�ǂ����m�F
		if(checkToitoi(tehai,addHai,combi) == false){
			return false;
		}
		
		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int j = 0; j < jyunTehaiLength; j++) {
			id = jyunTehai[j].getId();
			//�v�����v�Ȃ�Εs����
			if((id & KIND_TSUU) != 0){
				return false;
			}else{
				//���v�̏ꍇ1,9�v�ȊO�͕s����
				id &= KIND_MASK;
				if ((id > 1) && (id < 9)){
					return false;
				}
			}
		}
		
		//�����̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinshunsLength(); j++){
			checkHai = tehai.getMinshuns();
			id = checkHai[j][0].getId();
			//�v�����v�Ȃ�Εs����
			if((id & KIND_TSUU) != 0){
				return false;
			}else{
				//���v�̏ꍇ1,9�v�ȊO�͕s����
				id &= KIND_MASK;
				if ((id > 1) && (id < 9)){
					return false;
				}
			}
		}
		
		//�����̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkousLength(); j++){
			checkHai = tehai.getMinkous();
			id = checkHai[j][0].getId();
			//�v�����v�Ȃ�Εs����
			if((id & KIND_TSUU) != 0){
				return false;
			}else{
				//���v�̏ꍇ1,9�v�ȊO�͕s����
				id &= KIND_MASK;
				if ((id > 1) && (id < 9)){
					return false;
				}
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkansLength(); j++){
			checkHai = tehai.getMinkans();
			id = checkHai[j][0].getId();
			//�v�����v�Ȃ�Εs����
			if((id & KIND_TSUU) != 0){
				return false;
			}else{
				//���v�̏ꍇ1,9�v�ȊO�͕s����
				id &= KIND_MASK;
				if ((id > 1) && (id < 9)){
					return false;
				}
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int j = 0; j < tehai.getAnkansLength(); j++){
			checkHai = tehai.getAnkans();
			id = checkHai[j][0].getId();
			//�v�����v�Ȃ�Εs����
			if((id & KIND_TSUU) != 0){
				return false;
			}else{
				//���v�̏ꍇ1,9�v�ȊO�͕s����
				id &= KIND_MASK;
				if ((id > 1) && (id < 9)){
					return false;
				}
			}
		}
		
		return true;	}

	static boolean checkRyuuisou(Tehai tehai, Hai addHai, Combi combi) {
		int checkId[] = {KIND_SOU+2,KIND_SOU+3,KIND_SOU+4,KIND_SOU+6,KIND_SOU+8,KIND_HATU};
		int id;
		boolean ryuuisouflg = false;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			id = jyunTehai[i].getId();
			ryuuisouflg = false;
			for(int j = 0 ; j < checkId.length ; j++){
				//�Έ�F�Ɏg�p�ł���v������
				if(id == checkId[j]){
					ryuuisouflg = true;
				}
			}
			//�Y������v�ł͂Ȃ�����
			if(ryuuisouflg == false){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getId();
			//���q��2,3,4�ȊO�̏��q���������ꍇ�s����
			if (id != (KIND_SOU + 2)){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			ryuuisouflg = false;
			for(int j = 0 ; j < checkId.length ; j++){
				//�Έ�F�Ɏg�p�ł���v������
				if(id == checkId[j]){
					ryuuisouflg = true;
				}
			}
			//�Y������v�ł͂Ȃ�����
			if(ryuuisouflg == false){
				return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			ryuuisouflg = false;
			for(int j = 0 ; j < checkId.length ; j++){
				//�Έ�F�Ɏg�p�ł���v������
				if(id == checkId[j]){
					ryuuisouflg = true;
				}
			}
			//�Y������v�ł͂Ȃ�����
			if(ryuuisouflg == false){
				return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getId();
			if ((id & KIND_SHUU) == 0)
				ryuuisouflg = false;
			for(int j = 0 ; j < checkId.length ; j++){
				//�Έ�F�Ɏg�p�ł���v������
				if(id == checkId[j]){
					ryuuisouflg = true;
				}
			}
			//�Y������v�ł͂Ȃ�����
			if(ryuuisouflg == false){
				return false;
			}
		}
		
		//�����ɊY������
		return true;
	}

	static boolean checkCyuurennpoutou(Tehai tehai, Hai addHai, Combi combi) {
		int id;
		
		//�v�̐��𒲂ׂ邽�߂̔z�� (0�Ԓn�͎g�p���Ȃ��j
		int countNumber[] = {0,0,0,0,0,0,0,0,0,0};
		Hai checkHai[] = new Hai[JYUNTEHAI_MAX];
		
		//��������ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		//��v������ɂȂ��Ă��Ȃ��ꍇ���������Ȃ�
		if(checkTinitu(tehai,addHai,combi) == false){
			return false;
		}
		
		//��v���R�s�[����
		checkHai = tehai.getJyunTehai();
		
		//��v�ɂ���v�̔ԍ��𒲂ׂ�
		for(int i = 0 ; i < tehai.getJyunTehaiLength() ; i++){
			id = checkHai[i].getId();
			//�������`�F�b�N����
			id &= KIND_MASK;
			//����-1�̔ԍ����C���N�������g����
			countNumber[id]++;
		}
		
		//��@�󓕂ɂȂ��Ă��邩���ׂ�i1��9���R���ȏ� 2�`8���P���ȏ�)
		if(( countNumber[1] >= 3)
		&& ( countNumber[2] >= 1)
		&& ( countNumber[3] >= 1)
		&& ( countNumber[4] >= 1)
		&& ( countNumber[5] >= 1)
		&& ( countNumber[6] >= 1)
		&& ( countNumber[7] >= 1)
		&& ( countNumber[8] >= 1)
		&& ( countNumber[9] >= 3)){
			return true;
		}
		
		return false;
	}
}
