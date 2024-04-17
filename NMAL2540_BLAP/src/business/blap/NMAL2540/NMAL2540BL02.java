/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2540;

import static business.blap.NMAL2540.constant.NMAL2540Constant.NMAM0039E;
import static business.blap.NMAL2540.constant.NMAL2540Constant.NMAM0147I;
import static business.blap.NMAL2540.constant.NMAL2540Constant.NMAM8359E;
import static business.blap.NMAL2540.constant.NMAL2540Constant.NMAM8360W;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2540.constant.NMAL2540Constant;
import business.db.CTRYTMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/11   Fujitsu         N.Sugiura       Update          CSA-QC#4340
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NMAL2540BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2540_INIT".equals(screenAplID)) {
                doProcess_NMAL2540_INIT((NMAL2540CMsg) cMsg, (NMAL2540SMsg) sMsg);
            } else if ("NMAL2540Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2540Scrn00_CMN_Clear((NMAL2540CMsg) cMsg, (NMAL2540SMsg) sMsg);
            } else if ("NMAL2540Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NMAL2540Scrn00_CMN_Close((NMAL2540CMsg) cMsg, (NMAL2540SMsg) sMsg);
            // QC#4505
            } else if ("NMAL2540Scrn00_GetAddress".equals(screenAplID)) {
                doProcess_NMAL2540Scrn00_GetAddress((NMAL2540CMsg) cMsg, (NMAL2540SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param bizMsg NMAL2540CMsg
     * @param sMsg NMAL2540SMsg
     */
    private void doProcess_NMAL2540_INIT(NMAL2540CMsg bizMsg, NMAL2540SMsg sMsg) {
        // 2016/03/11 S21-QC#4340 Add Start
        CTRYTMsg ctryTMsg = new CTRYTMsg();
        ctryTMsg = (CTRYTMsg) ZYPCodeDataUtil.findByCode(CTRY.class, getGlobalCompanyCode(), CTRY.UNITED_STATES_OF_AMERICA);
        if (ctryTMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H1, ctryTMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryNm_H1, ctryTMsg.ctryNm);
        }
        // 2016/03/11 S21-QC#4340 Add End
    }

    /**
     * @param bizMsg NMAL2540CMsg
     * @param sMsg NMAL2540SMsg
     */
    private void doProcess_NMAL2540Scrn00_CMN_Clear(NMAL2540CMsg cMsg, NMAL2540SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
    }

    /**
     * @param bizMsg NMAL2540CMsg
     * @param sMsg NMAL2540SMsg
     */
    private void doProcess_NMAL2540Scrn00_CMN_Close(NMAL2540CMsg bizMsg, NMAL2540SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.firstLineAddr_H1)) {
            bizMsg.firstLineAddr_H1.setErrorInfo(1, NMAL2540Constant.ZZM9000E, new String[] {NMAL2540Constant.FIRST_LINE_ADDR });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.ctyAddr_H1)) {
            bizMsg.ctyAddr_H1.setErrorInfo(1, NMAL2540Constant.ZZM9000E, new String[] {NMAL2540Constant.CTY_ADDR });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.stCd_H1)) {
            bizMsg.stCd_H1.setErrorInfo(1, NMAL2540Constant.ZZM9000E, new String[] {NMAL2540Constant.ST_CD });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.postCd_H1)) {
            bizMsg.postCd_H1.setErrorInfo(1, NMAL2540Constant.ZZM9000E, new String[] {NMAL2540Constant.POST_CD });
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.ctryCd_H1)) {
            bizMsg.ctryCd_H1.setErrorInfo(1, NMAL2540Constant.ZZM9000E, new String[] {NMAL2540Constant.CTRY_CD });
            return;
        }

        // Address Validation API
        NMZC003001PMsg paramMsg = new NMZC003001PMsg();

        paramMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        paramMsg.firstLineAddr.setValue(bizMsg.firstLineAddr_H1.getValue());
        paramMsg.scdLineAddr.setValue(bizMsg.scdLineAddr_H1.getValue());
        paramMsg.ctyAddr.setValue(bizMsg.ctyAddr_H1.getValue());
        paramMsg.stCd.setValue(bizMsg.stCd_H1.getValue());
        paramMsg.postCd.setValue(bizMsg.postCd_H1.getValue());
        paramMsg.ctryCd.setValue(bizMsg.ctryCd_H1.getValue());
        paramMsg.cntyNm.setValue(bizMsg.cntyNm_H1.getValue());

        new NMZC003001().execute(paramMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(paramMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return;
            }
        }

        boolean result = true;
        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_01.getValue())) {
            bizMsg.firstLineAddr_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H1, paramMsg.firstLineAddr);
            bizMsg.firstLineAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_02.getValue())) {
            bizMsg.scdLineAddr_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H1, paramMsg.scdLineAddr);
            bizMsg.scdLineAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_03.getValue())) {
            bizMsg.ctyAddr_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_03.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_H1, paramMsg.ctyAddr);
            bizMsg.ctyAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_04.getValue())) {
            bizMsg.stCd_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_04.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H1, paramMsg.stCd);
            bizMsg.stCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_05.getValue())) {
            bizMsg.postCd_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H1, paramMsg.postCd);
            bizMsg.postCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_06.getValue())) {
            bizMsg.ctryCd_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_06.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H1, paramMsg.ctryCd);
            bizMsg.ctryCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_07.getValue())) {
            bizMsg.cntyNm_H1.setErrorInfo(1, NMAM8359E);
            result = false;

        } else if (RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_H1, paramMsg.cntyNm);
            bizMsg.cntyNm_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            result = false;

        }

        if (result) {
           ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_H1, paramMsg.cntyNm.getValue());
        }

        return;

    }
    
    // QC#4505
    private void doProcess_NMAL2540Scrn00_GetAddress(NMAL2540CMsg cMsg, NMAL2540SMsg sMsg) {
        if(!CTRY.UNITED_STATES_OF_AMERICA.equals(cMsg.ctryCd_H1.getValue())){
            cMsg.setMessageInfo(NMAM0147I);
            return;
        }

        List<Map<String, Object>> list = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("postCd", cMsg.postCd_H1.getValue());
        S21SsmEZDResult res = NMAL2540Query.getInstance().getAddrByPost(queryParam);
        
        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            if(cMsg.postCd_H1.getValue().length() > 5){
                queryParam.put("postCd", cMsg.postCd_H1.getValue().subSequence(0, 5));
                res = NMAL2540Query.getInstance().getAddrByPost(queryParam);
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }
        
        if (list == null) {
            cMsg.postCd_H1.setErrorInfo(1, NMAM0039E, new String[] {"Postal Code" });
            return;
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_H1, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_H1, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_H1, listCntyNm.get(0));
            }
        }
    }
    
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
}
