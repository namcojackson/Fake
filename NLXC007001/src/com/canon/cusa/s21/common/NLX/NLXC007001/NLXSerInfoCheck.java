/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC007001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC015001.NLXC015001;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_ERR_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.SER_EVENT_STSTMsg;
import business.db.SER_EVENT_STSTMsgArray;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         M.Irisawa       Create          N/A
 *</pre>
 */
public class NLXSerInfoCheck {
    /**
     */
    public static final String MDSE = "mdse";

    /**
     */
    public static final String SER_NUM = "serNum";

    /**
     */
    public static final String OLD_SER_NUM = "oldSerNum";

    /**
     */
    public static final String SYS_ID = "sysId";

    /**
     */
    public static final String MAIL_ADD = "mailAddress";

    /**
     */
    public static final String ERR_MSG = "errMsg";
    
    // 10/06/2010 D.Fukaya apppend start
    public static final String WH_CD = "whCd";
    // 10/06/2010 D.Fukaya apppend end

    // private -------------------------------------
    /**
     */
    private static final String NLBM1019E = "NLBM1019E";

    /**
     */
    private static final String NLBM1040W = "NLBM1040W";

    /**
     * statementId:getSerEventRuleList
     */
    private static final String GET_SER_EVENT_RULE_LIST = "getSerEventRuleList";

    /**
     * SER_EVENT_STS
     */
    private static final String SER_EVENT_STS = "SER_EVENT_STS";

    /**
     * SER_EVENT_STS_PK
     */
    private static final String SER_EVENT_STS_PK = "SER_EVENT_STS_PK";

    /**
     * SER_OWNR_ID
     */
    private static final String SER_OWNR_ID = "SER_OWNR_ID";

    /**
     * SER_ERR_STS_CD_DB
     */
    private static final String SER_ERR_STS_CD_DB = "SER_ERR_STS_CD";

    /**
     * updateByPartialValue
     */
    private static final String UPDATE_BY_PARTIAL_VALUE = "updateByPartialValue";

    /**
     * updateSelectionField
     */
    private static final String UPDATE_SELECTION_FIELD = "updateSelectionField";

    /**
     * glblCmpyCd
     */
    private static final String GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * mdseCd
     */
    private static final String MDSE_CD = "mdseCd";

    /**
     * serNum
     */
    private static final String SER_NUM_CON = "serNum";

    /**
     * serErrStsCd
     */
    private static final String SER_ERR_STS_CD = "serErrStsCd";

    // member ----------------------------------------------------
    /**
     */
    private S21SsmLowLevelCodingClient ssmLowLev = null;

