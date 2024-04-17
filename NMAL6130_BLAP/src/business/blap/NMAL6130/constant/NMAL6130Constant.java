/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NMAL6130.constant;

import java.math.BigDecimal;

public interface NMAL6130Constant {

	/**
	 * Message ID : Download file not found.
	 */
	String MSG_ID_DOWNLOADFILE_NOT_FOUND = "ZYPM0007E";	
	
	/**
	 * Message ID : Attachment record duplicate.
	 */
	String MSG_ID_ATTACHMENT_DUPLICATE = "ZYPM0006E";
	
	/**
	 * Message ID : Unknown error.
	 */
	String MSG_ID_UNKNOWN_ERROR = "ZZM9501E";
	
	/**
	 * Database table column : BLOB column
	 */
	String DB_COL_BLOB = "MSTR_ATT_DATA_BLOD";
	
	/**
	 * Byte Size Unit : 1KB(kilobyte)
	 */
	BigDecimal BYTE_KB = new BigDecimal( 1*1024 );
	
	/**
	 * Byte Size Unit : 1MB(megabyte)
	 */
	BigDecimal BYTE_MB = new BigDecimal( 1*1024*1024 );
	
	/**
	 * Byte Size Unit : 1GB(gigabyte)
	 */
	BigDecimal BYTE_GB = new BigDecimal( 1*1024*1024*1024 );

}
