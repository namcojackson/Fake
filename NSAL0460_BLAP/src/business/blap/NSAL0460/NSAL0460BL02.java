/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0460;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0460.common.NSAL0460CommonLogic;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import static business.blap.NSAL0460.constant.NSAL0460Constant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/11/27   Hitachi         T.Iwamoto       Update          QC#1235
 * 2015/12/03   Hitachi         T.Iwamoto       Update          QC#1261
 * 2016/03/31   Hitachi         T.Tomita        Update          QC#4587
 * 2016/10/27   Hitachi         K.Kishimoto     Update          QC#15511
 * 2017/01/04   Hitachi         K.Ochiai        Update          QC#16584
 * 2017/01/17   Hitachi         K.Ochiai        Update          QC#16331
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2024/02/15   Hitachi         K.Watanabe      Update          QC#63529
 *</pre>
 */
public class NSAL0460BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0460_INIT".equals(screenAplID)) {
                doProcess_NSAL0460_INIT((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            } else if ("NSAL0460Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0460Scrn00_PagePrev((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            } else if ("NSAL0460Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0460Scrn00_PageNext((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            // START 2017/01/17 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0460Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0460Scrn00_CMN_Reset((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            // END 2017/01/17 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0460_NSAL1060".equals(screenAplID)) {
                doProcess_NSAL0460_NSAL1060((NSAL0460CMsg) cMsg, (NSAL0460SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460_INIT(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        // [QC#1261,MOD]START
        ZYPEZDItemValueSetter.setValue(cMsg.xxNum, ZYPCodeDataUtil.getNumConstValue(EXST_ELIG_READ_BEF_DAYS, cMsg.glblCmpyCd.getValue()));
        // [QC#1261,MOD]END

        NSAL0460CommonLogic.createPullDown(cMsg);

        // get Detail Data and Set SMsg
        getMainData(cMsg, sMsg);

        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460Scrn00_PagePrev(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        NSAL0460CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0460_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460Scrn00_PageNext(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        NSAL0460CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0460_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    /**
     * do process (Clear)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460Scrn00_CMN_Reset(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {
        doProcess_NSAL0460_INIT(cMsg, sMsg);
        // [QC#1261,MOD]START
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAM0200I);
        }
        // [QC#1261,MOD]END
    }

    /**
     * do process (NSAL0460_NSAL1060)
     * @param cMsg NSAL0460CMsg
     * @param sMsg NSAL0460SMsg
     */
    private void doProcess_NSAL0460_NSAL1060(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        for (int i = 0; i < cMsg.Q.getValidCount(); i++) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (cMsg.Q.no(i).svcPhysMtrPk_P.getValueInt() == sMsg.A.no(j).svcPhysMtrPk.getValueInt()) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).mtrReadDt, cMsg.Q.no(i).mtrReadDt_P);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).readMtrCnt, cMsg.Q.no(i).readMtrCnt_P);
                    // Add Start 2016/10/27 <QC#15511>
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).svcPhysMtrReadGrpSq, cMsg.Q.no(i).svcPhysMtrReadGrpSq_P);
                    // Add End   2016/10/27 <QC#15511>
                    // START 2016/03/31 T.Tomita [QC#4587, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxReadOnlyFlg, ZYPConstant.FLG_ON_Y);
                    // END 2016/03/31 T.Tomita [QC#4587, ADD]
                    // START 2017/01/04 K.Ochiai [QC#16584, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).dsMtrReadTpGrpCd, cMsg.Q.no(i).dsMtrReadTpGrpCd_P);
                    // END 2017/01/04 K.Ochiai [QC#16584, ADD]
                    // START 2016/03/31 T.Tomita [QC#4587, DEL]
//                    if (j < cMsg.A.length()) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).mtrReadDt, cMsg.Q.no(i).mtrReadDt_P);
//                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).readMtrCnt, cMsg.Q.no(i).readMtrCnt_P);
//                    }
                    // END 2016/03/31 T.Tomita [QC#4587, DEL]
                }
            }
        }
        // START 2016/03/31 T.Tomita [QC#4587, ADD]
        for (int cMsgIdx = 0, sMsgIdx = cMsg.xxPageShowFromNum.getValueInt() - 1; sMsgIdx < cMsg.xxPageShowToNum.getValueInt(); cMsgIdx++, sMsgIdx++) {
            EZDMsg.copy(sMsg.A.no(sMsgIdx), null, cMsg.A.no(cMsgIdx), null);
        }
        // END 2016/03/31 T.Tomita [QC#4587, ADD]
    }

    /**
     * get Main Data List
     * @param cMsg NSAL0460CMsg
     * @return Data List
     */
    private void getMainData(NSAL0460CMsg cMsg, NSAL0460SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0460Query.getInstance().getMainData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 5000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W, new String[] {Integer.toString(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            }

            // Copy one page from SMsg to CMsg
            int idx = 0;
            // START 2022/06/22 E.Sanchez [QC#59804, ADD]
            SVC_PHYS_MTR_READTMsgArray readTmsg = null;
            String sNull = null;
            BigDecimal bdNull = null;
            // END 2022/06/22 E.Sanchez [QC#59804, ADD]
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (sMsg.A.no(i).xxRowNum_D.getValueInt() == 1) {
                    if (NSAL0460CommonLogic.notProtectContrLine(sMsg.A.no(i))) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox, ZYPConstant.CHKBOX_ON_Y);
                    }
                }
                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                    idx++;
                    // START 2024/02/15 K.Watanabe [QC#63529,ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                    // END   2024/02/15 K.Watanabe [QC#63529,ADD]
                    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
                    readTmsg = getSvcPhysMtrRead(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrDtlPk.getValue(), sMsg.A.no(i).svcPhysMtrPk.getValue());
                    if (readTmsg != null && readTmsg.getValidCount() > 0 && CARRY_OVER_TP_CD_ON.equals(readTmsg.no(0).carryOverTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrReadDt, sNull);
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).readMtrCnt, bdNull);
                    }
                    // END 2022/06/22 E.Sanchez [QC#59804, ADD]
                }
                // START 2016/03/31 T.Tomita [QC#4587, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxReadOnlyFlg, ZYPConstant.FLG_OFF_N);
                // END 2016/03/31 T.Tomita [QC#4587, ADD]
            }
            cMsg.A.setValidCount(idx);

            // Set page#
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrRead(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        return (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]
}
