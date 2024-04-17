/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1640;

import static business.blap.NWAL1640.constant.NWAL1640Constant.NMAM0039E;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1640.common.NWAL1640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1640BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#1029
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1640CMsg bizMsg = (NWAL1640CMsg) cMsg;
            NWAL1640SMsg glblMsg = (NWAL1640SMsg) sMsg;

            if ("NWAL1640_INIT".equals(screenAplID)) {
                doProcess_NWAL1640_INIT(bizMsg, glblMsg);

            } else if ("NWAL1640Scrn00_Change_SplyTpCd".equals(screenAplID)) {
                doProcess_NWAL1640Scrn00_Change_SplyTpCd(bizMsg, glblMsg);

            // START 2016/01/25 T.Tomita [QC#1029, DEL]
//            } else if ("NWAL1640_NMAL6780".equals(screenAplID)) {
//                doProcess_NWAL1640_NMAL6780(bizMsg, glblMsg);
            // END 2016/01/25 T.Tomita [QC#1029, DEL]

            // START 2016/01/25 T.Tomita [QC#1029, ADD]
            } else if ("NWAL1640_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL1640_NMAL6760(bizMsg, glblMsg);
            // END 2016/01/25 T.Tomita [QC#1029, ADD]

            } else if ("NWAL1640Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1640Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL1640Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL1640Scrn00_CMN_Close(bizMsg, glblMsg);

            // QC#4505
            } else if ("NWAL1640_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1640_NWAL1130(bizMsg, glblMsg);
                
            } else if ("NWAL1640Scrn00_GetAddress".equals(screenAplID)) {
                doProcess_NWAL1640Scrn00_GetAddress(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1640_INIT(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {

        ZYPCodeDataUtil.createPulldownList(SPLY_TP.class, bizMsg.splyTpCd_CD, bizMsg.splyTpNm_DP);

        if (ZYPCommonFunc.hasValue(bizMsg.splyStCd)) {
            NWAL1640CommonLogic.getStNm(bizMsg, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(bizMsg.stNm_BK, bizMsg.stNm);
        }
    }

    /**
     * Change_SplyTpCd Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1640Scrn00_Change_SplyTpCd(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {

        if (SPLY_TP.CUSTOMER.equals(bizMsg.splyTpCd.getValue())) {
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(bizMsg.splyNm, bizMsg.sellToCustLocNm);
            String prntVndNm = bizMsg.sellToCustAcctNm.getValue();
            if (prntVndNm.length() > bizMsg.getAttr("prntVndNm").getDigit()) {
            	prntVndNm = prntVndNm.substring(0, bizMsg.getAttr("prntVndNm").getDigit());
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, prntVndNm);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(bizMsg.splyFirstAddr, bizMsg.firstLineAddr_SE);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyStCd, bizMsg.stCd_SE);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyCtyAddr, bizMsg.ctyAddr_SE);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyPostCd, bizMsg.postCd_SE);

        } else if (SPLY_TP.CFS.equals(bizMsg.splyTpCd.getValue())) {
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(bizMsg.splyNm, bizMsg.xxLocNm);
            String prntVndNm = bizMsg.billToCustAcctNm.getValue();
            if (prntVndNm.length() > bizMsg.getAttr("prntVndNm").getDigit()) {
            	prntVndNm = prntVndNm.substring(0, bizMsg.getAttr("prntVndNm").getDigit());
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, prntVndNm);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(bizMsg.splyFirstAddr, bizMsg.firstLineAddr_BI);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyStCd, bizMsg.stCd_BI);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyCtyAddr, bizMsg.ctyAddr_BI);
            ZYPEZDItemValueSetter.setValue(bizMsg.splyPostCd, bizMsg.postCd_BI);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.splyStCd)) {
            NWAL1640CommonLogic.getStNm(bizMsg, getGlobalCompanyCode());
        }
    }

    // START 2016/01/25 T.Tomita [QC#1029, DEL]
//    /**
//     * NWAL1640_NMAL6780 Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NWAL1640_NMAL6780(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {
//
//        if (ZYPCommonFunc.hasValue(bizMsg.splyStCd)) {
//            NWAL1640CommonLogic.getStNm(bizMsg, getGlobalCompanyCode());
//        }
//    }
    // END 2016/01/25 T.Tomita [QC#1029, DEL]

    // START 2016/01/25 T.Tomita [QC#1029, ADD]
    /**
     * NWAL1640_NMAL6760 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1640_NMAL6760(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.splyStCd)) {
            NWAL1640CommonLogic.getStNm(bizMsg, getGlobalCompanyCode());
        }
    }
    // END 2016/01/25 T.Tomita [QC#1029, ADD]

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1640Scrn00_CMN_Clear(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.splyStCd)) {
            NWAL1640CommonLogic.getStNm(bizMsg, getGlobalCompanyCode());
        }
    }

    /**
     * CMN_Close Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1640Scrn00_CMN_Close(NWAL1640CMsg bizMsg, NWAL1640SMsg glblMsg) {

        NWAL1640CommonLogic.callAddrValidApi(bizMsg, getGlobalCompanyCode());
    }
    
    // QC#4505
    private void doProcess_NWAL1640_NWAL1130(NWAL1640CMsg cMsg, EZDSMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.splyStCd)) {
            NWAL1640CommonLogic.getStNm(cMsg, getGlobalCompanyCode());
        }
    }

    private void doProcess_NWAL1640Scrn00_GetAddress(NWAL1640CMsg cMsg, EZDSMsg sMsg) {
        List<Map<String, Object>> list = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("postCd", cMsg.splyPostCd.getValue());
        S21SsmEZDResult res = NWAL1640Query.getInstance().getAddrByPost(queryParam);
        
        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            if(cMsg.splyPostCd.getValue().length() > 5){
                queryParam.put("postCd", cMsg.splyPostCd.getValue().subSequence(0, 5));
                res = NWAL1640Query.getInstance().getAddrByPost(queryParam);
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }
        
        if (list == null) {
            cMsg.splyPostCd.setErrorInfo(1, NMAM0039E, new String[] {"Postal Code" });
            return;
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.splyCtyAddr, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.splyStCd, listStCd.get(0));
                NWAL1640CommonLogic.getStNm(cMsg, getGlobalCompanyCode());
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
