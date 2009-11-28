/***
 * Excerpted from "Hello, Android!",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/eband for more book information.
 ***/

package jp.sourceforge.andjong;

import static jp.sourceforge.andjong.mahjong.Hai.*;
import jp.sourceforge.andjong.R;
import jp.sourceforge.andjong.mahjong.EventIF;
import jp.sourceforge.andjong.mahjong.Fuuro;
import jp.sourceforge.andjong.mahjong.Hai;
import jp.sourceforge.andjong.mahjong.InfoUI;
import jp.sourceforge.andjong.mahjong.Kawa;
import jp.sourceforge.andjong.mahjong.Mahjong;
import jp.sourceforge.andjong.mahjong.PlayerAction;
import jp.sourceforge.andjong.mahjong.Sai;
import jp.sourceforge.andjong.mahjong.SuteHai;
import jp.sourceforge.andjong.mahjong.Tehai;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class AndjongView extends View implements EventIF {
	private static final String TAG = "Andjong";

	private static final int ID = 42;

	private final Game game;

	private boolean HaiSelectStatus;

	public AndjongView(Context context) {

		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);

		// ...
		setId(ID);

		initImage(getResources());
	}

	private class DrawItem {
		/** ��ԂȂ� */
		private static final int STATE_NONE = 0;
		/** �ǂ̊J�n */
		private static final int STATE_KYOKU_START = 1;
		/** �v���C */
		private static final int STATE_PLAY = 2;
		private static final int STATE_SUTEHAI_MACHI = 3;
		/** ���� */
		private static final int STATE_RYUUKYOKU = 4;

		/** ��� */
		int mState = STATE_NONE;

		/**
		 * ��Ԃ�ݒ肷��B
		 *
		 * @param state
		 *            ���
		 */
		synchronized void setState(int state) {
			this.mState = state;
		}

		/**
		 * ��Ԃ��擾����B
		 *
		 * @return ���
		 */
		synchronized int getState() {
			return mState;
		}

		class PlayerInfo {
			/** ��v */
			Tehai mTehai = new Tehai();
			Kawa mKawa = new Kawa();
			Hai mTsumoHai;
			/** �_�_ */
			int mTenbo;
		}

		PlayerInfo mPlayerInfos[] = new PlayerInfo[4];

		/** ���[�`�_�̐� */
		int mReachbou = 0;

		/** �{�� */
		int mHonba = 0;

		boolean mIsDebug = false;

		/** �C�x���gID */
		EID eid;

		/** ��v */
		//Tehai tehais[] = new Tehai[4];

		/** �� */
		//Kawa kawas[] = new Kawa[4];

		{
			for (int i = 0; i < 4; i++) {
				mPlayerInfos[i] = new PlayerInfo();
				//tehais[i] = new Tehai();
				//kawas[i] = new Kawa();
			}
		}

		/** �c���v */
		Hai tsumoHai = new Hai();
		int tsumoKaze = 5;

		Hai tsumoHais[] = new Hai[4];
	}

	private DrawItem mDrawItem = new DrawItem();

	private Bitmap[] mHaiImage;
	private int mHaiImageHeight;
	private int mHaiImageWidth;

	private Bitmap mHaiUraImage;

	private Bitmap[] mHaiHorizontalImage;

	private Bitmap mHaiHideImage;

	private Bitmap mTenbou1000Image;
	private Bitmap mTenbou100Image;

	private void initImage(Resources res) {
		mHaiImage = new Bitmap[Hai.ID_MAX + 1];

		mHaiImage[0] = BitmapFactory.decodeResource(res, R.drawable.hai_00_wan_1);
		mHaiImage[1] = BitmapFactory.decodeResource(res, R.drawable.hai_01_wan_2);
		mHaiImage[2] = BitmapFactory.decodeResource(res, R.drawable.hai_02_wan_3);
		mHaiImage[3] = BitmapFactory.decodeResource(res, R.drawable.hai_03_wan_4);
		mHaiImage[4] = BitmapFactory.decodeResource(res, R.drawable.hai_04_wan_5);
		mHaiImage[5] = BitmapFactory.decodeResource(res, R.drawable.hai_05_wan_6);
		mHaiImage[6] = BitmapFactory.decodeResource(res, R.drawable.hai_06_wan_7);
		mHaiImage[7] = BitmapFactory.decodeResource(res, R.drawable.hai_07_wan_8);
		mHaiImage[8] = BitmapFactory.decodeResource(res, R.drawable.hai_08_wan_9);

		mHaiImage[9] = BitmapFactory.decodeResource(res, R.drawable.hai_09_pin_1);
		mHaiImage[10] = BitmapFactory.decodeResource(res, R.drawable.hai_10_pin_2);
		mHaiImage[11] = BitmapFactory.decodeResource(res, R.drawable.hai_11_pin_3);
		mHaiImage[12] = BitmapFactory.decodeResource(res, R.drawable.hai_12_pin_4);
		mHaiImage[13] = BitmapFactory.decodeResource(res, R.drawable.hai_13_pin_5);
		mHaiImage[14] = BitmapFactory.decodeResource(res, R.drawable.hai_14_pin_6);
		mHaiImage[15] = BitmapFactory.decodeResource(res, R.drawable.hai_15_pin_7);
		mHaiImage[16] = BitmapFactory.decodeResource(res, R.drawable.hai_16_pin_8);
		mHaiImage[17] = BitmapFactory.decodeResource(res, R.drawable.hai_17_pin_9);

		mHaiImage[18] = BitmapFactory.decodeResource(res, R.drawable.hai_18_sou_1);
		mHaiImage[19] = BitmapFactory.decodeResource(res, R.drawable.hai_19_sou_2);
		mHaiImage[20] = BitmapFactory.decodeResource(res, R.drawable.hai_20_sou_3);
		mHaiImage[21] = BitmapFactory.decodeResource(res, R.drawable.hai_21_sou_4);
		mHaiImage[22] = BitmapFactory.decodeResource(res, R.drawable.hai_22_sou_5);
		mHaiImage[23] = BitmapFactory.decodeResource(res, R.drawable.hai_23_sou_6);
		mHaiImage[24] = BitmapFactory.decodeResource(res, R.drawable.hai_24_sou_7);
		mHaiImage[25] = BitmapFactory.decodeResource(res, R.drawable.hai_25_sou_8);
		mHaiImage[26] = BitmapFactory.decodeResource(res, R.drawable.hai_26_sou_9);

		mHaiImage[27] = BitmapFactory.decodeResource(res, R.drawable.hai_27_ton);
		mHaiImage[28] = BitmapFactory.decodeResource(res, R.drawable.hai_28_nan);
		mHaiImage[29] = BitmapFactory.decodeResource(res, R.drawable.hai_29_sha);
		mHaiImage[30] = BitmapFactory.decodeResource(res, R.drawable.hai_30_pei);

		mHaiImage[31] = BitmapFactory.decodeResource(res, R.drawable.hai_31_haku);
		mHaiImage[32] = BitmapFactory.decodeResource(res, R.drawable.hai_32_hatsu);
		mHaiImage[33] = BitmapFactory.decodeResource(res, R.drawable.hai_33_chun);

		mHaiImageHeight = mHaiImage[0].getHeight();
		mHaiImageWidth = mHaiImage[0].getWidth();

		mHaiUraImage = BitmapFactory.decodeResource(res, R.drawable.hai_ura);

		mHaiHorizontalImage = new Bitmap[Hai.ID_MAX + 1];

		for (int i = 0; i < mHaiHorizontalImage.length; i++) {
			mHaiHorizontalImage[i] = createHorizontalBitmap(mHaiImage[i]);
		}

		mHaiHideImage = BitmapFactory.decodeResource(res, R.drawable.hai_hide);

		mTenbou100Image = BitmapFactory.decodeResource(res, R.drawable.tenbou_100);
		mTenbou1000Image = BitmapFactory.decodeResource(res, R.drawable.tenbou_1000);
	}

	private Bitmap createHorizontalBitmap(Bitmap verticalImage) {
		int height = verticalImage.getWidth();
		int width = verticalImage.getHeight();
		Bitmap horizontalImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(horizontalImage);
		canvas.rotate(270, 0, 0);
		canvas.drawBitmap(verticalImage, -height, 0, null);
		return horizontalImage;
	}

	private static final int PLACE_PLAYER = 0;
	private static final int PLACE_KAMICHA = 1;
	private static final int PLACE_TOIMEN = 2;
	private static final int PLACE_SHIMOCHA = 3;

	private static final int KAWA_TEHAI_AREA_WIDTH = 320;
	private static final int KAWA_TEHAI_AREA_HEIGHT = 85;

	private static final int TEHAI_LEFT = 2;
	private static final int TEHAI_TOP = 47;

	private static final int KAWA_LEFT = 49;
	private static final int KAWA_TOP = 0;

	private static final int KAWA_TEHAI_AREA_PLAYER_LEFT = 0;
	private static final int KAWA_TEHAI_AREA_PLAYER_TOP = 321;

	private static final int KAWA_TEHAI_AREA_TOIMEN_LEFT = 0;
	private static final int KAWA_TEHAI_AREA_TOIMEN_TOP = 0;

	private static final int KAWA_TEHAI_AREA_KAMICHA_LEFT = 235;
	private static final int KAWA_TEHAI_AREA_KAMICHA_TOP = 47;

	private static final int KAWA_TEHAI_AREA_SHIMOCHA_LEFT = 0;
	private static final int KAWA_TEHAI_AREA_SHIMOCHA_TOP = 38;

	private static final int KYOKU_LEFT = 160;
	private static final int KYOKU_TOP = 85;

	private static final int TENBO_PLAYER_LEFT = 160;
	private static final int TENBO_PLAYER_TOP = 125;

	private static final int TENBO_TOIMEN_LEFT = 160;
	private static final int TENBO_TOIMEN_TOP = 105;

	private static final int TENBO_KAMICHA_LEFT = 197;
	private static final int TENBO_KAMICHA_TOP = 115;

	private static final int TENBO_SHIMOCHA_LEFT = 123;
	private static final int TENBO_SHIMOCHA_TOP = 115;

	private static final int DORAS_LEFT = 112;
	private static final int DORAS_TOP = 153;

	private static final int TENBOU_01000_MIN_IMAGE_LEFT = 123 - 16;
	private static final int TENBOU_01000_MIN_IMAGE_TOP = 140;

	private static final int TENBOU_00100_MIN_IMAGE_LEFT = 197 - 16;
	private static final int TENBOU_00100_MIN_IMAGE_TOP = 140;

	private static final int MESSAGE_AREA_LEFT = 87;
	private static final int MESSAGE_AREA_TOP = 178;
	private static final int MESSAGE_AREA_RIGHT = MESSAGE_AREA_LEFT + 146;
	private static final int MESSAGE_AREA_BOTTOM = MESSAGE_AREA_TOP + 141;

	private Bitmap getKawaTehaiAreaImage(Tehai tehai, Kawa kawa, int place, int kaze, boolean isPlayer, Hai tsumoHai) {
		int width;
		int height;
		Bitmap image;
		Canvas canvas;

		switch (place) {
		case PLACE_PLAYER:
			width = KAWA_TEHAI_AREA_WIDTH;
			height = KAWA_TEHAI_AREA_HEIGHT;
			image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			canvas = new Canvas(image);
			break;
		case PLACE_KAMICHA:
			width = KAWA_TEHAI_AREA_HEIGHT;
			height = KAWA_TEHAI_AREA_WIDTH;
			image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			canvas = new Canvas(image);
			canvas.rotate(270, 0, 0);
			canvas.translate(-height, 0);
			break;
		case PLACE_TOIMEN:
			width = KAWA_TEHAI_AREA_WIDTH;
			height = KAWA_TEHAI_AREA_HEIGHT;
			image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			canvas = new Canvas(image);
			canvas.rotate(180, 0, 0);
			canvas.translate(-width, -height);
			break;
		case PLACE_SHIMOCHA:
			width = KAWA_TEHAI_AREA_HEIGHT;
			height = KAWA_TEHAI_AREA_WIDTH;
			image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			canvas = new Canvas(image);
			canvas.rotate(90, 0, 0);
			canvas.translate(0, -width);
			break;
		default:
			return null;
		}

		drawKawa(KAWA_LEFT, KAWA_TOP, canvas, kawa, null);

		if ((mInfoUi.getManKaze() == kaze)) {
	//	if ((mInfoUi.getManKaze() == kaze) && (drawItem.tsumoKaze == kaze)) {
			drawTehai(TEHAI_LEFT, TEHAI_TOP, canvas, tehai, tsumoHai, kaze, selectSutehaiIdx, isPlayer);
		} else {
			drawTehai(TEHAI_LEFT, TEHAI_TOP, canvas, tehai, tsumoHai, kaze, 15, isPlayer);
		}

		return image;
	}

	private void drawKawa(int left, int top, Canvas canvas, Kawa kawa,
			Paint paint) {
		int leftTemp = left;
		SuteHai[] suteHais = kawa.getSuteHais();
		int kawaLength = kawa.getSuteHaisLength();
		boolean reachFlag = false;
		for (int i = 0; i < kawaLength; i++) {
			if (i == 12) {
				left = leftTemp;
				top += mHaiImageHeight;
			}

			if (suteHais[i].isReach() || reachFlag) {
				if (suteHais[i].isNaki()) {
					reachFlag = true;
				} else {
					canvas.drawBitmap(mHaiHorizontalImage[suteHais[i].getId()], left, top + ((mHaiImageHeight - mHaiImageWidth) / 2), paint);
					left += mHaiImageHeight - mHaiImageWidth;
					reachFlag = false;
				}
			} else {
				if (!suteHais[i].isNaki()) {
					canvas.drawBitmap(mHaiImage[suteHais[i].getId()], left, top, paint);
				}
			}

			left += mHaiImageWidth;
		}
	}

	private static final int FUURO_LEFT = 296;

	private void drawTehai(int left, int top, Canvas canvas, Tehai tehai, Hai tsumoHai, int kaze, int select, boolean isPlayer) {
		top += 15;
		boolean isDisp = isPlayer || mDrawItem.mIsDebug;

		Hai[] jyunTehai = tehai.getJyunTehai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		int width = mHaiImage[0].getWidth();
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (tsumoHai != null && mDrawItem.mState == DrawItem.STATE_SUTEHAI_MACHI) {
				if (i == skipIdx) {
					continue;
				}
			}
			if (i == select) {
				canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top - 10, null);
			} else {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top, null);
				} else {
					canvas.drawBitmap(mHaiHideImage, left + (width * i), top, null);
				}
			}
		}

		Log.d(this.getClass().getName(), "print, tsumoKaze = " + mDrawItem.tsumoKaze + ", id = " + mDrawItem.tsumoHai);
		if (tsumoHai != null) {
			if ((select >= jyunTehaiLength) && (mDrawItem.mState != DrawItem.STATE_SUTEHAI_MACHI)) {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[tsumoHai.getId()], left + ((width * jyunTehaiLength) + 5), top - 10, null);
				} else {
					canvas.drawBitmap(mHaiHideImage, left + ((width * jyunTehaiLength) + 5), top, null);
				}
			} else {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[tsumoHai.getId()], left + ((width * jyunTehaiLength) + 5), top, null);
				} else {
					canvas.drawBitmap(mHaiHideImage, left + ((width * jyunTehaiLength) + 5), top, null);
				}
			}
		}

		int fuuroLeft = FUURO_LEFT;
		int fuuroNums = tehai.getFuuroNums();
		if (fuuroNums > 0) {
			Fuuro[] fuuros = tehai.getFuuros();
			for (int i = 0; i < fuuroNums; i++) {
				Hai hais[] = fuuros[i].getHai();
				int type = fuuros[i].getType();
				int relation = fuuros[i].getRelation();

				if (relation == Mahjong.RELATION_KAMICHA) {
					fuuroLeft -= mHaiImageHeight;
					canvas.drawBitmap(mHaiHorizontalImage[hais[2].getId()], fuuroLeft, top + 4, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[1].getId()], fuuroLeft, top, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[0].getId()], fuuroLeft, top, null);
				} else if (relation == Mahjong.RELATION_TOIMEN) {
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[2].getId()], fuuroLeft, top, null);
					fuuroLeft -= mHaiImageHeight;
					canvas.drawBitmap(mHaiHorizontalImage[hais[1].getId()], fuuroLeft, top + 4, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[0].getId()], fuuroLeft, top, null);
				} else {
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[2].getId()], fuuroLeft, top, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[1].getId()], fuuroLeft, top, null);
					fuuroLeft -= mHaiImageHeight;
					canvas.drawBitmap(mHaiHorizontalImage[hais[0].getId()], fuuroLeft, top + 4, null);
				}
			}
		}
	}

	private void drawKyoku(int left, int top, Canvas canvas, int textSize) {
		int kyoku = mInfoUi.getkyoku();
		String kyokuString = null;
		switch (kyoku) {
		case Mahjong.KYOKU_TON_1:
			kyokuString = "�����";
			break;
		case Mahjong.KYOKU_TON_2:
			kyokuString = "�����";
			break;
		case Mahjong.KYOKU_TON_3:
			kyokuString = "���O��";
			break;
		case Mahjong.KYOKU_TON_4:
			kyokuString = "���l��";
			break;
		}

		drawString(left, top, canvas, textSize, Color.WHITE, kyokuString);
	}

	private void drawString(int left, int top, Canvas canvas, int textSize, int color, String string) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(color);
		paint.setTextSize(textSize);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(string, left, top - paint.getFontMetrics().top, paint);
	}

	private void drawDoras(int left, int top, Canvas canvas) {
		Hai doras[] = mInfoUi.getDoras();
		for (int i = 0; i < 5; i++) {
			if (i < doras.length) {
				canvas.drawBitmap(mHaiImage[doras[i].getId()], left + (i * mHaiImageWidth), top, null);
			} else {
				canvas.drawBitmap(mHaiUraImage, left + (i * mHaiImageWidth), top, null);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		synchronized (mDrawItem) {
			// �w�i��`�悷��
			Paint background = new Paint();
			background.setColor(getResources().getColor(R.color.puzzle_background));
			canvas.drawRect(0, 0, getWidth(), getHeight(), background);

			if (mDrawItem.mState == DrawItem.STATE_NONE) {
				return;
			} else if (mDrawItem.mState == DrawItem.STATE_KYOKU_START) {
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
				paint.setColor(Color.DKGRAY);
				RectF rect = new RectF(MESSAGE_AREA_LEFT, MESSAGE_AREA_TOP, MESSAGE_AREA_RIGHT, MESSAGE_AREA_BOTTOM);
				canvas.drawRoundRect(rect, 5, 5, paint);
				drawKyoku(160, 254 - 20, canvas, 30);
				return;
			} else if (mDrawItem.mState == DrawItem.STATE_RYUUKYOKU) {
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
				paint.setColor(Color.DKGRAY);
				RectF rect = new RectF(MESSAGE_AREA_LEFT, MESSAGE_AREA_TOP, MESSAGE_AREA_RIGHT, MESSAGE_AREA_BOTTOM);
				canvas.drawRoundRect(rect, 5, 5, paint);
				drawString(160, 254 - 20, canvas, 30, Color.WHITE, "����");
				return;
			}

			if (true) {
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
				paint.setColor(Color.DKGRAY);
				RectF rect = new RectF(MESSAGE_AREA_LEFT, MESSAGE_AREA_TOP, MESSAGE_AREA_RIGHT, MESSAGE_AREA_BOTTOM);
				canvas.drawRoundRect(rect, 5, 5, paint);
			}

			PlayerAction mPlayerAction = mInfoUi.getPlayerAction();
			int state;
			synchronized (mPlayerAction) {
				state = mPlayerAction.getState();
			}
			if (state == PlayerAction.STATE_ACTION_WAIT) {
				drawString(160, 254 - 20, canvas, 30, Color.WHITE, "MENU");
			}

			drawKyoku(KYOKU_LEFT, KYOKU_TOP, canvas, 18);

			drawDoras(DORAS_LEFT, DORAS_TOP, canvas);

			canvas.drawBitmap(mTenbou1000Image, TENBOU_01000_MIN_IMAGE_LEFT, TENBOU_01000_MIN_IMAGE_TOP, null);
			canvas.drawBitmap(mTenbou100Image, TENBOU_00100_MIN_IMAGE_LEFT, TENBOU_00100_MIN_IMAGE_TOP, null);

			int manKaze = mInfoUi.getManKaze();
			int dispKaze[] = { 0, 1, 2, 3 };
			for (int i = 0; i < 4; i++) {
				dispKaze[i] = manKaze;
				manKaze++;
				manKaze %= 4;
			}

			drawString(TENBO_PLAYER_LEFT, TENBO_PLAYER_TOP, canvas, 12, Color.WHITE, new Integer(mDrawItem.mPlayerInfos[dispKaze[0]].mTenbo).toString());
			drawString(TENBO_KAMICHA_LEFT, TENBO_KAMICHA_TOP, canvas, 12, Color.WHITE, new Integer(mDrawItem.mPlayerInfos[dispKaze[1]].mTenbo).toString());
			drawString(TENBO_TOIMEN_LEFT, TENBO_TOIMEN_TOP, canvas, 12, Color.WHITE, new Integer(mDrawItem.mPlayerInfos[dispKaze[2]].mTenbo).toString());
			drawString(TENBO_SHIMOCHA_LEFT, TENBO_SHIMOCHA_TOP, canvas, 12, Color.WHITE, new Integer(mDrawItem.mPlayerInfos[dispKaze[3]].mTenbo).toString());

			Bitmap test2 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[0]].mTehai, mDrawItem.mPlayerInfos[dispKaze[0]].mKawa, PLACE_PLAYER, dispKaze[0], true, mDrawItem.tsumoHais[dispKaze[0]]);
			canvas.drawBitmap(test2, KAWA_TEHAI_AREA_PLAYER_LEFT, KAWA_TEHAI_AREA_PLAYER_TOP, null);

			Bitmap test3 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[1]].mTehai, mDrawItem.mPlayerInfos[dispKaze[1]].mKawa, PLACE_KAMICHA, dispKaze[1], false, mDrawItem.tsumoHais[dispKaze[1]]);
			canvas.drawBitmap(test3, KAWA_TEHAI_AREA_KAMICHA_LEFT, KAWA_TEHAI_AREA_KAMICHA_TOP, null);

			Bitmap test = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[2]].mTehai, mDrawItem.mPlayerInfos[dispKaze[2]].mKawa, PLACE_TOIMEN, dispKaze[2], false, mDrawItem.tsumoHais[dispKaze[2]]);
			canvas.drawBitmap(test, KAWA_TEHAI_AREA_TOIMEN_LEFT, KAWA_TEHAI_AREA_TOIMEN_TOP, null);

			Bitmap test4 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[3]].mTehai, mDrawItem.mPlayerInfos[dispKaze[3]].mKawa, PLACE_SHIMOCHA, dispKaze[3], false, mDrawItem.tsumoHais[dispKaze[3]]);
			canvas.drawBitmap(test4, KAWA_TEHAI_AREA_SHIMOCHA_LEFT, KAWA_TEHAI_AREA_SHIMOCHA_TOP, null);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		PlayerAction mPlayerAction = mInfoUi.getPlayerAction();
		int state;
		synchronized (mPlayerAction) {
			state = mPlayerAction.getState();
		}
		if (state == PlayerAction.STATE_ACTION_WAIT) {
			int tx = (int)event.getX();
			int ty = (int)event.getY();
			int act_evt = event.getAction();
			if (act_evt == MotionEvent.ACTION_DOWN) {
				if (tx >= MESSAGE_AREA_LEFT && tx <= MESSAGE_AREA_RIGHT) {
					if (ty >= MESSAGE_AREA_TOP && ty <= MESSAGE_AREA_BOTTOM) {
						showAlertDialog("MENU");
					}
				}
			}
			return true;
		}
