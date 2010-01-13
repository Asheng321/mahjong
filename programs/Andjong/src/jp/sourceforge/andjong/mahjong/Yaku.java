package jp.sourceforge.andjong.mahjong;
import static jp.sourceforge.andjong.mahjong.AgariSetting.YakuflgName.*;
import static jp.sourceforge.andjong.mahjong.Hai.*;
import static jp.sourceforge.andjong.mahjong.Tehai.JYUN_TEHAI_LENGTH_MAX;
import jp.sourceforge.andjong.mahjong.CountFormat.Combi;

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
	AgariSetting setting;
	YakuHantei yakuhantei[];
	boolean nakiflg = false;

	/**
	 * Yaku�N���X�̃R���X�g���N�^�B
	 * ������ۑ����AYakuHantei�N���X�̔z����쐬����B
	 * @param tehai ��v�@addHai �オ�����v  combi ��v �̑g�ݍ��킹 info ���
	 */
	Yaku(Tehai tehai, Hai addHai, Combi combi,AgariSetting setting){
		this.tehai = tehai;
		this.addHai = addHai;
		this.combi  = combi;
		this.setting = setting;
		//��������ꍇ
		nakiflg = tehai.isNaki();

		YakuHantei buffer[] = {new CheckTanyao(),
							   new CheckPinfu(),
							   new CheckIpeikou(),
							   new CheckReach(),
							   new CheckIppatu(),
							   new CheckTumo(),
							   new CheckTon(),
							   new CheckNan(),
							   new CheckSya(),
							   new CheckPei(),
							   new CheckHaku(),
							   new CheckHatu(),
							   new CheckCyun(),
							   new CheckHaitei(),
							   new CheckHoutei(),
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
			if(checkDoubleReach() == true){
				hantei = false;
			}
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

	private class CheckTumo extends YakuHantei{
		CheckTumo(){
			hantei = checkTumo();
			yakuName = "��O�����̘a";
			hanSuu = 1;
		}
	}

	private class CheckTon extends YakuHantei{
		CheckTon(){
			hantei = checkTon();
			if(setting.getJikaze() == JIKAZE_TON){
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

	private class CheckHoutei extends YakuHantei{
		CheckHoutei(){
			hantei = checkHoutei();
			yakuName = "�͒ꝝ��";
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
		Hai checkHai[];

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			//�P�X���v�Ȃ�Εs����
			if (jyunTehai[i].isYaochuu() == true){
				return false;
			}
		}

		// �ǉ��v���`�F�b�N

		//�P�X���v�Ȃ�Εs����
		if (addHai.isYaochuu() == true){
			return false;
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
				//123 �Ɓ@789 �̏��q������Εs����
				if ((id == 1) || (id == 7)){
					return false;
				}
				break;
			case Fuuro.TYPE_MINKOU:
				//�����̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				if (checkHai[0].isYaochuu() == true){
					return false;
				}
				break;
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
				//���Ȃ̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				if (checkHai[0].isYaochuu() == true){
					return false;
				}
				break;
			case Fuuro.TYPE_ANKAN:
				//�ÞȂ̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				if (checkHai[0].isYaochuu() == true){
					return false;
				}
				break;
			default:
				break;
			}
		}

		return true;
	}

	boolean checkPinfu() {
		Hai atamaHai;
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}

		//�ʎq�����q�����ł͂Ȃ�
		if(combi.m_shunNum != 4){
			return false;
		}

		//�����O���v
		atamaHai = new Hai(Hai.noKindToId(combi.m_atamaNoKind));
		if( atamaHai.getKind() == KIND_SANGEN ){
			return false;
		}

		//�����ꕗ
		if( atamaHai.getKind() == KIND_FON
				&& atamaHai.getNo() == setting.getBakaze()){
			return false;
		}

		//��������
		if( atamaHai.getKind() == KIND_FON
				&& atamaHai.getNo() == setting.getJikaze()){
			return false;
		}

		//���v�̓��҂��̏ꍇ�͕s����
		if(addHai.isTsuu() == true){
			return false;
		}

		//�҂������ʑ҂�������
		boolean ryanmenflg = false;
		//int addHaiid = addHai.getId();
		int addHaiid = addHai.getNoKind();
		//�オ��v�̐����`�F�b�N���ďꍇ����
		switch(addHai.getNo()){
			//�オ��v��1,2,3�̏ꍇ��123,234,345�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 1:
			case 2:
			case 3:
				for(int i = 0 ; i < combi.m_shunNum ; i++){
					if(addHaiid == combi.m_shunNoKinds[i]){
						ryanmenflg = true;
					}
				}
				break;
			//�オ��v��4,5,6�̏ꍇ��456��234,567��345,678��456�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 4:
			case 5:
			case 6:
				for(int i = 0 ; i < combi.m_shunNum ; i++){
					if((addHaiid == combi.m_shunNoKinds[i])
					 ||(addHaiid-2 == combi.m_shunNoKinds[i])){
						ryanmenflg = true;
					}
				}
				break;
			//�オ��v��7,8,9�̏ꍇ��567,678,789�̏��q���ł��Ă��邩�ǂ����`�F�b�N
			case 7:
			case 8:
			case 9:
				for(int i = 0 ; i < combi.m_shunNum ; i++){
					if(addHaiid-2 == (combi.m_shunNoKinds[i])){
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
		for (int i = 0; i < combi.m_shunNum -1; i++) {
			if(combi.m_shunNoKinds[i] == combi.m_shunNoKinds[i+1]){
				return true;
			}
		}
		return false;
	}

	boolean checkReach() {
		return setting.getYakuflg(REACH.ordinal());
	}

	boolean checkIppatu() {
		return setting.getYakuflg(IPPATU.ordinal());
	}

	boolean checkTumo() {
		//���������Ă���ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}
		return setting.getYakuflg(TUMO.ordinal());
	}


	//��v���ł��Ă��邩�ǂ����̔���Ɏg���⏕���\�b�h
	private boolean checkYakuHai(Tehai tehai, Combi combi , int yakuHaiId) {
		int id;
		Hai checkHai[];

		//����v���`�F�b�N
		for(int i = 0; i < combi.m_kouNum ; i++){
			//ID�Ɩ�v��ID���`�F�b�N
			if( Hai.noKindToId(combi.m_kouNoKinds[i]) == yakuHaiId ){
				return true;
			}
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINKOU:
				//�����̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
				//ID�Ɩ�v��ID���`�F�b�N
				if( id == yakuHaiId ){
					return true;
				}
				break;
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
				//���Ȃ̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getId();
				//ID�Ɩ�v��ID���`�F�b�N
				if( id == yakuHaiId ){
					return true;
				}
				break;
			case Fuuro.TYPE_ANKAN:
				//�ÞȂ̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getId();
				//ID�Ɩ�v��ID���`�F�b�N
				if( id == yakuHaiId ){
					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}

	boolean checkTon() {
		return checkYakuHai(tehai,combi,ID_TON);
	}

	boolean checkNan() {
		if(setting.getJikaze() == JIKAZE_NAN){
			return checkYakuHai(tehai,combi,ID_NAN);
		}else{
			return false;
		}
	}

	boolean checkSya() {
		if(setting.getJikaze() == JIKAZE_SYA){
			return checkYakuHai(tehai,combi,ID_SHA);
		}else{
			return false;
		}
	}

	boolean checkPei() {
		if(setting.getJikaze() == JIKAZE_PEI){
			return checkYakuHai(tehai,combi,ID_PE);
		}else{
			return false;
		}
	}

	boolean checkHaku() {
		return checkYakuHai(tehai,combi,ID_HAKU);
	}

	boolean checkHatu() {
		return checkYakuHai(tehai,combi,ID_HATSU);
	}

	boolean checkCyun() {
		return checkYakuHai(tehai,combi,ID_CHUN);
	}

	boolean checkHaitei() {
		return setting.getYakuflg(HAITEI.ordinal());
	}

	boolean checkHoutei(){
		return setting.getYakuflg(HOUTEI.ordinal());
	}

	boolean checkRinsyan() {
		return setting.getYakuflg(RINSYAN.ordinal());
	}

	boolean checkCyankan() {
		return setting.getYakuflg(CHANKAN.ordinal());
	}

	boolean checkDoubleReach() {
		return setting.getYakuflg(DOUBLEREACH.ordinal());
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
		Hai checkHais[];
		Hai checkHai;

		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.m_kouNum ; i++){
			checkHai = new Hai(Hai.noKindToId(combi.m_kouNoKinds[i]));
			//���v�̏ꍇ�͐������`�F�b�N
			if (checkHai.isYaochuu() == false){
				return false;
			}
		}

		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.m_shunNum ; i++){
			checkHai = new Hai(Hai.noKindToId(combi.m_shunNoKinds[i]));
			//���v�̏ꍇ�͐������`�F�b�N
			if (checkHai.isTsuu() == false){
				if ((checkHai.getNo() > 1) && (checkHai.getNo() < 7))
					return false;
			}
		}

		//����v�̓����`�F�b�N
		checkHai = new Hai(combi.m_atamaNoKind);
		if (checkHai.isYaochuu() == false){
			return false;
		}
		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//123 �Ɓ@789 �ȊO�̏��q������Εs����
				if ((checkHais[0].getNo() > 1) && (checkHais[0].getNo() < 7))
					return false;
				break;
			case Fuuro.TYPE_MINKOU:
				//�����̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v�̏ꍇ�͐������`�F�b�N
				if (checkHais[0].isYaochuu() == false){
					return false;
				}
				break;
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
				//���Ȃ̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v�̏ꍇ�͐������`�F�b�N
				if (checkHais[0].isYaochuu() == false){
					return false;
				}
				break;
			case Fuuro.TYPE_ANKAN:
				//�ÞȂ̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v�̏ꍇ�͐������`�F�b�N
				if (checkHais[0].isYaochuu() == false){
					return false;
				}
				break;
			default:
				break;
			}
		}

		return true;
	}

	boolean checkIkkituukan() {
		int id;
		Hai checkHai[];
		boolean ikkituukanflg[]= {false,false,false,false,false,false,false,false,false};
		//�ݎq�A���q�A���q��1,4,7���`�F�b�N
		int checkId[] = {ID_WAN_1,ID_WAN_4,ID_WAN_7,ID_PIN_1,ID_PIN_4,ID_PIN_7,ID_SOU_1,ID_SOU_4,ID_SOU_7};

		//��v�̏��q���`�F�b�N
		for(int i = 0 ; i < combi.m_shunNum ; i++){
			id = combi.m_shunNoKinds[i];
			for(int j =0 ; j < checkId.length ; j++){
				if(id == checkId[j]){
					ikkituukanflg[j] = true;
				}
			}
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
				for(int j =0 ; j < checkId.length ; j++){
					if(id == checkId[j]){
						ikkituukanflg[j] = true;
					}
				}
				break;
			default:
				break;
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
		int checkId[] = {ID_WAN_1,ID_PIN_1,ID_SOU_1};
		for(int i =0 ; i < sansyokuflg.length ; i++){
			for(int j = 0; j < sansyokuflg[i].length ; j++){
				if(id == (checkId[i] + j) ){
					sansyokuflg[i][j] = true;
				}
			}
		}
	}

	boolean checkSansyokuDoujun() {
		int id;
		Hai checkHai[];
		boolean sansyokuflg[][]= new boolean[3][9];

		//�t���O�̏�����
		for(int i = 0 ; i<sansyokuflg.length; i++){
			for (int k = 0; k <sansyokuflg[i].length ; k++){
				sansyokuflg[i][k] = false;
			}
		}

		//��v�̏��q���`�F�b�N
		for(int i = 0 ; i < combi.m_shunNum ; i++){
			id = combi.m_shunNoKinds[i];
			checkSansyoku(id,sansyokuflg);
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
				checkSansyoku(id,sansyokuflg);
				break;
			default:
				break;
			}
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
		Hai checkHai[];
		boolean sansyokuflg[][]= new boolean[3][9];


		//�t���O�̏�����
		for(int i = 0 ; i<sansyokuflg.length; i++){
			for (int k = 0; k <sansyokuflg[i].length ; k++){
				sansyokuflg[i][k] = false;
			}
		}

		//��v�̍��q���`�F�b�N
		for(int i = 0 ; i < combi.m_kouNum ; i++){
			id = combi.m_kouNoKinds[i];
			checkSansyoku(id,sansyokuflg);
		}
		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINKOU:
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
			case Fuuro.TYPE_ANKAN:
				//�����v�̖������`�F�b�N
				//�����v�̖��Ȃ��`�F�b�N
				//�����v�̈ÞȂ��`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getId();
				checkSansyoku(id,sansyokuflg);
				break;
			default:
				break;
			}
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
		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		int minShunNum = 0;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				minShunNum++;
				break;
			default:
				break;
			}
		}
		//��v�ɏ��q������
		if((combi.m_shunNum != 0) || (minShunNum != 0) ){
			return false;
		}else{
			return true;
		}
	}

	boolean checkSanankou() {

		//�΁X�`�Ŗ����Ȃ���ΐ������Ă���y�c���a�����P�R�̏ꍇ�A�l�Í����D�悳���j
		if((checkToitoi() == true)
		 &&(nakiflg == false)){
			return true;
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		int anKanNum = 0;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_ANKAN:
				anKanNum++;
				break;
			default:
				break;
			}
		}

		//�Í��ƈÞȂ̍��v���R�ł͂Ȃ��ꍇ�͕s����
		if((combi.m_kouNum + anKanNum) != 3){
			return false;
		}

		//�c���オ��̏ꍇ�͐���
		if(setting.getYakuflg(TUMO.ordinal()) == true){
			return true;
		}
		//�����オ��̏ꍇ�A�a�������v��
		else{
			int id = addHai.getId();
			//�����オ��œ��҂��̏ꍇ�͐���
			if(id == combi.m_atamaNoKind){
				return true;
			}else{
				//�a�������v�ƍ��q�ɂȂ��Ă���v���������m�F
				boolean checkflg = false;
				for(int i = 0 ; i < combi.m_kouNum ; i++){
					if(id == combi.m_kouNoKinds[i]){
						checkflg = true;
					}
				}

				//���q�̔v�Řa�������ꍇ
				if(checkflg == true){
					//���v�Ȃ�Εs����
					if(addHai.isTsuu() == true){
						return false;
					}else{
						//���q�̑҂��ɂ��Ȃ��Ă��Ȃ����m�F����
						//�@��:11123 ��1�Řa����  , 45556 ��5�Řa����
						boolean checkshun = false;
						for(int i = 0 ; i < combi.m_shunNum ; i++){
							switch(addHai.getNo()){
								case 1:
									if(id == combi.m_shunNoKinds[i]){
										checkshun = true;
									}
									break;
								case 2:
									if((id == combi.m_shunNoKinds[i])
									 ||(id-1 == combi.m_shunNoKinds[i])){
										checkshun = true;
									}
									break;
								case 3:
								case 4:
								case 5:
								case 6:
								case 7:
									if((id == combi.m_shunNoKinds[i])
										 ||(id-1 == combi.m_shunNoKinds[i])
										 ||(id-2 == combi.m_shunNoKinds[i])){
											checkshun = true;
									}
									break;
								case 8:
									if((id-1 == combi.m_shunNoKinds[i])
										 ||(id-2 == combi.m_shunNoKinds[i])){
											checkshun = true;
									}
									break;
								case 9:
									if(id-2 == combi.m_shunNoKinds[i]){
											checkshun = true;
									}
									break;
							}
						}
						//���q�̔v�ł��������ꍇ�͐���
						if(checkshun == true){
							return true;
						}
						//�֌W���鏇�q���Ȃ������ꍇ�͕s����
						else{
							return false;
						}
					}
				}
				//���q�Ɗ֌W�Ȃ��v�Řa�������ꍇ�͐���
				else{
					return true;
				}
			}
		}
	}

	boolean checkSankantu() {
		int kansnumber = 0;

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
			case Fuuro.TYPE_ANKAN:
				kansnumber++;
				break;
			default:
				break;
			}
		}
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
		if(combi.m_shunNum < 4){
			return false;
		}

		//���q�̑g�ݍ��킹���m�F����
		if(combi.m_shunNoKinds[0] == combi.m_shunNoKinds[1]
		&& combi.m_shunNoKinds[2] == combi.m_shunNoKinds[3]){
			return true;
		}else{
			return false;
		}
	}

	boolean checkHonitu() {
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[];

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {KIND_WAN,KIND_PIN,KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean honituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
				if ((jyunTehai[j].getKind() != checkId[i]) && (jyunTehai[j].isTsuu() == false)){
					honituflg = false;
				}
			}

			Fuuro[] fuuros;
			fuuros = tehai.getFuuros();
			int fuuroNum;
			fuuroNum = tehai.getFuuroNum();
			int type;
			for (int j = 0; j < fuuroNum; j++) {
				type = fuuros[j].getType();
				switch (type) {
				case Fuuro.TYPE_MINSHUN:
				case Fuuro.TYPE_MINKOU:
				case Fuuro.TYPE_DAIMINKAN:
				case Fuuro.TYPE_KAKAN:
				case Fuuro.TYPE_ANKAN:
					//�����̔v���`�F�b�N
					//�����̔v���`�F�b�N
					//���Ȃ̔v���`�F�b�N
					//�ÞȂ̔v���`�F�b�N
					checkHai = fuuros[i].getHais();
					//�v��(�ݎq�A���q�A���q)�ȊO�������͎��v�ȊO
					if ((checkHai[0].getKind() != checkId[i]) && (checkHai[0].isTsuu() == false)){
						honituflg = false;
					}
					break;
				default:
					break;
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
		Hai checkHais[];
		Hai checkHai;

		//����v�̍��q���`�F�b�N
		for(int i = 0; i < combi.m_kouNum ; i++){
			checkHai = new Hai(Hai.noKindToId(combi.m_kouNoKinds[i]));
			//���v������Εs����
			if( checkHai.isTsuu() == true){
				return false;
			}

			//�����v�Ȃ�Εs����
			if(checkHai.isYaochuu() == false ){
				return false;
			}
		}

		//����v�̏��q���`�F�b�N
		for(int i = 0; i < combi.m_shunNum ; i++){
			checkHai = new Hai(Hai.noKindToId(combi.m_shunNoKinds[i]));
			//���v������Εs����
			if( checkHai.isTsuu() == true){
				return false;
			}

			//���v�̏ꍇ�͐������`�F�b�N
			if ((checkHai.getNo() > NO_1) && (checkHai.getNo() < NO_7)){
				return false;
			}
		}

		//����v�̓����`�F�b�N
		checkHai = new Hai(combi.m_atamaNoKind);
		//���v������Εs����
		if( checkHai.isTsuu() == true){
			return false;
		}
		//�����v�Ȃ�Εs����
		if(checkHai.isYaochuu() == false ){
			return false;
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//123 �Ɓ@789 �ȊO�̏��q������Εs����
				if ((checkHais[0].getNo() > NO_1) && (checkHais[0].getNo()< NO_7)){
					return false;
				}
				break;
			case Fuuro.TYPE_MINKOU:
				//�����̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v������Εs����
				if( checkHais[0].isTsuu() == true){
					return false;
				}
				//�����v�Ȃ�Εs����
				if(checkHais[0].isYaochuu() == false ){
					return false;
				}
				break;
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
				//���Ȃ̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v������Εs����
				if( checkHais[0].isTsuu() == true){
					return false;
				}
				//�����v�Ȃ�Εs����
				if(checkHais[0].isYaochuu() == false ){
					return false;
				}
				break;
			case Fuuro.TYPE_ANKAN:
				//�ÞȂ̔v���`�F�b�N
				checkHais = fuuros[i].getHais();
				//���v������Εs����
				if( checkHais[0].isTsuu() == true){
					return false;
				}
				//�����v�Ȃ�Εs����
				if(checkHais[0].isYaochuu() == false ){
					return false;
				}
				break;
			default:
				break;
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
		//Hai atamaHai = new Hai(combi.m_atamaNoKind);
		//if((atamaHai.getKind() == KIND_SANGEN) && (countSangen == 2)){
		if(((combi.m_atamaNoKind & KIND_SANGEN) == KIND_SANGEN) && (countSangen == 2)){
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

	boolean checkRenhou() {
		if(setting.getYakuflg(RENHOU.ordinal())){
			return true;
		}else{
			return false;
		}
	}

	boolean checkTinitu() {
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[];

		//�ݎq�A���q�A���q���`�F�b�N
		int checkId[] = {KIND_WAN,KIND_PIN,KIND_SOU};

		for (int i = 0 ; i < checkId.length ; i++){
			boolean Tinituflg = true;
			//����v���`�F�b�N
			int jyunTehaiLength = tehai.getJyunTehaiLength();
			for (int j = 0; j < jyunTehaiLength; j++) {
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (jyunTehai[j].getKind() != checkId[i]){
					Tinituflg = false;
				}
			}
			Fuuro[] fuuros;
			fuuros = tehai.getFuuros();
			int fuuroNum;
			fuuroNum = tehai.getFuuroNum();
			for (int j = 0; j < fuuroNum; j++) {
				checkHai = fuuros[j].getHais();
				//�v��(�ݎq�A���q�A���q)�ȊO
				if (checkHai[0].getKind() != checkId[i]){
					Tinituflg = false;
					break;
				}
			}

			//���ꂪ�������Ă���
			if(Tinituflg == true){
				return true;
			}

		}
		//�������Ă��Ȃ���Εs����
		return false;

	}

	boolean checkSuuankou() {
		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		int anKanNum = 0;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_ANKAN:
				anKanNum++;
				break;
			default:
				break;
			}
		}

		//��v�̈Í���4��
		if((combi.m_kouNum + anKanNum) != 4){
			return false;
		}else{
			//�c���a����̏ꍇ�͐���
			if(setting.getYakuflg(TUMO.ordinal())){
				return true;
			}
			//�����a����̏ꍇ
			else{
				//���҂��Ȃ�ΐ��� (�l�Í��P�R�҂�)
				if(addHai.getId() == combi.m_atamaNoKind){
					return true;
				}else{
					return false;
				}
			}
		}
	}

	boolean checkSuukantu() {
		int kansnumber = 0;
		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
			case Fuuro.TYPE_ANKAN:
				kansnumber++;
				break;
			default:
				break;
			}
		}
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

	boolean checkTenhou() {
		return setting.getYakuflg(TENHOU.ordinal());
	}

	boolean checkTihou() {
		return setting.getYakuflg(TIHOU.ordinal());
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
		//Hai atamaHai = new Hai(combi.m_atamaNoKind);
		//if((atamaHai.getKind() == KIND_FON) && (countFon == 3)){
		if(((combi.m_atamaNoKind & KIND_FON) == KIND_FON) && (countFon == 3)){
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
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[];

		//���q�����邩�ǂ����m�F
		if(checkToitoi() == false){
			return false;
		}

		//����v���`�F�b�N
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int j = 0; j < jyunTehaiLength; j++) {
			//�v�����v�ł͂Ȃ�
			if (jyunTehai[j].isTsuu() == false){
				return false;
			}
		}

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		for (int i = 0; i < fuuroNum; i++) {
			checkHai = fuuros[i].getHais();
			//�v�����v�ł͂Ȃ�
			if (checkHai[0].isTsuu() == false){
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
		int checkId[] = {ID_SOU_2,ID_SOU_3,ID_SOU_4,ID_SOU_6,ID_SOU_8,ID_HATSU};
		int id;
		boolean ryuuisouflg = false;
		Hai[] jyunTehai = tehai.getJyunTehai();
		Hai checkHai[];

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

		Fuuro[] fuuros;
		fuuros = tehai.getFuuros();
		int fuuroNum;
		fuuroNum = tehai.getFuuroNum();
		int type;
		for (int i = 0; i < fuuroNum; i++) {
			type = fuuros[i].getType();
			switch (type) {
			case Fuuro.TYPE_MINSHUN:
				//�����̔v���`�F�b�N
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
				//���q��2,3,4�ȊO�̏��q���������ꍇ�s����
				if (id != ID_SOU_2){
					return false;
				}
				break;
			case Fuuro.TYPE_MINKOU:
			case Fuuro.TYPE_DAIMINKAN:
			case Fuuro.TYPE_KAKAN:
			case Fuuro.TYPE_ANKAN:
				checkHai = fuuros[i].getHais();
				id = checkHai[0].getNo();
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
				break;
			default:
				break;
			}
		}

		//�����ɊY������
		return true;
	}

	boolean checkCyuurennpoutou() {
		//�v�̐��𒲂ׂ邽�߂̔z�� (0�Ԓn�͎g�p���Ȃ��j
		int countNumber[] = {0,0,0,0,0,0,0,0,0,0};
		Hai checkHai[] = new Hai[JYUN_TEHAI_LENGTH_MAX];

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
			//�����̔ԍ����C���N�������g����
			countNumber[checkHai[i].getNo()]++;
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
		//�v�̐��𒲂ׂ邽�߂̔z�� (0�Ԓn�͎g�p���Ȃ��j
		int checkId[] = {ID_WAN_1,ID_WAN_9,ID_PIN_1,ID_PIN_9,ID_SOU_1,ID_SOU_9,
								ID_TON,ID_NAN,ID_SHA,ID_PE,ID_HAKU,ID_HATSU,ID_CHUN};
		int countHai[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		Hai checkHai[] = new Hai[JYUN_TEHAI_LENGTH_MAX];

		//��������ꍇ�͐������Ȃ�
		if(nakiflg == true){
			return false;
		}

		//��v���R�s�[����
		checkHai = tehai.getJyunTehai();

		//��v��ID����������
		for(int i = 0 ; i < tehai.getJyunTehaiLength() ; i++){
			for(int j = 0 ; j < checkId.length ; j++){
				if(checkHai[i].getId() == checkId[j]){
					countHai[j]++;
				}
			}
		}

		//���m���o���������Ă��邩���ׂ�(��v�����ׂ�1.9���v ���ׂĂ̂P,�X���v�������Ă���j
		for(int i = 0 ; i < countHai.length ; i++){
			//0���̔v������Εs����
			if(countHai[i] == 0){
				return false;
			}
		}
		//�����𖞂����Ă���ΐ���
		return true;
	}
}
