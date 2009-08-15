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
	 * ��v�I�u�W�F�N�g���R�s�[����B
	 *
	 * @param tehai
	 *            ��v�I�u�W�F�N�g
	 * @param jyunTehaiCopy
	 *            ����v�̃R�s�[����
	 */
	public void copy(Tehai tehai, boolean jyunTehaiCopy) {
		initialize();
		if (jyunTehaiCopy == true) {
			this.jyunTehaiLength = tehai.copyJyunTehai(this.jyunTehai);
		}
		this.minShunsLength = tehai.copyMinshuns(this.minShuns);
		this.minKousLength = tehai.copyMinkous(this.minKous);
		this.minKansLength = tehai.copyMinkans(this.minKans);
		this.anKansLength = tehai.copyAnkans(this.anKans);
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

			jyunTehai[i].copy(jyunTehai[i - 1]);
		}

		jyunTehai[i].copy(hai);
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
			jyunTehai[i].copy(jyunTehai[i + 1]);
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
	 * ����v�̃C���f�b�N�X���w�肵�āA�v���R�s�[����B
	 *
	 * @param hai
	 *            �v
	 * @param idx
	 *            ����v�̃C���f�b�N�X
	 */
	public void copyJyunTehaiIdx(Hai hai, int idx) {
		hai.copy(jyunTehai[idx]);
	}

	/**
	 * ����v���R�s�[����B
	 *
	 * @param jyunTehai
	 *            ����v
	 * @return ����v�̒���
	 */
	public int copyJyunTehai(Hai[] jyunTehai) {
		for (int i = 0; i < this.jyunTehaiLength; i++)
			jyunTehai[i].copy(this.jyunTehai[i]);
		return this.jyunTehaiLength;
	}

	/**
	 * ������ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 *
	 * @param minshun
	 *            ����
	 */
	public void addMinshun(Hai[] minshun) {
		if (minShunsLength >= 4)
			return;

		minShuns[minShunsLength][0].copy(minshun[0]);
		minShuns[minShunsLength][1].copy(minshun[1]);
		minShuns[minShunsLength++][2].copy(minshun[2]);
	}

	/**
	 * �����̔z����擾����B
	 *
	 * @return �����̔z��
	 */
	public Hai[][] getMinshuns() {
		return minShuns;
	}

	/**
	 * �����̔z��̒������擾����B
	 *
	 * @return �����̔z��̒���
	 */
	public int getMinshunsLength() {
		return minShunsLength;
	}

	/**
	 * �����̔z����R�s�[����B
	 *
	 * @param minshuns
	 *            �����̔z��
	 * @return �����̔z��̒���
	 */
	public int copyMinshuns(Hai[][] minshuns) {
		for (int i = 0; i < this.minShunsLength; i++) {
			minshuns[i][0].copy(this.minShuns[i][0]);
			minshuns[i][1].copy(this.minShuns[i][1]);
			minshuns[i][2].copy(this.minShuns[i][2]);
		}
		return this.minShunsLength;
	}

	/**
	 * ������ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 *
	 * @param minkou
	 *            ����
	 */
	public void addMinkou(Hai[] minkou) {
		if (minKousLength >= 4)
			return;

		minKous[minKousLength][0].copy(minkou[0]);
		minKous[minKousLength][1].copy(minkou[1]);
		minKous[minKousLength++][2].copy(minkou[2]);
	}

	/**
	 * �����̔z����擾����B
	 *
	 * @return �����̔z��
	 */
	public Hai[][] getMinkous() {
		return minKous;
	}

	/**
	 * �����̔z��̒������擾����B
	 *
	 * @return �����̔z��̒���
	 */
	public int getMinkousLength() {
		return minKousLength;
	}

	/**
	 * �����̔z����R�s�[����B
	 *
	 * @param minkous
	 *            �����̔z��
	 * @return �����̔z��̒���
	 */
	public int copyMinkous(Hai[][] minkous) {
		for (int i = 0; i < this.minKousLength; i++) {
			minkous[i][0].copy(this.minKous[i][0]);
			minkous[i][1].copy(this.minKous[i][1]);
			minkous[i][2].copy(this.minKous[i][2]);
		}
		return this.minKousLength;
	}

	/**
	 * ���Ȃ�ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 *
	 * @param minkan
	 *            ����
	 */
	public void addMinkan(Hai[] minkan) {
		if (minKansLength >= 4)
			return;

		minKans[minKansLength][0].copy(minkan[0]);
		minKans[minKansLength][1].copy(minkan[1]);
		minKans[minKansLength][2].copy(minkan[2]);
		minKans[minKansLength++][3].copy(minkan[3]);
	}

	/**
	 * ���Ȃ̔z����擾����B
	 *
	 * @return ���Ȃ̔z��
	 */
	public Hai[][] getMinkans() {
		return minKans;
	}

	/**
	 * ���Ȃ̔z��̒������擾����B
	 *
	 * @return ���Ȃ̔z��̒���
	 */
	public int getMinkansLength() {
		return minKansLength;
	}

	/**
	 * ���Ȃ̔z����R�s�[����B
	 *
	 * @param minkans
	 *            ���Ȃ̔z��
	 * @return ���Ȃ̔z��̒���
	 */
	public int copyMinkans(Hai[][] minkans) {
		for (int i = 0; i < this.minKansLength; i++) {
			minkans[i][0].copy(this.minKans[i][0]);
			minkans[i][1].copy(this.minKans[i][1]);
			minkans[i][2].copy(this.minKans[i][2]);
			minkans[i][3].copy(this.minKans[i][3]);
		}
		return this.minKansLength;
	}

	/**
	 * �ÞȂ�ǉ�����B
	 * <p>
	 * Hai�I�u�W�F�N�g���R�s�[���܂��B
	 * </p>
	 *
	 * @param ankan
	 *            �Þ�
	 */
	public void addAnkan(Hai[] ankan) {
		if (anKansLength >= 4)
			return;

		anKans[anKansLength][0].copy(ankan[0]);
		anKans[anKansLength][1].copy(ankan[1]);
		anKans[anKansLength][2].copy(ankan[2]);
		anKans[anKansLength++][3].copy(ankan[3]);
	}

	/**
	 * �ÞȂ̔z����擾����B
	 *
	 * @return �ÞȂ̔z��
	 */
	public Hai[][] getAnkans() {
		return anKans;
	}

	/**
	 * �ÞȂ̔z��̒������擾����B
	 *
	 * @return �ÞȂ̔z��̒���
	 */
	public int getAnkansLength() {
		return anKansLength;
	}

	/**
	 * �ÞȂ̔z����R�s�[����B
	 *
	 * @param minKans
	 *            �ÞȂ̔z��
	 * @return �ÞȂ̔z��̒���
	 */
	public int copyAnkans(Hai[][] ankans) {
		for (int i = 0; i < this.anKansLength; i++) {
			ankans[i][0].copy(this.anKans[i][0]);
			ankans[i][1].copy(this.anKans[i][1]);
			ankans[i][2].copy(this.anKans[i][2]);
			ankans[i][3].copy(this.anKans[i][3]);
		}
		return this.anKansLength;
	}

	/**
	 * �|���̉ۂ��`�F�b�N���܂��B
	 *
	 * @param suteHai
	 *            �̔v
	 * @return �|���̉�
	 */
	public boolean validPon(Hai suteHai) {
		int haiId = suteHai.getId();
		int count = 0;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (haiId == jyunTehai[i].getId()) {
				count++;
			}
		}

		if (count >= 2) {
			return true;
		}
		return false;
	}

	/**
	 * �|����ݒ肵�܂��B
	 *
	 * @param suteHai
	 *            �̔v
	 */
	public void setPon(Hai suteHai) {
		int haiId = suteHai.getId();
		Hai[] minkou = new Hai[3];
		int minkouIdx = 0;

		minkou[minkouIdx++] = suteHai;
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (haiId == jyunTehai[i].getId()) {
				rmJyunTehai(i--);
				minkou[minkouIdx++] = suteHai;
				if (minkouIdx >= 3) {
					break;
				}
			}
		}
		addMinkou(minkou);
	}

	/**
	 * �J�E���g�t�H�[�}�b�g���Ǘ�����N���X�ł��B
	 * <p>
	 * �\���̂̂悤�Ɏg�p���܂��B
	 * </p>
	 *
	 * @author Yuji Urushibara
	 *
	 */
	public static class CountFormat {
		/**
		 * �J�E���g���Ǘ�����N���X�ł��B
		 * <p>
		 * �\���̂̂悤�Ɏg�p���܂��B
		 * </p>
		 *
		 * @author Yuji Urushibara
		 *
		 */
		public static class Count {
			/** �v�ԍ� */
			public int id;

			/** �v�̌� */
			public int length;
		}

		/** �J�E���g�̍ő�l */
		public static final int COUNT_MAX = 14 + 2;

		/** �J�E���g�̔z�� */
		public Count[] counts = new Count[COUNT_MAX];

		/** �J�E���g�̒��� */
		public int length;

		{
			for (int i = 0; i < COUNT_MAX; i++)
				counts[i] = new Count();
		}

		/**
		 * �J�E���g�̔z��̒����̍��v���擾����B
		 *
		 * @return�@�J�E���g�̔z��̒����̍��v
		 */
		public int getTotalCountLength() {
			int totalCountLength = 0;

			for (int i = 0; i < length; i++)
				totalCountLength += counts[i].length;

			return totalCountLength;
		}
	}

	/**
	 * �J�E���g�t�H�[�}�b�g���擾����B
	 *
	 * @param addHai
	 *            ��v�ɒǉ�����v�Bnull�ł��ǂ��B
	 * @return �J�E���g�t�H�[�}�b�g
	 */
	public void getCountFormat(CountFormat countFormat, Hai addHai) {
		int jyunTehaiId;
		int addHaiId = 0;
		boolean set = true;

		countFormat.length = 0;

		if (addHai != null) {
			addHaiId = addHai.getIdA();
			set = false;
		}

		for (int i = 0; i < jyunTehaiLength;) {
			jyunTehaiId = jyunTehai[i].getIdA();

			if (!set && (jyunTehaiId > addHaiId)) {
				set = true;
				countFormat.counts[countFormat.length].id = addHaiId;
				countFormat.counts[countFormat.length].length = 1;
				countFormat.length++;
				continue;
			}

			countFormat.counts[countFormat.length].id = jyunTehaiId;
			countFormat.counts[countFormat.length].length = 1;

			if (!set && (jyunTehaiId == addHaiId)) {
				set = true;
				countFormat.counts[countFormat.length].length++;
			}

			while (++i < jyunTehaiLength)
				if (jyunTehaiId == jyunTehai[i].getIdA())
					countFormat.counts[countFormat.length].length++;
				else
					break;

			countFormat.length++;
		}
	}

	/**
	 * �オ��̑g�ݍ��킹�̃N���X�ł��B
	 *
	 * @author Yuji Urushibara
	 *
	 */
	public static class Combi {
		/** ���̔v�ԍ� */
		public int atamaId = 0;

		/** ���q�̔v�ԍ��̔z�� */
		public int[] shunIds = new int[4];

		/** ���q�̔v�ԍ��̔z��̐� */
		public int shunCount = 0;

		/** ���q�̔v�ԍ��̔z�� */
		public int[] kouIds = new int[4];

		/** ���q�̔v�ԍ��̔z��̐� */
		public int kouCount = 0;

		/**
		 * Combi�I�u�W�F�N�g���R�s�[���܂��B
		 *
		 * @param combi
		 *            Combi�I�u�W�F�N�g
		 */
		public void copy(Combi combi) {
			atamaId = combi.atamaId;

			shunCount = combi.shunCount;
			shunIds[0] = combi.shunIds[0];
			shunIds[1] = combi.shunIds[1];
			shunIds[2] = combi.shunIds[2];
			shunIds[3] = combi.shunIds[3];

			kouCount = combi.kouCount;
			kouIds[0] = combi.kouIds[0];
			kouIds[1] = combi.kouIds[1];
			kouIds[2] = combi.kouIds[2];
			kouIds[3] = combi.kouIds[3];
		}
	}

	/**
	 * �オ��̑g�ݍ��킹�̍\�z���Ǘ�����N���X�ł��B
	 *
	 * @author Yuji Urushibara
	 *
	 */
	private static class CombiManage {
		/** �オ��̑g�ݍ��킹�i��Ɨ̈�j */
		public Combi combiWork = new Combi();

		/** �オ��̑g�ݍ��킹�̔z�� */
		public Combi[] combis = new Combi[10];

		/** �オ��̑g�ݍ��킹�̔z��̐� */
		public int combisCount = 0;

		/** �J�E���g�̔z��̒����̎c�� */
		public int totalCount = 0;

		{
			for (int i = 0; i < 10; i++)
				combis[i] = new Combi();
		}

		/**
		 * ��Ɨ̈������������B
		 *
		 * @param totalCount
		 *            �J�E���g�̔z��̒����̎c��
		 */
		public void init(int totalCount) {
			combiWork.atamaId = 0;
			combiWork.shunCount = 0;
			combiWork.kouCount = 0;
			combisCount = 0;
			this.totalCount = totalCount;
		}

		/**
		 * �オ��̑g�ݍ��킹��ǉ�����B
		 */
		public void add() {
			combis[combisCount++].copy(combiWork);
		}
	}

	/** �オ��̑g�ݍ��킹�̍\�z���Ǘ� */
	public CombiManage combiManage = new CombiManage();

	public int getCombi(Combi[] combis, CountFormat countFormat) {
		combiManage.init(countFormat.getTotalCountLength());
		searchCombi(countFormat, 0);
		combis = combiManage.combis;
		return combiManage.combisCount;
	}

	/**
	 * �オ��̑g�ݍ��킹��T���B
	 * <p>
	 * �ċA�I�ɏオ��̑g�ݍ��킹��T���܂��B<br>
	 * �������x���d�v�ł��B<br>
	 * TODO �œK���̎�i���������邱�ƁB
	 * </p>
	 *
	 * @param pos
	 *            �����ʒu
	 */
	public void searchCombi(CountFormat countFormat, int pos) {
		/*
		 * �����ʒu���X�V���܂��B
		 */
		for (; pos < countFormat.length; pos++)
			if (countFormat.counts[pos].length > 0)
				break;

		if (pos >= countFormat.length)
			return;

		/*
		 * �����`�F�b�N����B
		 */
		if (combiManage.combiWork.atamaId == 0) {
			if (countFormat.counts[pos].length >= 2) {
				/*
				 * �����m�肵�ď����X�V����B
				 */
				countFormat.counts[pos].length -= 2;
				combiManage.totalCount -= 2;
				combiManage.combiWork.atamaId = countFormat.counts[pos].id;

				/*
				 * �オ��̑g�ݍ��킹����������ǉ�����B
				 */
				if (combiManage.totalCount <= 0)
					combiManage.add();
				else
					searchCombi(countFormat, pos);

				/*
				 * �m�肵������߂��B
				 */
				countFormat.counts[pos].length += 2;
				combiManage.totalCount += 2;
				combiManage.combiWork.atamaId = 0;
			}
		}

		/*
		 * ���q���`�F�b�N����B
		 */
		if (((pos + 2) < countFormat.length) && ((countFormat.counts[pos + 2].id & Hai.ID_A_TSUU) == 0)) {
			if ((countFormat.counts[pos].id + 1 == countFormat.counts[pos + 1].id) && (countFormat.counts[pos + 1].length > 0)) {
				if ((countFormat.counts[pos].id + 2 == countFormat.counts[pos + 2].id) && (countFormat.counts[pos + 2].length > 0)) {
					/*
					 * ���q���m�肵�ď����X�V����B
					 */
					countFormat.counts[pos].length--;
					countFormat.counts[pos + 1].length--;
					countFormat.counts[pos + 2].length--;
					combiManage.totalCount -= 3;
					combiManage.combiWork.shunIds[combiManage.combiWork.shunCount] = countFormat.counts[pos].id;
					combiManage.combiWork.shunCount++;

					/*
					 * �オ��̑g�ݍ��킹����������ǉ�����B
					 */
					if (combiManage.totalCount <= 0)
						combiManage.add();
					else
						searchCombi(countFormat, pos);

					/*
					 * �m�肵�����q��߂��B
					 */
					countFormat.counts[pos].length++;
					countFormat.counts[pos + 1].length++;
					countFormat.counts[pos + 2].length++;
					combiManage.totalCount += 3;
					combiManage.combiWork.shunCount--;
				}
			}
		}

		/*
		 * ���q���`�F�b�N����B
		 */
		if (countFormat.counts[pos].length >= 3) {
			/*
			 * ���q���m�肵�ď����X�V����B
			 */
			countFormat.counts[pos].length -= 3;
			combiManage.totalCount -= 3;
			combiManage.combiWork.kouIds[combiManage.combiWork.kouCount] = countFormat.counts[pos].id;
			combiManage.combiWork.kouCount++;

			/*
			 * �オ��̑g�ݍ��킹����������ǉ�����B
			 */
			if (combiManage.totalCount <= 0)
				combiManage.add();
			else
				searchCombi(countFormat, pos);

			/*
			 * �m�肵�����q��߂��B
			 */
			combiManage.totalCount += 3;
			countFormat.counts[pos].length += 3;
			combiManage.combiWork.kouCount--;
		}
	}
}
