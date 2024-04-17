/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0340.common;

import static business.blap.NSBL0340.constant.NSBL0340Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NSBL0340.NSBL0340CMsg;
import business.blap.NSBL0340.NSBL0340SMsg;
import business.db.SVC_RQST_DOWN_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/10/18   Hitachi         A.Kohinata      Update          CSA QC#11030
 *</pre>
 */
public class NSBL0340CommonLogic {

    /**
     * Check Mandatory
     * @param cMsg NSBL0340CMsg
     * @param sMsg NSBL0340SMsg
     * @return boolean
     */
    public static boolean checkMandatory(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.orgCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Organization Code" });
            return false;
        }

        // del start 2016/10/18 CSA Defect#11030
        //if (!ZYPCommonFunc.hasValue(cMsg.orgLayerNum)) {
        //    cMsg.setMessageInfo(ZZM9000E, new String[] {"Organization Layer Number" });
        //    return false;
        //}
        // del end 2016/10/18 CSA Defect#11030

        if (!ZYPCommonFunc.hasValue(cMsg.svcMgrTpCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Manager Type Code" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.svcRqstDownTpCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Request Down Type Code" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.svcMgrPsnCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service manager Person Code" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.svcRqstCritTpCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"Service Request Criteria Type Code" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.dsSvcCallTpCd) && !ZYPCommonFunc.hasValue(cMsg.fsrSvcTaskStsRelnPk) && !ZYPCommonFunc.hasValue(cMsg.techCd) && !ZYPCommonFunc.hasValue(cMsg.svcCallSrcTpCd)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"DS Service Call Type Code or FSR Service Task Status Relation PK or Technician Code or Service Call Source Type Code" });
            return false;
        }
        return true;
    }

    /**
     * Get SvcRqstDownTpInfo
     * @param cMsg NSBL0340CMsg
     * @param sMsg NSBL0340SMsg
     */
    public static void getSvcRqstDownTpInfo(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg) {

        SVC_RQST_DOWN_TPTMsg inTMsg = new SVC_RQST_DOWN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.svcRqstDownTpCd, cMsg.svcRqstDownTpCd);
        SVC_RQST_DOWN_TPTMsg outTMsg = (SVC_RQST_DOWN_TPTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            cMsg.setMessageInfo(NWDM0007E);
            return;
        }
        setValue(cMsg.svcRqstDownFlgColNm, outTMsg.svcRqstDownFlgColNm);
    }

    /**
     * Pagenation
     * @param cMsg NSBL0340CMsg
     * @param sMsg NSBL0340SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSBL0340CMsg cMsg, NSBL0340SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sAry.getValidCount());
    }
}
