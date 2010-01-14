package jp.sourceforge.andjong.mahjong;

import android.util.Log;
import jp.sourceforge.andjong.AndjongView;
import jp.sourceforge.andjong.mahjong.AgariScore;
import jp.sourceforge.andjong.mahjong.AgariScore.AgariInfo;
import jp.sourceforge.andjong.mahjong.AgariSetting.YakuflgName;
import jp.sourceforge.andjong.mahjong.CountFormat.Combi;
import static jp.sourceforge.andjong.mahjong.EventIf.*;
import static jp.sourceforge.andjong.mahjong.Hai.*;

/**
 * �Q�[�����Ǘ�����N���X�ł��B
 *
 * @author Yuji Urushibara
 *
 */
public class Mahjong implements Runnable {
	/** AndjongView */
	private AndjongView m_view;

	/** �R */
	private Yama m_yama;

	/** ����� */
	public final static int KYOKU_TON_1 = 0;
	/** ����� */
	public final static int KYOKU_TON_2 = 1;
	/** ���O�� */
	public final static int KYOKU_TON_3 = 2;
	/** ���l�� */
	public final static int KYOKU_TON_4 = 3;

	/** �� */
	private int m_kyoku;

	/** �ǂ̍ő�l */
	private int m_kyokuEnd;

	/** �c���v */
	private Hai m_tsumoHai;

	/** �̔v */
	private Hai m_suteHai;

	/** ���[�`�_�̐� */
	private int m_reachbou;

	/** �{�� */
	private int m_honba;

	/** �v���C���[�̐l�� */
	private int m_playerNum;

	/** �v���C���[�ɒ񋟂����� */
	private Info m_info;

	/** �v���C���[�̔z�� */
	private Player[] m_players;

	/** �����v���C���[�C���f�b�N�X�ɕϊ�����z�� */
	private int[] m_kazeToPlayerIdx = new int[4];

	/** UI�ɒ񋟂����� */
	private InfoUi m_infoUi;

	/** �T�C�R���̔z�� */
	private Sai[] m_sais = new Sai[] { new Sai(), new Sai() };

	/** �e�̃v���C���[�C���f�b�N�X */
	private int m_iOya;

	/** �N�Ƃ̃v���C���[�C���f�b�N�X */
	private int m_iChiicha;

	/** �A�� */
	private boolean m_renchan;

	/** �C�x���g�𔭍s������ */
	private int m_kazeFrom;

	/** �C�x���g�̑ΏۂƂȂ����� */
	private int m_kazeTo;

	/** �����_�̏����l */
	private static final int TENBOU_INIT = 20000;

	/**
	 * �R���X�g���N�^
	 *
	 * @param view
	 *            View
	 */
	public Mahjong(AndjongView view) {
		super();
		this.m_view = view;
	}

	/**
	 * �R���擾����B
	 *
	 * @return �R
	 */
	Yama getYama() {
		return m_yama;
	}

	/**
	 * �ǂ��擾����B
	 *
	 * @return ��
	 */
	int getkyoku() {
		return m_kyoku;
	}

	/**
	 * �c���v���擾����B
	 *
	 * @return �c���v
	 */
	Hai getTsumoHai() {
		return m_tsumoHai;
	}

	/**
	 * �̔v���擾����B
	 *
	 * @return �̔v
	 */
	Hai getSuteHai() {
		return m_suteHai;
	}

	public int getReachbou() {
		return m_reachbou;
	}

	public void setReachbou(int reachbou) {
		m_reachbou = reachbou;
	}

	/**
	 * �N�Ƃ̃v���C���[�C���f�b�N�X���擾����B
	 *
	 * @return �N�Ƃ̃v���C���[�C���f�b�N�X
	 */
	public int getChiichaIdx() {
		return m_iChiicha;
	}

	/**
	 * �T�C�R���̔z����擾����B
	 *
	 * @return �T�C�R���̔z��
	 */
	Sai[] getSais() {
		return m_sais;
	}

