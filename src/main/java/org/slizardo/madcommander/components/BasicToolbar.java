 /**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: BasicToolbar.java,v 1.14 2010/01/26 17:57:09 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import org.slizardo.madcommander.actions.BriefAction;
import org.slizardo.madcommander.actions.FilterDirectoriesAction;
import org.slizardo.madcommander.actions.FilterHiddensAction;
import org.slizardo.madcommander.actions.FilterNoneAction;
import org.slizardo.madcommander.actions.FullAction;
import org.slizardo.madcommander.actions.HelpAction;
import org.slizardo.madcommander.actions.HistoricalActions;
import org.slizardo.madcommander.actions.RefreshAction;
import org.slizardo.madcommander.actions.SearchAction;
import org.slizardo.madcommander.resources.images.IconFactory;


public class BasicToolbar extends JToolBar {

	public BasicToolbar() {
		super();

		setRollover(true);

		JButton refresh = new JButton(new RefreshAction());
		JButton brief = new JButton(new BriefAction());
		JButton full = new JButton(new FullAction());

        JButton prev = new JButton(HistoricalActions.getPreviousAction());
        JButton next = new JButton(HistoricalActions.getNextAction());

		JRadioButtonMenuItem filterNone = new JRadioButtonMenuItem(new FilterNoneAction());
		filterNone.setSelected(true);
		JRadioButtonMenuItem filterDirectories = new JRadioButtonMenuItem(new FilterDirectoriesAction());
		JRadioButtonMenuItem filterHiddens = new JRadioButtonMenuItem(new FilterHiddensAction());

		final JPopupMenu popup = new JPopupMenu();
		popup.add(filterNone);
		popup.add(filterDirectories);
		popup.add(filterHiddens);

		ButtonGroup group = new ButtonGroup();
		group.add(filterNone);
		group.add(filterDirectories);
		group.add(filterHiddens);

		JButton find = new JButton(new SearchAction());

        JButton help = new JButton(new HelpAction());

		final JButton filters = new JButton("Filter", IconFactory.newIcon("filter.gif"));
		filters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				popup.show(filters, 0, filters.getY()
						+ filters.getHeight() + 1);
			}
		});
        
        add(prev);
		add(next);
		addSeparator();
		add(refresh);
		addSeparator();
		add(brief);
		add(full);
		addSeparator();
		add(find);
		add(filters);
		addSeparator();
		add(help);
	}
}
