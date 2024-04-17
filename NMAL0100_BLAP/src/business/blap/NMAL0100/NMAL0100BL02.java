package business.blap.NMAL0100;

import static business.blap.NMAL0100.constant.NMAL0100Constant.CSV_FILE_NAME;
import static business.blap.NMAL0100.constant.NMAL0100Constant.NMAL0100_MAX_DOWNLOAD_CNT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_MNF;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_PRC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ITEM_MSTR_UPLD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 * 2015/06/24   Fujitsu         M.Yamada        Update          0601
 * 10/02/2015   SRAA            K.Aratani       Update
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 * 2020/07/06   CITS            M.Furugoori     Update          QC#55448
 *</pre>
 */
public class NMAL0100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        NMAL0100CMsg bizMsg = (NMAL0100CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
            if ("NMAL0100_INIT".equals(scrnAplID)) {
                doProcess_NMAL0100_INIT(bizMsg, (NMAL0100SMsg) sMsg);
                ZYPGUITableColumn.getColData(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_CMN_Clear".equals(scrnAplID)) {
                doProcess_NMAL0100_CLEAR(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_CMN_Reset".equals(scrnAplID)) {
                doProcess_NMAL0100_RESET(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_Search".equals(scrnAplID)) {
                doProcess_NMAL0100_Search(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NMAL0100_PageJump(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NMAL0100_PagePrev(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NMAL0100_PageNext(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_TBLColumnSort".equals(scrnAplID)) {
                doProcess_NMAL0100_TBLColumnSort(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_OnChangeSavedSearchOption".equals(scrnAplID)) {
                doProcess_NMAL0100_OnChangeSavedSearchOption(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100Scrn00_CMN_Download".equals(scrnAplID)) {
                doProcess_NMAL0100_Download(bizMsg, (NMAL0100SMsg) sMsg);

            } else if ("NMAL0100_NWAL1130".equals(scrnAplID)) {
                doProcess_Product_Line_level_3_Link(bizMsg, sMsg);
                
            } else {
                //do nothing
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL0100_Download(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1000);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL0100Query.getInstance().getClass());

            //create csv file
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            NMAL0100CommonLogic.preSearchForSetName(bizMsg, getGlobalCompanyCode());

            int rownum = ZYPCodeDataUtil.getNumConstValue(NMAL0100_MAX_DOWNLOAD_CNT, getGlobalCompanyCode()).intValue();
            NMAL0100CommonLogic.createSsmPrm(bizMsg, getGlobalCompanyCode(), rownum + 1, ssmParam);
            //Search
            ps = ssmLLClient.createPreparedStatement("search", ssmParam, execParam);
            rs = ps.executeQuery();
            NMAL0100CommonLogic.writeCsvFile(bizMsg, rs, rownum);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void doProcess_NMAL0100_INIT(NMAL0100CMsg bizMsg, NMAL0100SMsg sMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        //Setup select box 
        NMAL0100CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

        bizMsg.xxLgcyOrdTpNm_H1.clear();
        bizMsg.xxLgcyOrdTpNm_CD.clear();
        bizMsg.xxLgcyOrdTpNm_NM.clear();
        bizMsg.xxLgcyOrdTpNm_CD.no(0).setValue("1");
        bizMsg.xxLgcyOrdTpNm_NM.no(0).setValue("Default");

        //MANUFACTURER
        // 2019/10/18 QC#53815 Del Start
        // bizMsg.mdseItemMnfCd_H1.clear();
        // bizMsg.mdseItemMnfCd_L1.clear();
        // bizMsg.mdseItemMnfNm_L1.clear();
        // ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_MNF.class, bizMsg.mdseItemMnfCd_L1, bizMsg.mdseItemMnfNm_L1);
        // 2019/10/18 QC#53815 Del End

        //STATUS
        bizMsg.mdseItemStsCd_H1.clear();
        bizMsg.mdseItemStsCd_L1.clear();
        bizMsg.mdseItemStsNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_STS.class, bizMsg.mdseItemStsCd_L1, bizMsg.mdseItemStsNm_L1);
        NMAL0100CommonLogic.setInitialItemStsPulldown(bizMsg, getGlobalCompanyCode());

        //ITEM TYPE
        bizMsg.mdseItemTpCd_H1.clear();
        bizMsg.mdseItemTpCd_L1.clear();
        bizMsg.mdseItemTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpNm_L1);

        //ITEM CLASSIFICATION
        bizMsg.mdseItemClsTpCd_H1.clear();
        bizMsg.mdseItemClsTpCd_L1.clear();
        bizMsg.mdseItemClsTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class, bizMsg.mdseItemClsTpCd_L1, bizMsg.mdseItemClsTpNm_L1);

        //MERCHANDISE TYPE
//        bizMsg.coaMdseTpCd_H1.clear();
//        bizMsg.coaMdseTpCd_L1.clear();
//        bizMsg.coaMdseTpNm_L1.clear();
//        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L1, bizMsg.coaMdseTpNm_L1, PULLDOWN_DELIMITER);

        //PLANNING GROUP
        bizMsg.prchGrpCd_H1.clear();
        bizMsg.prchGrpCd_L1.clear();
        bizMsg.prchGrpNm_L1.clear();
        NMAL0100CommonLogic.setInitialPlanningGroupPulldown(bizMsg, getGlobalCompanyCode());

        //PRICING GROUP
        bizMsg.mdsePrcGrpCd_H1.clear();
        bizMsg.mdsePrcGrpCd_L1.clear();
        bizMsg.mdsePrcGrpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_PRC_GRP.class, bizMsg.mdsePrcGrpCd_L1, bizMsg.mdsePrcGrpNm_L1);

        //PRODUCT LEVEL 1 - 5 NAME
        NMAL0100CommonLogic.setProductLevelName(bizMsg, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HN, ZYPConstant.FLG_OFF_N);

        // START 2020/07/06 [QC#55448,ADD]
        //UPLOAD FILE
        bizMsg.itemMstrUpldTpCd_H1.clear();
        bizMsg.itemMstrUpldTpCd_L1.clear();
        bizMsg.itemMstrUpldTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(ITEM_MSTR_UPLD_TP.class, bizMsg.itemMstrUpldTpCd_L1, bizMsg.itemMstrUpldTpNm_L1);
        // END 2020/07/06 [QC#55448,ADD]
    }

    private void doProcess_NMAL0100_Search(NMAL0100CMsg bizMsg, NMAL0100SMsg sMsg) {

        NMAL0100CommonLogic.preSearchForSetName(bizMsg, getGlobalCompanyCode());

        //Search
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        S21SsmEZDResult result //
        = NMAL0100Query.getInstance().search(bizMsg, sMsg, getGlobalCompanyCode(), sMsg.A.length() + 1);
        if (result.isCodeNormal()) {
            NMAL0100CommonLogic.setSearchResultToGlblMsg(result, sMsg);
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                //NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }
            //Copy from SMsg to cMsg
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
            //ZZM8100I=0,Process ended normally.
            bizMsg.setMessageInfo("ZZM8100I");
        } else {
            bizMsg.xxPageShowFromNum_10.clear();
            bizMsg.xxPageShowToNum_10.clear();
            bizMsg.xxPageShowOfNum_10.clear();
            //NZZM0000E=0,No search results found.
            bizMsg.setMessageInfo("NZZM0000E");
        }
    }

    private void doProcess_NMAL0100_PageNext(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg) {
        //copy data from SMsg onto CMsg
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

        //set value to page items
        cMsg.xxPageShowFromNum_10.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL0100_PagePrev(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg) {
        //copy data from SMsg to CMsg
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

    private void doProcess_NMAL0100_PageJump(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg) {
        //copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
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

        //set value to page items
        cMsg.xxPageShowToNum_10.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL0100_TBLColumnSort(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_01.getValue();

        //Table:A
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

            //set page no 
            cMsg.xxPageShowFromNum_10.setValue(1);
            cMsg.xxPageShowToNum_10.setValue(cMsg.A.getValidCount());
        }
    }

    private void doProcess_NMAL0100_CLEAR(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.xxLgcyOrdTpNm_H1.clear();
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

        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();
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
        // START 2020/07/06 [QC#55448,ADD]
        cMsg.itemMstrUpldTpCd_H1.clear();
        // END 2020/07/06 [QC#55448,ADD]
    }

    private void doProcess_NMAL0100_RESET(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg) {
        doProcess_NMAL0100_CLEAR(bizMsg, glblMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HN, ZYPConstant.FLG_OFF_N);
    }

    private void doProcess_NMAL0100_OnChangeSavedSearchOption(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL0100CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    // Decide third
    private void doProcess_Product_Line_level_3_Link(NMAL0100CMsg bizMsg, EZDSMsg sMsg) {
    	if (!ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlNm_H1) && ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlNm_H1)) {
    		Map<String, Object> map = NMAL0100CommonLogic.getThirdProdHrch(bizMsg, getGlobalCompanyCode());
    		if (map != null) {
    			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("SCD_PROD_CTRL_DESC_TXT"));
    		}
    	}
    }
    
}
