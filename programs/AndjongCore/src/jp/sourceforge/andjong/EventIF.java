package jp.sourceforge.andjong;

public interface EventIF {
	/** �C�x���gID */
	enum EID {
		/** �ꏊ���� */
		BASHOGIME,
		/** �e���� */
		OYAGIME,
		/** ���v */
		SENPAI,
		/** �T�C�U�� */
		SAIFURI,
		/** ���� */
		RYUUKYOKU,
		/** ���� */
		NAGASHI,
		/** �c�� */
		TSUMO,
		/** �̔v�I�� */
		SUTEHAISELECT,
		/** �c�������� */
		TSUMOAGARI,
		/** �̔v */
		SUTEHAI,
		/** ���[�` */
		REACH,
		/** ���� */
		RON,
		/** �|�� */
		PON,
		/** �`�[ */
		CHII,
		/** ���� */
		MINKAN,
		/** �Þ� */
		ANKAN
	}

	/**
	 * �C�x���g���������܂��B
	 * 
	 * @param eid
	 *            �C�x���gID
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑ΏۂƂȂ�����
	 * @return �C�x���gID
	 */
	EID event(EID eid, int fromKaze, int toKaze);

	/**
	 * �̔v�̃C���f�b�N�X���擾���܂��B
	 * 
	 * @return �̔v�̃C���f�b�N�X
	 */
	int getSutehaiIdx();
}
