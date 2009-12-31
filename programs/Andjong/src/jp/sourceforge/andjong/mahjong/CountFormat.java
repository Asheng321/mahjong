package jp.sourceforge.andjong.mahjong;

/**
 * �J�E���g�t�H�[�}�b�g���Ǘ�����N���X�ł��B
 *
 * @author Yuji Urushibara
 *
 */
public class CountFormat {
	/**
	 * �J�E���g���Ǘ�����N���X�ł��B
	 *
	 * @author Yuji Urushibara
	 *
	 */
	public static class Count {
		/** NK */
		public int m_noKind = 0;

		/** �� */
		public int m_num = 0;

		/**
		 * �J�E���g������������B
		 */
		public void initialize() {
			m_noKind = 0;
			m_num = 0;
		}
	}

	/**
	 * �オ��̑g�ݍ��킹�̃N���X�ł��B
	 *
	 * @author Yuji Urushibara
	 *
	 */
	public static class Combi {
		/** ����NK */
		public int m_atamaNoKind = 0;

		/** ���q��NK�̔z�� */
		public int[] m_shunNoKinds = new int[4];
		/** ���q��NK�̔z��̗L���Ȍ� */
		public int m_shunNum = 0;

		/** ���q��NK�̔z�� */
		public int[] m_kouNoKinds = new int[4];
		/** ���q��NK�̔z��̗L���Ȍ� */
		public int m_kouNum = 0;

		/**
		 * Combi���R�s�[����B
		 *
		 * @param a_dest
		 *            �R�s�[���Combi
		 * @param a_src
		 *            �R�s�[����Combi
		 */
		public static void copy(Combi a_dest, Combi a_src) {
			a_dest.m_atamaNoKind = a_src.m_atamaNoKind;

			a_dest.m_shunNum = a_src.m_shunNum;
			for (int i = 0; i < a_dest.m_shunNum; i++) {
				a_dest.m_shunNoKinds[i] = a_src.m_shunNoKinds[i];
			}

			a_dest.m_kouNum = a_src.m_kouNum;
			for (int i = 0; i < a_dest.m_kouNum; i++) {
				a_dest.m_kouNoKinds[i] = a_src.m_kouNoKinds[i];
			}
		}
	}

	/**
	 * �オ��̑g�ݍ��킹�̔z����Ǘ�����N���X�ł��B
	 *
	 * @author Yuji Urushibara
	 *
	 */
	private static class CombiManage {
		/** �オ��̑g�ݍ��킹�̔z��̍ő�l */
		public final static int COMBI_MAX = 10;

		/** �オ��̑g�ݍ��킹�̔z�� */
		public Combi[] m_combis = new Combi[COMBI_MAX];
		/** �オ��̑g�ݍ��킹�̔z��̗L���Ȍ� */
		public int m_combiNum = 0;

		/** �J�E���g�̔z��̎c��̌� */
		public int m_remain = 0;

		/** ��Ɨ̈� */
		public Combi m_work = new Combi();

		{
			for (int i = 0; i < m_combis.length; i++) {
				m_combis[i] = new Combi();
			}
		}

		/**
		 * ��Ɨ̈������������B
		 *
		 * @param a_remain
		 *            �J�E���g�̔z��̎c��̌�
		 */
		public void initialize(int a_remain) {
			m_combiNum = 0;
			this.m_remain = a_remain;
			m_work.m_atamaNoKind = 0;
			m_work.m_shunNum = 0;
			m_work.m_kouNum = 0;
		}

		/**
		 * �オ��̑g�ݍ��킹��ǉ�����B
		 */
		public void add() {
			Combi.copy(m_combis[m_combiNum++], m_work);
		}
	}

	/** �J�E���g�̍ő�l */
	public static final int COUNT_MAX = 14 + 2;

	/** �J�E���g�̔z�� */
	public Count[] m_counts;
	/** �J�E���g�̔z��̗L���Ȍ� */
	public int m_countNum;

	/** �オ��̑g�ݍ��킹�̔z����Ǘ� */
	private CombiManage m_combiManage = new CombiManage();

	{
		m_counts = new Count[COUNT_MAX];
		for (int i = 0; i < COUNT_MAX; i++) {
			m_counts[i] = new Count();
		}
	}

	/**
	 * �J�E���g�̔z��̒����̍��v���擾����B
	 *
	 * @return �J�E���g�̔z��̒����̍��v
	 */
	private int getTotalCountLength() {
		int totalCountLength = 0;

		for (int i = 0; i < m_countNum; i++) {
			totalCountLength += m_counts[i].m_num;
		}

		return totalCountLength;
	}

