package jp.sourceforge.andjong.mahjong;

/**
 * �J�E���g�t�H�[�}�b�g���Ǘ�����N���X�ł��B
 * <p>
 * �\���̂̂悤�Ɏg�p���܂��B
 * </p>
 *
 * @author Yuji Urushibara
 *
 */
public class CountFormat {
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

	/**
	 * �J�E���g�t�H�[�}�b�g���擾����B
	 *
	 * @param addHai
	 *            ��v�ɒǉ�����v�Bnull�ł��ǂ��B
	 * @return �J�E���g�t�H�[�}�b�g
	 */
	public static void getCountFormat(Tehai tehai, CountFormat countFormat,
			Hai addHai) {
		int jyunTehaiId;
		int addHaiId = 0;
		boolean set = true;

		countFormat.length = 0;

		if (addHai != null) {
			addHaiId = addHai.getIdA();
			set = false;
		}

		for (int i = 0; i < tehai.getJyunTehaiLength();) {
			jyunTehaiId = (tehai.getJyunTehai())[i].getIdA();

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

			while (++i < tehai.getJyunTehaiLength())
				if (jyunTehaiId == (tehai.getJyunTehai())[i].getIdA())
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

	public int getCombi(Combi[] combis) {
		combiManage.init(getTotalCountLength());
		searchCombi(this, 0);
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
