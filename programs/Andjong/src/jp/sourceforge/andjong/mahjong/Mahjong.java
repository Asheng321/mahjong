package jp.sourceforge.andjong.mahjong;

import jp.sourceforge.andjong.AndjongView;
import jp.sourceforge.andjong.mahjong.AgariScore;
import jp.sourceforge.andjong.mahjong.CountFormat.Combi;
import jp.sourceforge.andjong.mahjong.EventIF.EID;

/**
 * �Q�[�����Ǘ�����N���X�ł��B
 *
 * @author Yuji Urushibara
 *
 */
public class Mahjong implements Runnable {
	/** AndjongView */
	private AndjongView mView;

	/** �R */
	private Yama mYama;

	/** ����� */
	public final static int KYOKU_TON_1 = 0;
	/** ����� */
	public final static int KYOKU_TON_2 = 1;
	/** ���O�� */
	public final static int KYOKU_TON_3 = 2;
	/** ���l�� */
	public final static int KYOKU_TON_4 = 3;

	/** �� */
	private int mKyoku;

	/** �ǂ̍ő�l */
	private int mKyokuEnd;

	/** �c���v */
	private Hai mTsumoHai;

	/** �̔v */
	private Hai mSuteHai;

	/** ���[�`�_�̐� */
	private int mReachbou;

	/** �{�� */
	private int mHonba;

	/** �v���C���[�̐l�� */
	private int mPlayerNum;

	/** �v���C���[�ɒ񋟂����� */
	private Info mInfo;

	/** �v���C���[�̔z�� */
	private Player[] mPlayers;

	/** �����v���C���[�C���f�b�N�X�ɕϊ�����z�� */
	private int[] mKazeToPlayerIdx = new int[4];

	/** UI�ɒ񋟂����� */
	private InfoUI mInfoUi;

	/** �T�C�R���̔z�� */
	private Sai[] mSais = new Sai[] { new Sai(), new Sai() };

	/** �e�̃v���C���[�C���f�b�N�X */
	private int mOyaIdx;

	/** �N�Ƃ̃v���C���[�C���f�b�N�X */
	private int mChiichaIdx;

