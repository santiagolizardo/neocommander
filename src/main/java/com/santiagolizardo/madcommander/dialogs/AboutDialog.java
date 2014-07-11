/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs;

import javax.swing.JLabel;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.HtmlLabel;
import com.santiagolizardo.madcommander.resources.ResourcesLoader;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AboutDialog extends AbstractDialog {

	private static final long serialVersionUID = -3985858584067350439L;

	public AboutDialog(JFrame parentFrame) {
		super(parentFrame);

		setTitle("About this application");
		setModal(true);

		defineLayout();
	}

	private void defineLayout() {
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

		JLabel iconPanel = new JLabel(IconFactory.newIcon("icon.png"));

		JPanel leftPanel = new JPanel();
		leftPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
		leftPanel.add(iconPanel);

		container.add(leftPanel);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		String headlineText = String.format("<h1>%s <em>v%s</em></h1>",
				MainWindow.APP_NAME, MainWindow.APP_VERSION);
		String infoText = String.format("<p>%s</p>", String.format(
				Translator.tr("More info about the project at <a href=\"%s\">%s</a>."),
				MainWindow.APP_URL, MainWindow.APP_URL));
		String creditsText = ResourcesLoader.readResource(AboutDialog.class,
				"credits.html");

		String content = headlineText.concat(infoText).concat(creditsText);
		JEditorPane lblCredits = new HtmlLabel(content);
		lblCredits.setCaretPosition(0);

		JScrollPane scrollPane = new JScrollPane(lblCredits);
		scrollPane.setPreferredSize(new Dimension(380, 240));
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
		panel.add(scrollPane);

		container.add(panel);

		pack();
		setLocationRelativeTo(getOwner());
	}
}
