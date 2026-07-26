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

package com.santiagolizardo.madcommander.components;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class HtmlLabel extends JEditorPane implements HyperlinkListener {


	private static final Logger logger = Logger.getLogger(HtmlLabel.class.getName());

	public HtmlLabel(String text) {
		super("text/html", text);

		setEditable(false);
		setHighlighter(null);

		addHyperlinkListener(this);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent ev) {
		if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				URI uri = ev.getURL().toURI();
				Desktop.getDesktop().browse(uri);
			} catch (IOException | URISyntaxException e) {
				logger.warning(e.getMessage());
			}
		}
	}
}
