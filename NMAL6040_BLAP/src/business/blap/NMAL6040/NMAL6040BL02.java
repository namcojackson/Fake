/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6040;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6040.constant.NMAL6040Constant;
import business.db.MDSE_CATGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 * 13/09/2013   Fujitsu         A.Shinohara     Update          R-MS001
 *</pre>
 */
public class NMAL6040BL02 extends S21BusinessHandler implements NMAL6040Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // INIT
            if ("NMAL6040_INIT".equals(screenAplID)) {
                doProcess_NMAL6040_INIT(cMsg, sMsg);

            } else if ("NMAL6040Scrn00_Search_Merchandise".equals(screenAplID)) {
                doProcess_NMAL6040Scrn00_Search_Merchandise(cMsg, sMsg);

            } else if ("NMAL6040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6040Scrn00_PageNext((NMAL6040CMsg) cMsg, (NMAL6040SMsg) sMsg);

            } else if ("NMAL6040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6040Scrn00_PagePrev((NMAL6040CMsg) cMsg, (NMAL6040SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: Business processing for doProcess_NMAL6040_INIT
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6040_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL6040CMsg bizMsg = (NMAL6040CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd) || ZYPCommonFunc.hasValue(bizMsg.mdseNm) || ZYPCommonFunc.hasValue(bizMsg.firstProdCtrlCd) || ZYPCommonFunc.hasValue(bizMsg.fifthProdCtrlNm) || ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlCd)
                || ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlNm) || ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlCd) || ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlNm) || ZYPCommonFunc.hasValue(bizMsg.frthProdCtrlCd)
                || ZYPCommonFunc.hasValue(bizMsg.frthProdCtrlNm) || ZYPCommonFunc.hasValue(bizMsg.fifthProdCtrlCd) || ZYPCommonFunc.hasValue(bizMsg.fifthProdCtrlNm)) {

            search(cMsg, sMsg);
        }

        // 2013/05/13 Mod START R-MS001
        MDSE_CATGTMsgArray mdseCatgTmsgArray = (MDSE_CATGTMsgArray) S21CodeTableAccessor.findAll("MDSE_CATG");
        Map<String, String> tMsgKeysMdseCatg = new HashMap<String, String>();
        tMsgKeysMdseCatg.put(ZYPPulldownValueSetter.KEY_VALUE, "mdseCatgCd");
        tMsgKeysMdseCatg.put(ZYPPulldownValueSetter.KEY_DISPLAY, "mdseCatgNm");
        ZYPPulldownValueSetter.set(mdseCatgTmsgArray, tMsgKeysMdseCatg, bizMsg.mdseCatgCd_A2, bizMsg.mdseCatgNm_A3);

//     // 20120905 Deviation of Items in All Merchandise View - Mizutani 
//        XTRNL_MAT_GRPTMsgArray xtrnlMatGrpTmsgArry = (XTRNL_MAT_GRPTMsgArray) S21CodeTableAccessor.findAll(TBL_NM_XTRNL_MAT_GRP);
//        MDSE_RGTN_TPTMsgArray mdseRgtnTpTmsgArry = (MDSE_RGTN_TPTMsgArray) S21CodeTableAccessor.findAll(TBL_NM_MDSE_RGTN_TP);
//
//        Map<String, String> tMsgKeysXtrnlMatGrpCd = new HashMap<String, String>();
//        tMsgKeysXtrnlMatGrpCd.put(ZYPPulldownValueSetter.KEY_VALUE, XTRNL_MAT_GRP_CD);
//        tMsgKeysXtrnlMatGrpCd.put(ZYPPulldownValueSetter.KEY_DISPLAY, XTRNL_MAT_GRP_CD);
//        ZYPPulldownValueSetter.set(xtrnlMatGrpTmsgArry, tMsgKeysXtrnlMatGrpCd, bizMsg.xtrnlMatGrpCd_A2, bizMsg.xtrnlMatGrpCd_A3);
//
//        Map<String, String> tMsgKeysMdseRgtnTpTmsgArry = new HashMap<String, String>();
//        tMsgKeysMdseRgtnTpTmsgArry.put(ZYPPulldownValueSetter.KEY_VALUE, MDSE_REGN_TP_CD);
//        tMsgKeysMdseRgtnTpTmsgArry.put(ZYPPulldownValueSetter.KEY_DISPLAY, MDSE_REGN_TP_CD);
//        ZYPPulldownValueSetter.set(mdseRgtnTpTmsgArry, tMsgKeysMdseRgtnTpTmsgArry, bizMsg.mdseRgtnTpCd_A2, bizMsg.mdseRgtnTpCd_A3);
//     // 20120905 Deviation of Items in All Merchandise View - Mizutani 
        // 2013/05/13 Mod E N D R-MS001
		S21SsmEZDResult result = NMAL6040Query.getInstance().getProductLevelName(bizMsg, getGlobalCompanyCode());
		if (result.isCodeNormal()) {
			List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, String> map = (Map<String, String>) list.get(i);
					if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
						//PLG
						if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
							ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L1, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
						//PL
						} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
							ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L2, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
						//PL2
						} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
							ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L3, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
						//PL3
						} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
							ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L4, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
						//PL4
						} else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
							ZYPEZDItemValueSetter.setValue(bizMsg.mdseStruElmntTpNm_L5, (String) map.get("MDSE_STRU_ELMNT_TP_NM"));
						}
					}
				}
			}
		}
    }

    /**
     * Method name: Business processing for
     * doProcess_NMAL6040Scrn00_Search_Merchandise <dd>The method
     * explanation: The business peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6040Scrn00_Search_Merchandise(EZDCMsg cMsg, EZDSMsg sMsg) {

        search(cMsg, sMsg);

    }

    /**
     * Method name: search <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void search(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL6040CMsg bizMsg = (NMAL6040CMsg) cMsg;
        NMAL6040SMsg sharedMsg = (NMAL6040SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = null;
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
            // include merchandise mode
            ssmResult = NMAL6040Query.getInstance().getALL_MDSE_V(bizMsg, sharedMsg);
        } else {
            // structure mode
            ssmResult = NMAL6040Query.getInstance().getALL_MDSE_V_Structure(bizMsg, sharedMsg);
        }
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sharedMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sharedMsg.A.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sharedMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sharedMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Set page number
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * <pre>
     * Page Next Event
     * </pre>
     * @param cMsg Business Message
     * @param sMsg Business Message
     */
    private void doProcess_NMAL6040Scrn00_PageNext(NMAL6040CMsg cMsg, NMAL6040SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * <pre>
     * Page Preview Event
     * </pre>
     * @param cMsg Business Message
     * @param sMsg Business Message
     */
    private void doProcess_NMAL6040Scrn00_PagePrev(NMAL6040CMsg cMsg, NMAL6040SMsg sMsg) {
        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }
}
