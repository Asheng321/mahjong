package jp.sourceforge.andjong;

/**
 * ��v���Ǘ�����B
 *
 * @author Yuji Urushibara
 *
 */
public class Tehai {
	/** ����v�̍ő�l */
	public final static int JYUNTEHAI_MAX = 14;

	/** ����v */
	private Hai[] jyunTehai = new Hai[JYUNTEHAI_MAX];

	/** ����v�̒��� */
	private int jyunTehaiLength;

	/** ���I�̍ő�l */
	public final static int FUURO_MAX = 4;

	/** �ʎq�̒���(3) */
	public final static int MENTSU_LENGTH_3 = 3;

	/** �ʎq�̒���(4) */
	public final static int MENTSU_LENGTH_4 = 4;

	/** �����̔z�� */
	private Hai[][] minShuns = new Hai[FUURO_MAX][MENTSU_LENGTH_3];

	/** �����̔z��̒��� */
	private int minShunsLength;

	/** �����̔z�� */
	private Hai[][] minKous = new Hai[FUURO_MAX][MENTSU_LENGTH_3];

	/** �����̔z��̒��� */
	private int minKousLength;

	/** ���Ȃ̔z�� */
	private Hai[][] minKans = new Hai[FUURO_MAX][MENTSU_LENGTH_4];

	/** ���Ȃ̔z��̒��� */
	private int minKansLength;

	/** �ÞȂ̔z�� */
	private Hai[][] anKans = new Hai[FUURO_MAX][MENTSU_LENGTH_4];

	/** �ÞȂ̔z��̒��� */
	private int anKansLength;

	/**
	 * ��v���쐬����B
	 */
	public Tehai() {
		initialize();

		for (int i = 0; i < JYUNTEHAI_MAX; i++) {
			jyunTehai[i] = new Hai();
		}

		for (int i = 0; i < FUURO_MAX; i++) {
			for (int j = 0; j < MENTSU_LENGTH_3; j++) {
				minShuns[i][j] = new Hai();
				minKous[i][j] = new Hai();
			}

			for (int j = 0; j < MENTSU_LENGTH_4; j++) {
				minKans[i][j] = new Hai();
				anKans[i][j] = new Hai();
			}
		}
	}

	/**
	 * ��v������������B
	 */
	public void initialize() {
		jyunTehaiLength = 0;
		minShunsLength = 0;
		minKousLength = 0;
		minKansLength = 0;
		anKansLength = 0;
	}

	/**
	 * ��v���R�s�[����B
	 *
	 * @param destTehai
	 *            �R�s�[��̎�v
	 * @param srcTehai
	 *            �R�s�[���̎�v
	 * @param jyunTehaiCopy
	 *            ����v�̃R�s�[����
	 */
	public static void copy(Tehai destTehai, Tehai srcTehai,
			boolean jyunTehaiCopy) {
		if (jyunTehaiCopy == true) {
			destTehai.jyunTehaiLength = srcTehai.jyunTehaiLength;
			Tehai.copyJyunTehai(destTehai.jyunTehai, srcTehai.jyunTehai,
					destTehai.jyunTehaiLength);
		}

		destTehai.minShunsLength = srcTehai.minShunsLength;
		Tehai.copyMinShuns(destTehai.minShuns, srcTehai.minShuns,
				destTehai.minShunsLength);

		destTehai.minKousLength = srcTehai.minKousLength;
		Tehai.copyMinKous(destTehai.minKous, srcTehai.minKous,
				destTehai.minKousLength);

		destTehai.minKansLength = srcTehai.minKansLength;
		Tehai.copyMinKans(destTehai.minKans, srcTehai.minKans,
				destTehai.minKansLength);

		destTehai.anKansLength = srcTehai.anKansLength;
		Tehai.copyMinKans(destTehai.anKans, srcTehai.anKans,
				destTehai.anKansLength);
	}

