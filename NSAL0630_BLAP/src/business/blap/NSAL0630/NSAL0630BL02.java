/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0630;

import static business.blap.NSAL0630.constant.NSAL0630Constant.NSAM0065E;
import static business.blap.NSAL0630.constant.NSAL0630Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0630.common.NSAL0630CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
// import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/11/04   Hitachi         T.Tomita        Update          QC#4210
 * 2017/02/10   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#20817
 * 2017/08/31   Hitachi         K.Kim           Update          QC#20578
 *</pre>
 */
public class NSAL0630BL02 extends S21BusinessHandler {

    // START 2016/11/04 T.Tomita [QC#4210, MOD]
    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0630CMsg cMsg = (NSAL0630CMsg) arg0;
        NSAL0630SMsg sMsg = (NSAL0630SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0630_INIT".equals(screenAplID)) {
                doProcess_NSAL0630_INIT(cMsg, sMsg);
            // START 2017/02/10 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0630Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0630Scrn00_CMN_Reset(cMsg, sMsg);
            // END   2017/02/10 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0630Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0630Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0630Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0630Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0630Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0630Scrn00_PageNext(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0630_INIT(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/02/10 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0630Scrn00_CMN_Reset(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
    // END 2017/02/10 K.Ochiai [QC#16331, MOD]
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL0630Scrn00_CMN_Submit(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        // START 2017/08/31 K.Kojima [QC#20817,MOD]
        // init(cMsg, sMsg);
        int pageFrom = 0;
        if (cMsg.xxPageShowFromNum.getValueInt() > 0) {
            pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        }
        NSAL0630CommonLogic.pagenation(cMsg, sMsg, pageFrom);
        // END 2017/08/31 K.Kojima [QC#20817,MOD]
    }

    private void init(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt.clear();
        cMsg.xxChkBox_H.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0630CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A, NSAL0630CommonLogic.getRtnMsg(NSAM0065E));
            }
        }
        NSAL0630CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void findContrInfo(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
        }
        queryMap.put("dsContrPkList", dsContrPkList);
        queryMap.put("dsContrCatgCdIsWty", DS_CONTR_CATG.WARRANTY);
        // START 2017/08/31 K.Kim [QC#20578, MOD]
        // queryMap.put("dsContrStsCdIsExpired", DS_CONTR_STS.EXPIRED);
        // queryMap.put("dsContrStsCdIsCancelled", DS_CONTR_STS.CANCELLED);
        // queryMap.put("dsContrStsCdIsTerminated", DS_CONTR_STS.TERMINATED);
        queryMap.put("dsContrStsCdIsActive", DS_CONTR_CTRL_STS.ACTIVE);
        queryMap.put("dsContrStsCdIsSigned", DS_CONTR_CTRL_STS.SINGED);
        // END 2017/08/31 K.Kim [QC#20578, MOD]
        queryMap.put("limitCnt", sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NSAL0630Query.getInstance().getContrInfo(queryMap, sMsg.A);
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        return;
    }

    private void doProcess_NSAL0630Scrn00_PagePrev(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        NSAL0630CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0630CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NSAL0630Scrn00_PageNext(NSAL0630CMsg cMsg, NSAL0630SMsg sMsg) {
        NSAL0630CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        int pageFrom = cMsg.xxPageShowToNum.getValueInt();
        NSAL0630CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }
    // END 2016/11/04 T.Tomita [QC#4210, MOD]
}
