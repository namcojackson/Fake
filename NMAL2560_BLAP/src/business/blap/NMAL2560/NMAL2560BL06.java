/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2560;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2560.common.NMAL2560CommonLogic;
import business.blap.NMAL2560.constant.NMAL2560Constant;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.ORG_HRCH_STRU_DFNTMsg;
import business.db.ORG_HRCH_STRU_DFNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2560BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 * 2016/07/21   Fujitsu         C.Yokoi         Create          CSA-QC#12216
 *</pre>
 */
public class NMAL2560BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2560CMsg bizMsg = (NMAL2560CMsg) cMsg;
            NMAL2560SMsg glblMsg = (NMAL2560SMsg) sMsg;

            if ("NMAL2560Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2560Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2560Scrn00_CMN_Submit(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        NMAL2560CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);

        boolean errFlg = false;

        List<BigDecimal> dispDuplicatedPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {
                dispDuplicatedPkList.add(glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue());
            }
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // ////////
            // Check Duplicated Structure Name
            // ////////
            String[] msgArgs = new String[] {glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue(), glblMsg.A.no(i).bizAreaOrgCd_A.getValue() };
            int addDataErrCnt = 0;

            // Check Disp
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {

                if (glblMsg.A.no(i).bizAreaOrgCd_A.getValue().equals(glblMsg.A.no(j).bizAreaOrgCd_A.getValue()) && glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue().equals(glblMsg.A.no(j).orgHrchStruDfnNm_A.getValue())) {

                    if (NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {

                        // Case AddData
                        ++addDataErrCnt;
                        if (addDataErrCnt == 2) {
                            glblMsg.A.no(i).orgHrchStruDfnNm_A.setErrorInfo(1, NMAL2560Constant.NMAM8515E, msgArgs);
                            errFlg = true;
                            break;
                        }
                    } else {

                        // Case Not AddData
                        if (!glblMsg.A.no(i).xxRowId_A.getValue().equals(glblMsg.A.no(j).xxRowId_A.getValue())) {
                            glblMsg.A.no(i).orgHrchStruDfnNm_A.setErrorInfo(1, NMAL2560Constant.NMAM8515E, msgArgs);
                            errFlg = true;
                            break;
                        }
                    }
                }
            }

            // Check DB
            ORG_HRCH_STRU_DFNTMsg orgHrchStruDfnTmsg = new ORG_HRCH_STRU_DFNTMsg();
            orgHrchStruDfnTmsg.setSQLID("001");
            orgHrchStruDfnTmsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            orgHrchStruDfnTmsg.setConditionValue("orgTierCd01", "1");
            orgHrchStruDfnTmsg.setConditionValue("bizAreaOrgCd01", glblMsg.A.no(i).bizAreaOrgCd_A.getValue());
            ORG_HRCH_STRU_DFNTMsgArray orgHrchStruDfnList = (ORG_HRCH_STRU_DFNTMsgArray) EZDTBLAccessor.findByCondition(orgHrchStruDfnTmsg);

            for (int j = 0; j < orgHrchStruDfnList.length(); j++) {

                if (!dispDuplicatedPkList.contains(glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue())) {

                    if (glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue().equals(orgHrchStruDfnList.no(j).orgHrchStruDfnNm.getValue())) {
                        if (NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {
                            glblMsg.A.no(i).orgHrchStruDfnNm_A.setErrorInfo(1, NMAL2560Constant.NMAM8515E, msgArgs);
                            errFlg = true;
                            break;
                        } else {
                            if (!glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue().equals(orgHrchStruDfnList.no(j).orgHrchStruDfnPk.getValue())) {
                                glblMsg.A.no(i).orgHrchStruDfnNm_A.setErrorInfo(1, NMAL2560Constant.NMAM8515E, msgArgs);
                                errFlg = true;
                                break;
                            }
                        }
                    }
                }
            }

            // ////////
            // Check From < To
            // ////////
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A)) {
                if (glblMsg.A.no(i).effFromDt_A.getValue().compareTo(glblMsg.A.no(i).effThruDt_A.getValue()) >= 0) {
                    glblMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAL2560Constant.NMAM8516E);
                    glblMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAL2560Constant.NMAM8516E);
                    errFlg = true;
                }
            }
        }

        // ////////
        // Check Integrity From - To
        // ////////
        List<String> checkIntegrityList = new ArrayList<String>();
        List<BigDecimal> dispIntegrityPkList = new ArrayList<BigDecimal>();
        StringBuilder sortKeyword = new StringBuilder();

        // For Disp
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            sortKeyword.setLength(0);
            sortKeyword.append(ZYPCommonFunc.leftPad(glblMsg.A.no(i).bizAreaOrgCd_A.getValue(), 8, "0"));
            sortKeyword.append(glblMsg.A.no(i).effFromDt_A.getValue());
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A)) {
                sortKeyword.append(glblMsg.A.no(i).effThruDt_A.getValue());
            } else {
                sortKeyword.append("99991231");
            }
            if (!NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {
                sortKeyword.append(ZYPCommonFunc.leftPad(glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue().toString(), 28, "0"));
                dispIntegrityPkList.add(glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue());
            }
            // 2016/07/21 CSA-QC#12216 ADD Start
            sortKeyword.append(String.valueOf(i));
            // 2016/07/21 CSA-QC#12216 ADD End
            checkIntegrityList.add(sortKeyword.toString());
        }

        // For DB
        S21SsmEZDResult orgHrchStruDfnResult = NMAL2560Query.getInstance().getOrgHrchStruDfnList(glblMsg);

        if (orgHrchStruDfnResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) orgHrchStruDfnResult.getResultObject();
            for (Map<String, Object> resultMap : resultList) {

                if (!dispIntegrityPkList.contains(resultMap.get("ORG_HRCH_STRU_DFN_PK"))) {
                    sortKeyword.setLength(0);
                    sortKeyword.append(resultMap.get("BIZ_AREA_ORG_CD"));
                    sortKeyword.append(resultMap.get("EFF_FROM_DT"));

                    if (ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT"))) {
                        sortKeyword.append(resultMap.get("EFF_THRU_DT"));
                    } else {
                        sortKeyword.append("99991231");
                    }
                    sortKeyword.append(ZYPCommonFunc.leftPad(resultMap.get("ORG_HRCH_STRU_DFN_PK").toString(), 28, "0"));
                    // 2016/07/21 CSA-QC#12216 ADD Start
                    // dummy index
                    sortKeyword.append("999");
                    // 2016/07/21 CSA-QC#12216 ADD End
                    checkIntegrityList.add(sortKeyword.toString());
                }
            }
        }

        Collections.sort(checkIntegrityList);

        // Check Integrity From - To
        String wkBizAreaOrgCd = "";
        String wkEffThruDt = "";
        int index = 0;
        for (int i = 0; i < checkIntegrityList.size(); i++) {
            if (i == 37) {
                String a= null;
            }
            String b = checkIntegrityList.get(i);
            String bizAreaOrgCd = checkIntegrityList.get(i).substring(0, 8);
            String effFromDt = checkIntegrityList.get(i).substring(8, 16);
            String effThruDt = checkIntegrityList.get(i).substring(16, 24);
            // 2016/07/21 CSA-QC#12216 ADD Start
            if (checkIntegrityList.get(i).toString().length() >= 52) {
                index = Integer.parseInt(checkIntegrityList.get(i).substring(52));
            } else {
                index = Integer.parseInt(checkIntegrityList.get(i).substring(24));
            }
            // 2016/07/21 CSA-QC#12216 ADD End

            if (i != 0 && bizAreaOrgCd.equals(wkBizAreaOrgCd)) {
                if (!effFromDt.equals(ZYPDateUtil.addDays(wkEffThruDt, 1))) {
                    // 2016/07/21 CSA-QC#12216 DELETE Start
//                    for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
//                        if (bizAreaOrgCd.replaceFirst("^0+", "").equals(glblMsg.A.no(j).bizAreaOrgCd_A.getValue())) {
//                            if (NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(j).xxRowId_A.getValue())) {
//                                // Case AddData
//                                glblMsg.A.no(j).effFromDt_A.setErrorInfo(1, NMAL2560Constant.NMAM8514E);
//                                errFlg = true;
//                            } else {
//                                // Case Not AddData
//                                if (!pk.replaceFirst("^0+", "").equals(glblMsg.A.no(j).orgHrchStruDfnPk_1.getValue().toString())) {
//                                    glblMsg.A.no(j).effFromDt_A.setErrorInfo(1, NMAL2560Constant.NMAM8514E);
//                                    errFlg = true;
//                                }
//                            }
//                        }
//                    }
                    // 2016/07/21 CSA-QC#12216 DELETE End
                    // 2016/07/21 CSA-QC#12216 ADD Start
                    if (bizAreaOrgCd.replaceFirst("^0+", "").equals(glblMsg.A.no(index).bizAreaOrgCd_A.getValue())) {
                        if (NMAL2560Constant.INSERT_FLG.equals(glblMsg.A.no(index).xxRowId_A.getValue())) {
                            // Case AddData
                            glblMsg.A.no(index).effFromDt_A.setErrorInfo(1, NMAL2560Constant.NMAM8514E);
                            errFlg = true;
                        }
                    }
                    // 2016/07/21 CSA-QC#12216 ADD End
                }
            }
            wkBizAreaOrgCd = bizAreaOrgCd;
            wkEffThruDt = effThruDt;
        }

        // ////////
        // Check Duplicated Tier1-10
        // ////////
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // struDfnCd_1
            if (glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_2.getValue()) || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_3.getValue())
                    || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_4.getValue()) || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_5.getValue())
                    || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_6.getValue()) || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue())
                    || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue()) || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue())
                    || glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                glblMsg.A.no(i).struDfnCd_1.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                errFlg = true;
            }
            // struDfnCd_2
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_2)) {
                if (glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_3.getValue()) || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_4.getValue())
                        || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_5.getValue()) || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_6.getValue())
                        || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue()) || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue())
                        || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue()) || glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_2.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_3
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_3)) {
                if (glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_4.getValue()) || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_5.getValue())
                        || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_6.getValue()) || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue())
                        || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue()) || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue())
                        || glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_3.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_4
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_4)) {
                if (glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_5.getValue()) || glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_6.getValue())
                        || glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue()) || glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue())
                        || glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue()) || glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_4.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_5
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_5)) {
                if (glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.A.no(i).struDfnCd_6.getValue()) || glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue())
                        || glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue()) || glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue())
                        || glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_5.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_6
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_6)) {
                if (glblMsg.A.no(i).struDfnCd_6.getValue().equals(glblMsg.A.no(i).struDfnCd_7.getValue()) || glblMsg.A.no(i).struDfnCd_6.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue())
                        || glblMsg.A.no(i).struDfnCd_6.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue()) || glblMsg.A.no(i).struDfnCd_6.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_6.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_7
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_7)) {
                if (glblMsg.A.no(i).struDfnCd_7.getValue().equals(glblMsg.A.no(i).struDfnCd_8.getValue()) || glblMsg.A.no(i).struDfnCd_7.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue())
                        || glblMsg.A.no(i).struDfnCd_7.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_7.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_8
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_8)) {
                if (glblMsg.A.no(i).struDfnCd_8.getValue().equals(glblMsg.A.no(i).struDfnCd_9.getValue()) || glblMsg.A.no(i).struDfnCd_8.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_8.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
            // struDfnCd_9
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).struDfnCd_9)) {
                if (glblMsg.A.no(i).struDfnCd_9.getValue().equals(glblMsg.A.no(i).struDfnCd_10.getValue())) {
                    glblMsg.A.no(i).struDfnCd_9.setErrorInfo(1, NMAL2560Constant.NMAM8517E);
                    errFlg = true;
                }
            }
        }

        if (errFlg) {
            EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
            return;
        }

        // ////////
        // Registration/Update process
        // ////////
        boolean regUpdFlg = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            BIZ_AREA_ORGTMsg bizAreaOrgTMsg = new BIZ_AREA_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(bizAreaOrgTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(bizAreaOrgTMsg.bizAreaOrgCd, glblMsg.A.no(i).bizAreaOrgCd_A.getValue());
            bizAreaOrgTMsg = (BIZ_AREA_ORGTMsg) EZDTBLAccessor.findByKey(bizAreaOrgTMsg);

            String rowIdA = glblMsg.A.no(i).xxRowId_A.getValue();

            // Registration process
            if (NMAL2560Constant.INSERT_FLG.equals(rowIdA)) {

                for (int tierCnt = 0; tierCnt < NMAL2560Constant.TIER_CNT; tierCnt++) {
                    ORG_HRCH_STRU_DFNTMsg orgHrchStruDfnTmsg = new ORG_HRCH_STRU_DFNTMsg();
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORG_HRCH_STRU_DFN_SQ));
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnNm, glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue());
                    if (bizAreaOrgTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgStruTpCd, bizAreaOrgTMsg.orgStruTpCd.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.bizAreaOrgCd, glblMsg.A.no(i).bizAreaOrgCd_A.getValue());
                    if (tierCnt == 0) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._1);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_1.getValue());
                    } else if (tierCnt == 1) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._2);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_2.getValue());
                    } else if (tierCnt == 2) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._3);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_3.getValue());
                    } else if (tierCnt == 3) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._4);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_4.getValue());
                    } else if (tierCnt == 4) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._5);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_5.getValue());
                    } else if (tierCnt == 5) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._6);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_6.getValue());
                    } else if (tierCnt == 6) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._7);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_7.getValue());
                    } else if (tierCnt == 7) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._8);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_8.getValue());
                    } else if (tierCnt == 8) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._9);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_9.getValue());
                    } else if (tierCnt == 9) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgTierCd, ORG_TIER._10);
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_10.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.effFromDt, glblMsg.A.no(i).effFromDt_A.getValue());
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.effThruDt, glblMsg.A.no(i).effThruDt_A.getValue());
                    EZDTBLAccessor.create(orgHrchStruDfnTmsg);

                    // Case of abnormal
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgHrchStruDfnTmsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAL2560Constant.NMAM8020E);
                        return;
                    }
                }
                regUpdFlg = true;
                continue;
            }

            // Update process
            for (int j = 0; j < glblMsg.B.length(); j++) {

                String rowIdB = glblMsg.B.no(j).xxRowId_B.getValue();

                if (!rowIdA.equals(rowIdB)) {
                    continue;
                }

                // check diff
                if (glblMsg.A.no(i).bizAreaOrgCd_A.getValue().equals(glblMsg.B.no(j).bizAreaOrgCd_B.getValue()) && glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue().equals(glblMsg.B.no(j).orgHrchStruDfnNm_B.getValue())
                        && glblMsg.A.no(i).effFromDt_A.getValue().equals(glblMsg.B.no(j).effFromDt_B.getValue()) && glblMsg.A.no(i).effThruDt_A.getValue().equals(glblMsg.B.no(j).effThruDt_B.getValue())
                        && glblMsg.A.no(i).struDfnCd_1.getValue().equals(glblMsg.B.no(j).struDfnCd_1.getValue()) && glblMsg.A.no(i).struDfnCd_2.getValue().equals(glblMsg.B.no(j).struDfnCd_2.getValue())
                        && glblMsg.A.no(i).struDfnCd_3.getValue().equals(glblMsg.B.no(j).struDfnCd_3.getValue()) && glblMsg.A.no(i).struDfnCd_4.getValue().equals(glblMsg.B.no(j).struDfnCd_4.getValue())
                        && glblMsg.A.no(i).struDfnCd_5.getValue().equals(glblMsg.B.no(j).struDfnCd_5.getValue()) && glblMsg.A.no(i).struDfnCd_6.getValue().equals(glblMsg.B.no(j).struDfnCd_6.getValue())
                        && glblMsg.A.no(i).struDfnCd_7.getValue().equals(glblMsg.B.no(j).struDfnCd_7.getValue()) && glblMsg.A.no(i).struDfnCd_8.getValue().equals(glblMsg.B.no(j).struDfnCd_8.getValue())
                        && glblMsg.A.no(i).struDfnCd_9.getValue().equals(glblMsg.B.no(j).struDfnCd_9.getValue()) && glblMsg.A.no(i).struDfnCd_10.getValue().equals(glblMsg.B.no(j).struDfnCd_10.getValue())) {
                    break;
                }

                for (int tierCnt = 0; tierCnt < NMAL2560Constant.TIER_CNT; tierCnt++) {

                    // check exclusion
                    ORG_HRCH_STRU_DFNTMsg orgHrchStruDfnTmsg = new ORG_HRCH_STRU_DFNTMsg();
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.glblCmpyCd, getGlobalCompanyCode());
                    if (tierCnt == 0) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_1.getValue());
                    } else if (tierCnt == 1) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_2.getValue());
                    } else if (tierCnt == 2) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_3.getValue());
                    } else if (tierCnt == 3) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_4.getValue());
                    } else if (tierCnt == 4) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_5.getValue());
                    } else if (tierCnt == 5) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_6.getValue());
                    } else if (tierCnt == 6) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_7.getValue());
                    } else if (tierCnt == 7) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_8.getValue());
                    } else if (tierCnt == 8) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_9.getValue());
                    } else if (tierCnt == 9) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnPk, glblMsg.A.no(i).orgHrchStruDfnPk_10.getValue());
                    }
                    orgHrchStruDfnTmsg = (ORG_HRCH_STRU_DFNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgHrchStruDfnTmsg);

                    if (orgHrchStruDfnTmsg == null
                            || !ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(i).ezUpTime_A.getValue(), glblMsg.A.no(i).ezUpTimeZone_A.getValue(), orgHrchStruDfnTmsg.ezUpTime.getValue(), orgHrchStruDfnTmsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NMAL2560Constant.NZZM0003E);
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.glblCmpyCd, orgHrchStruDfnTmsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgHrchStruDfnNm, glblMsg.A.no(i).orgHrchStruDfnNm_A.getValue());
                    if (bizAreaOrgTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.orgStruTpCd, bizAreaOrgTMsg.orgStruTpCd.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.bizAreaOrgCd, glblMsg.A.no(i).bizAreaOrgCd_A.getValue());
                    if (tierCnt == 0) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_1.getValue());
                    } else if (tierCnt == 1) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_2.getValue());
                    } else if (tierCnt == 2) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_3.getValue());
                    } else if (tierCnt == 3) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_4.getValue());
                    } else if (tierCnt == 4) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_5.getValue());
                    } else if (tierCnt == 5) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_6.getValue());
                    } else if (tierCnt == 6) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_7.getValue());
                    } else if (tierCnt == 7) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_8.getValue());
                    } else if (tierCnt == 8) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_9.getValue());
                    } else if (tierCnt == 9) {
                        ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.struDfnCd, glblMsg.A.no(i).struDfnCd_10.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.effFromDt, glblMsg.A.no(i).effFromDt_A.getValue());
                    ZYPEZDItemValueSetter.setValue(orgHrchStruDfnTmsg.effThruDt, glblMsg.A.no(i).effThruDt_A.getValue());
                    EZDTBLAccessor.update(orgHrchStruDfnTmsg);

                    // Case of abnormal
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgHrchStruDfnTmsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAL2560Constant.NMAM8020E);
                        return;
                    }
                }
                regUpdFlg = true;
                break;
            }
        }

        // No change has been made.
        if (!regUpdFlg) {
            bizMsg.setMessageInfo(NMAL2560Constant.NMAM8333I);
        }
    }

}
