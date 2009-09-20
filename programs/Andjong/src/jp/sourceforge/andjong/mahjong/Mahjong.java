package jp.sourceforge.andjong.mahjong;

import jp.sourceforge.andjong.mahjong.AgariScore;
import jp.sourceforge.andjong.mahjong.CountFormat.Combi;
import jp.sourceforge.andjong.mahjong.EventIF.EID;

/**
 * �Q�[�����Ǘ�����N���X�ł��B
 *
 * @author Yuji Urushibara
 *
 */
public class Mahjong {
	/** �R */
	private Yama yama;

	/**
	 * �R���擾���܂��B
	 *
	 * @return �R
	 */
	Yama getYama() {
		return yama;
	}

	/** ����� */
	public final static int KYOKU_TON_1 = 1;
	/** ����� */
	public final static int KYOKU_TON_2 = 2;
	/** ���O�� */
	public final static int KYOKU_TON_3 = 3;
	/** ���l�� */
	public final static int KYOKU_TON_4 = 4;

	/** �� */
	private int kyoku;

	/**
	 * �ǂ��擾���܂��B
	 *
	 * @return ��
	 */
	int getkyoku() {
		return kyoku;
	}

	/** �ǂ̍ő�l */
	private int kyokuMax;

	/** �c���v */
	private Hai tsumoHai;

	/**
	 * �c���v���擾���܂��B
	 *
	 * @return �c���v
	 */
	Hai getTsumoHai() {
		return tsumoHai;
	}

	/** �̔v */
	private Hai suteHai;

	/**
	 * �̔v���擾���܂��B
	 *
	 * @return �̔v
	 */
	Hai getSuteHai() {
		return suteHai;
	}

	/** �v���C���[�ɒ񋟂����� */
	private Info info;

	/** �v���C���[�̐l�� */
	private int playerLength;

	/** �v���C���[�̔z�� */
	private Player[] players;

	/** �����v���C���[�C���f�b�N�X�ɕϊ�����z�� */
	private int[] kazeToPlayerIdx = new int[4];

	/** UI�ɒ񋟂����� */
	private InfoUI infoUi;

	/** UI */
	private Console ui;

	/** ���[�`�_�̐� */
	private int reachbou;

	/** �e�̃v���C���[�C���f�b�N�X */
	private int oyaIdx;

	/** �A�� */
	private boolean renchan;

	/** �{�� */
	private int honba;

	/** �C�x���g�𔭍s������ */
	private int fromKaze;

	/** �C�x���g�̑ΏۂƂȂ����� */
	private int toKaze;

	/** �T�C�R���̔z�� */
	private Sai[] sais = new Sai[] { new Sai(), new Sai() };

	/**
	 * �T�C�R���̔z����擾���܂��B
	 *
	 * @return �T�C�R���̔z��
	 */
	Sai[] getSais() {
		return sais;
	}

	public final static int KAZE_TON = 1;
	public final static int KAZE_NAN = 2;
	public final static int KAZE_SHA = 3;
	public final static int KAZE_PE = 4;

	/** ����� */
	private int wareme;

	/** �A�N�e�B�u�v���C���[ */
	private Player activePlayer;

	/**
	 * ���C���������J�n���܂��B
	 *
	 * @param args
	 *            �R�}���h���C������
	 */
	public static void main(String[] args) {
		// Game�C���X�^���X���쐬���܂��B
		Mahjong game = new Mahjong();

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

		// �C�x���g�i�ꏊ���߁j�𔭍s���܂��B
		ui.event(EID.BASHOGIME, 0, 0);

		// �v���C���[���e�����߂܂��B
		sais[0].saifuri();
		sais[1].saifuri();
		oyaIdx = (sais[0].getNo() + sais[1].getNo() - 1) % 4;

		// �C�x���g�i�e���߁j�𔭍s���܂��B
		ui.event(EID.OYAGIME, 0, 0);

		// �ǂ��J�n���܂��B
		while (kyoku <= kyokuMax) {
			startKyoku();
			if (!renchan) {
				kyoku++;
				honba = 0;
			} else {
				System.out.println("�A���ł��B");
			}
		}
	}

