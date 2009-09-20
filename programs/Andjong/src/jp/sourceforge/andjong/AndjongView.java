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
import jp.sourceforge.andjong.mahjong.Hai;
import jp.sourceforge.andjong.mahjong.InfoUI;
import jp.sourceforge.andjong.mahjong.Kawa;
import jp.sourceforge.andjong.mahjong.Mahjong;
import jp.sourceforge.andjong.mahjong.SuteHai;
import jp.sourceforge.andjong.mahjong.Tehai;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
	private static final String TAG = "Sudoku";

	private static final String SELX = "selX";
	private static final String SELY = "selY";
	private static final String VIEW_STATE = "viewState";
	private static final int ID = 42;

	private float width; // �}�X�̉��̒���
	private float height; // �}�X�̏c�̒���
	private int selX; // �I�����ꂽ�}�X��X���̓Y��
	private int selY; // �I�����ꂽ�}�X��Y���̓Y��
	private final Rect selRect = new Rect();

	private final Game game;

	private Bitmap[] m_hai_bitmap;
	private Bitmap m_ura_bitmap;

	public AndjongView(Context context) {

		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);

		// ...
		setId(ID);

		initBitmap();
	}

	private void initBitmap() {
		Resources res = this.getContext().getResources();
		m_hai_bitmap = new Bitmap[Hai.ID_MAX + 1];
		m_hai_bitmap[0] = BitmapFactory.decodeResource(res, R.drawable.m_hai_00_wan_1);
		m_hai_bitmap[1] = BitmapFactory.decodeResource(res, R.drawable.m_hai_01_wan_2);
		m_hai_bitmap[2] = BitmapFactory.decodeResource(res, R.drawable.m_hai_02_wan_3);
		m_hai_bitmap[3] = BitmapFactory.decodeResource(res, R.drawable.m_hai_03_wan_4);
		m_hai_bitmap[4] = BitmapFactory.decodeResource(res, R.drawable.m_hai_04_wan_5);
		m_hai_bitmap[5] = BitmapFactory.decodeResource(res, R.drawable.m_hai_05_wan_6);
		m_hai_bitmap[6] = BitmapFactory.decodeResource(res, R.drawable.m_hai_06_wan_7);
		m_hai_bitmap[7] = BitmapFactory.decodeResource(res, R.drawable.m_hai_07_wan_8);
		m_hai_bitmap[8] = BitmapFactory.decodeResource(res, R.drawable.m_hai_08_wan_9);

		m_hai_bitmap[9] = BitmapFactory.decodeResource(res, R.drawable.m_hai_09_pin_1);
		m_hai_bitmap[10] = BitmapFactory.decodeResource(res, R.drawable.m_hai_10_pin_2);
		m_hai_bitmap[11] = BitmapFactory.decodeResource(res, R.drawable.m_hai_11_pin_3);
		m_hai_bitmap[12] = BitmapFactory.decodeResource(res, R.drawable.m_hai_12_pin_4);
		m_hai_bitmap[13] = BitmapFactory.decodeResource(res, R.drawable.m_hai_13_pin_5);
		m_hai_bitmap[14] = BitmapFactory.decodeResource(res, R.drawable.m_hai_14_pin_6);
		m_hai_bitmap[15] = BitmapFactory.decodeResource(res, R.drawable.m_hai_15_pin_7);
		m_hai_bitmap[16] = BitmapFactory.decodeResource(res, R.drawable.m_hai_16_pin_8);
		m_hai_bitmap[17] = BitmapFactory.decodeResource(res, R.drawable.m_hai_17_pin_9);

		m_hai_bitmap[18] = BitmapFactory.decodeResource(res, R.drawable.m_hai_18_sou_1);
		m_hai_bitmap[19] = BitmapFactory.decodeResource(res, R.drawable.m_hai_19_sou_2);
		m_hai_bitmap[20] = BitmapFactory.decodeResource(res, R.drawable.m_hai_20_sou_3);
		m_hai_bitmap[21] = BitmapFactory.decodeResource(res, R.drawable.m_hai_21_sou_4);
		m_hai_bitmap[22] = BitmapFactory.decodeResource(res, R.drawable.m_hai_22_sou_5);
		m_hai_bitmap[23] = BitmapFactory.decodeResource(res, R.drawable.m_hai_23_sou_6);
		m_hai_bitmap[24] = BitmapFactory.decodeResource(res, R.drawable.m_hai_24_sou_7);
		m_hai_bitmap[25] = BitmapFactory.decodeResource(res, R.drawable.m_hai_25_sou_8);
		m_hai_bitmap[26] = BitmapFactory.decodeResource(res, R.drawable.m_hai_26_sou_9);

		m_hai_bitmap[27] = BitmapFactory.decodeResource(res, R.drawable.m_hai_27_ton);
		m_hai_bitmap[28] = BitmapFactory.decodeResource(res, R.drawable.m_hai_28_nan);
		m_hai_bitmap[29] = BitmapFactory.decodeResource(res, R.drawable.m_hai_29_sha);
		m_hai_bitmap[30] = BitmapFactory.decodeResource(res, R.drawable.m_hai_30_pe);

		m_hai_bitmap[31] = BitmapFactory.decodeResource(res, R.drawable.m_hai_31_haku);
		m_hai_bitmap[32] = BitmapFactory.decodeResource(res, R.drawable.m_hai_32_hatsu);
		m_hai_bitmap[33] = BitmapFactory.decodeResource(res, R.drawable.m_hai_33_chun);

		m_ura_bitmap = BitmapFactory.decodeResource(res, R.drawable.m_hai_ura);
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Parcelable p = super.onSaveInstanceState();
		Log.d(TAG, "onSaveInstanceState");
		Bundle bundle = new Bundle();
		bundle.putInt(SELX, selX);
		bundle.putInt(SELY, selY);
		bundle.putParcelable(VIEW_STATE, p);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		Log.d(TAG, "onRestoreInstanceState");
		Bundle bundle = (Bundle) state;
		select(bundle.getInt(SELX), bundle.getInt(SELY));
		super.onRestoreInstanceState(bundle.getParcelable(VIEW_STATE));
		return;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 9f;
		height = h / 9f;
		getRect(selX, selY, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	private boolean isDraw = false;

	@Override
	protected void onDraw(Canvas canvas) {
		isDraw = true;

		// �w�i��`�悷��
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.puzzle_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		Paint tehaiPaint = new Paint();
		Hai[] jyunTehai = printTehai.getJyunTehai();
		int jyunTehaiLength = printTehai.getJyunTehaiLength();
		for (int i = 0; i < jyunTehaiLength; i++) {
			canvas.drawBitmap(m_hai_bitmap[jyunTehai[i].getId()], 19 * i, 26,
					tehaiPaint);
		}

		isDraw = false;
/*
		// �Ֆʂ�`�悷��...

		// �g���̐F���`����
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));

		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.puzzle_light));

		// �}�X�ڂ���؂��
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, light);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
					hilite);
		}

		// 3 x 3 �̃u���b�N����؂��
		for (int i = 0; i < 9; i++) {
			if (i % 3 != 0)
				continue;
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
					hilite);
		}

		// ���l��`�悷��...
		// ���l�̐F�ƃX�^�C�����`����
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(height * 0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);

		// �}�X�ڂ̒����ɐ�����`��
		FontMetrics fm = foreground.getFontMetrics();
		// X�������ŃZ���^�����O����B�A���C�������g���g��
		float x = width / 2;
		// Y�������ŃZ���^�����O����B
		// �܂��A�Z���g/�f�B�Z���g�i�㔼���Ɖ������j�𒲂ׂ�B
		float y = height / 2 - (fm.ascent + fm.descent) / 2;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(this.game.getTileString(i, j), i * width + x, j
						* height + y, foreground);
			}
		}

		if (Settings.getHints(getContext())) {
			// �q���g��`�悷��...

			// �c���ꂽ��̐��Ɋ�Â��ăq���g�̐F��h��
			Paint hint = new Paint();
			int c[] = { getResources().getColor(R.color.puzzle_hint_0),
					getResources().getColor(R.color.puzzle_hint_1),
					getResources().getColor(R.color.puzzle_hint_2), };
			Rect r = new Rect();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int movesleft = 9 - game.getUsedTiles(i, j).length;
					if (movesleft < c.length) {
						getRect(i, j, r);
						hint.setColor(c[movesleft]);
						canvas.drawRect(r, hint);
					}
				}
			}

		}

		// �I�����ꂽ�}�X��`�悷��...
		Log.d(TAG, "selRect=" + selRect);
		Paint selected = new Paint();
		selected.setColor(getResources().getColor(R.color.puzzle_selected));
		canvas.drawRect(selRect, selected);
*/
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

		select((int) (event.getX() / width), (int) (event.getY() / height));
		game.showKeypadOrError(selX, selY);
		Log.d(TAG, "onTouchEvent: x " + selX + ", y " + selY);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event=" + event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			select(selX, selY - 1);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			select(selX, selY + 1);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			select(selX - 1, selY);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			select(selX + 1, selY);
			break;
		case KeyEvent.KEYCODE_0:
		case KeyEvent.KEYCODE_SPACE:
			setSelectedTile(0);
			break;
		case KeyEvent.KEYCODE_1:
			setSelectedTile(1);
			break;
		case KeyEvent.KEYCODE_2:
			setSelectedTile(2);
			break;
		case KeyEvent.KEYCODE_3:
			setSelectedTile(3);
			break;
		case KeyEvent.KEYCODE_4:
			setSelectedTile(4);
			break;
		case KeyEvent.KEYCODE_5:
			setSelectedTile(5);
			break;
		case KeyEvent.KEYCODE_6:
			setSelectedTile(6);
			break;
		case KeyEvent.KEYCODE_7:
			setSelectedTile(7);
			break;
		case KeyEvent.KEYCODE_8:
			setSelectedTile(8);
			break;
		case KeyEvent.KEYCODE_9:
			setSelectedTile(9);
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			game.showKeypadOrError(selX, selY);
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}

	public void setSelectedTile(int tile) {
		if (game.setTileIfValid(selX, selY, tile)) {
			invalidate();// �q���g�͕ς��\������
		} else {
			// ���̃}�X�̐��l�͑I�ׂȂ��l
			Log.d(TAG, "setSelectedTile: invalid: " + tile);
			startAnimation(AnimationUtils.loadAnimation(game, R.anim.shake));
		}
	}

	private void select(int x, int y) {
		invalidate(selRect);
		selX = Math.min(Math.max(x, 0), 8);
		selY = Math.min(Math.max(y, 0), 8);
		getRect(selX, selY, selRect);
		invalidate(selRect);
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height),
				(int) (x * width + width), (int) (y * height + height));
	}

	/** InfoUI�I�u�W�F�N�g */
	private InfoUI infoUi;

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
		this.infoUi = infoUi;
		this.name = name;
	}

	private void dispInfo() {
		System.out.println("-------- INFO --------");
		int kyoku = infoUi.getkyoku();
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
		System.out.print("[" + infoUi.getHonba() + "�{��]");
		System.out.println("[�c��:" + infoUi.getTsumoRemain() + "]");
		// �h���\���v��\�����܂��B
		Hai[] doras = infoUi.getDoras();
		System.out.print("[�h���\���v]");
		for (Hai hai : doras) {
			System.out.print("[" + idToString(hai.getId()) + "]");
		}
		System.out.println();

		// ���O�Ȃǂ�\�����Ă݂�B
		System.out.println("[" + jikazeToString(0) + "][" + infoUi.getName(0)
				+ "][" + infoUi.getTenbou(0) + "]");
		System.out.println("[" + jikazeToString(1) + "][" + infoUi.getName(1)
				+ "][" + infoUi.getTenbou(1) + "]");
		System.out.println("[" + jikazeToString(2) + "][" + infoUi.getName(2)
				+ "][" + infoUi.getTenbou(2) + "]");
		System.out.println("[" + jikazeToString(3) + "][" + infoUi.getName(3)
				+ "][" + infoUi.getTenbou(3) + "]");

		System.out.println("----------------------");
	}

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
			break;
		case SAIFURI:// �T�C�U��
			dispInfo();
			break;
		case RYUUKYOKU:// ����
			System.out.println("[����]");
			break;
		case NAGASHI:// ����
			// �\�����邱�Ƃ͂Ȃ��B
			break;
		case TSUMO:// �c��
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][�c��]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			if (isDraw == false) {
				infoUi.copyTehai(printTehai, infoUi.getJikaze());
				isPrintTehai = true;
				this.post(new Runnable() {
					public void run() {
						selRect.set(0, 0, getWidth(), getHeight());
						invalidate(selRect);
					}
				});
			}

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getId()));
			break;
		case TSUMOAGARI:// �c��������
			System.out.print("[" + jikazeToString(infoUi.getJikaze())
					+ "][�c��������]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// �c���v��\�����܂��B
			System.out
					.println(":" + idToString((infoUi.getTsumoHai()).getId()));
			break;
		case SUTEHAI:// �̔v
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�̔v]");

				// �͂�\�����܂��B
				printKawa(kawa);

				// �̔v��\�����܂��B
				// System.out.println(":"
				// + idToString((infoUi.getSuteHai()).getId()));
				System.out.println();
			}
			break;
		case PON:// �|��
			// �����̎̔v�݂̂�\�����܂��B
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
						+ "][�|��]");

				// ��v��\�����܂��B
				printJyunTehai(tehai);

				System.out.println();

				System.out.print("[" + jikazeToString(infoUi.getJikaze())
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
			if (fromKaze == infoUi.getJikaze()) {
				System.out.print("[" + jikazeToString(infoUi.getJikaze())
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
			System.out
					.print("[" + jikazeToString(infoUi.getJikaze()) + "][����]");

			// ��v��\�����܂��B
			printJyunTehai(tehai);

			// ������v��\�����܂��B
			System.out.println(":" + idToString((infoUi.getSuteHai()).getId()));
			break;
		default:
			break;
		}

		return EID.NAGASHI;
	}

	public int getSutehaiIdx() {
		return sutehaiIdx;
	}

	private boolean isPrintTehai = false;
	private Tehai printTehai = new Tehai();

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
		infoUi.copyTehai(tehai, infoUi.getJikaze());
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
		infoUi.copyKawa(kawa, infoUi.getJikaze());
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
		String[] yakuNames = this.infoUi.getYakuName(tehai, addHai);
		for(int i = 0 ; i < yakuNames.length ; i++){
			System.out.println(yakuNames[i]);
		}
	}
}