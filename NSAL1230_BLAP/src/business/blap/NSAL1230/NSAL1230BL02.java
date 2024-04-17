/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1230;

import static business.blap.NSAL1230.constant.NSAL1230Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1230.common.NSAL1230CommonLogic;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#2728
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1230CMsg cMsg = (NSAL1230CMsg) arg0;
        NSAL1230SMsg sMsg = (NSAL1230SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1230_INIT".equals(screenAplID)) {
                doProcess_NSAL1230_INIT(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1230Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1230Scrn00_CMN_Reset(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_Add".equals(screenAplID)) {
                doProcess_NSAL1230_Add(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSAL1230_Delete(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_Open_Win_GlComPop".equals(screenAplID)) {
                doProcess_NSAL1230Scrn00_Open_Win_GlComPop(cMsg, sMsg);

            } else if ("NSAL1230_NMAL2550".equals(screenAplID)) {
                doProcess_NSAL1230_NMAL2550(cMsg, sMsg);

            } else if ("NSAL1230Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1230_Search(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1230_INIT(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL1230Scrn00_CMN_Submit(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL1230Scrn00_CMN_Reset(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // QC#23378(Sol#320) Add Start
    private void doProcess_NSAL1230_Search(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        init(cMsg, sMsg);
    }
    // QC#23378(Sol#320) Add End

    private void doProcess_NSAL1230_Add(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        int currentRowCount = cMsg.A.getValidCount();
        if (cMsg.A.length() < currentRowCount + 1) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Segment", String.valueOf(cMsg.A.length()) });
            return;
        }

        cMsg.A.setValidCount(currentRowCount + 1);
        NSAL1230CommonLogic.setEmptyRecord(getGlobalCompanyCode(), cMsg, currentRowCount);
    }

    private void doProcess_NSAL1230_Delete(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, XX_CHK_BOX_A1, FLG_ON_Y);
        if (!selectedRows.isEmpty()) {
            ZYPTableUtil.deleteRows(cMsg.A, selectedRows);
        }
    }

    private void doProcess_NSAL1230Scrn00_Open_Win_GlComPop(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        int selRowNum = cMsg.xxRowNum.getValueInt();
        // START 2016/03/23 Y.Takeno [QC#2728, ADD]
        if (!NSAL1230CommonLogic.validateSegmentString(getGlobalCompanyCode(), cMsg, selRowNum)) {
            return;
        }
        // END   2016/03/23 Y.Takeno [QC#2728, ADD]
        NSAL1230CommonLogic.clear9Segments(cMsg.A.no(selRowNum));
        NSAL1230CommonLogic.reflectSegmentStringTo9Segments(cMsg.A.no(selRowNum));
    }

    private void doProcess_NSAL1230_NMAL2550(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        int selRowNum = cMsg.xxRowNum.getValueInt();
        NSAL1230CommonLogic.updateSegmentString(getGlobalCompanyCode(), cMsg, selRowNum);
        // QC#23378(Sol#320) Add Start
        String coaAcctCd = cMsg.A.no(selRowNum).coaAcctCd_A1.getValue();
        String svcAcctDistCatgNm = NSAL1230Query.getInstance().getSvcAcctDistCatgNm(getGlobalCompanyCode(), coaAcctCd);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(selRowNum).coaAcctDescTxt_A1, NSAL1230CommonLogic.getSvcAcctDistCatgNm(svcAcctDistCatgNm));
        // QC#23378(Sol#320) Add Start
    }

    private void init(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        // QC#23378(Sol#320) Mod Start
        List<Map<String, Object>> resultArray = findContractSegmentAllocationList(cMsg, sMsg);
        DS_CONTR_DTLTMsg dsContrDtlInfo =  findDsContrDtl(cMsg, sMsg);
        NSAL1230CommonLogic.initMsgs(getGlobalCompanyCode(), cMsg, sMsg, resultArray, dsContrDtlInfo);
        // QC#23378(Sol#320) Mod End
        // add start 2016/10/05 CSA Defect#13815
        if (cMsg.A.getValidCount() == 0) {
            DS_CONTR_BR_ALLOCTMsgArray brAllocTMsgArray = findContractBranchAllocationList(cMsg, sMsg);
            NSAL1230CommonLogic.initMsgsByContrBrAlloc(getGlobalCompanyCode(), cMsg, sMsg, brAllocTMsgArray);
        }
        // add end 2016/10/05 CSA Defect#13815
    }

    // QC#23378(Sol#320) Add Start
    private DS_CONTR_DTLTMsg findDsContrDtl(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {
        DS_CONTR_DTLTMsg findKeyTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(findKeyTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(findKeyTMsg.dsContrDtlPk, cMsg.dsContrDtlPk.getValue());
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(findKeyTMsg);
    }
    // QC#23378(Sol#320) Add End

    private List<Map<String, Object>> findContractSegmentAllocationList(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {

        // QC#23378(Sol#320) Mod Start
        S21SsmEZDResult res = NSAL1230Query.getInstance().findContractSegmentAllocationList(
            getGlobalCompanyCode(), cMsg.dsContrPk.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.svcInvChrgTpCd.getValue());
        List<Map<String, Object>> resultDsContrSegAllocList = (List) res.getResultObject();
        return resultDsContrSegAllocList;

//        DS_CONTR_SEG_ALLOCTMsgArray dsContrSegAllocTMsgAry = new DS_CONTR_SEG_ALLOCTMsgArray();
//        dsContrSegAllocTMsgAry.setMsgList(resultDsContrSegAllocList.toArray(new EZDTMsg[0]));
//        dsContrSegAllocTMsgAry.setValidCount(resultDsContrSegAllocList.size());
//
//        return dsContrSegAllocTMsgAry;
        // QC#23378(Sol#320) Mod End

// QC#23378(Sol#320) Comment Out Start
//        DS_CONTR_SEG_ALLOCTMsg findKeyTMsg = new DS_CONTR_SEG_ALLOCTMsg();
//
//        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue()) && ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd.getValue())) {
//            findKeyTMsg.setSQLID("001");
//            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
//            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
//            findKeyTMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());
//
//        } else if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue())) {
//            findKeyTMsg.setSQLID("002");
//            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
//            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
//
//        } else {
//            findKeyTMsg.setSQLID("003");
//            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
//        }
//
//        return (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);
// QC#23378(Sol#320) Comment Out End
    }

    // add start 2016/10/05 CSA Defect#13815
    private DS_CONTR_BR_ALLOCTMsgArray findContractBranchAllocationList(NSAL1230CMsg cMsg, NSAL1230SMsg sMsg) {

        DS_CONTR_BR_ALLOCTMsg findKeyTMsg = new DS_CONTR_BR_ALLOCTMsg();
        DS_CONTR_BR_ALLOCTMsgArray tMsgArray = new DS_CONTR_BR_ALLOCTMsgArray();

        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue()) && ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd.getValue())) {
            findKeyTMsg.setSQLID("001");
            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
            findKeyTMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());

            tMsgArray = (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);
            if (tMsgArray.getValidCount() > 0) {
                return tMsgArray;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue())) {
            findKeyTMsg.setSQLID("002");
            findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
            findKeyTMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());

            tMsgArray = (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);
            if (tMsgArray.getValidCount() > 0) {
                return tMsgArray;
            }

        }

        findKeyTMsg.setSQLID("003");
        findKeyTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        findKeyTMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());

        return (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(findKeyTMsg);
    }
    // add end 2016/10/05 CSA Defect#13815
}
