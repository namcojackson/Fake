/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0150.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLT_STRGYTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0150CommonLogic {

    /**
     * get CLT_STRGY By Primary Key
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @return CLT_STRGYTMsg
     */
    public static CLT_STRGYTMsg getCltStrgy(String glblCmpyCd, String cltStrgyCd) {
        CLT_STRGYTMsg prmTMsg = new CLT_STRGYTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.cltStrgyCd, cltStrgyCd);
        return (CLT_STRGYTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
}
