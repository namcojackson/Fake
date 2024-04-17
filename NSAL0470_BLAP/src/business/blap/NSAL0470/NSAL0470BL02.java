/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0470;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0470.common.NSAL0470CommonLogic;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import static business.blap.NSAL0470.constant.NSAL0470Constant.*;
import business.file.NSAL0470F00FMsg;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         K.Yamada        Create          N/A
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3050
 * 2016/04/21   Hitachi         A.Kohinata      Update          QC1088
 * 2016/06/10   Hitachi         M.Gotou         Update          QC9699
 * 2016/07/26   Hitachi         K.Kasai         Update          QC18882
 * 2020/03/09   CITS            T.Wada          Update          QC#55830
 *</pre>
 */
public class NSAL0470BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL0470CMsg bizMsg = (NSAL0470CMsg) cMsg;
            NSAL0470SMsg shareMsg = (NSAL0470SMsg) sMsg;

            if ("NSAL0470_INIT".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_init(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_Search(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_Collapse".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_Collapse(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_AllCollapse".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_AllCollapse(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_Expand".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_Expand(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_AllExpand".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_AllExpand(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_PageNext(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_PagePrev(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_CMN_Download(bizMsg, shareMsg);
            // START 2016/02/15 T.Aoyagi [QC3050, MOD]
            } else if ("NSAL0470Scrn00_ActivateContract".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_ActivateContract(bizMsg, shareMsg);
            } else if ("NSAL0470Scrn00_Override_Outcome".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_ActivateContract(bizMsg, shareMsg);
            // END 2016/02/15 T.Aoyagi [QC3050, MOD]
            // START 2016/04/21 [QC1088, ADD]
            } else if ("NSAL0470Scrn00_SendTo_SuperVisor".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_ActivateContract(bizMsg, shareMsg);
            // END 2016/04/21 [QC1088, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0470Scrn00_init(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        initMsg(cMsg, sMsg);

        // mod start 2016/06/10 CSA Defect#9699
        //createVldPullDown(cMsg);
        setOutcomeTxt(cMsg);
        // START 2016/04/21 [QC1088, ADD]
        setContrInfo(cMsg);
        // END 2016/04/21 [QC1088, ADD]
        createVldPullDown(cMsg);
        // mod end 2016/06/10 CSA Defect#9699

        // QC#55830 Add Start
        DS_CONTRTMsg dsContrTMsg = getDsContrHdr(getGlobalCompanyCode(), cMsg.dsContrNum.getValue());
        BigDecimal dsContrPk = null;
        if (ZYPCommonFunc.hasValue(dsContrTMsg.dsContrPk)) {
            dsContrPk = dsContrTMsg.dsContrPk.getValue();
        }
        if(!NSAL0470CommonLogic.callBllgSchdApiRecovMode(getGlobalCompanyCode(), cMsg, dsContrPk)){
            return;
        }
        // QC#55830 Add End

        // call contract validation API
        if (!callContrVldApi(cMsg)) {
            return;
        }

        doProcess_NSAL0470Scrn00_Search(cMsg, sMsg);
    }

    private void doProcess_NSAL0470Scrn00_Search(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        clearSearchResult(cMsg, sMsg);
        //get validation result
        if (!getVldRsltWrk(cMsg, sMsg)) {
            return;
        }
        createDispInfo(cMsg, sMsg);

        //check error or not validated result
        checkErrResult(cMsg, sMsg);

        setValue(cMsg.xxDplyCtrlFlg_AL, FLG_ON_Y);

        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL0470Scrn00_Collapse(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        changeDplyCntrl(cMsg, sMsg, FLG_OFF_N);
        createDispInfo(cMsg, sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NSAL0470Scrn00_AllCollapse(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        changeAllDplyCntrl(cMsg, sMsg, FLG_OFF_N);
        createDispInfo(cMsg, sMsg);
        setValue(cMsg.xxDplyCtrlFlg_AL, FLG_OFF_N);

        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL0470Scrn00_Expand(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        changeDplyCntrl(cMsg, sMsg, FLG_ON_Y);
        createDispInfo(cMsg, sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NSAL0470Scrn00_AllExpand(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        changeAllDplyCntrl(cMsg, sMsg, FLG_ON_Y);
        createDispInfo(cMsg, sMsg);
        setValue(cMsg.xxDplyCtrlFlg_AL, FLG_ON_Y);

        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL0470Scrn00_PageNext(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0470Scrn00_PagePrev(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0470CommonLogic.copySMsgToCMsg(cMsg, sMsg, pagenationFrom);
    }

    // START 2016/02/15 T.Aoyagi [QC3050, MOD]
    private void doProcess_NSAL0470Scrn00_ActivateContract(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
    // END 2016/02/15 T.Aoyagi [QC3050, MOD]

        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        if (hasValue(cMsg.getMessageKind()) && !"I".equals(cMsg.getMessageKind())) {
            return;
        }
        //END 2017/07/26 K.Kasai [QC#18882,ADD]
        clearSearchResult(cMsg, sMsg);
        setValue(cMsg.xxDplyCtrlFlg_AL, FLG_ON_Y);

    }

    private void doProcess_NSAL0470Scrn00_CMN_Download(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0470Query query = NSAL0470Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

            Map<String, Object> params = NSAL0470Query.getInstance().createFindCondMap(cMsg);
            params.put("rowNum", DOWNLOAD_LIMIT_CNT + 1);

            stmtSelect = ssmLLClient.createPreparedStatement("findContrVldRslt", params, execParam);
            rs = stmtSelect.executeQuery();
            NSAL0470F00FMsg fMsg = new NSAL0470F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

            final String[] csvHeader = new String[] {"Contract#", "Serial#", "Validation Description", "Outcome", "Message" };
            csvOutFile.writeHeader(csvHeader);

            // write contents
            while (rs.next()) {
                if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                    cMsg.setMessageInfo(NZZM0001W, null);
                    break;
                }

                // resultset -> fMsg
                setValue(fMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
                setValue(fMsg.serNum, rs.getString("SER_NUM"));
                setValue(fMsg.dsContrVldNm, rs.getString("DS_CONTR_VLD_NM"));
                setValue(fMsg.dsContrVldStsDescTxt, rs.getString("DS_CONTR_VLD_STS_DESC_TXT"));
                setValue(fMsg.dsContrVldRsltMsgTxt, rs.getString("DS_CONTR_VLD_RSLT_MSG_TXT"));

                csvOutFile.write();
            }

            csvOutFile.close();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private void changeDplyCntrl(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, String dplyCtrlFlg) {
        int rowNum = cMsg.xxRowNum.getValueInt();
        if (hasValue(cMsg.A.no(rowNum).svcMachMstrPk_A)) {
            changeDplyCntrlForMach(cMsg, sMsg, cMsg.A.no(rowNum).svcMachMstrPk_A.getValue(), dplyCtrlFlg);
        } else {
            changeDplyCntrlForContr(cMsg, sMsg, dplyCtrlFlg);
        }
    }

    private void changeAllDplyCntrl(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, String dplyCtrlFlg) {
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            setValue(sMsg.C.no(i).xxDplyCtrlFlg_C, dplyCtrlFlg);
        }
    }

    private void changeDplyCntrlForContr(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, String dplyCtrlFlg) {
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (hasValue(sMsg.C.no(i).dsContrPk_C)) {
                setValue(sMsg.C.no(i).xxDplyCtrlFlg_C, dplyCtrlFlg);
                break;
            }
        }
    }

    private void changeDplyCntrlForMach(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg
            , BigDecimal svcMachMstrPk, String dplyCtrlFlg) {
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (hasValue(sMsg.C.no(i).svcMachMstrPk_C)
                    && svcMachMstrPk.compareTo(sMsg.C.no(i).svcMachMstrPk_C.getValue()) == 0) {
                setValue(sMsg.C.no(i).xxDplyCtrlFlg_C, dplyCtrlFlg);
                break;
            }
        }
    }

    private void createVldPullDown(NSAL0470CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getContrVld(cMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> contrVldList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int idx = 0;
        for (Map<String, Object> contrVld : contrVldList) {
            // add start 2016/06/10 CSA Defect#9699
            if (!chkDsContrCtg(cMsg, contrVld)) {
                continue;
            }
            // add end 2016/06/10 CSA Defect#9699
            setValue(cMsg.dsContrVldPk_H1.no(idx), (BigDecimal) contrVld.get("DS_CONTR_VLD_PK"));
            setValue(cMsg.dsContrVldNm_H2.no(idx), (String) contrVld.get("DS_CONTR_VLD_NM"));
            idx++;
        }
    }

// add start 2016/06/10 CSA Defect#9699
    private boolean chkDsContrCtg(NSAL0470CMsg cMsg, Map<String, Object> contrVld) {
        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_ON_Y.equals((String) contrVld.get("VLD_REG_FLG"))) {
            return true;
        } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_ON_Y.equals((String) contrVld.get("VLD_FLEET_FLG"))) {
            return true;
        } else if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_ON_Y.equals((String) contrVld.get("VLD_AGGR_FLG"))) {
            return true;
        } else if (DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_ON_Y.equals((String) contrVld.get("VLD_WTY_FLG"))) {
            return true;
        }
        return false;
    }
// add end 2016/06/10 CSA Defect#9699

    private void setOutcomeTxt(NSAL0470CMsg cMsg) {
        setValue(cMsg.dsContrVldStsDescTxt_H1, getCodeName(DS_CONTR_VLD_STS.SUCCESS));
        setValue(cMsg.dsContrVldStsDescTxt_H2, getCodeName(DS_CONTR_VLD_STS.ERROR));
        setValue(cMsg.dsContrVldStsDescTxt_H3, getCodeName(DS_CONTR_VLD_STS.WARNING));
        setValue(cMsg.dsContrVldStsDescTxt_H4, getCodeName(DS_CONTR_VLD_STS.NOT_VALIDATED));
    }

    private String getCodeName(String cdValue) {
        return ZYPCodeDataUtil.getName(DS_CONTR_VLD_STS.class, getGlobalCompanyCode(), cdValue);
    }

    private void initMsg(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
        String dsContrNum = cMsg.dsContrNum.getValue();

        cMsg.clear();
        setValue(cMsg.dsContrNum, dsContrNum);

        clearSearchResult(cMsg, sMsg);
    }

    private void clearSearchResult(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
    }

    private boolean callContrVldApi(NSAL0470CMsg cMsg) {

        NSZC057001PMsg pMsg = new NSZC057001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(pMsg.dsContrNum, cMsg.dsContrNum);

        new NSZC057001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }

    private boolean getVldRsltWrk(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().findContrVldRslt(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return false;
        }
        if (ssmResult.getQueryResultCount() > sMsg.B.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        //create expand/collapse control info
        //xxDplyCtrlFlg_C Y: expand state N:collpase state
        //contract
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0470_BSMsg rsltWrk = sMsg.B.no(i);
            if (hasValue(rsltWrk.dsContrPk_B)) {
                setValue(sMsg.C.no(0).dsContrPk_C, sMsg.B.no(0).dsContrPk_B);
                setValue(sMsg.C.no(0).xxDplyCtrlFlg_C, FLG_ON_Y);
                break;
            }
        }

        //machine
        int idx = 1;
        List<BigDecimal> machList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0470_BSMsg rsltWrk = sMsg.B.no(i);
            if (hasValue(rsltWrk.svcMachMstrPk_B) && !machList.contains(rsltWrk.svcMachMstrPk_B.getValue())) {
                setValue(sMsg.C.no(idx).svcMachMstrPk_C, rsltWrk.svcMachMstrPk_B);
                setValue(sMsg.C.no(idx).xxDplyCtrlFlg_C, FLG_ON_Y);
                idx++;
                machList.add(rsltWrk.svcMachMstrPk_B.getValue());
            }
        }
        sMsg.C.setValidCount(idx);

        return true;

    }

    private void createDispInfo(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        //expand/collapse status of each machine
        Map<BigDecimal, String> expandMap = new HashMap<BigDecimal, String>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL0470_CSMsg expandInfo = sMsg.C.no(i);
            if (hasValue(expandInfo.svcMachMstrPk_C)) {
                expandMap.put(expandInfo.svcMachMstrPk_C.getValue(), expandInfo.xxDplyCtrlFlg_C.getValue());
            }
        }

        int idx = 0;
        //expand/collapse status of contract level
        String contrExpndFlg = sMsg.C.no(0).xxDplyCtrlFlg_C.getValue();

        //expand/collapse icon should be display only the first line of each Contract or Machine.
        boolean contrIconSet = false;
        List<BigDecimal> machineIconSetList = new ArrayList<BigDecimal>();

        boolean setSummaryContr = false;
        List<BigDecimal> setSummaryMach = new ArrayList<BigDecimal>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0470_BSMsg rsltWrk = sMsg.B.no(i);
            if (!hasValue(rsltWrk.svcMachMstrPk_B)) {
                if (FLG_ON_Y.equals(contrExpndFlg)) {
                    EZDMsg.copy(rsltWrk, "B", sMsg.A.no(idx), "A");
                    if (!contrIconSet) {
                        setValue(sMsg.A.no(idx).xxDplyCtrlFlg_A, FLG_ON_Y);
                        contrIconSet = true;
                    } else {
                        sMsg.A.no(idx).dsContrNum_A.clear();
                    }
                    idx++;
                } else {
                    if (!setSummaryContr) {
                        setSummaryInfoForContr(rsltWrk, sMsg, idx);
                        idx++;
                        setSummaryContr = true;
                    }
                }
            } else {
                String dplyFlg = expandMap.get(rsltWrk.svcMachMstrPk_B.getValue());
                if (FLG_ON_Y.equals(dplyFlg)) {
                    EZDMsg.copy(rsltWrk, "B", sMsg.A.no(idx), "A");
                    sMsg.A.no(idx).dsContrNum_A.clear();
                    if (!machineIconSetList.contains(rsltWrk.svcMachMstrPk_B.getValue())) {
                        setValue(sMsg.A.no(idx).xxDplyCtrlFlg_A, FLG_ON_Y);
                        machineIconSetList.add(rsltWrk.svcMachMstrPk_B.getValue());
                    }
                    idx++;
                } else {
                    if (!setSummaryMach.contains(rsltWrk.svcMachMstrPk_B.getValue())) {
                        setSummaryInfoForMachine(rsltWrk, sMsg, idx);
                        idx++;
                        setSummaryMach.add(rsltWrk.svcMachMstrPk_B.getValue());
                    }
                }
            }
        }
        sMsg.A.setValidCount(idx);
    }

    private void setSummaryInfoForContr(NSAL0470_BSMsg rsltWrk, NSAL0470SMsg sMsg, int idx) {

        setValue(sMsg.A.no(idx).dsContrPk_A, rsltWrk.dsContrPk_B);
        setValue(sMsg.A.no(idx).dsContrNum_A, rsltWrk.dsContrNum_B);

        boolean hasError = false;
        boolean hasWarn = false;
        boolean hasNotValidated = false;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            String vldSts = sMsg.B.no(i).dsContrVldStsCd_B.getValue();
            if (DS_CONTR_VLD_STS.ERROR.equals(vldSts)) {
                hasError = true;
                break;
            }
            if (DS_CONTR_VLD_STS.WARNING.equals(vldSts)) {
                hasWarn = true;
                continue;
            }
            if (DS_CONTR_VLD_STS.NOT_VALIDATED.equals(vldSts)) {
                hasNotValidated = true;
                continue;
            }
        }

        if (hasError) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.ERROR);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.ERROR));
        } else if (!hasError && hasWarn) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.WARNING);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.WARNING));
        } else if (!hasError && !hasWarn && !hasNotValidated) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.SUCCESS);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.SUCCESS));
        }
        setValue(sMsg.A.no(idx).xxDplyCtrlFlg_A, FLG_OFF_N);
    }

    private void setSummaryInfoForMachine(NSAL0470_BSMsg rsltWrk, NSAL0470SMsg sMsg, int idx) {

        BigDecimal svcMachMstrPk = rsltWrk.svcMachMstrPk_B.getValue();

        setValue(sMsg.A.no(idx).svcMachMstrPk_A, svcMachMstrPk);
        setValue(sMsg.A.no(idx).serNum_A, rsltWrk.serNum_B);

        boolean hasError = false;
        boolean hasWarn = false;
        boolean hasNotValidated = false;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!hasValue(sMsg.B.no(i).svcMachMstrPk_B)) {
                continue;
            }
            if (svcMachMstrPk.compareTo(sMsg.B.no(i).svcMachMstrPk_B.getValue()) != 0) {
                continue;
            }

            String vldSts = sMsg.B.no(i).dsContrVldStsCd_B.getValue();
            if (DS_CONTR_VLD_STS.ERROR.equals(vldSts)) {
                hasError = true;
                break;
            }
            if (DS_CONTR_VLD_STS.WARNING.equals(vldSts)) {
                hasWarn = true;
                continue;
            }
            if (DS_CONTR_VLD_STS.NOT_VALIDATED.equals(vldSts)) {
                hasNotValidated = true;
                continue;
            }
        }

        if (hasError) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.ERROR);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.ERROR));
        } else if (!hasError && hasWarn) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.WARNING);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.WARNING));
        } else if (!hasError && !hasWarn && !hasNotValidated) {
            setValue(sMsg.A.no(idx).dsContrVldStsCd_A, DS_CONTR_VLD_STS.SUCCESS);
            setValue(sMsg.A.no(idx).dsContrVldStsDescTxt_A, getCodeName(DS_CONTR_VLD_STS.SUCCESS));
        }
        setValue(sMsg.A.no(idx).xxDplyCtrlFlg_A, FLG_OFF_N);
    }

    private void checkErrResult(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().countErrResult(cMsg, sMsg);
        if (!ssmResult.isCodeNotFound()) {
            setValue(cMsg.xxErrFlg, FLG_ON_Y);
        } else {
            setValue(cMsg.xxErrFlg, FLG_OFF_N);
        }
    }

    // START 2016/04/21 [QC1088, ADD]
    private void setContrInfo(NSAL0470CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getDsContr(cMsg);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            setValue(cMsg.qltyAsrnHldPendApvlFlg, (String) resultMap.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
            setValue(cMsg.contrAdminPsnCd, (String) resultMap.get("CONTR_ADMIN_PSN_CD"));
            // add start 2016/06/10 CSA Defect#9699
            setValue(cMsg.dsContrCatgCd, (String) resultMap.get("DS_CONTR_CATG_CD"));
            // add end 2016/06/10 CSA Defect#9699
        }
    }
    // END 2016/04/21 [QC1088, ADD]

    // QC#55830 Add Start
    private DS_CONTRTMsg getDsContrHdr(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("002");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        dsContrTMsg.setConditionValue("dsContrSqNum01", "00");
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray != null && dsContrTMsgArray.getValidCount() > 0) {
            dsContrTMsg = dsContrTMsgArray.no(0);
        }
        return dsContrTMsg;
    }
    // QC#55830 Add End

}