	/**
	 * �J�E���g�t�H�[�}�b�g��ݒ肷��B
	 *
	 * @param a_tehai
	 *            ��v
	 * @param a_addHai
	 *            �ǉ�����v
	 */
	public void setCountFormat(Tehai a_tehai, Hai a_addHai) {
		for (int i = 0; i < m_counts.length; i++) {
			m_counts[i].initialize();
		}
		m_countNum = 0;

		int addHaiNoKind = 0;
		boolean set = true;
		if (a_addHai != null) {
			addHaiNoKind = a_addHai.getNoKind();
			set = false;
		}

		int jyunTehaiNoKind;
		int jyunTehaiLength = a_tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength;) {
			jyunTehaiNoKind = (a_tehai.getJyunTehai())[i].getNoKind();

			if (!set && (jyunTehaiNoKind > addHaiNoKind)) {
				set = true;
				m_counts[m_countNum].m_noKind = addHaiNoKind;
				m_counts[m_countNum].m_num = 1;
				m_countNum++;
				continue;
			}

			m_counts[m_countNum].m_noKind = jyunTehaiNoKind;
			m_counts[m_countNum].m_num = 1;

			if (!set && (jyunTehaiNoKind == addHaiNoKind)) {
				set = true;
				m_counts[m_countNum].m_num++;
			}

			while (++i < jyunTehaiLength) {
				if (jyunTehaiNoKind == (a_tehai.getJyunTehai())[i].getNoKind()) {
					m_counts[m_countNum].m_num++;
				} else {
					break;
				}
			}

			m_countNum++;
		}

		if (!set) {
			m_counts[m_countNum].m_noKind = addHaiNoKind;
			m_counts[m_countNum].m_num = 1;
			m_countNum++;
		}
	}

	/**
	 * �オ��̑g�ݍ��킹�̔z����擾����B
	 *
	 * @param a_combis
	 *            �オ��̑g�ݍ��킹�̔z��
	 * @return
	 */
	public int getCombis(Combi[] a_combis) {
		m_combiManage.initialize(getTotalCountLength());
		searchCombi(0);
		a_combis = m_combiManage.m_combis;
		return m_combiManage.m_combiNum;
	}

	public Combi[] getCombis() {
		return m_combiManage.m_combis;
	}

	public int getCombiNum() {
		return m_combiManage.m_combiNum;
	}

	/**
	 * �オ��̑g�ݍ��킹���ċA�I�ɒT���B
	 *
	 * @param a_iSearch
	 *            �����ʒu
	 */
	private void searchCombi(int a_iSearch) {
		// �����ʒu���X�V����B
		for (; a_iSearch < m_countNum; a_iSearch++) {
			if (m_counts[a_iSearch].m_num > 0) {
				break;
			}
		}

		if (a_iSearch >= m_countNum) {
			return;
		}

		// �����`�F�b�N����B
		if (m_combiManage.m_work.m_atamaNoKind == 0) {
			if (m_counts[a_iSearch].m_num >= 2) {
				// �����m�肷��B
				m_counts[a_iSearch].m_num -= 2;
				m_combiManage.m_remain -= 2;
				m_combiManage.m_work.m_atamaNoKind = m_counts[a_iSearch].m_noKind;

				// �オ��̑g�ݍ��킹����������ǉ�����B
				if (m_combiManage.m_remain <= 0) {
					m_combiManage.add();
				} else {
					searchCombi(a_iSearch);
				}

				// �m�肵������߂��B
				m_counts[a_iSearch].m_num += 2;
				m_combiManage.m_remain += 2;
				m_combiManage.m_work.m_atamaNoKind = 0;
			}
		}

		// ���q���`�F�b�N����B
		int left = a_iSearch;
		int center = a_iSearch + 1;
		int right = a_iSearch + 2;
		if (!Hai.isTsuu(m_counts[left].m_noKind)) {
			if ((m_counts[left].m_noKind + 1 == m_counts[center].m_noKind) && (m_counts[center].m_num > 0)) {
				if ((m_counts[left].m_noKind + 2 == m_counts[right].m_noKind) && (m_counts[right].m_num > 0)) {
					// ���q���m�肷��B
					m_counts[left].m_num--;
					m_counts[center].m_num--;
					m_counts[right].m_num--;
					m_combiManage.m_remain -= 3;
					m_combiManage.m_work.m_shunNoKinds[m_combiManage.m_work.m_shunNum] = m_counts[left].m_noKind;
					m_combiManage.m_work.m_shunNum++;

					// �オ��̑g�ݍ��킹����������ǉ�����B
					if (m_combiManage.m_remain <= 0) {
						m_combiManage.add();
					} else {
						searchCombi(a_iSearch);
					}

					// �m�肵�����q��߂��B
					m_counts[left].m_num++;
					m_counts[center].m_num++;
					m_counts[right].m_num++;
					m_combiManage.m_remain += 3;
					m_combiManage.m_work.m_shunNum--;
				}
			}
		}

		// ���q���`�F�b�N����B
		if (m_counts[a_iSearch].m_num >= 3) {
			// ���q���m�肷��B
			m_counts[a_iSearch].m_num -= 3;
			m_combiManage.m_remain -= 3;
			m_combiManage.m_work.m_kouNoKinds[m_combiManage.m_work.m_kouNum] = m_counts[a_iSearch].m_noKind;
			m_combiManage.m_work.m_kouNum++;

			// �オ��̑g�ݍ��킹����������ǉ�����B
			if (m_combiManage.m_remain <= 0) {
				m_combiManage.add();
			} else {
				searchCombi(a_iSearch);
			}

			// �m�肵�����q��߂��B/
			m_combiManage.m_remain += 3;
			m_counts[a_iSearch].m_num += 3;
			m_combiManage.m_work.m_kouNum--;
		}
	}
}
