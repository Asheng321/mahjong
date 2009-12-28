package jp.sourceforge.andjong.mahjong;

import jp.sourceforge.andjong.AndjongView;
import jp.sourceforge.andjong.mahjong.AgariScore;
import jp.sourceforge.andjong.mahjong.CountFormat.Combi;
import static jp.sourceforge.andjong.mahjong.EventIf.*;

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
	private int mFromKaze;

	/** �C�x���g�̑ΏۂƂȂ����� */
	private int mToKaze;

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

	/**
	 * �ǂ��J�n����B
	 */
	private void startKyoku() {
		// ���[�`�_�̐�������������B
		// TODO �オ��̈ʒu�Ɉړ����Ȃ��ƁB
		m_reachbou = 0;

		// �A��������������B
		m_renchan = false;

		// �C�x���g�𔭍s������������������B
		mFromKaze = m_players[m_iOya].getJikaze();

		// �C�x���g�̑ΏۂƂȂ�����������������B
		mToKaze = m_players[m_iOya].getJikaze();

		// �v���C���[�̎�����ݒ肷��B
		setJikaze();

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

		if (false) {
			for (int i = 0; i < 13; i++)
				m_players[1].getTehai().rmJyunTehai(i);

			m_players[1].getTehai().addJyunTehai(new Hai(0));
			m_players[1].getTehai().addJyunTehai(new Hai(1));
			m_players[1].getTehai().addJyunTehai(new Hai(2));
			m_players[1].getTehai().addJyunTehai(new Hai(3));
			m_players[1].getTehai().addJyunTehai(new Hai(4));
			m_players[1].getTehai().addJyunTehai(new Hai(5));
			m_players[1].getTehai().addJyunTehai(new Hai(6));
			m_players[1].getTehai().addJyunTehai(new Hai(7));
			m_players[1].getTehai().addJyunTehai(new Hai(8));
			m_players[1].getTehai().addJyunTehai(new Hai(9));
			m_players[1].getTehai().addJyunTehai(new Hai(10));
			m_players[1].getTehai().addJyunTehai(new Hai(11));
			m_players[1].getTehai().addJyunTehai(new Hai(11));
			/*
			mPlayers[1].getTehai().addJyunTehai(new Hai(0));
			mPlayers[1].getTehai().addJyunTehai(new Hai(0));
			mPlayers[1].getTehai().addJyunTehai(new Hai(0));
			mPlayers[1].getTehai().addJyunTehai(new Hai(2));
			mPlayers[1].getTehai().addJyunTehai(new Hai(2));
			mPlayers[1].getTehai().addJyunTehai(new Hai(2));
			mPlayers[1].getTehai().addJyunTehai(new Hai(4));
			mPlayers[1].getTehai().addJyunTehai(new Hai(4));
			mPlayers[1].getTehai().addJyunTehai(new Hai(4));
			mPlayers[1].getTehai().addJyunTehai(new Hai(6));
			mPlayers[1].getTehai().addJyunTehai(new Hai(6));
			mPlayers[1].getTehai().addJyunTehai(new Hai(6));
			mPlayers[1].getTehai().addJyunTehai(new Hai(8));
			*/
		}

		// UI�C�x���g�i�z�v�j�𔭍s����B
		//m_view.event(EventId.HAIPAI, mFromKaze, mToKaze);
		m_view.event(EventId.START_KYOKU, mFromKaze, mToKaze);

		EventId retEid;

		// �ǂ�i�s����B
		KYOKU_MAIN: while (true) {
			// UI�C�x���g�i�i�s�҂��j�𔭍s����B
			m_view.event(EventId.UI_WAIT_PROGRESS, KAZE_NONE, KAZE_NONE);

			// �c���v���擾����B
			m_tsumoHai = m_yama.tsumo();

			// �c���v���Ȃ��ꍇ�A���ǂ���B
			if (m_tsumoHai == null) {
				// UI�C�x���g�i���ǁj�𔭍s����B
				m_view.event(EventId.RYUUKYOKU, KAZE_NONE, KAZE_NONE);

				// �e���X�V����B�オ��A���Ƃ���B
				m_iOya++;
				if (m_iOya >= m_players.length) {
					m_iOya = 0;
				}

				// �{��𑝂₷�B
				m_honba++;

				break KYOKU_MAIN;
			}

			// �C�x���g�i�c���j�𔭍s����B
			retEid = tsumoEvent();

			// �C�x���g����������B
			switch (retEid) {
			case TSUMO_AGARI:// �c��������
				// UI�C�x���g�i�c��������j�𔭍s����B
				m_view.event(retEid, mFromKaze, mToKaze);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(m_reachbou * 1000);

				// �e���X�V����B
				if (m_iOya != m_kazeToPlayerIdx[mFromKaze]) {
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
				// UI�C�x���g�i�����j�𔭍s����B
				m_view.event(retEid, mFromKaze, mToKaze);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(m_reachbou * 1000);

				// �e���X�V����B
				if (m_iOya != m_kazeToPlayerIdx[mFromKaze]) {
					m_iOya++;
					if (m_iOya >= m_players.length) {
						m_iOya = 0;
					}
				} else {
					m_renchan = true;
					m_honba++;
				}

				break KYOKU_MAIN;
			case REACH:// ���[�`
				int tenbou = activePlayer.getTenbou();
				if (tenbou >= 1000) {
					activePlayer.reduceTenbou(1000);
					activePlayer.setReach(true);
					m_reachbou++;
				}
				break;
			default:
				break;
			}

			// �C�x���g�𔭍s���������X�V����B
			mFromKaze++;
			if (mFromKaze >= m_players.length) {
				mFromKaze = 0;
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
	}

	/**
	 * �C�x���g�i�c���j�𔭍s����B
	 *
	 * @return �C�x���gID
	 */
	private EventId tsumoEvent() {
		// �A�N�e�B�u�v���C���[��ݒ肷��B
		activePlayer = m_players[m_kazeToPlayerIdx[mFromKaze]];

		// UI�C�x���g�i�c���j�𔭍s����B
		m_view.event(EventId.TSUMO, mFromKaze, mFromKaze);

		// �C�x���g�i�c���j�𔭍s����B
		EventId retEid = activePlayer.getEventIf().event(EventId.TSUMO, mFromKaze, mFromKaze);

		// UI�C�x���g�i�i�s�҂��j�𔭍s����B
		m_view.event(EventId.UI_WAIT_PROGRESS, mFromKaze, mFromKaze);

		int sutehaiIdx;

		// �C�x���g����������B
		switch (retEid) {
		case TSUMO_AGARI:// �c��������
			break;
		case SUTEHAI:// �̔v
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getISutehai();

			// ���v�̊Ԃ��Ƃ�B
			m_infoUi.setSutehaiIdx(sutehaiIdx);
			m_view.event(EventId.UI_WAIT_RIHAI, mFromKaze, mFromKaze);

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
			retEid = notifyEvent(EventId.SUTEHAI, mFromKaze, mFromKaze);
			break;
		case REACH:
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getISutehai();
			activePlayer.setReach(true);
			m_view.event(EventId.UI_WAIT_RIHAI, mFromKaze, mFromKaze);

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

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EventId.REACH, mFromKaze, mFromKaze);
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
			retEid = activePlayer.getEventIf().event(eid, fromKaze, toKaze);

			// �C�x���g����������B
			switch (retEid) {
			case TSUMO_AGARI:// �c��������
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = toKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.mFromKaze]];
				break NOTIFYLOOP;
			case RON_AGARI:// ����
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = toKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.mFromKaze]];
				break NOTIFYLOOP;
			case PON:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = fromKaze;
				activePlayer = m_players[m_kazeToPlayerIdx[this.mFromKaze]];
				activePlayer.getTehai().setPon(m_suteHai, getRelation(this.mFromKaze, this.mToKaze));

				notifyEvent(EventId.SELECT_SUTEHAI, this.mFromKaze, this.mToKaze);

				// �̔v�̃C���f�b�N�X���擾����B
				int sutehaiIdx = activePlayer.getEventIf().getISutehai();
				activePlayer.getTehai().copyJyunTehaiIndex(m_suteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getKawa().add(m_suteHai);
				activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EventId.PON, this.mFromKaze, this.mToKaze);
				break NOTIFYLOOP;
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

	@Override
	public void run() {
		// �Q�[�����J�n����B
		play();
	}

	public void setSutehaiIdx(int sutehaiIdx) {
		m_info.setSutehaiIdx(sutehaiIdx);
	}
}
