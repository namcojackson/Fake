/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 11/25/2009   Fujitsu         FAP)D.Kato      Update          DefID 2024
 * 02/04/2010   Fujitsu         FAP)D.Kato      Update            
 * 02/22/2010   Fujitsu         FAP)N.Aoyama    Update          NFCM0139E Err Condition Update
 * 09/03/2010   Fujitsu         FAP)D.Kato      Update          DefID 4623
 * 05/14/2010   Fujitsu         FAP)N.Aoyama    Update          DefID:6321
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 09/07/2010   SRA             D.Yoshida       Update          DefID:8124
 * 10/13/2010   Fujitsu         T.Tanaka        Update          Merge R2->R3
 * 10/28/2010   Fujitsu         I.Kondo         Update          DefID:8289 No.448
 * 10/29/2010   Fujitsu         I.Kondo         Update          R2 -> R3 Merge.
 * 11/01/2010   Fujitsu         I.Kondo         Update          R2 -> R3 Merge.
 * 11/05/2010   Fujitsu         T.Tanaka        Update          M81
 * 04/11/2011   Fujitsu         K.Kimura        Update          DefID:2017
 * 06/02/2011   Canon           Y.Aikawa        Update          DefID:2271
 * 10/07/2015   Fujitsu         T.Tanaka        Update          for CSA NA
 * 03/31/2016   Fujitsu         T.Tanaka        Update          Def#6241
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53627
 * </pre>
 */
package business.blap.NFCL5050;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL5050.common.NFCL5050CommonLogic;
import business.blap.NFCL5050.constant.NFCL5050Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * NFCL5050BL02.
 */
