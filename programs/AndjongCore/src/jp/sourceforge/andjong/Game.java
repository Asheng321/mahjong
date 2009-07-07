package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Info.*;
import static jp.sourceforge.andjong.Hai.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import jp.sourceforge.andjong.Tehai.Combi;
import jp.sourceforge.andjong.Tehai.CountFormat;
import jp.sourceforge.andjong.Tehai.TehaiBuffer;

/**
 * �Q�[���̃n���h�����O���s���N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Game {
	/** �R */
	private Yama yama;

	/** �v���C���[�̐l�� */
	private int playerNum;

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

	private UI ui;
	
	private TehaiBuffer tehaiBuffer = new TehaiBuffer();

	private boolean checkTanyao(TehaiBuffer tehaiBuffer, Hai addHai, Combi combi) {
		int id;

		for(int i = 0; i< tehaiBuffer.jyunTehaiLength; i++ ) {
			id = tehaiBuffer.jyunTehai[i].getId();
			if((id & KIND_SHUU) == 0)
				return false;
			id &= KIND_MASK;
			if((id == 1) || (id == 9))
				return false;
		}
		
		// TODO ���v�̊m�F��
		
		return true;
	}

	public int getAgariScore(Tehai tehai, Hai addHai, int combisCount,
			Combi[] combis) {
		/*
		 * �����Ŗ��Ɠ��_���v�Z����B
		 */
		
		tehaiBuffer.copyTehai(tehai, true);
		boolean tanyao;
		for (int i = 0; i < combisCount; i++) {
			tanyao = checkTanyao(tehaiBuffer, addHai, combis[i]);
			if (tanyao) {
				System.out.println("�^�����I�I�I�I");
				/*
				 * TODO �����J�E���g���Ă����B
				 */
			}
		}
		return 0;
	}

	public void play() {
		// Game�I�u�W�F�N�g�����������܂��B
		init();

		// �ꏊ�����߂܂��B
		// TODO ���ƂŎ������܂��B

		// �e�����߂܂��B
		// TODO �����_���Ō��߂�K�v������܂��B
		oya = 0;

		// �v���C���[�����������܂��B
		playerNum = 4;
		players = new Player[playerNum];
		for (int i = 0; i < playerNum; i++)
			players[i] = new Player(new AI(info));

		// �ǂ��J�n���܂��B
		// TODO �ŏ��͓�����ɂ��Ă����܂��B
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

	public void saifuri() {
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

	private final static int HAIPAI_END = 52;

	private void haipai() {
		for (int i = 0, j = oya; i < HAIPAI_END; i++) {
			players[j].tehai.addJyunTehai(yama.tsumo());

			j++;
			if (j >= players.length) {
				j = 0;
			}
		}
	}

	private void startKyoku() {
		reachbou = 0;
		renchan = false;

		// �Ƃ�ݒ�
		setCha();

		// ���v
		yama.xipai();

		// �T�C�U��
		saifuri();

		// �v���C���[�̏�����
		for (int i = 0; i < players.length; i++)
			players[i].init();

		// �z�v
		haipai();

		Hai[] doras = yama.getDoraAll();
		for (Hai hai : doras) {
			System.out.println("�h��:" + hai.getId());
		}

		{
			// debug
			System.out.println("�e:" + oya);
		}

		// �ǂ̃��C�����[�v
		loopKyoku();

		{
			// debug
			System.out.println("honba:" + honba);
		}
	}

	private Player activePlayer;

	public Player getActivePlayer() {
		return activePlayer;
	}

	private final static int ACTION_TSUMOAGARI = 0;
	private final static int ACTION_RON = 1;

	// int eventCallPlayerIdx;
	int eventTargetPlayerIdx;

	private void callEvent(int eventCallPlayerIdx, int eventTargetPlayerIdx,
			int eventId) {
		int returnEvent;
		int j = eventCallPlayerIdx;

		for (int i = 0; i < players.length; i++) {
			// �A�N�V������ʒm

			// �c��
			ui.event(activePlayer.ChaToPlayer[eventCallPlayerIdx],
					activePlayer.ChaToPlayer[eventTargetPlayerIdx], eventId);
			returnEvent = activePlayer.ai.event(
					activePlayer.ChaToPlayer[eventCallPlayerIdx],
					activePlayer.ChaToPlayer[eventTargetPlayerIdx], eventId);

			switch (returnEvent) {
			case EVENTID_RON:
				activePlayer = players[j];
				activePlayerIdx = j;
				action = ACTION_RON;
				ui.event(activePlayerIdx,
						activePlayer.ChaToPlayer[eventCallPlayerIdx],
						EVENTID_RON);
				return;
			case EVENTID_TSUMOAGARI:
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

	Hai tsumoHai;

	Hai suteHai;

	private void sutehai() {
		int returnEvent;
		int sutehaiIdx;

		// �C�x���g�i�c���j
		ui.event(0, 0, EVENTID_TSUMO);
		returnEvent = activePlayer.ai.event(0, 0, EVENTID_TSUMO);

		switch (returnEvent) {
		case EVENTID_SUTEHAI:
			// �C�x���g�i�̔v�j
			sutehaiIdx = info.getSutehaiIdx();
			if (sutehaiIdx == 13) {
				// �c���؂�
				suteHai.copy(tsumoHai);
				activePlayer.kawa.add(suteHai);
				eventTargetPlayerIdx = activePlayerIdx;
				callEvent(activePlayerIdx, eventTargetPlayerIdx,
						EVENTID_SUTEHAI);
			} else {
				// ��o��
				activePlayer.tehai.copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.tehai.removeJyunTehai(sutehaiIdx);
				activePlayer.kawa.add(suteHai, Kawa.PROPERTY_TEDASHI);
				if (tsumoHai != null) {
					activePlayer.tehai.addJyunTehai(tsumoHai);
				}
				eventTargetPlayerIdx = activePlayerIdx;
				callEvent(activePlayerIdx, eventTargetPlayerIdx,
						EVENTID_SUTEHAI);
			}
			break;
		case EVENTID_TSUMOAGARI:
			ui.event(0, 0, EVENTID_TSUMOAGARI);
			action = ACTION_TSUMOAGARI;
			break;
		default:
			break;
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

	private void init() {
		yama = new Yama();
		kyoku = 0;
		renchan = false;
		info = new Info(this);
		ui = new UI(info);
		tsumoHai = new Hai();
		suteHai = new Hai();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}
