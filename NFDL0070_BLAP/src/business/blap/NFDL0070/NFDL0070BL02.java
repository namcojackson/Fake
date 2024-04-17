/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0070;

import static business.blap.NFDL0070.constant.NFDL0070Constant.*;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NFDL0070.common.NFDL0070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * NFDL0070BL02.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2016/04/27   Fujitsu         C.Naito         Update          QC#6409
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 * 2022/05/18   CITS            D.Mamaril       Update          QC#59333
 * </pre>
 */
public class NFDL0070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0070_INIT".equals(screenAplID)) {
                doProcess_NFDL0070_INIT(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_PageNext(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_PageJump(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_TBLColumnSort(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_Continue".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_Continue(cMsg, sMsg);

            } else if ("NFDL0070_NFDL0080".equals(screenAplID)) {
                doProcess_NFDL0070_NFDL0080(cMsg, sMsg);

            } else if ("NFDL0070Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0070Scrn00_CMN_Download(cMsg, sMsg);

            // START 2018/07/06 [QC#26989, ADD]
            } else if ("NFDL0070Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFDL0070_INIT(cMsg, sMsg);
            // END   2018/07/06 [QC#26989, ADD]

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFDL0070Scrn00_Continue(EZDCMsg cMsg, EZDSMsg sMsg) {

        // START 2016/11/11 J.Kim [QC#15756,ADD]
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        int index = bizMsg.xxRadioBtn.getValueInt();
        String arTrxNum = bizMsg.A.no(index).arTrxNum_A1.getValue();
        BigDecimal resultMap = (BigDecimal) NFDL0070Query.getInstance().getCreditCardRefundArRfRqstInfo(bizMsg, arTrxNum).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            bizMsg.setMessageInfo(NFDM0041E);
            // START 2022/05/18 D.Mamaril [QC#59333,ADD]
            return;
            // END 2022/05/18 D.Mamaril [QC#59333,ADD]
        }
        // END 2016/11/11 J.Kim [QC#15756,ADD]

        // START 2022/04/22 K.Suzuki [QC#59333,ADD]
        resultMap = (BigDecimal) NFDL0070Query.getInstance().getCountNotProcessRefundRqst(bizMsg, arTrxNum).getResultObject();
        if (ZYPCommonFunc.hasValue(resultMap) && BigDecimal.ZERO.compareTo(resultMap) < 0) {
            bizMsg.setMessageInfo(NFDM0053E);
        }
        // END   2022/04/22 K.Suzuki [QC#59333,ADD]
    }

    private void doProcess_NFDL0070_NFDL0080(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;
        globalMsg.clear();
        globalMsg.A.setValidCount(0);
        //[QC#6409] INSERT start
        bizMsg.xxRadioBtn.clear();
        //[QC#6409] INSERT end

        getDetailData(bizMsg, globalMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0070_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0070_INIT================================START", this);

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;
        globalMsg.clear();
        globalMsg.A.setValidCount(0);
        // START 2018/07/06 [QC#26989, ADD]
        bizMsg.xxRadioBtn.clear();
        // END   2018/07/06 [QC#26989, ADD]

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        NFDL0070CommonLogic.getAcctNm(bizMsg);

        getDetailData(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0070_INIT================================END", this);
    }

    private void getDetailData(NFDL0070CMsg bizMsg, NFDL0070SMsg globalMsg) {
        globalMsg.clear();
        globalMsg.A.setValidCount(0);

        Map<String, Object> ssmParam = NFDL0070CommonLogic.setSsmMap(bizMsg, globalMsg);

        // Search Records
        S21SsmEZDResult ssmResult = NFDL0070Query.getInstance().findApplicationInfoList(ssmParam, globalMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            int i = 0;
            for (; i < globalMsg.A.getValidCount(); i++) {

                if (i == bizMsg.A.length()) {
                    break;
                }

                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }

            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

            if (MAX_SEARCH_CNT == globalMsg.A.getValidCount()) {
                // Records count
                queryResCnt = globalMsg.A.no(0).xxTotCnt.getValueInt();
                if (queryResCnt > globalMsg.A.length()) {
                    bizMsg.setMessageInfo(MSG_ID.NZZM0001W.toString());
                }
            }

        } else {
            bizMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0070Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PageNext================================START", this);

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();

        int i = (pagenationFrom - bizMsg.A.length());
        int count = 0;
        for (; i < pagenationFrom; i++) {
            EZDMsg.copy(bizMsg.A.no(count), null, globalMsg.A.no(i), null);
            count++;
        }

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxRadioBtn.clear();

        int j = pagenationFrom;
        for (; j < pagenationFrom + bizMsg.A.length(); j++) {
            if (j < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(j), null, bizMsg.A.no(j - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(j - pagenationFrom);
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0070Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PagePrev================================START", this);

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int pagenationTo = bizMsg.xxPageShowToNum.getValueInt();

        int i = (pagenationFrom + bizMsg.A.length());
        int count = 0;
        for (; i < pagenationTo; i++) {
            EZDMsg.copy(bizMsg.A.no(count), null, globalMsg.A.no(i), null);
            count++;
        }

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.xxRadioBtn.clear();

        // copy data from SMsg onto CMsg
        int j = pagenationFrom;
        for (; j < pagenationFrom + bizMsg.A.length(); j++) {
            if (j < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(j), null, bizMsg.A.no(j - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(j - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0070Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PageJump================================START", this);
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;

        bizMsg.xxRadioBtn.clear();

        NFDL0070CommonLogic.setPageJump(globalMsg, bizMsg);

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_PageJump================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0070Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_TBLColumnSort================================START", this);

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;

        int cnt = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(cnt + i), null);
        }

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            if (bizMsg.A.getValidCount() > 0) {

                // Copy from SMsg to CMsg
                int i = 0;
                for (; i < globalMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                bizMsg.A.setValidCount(i);

                // Page break setting
                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            }
        }

        EZDDebugOutput.println(1, "doProcess_NFDL0070Scrn00_TBLColumnSort================================END", this);
    }

    private void doProcess_NFDL0070Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;
        NFDL0070SMsg globalMsg = (NFDL0070SMsg) sMsg;
        NFDL0070Query.getInstance().createDetailCSV(bizMsg, NFDL0070CommonLogic.setSsmMap(bizMsg, globalMsg));
    }
}
