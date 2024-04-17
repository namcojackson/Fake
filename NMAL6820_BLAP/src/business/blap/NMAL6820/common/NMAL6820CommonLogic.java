/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6820.common;

import static business.blap.NMAL6820.constant.NMAL6820Constant.BUSINESS_ID;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DS_ACCT_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DS_ACCT_NUM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_SWH_NM_OF_EMERG_SRC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_SWH_NM_OF_SRC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_WH_NM_OF_RTRN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.MESSAGE_PARAM_RTL_WH_NM_OF_SRC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_ALT_OWNER;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_BR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_DEF_EMRG_WH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_DEF_RTRN_WH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_DEF_SRC_WH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_INVTY_OWNR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_LOC_TP_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_OWNER;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTRN_TO_CODE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SHIP_TO_CODE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_ST;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_TECH_TOC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WH_GROUP_NAME;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WMS_WH_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PRF_CARR_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAL6820_ADDR_CHK_FLG;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAL6820_CHOICE_WH_OWNR_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0009E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0010E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0014E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0039E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0070E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0216E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0836E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0850E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8314E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8384E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8387E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8454E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8625E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMZC0030_ERROR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.PRNT_VND_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_SWH_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_SWH_DSBL_FLG_CHK_BOX_NAME_VALUE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_WH_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TAB_ADDR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TAB_SRC;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TAB_SWH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.VND_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.YES_NO_PULLDOWN_CD;
import static business.blap.NMAL6820.constant.NMAL6820Constant.YES_NO_PULLDOWN_NM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6820.NMAL6820CMsg;
import business.blap.NMAL6820.NMAL6820Query;
import business.blap.NMAL6820.NMAL6820SMsg;
import business.blap.NMAL6820.NMAL6820_ACMsg;
import business.db.COA_BRTMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PTYTMsg;
import business.db.PTYTMsgArray;
import business.db.RTL_WH_CATGTMsg;
import business.db.S21_PSNTMsg;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.TECH_MSTRTMsgArray;
import business.parts.NMXC107001PMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMX.NMXC107001.NMXC107001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 1590
 * 02/16/2016   CSAI            D.Fukaya        Update          QC# 2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC# 3436
 * 08/05/2016   CITS            S.Endo          Update          QC#10838
 * 10/19/2016   Fujitsu         C.Yokoi         Update          QC# 4096
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120
 * 02/07/2017   CITS            Y.IWASAKI       Update          QC#17233
 * 04/09/2019   Fujitsu         T.Ogura         Update          QC#28667
 * 09/01/2020   CITS            H.Dimay         Update          QC#57602
 * 09/17/2020   CITS            J.Evangelista   Update          QC#57659
 * 12/02/2020   CITS            H.Dimay         Update          QC#57659
 *</pre>
 */
public class NMAL6820CommonLogic {