	/**
	 * ���������܂��B
	 * <p>
	 * �ݒ�ɂ���ē��I�ɏ��������܂��B
	 * </p>
	 */
	private void init() {
		// �R�����������܂��B
		yama = new Yama();

		// �ǂ����������܂��B
		kyoku = 1;

		// �ǂ̍ő�l��ݒ肵�܂��B
		kyokuMax = 4;

		// �c���v�����������܂��B
		tsumoHai = new Hai();

		// �̔v�����������܂��B
		suteHai = new Hai();

		// �v���C���[�ɒ񋟂���������������܂��B
		info = new Info(this);

		// �v���C���[�̐l����ݒ肵�܂��B
		playerLength = 4;

		// �v���C���[�z������������܂��B
		players = new Player[playerLength];
		// for (int i = 0; i < players.length; i++) {
		// players[i] = new Player((EventIF) new AI(info));
		// }
		players[0] = new Player((EventIF) new AI(info, "��Y"));
		players[1] = new Player((EventIF) new AI(info, "��Y"));
		players[2] = new Player((EventIF) new AI(info, "�O�Y"));
		players[3] = new Player((EventIF) new AI(info, "�l�Y"));
		// players[0] = new Player((EventIF) new Man(info));

		// �����v���C���[�C���f�b�N�X�ɕϊ�����z������������܂��B
		kazeToPlayerIdx = new int[players.length];

		// UI�ɒ񋟂���������������܂��B
		infoUi = new InfoUI(this);

		// UI�����������܂��B
		ui = new Console(infoUi, "�R���\�[��");
	}

	/**
	 * �ǂ��J�n���܂��B
	 */
	private void startKyoku() {
		// ���[�`�_�̐������������܂��B
		reachbou = 0;

		// �A�������������܂��B
		renchan = false;

		// �C�x���g�𔭍s�����������������܂��B
		fromKaze = oyaIdx;

		// �C�x���g�̑ΏۂƂȂ����������������܂��B
		toKaze = oyaIdx;

		// �v���C���[�̎�����ݒ肵�܂��B
		setJikaze();

		// ���v�����܂��B
		yama.xipai();

		// UI�C�x���g�i���v�j�𔭍s���܂��B
		ui.event(EID.SENPAI, fromKaze, toKaze);

		// �T�C�U������܂��B
		sais[0].saifuri();
		sais[1].saifuri();

		// �R�Ɋ���ڂ�ݒ肵�܂��B
		setWareme(sais);

		// �v���C���[�z������������܂��B
		for (int i = 0; i < players.length; i++) {
			players[i].init();
		}

		// UI�C�x���g�i�T�C�U��j�𔭍s���܂��B
		ui.event(EID.SAIFURI, fromKaze, toKaze);

		// �z�v�����܂��B
		haipai();

		// �ǂ̃��C�����[�v
		EID retEid;
		MAINLOOP: while (true) {
			// �c�����܂��B
			tsumoHai = yama.tsumo();

			// �c���v���Ȃ��ꍇ�A���ǂ��܂��B
			if (tsumoHai == null) {
				// UI�C�x���g�i���ǁj�𔭍s���܂��B
				ui.event(EID.RYUUKYOKU, 0, 0);

				// �e���X�V���܂��B
				oyaIdx++;
				if (oyaIdx >= players.length) {
					oyaIdx = 0;
				}

				break MAINLOOP;
			}

			// �C�x���g�i�c���j�𔭍s���܂��B
			retEid = tsumoEvent();

			// �C�x���g���������܂��B
			switch (retEid) {
			case TSUMOAGARI:// �c��������
				// UI�C�x���g�i�c��������j�𔭍s���܂��B
				ui.event(retEid, fromKaze, toKaze);

				// TODO �_���𐴎Z���܂��B
				activePlayer.increaseTenbou(reachbou * 1000);

				// �e���X�V���܂��B
				if (oyaIdx != kazeToPlayerIdx[fromKaze]) {
					oyaIdx++;
					if (oyaIdx >= players.length) {
						oyaIdx = 0;
					}
				} else {
					renchan = true;
					honba++;
				}

				break MAINLOOP;
			case RON:// ����
				// UI�C�x���g�i�����j�𔭍s���܂��B
				ui.event(retEid, fromKaze, toKaze);

				// TODO �_���𐴎Z���܂��B
				activePlayer.increaseTenbou(reachbou * 1000);

				// �e���X�V���܂��B
				if (oyaIdx != kazeToPlayerIdx[fromKaze]) {
					oyaIdx++;
					if (oyaIdx >= players.length) {
						oyaIdx = 0;
					}
				} else {
					renchan = true;
					honba++;
				}

				break MAINLOOP;
			case REACH:// ���[�`
				int tenbou = activePlayer.getTenbou();
				if (tenbou >= 1000) {
					activePlayer.reduceTenbou(1000);
					activePlayer.setReach(true);
					reachbou++;
				}
				break;
			default:
				break;
			}

			// �C�x���g�𔭍s���������X�V���܂��B
			fromKaze++;
			if (fromKaze >= players.length) {
				fromKaze = 0;
			}
		}
	}

