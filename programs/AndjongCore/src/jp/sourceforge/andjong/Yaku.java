package jp.sourceforge.andjong;
import static jp.sourceforge.andjong.Hai.*;
import static jp.sourceforge.andjong.Tehai.JYUNTEHAI_MAX;
import jp.sourceforge.andjong.Tehai.Combi;


public class Yaku {
	
	YakuHantei yakuhantei[] = new YakuHantei[50];
	Yaku(Tehai tehai, Hai addHai, Combi combi){
		int i = 0;
		yakuhantei[i++] = new CheckTanyao(tehai,addHai,combi);
		yakuhantei[i++] = new CheckPinfu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckIpeikou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckTon(tehai,addHai,combi);
		yakuhantei[i++] = new CheckNan(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSya(tehai,addHai,combi);
		yakuhantei[i++] = new CheckPei(tehai,addHai,combi);
		yakuhantei[i++] = new CheckHaku(tehai,addHai,combi);
		yakuhantei[i++] = new CheckHatu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckCyun(tehai,addHai,combi);
		yakuhantei[i++] = new CheckCyanta(tehai,addHai,combi);
		yakuhantei[i++] = new CheckIkkituukan(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSansyokuDoukou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSansyokuDoujun(tehai,addHai,combi);
		yakuhantei[i++] = new CheckToitoi(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSanankou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSankantu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckRyanpeikou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckHonitu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckJunCyan(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSyousangen(tehai,addHai,combi);
		yakuhantei[i++] = new CheckHonroutou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckTinitu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSuuankou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSuukantu(tehai,addHai,combi);
		yakuhantei[i++] = new CheckDaisangen(tehai,addHai,combi);
		yakuhantei[i++] = new CheckSyousuushi(tehai,addHai,combi);
		yakuhantei[i++] = new CheckDaisuushi(tehai,addHai,combi);
		yakuhantei[i++] = new CheckDaisangen(tehai,addHai,combi);
		yakuhantei[i++] = new CheckTuuisou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckChinroutou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckRyuuisou(tehai,addHai,combi);
		yakuhantei[i++] = new CheckCyuurennpoutou(tehai,addHai,combi);
		yakuhantei[i++] = null;
	}
	
	int getHanSuu(){
		int hanSuu = 0;
		for(int i = 0 ; yakuhantei[i] != null ; i++){
			if( yakuhantei[i].getYakuHantei() == true){
				hanSuu+= yakuhantei[i].getHanSuu();
			}
		}
		return hanSuu;
	}

	String getYakuName(){
		String yakuName ="";
		for(int i = 0 ; yakuhantei[i] != null ; i++){
			if( yakuhantei[i].getYakuHantei() == true){
				yakuName = yakuhantei[i].getYakuName();
			}
		}
		return yakuName;
	}
	
	boolean getYakumanflg(){
		for(int i = 0 ; yakuhantei[i] != null ; i++){
			if( yakuhantei[i].getYakuman() == true){
				return true;
			}
		}
		return false;		
	}
	
	private class YakuHantei{
		boolean hantei = false;
		boolean yakuman = false;
		String  yakuName;
		int hanSuu;
		
		boolean getYakuHantei(){
			return hantei;
		}
		int getHanSuu(){
			return hanSuu;
		}
		
		String getYakuName(){
			return yakuName;
		}
		
		boolean getYakuman(){
			return yakuman;
		}
	}

	private class CheckTanyao extends YakuHantei{
		CheckTanyao(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkTanyao(tehai, addHai, combi);
			yakuName = "�f��";
			hanSuu = 1;
		}
	}

	private class CheckPinfu extends YakuHantei{
		CheckPinfu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkPinfu(tehai, addHai, combi);
			yakuName = "���a";
			hanSuu = 1;
		}
	}

	private class CheckIpeikou extends YakuHantei{
		CheckIpeikou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkIpeikou(tehai, addHai, combi);
			if(checkRyanpeikou(tehai, addHai, combi)){
				hantei = false;
			}
			yakuName = "��u��";
			hanSuu = 1;
		}
	}

	private class CheckTon extends YakuHantei{
		CheckTon(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkTon(tehai, addHai, combi);
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckNan extends YakuHantei{
		CheckNan(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkNan(tehai, addHai, combi);
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckSya extends YakuHantei{
		CheckSya(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSya(tehai, addHai, combi);
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckPei extends YakuHantei{
		CheckPei(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkPei(tehai, addHai, combi);
			yakuName = "�k";
			hanSuu = 1;
		}
	}

	private class CheckHaku extends YakuHantei{
		CheckHaku(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkHaku(tehai, addHai, combi);
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckHatu extends YakuHantei{
		CheckHatu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkHatu(tehai, addHai, combi);
			yakuName = "�";
			hanSuu = 1;
		}
	}

	private class CheckCyun extends YakuHantei{
		CheckCyun(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkCyun(tehai, addHai, combi);
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckCyanta extends YakuHantei{
		CheckCyanta(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkCyanta(tehai, addHai, combi);
			if(checkJunCyan(tehai, addHai, combi)){
				hantei = false;
			}
			if(checkHonroutou(tehai, addHai, combi)){
				hantei = false;
			}	
			yakuName = "�S��";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckIkkituukan extends YakuHantei{
		CheckIkkituukan(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkIkkituukan(tehai, addHai, combi);
			yakuName = "��C�ʊ�";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckSansyokuDoujun extends YakuHantei{
		CheckSansyokuDoujun(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSansyokuDoujun(tehai, addHai, combi);
			yakuName = "�O�F����";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckSansyokuDoukou extends YakuHantei{
		CheckSansyokuDoukou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSansyokuDoukou(tehai, addHai, combi);
			yakuName = "�O�F����";
			hanSuu = 2;
		}
	}

	private class CheckToitoi extends YakuHantei{
		CheckToitoi(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkToitoi(tehai, addHai, combi);
			yakuName = "�΁X�a";
			hanSuu = 2;
		}
	}

	private class CheckSanankou extends YakuHantei{
		CheckSanankou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSanankou(tehai, addHai, combi);
			yakuName = "�O�Í�";
			hanSuu = 2;
		}
	}

	private class CheckSankantu extends YakuHantei{
		CheckSankantu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSankantu(tehai, addHai, combi);
			yakuName = "�O�Ȏq";
			hanSuu = 2;
		}
	}

	private class CheckRyanpeikou extends YakuHantei{
		CheckRyanpeikou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkRyanpeikou(tehai, addHai, combi);
			yakuName = "��u��";
			hanSuu = 3;
		}
	}

	private class CheckHonitu extends YakuHantei{
		CheckHonitu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkHonitu(tehai, addHai, combi);
			yakuName = "����F";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 2;
			}else{
				hanSuu = 3;
			}
		}
	}

	private class CheckJunCyan extends YakuHantei{
		CheckJunCyan(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkJunCyan(tehai, addHai, combi);
			yakuName = "���S��";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 2;
			}else{
				hanSuu = 3;
			}
		}
	}

	private class CheckSyousangen extends YakuHantei{
		CheckSyousangen(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSyousangen(tehai, addHai, combi);
			yakuName = "���O��";
			hanSuu = 2;
		}
	}

	private class CheckHonroutou extends YakuHantei{
		CheckHonroutou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkHonroutou(tehai, addHai, combi);
			yakuName = "���V��";
			hanSuu = 2;
		}
	}

	private class CheckTinitu extends YakuHantei{
		CheckTinitu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkTinitu(tehai, addHai, combi);
			yakuName = "����F";
			if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
				hanSuu = 5;
			}else{
				hanSuu = 6;
			}
		}
	}

	private class CheckSuuankou extends YakuHantei{
		CheckSuuankou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSuuankou(tehai, addHai, combi);
			yakuName = "�l�Í�";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckSuukantu extends YakuHantei{
		CheckSuukantu(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSuukantu(tehai, addHai, combi);
			yakuName = "�l�Ȏq";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckDaisangen extends YakuHantei{
		CheckDaisangen(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkDaisangen(tehai, addHai, combi);
			yakuName = "��O��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckSyousuushi extends YakuHantei{
		CheckSyousuushi(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkSyousuushi(tehai, addHai, combi);
			yakuName = "���l��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckDaisuushi extends YakuHantei{
		CheckDaisuushi(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkDaisuushi(tehai, addHai, combi);
			yakuName = "��l��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckTuuisou extends YakuHantei{
		CheckTuuisou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkTuuisou(tehai, addHai, combi);
			yakuName = "����F";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckChinroutou extends YakuHantei{
		CheckChinroutou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkChinroutou(tehai, addHai, combi);
			yakuName = "���V��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckRyuuisou extends YakuHantei{
		CheckRyuuisou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkRyuuisou(tehai, addHai, combi);
			yakuName = "�Έ�F";
			hanSuu = 13;
			yakuman = true;
		}
	}
	private class CheckCyuurennpoutou extends YakuHantei{
		CheckCyuurennpoutou(Tehai tehai, Hai addHai, Combi combi){
			hantei = checkCyuurennpoutou(tehai, addHai, combi);
			yakuName = "��@��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	
	boolean checkTanyao(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkPinfu(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkIpeikou(Tehai tehai, Hai addHai, Combi combi) {
				
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
	boolean checkReach(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	boolean checkIppatu(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	boolean checkTumo(Tehai tehai, Hai addHai, Combi combi) {
		//���������Ă���ꍇ�͐������Ȃ�
		if(tehai.getJyunTehaiLength() < JYUNTEHAI_MAX){
			return false;
		}
		
		return true;
	}
*/

	//��v���ł��Ă��邩�ǂ����̔���Ɏg���⏕���\�b�h
	private boolean checkYakuHai(Tehai tehai, Combi combi , int yakuHaiId) {
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
	
	boolean checkTon(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_TON);
	}

	boolean checkNan(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_NAN);
	}

	boolean checkSya(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_SYA);
	}

	boolean checkPei(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_PEE);
	}

	boolean checkHaku(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_HAKU);
	}

	boolean checkHatu(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_HATU);
	}

	boolean checkCyun(Tehai tehai, Hai addHai, Combi combi) {
		return checkYakuHai(tehai,combi,KIND_CYUN);
	}
	
	//TODO ����Ȗ��͌��
/*
	boolean checkHaitei(Tehai tehai, Hai addHai, Combi combi) {

		return true;
	}
	
	boolean checkRinsyan(Tehai tehai, Hai addHai, Combi combi) {

		return true;
	}
	boolean checkCyankan(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
	
	boolean checkDoubleReach(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
	
	boolean checkTeetoitu(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	
	boolean checkCyanta(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkIkkituukan(Tehai tehai, Hai addHai, Combi combi) {
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
	
	boolean checkSansyokuDoujun(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkSansyokuDoukou(Tehai tehai, Hai addHai, Combi combi) {
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
	
	boolean checkToitoi(Tehai tehai, Hai addHai, Combi combi) {
		//��v�ɏ��q������
		if((combi.shunCount != 0) || (tehai.getMinshunsLength() != 0) ){
			return false;
		}else{
			return true;
		}
	}
	
	boolean checkSanankou(Tehai tehai, Hai addHai, Combi combi) {
		//��v�̈Í����R��
		//TODO �オ�����ۂ��o�オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 3){
			return true;
		}else{
			return true;
		}
	}
	
	boolean checkSankantu(Tehai tehai, Hai addHai, Combi combi) {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 3){
			return true;
		}else{
			return false;
		}
	}

	boolean checkRyanpeikou(Tehai tehai, Hai addHai, Combi combi) {
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
	
	boolean checkHonitu(Tehai tehai, Hai addHai, Combi combi) {
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
	
	boolean checkJunCyan(Tehai tehai, Hai addHai, Combi combi) {
		
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
	
	boolean checkSyousangen(Tehai tehai, Hai addHai, Combi combi) {
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
	
	boolean checkHonroutou(Tehai tehai, Hai addHai, Combi combi) {
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
	boolean checkRenhou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	boolean checkTinitu(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkSuuankou(Tehai tehai, Hai addHai, Combi combi) {
		//��v�̈Í���4��
		//TODO �オ�����ۂ��o�オ��A�c���オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 4){
			return true;
		}else{
			return true;
		}
	}

	boolean checkSuukantu(Tehai tehai, Hai addHai, Combi combi) {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 4){
			return true;
		}else{
			return false;
		}
	}

	boolean checkDaisangen(Tehai tehai, Hai addHai, Combi combi) {
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
	boolean checkTenhou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}

	boolean checkTihou(Tehai tehai, Hai addHai, Combi combi) {
		return true;
	}
*/
	
	boolean checkSyousuushi(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkDaisuushi(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkTuuisou(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkChinroutou(Tehai tehai, Hai addHai, Combi combi) {
		//���q�����邩�ǂ����m�F
		if(checkToitoi(tehai,addHai,combi) == false){
			return false;
		}
		
		//���q�Ȃ��ŃW�����`�������������Ă��邩�i1��9�݂̂ō쐬�j
		if(checkJunCyan(tehai,addHai,combi) == false){
			return false;
		}
		
		return true;
		
	}

	boolean checkRyuuisou(Tehai tehai, Hai addHai, Combi combi) {
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

	boolean checkCyuurennpoutou(Tehai tehai, Hai addHai, Combi combi) {
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
			//�����̔ԍ����C���N�������g����
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
