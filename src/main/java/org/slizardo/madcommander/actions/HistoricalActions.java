/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: HistoricalActions.java,v 1.1 2006/03/21 17:25:37 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import javax.swing.Action;

public class HistoricalActions {

    private static PreviousAction previousAction;
    private static NextAction nextAction;
    
    public static Action getPreviousAction() {
        if(previousAction == null) previousAction = new PreviousAction();
        
        return previousAction;
    }
    
    public static Action getNextAction() {
        if(nextAction == null) nextAction = new NextAction();
        
        return nextAction;
    }
}
