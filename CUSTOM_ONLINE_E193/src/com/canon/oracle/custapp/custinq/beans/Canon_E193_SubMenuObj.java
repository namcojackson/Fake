package com.canon.oracle.custapp.custinq.beans;
/**
 * Canon_E193_SubMenuObj Contains submenu prompt and url information
 * Creation date: (7/26/2005 11:27:30 AM)
 * @author:
 */
public class Canon_E193_SubMenuObj {

	public String strPromptName;
	public String strUrlName;

	/**
	 * Canon_E193_SubMenuObj constructor comment.
	 */
	public Canon_E193_SubMenuObj() {
		this.strPromptName = null;
		this.strUrlName = null;
	}

	/**
	 * Get submenu prompt.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public String getPromptName() {
		return this.strPromptName;
	}

	/**
	 * Get submenu url.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public String getUrlName() {
		return this.strUrlName;
	}

	/**
	 * Set submenu prompt and url.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public void setVal(String p, String u) {
		this.strPromptName = p;
		this.strUrlName = u ;
	}

	/**
	 * Set submenu prompt.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public void setPromptName(String p) {
		this.strPromptName = p;
	}

	/**
	 * Set submenu url.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @return java.lang.String
 	 */
	public void setUrlName(String u) {
		this.strUrlName = u ;
	}

}


