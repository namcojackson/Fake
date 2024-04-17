/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0670;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0670.constant.NSAL0670Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0670.common.NSAL0670CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#995
 * 2016/02/22   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/01/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 *</pre>
 */
public class NSAL0670BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0670CMsg cMsg = (NSAL0670CMsg) arg0;
        // mod start 2016/11/28 CSA QC#4210
        NSAL0670SMsg sMsg = (NSAL0670SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0670_INIT".equals(screenAplID)) {
                doProcess_NSAL0670_INIT(cMsg, sMsg);
            // START 2017/01/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0670Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0670_CMN_Reset(cMsg, sMsg);
            // END 2017/01/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0670Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0670Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0670Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0670Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0670_INIT(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/01/13 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0670_CMN_Reset(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {
        init(cMsg, sMsg);
    }
    // END 2017/01/13 K.Ochiai [QC#16331, MOD]

    private void init(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.xxChkBox_AL.clear();

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0670CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        setValue(cMsg.dsContrPk_PR, BigDecimal.ZERO);
    }

    private void findContrInfo(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        // START 2016/02/22 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add(cMsg.P.no(i).dsContrPk_P1.getValue());
            if (hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }
        queryMap.put("dsContrPkArray", dsContrPkList);
        queryMap.put("dsContrDtlPkArray", dsContrDtlPkList);
        // END 2016/02/22 T.Aoyagi [QC3694, MOD]
        S21SsmEZDResult ssmResult = NSAL0670Query.getInstance().getContrInfo(queryMap, sMsg.A);
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
    // mod end 2016/11/28 CSA QC#4210
    // mod start 2016/11/28 CSA QC#4210
    private void doProcess_NSAL0670Scrn00_PageNext(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        NSAL0670CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0670_ACMsgArray acMsgArray = cMsg.A;
        BigDecimal dsContrPre = cMsg.A.no(cMsg.A.getValidCount() - 1).dsContrPk_A1.getValue();
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
        setValue(cMsg.dsContrPk_PR, dsContrPre);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0670Scrn00_PagePrev(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        NSAL0670CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0670_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        if (i > 0) {
            setValue(cMsg.dsContrPk_PR, sMsg.A.no(i - 1).dsContrPk_A1.getValue());
        } else {
            setValue(cMsg.dsContrPk_PR, BigDecimal.ZERO);
        }
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
