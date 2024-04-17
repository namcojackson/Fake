package com.canon.oracle.custapp.custinq.beans;

import java.util.ArrayList;

/**
 * Canon_E193_MainMenuObj Contains mainmenu prompt, url, submenu id and submenu information
 * Creation date: (7/26/2005 9:30:28 AM).
 * @author:
 */
public class Canon_E193_MainMenuObj {

	public String strPromptName;
	public String strUrlName;
	public String iSubMenuID;
	public ArrayList alSubMenu;

	/**
	 * Canon_E193_MainMenuObj constructor comment.
	 */
	public Canon_E193_MainMenuObj() {
		this.strPromptName = null;
		this.strUrlName = null;
		this.iSubMenuID = "";
		this.alSubMenu  = new java.util.ArrayList();
	}

	/**
	 * Get mainmenu prompt.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public String getPromptName() {
		return this.strPromptName;
	}

	/**
	 * Get mainmenu url.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public String getUrlName() {
		return this.strUrlName;
	}

	/**
	 * Get submenu id.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return int
 	 */
	public String getSubMenuID() {
		return this.iSubMenuID;
	}

	/**
	 * Get submenu arraylist.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.util.ArrayList
 	 */
	public java.util.ArrayList getAlSubMenu() {
		return alSubMenu;
	}

	/**
	 * Set mainmenu prompt.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param p java.lang.String
 	 */
	public void setPromptName(String p){
		this.strPromptName = p;
	}

	/**
	 * Set mainmenu url.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param u java.lang.String
 	 */
	public void setUrlName(String u) {
		this.strUrlName = u ;
	}

	/**
	 * Set submenu id.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param i int
 	 */
	public void setSubMenuID(String i) {
		this.iSubMenuID = i;
	}

	/**
	 * Set submenu arraylist.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param newSubMenu java.util.ArrayList
	 */
	public void setAlSubMenu(java.util.ArrayList newSubMenu) {
		this.alSubMenu = newSubMenu;
	}
}
