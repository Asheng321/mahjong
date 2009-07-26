package jp.sourceforge.andjong;
import static jp.sourceforge.andjong.Hai.*;
import static jp.sourceforge.andjong.Tehai.JYUNTEHAI_MAX;
import jp.sourceforge.andjong.Tehai.Combi;

/**
 * ��v�S�̖̂��𔻒肷��N���X�ł��B
 * 
 * @author Hiroyuki Muromachi
 * 
 */
public class Yaku {
	public static final int JIKAZE_TON = 0;
	public static final int JIKAZE_NAN = 1;
	public static final int JIKAZE_SYA = 2;
	public static final int JIKAZE_PEI = 3;
	
	Tehai tehai;
	Hai addHai;
	Combi combi;
	Info info;
	YakuHantei yakuhantei[];
	boolean nakiflg = false;

	/**
	 * Yaku�N���X�̃R���X�g���N�^�B
	 * ������ۑ����AYakuHantei�N���X�̔z����쐬����B
	 * @param tehai ��v�@addHai �オ�����v  combi ��v �̑g�ݍ��킹 info ���
	 */
	Yaku(Tehai tehai, Hai addHai, Combi combi,Info info){
		this.tehai = tehai;
		this.addHai = addHai;
		this.combi  = combi;
		this.info   = info;
		//��������ꍇ
		if((tehai.getMinkansLength() != 0)
		|| (tehai.getMinkousLength() != 0)
		|| (tehai.getMinshunsLength() != 0)
		){
			nakiflg = true;
		}
		
		YakuHantei buffer[] = {new CheckTanyao(),
							   new CheckPinfu(),
							   new CheckIpeikou(),
							   new CheckReach(),
							   new CheckIppatu(),
							   new CheckTon(),
							   new CheckNan(),
							   new CheckSya(),
							   new CheckPei(),
							   new CheckHaku(),
							   new CheckHatu(),
							   new CheckCyun(),
							   new CheckHaitei(),
							   new CheckRinsyan(),
							   new CheckCyankan(),
							   new CheckDoubleReach(),
							   new CheckTeetoitu(),
							   new CheckCyanta(),
							   new CheckIkkituukan(),
							   new CheckSansyokuDoukou(),
							   new CheckSansyokuDoujun(),
							   new CheckToitoi(),
							   new CheckSanankou(),
							   new CheckSankantu(),
							   new CheckRyanpeikou(),
							   new CheckHonitu(),
							   new CheckJunCyan(),
							   new CheckSyousangen(),
							   new CheckHonroutou(),
							   new CheckTinitu(),
							   new CheckSuuankou(),
							   new CheckSuukantu(),
							   new CheckDaisangen(),
							   new CheckSyousuushi(),
							   new CheckDaisuushi(),
							   new CheckTuuisou(),
							   new CheckChinroutou(),
							   new CheckRyuuisou(),
							   new CheckCyuurennpoutou(),
							   new CheckKokushi()
		};
		
		yakuhantei = buffer;

		//�𖞐������͑��̈�ʖ��͐؂�̂Ă�
		for(int i = 0 ; i < yakuhantei.length ; i++){
			if((yakuhantei[i].getYakuman() == true) && (yakuhantei[i].getYakuHantei() == true)) {
				for(int j = 0 ; j < yakuhantei.length; j++){
					if(yakuhantei[j].getYakuman() == false){
						yakuhantei[j].setYakuHantei(false);
					}
				}
			}
		}
	}
	
	/**
	 * ��v�S�̖̂|�����擾���܂��B
	 * 
	 * @return ��v�S�̖̂|��
	 */
	int getHanSuu(){
		int hanSuu = 0;
		for(int i = 0 ; i < yakuhantei.length ; i++){
			if( yakuhantei[i].getYakuHantei() == true){
				hanSuu+= yakuhantei[i].getHanSuu();
			}
		}

		return hanSuu;
	}
	
