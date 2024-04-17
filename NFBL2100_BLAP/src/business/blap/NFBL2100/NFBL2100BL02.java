/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2100;

import static business.blap.NFBL2100.constant.NFBL2100Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2100.common.NFBL2100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#4998
 * 2016/10/12   Hitachi         K.Kojima        Update          QC#13088
 *</pre>
 */
public class NFBL2100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFBL2100CMsg cMsg = (NFBL2100CMsg) arg0;
        NFBL2100SMsg sMsg = (NFBL2100SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();

            if ("NFBL2100_INIT".equals(screenAplID)) {
                doProcess_NFBL2100_INIT((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFBL2100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_CMN_Clear((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFBL2100Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_CMN_Reset((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFBL2100Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_PageJump((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg);
            } else if ("NFBL2100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_PageNext((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg);
            } else if ("NFBL2100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_PagePrev((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg);
            } else if ("NFBL2100Scrn00_Search_custNm".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_Search_custNm((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
            } else if ("NFBL2100Scrn00_Search".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_Search((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
            } else if ("NFBL2100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFBL2100Scrn00_TBLColumnSort((NFBL2100CMsg) cMsg, (NFBL2100SMsg) sMsg, globalCompanyCode);
            }
        } finally {
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFBL2100_INIT(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {
        String cpoOrdNum = cMsg.cpoOrdNum.getValue();
        String cpoOrdNumH = cMsg.cpoOrdNum_H.getValue();

        cMsg.clear();
        sMsg.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);

        cMsg.apDsWfStsCd_CD.clear();
        cMsg.apDsWfStsDescTxt_SC.clear();

        setValue(cMsg.cpoOrdNum, cpoOrdNum);
        setValue(cMsg.cpoOrdNum_H, cpoOrdNumH);

        setValue(cMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(globalCompanyCode));
        ZYPCodeDataUtil.createPulldownList(AP_DS_WF_STS.class, cMsg.apDsWfStsCd_CD, cMsg.apDsWfStsDescTxt_SC);

        if (NFBL2100CommonLogic.hasSearchCondition(cMsg)) {
            doProcess_NFBL2100Scrn00_Search(cMsg, sMsg, globalCompanyCode);
        }
    }

    private void doProcess_NFBL2100Scrn00_CMN_Clear(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {
        String cpoOrdNumH = cMsg.cpoOrdNum_H.getValue();
        cMsg.A.clear();
        sMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.clear();
        setValue(cMsg.cpoOrdNum_H, cpoOrdNumH);
        doProcess_NFBL2100_INIT(cMsg, sMsg, globalCompanyCode);
    }

    private void doProcess_NFBL2100Scrn00_CMN_Reset(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {
        String cpoOrdNumH = cMsg.cpoOrdNum_H.getValue();
        cMsg.A.clear();
        sMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.clear();
        setValue(cMsg.cpoOrdNum, cpoOrdNumH);
        setValue(cMsg.cpoOrdNum_H, cpoOrdNumH);
        doProcess_NFBL2100_INIT(cMsg, sMsg, globalCompanyCode);
    }

    private void doProcess_NFBL2100Scrn00_PageJump(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        NFBL2100CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFBL2100Scrn00_PageNext(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFBL2100CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFBL2100Scrn00_PagePrev(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFBL2100CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFBL2100Scrn00_Search_custNm(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {
        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            String locNm = NFBL2100Query.getInstance().getLocNm(globalCompanyCode, cMsg.vndCd.getValue());
            if (locNm == null) {
                cMsg.vndCd.setErrorInfo(1, NFBM0133E, new String[] {cMsg.vndCd.getValue() });
            } else {
                // START 2016/10/12 K.Kojima [QC#13088,MOD]
                // setValue(cMsg.locNm, locNm);
                setValue(cMsg.dplyVndNm, locNm);
                // END 2016/10/12 K.Kojima [QC#13088,MOD]
            }
        } else {
            // START 2016/10/12 K.Kojima [QC#13088,MOD]
            // cMsg.locNm.clear();
            cMsg.dplyVndNm.clear();
            // END 2016/10/12 K.Kojima [QC#13088,MOD]
        }
    }

    private void doProcess_NFBL2100Scrn00_Search(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {

        if (NFBL2100CommonLogic.hasSearchCondition(cMsg)) {
            // START 2016/03/11 K.Kojima [QC#4998,ADD]
            if (ZYPCommonFunc.hasValue(cMsg.xxFromDt) && ZYPCommonFunc.hasValue(cMsg.xxToDt)) {
                if (ZYPDateUtil.compare(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue()) > 0) {
                    cMsg.xxFromDt.setErrorInfo(1, NFBM0215E);
                    cMsg.xxToDt.setErrorInfo(1, NFBM0215E);
                    return;
                }
            }
            // END 2016/03/11 K.Kojima [QC#4998,ADD]
            NFBL2100CommonLogic.getSearchData(cMsg, sMsg);

            NFBL2100CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            cMsg.setMessageInfo(NFBM0212E);
            cMsg.apDsWfStsCd_SV.setErrorInfo(1, NFBM0212E);
            cMsg.vndCd.setErrorInfo(1, NFBM0212E);
            cMsg.xxFromDt.setErrorInfo(1, NFBM0212E);
            cMsg.xxToDt.setErrorInfo(1, NFBM0212E);
            cMsg.cpoOrdNum.setErrorInfo(1, NFBM0212E);
        }

    }

    private void doProcess_NFBL2100Scrn00_TBLColumnSort(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, String globalCompanyCode) {
        NFBL2100CommonLogic.clearPageNum(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        NFBL2100CommonLogic.setPageNum(cMsg, 1, cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

}
