/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: Configurable.java,v 1.2 2006/03/06 17:19:23 slizardo Exp $
 */
package org.slizardo.madcommander.config;

public interface Configurable {

	public void loadProperties();

	public void saveProperties();
}
