/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0490;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0490.common.NSAL0490CommonLogic;
import business.blap.NSAL0490.constant.NSAL0490Constant;
import business.db.DS_MDLTMsg;
import business.db.DS_MDL_CONFIGTMsg;
import business.db.DS_MDL_EOLTMsg;
import business.db.DS_MDL_SPLY_RELNTMsg;
import business.db.MDL_BO_IMPCTTMsg;
import business.db.MDL_NMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/13   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2015/11/30   Hitachi         K.Yamada        Update          CSA QC#960,1154
 * 2016/03/15   Hitachi         M.Gotou         Update          QC#5081
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#5407
 * 2016/05/09   Hitachi         A.Kohinata      Update          QC#8055
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447, 6675
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2017/09/05   Hitachi         Y.Takeno        Update          QC#20624
 * 2017/11/02   CITS            S.Katsuma       Update          SOL#170(QC#18624)
 * 2017/12/22   Hitachi         U.Kim           Update          QC#22448
 *</pre>
 */
public class NSAL0490BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
            NSAL0490SMsg glblMsg = (NSAL0490SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();
            String dplyTab = bizMsg.xxDplyTab.getValue();

            if ("NSAL0490Scrn00_CMN_Submit".equals(screenAplID)) {
                if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
                    doProcess_NSAL0490Scrn00_CMN_Submit_ItemConfig(bizMsg, glblMsg);
                } else if (NSAL0490Constant.TAB_SVC_RULES.equals(dplyTab)) {
                    doProcess_NSAL0490Scrn00_CMN_Submit_SvcRules(bizMsg, glblMsg);
                } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
                    doProcess_NSAL0490Scrn00_CMN_Submit_SupplyMap(bizMsg, glblMsg);
                // add start 2016/05/19 CSA Defect#447
                } else if (NSAL0490Constant.TAB_END_OF_LIFE.equals(dplyTab)) {
                    doProcess_NSAL0490Scrn00_CMN_Submit_EndOfLife(bizMsg, glblMsg);
                // add end 2016/05/19 CSA Defect#447
                //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
                } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
                    doProcess_NSAL0490Scrn00_CMN_Submit_Criticality(bizMsg, glblMsg);
                //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
                } else {
                    throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
                }

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    private void doProcess_NSAL0490Scrn00_CMN_Submit_Criticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {
        NSAL0490CommonLogic.saveCMsgToSMsgCriticality(cMsg, sMsg);
        if (!checkInputForCriticality(cMsg, sMsg)) {
            return;
        }
        doProcForCriticality(cMsg , sMsg);

        NSAL0490CommonLogic.getCriticalityData(cMsg, sMsg);

    }

    private void doProcForCriticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {
        // logical remove model data that is not included in cMsg data.
        boolean registFlg;
        S21SsmEZDResult ssmResult = NSAL0490Query.getInstance().getModelBoImpctDataList(cMsg.glblCmpyCd.getValue(), cMsg.mdlId.getValue());
        List<Map<String, Object>> modelDataList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (ssmResult.isCodeNormal() && modelDataList != null && modelDataList.size() > 0) {
            for (Map<String, Object> modelData : modelDataList) {
                registFlg = false;
                String targetMdseCd = (String) modelData.get("MDSE_CD");
                for (int i = 0; i < cMsg.D.getValidCount(); i++) {
                    if (cMsg.D.no(i).mdseCd_D.getValue().equals(targetMdseCd)) {
                        registFlg = true;
                        break;
                    }
                }
                if (!registFlg) {
                    MDL_BO_IMPCTTMsg mdlBoTMsg = new MDL_BO_IMPCTTMsg();
                    ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdlBoImpctPk, (BigDecimal) modelData.get("MDL_BO_IMPCT_PK"));
                    ZYPEZDItemValueSetter.setValue(mdlBoTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    mdlBoTMsg = (MDL_BO_IMPCTTMsg) EZDTBLAccessor.findByKeyForUpdate(mdlBoTMsg);
                    EZDTBLAccessor.logicalRemove(mdlBoTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdlBoTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0033E, new String[] {NSAL0490Constant.MDL_BO_IMPCT });
                        return;
                    }
                }
            }
        }
        //insert or update model data
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            MDL_BO_IMPCTTMsg mdlBoTMsg = new MDL_BO_IMPCTTMsg();
            ssmResult = NSAL0490Query.getInstance().getModelBoImpctPk(cMsg.glblCmpyCd.getValue(), cMsg.mdlId.getValue(), cMsg.D.no(i).mdseCd_D.getValue());
            if (ssmResult.isCodeNormal() && ZYPCommonFunc.hasValue((BigDecimal) ssmResult.getResultObject())) {
                ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdlBoImpctPk, (BigDecimal) ssmResult.getResultObject());
                ZYPEZDItemValueSetter.setValue(mdlBoTMsg.glblCmpyCd, cMsg.glblCmpyCd);

                mdlBoTMsg = (MDL_BO_IMPCTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(mdlBoTMsg);
                if (mdlBoTMsg == null){
                    mdlBoTMsg = new MDL_BO_IMPCTTMsg();
                    createNewMdlBoImpctTMsg(cMsg, i, mdlBoTMsg);
                    EZDTBLAccessor.insert(mdlBoTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdlBoTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.MDL_BO_IMPCT });
                        return;
                    }
                    return;
                }
                ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdlId, cMsg.mdlId);
                ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdseCd, cMsg.D.no(i).mdseCd_D);
                ZYPEZDItemValueSetter.setValue(mdlBoTMsg.boImpctTpCd, cMsg.D.no(i).backOrdImpctTpCd_D2);
                EZDTBLAccessor.update(mdlBoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdlBoTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.MDL_BO_IMPCT });
                    return;
                }
            } else {
                createNewMdlBoImpctTMsg(cMsg, i, mdlBoTMsg);
                EZDTBLAccessor.insert(mdlBoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdlBoTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.MDL_BO_IMPCT });
                    return;
                }
            }
        }
    }

    /**
     * create New MdlBoImpctTMsg
     * @param cMsg NSAL0490CMsg
     * @param i int
     * @param mdlBoTMsg MDL_BO_IMPCTTMsg
     */
    private void createNewMdlBoImpctTMsg(NSAL0490CMsg cMsg, int i, MDL_BO_IMPCTTMsg mdlBoTMsg) {
        ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdlBoImpctPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MDL_BO_IMPCT_SQ));
        ZYPEZDItemValueSetter.setValue(mdlBoTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdlId, cMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(mdlBoTMsg.mdseCd, cMsg.D.no(i).mdseCd_D);
        ZYPEZDItemValueSetter.setValue(mdlBoTMsg.boImpctTpCd, cMsg.D.no(i).backOrdImpctTpCd_D2);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * do process (Submit for Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Submit_ItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgItemConfig(cMsg, sMsg);

        if (checkInputForItemConfig(cMsg, sMsg)) {
            return;
        }

        if (!isEmptyLine(sMsg.A.no(0).prntMdseCd_A.getValue(), sMsg.A.no(0).effFromDt_A.getValue())) {
            if (checkCorrelateForItemConfig(cMsg, sMsg)) {
                return;
            }
        }

        // Header
        if (!ZYPCommonFunc.hasValue(cMsg.mdlId)) {
            if (!doDsMdlInsert(cMsg)) {
                return;
            }
        } else {
            if (!doDsMdlUpdate(cMsg)) {
                return;
            }
        }

        // Detail
        doDtlProcForItemConfig(cMsg, sMsg);
    }

    /**
     * do process (Submit for Service Rules)
     * @param cMsg NSAL0490CMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Submit_SvcRules(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        doDsMdlUpdate(cMsg);
    }

    /**
     * do process (Submit for Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcess_NSAL0490Scrn00_CMN_Submit_SupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        NSAL0490CommonLogic.saveCurrentPageToSMsgSupplyMap(cMsg, sMsg);

        if (!checkInputForSupplyMap(cMsg, sMsg)) {
            return;
        }

        if (!checkCorrelateForSupplyMap(cMsg, sMsg)) {
            return;
        }

        // insert or update
        doProcForSupplyMap(cMsg, sMsg);
    }

    // add start 2016/05/19 CSA Defect#447
    private void doProcess_NSAL0490Scrn00_CMN_Submit_EndOfLife(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        doDsMdlUpdate(cMsg);
        doDsMdlEol(cMsg);
    }
    // add end 2016/05/19 CSA Defect#447

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    private boolean checkInputForCriticality(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {
        boolean result = true;
        Map<String, String> targetMdseMap = new HashMap<String, String>();
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            NSAL0490_DCMsg dcMsg = cMsg.D.no(i);
            EZDCStringItem targetMdseCd = dcMsg.mdseCd_D;
            if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd.getValue())) {
                targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, NSAL0490Constant.MDSE });
                result = false;
            }
            if (NSAL0490CommonLogic.checkItemIsSetParent(cMsg, targetMdseCd.getValue())) {
                targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0029E);
                result = false;
            }
            if (targetMdseMap.containsKey(targetMdseCd.getValue())) {
                targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0324E, new String[] {NSAL0490Constant.MDSE_CD });
                result = false;
            }
            if (NSAL0490CommonLogic.isErrorMdseLenChk(cMsg.glblCmpyCd.getValue(), targetMdseCd.getValue())) {
                targetMdseCd.setErrorInfo(1, NSAL0490Constant.NSAM0460E);
                result = false;
            }
            targetMdseMap.put(targetMdseCd.getValue(), ZYPConstant.FLG_ON_1);
        }

        return result;
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * check Input for Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return false : No Error
     */
    private boolean checkInputForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.mdlId)) {
            if (NSAL0490Query.getInstance().isExistMdlNm(cMsg)) {
                cMsg.mdlNm.setErrorInfo(1, NSAL0490Constant.NSAM0079E, new String[] {NSAL0490Constant.MDL_NM });
                return true;
            }
        }

        if (!NSAL0490Query.getInstance().isExistMdlGrp(cMsg)) {
            // mod start 2016/03/15 CSA Defect#5081
            cMsg.mdlGrpNm.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDL_GRP, NSAL0490Constant.MDL_GRP_MSTR });
            // mod end 2016/03/15 CSA Defect#5081
            return true;
        }

        if (isEmptyLine(sMsg.A.no(0).prntMdseCd_A.getValue(), sMsg.A.no(0).effFromDt_A.getValue())) {
            return false;
        }

        // check Date
        boolean isDateError = false;
        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(asMsg.effThruDt_A)) {
                if (1 == ZYPDateUtil.compare(asMsg.effFromDt_A.getValue(), asMsg.effThruDt_A.getValue())) {
                    if (i >= pageFromIdx && i <= pageToIdx) {
                        cMsg.A.no(i - pageFromIdx).effFromDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0323E, new String[] {NSAL0490Constant.START_DT, NSAL0490Constant.END_DT });
                    } else {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0323E, new String[] {NSAL0490Constant.START_DT, NSAL0490Constant.END_DT });
                    }
                    isDateError = true;
                }
            }
        }

        if (isDateError) {
            return true;
        }

        return false;
    }

    /**
     * check Correlate for Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return false : No Error
     */
    private boolean checkCorrelateForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);
            // add start 2016/04/22 CSA Defect#5407
            if (ZYPCommonFunc.hasValue(asMsg.dsMdlConfigPk_A)) {
                continue;
            }
            // add end 2016/04/22 CSA Defect#5407

            String targetMdseCd = null;
            if (NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                targetMdseCd = asMsg.prntMdseCd_A.getValue();
            } else {
                targetMdseCd = asMsg.childMdseCd_A.getValue();
            }

            // check Exist Master
            // 2015/10/07 CSA Y.Tsuchimoto Mod Start
            // if (!NSAL0490CommonLogic.isExistMdse(cMsg, targetMdseCd)) {
            if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd)) {
            // 2015/10/07 CSA Y.Tsuchimoto Mod End
                setMasterErrorForItemConfig(cMsg, asMsg, i, pageFromIdx, pageToIdx, NSAL0490Constant.MDSE);
                return true;
            }
            // add start 2016/04/22 CSA Defect#5407
            if (NSAL0490CommonLogic.isErrorMdseLenChk(cMsg.glblCmpyCd.getValue(), targetMdseCd)) {
                setMercuryMdseSizeChk(cMsg, asMsg, i, pageFromIdx, pageToIdx);
                return true;
            }
            // add end 2016/04/22 CSA Defect#5407
        }

        if (checkDuplicateChildMdse(cMsg, sMsg, pageFromIdx, pageToIdx)) {
            return true;
        }

        if (checkRangeDate(cMsg, sMsg, pageFromIdx, pageToIdx)) {
            return true;
        }

        if (NSAL0490CommonLogic.checkItemCombination(cMsg, sMsg)) {
            return true;
        }
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        if (NSAL0490CommonLogic.checkExistMachineOnly(cMsg, sMsg)) {
            return true;
        }
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        return false;
    }

    /**
     * check Input for Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return true : No Error
     */
    private boolean checkInputForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        // check Date
        boolean isDateError = false;
        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0490_BSMsg bsMsg = sMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(bsMsg.effThruDt_B)) {
                if (1 == ZYPDateUtil.compare(bsMsg.effFromDt_B.getValue(), bsMsg.effThruDt_B.getValue())) {
                    if (i >= pageFromIdx && i <= pageToIdx) {
                        cMsg.B.no(i - pageFromIdx).effFromDt_B.setErrorInfo(1, NSAL0490Constant.NSAM0323E, new String[] {NSAL0490Constant.START_DT, NSAL0490Constant.END_DT });
                    } else {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0323E, new String[] {NSAL0490Constant.START_DT, NSAL0490Constant.END_DT });
                    }
                    isDateError = true;
                }
            }
        }

        if (isDateError) {
            return false;
        }

        return true;
    }

    /**
     * check Correlate for Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return true : No Error
     */
    private boolean checkCorrelateForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        boolean isErrorFlg = false;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0490_BSMsg bsMsg = sMsg.B.no(i);

            String targetMdseCd = bsMsg.mdseCd_B.getValue();

            // 2015/10/07 CSA Y.Tsuchimoto Mod Start
            // check Exist Master
            //if (!NSAL0490CommonLogic.isExistMdse(cMsg, targetMdseCd)) {
            if (!NSAL0490CommonLogic.isExistAllMdseV(cMsg, targetMdseCd)) {
            // 2015/10/07 CSA Y.Tsuchimoto Mod End
                setMasterErrorForSupplyMap(cMsg, bsMsg, i, pageFromIdx, pageToIdx, NSAL0490Constant.MDSE);
                isErrorFlg = true;
            }
            // START 2017/09/05 [QC#20624, ADD]
            if (NSAL0490CommonLogic.isErrorMdseLenChk(cMsg.glblCmpyCd.getValue(), targetMdseCd)) {
                setMercuryMdseSizeChk(cMsg, bsMsg, i, pageFromIdx, pageToIdx);
                return true;
            }
            // END   2017/09/05 [QC#20624, ADD]
            
        }

        if (isErrorFlg) {
            return false;
        }

        if (!checkDuplicateMdseForSupplyMap(cMsg, sMsg, pageFromIdx, pageToIdx)) {
            return false;
        }

        return true;
    }

    /**
     * do Header Insert
     * @param cMsg NSAL0490CMsg
     * @return true : No Error
     */
    private boolean doDsMdlInsert(NSAL0490CMsg cMsg) {

        // insert DS_MDL
        DS_MDLTMsg insDsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mdlId, getNextMdlId(cMsg));
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mdlDescTxt, cMsg.mdlDescTxt);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mdlGrpId, NSAL0490Query.getInstance().getMdlGrpId(cMsg));
        // 2015/10/07 CSA Y.Tsuchimoto Mod Start
        //ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2015/10/07 CSA Y.Tsuchimoto Mod End
        // mod start 2015/11/30 CSA Defect#960
        if (ZYPCommonFunc.hasValue(cMsg.mtrGrpPk)) {
            ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mtrReqMdlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mtrReqMdlFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod end 2015/11/30 CSA Defect#960
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.prvntMaintAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcSegCd, cMsg.svcSegCd);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mdlActvFlg, cMsg.mdlActvFlg);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.mtrGrpPk, cMsg.mtrGrpPk);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcSkillNum, cMsg.svcSkillNum);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.custIstlFlg, convertFlg(cMsg.custIstlFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.siteSrvyReqFlg, convertFlg(cMsg.siteSrvyReqFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcIstlCallGrpNum, cMsg.svcIstlCallGrpNum_IN);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcDeinsCallGrpNum, cMsg.svcIstlCallGrpNum_DE);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcIstlReqFlg, convertFlg(cMsg.svcIstlReqFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.phoneFixFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.afterHourAllwFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcIstlRuleNum, cMsg.svcIstlRuleNum);
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcDeinsRuleNum, cMsg.svcDeinsRuleNum);
        // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcLbGrpCd, cMsg.svcLbGrpCd);
        // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
        // START 2018/01/15 K.Kojima [QC#23352,ADD]
        ZYPEZDItemValueSetter.setValue(insDsMdlTMsg.svcMdlTpCd, cMsg.svcMdlTpCd);
        // END 2018/01/15 K.Kojima [QC#23352,ADD]
        S21FastTBLAccessor.insert(insDsMdlTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insDsMdlTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.DS_MDL });
            return false;
        }

        // insert MDL_NM
        MDL_NMTMsg insMdlNmTMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_GlblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_MdlId, cMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_MdlNm, cMsg.mdlNm);
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_CratDt, cMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_CratUsrId, cMsg.getUserID());
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_TngRstFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMdlNmTMsg.t_AutoTngRstFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.insert(insMdlNmTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMdlNmTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.MDL_NM });
            return false;
        }

        // add start 2016/05/09 CSA Defect#8055
        // invoke Master Data Messaging
        boolean isExistsModel = false;
        NSAL0490CommonLogic.invokeMasterDataMessaging(isExistsModel, insDsMdlTMsg.ezUpTime.getValue(), cMsg.mdlId.getValue(), cMsg.mdlNm.getValue());
        // add end 2016/05/09 CSA Defect#8055

        return true;
    }

    /**
     * do Header Update
     * @param cMsg NSAL0490CMsg
     * @return true : No Error
     */
    private boolean doDsMdlUpdate(NSAL0490CMsg cMsg) {

        DS_MDLTMsg lockTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lockTMsg.mdlId, cMsg.mdlId);
        DS_MDLTMsg updTMsg = (DS_MDLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);

        // check exclusion
        String findEzUpTime = cMsg.ezUpTime_DM.getValue();
        String findEzUpTimeZone = cMsg.ezUpTimeZone_DM.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
            return false;
        }

        // update DS_MDL
        String dplyTab = cMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlDescTxt, cMsg.mdlDescTxt);
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlGrpId, NSAL0490Query.getInstance().getMdlGrpId(cMsg));
            ZYPEZDItemValueSetter.setValue(updTMsg.svcSegCd, cMsg.svcSegCd);
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(updTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlActvFlg, cMsg.mdlActvFlg);
            ZYPEZDItemValueSetter.setValue(updTMsg.mtrGrpPk, cMsg.mtrGrpPk);
            // add start 2015/11/30 CSA Defect#960
            if (ZYPCommonFunc.hasValue(cMsg.mtrGrpPk)) {
                ZYPEZDItemValueSetter.setValue(updTMsg.mtrReqMdlFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(updTMsg.mtrReqMdlFlg, ZYPConstant.FLG_OFF_N);
            }
            // add end 2015/11/30 CSA Defect#960
            ZYPEZDItemValueSetter.setValue(updTMsg.svcSkillNum, cMsg.svcSkillNum);
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(updTMsg.custIstlFlg, convertFlg(cMsg.custIstlFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(updTMsg.siteSrvyReqFlg, convertFlg(cMsg.siteSrvyReqFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(updTMsg.svcIstlCallGrpNum, cMsg.svcIstlCallGrpNum_IN);
            ZYPEZDItemValueSetter.setValue(updTMsg.svcDeinsCallGrpNum, cMsg.svcIstlCallGrpNum_DE);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            ZYPEZDItemValueSetter.setValue(updTMsg.svcIstlReqFlg, convertFlg(cMsg.svcIstlReqFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(updTMsg.svcIstlRuleNum, cMsg.svcIstlRuleNum);
            ZYPEZDItemValueSetter.setValue(updTMsg.svcDeinsRuleNum, cMsg.svcDeinsRuleNum);
            // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
            ZYPEZDItemValueSetter.setValue(updTMsg.svcLbGrpCd, cMsg.svcLbGrpCd);
            // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
            // START 2018/01/15 K.Kojima [QC#23352,ADD]
            ZYPEZDItemValueSetter.setValue(updTMsg.svcMdlTpCd, cMsg.svcMdlTpCd);
            // END 2018/01/15 K.Kojima [QC#23352,ADD]
        } else if (NSAL0490Constant.TAB_SVC_RULES.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.rcllIntvlDaysAot, cMsg.rcllIntvlDaysAot);
            ZYPEZDItemValueSetter.setValue(updTMsg.rcllCopyVolCnt, cMsg.rcllCopyVolCnt);
            ZYPEZDItemValueSetter.setValue(updTMsg.rcllGlblIntvlDaysAot, cMsg.rcllGlblIntvlDaysAot);
            ZYPEZDItemValueSetter.setValue(updTMsg.rcllGlblCopyVolCnt, cMsg.rcllGlblCopyVolCnt);
            // add start 2016/05/30 CSA Defect#6675
            ZYPEZDItemValueSetter.setValue(updTMsg.rspTmUpMnAot, cMsg.xxRtoTaskTmNum.getValue().multiply(BigDecimal.valueOf(60)).setScale(0, BigDecimal.ROUND_UP));
            ZYPEZDItemValueSetter.setValue(updTMsg.rspTmDownMnAot, updTMsg.rspTmUpMnAot);
            // add end 2016/05/30 CSA Defect#6675
            ZYPEZDItemValueSetter.setValue(updTMsg.xsVisitCnt, cMsg.xsVisitCnt);
            ZYPEZDItemValueSetter.setValue(updTMsg.phoneFixFlg, convertFlg(cMsg.phoneFixFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(updTMsg.phoneFixIntvlDaysAot, cMsg.phoneFixIntvlDaysAot);
            ZYPEZDItemValueSetter.setValue(updTMsg.afterHourAllwFlg, convertFlg(cMsg.afterHourAllwFlg.getValue()));
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(updTMsg.machInFldInacMthAot, cMsg.machInFldInacMthAot);
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlDurnTmNum, cMsg.mdlDurnTmNum);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            ZYPEZDItemValueSetter.setValue(updTMsg.copyVolDaysAot, cMsg.copyVolDaysAot);
            ZYPEZDItemValueSetter.setValue(updTMsg.maxCopyPerDayTotCnt, cMsg.maxCopyPerDayTotCnt);
            ZYPEZDItemValueSetter.setValue(updTMsg.maxCopyPerDayBlackCnt, cMsg.maxCopyPerDayBlackCnt);
            ZYPEZDItemValueSetter.setValue(updTMsg.maxCopyTestCnt, cMsg.maxCopyTestCnt);
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlSpeedBlackRate, cMsg.mdlSpeedBlackRate);
            ZYPEZDItemValueSetter.setValue(updTMsg.mdlSpeedColorRate, cMsg.mdlSpeedColorRate);
            ZYPEZDItemValueSetter.setValue(updTMsg.dsMdlPaperSizeCd, cMsg.dsMdlPaperSizeCd);
        // add start 2016/05/19 CSA Defect#447
        } else if (NSAL0490Constant.TAB_END_OF_LIFE.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.eolSvcContrCmntTxt, cMsg.eolSvcContrCmntTxt);
            ZYPEZDItemValueSetter.setValue(updTMsg.eolTmMatCmntTxt, cMsg.eolTmMatCmntTxt);
            ZYPEZDItemValueSetter.setValue(updTMsg.eolTechSprtCmntTxt, cMsg.eolTechSprtCmntTxt);
            ZYPEZDItemValueSetter.setValue(updTMsg.eolOthCmntTxt, cMsg.eolOthCmntTxt);
            // START 2017/12/22 U.Kim [QC#22448, ADD]
            ZYPEZDItemValueSetter.setValue(updTMsg.eolDisptCmntTxt, cMsg.eolDisptCmntTxt);
            // END 2017/12/22 U.Kim [QC#22448, ADD]
        // add end 2016/05/19 CSA Defect#447
        }
        S21FastTBLAccessor.update(updTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.DS_MDL });
            return false;
        }

        // add start 2016/05/09 CSA Defect#8055
        // invoke Master Data Messaging
        boolean isExistsModel = true;
        NSAL0490CommonLogic.invokeMasterDataMessaging(isExistsModel, updTMsg.ezUpTime.getValue(), cMsg.mdlId.getValue(), cMsg.mdlNm.getValue());
        // add end 2016/05/09 CSA Defect#8055

        return true;
    }

    // add start 2016/05/19 CSA Defect#447
    private void doDsMdlEol(NSAL0490CMsg cMsg) {

        //get Update/Delete List
        List<DS_MDL_EOLTMsg> insertList = new ArrayList<DS_MDL_EOLTMsg>();
        List<DS_MDL_EOLTMsg> updateList = new ArrayList<DS_MDL_EOLTMsg>();
        List<DS_MDL_EOLTMsg> deleteList = new ArrayList<DS_MDL_EOLTMsg>();
        boolean insertTargetSts1 = true;
        boolean insertTargetSts2 = true;
        boolean insertTargetSts3 = true;
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            NSAL0490_CCMsg cCMsg = cMsg.C.no(i);
            if (equalStringItem(cCMsg.dsMdlEolStsCd_C, cMsg.dsMdlEolStsCd_D1)) {
                DS_MDL_EOLTMsg inTMsg = setParamForUpdDel(cMsg, cCMsg, cMsg.dsMdlEolDt_D1);
                updateList.add(inTMsg);
                insertTargetSts1 = false;
            } else if (equalStringItem(cCMsg.dsMdlEolStsCd_C, cMsg.dsMdlEolStsCd_D2)) {
                DS_MDL_EOLTMsg inTMsg = setParamForUpdDel(cMsg, cCMsg, cMsg.dsMdlEolDt_D2);
                updateList.add(inTMsg);
                insertTargetSts2 = false;
            } else if (equalStringItem(cCMsg.dsMdlEolStsCd_C, cMsg.dsMdlEolStsCd_D3)) {
                DS_MDL_EOLTMsg inTMsg = setParamForUpdDel(cMsg, cCMsg, cMsg.dsMdlEolDt_D3);
                updateList.add(inTMsg);
                insertTargetSts3 = false;
            } else {
                DS_MDL_EOLTMsg inTMsg = setParamForUpdDel(cMsg, cCMsg, null);
                deleteList.add(inTMsg);
            }
        }
        //get Insert List
        if (insertTargetSts1 && ZYPCommonFunc.hasValue(cMsg.dsMdlEolStsCd_D1)) {
            DS_MDL_EOLTMsg inTMsg = setParamForIns(cMsg, cMsg.dsMdlEolStsCd_D1, cMsg.dsMdlEolDt_D1);
            insertList.add(inTMsg);
        }
        if (insertTargetSts2 && ZYPCommonFunc.hasValue(cMsg.dsMdlEolStsCd_D2)) {
            DS_MDL_EOLTMsg inTMsg = setParamForIns(cMsg, cMsg.dsMdlEolStsCd_D2, cMsg.dsMdlEolDt_D2);
            insertList.add(inTMsg);
        }
        if (insertTargetSts3 && ZYPCommonFunc.hasValue(cMsg.dsMdlEolStsCd_D3)) {
            DS_MDL_EOLTMsg inTMsg = setParamForIns(cMsg, cMsg.dsMdlEolStsCd_D3, cMsg.dsMdlEolDt_D3);
            insertList.add(inTMsg);
        }
        // Update/Delete process
        doDsMdlEolUpdDel(cMsg, updateList, NSAL0490Constant.UPD);
        doDsMdlEolUpdDel(cMsg, deleteList, NSAL0490Constant.DEL);
        doDsMdlEolIns(cMsg, insertList);
    }

    private void doDsMdlEolIns(NSAL0490CMsg cMsg, List<DS_MDL_EOLTMsg> insertList) {
        for (DS_MDL_EOLTMsg targetTMsg : insertList) {
            DS_MDL_EOLTMsg outTMsg = (DS_MDL_EOLTMsg) EZDTBLAccessor.findByKey(targetTMsg);
            if (outTMsg != null) {
                cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
                continue;
            }
            EZDTBLAccessor.create(targetTMsg);
            if (!RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[]{"DS_MDL_EOL"});
            }
        }
    }

    private void doDsMdlEolUpdDel(NSAL0490CMsg cMsg, List<DS_MDL_EOLTMsg> updateList, String mode) {
        for (DS_MDL_EOLTMsg targetTMsg : updateList) {
            DS_MDL_EOLTMsg outTMsg = (DS_MDL_EOLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);
            if (!RTNCD_NORMAL.equals(outTMsg.getReturnCode())) {
                if (mode.equals(NSAL0490Constant.UPD)) {
                    cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[]{"DS_MDL_EOL"});
                } else if (mode.equals(NSAL0490Constant.DEL)) {
                    cMsg.setMessageInfo(NSAL0490Constant.NSAM0033E, new String[]{"DS_MDL_EOL"});
                }
                continue;
            }
            if (!ZYPDateUtil.isSameTimeStamp(targetTMsg.ezUpTime.getValue(), targetTMsg.ezUpTimeZone.getValue(), outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
            } else {
                if (mode.equals(NSAL0490Constant.UPD)) {
                    ZYPEZDItemValueSetter.setValue(targetTMsg.mdseItemStsUpdProcFlg, outTMsg.mdseItemStsUpdProcFlg);
                    EZDTBLAccessor.update(targetTMsg);
                    if (!RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[]{"DS_MDL_EOL"});
                    }
                } else if (mode.equals(NSAL0490Constant.DEL)) {
                    EZDTBLAccessor.logicalRemove(targetTMsg);
                    if (!RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0033E, new String[]{"DS_MDL_EOL"});
                    }
                }
            }
        }
    }

    /**
     * @param cMsg
     * @return
     */
    private DS_MDL_EOLTMsg setParamForIns(NSAL0490CMsg cMsg, EZDCStringItem strItem, EZDCDateItem dateItem) {
        DS_MDL_EOLTMsg inTMsg = new DS_MDL_EOLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, cMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsMdlEolStsCd, strItem);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsMdlEolDt, dateItem);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdseItemStsUpdProcFlg, ZYPConstant.FLG_OFF_N);
        return inTMsg;
    }

    private boolean equalStringItem(EZDCStringItem item1, EZDCStringItem item2) {
        if (!ZYPCommonFunc.hasValue(item1) || !ZYPCommonFunc.hasValue(item2)) {
            return false;
        }
        return item1.getValue().equals(item2.getValue());
    }

    private DS_MDL_EOLTMsg setParamForUpdDel(NSAL0490CMsg cMsg, NSAL0490_CCMsg cCMsg, EZDCDateItem dateItem) {
        DS_MDL_EOLTMsg inTMsg = new DS_MDL_EOLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, cCMsg.mdlId_C);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsMdlEolStsCd, cCMsg.dsMdlEolStsCd_C);
        ZYPEZDItemValueSetter.setValue(inTMsg.ezUpTime, cCMsg.ezUpTime_C);
        ZYPEZDItemValueSetter.setValue(inTMsg.ezUpTimeZone, cCMsg.ezUpTimeZone_C);
        if (ZYPCommonFunc.hasValue(dateItem)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.dsMdlEolDt, dateItem);
        }
        return inTMsg;
    }
    // add end 2016/05/19 CSA Defect#447

    /**
     * do Detail Process For Item Configurations
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doDtlProcForItemConfig(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        List<DS_MDL_CONFIGTMsg> insTMsgList = new ArrayList<DS_MDL_CONFIGTMsg>();
        List<DS_MDL_CONFIGTMsg> updTMsgList = new ArrayList<DS_MDL_CONFIGTMsg>();
        Map<String, BigDecimal> prntConfigPkMap = new HashMap<String, BigDecimal>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);

            String targetMdseCd = null;
            if (NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                targetMdseCd = asMsg.prntMdseCd_A.getValue();
            } else {
                targetMdseCd = asMsg.childMdseCd_A.getValue();
            }

            if (isEmptyLine(targetMdseCd, asMsg.effFromDt_A.getValue())) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(asMsg.dsMdlConfigPk_A)) {
                // add Insert List
                addInsMdlConfigTMsg(cMsg, sMsg, asMsg, insTMsgList, prntConfigPkMap);

            } else {
                // add Update List
                if (!addUpdMdlConfigTMsg(cMsg, sMsg, asMsg, updTMsgList)) {
                    return;
                }
            }
        }

        // insert
        if (insTMsgList.size() > 0) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_MDL_CONFIGTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.DS_MDL_CONFIG });
                return;
            }
        }

        // update
        if (updTMsgList.size() > 0) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_MDL_CONFIGTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.DS_MDL_CONFIG });
                return;
            }
        }
    }

    /**
     * add Insert DS_MDL_CONFIGTMsg
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param asMsg NSAL0490_ASMsg
     * @param insTMsgList Insert TMsg List
     * @param prntInfoMap Parent Config PK Map
     */
    private void addInsMdlConfigTMsg(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, NSAL0490_ASMsg asMsg, List<DS_MDL_CONFIGTMsg> insTMsgList, Map<String, BigDecimal> prntConfigPkMap) {

        String dtlTp = asMsg.xxFlgNm_A.getValue();
        String mdlConfigFlg = ZYPConstant.FLG_OFF_N;

        if (NSAL0490Constant.DTL_TP_CHILD.equals(dtlTp)) {
            mdlConfigFlg = ZYPConstant.FLG_ON_Y;
        }

        // add insert DS_MDL_CONFIGTMsg
        DS_MDL_CONFIGTMsg insTMsg = new DS_MDL_CONFIGTMsg();
        BigDecimal dsMdlConfigPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_CONFIG_SQ);
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.mdlId, cMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsMdlConfigPk, dsMdlConfigPk);

        // get Parent Config PK
        BigDecimal prntConfigPk = dsMdlConfigPk;
        String lineNum = asMsg.xxLineNum_A.getValue();
        if (NSAL0490Constant.DTL_TP_PRNT.equals(dtlTp)) {
            prntConfigPkMap.put(lineNum, dsMdlConfigPk);
        } else {
            if (prntConfigPkMap.containsKey(lineNum)) {
                prntConfigPk = prntConfigPkMap.get(lineNum);
            } else {
                prntConfigPk = getRelationPrntConfigPk(sMsg, lineNum);
            }
        }

        ZYPEZDItemValueSetter.setValue(insTMsg.prntConfigPk, prntConfigPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.prntMdseCd, getRelationPrntMdseCd(asMsg.prntMdseCd_A.getValue(), sMsg, lineNum));
        ZYPEZDItemValueSetter.setValue(insTMsg.childMdseCd, asMsg.childMdseCd_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, asMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, asMsg.effThruDt_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.mdlConfigFlg, mdlConfigFlg);
        insTMsgList.add(insTMsg);
    }

    /**
     * add Update DS_MDL_CONFIGTMsg
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param asMsg NSAL0490_ASMsg
     * @param updTMsgList Update TMsg List
     * @return No Exclusion Error : true
     */
    private boolean addUpdMdlConfigTMsg(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, NSAL0490_ASMsg asMsg, List<DS_MDL_CONFIGTMsg> updTMsgList) {

        DS_MDL_CONFIGTMsg lockTMsg = new DS_MDL_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lockTMsg.dsMdlConfigPk, asMsg.dsMdlConfigPk_A);
        ZYPEZDItemValueSetter.setValue(lockTMsg.prntConfigPk, asMsg.prntConfigPk_A);
        ZYPEZDItemValueSetter.setValue(lockTMsg.prntMdseCd, getRelationPrntMdseCd(asMsg.prntMdseCd_A.getValue(), sMsg, asMsg.xxLineNum_A.getValue()));
        ZYPEZDItemValueSetter.setValue(lockTMsg.mdlId, cMsg.mdlId);
        DS_MDL_CONFIGTMsg updTMsg = (DS_MDL_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.DS_MDL_CONFIG });
            return false;
        }

        // check exclusion
        String findEzUpTime = asMsg.ezUpTime_A.getValue();
        String findEzUpTimeZone = asMsg.ezUpTimeZone_A.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(updTMsg.effFromDt, asMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(updTMsg.effThruDt, asMsg.effThruDt_A);
        updTMsgList.add(updTMsg);

        return true;
    }

    /**
     * get Next Model ID
     * @param cMsg NSAL0490CMsg
     * @return Next Model ID
     */
    private BigDecimal getNextMdlId(NSAL0490CMsg cMsg) {

        BigDecimal nextMdlId = NSAL0490Query.getInstance().getNextMdlId(cMsg);

        if (nextMdlId == null) {
            nextMdlId = BigDecimal.ONE;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.mdlId, nextMdlId);

        return nextMdlId;
    }

    /**
     * get convert Flag
     * @param targetFlg Target Flag
     * @return convert Flag
     */
    private String convertFlg(String targetFlg) {

        if (!ZYPCommonFunc.hasValue(targetFlg)) {
            return ZYPConstant.FLG_OFF_N;
        }

        return targetFlg;
    }

    /**
     * check Duplicate Child Mdse
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @return false : No Error
     */
    private boolean checkDuplicateChildMdse(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx) {

        List<String> mdseList = new ArrayList<String>();
        String prevLineNum = null;

        if (sMsg.A.getValidCount() > 1) {
            prevLineNum = sMsg.A.no(1).xxLineNum_A.getValue();
        } else {
            return false;
        }

        boolean isErrorFlg = false;
        for (int i = 1; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);
            String targetMdseCd = asMsg.childMdseCd_A.getValue();
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            String targetOrdTakeMdseCd = asMsg.mdseCd_A.getValue();
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            if (i == 1) {
                mdseList.add(targetMdseCd);
                // 2015/10/07 CSA Y.Tsuchimoto Add Start
                mdseList.add(targetOrdTakeMdseCd);
                // 2015/10/07 CSA Y.Tsuchimoto Add End
                continue;
            } else if (ZYPCommonFunc.hasValue(asMsg.prntMdseCd_A)) {
                continue;
            }

            String targetLineNum = asMsg.xxLineNum_A.getValue();
            if (targetLineNum.equals(prevLineNum)) {
                if (mdseList.indexOf(targetMdseCd) != -1) {
                    setDuplicateMdseErrorForItemConfig(cMsg, asMsg, i, pageFromIdx, pageToIdx);
                    isErrorFlg = true;
                } else {
                    mdseList.add(targetMdseCd);
                }
            } else {
                prevLineNum = targetLineNum;
                mdseList = new ArrayList<String>();
                mdseList.add(targetMdseCd);
            }
        }

        return isErrorFlg;
    }

    /**
     * check Range Date
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @return false : No Error
     */
    private boolean checkRangeDate(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx) {

        String prntLineNum = null;
        String prntStartDate = null;
        String prntEndDate = null;

        if (sMsg.A.getValidCount() > 0) {
            prntLineNum = sMsg.A.no(0).xxLineNum_A.getValue();
            prntStartDate = sMsg.A.no(0).effFromDt_A.getValue();
            prntEndDate = NSAL0490CommonLogic.getMaxDate(sMsg.A.no(0).effThruDt_A.getValue());
        }

        boolean isRangeError = false;
        for (int i = 1; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);
            String targetLineNum = asMsg.xxLineNum_A.getValue();

            if (targetLineNum.equals(prntLineNum) && !ZYPCommonFunc.hasValue(asMsg.prntMdseCd_A)) {
                String targetStartDate = asMsg.effFromDt_A.getValue();
                String targetEndDate = NSAL0490CommonLogic.getMaxDate(asMsg.effThruDt_A.getValue());

                if (1 == ZYPDateUtil.compare(prntStartDate, targetStartDate)) {
                    if (i >= pageFromIdx && i <= pageToIdx) {
                        cMsg.A.no(i - pageFromIdx).effFromDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0325E);
                    } else {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0325E);
                    }
                    isRangeError = true;

                }

                if (1 == ZYPDateUtil.compare(targetEndDate, prntEndDate)) {
                    if (i >= pageFromIdx && i <= pageToIdx) {
                        cMsg.A.no(i - pageFromIdx).effThruDt_A.setErrorInfo(1, NSAL0490Constant.NSAM0325E);
                    } else {
                        cMsg.setMessageInfo(NSAL0490Constant.NSAM0325E);
                    }
                    isRangeError = true;
                }
            } else {
                prntLineNum = targetLineNum;
                prntStartDate = asMsg.effFromDt_A.getValue();
                prntEndDate = NSAL0490CommonLogic.getMaxDate(asMsg.effThruDt_A.getValue());
            }
        }

        return isRangeError;
    }

    /**
     * check Duplicate Mdse For Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @return No Error : true
     */
    private boolean checkDuplicateMdseForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg, int pageFromIdx, int pageToIdx) {

        List<String> mdseList = new ArrayList<String>();
        String prevMdseCd = null;

        if (sMsg.B.getValidCount() > 0) {
            prevMdseCd = sMsg.B.no(0).mdseCd_B.getValue();
            mdseList.add(prevMdseCd);
        } else {
            return true;
        }

        boolean isErrorFlg = true;
        for (int i = 1; i < sMsg.B.getValidCount(); i++) {
            NSAL0490_BSMsg bsMsg = sMsg.B.no(i);
            String targetMdseCd = bsMsg.mdseCd_B.getValue();

            if (mdseList.indexOf(targetMdseCd) != -1) {
                setDuplicateMdseErrorForSupplyMap(cMsg, bsMsg, i, pageFromIdx, pageToIdx);
                isErrorFlg = false;
            } else {
                mdseList.add(targetMdseCd);
            }
        }

        return isErrorFlg;
    }

    /**
     * set Master Error (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param asMsg NSAL0490_ASMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @param masterNm Master Name
     */
    private void setMasterErrorForItemConfig(NSAL0490CMsg cMsg, NSAL0490_ASMsg asMsg, int targetIndex, int pageFromIdx, int pageToIdx, String masterNm) {

        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            if (NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                cMsg.A.no(targetIndex - pageFromIdx).prntMdseCd_A.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, masterNm });
            } else {
                cMsg.A.no(targetIndex - pageFromIdx).childMdseCd_A.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, masterNm });
            }
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, masterNm });
        }
    }

    /**
     * set Master Error (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param bsMsg NSAL0490_BSMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     * @param masterNm Master Name
     */
    private void setMasterErrorForSupplyMap(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg, int targetIndex, int pageFromIdx, int pageToIdx, String masterNm) {

        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            cMsg.B.no(targetIndex - pageFromIdx).mdseCd_B.setErrorInfo(1, NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, masterNm });
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0063E, new String[] {NSAL0490Constant.MDSE_CD, masterNm });
        }
    }

    /**
     * set Duplicate Mdse Error (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param asMsg NSAL0490_ASMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     */
    private void setDuplicateMdseErrorForItemConfig(NSAL0490CMsg cMsg, NSAL0490_ASMsg asMsg, int targetIndex, int pageFromIdx, int pageToIdx) {

        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            cMsg.A.no(targetIndex - pageFromIdx).childMdseCd_A.setErrorInfo(1, NSAL0490Constant.NSAM0324E, new String[] {NSAL0490Constant.CHILD_MDSE_CD });
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0324E, new String[] {NSAL0490Constant.CHILD_MDSE_CD });
        }
    }

    /**
     * set Duplicate Mdse Error (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param bsMsg NSAL0490_BSMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     */
    private void setDuplicateMdseErrorForSupplyMap(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg, int targetIndex, int pageFromIdx, int pageToIdx) {

        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            cMsg.B.no(targetIndex - pageFromIdx).mdseCd_B.setErrorInfo(1, NSAL0490Constant.NSAM0324E, new String[] {NSAL0490Constant.MDSE_CD });
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0324E, new String[] {NSAL0490Constant.MDSE_CD });
        }
    }

    /**
     * do Process For Supply Mapping
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     */
    private void doProcForSupplyMap(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        List<DS_MDL_SPLY_RELNTMsg> insTMsgList = new ArrayList<DS_MDL_SPLY_RELNTMsg>();
        List<DS_MDL_SPLY_RELNTMsg> updTMsgList = new ArrayList<DS_MDL_SPLY_RELNTMsg>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL0490_BSMsg bsMsg = sMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bsMsg.dsMdlSplyRelnPk_B)) {
                // add Insert List
                addInsMdlSplyRelnTMsg(cMsg, bsMsg, insTMsgList);

            } else {
                // add Update List
                if (!addUpdMdlSplyRelnTMsg(cMsg, bsMsg, updTMsgList)) {
                    return;
                }
            }
        }

        // insert
        if (insTMsgList.size() > 0) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_MDL_SPLY_RELNTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0032E, new String[] {NSAL0490Constant.DS_MDL_SPLY_RELN });
                return;
            }
        }

        // update
        if (updTMsgList.size() > 0) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_MDL_SPLY_RELNTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.DS_MDL_SPLY_RELN });
                return;
            }
        }

        // add start 2016/05/09 CSA Defect#8055
        // invoke Master Data Messaging
        if (insTMsgList.size() > 0 || updTMsgList.size() > 0) {
            boolean isExistsModel = true;
            String ezUpTime = null;
            DS_MDL_SPLY_RELNTMsg tMsg = new DS_MDL_SPLY_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            if (insTMsgList.size() > 0) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsMdlSplyRelnPk, insTMsgList.get(0).dsMdlSplyRelnPk);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.dsMdlSplyRelnPk, updTMsgList.get(0).dsMdlSplyRelnPk);
            }
            tMsg = (DS_MDL_SPLY_RELNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                ezUpTime = tMsg.ezUpTime.getValue();
            }
            NSAL0490CommonLogic.invokeMasterDataMessaging(isExistsModel, ezUpTime, cMsg.mdlId.getValue(), cMsg.mdlNm.getValue());
        }
        // add end 2016/05/09 CSA Defect#8055
    }

    /**
     * add Insert DS_MDL_SPLY_RELNTMsg
     * @param cMsg NSAL0490CMsg
     * @param bsMsg NSAL0490_BSMsg
     * @param insTMsgList Insert TMsg List
     */
    private void addInsMdlSplyRelnTMsg(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg, List<DS_MDL_SPLY_RELNTMsg> insTMsgList) {

        // check exclusion
        if (NSAL0490Query.getInstance().isExsitMdlSplyReln(cMsg, bsMsg)) {
            cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
            return;
        }

        DS_MDL_SPLY_RELNTMsg insTMsg = new DS_MDL_SPLY_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsMdlSplyRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_SPLY_RELN_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.mdlId, cMsg.mdlId);
        // 2015/10/07 CSA Y.Tsuchimoto Mod Start
        //ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, bsMsg.mdseCd_B);
        //ZYPEZDItemValueSetter.setValue(insTMsg.ordTakeMdseFlg, ZYPConstant.FLG_OFF_N);
        if (NSAL0490CommonLogic.isExistOrdMdseCd(cMsg, bsMsg.mdseCd_B.getValue())) {
            ZYPEZDItemValueSetter.setValue(insTMsg.ordTakeMdseCd, bsMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(insTMsg.ordTakeMdseFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, bsMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(insTMsg.ordTakeMdseFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2015/10/07 CSA Y.Tsuchimoto Mod End
        ZYPEZDItemValueSetter.setValue(insTMsg.splyTolPct, bsMsg.splyTolPct_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.custStkQty, bsMsg.custStkQty_B);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(insTMsg.splyInitQty, bsMsg.splyInitQty_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.splyContrQty, bsMsg.splyContrQty_B);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, bsMsg.effFromDt_B);
        ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, bsMsg.effThruDt_B);

        insTMsgList.add(insTMsg);
    }

    /**
     * add Update DS_MDL_CONFIGTMsg
     * @param cMsg NSAL0490CMsg
     * @param bsMsg NSAL0490_BSMsg
     * @param updTMsgList Update TMsg List
     * @return No Exclusion Error : true
     */
    private boolean addUpdMdlSplyRelnTMsg(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg, List<DS_MDL_SPLY_RELNTMsg> updTMsgList) {

        DS_MDL_SPLY_RELNTMsg lockTMsg = new DS_MDL_SPLY_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(lockTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lockTMsg.dsMdlSplyRelnPk, bsMsg.dsMdlSplyRelnPk_B);
        DS_MDL_SPLY_RELNTMsg updTMsg = (DS_MDL_SPLY_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(lockTMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0031E, new String[] {NSAL0490Constant.DS_MDL_SPLY_RELN });
            return false;
        }

        // check exclusion
        String findEzUpTime = bsMsg.ezUpTime_B.getValue();
        String findEzUpTimeZone = bsMsg.ezUpTimeZone_B.getValue();
        String currentEzUpTime = updTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = updTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NSAL0490Constant.ZZZM9004E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(updTMsg.splyTolPct, bsMsg.splyTolPct_B);
        ZYPEZDItemValueSetter.setValue(updTMsg.custStkQty, bsMsg.custStkQty_B);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(updTMsg.splyInitQty, bsMsg.splyInitQty_B);
        ZYPEZDItemValueSetter.setValue(updTMsg.splyContrQty, bsMsg.splyContrQty_B);
        // 2015/10/07 CSA Y.Tsuchimoto Add End
        ZYPEZDItemValueSetter.setValue(updTMsg.effFromDt, bsMsg.effFromDt_B);
        ZYPEZDItemValueSetter.setValue(updTMsg.effThruDt, bsMsg.effThruDt_B);
        updTMsgList.add(updTMsg);

        return true;
    }

    /**
     * check Empty Line
     * @param mdseCd MDSE Code
     * @param startDate Start Date
     * @return Empty Line : true
     */
    private boolean isEmptyLine(String mdseCd, String startDate) {

        if (!ZYPCommonFunc.hasValue(mdseCd) && !ZYPCommonFunc.hasValue(startDate)) {
            return true;
        }

        return false;
    }

    /**
     * get Relation Parent Config PK
     * @param sMsg NSAL0490SMsg
     * @param lineNum Line Number
     * @return Relation Parent Config PK
     */
    private BigDecimal getRelationPrntConfigPk(NSAL0490SMsg sMsg, String lineNum) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);

            if (lineNum.equals(asMsg.xxLineNum_A.getValue()) && ZYPCommonFunc.hasValue(asMsg.prntMdseCd_A)) {
                return asMsg.prntConfigPk_A.getValue();
            }
        }

        return null;
    }

    /**
     * get Relation Parent MDSE Code
     * @param prntMdseCd Target Parent Mdse Code
     * @param sMsg NSAL0490SMsg
     * @param targetLineNum Target Line Number
     * @return Relation Parent MDSE Code
     */
    private String getRelationPrntMdseCd(String prntMdseCd, NSAL0490SMsg sMsg, String lineNum) {

        if (ZYPCommonFunc.hasValue(prntMdseCd)) {
            return prntMdseCd;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0490_ASMsg asMsg = sMsg.A.no(i);
            String targetPrntMdseCd = asMsg.prntMdseCd_A.getValue();

            if (lineNum.equals(asMsg.xxLineNum_A.getValue()) && ZYPCommonFunc.hasValue(targetPrntMdseCd)) {
                return targetPrntMdseCd;
            }
        }

        return null;
    }

    // add start 2016/04/22 CSA Defect#5407
    /**
     * set Mercury Mdse Size Check Error
     * @param cMsg NSAL0490CMsg
     * @param asMsg NSAL0490_ASMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     */
    private void setMercuryMdseSizeChk(NSAL0490CMsg cMsg, NSAL0490_ASMsg asMsg, int targetIndex, int pageFromIdx, int pageToIdx) {

        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            if (NSAL0490Constant.DTL_TP_PRNT.equals(asMsg.xxFlgNm_A.getValue())) {
                cMsg.A.no(targetIndex - pageFromIdx).prntMdseCd_A.setErrorInfo(1, NSAL0490Constant.NSAM0460E);
            } else {
                cMsg.A.no(targetIndex - pageFromIdx).childMdseCd_A.setErrorInfo(1, NSAL0490Constant.NSAM0460E);
            }
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0460E);
        }
    }
    // add end 2016/04/22 CSA Defect#5407

    // START 2017/09/05 [QC#20624, ADD]
    /**
     * set Mercury Mdse Size Check Error
     * @param cMsg NSAL0490CMsg
     * @param asMsg NSAL0490_BSMsg
     * @param targetIndex Target Index Number
     * @param pageFromIdx Page From Index
     * @param pageToIdx Page To Index
     */
    private void setMercuryMdseSizeChk(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg, int targetIndex, int pageFromIdx, int pageToIdx) {
        if (targetIndex >= pageFromIdx && targetIndex <= pageToIdx) {
            cMsg.B.no(targetIndex - pageFromIdx).mdseCd_B.setErrorInfo(1, NSAL0490Constant.NSAM0460E);
        } else {
            cMsg.setMessageInfo(NSAL0490Constant.NSAM0460E);
        }
    }
    // START 2017/09/05 [QC#20624, ADD]

}
