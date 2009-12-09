package jp.sourceforge.andjong.mahjong;

/**
 * ��v���Ǘ�����B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Tehai {
	/** ����v�̒����̍ő�l */
	public final static int JYUN_TEHAI_LENGTH_MAX = 14;

	/** ���I�̍ő�l */
	public final static int FUURO_MAX = 4;

	/** �ʎq�̒���(3) */
	public final static int MENTSU_LENGTH_3 = 3;

	/** �ʎq�̒���(4) */
	public final static int MENTSU_LENGTH_4 = 4;

	/** ����v */
	private Hai[] m_jyunTehai = new Hai[JYUN_TEHAI_LENGTH_MAX];

	/** ����v�̒��� */
	private int m_jyunTehaiLength;

	/** �����̔z�� */
	private Hai[][] m_minShuns = new Hai[FUURO_MAX][MENTSU_LENGTH_3];

	/** �����̔z��̒��� */
	private int m_minShunsLength;

	/** ���Ȃ̔z�� */
	private Hai[][] m_minKans = new Hai[FUURO_MAX][MENTSU_LENGTH_4];

	/** ���Ȃ̔z��̒��� */
	private int m_minKansLength;

	/** �ÞȂ̔z�� */
	private Hai[][] m_anKans = new Hai[FUURO_MAX][MENTSU_LENGTH_4];

	/** �ÞȂ̔z��̒��� */
	private int m_anKansLength;

	/** ���I�̔z�� */
	private Fuuro[] m_fuuros = new Fuuro[FUURO_MAX];

	/** ���I�̌� */
	private int m_fuuroNums;

	{
		for (int i = 0; i < JYUN_TEHAI_LENGTH_MAX; i++) {
			m_jyunTehai[i] = new Hai();
		}

		for (int i = 0; i < FUURO_MAX; i++) {
			for (int j = 0; j < MENTSU_LENGTH_3; j++) {
				m_minShuns[i][j] = new Hai();
			}

			for (int j = 0; j < MENTSU_LENGTH_4; j++) {
				m_minKans[i][j] = new Hai();
				m_anKans[i][j] = new Hai();
			}
			m_fuuros[i] = new Fuuro();
		}
	}

	/**
	 * ��v���쐬����B
	 */
	public Tehai() {
		initialize();
	}

	/**
	 * ��v������������B
	 */
	public void initialize() {
		m_jyunTehaiLength = 0;
		m_minShunsLength = 0;
		m_minKansLength = 0;
		m_anKansLength = 0;
		m_fuuroNums = 0;
	}

	/**
	 * ��v���R�s�[����B
	 * 
	 * @param a_dest
	 *            �R�s�[��̎�v
	 * @param a_src
	 *            �R�s�[���̎�v
	 * @param jyunTehaiCopy
	 *            ����v�̃R�s�[����
	 */
	public static void copy(Tehai a_dest, Tehai a_src, boolean jyunTehaiCopy) {
		if (jyunTehaiCopy == true) {
			a_dest.m_jyunTehaiLength = a_src.m_jyunTehaiLength;
			Tehai.copyJyunTehai(a_dest.m_jyunTehai, a_src.m_jyunTehai, a_dest.m_jyunTehaiLength);
		}

		a_dest.m_minShunsLength = a_src.m_minShunsLength;
		Tehai.copyMinShuns(a_dest.m_minShuns, a_src.m_minShuns, a_dest.m_minShunsLength);

		a_dest.m_minKansLength = a_src.m_minKansLength;
		Tehai.copyMinKans(a_dest.m_minKans, a_src.m_minKans, a_dest.m_minKansLength);

		a_dest.m_anKansLength = a_src.m_anKansLength;
		Tehai.copyMinKans(a_dest.m_anKans, a_src.m_anKans, a_dest.m_anKansLength);

		a_dest.m_fuuroNums = a_src.m_fuuroNums;
		copyFuuros(a_dest.m_fuuros, a_src.m_fuuros, a_dest.m_fuuroNums);
	}

	/**
	 * ����v���擾����B
	 * 
	 * @return ����v
	 */
	public Hai[] getJyunTehai() {
		return m_jyunTehai;
	}

	/**
	 * ����v�̒������擾����B
	 * 
	 * @return ����v�̒���
	 */
	public int getJyunTehaiLength() {
		return m_jyunTehaiLength;
	}

	/**
	 * ����v�ɔv��ǉ�����B
	 * 
	 * @param a_hai
	 *            �ǉ�����v
	 * @return ����
	 */
	public boolean addJyunTehai(Hai a_hai) {
		if (m_jyunTehaiLength >= JYUN_TEHAI_LENGTH_MAX) {
			return false;
		}

		int i;
		for (i = m_jyunTehaiLength; i > 0; i--) {
			if (m_jyunTehai[i - 1].getId() <= a_hai.getId()) {
				break;
			}

			Hai.copy(m_jyunTehai[i], m_jyunTehai[i - 1]);
		}

		Hai.copy(m_jyunTehai[i], a_hai);
		m_jyunTehaiLength++;

		return true;
	}

	/**
	 * ����v����w��ʒu�̔v���폜����B
	 * 
	 * @param a_index
	 *            �w��ʒu
	 * @return ����
	 */
	public boolean rmJyunTehai(int a_index) {
		if (a_index >= JYUN_TEHAI_LENGTH_MAX) {
			return false;
		}

		for (int i = a_index; i < m_jyunTehaiLength - 1; i++) {
			Hai.copy(m_jyunTehai[i], m_jyunTehai[i + 1]);
		}

		m_jyunTehaiLength--;

		return true;
	}

	/**
	 * ����v����w��̔v���폜����B
	 * 
	 * @param a_hai
	 *            �w��̔v
	 * @return ����
	 */
	public boolean rmJyunTehai(Hai a_hai) {
		int id = a_hai.getId();

		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (id == m_jyunTehai[i].getId()) {
				return rmJyunTehai(i);
			}
		}

		return false;
	}

	/**
	 * ����v���R�s�[����B
	 * 
	 * @param a_dest
	 *            �R�s�[��̏���v
	 * @param a_src
	 *            �R�s�[���̏���v
	 * @param a_length
	 *            �R�s�[���钷��
	 * @return ����
	 */
	public static boolean copyJyunTehai(Hai[] a_dest, Hai[] a_src, int a_length) {
		if (a_length >= JYUN_TEHAI_LENGTH_MAX) {
			return false;
		}

		for (int i = 0; i < a_length; i++) {
			Hai.copy(a_dest[i], a_src[i]);
		}

		return true;
	}

	/**
	 * ����v�̎w��ʒu�̔v���R�s�[����B
	 * 
	 * @param a_hai
	 *            �v
	 * @param a_index
	 *            �w��ʒu
	 */
	public boolean copyJyunTehaiIdx(Hai a_hai, int a_index) {
		if (a_index >= m_jyunTehaiLength) {
			return false;
		}

		Hai.copy(a_hai, m_jyunTehai[a_index]);

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
		if (m_minShunsLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_3; i++) {
			Hai.copy(m_minShuns[m_minShunsLength][i], minShun[i]);
		}
		m_minShunsLength++;

		return true;
	}

	/**
	 * �����̔z����擾����B
	 * 
	 * @return �����̔z��
	 */
	public Hai[][] getMinShuns() {
		return m_minShuns;
	}

	/**
	 * �����̔z��̒������擾����B
	 * 
	 * @return �����̔z��̒���
	 */
	public int getMinShunsLength() {
		return m_minShunsLength;
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
	public static boolean copyMinShuns(Hai[][] destMinShuns, Hai[][] srcMinShuns, int length) {
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

		if (m_minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (m_jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiR1IdA = suteHaiIdA + 1;
		int suteHaiR2IdA = suteHaiIdA + 2;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiR1IdA) {
				for (int j = i + 1; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiR2IdA) {
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

		Hai.copy(m_minShuns[m_minShunsLength][0], suteHai);

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiR1IdA = suteHaiIdA + 1;
		int suteHaiR2IdA = suteHaiIdA + 2;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiR1IdA) {
				Hai.copy(m_minShuns[m_minShunsLength][1], m_jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiR2IdA) {
						Hai.copy(m_minShuns[m_minShunsLength][2], m_jyunTehai[j]);
						rmJyunTehai(j);
						m_minShunsLength++;
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

		if (m_minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (m_jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiL1IdA = suteHaiIdA - 1;
		int suteHaiR1IdA = suteHaiIdA + 1;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiL1IdA) {
				for (int j = i + 1; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiR1IdA) {
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

		Hai.copy(m_minShuns[m_minShunsLength][1], suteHai);

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiL1IdA = suteHaiIdA - 1;
		int suteHaiR1IdA = suteHaiIdA + 1;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiL1IdA) {
				Hai.copy(m_minShuns[m_minShunsLength][0], m_jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiR1IdA) {
						Hai.copy(m_minShuns[m_minShunsLength][2], m_jyunTehai[j]);
						rmJyunTehai(j);
						m_minShunsLength++;
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

		if (m_minShunsLength >= FUURO_MAX) {
			return false;
		}

		if (m_jyunTehaiLength <= MENTSU_LENGTH_3) {
			return false;
		}

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiL2IdA = suteHaiIdA - 2;
		int suteHaiL1IdA = suteHaiIdA - 1;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiL2IdA) {
				for (int j = i + 1; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiL1IdA) {
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

		Hai.copy(m_minShuns[m_minShunsLength][2], suteHai);

		int suteHaiIdA = suteHai.getNoKind();
		int suteHaiL2IdA = suteHaiIdA - 2;
		int suteHaiL1IdA = suteHaiIdA - 1;
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (m_jyunTehai[i].getNoKind() == suteHaiL2IdA) {
				Hai.copy(m_minShuns[m_minShunsLength][0], m_jyunTehai[i]);
				rmJyunTehai(i);
				for (int j = i; j < m_jyunTehaiLength; j++) {
					if (m_jyunTehai[j].getNoKind() == suteHaiL1IdA) {
						Hai.copy(m_minShuns[m_minShunsLength][1], m_jyunTehai[j]);
						rmJyunTehai(j);
						m_minShunsLength++;
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * �|���̉ۂ��`�F�b�N����B
	 * 
	 * @param a_suteHai
	 *            �̔v
	 * @return �|���̉�
	 */
	public boolean validPon(Hai a_suteHai) {
		if (m_fuuroNums >= FUURO_MAX) {
			return false;
		}

		int id = a_suteHai.getId();
		for (int i = 0, count = 1; i < m_jyunTehaiLength; i++) {
			if (id == m_jyunTehai[i].getId()) {
				count++;
				if (count >= MENTSU_LENGTH_3) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * �|����ݒ肷��B
	 * 
	 * @param a_suteHai
	 *            �̔v
	 * @param a_relation
	 *            �֌W
	 * @return ����
	 */
	public boolean setPon(Hai a_suteHai, int a_relation) {
		if (!validPon(a_suteHai)) {
			return false;
		}

		Hai hais[] = new Hai[Mahjong.MENTSU_HAI_MEMBERS_4];

		int iMinKou = 0;
		hais[iMinKou] = new Hai(a_suteHai);
		iMinKou++;

		int id = a_suteHai.getId();
		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (id == m_jyunTehai[i].getId()) {
				hais[iMinKou] = new Hai(m_jyunTehai[i]);
				iMinKou++;

				rmJyunTehai(i);
				i--;

				if (iMinKou >= MENTSU_LENGTH_3) {
					break;
				}
			}
		}

		hais[iMinKou] = new Hai();

		m_fuuros[m_fuuroNums].setType(Fuuro.TYPE_MINKOU);
		m_fuuros[m_fuuroNums].setRelation(a_relation);
		m_fuuros[m_fuuroNums].setHais(hais);
		m_fuuroNums++;

		return true;
	}

	/**
	 * ���I�̔z����擾����B
	 * 
	 * @return ���I�̔z��
	 */
	public Fuuro[] getFuuros() {
		return m_fuuros;
	}

	/**
	 * ���I�̌����擾����B
	 * 
	 * @return ���I�̌�
	 */
	public int getFuuroNums() {
		return m_fuuroNums;
	}

	/**
	 * ���Ȃ�ǉ�����B
	 * 
	 * @param minKan
	 *            ����
	 * @return ����
	 */
	public boolean addMinKan(Hai[] minKan) {
		if (m_minKansLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_4; i++) {
			Hai.copy(m_minKans[m_minKansLength][i], minKan[i]);
		}
		m_minKansLength++;

		return true;
	}

	/**
	 * ���Ȃ̔z����擾����B
	 * 
	 * @return ���Ȃ̔z��
	 */
	public Hai[][] getMinKans() {
		return m_minKans;
	}

	/**
	 * ���Ȃ̔z��̒������擾����B
	 * 
	 * @return ���Ȃ̔z��̒���
	 */
	public int getMinKansLength() {
		return m_minKansLength;
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
	public static boolean copyMinKans(Hai[][] destMinKans, Hai[][] srcMinKans, int length) {
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
		if (m_jyunTehaiLength <= MENTSU_LENGTH_4) {
			return false;
		}

		if (m_minKansLength >= FUURO_MAX) {
			return false;
		}

		int addHaiId = addHai.getId();
		for (int i = 0, minKanIdx = 1; i < m_jyunTehaiLength; i++) {
			if (addHaiId == m_jyunTehai[i].getId()) {
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

		Hai.copy(m_minKans[m_minKansLength][minKanIdx], suteHai);
		minKanIdx++;

		int suteHaiId = suteHai.getId();

		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (suteHaiId == m_jyunTehai[i].getId()) {
				Hai.copy(m_minKans[m_minKansLength][minKanIdx], m_jyunTehai[i]);
				minKanIdx++;

				rmJyunTehai(i--);

				if (minKanIdx >= MENTSU_LENGTH_4) {
					break;
				}
			}
		}

		m_minKansLength++;

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
		if (m_anKansLength >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < MENTSU_LENGTH_4; i++) {
			Hai.copy(m_anKans[m_anKansLength][i], anKan[i]);
		}
		m_anKansLength++;

		return true;
	}

	/**
	 * �ÞȂ̔z����擾����B
	 * 
	 * @return �ÞȂ̔z��
	 */
	public Hai[][] getAnKans() {
		return m_anKans;
	}

	/**
	 * �ÞȂ̔z��̒������擾����B
	 * 
	 * @return �ÞȂ̔z��̒���
	 */
	public int getAnKansLength() {
		return m_anKansLength;
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
	public static boolean copyAnKans(Hai[][] destAnKans, Hai[][] srcAnKans, int length) {
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
		if (m_jyunTehaiLength <= MENTSU_LENGTH_4) {
			return false;
		}

		if (m_anKansLength >= FUURO_MAX) {
			return false;
		}

		int addHaiId = addHai.getId();
		for (int i = 0, anKanIdx = 1; i < m_jyunTehaiLength; i++) {
			if (addHaiId == m_jyunTehai[i].getId()) {
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

		Hai.copy(m_anKans[m_anKansLength][anKanIdx], tsumoHai);
		anKanIdx++;

		int tsumoHaiId = tsumoHai.getId();

		for (int i = 0; i < m_jyunTehaiLength; i++) {
			if (tsumoHaiId == m_jyunTehai[i].getId()) {
				Hai.copy(m_anKans[m_anKansLength][anKanIdx], m_jyunTehai[i]);
				anKanIdx++;

				rmJyunTehai(i--);

				if (anKanIdx >= MENTSU_LENGTH_4) {
					break;
				}
			}
		}

		m_anKansLength++;

		return true;
	}

	public static boolean copyFuuros(Fuuro[] destFuuros, Fuuro[] srcFuuros, int length) {
		if (length >= FUURO_MAX) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			Fuuro.copy(destFuuros[i], srcFuuros[i]);
		}

		return true;
	}
}
