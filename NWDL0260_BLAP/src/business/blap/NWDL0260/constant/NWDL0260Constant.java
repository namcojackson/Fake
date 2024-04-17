/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWDL0260.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;

public interface NWDL0260Constant extends LOC_STS {

    String Y = ZYPConstant.FLG_ON_Y;
    String N = ZYPConstant.FLG_OFF_N;

    enum MsgId {

        /**
         * No search results found.
         */
        NZZM0000E,

        /**
         * There are too many search results, there is data that cannot be displayed.
         */
        NZZM0001W,

        /**
         * This data has been updated by another user.
         */
        NZZM0003E,
        
        /**
         * [@] field exceeded maximum value.
         */
        ZZM9002E,
    }
    
}