public class NFCL5050BL02 extends S21BusinessHandler implements NFCL5050Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
            String screenAplID = cMsg.getScreenAplID();

            // NFCL5050_INIT
            if ("NFCL5050_INIT".equals(screenAplID)) {
                doProcess_NFCL5050_INIT(cMsg, sMsg);

                ZYPGUITableColumn.getColData(bizMsg, (NFCL5050SMsg) sMsg);


                // NFCL5050Scrn00_SearchInvoice
            } else if ("NFCL5050Scrn00_SearchInvoice".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_SearchInvoice(cMsg, sMsg);
                // NFCL5050Scrn00_CMN_Clear
            } else if ("NFCL5050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_CMN_Clear(cMsg, sMsg);
                // NFCL5050Scrn00_PageNext
            } else if ("NFCL5050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_PageNext(cMsg, sMsg);
                // NFCL5050Scrn00_PagePrev
            } else if ("NFCL5050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_PagePrev(cMsg, sMsg);
                // NFCL5050Scrn00_SelectInvoice
            } else if ("NFCL5050Scrn00_SelectInvoice".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_SelectInvoice(cMsg, sMsg);
                // NFCL5050Scrn00_CalcGrossAmount
            } else if ("NFCL5050Scrn00_CalcGrossAmount".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_CalcGrossAmount(cMsg, sMsg);
                // NFCL5050Scrn00_CalcGrossAmount
            } else if ("NFCL5050Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL5050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_TBLColumnSort(cMsg, sMsg);
            // START 2018/07/20 Y.Matsui [QC#26985,ADD]
            } else if ("NFCL5050Scrn00_Check_All".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_Check_All(cMsg, sMsg);
            } else if ("NFCL5050Scrn00_Un_Check_All".equals(screenAplID)) {
                doProcess_NFCL5050Scrn00_Un_Check_All(cMsg, sMsg);
            // END   2018/07/20 Y.Matsui [QC#26985,ADD]
            // START 2019/09/21 S.Takami [QC#53627,ADD]
            } else if ("NFCL5050Scrn00_CMN_ColSave".equals(screenAplID)) {
                // no operations
            } else if ("NFCL5050Scrn00_CMN_ColClear".equals(screenAplID)) {
                // no operations
            // END 2019/09/21 S.Takami [QC#53627,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050_INIT================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;
        globalMsg.clear();
        globalMsg.A.setValidCount(0);

        ArrayList<String> newAr = new ArrayList<String>();
        ArrayList<String> newAr2 = new ArrayList<String>();

        for (int i = 0; i < bizMsg.arTrxTpCd.length(); i++) {
            String listCD = bizMsg.arTrxTpCd.no(i).getValue();
            if (!ZYPCommonFunc.hasValue(bizMsg.arTrxTpCd.no(i))) {
                break;
            } else if (!"RCP".equals(listCD) && ZYPCommonFunc.hasValue(bizMsg.arTrxTpCd.no(i))) {
                newAr.add(listCD);
                newAr2.add(bizMsg.xxArTrxTpTxt.no(i).getValue());
            }
        }

        bizMsg.arTrxTpCd.clear();
        bizMsg.xxArTrxTpTxt.clear();
        for (int n = 0; n < newAr.size(); n++) {
            bizMsg.arTrxTpCd.no(n).setValue(newAr.get(n));
            bizMsg.xxArTrxTpTxt.no(n).setValue(newAr2.get(n));
        }

        bizMsg.arTrxTpCd_P1.setValue("INV");

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("sellToCustCd01", bizMsg.payerCustCd.getValue());
        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(outMsg!=null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, outMsg.no(0).dsAcctNm.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.arCustRefNum.getValue())) {
            // START 2018/07/20 Y.Matsui [QC#26985,ADD]
            bizMsg.xxTrxNumSrchTxt.setValue(bizMsg.arCustRefNum.getValue());
            bizMsg.arCustRefNum.clear();
            // END   2018/07/20 Y.Matsui [QC#26985,ADD]

            // Def#6241 Delete Initial Search
            doProcess_NFCL5050Scrn00_SearchInvoice(bizMsg,globalMsg);
        }
        
        EZDDebugOutput.println(1, "doProcess_NFCL5050_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_SearchInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_SearchInvoice================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;
        globalMsg.clear();
        globalMsg.A.setValidCount(0);

        bizMsg.dealOrigGrsAmt_H2.setValue(0);
        bizMsg.arCashApplyStsCd.setValue("A");

        // START 2018/07/20 Y.Matsui [QC#26985,DEL]
//        if ( bizMsg.arCustRefNum.getValue().contains("%") ) {
//            bizMsg.xxChkBox_S1.setValue("Y");
//        } else {
//            bizMsg.xxChkBox_S1.setValue("N");
//        }
        // END   2018/07/20 Y.Matsui [QC#26985,DEL]

        Map<String, Object> ssmParam = NFCL5050CommonLogic.setSsmMap(bizMsg, globalMsg);

        // Search Records
        S21SsmEZDResult ssmResult = NFCL5050Query.getInstance().findApplicationInfoList(ssmParam, globalMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (SUMMARY_STATUS_Y.equals(bizMsg.xxRsltStsCd.getValue())) {
                    globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }

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
                    String queryResCntStr = Integer.toString(queryResCnt);
                    bizMsg.setMessageInfo("NFCM0184W", new String[] {queryResCntStr });
                }
            }


        } else {
            bizMsg.setMessageInfo("NZZM0000E", null);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_SearchInvoice================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_CMN_Clear================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

        doProcess_NFCL5050_INIT(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_CMN_Clear================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PageNext================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();

        int i = (pagenationFrom - bizMsg.A.length());
        int count = 0;
        for (; i < pagenationFrom; i++) {
            EZDMsg.copy(bizMsg.A.no(count), null, globalMsg.A.no(i), null);
            count++;
        }

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

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

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PagePrev================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

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

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_SelectInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_SelectInvoice================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

        if (bizMsg.xxPageShowFromNum.getValueInt() != 0) {
            int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            int k = pagenationFrom + bizMsg.A.length();
            for (int i = pagenationFrom; i < k; i++) {
                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
                } else {
                    break;
                }
            }
        } else {
            EZDDebugOutput.printLog(1, "There is no processing.", null);
        }

        List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (outGetSelectedRows.isEmpty()) {
            bizMsg.setMessageInfo("NFCM0094E", null);
            return;
        }

        if (outGetSelectedRows.size() > CHECKBOX_0430SEARCH_MAX) {
            if (MOD_0430SEARCH.equals(bizMsg.xxModeInd.getValue())) {
                bizMsg.setMessageInfo("NFCM0142E", null);
                return;
            }
        }
        bizMsg.C.clear();
        for (int i = 0; i < outGetSelectedRows.size(); i++) {
            int selectIndex = outGetSelectedRows.get(i);
            NFCL5050CommonLogic.isDepositDateAndTrxDate(bizMsg, globalMsg, selectIndex);
            bizMsg.C.no(i).arTrxNum_C1.setValue(globalMsg.A.no(selectIndex).arTrxNum_A1.getValue());
            bizMsg.C.no(i).arTrxTpCd_C1.setValue(globalMsg.A.no(selectIndex).arTrxTpCd_A1.getValue());
            bizMsg.C.no(i).arTrxBalPk_C1.setValue(globalMsg.A.no(selectIndex).arTrxBalPk.getValue());

        }
        if ("E".equals(bizMsg.getMessageKind())) {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (globalMsg.A.no(i).xxChkBox_A1.isError()) {
                    NFCL5050CommonLogic.setPageData(globalMsg, bizMsg, i + 1);
                    break;
                }
            }
            return;
        }

        bizMsg.C.setValidCount(outGetSelectedRows.size());

        EZDMsg.copy(globalMsg.A, null, bizMsg.A, null);
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_SelectInvoice================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_CalcGrossAmount(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_CalcGrossAmount================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

        NFCL5050CommonLogic.setPage(globalMsg, bizMsg);

        BigDecimal balance = BigDecimal.ZERO;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (globalMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                balance = balance.add(globalMsg.A.no(i).dealRmngBalGrsAmt.getValue());
            }
        }
        bizMsg.xxBalApplyGrsAmt.setValue(balance);

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_CalcGrossAmount================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PageJump================================START", this);
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

        NFCL5050CommonLogic.setPageJump(globalMsg, bizMsg);

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
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_PageJump================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL5050Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_TBLColumnSort================================START", this);

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;

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

        EZDDebugOutput.println(1, "doProcess_NFCL5050Scrn00_TBLColumnSort================================END", this);
    }

    // START 2018/07/20 Y.Matsui [QC#26985,ADD]
    private void doProcess_NFCL5050Scrn00_Check_All(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;
        NFCL5050CommonLogic.selectAll(globalMsg, bizMsg, ZYPConstant.FLG_ON_Y);
    }

    private void doProcess_NFCL5050Scrn00_Un_Check_All(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050SMsg globalMsg = (NFCL5050SMsg) sMsg;
        NFCL5050CommonLogic.selectAll(globalMsg, bizMsg, ZYPConstant.FLG_OFF_N);
    }
    // END   2018/07/20 Y.Matsui [QC#26985,ADD]
}
