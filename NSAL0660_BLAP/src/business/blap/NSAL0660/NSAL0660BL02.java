/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0660;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0660.constant.NSAL0660Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0660.common.NSAL0660CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Add general Notes
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kasai         Create          N/A
 * 2016/12/06   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/24   Hitachi         K.Ochiai        Update          QC#4210
 *</pre>
 */
public class NSAL0660BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0660CMsg cMsg = (NSAL0660CMsg) arg0;
        // mod start 2016/12/06 CSA QC#4210
        NSAL0660SMsg sMsg = (NSAL0660SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0660_INIT".equals(screenAplID)) {
                doProcess_NSAL0660_INIT(cMsg, sMsg);
            } else if ("NSAL0660Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0660Scrn00_CMN_Submit(cMsg);
            // START 2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0660Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0660_CMN_Reset(cMsg, sMsg);
            // END   2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0660Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0660Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0660Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0660Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0660_INIT(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL0660Scrn00_CMN_Submit(NSAL0660CMsg cMsg) {
    }

    // START 2017/02/13 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0660_CMN_Reset(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {
        init(cMsg, sMsg);
    }
    // END 2017/02/13 K.Ochiai [QC#16331, MOD]

    private void init(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.xxChkBox_AL.clear();
        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0660CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void findContrInfo(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
        }
        queryMap.put("dsContrPkList", dsContrPkList);
        S21SsmEZDResult ssmResult = NSAL0660Query.getInstance().getContrInfo(queryMap, sMsg.A);
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

    private void doProcess_NSAL0660Scrn00_PageNext(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {

        NSAL0660CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0660_ACMsgArray acMsgArray = cMsg.A;
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

    private void doProcess_NSAL0660Scrn00_PagePrev(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {

        NSAL0660CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0660_ACMsgArray acMsgArray = cMsg.A;
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
    // mod end 2016/12/06 CSA QC#4210
}