	/**
	 * �R�Ɋ���ڂ�ݒ肵�܂��B
	 *
	 * @param sais
	 *            �T�C�R���̔z��
	 */
	void setWareme(Sai[] sais) {
		int sum = sais[0].getNo() + sais[1].getNo() - 1;

		wareme = sum % 4;

		int startHaisIdx = ((sum % 4) * 36) + sum;

		yama.setTsumoHaisStartIdx(startHaisIdx);
	}

	/**
	 * �v���C���[�̎�����ݒ肵�܂��B
	 */
	private void setJikaze() {
		for (int i = 0, j = oyaIdx; i < players.length; i++, j++) {
			if (j >= players.length) {
				j = 0;
			}

			// �v���C���[�̎�����ݒ肵�܂��B
			players[j].setJikaze(i);

			// �����v���C���[�C���f�b�N�X�ɕϊ�����z���ݒ肵�܂��B
			kazeToPlayerIdx[i] = j;
		}
	}

	/**
	 * �z�v���܂��B
	 */
	private void haipai() {
		for (int i = 0, j = oyaIdx, max = players.length * 13; i < max; i++, j++) {
			if (j >= players.length) {
				j = 0;
			}

			players[j].getTehai().addJyunTehai(yama.tsumo());
		}
	}

	/**
	 * �C�x���g�i�c���j�𔭍s���܂��B
	 *
	 * @return �C�x���gID
	 */
	private EID tsumoEvent() {
		// �A�N�e�B�u�v���C���[��ݒ肵�܂��B
		activePlayer = players[kazeToPlayerIdx[fromKaze]];

		// UI�C�x���g�i�c���j�𔭍s���܂��B
		ui.event(EID.TSUMO, fromKaze, fromKaze);

		// �C�x���g�i�c���j�𔭍s���܂��B
		EID retEid = activePlayer.getEventIf().event(EID.TSUMO, fromKaze,
				fromKaze);

		int sutehaiIdx;

		// �C�x���g���������܂��B
		switch (retEid) {
		case TSUMOAGARI:// �c��������
			break;
		case SUTEHAI:// �̔v
			// �̔v�̃C���f�b�N�X���擾���܂��B
			sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();
			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(suteHai, tsumoHai);
				activePlayer.getKawa().add(suteHai);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(tsumoHai);
				activePlayer.getKawa().add(suteHai);
				activePlayer.getKawa().setTedashi(true);
			}

			// �C�x���g��ʒm���܂��B
			retEid = notifyEvent(EID.SUTEHAI, fromKaze, fromKaze);
			break;
		case REACH:
			// �̔v�̃C���f�b�N�X���擾���܂��B
			sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();
			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(suteHai, tsumoHai);
				activePlayer.getKawa().add(suteHai);
				activePlayer.getKawa().setReach(true);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(tsumoHai);
				activePlayer.getKawa().add(suteHai);
				activePlayer.getKawa().setTedashi(true);
				activePlayer.getKawa().setReach(true);
			}

