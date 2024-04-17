package business.blap.NLBL3090;

import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_FIRST_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_LAST_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_WH_MGR_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLAM0001E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;
import static business.blap.NLBL3090.constant.NLBL3090Constant.ZZM9037E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1346E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL3090.common.NLBL3090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 * 04/12/2016   CSAI            D.Fukaya        Update          QC#6088
 *</pre>
 */
public class NLBL3090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (("NLBL3090Scrn00_Search".equals(screenAplID)) || ("NLBL3090Scrn00_TAB_Assign".equals(screenAplID))) {
                doProcess_NLBL3090Scrn00_Search((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_PageNext((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_PagePrev((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if (("NLBL3090_INIT".equals(screenAplID)) || ("NLBL3090Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NLBL3090_INIT((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_AddNewMgr".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_AddNewMgr((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_AddNewSupr".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_AddNewSupr((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_AddNewCoord".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_AddNewCoord((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_AddNewST".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_AddNewST((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_Search_WH".equals(screenAplID) || "NLBL3090_NPAL1010".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search_WH((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3090_INIT((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090_NWAL1130".equals(screenAplID)) {
                doProcess_NLBL3090_NWAL1130((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_Search_MgrNm".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search_MgrNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_Search_SupvNm".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search_SupvNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_Search_SchdCoordNm".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_Search_SchdCoordNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else if ("NLBL3090Scrn00_TAB_Coordination".equals(screenAplID)) {
                doProcess_NLBL3090Scrn00_CoordinationTab((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090_INIT(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        sMsg.clear();
        cMsg.clear();
    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_Search(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {

        if (!NLBL3090CommonLogic.searchHeader(cMsg, getGlobalCompanyCode())) {
            return;
        }

        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            // Assign
            NLBL3090CommonLogic.searchAssign(cMsg, sMsg, getGlobalCompanyCode());
        } else {
            // Coordination
            NLBL3090CommonLogic.searchCoordination(cMsg, sMsg, getGlobalCompanyCode(), false, null);
        }
    }

    /**
     * <pre>
     * Coordination Tab Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_CoordinationTab(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {

        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);

        if (!NLBL3090CommonLogic.searchHeader(cMsg, getGlobalCompanyCode())) {
            return;
        }

        NLBL3090CommonLogic.setCheckBoxForAssignTab(sMsg);
        List<Map> schdCoordPsnCdList = NLBL3090CommonLogic.getSchdCoordPsnCdListSelectedInAssignTab(sMsg);
        List<String> coordPsnCdList = new ArrayList<String>();
        List<Map> addCoordPsnList = new ArrayList<Map>();

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        for (Map<String, String> selPsn : schdCoordPsnCdList) {
            BigDecimal resultCnt = NLBL3090CommonLogic.getCoordinationCount(cMsg, sMsg, getGlobalCompanyCode(), selPsn.get("coordPsnCd"));
            if (BigDecimal.ZERO.compareTo(resultCnt) == 0) {
                addCoordPsnList.add(selPsn);
            } else {
                coordPsnCdList.add(selPsn.get("coordPsnCd"));
            }
        }

        if (coordPsnCdList.size() != 0) {
            NLBL3090CommonLogic.searchCoordination(cMsg, sMsg, getGlobalCompanyCode(), true, coordPsnCdList);
        }

        for (Map<String, String> addCoordPsnInfo : addCoordPsnList) {
            NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
            addNewCoordCoordination(cMsg, sMsg, true, addCoordPsnInfo);
        }
        cMsg.xxPageShowFromNum_P2.setValue(BigDecimal.ONE);
        NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);

    }

    /**
     * <pre>
     * Add new row Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_AddNewMgr(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
        NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
        /***********************************/
        /***   Master Existence check   ****/
        /***********************************/
        if (!NLBL3090CommonLogic.searchHeader(cMsg, getGlobalCompanyCode())) {
            return;
        }
        /*****************************/
        /***   Max Record check   ****/
        /*****************************/
        int index = sMsg.A.getValidCount();
        if (sMsg.A.length() <= index) {
            return;
        }
        /******************************/
        /***   Duplication check   ****/
        /******************************/
        // Check Detail
        int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
            return;
        }
//        // Check Header
//        if (!NLBL3090CommonLogic.checkDuplicatedMgrPsnCdForAdd(sMsg, cMsg.mgrPsnCd_H1)) {
//            cMsg.setMessageInfo(ZZM9037E);
//            return;
//        }
        /***************************/
        /***   Add New Record   ****/
        /***************************/
        sMsg.A.no(index).clear();
        sMsg.A.no(index).xxNewRowNum_A1.setValue(-1);
        int newMgNo = getMaxMgNo(sMsg.A);
        sMsg.A.no(index).xxNewRowNum_A2.setValue(newMgNo + 1);
        sMsg.A.no(index).xxNewRowNum_A3.setValue(1);
        sMsg.A.no(index).xxNewRowNum_A4.setValue(1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhCd_A, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mgrPsnCd_AM, cMsg.mgrPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).supvPsnCd_AS, cMsg.supvPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).schdCoordPsnCd_AC, cMsg.schdCoordPsnCd_H1);
        sMsg.A.setValidCount(index + 1);
        cMsg.xxPageShowFromNum_P1.setValue(index + 1);
        NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
    }

    /**
     * <pre>
     * Add new row Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_AddNewSupr(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
        NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
        /***********************************/
        /***   Master Existence check   ****/
        /***********************************/
        if (!NLBL3090CommonLogic.searchHeader(cMsg, getGlobalCompanyCode())) {
            return;
        }
        /*****************************/
        /***   Max Record check   ****/
        /*****************************/
        if (sMsg.A.length() <= sMsg.A.getValidCount()) {
            return;
        }
        /*********************************/
        /***   Get Index for Insert   ****/
        /*********************************/
        int insertIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_AM.getValue())) {
                String mgrPsnCd = sMsg.A.no(i).mgrPsnCd_AM.getValue();
                for (int j = i; j < sMsg.A.getValidCount(); j++) {
                    if (j != i) {
                        if (!mgrPsnCd.equals(sMsg.A.no(j).mgrPsnCd_AM.getValue())) {
                            insertIndex = j - 1;
                            break;
                        }
                    }
                    if (sMsg.A.getValidCount() <= (j + 1)) {
                        insertIndex = j;
                    }
                }
                break;
            }
        }
        if (0 <= insertIndex) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(insertIndex).mgrPsnCd_AM)) {
                sMsg.A.no(insertIndex).mgrPsnCd_AM.setErrorInfo(1, NLAM0001E);
                cMsg.setMessageInfo(ZZM9037E);
            } else {
                /******************************/
                /***   Duplication check   ****/
                /******************************/
                // Check Detail
                int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
                if (firstErrIndex != -1) {
                    cMsg.setMessageInfo(ZZM9037E);
                    cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
                    NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
                    return;
                }
//                // Check Header
//                if (!NLBL3090CommonLogic.checkDuplicatedSupvPsnCdForAdd(sMsg, sMsg.A.no(insertIndex).mgrPsnCd_AM.getValue(), cMsg.supvPsnCd_H1)) {
//                    cMsg.setMessageInfo(ZZM9037E);
//                    return;
//                }

                /***************************/
                /***   Add New Record   ****/
                /***************************/
                int newSvNo = getMaxSvNo(sMsg.A, sMsg.A.no(insertIndex).xxNewRowNum_A2.getValueInt());
                moveShiftRow(sMsg.A, insertIndex + 1);
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A1.setValue(-1);
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A2.setValue(sMsg.A.no(insertIndex).xxNewRowNum_A2.getValue());
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A3.setValue(newSvNo + 1);
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A4.setValue(1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).rtlWhCd_A, sMsg.A.no(insertIndex).rtlWhCd_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).mgrPsnCd_AM, sMsg.A.no(insertIndex).mgrPsnCd_AM);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).supvPsnCd_AS, cMsg.supvPsnCd_H1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).schdCoordPsnCd_AC, cMsg.schdCoordPsnCd_H1);
                cMsg.xxPageShowFromNum_P1.setValue(insertIndex + 2);
            }
        }
        NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
    }

    /**
     * <pre>
     * Add new row Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_AddNewCoord(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {

        if (!NLBL3090CommonLogic.searchHeader(cMsg, getGlobalCompanyCode())) {
            return;
        }

        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
            addNewCoordAssign(cMsg, sMsg);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);

        } else {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
            addNewCoordCoordination(cMsg, sMsg, false, null);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
        }
    }

    private void addNewCoordAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        /*****************************/
        /***   Max Record check   ****/
        /*****************************/
        if (sMsg.A.length() <= sMsg.A.getValidCount()) {
            return;
        }
        /*********************************/
        /***   Get Index for Insert   ****/
        /*********************************/
        int insertIndex = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_AS.getValue())) {
                String mgrPsnCd = sMsg.A.no(i).mgrPsnCd_AM.getValue();
                String supvPsnCd = sMsg.A.no(i).supvPsnCd_AS.getValue();
                for (int j = i; j < sMsg.A.getValidCount(); j++) {
                    if (j != i) {
                        if (!mgrPsnCd.equals(sMsg.A.no(j).mgrPsnCd_AM.getValue())) {
                            insertIndex = j - 1;
                            break;
                        }
                        if (!supvPsnCd.equals(sMsg.A.no(j).supvPsnCd_AS.getValue())) {
                            insertIndex = j - 1;
                            break;
                        }
                    }
                    if (sMsg.A.getValidCount() <= (j + 1)) {
                        insertIndex = j;
                    }
                }
                break;
            }
        }
        if (0 <= insertIndex) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(insertIndex).supvPsnCd_AS)) {
                sMsg.A.no(insertIndex).supvPsnCd_AS.setErrorInfo(1, NLAM0001E);
                cMsg.setMessageInfo(ZZM9037E);
            } else {
                /******************************/
                /***   Duplication check   ****/
                /******************************/
                // Check Detail
                int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
                if (firstErrIndex != -1) {
                    cMsg.setMessageInfo(ZZM9037E);
                    cMsg.xxPageShowFromNum_P1.setValue(firstErrIndex);
                    return;
                }
//                // Check Header
//                if (!NLBL3090CommonLogic.checkDuplicatedSchdCoodPsnCdForAdd(sMsg, sMsg.A.no(insertIndex).mgrPsnCd_AM.getValue(), sMsg.A.no(insertIndex).supvPsnCd_AS.getValue(), cMsg.schdCoordPsnCd_H1)) {
//                    cMsg.setMessageInfo(ZZM9037E);
//                    return;
//                }

                /***************************/
                /***   Add New Record   ****/
                /***************************/
                int newCoNo = getMaxCoNo(sMsg.A, sMsg.A.no(insertIndex).xxNewRowNum_A2.getValueInt(), sMsg.A.no(insertIndex).xxNewRowNum_A3.getValueInt());
                moveShiftRow(sMsg.A, insertIndex + 1);
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A1.setValue(-1);
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A2.setValue(sMsg.A.no(insertIndex).xxNewRowNum_A2.getValue());
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A3.setValue(sMsg.A.no(insertIndex).xxNewRowNum_A3.getValue());
                sMsg.A.no(insertIndex + 1).xxNewRowNum_A4.setValue(newCoNo + 1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).rtlWhCd_A, sMsg.A.no(insertIndex).rtlWhCd_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).mgrPsnCd_AM, sMsg.A.no(insertIndex).mgrPsnCd_AM);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).supvPsnCd_AS, sMsg.A.no(insertIndex).supvPsnCd_AS);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(insertIndex + 1).schdCoordPsnCd_AC, cMsg.schdCoordPsnCd_H1);
                cMsg.xxPageShowFromNum_P1.setValue(insertIndex + 2);
            }
        }
    }

    private void addNewCoordCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg, boolean coordinationTabFlg, Map<String, String> addCoordPsnInfo) {
        /*********************************/
        /***   Get Index for Insert   ****/
        /*********************************/
        int index = sMsg.B.getValidCount();
        if (sMsg.B.length() <= index) {
            return;
        }
        /******************************/
        /***   Duplication check   ****/
        /******************************/
        // Check Detail
        int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForCoordinationTab(sMsg);
        if (firstErrIndex != -1) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.xxPageShowFromNum_P2.setValue(firstErrIndex);
            return;
        }
        /***************************/
        /***   Add New Record   ****/
        /***************************/
        sMsg.B.no(index).clear();
        sMsg.B.no(index).xxNewRowNum_B1.setValue(-1);
        int newCoNo = getMaxCoNo(sMsg.B);
        sMsg.B.no(index).xxNewRowNum_B2.setValue(newCoNo + 1);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).rtlWhCd_B, cMsg.rtlWhCd_H1);
        if (coordinationTabFlg) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).schdCoordPsnCd_BC, addCoordPsnInfo.get("coordPsnCd"));
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).fullPsnNm_BC, addCoordPsnInfo.get("coordPsnNm"));
        }
        // Set from date
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(index).effFromDt_B, ZYPDateUtil.getSalesDate());
        sMsg.B.setValidCount(index + 1);
        cMsg.xxPageShowFromNum_P2.setValue(index + 1);
    }

    /**
     * <pre>
     * Add new row Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_AddNewST(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
        NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
        if (sMsg.B.length() <= sMsg.B.getValidCount()) {
            return;
        }
        int insertIndex = -1;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxChkBox_BC.getValue())) {
                String schdCoordPsnCd = sMsg.B.no(i).schdCoordPsnCd_BC.getValue();
                for (int j = i; j < sMsg.B.getValidCount(); j++) {
                    if (j != i) {
                        if (!schdCoordPsnCd.equals(sMsg.B.no(j).schdCoordPsnCd_BC.getValue())) {
                            insertIndex = j - 1;
                            break;
                        }
                    }
                    if (sMsg.B.getValidCount() <= (j + 1)) {
                        insertIndex = j;
                    }
                }
                break;
            }
        }
        if (0 <= insertIndex) {
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(insertIndex).schdCoordPsnCd_BC)) {
                sMsg.B.no(insertIndex).schdCoordPsnCd_BC.setErrorInfo(1, NLAM0001E);
                cMsg.setMessageInfo(ZZM9037E);
            } else {
                moveShiftRow(sMsg.B, insertIndex + 1);
                sMsg.B.no(insertIndex + 1).xxNewRowNum_B1.setValue(-1);
                sMsg.B.no(insertIndex + 1).xxNewRowNum_B2.setValue(sMsg.B.no(insertIndex).xxNewRowNum_B2.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(insertIndex + 1).rtlWhCd_B, sMsg.B.no(insertIndex).rtlWhCd_B);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(insertIndex + 1).schdCoordPsnCd_BC, sMsg.B.no(insertIndex).schdCoordPsnCd_BC);
                // Set from date
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(insertIndex + 1).effFromDt_B, ZYPDateUtil.getSalesDate());
                cMsg.xxPageShowFromNum_P1.setValue(insertIndex + 2);
            }
        }
        NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
    }

    private void moveShiftRow(NLBL3090_ASMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        if (table.length() < table.getValidCount() + 1) {
            return;
        }
        for (int i = table.getValidCount(); index < i; i--) {
            EZDMsg.copy(table.no(i - 1), null, table.no(i), null);
        }
        table.no(index).clear();
        table.setValidCount(table.getValidCount() + 1);
    }

    private void moveShiftRow(NLBL3090_BSMsgArray table, int index) {
        if (index < 0) {
            return;
        }
        if (table.length() < table.getValidCount() + 1) {
            return;
        }
        for (int i = table.getValidCount(); index < i; i--) {
            EZDMsg.copy(table.no(i - 1), null, table.no(i), null);
        }
        table.no(index).clear();
        table.setValidCount(table.getValidCount() + 1);
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_PagePrev(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
            /******************************/
            /***   Duplication check   ****/
            /******************************/
            int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
            if (firstErrIndex != -1) {
                cMsg.setMessageInfo(ZZM9037E);
                NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
                return;
            }
            /******************************/
            /***   Pagination          ****/
            /******************************/
            cMsg.xxPageShowFromNum_P1.setValue(cMsg.xxPageShowFromNum_P1.getValueInt() - cMsg.A.length());
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);

        } else {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
            /******************************/
            /***   Duplication check   ****/
            /******************************/
            int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForCoordinationTab(sMsg);
            if (firstErrIndex != -1) {
                cMsg.setMessageInfo(ZZM9037E);
                NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
                return;
            }
            /******************************/
            /***   Pagination          ****/
            /******************************/
            cMsg.xxPageShowFromNum_P2.setValue(cMsg.xxPageShowFromNum_P2.getValueInt() - cMsg.B.length());
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_PageNext(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsAssignTab(sMsg);
            /******************************/
            /***   Duplication check   ****/
            /******************************/
            int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForAssignTab(sMsg);
            if (firstErrIndex != -1) {
                cMsg.setMessageInfo(ZZM9037E);
                NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
                return;
            }
            /******************************/
            /***   Pagination          ****/
            /******************************/
            cMsg.xxPageShowFromNum_P1.setValue(cMsg.xxPageShowFromNum_P1.getValueInt() + cMsg.A.length());
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);

        } else {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
            NLBL3090CommonLogic.updateValueForInvisibleFieldsCoordinationTab(sMsg);
            /******************************/
            /***   Duplication check   ****/
            /******************************/
            int firstErrIndex = NLBL3090CommonLogic.checkDuplicationForCoordinationTab(sMsg);
            if (firstErrIndex != -1) {
                cMsg.setMessageInfo(ZZM9037E);
                NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
                return;
            }
            /******************************/
            /***   Pagination          ****/
            /******************************/
            cMsg.xxPageShowFromNum_P2.setValue(cMsg.xxPageShowFromNum_P2.getValueInt() + cMsg.B.length());
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Search WH Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_Search_WH(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (TAB_ASSIGN.equals(cMsg.xxDplyTab.getValue())) {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);
            searchWh(cMsg);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
        } else {
            NLBL3090CommonLogic.copyFromCmsgOntoSmsgCoordination(cMsg, sMsg);
            searchWh(cMsg);
            NLBL3090CommonLogic.copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
        }
//        ZYPEZDItemValueSetter.setValue(cMsg.mgrPsnCd_H1, cMsg.psnCd_H1);
//        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HM, cMsg.fullPsnNm_H1);
    }

    private void searchWh(NLBL3090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_H1);

        // Execute
        S21SsmEZDResult result = NLBL3090Query.getInstance().searchWh(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, (String) recode.get(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H1, (String) recode.get(DB_COLUMN_WH_MGR_PSN_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_H1, NLBL3090CommonLogic.connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM), (String) recode.get(DB_COLUMN_PSN_LAST_NM)));
        } else {
            cMsg.rtlWhCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Warehouse Code"});
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.rtlWhNm_H1.clear();
            cMsg.psnCd_H1.clear();
            cMsg.fullPsnNm_H1.clear();
        }
    }

    /**
     * <pre>
     * Search Manager Name Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_Search_MgrNm(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd_H1)) {
            String mgrNm = NLBL3090CommonLogic.getPsnNm(cMsg.mgrPsnCd_H1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(mgrNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HM, mgrNm);
            } else {
                cMsg.fullPsnNm_HM.clear();
                cMsg.mgrPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Manager Code"});
                cMsg.setMessageInfo(ZZM9037E);
            }
        }
    }

    /**
     * <pre>
     * Search Supervisor Name Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_Search_SupvNm(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd_H1)) {
            String supvNm = NLBL3090CommonLogic.getPsnNm(cMsg.supvPsnCd_H1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(supvNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HS, supvNm);
            } else {
                cMsg.fullPsnNm_HS.clear();
                cMsg.supvPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Supervisor Code"});
                cMsg.setMessageInfo(ZZM9037E);
            }
        }
    }

    /**
     * <pre>
     * Search Coordinator Name Event
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090Scrn00_Search_SchdCoordNm(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_H1)) {
            String schdCoordNm = NLBL3090CommonLogic.getPsnNm(cMsg.schdCoordPsnCd_H1.getValue(), getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(schdCoordNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HC, schdCoordNm);
            } else {
                cMsg.fullPsnNm_HC.clear();
                cMsg.schdCoordPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Coordinator Code"});
                cMsg.setMessageInfo(ZZM9037E);
            }
        }
    }

    private int getMaxMgNo(NLBL3090_ASMsgArray table) {
        int ret = 0;
        for (int i = 0; i < table.getValidCount(); i++) {
            if (ret < table.no(i).xxNewRowNum_A2.getValueInt()) {
                ret = table.no(i).xxNewRowNum_A2.getValueInt();
            }
        }
        return ret;
    }

    private int getMaxSvNo(NLBL3090_ASMsgArray table, int mgNo) {
        int ret = 0;
        for (int i = 0; i < table.getValidCount(); i++) {
            if (mgNo == table.no(i).xxNewRowNum_A2.getValueInt()) {
                if (ret < table.no(i).xxNewRowNum_A3.getValueInt()) {
                    ret = table.no(i).xxNewRowNum_A3.getValueInt();
                }
            }
        }
        return ret;
    }

    private int getMaxCoNo(NLBL3090_ASMsgArray table, int mgNo, int svNo) {
        int ret = 0;
        for (int i = 0; i < table.getValidCount(); i++) {
            if (mgNo == table.no(i).xxNewRowNum_A2.getValueInt() && svNo == table.no(i).xxNewRowNum_A3.getValueInt()) {
                if (ret < table.no(i).xxNewRowNum_A4.getValueInt()) {
                    ret = table.no(i).xxNewRowNum_A4.getValueInt();
                }
            }
        }
        return ret;
    }

    private int getMaxCoNo(NLBL3090_BSMsgArray table) {
        int ret = 0;
        for (int i = 0; i < table.getValidCount(); i++) {
            if (ret < table.no(i).xxNewRowNum_B2.getValueInt()) {
                ret = table.no(i).xxNewRowNum_B2.getValueInt();
            }
        }
        return ret;
    }

    /**
     * <pre>
     * Return Process from NWAL1130
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    private void doProcess_NLBL3090_NWAL1130(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String scrEventNm = cMsg.xxScrEventNm_X.getValue();
        String psnNm = "";

        if ("OpenWin_S21PersonM".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(cMsg.xxNum.getValueInt()).mgrPsnCd_AM)) {
                psnNm = NLBL3090CommonLogic.getPsnNm(cMsg.A.no(cMsg.xxNum.getValueInt()).mgrPsnCd_AM.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).fullPsnNm_AM, psnNm);
            }

        } else if ("OpenWin_S21PersonS".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(cMsg.xxNum.getValueInt()).supvPsnCd_AS)) {
                psnNm = NLBL3090CommonLogic.getPsnNm(cMsg.A.no(cMsg.xxNum.getValueInt()).supvPsnCd_AS.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).fullPsnNm_AS, psnNm);
            }

        } else if ("OpenWin_CdPerson".equals(scrEventNm)) {
            if ("AC".equals(cMsg.xxPopPrm_X.getValue())) {
                psnNm = NLBL3090CommonLogic.getPsnNm(cMsg.A.no(cMsg.xxNum.getValueInt()).schdCoordPsnCd_AC.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).fullPsnNm_AC, psnNm);

            } else if ("BC".equals(cMsg.xxPopPrm_X.getValue())) {
                psnNm = NLBL3090CommonLogic.getPsnNm(cMsg.B.no(cMsg.xxNum.getValueInt()).schdCoordPsnCd_BC.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(cMsg.xxNum.getValueInt()).fullPsnNm_BC, psnNm);
            }

        } else if ("OpenWin_Carrier".equals(scrEventNm)) {
            String carrNm = NLBL3090CommonLogic.getCarrNm(cMsg.B.no(cMsg.xxNum.getValueInt()).carrCd_B.getValue(), glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(cMsg.xxNum.getValueInt()).locNm_B, carrNm);

        } else if ("OpenWin_Wh".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {
                doProcess_NLBL3090Scrn00_Search_WH(cMsg, sMsg);
            }
        } else if ("NLBL3090Scrn00_OpenWin_Manager".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd_H1)) {
                doProcess_NLBL3090Scrn00_Search_MgrNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);
            }
        } else if ("NLBL3090Scrn00_OpenWin_Supervisor".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd_H1)) {
                doProcess_NLBL3090Scrn00_Search_SupvNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);
            }
        } else if ("NLBL3090Scrn00_OpenWin_Coordinator".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_H1)) {
                doProcess_NLBL3090Scrn00_Search_SchdCoordNm((NLBL3090CMsg) cMsg, (NLBL3090SMsg) sMsg);
            }
        }
    }
}
