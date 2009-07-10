package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.*;

import java.util.Random;

import jp.sourceforge.andjong.EventIF.EID;
import jp.sourceforge.andjong.Tehai.Combi;
import jp.sourceforge.andjong.Yaku;

/**
 * �Q�[���̃n���h�����O���s���N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Game {
	/** �R */
	private Yama yama;

	public Yama getYama() {
		return yama;
	}
	
	/** �v���C���[�̐l�� */
	private int playerNum = 4;

	/** �v���C���[�̔z�� */
	Player[] players;

	private int kyoku;

	private boolean renchan;

	private int oya;

	private int honba;

	private int reachbou;

	private int wareme;

	private int activePlayerIdx;

	private int action;

	private Info info;
	private InfoUI infoUi;

	private UI ui;

	class SAI {
		private int no;

		public int getNo() {
			return no;
		}

		public int saifuri() {
			return no = new Random().nextInt(6) + 1;
		}
	}

	private SAI[] sais = new SAI[] { new SAI(), new SAI() };

	SAI[] getSais() {
		return sais;
	}


	private Player activePlayer;

	public Player getActivePlayer() {
		return activePlayer;
	}

	private final static int ACTION_TSUMOAGARI = 0;
	private final static int ACTION_RON = 1;

	int eventTargetPlayerIdx;


	Hai tsumoHai;

	Hai suteHai;

	private final static int HAIPAI_END = 52;

	/**
	 * ���C���������J�n���܂��B
	 * 
	 * @param args
	 *            �R�}���h���C������
	 */
	public static void main(String[] args) {
		// Game�C���X�^���X���쐬���܂��B
		Game game = new Game();

		// �Q�[�����J�n���܂��B
		game.play();
	}

	/**
	 * �Q�[�����J�n���܂��B
	 */
	public void play() {
		// Game�C���X�^���X�����������܂��B
		init();

		// �ꏊ�����߂܂��B
		// TODO �������ł��B

		// �C�x���g�i�ꏊ���߁j
		ui.event(EID.BASHOGIME, 0, 0);

		// �e�����߂܂��B
		sais[0].saifuri();
		sais[1].saifuri();
		oya = (sais[0].getNo() + sais[1].getNo() - 1) % 4;

		// �C�x���g�i�e���߁j
		ui.event(EID.OYAGIME, 0, 0);

		// �ǂ��J�n���܂��B
		// TODO �b��I�ɓ�����Ŏ������܂��B
		while (kyoku < 4) {
			startKyoku();
			break;
			// if (!renchan) {
			// kyoku++;
			// honba = 0;
			// } else {
			// System.out.println("�A���ł��B");
			// }
		}
	}

	private void init() {
		yama = new Yama();
		kyoku = 0;
		renchan = false;
		info = new Info(this);
		infoUi = new InfoUI(this);
		ui = new UI(infoUi);
		tsumoHai = new Hai();
		suteHai = new Hai();

		// �v���C���[�����������܂��B
		players = new Player[playerNum];
		for (int i = 0; i < playerNum; i++)
			players[i] = new Player(new AI(info));
		// players[0] = new Player((EventIF) new Man(info));
	}

	private void startKyoku() {
		reachbou = 0;
		renchan = false;

		// �Ƃ�ݒ�
		setCha();

		// ���v
		yama.xipai();

		// UI�C�x���g�i���v�j
		ui.event(EID.SENPAI, 0, 0);

		// �T�C�U��
		saifuri();

		// UI�C�x���g�i�T�C�U��j
		ui.event(EID.SAIFURI, 0, 0);

		// �v���C���[�̏�����
		for (int i = 0; i < players.length; i++)
			players[i].init();

		// �z�v
		haipai();

		// �ǂ̃��C�����[�v
		loopKyoku();

		{
			// debug
			System.out.println("honba:" + honba);
		}
	}

	private void setCha() {
		for (int i = 0, j = oya; i < players.length; i++, j++) {
			if (j >= players.length)
				j = 0;

			players[j].setJikaze(i);

			for (int k = 0, l = j; k < players.length; k++, l++) {
				if (l >= players.length)
					l = 0;
				players[j].ChaToPlayer[l] = k;
				players[j].PlayerToCha[k] = l;
				players[j].players[k] = players[l];
			}
		}
	}

	private void saifuri() {
		Random random = new Random();
		int sainome = random.nextInt(10) + 2;

		switch (sainome) {
		case 5:
		case 9:
			wareme = oya;
			break;
		case 2:
		case 6:
		case 10:
			wareme = (oya + 1) % players.length;
			break;
		case 3:
		case 7:
		case 11:
			wareme = (oya + 2) % players.length;
			break;
		case 4:
		case 8:
		case 12:
			wareme = (oya + 3) % players.length;
			break;
		}
	}
	
	private void haipai() {
		for (int i = 0, j = oya; i < HAIPAI_END; i++) {
			players[j].tehai.addJyunTehai(yama.tsumo());

			j++;
			if (j >= players.length) {
				j = 0;
			}
		}
	}

	private void loopKyoku() {
		activePlayerIdx = oya;
		eventTargetPlayerIdx = oya;
		action = 2;

		while (true) {
			// �c��
			tsumoHai = yama.tsumo();
			if (tsumoHai == null) {
				// ����
				oya++;
				if (oya >= players.length) {
					oya = 0;
				}
				return;
			}

			activePlayer = players[activePlayerIdx];
			sutehai();

			switch (action) {
			case ACTION_TSUMOAGARI:
				activePlayer.tenbou += reachbou * 1000;
				action = 0;
				if (oya != activePlayerIdx) {
					// System.out.println("oya++");
					oya++;
					if (oya >= players.length) {
						oya = 0;
					}
				} else {
					renchan = true;
					honba++;
				}
				return;
			case ACTION_RON:
				activePlayer.tenbou += reachbou * 1000;
				action = 0;
				if (oya != activePlayerIdx) {
					// System.out.println("oya++");
					oya++;
					if (oya >= players.length) {
						oya = 0;
					}
				} else {
					renchan = true;
					honba++;
				}
				return;
			default:
				activePlayerIdx++;
				if (activePlayerIdx >= players.length) {
					activePlayerIdx = 0;
				}
				break;
			}
		}
	}

	private void sutehai() {
		EID returnEid;
		int sutehaiIdx;

		// �C�x���g�i�c���j
		ui.event(EID.TSUMO, 0, 0);
		returnEid = activePlayer.ai.event(EID.TSUMO, 0, 0);

		switch (returnEid) {
		case SUTEHAI:
			// �C�x���g�i�̔v�j
			sutehaiIdx = info.getSutehaiIdx();
			if (sutehaiIdx == 13) {
				// �c���؂�
				suteHai.copy(tsumoHai);
				activePlayer.kawa.add(suteHai);
				eventTargetPlayerIdx = activePlayerIdx;
				callEvent(activePlayerIdx, eventTargetPlayerIdx, EID.SUTEHAI);
			} else {
				// ��o��
				activePlayer.tehai.copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.tehai.removeJyunTehai(sutehaiIdx);
				activePlayer.kawa.add(suteHai, Kawa.PROPERTY_TEDASHI);
				if (tsumoHai != null) {
					activePlayer.tehai.addJyunTehai(tsumoHai);
				}
				eventTargetPlayerIdx = activePlayerIdx;
				callEvent(activePlayerIdx, eventTargetPlayerIdx, EID.SUTEHAI);
			}
			break;
		case TSUMOAGARI:
			ui.event(EID.TSUMOAGARI, 0, 0);
			action = ACTION_TSUMOAGARI;
			break;
		default:
			break;
		}
	}
	
	private void callEvent(int eventCallPlayerIdx, int eventTargetPlayerIdx,
			EID eid) {
		EID returnEid;
		int j = eventCallPlayerIdx;

		for (int i = 0; i < players.length; i++) {
			// �A�N�V������ʒm

			// �c��
			ui.event(eid, activePlayer.ChaToPlayer[eventCallPlayerIdx],
					activePlayer.ChaToPlayer[eventTargetPlayerIdx]);
			returnEid = activePlayer.ai.event(eid,
					activePlayer.ChaToPlayer[eventCallPlayerIdx],
					activePlayer.ChaToPlayer[eventTargetPlayerIdx]);

			switch (returnEid) {
			case RON:
				activePlayer = players[j];
				activePlayerIdx = j;
				action = ACTION_RON;
				ui.event(EID.RON, activePlayerIdx,
						activePlayer.ChaToPlayer[eventCallPlayerIdx]);
				return;
			case TSUMOAGARI:
				activePlayer = players[j];
				activePlayerIdx = j;
				action = ACTION_TSUMOAGARI;
				return;
			default:
				j++;
				if (j >= players.length) {
					j = 0;
				}
				activePlayer = players[j];
				break;
			}
		}
	}
	
	private int countHu(Tehai tehai, Hai addHai, int combisCount, Combi combi) {
		int countHu = 20;
		int id;
		Hai checkHai[][];

		id = combi.atamaId;
		/*
		 * // TODO ��v�̎��ʂ��K�v
		 * 
		 * //������v if((combi.atamaId & KIND_TSUU) != 0 ){ //�Q ���ǉ� countHan += 2;
		 * }
		 */
		// TODO �P�R�A�J���`�����A�y���`�����Ȃ��
		// countHu += 2;
		
		// ���q�ɂ��ǉ�
		// �Í��ɂ����_
		for (int i = 0; i < combi.kouCount; i++) {
			id = combi.kouIds[i];
			// �v�����v��������1,9
			if (((id & KIND_TSUU) != 0) || ((id & KIND_MASK) == 1)
					|| ((id & KIND_MASK) == 9)) {
				countHu += 8;
			} else {
				countHu += 4;
			}
		}

		// �����ɂ����_
		for (int i = 0; i < tehai.getMinkousLength(); i++) {
			checkHai = tehai.getMinkous();
			id = checkHai[i][0].getId();
			// �v�����v��������1,9
			if (((id & KIND_TSUU) != 0) || ((id & KIND_MASK) == 1)
					|| ((id & KIND_MASK) == 9)) {
				countHu += 4;
			} else {
				countHu += 2;
			}
		}

		// ���Ȃɂ����_
		for (int i = 0; i < tehai.getMinkansLength(); i++) {
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			// �v�����v��������1,9
			if (((id & KIND_TSUU) != 0) || ((id & KIND_MASK) == 1)
					|| ((id & KIND_MASK) == 9)) {
				countHu += 16;
			} else {
				countHu += 8;
			}
		}

		// �ÞȂɂ����_
		for (int i = 0; i < tehai.getMinkansLength(); i++) {
			checkHai = tehai.getMinkans();
			id = checkHai[i][0].getId();
			// �v�����v��������1,9
			if (((id & KIND_TSUU) != 0) || ((id & KIND_MASK) == 1)
					|| ((id & KIND_MASK) == 9)) {
				countHu += 32;
			} else {
				countHu += 16;
			}
		}

		// TODO �c���オ��ɂ��ǉ�
		// countHu += 2;

		// TODO �ʑO�����オ��ɂ��ǉ�
		if (tehai.getJyunTehaiLength() < Tehai.JYUNTEHAI_MAX) {
			// TODO �����オ��̏ꍇ
			// countHu += 10;
		}

		return countHu;
	}
	
	public int getScore(int hanSuu ,int huSuu){
		int score;
		//���@�~  �Q�́@�i�𐔁@+�@��]����2��)
		score = huSuu * (int)Math.pow(2, hanSuu + 2 );
		//�q�͏��4�{����{�_(�e��6�{)
		score *= 4;
		
		
		if(hanSuu >= 13){		 //13�|�ȏ�͖�
			score = 32000;
		}else if (hanSuu >= 11){ //11�|�ȏ��3�{��
			score = 24000;
		}else if (hanSuu >= 8){  //8�|�ȏ�͔{��
			score = 16000;
		}else if (hanSuu >= 6){  //6�|�ȏ�͒���
			score = 12000;
		}else if (hanSuu >= 5){  //5�|�ȏ�͖���
			score = 8000;
		}
		
		//7700��8000�Ƃ���
		if(score > 7600){
			score = 8000;
		}
		
		//100�Ŋ���؂�Ȃ���������ꍇ100�_�J�グ
		if(score % 100 != 0){
			score = score - (score % 100) + 100;
		}
		
		return score;
	}

	public int getAgariScore(Tehai tehai, Hai addHai, int combisCount,Combi[] combis) {
		//��
		int hanSuu[] = new int [combisCount];
		//��
		int huSuu[]  = new int [combisCount];
		//�_���i�q�̃����オ��j
		int agariScore[]  = new int [combisCount];
		//�ő�̓_��
		int maxagariScore = 0;
		
		for (int i = 0; i < combisCount; i++) {
			Yaku yaku = new Yaku(tehai, addHai, combis[i]);
			hanSuu[i] = yaku.getHanSuu();
			huSuu[i] = countHu(tehai, addHai, combisCount, combis[i]);
			//TODO �h���̌v�Z
			agariScore[i] = getScore(hanSuu[i], huSuu[i]);
		}
			
		//�ő�l��T��
		maxagariScore = agariScore[0];
		for (int i = 0; i < combisCount; i++) {
			maxagariScore = Math.max(maxagariScore, agariScore[i]);
		}
		return maxagariScore;
	}
}
