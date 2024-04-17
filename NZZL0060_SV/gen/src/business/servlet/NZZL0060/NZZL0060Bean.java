// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170216154532000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NZZL0060Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NZZL0060;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NZZL0060 is data bean class.
 */
public class NZZL0060Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/**
	 * Method name:NZZL0060 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NZZL0060Bean is generated
	 * <dd>Remarks:
	 */
	public NZZL0060Bean() {
		super("business.servlet.NZZL0060.NZZL0060BMsg");
	}

	/**
	 * get  XX_SCR_EVENT_NM.
	 * @return XX_SCR_EVENT_NM
	 */
	public String getXxScrEventNm() {
		return outputValue(xxScrEventNm);
	}

	/**
	 * set  XX_SCR_EVENT_NM.
	 * @param data XX_SCR_EVENT_NM
	 */
	public void setXxScrEventNm(String data) {
		inputValue(xxScrEventNm,data);
	}

}
