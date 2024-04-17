/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1630.common;

import static business.blap.NWAL1630.constant.NWAL1630Constant.NWAM0588E;
import static business.blap.NWAL1630.constant.NWAL1630Constant.NWAM0670E;
import static business.blap.NWAL1630.constant.NWAL1630Constant.NWAM0945E;
import static business.blap.NWAL1630.constant.NWAL1630Constant.NWZM0422E;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_BW_ORG_ATTRB_NM;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_CLR_ORG_ATTRB_NM;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_TOT_ORG_ATTRB_NM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1630.NWAL1630CMsg;
import business.blap.NWAL1630.NWAL1630Query;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1630CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2019/01/24   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630CommonLogic {

    /**
     * callPricingAPI
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     */
    public static void callPricingAPI(NWAL1630CMsg bizMsg, String glblCmpyCd) {

        bizMsg.csmpPrcListCd.clear();

        NWZC157001PMsg prcApiMsg = new NWZC157001PMsg();
        setPricingAPIMsg(prcApiMsg, bizMsg, glblCmpyCd);

        NWZC157001 pricingApi = new NWZC157001();
        pricingApi.execute(prcApiMsg, ONBATCH_TYPE.ONLINE);

        if (prcApiMsg.xxMsgIdList.getValidCount() > 1) {
            bizMsg.setMessageInfo(NWAM0588E);
            return;
        }

        if (prcApiMsg.xxPrcList.getValidCount() < 1) {
            bizMsg.setMessageInfo(NWZM0422E);
            return;
        }
        if (prcApiMsg.xxPrcList.getValidCount() > 1) {
            bizMsg.setMessageInfo(NWAM0670E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.csmpPrcListCd, prcApiMsg.xxPrcList.no(0).prcCatgCd);
        return;
    }

    /**
     * setPricingAPIMsg
     * @param prcApiMsg NWZC157001PMsg
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setPricingAPIMsg(NWZC157001PMsg prcApiMsg, NWAL1630CMsg bizMsg, String glblCmpyCd) {

        ZYPEZDItemValueSetter.setValue(prcApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.ordDt);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcCtxTpCd, PRC_CTX_TP.CSMP_CREDIT);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dlrRefNum, bizMsg.dlrRefNum);
    }
    
    // 2019/01/24 S21_NA#29446 Add Start
    @SuppressWarnings("unchecked")
    public static void driveContrCapInfo(NWAL1630CMsg bizMsg, String glblCmpyCd) {
        bizMsg.annTermCapNum_BW.clear();
        bizMsg.annTermCapNum_CL.clear();
        bizMsg.annTermCapNum_TT.clear();
        bizMsg.splyCdTxt.clear();

        boolean isCappedSupply = false;
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrNum) && ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk)) {
            List<String> svcTermCondAttrbNmList = new ArrayList<String>();
            String capBwOrig = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_BW_ORG_ATTRB_NM, glblCmpyCd);
            String capClrOrig = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_CLR_ORG_ATTRB_NM, glblCmpyCd);
            String capTotOrig = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_TOT_ORG_ATTRB_NM, glblCmpyCd);
            setConstValList(svcTermCondAttrbNmList, capBwOrig);
            setConstValList(svcTermCondAttrbNmList, capClrOrig);
            setConstValList(svcTermCondAttrbNmList, capTotOrig);

            S21SsmEZDResult ssmResult = NWAL1630Query.getInstance().getContrCapInfo(bizMsg, svcTermCondAttrbNmList);
            if (!ssmResult.isCodeNotFound()) {
                List<Map<String, String>> resultMapList = (List<Map<String, String>>) ssmResult.getResultObject();
                for (Map<String, String> resultMap : resultMapList) {
                    String attrbNm = resultMap.get("SVC_TERM_COND_ATTRB_NM");
                    String attrbVal = resultMap.get("SVC_TERM_ATTRB_MAP_VAL_CD");
                    if (S21StringUtil.isEquals(attrbNm, capBwOrig) && ZYPCommonFunc.hasValue(attrbVal)) {
                        isCappedSupply = true;
                        ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum_BW, new BigDecimal(attrbVal));
                    } else if (S21StringUtil.isEquals(attrbNm, capClrOrig) && ZYPCommonFunc.hasValue(attrbVal)) {
                        isCappedSupply = true;
                        ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum_CL, new BigDecimal(attrbVal));
                    } else if (S21StringUtil.isEquals(attrbNm, capTotOrig) && ZYPCommonFunc.hasValue(attrbVal)) {
                        isCappedSupply = true;
                        ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum_TT, new BigDecimal(attrbVal));
                    }
                }
            }
        }
        if (isCappedSupply) {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyCdTxt, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyCdTxt, ZYPConstant.FLG_OFF_N);
        }
    }

    private static void setConstValList(List<String> constValList, String constVal) {
        if (!ZYPCommonFunc.hasValue(constVal)) {
            return;
        }
        constValList.add(constVal);
    }

    @SuppressWarnings("unchecked")
    public static void deriveSvcMachMstrPk(NWAL1630CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.serNum) || ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            S21SsmEZDResult ssmResult = NWAL1630Query.getInstance().getMachInfo(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.serNum.setErrorInfo(1, NWAM0945E);
                bizMsg.dsContrNum.setErrorInfo(1, NWAM0945E);
            } else {
                List<Map<String, Object>> resMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resMapList.size() == 1) {
                    resMapList.get(0);
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrPk, (BigDecimal) resMapList.get(0).get("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.serNum, (String) resMapList.get(0).get("SER_NUM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum, (String) resMapList.get(0).get("DS_CONTR_NUM"));
                }
            }
        }
    }
    
    public static void initClear(NWAL1630CMsg bizMsg) {
        bizMsg.svcMachMstrPk.clear();
        bizMsg.annTermCapNum_BW.clear();
        bizMsg.annTermCapNum_CL.clear();
        bizMsg.annTermCapNum_TT.clear();
        bizMsg.splyCdTxt.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.splyCdTxt, ZYPConstant.FLG_OFF_N);
    }
    // 2019/01/24 S21_NA#29446 Add End
}
