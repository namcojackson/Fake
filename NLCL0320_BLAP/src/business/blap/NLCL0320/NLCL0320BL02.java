/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0320;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0320.common.NLCL0320CommonLogic;
import business.blap.NLCL0320.constant.NLCL0320Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320BL02 extends S21BusinessHandler implements NLCL0320Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
 
            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0320Scrn00_CMN_ColSave".equals(screenAplID)) {

            } else if ("NLCL0320Scrn00_CMN_ColClear".equals(screenAplID)) {

            } else if ("NLCL0320_INIT".equals(screenAplID)) {
                doProcess_NLCL0320_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_CMN_Reset((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NLCL0320Scrn00_Click_Btn_DeleteRow".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_Click_Btn_DeleteRow((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_Click_Btn_InsertRow".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_Click_Btn_InsertRow((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_PageNext((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_PagePrev((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_Search".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_Search((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_Click_Btn_RetailWh".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_Click_Btn_RetailWh((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320Scrn00_Click_Btn_Account".equals(screenAplID)) {
                doProcess_NLCL0320Scrn00_Click_Btn_Account((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else if ("NLCL0320_NWAL1130".equals(screenAplID)) {
                doProcess_NLCL0320_NWAL1130((NLCL0320CMsg) cMsg, (NLCL0320SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0320_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320_INIT START ----- ", this);

        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;
        NLCL0320SMsg globalMsg = (NLCL0320SMsg) sMsg;
        bizMsg.clear();
        globalMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        bizMsg.xxChkBox_H.setValue(ZYPConstant.CHKBOX_ON_Y);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320_INIT END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Reset START ----- ", this);
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;
        NLCL0320SMsg globalMsg = (NLCL0320SMsg) sMsg;
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        doProcess_NLCL0320_INIT(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, xxComnColOrdTxt);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Reset END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Submit START ----- ", this);

        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;
        NLCL0320SMsg globalMsg = (NLCL0320SMsg) sMsg;

        doProcess_NLCL0320Scrn00_Search(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_CMN_Submit END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_Click_Btn_DeleteRow(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Click_Btn_DeleteRow START ----- ", this);
        int procCnt = 0;
        NLCL0320CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A.getValue())) {
                procCnt++;
            }
        }
        if (procCnt == 0) {
            bizMsg.setMessageInfo(MESSAGE_ID.NFAM0075E.toString());
            return;
        }

        NLCL0320SMsg tmpMsg = new NLCL0320SMsg();
        EZDMsg.copy(globalMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(globalMsg.A);

        int j = 0;
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A.getValue())) {
                EZDMsg.copy(tmpMsg.A.no(i), null, globalMsg.A.no(j), null);
                j++;
            }
        }
        globalMsg.A.setValidCount(j);
        BigDecimal lastPageFromNum = NLCL0320CommonLogic.getLastPageFromNum(bizMsg, globalMsg);
        if (bizMsg.xxPageShowFromNum_A.getValue().compareTo(lastPageFromNum) < 0) {
            NLCL0320CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
        } else {
            NLCL0320CommonLogic.lastPage(bizMsg, globalMsg);
        }
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Click_Btn_DeleteRow END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_Click_Btn_InsertRow(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Click_Btn_InsertRow START ----- ", this);

        NLCL0320CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        int no = globalMsg.A.getValidCount();

        if (no == 0) {
            if (!doProcess_NLCL0320Scrn00_Click_Btn_RetailWh(bizMsg, globalMsg)) {
                return;
            }

        }

        if (no == globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(globalMsg.A.length()) });
            return;
        }

        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).rtlWhCd_A, bizMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).effFromDt_A, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).effThruDt_A, LAST_DATE);
        globalMsg.A.setValidCount(no + 1);
        NLCL0320CommonLogic.lastPage(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Click_Btn_InsertRow END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_PageNext(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_PageNext START ----- ", this);

        NLCL0320CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLCL0320CommonLogic.nextPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_PageNext END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_PagePrev(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_PagePrev START ----- ", this);

        NLCL0320CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLCL0320CommonLogic.prevPage(bizMsg, globalMsg);	

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_PagePrev END ----- ", this);
    }

    private void doProcess_NLCL0320Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Search START ----- ", this);

        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;
        NLCL0320SMsg globalMsg = (NLCL0320SMsg) sMsg;
        boolean errFlg = false;
        if (!ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H)) {
            bizMsg.rtlWhNm_H.clear();
        } else {
            if (!doProcess_NLCL0320Scrn00_Click_Btn_RetailWh(bizMsg, globalMsg)) {
                errFlg = true;
            }
        }
        if (errFlg) {
            return;
        }
        getAdjAcctAlias(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Search END ----- ", this);
    }

    private void getAdjAcctAlias(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- getAdjAcctAlias START ----- ", this);

        NLCL0320_ACMsgArray bizMsgArray = bizMsg.A;
        NLCL0320_ASMsgArray globalMsgArray = globalMsg.A;

        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);
        ZYPTableUtil.clear(globalMsg.B);
        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("rtlWhCd", bizMsg.rtlWhCd_H.getValue());
        prmMap.put("xxChkBox", bizMsg.xxChkBox_H.getValue());
        prmMap.put("slsDt", ZYPDateUtil.getSalesDate());
        prmMap.put("rowNum", String.valueOf(globalMsg.A.length()));

        S21SsmEZDResult ssmResult = NLCL0320Query.getInstance().getAdjAcctAlias(bizMsg, globalMsg, prmMap);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = globalMsgArray.length();

            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0098I.toString(), new String[] {Integer.toString(maxCnt) });
            }

            EZDMsg.copy(globalMsgArray, null, bizMsgArray, null);
            EZDMsg.copy(globalMsgArray, "A", globalMsg.B, "B");
            EZDMsg.copy(globalMsgArray, "A0", globalMsg.B, "B0");

            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            bizMsg.xxPageShowToNum_A.setValue(bizMsgArray.getValidCount());

        } else {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0007I.toString());
            return;
        }

        EZDDebugOutput.println(1, "----- getAdjAcctAlias END ----- ", this);
    }

    private boolean doProcess_NLCL0320Scrn00_Click_Btn_RetailWh(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_Click_Btn_RetailWh START ----- ", this);
        String rtlWhNm = NLCL0320CommonLogic.getRtlWhNm(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue());
        if (rtlWhNm == null) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, rtlWhNm);
        }
        return true;
    }

    private void doProcess_NLCL0320_NWAL1130(NLCL0320CMsg bizMsg, NLCL0320SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0320Scrn00_NLCL0320_NWAL1130 START ----- ", this);
        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        if ("Click_Link_RetailWh".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H)) {
                doProcess_NLCL0320Scrn00_Click_Btn_RetailWh(bizMsg, globalMsg);
            }
        }
    }

    private void doProcess_NLCL0320Scrn00_Click_Btn_Account(NLCL0320CMsg cMsg, NLCL0320SMsg sMsg) {
        NLCL0320CommonLogic.checkManualSegmentElement(cMsg, sMsg, getGlobalCompanyCode());
    }
}
