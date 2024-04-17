/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0710;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0710.constant.NSAL0710Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0710.common.NSAL0710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/11/18   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 *</pre>
 */
public class NSAL0710BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0710CMsg cMsg = (NSAL0710CMsg) arg0;
        // mod start 2016/11/18 CSA QC#4210
        NSAL0710SMsg sMsg = (NSAL0710SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0710_INIT".equals(screenAplID)) {
                doProcess_NSAL0710_INIT(cMsg, sMsg);
            // START 2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0710Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0710_CMN_Reset(cMsg, sMsg);
            // END   2017/02/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0710Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0710Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0710Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0710Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
            // mod end 2016/11/08 CSA QC#4210
        }
    }

    private void doProcess_NSAL0710_INIT(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/02/13 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0710_CMN_Reset(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {
    // END   2017/02/13 K.Ochiai [QC#16331, MOD]
        init(cMsg, sMsg);
    }

    private void init(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.mtrReadMethCd_H3.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.xxChkBox_AL.clear();
        cMsg.xxChkBox_BL.clear();

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0710CommonLogic.createPullDown(cMsg);
        // mod start 2016/11/11 CSA QC#4210
        findContrInfo(cMsg, sMsg);
        setMtrReadMethFlg(cMsg);
        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        setValue(cMsg.dsContrPk_PR, BigDecimal.ZERO);
        // mod end 2016/11/11 CSA QC#4210
    }

    private void findContrInfo(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add(cMsg.P.no(i).dsContrPk_P1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }
        queryMap.put("dsContrPkArray", dsContrPkList);
        queryMap.put("dsContrDtlPkArray", dsContrDtlPkList);
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        S21SsmEZDResult ssmResult = NSAL0710Query.getInstance().getContrInfo(queryMap, sMsg.A);
        // mod start 2016/11/18 CSA QC#4210
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i));
        }
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        // mod end 2016/11/18 CSA QC#4210
    }

    private void setMtrReadMethFlg(NSAL0710CMsg cMsg) {

        int cnt = cMsg.A.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NSAL0710_ACMsg aCMsg = cMsg.A.no(i);
            setValue(aCMsg.xxDplyCtrlFlg_A1, FLG_OFF_N);
            setValue(aCMsg.xxDplyCtrlFlg_A2, FLG_OFF_N);
            setValue(aCMsg.xxDplyCtrlFlg_A3, FLG_OFF_N);
            setValue(aCMsg.xxDplyCtrlFlg_A4, FLG_OFF_N);
            setValue(aCMsg.xxDplyCtrlFlg_A5, FLG_OFF_N);
            if (MTR_READ_METH.PHONE.equals(aCMsg.mtrReadMethCd_A1.getValue())) {
                setValue(aCMsg.xxDplyCtrlFlg_A1, CHKBOX_ON_Y);
            } else if (MTR_READ_METH.EMANAGE.equals(aCMsg.mtrReadMethCd_A1.getValue())) {
                setValue(aCMsg.xxDplyCtrlFlg_A2, CHKBOX_ON_Y);
            } else if (MTR_READ_METH.EMAIL.equals(aCMsg.mtrReadMethCd_A1.getValue())) {
                setValue(aCMsg.xxDplyCtrlFlg_A3, CHKBOX_ON_Y);
            } else if (MTR_READ_METH.FAX.equals(aCMsg.mtrReadMethCd_A1.getValue())) {
                setValue(aCMsg.xxDplyCtrlFlg_A4, CHKBOX_ON_Y);
            } else if (MTR_READ_METH.IMAGEWARE.equals(aCMsg.mtrReadMethCd_A1.getValue())) {
                setValue(aCMsg.xxDplyCtrlFlg_A5, CHKBOX_ON_Y);
            }
        }
    }
    // mod start 2016/11/11 CSA QC#4210
    private void doProcess_NSAL0710Scrn00_PageNext(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        NSAL0710CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0710_ACMsgArray acMsgArray = cMsg.A;
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
        setMtrReadMethFlg(cMsg);
        setValue(cMsg.dsContrPk_PR, dsContrPre);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    private void doProcess_NSAL0710Scrn00_PagePrev(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        NSAL0710CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0710_ACMsgArray acMsgArray = cMsg.A;
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
        setMtrReadMethFlg(cMsg);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/11/11 CSA QC#4210
}
