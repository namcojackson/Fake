/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0120.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLT_STRGYTMsg;
import business.db.CLT_STRGY_RELN_WRK_ITEMTMsg;
import business.db.CLT_WRK_ITEMTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0120CommonLogic {

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
        return (CLT_STRGYTMsg) EZDTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * get CLT_WRK_ITEM By Primary Key
     * @param glblCmpyCd String
     * @param cltWrkItemCd String
     * @return CLT_WRK_ITEMTMsg
     */
    public static CLT_WRK_ITEMTMsg getCltWrkItem(String glblCmpyCd, String cltWrkItemCd) {
        CLT_WRK_ITEMTMsg prmTMsg = new CLT_WRK_ITEMTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.cltWrkItemCd, cltWrkItemCd);
        return (CLT_WRK_ITEMTMsg) EZDTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * get CLT_STRGY_RELN_WRK_ITEM By Primary Key For Update No Wait
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @param cltWrkItemCd String
     * @return CLT_STRGY_RELN_WRK_ITEMTMsg
     */
    public static CLT_STRGY_RELN_WRK_ITEMTMsg getCltStrgyRelnWrkItemForUpdateNoWait(String glblCmpyCd, String cltStrgyCd, String cltWrkItemCd) {
        CLT_STRGY_RELN_WRK_ITEMTMsg prmTMsg = new CLT_STRGY_RELN_WRK_ITEMTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.cltStrgyCd, cltStrgyCd);
        setValue(prmTMsg.cltWrkItemCd, cltWrkItemCd);
        return (CLT_STRGY_RELN_WRK_ITEMTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

}
