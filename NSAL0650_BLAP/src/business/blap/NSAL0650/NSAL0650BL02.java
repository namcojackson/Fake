/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0650;

import static business.blap.NSAL0650.constant.NSAL0650Constant.MTR_EST_TP_TBL;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0013E;
import static business.blap.NSAL0650.constant.NSAL0650Constant.NSAM0065E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0650.common.NSAL0650CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2015/12/14   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/12/02   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2018/07/02   Fujitsu         T.Ogura         Update          QC#26786
 *</pre>
 */
public class NSAL0650BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0650CMsg cMsg = (NSAL0650CMsg) arg0;
        // mod start 2016/12/02 CSA QC#4210
        NSAL0650SMsg sMsg = (NSAL0650SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0650_INIT".equals(screenAplID)) {
                doProcess_NSAL0650_INIT(cMsg, sMsg);
            // START 2017/02/13 K.Ochiai [QC#16331, MOD] 
            } else if ("NSAL0650Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0650_CMN_Reset(cMsg, sMsg);
            // END   2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0650Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0650_ApplyToAll(cMsg, sMsg);
            } else if ("NSAL0650Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0650Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0650Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0650Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0650_INIT(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/02/13 K.Ochiai [QC#16331, MOD] 
    private void doProcess_NSAL0650_CMN_Reset(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        init(cMsg, sMsg);
    }
    // END   2017/02/13 K.Ochiai [QC#16331, MOD] 

    private void doProcess_NSAL0650_ApplyToAll(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        NSAL0650CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        if (!NSAL0650CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mtrEstTpCd_AH, cMsg.mtrEstTpCd_H);
            }
        }
        NSAL0650CommonLogic.copyThisPageToCMsg(cMsg, sMsg);
    }

    private void init(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {
        cMsg.mtrEstTpCd_H.clear();
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt_H.clear();
        cMsg.xxChkBox_AL.clear();
        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0650CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPCodeDataUtil.createPulldownList(MTR_EST_TP_TBL, cMsg.A.no(i).mtrEstTpCd_AL, cMsg.A.no(i).mtrEstTpNm_AL);
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsMsgTxt_A1, NSAL0650CommonLogic.getRtnMsg(NSAM0065E));
            }
        }
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void findContrInfo(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryMap.put("dsContrCatgCdIsWty", DS_CONTR_CATG.WARRANTY);
        // START 2018/07/02 T.Ogura [QC#26786,MOD]
//        queryMap.put("dsContrStsCdIsExpired", DS_CONTR_STS.EXPIRED);
//        queryMap.put("dsContrStsCdIsCancelled", DS_CONTR_STS.CANCELLED);
//        queryMap.put("dsContrStsCdIsTerminated", DS_CONTR_STS.TERMINATED);
//        queryMap.put("dsContrStsCdIsApproved", DS_CONTR_STS.APPROVED);
//        queryMap.put("dsContrStsCdIsEffectived", DS_CONTR_STS.EFFECTIVE);
        queryMap.put("dsContrCtrlStsCdIsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
        queryMap.put("dsContrCtrlStsCdIsExpired", DS_CONTR_CTRL_STS.EXPIRED);
        queryMap.put("dsContrCtrlStsCdIsExpiredHold", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        queryMap.put("dsContrCtrlStsCdIsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
        queryMap.put("dsContrCtrlStsCdIsTerminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // END   2018/07/02 T.Ogura [QC#26786,MOD]
        queryMap.put("slsDt", ZYPDateUtil.getSalesDate());
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
        }
        queryMap.put("dsContrPkList", dsContrPkList);
        queryMap.put("limitCnt", sMsg.A.length() + 1);

        S21SsmEZDResult ssmResult = NSAL0650Query.getInstance().getContrInfo(queryMap, sMsg.A);
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A1, BigDecimal.valueOf(i));
        }
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        return;
    }

    private void doProcess_NSAL0650Scrn00_PageNext(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {

        NSAL0650CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0650_ACMsgArray acMsgArray = cMsg.A;
//        BigDecimal dsContrPre = cMsg.A.no(cMsg.A.getValidCount()-1).dsContrPk_A1.getValue();
        ZYPTableUtil.clear(acMsgArray);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
                ZYPCodeDataUtil.createPulldownList(MTR_EST_TP_TBL, cMsg.A.no(i - pageTo).mtrEstTpCd_AL, cMsg.A.no(i - pageTo).mtrEstTpNm_AL);
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i - pageTo).xxDplyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pageTo).dsMsgTxt_A1, NSAL0650CommonLogic.getRtnMsg(NSAM0065E));
                }
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0650Scrn00_PagePrev(NSAL0650CMsg cMsg, NSAL0650SMsg sMsg) {

        NSAL0650CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0650_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
                ZYPCodeDataUtil.createPulldownList(MTR_EST_TP_TBL, cMsg.A.no(i - pageFrom).mtrEstTpCd_AL, cMsg.A.no(i - pageFrom).mtrEstTpNm_AL);
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i - pageFrom).xxDplyCtrlFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pageFrom).dsMsgTxt_A1, NSAL0650CommonLogic.getRtnMsg(NSAM0065E));
                }
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/12/01 CSA QC#4210
}