	public int getRelation(
			int fromKaze,
			int toKaze) {
		int relation;
		if (fromKaze == toKaze) {
			relation = RELATION_JIBUN;
		} else if ((fromKaze + 1) % 4 == toKaze) {
			relation = RELATION_SHIMOCHA;
		} else if ((fromKaze + 2) % 4 == toKaze) {
			relation = RELATION_TOIMEN;
		} else {
			relation = RELATION_KAMICHA;
		}
		return relation;
	}

	/*
	 * ���ʒ�`
	 */

	/** �ʎq�̍\���v�̐�(3��) */
	public static final int MENTSU_HAI_MEMBERS_3 = 3;
	/** �ʎq�̍\���v�̐�(4��) */
	public static final int MENTSU_HAI_MEMBERS_4 = 4;

	/** ���ƂƂ̊֌W ���� */
	public static final int RELATION_JIBUN = 0;
	/** ���ƂƂ̊֌W ��� */
	public static final int RELATION_KAMICHA = 1;
	/** ���ƂƂ̊֌W �Ζ� */
	public static final int RELATION_TOIMEN = 2;
	/** ���ƂƂ̊֌W ���� */
	public static final int RELATION_SHIMOCHA = 3;

	/** ����� */
	private int mWareme;

	/** �A�N�e�B�u�v���C���[ */
	private Player activePlayer;

	private PlayerAction m_playerAction = new PlayerAction();

	public int getManKaze() {
		return m_players[0].getJikaze();
	}

	/**
	 * �Q�[�����J�n����B
	 */
	public void play() {
		// ����������B
		initialize();

		// �e�����߂�B
		m_sais[0].saifuri();
		m_sais[1].saifuri();
		m_iOya = (m_sais[0].getNo() + m_sais[1].getNo() - 1) % 4;
		m_iChiicha = m_iOya;

		// �C�x���g�i�e���߁j�𔭍s����B
		//m_view.event(EventId.OYAGIME, KAZE_NONE, KAZE_NONE);
		m_view.event(EventId.START_GAME, KAZE_NONE, KAZE_NONE);

		// �ǂ��J��Ԃ��āA�Q�[����i�s����B
		while (m_kyoku <= m_kyokuEnd) {
			Log.e("Mahjong", "oya = " + m_iOya);
			// �ǂ��J�n����B
			startKyoku();

			// �A���̏ꍇ�A�ǂ�i�߂Ȃ��B
			if (m_renchan) {
				// �C�x���g�i�A���j�𔭍s����B
				//m_view.event(EventId.RENCHAN, KAZE_NONE, KAZE_NONE);
				continue;
			}

			// �ǂ�i�߂�B
			m_kyoku++;

			// �{�������������B
			// TODO �オ��̈ʒu�Ɉړ����Ȃ��ƁB
			m_honba = 0;
		}

		// �C�x���g�i�Q�[���̏I���j�𔭍s����B
		m_view.event(EventId.END_GAME, KAZE_NONE, KAZE_NONE);
	}

	/**
	 * ����������B
	 */
	private void initialize() {
		// �R���쐬����B
		m_yama = new Yama();

		// �ǂ�����������B
		m_kyoku = KYOKU_TON_1;

		// �ǂ̏I����ݒ肷��B
		m_kyokuEnd = KYOKU_TON_4;

		// �c���v���쐬����B
		m_tsumoHai = new Hai();

		// �̔v���쐬����B
		m_suteHai = new Hai();

		// ���[�`�_�̐�������������B
		m_reachbou = 0;

		// �{�������������B
		m_honba = 0;

		// �v���C���[�̐l����ݒ肷��B
		m_playerNum = 4;

		// �v���C���[�ɒ񋟂�������쐬����B
		m_info = new Info(this);

		// �v���C���[�̔z�������������B
		m_players = new Player[m_playerNum];
		m_players[0] = new Player((EventIf) new Man(m_info, "A", m_playerAction));
		m_players[1] = new Player((EventIf) new AI(m_info, "B"));
		m_players[2] = new Player((EventIf) new AI(m_info, "C"));
		m_players[3] = new Player((EventIf) new AI(m_info, "D"));

		for (int i = 0; i < m_playerNum; i++) {
			m_players[i].setTenbou(TENBOU_INIT);
		}

		// �����v���C���[�C���f�b�N�X�ɕϊ�����z�������������B
		m_kazeToPlayerIdx = new int[m_players.length];

		// UI�ɒ񋟂�������쐬����B
		m_infoUi = new InfoUi(this, m_playerAction);

		// UI������������B
		m_view.initUi(m_infoUi, "AndjongView");
	}

