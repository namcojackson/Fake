/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL1040.common;

import static business.blap.NLCL1040.constant.NLCL1040Constant.ABC_ANLS_RQST_SRC_TP_ANLZ;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ABC_ANLS_RQST_STS_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ABC_ASG_PK;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_LIMIT_EFF_THRU_DT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_MDSE_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ROWNUM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_RTL_SWH_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_RTL_WH_CATG_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_RTL_WH_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_SALES_DATE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_SMSG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_STK_STS_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_SWH_CD_LIST;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_WH_CD_LIST;
import static business.blap.NLCL1040.constant.NLCL1040Constant.CODE_SEARCH_SIZE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_SUBMIT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_DELETE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_ITEMSEARCH;
import static business.blap.NLCL1040.constant.NLCL1040Constant.LIMIT_DATE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLAM0173E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLAM1294E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLAM1310W;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0125E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0131E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0185E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0193E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0194E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0195E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0196I;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0197E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0198E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0205E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0206E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLGM0059E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NMAM0038I;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NMAM8509E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NPAM0076E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NPAM1297E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NPAM1361E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NZZM0000E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NZZM0001W;
import static business.blap.NLCL1040.constant.NLCL1040Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL1040.NLCL1040CMsg;
import business.blap.NLCL1040.NLCL1040Query;
import business.blap.NLCL1040.NLCL1040SMsg;
import business.blap.NLCL1040.constant.NLCL1040Constant;
import business.db.ABC_ANLS_RQSTTMsg;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.ABC_ANLS_RSLT_DTLTMsg;
import business.db.ABC_ASGTMsg;
import business.db.ABC_ASG_STK_STSTMsg;
import business.db.ABC_ASG_SWHTMsg;
import business.db.ABC_ASG_WHTMsg;
import business.db.STK_STSTMsg;
import business.db.STK_STSTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ABC_ANLS_RQST_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040CommonLogic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 * 12/27/2016   CITS            Y.Fujii         Update          QC#14064
 * 11/26/2018   Fujitsu         T.Ogura         Update          QC#29124
 *</pre>
 */
