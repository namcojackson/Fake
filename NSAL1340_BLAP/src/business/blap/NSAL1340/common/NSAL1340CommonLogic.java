/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1340.common;

import business.blap.NSAL1340.NSAL1340CMsg;
import business.blap.NSAL1340.NSAL1340Query;
import business.db.MTR_LBTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_MTR_PKGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NSAL1340CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340CommonLogic {

    /**
     * getPrcListDispNm
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean getPrcListDispNm(NSAL1340CMsg bizMsg, String glblCmpyCd) {
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, bizMsg.prcCatgCd);

        PRC_CATGTMsg prcCatgResult = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);
        if (prcCatgResult == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, prcCatgResult.prcCatgNm);
        return true;
    }

    /**
     * getPrcMtrPkgNm
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean getPrcMtrPkgNm(NSAL1340CMsg bizMsg, String glblCmpyCd) {
        PRC_MTR_PKGTMsg prcMtrPkgTMsg = new PRC_MTR_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(prcMtrPkgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcMtrPkgTMsg.prcMtrPkgPk, bizMsg.prcMtrPkgPk);

        PRC_MTR_PKGTMsg prcMtrPkgResult = (PRC_MTR_PKGTMsg) S21FastTBLAccessor.findByKey(prcMtrPkgTMsg);
        if (prcMtrPkgResult == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcMtrPkgNm, prcMtrPkgResult.prcMtrPkgNm);
        return true;
    }

    /**
     * getPrcMtrPkgNm
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean getBllgCntrNm(NSAL1340CMsg bizMsg, String glblCmpyCd) {
        MTR_LBTMsg mtrLbTMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mtrLbTMsg.mtrLbCd, bizMsg.bllgMtrLbCd);

        MTR_LBTMsg mtrLbResult = (MTR_LBTMsg) S21FastTBLAccessor.findByKey(mtrLbTMsg);
        if (mtrLbResult == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.mtrLbNm, mtrLbResult.mtrLbNm);
        return true;
    }

    /**
     * getBandList
     * @param bizMsg Business Msg
     * @return boolean
     */
    public static boolean getBandList(NSAL1340CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NSAL1340Query.getInstance().getBandList(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            return false;
        }
        return true;
    }

}
