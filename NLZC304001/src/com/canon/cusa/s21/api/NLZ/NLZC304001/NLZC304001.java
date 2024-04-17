/*
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC304001;

import java.util.List;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;

import business.db.RWS_SERTMsg;
import business.parts.NLZC304001PMsg;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;

/**
 * <pre>
 *
 * Date        Company     Name         Create/Update    Defect No
 * ----------------------------------------------------------------------
 * 01/09/2013  Hitachi     T.Kanasaka   Create           WDS#16
 * 05/22/2013  Hitachi     T.Kanasaka   Update           QC1222
 * 05/23/2013  Hitachi     T.Kanasaka   Update           QC1222
 * 06/14/2013  Hitachi     T.Kanasaka   Update           QC1222(V2)
 *</pre>
 */
public class NLZC304001 extends S21ApiCommonBase {

    /**
     * A duplicate error occurred while registering the Table. TABLE
     * ID : [@], Field Name: [@], Key Information: [@]
     */
    public static final String NLAM1133E = "NLAM1133E";

    /**
     * Merchandise Code is not entered.
     */
    public static final String NLZM0005E = "NLZM0005E";

    /**
     * "Global Company Code" must be entered.
     */
    public static final String NLZM2052E = "NLZM2052E";

    /**
     * RWS Number is not set.
     */
    public static final String NLZM2133E = "NLZM2133E";

    /**
     * SerialList is not set.
     */
    public static final String NLZM2134E = "NLZM2134E";

    /**
     * RWS Detail Line Number is not set.
     */
    public static final String NLZM2135E = "NLZM2135E";

    /**
     * Serial Number is not set.
     */
    public static final String NLZM2136E = "NLZM2136E";

    /**
     * PutAway Check Status Code must be either 0 or 9.
     */
    public static final String NLZM2137E = "NLZM2137E";

    // START 2013/06/14 T.Kanasaka [QC1222(V2) ADD]
    /**
     * Serial Number Send Flag must be either Y or N.
     */
    public static final String NLZM2166E = "NLZM2166E";
    // END 2013/06/14 T.Kanasaka [QC1222(V2) ADD]

    /** table:RWS_SER */
    private static final String RWS_SER = "RWS_SER";

    /** column:GLBL_CMPY_CD */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** column:RWS_NUM */
    private static final String RWS_NUM = "RWS_NUM";

    /** column:RWS_LINE_NUM */
    private static final String RWS_LINE_NUM = "RWS_LINE_NUM";

    /** column:SER_NUM */
    private static final String SER_NUM = "SER_NUM";

    /** column:MDSE_CD */
    private static final String MDSE_CD = "MDSE_CD";

    // START 2013/05/23 T.Kanasaka [QC1222 DEL]
//    /** PUT_AWAY_CHK_STS_CD domain:0 */
//    private static final String PUT_AWAY_CHK_STS_CD_0 = "0";

//    /** PUT_AWAY_CHK_STS_CD domain:9 */
//    private static final String PUT_AWAY_CHK_STS_CD_9 = "9";
    // END 2013/05/23 T.Kanasaka [QC1222 DEL]

    /**
     * constructor
     */
    public NLZC304001() {
        super();
    }

    /**
     * execute API(List)
     * @param params List<NLZC304001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC304001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NLZC304001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * execute API
     * @param param NLZC304001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC304001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (!checkParam(msgMap, onBatchType)) {
            // START 2013/05/22 T.Kanasaka [QC1222 ADD]
            msgMap.flush();
            // END 2013/05/22 T.Kanasaka [QC1222 ADD]
            return;
        }

        insertRwsSer(msgMap, onBatchType);

        msgMap.flush();
    }

    /**
     * check parameter
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return check result true:OK, false:NG
     */
    protected boolean checkParam(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        boolean returnValue = true;

        NLZC304001PMsg param = (NLZC304001PMsg) msgMap.getPmsg();

        // check glblCmpyCd
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            S21InfoLogOutput.println(NLZM2052E);
            msgMap.addXxMsgId(NLZM2052E);
            returnValue = false;
        }

        // check rwsNum
        if (!ZYPCommonFunc.hasValue(param.rwsNum)) {
            S21InfoLogOutput.println(NLZM2133E);
            msgMap.addXxMsgId(NLZM2133E);
            returnValue = false;
        }

