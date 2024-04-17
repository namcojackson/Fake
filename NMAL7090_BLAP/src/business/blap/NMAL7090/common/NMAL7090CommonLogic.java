/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7090.common;

import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_PRC_LIST_ACT_TP_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_PRC_LIST_ACT_TP_DESC_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_PRC_LIST_TP_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_PRC_LIST_TP_DESC_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_RQST_STS_TP_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_CLM_RQST_STS_TP_DESC_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_GLBL_CMPY_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_COPY;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_UPDATE;
import static business.blap.NMAL7090.constant.NMAL7090Constant.MAX_ROWS;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8187E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NZZM0007E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7090.NMAL7090CMsg;
import business.blap.NMAL7090.NMAL7090Query;
import business.blap.NMAL7090.NMAL7090SMsg;
import business.blap.NMAL7090.NMAL7090_BSMsg;
import business.blap.NMAL7090.NMAL7090_CCMsg;
import business.blap.NMAL7090.NMAL7090_CSMsg;
import business.blap.NMAL7090.constant.NMAL7090Constant;
import business.db.PRC_LIST_EQUIP_RQSTTMsg;
import business.file.NMAL7090F01FMsg;
import business.file.NMAL7090F02FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_STS_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/06   CITS            S.Tanikawa      UPDATE          QC#7724
 * 2016/05/11   CITS            S.Tanikawa      UPDATE          QC#8178
 *</pre>
 */
public class NMAL7090CommonLogic {

    /**
     * getPrcListTp
     * @param bizMsg NMAL7090CMsg
     * @param gCmpyCd String
     */
    public static void getPrcListTp(NMAL7090CMsg bizMsg, String gCmpyCd) {
        bizMsg.prcListTpCd_PD.clear();
        bizMsg.prcListTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, gCmpyCd);

