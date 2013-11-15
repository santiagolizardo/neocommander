 /**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import com.santiagolizardo.madcommander.actions.BriefAction;
import com.santiagolizardo.madcommander.actions.FilterDirectoriesAction;
import com.santiagolizardo.madcommander.actions.FilterHiddensAction;
import com.santiagolizardo.madcommander.actions.FilterNoneAction;
import com.santiagolizardo.madcommander.actions.FullAction;
import com.santiagolizardo.madcommander.actions.HelpAction;
import com.santiagolizardo.madcommander.actions.HistoricalActions;
import com.santiagolizardo.madcommander.actions.RefreshAction;
import com.santiagolizardo.madcommander.actions.SearchAction;
import com.santiagolizardo.madcommander.resources.images.IconFactory;


public class BasicToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2081100107934692827L;

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