/*
 		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

*/
		/* X,Y���W�̎擾 */
		int tx = (int)event.getX();
		int ty = (int)event.getY();
		int act_evt = event.getAction();

		/* Y���W�̔���(�v�̍����̊�) */
		if ((397 <= ty) && (ty <= 426) )
		{
			/* X���W�̔���(�v1�̉��͈̔�) */
			if ((3 <= tx) && (tx <= 21) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 0;
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v2�̉��͈̔�) */
			else if ((22 <= tx) && (tx <= 40) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 1;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v3�̉��͈̔�) */
			else if ((41 <= tx) && (tx <= 59) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 2;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v4�̉��͈̔�) */
			else if ((60 <= tx) && (tx <= 78) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 3;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v5�̉��͈̔�) */
			else if ((79 <= tx) && (tx <= 97) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 4;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v6�̉��͈̔�) */
			else if ((98 <= tx) && (tx <= 116) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 5;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v7�̉��͈̔�) */
			else if ((117 <= tx) && (tx <= 136) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 6;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v8�̉��͈̔�) */
			else if ((137 <= tx) && (tx <= 155) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 7;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v9�̉��͈̔�) */
			else if ((156 <= tx) && (tx <= 174) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 8;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v10�̉��͈̔�) */
			else if ((175 <= tx) && (tx <= 191) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 9;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v11�̉��͈̔�) */
			else if ((192 <= tx) && (tx <= 211) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 10;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v12�̉��͈̔�) */
			else if ((212 <= tx) && (tx <= 230) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 11;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v13�̉��͈̔�) */
			else if ((231 <= tx) && (tx <= 249) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 12;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			/* X���W�̔���(�v14�̉��͈̔�) */
			else if ((256 <= tx) && (tx <= 274) )
			{
				/* �I��v�̔ԍ���ێ� */
				selectSutehaiIdx = 13;

				/* �v��I����� */
				HaiSelectStatus = true;
			}
			else
			{
				// do nothing
			}
		}
		else
		{
			/* �v���I����ԁE�C�x���gACTION_UP�EY���W��385�ȉ��̎��A�v���̂Ă�ꂽ�Ƃ��� */
			if ((HaiSelectStatus == true) && (act_evt == MotionEvent.ACTION_UP) && (ty <= 385))
			{
				game.mahjong.setSutehaiIdx(selectSutehaiIdx);
				/* �v���I����Ԃɂ��� */
				HaiSelectStatus = false;
			}
			else if (act_evt == MotionEvent.ACTION_MOVE)
			{
				// ACTION_MOVE�C�x���g�̏ꍇ�́A�v�̏�Ԃ�ύX���Ȃ��B(�������������Ȃ�)
			}
			else
			{
				/* �v���I����Ԃɂ��� */
				HaiSelectStatus = false;
			}
		}
		/* �ĕ`��̎w�� */
		invalidate();
		return true;
	}


	private int selectSutehaiIdx = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event=" + event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			selectSutehaiIdx = 0;
			if(mDrawItem.mIsDebug){
				mDrawItem.mIsDebug = false;
			}else{
				mDrawItem.mIsDebug = true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			selectSutehaiIdx = 100;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			selectSutehaiIdx--;
			if (selectSutehaiIdx < 0) {
				selectSutehaiIdx = 13;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			selectSutehaiIdx++;
			if (selectSutehaiIdx > 13) {
				selectSutehaiIdx = 0;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			game.mahjong.setSutehaiIdx(selectSutehaiIdx);
			PlayerAction playerAction = mInfoUi.getPlayerAction();
			Log.d(this.getClass().getName(), "mPlayerAction.actionNotifyAll()");
			playerAction.actionNotifyAll();
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		invalidate();
		return true;
	}

	/** InfoUI�I�u�W�F�N�g */
	private InfoUI mInfoUi;

	/** ��v */
	private Tehai tehai = new Tehai();

	/** �� */
	private Kawa kawa = new Kawa();

	/** �̔v�̃C���f�b�N�X */
	private int sutehaiIdx = 0;

	private String name;

	public String getName() {
		return name;
	}

	/**
	 * UI������������B
	 *
	 * @param infoUi
	 *            InfoUI
	 */
	public void init(InfoUI infoUi, String name) {
		this.mInfoUi = infoUi;
		this.name = name;
	}

    private void showAlertDialog(String message) {
      AlertDialog.Builder builder = new AlertDialog.Builder(game);
      builder.setMessage(message)
             .setCancelable(false)
             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                                 }
             });
      AlertDialog alert = builder.create();
      alert.show();
    }

    private int skipIdx = 0;

    /** �ǂ̊J�n�̑҂����� */
    private static final int KYOKU_START_WAIT_TIME = 2000;

    /** �i�s�̑҂����� */
	private static int PROGRESS_WAIT_TIME = 300;

    /** ���ǂ̑҂����� */
    private static final int RYUUKYOKU_WAIT_TIME = 2000;

	/**
	 * �C�x���g����������B
	 *
	 * @param fromKaze
	 *            �C�x���g�𔭍s������
	 * @param toKaze
	 *            �C�x���g�̑Ώۂ̉�
	 * @param eid
	 *            �C�x���gID
	 */
	public EID event(EID eid, int fromKaze, int toKaze) {
		switch (eid) {
		case PROGRESS_WAIT:// �i�s�҂�
			try {
				Thread.sleep(PROGRESS_WAIT_TIME, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			break;
		case BASHOGIME:// �ꏊ����
			// �����\�����Ȃ��B
			break;
		case OYAGIME:// �e����
			// ���̒i�K�ŏ���������Ă������ݒ肷��B
			mDrawItem.setState(DrawItem.STATE_NONE);
			synchronized (mDrawItem) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mDrawItem.mPlayerInfos[i].mTenbo = mInfoUi.getTenbou(i);
				}
				mDrawItem.mReachbou = mInfoUi.getReachbou();
				mDrawItem.mHonba = mInfoUi.getHonba();
			}
			break;
		case SENPAI:// ���v
			// �����\�����Ȃ��B
			break;
		case SAIFURI:// �T�C�U��
			// �T�C�U����ǂ̊J�n�ƍl����B
			mDrawItem.setState(DrawItem.STATE_KYOKU_START);
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(KYOKU_START_WAIT_TIME, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			break;
		case HAIPAI:
			// ��v��ݒ肵�āA�v���C��Ԃɂ���B
			mDrawItem.setState(DrawItem.STATE_PLAY);
			synchronized (mDrawItem) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
				}
			}
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case RYUUKYOKU:// ����
			// �T�C�U����ǂ̊J�n�ƍl����B
			mDrawItem.setState(DrawItem.STATE_RYUUKYOKU);
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(RYUUKYOKU_WAIT_TIME, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			break;
		case NAGASHI:// ����
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case TSUMO:// �c��
			/*
			this.post(new Runnable() {
				public void run() {
					String[] yaku;
					Tehai tehai = new Tehai();
					tehai.addJyunTehai(new Hai(1));
					tehai.addJyunTehai(new Hai(1));
					tehai.addJyunTehai(new Hai(1));
					tehai.addJyunTehai(new Hai(2));
					tehai.addJyunTehai(new Hai(2));
					tehai.addJyunTehai(new Hai(2));
					tehai.addJyunTehai(new Hai(3));
					tehai.addJyunTehai(new Hai(3));
					tehai.addJyunTehai(new Hai(3));
					tehai.addJyunTehai(new Hai(4));
					tehai.addJyunTehai(new Hai(4));
					tehai.addJyunTehai(new Hai(4));
					tehai.addJyunTehai(new Hai(5));
					Hai addHai = new Hai(5);
					yaku = mInfoUi.getYakuName(tehai, mInfoUi.getSuteHai());
					showAlertDialog("�e�X�g" + yaku[0]);
				}
			});
			*/
			// ��v��\�����܂��B
			printJyunTehai(tehai);

//			if (drawItem.isOnDraw == false) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
				}
				mDrawItem.tsumoKaze = mInfoUi.getJikaze();
				Log.d(TAG, "tsumoKaze = " + mDrawItem.tsumoKaze);
				mDrawItem.tsumoHai = mInfoUi.getTsumoHai();

				mDrawItem.tsumoHais[mInfoUi.getJikaze()] = mInfoUi.getTsumoHai();

				this.postInvalidate(0, 0, getWidth(), getHeight());
				/*
				 * this.post(new Runnable() { public void run() { selRect.set(0,
				 * 0, getWidth(), getHeight()); invalidate(selRect); } });
				 */
//			}
			Log.d(this.getClass().getName(), "tsumo, tsumoKaze = " + mDrawItem.tsumoKaze + ", id = " + mDrawItem.tsumoHai.getId());
			/*
			 * if (isDraw == false) { infoUi.copyTehai(printTehai,
			 * infoUi.getJikaze()); isPrintTehai = true; this.post(new
			 * Runnable() { public void run() { selRect.set(0, 0, getWidth(),
			 * getHeight()); invalidate(selRect); } }); }
			 */

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((mInfoUi.getTsumoHai()).getId()));
			break;
		case TSUMOAGARI:// �c��������
			System.out.print("[" + jikazeToString(mInfoUi.getJikaze())
					+ "][�c��������]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((mInfoUi.getTsumoHai()).getId()));

			this.post(new Runnable() {
				public void run() {
					showAlertDialog("�c��");
				}
			});
			break;
		case RIHAI_WAIT:
			skipIdx = mInfoUi.getSutehaiIdx();
			mDrawItem.mState = DrawItem.STATE_SUTEHAI_MACHI;
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(200, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			selectSutehaiIdx = 13;
			mDrawItem.mState = DrawItem.STATE_PLAY;
			break;
		case SUTEHAI:// �̔v
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == mInfoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(mInfoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				Log.d(this.getClass().getName(), "sutehai");
				{
					for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
						mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
						mInfoUi.copyKawa(mDrawItem.mPlayerInfos[i].mKawa, i);
					}
					mDrawItem.tsumoKaze = 5;
					mDrawItem.tsumoHai = null;
					mDrawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
				}
				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case SUTEHAISELECT:
			if (fromKaze == mInfoUi.getJikaze()) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
				}
					mDrawItem.tsumoKaze = 5;
					mDrawItem.tsumoHai = null;
					mDrawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
			}
			break;
		case PON:// �|��
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == mInfoUi.getJikaze()) {
				{
					for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
						mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
						mInfoUi.copyKawa(mDrawItem.mPlayerInfos[i].mKawa, i);
					}
					mDrawItem.tsumoKaze = 5;
					mDrawItem.tsumoHai = null;
					mDrawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
				}
				/*
				this.post(new Runnable() {
					public void run() {
						Dialog a = new Dialog(game);
						a.show();
					}
				});
				*/
				System.out.print("[" + jikazeToString(mInfoUi.getJikaze())
						+ "][�|��]");

				// ��v��\�����܂��B
				printJyunTehai(tehai);

				System.out.println();

				System.out.print("[" + jikazeToString(mInfoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case REACH:
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == mInfoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(mInfoUi.getJikaze())
						+ "][���[�`]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case RON:// ����
			this.post(new Runnable() {
				public void run() {
					String[] yaku;
					yaku = mInfoUi.getYakuName(tehai, mInfoUi.getSuteHai());
					showAlertDialog("����" + yaku[0]);
				}
			});

			System.out
					.print("[" + jikazeToString(mInfoUi.getJikaze()) + "][����]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// ������v��\�����܂��B
			System.out.println(":" + idToString((mInfoUi.getSuteHai()).getId()));
			break;
		default:
			break;
		}

		return EID.NAGASHI;
	}

	public int getSutehaiIdx() {
		return sutehaiIdx;
	}

	/**
	 * ��v��\�����܂��B
	 * <p>
	 * TODO ���v��\�����邱�ƁB
	 * </p>
	 *
	 * @param tehai
	 *            ��v
	 */
	private void printJyunTehai(Tehai tehai) {
		mInfoUi.copyTehai(tehai, mInfoUi.getJikaze());
		Hai[] jyunTehai = tehai.getJyunTehai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++)
			System.out.print(idToString(jyunTehai[i].getId()));

		int minkousLength = tehai.getMinKousLength();
		Hai[][] minkous = tehai.getMinKous();
		for (int i = 0; i < minkousLength; i++) {
			System.out.print("[");
			System.out.print(idToString(minkous[i][0].getId()));
			System.out.print(idToString(minkous[i][1].getId()));
			System.out.print(idToString(minkous[i][2].getId()));
			System.out.print("]");
		}
	}

	/**
	 * �͂�\�����܂��B
	 *
	 * @param kawa
	 *            ��
	 */
	private void printKawa(Kawa kawa) {
		mInfoUi.copyKawa(kawa, mInfoUi.getJikaze());
		SuteHai[] SuteHai = kawa.getSuteHais();
		int kawaLength = kawa.getSuteHaisLength();
		for (int i = 0; i < kawaLength; i++)
			System.out.print(idToString(SuteHai[i].getId()));
	}

	/**
	 * �v�ԍ��𕶎���ɕϊ����܂�
	 *
	 * @param id
	 *            �v�ԍ�
	 * @return ������
	 */
	static public String idToString(int id) {
		switch (id) {
		case ID_WAN_1:
			return "��";
		case ID_WAN_2:
			return "��";
		case ID_WAN_3:
			return "�O";
		case ID_WAN_4:
			return "�l";
		case ID_WAN_5:
			return "��";
		case ID_WAN_6:
			return "�Z";
		case ID_WAN_7:
			return "��";
		case ID_WAN_8:
			return "��";
		case ID_WAN_9:
			return "��";
		case ID_PIN_1:
			return "�@";
		case ID_PIN_2:
			return "�A";
		case ID_PIN_3:
			return "�B";
		case ID_PIN_4:
			return "�C";
		case ID_PIN_5:
			return "�D";
		case ID_PIN_6:
			return "�E";
		case ID_PIN_7:
			return "�F";
		case ID_PIN_8:
			return "�G";
		case ID_PIN_9:
			return "�H";
		case ID_SOU_1:
			return "�P";
		case ID_SOU_2:
			return "�Q";
		case ID_SOU_3:
			return "�R";
		case ID_SOU_4:
			return "�S";
		case ID_SOU_5:
			return "�T";
		case ID_SOU_6:
			return "�U";
		case ID_SOU_7:
			return "�V";
		case ID_SOU_8:
			return "�W";
		case ID_SOU_9:
			return "�X";
		case ID_TON:
			return "��";
		case ID_NAN:
			return "��";
		case ID_SHA:
			return "��";
		case ID_PE:
			return "�k";
		case ID_HAKU:
			return "��";
		case ID_HATSU:
			return "�";
		case ID_CHUN:
			return "��";
		}

		return null;
	}

	/**
	 * �����𕶎���ɕϊ����܂��B
	 *
	 * @param jikaze
	 *            ����
	 * @return�@������
	 */
	static public String jikazeToString(int jikaze) {
		switch (jikaze) {
		case 0:
			return "��";
		case 1:
			return "��";
		case 2:
			return "��";
		case 3:
			return "�k";
		}

		return null;
	}

	/**
	 * �������Ă������\�����܂��B
	 *
	 * @param jikaze
	 *            ����
	 * @return�@
	 */
	public void jikazeToString(Hai addHai) {
		String[] yakuNames = this.mInfoUi.getYakuName(tehai, addHai);
		for (int i = 0; i < yakuNames.length; i++) {
			System.out.println(yakuNames[i]);
		}
	}
}