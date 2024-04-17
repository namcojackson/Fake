/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC106001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.POTMsg;
import business.db.PO_NTC_SEND_PSNTMsg;
import business.db.S21_PSNTMsg;
import business.parts.NPZC106001PMsg;

import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetLocRoleTpCdForPrchReq;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetLocRoleTpCdForPrchReqBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Get Email Address for PO API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/10/2013   Hitachi         Y.Igarashi      Create          N/A
 * 06/27/2013   Hitachi         T.Tomita        Update          QC1325
 * 04/28/2014   Hitachi         T.Tomita        Update          ITG#507619
 *</pre>
 */
public class NPZC106001 extends S21ApiCommonBase implements ZYPConstant {

    /** NPZM0001E */
    private static final String NPZM0001E = "NPZM0001E";

    /** NPZM0125E */
    private static final String NPZM0125E = "NPZM0125E";

    /** ASTERISK (*) */
    private static final String ASTERISK = "*";

    /** SEPARATOR (,) */
    private static final String SEPARATOR = ",";

    // START 04/28/2014 T.Tomita [ITG#507619, ADD]
    /** NPZC1060_PO_SUBMT_PSN_FLG */
    private static final String NPZC1060_PO_SUBMT_PSN_USE_FLG = "NPZC1060_PO_SUBMT_PSN_USE_FLG";
    // END 04/28/2014 T.Tomita [ITG#507619, ADD]

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * NPZC106001
     */
    public NPZC106001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NPZC106001PMsg
     * @param onBatchType BATCH/ONLINE
     */
    public void execute(final NPZC106001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        doProcess(msgMap);
        msgMap.flush();
    }

    /**
     * doProcess
     * @param msgMap PMsg
     */
    private void doProcess(S21ApiMessageMap msgMap) {
        if (!checkParam(msgMap)) {
            return;
        }

        NPZC106001PMsg pMsg = (NPZC106001PMsg) msgMap.getPmsg();
        // START 04/28/2014 T.Tomita [ITG#507619, ADD]
        final String getPoAddressFlg = ZYPCodeDataUtil.getVarCharConstValue(NPZC1060_PO_SUBMT_PSN_USE_FLG, pMsg.glblCmpyCd.getValue());
        // END 04/28/2014 T.Tomita [ITG#507619, ADD]
        POTMsg poTMsg = null;
        if (ZYPCommonFunc.hasValue(pMsg.poOrdNum)) {
            poTMsg = getPO(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue());
            // START 04/28/2014 T.Tomita [ITG#507619, ADD]
            if (ZYPCommonFunc.hasValue(getPoAddressFlg) && getPoAddressFlg.equals(ZYPConstant.FLG_ON_Y)) {
            // END 04/28/2014 T.Tomita [ITG#507619, ADD]
                if (getPoAddress(pMsg, poTMsg)) {
                    return;
                }
            // START 04/28/2014 T.Tomita [ITG#507619, ADD]
            }
            // END 04/28/2014 T.Tomita [ITG#507619, ADD]
        }

        getDefaultEml(pMsg, poTMsg);

        if (pMsg.EmlAddrList.getValidCount() < 1) {
            msgMap.addXxMsgId(NPZM0125E);
        }
    }

    /**
     * checkParam
     * @param msgMap PMsg
     * @return true : no error / false : error
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {
        NPZC106001PMsg pMsg = (NPZC106001PMsg) msgMap.getPmsg();
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZM0001E);
            return false;
        }
        return true;
    }

    /**
     * getPoAddress
     * @param pMsg NPZC106001PMsg
     * @param poTMsg POTMsg
     * @return true : Get EMail / false : Other
     */
    private boolean getPoAddress(NPZC106001PMsg pMsg, POTMsg poTMsg) {
        if (poTMsg != null) {
            S21_PSNTMsg psnTMsg = getS21Psn(pMsg.glblCmpyCd.getValue(), poTMsg.poSubmtPsnCd.getValue());
            if (psnTMsg != null && ZYPCommonFunc.hasValue(psnTMsg.emlAddr)) {
                setEml(pMsg, psnTMsg.emlAddr.getValue());
                return true;
            }
        }
        return false;
    }

    /**
     * getPO
     * @param glblCmpyCd Global Company Code
     * @param poOrdNum PO Order Number
     * @return POTMsg
     */
    private POTMsg getPO(String glblCmpyCd, String poOrdNum) {
        POTMsg inTMsg = new POTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.poOrdNum, poOrdNum);

        return (POTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
    }

    /**
     * getS21Psn
     * @param glblCmpyCd Global Company Code
     * @param psnCd Person Code
     * @return S21_PSNTMsg
     */
    private S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {
        S21_PSNTMsg inTMsg = new S21_PSNTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.psnCd, psnCd);

