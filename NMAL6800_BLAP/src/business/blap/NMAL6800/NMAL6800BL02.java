package business.blap.NMAL6800;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6800.common.NMAL6800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_MNF;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_PRC_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Item Master Serch(Popup) Business Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/09/80   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 *</pre>
 */
public class NMAL6800BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        NMAL6800CMsg bizMsg = (NMAL6800CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
            if ("NMAL6800_INIT".equals(scrnAplID)) {
                doProcess_NMAL6800_INIT(bizMsg, (NMAL6800SMsg) sMsg);
                ZYPGUITableColumn.getColData(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800Scrn00_CMN_Clear".equals(scrnAplID)) {
                doProcess_NMAL6800_CLEAR(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800Scrn00_Search".equals(scrnAplID)) {
                doProcess_NMAL6800_Search(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NMAL6800_PagePrev(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NMAL6800_PageNext(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800Scrn00_TBLColumnSort".equals(scrnAplID)) {
                doProcess_NMAL6800_TBLColumnSort(bizMsg, (NMAL6800SMsg) sMsg);

            } else if ("NMAL6800_NWAL1130".equals(scrnAplID)) {
                doProcess_Product_Line_level_3_Link(bizMsg, sMsg);
                
            } else {
                // do nothing
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6800_INIT(NMAL6800CMsg bizMsg, NMAL6800SMsg sMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        bizMsg.xxRptNm_H1.clear();
        bizMsg.xxRptNm_CD.clear();
        bizMsg.xxRptNm_NM.clear();
        bizMsg.xxRptNm_CD.no(0).setValue("1");
        bizMsg.xxRptNm_NM.no(0).setValue("Default");

        // MANUFACTURER
        //bizMsg.mdseItemMnfCd_H1.clear();
        // 2019/10/18 QC#53815 Del Start
        // bizMsg.mdseItemMnfCd_L1.clear();
        // bizMsg.mdseItemMnfNm_L1.clear();
        // ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_MNF.class, bizMsg.mdseItemMnfCd_L1, bizMsg.mdseItemMnfNm_L1);
        // 2019/10/18 QC#53815 Del End

        // STATUS
        //bizMsg.mdseItemStsCd_H1.clear();
        bizMsg.mdseItemStsCd_L1.clear();
        bizMsg.mdseItemStsNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_STS.class, bizMsg.mdseItemStsCd_L1, bizMsg.mdseItemStsNm_L1);
        NMAL6800CommonLogic.setInitialItemStsPulldown(bizMsg, getGlobalCompanyCode());

        // ITEM TYPE
        // bizMsg.mdseItemTpCd_H1.clear();
        bizMsg.mdseItemTpCd_L1.clear();
        bizMsg.mdseItemTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpNm_L1);

        // ITEM CLASSIFICATION
        // bizMsg.mdseItemClsTpCd_H1.clear();
        bizMsg.mdseItemClsTpCd_L1.clear();
        bizMsg.mdseItemClsTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class, bizMsg.mdseItemClsTpCd_L1, bizMsg.mdseItemClsTpNm_L1);

//        // MERCHANDISE TYPE
//        // bizMsg.coaMdseTpCd_H1.clear();
//        bizMsg.coaMdseTpCd_L1.clear();
//        bizMsg.coaMdseTpNm_L1.clear();
//        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L1, bizMsg.coaMdseTpNm_L1, PULLDOWN_DELIMITER);

        // PLANNING GROUP
        // bizMsg.prchGrpCd_H1.clear();
        bizMsg.prchGrpCd_L1.clear();
        bizMsg.prchGrpNm_L1.clear();
        NMAL6800CommonLogic.setInitialPlanningGroupPulldown(bizMsg, getGlobalCompanyCode());

        // PRICING GROUP
        //bizMsg.mdsePrcGrpCd_H1.clear();
        bizMsg.mdsePrcGrpCd_L1.clear();
        bizMsg.mdsePrcGrpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_PRC_GRP.class, bizMsg.mdsePrcGrpCd_L1, bizMsg.mdsePrcGrpNm_L1);

        // PRODUCT LEVEL 1 - 5 NAME
        NMAL6800CommonLogic.setProductLevelName(bizMsg, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HN, ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1) 
        		|| ZYPCommonFunc.hasValue(bizMsg.mdseDescShortTxt_H1) 
        		|| ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) 
        		|| ZYPCommonFunc.hasValue(bizMsg.mdseItemClsTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1) 
                || ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1) 
                || ZYPCommonFunc.hasValue(bizMsg.prchGrpCd_H1) 
                || ZYPCommonFunc.hasValue(bizMsg.mnfItemCd_H1)) { // S21_NA#9761 2016/09/28 ADD
            doProcess_NMAL6800_Search(bizMsg, sMsg);
        }
    }

    private void doProcess_NMAL6800_Search(NMAL6800CMsg bizMsg, NMAL6800SMsg sMsg) {

        NMAL6800CommonLogic.preSearchForSetName(bizMsg, getGlobalCompanyCode());

        // Search
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        S21SsmEZDResult result 
        = NMAL6800Query.getInstance().search(bizMsg, sMsg, getGlobalCompanyCode(), sMsg.A.length() + 1);
        if (result.isCodeNormal()) {
            NMAL6800CommonLogic.setSearchResultToGlblMsg(result, sMsg);
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                // NZZM0001W=0,There are too many search results,
                // there is data that cannot be displayed.
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }
            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            // Set page number
            bizMsg.xxPageShowFromNum_10.setValue(1);
            bizMsg.xxPageShowToNum_10.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_10.setValue(queryResCnt);
            // ZZM8100I=0,Process ended normally.
            bizMsg.setMessageInfo("ZZM8100I");
        } else {
            bizMsg.xxPageShowFromNum_10.clear();
            bizMsg.xxPageShowToNum_10.clear();
            bizMsg.xxPageShowOfNum_10.clear();
            // NZZM0000E=0,No search results found.
            bizMsg.setMessageInfo("NZZM0000E");
        }
    }

    private void doProcess_NMAL6800_PageNext(NMAL6800CMsg cMsg, NMAL6800SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        cMsg.xxRadioBtn.clear();

        // set value to page items
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL6800_PagePrev(NMAL6800CMsg cMsg, NMAL6800SMsg sMsg) {
        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        cMsg.xxRadioBtn.clear();

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NMAL6800_TBLColumnSort(NMAL6800CMsg cMsg, NMAL6800SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_01.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set page no
            cMsg.xxPageShowFromNum_10.setValue(1);
            cMsg.xxPageShowToNum_10.setValue(cMsg.A.getValidCount());
        }
    }

    private void doProcess_NMAL6800_CLEAR(NMAL6800CMsg cMsg, NMAL6800SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.xxRptNm_H1.clear();
        cMsg.mdseCd_H1.clear();
        cMsg.mdseDescShortTxt_H1.clear();
        cMsg.mdseItemMnfCd_H1.clear();
        cMsg.mdseItemMnfNm_H1.clear(); // 2019/10/18 QC#53815 Add
        cMsg.mnfItemCd_H1.clear();
        cMsg.upcCd_H1.clear();
        cMsg.mdseItemStsCd_H1.clear();
        cMsg.mdseDescLongTxt_H1.clear();
        cMsg.mdseItemActvDt_H1.clear();
        cMsg.mdseItemActvDt_H2.clear();
        cMsg.mdseCratTmplNm_H1.clear();
        cMsg.mdseItemTpCd_H1.clear();
        cMsg.mdseItemClsTpCd_H1.clear();
        cMsg.coaMdseTpCd_H1.clear();
        cMsg.coaProjDescTxt_H1.clear();
        cMsg.coaProdCd_H1.clear();
        cMsg.coaProdNm_H1.clear();
        cMsg.prchGrpCd_H1.clear();
        cMsg.mdsePrcGrpCd_H1.clear();
        cMsg.mktMdlCd_H1.clear();
        cMsg.mktMdlNm_H1.clear();
        cMsg.mktMdlSegCd_H1.clear();
        cMsg.mktMdlSegNm_H1.clear();
        cMsg.zerothProdCtrlCd_H1.clear();
        cMsg.zerothProdCtrlNm_H1.clear();
        cMsg.firstProdCtrlCd_H1.clear();
        cMsg.firstProdCtrlNm_H1.clear();
        cMsg.scdProdCtrlCd_H1.clear();
        cMsg.scdProdCtrlNm_H1.clear();
        cMsg.thirdProdCtrlCd_H1.clear();
        cMsg.thirdProdCtrlNm_H1.clear();
        cMsg.frthProdCtrlCd_H1.clear();
        cMsg.frthProdCtrlNm_H1.clear();
        cMsg.thirdPtyItemFlg_HY.clear();
        cMsg.thirdPtyItemFlg_HN.clear();
        cMsg.slsMatGrpCd_01.clear();
        cMsg.slsMatGrpDescTxt_01.clear();
        cMsg.slsMatGrpCd_02.clear();
        cMsg.slsMatGrpDescTxt_02.clear();
        cMsg.slsMatGrpCd_03.clear();
        cMsg.slsMatGrpDescTxt_03.clear();
        cMsg.dsCmsnGrpCd_H1.clear();
        cMsg.dsCmsnGrpDescTxt_H1.clear();

    }

    // Decide third
    private void doProcess_Product_Line_level_3_Link(NMAL6800CMsg bizMsg, EZDSMsg sMsg) {
    	if (!ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlNm_H1) && ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlNm_H1)) {
    		Map<String, Object> map = NMAL6800CommonLogic.getThirdProdHrch(bizMsg, getGlobalCompanyCode());
    		if (map != null) {
    			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("SCD_PROD_CTRL_DESC_TXT"));
    		}
    	}
    }
}