public class NLCL1040CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createInitHeader
     * @param cMsg NLCL1040CMsg
     * @param srchOptUsrId String
     */
    public static void createInitHeader(NLCL1040CMsg cMsg, String srchOptUsrId) {

        // Create Pull-Down Header
        createRtlWhCatgPullDown(cMsg);
        createMdseItemTpPullDown(cMsg);
        createAnalysisCriteriaPullDown(cMsg);
        createAbcClassNamePullDown(cMsg);

        // Check Box Table
        createStkStsChkBox(cMsg);

    }

    /**
     * createRtlWhCatgPullDown
     * @param cMsg NLCL1040CMsg
     */
    private static void createRtlWhCatgPullDown(NLCL1040CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_L1, cMsg.rtlWhCatgDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_L2, cMsg.rtlWhCatgDescTxt_L2);

    }

    /**
     * createMdseItemTpPullDown
     * @param cMsg NLCL1040CMsg
     */
    private static void createMdseItemTpPullDown(NLCL1040CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, cMsg.mdseItemTpCd_L1, cMsg.mdseItemTpDescTxt_L1);

    }

    /**
     * createAnalysisCriteriaPullDown
     * @param cMsg NLCL1040CMsg
     */
    private static void createAnalysisCriteriaPullDown(NLCL1040CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        S21SsmEZDResult ssmResult = NLCL1040Query.getInstance().getPullDownList(params, "geAnalysisCriteriaList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> rtlWhCatgList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (rtlWhCatgList != null && !rtlWhCatgList.isEmpty()) {

                for (int i = 0; i < rtlWhCatgList.size(); i++) {

                    Map<String, Object> rtlWhCatgMap = (Map<String, Object>) rtlWhCatgList.get(i);

                    if (i >= cMsg.rtlWhCatgCd_L1.length()) {

                        break;
                    }

                    if (i == 0) {
                        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritCd_H1, (String) rtlWhCatgMap.get("ABC_ANLS_CRIT_CD"));
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritCd_L1.no(i), (String) rtlWhCatgMap.get("ABC_ANLS_CRIT_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritDescTxt_L1.no(i), (String) rtlWhCatgMap.get("ABC_ANLS_CRIT_DESC_TXT"));

                }

                return;
            }
        }

        cMsg.setMessageInfo(NLCL1040Constant.NLCM0170E, new String[] {"Warehouse Type Master" });
        return;

    }

    /**
     * createAbcClassNamePullDown
     * @param cMsg NLCL1040CMsg
     */
    private static void createAbcClassNamePullDown(NLCL1040CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        S21SsmEZDResult ssmResult = NLCL1040Query.getInstance().getPullDownList(params, "getAbcClassNameList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> abcClassNameList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (abcClassNameList != null && !abcClassNameList.isEmpty()) {

                for (int i = 0; i < abcClassNameList.size(); i++) {

                    Map<String, Object> rtlWhCatgMap = (Map<String, Object>) abcClassNameList.get(i);

                    if (i >= cMsg.abcAnlsClsNm_L1.length()) {

                        break;
                    }

                    if (i == 0) {
                        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_H1, (String) rtlWhCatgMap.get("ABC_ANLS_CLS_NUM"));
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_L1.no(i), (String) rtlWhCatgMap.get("ABC_ANLS_CLS_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm_L1.no(i), (String) rtlWhCatgMap.get("ABC_ANLS_CLS_NM"));
                }

                return;
            }
        }

        cMsg.setMessageInfo(NLCL1040Constant.NLCM0170E, new String[] {"ABC Class Master"});
        return;

    }

    /**
     * createStkStsChkBox
     * @param cMsg NLCL1040CMsg
     */
    private static void createStkStsChkBox(NLCL1040CMsg cMsg) {

        STK_STSTMsg stkStsTMsg = new STK_STSTMsg();
        stkStsTMsg.setSQLID("001");
        stkStsTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        STK_STSTMsgArray stkStsResult = (STK_STSTMsgArray) EZDTBLAccessor.findByCondition(stkStsTMsg);

        if (stkStsResult != null && stkStsResult.getValidCount() != 0) {

            StringBuffer chkBoxNm = null;
            int validCount = stkStsResult.getValidCount();

            for (int i = 0; i < stkStsResult.getValidCount(); i++) {

                if (i >= cMsg.S.length()) {

                    validCount = i;
                    break;
                }

                chkBoxNm = new StringBuffer();
                chkBoxNm.append(stkStsResult.no(i).stkStsCd.getValue());
                chkBoxNm.append(":");
                chkBoxNm.append(stkStsResult.no(i).stkStsDescTxt.getValue());

                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxScrItem61Txt_SS, chkBoxNm.toString());
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).stkStsCd_SS, stkStsResult.no(i).stkStsCd);
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.CHKBOX_ON_Y);

                ZYPEZDItemValueSetter.setValue(cMsg.stkStsCd_L2.no(i), stkStsResult.no(i).stkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.stkStsDescTxt_L2.no(i), stkStsResult.no(i).stkStsDescTxt.getValue());
            }

            cMsg.S.setValidCount(validCount);

        } else {

            cMsg.setMessageInfo(NLCL1040Constant.NLZM0024E);
        }
        return;

    }

    /**
     * setSearchResultToBackUp
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void setSearchResultToBackUp(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Copy Search Result to Backup
        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgNm_B, cMsg.abcAsgNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgDescTxt_B, cMsg.abcAsgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_B, cMsg.rtlWhCatgCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RB, cMsg.rtlWhCdSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RB, cMsg.rtlWhNmSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCdSrchTxt_SB, cMsg.rtlSwhCdSrchTxt_SW.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_B, cMsg.mdseItemTpCd_H1.getValue());

        for (int i = 0; i < cMsg.S.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).stkStsCd_SB, cMsg.S.no(i).stkStsCd_SS.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxScrItem61Txt_SB, cMsg.S.no(i).xxScrItem61Txt_SS.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SB, cMsg.S.no(i).xxChkBox_SS.getValue());
        }

    }

    /**
     * getLatestAnlsRequest
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getLatestAnlsRequest(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk.getValue());

        S21SsmEZDResult result = NLCL1040Query.getInstance().getLatestAnlsRequest(params, sMsg);

        if (result.isCodeNormal()) {

            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsLastProcTs, sMsg.abcAnlsLastProcTs);
            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsRqstStsDescTxt, sMsg.abcAnlsRqstStsDescTxt);

            if (ZYPCommonFunc.hasValue(sMsg.abcAnlsLastProcTs)) {
                String d = ZYPDateUtil.DateFormatter(sMsg.abcAnlsLastProcTs.getValue(), "yyyyMMddHHmmssSSS", "MM/dd/yyyy HH:mm");
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem30Txt, d);
            }

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Refresh" });
        }

    }

    /**
     * getAbcHeader
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getAbcHeader(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // get header info
        // ***************************************************
        cMsg.mdseCd.clear();

        S21SsmEZDResult ssmResultAbcHeader = NLCL1040Query.getInstance().getAbcHeader(cMsg, sMsg);

        // there is no data
        if (!ssmResultAbcHeader.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0038I);
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.abcAsgPk.clear();
            cMsg.abcAsgDescTxt.clear();
            cMsg.abcAnlsRqstStsDescTxt.clear();
            cMsg.xxScrItem30Txt.clear();
            cMsg.rtlWhCatgCd_H1.clear();
            cMsg.rtlWhCdSrchTxt_RW.clear();
            cMsg.rtlWhNmSrchTxt_RW.clear();
            cMsg.rtlSwhCdSrchTxt_SW.clear();
            cMsg.mdseItemTpCd_H1.clear();
            cMsg.effFromDt.clear();
            cMsg.effThruDt.clear();

            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritCd_H1, cMsg.abcAnlsCritCd_L1.no(0));
            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_H1, cMsg.abcAnlsClsNum_L1.no(0));

            for (int i = 0; i < cMsg.S.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.FLG_ON_Y);
            }

            return;
        }

        // copy data sMsg -> cMsg
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, sMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgNm, sMsg.abcAsgNm);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgDescTxt, sMsg.abcAsgDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgPk, sMsg.abcAsgPk);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_H1, sMsg.rtlWhCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_H1, sMsg.mdseItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd, sMsg.rtlWhCatgCd);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd, sMsg.mdseItemTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritCd, sMsg.abcAnlsCritCd);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsCritCd_H1, sMsg.abcAnlsCritCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt, sMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt, sMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_H1, sMsg.abcAnlsClsNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum, sMsg.abcAnlsClsNum);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsLastProcTs, sMsg.abcAnlsLastProcTs);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsRqstPk, sMsg.abcAnlsRqstPk);
        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsRqstStsDescTxt, sMsg.abcAnlsRqstStsDescTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_AA, sMsg.ezUpTime_AA);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_AA, sMsg.ezUpTimeZone_AA);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_AR, sMsg.ezUpTime_AR);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_AR, sMsg.ezUpTimeZone_AR);

        if (ZYPCommonFunc.hasValue(sMsg.abcAnlsLastProcTs)) {
            String d = ZYPDateUtil.DateFormatter(sMsg.abcAnlsLastProcTs.getValue(), "yyyyMMddHHmmssSSS", "MM/dd/yyyy HH:mm");
            ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem30Txt, d);
        }

        // get Warehouse info and create detail pulldown
        // ***************************************************
        S21SsmEZDResult ssmResultWhHeader = NLCL1040Query.getInstance().getHeaderInfo(cMsg, sMsg, "getWhHeader");

        if (ssmResultWhHeader.isCodeNormal()) {

            List<Map<String, Object>> whList = (List<Map<String, Object>>) ssmResultWhHeader.getResultObject();
            if (whList != null && !whList.isEmpty()) {

                StringBuffer whCdBuffer = new StringBuffer();
                StringBuffer whNmBuffer = new StringBuffer();

                for (int i = 0; i < whList.size(); i++) {

                    Map<String, Object> whMap = (Map<String, Object>) whList.get(i);

                    if (i != 0) {
                        whCdBuffer.append(",");
                    }
                    whCdBuffer.append((String) whMap.get("RTL_WH_CD"));

                    if (i != 0) {
                        whNmBuffer.append(",");
                    }
                    whNmBuffer.append((String) whMap.get("RTL_WH_NM"));

                }

                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RW, whCdBuffer.toString());
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RW, whNmBuffer.toString());

                // create pull down Warehouse
                createDetailRtlWhPullDown(cMsg);

            }
        }

        // get Sub Warehouse info and create detail pulldown
        // ***************************************************
        S21SsmEZDResult ssmResultSubWhHeader = NLCL1040Query.getInstance().getHeaderInfo(cMsg, sMsg, "getSubWhHeader");
        if (ssmResultSubWhHeader.isCodeNormal()) {

            List<Map<String, Object>> swhList = (List<Map<String, Object>>) ssmResultSubWhHeader.getResultObject();

            if (swhList != null && !swhList.isEmpty()) {

                StringBuffer swhCdBuffer = new StringBuffer();
                StringBuffer swhNmBuffer = new StringBuffer();

                for (int i = 0; i < swhList.size(); i++) {

                    Map<String, Object> whMap = (Map<String, Object>) swhList.get(i);

                    if (i != 0) {
                        swhCdBuffer.append(",");
                    }
                    swhCdBuffer.append((String) whMap.get("RTL_SWH_CD"));

                    if (i != 0) {
                        swhNmBuffer.append(",");
                    }
                    swhNmBuffer.append((String) whMap.get("RTL_SWH_NM"));

                }

                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCdSrchTxt_SW, swhCdBuffer.toString());
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNmSrchTxt_SW, swhCdBuffer.toString());

                // create pull down Sub Warehouse
                createDetailRtlSwhPullDown(cMsg);
            }
        }

        // get Stock Status info and create header pulldown
        // ***************************************************
        // init stkSts check box
        for (int i = 0; i < cMsg.S.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.FLG_OFF_N);
        }

        S21SsmEZDResult ssmResultstockStatusHeader = NLCL1040Query.getInstance().getHeaderInfo(cMsg, sMsg, "getstockStatusHeader");

        if (ssmResultstockStatusHeader.isCodeNormal()) {

            List<Map<String, Object>> stsList = (List<Map<String, Object>>) ssmResultstockStatusHeader.getResultObject();

            if (stsList != null && !stsList.isEmpty()) {

                // stkSts check box on Y
                for (int i = 0; i < cMsg.S.getValidCount(); i++) {
                    String stkStsCd = cMsg.S.no(i).stkStsCd_SS.getValue();
                    for (int j = 0; j < stsList.size(); j++) {
                        Map<String, Object> stsMap = (Map<String, Object>) stsList.get(j);
                        String sts = (String) stsMap.get("STK_STS_CD");

                        if (sts.equals(stkStsCd)) {
                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.CHKBOX_ON_Y);
                        }
                    }
                }

            }
        }

        // get ABC Class Tag info and create header pulldown
        // ***************************************************
        S21SsmEZDResult ssmResultAbcClassTag = NLCL1040Query.getInstance().getabcClassTag(cMsg, sMsg);

        if (ssmResultAbcClassTag.isCodeNormal()) {

            List<Map<String, Object>> abcClassTagList = (List<Map<String, Object>>) ssmResultAbcClassTag.getResultObject();

            if (abcClassTagList != null && !abcClassTagList.isEmpty()) {

                for (int i = 0; i < abcClassTagList.size(); i++) {

                    Map<String, Object> abcClassTagMap = (Map<String, Object>) abcClassTagList.get(i);

                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsTagCd_L3.no(i), (String) abcClassTagMap.get("ABC_ANLS_CLS_TAG_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsTagCd_L2.no(i), (String) abcClassTagMap.get("ABC_ANLS_CLS_TAG_CD"));

                }

            }

        }

        // get Detail info
        // ***************************************************
        getAbcDetail(cMsg, sMsg);

    }

    /**
     * getAbcDetail
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getAbcDetail(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // get detail info
        // ***************************************************
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_SMSG, sMsg);
        params.put(BIND_ROWNUM, sMsg.A.length());

        if (EVENT_NM_NLCL1040_ITEMSEARCH.equals(cMsg.xxMntEventNm.getValue())) {
            params.put(BIND_MDSE_CD, cMsg.mdseCd.getValue());
        }

        S21SsmEZDResult ssmResultAbcDetail = NLCL1040Query.getInstance().getAbcDetail(params, sMsg);

        if (ssmResultAbcDetail.isCodeNormal()) {

            int queryResCnt = ssmResultAbcDetail.getQueryResultCount();

            if (queryResCnt == 0) {

                ZYPTableUtil.clear(cMsg.A);
                ZYPTableUtil.clear(sMsg.A);
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();
                cMsg.setMessageInfo(NZZM0000E);
                return;

            } else if (queryResCnt > sMsg.A.length()) {

                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();

            }

            // Copy from SMsg to cMsg
            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

            }

            cMsg.A.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);

        } else {

            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }

    }

    /**
     * createDetailRtlWhPullDown
     * @param cMsg NLCL1040CMsg
     */
    public static void createDetailRtlWhPullDown(NLCL1040CMsg cMsg) {

        String[] array = cMsg.rtlWhCdSrchTxt_RW.getValue().split(",");

        for (int i = 0; i < array.length; i++) {

            String rtlWhcd = array[i];

            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_L2.no(i), rtlWhcd);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhDescTxt_L2.no(i), rtlWhcd);

        }

    }

    /**
     * createDetailRtlSwhPullDown
     * @param cMsg NLCL1040CMsg
     */
    public static void createDetailRtlSwhPullDown(NLCL1040CMsg cMsg) {

        String[] array = cMsg.rtlSwhCdSrchTxt_SW.getValue().split(",");

        for (int i = 0; i < array.length; i++) {

            String rtlWhcd = array[i];

            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_L2.no(i), rtlWhcd);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhDescTxt_L2.no(i), rtlWhcd);

        }

    }

    /**
     * getItemDescription
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getItemDescription(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        int selectNum = cMsg.xxNum.getValueInt();

        S21SsmEZDResult result = NLCL1040Query.getInstance().getItemDescription(cMsg, selectNum);

        if (result.isCodeNormal()) {

            Map<String, Object> resultMap = (Map<String, Object>) result.getResultObject();

            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).mdseDescShortTxt_A, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));

        } else {

            cMsg.A.no(selectNum).mdseCd_A.setErrorInfo(1, NPAM0076E, new String[] {cMsg.A.no(selectNum).mdseCd_A.getValue() });
            cMsg.setMessageInfo(NPAM0076E, new String[] {cMsg.A.no(selectNum).mdseCd_A.getValue() });
        }

    }

    /**
     * copyMessage
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void copyMessage(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        int pageShowFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);

        }

    }

    /**
     * checkMdseInvtyCtrl
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void checkMdseInvtyCtrl(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            params.put(BIND_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());

            S21SsmEZDResult result = NLCL1040Query.getInstance().checkMdseInvtyCtrl(params);

            if (result.isCodeNormal()) {

                Map<String, Object> resultMap = (Map<String, Object>) result.getResultObject();

                if (ZYPConstant.FLG_OFF_N.equals((String) resultMap.get("INVTY_CTRL_FLG"))) {

                    sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLCM0198E);
                    cMsg.setMessageInfo(NLCM0198E);
                }

            } else {

                sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLCM0197E, new String[] {"Item Number" });
                cMsg.setMessageInfo(NLCM0197E, new String[] {"Item Number" });
            }

        }

    }

    /**
     * checkDuplicate
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @return boolean
     */
    public static boolean checkDuplicate(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        int maxDisplayRows = cMsg.A.length();
        int maxIndex = sMsg.A.getValidCount();
        int errScrnInex = 0;

        // Check Duplication
        for (int i = 0; i < maxIndex; i++) {

            // check inventory control flag inserted row
            // new data does not exists inventory control flag
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyCtrlFlg_A)) {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                params.put(BIND_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());

                S21SsmEZDResult result = NLCL1040Query.getInstance().checkMdseInvtyCtrl(params);

                if (result.isCodeNormal()) {

                    Map<String, Object> resultMap = (Map<String, Object>) result.getResultObject();

                    if (ZYPConstant.FLG_OFF_N.equals((String) resultMap.get("INVTY_CTRL_FLG"))) {

                        sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLCM0198E);

                        if (errScrnInex == 0) {
                            errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_A.setValue(errScrnInex);
                        }
                    }

                } else {

                    sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLCM0197E, new String[] {"Item Number" });

                    if (errScrnInex == 0) {
                        errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_A.setValue(errScrnInex);
                    }
                }
            }

            // Check with DB records and DB Update
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyCtrlFlg_A)) {

                Map<String, Object> params = new HashMap<String, Object>();

                params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                params.put(BIND_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());
                params.put(BIND_RTL_WH_CD, sMsg.A.no(i).rtlWhCd_A.getValue());
                params.put(BIND_RTL_SWH_CD, sMsg.A.no(i).rtlSwhCd_A.getValue());
                params.put(BIND_STK_STS_CD, sMsg.A.no(i).stkStsCd_A.getValue());

                S21SsmEZDResult result = NLCL1040Query.getInstance().duplicateCheckWithAnlsDtl(params);

                if (result.isCodeNormal()) {
                    sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).stkStsCd_A.setErrorInfo(1, NMAM8509E);
                    sMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, NMAM8509E);

                    if (errScrnInex == 0) {
                        errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_A.setValue(errScrnInex);
                    }
                }
            }

            for (int j = 0; j < maxIndex; j++) {

                if (i == j) {
                    continue;
                }

                if (sMsg.A.no(i).mdseCd_A.getValue().equals(sMsg.A.no(j).mdseCd_A.getValue()) && sMsg.A.no(i).rtlWhCd_A.getValue().equals(sMsg.A.no(j).rtlWhCd_A.getValue())) {

                    // check line duplicate
                    if (sMsg.A.no(i).stkStsCd_A.getValue().equals(sMsg.A.no(j).stkStsCd_A.getValue()) && sMsg.A.no(i).rtlSwhCd_A.getValue().equals(sMsg.A.no(j).rtlSwhCd_A.getValue())) {
                        sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NMAM8509E);
                        sMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, NMAM8509E);
                        sMsg.A.no(i).stkStsCd_A.setErrorInfo(1, NMAM8509E);
                        sMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, NMAM8509E);

                        if (errScrnInex == 0) {
                            errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_A.setValue(errScrnInex);
                        }
                    }

                    // check ABC Tag
                    if (!sMsg.A.no(i).abcAnlsClsTagCd_A.getValue().equals(sMsg.A.no(j).abcAnlsClsTagCd_A.getValue())) {
                        sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NLCM0205E);
                        sMsg.A.no(i).rtlWhCd_A.setErrorInfo(1, NLCM0205E);
                        sMsg.A.no(i).abcAnlsClsTagCd_A.setErrorInfo(1, NLCM0205E);

                        if (errScrnInex == 0) {
                            errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum_A.setValue(errScrnInex);
                        }
                    }
                }
                if (errScrnInex != 0 && j == errScrnInex + maxDisplayRows) {
                    break;
                }
            }
            if (errScrnInex != 0 && i == errScrnInex + maxDisplayRows) {
                break;
            }
        }

        if (errScrnInex != 0) {
            
            int lineStartNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int lineEndNum = lineStartNum + cMsg.A.length();
            
            if (lineEndNum > sMsg.A.getValidCount()) {
                lineEndNum = sMsg.A.getValidCount();
            }

            for (int i = 0; i < lineEndNum - lineStartNum; i++) {

                EZDMsg.copy(sMsg.A.no(lineStartNum + i), null, cMsg.A.no(i), null);

            }
            return false;
        }
        return true;
    }

    /**
     * getWarehouseName
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getWarehouseName(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        ArrayList<String> splitWhCdList = splitSrchTxt(cMsg.rtlWhCdSrchTxt_RW, false, null);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_WH_CD_LIST, splitWhCdList);
        params.put(BIND_SALES_DATE, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        params.put(BIND_LIMIT_EFF_THRU_DT, LIMIT_DATE);
        params.put(BIND_ROWNUM, CODE_SEARCH_SIZE);

        S21SsmEZDResult result = NLCL1040Query.getInstance().getWarehouseInfoList(params);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> whCdList = (List<Map<String, Object>>) result.getResultObject();

            if (whCdList == null || whCdList.isEmpty() || (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_RW) && whCdList.size() < splitWhCdList.size())) {

                cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NPAM1361E, new String[] {"Warehouse" });
                cMsg.setMessageInfo(NPAM1361E, new String[] {"Warehouse" });    // 2018/11/26 T.Ogura [QC#29124,ADD]

            } else {

                StringBuffer whCdBuffer = new StringBuffer();
                String rtlwhCatgCd = cMsg.rtlWhCatgCd_H1.getValue();

                for (int i = 0; i < whCdList.size(); i++) {

                    Map<String, Object> whCdMap = (Map<String, Object>) whCdList.get(i);

                    if (i != 0) {
                        whCdBuffer.append(",");
                    }
                    whCdBuffer.append((String) whCdMap.get("RTL_WH_NM"));

                    if (ZYPCommonFunc.hasValue(rtlwhCatgCd) && !rtlwhCatgCd.equals((String) whCdMap.get("RTL_WH_CATG_CD"))) {
                        cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NLCM0185E);
                        cMsg.setMessageInfo(NLCM0185E);
                        break;
                    }

                }

                Map<String, Object> whTp = (Map<String, Object>) whCdList.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_H1, (String) whTp.get("RTL_WH_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RW, whCdBuffer.toString());

            }
        } else if (result.isCodeNotFound()) {
            cMsg.rtlWhCatgCd_H1.clear();
            cMsg.rtlWhNmSrchTxt_RW.clear();
            cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NPAM1361E, new String[] {"Warehouse"});
            cMsg.setMessageInfo(NPAM1361E, new String[] {"Warehouse" });    // 2018/11/26 T.Ogura [QC#29124,ADD]
        }

    }

    /**
     * getSubWarehouseName
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getSubWarehouseName(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        ArrayList<String> splitWhCdList = splitSrchTxt(cMsg.rtlWhCdSrchTxt_RW, false, null);
        ArrayList<String> splitSubWhCdList = splitSrchTxt(cMsg.rtlSwhCdSrchTxt_SW, false, null);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_WH_CD_LIST, splitWhCdList);
        params.put(BIND_SWH_CD_LIST, splitSubWhCdList);
        params.put(BIND_SALES_DATE, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        params.put(BIND_LIMIT_EFF_THRU_DT, LIMIT_DATE);
        params.put(BIND_RTL_WH_CATG_CD, cMsg.rtlWhCatgCd_H1.getValue());

        S21SsmEZDResult result = NLCL1040Query.getInstance().getSubWarehouseInfoList(params);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> subwhCdList = (List<Map<String, Object>>) result.getResultObject();

            if (subwhCdList == null || subwhCdList.isEmpty() || (ZYPCommonFunc.hasValue(cMsg.rtlSwhCdSrchTxt_SW) && subwhCdList.size() < splitSubWhCdList.size())) {

                // START 2018/11/26 T.Ogura [QC#29124,MOD]
//                cMsg.rtlSwhCdSrchTxt_SW.setErrorInfo(1, NPAM1361E, new String[] {cMsg.rtlSwhCdSrchTxt_SW.getValue() });
                cMsg.rtlSwhCdSrchTxt_SW.setErrorInfo(1, NPAM1361E, new String[] {"Sub Warehouse" });
                // END   2018/11/26 T.Ogura [QC#29124,MOD]
                cMsg.setMessageInfo(NPAM1361E, new String[] {"Sub Warehouse" });

            } else {

                StringBuffer whCdBuffer = new StringBuffer();

                for (int i = 0; i < subwhCdList.size(); i++) {

                    Map<String, Object> whCdMap = (Map<String, Object>) subwhCdList.get(i);

                    if (i != 0) {
                        whCdBuffer.append(",");
                    }
                    whCdBuffer.append((String) whCdMap.get("RTL_SWH_CD"));
                }

                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCdSrchTxt_SW, whCdBuffer.toString());

            }
        // START 2018/11/26 T.Ogura [QC#29124,ADD]
        } else if (result.isCodeNotFound()) {
            cMsg.rtlSwhCdSrchTxt_SW.setErrorInfo(1, NPAM1361E, new String[] {"Sub Warehouse" });
            cMsg.setMessageInfo(NPAM1361E, new String[] {"Sub Warehouse" });
        // END   2018/11/26 T.Ogura [QC#29124,ADD]
        }

    }

    /**
     * getAbcName
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void getAbcName(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        if (!EVENT_NM_NLCL1040_DELETE.equals(cMsg.xxMntEventNm.getValue())) {
            params.put("abcAsgNm", cMsg.abcAsgNm);
        }

        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk);

        S21SsmEZDResult result = NLCL1040Query.getInstance().getAbcName(params);

        if (result.isCodeNormal()) {

            if (!EVENT_NM_NLCL1040_DELETE.equals(cMsg.xxMntEventNm.getValue())) {

                cMsg.abcAsgNm.setErrorInfo(1, NLAM1294E, new String[] {"ABC Name" });
                cMsg.setMessageInfo(NLAM1294E, new String[] {"ABC Name" });

            }

        } else if (result.isCodeNotFound()) {

            if (EVENT_NM_NLCL1040_DELETE.equals(cMsg.xxMntEventNm.getValue())) {

                cMsg.abcAsgNm.setErrorInfo(1, NLCM0195E);
                cMsg.setMessageInfo(NLCM0195E);

            }
        }

    }

    // START 2018/11/26 T.Ogura [QC#29124,ADD]
    /**
     * isExistAbcAsg
     * @param cMsg NLCL1040CMsg
     */
    public static void isExistAbcAsg(NLCL1040CMsg cMsg) {

        ABC_ASGTMsg tMsg = new ABC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, cMsg.abcAsgPk);

        ABC_ASGTMsg result = (ABC_ASGTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (result == null) {
            cMsg.abcAsgNm.setErrorInfo(1, NLCM0195E);
            cMsg.setMessageInfo(NLCM0195E);
        }
    }
    // END   2018/11/26 T.Ogura [QC#29124,ADD]

    /**
     * whDuplicate
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void whDuplicate(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        ArrayList<String> splitWhCdList = splitSrchTxt(cMsg.rtlWhCdSrchTxt_RW, false, null);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_WH_CD_LIST, splitWhCdList);
        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk.getValue());

        S21SsmEZDResult result = NLCL1040Query.getInstance().whDuplicate(params);

        if (result.isCodeNormal()) {

            if (!ZYPCommonFunc.hasValue(cMsg.xxWrnSkipFlg) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxWrnSkipFlg.getValue())) {
                cMsg.setMessageInfo(NLAM1310W, new String[] {"Warehouse" });
                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            }

        } else {

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        }

    }

    /**
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItem
     * @param isMdse boolean
     * @param glblCmpyCd String
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem, boolean isMdse, String glblCmpyCd) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(",") != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(",");

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim().replace("%", "");

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim().replace("%", "");

                if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                    splitSrchTxtList.add(srchTxtItem.getValue().trim());
                }
            }
        }

        if (splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

            return splitSrchTxtList;
        }

        return null;
    }

    /**
     * ArrayList<String>
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void historicalCheck(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put("abcAnlsCritCd", cMsg.abcAnlsCritCd_H1.getValue());

        S21SsmEZDResult result = NLCL1040Query.getInstance().historicaCheckt(params);

        if (result.isCodeNormal()) {

            if (!ZYPCommonFunc.hasValue(cMsg.effFromDt)) {
                cMsg.effFromDt.setErrorInfo(1, NLAM0173E, new String[] {"From Date" });
                cMsg.setMessageInfo(NLAM0173E, new String[] {"From Date" });
                return;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.effThruDt)) {
                cMsg.effThruDt.setErrorInfo(1, NLAM0173E, new String[] {"To Date" });
                cMsg.setMessageInfo(NLAM0173E, new String[] {"To Date" });
                return;
            }

            if (ZYPDateUtil.compare(cMsg.effFromDt.getValue(), cMsg.effThruDt.getValue()) > 0) {
                cMsg.effFromDt.setErrorInfo(1, NLGM0059E, new String[] {"To Date", "From Date" });
                cMsg.setMessageInfo(NLGM0059E, new String[] {"To Date", "From Date" });
                return;
            }
        } else if(result.isCodeNotFound()) {
            cMsg.effFromDt.clear();
            cMsg.effThruDt.clear();
        }

    }

    /**
     * masterCheck
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void masterCheck(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        if (EVENT_NM_NLCL1040_CMN_SUBMIT.equals(cMsg.xxMntEventNm.getValue())) {

            // mdse invty ctrl flag check
            // ************************************
            checkMdseInvtyCtrl(cMsg, sMsg);

        } else {

            // Warehouse
            // ************************************
            getWarehouseName(cMsg, sMsg);

            // Sub Warehouse
            // ************************************
            getSubWarehouseName(cMsg, sMsg);

            // ABC Name
            // ************************************
            getAbcName(cMsg, sMsg);

            // WH Duplicate
            // ************************************
            whDuplicate(cMsg, sMsg);

        }
    }

    /**
     * createOrUpdateAbc
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @return true:Success/false:Error
     */
    public static boolean createOrUpdateAbc(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        boolean modeCreate = true;

        if (ZYPCommonFunc.hasValue(cMsg.abcAsgPk.getValue())) {
            modeCreate = false;
        }

        // ABC_ASG
        // ************************************
        if (!createOrUpdateAbcAsg(cMsg, sMsg, modeCreate)) {
            // Error
            return false;
        }

        // ABC_ASG_WH
        // ************************************
        if (!createOrUpdateAbcAsgWh(cMsg, sMsg, modeCreate)) {
            // Error
            return false;
        }

        // ABC_ASG_SWH
        // ************************************
        if (!createOrUpdateAbcAsgSubWh(cMsg, sMsg, modeCreate)) {
            // Error
            return false;
        }

        // ABC_ASG_STK_STS
        // ************************************
        if (!createOrUpdateAbcAsgStkSts(cMsg, sMsg, modeCreate)) {
            // Error
            return false;
        }

        return true;
    }

    /**
     * createOrUpdateAbcAsg
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @param modeCreate boolean
     * @return boolean
     */
    public static boolean createOrUpdateAbcAsg(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg, boolean modeCreate) {

        ABC_ASGTMsg abcAsgTmsg = new ABC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());

        if (modeCreate) {

            ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ASG_SQ));

        } else {

            ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
            abcAsgTmsg = (ABC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAsgTmsg);

            if (abcAsgTmsg == null) {
                return false;
            }

            String aslHdrEzUpTime = cMsg.ezUpTime_AA.getValue();
            String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AA.getValue();
            String dbEzUpTime = abcAsgTmsg.ezUpTime.getValue();
            String dbEzTimeZone = abcAsgTmsg.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                cMsg.setMessageInfo(NPAM1297E);
                return false;
            }

        }

        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgNm, cMsg.abcAsgNm.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgDescTxt, cMsg.abcAsgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgCratDt, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.effFromDt, cMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.effThruDt, cMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.rtlWhCatgCd, cMsg.rtlWhCatgCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.mdseItemTpCd, cMsg.mdseItemTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAnlsCritCd, cMsg.abcAnlsCritCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAnlsClsNum, cMsg.abcAnlsClsNum_H1.getValue());

        // delete insert
        if (!modeCreate) {

            EZDTBLAccessor.logicalRemove(abcAsgTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgTmsg.getReturnCode())) {
                cMsg.setMessageInfo(NLCM0131E, new String[] {abcAsgTmsg.getTableName() });
                return false;
            }
        }

        EZDTBLAccessor.create(abcAsgTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgTmsg.getReturnCode())) {
            cMsg.setMessageInfo(NLCM0125E, new String[] {abcAsgTmsg.getTableName() });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgPk, abcAsgTmsg.abcAsgPk.getValue());

        return true;
    }

    /**
     * createOrUpdateAbcAsgWh
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @param modeCreate boolean
     * @return boolean
     */
    public static boolean createOrUpdateAbcAsgWh(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg, boolean modeCreate) {

        if (!modeCreate) {

            S21SsmEZDResult result = NLCL1040Query.getInstance().getLogicalRemoveList(cMsg, "abcAsgWhRemoveList");

            if (result.isCodeNormal()) {

                List<Map<String, Object>> removeList = (List<Map<String, Object>>) result.getResultObject();

                for (int i = 0; i < removeList.size(); i++) {

                    Map<String, Object> removeMap = (Map<String, Object>) removeList.get(i);

                    ABC_ASG_WHTMsg abcAsgWhTmsg = new ABC_ASG_WHTMsg();
                    ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.abcAsgPk, (BigDecimal) removeMap.get("ABC_ASG_PK"));
                    ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.rtlWhCd, (String) removeMap.get("RTL_WH_CD"));

                    abcAsgWhTmsg = (ABC_ASG_WHTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAsgWhTmsg);
                    if (abcAsgWhTmsg == null) {
                        return false;
                    }

                    String aslHdrEzUpTime = cMsg.ezUpTime_AA.getValue();
                    String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AA.getValue();
                    String dbEzUpTime = abcAsgWhTmsg.ezUpTime.getValue();
                    String dbEzTimeZone = abcAsgWhTmsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                        cMsg.setMessageInfo(NPAM1297E);
                        return false;
                    }

                    EZDTBLAccessor.logicalRemove(abcAsgWhTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgWhTmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLCM0131E, new String[] {abcAsgWhTmsg.getTableName() });
                        return false;
                    }

                }
            }

        }

        String[] array = cMsg.rtlWhCdSrchTxt_RW.getValue().split(",");

        for (int i = 0; i < array.length; i++) {

            String rtlWhcd = array[i];

            ABC_ASG_WHTMsg abcAsgWhTmsg = new ABC_ASG_WHTMsg();
            ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
            ZYPEZDItemValueSetter.setValue(abcAsgWhTmsg.rtlWhCd, rtlWhcd);

            EZDTBLAccessor.create(abcAsgWhTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgWhTmsg.getReturnCode())) {
                return false;
            }
        }

        return true;
    }

    /**
     * createOrUpdateAbcAsgSubWh
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @param modeCreate boolean
     * @return boolean
     */
    public static boolean createOrUpdateAbcAsgSubWh(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg, boolean modeCreate) {

        if (!modeCreate) {

            S21SsmEZDResult result = NLCL1040Query.getInstance().getLogicalRemoveList(cMsg, "abcAsgSubWhRemoveList");

            if (result.isCodeNormal()) {

                List<Map<String, Object>> removeList = (List<Map<String, Object>>) result.getResultObject();

                for (int i = 0; i < removeList.size(); i++) {

                    Map<String, Object> removeMap = (Map<String, Object>) removeList.get(i);

                    ABC_ASG_SWHTMsg abcAsgSubWhTmsg = new ABC_ASG_SWHTMsg();
                    ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.abcAsgPk, (BigDecimal) removeMap.get("ABC_ASG_PK"));
                    ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.rtlSwhCd, (String) removeMap.get("RTL_SWH_CD"));

                    abcAsgSubWhTmsg = (ABC_ASG_SWHTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAsgSubWhTmsg);
                    if (abcAsgSubWhTmsg == null) {
                        return false;
                    }

                    String aslHdrEzUpTime = cMsg.ezUpTime_AA.getValue();
                    String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AA.getValue();
                    String dbEzUpTime = abcAsgSubWhTmsg.ezUpTime.getValue();
                    String dbEzTimeZone = abcAsgSubWhTmsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                        cMsg.setMessageInfo(NPAM1297E);
                        return false;
                    }

                    EZDTBLAccessor.logicalRemove(abcAsgSubWhTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgSubWhTmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLCM0131E, new String[] {abcAsgSubWhTmsg.getTableName() });
                        return false;
                    }

                }
            }

        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCdSrchTxt_SW)) {

            String[] array = cMsg.rtlSwhCdSrchTxt_SW.getValue().split(",");

            for (int i = 0; i < array.length; i++) {

                String rtlSubWhcd = array[i];

                ABC_ASG_SWHTMsg abcAsgSubWhTmsg = new ABC_ASG_SWHTMsg();
                ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
                ZYPEZDItemValueSetter.setValue(abcAsgSubWhTmsg.rtlSwhCd, rtlSubWhcd);

                EZDTBLAccessor.create(abcAsgSubWhTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgSubWhTmsg.getReturnCode())) {
                    return false;
                }
            }

        }

        return true;

    }

    /**
     * NLCL1040CMsg
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void createOrUpdateAnalysisResult(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // ABC_ANLS_RSLT_DTL
        // ************************************
        createOrUpdateAnalysisResultDetail(cMsg, sMsg);

        // ABC_ANLS_RSLT
        // ************************************
        createOrUpdateAnalysisResultHeader(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        }

    }

    /**
     * createOrUpdateAnalysisResultDetail
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void createOrUpdateAnalysisResultDetail(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            ABC_ANLS_RSLT_DTLTMsg abcAnlsRsltDtlTmsg = new ABC_ANLS_RSLT_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());

            // update
            if (ZYPCommonFunc.hasValue(sMsg.A.no(index).abcAnlsRsltDtlPk_A)) {

                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAnlsRsltDtlPk, sMsg.A.no(index).abcAnlsRsltDtlPk_A.getValue());

                abcAnlsRsltDtlTmsg = (ABC_ANLS_RSLT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAnlsRsltDtlTmsg);

                if (abcAnlsRsltDtlTmsg == null) {
                    sMsg.A.no(index).mdseCd_A.setErrorInfo(1, NPAM1297E);
                    cMsg.setMessageInfo(NPAM1297E);
                    return;

                }

                String aslHdrEzUpTime = sMsg.A.no(index).ezUpTime_AD.getValue();
                String aslHdrEzTimeZone = sMsg.A.no(index).ezUpTimeZone_AD.getValue();
                String dbEzUpTime = abcAnlsRsltDtlTmsg.ezUpTime.getValue();
                String dbEzTimeZone = abcAnlsRsltDtlTmsg.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                // delete
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).deleteFlagIf_A.getValue())) {

                    EZDTBLAccessor.logicalRemove(abcAnlsRsltDtlTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRsltDtlTmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLCM0131E, new String[] {abcAnlsRsltDtlTmsg.getTableName() });
                        return;
                    }

                } else {

                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAnlsClsTagCd, sMsg.A.no(index).abcAnlsClsTagCd_A.getValue());
                    EZDTBLAccessor.update(abcAnlsRsltDtlTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRsltDtlTmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLCM0206E, new String[] {abcAnlsRsltDtlTmsg.getTableName() });
                        return;
                    }
                }

            } else {

                // create
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAnlsRsltDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_DTL_SQ));
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAnlsRqstPk, cMsg.abcAnlsRqstPk.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.rtlWhCd, sMsg.A.no(index).rtlWhCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.mdseCd, sMsg.A.no(index).mdseCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.rtlSwhCd, sMsg.A.no(index).rtlSwhCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.stkStsCd, sMsg.A.no(index).stkStsCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.curInvtyQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.curInvtyCostAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.histInvtyTrxQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.histInvtyTrxCostAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.histInvtyTrxRecCnt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltDtlTmsg.abcAnlsClsTagCd, sMsg.A.no(index).abcAnlsClsTagCd_A.getValue());

                EZDTBLAccessor.create(abcAnlsRsltDtlTmsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRsltDtlTmsg.getReturnCode())) {
                    return;
                }
            }
        }

    }

    /**
     * createOrUpdateAnalysisResultHeader
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void createOrUpdateAnalysisResultHeader(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        int maxIndex = sMsg.A.getValidCount();

        ABC_ANLS_RSLTTMsg abcAnlsRsltTmsg = new ABC_ANLS_RSLTTMsg();

        ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());

        // Get Cycle Count List 
        S21SsmEZDResult result = NLCL1040Query.getInstance().getabcClassTag(cMsg, sMsg);

        List<Map<String, Object>> abcClassTagList = (List<Map<String, Object>>) result.getResultObject();
        HashMap<String, BigDecimal> abcCycleCountList = new HashMap<String, BigDecimal>();
        for (Map<String, Object> abcClassTag : abcClassTagList) {
            abcCycleCountList.put((String) abcClassTag.get("ABC_ANLS_CLS_TAG_CD")//
                    , (BigDecimal) abcClassTag.get("CYCLE_CNT_FREQ_DAYS_AOT"));
        }

        for (int i = 0; i < maxIndex; i++) {

            // add data
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).processedFlag_A.getValue())) {

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).abcAnlsRsltPk_A)) {
                    for (int j = 0; j < maxIndex; j++) {
                        if (sMsg.A.no(i).rtlWhCd_A.equals(sMsg.A.no(j).rtlWhCd_A) && sMsg.A.no(i).mdseCd_A.equals(sMsg.A.no(j).mdseCd_A)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).abcAnlsRsltPk_A, sMsg.A.no(j).abcAnlsRsltPk_A);
                            break;
                        }
                    }
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).abcAnlsRsltPk_A)) {
                    // create
                    abcAnlsRsltTmsg = new ABC_ANLS_RSLTTMsg();
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsRsltPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_SQ));
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsRqstPk, cMsg.abcAnlsRqstPk.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.mdseCd, sMsg.A.no(i).mdseCd_A.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsValAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsCumValAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsCumValPct, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsClsNum, cMsg.abcAnlsClsNum.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsClsTagCd, sMsg.A.no(i).abcAnlsClsTagCd_A.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.cycleCntFreqDaysAot, abcCycleCountList.get(sMsg.A.no(i).abcAnlsClsTagCd_A.getValue()));
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsLtstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

                    EZDTBLAccessor.insert(abcAnlsRsltTmsg);

                } else {

                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsRsltPk, sMsg.A.no(i).abcAnlsRsltPk_A.getValue());

                    abcAnlsRsltTmsg = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAnlsRsltTmsg);

                    // update
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsClsTagCd, sMsg.A.no(i).abcAnlsClsTagCd_A.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.cycleCntFreqDaysAot, abcCycleCountList.get(sMsg.A.no(i).abcAnlsClsTagCd_A.getValue()));
                    ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                    abcAnlsRsltTmsg.intfcUpdTs.clear();
                    EZDTBLAccessor.update(abcAnlsRsltTmsg);

                }

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRsltTmsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLCM0206E, new String[] {abcAnlsRsltTmsg.getTableName() });
                    return;

                }
            } else {
                // Update ABC Tag 
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsRsltPk, sMsg.A.no(i).abcAnlsRsltPk_A.getValue());

                abcAnlsRsltTmsg = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAnlsRsltTmsg);

                // update
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsClsTagCd, sMsg.A.no(i).abcAnlsClsTagCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.cycleCntFreqDaysAot, abcCycleCountList.get(sMsg.A.no(i).abcAnlsClsTagCd_A.getValue()));
                ZYPEZDItemValueSetter.setValue(abcAnlsRsltTmsg.abcAnlsProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                abcAnlsRsltTmsg.intfcUpdTs.clear();
                EZDTBLAccessor.update(abcAnlsRsltTmsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRsltTmsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLCM0206E, new String[] {abcAnlsRsltTmsg.getTableName() });
                    return;
                }
            }
        }
    }

    /**
     * createOrUpdateAbcAsgStkSts
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @param modeCreate boolean
     * @return boolean
     */
    public static boolean createOrUpdateAbcAsgStkSts(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg, boolean modeCreate) {

        if (!modeCreate) {

            S21SsmEZDResult result = NLCL1040Query.getInstance().getLogicalRemoveList(cMsg, "abcAsgStkStsRemoveList");

            if (result.isCodeNormal()) {

                List<Map<String, Object>> removeList = (List<Map<String, Object>>) result.getResultObject();

                for (int i = 0; i < removeList.size(); i++) {

                    Map<String, Object> removeMap = (Map<String, Object>) removeList.get(i);

                    ABC_ASG_STK_STSTMsg abcAsgStkStsTmsg = new ABC_ASG_STK_STSTMsg();
                    ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.abcAsgPk, (BigDecimal) removeMap.get("ABC_ASG_PK"));
                    ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.stkStsCd, (String) removeMap.get("STK_STS_CD"));

                    abcAsgStkStsTmsg = (ABC_ASG_STK_STSTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAsgStkStsTmsg);

                    if (abcAsgStkStsTmsg == null) {
                        return false;
                    }

                    String aslHdrEzUpTime = cMsg.ezUpTime_AA.getValue();
                    String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AA.getValue();
                    String dbEzUpTime = abcAsgStkStsTmsg.ezUpTime.getValue();
                    String dbEzTimeZone = abcAsgStkStsTmsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                        cMsg.setMessageInfo(NPAM1297E);
                        return false;
                    }

                    EZDTBLAccessor.logicalRemove(abcAsgStkStsTmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgStkStsTmsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLCM0131E, new String[] {abcAsgStkStsTmsg.getTableName() });
                        return false;
                    }

                }
            }

        }

        for (int index = 0; index < cMsg.S.getValidCount(); index++) {

            if (ZYPCommonFunc.hasValue(cMsg.S.no(index).xxChkBox_SS)) {

                ABC_ASG_STK_STSTMsg abcAsgStkStsTmsg = new ABC_ASG_STK_STSTMsg();
                ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
                ZYPEZDItemValueSetter.setValue(abcAsgStkStsTmsg.stkStsCd, cMsg.S.no(index).stkStsCd_SS.getValue());

                EZDTBLAccessor.create(abcAsgStkStsTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgStkStsTmsg.getReturnCode())) {
                    return false;
                }

            }

        }

        return true;

    }

    /**
     * compareWithBakup
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     * @return boolean
     */
    public static boolean compareWithBakup(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        boolean isError = false;

        if (!cMsg.abcAsgNm_B.getValue().equals(cMsg.abcAsgNm.getValue())) {
            cMsg.abcAsgNm.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.abcAsgDescTxt_B.getValue().equals(cMsg.abcAsgDescTxt.getValue())) {
            cMsg.abcAsgDescTxt.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.rtlWhCatgCd_B.getValue().equals(cMsg.rtlWhCatgCd_H1.getValue())) {
            cMsg.rtlWhCatgCd_H1.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.rtlWhCdSrchTxt_RB.getValue().equals(cMsg.rtlWhCdSrchTxt_RW.getValue())) {
            cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.rtlWhNmSrchTxt_RB.getValue().equals(cMsg.rtlWhNmSrchTxt_RW.getValue())) {
            cMsg.rtlWhNmSrchTxt_RW.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.rtlSwhCdSrchTxt_SB.getValue().equals(cMsg.rtlSwhCdSrchTxt_SW.getValue())) {
            cMsg.rtlSwhCdSrchTxt_SW.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        if (!cMsg.mdseItemTpCd_B.getValue().equals(cMsg.mdseItemTpCd_H1.getValue())) {
            cMsg.mdseItemTpCd_H1.setErrorInfo(1, NLCM0193E);
            cMsg.setMessageInfo(NLCM0193E);
            isError = true;
        }

        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.S.no(i).xxChkBox_SS)) {
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.FLG_OFF_N);
            }
            if (!ZYPCommonFunc.hasValue(cMsg.S.no(i).xxChkBox_SB)) {
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SB, ZYPConstant.FLG_OFF_N);
            }

            if (!cMsg.S.no(i).xxChkBox_SB.getValue().equals(cMsg.S.no(i).xxChkBox_SS.getValue())) {
                cMsg.S.no(i).xxChkBox_SS.setErrorInfo(1, NLCM0193E);
                cMsg.setMessageInfo(NLCM0193E);
                isError = true;
            }

        }

        return isError;
    }

    /**
     * logicalRemoveAbsAsg
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void logicalRemoveAbsAsg(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        ABC_ASGTMsg abcAsgTmsg = new ABC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(abcAsgTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());

        abcAsgTmsg = (ABC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAsgTmsg);

        if (abcAsgTmsg == null) {
            return;
        }

        String aslHdrEzUpTime = cMsg.ezUpTime_AA.getValue();
        String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AA.getValue();
        String dbEzUpTime = abcAsgTmsg.ezUpTime.getValue();
        String dbEzTimeZone = abcAsgTmsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
            cMsg.setMessageInfo(NPAM1297E);
            return;
        }

        EZDTBLAccessor.logicalRemove(abcAsgTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAsgTmsg.getReturnCode())) {
            cMsg.setMessageInfo(NLCM0131E, new String[] {abcAsgTmsg.getTableName() });
            return;
        }

    }

    /**
     * requestStatusCheck
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void requestStatusCheck(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Request Status Check
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk.getValue());
        params.put(BIND_ABC_ANLS_RQST_STS_CD, ABC_ANLS_RQST_STS.COMPLEATED );

        S21SsmEZDResult result = NLCL1040Query.getInstance().getRequestStatus(params);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> abcAnalysisRequestList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < abcAnalysisRequestList.size(); i++) {

                Map<String, Object> abcAnalysisRequestMap = (Map<String, Object>) abcAnalysisRequestList.get(i);

                if (ZYPConstant.FLG_ON_Y.equals((abcAnalysisRequestMap.get("OPEN_STS_FLG")))) {
                    cMsg.mdseItemTpCd_H1.setErrorInfo(1, NLCM0194E);
                    cMsg.setMessageInfo(NLCM0194E);
                    return;
                }

            }
        }

        if (!ZYPCommonFunc.hasValue(cMsg.xxWrnSkipFlg) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxWrnSkipFlg.getValue())) {
            cMsg.setMessageInfo(NLCM0196I);
            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        }

    }

    /**
     * CreateOrUpdateAnalysisRequest
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void createOrUpdateAnalysisRequest(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Request Status Check
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ASG_PK, cMsg.abcAsgPk.getValue());

        S21SsmEZDResult result = NLCL1040Query.getInstance().getRequestStatus(params);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> abcAnalysisRequestList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < abcAnalysisRequestList.size(); i++) {

                Map<String, Object> abcAnalysisRequestMap = (Map<String, Object>) abcAnalysisRequestList.get(i);

                if (ZYPConstant.FLG_ON_Y.equals((abcAnalysisRequestMap.get("OPEN_STS_FLG")))) {
                    cMsg.mdseItemTpCd_H1.setErrorInfo(1, NLCM0194E);
                    cMsg.setMessageInfo(NLCM0194E);
                    return;
                }

            }

            // UPDATE ABC Analysis Request Latest Flag -> N
            for (int j = 0; j < abcAnalysisRequestList.size(); j++) {

                Map<String, Object> abcAnalysisRequestMap = (Map<String, Object>) abcAnalysisRequestList.get(j);

                ABC_ANLS_RQSTTMsg abcAnlsRqstTmsg = new ABC_ANLS_RQSTTMsg();
                ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAnlsRqstPk, ((BigDecimal) abcAnalysisRequestMap.get("ABC_ANLS_RQST_PK")));

                abcAnlsRqstTmsg = (ABC_ANLS_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(abcAnlsRqstTmsg);
                if (abcAnlsRqstTmsg == null) {
                    return;
                }

                String aslHdrEzUpTime = cMsg.ezUpTime_AR.getValue();
                String aslHdrEzTimeZone = cMsg.ezUpTimeZone_AR.getValue();
                String dbEzUpTime = abcAnlsRqstTmsg.ezUpTime.getValue();
                String dbEzTimeZone = abcAnlsRqstTmsg.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                    cMsg.setMessageInfo(NPAM1297E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.rqstLtstFlg, ZYPConstant.FLG_OFF_N);

                EZDTBLAccessor.update(abcAnlsRqstTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRqstTmsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLCM0206E, new String[] {abcAnlsRqstTmsg.getTableName() });
                    return;
                }
            }
        }

        // getVarCharConstValue ABC_ANLS_RQST_SRC_TP_ANLZ
        // ******************************************
        String abcAnalsRqstSrcTp = ZYPCodeDataUtil.getVarCharConstValue(ABC_ANLS_RQST_SRC_TP_ANLZ, cMsg.glblCmpyCd.getValue());

        if (!ZYPCommonFunc.hasValue(abcAnalsRqstSrcTp)) {

            return;
        }

        // create ABC Analysis Request
        ABC_ANLS_RQSTTMsg abcAnlsRqstTmsg = new ABC_ANLS_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAnlsRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RQST_SQ));
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAsgPk, cMsg.abcAsgPk.getValue());
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAnlsRqstSrcTpCd, abcAnalsRqstSrcTp);
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAnlsRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        abcAnlsRqstTmsg.abcAnlsLastProcTs.clear();
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.abcAnlsRqstStsCd, ABC_ANLS_RQST_STS.REQUESTED);
        ZYPEZDItemValueSetter.setValue(abcAnlsRqstTmsg.rqstLtstFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.create(abcAnlsRqstTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(abcAnlsRqstTmsg.getReturnCode())) {
            return;
        }

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Analyse Request" });

    }
}