	/**
	 * �������Ă�����̖��O���擾���܂��B
	 * 
	 * @return �������Ă�����̖��O�̔z��
	 */
	String[] getYakuName(){
		int count = 0;
		
		//�������Ă�����̐����J�E���g
		for(int i = 0 ; i < yakuhantei.length ; i++){
			if( yakuhantei[i].getYakuHantei() == true){
				count++;
			}
		}
		
		String yakuName[] = new String[count];
		count = 0;
		for(int i = 0 ; i < yakuhantei.length ; i++){
			if( yakuhantei[i].getYakuHantei() == true){
				yakuName[count] = yakuhantei[i].getYakuName() + " " + yakuhantei[i].getHanSuu() + "�|";
				count++;
			}
		}
		return yakuName;
	}
	
	/**
	 * �𖞂��������Ă��邩���擾���܂��B
	 * 
	 * @return �𖞐����t���O
	 */	
	boolean getYakumanflg(){
		for(int i = 0 ; yakuhantei[i] != null ; i++){
			if( yakuhantei[i].getYakuman() == true){
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * �ʂ̖��𔻒肷��N���X�ł��B
	 * 
	 * @author Hiroyuki Muromachi
	 * 
	 */
	private class YakuHantei{
		/** ���̐�������t���O */
		boolean hantei = false;
		/** �𖞂̔���t���O */
		boolean yakuman = false;
		/** ���̖��O */
		String  yakuName;
		/** ���̖|�� */
		int hanSuu;
		
		/**
		 * ���̐�������t���O���擾���܂��B
		 * 
		 * @return ���̐�������t���O
		 */
		boolean getYakuHantei(){
			return hantei;
		}
		
		/**
		 * ���̐�������t���O��ݒ肵�܂��B
		 * 
		 * @param hantei
		 */
		void setYakuHantei(boolean hantei){
			this.hantei = hantei;
		}
		
		/**
		 * ���̖|�����擾���܂��B
		 * 
		 * @return ���̖|��
		 */		
		int getHanSuu(){
			return hanSuu;
		}
		
		/**
		 * ���̖��O���擾���܂��B
		 * 
		 * @return ���̖��O
		 */			
		String getYakuName(){
			return yakuName;
		}
		
		/**
		 * �𖞂̔���t���O���擾���܂��B
		 * 
		 * @return �𖞂̔���t���O
		 */			
		boolean getYakuman(){
			return yakuman;
		}
	}

	private class CheckTanyao extends YakuHantei{
		CheckTanyao(){
			hantei = checkTanyao();
			yakuName = "�f��";
			hanSuu = 1;
		}
	}

	private class CheckPinfu extends YakuHantei{
		CheckPinfu(){
			hantei = checkPinfu();
			yakuName = "���a";
			hanSuu = 1;
		}
	}

	private class CheckIpeikou extends YakuHantei{
		CheckIpeikou(){
			hantei = checkIpeikou();
			if(checkRyanpeikou()){
				hantei = false;
			}
			yakuName = "��u��";
			hanSuu = 1;
		}
	}
	
	private class CheckReach extends YakuHantei{
		CheckReach(){
			hantei = checkReach();
			yakuName = "����";
			hanSuu = 1;
		}
	}
	
	private class CheckIppatu extends YakuHantei{
		CheckIppatu(){
			hantei = checkIppatu();
			yakuName = "�ꔭ";
			hanSuu = 1;
		}
	}

	private class CheckTon extends YakuHantei{
		CheckTon(){
			hantei = checkTon();
			if(info.getJikaze() == JIKAZE_TON){
				yakuName = "�_�u��";
				hanSuu = 2;
			}else{
				yakuName = "��";
				hanSuu = 1;
			}
		}
	}

	private class CheckNan extends YakuHantei{
		CheckNan(){
			hantei = checkNan();
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckSya extends YakuHantei{
		CheckSya(){
			hantei = checkSya();
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckPei extends YakuHantei{
		CheckPei(){
			hantei = checkPei();
			yakuName = "�k";
			hanSuu = 1;
		}
	}

	private class CheckHaku extends YakuHantei{
		CheckHaku(){
			hantei = checkHaku();
			yakuName = "��";
			hanSuu = 1;
		}
	}

	private class CheckHatu extends YakuHantei{
		CheckHatu(){
			hantei = checkHatu();
			yakuName = "�";
			hanSuu = 1;
		}
	}

	private class CheckCyun extends YakuHantei{
		CheckCyun(){
			hantei = checkCyun();
			yakuName = "��";
			hanSuu = 1;
		}
	}
	
	private class CheckHaitei extends YakuHantei{
		CheckHaitei(){
			hantei = checkHaitei();
			yakuName = "�C��̌�";
			hanSuu = 1;
		}
	}	
	
	private class CheckRinsyan extends YakuHantei{
		CheckRinsyan(){
			hantei = checkRinsyan();
			yakuName = "���J��";
			hanSuu = 1;
		}
	}

	private class CheckCyankan extends YakuHantei{
		CheckCyankan(){
			hantei = checkCyankan();
			yakuName = "����";
			hanSuu = 1;
		}
	}

	private class CheckDoubleReach extends YakuHantei{
		CheckDoubleReach(){
			hantei = checkDoubleReach();
			yakuName = "�_�u������";
			hanSuu = 2;
		}
	}

	private class CheckTeetoitu extends YakuHantei{
		CheckTeetoitu(){
			hantei = checkTeetoitu();
			yakuName = "���Ύq";
			hanSuu = 2;
		}
	}

	private class CheckCyanta extends YakuHantei{
		CheckCyanta(){
			hantei = checkCyanta();
			if(checkJunCyan()){
				hantei = false;
			}
			if(checkHonroutou()){
				hantei = false;
			}	
			yakuName = "�S��";
			if (nakiflg == true) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckIkkituukan extends YakuHantei{
		CheckIkkituukan(){
			hantei = checkIkkituukan();
			yakuName = "��C�ʊ�";
			if (nakiflg == true) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckSansyokuDoujun extends YakuHantei{
		CheckSansyokuDoujun(){
			hantei = checkSansyokuDoujun();
			yakuName = "�O�F����";
			if (nakiflg == true) {
				hanSuu = 1;
			}else{
				hanSuu = 2;
			}
		}
	}

	private class CheckSansyokuDoukou extends YakuHantei{
		CheckSansyokuDoukou(){
			hantei = checkSansyokuDoukou();
			yakuName = "�O�F����";
			hanSuu = 2;
		}
	}

	private class CheckToitoi extends YakuHantei{
		CheckToitoi(){
			hantei = checkToitoi();
			yakuName = "�΁X�a";
			hanSuu = 2;
		}
	}

	private class CheckSanankou extends YakuHantei{
		CheckSanankou(){
			hantei = checkSanankou();
			yakuName = "�O�Í�";
			hanSuu = 2;
		}
	}

	private class CheckSankantu extends YakuHantei{
		CheckSankantu(){
			hantei = checkSankantu();
			yakuName = "�O�Ȏq";
			hanSuu = 2;
		}
	}

	private class CheckRyanpeikou extends YakuHantei{
		CheckRyanpeikou(){
			hantei = checkRyanpeikou();
			yakuName = "��u��";
			hanSuu = 3;
		}
	}

	private class CheckHonitu extends YakuHantei{
		CheckHonitu(){
			hantei = checkHonitu();
			if(checkTinitu()){
				hantei = false;
			}
			yakuName = "����F";
			if (nakiflg == true) {
				hanSuu = 2;
			}else{
				hanSuu = 3;
			}
		}
	}

	private class CheckJunCyan extends YakuHantei{
		CheckJunCyan(){
			hantei = checkJunCyan();
			yakuName = "���S��";
			if (nakiflg == true) {
				hanSuu = 2;
			}else{
				hanSuu = 3;
			}
		}
	}

	private class CheckSyousangen extends YakuHantei{
		CheckSyousangen(){
			hantei = checkSyousangen();
			yakuName = "���O��";
			hanSuu = 2;
		}
	}

	private class CheckHonroutou extends YakuHantei{
		CheckHonroutou(){
			hantei = checkHonroutou();
			yakuName = "���V��";
			hanSuu = 2;
		}
	}

	private class CheckTinitu extends YakuHantei{
		CheckTinitu(){
			hantei = checkTinitu();
			yakuName = "����F";
			if (nakiflg == true) {
				hanSuu = 5;
			}else{
				hanSuu = 6;
			}
		}
	}

	private class CheckSuuankou extends YakuHantei{
		CheckSuuankou(){
			hantei = checkSuuankou();
			yakuName = "�l�Í�";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckSuukantu extends YakuHantei{
		CheckSuukantu(){
			hantei = checkSuukantu();
			yakuName = "�l�Ȏq";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckDaisangen extends YakuHantei{
		CheckDaisangen(){
			hantei = checkDaisangen();
			yakuName = "��O��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckSyousuushi extends YakuHantei{
		CheckSyousuushi(){
			hantei = checkSyousuushi();
			yakuName = "���l��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckDaisuushi extends YakuHantei{
		CheckDaisuushi(){
			hantei = checkDaisuushi();
			yakuName = "��l��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckTuuisou extends YakuHantei{
		CheckTuuisou(){
			hantei = checkTuuisou();
			yakuName = "����F";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckChinroutou extends YakuHantei{
		CheckChinroutou(){
			hantei = checkChinroutou();
			yakuName = "���V��";
			hanSuu = 13;
			yakuman = true;
		}
	}

	private class CheckRyuuisou extends YakuHantei{
		CheckRyuuisou(){
			hantei = checkRyuuisou();
			yakuName = "�Έ�F";
			hanSuu = 13;
			yakuman = true;
		}
	}
	private class CheckCyuurennpoutou extends YakuHantei{
		CheckCyuurennpoutou(){
			hantei = checkCyuurennpoutou();
			yakuName = "��@��";
			hanSuu = 13;
			yakuman = true;
		}
	}
	private class CheckKokushi extends YakuHantei{
		CheckKokushi(){
			hantei = checkKokushi();
			yakuName = "���m���o";
			hanSuu = 13;
			yakuman = true;
		}
	}

	
	boolean checkTanyao() {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			id = jyunTehai[i].getOldId();
			//���v�łȂ���Εs����
			if ((id & OLD_KIND_SHUU) == 0)
				return false;
			id &= OLD_KIND_MASK;
			//����1��9�Ȃ�Εs����
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getOldId();
			id &= OLD_KIND_MASK;
			//123 �Ɓ@789 �̏��q������Εs����
			if ((id == 1) || (id == 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getOldId();
			if ((id & OLD_KIND_SHUU) == 0)
				return false;
			id &= OLD_KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			if ((id & OLD_KIND_SHUU) == 0)
				return false;
			id &= OLD_KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
			if ((id & OLD_KIND_SHUU) == 0)
				return false;
			id &= OLD_KIND_MASK;
			if ((id == 1) || (id == 9))
				return false;
		}
		
		return true;
	}

	boolean checkPinfu() {
		int id;
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}
		
		//�ʎq�����q�����ł͂Ȃ�
		if(combi.shunCount != 4){
			return false;
		}
		
		//�����O���v 
		id = combi.atamaId;
		if( (id & OLD_KIND_SANGEN) != 0){
			return false;
		}
		
		//�����ꕗ
		if( (id & OLD_KIND_TON) != 0){
			return false;
		}
		
		//��������
		if(((id & OLD_KIND_FON) != 0) && 
				(id & OLD_KIND_MASK) == (info.getJikaze()+1)){
			return false;
		}		

		int addHaiid = addHai.getOldId();
		//���v�̓��҂��̏ꍇ�͕s����
		if((addHaiid & OLD_KIND_TSUU) != 0){
			return false;
		}

		//�҂������ʑ҂�������
		boolean ryanmenflg = false;
		//�オ��v�̐����`�F�b�N���ďꍇ����
		switch(addHaiid & OLD_KIND_MASK){
			//�オ��v��1,2,3�̏ꍇ��123,234,345�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 1:
			case 2:
			case 3:
				for(int i = 0 ; i < combi.shunCount ; i++){
					if(addHaiid == combi.shunIds[i]){
						ryanmenflg = true;
					}
				}
				break;
			//�オ��v��4,5,6�̏ꍇ��456��234,567��345,678��456�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 4:
			case 5:
			case 6:
				for(int i = 0 ; i < combi.shunCount ; i++){
					if((addHaiid == combi.shunIds[i])
					 ||(addHaiid-2 == combi.shunIds[i])){
						ryanmenflg = true;
					}
				}
				break;
			//�オ��v��7,8,9�̏ꍇ��567,678,789�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 7:
			case 8:
			case 9:
				for(int i = 0 ; i < combi.shunCount ; i++){
					if(addHaiid-2 == (combi.shunIds[i])){
						ryanmenflg = true;
					}
				}
				break;
			default:
				break;
		}
		if(ryanmenflg == false){
			return false;
		}

		
		//�����𖞂����Ă���΁A�񐬗�
		return true;
	}

	boolean checkIpeikou() {
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
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
	
	
	boolean checkReach() {
		return info.isReach(info.getJikaze());
	}
	
	boolean checkIppatu() {
		//TODO �ꔭ�̔���
		return false;
	}

	boolean checkTumo() {
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}
		//TODO �c���オ�肩�ǂ����̔���
		return true;
	}


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
			id = checkHai[i][0].getOldId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
			//ID�Ɩ�v��ID���`�F�b�N
			if( id == yakuHaiId ){
				return true;
			}
		}
		return false;
	}
	
	boolean checkTon() {
		return checkYakuHai(tehai,combi,OLD_KIND_TON);
	}

	boolean checkNan() {
		if(info.getJikaze() == JIKAZE_NAN){
			return checkYakuHai(tehai,combi,OLD_KIND_NAN);
		}else{
			return false;
		}
	}

	boolean checkSya() {
		if(info.getJikaze() == JIKAZE_SYA){
			return checkYakuHai(tehai,combi,OLD_KIND_NAN);
		}else{
			return false;
		}
	}

	boolean checkPei() {
		if(info.getJikaze() == JIKAZE_PEI){
			return checkYakuHai(tehai,combi,OLD_KIND_NAN);
		}else{
			return false;
		}
	}

	boolean checkHaku() {
		return checkYakuHai(tehai,combi,OLD_KIND_HAKU);
	}

	boolean checkHatu() {
		return checkYakuHai(tehai,combi,OLD_KIND_HATU);
	}

	boolean checkCyun() {
		return checkYakuHai(tehai,combi,OLD_KIND_CYUN);
	}
	
	//TODO ����Ȗ��͌��

	boolean checkHaitei() {
		//TODO�@�C��̌�
		return false;
	}
	
	boolean checkRinsyan() {
		//TODO ���J��
		return false;
	}
	boolean checkCyankan() {
		//TODO ����
		return false;
	}
	
	boolean checkDoubleReach() {
		//TODO �_�u�����[�`
		return false;
	}
	
	boolean checkTeetoitu() {
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}
		//TODO ���Ύq
		return false;
	}
	
	boolean checkCyanta() {
		int id;
		Hai checkHai[][]; 

		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.kouCount ; i++){
			id = combi.kouIds[i];
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 7))
					return false;
			}			
		}
		
		//����v�̓����`�F�b�N
		id = combi.atamaId;
		if ((id & OLD_KIND_SHUU) != 0){
			id &= OLD_KIND_MASK;
			if ((id > 1) && (id < 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getOldId();
			id &= OLD_KIND_MASK;
			//123 �Ɓ@789 �ȊO�̏��q������Εs����
			if ((id > 1) && (id < 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getOldId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		return true;
	}

	boolean checkIkkituukan() {
		int id;
		Hai checkHai[][]; 
		boolean ikkituukanflg[]= {false,false,false,false,false,false,false,false,false};
		//�ݎq�A���q�A���q��1,4,7���`�F�b�N
		int checkId[] = {OLD_KIND_WAN+1,OLD_KIND_WAN+4,OLD_KIND_WAN+7,OLD_KIND_PIN+1,OLD_KIND_PIN+4,OLD_KIND_PIN+7,OLD_KIND_SOU+1,OLD_KIND_SOU+4,OLD_KIND_SOU+7};
		
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
			id = checkHai[i][0].getOldId();
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
		int checkId[] = {OLD_KIND_WAN,OLD_KIND_PIN,OLD_KIND_SOU};
		for(int i =0 ; i < sansyokuflg.length ; i++){
			for(int j = 0; j < sansyokuflg[i].length ; j++){
				if(id == (checkId[i] + j+1) ){
					sansyokuflg[i][j] = true;
				}
			}
		}		
	}
	
	boolean checkSansyokuDoujun() {
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
			id = checkHai[i][0].getOldId();
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

	boolean checkSansyokuDoukou() {
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
			id = checkHai[i][0].getOldId();
			checkSansyoku(id,sansyokuflg);
		}
		
		//�����v�̖��Ȃ��`�F�b�N
		for(int i = 0 ; i < tehai.getMinkansLength() ; i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			checkSansyoku(id,sansyokuflg);
		}

		//�����v�̈ÞȂ��`�F�b�N
		for(int i = 0 ; i < tehai.getAnkansLength() ; i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
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
	
	boolean checkToitoi() {
		//��v�ɏ��q������
		if((combi.shunCount != 0) || (tehai.getMinshunsLength() != 0) ){
			return false;
		}else{
			return true;
		}
	}
	
	boolean checkSanankou() {
		//��v�̈Í����R��
		//TODO �オ�����ۂ��o�オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 3){
			return true;
		}else{
			return false;
		}
	}
	
	boolean checkSankantu() {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 3){
			return true;
		}else{
			return false;
		}
	}

	boolean checkRyanpeikou() {
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
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
	
	boolean checkHonitu() {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {OLD_KIND_WAN,OLD_KIND_PIN,OLD_KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean honituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				id = jyunTehai[j].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if (((id & checkId[i]) == 0) && ((id & OLD_KIND_TSUU) == 0) ){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinshunsLength(); j++){
				checkHai = tehai.getMinshuns();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if (((id & checkId[i]) == 0) && ((id & OLD_KIND_TSUU) == 0) ){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkousLength(); j++){
				checkHai = tehai.getMinkous();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if (((id & checkId[i]) == 0) && ((id & OLD_KIND_TSUU) == 0) ){
					honituflg = false;
				}
			}
			
			//���Ȃ̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkansLength(); j++){
				checkHai = tehai.getMinkans();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if (((id & checkId[i]) == 0) && ((id & OLD_KIND_TSUU) == 0) ){
					honituflg = false;
				}
			}
			
			//�ÞȂ̔v���`�F�b�N
			for(int j = 0; j < tehai.getAnkansLength(); j++){
				checkHai = tehai.getAnkans();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if (((id & checkId[i]) == 0) && ((id & OLD_KIND_TSUU) == 0) ){
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
	
	boolean checkJunCyan() {
		
		int id;
		Hai checkHai[][]; 
		
		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.kouCount ; i++){
			id = combi.kouIds[i];
			//���v������Εs����
			if( (id & OLD_KIND_TSUU) != 0){
				return false;
			}

			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.shunCount ; i++){
			id = combi.shunIds[i];
			//���v������Εs����
			if( (id & OLD_KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 7))
					return false;
			}			
		}
		
		//����v�̓����`�F�b�N
		id = combi.atamaId;
		//���v������Εs����
		if( (id & OLD_KIND_TSUU) != 0){
			return false;
		}
		if ((id & OLD_KIND_SHUU) != 0){
			id &= OLD_KIND_MASK;
			if ((id > 1) && (id < 9))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinshunsLength(); i++){
			checkHai = tehai.getMinshuns();
			id = checkHai[i][0].getOldId();
			id &= OLD_KIND_MASK;
			//123 �Ɓ@789 �ȊO�̏��q������Εs����
			if ((id > 1) && (id < 7))
				return false;
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getOldId();
			//���v������Εs����
			if( (id & OLD_KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkansLength(); i++){
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getOldId();
			//���v������Εs����
			if( (id & OLD_KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int i = 0; i < tehai.getAnkansLength(); i++){
			checkHai = tehai.getAnkans();
			id = checkHai[i][0].getOldId();
			//���v������Εs����
			if( (id & OLD_KIND_TSUU) != 0){
				return false;
			}
			//���v�̏ꍇ�͐������`�F�b�N
			if ((id & OLD_KIND_SHUU) != 0){
				id &= OLD_KIND_MASK;
				if ((id > 1) && (id < 9))
					return false;
			}
		}
		
		return true;
	}
	
	boolean checkSyousangen() {
		//�O���v�����������Ă�����𒲂ׂ�
		int countSangen = 0;
		//�������q
		if(checkHaku() == true){
			countSangen++;
		}
		//�������q
		if(checkHatu() == true){
			countSangen++;
		}
		//�������q
		if(checkCyun() == true){
			countSangen++;
		}
		//�����O���v ���A�O���v����2����
		if(((combi.atamaId & OLD_KIND_SANGEN) != 0) && (countSangen == 2)){
			return true;
		}
		
		return false;
	}
	
	boolean checkHonroutou() {
		//�g�C�g�C���������Ă���
		if(checkToitoi() == false){
			return false;
		}
		
		//�`�����^���������Ă���
		if(checkCyanta() == true){
			return true;
		}else{
			return false;
		}
	}
/*	
	boolean checkRenhou() {
		return true;
	}
*/
	boolean checkTinitu() {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {OLD_KIND_WAN,OLD_KIND_PIN,OLD_KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean honituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				id = jyunTehai[j].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (((id & checkId[i]) == 0)){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinshunsLength(); j++){
				checkHai = tehai.getMinshuns();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (((id & checkId[i]) == 0)){
					honituflg = false;
				}
			}
			
			//�����̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkousLength(); j++){
				checkHai = tehai.getMinkous();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (((id & checkId[i]) == 0)){
					honituflg = false;
				}
			}
			
			//���Ȃ̔v���`�F�b�N
			for(int j = 0; j < tehai.getMinkansLength(); j++){
				checkHai = tehai.getMinkans();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (((id & checkId[i]) == 0)){
					honituflg = false;
				}
			}
			
			//�ÞȂ̔v���`�F�b�N
			for(int j = 0; j < tehai.getAnkansLength(); j++){
				checkHai = tehai.getAnkans();
				id = checkHai[j][0].getOldId();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (((id & checkId[i]) == 0)){
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

	boolean checkSuuankou() {
		//��v�̈Í���4��
		//TODO �オ�����ۂ��o�オ��A�c���オ�肩�ǂ����̔�����K�v�H
		if(combi.kouCount == 4){
			return true;
		}else{
			return false;
		}
	}

	boolean checkSuukantu() {
		int kansnumber = 0;
		kansnumber += tehai.getAnkansLength();
		kansnumber += tehai.getMinkansLength();
		if(kansnumber == 4){
			return true;
		}else{
			return false;
		}
	}

	boolean checkDaisangen() {
		//�O���v�����������Ă�����𒲂ׂ�
		int countSangen = 0;
		//�������q
		if(checkHaku() == true){
			countSangen++;
		}
		//�������q
		if(checkHatu() == true){
			countSangen++;
		}
		//�������q
		if(checkCyun() == true){
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
	boolean checkTenhou() {
		return false;
	}

	boolean checkTihou() {
		return false;
	}
	
	boolean checkSyousuushi() {
		//���v�����������Ă�����𒲂ׂ�
		int countFon = 0;
		//�������q
		if(checkTon() == true){
			countFon++;
		}
		//�삪���q
		if(checkNan() == true){
			countFon++;
		}
		//�������q
		if(checkSya() == true){
			countFon++;
		}
		//�k�����q
		if(checkPei() == true){
			countFon++;
		}

		//�������v ���A���v����3����
		if(((combi.atamaId & OLD_KIND_FON) != 0) && (countFon == 3)){
			return true;
		}else{
			return false;
		}
	}

	boolean checkDaisuushi() {
		//���v�����������Ă�����𒲂ׂ�
		int countFon = 0;
		//�������q
		if(checkTon() == true){
			countFon++;
		}
		//�삪���q
		if(checkNan() == true){
			countFon++;
		}
		//�������q
		if(checkSya() == true){
			countFon++;
		}
		//�k�����q
		if(checkPei() == true){
			countFon++;
		}
			//���v����4����
		if(countFon == 4){
			return true;
		}else{
			return false;
		}
	}

	boolean checkTuuisou() {
		int id;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 
		
		//���q�����邩�ǂ����m�F
		if(checkToitoi() == false){
			return false;
		}
		
		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int j = 0; j < jyunTehaiLength; j++) {
			id = jyunTehai[j].getOldId();
			//�v�����v
			if ((id & OLD_KIND_SHUU) != 0){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkousLength(); j++){
			checkHai = tehai.getMinkous();
			id = checkHai[j][0].getOldId();
			//�v�����v
			if ((id & OLD_KIND_SHUU) != 0){
				return false;
			}
		}
		
		//���Ȃ̔v���`�F�b�N
		for(int j = 0; j < tehai.getMinkansLength(); j++){
			checkHai = tehai.getMinkans();
			id = checkHai[j][0].getOldId();
			//�v�����v
			if ((id & OLD_KIND_SHUU) != 0){
				return false;
			}
		}
		
		//�ÞȂ̔v���`�F�b�N
		for(int j = 0; j < tehai.getAnkansLength(); j++){
			checkHai = tehai.getAnkans();
			id = checkHai[j][0].getOldId();
			//�v�����v
			if ((id & OLD_KIND_SHUU) != 0){
				return false;
			}
		}
		
		return true;
	}

	boolean checkChinroutou() {
		//���q�����邩�ǂ����m�F
		if(checkToitoi() == false){
			return false;
		}
		
		//���q�Ȃ��ŃW�����`�������������Ă��邩�i1��9�݂̂ō쐬�j
		if(checkJunCyan() == false){
			return false;
		}
		
		return true;
		
	}

	boolean checkRyuuisou() {
		int checkId[] = {OLD_KIND_SOU+2,OLD_KIND_SOU+3,OLD_KIND_SOU+4,OLD_KIND_SOU+6,OLD_KIND_SOU+8,OLD_KIND_HATU};
		int id;
		boolean ryuuisouflg = false;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[][]; 

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			id = jyunTehai[i].getOldId();
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
			id = checkHai[i][0].getOldId();
			//���q��2,3,4�ȊO�̏��q���������ꍇ�s����
			if (id != (OLD_KIND_SOU + 2)){
				return false;
			}
		}
		
		//�����̔v���`�F�b�N
		for(int i = 0; i < tehai.getMinkousLength(); i++){
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getOldId();
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
			id = checkHai[i][0].getOldId();
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
			id = checkHai[i][0].getOldId();
			if ((id & OLD_KIND_SHUU) == 0)
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

	boolean checkCyuurennpoutou() {
		int id;
		
		//�v�̐��𒲂ׂ邽�߂̔z�� (0�Ԓn�͎g�p���Ȃ��j
		int countNumber[] = {0,0,0,0,0,0,0,0,0,0};
		Hai checkHai[] = new Hai[JYUNTEHAI_MAX];
		
		//��������ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}
		//��v������ɂȂ��Ă��Ȃ��ꍇ���������Ȃ�
		if(checkTinitu() == false){
			return false;
		}
		
		//��v���R�s�[����
		checkHai = tehai.getJyunTehai();
		
		//��v�ɂ���v�̔ԍ��𒲂ׂ�
		for(int i = 0 ; i < tehai.getJyunTehaiLength() ; i++){
			id = checkHai[i].getOldId();
			//�������`�F�b�N����
			id &= OLD_KIND_MASK;
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
	
	boolean checkKokushi() {
		return false;
	}
}
