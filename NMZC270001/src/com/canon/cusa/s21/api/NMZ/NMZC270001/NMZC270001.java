/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC270001;

import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.DB_PARAM_MAX_EFF_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.DB_PARAM_MAX_TRTY_RULE_THRU;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.DEFAULT_VALUE_FOR_END_DATE;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.EFF_THRU_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMAM8401E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMAM8442E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMAM8443E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0070E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0071E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0072E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0073E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0074E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0075E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0076E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0077E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0078E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0079E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0080E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0081E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0082E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0083E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0084E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0085E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0087E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0088E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0089E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0092E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0185E;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0359W;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0360W;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.NMZM0361W;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_FIRST_HALF_LENGTH;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_FORMAT10;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_FORMAT5;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_FORMAT_LIKE;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_LENGTH;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_MAX_SECOND_HALF;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.POSTAL_CODE_MIN_SECOND_HALF;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.TRTY_RULE_FROM_VAL_TXT;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.TRTY_RULE_LOGIC_TP_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.TRTY_RULE_OPRD_TP_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.TRTY_RULE_THRU_VAL_TXT;
import static com.canon.cusa.s21.api.NMZ.NMZC270001.Constant.NMZC270001Constant.TRTY_RULE_TP_CD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.parts.NMZC270001PMsg;
import business.parts.NMZC270001_trtyRuleListPMsg;
import business.parts.NMZC270001_trtyRuleListPMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 *<pre>
 * NMZC2700 Territory Rule Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         K.Minamide      Create          N/A
 * 2016/01/14   Fujitsu         K.Koitabashi    Update          QC#2973, QC#3154
 * 2016/01/19   Fujitsu         K.Koitabashi    Update          QC#3320
 * 2016/03/13   Fujitsu         N.Sugiura       Update          CSA-QC#5364
 * 2016/03/18   Fujitsu         C.Yokoi         Update          CSA-QC#5658
 * 2016/03/22   Fujitsu         C.Yokoi         Update          CSA-QC#5364
 * 2016/03/30   Fujitsu         C.Yokoi         Update          CSA-QC#6255
 * 2016/06/21   Hitachi         A.Kohinata      Update          CSA-QC#10593
 * 2016/07/05   Fujitsu         W.Honda         Update          CSA-QC#11391
 * 2016/07/12   Hitachi         Y.Tsuchimoto    Update          CSA-QC#11726
 * 2016/07/21   Hitachi         J.Kim           Update          CSA-QC#11908
 * 2016/08/31   SRAA            Y.Chen          Update          QC#11728
 * 2016/09/14   SRAA            Y.Chen          Update          QC#11638
 * 2016/10/14   Fujitsu         C.Yokoi         Update          CSA-QC#15218
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 *</pre>
 */
