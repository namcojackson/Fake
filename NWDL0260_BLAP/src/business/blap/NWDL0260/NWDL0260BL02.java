/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 * 12/15/2010   Fujitsu         A.Suda          Update          951
 *</pre>
 */
package business.blap.NWDL0260;

import static business.blap.NWDL0260.common.NWDL0260CommonLogic.backupSearchCriteria;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.clearAllocationQty;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.clearAvailableQty;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.clearSearchCriteria;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.clearMemo;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.debugMethodEnd;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.debugMethodStart;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getInTrnstInvty;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getMdse;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getOnHandInvty;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.getQuery;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.isScreen00;
import static business.blap.NWDL0260.common.NWDL0260CommonLogic.toBigDecimal;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWDL0260.constant.NWDL0260Constant;
import business.db.INVTYTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class NWDL0260BL02 extends S21BusinessHandler implements NWDL0260Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        NWDL0260CMsg myCMsg = (NWDL0260CMsg) cMsg;
        NWDL0260SMsg mySMsg = (NWDL0260SMsg) sMsg;
        myCMsg.setCommitSMsg(true);

        try {

            String scrnAplId = cMsg.getScreenAplID();

            // Scrn00
            if (isScreen00(myCMsg)) {
                if (scrnAplId.endsWith("_SearchAvailableQty")) {
                    doProcess_Scrn00_SearchAvailableQty(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_Allocate")) {
                    doProcess_Scrn00_Allocate(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_Search")) {
                    doProcess_Scrn00_Search(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_Cancel")) {
                    doProcess_Scrn00_Cancel(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_PageNext")) {
                    doProcess_Scrn00_PageNext(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_PagePrev")) {
                    doProcess_Scrn00_PagePrev(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_PageJump")) {
                    doProcess_Scrn00_PageJump(myCMsg, mySMsg);
                }
                if (scrnAplId.endsWith("_TBLColumnSort")) {
                    doProcess_Scrn00_TBLColumnSort(myCMsg, mySMsg);
                }
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_Scrn00_Search(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_Search";
        debugMethodStart(this, methodNm);

        try {

            backupSearchCriteria(cMsg);

            // --------------------------------------------------
            // search Hiding Inventory List.
            // --------------------------------------------------
            searchHidingInventoryList(cMsg, sMsg.A);

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_SearchAvailableQty(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_SearchAvailableQty";
        debugMethodStart(this, methodNm);

        try {

            backupSearchCriteria(cMsg);

            // --------------------------------------------------
            // search Available Qty.
            // --------------------------------------------------
            searchAvailableQty(cMsg);

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_Allocate(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_Allocate";
        debugMethodStart(this, methodNm);

        try {

            clearSearchCriteria(cMsg);
            clearAvailableQty(cMsg);
            clearAllocationQty(cMsg);
            clearMemo(cMsg);
            
            // --------------------------------------------------
            // search Hiding Inventory List.
            // --------------------------------------------------
            searchHidingInventoryList(cMsg, sMsg.A);

            // --------------------------------------------------
            // show last page.
            // --------------------------------------------------
            showLastPage(cMsg, sMsg.A);

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_Cancel(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_Cancel";
        debugMethodStart(this, methodNm);

        try {

            clearSearchCriteria(cMsg);
            clearAvailableQty(cMsg);
            clearAllocationQty(cMsg);
            clearMemo(cMsg);

            // --------------------------------------------------
            // search Hiding Inventory List.
            // --------------------------------------------------
            searchHidingInventoryList(cMsg, sMsg.A);

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_PageJump(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_PageJump";
        debugMethodStart(this, methodNm);

        try {

            NWDL0260_ACMsgArray cMsgArray = cMsg.A;
            ZYPTableUtil.clear(cMsgArray);

            // --------------------------------------------------
            // Page Jump. (copy next page data from SMsg to CMsg.)
            // --------------------------------------------------
            NWDL0260_ASMsgArray sMsgArray = sMsg.A;
            EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
            EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

            int pageFrom = pageShowFromItem.getValueInt();
            int i = pageFrom;
            for (; i < pageFrom + cMsgArray.length(); i++) {
                if (i < sMsgArray.getValidCount()) {
                    EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pageFrom), null);
                } else {
                    break;
                }
            }
            cMsgArray.setValidCount(i - pageFrom);

            setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
            setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_PageNext(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_PageNext";
        debugMethodStart(this, methodNm);

        try {

            NWDL0260_ACMsgArray cMsgArray = cMsg.A;
            ZYPTableUtil.clear(cMsgArray);

            // --------------------------------------------------
            // Next Page. (copy next page data from SMsg to CMsg.)
            // --------------------------------------------------
            NWDL0260_ASMsgArray sMsgArray = sMsg.A;
            EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
            EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

            setValue(pageShowFromItem, pageShowToItem);

            int pageFrom = pageShowFromItem.getValueInt();
            int i = pageFrom;
            for (; i < pageFrom + cMsgArray.length(); i++) {
                if (i == sMsgArray.getValidCount()) {
                    break;
                }
                EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
            }
            cMsgArray.setValidCount(i - pageFrom);

            setValue(pageShowFromItem, toBigDecimal(pageFrom + 1));
            setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount()));

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_PagePrev(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_PagePrev";
        debugMethodStart(this, methodNm);

        try {

            NWDL0260_ACMsgArray cMsgArray = cMsg.A;
            ZYPTableUtil.clear(cMsgArray);

            // --------------------------------------------------
            // Prev Page. (copy prev page data from SMsg to CMsg.)
            // --------------------------------------------------
            NWDL0260_ASMsgArray sMsgArray = sMsg.A;
            EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
            EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;

            setValue(pageShowFromItem, toBigDecimal(pageShowFromItem.getValueInt() - cMsgArray.length() - 1));

            int pageFrom = pageShowFromItem.getValueInt();
            int i = pageFrom;
            for (; i < pageFrom + cMsgArray.length(); i++) {
                if (i == sMsgArray.getValidCount()) {
                    break;
                }
                EZDMsg.copy(sMsgArray.no(i), null, cMsgArray.no(i - pageFrom), null);
            }
            cMsgArray.setValidCount(i - pageFrom);

            pageFrom = pageFrom + 1;
            setValue(pageShowFromItem, toBigDecimal(pageFrom));
            setValue(pageShowToItem,   toBigDecimal(pageFrom + cMsgArray.getValidCount() - 1));

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    private void doProcess_Scrn00_TBLColumnSort(NWDL0260CMsg cMsg, NWDL0260SMsg sMsg) {
        final String methodNm = "doProcess_Scrn00_TBLColumnSort";
        debugMethodStart(this, methodNm);

        try {

            String sortTblNm  = cMsg.xxSortTblNm.getValue();
            String sortItemNm = cMsg.xxSortItemNm.getValue();
            String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

            // Table:A
            if ("A".equals(sortTblNm)) {

                // execute column sort function
                final S21SortKey sortKey = new S21SortKey();
                sortKey.add(sortItemNm, sortOrdBy);
                S21EZDMsgArraySort.sort(new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents()), sortKey, 0, sMsg.A.getValidCount());

                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                setValue(cMsg.xxPageShowFromNum, ONE);
                setValue(cMsg.xxPageShowToNum,   toBigDecimal(cMsg.A.getValidCount()));
            }

        } finally {
            debugMethodEnd(this, methodNm);
        }
    }

    @SuppressWarnings("unchecked")
    private boolean searchAvailableQty(NWDL0260CMsg cMsg) {

        clearAvailableQty(cMsg);
        clearAllocationQty(cMsg);

        // --------------------------------------------------
        // search Available Qty.
        // --------------------------------------------------
        // On Hand
        INVTYTMsg onHandInvty = getOnHandInvty(cMsg);
        if (onHandInvty != null) {
            if (isAllocAvailableLocTp(onHandInvty)) {
                if (getMdse(cMsg) != null) {
                    setValue(cMsg.invtyAdvcOnHandQty_AV, onHandInvty.invtyAvalQty);
                } else {
                    onHandInvty = null;
                }
            } else {
                onHandInvty = null;
            }
        }
        // In Transit
        INVTYTMsg inTrnstInvty = getInTrnstInvty(cMsg);
        if (inTrnstInvty != null) {
            if (isAllocAvailableLocTp(inTrnstInvty)) {
                if (getMdse(cMsg) != null) {
                    setValue(cMsg.invtyAdvcInTrnstQty_AV, inTrnstInvty.invtyAvalQty);
                } else {
                    inTrnstInvty = null;
                }
            } else {
                inTrnstInvty = null;
            }
        }

        boolean isError = onHandInvty == null && inTrnstInvty == null;

        if (isError) {
            // Merchandise Code, WH
            cMsg.mdseCd.setErrorInfo(1, MsgId.NZZM0000E.name());
            cMsg.invtyLocCd.setErrorInfo(1, MsgId.NZZM0000E.name());
            // Product Line
            EZDCStringItem firstProdCtrlCdItem = cMsg.firstProdCtrlCd;
            if (hasValue(firstProdCtrlCdItem)) {
                firstProdCtrlCdItem.setErrorInfo(1, MsgId.NZZM0000E.name());
            }
        }

        return !isError;
    }

    private boolean searchHidingInventoryList(NWDL0260CMsg cMsg, NWDL0260_ASMsgArray sMsgArray) {

        // --------------------------------------------------
        // clear data.
        // --------------------------------------------------
        NWDL0260_ACMsgArray cMsgArray = cMsg.A;
        ZYPTableUtil.clear(cMsgArray);
        ZYPTableUtil.clear(sMsgArray);

        // --------------------------------------------------
        // search Hiding Inventory List.
        // --------------------------------------------------
        S21SsmEZDResult ssmRes = getQuery().getHidingInventoryList(cMsg, sMsgArray);
        if (ssmRes.isCodeNormal()) {
            if (ssmRes.getQueryResultCount() > sMsgArray.length()) {
                cMsg.setMessageInfo(MsgId.NZZM0001W.name());
            }
        } else {
            if (sMsgArray.getValidCount() == 0) {
                if (isScreen00(cMsg) && cMsg.getScreenAplID().endsWith("_Search")) {
                    cMsg.setMessageInfo(MsgId.NZZM0000E.name());
                }
            }
        }

        // --------------------------------------------------
        // set to CMsg.
        // --------------------------------------------------
        int line = 0;
        while (line < sMsgArray.getValidCount()) {
            if (line == cMsgArray.length()) {
                break;
            }
            EZDMsg.copy(sMsgArray.no(line), null, cMsgArray.no(line), null);
            line++;
        }
        cMsgArray.setValidCount(line);

        setValue(cMsg.xxPageShowFromNum, ONE);
        setValue(cMsg.xxPageShowToNum,   toBigDecimal(cMsgArray.getValidCount()));
        setValue(cMsg.xxPageShowOfNum,   toBigDecimal(sMsgArray.getValidCount()));

        return cMsgArray.getValidCount() > 0;
    }

    private void showLastPage(NWDL0260CMsg cMsg, NWDL0260_ASMsgArray sMsgArray) {

        NWDL0260_ACMsgArray cMsgArray = cMsg.A;
        int pageLength = cMsgArray.length();

        EZDCBigDecimalItem pageShowFromItem = cMsg.xxPageShowFromNum;
        EZDCBigDecimalItem pageShowToItem   = cMsg.xxPageShowToNum;
        EZDCBigDecimalItem pageShowOfItem   = cMsg.xxPageShowOfNum;

        int showingTotal = pageShowOfItem.getValueInt() / pageLength;
        if (pageShowOfItem.getValueInt() % pageLength != 0) {
            showingTotal++;
        }

        if (showingTotal > 1) {

            ZYPTableUtil.clear(cMsgArray);

            int i = 0;
            int sMsgStartIndex = (showingTotal - 1) * pageLength;
            for (; i < cMsgArray.length(); i++) {
                int sMsgIndex = i + sMsgStartIndex;
                if (sMsgIndex >= sMsgArray.getValidCount()) {
                    break;
                }
                EZDMsg.copy(sMsgArray.no(sMsgIndex), null, cMsgArray.no(i), null);
            }
            cMsgArray.setValidCount(i);

            setValue(pageShowFromItem, toBigDecimal(sMsgStartIndex + 1));
            setValue(pageShowToItem,   toBigDecimal(sMsgArray.getValidCount()));
        }
    }

    private static boolean isAllocAvailableLocTp(INVTYTMsg invty) {
        
        if (invty != null) {
            return asList(LOC_TP.WAREHOUSE).contains(invty.locTpCd.getValue());
        }
        
        return false;
    }
    
}
