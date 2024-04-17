/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0140.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CLT_STRGYTMsg;
import business.db.CLT_STRGY_RELN_CUST_TPTMsg;
import business.db.CLT_STRGY_RELN_CUST_TPTMsgArray;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         K.Kojima        Create          N/A
 * 2016/03/17   Hitachi         K.Kojima        Update          CSA QC#5349
 *</pre>
 */
public class NFDL0140CommonLogic {

    /**
     * getSearchData
     * @param glblCmpyCd String
     * @return CLT_WRK_ITEMTMsgArray
     */
    public static CLT_STRGY_RELN_CUST_TPTMsgArray getSearchData(String glblCmpyCd, String cltStrgyCd) {
        CLT_STRGY_RELN_CUST_TPTMsg tMsg = new CLT_STRGY_RELN_CUST_TPTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cltStrgyCd01", cltStrgyCd);
        CLT_STRGY_RELN_CUST_TPTMsgArray tMsgArr = (CLT_STRGY_RELN_CUST_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        return tMsgArr;
    }

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
     * get CLT_STRGY_RELN_WRK_ITEM By Primary Key For Update No Wait
     * @param glblCmpyCd String
     * @param cltStrgyCd String
     * @param cltCustTpCd String
     * @param cltOverDueRangeLowAmt BigDecimal
     * @return CLT_STRGY_RELN_WRK_ITEMTMsg
     */
    public static CLT_STRGY_RELN_CUST_TPTMsg getCltStrgyRelnCustTpForUpdateNoWait(String glblCmpyCd, String cltStrgyCd, String cltCustTpCd, BigDecimal cltOverDueRangeLowAmt) {
        CLT_STRGY_RELN_CUST_TPTMsg prmTMsg = new CLT_STRGY_RELN_CUST_TPTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.cltStrgyCd, cltStrgyCd);
        setValue(prmTMsg.cltCustTpCd, cltCustTpCd);
        // START 2016/03/17 K.Kojima [QC#5349,ADD]
        setValue(prmTMsg.cltOverDueRangeLowAmt, cltOverDueRangeLowAmt);
        // END 2016/03/17 K.Kojima [QC#5349,ADD]
        return (CLT_STRGY_RELN_CUST_TPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

}
