/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1530;

import static business.blap.NWAL1530.constant.NWAL1530Constant.EXISTS_SELIAL_NUMBER;
import static business.blap.NWAL1530.constant.NWAL1530Constant.MESSAGE_NAME_ORDER_NUM;
import static business.blap.NWAL1530.constant.NWAL1530Constant.ZZZM9001E;
import static business.blap.NWAL1530.constant.NWAL1530Constant.ZZZM9006E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.db.CPOTMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/28   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/17   Fujitsu         S.Takami        Update          QC#2213
 * 2016/08/05   Fujitsu         T.Yoshida       Update          QC#11057
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 * 2019/10/17   Fujitsu         C.Hara          Update          QC#53186-1
 * 2019/12/12   Fujitsu         C.Hara          Update          QC#54474
 *</pre>
 */
public class NWAL1530BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL1530_INIT".equals(screenAplID)) {
                doProcess_NWAL1530_INIT((NWAL1530CMsg) cMsg);
                // 2018/07/11 QC#19801 add start
                ZYPGUITableColumn.getColData((NWAL1530CMsg) cMsg, (NWAL1530SMsg) sMsg, "AHEAD");
                // 2018/07/11 QC#19801 add end

            } else if ("NWAL1530Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_CMN_Reset((NWAL1530CMsg) cMsg);

            } else if ("NWAL1530Scrn00_TAB_So".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_TAB_So((NWAL1530CMsg) cMsg);
                // 2018/07/11 QC#19801 add start
                ZYPGUITableColumn.getColData((NWAL1530CMsg) cMsg, (NWAL1530SMsg) sMsg, "BHEAD");
                // 2018/07/11 QC#19801 add end

            } else if ("NWAL1530Scrn00_TAB_Po".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_TAB_Po((NWAL1530CMsg) cMsg);
                // 2018/07/11 QC#19801 add start
                ZYPGUITableColumn.getColData((NWAL1530CMsg) cMsg, (NWAL1530SMsg) sMsg, "CHEAD");
                // 2018/07/11 QC#19801 add end

            } else if ("NWAL1530Scrn00_TAB_Rws".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_TAB_Rws((NWAL1530CMsg) cMsg);
                // 2018/07/11 QC#19801 add start
                ZYPGUITableColumn.getColData((NWAL1530CMsg) cMsg, (NWAL1530SMsg) sMsg, "DHEAD");
                // 2018/07/11 QC#19801 add end

            } else if ("NWAL1530Scrn00_TAB_Inv".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_TAB_Inv((NWAL1530CMsg) cMsg);
            // 2018/07/11 QC#19801 add start
            } else if ("NWAL1530Scrn00_TAB_ShpgDtl".equals(screenAplID)) {
                ZYPGUITableColumn.getColData((NWAL1530CMsg) cMsg, (NWAL1530SMsg) sMsg, "AHEAD");
            // 2018/07/11 QC#19801 add end
            } else if ("NWAL1530Scrn00_Search_Order".equals(screenAplID)) {
                doProcess_NWAL1530Scrn00_Search_Order((NWAL1530CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1530_INIT(NWAL1530CMsg bizMsg) {

        if (!initialProcess(bizMsg)) {
            return;
        }

        searchShpgDetail(bizMsg);
    }

    /**
     * Order Search Event
     * @param bizMsg DMAL1050CMsg
     */
    private void doProcess_NWAL1530Scrn00_Search_Order(NWAL1530CMsg bizMsg) {

        if (!initialProcess(bizMsg)) {
            return;
        }

        searchShpgDetail(bizMsg);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1530Scrn00_CMN_Reset(NWAL1530CMsg bizMsg) {

        if (!initialProcess(bizMsg)) {
            return;
        }

        searchShpgDetail(bizMsg);
    }

    /**
     * TAB SO Transition Event
     * @param bizMsg DMAL1050CMsg
     */
    private void doProcess_NWAL1530Scrn00_TAB_So(NWAL1530CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_SO)) {
            searchSoInfo(bizMsg);
            bizMsg.xxRsltFlg_SO.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * TAB PO Transition Event
     * @param bizMsg DMAL1050CMsg
     */
    private void doProcess_NWAL1530Scrn00_TAB_Po(NWAL1530CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_PO)) {
            searchPoInfo(bizMsg);
            bizMsg.xxRsltFlg_PO.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * TAB RWS Transition Event
     * @param bizMsg DMAL1050CMsg
     */
    private void doProcess_NWAL1530Scrn00_TAB_Rws(NWAL1530CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_RW)) {
            searchRwsInfo(bizMsg);
            bizMsg.xxRsltFlg_RW.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * TAB Invoice Transition Event
     * @param bizMsg DMAL1050CMsg
     */
    private void doProcess_NWAL1530Scrn00_TAB_Inv(NWAL1530CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxRsltFlg_IV)) {
            searchInvoiceInfo(bizMsg);
            bizMsg.xxRsltFlg_IV.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * Search Shipping Detail
     * @param bizMsg Business Component Interface Message
     */
    private void searchShpgDetail(NWAL1530CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1530Query.getInstance().getOrderDetail(bizMsg);

        // has search result
        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(ZZZM9001E);
            bizMsg.errMsgCd_SD.setValue(ZZZM9001E);
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1530_ACMsg lineMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxLineNum_A, NWXC150001DsCheck.editDtlLineNum(lineMsg.dsOrdPosnNum_A.getValue(), lineMsg.dsCpoLineNum_A.getValue(), lineMsg.dsCpoLineSubNum_A.getValue()));
        }
    }

    /**
     * Search SO Info
     * @param bizMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    private void searchSoInfo(NWAL1530CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1530Query.getInstance().getSoInfo(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.errMsgCd_SO.setValue(ZZZM9001E);
            return;
        }

        // Serial Number Search
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1530_BCMsg lineMsg = bizMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxLineNum_B, NWXC150001DsCheck.editDtlLineNum(lineMsg.dsOrdPosnNum_B.getValue(), lineMsg.dsCpoLineNum_B.getValue(), lineMsg.dsCpoLineSubNum_B.getValue()));

            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("glblCmpyCd", getGlobalCompanyCode());
            inMap.put("cpoOrdNum", lineMsg.cpoOrdNum_B.getValue());
            // 2016/02/17 QC#2213 Add Start
            inMap.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum_B.getValue());
            inMap.put("cpoDtlLineSubNum", lineMsg.cpoDtlLineSubNum_B.getValue());
            // 2016/02/17 QC#2213 Add End

            List<Map<String, String>> outList = (List<Map<String, String>>) NWAL1530Query.getInstance().getSerialNumber(inMap).getResultObject();

            if (outList.size() == 1) {
                Map<String, String> outMap = (Map<String, String>) outList.get(0);
                String serNum = outMap.get("SER_NUM");
                String shpgStsCd = outMap.get("SHPG_STS_CD");

                lineMsg.serNum_B.setValue(serNum);
                lineMsg.shpgStsCd_B.setValue(shpgStsCd);

            } else if (outList.size() > 1) {
                Map<String, String> outMap = (Map<String, String>) outList.get(0);
                String shpgStsCd = outMap.get("SHPG_STS_CD");

                lineMsg.serNum_B.setValue(EXISTS_SELIAL_NUMBER);
                lineMsg.serNum_LK.setValue(EXISTS_SELIAL_NUMBER);
                lineMsg.shpgStsCd_B.setValue(shpgStsCd);
            }
            // 2019/12/12 QC#54474 Add Start
            if (ZYPCommonFunc.hasValue(lineMsg.carrTrkUrl_B)) {
                String carrUrl = lineMsg.carrTrkUrl_B.getValue().replace("$$", lineMsg.proNum_B.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.carrTrkUrl_B,carrUrl);
            }
            // 2019/12/12 QC#54474 Add End
        }
    }

    /**
     * Search PO Info
     * @param bizMsg Business Component Interface Message
     */
    private void searchPoInfo(NWAL1530CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1530Query.getInstance().getPoInfo(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.errMsgCd_PO.setValue(ZZZM9001E);
            return;
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1530_CCMsg lineMsg = bizMsg.C.no(i);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxLineNum_C, NWXC150001DsCheck.editDtlLineNum(lineMsg.dsOrdPosnNum_C.getValue(), lineMsg.dsCpoLineNum_C.getValue(), lineMsg.dsCpoLineSubNum_C.getValue()));
            // 2019/10/17 QC#53186-1 Add Start
            if(ZYPCommonFunc.hasValue(lineMsg.carrTrkUrl_C)){
                String carrUrl = lineMsg.carrTrkUrl_C.getValue().replace("$$", lineMsg.proNum_C.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.carrTrkUrl_C,carrUrl);
            }
            // 2019/10/17 QC#53186-1 Add End
        }
    }

    /**
     * Search RWS Info
     * @param bizMsg Business Component Interface Message
     */
    private void searchRwsInfo(NWAL1530CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1530Query.getInstance().getRwsInfo(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.errMsgCd_RW.setValue(ZZZM9001E);
            return;
        }

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL1530_DCMsg lineMsg = bizMsg.D.no(i);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxLineNum_D, NWXC150001DsCheck.editDtlLineNum(lineMsg.dsOrdPosnNum_D.getValue(), lineMsg.dsCpoLineNum_D.getValue(), lineMsg.dsCpoLineSubNum_D.getValue()));
        }
    }

    /**
     * Search Invoice Info
     * @param bizMsg Business Component Interface Message
     */
    private void searchInvoiceInfo(NWAL1530CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1530Query.getInstance().getInvoiceInfo(bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.errMsgCd_IV.setValue(ZZZM9001E);
        }
    }

    /**
     * Initial Process
     * @param bizMsg DMAL1050CMsg
     * @return No Error : true
     */
    private boolean initialProcess(NWAL1530CMsg bizMsg) {

        // Error Message Clear
        bizMsg.errMsgCd_SD.clear();
        bizMsg.errMsgCd_SO.clear();
        bizMsg.errMsgCd_PO.clear();
        bizMsg.errMsgCd_RW.clear();
        bizMsg.errMsgCd_IV.clear();

        // Searched Flag Clear
        bizMsg.xxRsltFlg_SO.clear();
        bizMsg.xxRsltFlg_PO.clear();
        bizMsg.xxRsltFlg_RW.clear();
        bizMsg.xxRsltFlg_IV.clear();

        // Detail Clear
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.E);

        // Presence check of order number
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);

        if (cpoTMsg == null) {
            bizMsg.cpoOrdNum.setErrorInfo(1, ZZZM9006E, new String[] {MESSAGE_NAME_ORDER_NUM });
            return false;
        }

        return true;
    }
}