        S21_PSNTMsg outTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (outTMsg != null && FLG_ON_Y.equals(outTMsg.delFlg.getValue())) {
            return null;
        }
        return outTMsg;
    }

    /**
     * getDefaultEml
     * @param pMsg getDefaultEml
     * @param poTMsg POTMsg
     */
    private void getDefaultEml(NPZC106001PMsg pMsg, POTMsg poTMsg) {
        List<String> prchGrpCdList = new ArrayList<String>();
        String locRoleTpCd = null;
        if (ZYPCommonFunc.hasValue(pMsg.poOrdNum)) {
            prchGrpCdList = getPrchGrpCdList(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue());
            if (prchGrpCdList.size() > 0 && poTMsg != null) {
                // 2013/06/27 QC1325 T.Tomita Update Start
                // locRoleTpCd = NPXC001001GetLocRoleTpCdForPrchReq.getLocRoleTpCd(pMsg.glblCmpyCd.getValue(), poTMsg.invtyLocCd.getValue());
                NPXC001001GetLocRoleTpCdForPrchReqBean bean = NPXC001001GetLocRoleTpCdForPrchReq.getLocRoleTpCd(pMsg.glblCmpyCd.getValue(), poTMsg.invtyLocCd.getValue());
                if (bean != null) {
                    locRoleTpCd = bean.getLocRoleTpCd();
                }
                // 2013/06/27 QC1325 T.Tomita Update End
            }
        }
        getPoNtcSendPsn(pMsg, prchGrpCdList, locRoleTpCd);
    }

    /**
     * getPrchGrpCdList
     * @param glblCmpyCd Global Company Code
     * @param poOrdNum PO Order Number
     * @return PrchGrpCd List
     */
    @SuppressWarnings("unchecked")
    private List<String> getPrchGrpCdList(String glblCmpyCd, String poOrdNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("poOrdNum", poOrdNum);

        List<String> resultList = this.ssmBatchClient.queryObjectList("getPrchGrpCd", paramMap);
        return resultList;
    }

    /**
     * getPoNtcSendPsn
     * @param pMsg NPZC106001PMsg
     * @param prchGrpCdList Purchase Group Code List
     * @param locRoleTpd Location Role Type Code
     */
    private void getPoNtcSendPsn(NPZC106001PMsg pMsg, List<String> prchGrpCdList, String locRoleTpd) {
        boolean hasFlg = false;
        if (prchGrpCdList.size() > 0) {
            if (ZYPCommonFunc.hasValue(locRoleTpd)) {
                // search condition 1
                hasFlg = getPoNtcSendPsnEml(pMsg, prchGrpCdList, locRoleTpd);
            }
            if (!hasFlg) {
                // search condition 2
                hasFlg = getPoNtcSendPsnEml(pMsg, prchGrpCdList, ASTERISK);
            }
        }
        if (!hasFlg) {
            // search condition 3
            getPoNtcSendPsnEml(pMsg, ASTERISK, ASTERISK);
        }
    }

    /**
     * getPoNtcSendPsnEml
     * @param pMsg NPZC106001PMsg
     * @param prchGrpCdList Purchase Group Code List
     * @param locRoleTpCd Location Role Type Code
     * @return true : Get EMail / false : Other
     */
    private boolean getPoNtcSendPsnEml(NPZC106001PMsg pMsg, List<String> prchGrpCdList, String locRoleTpCd) {
        boolean hasFlg = false;
        for (String prchGrpCd : prchGrpCdList) {
            if (getPoNtcSendPsnEml(pMsg, prchGrpCd, locRoleTpCd)) {
                hasFlg = true;
            }
        }
        return hasFlg;
    }

    /**
     * getPoNtcSendPsnEml
     * @param pMsg NPZC106001PMsg
     * @param prchGrpCd Purchase Group Code
     * @param locRoleTpCd Location Role Type Code
     * @return true : Get EMail / false : Other
     */
    private boolean getPoNtcSendPsnEml(NPZC106001PMsg pMsg, String prchGrpCd, String locRoleTpCd) {
        PO_NTC_SEND_PSNTMsg inTMsg = new PO_NTC_SEND_PSNTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.prchGrpCd, prchGrpCd);
        setValue(inTMsg.locRoleTpCd, locRoleTpCd);

        PO_NTC_SEND_PSNTMsg outTMsg = (PO_NTC_SEND_PSNTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (outTMsg != null) {
            setEml(pMsg, outTMsg.poCtacEmlAddr.getValue());
            return true;
        }
        return false;
    }

    /**
     * setEmail
     * @param pMsg NPZC106001PMsg
     * @param eMailAddr E-Mail Address
     */
    private void setEml(NPZC106001PMsg pMsg, String eMailAddr) {
        String[] addrList = eMailAddr.split(SEPARATOR);
        int tmpCnt = pMsg.EmlAddrList.getValidCount();
        for (String addr : addrList) {
            setValue(pMsg.EmlAddrList.no(tmpCnt).emlAddr, addr);
            tmpCnt++;
        }
        pMsg.EmlAddrList.setValidCount(tmpCnt);
    }

}