	boolean m_tenpai[] = new boolean[4];

	public boolean[] getTenpai() {
		return m_tenpai;
	}

	/**
	 * �ǂ��J�n����B
	 */
	private void startKyoku() {
		// ���[�`�_�̐�������������B
		// TODO �オ��̈ʒu�Ɉړ����Ȃ��ƁB
		m_reachbou = 0;

		// �A��������������B
		m_renchan = false;

		m_isTsumo = false;
		m_isRinshan = false;
		m_isLast = false;

		// �v���C���[�̎�����ݒ肷��B
		setJikaze();

		// �C�x���g�𔭍s������������������B
		m_kazeFrom = m_players[m_iOya].getJikaze();

		// �C�x���g�̑ΏۂƂȂ�����������������B
		m_kazeTo = m_players[m_iOya].getJikaze();

		// �v���C���[�z�������������B
		for (int i = 0; i < m_players.length; i++) {
			m_players[i].init();
		}

		// ���v����B
		m_yama.xipai();

		// �T�C�U�������B
		m_sais[0].saifuri();
		m_sais[1].saifuri();

		// UI�C�x���g�i�T�C�U��j�𔭍s����B
		//m_view.event(EventId.SAIFURI, mFromKaze, mToKaze);

		// �R�Ɋ���ڂ�ݒ肷��B
		setWareme(m_sais);

		// �z�v����B
		haipai();

		// UI�C�x���g�i�z�v�j�𔭍s����B
		//m_view.event(EventId.HAIPAI, mFromKaze, mToKaze);
		m_view.event(EventId.START_KYOKU, m_kazeFrom, m_kazeTo);

		EventId retEid;

		int tsumoNokori;
		// �ǂ�i�s����B
		KYOKU_MAIN: while (true) {
			// UI�C�x���g�i�i�s�҂��j�𔭍s����B
			m_view.event(EventId.UI_WAIT_PROGRESS, KAZE_NONE, KAZE_NONE);

			// �c���v���擾����B
			m_tsumoHai = m_yama.tsumo();

			// �c���v���Ȃ��ꍇ�A���ǂ���B
			if (m_tsumoHai == null) {
				// �e���p�C�̊m�F������B
				int tenpaiCount = 0;
				int iPlayer;
				for (int i = 0; i < m_tenpai.length; i++) {
					iPlayer = m_kazeToPlayerIdx[i];
					m_tenpai[iPlayer] = m_players[iPlayer].isTenpai();
					if (m_tenpai[iPlayer]) {
						tenpaiCount++;
					}
				}
				int incScore = 0;
				int redScore = 0;
				switch (tenpaiCount) {
				case 0:
					break;
				case 1:
					incScore = 3000;
					redScore = 1000;
					break;
				case 2:
					incScore = 1500;
					redScore = 1500;
					break;
				case 3:
					incScore = 1000;
					redScore = 3000;
					break;
				}
				for (int i = 0; i < m_tenpai.length; i++) {
					if (m_tenpai[i]) {
						m_players[i].increaseTenbou(incScore);
					} else {
						m_players[i].reduceTenbou(redScore);
					}
				}

				// UI�C�x���g�i���ǁj�𔭍s����B
				m_view.event(EventId.RYUUKYOKU, KAZE_NONE, KAZE_NONE);

				// �t���O�𗎂Ƃ��Ă����B
				for (int i = 0; i < m_tenpai.length; i++) {
					m_tenpai[i] = false;
				}

				// �e���X�V����B�オ��A���Ƃ���B
				m_iOya++;
				if (m_iOya >= m_players.length) {
					m_iOya = 0;
				}

				// �{��𑝂₷�B
				m_honba++;

				break KYOKU_MAIN;
			}

			tsumoNokori = m_yama.getTsumoNokori();
			if (tsumoNokori == 0) {
				m_isLast = true;
			}

			// �C�x���g�i�c���j�𔭍s����B
			retEid = tsumoEvent();

			Log.e("Mahjong", "kazeFrom = " + m_kazeFrom + " kazeTo = " + m_kazeTo);
			Log.e("Mahjong", "iFrom = " + m_kazeToPlayerIdx[m_kazeFrom] + " iTo = " + m_kazeToPlayerIdx[m_kazeTo]);
			int score;
			int iPlayer;
			// �C�x���g����������B
			switch (retEid) {
			case TSUMO_AGARI:// �c��������
				iPlayer = m_kazeToPlayerIdx[m_kazeTo];
				if (m_iOya == iPlayer) {
					score = m_agariInfo.m_score.m_oyaRon + (m_honba * 300);
					for (int i = 0; i < 3; i++) {
						iPlayer = (iPlayer + 1) % 4;
						m_players[iPlayer].reduceTenbou(m_agariInfo.m_score.m_oyaTsumo + (m_honba * 100));
					}
				} else {
					score = m_agariInfo.m_score.m_koRon + (m_honba * 300);
					for (int i = 0; i < 3; i++) {
						iPlayer = (iPlayer + 1) % 4;
						if (m_iOya == iPlayer) {
							m_players[iPlayer].reduceTenbou(m_agariInfo.m_score.m_oyaTsumo + (m_honba * 100));
						} else {
							m_players[iPlayer].reduceTenbou(m_agariInfo.m_score.m_koTsumo + (m_honba * 100));
						}
					}
				}

				activePlayer.increaseTenbou(score);
				m_agariInfo.m_agariScore = score - (m_honba * 300);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(m_reachbou * 1000);

				// UI�C�x���g�i�c��������j�𔭍s����B
				m_view.event(retEid, m_kazeFrom, m_kazeTo);

				// �e���X�V����B
				if (m_iOya != m_kazeToPlayerIdx[m_kazeFrom]) {
					m_iOya++;
					if (m_iOya >= m_players.length) {
						m_iOya = 0;
					}
				} else {
					m_renchan = true;
					m_honba++;
				}

				break KYOKU_MAIN;
			case RON_AGARI:// ����
				if (m_iOya == m_kazeToPlayerIdx[m_kazeTo]) {
					score = m_agariInfo.m_score.m_oyaRon + (m_honba * 300);
				} else {
					score = m_agariInfo.m_score.m_koRon + (m_honba * 300);
				}

				m_players[m_kazeToPlayerIdx[m_kazeFrom]].increaseTenbou(score);
				m_players[m_kazeToPlayerIdx[m_kazeTo]].reduceTenbou(score);

				m_agariInfo.m_agariScore = score - (m_honba * 300);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(m_reachbou * 1000);

				// UI�C�x���g�i�����j�𔭍s����B
				m_view.event(retEid, m_kazeFrom, m_kazeTo);

				// �e���X�V����B
				if (m_iOya != m_kazeToPlayerIdx[m_kazeFrom]) {
					m_iOya++;
					if (m_iOya >= m_players.length) {
						m_iOya = 0;
					}
				} else {
					m_renchan = true;
					m_honba++;
				}

				break KYOKU_MAIN;
			default:
				break;
			}

			// �C�x���g�𔭍s���������X�V����B
			m_kazeFrom++;
			if (m_kazeFrom >= m_players.length) {
				m_kazeFrom = 0;
			}
		}
	}

