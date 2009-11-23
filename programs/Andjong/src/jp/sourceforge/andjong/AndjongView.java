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

		initBaImage();

		initTenbouImage(getResources());
	}

	private class DrawItem {
		class PlayerInfo {
			int mState;
			Tehai mTehai;
			Kawa mKawa;
			Hai mTsumoHai;
		}
		PlayerInfo mPlayerInfos[] = new PlayerInfo[4];

		int mState;

		boolean mIsDebug = false;

		/** �C�x���gID */
		EID eid;

		/** ��v */
		Tehai tehais[] = new Tehai[4];

		/** �� */
		Kawa kawas[] = new Kawa[4];

		{
			for (int i = 0; i < 4; i++) {
				tehais[i] = new Tehai();
				kawas[i] = new Kawa();
			}
		}

		/** �c���v */
		Hai tsumoHai = new Hai();
		int tsumoKaze = 5;

		Hai tsumoHais[] = new Hai[4];
	}

	private DrawItem drawItem = new DrawItem();

	private Bitmap[] mHaiImage;
	private int mHaiImageHeight;
	private int mHaiImageWidth;

	private Bitmap mHaiUraImage;

	private Bitmap[] mHaiHorizontalImage;

	private Bitmap mHaiHideImage;

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

	private static final int DORA_IMAGE_LEFT = 1;
	private static final int DORA_IMAGE_TOP = 39;

	private int mDoraImageLeft;
	private int mDoraImageTop;
	private int mUraDoraImageLeft;
	private int mUraDoraImageTop;

	private Bitmap tenbou01000MinImage;
	private Bitmap tenbou00100MinImage;

	private static final int TENBOU_01000_MIN_IMAGE_LEFT = 1;
	private static final int TENBOU_01000_MIN_IMAGE_TOP = 27;

	private static final int TENBOU_00100_MIN_IMAGE_LEFT = 51;
	private static final int TENBOU_00100_MIN_IMAGE_TOP = 27;

	private void initTenbouImage(
			Resources res) {
		tenbou00100MinImage = BitmapFactory.decodeResource(res, R.drawable.tenbou_00100_min);
		tenbou01000MinImage = BitmapFactory.decodeResource(res, R.drawable.tenbou_01000_min);
	}

	private static final int BA_IMAGE_WIDTH = 110;
	private static final int BA_IMAGE_HEIGHT = 213;
	private static final int BA_IMAGE_LEFT = 105;
	private static final int BA_IMAGE_TOP = 107;

	private Bitmap mBaImage;

	private Canvas mBaCanvas;

	private void initBaImage() {
		mBaImage = Bitmap.createBitmap(BA_IMAGE_WIDTH, BA_IMAGE_HEIGHT, Bitmap.Config.ARGB_8888);
		mBaCanvas = new Canvas(mBaImage);

		mDoraImageLeft = DORA_IMAGE_LEFT;
		mDoraImageTop = DORA_IMAGE_TOP;
		mUraDoraImageLeft = DORA_IMAGE_LEFT;
		mUraDoraImageTop = DORA_IMAGE_TOP + mHaiImage[0].getHeight();
	}

	private void setBaImage() {
		Hai doras[] = mInfoUi.getDoras();
		for (int i = 0; i < 5; i++) {
			if (i < doras.length) {
				mBaCanvas.drawBitmap(mHaiImage[doras[i].getId()], mDoraImageLeft + (i * mHaiImage[0].getWidth()), mDoraImageTop, null);
			} else {
				mBaCanvas.drawBitmap(mHaiUraImage, mDoraImageLeft + (i * mHaiUraImage.getWidth()), mDoraImageTop, null);
			}
			mBaCanvas.drawBitmap(mHaiUraImage, mUraDoraImageLeft + (i * mHaiUraImage.getWidth()), mUraDoraImageTop, null);
		}

		mBaCanvas.drawBitmap(tenbou01000MinImage, TENBOU_01000_MIN_IMAGE_LEFT, TENBOU_01000_MIN_IMAGE_TOP, null);
		mBaCanvas.drawBitmap(tenbou00100MinImage, TENBOU_00100_MIN_IMAGE_LEFT, TENBOU_00100_MIN_IMAGE_TOP, null);

		/*
		Paint kazuPaint = new Paint();
		//Paint kazuPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		kazuPaint.setColor(getResources().getColor(R.color.puzzle_foreground));
		kazuPaint.setStyle(Style.FILL);
		kazuPaint.setTextSize(16);
		kazuPaint.setTypeface(Typeface.MONOSPACE);
		//kazuPaint.setTextSize(height * 0.75f);
		//kazuPaint.setTextScaleX(width / height);
		kazuPaint.setTextAlign(Paint.Align.CENTER);

		mBaCanvas.drawText("0", TENBOU_01000_MIN_IMAGE_LEFT + 40, TENBOU_01000_MIN_IMAGE_TOP + 10, kazuPaint);
		*/
	}

	/** �v���C���[���̈�̍��� */
	private static final int PLAYER_INFO_AREA_HEIGHT = 105;
	/** �v���C���[���̈�̍��� */
	private static final int PLAYER_INFO_AREA_WIDTH = 316;

	private static final int PLAYER_INFO_AREA_LEFT = 4;
	private static final int PLAYER_INFO_AREA_TOP = 319;

	private static final int KAWA_LEFT = 42;
	private static final int KAWA_TOP = 10;

	private static final int DEGREES_0 = 0;
	private static final int DEGREES_90 = 90;
	private static final int DEGREES_180 = 180;
	private static final int DEGREES_270 = 270;

	private Bitmap getKawaBitmap(Tehai tehai, Kawa kawa, int degrees, int kaze, boolean isPlayer, Hai tsumoHai) {
		int width;
		int height;

		if ((degrees == DEGREES_0) || (degrees == DEGREES_180)) {
			width = PLAYER_INFO_AREA_WIDTH;
			height = PLAYER_INFO_AREA_HEIGHT;
		} else {
			width = PLAYER_INFO_AREA_HEIGHT;
			height = PLAYER_INFO_AREA_WIDTH;
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		//canvas.rotate(-degrees, width / 2, height / 2);
		canvas.rotate(degrees, width / 2, height / 2);

		if ((degrees == DEGREES_0) || (degrees == DEGREES_180)) {
		} else {
			canvas.translate((width - height) / 2, -(width - height) / 2);
			//canvas.translate((width - height) / 2, (width - height) / 2);
		}

		drawKawa(KAWA_LEFT, KAWA_TOP, canvas, kawa, null);

		if ((mInfoUi.getManKaze() == kaze)) {
	//	if ((mInfoUi.getManKaze() == kaze) && (drawItem.tsumoKaze == kaze)) {
			drawTehai(0, 79, canvas, tehai, tsumoHai, kaze, selectSutehaiIdx, isPlayer);
		} else {
			drawTehai(0, 79, canvas, tehai, tsumoHai, kaze, 15, isPlayer);
		}

		return bitmap;
	}

	private void drawKawa(int left, int top, Canvas canvas, Kawa kawa,
			Paint paint) {
		int leftTemp = left;
		SuteHai[] suteHais = kawa.getSuteHais();
		int kawaLength = kawa.getSuteHaisLength();
		for (int i = 0; i < kawaLength; i++) {
			if (i == 12) {
				left = leftTemp;
				top += mHaiImageHeight;
			}

			if (suteHais[i].isReach()) {
				canvas.drawBitmap(mHaiHorizontalImage[suteHais[i].getId()], left, top + ((mHaiImageHeight - mHaiImageWidth) / 2), paint);
				left += mHaiImageHeight - mHaiImageWidth;
			} else {
				canvas.drawBitmap(mHaiImage[suteHais[i].getId()], left, top, paint);
			}

			left += mHaiImageWidth;
		}
	}

	private static final int FUURO_LEFT = 296;

	private void drawTehai(int left, int top, Canvas canvas, Tehai tehai, Hai tsumoHai, int kaze, int select, boolean isPlayer) {
		boolean isDisp = isPlayer || drawItem.mIsDebug;

		Hai[] jyunTehai = tehai.getJyunTehai();
		int jyunTehaiLength = tehai.getJyunTehaiLength();
		int width = mHaiImage[0].getWidth();
		for (int i = 0; i < jyunTehaiLength; i++) {
			if (tsumoHai != null && mState == STATE_SUTEHAI_MACHI) {
				if (i == skipIdx) {
					continue;
				}
			}
			if (i == select) {
				canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top - 5, null);
			} else {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top, null);
				} else {
					canvas.drawBitmap(mHaiHideImage, left + (width * i), top, null);
				}
			}
		}

		Log.d(this.getClass().getName(), "print, tsumoKaze = " + drawItem.tsumoKaze + ", id = " + drawItem.tsumoHai);
		if (tsumoHai != null) {
			if ((select >= jyunTehaiLength) && (mState != STATE_SUTEHAI_MACHI)) {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[tsumoHai.getId()], left + ((width * jyunTehaiLength) + 5), top - 5, null);
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
					canvas.drawBitmap(mHaiHorizontalImage[hais[2].getId()], fuuroLeft, 86, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[1].getId()], fuuroLeft, 79, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[0].getId()], fuuroLeft, 79, null);
				} else if (relation == Mahjong.RELATION_TOIMEN) {
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[2].getId()], fuuroLeft, 79, null);
					fuuroLeft -= mHaiImageHeight;
					canvas.drawBitmap(mHaiHorizontalImage[hais[1].getId()], fuuroLeft, 86, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[0].getId()], fuuroLeft, 79, null);
				} else {
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[2].getId()], fuuroLeft, 79, null);
					fuuroLeft -= mHaiImageWidth;
					canvas.drawBitmap(mHaiImage[hais[1].getId()], fuuroLeft, 79, null);
					fuuroLeft -= mHaiImageHeight;
					canvas.drawBitmap(mHaiHorizontalImage[hais[0].getId()], fuuroLeft, 86, null);
				}
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		synchronized (drawItem) {
			// �w�i��`�悷��
			Paint background = new Paint();
			background.setColor(getResources().getColor(R.color.puzzle_background));
			canvas.drawRect(0, 0, getWidth(), getHeight(), background);

			if (mState == STATE_INIT) {
				return;
			}

			if (mState == STATE_KYOKU_START) {
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

				/*
				Paint kazuPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				kazuPaint.setColor(getResources().getColor(R.color.puzzle_foreground));
				kazuPaint.setStyle(Style.FILL);
				kazuPaint.setTextSize(30);
				kazuPaint.setTypeface(Typeface.MONOSPACE);
				// kazuPaint.setTextSize(height * 0.75f);
				// kazuPaint.setTextScaleX(width / height);
				kazuPaint.setTextAlign(Paint.Align.CENTER);
				*/

				kyokuString = "����� 25000";
				Paint kazuPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				kazuPaint.setColor(getResources().getColor(R.color.puzzle_foreground));
				kazuPaint.setStyle(Style.FILL);
				kazuPaint.setTextSize(20);
				kazuPaint.setTypeface(Typeface.MONOSPACE);
				// kazuPaint.setTextSize(height * 0.75f);
				// kazuPaint.setTextScaleX(width / height);
				kazuPaint.setTextAlign(Paint.Align.CENTER);

				canvas.drawText(kyokuString, 150, 150, kazuPaint);

				return;
			}

			int manKaze = mInfoUi.getManKaze();
			int dispKaze[] = { 0, 1, 2, 3 };
			for (int i = 0; i < 4; i++) {
				dispKaze[i] = manKaze;
				manKaze++;
				manKaze %= 4;
			}

			Bitmap test2 = getKawaBitmap(drawItem.tehais[dispKaze[0]], drawItem.kawas[dispKaze[0]], 0, dispKaze[0], true, drawItem.tsumoHais[dispKaze[0]]);
			canvas.drawBitmap(test2, PLAYER_INFO_AREA_LEFT, PLAYER_INFO_AREA_TOP, null);

			Bitmap test3 = getKawaBitmap(drawItem.tehais[dispKaze[1]], drawItem.kawas[dispKaze[1]], 270, dispKaze[1], false, drawItem.tsumoHais[dispKaze[1]]);
			canvas.drawBitmap(test3, 215, 56, null);

			Bitmap test = getKawaBitmap(drawItem.tehais[dispKaze[2]], drawItem.kawas[dispKaze[2]], 180, dispKaze[2], false, drawItem.tsumoHais[dispKaze[2]]);
			canvas.drawBitmap(test, 0, 1, null);

			Bitmap test4 = getKawaBitmap(drawItem.tehais[dispKaze[3]], drawItem.kawas[dispKaze[3]], 90, dispKaze[3], false, drawItem.tsumoHais[dispKaze[3]]);
			canvas.drawBitmap(test4, 0, 53, null);

			setBaImage();
			canvas.drawBitmap(mBaImage, BA_IMAGE_LEFT, BA_IMAGE_TOP, null);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
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
			if(drawItem.mIsDebug){
				drawItem.mIsDebug = false;
			}else{
				drawItem.mIsDebug = true;
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
	 * UI�����������܂��B
	 *
	 * @param infoUi
	 *            InfoUI�I�u�W�F�N�g
	 */
	public void Console(InfoUI infoUi, String name) {
		this.mInfoUi = infoUi;
		this.name = name;
	}

	private void dispInfo() {
		System.out.println("-------- INFO --------");
		int kyoku = mInfoUi.getkyoku();
		switch (kyoku) {
		case Mahjong.KYOKU_TON_1:
			System.out.print("[�����]");
			break;
		case Mahjong.KYOKU_TON_2:
			System.out.print("[�����]");
			break;
		case Mahjong.KYOKU_TON_3:
			System.out.print("[���O��]");
			break;
		case Mahjong.KYOKU_TON_4:
			System.out.print("[���l��]");
			break;
		}
		System.out.print("[" + mInfoUi.getHonba() + "�{��]");
		System.out.println("[�c��:" + mInfoUi.getTsumoRemain() + "]");
		// �h���\���v��\�����܂��B
		Hai[] doras = mInfoUi.getDoras();
		System.out.print("[�h���\���v]");
		for (Hai hai : doras) {
			System.out.print("[" + idToString(hai.getId()) + "]");
		}
		System.out.println();

		// ���O�Ȃǂ�\�����Ă݂�B
		System.out.println("[" + jikazeToString(0) + "][" + mInfoUi.getName(0)
				+ "][" + mInfoUi.getTenbou(0) + "]");
		System.out.println("[" + jikazeToString(1) + "][" + mInfoUi.getName(1)
				+ "][" + mInfoUi.getTenbou(1) + "]");
		System.out.println("[" + jikazeToString(2) + "][" + mInfoUi.getName(2)
				+ "][" + mInfoUi.getTenbou(2) + "]");
		System.out.println("[" + jikazeToString(3) + "][" + mInfoUi.getName(3)
				+ "][" + mInfoUi.getTenbou(3) + "]");

		System.out.println("----------------------");
	}

	private static final int STATE_INIT = 0;
	private static final int STATE_KYOKU_START = 1;
	private static final int STATE_PLAY = 2;
	private static final int STATE_SUTEHAI_MACHI = 3;
	int mState = STATE_INIT;

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

    private int mKazeToPlace[] = new int[4];

    /**
	 * �C�x���g���������܂��B
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
		case BASHOGIME:// �ꏊ����
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case OYAGIME:// �e����
			// �T�C�U���\�����܂��B
			// Sai[] sai = infoUi.getSais();
			// System.out.println("[�e����][" + sai[0].getNo() + "]["
			// + sai[1].getNo() + "]");
			break;
		case SENPAI:// ���v
			for (int i = 0; i < drawItem.kawas.length; i++) {
				drawItem.kawas[i] = new Kawa();
			}
			break;
		case SAIFURI:// �T�C�U��
			mState = STATE_KYOKU_START;
			dispInfo();
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(2000, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			mState = STATE_PLAY;
			break;
		case HAIPAI:
			mInfoUi.copyTehai(drawItem.tehais[0], 0);
			mInfoUi.copyTehai(drawItem.tehais[1], 1);
			mInfoUi.copyTehai(drawItem.tehais[2], 2);
			mInfoUi.copyTehai(drawItem.tehais[3], 3);
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case RYUUKYOKU:// ����
			System.out.println("[����]");
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
				mInfoUi.copyTehai(drawItem.tehais[0], 0);
				mInfoUi.copyTehai(drawItem.tehais[1], 1);
				mInfoUi.copyTehai(drawItem.tehais[2], 2);
				mInfoUi.copyTehai(drawItem.tehais[3], 3);
				drawItem.tsumoKaze = mInfoUi.getJikaze();
				Log.d(TAG, "tsumoKaze = " + drawItem.tsumoKaze);
				drawItem.tsumoHai = mInfoUi.getTsumoHai();

				drawItem.tsumoHais[mInfoUi.getJikaze()] = mInfoUi.getTsumoHai();

				this.postInvalidate(0, 0, getWidth(), getHeight());
				/*
				 * this.post(new Runnable() { public void run() { selRect.set(0,
				 * 0, getWidth(), getHeight()); invalidate(selRect); } });
				 */
//			}
			Log.d(this.getClass().getName(), "tsumo, tsumoKaze = " + drawItem.tsumoKaze + ", id = " + drawItem.tsumoHai.getId());
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
			mState = STATE_SUTEHAI_MACHI;
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(200, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			selectSutehaiIdx = 13;
			mState = STATE_PLAY;
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
					mInfoUi.copyTehai(drawItem.tehais[0], 0);
					mInfoUi.copyTehai(drawItem.tehais[1], 1);
					mInfoUi.copyTehai(drawItem.tehais[2], 2);
					mInfoUi.copyTehai(drawItem.tehais[3], 3);
					mInfoUi.copyKawa(drawItem.kawas[0], 0);
					mInfoUi.copyKawa(drawItem.kawas[1], 1);
					mInfoUi.copyKawa(drawItem.kawas[2], 2);
					mInfoUi.copyKawa(drawItem.kawas[3], 3);
					drawItem.tsumoKaze = 5;
					drawItem.tsumoHai = null;
					drawItem.tsumoHais[mInfoUi.getJikaze()] = null;
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
					mInfoUi.copyTehai(drawItem.tehais[0], 0);
					mInfoUi.copyTehai(drawItem.tehais[1], 1);
					mInfoUi.copyTehai(drawItem.tehais[2], 2);
					mInfoUi.copyTehai(drawItem.tehais[3], 3);
					drawItem.tsumoKaze = 5;
					drawItem.tsumoHai = null;
					drawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
			}
			break;
		case PON:// �|��
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == mInfoUi.getJikaze()) {
				{
					mInfoUi.copyTehai(drawItem.tehais[0], 0);
					mInfoUi.copyTehai(drawItem.tehais[1], 1);
					mInfoUi.copyTehai(drawItem.tehais[2], 2);
					mInfoUi.copyTehai(drawItem.tehais[3], 3);
					mInfoUi.copyKawa(drawItem.kawas[0], 0);
					mInfoUi.copyKawa(drawItem.kawas[1], 1);
					mInfoUi.copyKawa(drawItem.kawas[2], 2);
					mInfoUi.copyKawa(drawItem.kawas[3], 3);
					drawItem.tsumoKaze = 5;
					drawItem.tsumoHai = null;
					drawItem.tsumoHais[mInfoUi.getJikaze()] = null;
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