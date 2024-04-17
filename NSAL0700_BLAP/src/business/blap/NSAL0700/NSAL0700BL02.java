/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0700;

import static business.blap.NSAL0700.constant.NSAL0700Constant.LVL_NUM_10;
import static business.blap.NSAL0700.constant.NSAL0700Constant.LVL_NUM_20;
import static business.blap.NSAL0700.constant.NSAL0700Constant.LVL_NUM_30;
import static business.blap.NSAL0700.constant.NSAL0700Constant.MTR_LB_NM;
import static business.blap.NSAL0700.constant.NSAL0700Constant.NSAM0013E;
import static business.blap.NSAL0700.constant.NSAL0700Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0700.common.NSAL0700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2017/09/04   Hitachi         K.Kojima        Update          QC#20816
 *</pre>
 */
public class NSAL0700BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0700CMsg cMsg = (NSAL0700CMsg) arg0;
        // mod start 2016/11/28 CSA QC#4210
        NSAL0700SMsg sMsg = (NSAL0700SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0700_INIT".equals(screenAplID)) {
                doProcess_NSAL0700_INIT(cMsg, sMsg);
            // START 2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0700Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0700_CMN_Reset(cMsg, sMsg);
            // END   2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0700Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0700Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0700Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0700Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0700_INIT(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/02/13 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0700_CMN_Reset(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {
    // END   2017/02/13 K.Ochiai [QC#16331, MOD]
        init(cMsg, sMsg);
    }

    private void init(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.xxChkBox_H1.clear();
        cMsg.xxChkBox_H2.clear();
        cMsg.xxChkBox_H3.clear();
        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0700CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void findContrInfo(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {
    // mod end 2016/11/28 CSA QC#4210
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }
        queryMap.put("dsContrPkList", dsContrPkList);
        queryMap.put("dsContrDtlPkList", dsContrDtlPkList);
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        queryMap.put("dsContrCatgCdReg", DS_CONTR_CATG.REGULAR);
        queryMap.put("dsContrCatgCdFlt", DS_CONTR_CATG.FLEET);
        queryMap.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
        queryMap.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        queryMap.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        queryMap.put("lvlNum10", LVL_NUM_10);
        queryMap.put("lvlNum20", LVL_NUM_20);
        queryMap.put("lvlNum30", LVL_NUM_30);
        queryMap.put("mtrLbNm", MTR_LB_NM);
        // mod start 2016/11/28 CSA QC#4210
        // START 2017/09/04 K.Kojima [QC#20816,ADD]
        queryMap.put("slsDt", ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        // END 2017/09/04 K.Kojima [QC#20816,ADD]
        S21SsmEZDResult ssmResult = NSAL0700Query.getInstance().getContrInfo(queryMap, sMsg.A);
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        // START 2017/02/27 K.Ochiai [QC4210, MOD]
        if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        }
        // END 2017/02/27 K.Ochiai [QC4210, MOD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A1, BigDecimal.valueOf(i));
        }
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        // mod end 2016/11/28 CSA QC#4210
        return;
    }
    // mod start 2016/11/28 CSA QC#4210
    private void doProcess_NSAL0700Scrn00_PageNext(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {

        NSAL0700CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0700_ACMsgArray acMsgArray = cMsg.A;
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
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0700Scrn00_PagePrev(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {

        NSAL0700CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0700_ACMsgArray acMsgArray = cMsg.A;
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
    // mod end 2016/11/28 CSA QC#4210
}
