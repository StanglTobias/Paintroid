/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010-2011 The Catroid Team
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *  
 *  Paintroid: An image manipulation application for Android, part of the
 *  Catroid project and Catroid suite of software.
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://www.catroid.org/catroid_license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *   
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.tugraz.ist.paintroid.test.junit.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import at.tugraz.ist.paintroid.command.implementation.MagicCommand;
import at.tugraz.ist.paintroid.test.utils.PaintroidAsserts;
import at.tugraz.ist.paintroid.test.utils.PrivateAccess;

public class MagicCommandTest extends CommandTestSetup {

	// protected MagicCommand mCommandUnderTest;
	// protected MagicCommand mCommandUnderTestNull;
	// protected Paint mPaintUnderTest;
	// protected PointF mPointUnderTest;
	// protected Canvas mCanvasUnderTest;
	// protected Bitmap mBitmapUnderTest;
	// protected int mColorUnderTest = Color.BLUE;

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		// PrivateAccess = new PrivateAccess();
		// mPaintUnderTest = new Paint();
		// mPointUnderTest = new PointF();
		mCanvasUnderTest = null;
		// mBitmapUnderTest = Bitmap.createBitmap(10, 10, Config.ARGB_8888);
		mBitmapUnderTest.eraseColor(BITMAP_BASE_COLOR - 1);
		// mCanvasUnderTest.setBitmap(mBitmapUnderTest);
		// mPointUnderTest.x = mBitmapUnderTest.getWidth() / 2;
		// mPointUnderTest.y = mBitmapUnderTest.getHeight() / 2;
		// mPaintUnderTest.setColor(mColorUnderTest);
		mCommandUnderTest = new MagicCommand(mPaintUnderTest, mPointUnderTest);
		mCommandUnderTestNull = new MagicCommand(null, null);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testRun() {
		try {
			// PrivateAccess.setMemberValue(BaseCommand.class, mMagicCommandUnderTest, "mPaint", mPaintUnderTest);
			Bitmap expectedBitmap = mBitmapUnderTest.copy(Config.ARGB_8888, true);
			expectedBitmap.eraseColor(PAINT_BASE_COLOR);
			mCommandUnderTest.run(mCanvasUnderTest, mBitmapUnderTest);
			mCommandUnderTest.run(mCanvasUnderTest, mBitmapUnderTest);
			PaintroidAsserts.assertBitmapEquals(expectedBitmap, mBitmapUnderTest);
			mCommandUnderTestNull.run(null, null);
			mCommandUnderTestNull.run(null, mBitmapUnderTest);
			mCommandUnderTestNull.run(mCanvasUnderTest, null);
			mCommandUnderTestNull.run(mCanvasUnderTest, mBitmapUnderTest);
		} catch (Exception e) {
			fail("Failed with exception:" + e.toString());
		}
	}

	@Test
	public void testRunPointOutOfBitmapBounds() {
		try {
			mBitmapUnderTest = Bitmap.createBitmap((int) (mPointUnderTest.x - 1), (int) (mPointUnderTest.y - 1),
					Config.ARGB_8888);
			mCommandUnderTest.run(mCanvasUnderTest, mBitmapUnderTest);
			mCommandUnderTestNull.run(null, null);
			mCommandUnderTestNull.run(null, mBitmapUnderTest);
			mCommandUnderTestNull.run(mCanvasUnderTest, null);
		} catch (Exception e) {
			fail("Failed with exception:" + e.toString());
		}
	}

	@Test
	public void testRunReplaceAllExceptOne() {
		mBitmapUnderTest.setPixel(0, 0, BITMAP_REPLACE_COLOR);
		Bitmap expectedBitmap = Bitmap.createBitmap(mBitmapUnderTest.getWidth(), mBitmapUnderTest.getHeight(),
				Config.ARGB_8888);
		expectedBitmap.eraseColor(PAINT_BASE_COLOR);
		expectedBitmap.setPixel(0, 0, BITMAP_REPLACE_COLOR);
		mCommandUnderTest.run(null, mBitmapUnderTest);
		PaintroidAsserts.assertBitmapEquals(expectedBitmap, mBitmapUnderTest);

	}

	@Test
	public void testMagicCommand() {
		try {
			Point pointToTest = new Point((Point) PrivateAccess.getMemberValue(MagicCommand.class, mCommandUnderTest,
					"mColorPixel"));
			assertNotNull(pointToTest);
			assertEquals((int) mPointUnderTest.x, pointToTest.x);
			assertEquals((int) mPointUnderTest.y, pointToTest.y);
			assertNotNull(PrivateAccess.getMemberValue(MagicCommand.class, mCommandUnderTestNull, "mColorPixel"));
		} catch (Exception e) {
			fail("Failed with exception:" + e.toString());
		}
	}
}