	/**
	 * ����v���擾����B
	 *
	 * @return ����v
	 */
	public Hai[] getJyunTehai() {
		return jyunTehai;
	}

	/**
	 * ����v�̒������擾����B
	 *
	 * @return ����v�̒���
	 */
	public int getJyunTehaiLength() {
		return jyunTehaiLength;
	}

	/**
	 * ����v�ɔv��ǉ�����B
	 *
	 * @param hai
	 *            �ǉ�����v
	 * @return ����
	 */
	public boolean addJyunTehai(Hai hai) {
		if (jyunTehaiLength >= JYUNTEHAI_MAX) {
			return false;
		}

		int i;
		for (i = jyunTehaiLength; i > 0; i--) {
			if (jyunTehai[i - 1].getId() <= hai.getId()) {
				break;
			}

			Hai.copy(jyunTehai[i], jyunTehai[i - 1]);
		}

		Hai.copy(jyunTehai[i], hai);
		jyunTehaiLength++;

		return true;
	}

	/**
	 * ����v����w��ʒu�̔v���폜����B
	 *
	 * @param idx
	 *            �w��ʒu
	 * @return ����
	 */
	public boolean rmJyunTehai(int idx) {
		if (idx >= JYUNTEHAI_MAX) {
			return false;
		}

		for (int i = idx; i < jyunTehaiLength - 1; i++) {
			Hai.copy(jyunTehai[i], jyunTehai[i + 1]);
		}

		jyunTehaiLength--;

		return true;
	}