        // check SerialList
        if (param.SerialList == null || param.SerialList.getValidCount() == 0) {
            S21InfoLogOutput.println(NLZM2134E);
            msgMap.addXxMsgId(NLZM2134E);
            return false;
        }

        for (int i = 0; i < param.SerialList.getValidCount(); i++) {

            // check rwsDtlLineNum
            if (!ZYPCommonFunc.hasValue(param.SerialList.no(i).rwsDtlLineNum)) {
                S21InfoLogOutput.println(NLZM2135E);
                msgMap.addXxMsgId(NLZM2135E);
                returnValue = false;
            }

            // check serNum
            if (!ZYPCommonFunc.hasValue(param.SerialList.no(i).serNum)) {
                S21InfoLogOutput.println(NLZM2136E);
                msgMap.addXxMsgId(NLZM2136E);
                returnValue = false;
            }

            // check mdseCd
            if (!ZYPCommonFunc.hasValue(param.SerialList.no(i).mdseCd)) {
                S21InfoLogOutput.println(NLZM0005E);
                msgMap.addXxMsgId(NLZM0005E);
                returnValue = false;
            }

            // check putAwayChkStsCd
            // START 2013/05/23 T.Kanasaka [QC1222 MOD]
//            if (!PUT_AWAY_CHK_STS_CD_0.equals(param.SerialList.no(i).putAwayChkStsCd.getValue()) && !PUT_AWAY_CHK_STS_CD_9.equals(param.SerialList.no(i).putAwayChkStsCd.getValue())) {
            if (!PUT_AWAY_CHK_STS.NOT_PROCESSED.equals(param.SerialList.no(i).putAwayChkStsCd.getValue()) && !PUT_AWAY_CHK_STS.NO_NEED.equals(param.SerialList.no(i).putAwayChkStsCd.getValue())) {
            // END 2013/05/23 T.Kanasaka [QC1222 MOD]
                S21InfoLogOutput.println(NLZM2137E);
                msgMap.addXxMsgId(NLZM2137E);
                returnValue = false;
            }

            // START 2013/06/14 T.Kanasaka [QC1222(V2) ADD]
            // check serNumSendFlg
            if (!ZYPConstant.FLG_OFF_N.equals(param.SerialList.no(i).serNumSendFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(param.SerialList.no(i).serNumSendFlg.getValue())) {
                S21InfoLogOutput.println(NLZM2166E);
                msgMap.addXxMsgId(NLZM2166E);
                returnValue = false;
            }
            // END 2013/06/14 T.Kanasaka [QC1222(V2) ADD]
        }

        return returnValue;
    }

    /**
     * insert RWS_SER
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    protected void insertRwsSer(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC304001PMsg param = (NLZC304001PMsg) msgMap.getPmsg();

        RWS_SERTMsg rwsSerT;
        for (int i = 0; i < param.SerialList.getValidCount(); i++) {
            rwsSerT = new RWS_SERTMsg();

            ZYPEZDItemValueSetter.setValue(rwsSerT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSerT.rwsNum, param.rwsNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSerT.rwsLineNum, param.SerialList.no(i).rwsDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSerT.serNum, param.SerialList.no(i).serNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSerT.mdseCd, param.SerialList.no(i).mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSerT.putAwayChkStsCd, param.SerialList.no(i).putAwayChkStsCd.getValue());
            // START 2013/06/14 T.Kanasaka [QC1222(V2) ADD]
            ZYPEZDItemValueSetter.setValue(rwsSerT.serNumSendFlg, param.SerialList.no(i).serNumSendFlg.getValue());
            // END 2013/06/14 T.Kanasaka [QC1222(V2) ADD]

            // insert RWS_SER
            S21ApiTBLAccessor.insert(rwsSerT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSerT.getReturnCode())) {
                throw new S21AbendException(NLAM1133E, new String[] {RWS_SER, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RWS_NUM, RWS_LINE_NUM, SER_NUM, MDSE_CD),
                        NLXCMsgHelper.toListedString(rwsSerT.glblCmpyCd, rwsSerT.rwsNum, rwsSerT.rwsLineNum, rwsSerT.serNum, rwsSerT.mdseCd) });
            }
        }
    }
}
