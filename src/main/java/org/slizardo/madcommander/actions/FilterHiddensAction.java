/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilterHiddensAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.filters.FilterHiddens;
import org.slizardo.madcommander.resources.languages.Translator;


public class FilterHiddensAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269733626952310453L;

	public FilterHiddensAction() {
		super(Translator.text("Filter_hiddens"));
	}
	
	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().setFilter(new FilterHiddens());
		MainGUI.app.getSource().refreshFiles();
	}
}