			// �C�x���g��ʒm���܂��B
			retEid = notifyEvent(EID.REACH, fromKaze, fromKaze);
			break;
		default:
			break;
		}

		return retEid;
	}

	/**
	 * �C�x���g��ʒm���܂��B
	 *
	 * @param eid
	 *            �C�x���gID
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑ΏۂƂȂ�����
	 * @return �C�x���gID
	 */
	private EID notifyEvent(EID eid, int fromKaze, int toKaze) {
		EID retEid = EID.NAGASHI;

		// �e�v���C���[�ɃC�x���g��ʒm����B
		NOTIFYLOOP: for (int i = 0, j = fromKaze; i < players.length; i++, j++) {
			if (j >= players.length) {
				j = 0;
			}

			// �A�N�e�B�u�v���C���[��ݒ肵�܂��B
			activePlayer = players[kazeToPlayerIdx[j]];

			// UI�C�x���g�𔭍s���܂��B
			ui.event(eid, fromKaze, toKaze);

			// �C�x���g�𔭍s���܂��B
			retEid = activePlayer.getEventIf().event(eid, fromKaze, toKaze);

			// �C�x���g���������܂��B
			switch (retEid) {
			case TSUMOAGARI:// �c��������
				// �A�N�e�B�u�v���C���[��ݒ肵�܂��B
				this.fromKaze = j;
				this.toKaze = toKaze;
				activePlayer = players[kazeToPlayerIdx[this.fromKaze]];
				break NOTIFYLOOP;
			case RON:// ����
				// �A�N�e�B�u�v���C���[��ݒ肵�܂��B
				this.fromKaze = j;
				this.toKaze = toKaze;
				activePlayer = players[kazeToPlayerIdx[this.fromKaze]];
				break NOTIFYLOOP;
			case PON:
				// �A�N�e�B�u�v���C���[��ݒ肵�܂��B
				this.fromKaze = j;
				this.toKaze = fromKaze;
				activePlayer = players[kazeToPlayerIdx[this.fromKaze]];
				activePlayer.getTehai().setPon(suteHai);

				notifyEvent(EID.SUTEHAISELECT, this.fromKaze, this.toKaze);

				// �̔v�̃C���f�b�N�X���擾���܂��B
				int sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();
				activePlayer.getTehai().copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getKawa().add(suteHai);
				activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm���܂��B
				retEid = notifyEvent(EID.PON, this.fromKaze, this.toKaze);
				break NOTIFYLOOP;
			default:
				break;
			}
		}

		return retEid;
	}

	/*
	 * Info, InfoUI�ɒ񋟂���API���`���܂��B
	 */

	/**
	 * �\�h���A�ȃh���̔z����擾���܂��B
	 *
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getDoras() {
		return getYama().getOmoteDoraHais();
	}

	/**
	 * �\�h���A�ȃh���̔z����擾���܂��B
	 *
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getUraDoras() {
		return getYama().getUraDoraHais();
	}

	/**
	 * �������擾���܂��B
	 */
	int getJikaze() {
		return activePlayer.getJikaze();
	}

	/**
	 * �{����擾���܂��B
	 *
	 * @return �{��
	 */
	int getHonba() {
		return honba;
	}

	/**
	 * ���[�`���擾���܂��B
	 *
	 * @param kaze
	 *            ��
	 * @return ���[�`
	 */
	boolean isReach(int kaze) {
		return players[kazeToPlayerIdx[kaze]].isReach();
	}

	/**
	 * ��v���R�s�[���܂��B
	 *
	 * @param tehai
	 *            ��v
	 * @param kaze
	 *            ��
	 */
	void copyTehai(Tehai tehai, int kaze) {
		if (activePlayer.getJikaze() == kaze) {
			Tehai.copy(tehai, activePlayer.getTehai(), true);
		} else {
			Tehai.copy(tehai, players[kazeToPlayerIdx[kaze]].getTehai(), false);
		}
	}

	/**
	 * �͂��R�s�[���܂��B
	 *
	 * @param kawa
	 *            ��
	 * @param kaze
	 *            ��
	 */
	void copyKawa(Kawa kawa, int kaze) {
		Kawa.copy(kawa, players[kazeToPlayerIdx[kaze]].getKawa());
	}

	/**
	 * �c���̎c�萔���擾���܂��B
	 *
	 * @return �c���̎c�萔
	 */
	int getTsumoRemain() {
		return yama.getTsumoNokori();
	}

	String getName(int kaze) {
		return players[kazeToPlayerIdx[kaze]].getEventIf().getName();
	}

	int getTenbou(int kaze) {
		return players[kazeToPlayerIdx[kaze]].getTenbou();
	}

	int getWareme() {
		return wareme;
	}

	private Combi[] combis = new Combi[10];
	{
		for (int i = 0; i < combis.length; i++)
			combis[i] = new Combi();
	}

	public int getAgariScore(Tehai tehai, Hai addHai) {
		AgariSetting setting = new AgariSetting(this);
		AgariScore score = new AgariScore();
		return score.getAgariScore(tehai, addHai, combis, setting);
	}
	
	public String[] getYakuName(Tehai tehai, Hai addHai){
		AgariSetting setting = new AgariSetting(this);
		AgariScore score = new AgariScore();
		return score.getYakuName(tehai, addHai, combis, setting);	
	}
	
	
}
