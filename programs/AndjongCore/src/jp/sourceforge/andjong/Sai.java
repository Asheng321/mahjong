package jp.sourceforge.andjong;

import java.util.Random;

/**
 * �T�C�R�����Ǘ�����N���X
 * 
 * @author Yuji Urushibara
 * 
 */
public class Sai {
	/** �ԍ� */
	private int no;

	/**
	 * �ԍ����擾����B
	 * 
	 * @return �ԍ�
	 */
	public int getNo() {
		return no;
	}

	/**
	 * �T�C�R����U���Ĕԍ����擾����B
	 * 
	 * @return �ԍ�
	 */
	int saifuri() {
		return no = new Random().nextInt(6) + 1;
	}
}