    /**
     */
    public NLXSerInfoCheck() {
        this.ssmLowLev = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param serList serList
     * @param errList errList
     * @throws SQLException SQLException
     */
    public void checkSerInfo(String glblCmpyCd, List<Map<String, String>> serList, List<Map<String, String>> errList) throws SQLException {
        NLXC015001.sortListMap(serList, new String[] {MDSE, SER_NUM }, new String[] {NLXC015001.SORT_ASC, NLXC015001.SORT_ASC });

        String mdseTmpBak = "";
        String serNumTmpBak = "";

        // 1
        for (Map<String, String> serMap : serList) {
            // 1.1
            String mdseTmp = serMap.get(MDSE);
            String serNumTmp = serMap.get(SER_NUM);

            PreparedStatement pstmt = null;
            ResultSet serEventRuleRs = null;
            if (!(mdseTmpBak.equals(mdseTmp)) || !(serNumTmpBak.equals(serNumTmp))) {
                // 1.2.1
                try {
                    SER_EVENT_STSTMsg serEventStsTMsg01 = new SER_EVENT_STSTMsg();
                    SER_EVENT_STSTMsg updSerEventStsTMsg01 = new SER_EVENT_STSTMsg();
                    serEventStsTMsg01.setConditionValue("glblCmpyCd01", NLXC014001.nullToEmpty(glblCmpyCd));
                    serEventStsTMsg01.setConditionValue("mdseCd01", NLXC014001.nullToEmpty(mdseTmp));
                    serEventStsTMsg01.setConditionValue("serNum01", NLXC014001.nullToEmpty(serNumTmp));
                    serEventStsTMsg01.setConditionValue("serErrStsCd01", SER_ERR_STS.EVT);
                    serEventStsTMsg01.setSQLID("004");
                    SER_EVENT_STSTMsgArray serEventStsTMsgArray01 = (SER_EVENT_STSTMsgArray) EZDTBLAccessor.findByCondition(serEventStsTMsg01);
                    if (serEventStsTMsgArray01.length() > 0) {
                        String[] conItem = new String[] {GLBL_CMPY_CD, MDSE_CD, SER_NUM_CON, SER_ERR_STS_CD };
                        String[] updItem = new String[] {SER_ERR_STS_CD };
                        serEventStsTMsg01.glblCmpyCd.setValue(NLXC014001.nullToEmpty(glblCmpyCd));
                        serEventStsTMsg01.mdseCd.setValue(NLXC014001.nullToEmpty(mdseTmp));
                        serEventStsTMsg01.serNum.setValue(NLXC014001.nullToEmpty(serNumTmp));
                        serEventStsTMsg01.serErrStsCd.setValue(SER_ERR_STS.EVT);
                        updSerEventStsTMsg01.serErrStsCd.setValue(SER_ERR_STS.SPACE);
                        S21ApiTBLAccessor.updateByPartialValue(serEventStsTMsg01, conItem, updSerEventStsTMsg01, updItem);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSerEventStsTMsg01.getReturnCode())) {
                            throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, UPDATE_BY_PARTIAL_VALUE, updSerEventStsTMsg01.getReturnCode() });
                        }
                    }
                    // 1.3.1
                    Map<String, String> serEventStsParamMap02 = new HashMap<String, String>();
                    serEventStsParamMap02.put("glblCmpyCd", glblCmpyCd);
                    serEventStsParamMap02.put("mdseCd", mdseTmp);
                    serEventStsParamMap02.put("serNum", serNumTmp);
                    pstmt = ssmLowLev.createPreparedStatement(GET_SER_EVENT_RULE_LIST, serEventStsParamMap02);
                    serEventRuleRs = pstmt.executeQuery();
                    while (serEventRuleRs.next()) {
                        String[] msgList = new String[] {serEventRuleRs.getString(SER_EVENT_STS_PK), mdseTmp, serNumTmp };
                        S21InfoLogOutput.println(NLBM1040W, msgList);
                        Map<String, String> errMap = new HashMap<String, String>();
                        errMap.put(SYS_ID, serEventRuleRs.getString(SER_OWNR_ID));
                        errMap.put(ERR_MSG, S21MessageFunc.clspGetMessage(NLBM1040W, msgList));
                        errList.add(errMap);
                        if (SER_ERR_STS.SPACE.equals(serEventRuleRs.getString(SER_ERR_STS_CD_DB))) {
                            SER_EVENT_STSTMsg updSerEventStsTMsg02 = new SER_EVENT_STSTMsg();
                            updSerEventStsTMsg02.glblCmpyCd.setValue(NLXC014001.nullToEmpty(glblCmpyCd));
                            updSerEventStsTMsg02.serEventStsPk.setValue(serEventRuleRs.getBigDecimal(SER_EVENT_STS_PK));
                            updSerEventStsTMsg02.serErrStsCd.setValue(SER_ERR_STS.EVT);
                            EZDTBLAccessor.updateSelectionField(updSerEventStsTMsg02, new String[] {SER_ERR_STS_CD });
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSerEventStsTMsg02.getReturnCode())) {
                                throw new S21AbendException(NLBM1019E, new String[] {SER_EVENT_STS, UPDATE_SELECTION_FIELD, updSerEventStsTMsg02.getReturnCode() });
                            }
                        }
                    }
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(pstmt, serEventRuleRs);
                }
            }
            mdseTmpBak = mdseTmp;
            serNumTmpBak = serNumTmp;
        }
        // 2
        // 2.1
        NLXC015001.sortListMap(errList, new String[] {SYS_ID }, new String[] {NLXC015001.SORT_ASC });
    }
}
