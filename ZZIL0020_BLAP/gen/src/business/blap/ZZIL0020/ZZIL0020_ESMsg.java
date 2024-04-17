//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20100204110010000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0020_ESMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0020_ESMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MQ_CONFIG_JNDI_ID_E*/
	public final EZDSStringItem              mqConfigJndiId_E;

    /** PROVIDER_URL_E*/
	public final EZDSStringItem              providerUrl_E;

    /** QUEUE_FACTORY_JNDI_NM_E*/
	public final EZDSStringItem              queueFactoryJndiNm_E;

    /** QUEUE_JNDI_NM_E*/
	public final EZDSStringItem              queueJndiNm_E;

    /** REPLY_QUEUE_JNDI_NM_E*/
	public final EZDSStringItem              replyQueueJndiNm_E;

    /** FAIL_QUEUE_JNDI_NM_E*/
	public final EZDSStringItem              failQueueJndiNm_E;


	/**
	 * ZZIL0020_ESMsg is constructor.
	 * The initialization when the instance of ZZIL0020_ESMsg is generated.
	 */
	public ZZIL0020_ESMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0020_ESMsg is constructor.
	 * The initialization when the instance of ZZIL0020_ESMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0020_ESMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mqConfigJndiId_E = (EZDSStringItem)newItem("mqConfigJndiId_E");
		providerUrl_E = (EZDSStringItem)newItem("providerUrl_E");
		queueFactoryJndiNm_E = (EZDSStringItem)newItem("queueFactoryJndiNm_E");
		queueJndiNm_E = (EZDSStringItem)newItem("queueJndiNm_E");
		replyQueueJndiNm_E = (EZDSStringItem)newItem("replyQueueJndiNm_E");
		failQueueJndiNm_E = (EZDSStringItem)newItem("failQueueJndiNm_E");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0020_ESMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0020_ESMsgArray();
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

