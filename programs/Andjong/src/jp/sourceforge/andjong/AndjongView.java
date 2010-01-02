package jp.sourceforge.andjong;

import jp.sourceforge.andjong.R;
import jp.sourceforge.andjong.DrawItem.PlayerInfo;
import jp.sourceforge.andjong.mahjong.EventIf;
import jp.sourceforge.andjong.mahjong.Fuuro;
import jp.sourceforge.andjong.mahjong.Hai;
import jp.sourceforge.andjong.mahjong.InfoUi;
import jp.sourceforge.andjong.mahjong.Kawa;
import jp.sourceforge.andjong.mahjong.Mahjong;
import jp.sourceforge.andjong.mahjong.PlayerAction;
import jp.sourceforge.andjong.mahjong.SuteHai;
import jp.sourceforge.andjong.mahjong.Tehai;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AndjongView extends View implements EventIf {
	private static final String TAG = "AndjongView";

	/** �A�N�e�B�r�e�B */
	private Game mGame;

	/** �v�̃C���[�W */
	private Bitmap[] mHaiImage;
	/** �v�̃C���[�W�̕� */
	private int mHaiImageWidth;
	/** �v�̃C���[�W�̍��� */
	private int mHaiImageHeight;

	/** �v�̗��̃C���[�W */
	private Bitmap mHaiUraImage;

	/** ���ɂȂ����v�̃C���[�W */
	private Bitmap[] mHaiHorizontalImage;

	/** �B���Ă���v�̃C���[�W */
	private Bitmap mHaiHideImage;

	/** 1000�_�_�̃C���[�W */
	private Bitmap mTenbou1000Image;
	/** 100�_�_�̃C���[�W */
	private Bitmap mTenbou100Image;

	/** �N�ƃ}�[�N�̃C���[�W */
	private Bitmap mChiichaImage;

	/** ���j���[�I���̃C���[�W */
	private Bitmap mMenuSelectImage;

	/** �w�i�̃y�C���g */
	private Paint mBackgroundPaint;

	/** ���b�Z�[�W�G���A�̕� */
	private static final int MESSAGE_AREA_WIDTH = 146;
	/** ���b�Z�[�W�G���A�̍��� */
	private static final int MESSAGE_AREA_HEIGHT = 143;

	/** ���b�Z�[�W�G���A��Left */
	private static final int MESSAGE_AREA_LEFT = 87;
	/** ���b�Z�[�W�G���A��Top */
	private static final int MESSAGE_AREA_TOP = 176;
	/** ���b�Z�[�W�G���A��Right */
	private static final int MESSAGE_AREA_RIGHT = MESSAGE_AREA_LEFT + MESSAGE_AREA_WIDTH;
	/** ���b�Z�[�W�G���A��Bottom */
	private static final int MESSAGE_AREA_BOTTOM = MESSAGE_AREA_TOP + MESSAGE_AREA_HEIGHT;

	/** ���b�Z�[�W�̃e�L�X�g�T�C�Y */
	private static final int MESSAGE_TEXT_SIZE = 30;

	/** ���b�Z�[�W�̘g�̊ۂ� */
	private static final int MESSAGE_ROUND = 5;

	/** ���b�Z�[�W�̃y�C���g */
	private Paint mMessagePaint;

	/** ���b�Z�[�W�̘g */
	private RectF mMessageRect;

	/*
	 * ���j���[
	 */

	/** ���j���[�̌��̍ő�l */
	private static final int MENU_ITEM_MAX = 4;

	/** ���j���[�̕� */
	private static final int MENU_WIDTH = 78;
	/** ���j���[�̍��� */
	private static final int MENU_HEIGHT = 72;

	/** ���j���[�G���A��top */
	private static final int MENU_AREA_TOP = 406;
	/** ���j���[�G���A��left */
	private static final int MENU_AREA_LEFT = 0;
	/** ���j���[�G���A��top�̃}�[�W�� */
	private static final int MENU_AREA_TOP_MARGIN = 1;
	/** ���j���[�G���A��left�̃}�[�W�� */
	private static final int MENU_AREA_LEFT_MARGIN = 1;

	/** ���j���[�̘g */
	private RectF m_menuRect[];

	/** �`��A�C�e�� */
	private DrawItem mDrawItem = new DrawItem();

	/** InfoUI */
	private InfoUi mInfoUi;

	/** UI�̖��O */
	private String mName;

	/** �v���C���[�A�N�V���� */
	private PlayerAction m_playerAction;

	/** �ǂ�Left */
	private static final int KYOKU_LEFT = 160;
	/** �ǂ�Top */
	private static final int KYOKU_TOP = 85 + 11;

	/** �ǂ̃e�L�X�g�T�C�Y */
	private static final int KYOKU_TEXT_SIZE = 18;

	/** �h����Left */
	private static final int DORAS_LEFT = 112;
	/** �h����Top */
	private static final int DORAS_TOP = 150;

	/** ���[�`�_�̃C���[�W��Left */
	private static final int TENBOU_01000_MIN_IMAGE_LEFT = 100;
	/** ���[�`�_�̃C���[�W��Top */
	private static final int TENBOU_01000_MIN_IMAGE_TOP = 137;

	/** ���[�`�_�̐���Left */
	private static final int REACHBOU_LEFT = TENBOU_01000_MIN_IMAGE_LEFT + 43;
	/** ���[�`�_�̐�Top */
	private static final int REACHBOU_TOP = TENBOU_01000_MIN_IMAGE_TOP + 5;

	/** �������̃e�L�X�g�T�C�Y */
	private static final int MINI_TEXT_SIZE = 12;

	/** �_�_��Left */
	private static final int[] TENBO_LEFT = { 160, 197, 160, 123 };
	/** �_�_��Top */
	private static final int[] TENBO_TOP = { 131, 121, 111, 121 };

	/** �{��̃C���[�W��Left */
	private static final int TENBOU_00100_MIN_IMAGE_LEFT = 170;
	/** �{��̃C���[�W��Top */
	private static final int TENBOU_00100_MIN_IMAGE_TOP = TENBOU_01000_MIN_IMAGE_TOP;

	/** �{��̐���Left */
	private static final int HONBA_LEFT = TENBOU_00100_MIN_IMAGE_LEFT + 43;
	/** �{��̐�Top */
	private static final int HONBA_TOP = TENBOU_00100_MIN_IMAGE_TOP + 5;

	private boolean mHaiSelectStatus;

	/**
	 * �R���X�g���N�^
	 *
	 * @param context
	 *            �A�N�e�B�r�e�B
	 */
	public AndjongView(Context context) {
		super(context);

		// �A�N�e�B�r�e�B��ۑ�����B
		this.mGame = (Game) context;

		// �C���[�W������������B
		initImage(getResources());

		// �y�C���g������������B
		initPaint(getResources());

		// UI�������������܂ő҂B
		mDrawItem.setState(DrawItem.STATE_INIT_WAIT);

		// �t�H�[�J�X��L���ɂ���B
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	/**
	 * �C���[�W������������B
	 *
	 * @param res
	 *            ���\�[�X
	 */
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

		mHaiImageWidth = mHaiImage[0].getWidth();
		mHaiImageHeight = mHaiImage[0].getHeight();

		mHaiUraImage = BitmapFactory.decodeResource(res, R.drawable.hai_ura);

		mHaiHorizontalImage = new Bitmap[Hai.ID_MAX + 1];

		for (int i = 0; i < mHaiHorizontalImage.length; i++) {
			mHaiHorizontalImage[i] = createHorizontalBitmap(mHaiImage[i]);
		}

		mHaiHideImage = BitmapFactory.decodeResource(res, R.drawable.hai_hide);

		mTenbou1000Image = BitmapFactory.decodeResource(res, R.drawable.tenbou_1000);
		mTenbou100Image = BitmapFactory.decodeResource(res, R.drawable.tenbou_100);

		mChiichaImage = BitmapFactory.decodeResource(res, R.drawable.chiicha);

		mMenuSelectImage = BitmapFactory.decodeResource(res, R.drawable.menu_select);
	}

	/**
	 * �y�C���g������������B
	 *
	 * @param res
	 *            ���\�[�X
	 */
	private void initPaint(Resources res) {
		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(res.getColor(R.color.andjong_background));

		mMessagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mMessagePaint.setColor(Color.DKGRAY);
		mMessagePaint.setAlpha(200);
		mMessageRect = new RectF(MESSAGE_AREA_LEFT, MESSAGE_AREA_TOP, MESSAGE_AREA_RIGHT, MESSAGE_AREA_BOTTOM);

		// ���j���[������������B
		m_menuRect = new RectF[MENU_ITEM_MAX];
		int left;
		int top = MENU_AREA_TOP + MENU_AREA_TOP_MARGIN;
		int right;
		int bottom = top + MENU_HEIGHT;
		for (int i = 0; i < m_menuRect.length; i++) {
			left = (MENU_AREA_LEFT + MENU_AREA_LEFT_MARGIN) + ((MENU_WIDTH + (MENU_AREA_LEFT_MARGIN * 2)) * i);
			right = left + MENU_WIDTH;
			m_menuRect[i] = new RectF(left, top, right, bottom);
		}
	}

	/**
	 * ���ɂȂ����C���[�W���쐬����B
	 *
	 * @param verticalImage
	 *            �c�̃C���[�W
	 * @return ���ɂȂ����C���[�W
	 */
	private Bitmap createHorizontalBitmap(Bitmap verticalImage) {
		int height = verticalImage.getWidth();
		int width = verticalImage.getHeight();
		Bitmap horizontalImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(horizontalImage);
		canvas.rotate(270, 0, 0);
		canvas.drawBitmap(verticalImage, -height, 0, null);
		return horizontalImage;
	}

	/**
	 * UI������������B
	 *
	 * @param infoUi
	 *            InfoUi
	 * @param name
	 *            UI�̖��O
	 */
	public void initUi(InfoUi infoUi, String name) {
		this.mInfoUi = infoUi;
		this.mName = name;

		// �v���C���[�A�N�V�������擾����B
		m_playerAction = mInfoUi.getPlayerAction();

		// ��ԂȂ��ɂ��Ă����B
		mDrawItem.setState(DrawItem.STATE_NONE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// �w�i��`�悷��B
		canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);

		synchronized (mDrawItem) {
			switch (mDrawItem.mState) {
			case DrawItem.STATE_INIT_WAIT:
			case DrawItem.STATE_NONE:
				// �����`�悵�Ȃ��B
				return;
			case DrawItem.STATE_KYOKU_START:
				// �ǂ�\������B
				drawMessage(canvas, mDrawItem.getKyokuString());
				return;
			case DrawItem.STATE_REACH:
				// ���[�`�̃��b�Z�[�W��\������B
				drawMessage(canvas, "���[�`");
				break;
			case DrawItem.STATE_RON:
				// �����̃��b�Z�[�W��\������B
				drawMessage(canvas, "����");
				break;
			case DrawItem.STATE_TSUMO:
				// �c���̃��b�Z�[�W��\������B
				drawMessage(canvas, "�c��");
				break;
			case DrawItem.STATE_RYUUKYOKU:
				// ���ǂ̃��b�Z�[�W��\������B
				drawMessage(canvas, getResources().getString(R.string.ryuukyoku));
				break;
			}

			// �A�N�V�����{�^����\������B
			boolean actionRequest = m_playerAction.isActionRequest();
			if (actionRequest) {
				//drawMessage(canvas, getResources().getString(R.string.action_button));
				int iMenu = 0;

				if (m_playerAction.isValidRon()) {
					drawMenuMessage(canvas, "����", iMenu);
					iMenu++;
				}

				if (m_playerAction.isValidTsumo()) {
					drawMenuMessage(canvas, "�c��", iMenu);
					iMenu++;
				}

				//drawMenuMessage(canvas, "����", iMenu);

				if (m_playerAction.getMenuSelect() == 0) {
					//canvas.drawBitmap(mMenuSelectImage, MENU1_AREA_LEFT, MENU_AREA_TOP, null);
				} else {
					//canvas.drawBitmap(mMenuSelectImage, MENU2_AREA_LEFT, MENU_AREA_TOP, null);
				}
			}

			// �ǂ�\������B
			drawString(KYOKU_LEFT, KYOKU_TOP, canvas, KYOKU_TEXT_SIZE, Color.WHITE, mDrawItem.getKyokuString());

			// ���[�`�_�̐���\������B
			drawReachbou(canvas, mDrawItem.getReachbou());

			// �{���\������B
			drawHonba(canvas, mDrawItem.getHonba());

			// �h����\������B
			drawDoras(canvas);

			int manKaze = mInfoUi.getManKaze();
			int dispKaze[] = { 0, 1, 2, 3 };
			for (int i = 0; i < 4; i++) {
				dispKaze[i] = manKaze;
				manKaze++;
				manKaze %= 4;
			}

			// �_�_��\������B
			for (int i = 0; i < EventIf.KAZE_KIND_NUM; i++) {
				drawString(TENBO_LEFT[dispKaze[i]], TENBO_TOP[dispKaze[i]], canvas, MINI_TEXT_SIZE, Color.WHITE, new Integer(mDrawItem.mPlayerInfos[dispKaze[0]].mTenbo).toString());
			}

			// �N�ƃ}�[�N��\������B
			drawChiicha(canvas, mDrawItem.getChiicha());

			Bitmap test2 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[0]].mTehai, mDrawItem.mPlayerInfos[dispKaze[0]].mKawa, PLACE_PLAYER, dispKaze[0], true, mDrawItem.mPlayerInfos[dispKaze[0]].mTsumoHai);
			canvas.drawBitmap(test2, KAWA_TEHAI_AREA_PLAYER_LEFT, KAWA_TEHAI_AREA_PLAYER_TOP, null);

			Bitmap test3 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[1]].mTehai, mDrawItem.mPlayerInfos[dispKaze[1]].mKawa, PLACE_KAMICHA, dispKaze[1], false, mDrawItem.mPlayerInfos[dispKaze[1]].mTsumoHai);
			canvas.drawBitmap(test3, KAWA_TEHAI_AREA_KAMICHA_LEFT, KAWA_TEHAI_AREA_KAMICHA_TOP, null);

			Bitmap test = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[2]].mTehai, mDrawItem.mPlayerInfos[dispKaze[2]].mKawa, PLACE_TOIMEN, dispKaze[2], false, mDrawItem.mPlayerInfos[dispKaze[2]].mTsumoHai);
			canvas.drawBitmap(test, KAWA_TEHAI_AREA_TOIMEN_LEFT, KAWA_TEHAI_AREA_TOIMEN_TOP, null);

			Bitmap test4 = getKawaTehaiAreaImage(mDrawItem.mPlayerInfos[dispKaze[3]].mTehai, mDrawItem.mPlayerInfos[dispKaze[3]].mKawa, PLACE_SHIMOCHA, dispKaze[3], false, mDrawItem.mPlayerInfos[dispKaze[3]].mTsumoHai);
			canvas.drawBitmap(test4, KAWA_TEHAI_AREA_SHIMOCHA_LEFT, KAWA_TEHAI_AREA_SHIMOCHA_TOP, null);
		}
	}

	/**
	 * ���b�Z�[�W��\������B
	 *
	 * @param canvas
	 *            �L�����o�X
	 * @param string
	 *            ������
	 */
	private void drawMessage(Canvas canvas, String string) {
		canvas.drawRoundRect(mMessageRect, MESSAGE_ROUND, MESSAGE_ROUND, mMessagePaint);
		drawString((MESSAGE_AREA_LEFT + MESSAGE_AREA_RIGHT) / 2, (MESSAGE_AREA_TOP + MESSAGE_AREA_BOTTOM) / 2, canvas, MESSAGE_TEXT_SIZE, Color.WHITE, string);
	}


	private void drawMenuMessage(Canvas canvas, String string, int no) {
		canvas.drawRoundRect(m_menuRect[no], MESSAGE_ROUND, MESSAGE_ROUND, mMessagePaint);
		drawString((int) (m_menuRect[no].left + (MENU_WIDTH / 2)), (int) (m_menuRect[no].top + (MENU_HEIGHT / 2)), canvas, MESSAGE_TEXT_SIZE, Color.WHITE, string);
	}

	/**
	 * �������\������B
	 *
	 * @param left
	 *            Left
	 * @param top
	 *            Top
	 * @param canvas
	 *            �L�����o�X
	 * @param textSize
	 *            �e�L�X�g�T�C�Y
	 * @param color
	 *            �F
	 * @param string
	 *            ������
	 */
	private void drawString(int left, int top, Canvas canvas, int textSize,
			int color, String string) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(color);
		paint.setTextSize(textSize);
		paint.setTextAlign(Paint.Align.CENTER);

		canvas.drawText(string, left, top - ((paint.ascent() + paint.descent()) / 2), paint);
	}

	/**
	 * �h����\������B
	 *
	 * @param canvas
	 *            �L�����o�X
	 */
	private void drawDoras(Canvas canvas) {
		Hai doras[] = mInfoUi.getDoras();
		for (int i = 0; i < 5; i++) {
			if (i < doras.length) {
				canvas.drawBitmap(mHaiImage[doras[i].getId()], DORAS_LEFT + (i * mHaiImageWidth), DORAS_TOP, null);
			} else {
				canvas.drawBitmap(mHaiUraImage, DORAS_LEFT + (i * mHaiImageWidth), DORAS_TOP, null);
			}
		}
	}

	/**
	 * ���[�`�_�̐���\������B
	 *
	 * @param canvas
	 *            �L�����o�X
	 * @param reachbou
	 *            ���[�`�_�̐�
	 */
	private void drawReachbou(Canvas canvas, int reachbou) {
		canvas.drawBitmap(mTenbou1000Image, TENBOU_01000_MIN_IMAGE_LEFT, TENBOU_01000_MIN_IMAGE_TOP, null);
		drawString(REACHBOU_LEFT, REACHBOU_TOP, canvas, MINI_TEXT_SIZE, Color.WHITE, "x " + new Integer(reachbou).toString());
	}

	/**
	 * �{���\������B
	 *
	 * @param canvas
	 *            �L�����o�X
	 * @param honba
	 *            �{��
	 */
	private void drawHonba(Canvas canvas, int honba) {
		canvas.drawBitmap(mTenbou100Image, TENBOU_00100_MIN_IMAGE_LEFT, TENBOU_00100_MIN_IMAGE_TOP, null);
		drawString(HONBA_LEFT, HONBA_TOP, canvas, MINI_TEXT_SIZE, Color.WHITE, "x " + new Integer(honba).toString());
	}

	/**
	 * �N�ƃ}�[�N��\������B
	 *
	 * @param canvas
	 *            �L�����o�X
	 * @param chiicha
	 *            �N��
	 */
	private void drawChiicha(Canvas canvas, int chiicha) {
		canvas.drawBitmap(mChiichaImage, TENBO_LEFT[chiicha] - 26, TENBO_TOP[chiicha] - 5, null);
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
			drawTehai(TEHAI_LEFT, TEHAI_TOP, canvas, tehai, tsumoHai, kaze, mSelectSutehaiIdx, isPlayer);
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
			if (tsumoHai != null && mDrawItem.mState == DrawItem.STATE_RIHAI_WAIT) {
				if (i == mDrawItem.getSkipIdx()) {
					continue;
				}
			}
			if ((i == select) && (m_playerAction.getState() == PlayerAction.STATE_SUTEHAI_SELECT)) {
				canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top - 10, null);
			} else {
				if (isDisp) {
					canvas.drawBitmap(mHaiImage[jyunTehai[i].getId()], left + (width * i), top, null);
				} else {
					canvas.drawBitmap(mHaiHideImage, left + (width * i), top, null);
				}
			}
		}

		//Log.d(this.getClass().getName(), "print, tsumoKaze = " + mDrawItem.tsumoKaze + ", id = " + mDrawItem.tsumoHai);
		if (tsumoHai != null) {
			if ((select >= jyunTehaiLength) && (mDrawItem.mState != DrawItem.STATE_RIHAI_WAIT)) {
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
		int fuuroNums = tehai.getFuuroNum();
		if (fuuroNums > 0) {
			Fuuro[] fuuros = tehai.getFuuros();
			for (int i = 0; i < fuuroNums; i++) {
				Hai hais[] = fuuros[i].getHais();
				//int type = fuuros[i].getType();
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		int action = event.getAction();

		Log.d(TAG, "onTouchEvent: x = " + x + ", y = " + y);

		boolean actionRequest = m_playerAction.isActionRequest();
		if (actionRequest) {
			if (action == MotionEvent.ACTION_DOWN) {
				int iMenu = 5;
				for (int i = 0; i < m_menuRect.length; i++) {
					if (x >= m_menuRect[i].left && x <= m_menuRect[i].right) {
						if (y >= m_menuRect[i].top && y <= m_menuRect[i].bottom) {
							iMenu = i;
							break;
						}
					}
				}
				Log.d(TAG, "actionRequest actionNotifyAll");
				m_playerAction.setMenuSelect(iMenu);
				m_playerAction.actionNotifyAll();
			}
			return true;
		}

		if (action == MotionEvent.ACTION_DOWN) {
			synchronized (mDrawItem) {
				switch (mDrawItem.mState) {
				case DrawItem.STATE_PLAY:
					break;
				default:
					Log.d(TAG, "mDrawItem actionNotifyAll");
					m_playerAction.actionNotifyAll();
					return true;
				}
			}
		}

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
				mSelectSutehaiIdx = 0;
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v2�̉��͈̔�) */
			else if ((22 <= tx) && (tx <= 40) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 1;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v3�̉��͈̔�) */
			else if ((41 <= tx) && (tx <= 59) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 2;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v4�̉��͈̔�) */
			else if ((60 <= tx) && (tx <= 78) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 3;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v5�̉��͈̔�) */
			else if ((79 <= tx) && (tx <= 97) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 4;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v6�̉��͈̔�) */
			else if ((98 <= tx) && (tx <= 116) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 5;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v7�̉��͈̔�) */
			else if ((117 <= tx) && (tx <= 136) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 6;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v8�̉��͈̔�) */
			else if ((137 <= tx) && (tx <= 155) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 7;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v9�̉��͈̔�) */
			else if ((156 <= tx) && (tx <= 174) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 8;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v10�̉��͈̔�) */
			else if ((175 <= tx) && (tx <= 191) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 9;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v11�̉��͈̔�) */
			else if ((192 <= tx) && (tx <= 211) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 10;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v12�̉��͈̔�) */
			else if ((212 <= tx) && (tx <= 230) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 11;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v13�̉��͈̔�) */
			else if ((231 <= tx) && (tx <= 249) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 12;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			/* X���W�̔���(�v14�̉��͈̔�) */
			else if ((256 <= tx) && (tx <= 274) )
			{
				/* �I��v�̔ԍ���ێ� */
				mSelectSutehaiIdx = 13;

				/* �v��I����� */
				mHaiSelectStatus = true;
			}
			else
			{
				// do nothing
			}
		}
		else
		{
			/* �v���I����ԁE�C�x���gACTION_UP�EY���W��385�ȉ��̎��A�v���̂Ă�ꂽ�Ƃ��� */
			if ((mHaiSelectStatus == true) && (act_evt == MotionEvent.ACTION_UP) && (ty <= 385))
			{
				mInfoUi.getPlayerAction().setSutehaiIdx(mSelectSutehaiIdx);
				/* �v���I����Ԃɂ��� */
				mHaiSelectStatus = false;
			}
			else if (act_evt == MotionEvent.ACTION_MOVE)
			{
				// ACTION_MOVE�C�x���g�̏ꍇ�́A�v�̏�Ԃ�ύX���Ȃ��B(�������������Ȃ�)
			}
			else
			{
				/* �v���I����Ԃɂ��� */
				mHaiSelectStatus = false;
			}
		}
		/* �ĕ`��̎w�� */
		invalidate();
		return true;
	}


	private int mSelectSutehaiIdx = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event=" + event);
		boolean actionRequest = m_playerAction.isActionRequest();
		int menuSelect = m_playerAction.getMenuSelect();
		if (actionRequest) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				menuSelect--;
				menuSelect %= 2;
				m_playerAction.setMenuSelect(menuSelect);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				menuSelect++;
				menuSelect %= 2;
				m_playerAction.setMenuSelect(menuSelect);
				break;
			case KeyEvent.KEYCODE_ENTER:
			case KeyEvent.KEYCODE_DPAD_CENTER:
				Log.d(TAG, "KEYCODE_DPAD_CENTER actionNotifyAll");
				m_playerAction.actionNotifyAll();
				break;
			default:
				return super.onKeyDown(keyCode, event);
			}
			invalidate();
			return true;
		}

		int state = m_playerAction.getState();
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (state != PlayerAction.STATE_SUTEHAI_SELECT) {
				return true;
			}
			mSelectSutehaiIdx = 0;
			if(mDrawItem.mIsDebug){
				mDrawItem.mIsDebug = false;
			}else{
				mDrawItem.mIsDebug = true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (state != PlayerAction.STATE_SUTEHAI_SELECT) {
				return true;
			}
			mSelectSutehaiIdx = 100;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (state != PlayerAction.STATE_SUTEHAI_SELECT) {
				return true;
			}
			mSelectSutehaiIdx--;
			if (mSelectSutehaiIdx < 0) {
				mSelectSutehaiIdx = 13;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (state != PlayerAction.STATE_SUTEHAI_SELECT) {
				return true;
			}
			mSelectSutehaiIdx++;
			if (mSelectSutehaiIdx > 13) {
				mSelectSutehaiIdx = 0;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			synchronized (mDrawItem) {
				switch (mDrawItem.mState) {
				case DrawItem.STATE_PLAY:
					Log.d(TAG, "STATE_PLAY actionNotifyAll");
					m_playerAction.setSutehaiIdx(mSelectSutehaiIdx);
					m_playerAction.actionNotifyAll();
					break;
				default:
					Log.d(TAG, "default actionNotifyAll");
					m_playerAction.actionNotifyAll();
					break;
				}
			}

			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		invalidate();
		return true;
	}

	/** �̔v�̃C���f�b�N�X */
	private int mSutehaiIdx = 0;

	public String getName() {
		return mName;
	}

    //private int mSkipIdx = 0;

    /** �i�s�̑҂����� */
	private static int PROGRESS_WAIT_TIME = 100;

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
	public EventId event(EventId eid, int fromKaze, int toKaze) {
		Log.d(TAG, "eid = " + eid.toString());
		switch (eid) {
		case UI_WAIT_PROGRESS:// �i�s�҂�
			try {
				Thread.sleep(PROGRESS_WAIT_TIME, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			break;
		case START_GAME:// �e����
			// ���̒i�K�ŏ���������Ă������ݒ肷��B
			mDrawItem.setState(DrawItem.STATE_NONE);
			synchronized (mDrawItem) {
				// �_�_��ݒ肷��B
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mDrawItem.mPlayerInfos[i].mTenbo = mInfoUi.getTenbou(i);
				}

				// ���[�`�_�̐���ݒ肷��B
				mDrawItem.setReachbou(mInfoUi.getReachbou());

				// �{���ݒ肷��B
				mDrawItem.setHonba(mInfoUi.getHonba());

				// �N�Ƃ�ݒ肷��B
				mDrawItem.setChiicha(mInfoUi.getChiichaIdx());
			}
			break;
		case START_KYOKU:// �T�C�U��
			// �T�C�U����ǂ̊J�n�ƍl����B

			// �ǂ̕������ݒ肷��B
			mDrawItem.setKyokuString(getResources(), mInfoUi.getkyoku());

			mDrawItem.setState(DrawItem.STATE_KYOKU_START);

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());

			// �A�N�V������҂B
			m_playerAction.actionWait();
//			break;
//		case HAIPAI:
			// ��v��ݒ肵�āA�v���C��Ԃɂ���B
			mDrawItem.setState(DrawItem.STATE_PLAY);
			synchronized (mDrawItem) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
					mInfoUi.copyKawa(mDrawItem.mPlayerInfos[i].mKawa, i);
					mDrawItem.mPlayerInfos[i].mTsumoHai = null;
				}
			}

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case RYUUKYOKU:// ����
			// �T�C�U����ǂ̊J�n�ƍl����B
			mDrawItem.setState(DrawItem.STATE_RYUUKYOKU);

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());

			// �A�N�V������҂B
			m_playerAction.actionWait();
			break;
		case NAGASHI:// ����
			// �����\�����Ȃ��B
			break;
		case TSUMO:// �c��
			// �c���v���擾����B
			mDrawItem.mPlayerInfos[mInfoUi.getJikaze()].mTsumoHai = mInfoUi.getTsumoHai();

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case TSUMO_AGARI:// �c��������
			mDrawItem.setState(DrawItem.STATE_TSUMO);

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());

			// �A�N�V������҂B
			Log.d(TAG, "actionWait start");
			m_playerAction.actionWait();
			Log.d(TAG, "actionWait end");
			break;
		case SUTEHAI:// �̔v
			Log.e(TAG, "SUTEHAI fromKaze = " + fromKaze);
			// ��v���R�s�[����B
			mInfoUi.copyTehai(mDrawItem.mPlayerInfos[fromKaze].mTehai, fromKaze);

			// �͂��R�s�[����B
			mInfoUi.copyKawa(mDrawItem.mPlayerInfos[fromKaze].mKawa, fromKaze);

			// �c���v���Ȃ����B
			mDrawItem.mPlayerInfos[fromKaze].mTsumoHai = null;

			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case SELECT_SUTEHAI:
			if (fromKaze == mInfoUi.getJikaze()) {
				for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
					mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
				}
					//mDrawItem.tsumoKaze = 5;
					//mDrawItem.tsumoHai = null;
					//mDrawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
			}
			break;
		case UI_WAIT_RIHAI:
			mDrawItem.setSkipIdx(mInfoUi.getSutehaiIdx());
			mDrawItem.mState = DrawItem.STATE_RIHAI_WAIT;
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(PROGRESS_WAIT_TIME, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			mSelectSutehaiIdx = 13;
			mDrawItem.mState = DrawItem.STATE_PLAY;
			break;
		case PON:// �|��
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == mInfoUi.getJikaze()) {
				{
					for (int i = 0; i < mDrawItem.mPlayerInfos.length; i++) {
						mInfoUi.copyTehai(mDrawItem.mPlayerInfos[i].mTehai, i);
						mInfoUi.copyKawa(mDrawItem.mPlayerInfos[i].mKawa, i);
					}
					//mDrawItem.tsumoKaze = 5;
					//mDrawItem.tsumoHai = null;
					//mDrawItem.tsumoHais[mInfoUi.getJikaze()] = null;
					this.postInvalidate(0, 0, getWidth(), getHeight());
				}

				/*
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
				*/
			}
			break;
		case REACH:
			Log.e(TAG, "REACH fromKaze = " + fromKaze);
			// ��v���R�s�[����B
			mInfoUi.copyTehai(mDrawItem.mPlayerInfos[fromKaze].mTehai, fromKaze);

			// �͂��R�s�[����B
			mInfoUi.copyKawa(mDrawItem.mPlayerInfos[fromKaze].mKawa, fromKaze);

			// �c���v���Ȃ����B
			mDrawItem.mPlayerInfos[fromKaze].mTsumoHai = null;

			mDrawItem.mState = DrawItem.STATE_REACH;
			this.postInvalidate(0, 0, getWidth(), getHeight());
			try {
				Thread.sleep(2000, 0);
			} catch (InterruptedException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			mDrawItem.mState = DrawItem.STATE_PLAY;
			// �`�悷��B
			this.postInvalidate(0, 0, getWidth(), getHeight());
			break;
		case RON_AGARI:// ����
			Log.d(TAG, "RON_AGARI");
			mDrawItem.mState = DrawItem.STATE_RON;
			this.postInvalidate(0, 0, getWidth(), getHeight());

			// �A�N�V������҂B
			Log.d(TAG, "actionWait start");
			m_playerAction.actionWait();
			Log.d(TAG, "actionWait end");
			break;
		default:
			break;
		}

		return EventId.NAGASHI;
	}

	public int getISutehai() {
		return mSutehaiIdx;
	}

	/**
	 * �������Ă������\�����܂��B
	 *
	 * @param jikaze
	 *            ����
	 * @return�@
	 */
	public void jikazeToString(Hai addHai) {
		/*
		String[] yakuNames = this.mInfoUi.getYakuName(tehai, addHai);
		for (int i = 0; i < yakuNames.length; i++) {
			System.out.println(yakuNames[i]);
		}
		*/
	}
}