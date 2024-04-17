package business.blap.NMAL0150.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public interface NMAL0150Constant {
	
    /**
     * RETURN_CD_NORMAL -- 0000
     */
    String RETURN_CD_NORMAL = "0000";
	
    /**
     * Maximum Amount Value 
     */
	BigDecimal MAXIMUM_AMOUNT = new BigDecimal("999999999999999.9999");
}
