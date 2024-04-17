/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 * 09/28/2009   Fujitsu         FAP)Y.Furuta        Update          N/A
 * 05/22/2013   Fujitsu         T.Tozuka            Update          R-OM025 Inventory Transaction
 * 06/27/2013   Fujitsu         Y.Taoka             Update          R-OM025 Inventory Transaction
 *</pre>
 */
package business.blap.NLCL0180.constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;

public interface NLCL0180Constant {

    /**
     * ZERO_INT
     */
    int ZERO_INT = 0;

    /**
     */
    String MSG_ARG_MDSE_CD = "Merchandise Code";

    /**
     */
    String MSG_ARG_VENDOR_CD = "Vendor Code";

    // 2013/06/27 R-OM025 Inventory Transaction Mode Start
    /**
     */
    String SYS_SRC_CD = SYS_SRC.S21_LOGISTICS;
    // 2013/06/27 R-OM025 Inventory Transaction Mode End

    // 2013/05/22 R-OM025 Inventory Transaction Add Start
    /**
     * 
     */
    String BUSINESS_ID = "NLCL0180";
    // 2013/05/22 R-OM025 Inventory Transaction Add End

}
