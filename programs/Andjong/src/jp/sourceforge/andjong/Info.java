package jp.sourceforge.andjong;

/**
 * AI��UI��Game�N���X�̏���񋟂���N���X�ł��B
 * 
 * @author Yuji Urushibara
 * 
 */
public class Info {
	private Game game;
	
	public int sutehaiIdx;

	public Info(Game game) {
		this.game = game;
	}

	public void getTsumoHai(Hai tsumoHai) {
		tsumoHai.copy(game.tsumoHai);
	}
}