public class NMZC270001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /** Active Territory rule bean list**/
    private List<NWZC270001_TrtyRuleBean> trtyRuleBeanList = new ArrayList<NWZC270001_TrtyRuleBean>();

    /**
     * Constructor
     */
    public NMZC270001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(getClass());

    }

    /**
     * execute (This can be called method from external class.)
     * @param pMsg NMZC270001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC270001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(pMsg);

        this.doProcess(pMsg, onBatchType);
        
        // QC#11728
        moveErrorMessageFromActiveRuleBeanToPMsg(pMsg);

        this.msgMap.flush();
    }
    
    // QC#11728
    private void moveErrorMessageFromActiveRuleBeanToPMsg(NMZC270001PMsg pMsg) {
        for (NWZC270001_TrtyRuleBean bean : this.trtyRuleBeanList) {
            String msgId = bean.getXxMsgId();
            String msgPrmTxt = bean.getXxMsgPrmTxt();
            if (ZYPCommonFunc.hasValue(msgId)) {
                int rowInPMsg = bean.getXxRowNum();
                if (rowInPMsg >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(rowInPMsg).xxMsgId_R, msgId);
                    ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(rowInPMsg).xxMsgPrmTxt_R1, msgPrmTxt);
                }
            }
        }
    }

    /**
     * Main process method.
     * @param pMsg NMZC270001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(NMZC270001PMsg pMsg, ONBATCH_TYPE onBatchType) {

        // 2016/07/21 CSA-QC#11908 Add Start
        this.trtyRuleBeanList.clear();
        // 2016/07/21 CSA-QC#11908 Add End

        if (!mandataryCheckInput(pMsg)) {
            return;
        }

        if (!singleItemCheckInput(pMsg)) {
            return;
        }

        if (!sameTerritoryCheck(pMsg)) {
            return;
        }

        if (!duplicateTerritoryRuleCheck(pMsg, onBatchType)) {
            return;
        }

        // 2016/03/22 CSA-QC#5364 Add Start
        if (!existTerritoryCheckForAnd(pMsg)) {
            return;
        }
        // 2016/03/22 CSA-QC#5364 Add End

        if (!existTerritoryCheck(pMsg)) {
            return;
        }

    }

    /**
     * Check Mandatary Input Parameter
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean mandataryCheckInput(NMZC270001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NMZM0092E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.orgCd)) {
            this.msgMap.addXxMsgId(NMZM0071E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.firstOrgCd)) {
            this.msgMap.addXxMsgId(NMZM0072E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.trtyTpCd)) {
            this.msgMap.addXxMsgId(NMZM0073E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.orgTierCd)) {
            this.msgMap.addXxMsgId(NMZM0074E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.orgStruTpCd)) {
            this.msgMap.addXxMsgId(NMZM0075E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.trtyGrpTpCd)) {
            this.msgMap.addXxMsgId(NMZM0076E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.effFromDt_T)) {
            this.msgMap.addXxMsgId(NMZM0077E);
            return false;
        }

        NMZC270001_trtyRuleListPMsgArray trtyRuleList = pMsg.trtyRuleList;

        for (int i = 0; i < trtyRuleList.getValidCount(); i++) {
            NMZC270001_trtyRuleListPMsg trtyRuleListPMsg = trtyRuleList.no(i);
            if (!ZYPCommonFunc.hasValue(trtyRuleListPMsg.trtyRuleTpCd)) {
                this.msgMap.addXxMsgId(NMZM0078E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0078E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_TP_CD);
                return false;
            }
            if (!ZYPCommonFunc.hasValue(trtyRuleListPMsg.trtyRuleOprdTpCd)) {
                this.msgMap.addXxMsgId(NMZM0079E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0079E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_OPRD_TP_CD);
                return false;
            }
            if (!ZYPCommonFunc.hasValue(trtyRuleListPMsg.trtyRuleFromValTxt)) {
                this.msgMap.addXxMsgId(NMZM0080E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0080E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_FROM_VAL_TXT);
                return false;
            }
            if (!ZYPCommonFunc.hasValue(trtyRuleListPMsg.effFromDt_R)) {
                this.msgMap.addXxMsgId(NMZM0081E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0081E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(EFF_FROM_DT);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgMap.addXxMsgId(NMZM0082E);
            return false;
        }

        return true;
    }

    /**
     * Check single item Input Parameter
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean singleItemCheckInput(NMZC270001PMsg pMsg) {

        NMZC270001_trtyRuleListPMsgArray trtyRuleList = pMsg.trtyRuleList;

        String effFromDtT = pMsg.effFromDt_T.getValue();
        String effThruDtT = pMsg.effThruDt_T.getValue();

        // for LogicTp check
        String prevTrtyRuleLogicTpCd = null;
        boolean isAllLogicTpEntered = true;
        int i = 0;

        for (; i < trtyRuleList.getValidCount(); i++) {

            NMZC270001_trtyRuleListPMsg trtyRuleListPMsg = trtyRuleList.no(i);

            // Postal Code Format Check
            String trtyRuleTpCd = trtyRuleListPMsg.trtyRuleTpCd.getValue();
            String trtyRuleOprdTpCd = trtyRuleListPMsg.trtyRuleOprdTpCd.getValue();
            String trtyRuleFromValTxt = trtyRuleListPMsg.trtyRuleFromValTxt.getValue();
            String trtyRuleThruValTxt = trtyRuleListPMsg.trtyRuleThruValTxt.getValue();
            // Mod Start 2018/06/01 QC#24293
            //if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd) && !TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCd)) {
            if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd)) {
                // Mod End 2018/06/01 QC#24293
                StringBuffer errItemNm = new StringBuffer();
                if (!postalNotLikeCheck(trtyRuleFromValTxt, trtyRuleThruValTxt, errItemNm)) {
                    this.msgMap.addXxMsgId(NMZM0070E);
                    // QC#11728
                    trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0070E);
                    ZYPEZDItemValueSetter.setValue(trtyRuleListPMsg.xxTblItemTxt_R, errItemNm.toString());
                    return false;
                }
                // Del Start 2018/06/01 QC#24293
            //} else if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd) && TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCd)) {
            //    StringBuffer errItemNm = new StringBuffer();
            //    if (!postalLikeCheck(trtyRuleFromValTxt, trtyRuleThruValTxt, errItemNm)) {
            //        this.msgMap.addXxMsgId(NMZM0070E);
            //        // QC#11728
            //        trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0070E);
            //        ZYPEZDItemValueSetter.setValue(trtyRuleListPMsg.xxTblItemTxt_R, errItemNm.toString());
            //        return false;
            //    }
                // Del End 2018/06/01 QC#24293
            }
            // Effective Date Check
            // territory Date needs to include territory rule Date
            String effFromDtR = trtyRuleListPMsg.effFromDt_R.getValue();
            String effThruDtR = trtyRuleListPMsg.effThruDt_R.getValue();

            if (effFromDtT.compareTo(effFromDtR) > 0) {
                this.msgMap.addXxMsgId(NMZM0083E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0083E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(EFF_FROM_DT);
                return false;
            }
            if (ZYPCommonFunc.hasValue(effThruDtT) && ZYPCommonFunc.hasValue(effThruDtR) && effThruDtR.compareTo(effThruDtT) > 0) {
                this.msgMap.addXxMsgId(NMZM0083E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0083E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(EFF_THRU_DT);
                return false;
            }

            // Operator Check(BETWEEN)
            if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCd)) {
                if (!ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                    this.msgMap.addXxMsgId(NMZM0084E);
                    // QC#11728
                    trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0084E);
                    trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_THRU_VAL_TXT);
                    return false;
                }
               if (trtyRuleFromValTxt.compareTo(trtyRuleThruValTxt) > 0) {
                   this.msgMap.addXxMsgId(NMZM0185E);
                   // QC#11728
                   trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0185E);
                   trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_THRU_VAL_TXT);
                   return false;
                }

            } else if (!TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCd)) {
                if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                    this.msgMap.addXxMsgId(NMZM0085E);
                    // QC#11728
                    trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0085E);
                    trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_THRU_VAL_TXT);
                    return false;
                }
            }

            // last logicTP must be null
            String trtyRuleLogicTpCd = trtyRuleListPMsg.trtyRuleLogicTpCd.getValue();
            // if (i == trtyRuleList.getValidCount() - 1) {
            //    if (ZYPCommonFunc.hasValue(trtyRuleLogicTpCd)) {
            //        this.msgMap.addXxMsgId(NMZM0086E);
            //        return false;
            //    }
            // }

             // 2016/03/30 CSA-QC#6255 Add Start
             if (!ZYPCommonFunc.hasValue(trtyRuleLogicTpCd)) {
                 isAllLogicTpEntered = false;
             }
             // 2016/03/30 CSA-QC#6255 Add End

             // Current logic equal to previous logic
            if (ZYPCommonFunc.hasValue(prevTrtyRuleLogicTpCd) && !trtyRuleLogicTpCd.equals(prevTrtyRuleLogicTpCd)) {
                this.msgMap.addXxMsgId(NMZM0089E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0089E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_LOGIC_TP_CD);
                return false;
            }

            // Any logic must be set except 1 rule only.
            if (i != trtyRuleList.getValidCount() - 1 && !ZYPCommonFunc.hasValue(trtyRuleLogicTpCd)) {
                this.msgMap.addXxMsgId(NMZM0089E);
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0089E);
                trtyRuleListPMsg.xxTblItemTxt_R.setValue(TRTY_RULE_LOGIC_TP_CD);
                return false;
            }
            prevTrtyRuleLogicTpCd = trtyRuleLogicTpCd;
        }

        // 2016/03/30 CSA-QC#6255 Add Start
        if (i > 1) {
            if (!isAllLogicTpEntered) {
                this.msgMap.addXxMsgId(NMAM8442E);
                return false;
            }
        } else {
            // 2016/06/21 CSA-QC#10593 Mod Start
//            if (isAllLogicTpEntered) {
            if (!TRTY_RULE_LOGIC_TP.OR.equals(prevTrtyRuleLogicTpCd)) {
                this.msgMap.addXxMsgId(NMAM8443E);
                return false;
            }
            // 2016/06/21 CSA-QC#10593 Mod End
        }
        // 2016/03/30 CSA-QC#6255 Add End

        return true;
    }

    /**
     * postal check for except "LIKE"
     * @param trtyRuleFromValTxt not null
     * @param trtyRuleThruValTxt
     * @param errItemNm
     * @return No Error : true
     */
    private static boolean postalNotLikeCheck(String trtyRuleFromValTxt, String trtyRuleThruValTxt, StringBuffer errItemNm) {
        if (trtyRuleFromValTxt.length() != POSTAL_CODE_FIRST_HALF_LENGTH && trtyRuleFromValTxt.length() != POSTAL_CODE_LENGTH) {
            errItemNm.append(TRTY_RULE_FROM_VAL_TXT);
            return false;
        }
        // trtyRuleThruValTxt may be null
        if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt) && trtyRuleThruValTxt.length() != POSTAL_CODE_FIRST_HALF_LENGTH && trtyRuleThruValTxt.length() != POSTAL_CODE_LENGTH) {
            errItemNm.append(TRTY_RULE_THRU_VAL_TXT);
            return false;
        }
        if (trtyRuleFromValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
            if (!validateFormat(POSTAL_CODE_FORMAT5, trtyRuleFromValTxt)) {
                errItemNm.append(TRTY_RULE_FROM_VAL_TXT);    
                return false;
            }
        } else if (trtyRuleFromValTxt.length() == POSTAL_CODE_LENGTH) {
            if (!validateFormat(POSTAL_CODE_FORMAT10, trtyRuleFromValTxt)) {
                errItemNm.append(TRTY_RULE_FROM_VAL_TXT);
                return false;
            }
        }
        if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt) && trtyRuleThruValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
            if (!validateFormat(POSTAL_CODE_FORMAT5, trtyRuleThruValTxt)) {
                errItemNm.append(TRTY_RULE_THRU_VAL_TXT);
                return false;
            }
        } else if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt) && trtyRuleThruValTxt.length() == POSTAL_CODE_LENGTH) {
            if (!validateFormat(POSTAL_CODE_FORMAT10, trtyRuleThruValTxt)) {
                errItemNm.append(TRTY_RULE_THRU_VAL_TXT);
                return false;
            }
        }
        return true;
    }

    /**
     * @param trtyRuleFromValTxt
     * @param trtyRuleThruValTxt must be null
     * @return No Error : true
     */
    private static boolean postalLikeCheck(String trtyRuleFromValTxt, String trtyRuleThruValTxt, StringBuffer errItemNm) {
        if (trtyRuleFromValTxt.length() > POSTAL_CODE_LENGTH) {
            errItemNm.append(TRTY_RULE_FROM_VAL_TXT);
            return false;
        }
        if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt) && trtyRuleThruValTxt.length() > POSTAL_CODE_LENGTH) {
            errItemNm.append(TRTY_RULE_THRU_VAL_TXT);
            return false;
        }
        if (!validateFormat(POSTAL_CODE_FORMAT_LIKE, trtyRuleFromValTxt)) {
            errItemNm.append(TRTY_RULE_FROM_VAL_TXT);
            return false;
        }
        if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
            if (!validateFormat(POSTAL_CODE_FORMAT_LIKE, trtyRuleThruValTxt)) {
                errItemNm.append(TRTY_RULE_THRU_VAL_TXT);
                return false;
            }
        }
        return true;
    }

    /**
     * duplicate territory rule check
     * @param pMsg NMZC270001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return No Error : true
     */
    private boolean duplicateTerritoryRuleCheck(NMZC270001PMsg pMsg, ONBATCH_TYPE onBatchType) {
     // 2016/07/12 CSA-QC#11726 Mod start
     // copyActiveRuleToBean(pMsg);
     // if (this.trtyRuleBeanList.isEmpty()) {
     //        return true;
     //    }
     // 2016/07/12 CSA-QC#11726 Mod End

        // 2016/10/14 CSA-QC#15218 Add Start
        if (this.trtyRuleBeanList.isEmpty()) {
            return true;
        }
        // 2016/10/14 CSA-QC#15218 Add End

        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
             S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
             execParam.setFetchSize(DEFAULT_FETCH_SIZE);
             execParam.setMaxRows(0);
             execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
             execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
             execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

             Map<String, Object> ssmParam = getDuplicateTerritoryId(pMsg);

             stmt = ssmLLClient.createPreparedStatement("getDuplicateTerritoryId", ssmParam, execParam);
             rsSet = stmt.executeQuery();

             boolean duplicatedFlg = false;
             String prevOrgCd = null;
             List<Integer> idxList = null;
             // QC#11638
             List<NWZC270001_TrtyRuleBean> listDuplicate = new ArrayList<NWZC270001_TrtyRuleBean>();

             while (rsSet.next()) {
                 if (!rsSet.getString("ORG_CD").equals(prevOrgCd)) {
                     prevOrgCd = rsSet.getString("ORG_CD");
                     idxList = new ArrayList<Integer>();
                     // QC#11638
                     listDuplicate = new ArrayList<NWZC270001_TrtyRuleBean>();
                 }

                 for (int i = 0; i < this.trtyRuleBeanList.size(); i++) {
                     NWZC270001_TrtyRuleBean trtyRule = this.trtyRuleBeanList.get(i);

                      if (!checkEqualVal(trtyRule.getTrtyRuleFromValTxt(), rsSet.getString("TRTY_RULE_FROM_VAL_TXT"))) {
                          continue;
                      }

                      if (!checkEqualVal(trtyRule.getTrtyRuleThruValTxt(), rsSet.getString("TRTY_RULE_THRU_VAL_TXT"))) {
                          continue;
                      }

                      if (!checkEqualVal(trtyRule.getTrtyRuleTpCd(), rsSet.getString("TRTY_RULE_TP_CD"))) {
                          continue;
                      }

                      if (!checkEqualVal(trtyRule.getTrtyRuleOprdTpCd(), rsSet.getString("TRTY_RULE_OPRD_TP_CD"))) {
                          continue;
                      }
                      // QC#11638
                      String pmsgEffFromDt = trtyRule.getEffFromDt();
                      String pmsgEffThrueDt = trtyRule.getEffThruDt();
                      String dbEffFromDt = rsSet.getString("EFF_FROM_DT");
                      String dbEffThruDt = rsSet.getString("EFF_THRU_DT");
                      if (!isDuplicateDate(pmsgEffFromDt, pmsgEffThrueDt, dbEffFromDt, dbEffThruDt)) {
                          continue;
                      }

                      // all equal is duplicate
                      idxList.add(i);
                      // QC#11638
                      listDuplicate.add(trtyRule);
                 }

                 // QC#11638
                 if(TRTY_RULE_LOGIC_TP.OR.equals(rsSet.getString("TRTY_RULE_LOGIC_TP_CD"))){
                     if(idxList != null && idxList.size() > 0){
                         duplicatedFlg = true;
                         for (NWZC270001_TrtyRuleBean trtyRule : listDuplicate) {
                             // 2017/11/16 CSA-QC#20597 Mod Start
                             // trtyRule.setXxMsgId(NMZM0332E);
                             trtyRule.setXxMsgId(NMZM0360W);
                             // 2017/11/16 CSA-QC#20597 Mod End
                             trtyRule.setXxMsgPrmTxt(rsSet.getString("ORG_NM"));
                         }    
                         // break;
                     }
                 }

                 // if exist not match record, it's not duplication.
                 if (this.trtyRuleBeanList.size() == idxList.size()) {
                     duplicatedFlg = true;
                     // QC#11728
                     for (int i = 0; i < this.trtyRuleBeanList.size(); i++) {
                         NWZC270001_TrtyRuleBean trtyRule = this.trtyRuleBeanList.get(i);
                         // 2017/11/16 CSA-QC#20597 Mod Start
                         // trtyRule.setXxMsgId(NMZM0332E);
                         trtyRule.setXxMsgId(NMZM0360W);
                         // 2017/11/16 CSA-QC#20597 Mod End
                         trtyRule.setXxMsgPrmTxt(rsSet.getString("ORG_NM"));
                     }    
                     // break;
                 }
             }

            if (duplicatedFlg) {
                // 2017/11/16 CSA-QC#20597 Mod Start
                // this.msgMap.addXxMsgId(NMZM0088E);
                if (onBatchType.equals(ONBATCH_TYPE.ONLINE)) {
                    this.msgMap.addXxMsgId(NMZM0359W);
                } else {
                    this.msgMap.addXxMsgId(NMZM0361W);
                }
                // 2017/11/16 CSA-QC#20597 Mod End
                return false;
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        // 2016/07/05 CSA-QC#11391 Mod end
        return true;
    }

    /**
     * same territry check (for input all rules)
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean sameTerritoryCheck(NMZC270001PMsg pMsg) {
        // 2016/07/12 CSA-QC#11726 Mod start
        // for (int i = 0; i < pMsg.trtyRuleList.getValidCount(); i++) {
        //    NWZC270001_TrtyRuleBean trtyRuleBean = new NWZC270001_TrtyRuleBean(pMsg.trtyRuleList.no(i));
        //     trtyRuleBeanList.add(trtyRuleBean);
        // }
        copyActiveRuleToBean(pMsg);
        // 2016/07/12 CSA-QC#11726 Mod end

        if (this.trtyRuleBeanList.isEmpty()) {
            return true;
        }

        // 2016/07/12 CSA-QC#11726 Mod start
        boolean duplicatedFlg = false;
        for (int i = 0; i < this.trtyRuleBeanList.size(); i++) {
            NWZC270001_TrtyRuleBean targetTrtyRule = this.trtyRuleBeanList.get(i);

            for (int j = 0; j < this.trtyRuleBeanList.size(); j++) {
                if (i >= j) {
                    continue;
                }
                NWZC270001_TrtyRuleBean checkTrtyRule = this.trtyRuleBeanList.get(j);
                if (checkTrtyRule == null) {
                    continue;
                }
                // Rule Type duplicate check.
                if (ZYPCommonFunc.hasValue(checkTrtyRule.getTrtyRuleTpCd()) && !checkTrtyRule.getTrtyRuleTpCd().equals(targetTrtyRule.getTrtyRuleTpCd())) {
                    continue;
                }
                // Operator duplicate check.
                if (ZYPCommonFunc.hasValue(checkTrtyRule.getTrtyRuleOprdTpCd()) && !checkTrtyRule.getTrtyRuleOprdTpCd().equals(targetTrtyRule.getTrtyRuleOprdTpCd())) {
                    continue;
                }
                // Value From duplicate check.
                if (ZYPCommonFunc.hasValue(checkTrtyRule.getTrtyRuleFromValTxt()) && !checkTrtyRule.getTrtyRuleFromValTxt().equals(targetTrtyRule.getTrtyRuleFromValTxt())) {
                    continue;
                }
                // Value To duplicate check.
                if (ZYPCommonFunc.hasValue(checkTrtyRule.getTrtyRuleThruValTxt()) && !checkTrtyRule.getTrtyRuleThruValTxt().equals(targetTrtyRule.getTrtyRuleThruValTxt())) {
                    continue;
                } else if (!ZYPCommonFunc.hasValue(checkTrtyRule.getTrtyRuleThruValTxt()) && ZYPCommonFunc.hasValue(targetTrtyRule.getTrtyRuleThruValTxt())) {
                    continue;
                }
                // Start Date, End Date duplicate check.
                if (isDuplicateDate(targetTrtyRule.getEffFromDt(), targetTrtyRule.getEffThruDt(), checkTrtyRule.getEffFromDt(), checkTrtyRule.getEffThruDt())) {
                    duplicatedFlg = true;
                    // QC#11728
                    targetTrtyRule.setXxMsgId(NMZM0088E);
                    checkTrtyRule.setXxMsgId(NMZM0088E);
                    break;
                }
            }
            if (duplicatedFlg) {
                break;
            }
        }

        if (duplicatedFlg) {
            this.msgMap.addXxMsgId(NMZM0088E);
            return false;
        }
        return true;

//        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
//        PreparedStatement stmt = null;
//        ResultSet rsSet = null;
//
//        try {
//             S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//             execParam.setFetchSize(DEFAULT_FETCH_SIZE);
//             execParam.setMaxRows(0);
//             execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//             execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//             execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//             Map<String, Object> ssmParam = getDuplicateTerritoryId(pMsg);
//
//             stmt = ssmLLClient.createPreparedStatement("getDuplicateTerritoryId", ssmParam, execParam);
//             rsSet = stmt.executeQuery();
//
//             boolean duplicatedFlg = false;
//             String prevOrgCd = null;
//             List<Integer> idxList = null;
//             while (rsSet.next()) {
//                 if (!rsSet.getString("ORG_CD").equals(prevOrgCd)) {
//                     prevOrgCd = rsSet.getString("ORG_CD");
//                     idxList = new ArrayList<Integer>();
//                 }
//
//                 for (int i = 0; i < this.trtyRuleBeanList.size(); i++) {
//                     NWZC270001_TrtyRuleBean trtyRule = this.trtyRuleBeanList.get(i);
//
//                      if (!checkEqualVal(trtyRule.getTrtyRuleFromValTxt(), rsSet.getString("TRTY_RULE_FROM_VAL_TXT"))) {
//                          continue;
//                      }
//
//                      if (!checkEqualVal(trtyRule.getTrtyRuleThruValTxt(), rsSet.getString("TRTY_RULE_THRU_VAL_TXT"))) {
//                          continue;
//                      }
//
//                      if (!checkEqualVal(trtyRule.getTrtyRuleTpCd(), rsSet.getString("TRTY_RULE_TP_CD"))) {
//                          continue;
//                      }
//
//                      if (!checkEqualVal(trtyRule.getTrtyRuleOprdTpCd(), rsSet.getString("TRTY_RULE_OPRD_TP_CD"))) {
//                          continue;
//                      }
//                      // all equal is duplicate
//                      idxList.add(i);
//                 }
//
//                 // if exist not match record, it's not duplication.
//                 if (this.trtyRuleBeanList.size() == idxList.size()) {
//                     duplicatedFlg = true;
//                     break;
//                 }
//             }
//
//            if (duplicatedFlg) {
//                this.msgMap.addXxMsgId(NMZM0088E);
//                return false;
//            }
//            
//        } catch (SQLException se) {
//            sqlExceptionHandler(se);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
//        }
//        // 2016/07/05 CSA-QC#11391 Mod end
//        return true;
        // 2016/07/12 CSA-QC#11726 Mod end
    }

    // 2016/07/12 CSA-QC#11726 Add start
    private boolean isDuplicateDate(String targetStartDate, String targetEndDate, String checkStartDate, String checkEndDate) {
        if (!ZYPCommonFunc.hasValue(targetEndDate)) {
            targetEndDate = DEFAULT_VALUE_FOR_END_DATE;
        }
        if (!ZYPCommonFunc.hasValue(checkEndDate)) {
            checkEndDate = DEFAULT_VALUE_FOR_END_DATE;
        }
        if (targetStartDate.compareTo(checkEndDate) > 0) {
            return false;
        }
        if (targetEndDate.compareTo(checkStartDate) < 0) {
            return false;
        }
        return true;
    }
    // 2016/07/12 CSA-QC#11726 Add end

    /**
     * value equal check
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean checkEqualVal(String val1, String val2) {
        boolean resultFlg = false;
        if (ZYPCommonFunc.hasValue(val1)) {
            if (ZYPCommonFunc.hasValue(val2)) {
                if (val1.equals(val2)) {
                    resultFlg = true;
                }
            }
        } else {
            if (!ZYPCommonFunc.hasValue(val2)) {
                resultFlg = true;
            }
        }
        
        
        return resultFlg;
    }

    /**
     * exist territry check (for input all rules)
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean existTerritoryCheckForAnd(NMZC270001PMsg pMsg) {
        NMZC270001_trtyRuleListPMsgArray trtyRuleList = pMsg.trtyRuleList;

        NWZC270001_TrtyRuleBean trtyRuleBean = null;
        List<String[]> existsList = new ArrayList<String[]>();
        // QC#11728
        List<NMZC270001_trtyRuleListPMsg> checkList = new ArrayList<NMZC270001_trtyRuleListPMsg>();

        // if logic type is not "AND", do nothing
        if (trtyRuleList.getValidCount() <= 1 || !TRTY_RULE_LOGIC_TP.AND.equals(trtyRuleList.no(0).trtyRuleLogicTpCd.getValue())) {
            return true;
        }

        // List territory rules that contains postCode and "AND"
        for (int i = 0; i < trtyRuleList.getValidCount(); i++) {
            NMZC270001_trtyRuleListPMsg trtyRuleListPMsg = trtyRuleList.no(i);
            String trtyRuleTpCd = trtyRuleListPMsg.trtyRuleTpCd.getValue();
            String trtyRuleOprdTpCd = trtyRuleListPMsg.trtyRuleOprdTpCd.getValue();
            String trtyRuleFromValTxt = trtyRuleListPMsg.trtyRuleFromValTxt.getValue();
            String trtyRuleThruValTxt = trtyRuleListPMsg.trtyRuleThruValTxt.getValue();
            String effFromDt = trtyRuleListPMsg.effFromDt_R.getValue();
            String effThruDt = trtyRuleListPMsg.effThruDt_R.getValue();

            if (!checkActiveDate(effFromDt, effThruDt, ZYPDateUtil.getSalesDate())) {
                continue;
            }

            if (!TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd)) {
                continue;
            }

            existsList = makePatternList(trtyRuleOprdTpCd, trtyRuleFromValTxt, trtyRuleThruValTxt);

            if (trtyRuleBean == null) {
                trtyRuleBean = new NWZC270001_TrtyRuleBean(existsList.get(0));
            } else {
                if (!setPatternForTrtyRuleFromVal(trtyRuleBean, existsList.get(0)[0])) {
                    this.msgMap.addXxMsgId(NMAM8401E);
                    // QC#11728
                    trtyRuleListPMsg.xxMsgId_R.setValue(NMAM8401E);
                    return false;
                }

                if (!setPatternForTrtyRuleThruVal(trtyRuleBean, existsList.get(0)[1])) {
                    this.msgMap.addXxMsgId(NMAM8401E);
                    // QC#11728
                    trtyRuleListPMsg.xxMsgId_R.setValue(NMAM8401E);
                    return false;
                }
            }
            
            // QC#11728
            checkList.add(trtyRuleListPMsg);
        }

        if (trtyRuleBean == null) {
            return true;
        }

        // get territory rules that First 5 digits is overLapped
        List<Map<String, Object>> overlapTrtyRuleList = getOverlapTrtyRule(pMsg, trtyRuleBean);
        if (overlapTrtyRuleList.size() < 1) {
            return true;
        }

        existsList.clear();
        NWZC270001_TrtyRuleBean compareTrtyRuleBean = null;

        // check if all digits is overlapped
        for (Map<String, Object> overlapMap : overlapTrtyRuleList) {
            String overlapTrtyRuleOprdTpCd = (String) overlapMap.get("TRTY_RULE_OPRD_TP_CD");
            String overlapTrtyRuleFromValTx = (String) overlapMap.get("TRTY_RULE_FROM_VAL_TXT");
            String overlapTrtyRuleThruValTx = (String) overlapMap.get("TRTY_RULE_THRU_VAL_TXT");
            String overlapTrtyId = (String) overlapMap.get("ORG_CD");

            List<Map<String, Object>> activeOverlapTrtyRuleList = getTrtyRule(pMsg, overlapTrtyId);

            // single Territory rule or rules contains "OR" for logic type
            if (activeOverlapTrtyRuleList.size() == 1
                    || TRTY_RULE_LOGIC_TP.OR.equals(activeOverlapTrtyRuleList.get(0).get("TRTY_RULE_LOGIC_TP_CD"))) {
                existsList = makePatternList(overlapTrtyRuleOprdTpCd, overlapTrtyRuleFromValTx, overlapTrtyRuleThruValTx);

            } else {
                // territory rules which contains "AND" for logic type
                for (Map<String, Object> activeOverlapTrtyRule : activeOverlapTrtyRuleList) {
                    String activeOverlapTrtyRuleOprdTpCd = (String) activeOverlapTrtyRule.get("TRTY_RULE_OPRD_TP_CD");
                    String activeOverlapTrtyRuleFromValTx = (String) activeOverlapTrtyRule.get("TRTY_RULE_FROM_VAL_TXT");
                    String activeOverlapTrtyRuleThruValTx = (String) activeOverlapTrtyRule.get("TRTY_RULE_THRU_VAL_TXT");

                    existsList = makePatternList(activeOverlapTrtyRuleOprdTpCd, activeOverlapTrtyRuleFromValTx, activeOverlapTrtyRuleThruValTx);

                    if (compareTrtyRuleBean == null) {
                        compareTrtyRuleBean = new NWZC270001_TrtyRuleBean(existsList.get(0));
                    } else {
                        setPatternForTrtyRuleFromVal(compareTrtyRuleBean, existsList.get(0)[0]);
                        setPatternForTrtyRuleThruVal(compareTrtyRuleBean, existsList.get(0)[1]);
                    }
                }
            }

            if (compareTrtyRuleBean == null) {
                compareTrtyRuleBean = new NWZC270001_TrtyRuleBean(existsList.get(0));
            }

            // overlap check
            if (!checkPattern(trtyRuleBean, compareTrtyRuleBean)) {
                this.msgMap.addXxMsgIdWithPrm(NMZM0087E, new String[] {(String) overlapMap.get("ORG_NM") });
                // QC#11728
                for (NMZC270001_trtyRuleListPMsg subPMsg : checkList) {
                    subPMsg.xxMsgId_R.setValue(NMZM0087E);
                    subPMsg.xxMsgPrmTxt_R1.setValue((String) overlapMap.get("ORG_NM"));
                }
                return false;
            }

            // clear
            existsList.clear();
            compareTrtyRuleBean = null;
        }
        return true;
    }

    /**
     * exist territry check (for input all rules)
     * @param pMsg NMZC270001PMsg
     * @return No Error : true
     */
    private boolean existTerritoryCheck(NMZC270001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        String orgCd = pMsg.orgCd.getValue();
        // 2016/03/13 CSA-QC#5364 Del Start
        // String prevTrtyRuleLogicTpCd = null;
        // String prevOverlapTrtyRuleLogicTpCd = null;
        // String prevOverlapOrgCd = null;
        // boolean existAndTrtyRuleLogicTpCdFlg = false;
        // 2016/03/13 CSA-QC#5364 Del End
        boolean existOverlapFlg = false;

        NMZC270001_trtyRuleListPMsgArray trtyRuleList = pMsg.trtyRuleList;

        if (trtyRuleList.getValidCount() > 1
                && TRTY_RULE_LOGIC_TP.AND.equals(trtyRuleList.no(0).trtyRuleLogicTpCd.getValue())) {
            return true;
        }

        for (int i = 0; i < trtyRuleList.getValidCount(); i++) {

            NMZC270001_trtyRuleListPMsg trtyRuleListPMsg = trtyRuleList.no(i);
            String trtyRuleTpCd = trtyRuleListPMsg.trtyRuleTpCd.getValue();
            String trtyRuleOprdTpCd = trtyRuleListPMsg.trtyRuleOprdTpCd.getValue();
            String trtyRuleLogicTpCd = trtyRuleListPMsg.trtyRuleLogicTpCd.getValue();
            String trtyRuleFromValTxt = trtyRuleListPMsg.trtyRuleFromValTxt.getValue();
            String trtyRuleThruValTxt = trtyRuleListPMsg.trtyRuleThruValTxt.getValue();
            String trtyRuleEffFromDt = trtyRuleListPMsg.effFromDt_R.getValue();
            String trtyRuleEffThruDt = trtyRuleListPMsg.effThruDt_R.getValue();

            if (!TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd)) {
                continue;
            }
            
            // QC#11638
            if (!isCurrentOrFutureDate(trtyRuleEffFromDt, trtyRuleEffThruDt, slsDt)) {
                continue;
            }

//            existOverlapFlg = false;
//            Map<String, String> ssmDuplicateParam = new HashMap<String, String>();
//            // Duplicate check(LogicTpCd check is not need)
//            ssmDuplicateParam.put("glblCmpyCd", glblCmpyCd);
//            ssmDuplicateParam.put("trtyRuleTpCd", trtyRuleTpCd);
//            ssmDuplicateParam.put("trtyRuleOprdTpCd", trtyRuleOprdTpCd);
//            ssmDuplicateParam.put("trtyRuleFromValTxt", trtyRuleFromValTxt);
//            ssmDuplicateParam.put("trtyRuleThruValTxt", trtyRuleThruValTxt);
//            ssmDuplicateParam.put("slsDt", slsDt);
//            ssmDuplicateParam.put("orgCd", orgCd);
//
//            List<Map<String, Object>> duplicateList = this.ssmClient.queryObjectList("getDuplicateTrtyRule", ssmDuplicateParam);
//
//            if (duplicateList.size() > 0) {
//                this.msgMap.addXxMsgId(NMZM0088E);
//                return false;
//            }

            // 2016/03/13 CSA-QC#5364 Del Start
//            if (TRTY_RULE_LOGIC_TP.AND.equals(trtyRuleLogicTpCd) || TRTY_RULE_LOGIC_TP.AND.equals(prevTrtyRuleLogicTpCd)) {
//                prevTrtyRuleLogicTpCd = trtyRuleLogicTpCd;
//                continue;
//            }
            // 2016/03/13 CSA-QC#5364 Del End
            Map<String, String> ssmOverlapParam = new HashMap<String, String>();
            // Postal Code Range Overlap check
            ssmOverlapParam.put("glblCmpyCd", glblCmpyCd);
            ssmOverlapParam.put("orgStruTpCd", pMsg.orgStruTpCd.getValue());
            ssmOverlapParam.put("trtyGrpTpCd", pMsg.trtyGrpTpCd.getValue());
            ssmOverlapParam.put("trtyTpCd", pMsg.trtyTpCd.getValue());
            ssmOverlapParam.put("firstOrgCd", pMsg.firstOrgCd.getValue());
            ssmOverlapParam.put("orgCd", orgCd);
            ssmOverlapParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
            ssmOverlapParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
            ssmOverlapParam.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
            ssmOverlapParam.put("trtyRuleEffFromDt", trtyRuleEffFromDt);
            if (ZYPCommonFunc.hasValue(trtyRuleEffThruDt)) {
                ssmOverlapParam.put("trtyRuleEffThruDt", trtyRuleEffThruDt);
            }
            // ssmOverlapParam.put("slsDt", slsDt);
            // QC#11638
            ssmOverlapParam.put("trtyRuleLogicTp_OR", TRTY_RULE_LOGIC_TP.OR);

            List<Map<String, String>> overlapList = this.ssmClient.queryObjectList("getTrtyRuleForRangeOverlapCheck", ssmOverlapParam);

            // Postal Code Range Overlap check
            List<String[]> patternList = makePatternList(trtyRuleOprdTpCd, trtyRuleFromValTxt, trtyRuleThruValTxt);
            String orgNm = "";
            for (Map<String, String> overlapMap : overlapList) {
                String overlapTrtyRuleOprdTpCd = overlapMap.get("TRTY_RULE_OPRD_TP_CD");
                String overlapTrtyRuleFromValTx = overlapMap.get("TRTY_RULE_FROM_VAL_TXT");
                String overlapTrtyRuleThruValTx = overlapMap.get("TRTY_RULE_THRU_VAL_TXT");
                
                // 2016/03/13 CSA-QC#5364 Del Start
//                String overlapTrtyRuleLogicTpCd = overlapMap.get("TRTY_RULE_LOGIC_TP_CD");
//                String overlapOrgCd = overlapMap.get("ORG_CD");

//                if (!overlapOrgCd.equals(prevOverlapOrgCd)) {
//                    existAndTrtyRuleLogicTpCdFlg = false;
//                }

//                if (TRTY_RULE_LOGIC_TP.AND.equals(overlapTrtyRuleLogicTpCd) || TRTY_RULE_LOGIC_TP.AND.equals(prevOverlapTrtyRuleLogicTpCd)) {
//                    existAndTrtyRuleLogicTpCdFlg = true;
//                    prevOverlapTrtyRuleLogicTpCd = overlapTrtyRuleLogicTpCd;
//                    continue;
//                } else if (existAndTrtyRuleLogicTpCdFlg) {
//                    prevOverlapTrtyRuleLogicTpCd = overlapTrtyRuleLogicTpCd;
//                    continue;
//                }
                // 2016/03/13 CSA-QC#5364 Del End
                
                // QC#11638
                if(isDuplicateDate(trtyRuleEffFromDt, trtyRuleEffThruDt, overlapMap.get("EFF_FROM_DT"), overlapMap.get("EFF_FROM_DT"))){
                    continue;
                }

                List<String[]> existsList = makePatternList(overlapTrtyRuleOprdTpCd, overlapTrtyRuleFromValTx, overlapTrtyRuleThruValTx);

                // 2016/03/13 CSA-QC#5364 Mod Start
                if (!checkPatternList(trtyRuleLogicTpCd, existsList, patternList)) {
                    orgNm = overlapMap.get("ORG_NM");
                    existOverlapFlg = true;
                }
//                if (TRTY_RULE_OPRD_TP.NOT_EQUAL.equals(overlapTrtyRuleOprdTpCd)
//                        && trtyRuleFromValTxt.equals(overlapTrtyRuleFromValTx)) {
//                   existOverlapFlg = false;
//                }
                // prevOverlapOrgCd = overlapOrgCd;
                // 2016/03/13 CSA-QC#5364 Mod End
            }
            if (existOverlapFlg) {
                this.msgMap.addXxMsgIdWithPrm(NMZM0087E, new String[] {orgNm });
                // QC#11728
                trtyRuleListPMsg.xxMsgId_R.setValue(NMZM0087E);
                trtyRuleListPMsg.xxMsgPrmTxt_R1.setValue(orgNm);
                return false;
            }
            // 2016/03/13 CSA-QC#5364 Del Start
            // prevTrtyRuleLogicTpCd = trtyRuleLogicTpCd;
            // 2016/03/13 CSA-QC#5364 Del End
        }
        return true;
    }

    /**
     * make normalized pattern. <br>
     * -There are fromValue and toValue <br>
     * -both fromValue and toValue is [0-9]{5}-[0-9]{4} <br>
     * -logic NOT_EQUAL <br>
     * makes 2 patterns, the others make a pattern.
     * @param trtyRuleBean NWZC270001_TrtyRuleBean
     * @param trtyRuleFromValTxt String
     * @return boolean
     */
    private static boolean setPatternForTrtyRuleFromVal(NWZC270001_TrtyRuleBean trtyRuleBean, String trtyRuleFromValTxt) {
        String prevRuleFromFirstHalf = trtyRuleBean.getTrtyRuleFromValTxt().substring(0, 5);
        String prevRuleFromEndHalf = trtyRuleBean.getTrtyRuleFromValTxt().substring(trtyRuleBean.getTrtyRuleFromValTxt().length() - 5);
        String prevRuleThruFirstHalf = trtyRuleBean.getTrtyRuleThruValTxt().substring(0, 5);
        String prevRuleThruEndHalf = trtyRuleBean.getTrtyRuleThruValTxt().substring(trtyRuleBean.getTrtyRuleThruValTxt().length() - 5);

        String ruleFromFirstHalf = trtyRuleFromValTxt.substring(0, 5);
        String ruleFromEndHalf = trtyRuleFromValTxt.substring(trtyRuleFromValTxt.length() - 5);

        if (prevRuleFromFirstHalf.compareTo(ruleFromFirstHalf) < 0) {
            if (prevRuleThruFirstHalf.compareTo(ruleFromFirstHalf) > 0) {
                trtyRuleBean.setTrtyRuleFromValTxt(trtyRuleFromValTxt);

            } else if (prevRuleThruFirstHalf.compareTo(ruleFromFirstHalf) == 0) {
                if (prevRuleThruEndHalf.compareTo(ruleFromEndHalf) > 0) {
                    trtyRuleBean.setTrtyRuleFromValTxt(trtyRuleFromValTxt);

                // no area
                } else if (prevRuleThruEndHalf.compareTo(ruleFromEndHalf) < 0) {
                    return false;
                }

            // no area
            } else {
                return false;
            }

        } else if (prevRuleFromFirstHalf.compareTo(ruleFromFirstHalf) == 0) {
            if (prevRuleFromEndHalf.compareTo(ruleFromEndHalf) < 0) {
                trtyRuleBean.setTrtyRuleFromValTxt(trtyRuleFromValTxt);
            }
        }

        return true;
    }

    /**
     * make normalized pattern. <br>
     * -There are fromValue and toValue <br>
     * -both fromValue and toValue is [0-9]{5}-[0-9]{4} <br>
     * -logic NOT_EQUAL <br>
     * makes 2 patterns, the others make a pattern.
     * @param trtyRuleBean NWZC270001_TrtyRuleBean
     * @param trtyRuleThruValTxt String
     * @return boolean
     */
    private static boolean setPatternForTrtyRuleThruVal(NWZC270001_TrtyRuleBean trtyRuleBean, String trtyRuleThruValTxt) {
        String prevRuleFromFirstHalf = trtyRuleBean.getTrtyRuleFromValTxt().substring(0, 5);
        String prevRuleFromEndHalf = trtyRuleBean.getTrtyRuleFromValTxt().substring(trtyRuleBean.getTrtyRuleFromValTxt().length() - 5);
        String prevRuleThruFirstHalf = trtyRuleBean.getTrtyRuleThruValTxt().substring(0, 5);
        String prevRuleThruEndHalf = trtyRuleBean.getTrtyRuleThruValTxt().substring(trtyRuleBean.getTrtyRuleThruValTxt().length() - 5);

        String ruleThruFirstHalf = trtyRuleThruValTxt.substring(0, 5);
        String ruleThruEndHalf = trtyRuleThruValTxt.substring(trtyRuleThruValTxt.length() - 5);

        if (prevRuleThruFirstHalf.compareTo(ruleThruFirstHalf) > 0) {
            if (prevRuleFromFirstHalf.compareTo(ruleThruFirstHalf) < 0) {
                trtyRuleBean.setTrtyRuleThruValTxt(trtyRuleThruValTxt);

            } else if (prevRuleFromFirstHalf.compareTo(ruleThruFirstHalf) == 0) {
                if (prevRuleFromEndHalf.compareTo(ruleThruEndHalf) <= 0) {
                    trtyRuleBean.setTrtyRuleThruValTxt(trtyRuleThruValTxt);

                // no area
                } else {
                    return false;
                }

            // no area
            } else {
                return false;
            }

        } else if (prevRuleThruFirstHalf.compareTo(ruleThruFirstHalf) == 0) {
            if (prevRuleThruEndHalf.compareTo(ruleThruEndHalf) > 0) {
                trtyRuleBean.setTrtyRuleThruValTxt(trtyRuleThruValTxt);
            }
        }
        return true;
    }

    /**
     * make normalized pattern. <br>
     * -There are fromValue and toValue <br>
     * -both fromValue and toValue is [0-9]{5}-[0-9]{4} <br>
     * -logic NOT_EQUAL <br>
     * makes 2 patterns, the others make a pattern.
     * @param trtyRuleOprdTpCd
     * @param trtyRuleFromValTxt
     * @param trtyRuleThruValTxt
     * @return List<String[2]> patternList
     */
    private static List<String[]> makePatternList(String trtyRuleOprdTpCd, String trtyRuleFromValTxt, String trtyRuleThruValTxt) {
        List<String[]> pattern = new ArrayList<String[]>();

        if (TRTY_RULE_OPRD_TP.EQUAL.equals(trtyRuleOprdTpCd)) {
            if (trtyRuleFromValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
                pattern.add(new String[] {trtyRuleFromValTxt + POSTAL_CODE_MIN_SECOND_HALF, trtyRuleFromValTxt + POSTAL_CODE_MAX_SECOND_HALF });
            } else if (trtyRuleFromValTxt.length() == POSTAL_CODE_LENGTH) {
                pattern.add(new String[] {trtyRuleFromValTxt, trtyRuleFromValTxt });
            }
        } else if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCd)) {
            String from = null;
            String to = null;
            if (trtyRuleFromValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
                from = trtyRuleFromValTxt + POSTAL_CODE_MIN_SECOND_HALF;
            } else if (trtyRuleFromValTxt.length() == POSTAL_CODE_LENGTH) {
                from = trtyRuleFromValTxt;
            }
            if (trtyRuleThruValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
                to = trtyRuleThruValTxt + POSTAL_CODE_MAX_SECOND_HALF;
            } else if (trtyRuleThruValTxt.length() == POSTAL_CODE_LENGTH) {
                to = trtyRuleThruValTxt;
            }
            pattern.add(new String[] {from, to });
            // Del Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCd)) {
        //    int index = trtyRuleFromValTxt.indexOf("%");
        //    if (index == -1) {
        //        return makePatternList(TRTY_RULE_OPRD_TP.EQUAL, trtyRuleFromValTxt, "");
        //    }
        //    String from = trtyRuleFromValTxt.substring(0, index) + POSTAL_CODE_MIN.substring(index);
        //    String to = trtyRuleFromValTxt.substring(0, index) + POSTAL_CODE_MAX.substring(index);
        //    pattern.add(new String[] {from, to });
            // Del End 2018/06/01 QC#24293
          // 2016/03/13 CSA-QC#5364 Del Start
//        } else if (TRTY_RULE_OPRD_TP.NOT_EQUAL.equals(trtyRuleOprdTpCd)) {
//            if (trtyRuleFromValTxt.length() == POSTAL_CODE_FIRST_HALF_LENGTH) {
//                if (!POSTAL_CODE_MIN_FIRST_HALF.equals(trtyRuleFromValTxt)) {
//                    String notEqualTo = String.format("%05d", Integer.valueOf(trtyRuleFromValTxt) - 1) + POSTAL_CODE_MAX_SECOND_HALF;
//                    pattern.add(new String[] {POSTAL_CODE_MIN, notEqualTo });
//                }
//                if (!POSTAL_CODE_MAX_FIRST_HALF.equals(trtyRuleFromValTxt)) {
//                    String notEqualFrom = String.format("%05d", Integer.valueOf(trtyRuleFromValTxt) + 1) + POSTAL_CODE_MIN_SECOND_HALF;
//                    pattern.add(new String[] {notEqualFrom, POSTAL_CODE_MAX });
//                }
//            } else if (trtyRuleFromValTxt.length() == POSTAL_CODE_LENGTH) {
//                int num = Integer.valueOf(trtyRuleFromValTxt.substring(0, POSTAL_CODE_FIRST_HALF_LENGTH) + trtyRuleFromValTxt.substring(POSTAL_CODE_FIRST_HALF_LENGTH + 1));
//
//                if (!POSTAL_CODE_MIN.equals(trtyRuleFromValTxt)) {
//                    String notEqualTo = String.format("%09d", num - 1);
//                    pattern.add(new String[] {POSTAL_CODE_MIN, notEqualTo.substring(0, POSTAL_CODE_FIRST_HALF_LENGTH) + "-" + notEqualTo.substring(POSTAL_CODE_FIRST_HALF_LENGTH) });
//                }
//                if (!POSTAL_CODE_MAX.equals(trtyRuleFromValTxt)) {
//                    String notEqualFrom = String.format("%09d", num + 1);
//                    pattern.add(new String[] {notEqualFrom.substring(0, POSTAL_CODE_FIRST_HALF_LENGTH) + "-" + notEqualFrom.substring(POSTAL_CODE_FIRST_HALF_LENGTH), POSTAL_CODE_MAX });
//                }
//            }
          // 2016/03/13 CSA-QC#5364 Del End
        }
        return pattern;
    }

    /**
     * overlap check
     * @param trtyRuleLogicTpCd
     * @param prevPatternList
     * @param patternList
     * @return
     */
    private static boolean checkPatternList(String trtyRuleLogicTpCd, List<String[]> prevPatternList, List<String[]> patternList) {
        for (String[] prevPattern : prevPatternList) {
            for (String[] pattern : patternList) {
                if (!checkPattern(trtyRuleLogicTpCd, prevPattern, pattern)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * overlap check for each pattern
     * @param trtyRuleLogicTpCd
     * @param prevPattern
     * @param pattern
     * @return
     */
    private static boolean checkPattern(String trtyRuleLogicTpCd, String[] prevPattern, String[] pattern) {

        // logic AND must be overlap
//        if (TRTY_RULE_LOGIC_TP.AND.equals(trtyRuleLogicTpCd)) {
//            return false;
//        }

        int prevFromToComp = prevPattern[0].compareTo(pattern[1]);
        int fromPrevToComp = pattern[0].compareTo(prevPattern[1]);

        // compareTo == 0 must be overlap
        if (prevFromToComp == 0 || fromPrevToComp == 0) {
            return false;
        }

        // ex) prevFromToComp:{10000,40000}
        // fromPrevToComp:{20000,30000}
        // prevFromToComp or fromPrevToComp must be less than 0
        // If both prevFromToComp and fromPrevToComp are less than 0,
        // there must be overlap.
        if (prevFromToComp < 0 && fromPrevToComp < 0) {
            return false;
        }

        return true;
    }

    /**
     * overlap check for each pattern
     * @param trtyRuleBean NWZC270001_TrtyRuleBean
     * @param compareTrtyRuleBean NWZC270001_TrtyRuleBean
     * @return boolean
     */
    private static boolean checkPattern(NWZC270001_TrtyRuleBean trtyRuleBean, NWZC270001_TrtyRuleBean compareTrtyRuleBean) {
        int prevFromToComp = trtyRuleBean.getTrtyRuleFromValTxt().compareTo(compareTrtyRuleBean.getTrtyRuleThruValTxt());
        int fromPrevToComp = compareTrtyRuleBean.getTrtyRuleFromValTxt().compareTo(trtyRuleBean.getTrtyRuleThruValTxt());

        // compareTo == 0 must be overlap
        if (prevFromToComp == 0 || fromPrevToComp == 0) {
            return false;
        }

        if (prevFromToComp < 0 && fromPrevToComp < 0) {
            return false;
        }

        return true;
    }
    /**
     * check format(need full match)
     * @param format
     * @param value
     * @return true:exactly match
     */
    private static boolean validateFormat(String format, String value) {

        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    /**
     * check effective date is active
     * @param effFromDt String
     * @param effThruDt String
     * @param slsDt String
     * @return true:active
     */
    private boolean checkActiveDate(String effFromDt, String effThruDt, String slsDt) {

        if (effFromDt.compareTo(slsDt) <= 0) {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return true;
            } else if (effThruDt.compareTo(slsDt) >= 0) {
                return true;
            }
        }

        return false;
    }


    /**
     * copy active rule to Bean
     * @param pMsg NMZC270001PMsg
     */
    private void copyActiveRuleToBean(NMZC270001PMsg pMsg) {

        for (int i = 0; i < pMsg.trtyRuleList.getValidCount(); i++) {
            // QC#11638
            // if (checkActiveDate(pMsg.trtyRuleList.no(i).effFromDt_R.getValue(), pMsg.trtyRuleList.no(i).effThruDt_R.getValue(), ZYPDateUtil.getSalesDate())) {
            if (isCurrentOrFutureDate(pMsg.trtyRuleList.no(i).effFromDt_R.getValue(), pMsg.trtyRuleList.no(i).effThruDt_R.getValue(), ZYPDateUtil.getSalesDate())) {

                NWZC270001_TrtyRuleBean trtyRuleBean = new NWZC270001_TrtyRuleBean(pMsg.trtyRuleList.no(i));
                trtyRuleBeanList.add(trtyRuleBean);
                // QC#11728
                trtyRuleBean.setXxRowNum(i);
            }
        }
    }

    /**
     * get territory ID which territory rule is duplicated
     * @param pMsg NMZC270001PMsg
     * @return List<Map<String, Object>>
     */
    private Map<String, Object> getDuplicateTerritoryId(NMZC270001PMsg pMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",   pMsg.glblCmpyCd.getValue());
        ssmParam.put("logicTpCd", trtyRuleBeanList.get(0).getTrtyRuleLogicTpCd());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffDt", DB_PARAM_MAX_EFF_DT);
        ssmParam.put("orgCd", pMsg.orgCd.getValue());
        ssmParam.put("orgStruTpCd", pMsg.orgStruTpCd.getValue());
        ssmParam.put("trtyGrpTpCd", pMsg.trtyGrpTpCd.getValue());
        ssmParam.put("trtyTpCd", pMsg.trtyTpCd.getValue());
        ssmParam.put("firstOrgCd", pMsg.firstOrgCd.getValue());
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFuture", GNRN_TP.FUTURE);

        ssmParam.put("trtyRuleBeanList", this.trtyRuleBeanList);

        return ssmParam;
    }

    /**
     * get territory ID which territory rule is duplicated
     * @param pMsg NMZC270001PMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getOverlapTrtyRule(NMZC270001PMsg pMsg, NWZC270001_TrtyRuleBean trtyRuleBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",   pMsg.glblCmpyCd.getValue());
        ssmParam.put("orgCd", pMsg.orgCd.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("orgStruTpCd", pMsg.orgStruTpCd.getValue());
        ssmParam.put("trtyGrpTpCd", pMsg.trtyGrpTpCd.getValue());
        ssmParam.put("trtyTpCd", pMsg.trtyTpCd.getValue());
        ssmParam.put("firstOrgCd", pMsg.firstOrgCd.getValue());
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
        ssmParam.put("trtyRuleTp_postCd", TRTY_RULE_TP.POSTAL_CODE);
        ssmParam.put("trtyRuleOprdTp_Equal", TRTY_RULE_OPRD_TP.EQUAL);
        // Del Start 2018/06/01 QC#24293
        //ssmParam.put("trtyRuleOprdTp_Like", TRTY_RULE_OPRD_TP.LIKE);
        // Del End 2018/06/01 QC#24293
        ssmParam.put("trtyRuleOprdTp_Between", TRTY_RULE_OPRD_TP.BETWEEN);
        ssmParam.put("trtyRuleFromVal_FirstFiveDigits", trtyRuleBean.getTrtyRuleFromValTxt().substring(0, 5));
        ssmParam.put("trtyRuleThruVal_FirstFiveDigits", trtyRuleBean.getTrtyRuleThruValTxt().substring(0, 5));
        ssmParam.put("trtyRuleFromVal_FullDigits", trtyRuleBean.getTrtyRuleFromValTxt());
        ssmParam.put("trtyRuleThruVal_FullDigits", trtyRuleBean.getTrtyRuleThruValTxt());
        ssmParam.put("likeTrtyRuleFromVal", trtyRuleBean.getTrtyRuleFromValTxt().substring(0, 1) + "%");
        ssmParam.put("likeTrtyRuleThruVal", trtyRuleBean.getTrtyRuleThruValTxt().substring(0, 1) + "%");
        ssmParam.put("maxEffThruDt", DB_PARAM_MAX_EFF_DT);
        ssmParam.put("maxTrtyRuleThru", DB_PARAM_MAX_TRTY_RULE_THRU);

        List<Map<String, Object>> overlapTrtyRuleList =  this.ssmClient.queryObjectList("getOverlapTrtyRule", ssmParam);
        return overlapTrtyRuleList;
    }

    /**
     * get territory ID which territory rule is duplicated
     * @param pMsg NMZC270001PMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getTrtyRule(NMZC270001PMsg pMsg, String overlapTrtyId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",   pMsg.glblCmpyCd.getValue());
        ssmParam.put("orgCd", overlapTrtyId);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("trtyRuleTp_postCd", TRTY_RULE_TP.POSTAL_CODE);
        ssmParam.put("maxEffThruDt", DB_PARAM_MAX_EFF_DT);

        List<Map<String, Object>> overlapTrtyRuleList =  this.ssmClient.queryObjectList("getTrtyRule", ssmParam);
        return overlapTrtyRuleList;
    }
    
    // QC#11638
    private boolean isCurrentOrFutureDate(String effFromDt, String effThruDt, String slsDt) {
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            return true;
        } else if (effThruDt.compareTo(slsDt) >= 0) {
            return true;
        }
        return false;
    }
}
