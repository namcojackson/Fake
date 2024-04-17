/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0480;

import static business.blap.NSAL0480.constant.NSAL0480Constant.CSV_FILE_NAME;
import static business.blap.NSAL0480.constant.NSAL0480Constant.LIMIT_DL_ROWNUM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.MAX_FETCH_SIZE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0480.common.NSAL0480CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/04/06   Hitachi         Y.Takeno        Update          QC#5989
 * 2016/10/07   Hitachi         N.Arai          Update          QC#15001
 *</pre>
 */
public class NSAL0480BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0480_INIT".equals(screenAplId)) {
                doProcess_NSAL0480_INIT((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);
                // 2015/10/05 CSA Y.Tsuchimoto Add Start
                ZYPGUITableColumn.getColData((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);
                // 2015/10/05 CSA Y.Tsuchimoto Add End
                // events of NSAL0480Scrn00.
            } else if (screenAplId.startsWith("NSAL0480Scrn00")) {

                if (screenAplId.endsWith("_ViewItem")) {
                    doProcess_NSAL0480_ViewItem((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_Search")) {
                    doProcess_NSAL0480_Search((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_OnChangeSavedSearchOption")) {
                    doProcess_NSAL0480_OnChangeSavedSearchOption((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_SaveSearch")) {
                    doProcess_NSAL0480_SaveSearch((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_DeleteSearch")) {
                    doProcess_NSAL0480_DeleteSearch((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0480_PagePrev((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0480_PageNext((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0480_CMN_Clear((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Download")) {
                    doProcess_NSAL0480_CMN_Download((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Return")) {
                    doProcess_NSAL0480_CMN_Return((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

                }
            } else if (screenAplId.endsWith("NSAL0480_NSAL0490")) {
                doProcess_NSAL0480_NSAL0490((NSAL0480CMsg) cMsg, (NSAL0480SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0480_CMN_Return(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        // nop
        return;
    }

    private void doProcess_NSAL0480_CMN_Download(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSAL0480Query.getInstance().getClass());

            //create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> ssMParam = NSAL0480Query.getSsmParam(cMsg, sMsg, LIMIT_DL_ROWNUM + 1);
            ps = ssmLLClient.createPreparedStatement("getSearchData", ssMParam, execParam);
            rs = ps.executeQuery();
            NSAL0480CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    private void doProcess_NSAL0480_CMN_Clear(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        sMsg.A.clear();

        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();

        cMsg.t_MdlNm_H.clear();
        cMsg.mdlDescTxt_H.clear();
        cMsg.mdlGrpNm_H.clear();
        cMsg.svcSegCd_H.clear();
        cMsg.mdlActvFlg_H.clear();
        cMsg.xxCratDt_H.clear();
        cMsg.mtrGrpPk_H.clear();
        cMsg.svcSkillNum_H.clear();
// START 2016/10/07 N.Arai [QC#15001, MOD]
        cMsg.svcSkillDescTxt_H.clear();
// END 2016/10/07 N.Arai [QC#15001, MOD]
        cMsg.svcIstlRuleNum_HN.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlReqFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlReqFlg_HN, ZYPConstant.FLG_OFF_N);
        cMsg.svcIstlRuleNum_HY.clear();
        cMsg.t_ItemCd_H.clear();
        cMsg.mdseCd_H.clear();
        cMsg.imgSplyOemCd_H.clear();
        cMsg.mdseItemClsTpCd_H.clear();
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.custIstlFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.custIstlFlg_HN, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.siteSrvyReqFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.siteSrvyReqFlg_HN, ZYPConstant.FLG_OFF_N);
        // 2015/10/05 CSA Y.Tsuchimoto Add End
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    private void doProcess_NSAL0480_DeleteSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0480_SaveSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0480_OnChangeSavedSearchOption(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NSAL0480CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    private void doProcess_NSAL0480_Search(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        NSAL0480CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0480CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0480CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0480CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
        cMsg.xxRadioBtn.clear();
    }

    private void doProcess_NSAL0480_ViewItem(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        // no operation
        return;
    }

    private void doProcess_NSAL0480_PageNext(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL0480CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0480CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
        cMsg.xxRadioBtn.clear();
    }

    private void doProcess_NSAL0480_PagePrev(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.A.length())));
        NSAL0480CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0480CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
        cMsg.xxRadioBtn.clear();
    }

    private void doProcess_NSAL0480_NSAL0490(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        NSAL0480CommonLogic.getSearchData(cMsg, sMsg);
        BigDecimal p = new BigDecimal(getCurrentShowFromNum(cMsg, sMsg));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, p);
        NSAL0480CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0480CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private int getCurrentShowFromNum(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {
        if (sMsg.A.getValidCount() == 0) {
            cMsg.xxRadioBtn.clear();
            return 0;
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0480_ASMsg asMsg = sMsg.A.no(i);
            if (cMsg.t_MdlNm_P.getValue().equals(asMsg.t_MdlNm_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn //
                        , new BigDecimal(i % cMsg.A.length()));
                return ((i / cMsg.A.length()) * cMsg.A.length()) + 1;
            }
        }
        cMsg.xxRadioBtn.clear();
        return 1;
    }

    /**
     * doProcess_NSAL0480_INIT
     * @param cMsg NSAL0480CMsg
     * @param sMsg NSAL0480SMsg
     */
    private void doProcess_NSAL0480_INIT(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        if (checkInparam(cMsg)) {
            return;
        }

        NSAL0480CommonLogic.createPullDown(cMsg, getContextUserInfo().getUserId());

        // START 2016/04/06 [QC#5989, ADD]
        if (hasInParamsFromOtherScreen(cMsg)) {
            doProcess_NSAL0480_Search(cMsg, sMsg);
        }
        // END   2016/04/06 [QC#5989, ADD]
    }

    private boolean checkInparam(NSAL0480CMsg cMsg) {
        // no check
        return false;
    }

// START 2016/04/06 [QC#5989, ADD]
    private boolean hasInParamsFromOtherScreen(NSAL0480CMsg cMsg) {
        return ZYPCommonFunc.hasValue(cMsg.t_MdlNm_H) || ZYPCommonFunc.hasValue(cMsg.t_ItemCd_H) || ZYPCommonFunc.hasValue(cMsg.mtrGrpPk_H);
    }
// END   2016/04/06 [QC#5989, ADD]
}
