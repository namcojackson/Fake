/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0320;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0320.constant.NSBL0320Constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.db.SVC_RQST_CRIT_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         Y.Osawa         Create          N/A
 * 2019/10/01   Hitachi         K.Kitachi       Update          QC#53819
 *</pre>
 */
public class NSBL0320BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg sMsg) {
        NSBL0320CMsg cMsg = (NSBL0320CMsg) arg0;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0320_INIT".equals(screenAplID)) {
                doProcess_NSBL0320_INIT(cMsg);
            } else if ("NSBL0320Scrn00_OnChangeCriteria".equals(screenAplID)) {
                doProcess_NSBL0320Scrn00_OnChangeCriteria(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0320_INIT(NSBL0320CMsg cMsg) {

        setInitParams(cMsg);
        createPullDown(cMsg);
        getSearchData(cMsg);
    }

    private void doProcess_NSBL0320Scrn00_OnChangeCriteria(NSBL0320CMsg cMsg) {
        ZYPTableUtil.clear(cMsg.A);
        getSearchData(cMsg);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0320CMsg
     */
    private void setInitParams(NSBL0320CMsg cMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

    }

    /**
     * Clear Message
     * @param cMsg NSBL0320CMsg
     */
    public static void clearMsg(NSBL0320CMsg cMsg) {
        cMsg.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0320CMsg
     */
    public static void createPullDown(NSBL0320CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(SVC_RQST_CRIT_TP.class, cMsg.svcRqstCritTpCd_01, cMsg.svcRqstCritTpDescTxt_01);

        for (int i = 0; i < cMsg.svcRqstCritTpCd_01.length(); i++) {
            if (!hasValue(cMsg.svcRqstCritTpCd_01.no(i))) {
                break;
            }

            SVC_RQST_CRIT_TPTMsg critTpMsg = (SVC_RQST_CRIT_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_RQST_CRIT_TP.class
                    , cMsg.glblCmpyCd.getValue(), cMsg.svcRqstCritTpCd_01.no(i).getValue());
            if (critTpMsg != null && ZYPConstant.FLG_ON_Y.equals(critTpMsg.svcRqstCritDefFlg.getValue())) {
                setValue(cMsg.svcRqstCritTpCd, critTpMsg.svcRqstCritTpCd);
                break;
            }

            if (!hasValue(cMsg.svcRqstCritTpCd.getValue())) {
                setValue(cMsg.svcRqstCritTpCd, cMsg.svcRqstCritTpCd_01.no(0).getValue());
            }
        }
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0320CMsg
     */
    public static void getSearchData(NSBL0320CMsg cMsg) {

        S21SsmEZDResult ssmResult = NSBL0320Query.getInstance().getSearchData(cMsg);
        List<Map<String, Object>> searchList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

        if (ssmResult.isCodeNormal()) {
            // Result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                int i = 0;
                for (Map<String, Object> searchMap : searchList) {
                    // START 2019/10/01 K.Kitachi [QC#53819, MOD]
                    if (i >= cMsg.A.length()) {
                        break;
                    }
                    // END 2019/10/01 K.Kitachi [QC#53819, MOD]
                    cMsg.A.no(i).xxChkBox.setValue(ZYPConstant.FLG_OFF_N);
                    cMsg.A.no(i).xxDtlCd.setValue((String) searchMap.get("CD"));
                    setValue(cMsg.A.no(i).xxGenlFldAreaTxt, (String) searchMap.get("CDTXT"));
                    i++;
                }
                cMsg.A.setValidCount(cMsg.A.length());
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                int i = 0;
                for (Map<String, Object> searchMap : searchList) {
                    cMsg.A.no(i).xxChkBox.setValue(ZYPConstant.FLG_OFF_N);
                    cMsg.A.no(i).xxDtlCd.setValue((String) searchMap.get("CD"));
                    setValue(cMsg.A.no(i).xxGenlFldAreaTxt, (String) searchMap.get("CDTXT"));
                    i++;
                }
                cMsg.A.setValidCount(i);
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }
}