	/**
	 * ����v����w��̔v���폜����B
	 *
	 * @param hai
	 *            �w��̔v
	 * @return ����
	 */
	public boolean rmJyunTehai(Hai hai) {
		int id = hai.getId();

		for (int i = 0; i < jyunTehaiLength; i++) {
			if (id == jyunTehai[i].getId()) {
				rmJyunTehai(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * ����v���R�s�[����B
	 *
	 * @param destJyunTehai
	 *            �R�s�[��̏���v
	 * @param srcJyunTehai
	 *            �R�s�[���̏���v
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyJyunTehai(Hai[] destJyunTehai,
			Hai[] srcJyunTehai, int length) {
		if (length >= JYUNTEHAI_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			Hai.copy(destJyunTehai[i], srcJyunTehai[i]);
		}

		return true;
	}

	/**
	 * ����v�̎w��ʒu�̔v���R�s�[����B
	 *
	 * @param hai
	 *            �v
	 * @param idx
	 *            �w��ʒu
	 */
	public boolean copyJyunTehaiIdx(Hai hai, int idx) {
		if (idx >= jyunTehaiLength) {
			return false;
		}

		Hai.copy(hai, jyunTehai[idx]);

		return true;
	}

	/**
	 * ������ǉ�����B
	 *
	 * @param minShun
	 *            ����
	 * @return ����
	 */
	public boolean addMinShun(Hai[] minShun) {
		if (minShunsLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_3; i++) {
			Hai.copy(minShuns[minShunsLength][i], minShun[i]);
		}
		minShunsLength++;

		return true;
	}

	/**
	 * �����̔z����擾����B
	 *
	 * @return �����̔z��
	 */
	public Hai[][] getMinShuns() {
		return minShuns;
	}

	/**
	 * �����̔z��̒������擾����B
	 *
	 * @return �����̔z��̒���
	 */
	public int getMinShunsLength() {
		return minShunsLength;
	}

	/**
	 * �����̔z����R�s�[����B
	 *
	 * @param destMinShuns
	 *            �R�s�[��̖����̔z��
	 * @param srcMinShuns
	 *            �R�s�[���̖����̔z��
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyMinShuns(Hai[][] destMinShuns,
			Hai[][] srcMinShuns, int length) {
		if (length >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < MENTSU_LENGTH_3; j++) {
				Hai.copy(destMinShuns[i][j], srcMinShuns[i][j]);
			}
		}

		return true;
	}

	/**
	 * �`�[(��)�̉ۂ��`�F�b�N����B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return �`�[(��)�̉�
	 */
	public boolean validChiiLeft(Hai suteHai) {
		if (suteHai.isTsuu()) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_8) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_9) {
			return false;
		}

		if (minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiR1IdA = suteHaiIdA + 1;
		int suteHaiR2IdA = suteHaiIdA + 2;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiR1IdA) {
				for (int j = i + 1; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiR2IdA) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �`�[(��)��ݒ肷��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return ����
	 */
	public boolean setChiiLeft(Hai suteHai) {
		if (!validChiiLeft(suteHai)) {
			return false;
		}

		Hai.copy(minShuns[minShunsLength][0], suteHai);

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiR1IdA = suteHaiIdA + 1;
		int suteHaiR2IdA = suteHaiIdA + 2;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiR1IdA) {
				Hai.copy(minShuns[minShunsLength][1], jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiR2IdA) {
						Hai.copy(minShuns[minShunsLength][2], jyunTehai[j]);
						rmJyunTehai(j);
						minShunsLength++;
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �`�[(����)�̉ۂ��`�F�b�N����B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return �`�[(����)�̉�
	 */
	public boolean validChiiCenter(Hai suteHai) {
		if (suteHai.isTsuu()) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_1) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_9) {
			return false;
		}

		if (minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiL1IdA = suteHaiIdA - 1;
		int suteHaiR1IdA = suteHaiIdA + 1;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiL1IdA) {
				for (int j = i + 1; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiR1IdA) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �`�[(����)��ݒ肷��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return ����
	 */
	public boolean setChiiCenter(Hai suteHai) {
		if (!validChiiCenter(suteHai)) {
			return false;
		}

		Hai.copy(minShuns[minShunsLength][1], suteHai);

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiL1IdA = suteHaiIdA - 1;
		int suteHaiR1IdA = suteHaiIdA + 1;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiL1IdA) {
				Hai.copy(minShuns[minShunsLength][0], jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiR1IdA) {
						Hai.copy(minShuns[minShunsLength][2], jyunTehai[j]);
						rmJyunTehai(j);
						minShunsLength++;
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �`�[(�E)�̉ۂ��`�F�b�N����B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return �`�[(�E)�̉�
	 */
	public boolean validChiiRight(Hai suteHai) {
		if (suteHai.isTsuu()) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_1) {
			return false;
		}

		if (suteHai.getNo() == Hai.NO_2) {
			return false;
		}

		if (minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiL2IdA = suteHaiIdA - 2;
		int suteHaiL1IdA = suteHaiIdA - 1;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiL2IdA) {
				for (int j = i + 1; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiL1IdA) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �`�[(�E)��ݒ肷��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return ����
	 */
	public boolean setChiiRight(Hai suteHai) {
		if (!validChiiRight(suteHai)) {
			return false;
		}

		Hai.copy(minShuns[minShunsLength][2], suteHai);

		int suteHaiIdA = suteHai.getIdA();
		int suteHaiL2IdA = suteHaiIdA - 2;
		int suteHaiL1IdA = suteHaiIdA - 1;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (jyunTehai[i].getIdA() == suteHaiL2IdA) {
				Hai.copy(minShuns[minShunsLength][0], jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < jyunTehaiLength; j++) {
					if (jyunTehai[j].getIdA() == suteHaiL1IdA) {
						Hai.copy(minShuns[minShunsLength][1], jyunTehai[j]);
						rmJyunTehai(j);
						minShunsLength++;
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * ������ǉ�����B
	 *
	 * @param minKou
	 *            ����
	 * @return ����
	 */
	public boolean addMinKou(Hai[] minKou) {
		if (minKousLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_3; i++) {
			Hai.copy(minKous[minKousLength][i], minKou[i]);
		}
		minKousLength++;

		return true;
	}

	/**
	 * �����̔z����擾����B
	 *
	 * @return �����̔z��
	 */
	public Hai[][] getMinKous() {
		return minKous;
	}

	/**
	 * �����̔z��̒������擾����B
	 *
	 * @return �����̔z��̒���
	 */
	public int getMinKousLength() {
		return minKousLength;
	}

	/**
	 * �����̔z����R�s�[����B
	 *
	 * @param destMinKous
	 *            �R�s�[��̖����̔z��
	 * @param srcMinKous
	 *            �R�s�[���̖����̔z��
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyMinKous(Hai[][] destMinKous, Hai[][] srcMinKous,
			int length) {
		if (length >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < MENTSU_LENGTH_3; j++) {
				Hai.copy(destMinKous[i][j], srcMinKous[i][j]);
			}
		}

		return true;
	}

	/**
	 * �|���̉ۂ��`�F�b�N����B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return �|���̉�
	 */
	public boolean validPon(Hai suteHai) {
		if (minKousLength >= FUURO_MAX) {
			return false;
		}

		int suteHaiId = suteHai.getId();
		for (int i = 0, minKouIdx = 1; i < jyunTehaiLength; i++) {
			if (suteHaiId == jyunTehai[i].getId()) {
				minKouIdx++;
				if (minKouIdx >= MENTSU_LENGTH_3) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * �|����ݒ肷��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return ����
	 */
	public boolean setPon(Hai suteHai) {
		if (!validPon(suteHai)) {
			return false;
		}

		int minKouIdx = 0;

		Hai.copy(minKous[minKousLength][minKouIdx], suteHai);
		minKouIdx++;

		int suteHaiId = suteHai.getId();

		for (int i = 0; i < jyunTehaiLength; i++) {
			if (suteHaiId == jyunTehai[i].getId()) {
				Hai.copy(minKous[minKousLength][minKouIdx], jyunTehai[i]);
				minKouIdx++;

				rmJyunTehai(i--);

				if (minKouIdx >= MENTSU_LENGTH_3) {
					break;
				}
			}
		}

		minKousLength++;

		return true;
	}

	/**
	 * ���Ȃ�ǉ�����B
	 *
	 * @param minKan
	 *            ����
	 * @return ����
	 */
	public boolean addMinKan(Hai[] minKan) {
		if (minKansLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_4; i++) {
			Hai.copy(minKans[minKansLength][i], minKan[i]);
		}
		minKansLength++;

		return true;
	}

	/**
	 * ���Ȃ̔z����擾����B
	 *
	 * @return ���Ȃ̔z��
	 */
	public Hai[][] getMinKans() {
		return minKans;
	}

	/**
	 * ���Ȃ̔z��̒������擾����B
	 *
	 * @return ���Ȃ̔z��̒���
	 */
	public int getMinKansLength() {
		return minKansLength;
	}

	/**
	 * ���Ȃ̔z����R�s�[����B
	 *
	 * @param destMinKans
	 *            �R�s�[��̖��Ȃ̔z��
	 * @param srcMinKans
	 *            �R�s�[���̖��Ȃ̔z��
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyMinKans(Hai[][] destMinKans, Hai[][] srcMinKans,
			int length) {
		if (length >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < MENTSU_LENGTH_4; j++) {
				Hai.copy(destMinKans[i][j], srcMinKans[i][j]);
			}
		}

		return true;
	}

	/**
	 * ���Ȃ̉ۂ��`�F�b�N����B
	 *
	 * @param addHai
	 *            �ǉ�����v
	 * @return ���Ȃ̉�
	 */
	public boolean validMinKan(Hai addHai) {
		if (jyunTehaiLength <= MENTSU_LENGTH_4) {
			return false;
		}

		if (minKansLength >= FUURO_MAX) {
			return false;
		}

		int addHaiId = addHai.getId();
		for (int i = 0, minKanIdx = 1; i < jyunTehaiLength; i++) {
			if (addHaiId == jyunTehai[i].getId()) {
				minKanIdx++;
				if (minKanIdx >= MENTSU_LENGTH_4) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * ���Ȃ�ݒ肷��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return ����
	 */
	public boolean setMinKan(Hai suteHai) {
		if (!validMinKan(suteHai)) {
			return false;
		}

		int minKanIdx = 0;

		Hai.copy(minKans[minKansLength][minKanIdx], suteHai);
		minKanIdx++;

		int suteHaiId = suteHai.getId();

		for (int i = 0; i < jyunTehaiLength; i++) {
			if (suteHaiId == jyunTehai[i].getId()) {
				Hai.copy(minKans[minKansLength][minKanIdx], jyunTehai[i]);
				minKanIdx++;

				rmJyunTehai(i--);

				if (minKanIdx >= MENTSU_LENGTH_4) {
					break;
				}
			}
		}

		minKansLength++;

		return true;
	}

	/**
	 * �ÞȂ�ǉ�����B
	 *
	 * @param anKan
	 *            �Þ�
	 * @return ����
	 */
	public boolean addAnKan(Hai[] anKan) {
		if (anKansLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_4; i++) {
			Hai.copy(anKans[anKansLength][i], anKan[i]);
		}
		anKansLength++;

		return true;
	}

	/**
	 * �ÞȂ̔z����擾����B
	 *
	 * @return �ÞȂ̔z��
	 */
	public Hai[][] getAnKans() {
		return anKans;
	}

	/**
	 * �ÞȂ̔z��̒������擾����B
	 *
	 * @return �ÞȂ̔z��̒���
	 */
	public int getAnKansLength() {
		return anKansLength;
	}

	/**
	 * �ÞȂ̔z����R�s�[����B
	 *
	 * @param destAnKans
	 *            �R�s�[��̈ÞȂ̔z��
	 * @param srcAnKans
	 *            �R�s�[���̈ÞȂ̔z��
	 * @param length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyAnKans(Hai[][] destAnKans, Hai[][] srcAnKans,
			int length) {
		if (length >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < MENTSU_LENGTH_4; j++) {
				Hai.copy(destAnKans[i][j], srcAnKans[i][j]);
			}
		}

		return true;
	}

	/**
	 * �ÞȂ̉ۂ��`�F�b�N����B
	 *
	 * @param addHai
	 *            �ǉ�����v
	 * @return �ÞȂ̉�
	 */
	public boolean validAnKan(Hai addHai) {
		if (jyunTehaiLength <= MENTSU_LENGTH_4) {
			return false;
		}

		if (anKansLength >= FUURO_MAX) {
			return false;
		}

		int addHaiId = addHai.getId();
		for (int i = 0, anKanIdx = 1; i < jyunTehaiLength; i++) {
			if (addHaiId == jyunTehai[i].getId()) {
				anKanIdx++;
				if (anKanIdx >= MENTSU_LENGTH_4) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * �ÞȂ�ݒ肷��B
	 *
	 * @param tsumoHai
	 *            �c���v
	 * @return ����
	 */
	public boolean setAnKan(Hai tsumoHai) {
		if (!validAnKan(tsumoHai)) {
			return false;
		}

		int anKanIdx = 0;

		Hai.copy(anKans[anKansLength][anKanIdx], tsumoHai);
		anKanIdx++;

		int tsumoHaiId = tsumoHai.getId();

		for (int i = 0; i < jyunTehaiLength; i++) {
			if (tsumoHaiId == jyunTehai[i].getId()) {
				Hai.copy(anKans[anKansLength][anKanIdx], jyunTehai[i]);
				anKanIdx++;

				rmJyunTehai(i--);

				if (anKanIdx >= MENTSU_LENGTH_4) {
					break;
				}
			}
		}

		anKansLength++;

		return true;
	}
}
