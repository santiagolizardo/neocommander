/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

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