	/**
	 * �v���C���[�̎�����ݒ肷��B
	 */
	private void setJikaze() {
		for (int i = 0, j = m_iOya; i < m_players.length; i++, j++) {
			if (j >= m_players.length) {
				j = 0;
			}

			// �v���C���[�̎�����ݒ肷��B
			m_players[j].setJikaze(i);

			// �����v���C���[�C���f�b�N�X�ɕϊ�����z���ݒ肷��B
			m_kazeToPlayerIdx[i] = j;
		}
	}

	/**
	 * �R�Ɋ���ڂ�ݒ肷��B
	 *
	 * @param sais
	 *            �T�C�R���̔z��
	 */
	void setWareme(Sai[] sais) {
		int sum = sais[0].getNo() + sais[1].getNo() - 1;

		mWareme = sum % 4;

		int startHaisIdx = ((sum % 4) * 36) + sum;

		m_yama.setTsumoHaisStartIndex(startHaisIdx);
	}

	/**
	 * �z�v����B
	 */
	private void haipai() {
		for (int i = 0, j = m_iOya, max = m_players.length * 13; i < max; i++, j++) {
			if (j >= m_players.length) {
				j = 0;
			}

			m_players[j].getTehai().addJyunTehai(m_yama.tsumo());
		}

		//if (true) {
		if (false) {
			while (m_players[0].getTehai().getJyunTehaiLength() > 0) {
				m_players[0].getTehai().rmJyunTehai(0);
			}
			int haiIds[] = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4};
			//int haiIds[] = {0, 0, 0, 9, 9, 9, 18, 18, 18, 27, 27, 29, 28, 28};
			//int haiIds[] = {0, 0, 0, 9, 9, 9, 18, 18, 18, 27, 27, 28, 28, 28};
			//int haiIds[] = {0, 0, 0, 9, 9, 9, 18, 18, 18, 5, 6, 7, 27, 27};
			//int haiIds[] = {0, 0, 0, 2, 2, 2, 3, 3, 3, 5, 6, 7, 27, 27};
			//int haiIds[] = {0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4, 10, 10};
			//int haiIds[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 10}; // �C�b�c�[
			//int haiIds[] = {0, 1, 2, 9, 10, 11, 18, 19, 20, 33, 33, 33, 27, 27};
			//int haiIds[] = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4}; // ���[�`�^���s���C�[�y�[�R�[Tehai tehai = new Tehai();
			//int haiIds[] = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7}; // ���[�`�^���s���C�[�y�[�R�[Tehai tehai = new Tehai();
			//int haiIds[] = {1, 1, 2, 2, 3, 3, 4, 5, 6, 10, 10, 10, 11, 12}; // ���[�`�^���s���C�[�y�[�R�[Tehai tehai = new Tehai();
			for (int i = 0; i < haiIds.length - 1; i++) {
				m_players[0].getTehai().addJyunTehai(new Hai(haiIds[i]));
			}
			//m_players[0].getTehai().rmJyunTehai(0);
			//m_players[0].getTehai().setPon(new Hai(0), getRelation(this.m_kazeFrom, this.m_kazeTo));
		}
	}

	boolean m_isTsumo = false;
	boolean m_isRinshan = false;
	boolean m_isLast = false;

	/**
	 * �C�x���g�i�c���j�𔭍s����B
	 *
	 * @return �C�x���gID
	 */
	private EventId tsumoEvent() {
		// �A�N�e�B�u�v���C���[��ݒ肷��B
		activePlayer = m_players[m_kazeToPlayerIdx[m_kazeFrom]];

		//m_tsumoHai = new Hai(0);
		m_isTsumo = true;

		// UI�C�x���g�i�c���j�𔭍s����B
		m_view.event(EventId.TSUMO, m_kazeFrom, m_kazeFrom);

		// �C�x���g�i�c���j�𔭍s����B
		EventId retEid = activePlayer.getEventIf().event(EventId.TSUMO, m_kazeFrom, m_kazeFrom);

		m_isTsumo = false;

		// UI�C�x���g�i�i�s�҂��j�𔭍s����B
		m_view.event(EventId.UI_WAIT_PROGRESS, m_kazeFrom, m_kazeFrom);

		int sutehaiIdx;
		Hai[] kanHais;

		if (retEid != EventId.REACH) {
			activePlayer.setIppatsu(false);
		}

		// �C�x���g����������B
		switch (retEid) {
		case ANKAN:
			activePlayer.getTehai().addJyunTehai(m_tsumoHai);
			sutehaiIdx = activePlayer.getEventIf().getISutehai();
			kanHais = m_playerAction.getKanHais();
			activePlayer.getTehai().setAnKan(kanHais[sutehaiIdx], getRelation(this.m_kazeFrom, this.m_kazeTo));

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EventId.ANKAN, m_kazeFrom, m_kazeFrom);

			// UI�C�x���g�i�i�s�҂��j�𔭍s����B
			m_view.event(EventId.UI_WAIT_PROGRESS, KAZE_NONE, KAZE_NONE);

			// �c���v���擾����B
			m_tsumoHai = m_yama.rinshanTsumo();

			// �C�x���g�i�c���j�𔭍s����B
			m_isRinshan = true;
			retEid = tsumoEvent();
			m_isRinshan = false;
			break;
		case TSUMO_AGARI:// �c��������
			break;
		case SUTEHAI:// �̔v
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getISutehai();

			// ���v�̊Ԃ��Ƃ�B
			m_infoUi.setSutehaiIdx(sutehaiIdx);
			m_view.event(EventId.UI_WAIT_RIHAI, m_kazeFrom, m_kazeFrom);

			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(m_suteHai, m_tsumoHai);
				activePlayer.getKawa().add(m_suteHai);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(m_tsumoHai);
				activePlayer.getKawa().add(m_suteHai);
				activePlayer.getKawa().setTedashi(true);
			}

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EventId.SUTEHAI, m_kazeFrom, m_kazeFrom);
			break;
		case REACH:
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getISutehai();
			activePlayer.setReach(true);
			m_view.event(EventId.UI_WAIT_RIHAI, m_kazeFrom, m_kazeFrom);

			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(m_suteHai, m_tsumoHai);
				activePlayer.getKawa().add(m_suteHai);
				activePlayer.getKawa().setReach(true);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(m_tsumoHai);
				activePlayer.getKawa().add(m_suteHai);
				activePlayer.getKawa().setTedashi(true);
				activePlayer.getKawa().setReach(true);
			}

			activePlayer.reduceTenbou(1000);
			activePlayer.setReach(true);
			m_reachbou++;

			activePlayer.setIppatsu(true);

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EventId.REACH, m_kazeFrom, m_kazeFrom);
			break;
		default:
			break;
		}

		return retEid;
	}

	/**
	 * �C�x���g��ʒm����B
	 *
	 * @param eid
	 *            �C�x���gID
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑ΏۂƂȂ�����
	 * @return �C�x���gID
	 */
	private EventId notifyEvent(EventId eid, int fromKaze, int toKaze) {
		EventId retEid = EventId.NAGASHI;
		int iSuteHai;

		// UI�C�x���g�𔭍s����B
		m_view.event(eid, fromKaze, toKaze);

		// �e�v���C���[�ɃC�x���g��ʒm����B
		NOTIFYLOOP: for (int i = 0, j = fromKaze; i < m_players.length; i++, j++) {
			if (j >= m_players.length) {
				j = 0;
			}

			// �A�N�e�B�u�v���C���[��ݒ肷��B
			activePlayer = m_players[m_kazeToPlayerIdx[j]];

			// UI�C�x���g�𔭍s����B
			//mView.event(eid, fromKaze, toKaze);

			// �C�x���g�𔭍s����B
			toKaze = j;
			retEid = activePlayer.getEventIf().event(eid, fromKaze, toKaze);

			if (retEid != EventId.NAGASHI) {
				for (int k = 0; k < 4; k++) {
					m_players[k].setIppatsu(false);
				}
			}

			// �C�x���g����������B
			switch (retEid) {
			case TSUMO_AGARI:// �c��������
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = toKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				break NOTIFYLOOP;
			case RON_AGARI:// ����
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = toKaze;
				this.m_kazeTo = fromKaze;
//				this.m_kazeFrom = j;
//				this.m_kazeTo = toKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				break NOTIFYLOOP;
			case PON:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				activePlayer.getTehai().setPon(m_suteHai, getRelation(this.m_kazeFrom, this.m_kazeTo));
				m_players[m_kazeToPlayerIdx[this.m_kazeTo]].getKawa().setNaki(true);

				notifyEvent(EventId.SELECT_SUTEHAI, this.m_kazeFrom, this.m_kazeTo);

				// �̔v�̃C���f�b�N�X���擾����B
				iSuteHai = activePlayer.getEventIf().getISutehai();
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, iSuteHai);
				activePlayer.getTehai().rmJyunTehai(iSuteHai);
				activePlayer.getKawa().add(m_suteHai);
				//activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.PON, this.m_kazeFrom, this.m_kazeTo);
				break NOTIFYLOOP;
			case CHII_LEFT:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				activePlayer.getTehai().setChiiLeft(m_suteHai, getRelation(this.m_kazeFrom, this.m_kazeTo));
				m_players[m_kazeToPlayerIdx[this.m_kazeTo]].getKawa().setNaki(true);

				notifyEvent(EventId.SELECT_SUTEHAI, this.m_kazeFrom, this.m_kazeTo);

				// �̔v�̃C���f�b�N�X���擾����B
				iSuteHai = activePlayer.getEventIf().getISutehai();
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, iSuteHai);
				activePlayer.getTehai().rmJyunTehai(iSuteHai);
				activePlayer.getKawa().add(m_suteHai);
				//activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.CHII_LEFT, this.m_kazeFrom, this.m_kazeTo);
				break;
			case CHII_CENTER:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				activePlayer.getTehai().setChiiCenter(m_suteHai, getRelation(this.m_kazeFrom, this.m_kazeTo));
				m_players[m_kazeToPlayerIdx[this.m_kazeTo]].getKawa().setNaki(true);

				notifyEvent(EventId.SELECT_SUTEHAI, this.m_kazeFrom, this.m_kazeTo);

				// �̔v�̃C���f�b�N�X���擾����B
				iSuteHai = activePlayer.getEventIf().getISutehai();
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, iSuteHai);
				activePlayer.getTehai().rmJyunTehai(iSuteHai);
				activePlayer.getKawa().add(m_suteHai);
				//activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.CHII_CENTER, this.m_kazeFrom, this.m_kazeTo);
				break;
			case CHII_RIGHT:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				activePlayer.getTehai().setChiiRight(m_suteHai, getRelation(this.m_kazeFrom, this.m_kazeTo));
				m_players[m_kazeToPlayerIdx[this.m_kazeTo]].getKawa().setNaki(true);

				notifyEvent(EventId.SELECT_SUTEHAI, this.m_kazeFrom, this.m_kazeTo);

				// �̔v�̃C���f�b�N�X���擾����B
				iSuteHai = activePlayer.getEventIf().getISutehai();
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, iSuteHai);
				activePlayer.getTehai().rmJyunTehai(iSuteHai);
				activePlayer.getKawa().add(m_suteHai);
				//activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.CHII_RIGHT, this.m_kazeFrom, this.m_kazeTo);
				break;
			case DAIMINKAN:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.m_kazeFrom = j;
				this.m_kazeTo = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.m_kazeFrom]];
				activePlayer.getTehai().setDaiMinKan(m_suteHai, getRelation(this.m_kazeFrom, this.m_kazeTo));
				m_players[m_kazeToPlayerIdx[this.m_kazeTo]].getKawa().setNaki(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.DAIMINKAN, this.m_kazeFrom, this.m_kazeTo);

				// UI�C�x���g�i�i�s�҂��j�𔭍s����B
				m_view.event(EventId.UI_WAIT_PROGRESS, KAZE_NONE, KAZE_NONE);

				// �c���v���擾����B
				m_tsumoHai = m_yama.rinshanTsumo();

				// �C�x���g�i�c���j�𔭍s����B
				m_isRinshan = true;
				retEid = tsumoEvent();
				m_isRinshan = false;
				break;
			default:
				break;
			}

			if (eid == EventId.SELECT_SUTEHAI) {
				return retEid;
			}
		}

		return retEid;
	}

	/*
	 * Info, InfoUI�ɒ񋟂���API���`����B
	 */

	/**
	 * �\�h���A�ȃh���̔z����擾����B
	 *
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getDoras() {
		return getYama().getOmoteDoraHais();
	}

	/**
	 * �\�h���A�ȃh���̔z����擾����B
	 *
	 * @return �\�h���A�ȃh���̔z��
	 */
	Hai[] getUraDoras() {
		return getYama().getUraDoraHais();
	}

	/**
	 * �������擾����B
	 */
	int getJikaze() {
		return activePlayer.getJikaze();
	}

	/**
	 * �{����擾����B
	 *
	 * @return �{��
	 */
	int getHonba() {
		return m_honba;
	}

	/**
	 * ���[�`���擾����B
	 *
	 * @param kaze
	 *            ��
	 * @return ���[�`
	 */
	boolean isReach(int kaze) {
		return m_players[m_kazeToPlayerIdx[kaze]].isReach();
	}

	/**
	 * ��v���R�s�[����B
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
			Tehai.copy(tehai, m_players[m_kazeToPlayerIdx[kaze]].getTehai(), false);
		}
	}

	/**
	 * ��v���R�s�[����B
	 *
	 * @param tehai
	 *            ��v
	 * @param kaze
	 *            ��
	 */
	void copyTehaiUi(Tehai tehai, int kaze) {
		Tehai.copy(tehai, m_players[m_kazeToPlayerIdx[kaze]].getTehai(), true);
	}

	/**
	 * �͂��R�s�[����B
	 *
	 * @param kawa
	 *            ��
	 * @param kaze
	 *            ��
	 */
	void copyKawa(Kawa kawa, int kaze) {
		Kawa.copy(kawa, m_players[m_kazeToPlayerIdx[kaze]].getKawa());
	}

	/**
	 * �c���̎c�萔���擾����B
	 *
	 * @return �c���̎c�萔
	 */
	int getTsumoRemain() {
		return m_yama.getTsumoNokori();
	}

	String getName(int kaze) {
		return m_players[m_kazeToPlayerIdx[kaze]].getEventIf().getName();
	}

	int getTenbou(int kaze) {
		return m_players[m_kazeToPlayerIdx[kaze]].getTenbou();
	}

	private Combi[] combis = new Combi[10];
	{
		for (int i = 0; i < combis.length; i++)
			combis[i] = new Combi();
	}

	AgariScore m_score;
	AgariInfo m_agariInfo = new AgariInfo();

	public AgariInfo getAgariInfo() {
		return m_agariInfo;
	}

	public int getAgariScore(Tehai tehai, Hai addHai) {
		AgariSetting setting = new AgariSetting(this);
		if (activePlayer.isReach()) {
			setting.setYakuflg(YakuflgName.REACH.ordinal(), true);
		}
		if (m_isTsumo) {
			setting.setYakuflg(YakuflgName.TUMO.ordinal(), true);
		}
		if (m_isTsumo && m_isRinshan) {
			setting.setYakuflg(YakuflgName.RINSYAN.ordinal(), true);
		}
		if (m_isLast) {
			if (m_isTsumo) {
				setting.setYakuflg(YakuflgName.HAITEI.ordinal(), true);
			} else {
				setting.setYakuflg(YakuflgName.HOUTEI.ordinal(), true);
			}
		}
		if (activePlayer.isIppatsu()) {
			setting.setYakuflg(YakuflgName.IPPATU.ordinal(), true);
		}
		m_score = new AgariScore();
		int score = m_score.getAgariScore(tehai, addHai, combis, setting, m_agariInfo);
		return score;
	}

	public String[] getYakuName(Tehai tehai, Hai addHai){
		AgariSetting setting = new AgariSetting(this);
		AgariScore score = new AgariScore();
		return score.getYakuName(tehai, addHai, combis, setting);
	}

	@Override
	public void run() {
		// �Q�[�����J�n����B
		play();
	}

	public void setSutehaiIdx(int sutehaiIdx) {
		m_info.setSutehaiIdx(sutehaiIdx);
	}

	public void postUiEvent(EventId a_eventId, int a_kazeFrom, int a_kazeTo) {
		m_view.event(a_eventId, a_kazeFrom, a_kazeTo);
	}
}
