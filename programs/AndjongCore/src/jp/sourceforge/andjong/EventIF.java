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
		NAGASHI,
		/** �c�� */
		TSUMO,
		/** �c�������� */
		TSUMOAGARI,
		/** �̔v */
		SUTEHAI,
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
	 * @return
	 */
	EID event(EID eid, int fromKaze, int toKaze);
}
