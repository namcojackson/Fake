/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/10/14   Fujitsu         I.Kondo         Create          N/A
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 *</pre>
 */
package business.blap.NPAL0100;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL0100.common.NPAL0100CommonLogic;
import business.blap.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class NPAL0100BL02 extends S21BusinessHandler implements NPAL0100Constant {
    
    private NPAL0100CommonLogic common = new NPAL0100CommonLogic();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            NPAL0100CommonLogic.setEventId(cMsg);
            if (SCRN_APL_ID.NPAL0100_INIT.toString().equals(screenAplID)) {
                doProcess_NPAL0100_INIT(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_Apply.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_Apply(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_Cancel.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_Cancel(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_CMN_Clear.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_Clear(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_CMN_Close.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_Close(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_PagePrev.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_PagePrev(cMsg, sMsg);
            } else if (SCRN_APL_ID.NPAL0100Scrn00_PageNext.toString().equals(screenAplID)) {
                doProcess_NPAL0100Scrn00_PageNext(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL0100_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;        

        globalMsg.clear();
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        bizMsg.locNm_H2.setValue(NPAL0100CommonLogic.getBillToCustCd(bizMsg, getGlobalCompanyCode()));
        bizMsg.coa1L3If.setValue(getMerchandiseType(bizMsg));

        // Set MAX Count
        if (globalMsg.B.length() < bizMsg.invQty.getValue().intValue()) {
            bizMsg.xxPageShowOfNum.setValue(globalMsg.B.length());
        } else {
            bizMsg.xxPageShowOfNum.setValue(bizMsg.invQty.getValue());
        }
        bizMsg.B.setValidCount(bizMsg.xxPageShowOfNum.getValueInt());
        globalMsg.B.setValidCount(bizMsg.B.getValidCount());

        // Set Serial#
        // Set Serial# by DB data
        NPAL0100CommonLogic.setSerialNumberList(bizMsg, globalMsg);

        if (ZYPCommonFunc.hasValue(globalMsg.B.no(0).xxRowNum_B2)) {
            // Sort
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.B, globalMsg.B.no(0).getBaseContents());

            S21SortKey sortKey = new S21SortKey();
            sortKey.add(TABLE_INIT_SORT_KEY, S21SortKey.ASC);

            int sortEndPosition = 0;
            for (int i = 0; i < globalMsg.B.length(); i++) {
                if (!ZYPCommonFunc.hasValue(globalMsg.B.no(i).xxRowNum_B2)) {
                    break;
                } else {
                    sortEndPosition++;
                }
            }

            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sortEndPosition);
        }

        // Set RowNum
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            // Set for ViewNum            
            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxRowNum_B1, bizMsg.B.no(i).xxRowNum_B1);
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).xxRowNum_B2)) {
                // Set for innnerNum
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxRowNum_B2, bizMsg.B.no(i).xxRowNum_B2);
            }
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            // Override Serial# by Request data if it has data.
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).serNum_B1)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).serNum_B1, bizMsg.B.no(i).serNum_B1);
            }
            // 1/20/2010 add.
            // Override updateFlg by Request data if it has data.
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).xxSetFlg_B1)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxSetFlg_B1, bizMsg.B.no(i).xxSetFlg_B1);
            }
        }

        // Set for Clear Data
        // EZDMsg.copy(globalMsg.A, null, globalMsg.O, null);
        for (int i = 0; i < globalMsg.B.length(); i++) {
            EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B1.toString(), globalMsg.O.no(i), SMSG_TABLE_MDF.O1.toString());
            EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B2.toString(), globalMsg.O.no(i), SMSG_TABLE_MDF.O2.toString());
        }

        // Def# 362045 TODO        
        NPAL0100Query.getInstance().getShpgPlnInfo(bizMsg);

        // Set Page Num
        initializePageInfo(bizMsg, globalMsg);
    }

    private void doProcess_NPAL0100Scrn00_Apply(EZDCMsg cMsg, EZDSMsg sMsg) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

        common.setSerialNumberData(bizMsg, globalMsg);

        if (ERROR_FLG_ON.equals(bizMsg.xxErrFlg.getValue())) {
            return;
        }

        for (int i = 0; i < globalMsg.B.length(); i++) {
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
        }
    }

    private void doProcess_NPAL0100Scrn00_Cancel(EZDCMsg cMsg, EZDSMsg sMsg) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

        setSerialNumberFromGlobalMsg(bizMsg, globalMsg);

    }

    private void doProcess_NPAL0100Scrn00_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.B.clear();
        bizMsg.xxErrFlg.setValue(ERROR_FLG_OFF);
        globalMsg.B.clear();

        for (int i = 0; i < globalMsg.O.length(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.O.no(i).xxRowNum_O1)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxRowNum_B1, globalMsg.O.no(i).xxRowNum_O1);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRowNum_B1, globalMsg.O.no(i).xxRowNum_O1);
            }
            if (ZYPCommonFunc.hasValue(globalMsg.O.no(i).xxRowNum_O2)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxRowNum_B2, globalMsg.O.no(i).xxRowNum_O2);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRowNum_B2, globalMsg.O.no(i).xxRowNum_O2);
            }
            if (ZYPCommonFunc.hasValue(globalMsg.O.no(i).serNum_O1)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).serNum_B1, globalMsg.O.no(i).serNum_O1);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).serNum_B1, globalMsg.O.no(i).serNum_O1);
            }
            if (ZYPCommonFunc.hasValue(globalMsg.O.no(i).xxSetFlg_O1)) {
                ZYPEZDItemValueSetter.setValue(globalMsg.B.no(i).xxSetFlg_B1, globalMsg.O.no(i).xxSetFlg_O1);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxSetFlg_B1, globalMsg.O.no(i).xxSetFlg_O1);
            }
        }

        // Set Page Num
        initializePageInfo(bizMsg, globalMsg);

    }

    private void initializePageInfo(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {

        int outGetBlankIndex = getBlankIndex(globalMsg);
        int initPage = outGetBlankIndex / bizMsg.A.length();
        int goPageFrom = initPage * bizMsg.A.length();

        bizMsg.xxPageShowFromNum.setValue(goPageFrom);
        bizMsg.xxPageShowToNum.clear();

        pagenation(bizMsg, globalMsg);
    }

    private void doProcess_NPAL0100Scrn00_Close(EZDCMsg cMsg, EZDSMsg sMsg) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

        common.setSerialNumberData(bizMsg, globalMsg);

        if (ERROR_FLG_ON.equals(bizMsg.xxErrFlg.getValue())) {
            return;
        }

        for (int i = 0; i < globalMsg.B.length(); i++) {
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
        }
    }

    private void doProcess_NPAL0100Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        paging(cMsg, sMsg, PAGING_TYPE.PREV);
    }

    private void doProcess_NPAL0100Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        paging(cMsg, sMsg, PAGING_TYPE.NEXT);
    }

    private void paging(EZDCMsg cMsg, EZDSMsg sMsg, PAGING_TYPE type) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

        common.setSerialNumberData(bizMsg, globalMsg);

        if (ERROR_FLG_ON.equals(bizMsg.xxErrFlg.getValue())) {
            return;
        }

        setSerialNumberToGlobalMsg(bizMsg, globalMsg);

        if (PAGING_TYPE.PREV.equals(type)) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
        } else if (PAGING_TYPE.NEXT.equals(type)) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt());
        } else {
            // do nothing.
        }
        bizMsg.xxPageShowToNum.clear();

        pagenation(bizMsg, globalMsg);
    }

    private int getBlankIndex(NPAL0100SMsg globalMsg) {

        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.B.no(i).xxRowNum_B1)) {
                return i;
            }
        }

        // if no data, return zero.
        return 0;

    }

    private void pagenation(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {

        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.B.getValidCount()) {
                EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B1.toString(), bizMsg.A.no(i - pagenationFrom), CMSG_TABLE_MDF.A1.toString());
                EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B2.toString(), bizMsg.A.no(i - pagenationFrom), CMSG_TABLE_MDF.A2.toString());
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // Set Showing From & Showing To
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }

    private void setSerialNumberToGlobalMsg(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {
        int pageShowFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A1.toString(), globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B1.toString());
            EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A2.toString(), globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B2.toString());
        }
    }

    private void setSerialNumberFromGlobalMsg(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {
        int pageShowFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        EZDMsg.copy(globalMsg.B.no(pageShowFrom + bizMsg.xxRowNum.getValueInt()), SMSG_TABLE_MDF.B1.toString(), bizMsg.A.no(bizMsg.xxRowNum.getValueInt()), CMSG_TABLE_MDF.A1.toString());
        EZDMsg.copy(globalMsg.B.no(pageShowFrom + bizMsg.xxRowNum.getValueInt()), SMSG_TABLE_MDF.B2.toString(), bizMsg.A.no(bizMsg.xxRowNum.getValueInt()), CMSG_TABLE_MDF.A2.toString());
    }

    private String getMerchandiseType(NPAL0100CMsg bizMsg) {
        String mdseTpCd = NPAL0100CommonLogic.getMdseTpCd(bizMsg, getGlobalCompanyCode());
        if (mdseTpCd.equals(MDSE_TP_CD_KIT)) {
            return MDSE_TP_CD_VIEW.Yes.toString();
        } else {
            return MDSE_TP_CD_VIEW.No.toString();
        }
    }
}
