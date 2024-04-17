package business.blap.NLBL0110.common;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.blap.NLBL0110.NLBL0110CMsg;
import business.blap.NLBL0110.NLBL0110SMsg;
import business.blap.NLBL0110.constant.NLBL0110Constant;

/**
 * <pre>
 * This common logic the constant used in the bussiness application
 *  program of BusinessID NLBL0110. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110CommonLogic implements NLBL0110Constant {

    /**
     * The method explanation: clear cMsg.
     * @param cMsg Business Component Interface Message
     */
    public static void clearCMsg(NLBL0110CMsg cMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
    }

    /**
     * The method explanation: clear sMsg. 
     * @param sMsg Global area information
     */
    public static void clearSMsg(NLBL0110SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
    }

    /**
     * Get Location Role Type(COMMA Format)
     * @param glblCmpyCd Global Company Code
     * @return Location Role Type Code List
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd) {
        return NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_ID);
    }
    // 2013/05/22 R-OM025 Inventory Transaction Add End
}
