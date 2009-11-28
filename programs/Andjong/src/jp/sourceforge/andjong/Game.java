package jp.sourceforge.andjong;

import jp.sourceforge.andjong.mahjong.Mahjong;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Game extends Activity {
	private static final String TAG = "Andjong";

	private AndjongView mAndjongView;

	Mahjong mMahjong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		// �^�C�g����\�����Ȃ��悤�ɂ���B
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// �t���X�N���[���ɂ���B
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mAndjongView = new AndjongView(this);
		setContentView(mAndjongView);
		mAndjongView.requestFocus();

		mMahjong = new Mahjong();
		mMahjong.setAndjongView(mAndjongView);
		new Thread(mMahjong).start();
	}
}
