/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1260.common;

import static business.blap.NSAL1260.constant.NSAL1260Constant.NSAM0478E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import business.blap.NSAL1260.NSAL1260CMsg;
import business.blap.NSAL1260.NSAL1260Query;
import business.blap.NSAL1260.NSAL1260SMsg;
import business.blap.NSAL1260.NSAL1260_ASMsg;
import business.blap.NSAL1260.NSAL1260_QCMsgArray;
import business.db.BLLG_CYCLETMsg;
import business.db.MTR_READ_METHTMsg;
import business.parts.NMZC610001PMsg;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/30   Hitachi         Y.Takeno        Update          QC#447
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2018/08/24   CITS            M.Naito         Update          QC#24021
 *</pre>
 */
public class NSAL1260CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1260CMsg
     * @param sMsg NSAL1260SMsg
     */
    public static void clearMsg(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        cMsg.xxFileData.clear();
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        cMsg.xxPageShowCurNum.clear();
        cMsg.xxPageShowTotNum.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL1260CMsg
     */
    public static void createPullDown(NSAL1260CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_L, cMsg.bllgCycleDescTxt_L);
        // START 2017/05/26 Y.Osawa [QC#18560, ADD]
        deletePulldownList(cMsg.bllgCycleCd_L, cMsg.bllgCycleDescTxt_L, BLLG_CYCLE.DAILY);
        // END   2017/05/26 Y.Osawa [QC#18560, ADD]
        ZYPCodeDataUtil.createPulldownList(MTR_READ_METH.class, cMsg.mtrReadMethCd_L, cMsg.mtrReadMethDescTxt_L);
    }

    /**
     * getBllgCycleCd
     * @param glblCmpyCd String
     * @param bllgCycleDescTxt String
     * @return String
     */
    public static String getBllgCycleCd(String glblCmpyCd, String bllgCycleDescTxt) {

        NSAL1260Query query = NSAL1260Query.getInstance();
        if (bllgCycleDescTxt.length() <= 2) {
            BLLG_CYCLETMsg bllgCycleTmsg = query.getBllgCycleCdByCd(glblCmpyCd, bllgCycleDescTxt);
            if (bllgCycleTmsg != null) {
                return bllgCycleTmsg.bllgCycleCd.getValue();
            }
        }
        BLLG_CYCLETMsg bllgCycleTmsg = query.getBllgCycleCdByDescTxt(glblCmpyCd, bllgCycleDescTxt);
        if (bllgCycleTmsg != null) {
            return bllgCycleTmsg.bllgCycleCd.getValue();
        }
        return null;
    }

    /**
     * getMtrReadMethCd
     * @param glblCmpyCd String
     * @param mtrReadMethDescTxt String
     * @return String
     */
    public static String getMtrReadMethCd(String glblCmpyCd, String mtrReadMethDescTxt) {

        NSAL1260Query query = NSAL1260Query.getInstance();
        if (mtrReadMethDescTxt.length() <= 2) {
            MTR_READ_METHTMsg mtrReadMethTmsg = query.getMtrReadMethCdByCd(glblCmpyCd, mtrReadMethDescTxt);
            if (mtrReadMethTmsg != null) {
                return mtrReadMethTmsg.mtrReadMethCd.getValue();
            }
        }
        MTR_READ_METHTMsg mtrReadMethTmsg = query.getMtrReadMethCdByDescTxt(glblCmpyCd, mtrReadMethDescTxt);
        if (mtrReadMethTmsg != null) {
            return mtrReadMethTmsg.mtrReadMethCd.getValue();
        }
        return null;
    }

    /**
     * existsContr
     * @param cMsg NSAL1260CMsg
     * @param sMsgLine NSAL1260_ASMsg
     * @return boolean
     */
    public static boolean existsContr(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {

        NSAL1260Query query = NSAL1260Query.getInstance();
        BigDecimal count = query.getContrCount(cMsg, sMsgLine);
        if (count != null && BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }

    /**
     * Set Output Parameter
     * @param cMsg NSAL1260CMsg
     * @param sMsg NSAL1260SMsg
     */
    public static void setOutputParam(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_AA", ZYPConstant.CHKBOX_ON_Y);

        NSAL1260_QCMsgArray cMsgArray = cMsg.Q;
        int idx = 0;

        // Set Output Parameter
        for (int selectedRow : selectedRows) {
            if (cMsgArray.length() <= idx) {
                break;
            }
            setValue(cMsgArray.no(idx).svcConfigMstrPk_Q, sMsg.A.no(selectedRow).svcConfigMstrPk_A);
            setValue(cMsgArray.no(idx).svcMachMstrPk_Q, sMsg.A.no(selectedRow).svcMachMstrPk_A);
            setValue(cMsgArray.no(idx).contrEffFromDt_Q, sMsg.A.no(selectedRow).contrVrsnEffFromDt_A);
            setValue(cMsgArray.no(idx).contrEffThruDt_Q, sMsg.A.no(selectedRow).contrVrsnEffThruDt_A);
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(selectedRow).xxChkBox_AB.getValue())) {
                setValue(cMsgArray.no(idx).bllgFlg_Q, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(cMsgArray.no(idx).bllgFlg_Q, ZYPConstant.FLG_OFF_N);
            }
            setValue(cMsgArray.no(idx).basePrcDealAmt_Q, sMsg.A.no(selectedRow).basePrcDealAmt_A);
            setValue(cMsgArray.no(idx).bllgCycleCd_Q, sMsg.A.no(selectedRow).bllgCycleCd_A);
            setValue(cMsgArray.no(idx).mtrReadMethCd_Q, sMsg.A.no(selectedRow).mtrReadMethCd_A);
            idx++;
        }
        cMsg.Q.setValidCount(idx);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1260CMsg
     * @param sMsg NSAL1260SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
        cMsg.setCommitSMsg(true);
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL1260CMsg cMsg, NSAL1260SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // START 2016/05/30 [QC#447, ADD]
    /**
     * checkEOL
     * 
     * @param cMsg NSAL1260CMsg
     * @param sMsgLine NSAL1260_ASMsg
     * @return true/false
     */
    public static boolean checkEOL(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {
        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        infoBean.setSvcMachMstrPk(sMsgLine.svcMachMstrPk_A.getValue());
        infoBean.setEolDt(cMsg.contrVrsnEffFromDt_H.getValue());

        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            sMsgLine.serNum_A.setErrorInfo(1, infoBean.getXxMsgId());
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
            sMsgLine.serNum_A.setErrorInfo(1, NSAM0478E, new String[] {sMsgLine.serNum_A.getValue() });
            return false;
        }

        return true;
    }
   // END   2016/05/30 [QC#447, ADD]


    // START 2017/05/26 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/26 Y.Osawa [QC#18560, ADD]

    // START 2018/08/24 M.Naito [QC#24021, ADD]
    public static boolean checkAcctReln(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {

        if (cMsg.dsAcctNum_H.getValue().equals(sMsgLine.billToAcctNum_A.getValue())) {
            return true;
        }
        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I1, cMsg.dsAcctNum_H.getValue());
        setValue(apiMsg.dsAcctNum_I2, sMsgLine.billToAcctNum_A.getValue());
        setValue(apiMsg.slsDt, cMsg.slsDt.getValue());

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnRecipFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END 2018/08/24 M.Naito [QC#24021, ADD]
}
