//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20090828172501000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0020F04FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0020F04 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0020F04FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MQ_CONFIG_JNDI_ID_E*/
	public final EZDFStringItem              mqConfigJndiId_E;

    /** PROVIDER_URL_E*/
	public final EZDFStringItem              providerUrl_E;

    /** QUEUE_FACTORY_JNDI_NM_E*/
	public final EZDFStringItem              queueFactoryJndiNm_E;

    /** QUEUE_JNDI_NM_E*/
	public final EZDFStringItem              queueJndiNm_E;

    /** REPLY_QUEUE_JNDI_NM_E*/
	public final EZDFStringItem              replyQueueJndiNm_E;

    /** FAIL_QUEUE_JNDI_NM_E*/
	public final EZDFStringItem              failQueueJndiNm_E;


	/**
	 * ZZIL0020F04FMsg is constructor.
	 * The initialization when the instance of ZZIL0020F04FMsg is generated.
	 */
	public ZZIL0020F04FMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0020F04FMsg is constructor.
	 * The initialization when the instance of ZZIL0020F04FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0020F04FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mqConfigJndiId_E = (EZDFStringItem)newItem("mqConfigJndiId_E");
		providerUrl_E = (EZDFStringItem)newItem("providerUrl_E");
		queueFactoryJndiNm_E = (EZDFStringItem)newItem("queueFactoryJndiNm_E");
		queueJndiNm_E = (EZDFStringItem)newItem("queueJndiNm_E");
		replyQueueJndiNm_E = (EZDFStringItem)newItem("replyQueueJndiNm_E");
		failQueueJndiNm_E = (EZDFStringItem)newItem("failQueueJndiNm_E");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0020F04FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0020F04FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mqConfigJndiId_E", "mqConfigJndiId_E", "E", null, TYPE_HANKAKUEISU, "10", null},
	{"providerUrl_E", "providerUrl_E", "E", null, TYPE_HANKAKUEISU, "200", null},
	{"queueFactoryJndiNm_E", "queueFactoryJndiNm_E", "E", null, TYPE_HANKAKUEISU, "200", null},
	{"queueJndiNm_E", "queueJndiNm_E", "E", null, TYPE_HANKAKUEISU, "200", null},
	{"replyQueueJndiNm_E", "replyQueueJndiNm_E", "E", null, TYPE_HANKAKUEISU, "200", null},
	{"failQueueJndiNm_E", "failQueueJndiNm_E", "E", null, TYPE_HANKAKUEISU, "200", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MQ_CONFIG_JNDI_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mqConfigJndiId_E
        {"PROVIDER_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//providerUrl_E
        {"QUEUE_FACTORY_JNDI_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//queueFactoryJndiNm_E
        {"QUEUE_JNDI_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//queueJndiNm_E
        {"REPLY_QUEUE_JNDI_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//replyQueueJndiNm_E
        {"FAIL_QUEUE_JNDI_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//failQueueJndiNm_E
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}