	/** �A�� */
	private boolean mRenchan;

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
		this.mView = view;
	}

	/**
	 * �R���擾����B
	 *
	 * @return �R
	 */
	Yama getYama() {
		return mYama;
	}

	/**
	 * �ǂ��擾����B
	 *
	 * @return ��
	 */
	int getkyoku() {
		return mKyoku;
	}

	/**
	 * �c���v���擾����B
	 *
	 * @return �c���v
	 */
	Hai getTsumoHai() {
		return mTsumoHai;
	}

	/**
	 * �̔v���擾����B
	 *
	 * @return �̔v
	 */
	Hai getSuteHai() {
		return mSuteHai;
	}

	public int getReachbou() {
		return mReachbou;
	}

	public void setReachbou(int reachbou) {
		mReachbou = reachbou;
	}

	/**
	 * �N�Ƃ̃v���C���[�C���f�b�N�X���擾����B
	 *
	 * @return �N�Ƃ̃v���C���[�C���f�b�N�X
	 */
	public int getChiichaIdx() {
		return mChiichaIdx;
	}

	/**
	 * �T�C�R���̔z����擾����B
	 *
	 * @return �T�C�R���̔z��
	 */
	Sai[] getSais() {
		return mSais;
	}

	public final static int KAZE_TON = 0;
	public final static int KAZE_NAN = 1;
	public final static int KAZE_SHA = 2;
	public final static int KAZE_PE = 3;
	public final static int KAZE_COUNT_MAX = KAZE_PE + 1;
	public final static int KAZE_NONE = 4;

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

	private PlayerAction mPlayerAction = new PlayerAction();

	public int getManKaze() {
		return mPlayers[0].getJikaze();
	}

	/**
	 * �Q�[�����J�n����B
	 */
	public void play() {
		// ����������B
		init();

		// �ꏊ�����߂�B
		// TODO �������B�Œ�Ƃ���B

		// �C�x���g�i�ꏊ���߁j�𔭍s����B
		mView.event(EID.BASHOGIME, KAZE_NONE, KAZE_NONE);

		// �e�����߂�B
		// TODO �v���C���[�ɃT�C�R����U�点��@�\�͖������B
		mSais[0].saifuri();
		mSais[1].saifuri();
		mOyaIdx = (mSais[0].getNo() + mSais[1].getNo() - 1) % 4;
		mChiichaIdx = mOyaIdx;

		// �C�x���g�i�e���߁j�𔭍s����B
		mView.event(EID.OYAGIME, KAZE_NONE, KAZE_NONE);

		// �ǂ��J��Ԃ��āA�Q�[����i�s����B
		while (mKyoku <= mKyokuEnd) {
			// �ǂ��J�n����B
			startKyoku();

			// �A���̏ꍇ�A�ǂ�i�߂Ȃ��B
			if (mRenchan) {
				// �C�x���g�i�A���j�𔭍s����B
				mView.event(EID.RENCHAN, KAZE_NONE, KAZE_NONE);
				continue;
			}

			// �ǂ�i�߂�B
			mKyoku++;

			// �{�������������B
			// TODO �オ��̈ʒu�Ɉړ����Ȃ��ƁB
			mHonba = 0;
		}

		// �C�x���g�i�Q�[���̏I���j�𔭍s����B
		mView.event(EID.END, KAZE_NONE, KAZE_NONE);
	}

	/**
	 * ����������B
	 */
	private void init() {
		// �R���쐬����B
		mYama = new Yama();

		// �ǂ�����������B
		mKyoku = KYOKU_TON_1;

		// �ǂ̏I����ݒ肷��B
		mKyokuEnd = KYOKU_TON_4;

		// �c���v���쐬����B
		mTsumoHai = new Hai();

		// �̔v���쐬����B
		mSuteHai = new Hai();

		// ���[�`�_�̐�������������B
		mReachbou = 0;

		// �{�������������B
		mHonba = 0;

		// �v���C���[�̐l����ݒ肷��B
		mPlayerNum = 4;

		// �v���C���[�ɒ񋟂�������쐬����B
		mInfo = new Info(this);

		// �v���C���[�̔z�������������B
		mPlayers = new Player[mPlayerNum];
		mPlayers[0] = new Player((EventIF) new Man(mInfo, "A", mPlayerAction));
		mPlayers[1] = new Player((EventIF) new AI(mInfo, "B"));
		mPlayers[2] = new Player((EventIF) new AI(mInfo, "C"));
		mPlayers[3] = new Player((EventIF) new AI(mInfo, "D"));

		for (int i = 0; i < mPlayerNum; i++) {
			mPlayers[i].setTenbou(TENBOU_INIT);
		}

		// �����v���C���[�C���f�b�N�X�ɕϊ�����z�������������B
		mKazeToPlayerIdx = new int[mPlayers.length];

		// UI�ɒ񋟂�������쐬����B
		mInfoUi = new InfoUI(this, mPlayerAction);

		// UI������������B
		mView.initUi(mInfoUi, "AndjongView");
	}

	/**
	 * �ǂ��J�n����B
	 */
	private void startKyoku() {
		// ���[�`�_�̐�������������B
		// TODO �オ��̈ʒu�Ɉړ����Ȃ��ƁB
		mReachbou = 0;

		// �A��������������B
		mRenchan = false;

		// �C�x���g�𔭍s������������������B
		mFromKaze = mPlayers[mOyaIdx].getJikaze();

		// �C�x���g�̑ΏۂƂȂ�����������������B
		mToKaze = mPlayers[mOyaIdx].getJikaze();

		// �v���C���[�̎�����ݒ肷��B
		setJikaze();

		// �v���C���[�z�������������B
		for (int i = 0; i < mPlayers.length; i++) {
			mPlayers[i].init();
		}

		// ���v����B
		mYama.xipai();

		// UI�C�x���g�i���v�j�𔭍s����B
		mView.event(EID.SENPAI, mFromKaze, mToKaze);

		// �T�C�U�������B
		mSais[0].saifuri();
		mSais[1].saifuri();

		// UI�C�x���g�i�T�C�U��j�𔭍s����B
		mView.event(EID.SAIFURI, mFromKaze, mToKaze);

		// �R�Ɋ���ڂ�ݒ肷��B
		setWareme(mSais);

		// �z�v����B
		haipai();

		// UI�C�x���g�i�z�v�j�𔭍s����B
		mView.event(EID.HAIPAI, mFromKaze, mToKaze);

		EID retEid;

		// �ǂ�i�s����B
		KYOKU_MAIN: while (true) {
			// UI�C�x���g�i�i�s�҂��j�𔭍s����B
			mView.event(EID.PROGRESS_WAIT, KAZE_NONE, KAZE_NONE);

			// �c���v���擾����B
			mTsumoHai = mYama.tsumo();

			// �c���v���Ȃ��ꍇ�A���ǂ���B
			if (mTsumoHai == null) {
				// UI�C�x���g�i���ǁj�𔭍s����B
				mView.event(EID.RYUUKYOKU, KAZE_NONE, KAZE_NONE);

				// �e���X�V����B�オ��A���Ƃ���B
				mOyaIdx++;
				if (mOyaIdx >= mPlayers.length) {
					mOyaIdx = 0;
				}

				// �{��𑝂₷�B
				mHonba++;

				break KYOKU_MAIN;
			}

			// �C�x���g�i�c���j�𔭍s����B
			retEid = tsumoEvent();

			// �C�x���g����������B
			switch (retEid) {
			case TSUMOAGARI:// �c��������
				// UI�C�x���g�i�c��������j�𔭍s����B
				mView.event(retEid, mFromKaze, mToKaze);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(mReachbou * 1000);

				// �e���X�V����B
				if (mOyaIdx != mKazeToPlayerIdx[mFromKaze]) {
					mOyaIdx++;
					if (mOyaIdx >= mPlayers.length) {
						mOyaIdx = 0;
					}
				} else {
					mRenchan = true;
					mHonba++;
				}

				break KYOKU_MAIN;
			case RON:// ����
				// UI�C�x���g�i�����j�𔭍s����B
				mView.event(retEid, mFromKaze, mToKaze);

				// TODO �_���𐴎Z����B
				activePlayer.increaseTenbou(mReachbou * 1000);

				// �e���X�V����B
				if (mOyaIdx != mKazeToPlayerIdx[mFromKaze]) {
					mOyaIdx++;
					if (mOyaIdx >= mPlayers.length) {
						mOyaIdx = 0;
					}
				} else {
					mRenchan = true;
					mHonba++;
				}

				break KYOKU_MAIN;
			case REACH:// ���[�`
				int tenbou = activePlayer.getTenbou();
				if (tenbou >= 1000) {
					activePlayer.reduceTenbou(1000);
					activePlayer.setReach(true);
					mReachbou++;
				}
				break;
			default:
				break;
			}

			// �C�x���g�𔭍s���������X�V����B
			mFromKaze++;
			if (mFromKaze >= mPlayers.length) {
				mFromKaze = 0;
			}
		}
	}

	/**
	 * �v���C���[�̎�����ݒ肷��B
	 */
	private void setJikaze() {
		for (int i = 0, j = mOyaIdx; i < mPlayers.length; i++, j++) {
			if (j >= mPlayers.length) {
				j = 0;
			}

			// �v���C���[�̎�����ݒ肷��B
			mPlayers[j].setJikaze(i);

			// �����v���C���[�C���f�b�N�X�ɕϊ�����z���ݒ肷��B
			mKazeToPlayerIdx[i] = j;
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

		mYama.setTsumoHaisStartIdx(startHaisIdx);
	}

	/**
	 * �z�v����B
	 */
	private void haipai() {
		for (int i = 0, j = mOyaIdx, max = mPlayers.length * 13; i < max; i++, j++) {
			if (j >= mPlayers.length) {
				j = 0;
			}

			mPlayers[j].getTehai().addJyunTehai(mYama.tsumo());
		}
	}

	/**
	 * �C�x���g�i�c���j�𔭍s����B
	 *
	 * @return �C�x���gID
	 */
	private EID tsumoEvent() {
		// �A�N�e�B�u�v���C���[��ݒ肷��B
		activePlayer = mPlayers[mKazeToPlayerIdx[mFromKaze]];

		// UI�C�x���g�i�c���j�𔭍s����B
		mView.event(EID.TSUMO, mFromKaze, mFromKaze);

		// �C�x���g�i�c���j�𔭍s����B
		EID retEid = activePlayer.getEventIf().event(EID.TSUMO, mFromKaze, mFromKaze);

		// UI�C�x���g�i�i�s�҂��j�𔭍s����B
		mView.event(EID.PROGRESS_WAIT, mFromKaze, mFromKaze);

		int sutehaiIdx;

		// �C�x���g����������B
		switch (retEid) {
		case TSUMOAGARI:// �c��������
			break;
		case SUTEHAI:// �̔v
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();

			// ���v�̊Ԃ��Ƃ�B
			mInfoUi.setSutehaiIdx(sutehaiIdx);
			mView.event(EID.RIHAI_WAIT, mFromKaze, mFromKaze);

			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(mSuteHai, mTsumoHai);
				activePlayer.getKawa().add(mSuteHai);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIdx(mSuteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(mTsumoHai);
				activePlayer.getKawa().add(mSuteHai);
				activePlayer.getKawa().setTedashi(true);
			}

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EID.SUTEHAI, mFromKaze, mFromKaze);
			break;
		case REACH:
			// �̔v�̃C���f�b�N�X���擾����B
			sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();
			if (sutehaiIdx == 13) {// �c���؂�
				Hai.copy(mSuteHai, mTsumoHai);
				activePlayer.getKawa().add(mSuteHai);
				activePlayer.getKawa().setReach(true);
			} else {// ��o��
				activePlayer.getTehai().copyJyunTehaiIdx(mSuteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getTehai().addJyunTehai(mTsumoHai);
				activePlayer.getKawa().add(mSuteHai);
				activePlayer.getKawa().setTedashi(true);
				activePlayer.getKawa().setReach(true);
			}

			// �C�x���g��ʒm����B
			retEid = notifyEvent(EID.REACH, mFromKaze, mFromKaze);
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
	private EID notifyEvent(EID eid, int fromKaze, int toKaze) {
		EID retEid = EID.NAGASHI;

		// UI�C�x���g�𔭍s����B
		mView.event(eid, fromKaze, toKaze);

		// �e�v���C���[�ɃC�x���g��ʒm����B
		NOTIFYLOOP: for (int i = 0, j = fromKaze; i < mPlayers.length; i++, j++) {
			if (j >= mPlayers.length) {
				j = 0;
			}

			// �A�N�e�B�u�v���C���[��ݒ肷��B
			activePlayer = mPlayers[mKazeToPlayerIdx[j]];

			// UI�C�x���g�𔭍s����B
			//mView.event(eid, fromKaze, toKaze);

			// �C�x���g�𔭍s����B
			retEid = activePlayer.getEventIf().event(eid, fromKaze, toKaze);

			// �C�x���g����������B
			switch (retEid) {
			case TSUMOAGARI:// �c��������
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = toKaze;
				activePlayer = mPlayers[mKazeToPlayerIdx[this.mFromKaze]];
				break NOTIFYLOOP;
			case RON:// ����
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = toKaze;
				activePlayer = mPlayers[mKazeToPlayerIdx[this.mFromKaze]];
				break NOTIFYLOOP;
			case PON:
				// �A�N�e�B�u�v���C���[��ݒ肷��B
				this.mFromKaze = j;
				this.mToKaze = fromKaze;
				activePlayer = mPlayers[mKazeToPlayerIdx[this.mFromKaze]];
				activePlayer.getTehai().setPon(mSuteHai, getRelation(this.mFromKaze, this.mToKaze));

				notifyEvent(EID.SUTEHAISELECT, this.mFromKaze, this.mToKaze);

				// �̔v�̃C���f�b�N�X���擾����B
				int sutehaiIdx = activePlayer.getEventIf().getSutehaiIdx();
				activePlayer.getTehai().copyJyunTehaiIdx(mSuteHai, sutehaiIdx);
				activePlayer.getTehai().rmJyunTehai(sutehaiIdx);
				activePlayer.getKawa().add(mSuteHai);
				activePlayer.getKawa().setNaki(true);
				activePlayer.getKawa().setTedashi(true);

				// �C�x���g��ʒm����B
				retEid = notifyEvent(EID.PON, this.mFromKaze, this.mToKaze);
				break NOTIFYLOOP;
			default:
				break;
			}

			if (eid == EID.SUTEHAISELECT) {
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
		return mHonba;
	}

	/**
	 * ���[�`���擾����B
	 *
	 * @param kaze
	 *            ��
	 * @return ���[�`
	 */
	boolean isReach(int kaze) {
		return mPlayers[mKazeToPlayerIdx[kaze]].isReach();
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
			Tehai.copy(tehai, mPlayers[mKazeToPlayerIdx[kaze]].getTehai(), false);
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
		Tehai.copy(tehai, mPlayers[mKazeToPlayerIdx[kaze]].getTehai(), true);
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
		Kawa.copy(kawa, mPlayers[mKazeToPlayerIdx[kaze]].getKawa());
	}

	/**
	 * �c���̎c�萔���擾����B
	 *
	 * @return �c���̎c�萔
	 */
	int getTsumoRemain() {
		return mYama.getTsumoNokori();
	}

	String getName(int kaze) {
		return mPlayers[mKazeToPlayerIdx[kaze]].getEventIf().getName();
	}

	int getTenbou(int kaze) {
		return mPlayers[mKazeToPlayerIdx[kaze]].getTenbou();
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
		mInfo.setSutehaiIdx(sutehaiIdx);
	}
}
