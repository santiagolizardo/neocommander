/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: Historical.java,v 1.4 2006/03/21 17:25:36 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.util.ArrayList;
import java.util.ListIterator;

import org.slizardo.madcommander.actions.HistoricalActions;


public class Historical {

    private ArrayList<String> history;
    private ListIterator<String> iterator;
    
    public Historical() {
        history = new ArrayList<String>();
        iterator = history.listIterator();
    }
    
    public void updateActions() {
        boolean previousEnabled = iterator.hasPrevious();
        boolean nextEnabled = iterator.hasNext();
             
        HistoricalActions.getPreviousAction().setEnabled(previousEnabled);
        HistoricalActions.getNextAction().setEnabled(nextEnabled);
    }
    
    public void add(String path) {
        history.add(path);
        iterator = history.listIterator();
        while(iterator.hasNext()) iterator.next();
        updateActions();
    }
    
    public String getPrevious() {
        String previous = iterator.previous();
        updateActions();
        return previous;
    }
    
    public String getNext() {
        String next = iterator.next();
        updateActions();
        return next;
    }
}