    /**
     * clear Msg
     * @param cMsg NMAL6820CMsg
     * @param sMsg NMAL6820SMsg
     */
    public static void clearMsg(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        // cMsg initialization
        // Header
        cMsg.rtlWhCd_H1.clear();
        cMsg.rtlWhNm_H1.clear();
        cMsg.rtlWhDescTxt_H1.clear();
        cMsg.rtlWhCatgCd_H1.clear();
        cMsg.whMgrPsnCd_H1.clear();
        cMsg.fullPsnNm_H1.clear();
        cMsg.altPsnCd_H1.clear();
        cMsg.fullPsnNm_H2.clear();
        cMsg.telNum_H1.clear();
        cMsg.faxNum_H1.clear();
        cMsg.invtyAcctCd_H1.clear();
        cMsg.invtyAcctCd_L1.clear();
        cMsg.invtyAcctNm_L1.clear();
        cMsg.invtyOwnrCd_H1.clear();
        cMsg.invtyOwnrCd_L1.clear();
        cMsg.invtyOwnrNm_L1.clear();
        cMsg.whOwnrCd_H1.clear();
        cMsg.whOwnrCd_L1.clear();
        cMsg.whOwnrNm_L1.clear();
        cMsg.effFromDt_H1.clear();
        cMsg.effThruDt_H1.clear();
        cMsg.whSysTpCd_H1.clear();
        cMsg.whSysTpCd_L1.clear();
        cMsg.whSysTpNm_L1.clear();
        cMsg.wmsWhCd_H1.clear();
        cMsg.physWhCd_H1.clear();
        cMsg.autoSoDropFlg_H1.clear();
        cMsg.firstRefCmntTxt_H1.clear();
        cMsg.whStartTm_H1.clear();
        cMsg.xxScrItem7Txt_ST.clear();
        cMsg.whEndTm_H1.clear();
        cMsg.xxScrItem7Txt_ET.clear();
        cMsg.whCutOffTm_H1.clear();
        cMsg.xxScrItem7Txt_CT.clear();
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        cMsg.fedexCutOffTm_H1.clear();
        cMsg.xxScrItem7Txt_FT.clear();
        cMsg.upsCutOffTm_H1.clear();
        cMsg.xxScrItem7Txt_UT.clear();
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        cMsg.geoCd_H1.clear();
        cMsg.carrCd_H1.clear();
        cMsg.carrNm_H1.clear();
        cMsg.coaBrCd_H1.clear();
        cMsg.coaBrNm_H1.clear();
        cMsg.locTpCd_H1.clear();
        cMsg.whCd_H1.clear();

        // Address
        cMsg.xxSetFlg_S1.clear();
        cMsg.shipToCustCd_S1.clear();
        cMsg.locNum_S1.clear();
        cMsg.dsAcctNum_S1.clear();
        cMsg.dsAcctNm_S1.clear();
        cMsg.dsLocNm_S1.clear();
        cMsg.addlLocNm_S1.clear();
        cMsg.firstLineAddr_S1.clear();
        cMsg.scdLineAddr_S1.clear();
        cMsg.thirdLineAddr_S1.clear();
        cMsg.frthLineAddr_S1.clear();
        cMsg.ctyAddr_S1.clear();
        cMsg.cntyPk_S1.clear();
        cMsg.stCd_S1.clear();
        cMsg.provNm_S1.clear();
        cMsg.postCd_S1.clear();
        cMsg.ctryCd_S1.clear();
        cMsg.vndLocCd_S1.clear();
        cMsg.poRcptRteTpCd_S1.clear();
        cMsg.rmaRcptRteTpCd_S1.clear();
        cMsg.xxChkBox_R1.clear();
        cMsg.xxSetFlg_R1.clear();
        cMsg.rtrnToCustCd_R1.clear();
        cMsg.locNum_R1.clear();
        cMsg.dsAcctNum_R1.clear();
        cMsg.dsAcctNm_R1.clear();
        cMsg.dsLocNm_R1.clear();
        cMsg.rtrnToAddlLocNm_R1.clear();
        cMsg.rtrnToFirstLineAddr_R1.clear();
        cMsg.rtrnToScdLineAddr_R1.clear();
        cMsg.rtrnToThirdLineAddr_R1.clear();
        cMsg.rtrnToFrthLineAddr_R1.clear();
        cMsg.rtrnToCtyAddr_R1.clear();
        cMsg.cntyPk_R1.clear();
        cMsg.rtrnToStCd_R1.clear();
        cMsg.rtrnToProvNm_R1.clear();
        cMsg.rtrnToPostCd_R1.clear();
        cMsg.ctryCd_R1.clear();
        cMsg.vndLocCd_R1.clear();
        cMsg.cntyNm_S1.clear();
        cMsg.cntyNm_R1.clear();

        // Sourcing
        cMsg.srcZnCd_S1.clear();
        cMsg.procrTpCd_S1.clear();
        cMsg.prntVndNm_SD.clear();
        cMsg.vndNm_SD.clear();
        cMsg.procrTpCd_E1.clear();
        cMsg.prntVndNm_SE.clear();
        cMsg.vndNm_SE.clear();
        cMsg.procrTpCd_R1.clear();
        cMsg.prntVndNm_SR.clear();
        cMsg.vndNm_SR.clear();

        // SWH
        ZYPTableUtil.clear(cMsg.A);

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.C);
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * @param cd EZDBBigDecimalItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCBigDecimalItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (BigDecimal) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length() - 1) {
                break;
            }
        }
    }

    /**
     * @param targetItem EZDCStringItem
     * @param pullDownList List<Map>
     * @param dbColumnNm String
     */
    public static void setDefaultForPulldownList(EZDCStringItem targetItem, List<Map> pullDownList, String dbColumnNm) {

        if (ZYPCommonFunc.hasValue(targetItem) && pullDownList.size() > 0) {
            boolean existFlag = false;
            for (int i = 0; i < pullDownList.size(); i++) {
                if (targetItem.getValue().equals((String) pullDownList.get(i).get(dbColumnNm))) {
                    existFlag = true;
                }
            }
            if (!existFlag) {
                ZYPEZDItemValueSetter.setValue(targetItem, (String) pullDownList.get(0).get(dbColumnNm));
            }
        }
    }

    /**
     * The method explanation: The search processing of Retail
     * Warehouse information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchRtlWh(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg, S21BusinessHandler handler) {

        // =================================================
        // search Retail WH
        // =================================================
        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getRtlWh(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            EZDMsg.copy(sMsg, null, cMsg, null);

            // Set Account# and Account Name
            if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_S1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_S1, sMsg.dsAcctNum_G1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_S1, sMsg.dsAcctNm_G1.getValue());
            }
            if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_R1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_R1, sMsg.dsAcctNum_G1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_R1, sMsg.dsAcctNm_G1.getValue());
            }
            // =================================================
            // Keep search result for back up
            // =================================================
            ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_BK, sMsg.rtlWhCatgCd_H1);
            ZYPEZDItemValueSetter.setValue(sMsg.invtyAcctCd_BK, sMsg.invtyAcctCd_H1);
            ZYPEZDItemValueSetter.setValue(sMsg.invtyOwnrCd_BK, sMsg.invtyOwnrCd_H1);
        } else {
            return ssmResult;
        }

        // =================================================
        // search Retail SWH
        // =================================================
        S21SsmEZDResult ssmResult2 = NMAL6820Query.getInstance().getRtlSwh(cMsg, sMsg);

        if (ssmResult2.isCodeNormal()) {

            EZDMsg.copy(sMsg, null, cMsg, null);
            ZYPTableUtil.clear(cMsg.A);

            int queryResCnt = ssmResult2.getQueryResultCount();

            int i = 0;
            int j = 0;
            for (; i < queryResCnt; i++) {

                if (j == cMsg.A.length()) {
                    break;
                }

                if (sMsg.rtlWhCatgCd_H1.getValue().equals(sMsg.A.no(i).rtlWhCatgCd_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhCd_A1, sMsg.A.no(i).rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhNm_A1, sMsg.A.no(i).rtlSwhNm_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhDescTxt_A1, sMsg.A.no(i).rtlSwhDescTxt_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_P1, sMsg.A.no(i).xxChkBox_P1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_D1, sMsg.A.no(i).xxChkBox_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_M1, sMsg.A.no(i).xxChkBox_M1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxScrItem8Txt_A1, sMsg.A.no(i).xxScrItem8Txt_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).procrTpCd_A1, sMsg.A.no(i).procrTpCd_A1);
                    // ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlWhCd_AS,
                    // sMsg.A.no(i).rtlWhCd_AS);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).prntVndNm_AS, sMsg.A.no(i).prntVndNm_AS);
                    // ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhCd_AS,
                    // sMsg.A.no(i).rtlSwhCd_AS);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).vndNm_AS, sMsg.A.no(i).vndNm_AS);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).procrTpCd_A2, sMsg.A.no(i).procrTpCd_A2);
                    // ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlWhCd_AR,
                    // sMsg.A.no(i).rtlWhCd_AR);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).prntVndNm_AR, sMsg.A.no(i).prntVndNm_AR);
                    // ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhCd_AR,
                    // sMsg.A.no(i).rtlSwhCd_AR);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).vndNm_AR, sMsg.A.no(i).vndNm_AR);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxModeCd_A1, sMsg.A.no(i).xxModeCd_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).invtyLocCd_A1, sMsg.A.no(i).invtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlWhCatgCd_A1, sMsg.A.no(i).rtlWhCatgCd_A1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effFromDt_S1, sMsg.A.no(i).effFromDt_S1);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effThruDt_S1, sMsg.A.no(i).effThruDt_S1);

                    j++;
                }
            }
            cMsg.A.setValidCount(j);
        }

        return ssmResult;
    }

    /**
     * The method explanation: The search processing of SWH WH
     * Category Relation information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     */
    public static void searchSwhWhCatgRel(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg, S21BusinessHandler handler) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getSwhWhCatgRel(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            int i = 0;
            for (; i < queryResCnt; i++) {

                if (i == sMsg.C.length()) {
                    break;
                }
            }
            sMsg.C.setValidCount(i);
        }
    }

    /**
     * master check
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkInputForSubmit(NMAL6820CMsg cMsg) {

        RTL_WH_CATGTMsg rtlWhCatgTMsg = new RTL_WH_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhCatgTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(rtlWhCatgTMsg.rtlWhCatgCd, cMsg.rtlWhCatgCd_H1);

        rtlWhCatgTMsg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(rtlWhCatgTMsg);

        if (rtlWhCatgTMsg == null) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_LOC_TP_CD });
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.locTpCd_H1, rtlWhCatgTMsg.locTpCd);
        }

        if (LOC_TP.TECHNICIAN.equals(cMsg.locTpCd_H1.getValue())) {

            if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd_H1.getValue())) {
                TECH_MSTRTMsg techMstr = new TECH_MSTRTMsg();
                techMstr.setConditionValue("techTocCd01", cMsg.whMgrPsnCd_H1.getValue());
                techMstr.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_G1.getValue());
                techMstr.setSQLID("001");

                TECH_MSTRTMsgArray techMstrAry = (TECH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(techMstr);

                if (techMstrAry.length() != 1) {

                    cMsg.whMgrPsnCd_H1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_TECH_TOC });
                    return false;
                }
            } else {
                cMsg.whMgrPsnCd_H1.setErrorInfo(1, NMAM0014E, new String[] {NAME_FOR_MESSAGE_TECH_TOC });
                return false;
            }
        }

        // existence check for Owner
        if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd_H1.getValue())) {

            S21_PSN_VTMsg whMgr = new S21_PSN_VTMsg();
            whMgr.setConditionValue("psnCd01", cMsg.whMgrPsnCd_H1.getValue());
            whMgr.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_G1.getValue());
            whMgr.setSQLID("001");

            S21_PSN_VTMsgArray whMgrAry = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(whMgr);

            if (whMgrAry.length() != 1) {

                cMsg.whMgrPsnCd_H1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_OWNER });
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.altPsnCd_H1.getValue())) {

            S21_PSN_VTMsg whMgr = new S21_PSN_VTMsg();
            whMgr.setConditionValue("psnCd01", cMsg.altPsnCd_H1.getValue());
            whMgr.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_G1.getValue());
            whMgr.setSQLID("001");

            S21_PSN_VTMsgArray altOwnrAry = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(whMgr);

            if (altOwnrAry.length() != 1) {

                cMsg.altPsnCd_H1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_ALT_OWNER });
                return false;
            }
        }

        // existence check for Branch
        if (ZYPCommonFunc.hasValue(cMsg.coaBrCd_H1.getValue())) {

            COA_BRTMsg coaBrTMsg = new COA_BRTMsg();
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.coaBrCd, cMsg.coaBrCd_H1);
            coaBrTMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(coaBrTMsg);

            if (coaBrTMsg == null) {

                cMsg.coaBrCd_H1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_BR });
                return false;
            }
        }

        // existence check for Ship to State
        if (ZYPCommonFunc.hasValue(cMsg.stCd_S1.getValue())) {

            STTMsg shipToSt = new STTMsg();
            ZYPEZDItemValueSetter.setValue(shipToSt.stCd, cMsg.stCd_S1);
            ZYPEZDItemValueSetter.setValue(shipToSt.glblCmpyCd, cMsg.glblCmpyCd_G1);

            shipToSt = (STTMsg) EZDTBLAccessor.findByKey(shipToSt);
            if (shipToSt == null) {
                cMsg.stCd_S1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_ST });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDR);
                return false;
            }
        }

        // existence check for Return to State
        if (ZYPCommonFunc.hasValue(cMsg.rtrnToStCd_R1.getValue())) {

            STTMsg rtrnToSt = new STTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnToSt.stCd, cMsg.rtrnToStCd_R1);
            ZYPEZDItemValueSetter.setValue(rtrnToSt.glblCmpyCd, cMsg.glblCmpyCd_G1);

            rtrnToSt = (STTMsg) EZDTBLAccessor.findByKey(rtrnToSt);
            if (rtrnToSt == null) {
                cMsg.rtrnToStCd_R1.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_ST });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDR);
                return false;
            }
        }

        // Set Ship-To Address Information
        if (ZYPCommonFunc.hasValue(cMsg.locNum_S1)) {
            if (!NMAL6820CommonLogic.setShipToCustInfo(cMsg)) {
                return false;
            }

        } else {
            cMsg.shipToCustCd_S1.clear();
        }

        // Set Return-To Address Information
        if (ZYPCommonFunc.hasValue(cMsg.locNum_R1)) {
            if (!NMAL6820CommonLogic.setRtrnToCustInfo(cMsg)) {
                return false;
            }
        } else {
            cMsg.rtrnToCustCd_R1.clear();
        }

        // digit num check for warehouse code
        RTL_WH_CATGTMsg whCatg = new RTL_WH_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(whCatg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(whCatg.rtlWhCatgCd, cMsg.rtlWhCatgCd_H1);

        whCatg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(whCatg);

        BigDecimal mstrDigitNum = whCatg.swhDigitNum.getValue();
        BigDecimal whCdDigitNum = BigDecimal.valueOf(cMsg.rtlWhCd_H1.getValue().length());

        if (whCdDigitNum.compareTo(mstrDigitNum) > 0) {

            cMsg.rtlWhCd_H1.setErrorInfo(1, NMAM0216E, new String[] {NAME_FOR_MESSAGE_RTL_WH_CD, mstrDigitNum.toString() });
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDR);
            return false;
        }

        return true;

    }

    /**
     * Check input for update mode
     * @param cMsg NMAL6820CMsg
     * @param sMsg Global area information
     * @return boolean
     */
    public static boolean isNoErrorForUpdateMode(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.whCd_H1)) {
            return true;
        }

        if (sMsg.rtlWhCatgCd_BK.getValue().equals(cMsg.rtlWhCatgCd_H1.getValue()) && sMsg.invtyAcctCd_BK.getValue().equals(cMsg.invtyAcctCd_H1.getValue()) && sMsg.invtyOwnrCd_BK.getValue().equals(cMsg.invtyOwnrCd_H1.getValue())) {
            return true;
        }

        // Check PR existence
        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getPR(cMsg.glblCmpyCd_G1.getValue(), cMsg.whCd_H1.getValue());
        if ((Integer) ssmResult.getResultObject() > 0) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyAcctCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyOwnrCd_H1.setErrorInfo(1, NMAM8314E);
            return false;
        }

        // Check PO existence
        ssmResult = NMAL6820Query.getInstance().getPO(cMsg.glblCmpyCd_G1.getValue(), cMsg.whCd_H1.getValue());
        if ((Integer) ssmResult.getResultObject() > 0) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyAcctCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyOwnrCd_H1.setErrorInfo(1, NMAM8314E);
            return false;
        }

        // Check RWS existence
        ssmResult = NMAL6820Query.getInstance().getRWS(cMsg.glblCmpyCd_G1.getValue(), cMsg.whCd_H1.getValue());
        if ((Integer) ssmResult.getResultObject() > 0) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyAcctCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyOwnrCd_H1.setErrorInfo(1, NMAM8314E);
            return false;
        }

        // Check Inventory existence
        ssmResult = NMAL6820Query.getInstance().getInvty(cMsg.glblCmpyCd_G1.getValue(), cMsg.whCd_H1.getValue());
        if ((Integer) ssmResult.getResultObject() > 0) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyAcctCd_H1.setErrorInfo(1, NMAM8314E);
            cMsg.invtyOwnrCd_H1.setErrorInfo(1, NMAM8314E);
            return false;
        }
        return true;
    }

    /**
     * Inventory Master Check
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkForInvtyMstr(NMAL6820CMsg cMsg) {

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(cMsg.A, RTL_SWH_DSBL_FLG_CHK_BOX_NAME_VALUE, ZYPConstant.FLG_ON_Y);

        for (int i = 0; i < chkYList.size(); i++) {

            int rowIndex = chkYList.get(i).intValue();

            S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getInvtyMstr(cMsg.glblCmpyCd_G1.getValue(), cMsg.A.no(rowIndex).invtyLocCd_A1.getValue());

            if ((Integer) ssmResult.getResultObject() > 0) {

                cMsg.A.no(rowIndex).xxChkBox_D1.setErrorInfo(1, NMAM0850E);
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
                return false;
            }
        }

        return true;
    }

    /**
     * check for wh existence
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkForWhExist(NMAL6820CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.whCd_H1)) {

            SHIP_TO_CUSTTMsgArray shipToCust = findShipToCustByCondition(cMsg.glblCmpyCd_G1.getValue(), cMsg.rtlWhCd_H1.getValue());

            if (shipToCust.length() != 0) {

                cMsg.rtlWhCd_H1.setErrorInfo(1, NMAM0010E, new String[] {NAME_FOR_MESSAGE_RTL_WH_CD });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
                return false;
            }

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {
                    break;
                }

                String ptyCd = cMsg.rtlWhCd_H1.getValue() + cMsg.A.no(i).rtlSwhCd_A1.getValue();

                PTYTMsgArray ptyTables = findPtyByCondition(cMsg.glblCmpyCd_G1.getValue(), ptyCd);

                if (ptyTables.length() != 0) {

                    cMsg.A.no(i).xxChkBox_P1.setErrorInfo(1, NMAM0010E, new String[] {NAME_FOR_MESSAGE_RTL_WH_CD });
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
                    return false;
                }

                if (isDuplicateInventoryLocation_Consignee(cMsg.glblCmpyCd_G1.getValue(), ptyCd)) {

                    cMsg.A.no(i).xxChkBox_P1.setErrorInfo(1, NMAM0010E, new String[] {NAME_FOR_MESSAGE_RTL_WH_CD });
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
                    return false;
                }

                shipToCust = findShipToCustByCondition(cMsg.glblCmpyCd_G1.getValue(), ptyCd);

                if (shipToCust.length() != 0) {

                    cMsg.A.no(i).xxChkBox_P1.setErrorInfo(1, NMAM0010E, new String[] {NAME_FOR_MESSAGE_RTL_WH_CD });
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * check for default Return-To existence
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkForDefaultRtrnToExist(NMAL6820CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.locNum_R1)) {
            String suffixForRtrnTo = getSuffixForRtrnTo(cMsg);
            if (!ZYPCommonFunc.hasValue(suffixForRtrnTo)) {
                cMsg.setMessageInfo(NMAM8387E);
                return false;
            }
            SHIP_TO_CUSTTMsgArray shipToCust = findShipToCustByCondition(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(cMsg.rtlWhCd_H1.getValue(), suffixForRtrnTo));
            if (shipToCust.length() != 0) {
                cMsg.locNum_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_RTRN_TO_CODE });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDR);
                return false;
            }
        }

        return true;
    }

    /**
     * checkForCarrierExist
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkForCarrierExist(NMAL6820CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.carrCd_H1)) {
            // Preferred Carrier Code is optional.
            return true;
        }

        String glblCmpyCd = cMsg.glblCmpyCd_G1.getValue();
        String carrCd = cMsg.carrCd_H1.getValue();

        OTBD_CARR_VTMsgArray carrTMsgArray = findOtbdCarrVByCondition(glblCmpyCd, carrCd);
        if (carrTMsgArray == null || carrTMsgArray.getValidCount() <= 0) {
            cMsg.carrCd_H1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_PRF_CARR_CD });
            return false;
        }

        return true;
    }

    /**
     * check for Sourcing Tab
     * @param cMsg NMAL6820CMsg
     * @param dataSecList List<S21DataSecurityAttributeData
     * @return boolean
     */
    public static boolean checkSourcingTab(NMAL6820CMsg cMsg, List<S21DataSecurityAttributeData> dataSecList) {
        boolean noErrorFlg = true;

        // Check Default Source
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_S1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SD) || ZYPCommonFunc.hasValue(cMsg.vndNm_SD)) {
                if (!NMAL6820CommonLogic.setSupplierCd(cMsg, cMsg.prntVndNm_SD, cMsg.vndNm_SD, cMsg.prntVndCd_SD, cMsg.vndCd_SD)) {
                    cMsg.prntVndNm_SD.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                    cMsg.vndNm_SD.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                    noErrorFlg = false;
                }
            } else {
                cMsg.prntVndCd_SD.clear();
                cMsg.vndCd_SD.clear();
            }
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_S1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SD) || ZYPCommonFunc.hasValue(cMsg.vndNm_SD)) {
                if (!NMAL6820CommonLogic.setRtlWhSwhCd(cMsg, cMsg.prntVndNm_SD, cMsg.vndNm_SD, cMsg.rtlWhCd_SD, cMsg.rtlSwhCd_SD)) {
                    cMsg.prntVndNm_SD.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                    cMsg.vndNm_SD.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                    noErrorFlg = false;
                } else {
                    NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(cMsg.rtlWhCd_SD.getValue(), cMsg.rtlSwhCd_SD.getValue()), BUSINESS_ID, dataSecList,
                            ZYPConstant.FLG_ON_Y);
                    if (!ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                        cMsg.prntVndNm_SD.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_SRC_WH });
                        cMsg.vndNm_SD.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_SRC_WH });
                        noErrorFlg = false;
                    }
                }
            } else {
                cMsg.rtlWhCd_SD.clear();
                cMsg.rtlSwhCd_SD.clear();
            }
        }

        // Check Emergency Source
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_E1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SE) || ZYPCommonFunc.hasValue(cMsg.vndNm_SE)) {
                if (!NMAL6820CommonLogic.setSupplierCd(cMsg, cMsg.prntVndNm_SE, cMsg.vndNm_SE, cMsg.prntVndCd_SE, cMsg.vndCd_SE)) {
                    cMsg.prntVndNm_SE.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC, MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC });
                    cMsg.vndNm_SE.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC, MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC });
                    noErrorFlg = false;
                }
            } else {
                cMsg.prntVndCd_SE.clear();
                cMsg.vndCd_SE.clear();
            }
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_E1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SE) || ZYPCommonFunc.hasValue(cMsg.vndNm_SE)) {
                if (!NMAL6820CommonLogic.setRtlWhSwhCd(cMsg, cMsg.prntVndNm_SE, cMsg.vndNm_SE, cMsg.rtlWhCd_SE, cMsg.rtlSwhCd_SE)) {
                    cMsg.prntVndNm_SE.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_EMERG_SRC });
                    cMsg.vndNm_SE.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_EMERG_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_EMERG_SRC });
                    noErrorFlg = false;
                } else {
                    NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(cMsg.rtlWhCd_SE.getValue(), cMsg.rtlSwhCd_SE.getValue()), BUSINESS_ID, dataSecList,
                            ZYPConstant.FLG_ON_Y);
                    if (!ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                        cMsg.prntVndNm_SE.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_EMRG_WH });
                        cMsg.vndNm_SE.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_EMRG_WH });
                        noErrorFlg = false;
                    }
                }
            } else {
                cMsg.rtlWhCd_SE.clear();
                cMsg.rtlSwhCd_SE.clear();
            }
        }

        // Check Return To
        if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_R1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SR) || ZYPCommonFunc.hasValue(cMsg.vndNm_SR)) {
                if (!NMAL6820CommonLogic.setSupplierCd(cMsg, cMsg.prntVndNm_SR, cMsg.vndNm_SR, cMsg.prntVndCd_SR, cMsg.vndCd_SR)) {
                    cMsg.prntVndNm_SR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                    cMsg.vndNm_SR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                    noErrorFlg = false;
                }
            } else {
                cMsg.rtlWhCd_SR.clear();
                cMsg.rtlSwhCd_SR.clear();
            }
        }
        if (PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_R1.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_SR) || ZYPCommonFunc.hasValue(cMsg.vndNm_SR)) {
                if (!NMAL6820CommonLogic.setRtlWhSwhCd(cMsg, cMsg.prntVndNm_SR, cMsg.vndNm_SR, cMsg.rtlWhCd_SR, cMsg.rtlSwhCd_SR)) {
                    cMsg.prntVndNm_SR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                    cMsg.vndNm_SR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                    noErrorFlg = false;
                } else {
                    NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(cMsg.rtlWhCd_SR.getValue(), cMsg.rtlSwhCd_SR.getValue()), BUSINESS_ID, dataSecList,
                            ZYPConstant.FLG_ON_Y);
                    if (!ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                        cMsg.prntVndNm_SR.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_RTRN_WH });
                        cMsg.vndNm_SR.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_RTRN_WH });
                        noErrorFlg = false;
                    }
                }
            } else {
                cMsg.rtlWhCd_SR.clear();
                cMsg.rtlSwhCd_SR.clear();
            }
        }

        if (!noErrorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SRC);
        }
        return noErrorFlg;
    }

    /**
     * check for SubWh Tab
     * @param cMsg NMAL6820CMsg
     * @param dataSecList List<S21DataSecurityAttributeData
     * @return boolean
     */
    public static boolean checkSubWhTab(NMAL6820CMsg cMsg, List<S21DataSecurityAttributeData> dataSecList) {
        boolean noErrorFlg = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NMAL6820_ACMsg row = cMsg.A.no(i);
            // Checks existence of Source WH/SWH
            if (PROCR_TP.SUPPLIER.equals(row.procrTpCd_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(row.prntVndNm_AS) || ZYPCommonFunc.hasValue(row.vndNm_AS)) {
                    if (!NMAL6820CommonLogic.setSupplierCd(cMsg, row.prntVndNm_AS, row.vndNm_AS, row.prntVndCd_AS, row.vndCd_AS)) {
                        row.prntVndNm_AS.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                        row.vndNm_AS.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                        noErrorFlg = false;
                    }
                } else {
                    row.prntVndCd_AS.clear();
                    row.vndCd_AS.clear();
                }
            }
            if (PROCR_TP.WAREHOUSE.equals(row.procrTpCd_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(row.prntVndNm_AS) || ZYPCommonFunc.hasValue(row.vndNm_AS)) {
                    if (!NMAL6820CommonLogic.setRtlWhSwhCd(cMsg, row.prntVndNm_AS, row.vndNm_AS, row.rtlWhCd_AS, row.rtlSwhCd_AS)) {
                        row.prntVndNm_AS.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                        row.vndNm_AS.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_SRC, MESSAGE_PARAM_RTL_SWH_NM_OF_SRC });
                        noErrorFlg = false;
                    } else {
                        NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(row.rtlWhCd_AS.getValue(), row.rtlSwhCd_AS.getValue()), BUSINESS_ID, dataSecList,
                                ZYPConstant.FLG_ON_Y);
                        if (!ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                            row.prntVndNm_AS.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_SRC_WH });
                            row.vndNm_AS.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_SRC_WH });
                            noErrorFlg = false;
                        }
                    }
                } else {
                    row.rtlWhCd_AS.clear();
                    row.rtlSwhCd_AS.clear();
                }
            }

            // Checks existence of Return To WH/SWH
            if (PROCR_TP.SUPPLIER.equals(row.procrTpCd_A2.getValue())) {
                if (ZYPCommonFunc.hasValue(row.prntVndNm_AR) || ZYPCommonFunc.hasValue(row.vndNm_AR)) {
                    if (!NMAL6820CommonLogic.setSupplierCd(cMsg, row.prntVndNm_AR, row.vndNm_AR, row.prntVndCd_AR, row.vndCd_AR)) {
                        row.prntVndNm_AR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                        row.vndNm_AR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                        noErrorFlg = false;
                    }
                } else {
                    row.prntVndCd_AR.clear();
                    row.vndCd_AR.clear();
                }
            }
            if (PROCR_TP.WAREHOUSE.equals(row.procrTpCd_A2.getValue())) {
                if (ZYPCommonFunc.hasValue(row.prntVndNm_AR) || ZYPCommonFunc.hasValue(row.vndNm_AR)) {
                    if (!NMAL6820CommonLogic.setRtlWhSwhCd(cMsg, row.prntVndNm_AR, row.vndNm_AR, row.rtlWhCd_AR, row.rtlSwhCd_AR)) {
                        row.prntVndNm_AR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                        row.vndNm_AR.setErrorInfo(1, NMAM0070E, new String[] {MESSAGE_PARAM_RTL_WH_NM_OF_RTRN, MESSAGE_PARAM_RTL_SWH_NM_OF_RTRN });
                        noErrorFlg = false;
                    } else {
                        NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(cMsg.glblCmpyCd_G1.getValue(), S21StringUtil.concatStrings(row.rtlWhCd_AR.getValue(), row.rtlSwhCd_AR.getValue()), BUSINESS_ID, dataSecList,
                                ZYPConstant.FLG_ON_Y);
                        if (!ZYPCommonFunc.hasValue(enableWHData.getInvtyLocNm())) {
                            row.prntVndNm_AR.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_RTRN_WH });
                            row.vndNm_AR.setErrorInfo(1, NMAM0039E, new String[] {NAME_FOR_MESSAGE_DEF_RTRN_WH });
                            noErrorFlg = false;
                        }
                    }
                } else {
                    row.rtlWhCd_AR.clear();
                    row.rtlSwhCd_AR.clear();
                }
            }
        }
        if (!noErrorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SWH);
        }
        return noErrorFlg;
    }

    /**
     * Check RTL_WH consistency.
     * @param cMsg NMAL6820CMsg
     * @return boolean
     */
    public static boolean checkForRtlWh(NMAL6820CMsg cMsg) {

        boolean valid = true;
        String glblCmpyCd = cMsg.glblCmpyCd_G1.getValue();
        String rtlWhCd = cMsg.rtlWhCd_H1.getValue();
        String invtyOwnrCd = cMsg.invtyOwnrCd_H1.getValue();
        String wmsWhCd = cMsg.wmsWhCd_H1.getValue();
        String physWhCd = cMsg.physWhCd_H1.getValue();
        String choiceWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL6820_CHOICE_WH_OWNR_CD, glblCmpyCd);    // 2019/04/09 T.Ogura [QC#28667,ADD]

        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        if (ZYPCommonFunc.hasValue(wmsWhCd)) {
        // START 2020/12/02 H.Dimay [QC#57659,MOD]
//        if (ZYPCommonFunc.hasValue(wmsWhCd) && !S21StringUtil.isEquals(choiceWhOwnrCd, cMsg.whOwnrCd_H1.getValue())) {
        // END   2019/04/09 T.Ogura [QC#28667,MOD]
        if (ZYPCommonFunc.hasValue(wmsWhCd) && !isOwnerCodeValid(choiceWhOwnrCd, cMsg.whOwnrCd_H1.getValue())) {
        // END 2020/12/02 H.Dimay [QC#57659,MOD]
            // Constraint: WMS_WH_CD must be unique in each INVTY_OWNR_CD.
            S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().countRtlWhByWmsWhCd(glblCmpyCd, rtlWhCd, invtyOwnrCd, wmsWhCd);

            if (ssmResult.isCodeNormal()) {
                int count = (Integer) ssmResult.getResultObject();
                if (count > 0) {
                    cMsg.wmsWhCd_H1.setErrorInfo(1, NMAM8625E, new String[] {NAME_FOR_MESSAGE_WMS_WH_CD, NAME_FOR_MESSAGE_INVTY_OWNR });
                    valid = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(physWhCd)) {
            // Constraint: PHYS_WH_CD must be unique in each INVTY_OWNR_CD.
            S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().countRtlWhByPhysWhCd(glblCmpyCd, rtlWhCd, invtyOwnrCd, physWhCd);

            if (ssmResult.isCodeNormal()) {
                int count = (Integer) ssmResult.getResultObject();
                if (count > 0) {
                    cMsg.physWhCd_H1.setErrorInfo(1, NMAM8625E, new String[] {NAME_FOR_MESSAGE_WH_GROUP_NAME, NAME_FOR_MESSAGE_INVTY_OWNR });
                    valid = false;
                }
            }
        }

        return valid;
    }

    /**
     * @param globalCompanyCode String
     * @param ptyCd String
     * @return PTYTMsgArray
     */
    private static PTYTMsgArray findPtyByCondition(String globalCompanyCode, String ptyCd) {

        PTYTMsg pty = new PTYTMsg();

        pty.setSQLID("005");
        pty.setConditionValue("glblCmpyCd01", globalCompanyCode);
        pty.setConditionValue("ptyCd01", ptyCd);

        return (PTYTMsgArray) EZDTBLAccessor.findByCondition(pty);
    }

    /**
     * @param globalCompanyCode String
     * @param ptyCd String
     * @return PTYTMsgArray
     */
    private static SHIP_TO_CUSTTMsgArray findShipToCustByCondition(String globalCompanyCode, String ptyCd) {

        SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();

        shipToCust.setSQLID("004");
        shipToCust.setConditionValue("glblCmpyCd01", globalCompanyCode);
        shipToCust.setConditionValue("shipToCustCd01", ptyCd);

        return (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCust);
    }

    /**
     * findOtbdCarrVByCondition
     * @param glblCmpyCd String
     * @param carrCd String
     * @return OTBD_CARR_VTMsgArray
     */
    private static OTBD_CARR_VTMsgArray findOtbdCarrVByCondition(String glblCmpyCd, String carrCd) {
        OTBD_CARR_VTMsg carrTMsg = new OTBD_CARR_VTMsg();

        carrTMsg.setSQLID("004");
        carrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        carrTMsg.setConditionValue("carrCd01", carrCd);

        return (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(carrTMsg);
    }

    /**
     * @param globalCompanyCode String
     * @param ptyCd String
     * @return PTYTMsgArray
     */
    private static boolean isDuplicateInventoryLocation_Consignee(String globalCompanyCode, String ptyCd) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getImportInvoiceConsigneeByPartyCode(globalCompanyCode, ptyCd);

        if (ssmResult.isCodeNormal()) {
            return true;
        }

        return false;
    }

    /**
     * The method explanation: The search processing of OpenWin Tech
     * Category Relation information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     */
    public static void searchTechInfo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg, S21BusinessHandler handler) {

        S21_PSNTMsg s21Psn = new S21_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(s21Psn.psnCd, cMsg.whMgrPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(s21Psn.glblCmpyCd, cMsg.glblCmpyCd_G1);

        s21Psn = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21Psn);

        if (s21Psn != null) {

            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_S1, s21Psn.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_S1, s21Psn.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddr_S1, s21Psn.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddr_S1, s21Psn.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, s21Psn.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_S1, s21Psn.cntyPk);

            if (s21Psn.cntyPk.getValue() != null) {
                S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getCntyNm(cMsg.glblCmpyCd_G1.getValue(), s21Psn.cntyPk.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, (String) ssmResult.getResultObject());
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, "");
            }
            ZYPEZDItemValueSetter.setValue(cMsg.provNm_S1, s21Psn.provNm);
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, s21Psn.stCd);
            ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, s21Psn.postCd);
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_S1, s21Psn.ctryCd);
        }
    }

    /**
     * The method explanation: The search processing of OpenWin Tech
     * Category Relation information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     */
    public static void getSwhWhCatgRel(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg, S21BusinessHandler handler) {

        // set to SMsg for backup
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {

                if (cMsg.A.no(i).rtlSwhCd_A1.getValue().equals(sMsg.C.no(j).rtlSwhCd_C1.getValue()) && cMsg.A.no(i).rtlWhCatgCd_A1.getValue().equals(sMsg.C.no(j).rtlWhCatgCd_C1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).xxModeCd_C1, cMsg.A.no(i).xxModeCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).xxChkBox_CP, cMsg.A.no(i).xxChkBox_P1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).xxChkBox_CD, cMsg.A.no(i).xxChkBox_D1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).xxChkBox_CM, cMsg.A.no(i).xxChkBox_M1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).xxScrItem8Txt_C1, cMsg.A.no(i).xxScrItem8Txt_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).procrTpCd_C1, cMsg.A.no(i).procrTpCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).prntVndNm_CS, cMsg.A.no(i).prntVndNm_AS);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).vndNm_CS, cMsg.A.no(i).vndNm_AS);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).procrTpCd_C2, cMsg.A.no(i).procrTpCd_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).prntVndNm_CR, cMsg.A.no(i).prntVndNm_AR);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).vndNm_CR, cMsg.A.no(i).vndNm_AR);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).rtlSwhMndFlg_C1, cMsg.A.no(i).rtlSwhMndFlg_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).invtyLocCd_C1, cMsg.A.no(i).invtyLocCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).effFromDt_C1, cMsg.A.no(i).effFromDt_S1);
                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).effThruDt_C1, cMsg.A.no(i).effThruDt_S1);
                }
            }
        }

        ZYPTableUtil.clear(cMsg.A);

        // get SWH from SMsg
        int j = 0;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            if (j == cMsg.A.length()) {
                break;
            }

            if (cMsg.rtlWhCatgCd_H1.getValue().equals(sMsg.C.no(i).rtlWhCatgCd_C1.getValue())) {

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxModeCd_A1, sMsg.C.no(i).xxModeCd_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhCd_A1, sMsg.C.no(i).rtlSwhCd_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhNm_A1, sMsg.C.no(i).rtlSwhNm_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhDescTxt_A1, sMsg.C.no(i).rtlSwhDescTxt_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_P1, sMsg.C.no(i).xxChkBox_CP);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_D1, sMsg.C.no(i).xxChkBox_CD);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxChkBox_M1, sMsg.C.no(i).xxChkBox_CM);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).xxScrItem8Txt_A1, sMsg.C.no(i).xxScrItem8Txt_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).procrTpCd_A1, sMsg.C.no(i).procrTpCd_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).prntVndNm_AS, sMsg.C.no(i).prntVndNm_CS);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).vndNm_AS, sMsg.C.no(i).vndNm_CS);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).procrTpCd_A2, sMsg.C.no(i).procrTpCd_C2);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).prntVndNm_AR, sMsg.C.no(i).prntVndNm_CR);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).vndNm_AR, sMsg.C.no(i).vndNm_CR);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlSwhMndFlg_A1, sMsg.C.no(i).rtlSwhMndFlg_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).invtyLocCd_A1, sMsg.C.no(i).invtyLocCd_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).rtlWhCatgCd_A1, sMsg.C.no(i).rtlWhCatgCd_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effFromDt_S1, sMsg.C.no(i).effFromDt_C1);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effThruDt_S1, sMsg.C.no(i).effThruDt_C1);
                j++;
            }
        }
        cMsg.A.setValidCount(j);
    }

    /**
     * Set Yes/No Pulldown List
     * @param cMsg NMAL6820CMsg
     */
    public static void setYesNoPulldownList(NMAL6820CMsg cMsg) {
        cMsg.xxTpCd_L1.clear();
        cMsg.xxTpNm_L1.clear();
        for (int idx = 0; idx < YES_NO_PULLDOWN_CD.length; idx++) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_L1.no(idx), YES_NO_PULLDOWN_CD[idx]);
            ZYPEZDItemValueSetter.setValue(cMsg.xxTpNm_L1.no(idx), YES_NO_PULLDOWN_NM[idx]);
        }

        // START 2020/09/01 [QC#57602, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {
        // END 2020/09/01 [QC#57602, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.autoSoDropFlg_H1, ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * <p>
     * set Retail WH/SWH Code based on the WH/SWH Name
     * </p>
     * @param cMsg NMAL6820CMsg
     * @param rtlWhNm WH Name
     * @param rtlSwhNm Sub WH Name
     * @param rtlWhCd WH Code
     * @param rtlSwhCd Sub WH Code
     * @return if the value are exists, return true.
     */
    public static boolean setRtlWhSwhCd(NMAL6820CMsg cMsg, EZDCStringItem rtlWhNm, EZDCStringItem rtlSwhNm, EZDCStringItem rtlWhCd, EZDCStringItem rtlSwhCd) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().searchWhSwh(cMsg, rtlWhNm, rtlSwhNm);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList != null && resultList.size() > 0) {
                ZYPEZDItemValueSetter.setValue(rtlWhCd, (String) resultList.get(0).get(RTL_WH_CD_DBCOLUMN));
                ZYPEZDItemValueSetter.setValue(rtlSwhCd, (String) resultList.get(0).get(RTL_SWH_CD_DBCOLUMN));
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * set Supplier/Supplier Site Code based on the Supplier/Supplier
     * Site Name
     * </p>
     * @param cMsg NMAL6820CMsg
     * @param prntVndNm Supplier Name
     * @param vndNm Supplier Site Name
     * @param prntVndCd Supplier Code
     * @param vndCd Supplier Site Code
     * @return if the value are exists, return true.
     */
    public static boolean setSupplierCd(NMAL6820CMsg cMsg, EZDCStringItem prntVndNm, EZDCStringItem vndNm, EZDCStringItem prntVndCd, EZDCStringItem vndCd) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().searchSupplier(cMsg, prntVndNm, vndNm);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList != null && resultList.size() > 0) {
                ZYPEZDItemValueSetter.setValue(prntVndCd, (String) resultList.get(0).get(PRNT_VND_CD_DBCOLUMN));
                ZYPEZDItemValueSetter.setValue(vndCd, (String) resultList.get(0).get(VND_CD_DBCOLUMN));
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * set default DS Account# and DS Account Name
     * </p>
     * @param cMsg NMAL6820CMsg
     * @param sMsg Global area information
     * @return if the value are exists, return true.
     */
    public static boolean getDefDsAcctInfo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getDefDsAcctInfo(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList != null && resultList.size() > 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.dsAcctNum_G1, (String) resultList.get(0).get(DS_ACCT_NUM_DBCOLUMN));
                ZYPEZDItemValueSetter.setValue(sMsg.dsAcctNm_G1, (String) resultList.get(0).get(DS_ACCT_NM_DBCOLUMN));
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * set Ship-To Information
     * </p>
     * @param cMsg NMAL6820CMsg
     * @return if the value are exists, return true.
     */
    public static boolean setShipToCustInfo(NMAL6820CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getShipToCustInfo(cMsg.glblCmpyCd_G1.getValue(), cMsg.locNum_S1.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList != null && resultList.size() > 0) {
                if (ZYPConstant.FLG_OFF_N.equals((String) resultList.get(0).get("WH_DEF_CUST_EXIST_FLG"))) {
                    cMsg.locNum_S1.setErrorInfo(1, NMAM8384E, new String[] {NAME_FOR_MESSAGE_SHIP_TO_CODE, NAME_FOR_MESSAGE_SHIP_TO_CODE });
                    cMsg.xxSetFlg_S1.clear();
                    cMsg.shipToCustCd_S1.clear();
                    return false;

                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg_S1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_S1, (String) resultList.get(0).get("SHIP_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_S1, (String) resultList.get(0).get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_S1, (String) resultList.get(0).get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsLocNm_S1, (String) resultList.get(0).get("DS_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.addlLocNm_S1, (String) resultList.get(0).get("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_S1, (String) resultList.get(0).get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_S1, (String) resultList.get(0).get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddr_S1, (String) resultList.get(0).get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddr_S1, (String) resultList.get(0).get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, (String) resultList.get(0).get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_S1, (BigDecimal) resultList.get(0).get("CNTY_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, (String) resultList.get(0).get("CNTY_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, (String) resultList.get(0).get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.provNm_S1, (String) resultList.get(0).get("PROV_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, (String) resultList.get(0).get("POST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_S1, (String) resultList.get(0).get("CTRY_CD"));

                    return true;
                }
            }
        }
        cMsg.locNum_S1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_SHIP_TO_CODE });
        cMsg.shipToCustCd_S1.clear();
        return false;
    }

    /**
     * <p>
     * set Return-To Information
     * </p>
     * @param cMsg NMAL6820CMsg
     * @return if the value are exists, return true.
     */
    public static boolean setRtrnToCustInfo(NMAL6820CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getShipToCustInfo(cMsg.glblCmpyCd_G1.getValue(), cMsg.locNum_R1.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList != null && resultList.size() > 0) {
                if (ZYPConstant.FLG_OFF_N.equals((String) resultList.get(0).get("WH_DEF_CUST_EXIST_FLG"))) {
                    cMsg.locNum_R1.setErrorInfo(1, NMAM8384E, new String[] {NAME_FOR_MESSAGE_RTRN_TO_CODE, NAME_FOR_MESSAGE_RTRN_TO_CODE });
                    cMsg.xxSetFlg_R1.clear();
                    cMsg.rtrnToCustCd_R1.clear();
                    return false;

                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg_R1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCustCd_R1, (String) resultList.get(0).get("SHIP_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_R1, (String) resultList.get(0).get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_R1, (String) resultList.get(0).get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.dsLocNm_R1, (String) resultList.get(0).get("DS_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToAddlLocNm_R1, (String) resultList.get(0).get("ADDL_LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToFirstLineAddr_R1, (String) resultList.get(0).get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToScdLineAddr_R1, (String) resultList.get(0).get("SCD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToThirdLineAddr_R1, (String) resultList.get(0).get("THIRD_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToFrthLineAddr_R1, (String) resultList.get(0).get("FRTH_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCtyAddr_R1, (String) resultList.get(0).get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_R1, (BigDecimal) resultList.get(0).get("CNTY_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_R1, (String) resultList.get(0).get("CNTY_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToStCd_R1, (String) resultList.get(0).get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToProvNm_R1, (String) resultList.get(0).get("PROV_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.rtrnToPostCd_R1, (String) resultList.get(0).get("POST_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_R1, (String) resultList.get(0).get("CTRY_CD"));
                    return true;
                }
            }
        }
        cMsg.locNum_R1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_RTRN_TO_CODE });
        cMsg.rtrnToCustCd_R1.clear();
        return false;
    }

    /**
     * <p>
     * Sets the message information to the CMsg.
     * </p>
     * @param cMsg bizMsg.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    public static void setMessageInfo(EZDCMsg cMsg, String messageCd, Object... messageParams) {
        cMsg.setMessageInfo(messageCd, toStringArray(messageParams));
    }

    /**
     * <p>
     * Converts the array of {@link Object} to the array of
     * {@link String}.
     * </p>
     * @param objects the array of Object.
     * @return the array of String.
     */
    private static String[] toStringArray(Object[] objects) {
        if (objects == null) {
            return null;
        }
        String[] params = new String[objects.length];
        for (int index = 0; index < objects.length; index++) {
            if (objects[index] == null) {
                params[index] = "";
            } else {
                params[index] = objects[index].toString();
            }
        }
        return params;
    }

    /**
     * <pre>
     * Get Suffix for Return-To Code
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @return String
     */
    public static String getSuffixForRtrnTo(NMAL6820CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getSuffixForRtrnTo(cMsg);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }

    /**
     * <pre>
     * Get Ship-To Cust Code by Loc Num
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param locNum locNum
     * @return String
     */
    public static String getShipToCustCdByLocNum(String glblCmpyCd, String locNum) {

        S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getShipToCustCdByLocNum(glblCmpyCd, locNum);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }

    /**
     * check Address values using API.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg cMsg
     * @param sMsg sMsg
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean checkAddress(String glblCmpyCd, NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        boolean rtrnCd = true;

        String addrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(NMAL6820_ADDR_CHK_FLG, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(addrChkFlg) //
                && ZYPConstant.FLG_OFF_N.equals(addrChkFlg)) {
            // Unnecessary address check.
            return rtrnCd;
        }

        // Check Address
        if (!callAddrValidApiShipTo(cMsg, glblCmpyCd)) {
            rtrnCd = false;
            // return rtrnCd;
        } else {
            // Set sMsg.
            ZYPEZDItemValueSetter.setValue(sMsg.firstLineAddr_S1, cMsg.firstLineAddr_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.scdLineAddr_S1, cMsg.scdLineAddr_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.ctyAddr_S1, cMsg.ctyAddr_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.stCd_S1, cMsg.stCd_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.postCd_S1, cMsg.postCd_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.cntyNm_S1, cMsg.cntyNm_S1);
            ZYPEZDItemValueSetter.setValue(sMsg.cntyPk_S1, cMsg.cntyPk_S1);
        }
        if (!callAddrValidApiReturnTo(cMsg, glblCmpyCd)) {
            rtrnCd = false;
        } else {
            // Set sMsg.
            ZYPEZDItemValueSetter.setValue(sMsg.rtrnToFirstLineAddr_R1, cMsg.rtrnToFirstLineAddr_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.rtrnToScdLineAddr_R1, cMsg.rtrnToScdLineAddr_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.rtrnToCtyAddr_R1, cMsg.rtrnToCtyAddr_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.rtrnToStCd_R1, cMsg.rtrnToStCd_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.rtrnToPostCd_R1, cMsg.rtrnToPostCd_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.cntyNm_R1, cMsg.cntyNm_R1);
            ZYPEZDItemValueSetter.setValue(sMsg.cntyPk_R1, cMsg.cntyPk_R1);
        }

        return rtrnCd;
    }

    /**
     * <pre>
     * callAddrValidApiShipTo
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @param glblCmpyCd String
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean callAddrValidApiShipTo(NMAL6820CMsg cMsg, String glblCmpyCd) {
        boolean rtrnCd = true;

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, cMsg.firstLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, cMsg.scdLineAddr_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, cMsg.ctyAddr_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, cMsg.stCd_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, cMsg.postCd_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, cMsg.ctryCd_S1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, cMsg.cntyNm_S1);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        // check Error
        // Address Line
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
            cMsg.firstLineAddr_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Address Line 1" });
            rtrnCd = false;
        }
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            cMsg.scdLineAddr_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Address Line 2" });
            rtrnCd = false;
        }
        // City
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            cMsg.ctyAddr_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location City" });
            rtrnCd = false;
        }
        // State
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            cMsg.stCd_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location State" });
            rtrnCd = false;
        }
        // Postal code
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            cMsg.postCd_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Postal Code" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            cMsg.ctryCd_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location Country" });
            rtrnCd = false;
        }
        // Cnty
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            cMsg.cntyNm_S1.setErrorInfo(1, NMAM8454E, new String[] {"Ship to Location County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {
                setAddrValidResult((String) msgIdList.get(0), cMsg.firstLineAddr_S1, addrValidApiPMsg.xxVldStsCd_01);
                setAddrValidResult((String) msgIdList.get(0), cMsg.scdLineAddr_S1, addrValidApiPMsg.xxVldStsCd_02);
                setAddrValidResult((String) msgIdList.get(0), cMsg.ctyAddr_S1, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(0), cMsg.stCd_S1, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(0), cMsg.postCd_S1, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(0), cMsg.ctryCd_S1, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(0), cMsg.cntyNm_S1, addrValidApiPMsg.xxVldStsCd_07);
                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_S1, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_S1, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, addrValidApiPMsg.stCd);
            ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, addrValidApiPMsg.postCd);
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_S1, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, addrValidApiPMsg.cntyNm);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_S1, addrValidApiPMsg.cntyPk);

        }
        return rtrnCd;
    }

    /**
     * <pre>
     * callAddrValidApiReturnTo
     * </pre>
     * @param cMsg NMAL6820CMsg
     * @param glblCmpyCd String
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean callAddrValidApiReturnTo(NMAL6820CMsg cMsg, String glblCmpyCd) {
        boolean rtrnCd = true;

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, cMsg.rtrnToFirstLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, cMsg.rtrnToScdLineAddr_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, cMsg.rtrnToCtyAddr_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, cMsg.rtrnToStCd_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, cMsg.rtrnToPostCd_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, cMsg.ctryCd_R1);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, cMsg.cntyNm_R1);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        // check Error
        // Address Line
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
            cMsg.rtrnToFirstLineAddr_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location Address Line 1" });
            rtrnCd = false;
        }
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            cMsg.rtrnToScdLineAddr_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location Address Line 2" });
            rtrnCd = false;
        }
        // City
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            cMsg.rtrnToCtyAddr_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location City" });
            rtrnCd = false;
        }
        // State
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            cMsg.rtrnToStCd_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location State" });
            rtrnCd = false;
        }
        // Postal code
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            cMsg.rtrnToPostCd_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location Postal Code" });
            rtrnCd = false;
        }
        // Ctry
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            cMsg.ctryCd_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location Country" });
            rtrnCd = false;
        }
        // Cnty
        if (NMZC0030_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            cMsg.cntyNm_R1.setErrorInfo(1, NMAM8454E, new String[] {"Return to Location County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {
                setAddrValidResult((String) msgIdList.get(0), cMsg.rtrnToFirstLineAddr_R1, addrValidApiPMsg.xxVldStsCd_01);
                setAddrValidResult((String) msgIdList.get(0), cMsg.rtrnToScdLineAddr_R1, addrValidApiPMsg.xxVldStsCd_02);
                setAddrValidResult((String) msgIdList.get(0), cMsg.rtrnToCtyAddr_R1, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(0), cMsg.rtrnToStCd_R1, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(0), cMsg.rtrnToPostCd_R1, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(0), cMsg.ctryCd_R1, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(0), cMsg.cntyNm_R1, addrValidApiPMsg.xxVldStsCd_07);
                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnToFirstLineAddr_R1, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnToScdLineAddr_R1, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCtyAddr_R1, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnToStCd_R1, addrValidApiPMsg.stCd);
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnToPostCd_R1, addrValidApiPMsg.postCd);
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_R1, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_R1, addrValidApiPMsg.cntyNm);
            ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_R1, addrValidApiPMsg.cntyPk);

        }
        return rtrnCd;
    }

    /**
     * <pre>
     * setAddrValidResult
     * </pre>
     * @param msgId String
     * @param checkItem EZDCStringItem
     * @param rtnStsCd EZDPStringItem
     */
    public static void setAddrValidResult(String msgId, EZDCStringItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    /**
     * <pre>
     * setAddrValidResult
     * </pre>
     * @param msgId String
     * @param checkItem EZDCBigDecimalItem
     * @param rtnStsCd EZDPStringItem
     */
    public static void setAddrValidResult(String msgId, EZDCBigDecimalItem checkItem, EZDPStringItem rtnStsCd) {
        if (NMZC0030_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    /**
     * checkExistsGeoCodeByVertex
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL6820SMsg
     * @return boolean
     */
    public static boolean checkExistsGeoCodeByVertex(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        // 2016/10/14 CSA-QC#4096 Add Start
        if (!ZYPCommonFunc.hasValue(cMsg.geoCd_H1)) {
            return true;
        }

        if (cMsg.geoCd_H1.getValue().equals(sMsg.geoCd_BK.getValue())) {
            return true;
        }

        NMXC107001PMsg pMsg = new NMXC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMXC107001.CHECK_EXISTS_GEO_CODE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd, cMsg.geoCd_H1);

        // Get Geo Code
        NMXC107001 api = new NMXC107001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            String msgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            cMsg.setMessageInfo(msgId);
            return false;
        }

        return true;
        // 2016/10/14 CSA-QC#4096 Add End
    }
    /**
     * getSupplierName
     * @param bizMsg
     * @param glblMsg
     */
    public static void getSupplierName(NMAL6820CMsg bizMsg, NMAL6820SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.vndCd)) {
            S21SsmEZDResult result = NMAL6820Query.getInstance().getVendorInfo(bizMsg);

            if (result.isCodeNormal()) {
                List<Map> vndInfo = (List<Map>) result.getResultObject();
                // Set First Record Data
                if (0 < vndInfo.size()) {
                    Map recode = vndInfo.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) recode.get("PRNT_VND_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) recode.get("PRNT_VND_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndCd, (String) recode.get("VND_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndNm, (String) recode.get("LOC_NM"));
                }
            } else {
                bizMsg.vndCd.setErrorInfo(1, "NPAM0076E", new String[] {"Supplier Site" });
            }
        }
    }
    // START 2020/12/02 H.Dimay [QC#57659,ADD]
    private static boolean isOwnerCodeValid(String validOwnerCodeList, String ownerCode) {
        boolean valid = false;

        if (ZYPCommonFunc.hasValue(validOwnerCodeList)) {
            String[] arrayOwnerCode = validOwnerCodeList.split(",", 0);
            for (int i = 0; i < arrayOwnerCode.length; i++) {
                if (S21StringUtil.isEquals(arrayOwnerCode[i], ownerCode)) {
                    valid = true;
                    break;
                }
            }
        }

        return valid;
    }
    // END 2020/12/02 H.Dimay [QC#57659,ADD]
}
