package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.Hai.*;

import jp.sourceforge.andjong.EventIF.EID;
import jp.sourceforge.andjong.Tehai.Combi;
import jp.sourceforge.andjong.Tehai.CountFormat;
import jp.sourceforge.andjong.Yaku;

/**
 * �Q�[�����Ǘ�����N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Game {
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

		// �C�x���g�i�ꏊ���߁j�𔭍s���܂��B
		ui.event(EID.BASHOGIME, 0, 0);

		// �v���C���[���e�����߂܂��B
		sais[0].saifuri();
		sais[1].saifuri();
		oyaIdx = (sais[0].getNo() + sais[1].getNo() - 1) % 4;

		// �C�x���g�i�e���߁j�𔭍s���܂��B
		ui.event(EID.OYAGIME, 0, 0);

		// �ǂ��J�n���܂��B
		while (kyoku < kyokuMax) {
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
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player((EventIF) new AI(info));
		}
		// players[0] = new Player((EventIF) new Man(info));

		// �����v���C���[�C���f�b�N�X�ɕϊ�����z������������܂��B
		kazeToPlayerIdx = new int[players.length];

		// UI�ɒ񋟂���������������܂��B
		infoUi = new InfoUI(this);

		// UI�����������܂��B
		ui = new Console(infoUi);
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
		wareme = (sais[0].getNo() + sais[1].getNo() - 1) % 4;

		// UI�C�x���g�i�T�C�U��j�𔭍s���܂��B
		ui.event(EID.SAIFURI, fromKaze, toKaze);

		// �v���C���[�z������������܂��B
		for (int i = 0; i < players.length; i++) {
			players[i].init();
		}

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
		// TODO �R�Ɋ���ڂ�ݒ肷��K�v������܂��B

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
				suteHai.copy(tsumoHai);
				activePlayer.getKawa().add(suteHai);

				// �C�x���g��ʒm���܂��B
				retEid = notifyEvent(EID.SUTEHAI, fromKaze, fromKaze);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIdx(suteHai, sutehaiIdx);
				activePlayer.getTehai().removeJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(tsumoHai);
				activePlayer.getKawa().add(suteHai, Kawa.PROPERTY_TEDASHI);

				// �C�x���g��ʒm���܂��B
				retEid = notifyEvent(EID.SUTEHAI, fromKaze, fromKaze);
			}
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
		return getYama().getDoras();
	}

	/**
	 * �������擾���܂��B
	 */
	int getJikaze() {
		return activePlayer.getJikaze();
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
			tehai.copy(activePlayer.getTehai(), true);
		} else {
			tehai.copy(players[kazeToPlayerIdx[kaze]].getTehai(), false);
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
		kawa.copy(players[kazeToPlayerIdx[kaze]].getKawa());
	}

	/** �J�E���g�t�H�[�}�b�g */
	private CountFormat countFormat = new CountFormat();

	private Combi[] combis = new Combi[10];
	{
		for (int i = 0; i < combis.length; i++)
			combis[i] = new Combi();
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

	public int getScore(int hanSuu, int huSuu) {
		int score;
		// ���@�~ �Q�́@�i�𐔁@+�@��]����2��)
		score = huSuu * (int) Math.pow(2, hanSuu + 2);
		// �q�͏��4�{����{�_(�e��6�{)
		score *= 4;

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

		// 7700��8000�Ƃ���
		if (score > 7600) {
			score = 8000;
		}

		// 100�Ŋ���؂�Ȃ���������ꍇ100�_�J�グ
		if (score % 100 != 0) {
			score = score - (score % 100) + 100;
		}

		return score;
	}

	public int getAgariScore(Tehai tehai, Hai addHai) {
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
			Yaku yaku = new Yaku(tehai, addHai, combis[i]);
			hanSuu[i] = yaku.getHanSuu();
			huSuu[i] = countHu(tehai, addHai, combisCount, combis[i]);
			// TODO �h���̌v�Z
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