        S21SsmEZDResult result = NMAL7090Query.getInstance().getPrcListTp(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rec = list.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpCd_PD.no(i), (String) rec.get(DB_CLM_PRC_LIST_TP_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpDescTxt_PD.no(i), (String) rec.get(DB_CLM_PRC_LIST_TP_DESC_TXT));

                if (i >= bizMsg.prcListTpCd_PD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * getPrcListActTp
     * @param bizMsg NMAL7090CMsg
     * @param gCmpyCd String
     */
    public static void getPrcListActTp(NMAL7090CMsg bizMsg, String gCmpyCd) {
        bizMsg.prcListActTpCd_PD.clear();
        bizMsg.prcListActTpDescTxt_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, gCmpyCd);

        S21SsmEZDResult result = NMAL7090Query.getInstance().getPrcListActTp(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rec = list.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.prcListActTpCd_PD.no(i), (String) rec.get(DB_CLM_PRC_LIST_ACT_TP_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcListActTpDescTxt_PD.no(i), (String) rec.get(DB_CLM_PRC_LIST_ACT_TP_DESC_TXT));

                if (i >= bizMsg.prcListTpCd_PD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * setPullDownOnBSMsg to set pulldown on table B
     * @param line NMAL7090_BSMsg
     * @param cMsg NMAL7090CMsg
     */
    public static void setPullDownOnBSMsg(NMAL7090_BSMsg line, NMAL7090CMsg cMsg) {

        for (int j = 0; j < cMsg.prcListTpCd_PD.length(); j++) {
            ZYPEZDItemValueSetter.setValue(line.prcListTpCd_BP.no(j), cMsg.prcListTpCd_PD.no(j));
            ZYPEZDItemValueSetter.setValue(line.prcListTpDescTxt_BP.no(j), cMsg.prcListTpDescTxt_PD.no(j));
        }

        for (int j = 0; j < cMsg.prcListActTpCd_PD.length(); j++) {
            ZYPEZDItemValueSetter.setValue(line.prcListActTpCd_BP.no(j), cMsg.prcListActTpCd_PD.no(j));
            ZYPEZDItemValueSetter.setValue(line.prcListActTpDescTxt_BP.no(j), cMsg.prcListActTpDescTxt_PD.no(j));
        }

    }

    /**
     * clearFilterItemsA
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void clearFilterItemsA(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        sMsg.xxYesNoCd_FA.clear();

        sMsg.supdFromMdseCd_FA.clear();
        sMsg.mdseDescShortTxt_FO.clear();
        sMsg.supdToMdseCd_FA.clear();
        sMsg.mdseDescShortTxt_FN.clear();
        sMsg.supdCratDt_FA.clear();

        cMsg.supdFromMdseCd_FA.clear();
        cMsg.mdseDescShortTxt_FO.clear();
        cMsg.supdToMdseCd_FA.clear();
        cMsg.mdseDescShortTxt_FN.clear();
        cMsg.supdCratDt_FA.clear();
    }

    /**
     * clearFilterItemsB
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void clearFilterItemsB(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        sMsg.xxYesNoCd_FB.clear();

        sMsg.submtFlg_BA.clear();
        sMsg.rqstDscdFlg_BA.clear();
        sMsg.oldMdseCd_FB.clear();
        sMsg.oldMdseDescShortTxt_FB.clear();
        sMsg.newMdseCd_FB.clear();
        sMsg.newMdseDescShortTxt_FB.clear();
        sMsg.prcListTpCd_FS.clear();
        sMsg.prcListActTpCd_FS.clear();

        cMsg.submtFlg_BA.clear();
        cMsg.rqstDscdFlg_BA.clear();
        cMsg.oldMdseCd_FB.clear();
        cMsg.oldMdseDescShortTxt_FB.clear();
        cMsg.newMdseCd_FB.clear();
        cMsg.newMdseDescShortTxt_FB.clear();
        cMsg.prcListTpCd_FS.clear();
        cMsg.prcListActTpCd_FS.clear();
    }

    /**
     * clearFilterItemsC
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void clearFilterItemsC(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        sMsg.xxYesNoCd_FC.clear();

        sMsg.oldMdseCd_FC.clear();
        sMsg.oldMdseDescShortTxt_FC.clear();
        sMsg.newMdseCd_FC.clear();
        sMsg.newMdseDescShortTxt_FC.clear();
        sMsg.prcListTpCd_CS.clear();
        sMsg.prcListActTpCd_CS.clear();
        sMsg.newPrcAmt_FC.clear();
        sMsg.rqstStsTpCd_CS.clear();
        sMsg.xxAuthByNm_FC.clear();

        cMsg.oldMdseCd_FC.clear();
        cMsg.oldMdseDescShortTxt_FC.clear();
        cMsg.newMdseCd_FC.clear();
        cMsg.newMdseDescShortTxt_FC.clear();
        cMsg.prcListTpCd_CS.clear();
        cMsg.prcListActTpCd_CS.clear();
        cMsg.newPrcAmt_FC.clear();
        cMsg.rqstStsTpCd_CS.clear();
        cMsg.xxAuthByNm_FC.clear();
    }

    /**
     * getItemList: get detail for table A
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void getItemList(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        S21SsmEZDResult result = NMAL7090Query.getInstance().getItemList(cMsg, sMsg);

        if (result.isCodeNormal()) {
            ZYPTableUtil.clear(cMsg.A);

            // Max Record Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > MAX_ROWS) {
                queryResCnt = MAX_ROWS;
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

                // set Processed Data Flag:xxPrinFlag_A
                setProcessedTableA(cMsg, sMsg, i);

            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowToNum_A.setValue((cMsg.xxPageShowFromNum_A.getValueInt() - 1) + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);

            // No result
        } else {
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }
    }

    /**
     * setProcessedTableA
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     * @param i int
     */
    public static void setProcessedTableA(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg, int i) {

        int resCnt = 0;

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxPrinFlg_A, ZYPConstant.FLG_OFF_N);

        // UPDATE START 2016/05/06 QC#7724
        // resCnt += NMAL7090Query.getInstance().getCntPrcListEquip(cMsg.A.no(i).supdFromMdseCd_A.getValue());
        // UPDATE END 2016/05/06 QC#7724
        resCnt += NMAL7090Query.getInstance().getCntPrcListEquipReq(cMsg.A.no(i).supdFromMdseCd_A.getValue(), cMsg.A.no(i).supdToMdseCd_A.getValue());

        if (resCnt > 0) {

            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxPrinFlg_A, ZYPConstant.FLG_ON_Y);

        } else {
            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
                if (cMsg.A.no(i).supdFromMdseCd_A.getValue().equals(sMsg.B.no(j).oldMdseCd_B.getValue()) && cMsg.A.no(i).supdToMdseCd_A.getValue().equals(sMsg.B.no(j).newMdseCd_B.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxPrinFlg_A, ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }
    }

    /**
     * getNewRequest: get detail for table B
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void getNewRequest(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        S21SsmEZDResult result = NMAL7090Query.getInstance().getNewRequest(cMsg, sMsg);

        if (result.isCodeNormal()) {
            ZYPTableUtil.clear(cMsg.B);

            // Max Record Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0007E);
                queryResCnt = sMsg.B.length();
            }
            // set initial data
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                // set pulldown list for Detail Line
                setPullDownOnBSMsg(sMsg.B.no(i), cMsg);

                // set Index
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxNum_B, new BigDecimal(i));

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_ON_Y);
            }

            // // Copy Data to Table X(paging table)
            // NMAL7090CommonLogic.copySMsgBtoSMsgX(sMsg);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

            }
            cMsg.B.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum_B.setValue(1);
            cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            cMsg.xxPageShowFromNum_B.clear();
            cMsg.xxPageShowToNum_B.clear();
            cMsg.xxPageShowOfNum_B.clear();
        }
    }

    /**
     * getHistRequest: get detail for table C
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void getHistRequest(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        S21SsmEZDResult result = NMAL7090Query.getInstance().getHistRequest(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.C);

        if (result.isCodeNormal()) {

            // Max Record Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > MAX_ROWS) {
                queryResCnt = MAX_ROWS;
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {

                NMAL7090CommonLogic.copyHistRequestToBizMsg(cMsg.C.no(i), sMsg.C.no(i));
            }
            cMsg.C.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowToNum_C.setValue((cMsg.xxPageShowFromNum_C.getValueInt() - 1) + cMsg.C.getValidCount());
            cMsg.xxPageShowOfNum_C.setValue(queryResCnt);

            // No result
        } else {
            cMsg.xxPageShowFromNum_C.clear();
            cMsg.xxPageShowToNum_C.clear();
            cMsg.xxPageShowOfNum_C.clear();
        }
    }

    /**
     * setEquipReqStsTp
     * @param bizMsg NMAL7090CMsg
     * @param gCmpyCd String
     */
    public static void setEquipReqStsTp(NMAL7090CMsg bizMsg, String gCmpyCd) {
        bizMsg.rqstStsTpCd_CP.clear();
        bizMsg.rqstStsTpDescTxt_CP.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, gCmpyCd);

        S21SsmEZDResult result = NMAL7090Query.getInstance().getRqstStsTp(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rec = list.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.rqstStsTpCd_CP.no(i), (String) rec.get(DB_CLM_RQST_STS_TP_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.rqstStsTpDescTxt_CP.no(i), (String) rec.get(DB_CLM_RQST_STS_TP_DESC_TXT));

                if (i >= bizMsg.rqstStsTpCd_CP.length()) {
                    break;
                }
            }
        }
    }

    /**
     * copyHistRequestToBizMsg
     * @param cLine NMAL7090_CCMsg
     * @param sLine NMAL7090_CSMsg
     */
    public static void copyHistRequestToBizMsg(NMAL7090_CCMsg cLine, NMAL7090_CSMsg sLine) {
        ZYPEZDItemValueSetter.setValue(cLine.oldMdseCd_C, sLine.oldMdseCd_C);
        ZYPEZDItemValueSetter.setValue(cLine.oldMdseDescShortTxt_C, sLine.oldMdseDescShortTxt_C);
        ZYPEZDItemValueSetter.setValue(cLine.oldTmthTotStdCostAmt_C, sLine.oldTmthTotStdCostAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMdseCd_C, sLine.newMdseCd_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMdseDescShortTxt_C, sLine.newMdseDescShortTxt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newTmthTotStdCostAmt_C, sLine.newTmthTotStdCostAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.prcListTpCd_C, sLine.prcListTpCd_C);
        ZYPEZDItemValueSetter.setValue(cLine.prcListTpDescTxt_C, sLine.prcListTpDescTxt_C);
        ZYPEZDItemValueSetter.setValue(cLine.prcListActTpCd_C, sLine.prcListActTpCd_C);
        ZYPEZDItemValueSetter.setValue(cLine.prcListActTpDescTxt_C, sLine.prcListActTpDescTxt_C);
        ZYPEZDItemValueSetter.setValue(cLine.prcListsDescTxt_C, sLine.prcListsDescTxt_C);
        ZYPEZDItemValueSetter.setValue(cLine.retanOrigPrcFlg_C, sLine.retanOrigPrcFlg_C);
        ZYPEZDItemValueSetter.setValue(cLine.newPrcAmt_C, sLine.newPrcAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMinPrcAmt_C, sLine.newMinPrcAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMaxPrcAmt_C, sLine.newMaxPrcAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMlyPmtAmt_C, sLine.newMlyPmtAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMinLeasePmtAmt_C, sLine.newMinLeasePmtAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.newMaxLeasePmtAmt_C, sLine.newMaxLeasePmtAmt_C);
        ZYPEZDItemValueSetter.setValue(cLine.rqstStsTpCd_C, sLine.rqstStsTpCd_C);
        ZYPEZDItemValueSetter.setValue(cLine.rqstStsTpDescTxt_C, sLine.rqstStsTpDescTxt_C);

        if (!RQST_STS_TP.REQUESTED.equals(sLine.rqstStsTpCd_C.getValue())) {
            ZYPEZDItemValueSetter.setValue(cLine.prcListEquipRqstPk_C, sLine.prcListEquipRqstPk_C);
        }
        ZYPEZDItemValueSetter.setValue(cLine.xxAllPsnNm_CC, sLine.xxAllPsnNm_CC);
        ZYPEZDItemValueSetter.setValue(cLine.cratDt_C, sLine.cratDt_C);
        ZYPEZDItemValueSetter.setValue(cLine.xxAllPsnNm_CU, sLine.xxAllPsnNm_CU);
        ZYPEZDItemValueSetter.setValue(cLine.lastUpdDt_C, sLine.lastUpdDt_C);
    }

    /**
     * addNewRequest
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     * @param eventName String
     */
    public static void addNewRequest(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg, String eventName) {

        copyBCMsgToBSMsg(cMsg, sMsg);

        int selectedNum = cMsg.xxNum_SL.getValueInt();

        if (sMsg.B.getValidCount() == sMsg.B.length()) {
            /** @ cannot be added because it is exceeding the maximum number of row [@] */
            cMsg.setMessageInfo(NMAM8187E, new String[] {"New Request List", "200" });

            return;
        }
        sMsg.B.setValidCount(sMsg.B.getValidCount() + 1);
        int i = sMsg.B.getValidCount() - 1;

        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxNum_B, new BigDecimal(i));
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_ON_Y);
        if (eventName.equals(EVENT_NM_NMAL7090_UPDATE)) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedNum).xxPrinFlg_A, ZYPConstant.FLG_ON_Y);

            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).oldMdseCd_B, cMsg.A.no(selectedNum).supdFromMdseCd_A);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).oldMdseDescShortTxt_B, cMsg.A.no(selectedNum).mdseDescShortTxt_AO);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).newMdseCd_B, cMsg.A.no(selectedNum).supdToMdseCd_A);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).newMdseDescShortTxt_B, cMsg.A.no(selectedNum).mdseDescShortTxt_AO);

        } else if (eventName.equals(EVENT_NM_NMAL7090_COPY)) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).oldMdseCd_B, cMsg.B.no(selectedNum).oldMdseCd_B);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).oldMdseDescShortTxt_B, cMsg.B.no(selectedNum).oldMdseDescShortTxt_B);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).oldTmthTotStdCostAmt_B, cMsg.B.no(selectedNum).oldTmthTotStdCostAmt_B);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).newMdseCd_B, cMsg.B.no(selectedNum).newMdseCd_B);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).newMdseDescShortTxt_B, cMsg.B.no(selectedNum).newMdseDescShortTxt_B);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).newTmthTotStdCostAmt_B, cMsg.B.no(selectedNum).newTmthTotStdCostAmt_B);
        }

        // set retain original price flag
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).retanOrigPrcFlg_B, ZYPConstant.FLG_ON_Y);

        // set submit & discard flag
        if (ZYPConstant.FLG_ON_Y.equals(sMsg.submtFlg_BA.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);

        } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.rqstDscdFlg_BA.getValue())) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_ON_Y);
        }

        // set pull down
        setPullDownOnBSMsg(sMsg.B.no(i), cMsg);

        // copySMsgBtoSMsgX(sMsg);
    }

    /**
     * pageAddNewRequest
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void pageAddNewRequest(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        cMsg.B.clear();

        int pageCnt = (sMsg.B.getValidCount() - 1) / cMsg.B.length();
        int pagenationFrom = pageCnt * cMsg.B.length();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    /**
     * findPrcListEquipRqst
     * @param glblCmpyCd String
     * @param prcListEquipRqstPk BigDecimal
     * @return PRC_LIST_EQUIP_RQSTTMsg
     */
    public static PRC_LIST_EQUIP_RQSTTMsg findPrcListEquipRqst(String glblCmpyCd, BigDecimal prcListEquipRqstPk) {
        PRC_LIST_EQUIP_RQSTTMsg tMsg = new PRC_LIST_EQUIP_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipRqstPk, prcListEquipRqstPk);

        return (PRC_LIST_EQUIP_RQSTTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    // public static void copySMsgBtoSMsgX(NMAL7090SMsg sMsg) {
    // int indx = 0;
    // for (int i = 0; i < sMsg.B.getValidCount(); i++) {
    // if (indx == sMsg.X.length()) {
    // indx++;
    // break;
    // }
    // if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxYesNoCd_B.getValue())) {
    // EZDMsg.copy(sMsg.B.no(i), null, sMsg.X.no(indx), null);
    // indx++;
    // }
    // }
    // sMsg.X.setValidCount(indx);
    // }
    //
    // public static void copySMsgXtoSMsgB(NMAL7090SMsg sMsg) {
    // for (int i = 0; i < sMsg.X.getValidCount(); i++) {
    // EZDMsg.copy(sMsg.X.no(i), null, sMsg.B.no(sMsg.X.no(i).xxNum_B.getValueInt()), null);
    // }
    // }

    /**
     * copyBCMsgToBSMsg
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     */
    public static void copyBCMsgToBSMsg(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        cMsg.setCommitSMsg(true);
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            // CMsg B to SMsg X
            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(cMsg.B.no(i).xxNum_B.getValueInt()), null);
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.B.no(i).submtFlg_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cMsg.B.no(i).xxNum_B.getValueInt()).submtFlg_B, ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.B.no(i).rqstDscdFlg_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cMsg.B.no(i).xxNum_B.getValueInt()).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.B.no(i).retanOrigPrcFlg_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(cMsg.B.no(i).xxNum_B.getValueInt()).retanOrigPrcFlg_B, ZYPConstant.FLG_OFF_N);
            }
            // // SMsg X to SMsg B
            // EZDMsg.copy(sMsg.X.no(cMsg.B.no(i).xxNum_B.getValueInt()), null, sMsg.B.no(cMsg.B.no(i).xxNum_B.getValueInt()), null);
        }
    }

    /**
     * errorPage when error has occurred in Global Msg Table B, do paging and return index on Biz Msg
     * @param cMsg NMAL7090CMsg
     * @param sMsg NMAL7090SMsg
     * @param indx int
     * @return int
     */
    public static int errorPage(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg, int indx) {
        int pageCnt = indx / cMsg.B.length();
        int pageIndx = indx % cMsg.B.length();

        int pageFrom = pageCnt * cMsg.B.length();
        int j = pageFrom;
        for (; j < pageFrom + cMsg.B.length(); j++) {
            if (j < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.no(j), null, cMsg.B.no(j - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.B.setValidCount(j - pageFrom);

        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());

        return pageIndx;
    }

    /**
     * isExistPrcListTpCd when PRC_LIST_TP_CD is exist in DB, return true.
     * @param lineMsg NMAL7090_BSMsg
     * @return boolean
     */
    public static boolean isExistPrcListTpCd(NMAL7090_BSMsg lineMsg) {
        Integer resCnt = NMAL7090Query.getInstance().getCntPrcListTpCd(lineMsg);

        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    /**
     * isExistInOrdTakeMdse when ORD_TAKE_MDSE_CD(8 digits) is exist in DB, return true.
     * @param mdseCd String
     * @return boolean
     */
    public static boolean isExistInOrdTakeMdse(String mdseCd) {
        Integer resCnt = NMAL7090Query.getInstance().getCntOrdTakeMdse(mdseCd);

        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    /**
     * isExistInMdse when MDSE_CD(10 digits) is exist in DB, return true.
     * @param mdseCd String
     * @return boolean
     */
    public static boolean isExistInMdse(String mdseCd) {
        Integer resCnt = NMAL7090Query.getInstance().getCntMdse(mdseCd);

        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    /**
     * setPrcListEquipRqst
     * @param line NMAL7090_BSMsg
     * @param tMsg PRC_LIST_EQUIP_RQSTTMsg
     * @return PRC_LIST_EQUIP_RQSTTMsg
     */
    public static PRC_LIST_EQUIP_RQSTTMsg setPrcListEquipRqst(NMAL7090_BSMsg line, PRC_LIST_EQUIP_RQSTTMsg tMsg) {

        S21SsmEZDResult resultOld = NMAL7090Query.getInstance().getItemInfo(line.oldMdseCd_B.getValue());
        S21SsmEZDResult resultNew = NMAL7090Query.getInstance().getItemInfo(line.newMdseCd_B.getValue());

        if (resultOld.isCodeNormal()) {
            Map mapOld = (Map) resultOld.getResultObject();
            ZYPEZDItemValueSetter.setValue(tMsg.oldMdseDescShortTxt, mapOld.get("MDSE_DESC_SHORT_TXT").toString());
            ZYPEZDItemValueSetter.setValue(tMsg.oldTmthTotStdCostAmt, new BigDecimal(mapOld.get("THIS_MTH_TOT_STD_COST_AMT").toString()));

        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.newMdseDescShortTxt, line.newMdseDescShortTxt_B);
            ZYPEZDItemValueSetter.setValue(tMsg.newTmthTotStdCostAmt, line.newTmthTotStdCostAmt_B);

        }
        if (resultNew.isCodeNormal()) {
            Map mapNew = (Map) resultNew.getResultObject();
            ZYPEZDItemValueSetter.setValue(tMsg.newMdseDescShortTxt, mapNew.get("MDSE_DESC_SHORT_TXT").toString());
            ZYPEZDItemValueSetter.setValue(tMsg.newTmthTotStdCostAmt, new BigDecimal(mapNew.get("THIS_MTH_TOT_STD_COST_AMT").toString()));

        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.newMdseDescShortTxt, line.newMdseDescShortTxt_B);
            ZYPEZDItemValueSetter.setValue(tMsg.newTmthTotStdCostAmt, line.newTmthTotStdCostAmt_B);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.oldMdseCd, line.oldMdseCd_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMdseCd, line.newMdseCd_B);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, line.prcListTpCd_BS);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListActTpCd, line.prcListActTpCd_BS);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListsDescTxt, line.prcListsDescTxt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.retanOrigPrcFlg, line.retanOrigPrcFlg_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newPrcAmt, line.newPrcAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMinPrcAmt, line.newMinPrcAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMaxPrcAmt, line.newMaxPrcAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMlyPmtAmt, line.newMlyPmtAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMinLeasePmtAmt, line.newMinLeasePmtAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.newMaxLeasePmtAmt, line.newMaxLeasePmtAmt_B);
        ZYPEZDItemValueSetter.setValue(tMsg.submtFlg, line.submtFlg_B);
        ZYPEZDItemValueSetter.setValue(tMsg.rqstDscdFlg, line.rqstDscdFlg_B);

        if (ZYPConstant.FLG_ON_Y.equals(line.submtFlg_B.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstStsTpCd, RQST_STS_TP.REQUESTED);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstStsTpCd, RQST_STS_TP.NEW);
        }

        return tMsg;
    }

    /**
     * writeCsvFileHistRequest
     * @param cMsg NMAL7090CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileHistRequest(NMAL7090CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL7090F01FMsg fMsg = new NMAL7090F01FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(new String[] {"Price List ID", "Old Merchandise Code", "Old Merchandise Description Short Text", "New Merchandise Code", "New Merchandise Description Short Text", "Last Update By", "Last Update Date",
                "Retain Original Price Flag", "New Price Amount", "New Minimum Price Amount", "New Max Price Amount", "New Monthly Payment Amount", "New Minimum Lease Payment Amount", "New Max Lease Payment Amount", "Result Status" });
        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= NMAL7090Constant.DOWNLOAD_MAX_ROWS) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd_D1, rs.getString("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldMdseCd_D1, rs.getString("OLD_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldMdseDescShortTxt_D1, rs.getString("OLD_MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMdseCd_D1, rs.getString("NEW_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMdseDescShortTxt_D1, rs.getString("NEW_MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.updUsrId_D1, rs.getString("LAST_UPD_USR_ID"));
            ZYPEZDItemValueSetter.setValue(fMsg.lastUpdDt_D1, rs.getString("LAST_UPD_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.retanOrigPrcFlg_D1, rs.getString("RETAN_ORIG_PRC_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.newPrcAmt_D1, rs.getBigDecimal("NEW_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMinPrcAmt_D1, rs.getBigDecimal("NEW_MIN_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMaxPrcAmt_D1, rs.getBigDecimal("NEW_MAX_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMlyPmtAmt_D1, rs.getBigDecimal("NEW_MLY_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMinLeasePmtAmt_D1, rs.getBigDecimal("NEW_MIN_LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMaxLeasePmtAmt_D1, rs.getBigDecimal("NEW_MAX_LEASE_PMT_AMT"));
            // UPDATE START 2016/05/11 QC#8178
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRsltStsTpDescTxt_D1, rs.getString("RQST_RSLT_STS_TP_DESC_TXT"));
            // UPDATE END 2016/05/11 QC#8178
            csvOutFile.write();
        } while (rs.next());
        csvOutFile.close();
    }

    /**
     * writeCsvFileNewRequest
     * @param cMsg NMAL7090CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileNewRequest(NMAL7090CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL7090F02FMsg fMsg = new NMAL7090F02FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(new String[] {"Price List ID", "Old Item Code", "Old Item Code Description", "Old Item Cost", "New Item Code", "New Item Code Description", "New Item Cost", "Update Price List Type",
                "Update Price List Action", "Update Price List ID's", "Retain Original Price", "New Price", "New Min Price", "New Max Price", "New Lease Payment", "New Min Lease Payment", "New Max Lease Payment", "Submit", "Discard" });

        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= NMAL7090Constant.DOWNLOAD_MAX_ROWS) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipRqstPk_D2, rs.getBigDecimal("PRC_LIST_EQUIP_RQST_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldMdseCd_D2, rs.getString("OLD_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldMdseDescShortTxt_D2, rs.getString("OLD_MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldTmthTotStdCostAmt_D2, rs.getBigDecimal("OLD_TMTH_TOT_STD_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMdseCd_D2, rs.getString("NEW_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMdseDescShortTxt_D2, rs.getString("NEW_MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newTmthTotStdCostAmt_D2, rs.getBigDecimal("NEW_TMTH_TOT_STD_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt_D2, rs.getString("PRC_LIST_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListActTpDescTxt_D2, rs.getString("PRC_LIST_ACT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListsDescTxt_D2, rs.getString("PRC_LISTS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.retanOrigPrcFlg_D2, rs.getString("RETAN_ORIG_PRC_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.newPrcAmt_D2, rs.getBigDecimal("NEW_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMinPrcAmt_D2, rs.getBigDecimal("NEW_MIN_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMaxPrcAmt_D2, rs.getBigDecimal("NEW_MAX_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMlyPmtAmt_D2, rs.getBigDecimal("NEW_MLY_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMinLeasePmtAmt_D2, rs.getBigDecimal("NEW_MIN_LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.newMaxLeasePmtAmt_D2, rs.getBigDecimal("NEW_MAX_LEASE_PMT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.submtFlg_D2, rs.getString("SUBMT_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstDscdFlg_D2, rs.getString("RQST_DSCD_FLG"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}
