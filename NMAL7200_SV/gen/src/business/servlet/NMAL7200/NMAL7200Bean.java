// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160905111049000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7200Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL7200;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL7200 is data bean class.
 */
public class NMAL7200Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_ITEM_29_TXT*/
	public static final String xxScrItem29Txt = "xxScrItem29Txt";

	/** PRC_GRP_NM*/
	public static final String prcGrpNm = "prcGrpNm";

	/** PRC_GRP_DESC_TXT*/
	public static final String prcGrpDescTxt = "prcGrpDescTxt";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** A*/
	public static final String A = "A";

	/** XX_SCR_ITEM_10_TXT*/
	public static final String xxScrItem10Txt = "xxScrItem10Txt";

	/** PRC_CATG_NM*/
	public static final String prcCatgNm = "prcCatgNm";

	/** XX_SCR_ITEM_30_TXT*/
	public static final String xxScrItem30Txt = "xxScrItem30Txt";

	/** XX_SCR_ITEM_7_TXT*/
	public static final String xxScrItem7Txt = "xxScrItem7Txt";

	/** PRC_RULE_COND_TP_CD*/
	public static final String prcRuleCondTpCd = "prcRuleCondTpCd";

	/**
	 * Method name:NMAL7200 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL7200Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL7200Bean() {
		super("business.servlet.NMAL7200.NMAL7200BMsg");
	}

	/**
	 * get  XX_SCR_ITEM_29_TXT.
	 * @return XX_SCR_ITEM_29_TXT
	 */
	public String getXxScrItem29Txt() {
		return outputValue(xxScrItem29Txt);
	}

	/**
	 * set  XX_SCR_ITEM_29_TXT.
	 * @param data XX_SCR_ITEM_29_TXT
	 */
	public void setXxScrItem29Txt(String data) {
		inputValue(xxScrItem29Txt,data);
	}

	/**
	 * get  PRC_GRP_NM.
	 * @return PRC_GRP_NM
	 */
	public String getPrcGrpNm() {
		return outputValue(prcGrpNm);
	}

	/**
	 * set  PRC_GRP_NM.
	 * @param data PRC_GRP_NM
	 */
	public void setPrcGrpNm(String data) {
		inputValue(prcGrpNm,data);
	}

	/**
	 * get  PRC_GRP_DESC_TXT.
	 * @return PRC_GRP_DESC_TXT
	 */
	public String getPrcGrpDescTxt() {
		return outputValue(prcGrpDescTxt);
	}

	/**
	 * set  PRC_GRP_DESC_TXT.
	 * @param data PRC_GRP_DESC_TXT
	 */
	public void setPrcGrpDescTxt(String data) {
		inputValue(prcGrpDescTxt,data);
	}

	/**
	 * get  XX_RADIO_BTN.
	 * @return XX_RADIO_BTN
	 */
	public String getXxRadioBtn() {
		return outputValue(xxRadioBtn);
	}

	/**
	 * set  XX_RADIO_BTN.
	 * @param data XX_RADIO_BTN
	 */
	public void setXxRadioBtn(String data) {
		inputValue(xxRadioBtn,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_SCR_ITEM_10_TXT.
	 * @param idx1 List Number
	 * @return XX_SCR_ITEM_10_TXT
	 */
	public String getXxScrItem10Txt(int idx1) {
		return outputValue(A, idx1, xxScrItem10Txt);
	}

	/**
	 * set  XX_SCR_ITEM_10_TXT.
	 * @param data XX_SCR_ITEM_10_TXTArray
	 */
	public void setXxScrItem10Txt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxScrItem10Txt, data[j]);
		}
	}

	/**
	 * get  PRC_CATG_NM.
	 * @param idx1 List Number
	 * @return PRC_CATG_NM
	 */
	public String getPrcCatgNm(int idx1) {
		return outputValue(A, idx1, prcCatgNm);
	}

	/**
	 * set  PRC_CATG_NM.
	 * @param data PRC_CATG_NMArray
	 */
	public void setPrcCatgNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcCatgNm, data[j]);
		}
	}

	/**
	 * get  XX_SCR_ITEM_30_TXT.
	 * @param idx1 List Number
	 * @return XX_SCR_ITEM_30_TXT
	 */
	public String getXxScrItem30Txt(int idx1) {
		return outputValue(A, idx1, xxScrItem30Txt);
	}

	/**
	 * set  XX_SCR_ITEM_30_TXT.
	 * @param data XX_SCR_ITEM_30_TXTArray
	 */
	public void setXxScrItem30Txt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxScrItem30Txt, data[j]);
		}
	}

	/**
	 * get  XX_SCR_ITEM_7_TXT.
	 * @param idx1 List Number
	 * @return XX_SCR_ITEM_7_TXT
	 */
	public String getXxScrItem7Txt(int idx1) {
		return outputValue(A, idx1, xxScrItem7Txt);
	}

	/**
	 * set  XX_SCR_ITEM_7_TXT.
	 * @param data XX_SCR_ITEM_7_TXTArray
	 */
	public void setXxScrItem7Txt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxScrItem7Txt, data[j]);
		}
	}

	/**
	 * get  PRC_RULE_COND_TP_CD.
	 * @param idx1 List Number
	 * @return PRC_RULE_COND_TP_CD
	 */
	public String getPrcRuleCondTpCd(int idx1) {
		return outputValue(A, idx1, prcRuleCondTpCd);
	}

	/**
	 * set  PRC_RULE_COND_TP_CD.
	 * @param data PRC_RULE_COND_TP_CDArray
	 */
	public void setPrcRuleCondTpCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcRuleCondTpCd, data[j]);
		}
	}

}
