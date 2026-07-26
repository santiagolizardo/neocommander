/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.dialogs.changeattributes;

import javax.swing.*;

public class AttributesPanel extends JPanel {


	private final JCheckBox readPermissionCheckbox;
	private final JCheckBox writePermissionCheckbox;
	private final JCheckBox executePermissionCheckbox;

	private final JCheckBox readOnlyPermissionCheckbox;

	public AttributesPanel() {
		super();

		setBorder(BorderFactory.createTitledBorder(" Attributes "));

		readPermissionCheckbox = new JCheckBox("Read");
		writePermissionCheckbox = new JCheckBox("Write");
		executePermissionCheckbox = new JCheckBox("Execute");

		readOnlyPermissionCheckbox = new JCheckBox("Read only");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(readPermissionCheckbox);
		add(writePermissionCheckbox);
		add(executePermissionCheckbox);
		add(Box.createVerticalStrut(10));
		add(readOnlyPermissionCheckbox);
	}

	public boolean isReadOnlyPermissionSelected() {
		return readOnlyPermissionCheckbox.isSelected();
	}

	public boolean isReadPermissionSelected() {
		return readPermissionCheckbox.isSelected();
	}

	public boolean isWritePermissionSelected() {
		return writePermissionCheckbox.isSelected();
	}

	public boolean isExecutePermissionSelected() {
		return executePermissionCheckbox.isSelected();
	}
}
