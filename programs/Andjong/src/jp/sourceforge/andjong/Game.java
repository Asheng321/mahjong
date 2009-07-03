package jp.sourceforge.andjong;

/**
 * �Q�[���̃n���h�����O���s���N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Game {
	/** �R */
	private Yama yama = new Yama();

	/** �v���C���[�̐l�� */
	private int playerNum;

	/** �v���C���[�̔z�� */
	private Player[] players;

	public void play() {
		// �R����v���܂��B
		yama.xipai();

		// �h����\�����Ă݂܂��B
		{
			Hai[] dora;
			dora = yama.getDora();
			System.out.println("dora.length = " + dora.length);
			for (int i = 0; i < dora.length; i++)
				System.out.println("dora = " + dora[i].id);
		}

		// �v���C���[�����������܂��B
		playerNum = 4;
		players = new Player[playerNum];
		for (int i = 0; i < playerNum; i++)
			players[i] = new Player(new AI());

		// �c�����܂��B
		for (int i = 0; i < playerNum; i++)
			for (int j = 0; j < 13; j++)
				players[i].tehai.addJyunTehai(yama.tsumo());

		// ����v��\�����܂��B
		for (int i = 0; i < playerNum; i++) {
			System.out.println("players[" + i + "]");
			for (int j = 0; j < players[i].tehai.jyunTehaiLength; j++)
				System.out.print(players[i].tehai.jyunTehai[j].id + ",");
			System.out.println();
		}

		// �͂Ɏ̂ĂĂ݂܂��B
		for (int i = 0; i < 5; i++) {
			players[0].kawa.add(players[0].tehai.jyunTehai[0]);
			players[0].tehai.removeJyunTehai(0);
		}

		// �͂�\�����Ă݂܂��B
		System.out.println("kawa");
		for (int i = 0; i < players[0].kawa.kawaLength; i++)
			System.out.print(players[0].kawa.hais[i].id + ",");
		System.out.println();

		{
			Tehai tehai = new Tehai();

			Hai[] minshun = new Hai[] { new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 2), new Hai(Hai.KIND_WAN | 3) };

			for (int i = 0; i < 5; i++)
				tehai.addMinshun(minshun);

			Hai[] minkou = new Hai[] { new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 1), new Hai(Hai.KIND_WAN | 1) };

			for (int i = 0; i < 5; i++)
				tehai.addMinkou(minkou);

			Hai[] minkan = new Hai[] { new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 1), new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 1) };

			for (int i = 0; i < 5; i++)
				tehai.addMinkan(minkan);

			Hai[] ankan = new Hai[] { new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 1), new Hai(Hai.KIND_WAN | 1),
					new Hai(Hai.KIND_WAN | 1) };

			for (int i = 0; i < 5; i++)
				tehai.addankan(ankan);
		}

		Hai tsumoHai;
		while (true) {
			tsumoHai = yama.tsumo();
			if (tsumoHai == null)
				break;
			System.out.println("tsumoHai = " + tsumoHai.id);
		}

		Hai rinshanHai;
		while (true) {
			rinshanHai = yama.rinshan();
			if (rinshanHai == null)
				break;
			System.out.println("rinshanHai = " + rinshanHai.id);
		}

		// �h����\�����Ă݂܂��B
		{
			Hai[] dora;
			dora = yama.getDoraAll();
			System.out.println("dora.length = " + dora.length);
			for (int i = 0; i < dora.length; i++)
				System.out.println("dora = " + dora[i].id);
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}
