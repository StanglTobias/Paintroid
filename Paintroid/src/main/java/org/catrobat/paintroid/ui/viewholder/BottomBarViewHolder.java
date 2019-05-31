/*
 * Paintroid: An image manipulation application for Android.
 * Copyright (C) 2010-2015 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.paintroid.ui.viewholder;

import android.view.View;

import org.catrobat.paintroid.contract.MainActivityContracts;
import org.catrobat.paintroid.tools.ToolType;

public class BottomBarViewHolder implements MainActivityContracts.BottomBarViewHolder {
	public final View layout;
	public final int orientation;

	public BottomBarViewHolder(View layout) {
		this.layout = layout;
		this.orientation = layout.getResources().getConfiguration().orientation;
	}

	@Override
	public void show() {
		layout.setVisibility(View.VISIBLE);
	}

	@Override
	public void hide() {
		layout.setVisibility(View.GONE);
	}

	@Override
	public void selectToolButton(ToolType toolType) {
		View buttonView = layout.findViewById(toolType.getToolButtonID());
		buttonView.setSelected(true);
		hide();
	}

	@Override
	public void deSelectToolButton(ToolType toolType) {
		View buttonView = layout.findViewById(toolType.getToolButtonID());
		buttonView.setSelected(false);
	}

	@Override
	public boolean isVisible() {
		return layout.getVisibility() == View.VISIBLE;
	}
}

