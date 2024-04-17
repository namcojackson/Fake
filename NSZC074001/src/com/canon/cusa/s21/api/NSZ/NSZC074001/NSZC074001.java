/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC074001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_BR_POST_XREFTMsg;
import business.db.SVC_BR_POST_XREFTMsgArray;
import business.parts.NSZC074001PMsg;
import business.parts.NSZC074001_mapListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * Service Branch Zip Code Mapping API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Hitachi         K.Kasai         Create
 * 05/17/2016   Hitachi         T.Iwamoto       Update          CSA QC#7643
 * 08/29/2016   Hitachi         K.Yamada        Update          CSA QC#13690
 * 10/03/2016   Hitachi         T.Tomita        Update          CSA QC#10568
 * </pre>
 */
public class NSZC074001 extends S21ApiCommonBase {

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** All parameters are Mandatory. */
    public static final String NSZM0647E = "NSZM0647E";

    /** Failed to insert a Service Branch Post Cross Reference record. */
    public static final String NSZM0648E = "NSZM0648E";

    /** Failed to update a Service Branch Post Cross Reference record. */
    public static final String NSZM0649E = "NSZM0649E";

    /** Failed to delete a Service Branch Post Cross Reference record. */
    public static final String NSZM0650E = "NSZM0650E";

    /** length:3 */
    public static final int LEN_3 = 3;

    /**
     * Constructor
     */
    public NSZC074001() {
        super();
    }

    /**
     * Service Branch Zip Code Mapping API
     * @param pMsg {@link NSZC074001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC074001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap)) {
            doProcess(msgMap, pMsg);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC074001PMsg pMsg = (NSZC074001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        for (int idx = 0; idx < pMsg.mapList.getValidCount(); idx++) {
            mandatoryCheck(msgMap, pMsg.mapList.no(idx).delFlg, NSZM0647E);
            mandatoryCheck(msgMap, pMsg.mapList.no(idx).postCd, NSZM0647E);
            mandatoryCheck(msgMap, pMsg.mapList.no(idx).svcLineBizCd, NSZM0647E);
            // mod start 2016/05/17 CSA Defect#7643
            mandatoryCheck(msgMap, pMsg.mapList.no(idx).svcBrCdDescTxt, NSZM0647E);
            // mod end 2016/05/17 CSA Defect#7643
            // START 2016/10/03 T.Tomita [QC#10568, DEL]
//            // add start 2016/08/29 CSA Defect#13690
//            mandatoryCheck(msgMap, pMsg.mapList.no(idx).svcTrtyDescTxt, NSZM0647E);
//            // add end 2016/08/29 CSA Defect#13690
            // END 2016/10/03 T.Tomita [QC#10568, DEL]
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private SVC_BR_POST_XREFTMsgArray getSvcBrPostXref(String glblCmpyCd, String postCd, String svcLineBizCd) {
        SVC_BR_POST_XREFTMsg paramTMsg = new SVC_BR_POST_XREFTMsg();
        paramTMsg.setSQLID("001");
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("postCd01", postCd);
        paramTMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);
        return (SVC_BR_POST_XREFTMsgArray) S21ApiTBLAccessor.findByCondition(paramTMsg);
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC074001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String delFlg;
        SVC_BR_POST_XREFTMsgArray svcBrPostXrefArray;
        NSZC074001_mapListPMsg mapPMsg;
        for (int idx = 0; idx < pMsg.mapList.getValidCount(); idx++) {
            mapPMsg = pMsg.mapList.no(idx);
            svcBrPostXrefArray = getSvcBrPostXref(glblCmpyCd, mapPMsg.postCd.getValue(), mapPMsg.svcLineBizCd.getValue());
            delFlg = mapPMsg.delFlg.getValue();
            if (ZYPConstant.FLG_OFF_N.equals(delFlg)) {
                if (svcBrPostXrefArray.getValidCount() == 0) {
                    // insert
                    executeInsert(msgMap, mapPMsg, glblCmpyCd);
                } else {
                    // update
                    executeUpdate(msgMap, mapPMsg, svcBrPostXrefArray.no(0));
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(delFlg)) {
                if (svcBrPostXrefArray.getValidCount() == 0) {
                    msgMap.addXxMsgId(NSZM0650E);
                    return;
                }
                // delete
                executeDelete(msgMap, svcBrPostXrefArray.no(0));
            }
        }
    }

    private void executeInsert(S21ApiMessageMap msgMap, NSZC074001_mapListPMsg mapPMsg, String glblCmpyCd) {
        SVC_BR_POST_XREFTMsg svcBrPostXref = new SVC_BR_POST_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcBrPostXrefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BR_POST_XREF_SQ));
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.postCd, mapPMsg.postCd);
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcLineBizCd, mapPMsg.svcLineBizCd);
        // mod start 2016/05/17 CSA Defect#7643
        if (mapPMsg.svcBrCdDescTxt.getValue().length() >= LEN_3) {
            ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcBrCd, S21StringUtil.subStringByLength(mapPMsg.svcBrCdDescTxt.getValue(), 0, LEN_3));
        }
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcBrCdDescTxt, mapPMsg.svcBrCdDescTxt);
        // mod end 2016/05/17 CSA Defect#7643
        // add start 2016/08/29 CSA Defect#13690
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcTrtyDescTxt, mapPMsg.svcTrtyDescTxt);
        // add end 2016/08/29 CSA Defect#13690
        S21ApiTBLAccessor.insert(svcBrPostXref);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcBrPostXref.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0648E);
        }
    }

    private void executeUpdate(S21ApiMessageMap msgMap, NSZC074001_mapListPMsg mapPMsg, SVC_BR_POST_XREFTMsg svcBrPostXref) {
        // mod start 2016/05/17 CSA Defect#7643
        if (mapPMsg.svcBrCdDescTxt.getValue().length() >= LEN_3) {
            ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcBrCd, S21StringUtil.subStringByLength(mapPMsg.svcBrCdDescTxt.getValue(), 0, LEN_3));
        }
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcBrCdDescTxt, mapPMsg.svcBrCdDescTxt);
        // mod end 2016/05/17 CSA Defect#7643
        // add start 2016/08/29 CSA Defect#13690
        ZYPEZDItemValueSetter.setValue(svcBrPostXref.svcTrtyDescTxt, mapPMsg.svcTrtyDescTxt);
        // add end 2016/08/29 CSA Defect#13690
        S21ApiTBLAccessor.update(svcBrPostXref);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcBrPostXref.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0649E);
        }
    }

    private void executeDelete(S21ApiMessageMap msgMap, SVC_BR_POST_XREFTMsg svcBrPostXref) {
        S21ApiTBLAccessor.logicalRemove(svcBrPostXref);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcBrPostXref.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0650E);
        }
    }
}
