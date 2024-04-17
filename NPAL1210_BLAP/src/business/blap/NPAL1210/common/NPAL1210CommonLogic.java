/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1210.common;

import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_BY_PSN_CD;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_FULL_PSN_NM;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_HIST_ACT_TP_DESC_TXT;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_HIST_INFO_TS;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_HIST_SRC_TP_DESC_TXT;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_APVL_HIST_TXT;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_COLUMN_TRX_REF_NUM;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1210.constant.NPAL1210Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1210.constant.NPAL1210Constant.NMAM0038I;
import static business.blap.NPAL1210.constant.NPAL1210Constant.NMAM8181W;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1210.NPAL1210CMsg;
import business.blap.NPAL1210.NPAL1210Query;
import business.blap.NPAL1210.NPAL1210SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1210 PO/Inventory Approval History
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 * 03/08/2016   CITS            K.Ogino         Update          QC#5148
 * 03/24/2016   CITS            T.Tokutomi      Update          QC#5763
 *</pre>
 */
public class NPAL1210CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Method Name: complementFromToDate <dd>When one of from or to is
     * empty, fill empty value with the other. <dd>When from is future
     * of to, swap from and to.
     * @param from EZDCDateItem
     * @param to EZDCDateItem
     */
    public static void complementFromToDate(EZDCDateItem from, EZDCDateItem to) {
        if (ZYPCommonFunc.hasValue(from) && !ZYPCommonFunc.hasValue(to)) {
            to.setValue(from.getValue());
            return;
        }

        if (ZYPCommonFunc.hasValue(to) && !ZYPCommonFunc.hasValue(from)) {
            from.setValue(to.getValue());
            return;
        }

        if (ZYPCommonFunc.hasValue(from) && ZYPCommonFunc.hasValue(to)) {
            // date can be compared as string
            String fromValue = from.getValue();

            if (fromValue.compareTo(to.getValue()) > 0) {
                from.setValue(to.getValue());
                to.setValue(fromValue);
            }
        }
    }

    /**
     * Search
     * @param cMsg NPAL1210CMsg
     * @param sMsg NPAL1210SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1210CMsg cMsg, NPAL1210SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = NPAL1210Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W);
                queryResCnt = sMsg.A.length();
            }
            List<Map<String, String>> list = (List) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlByPsnCd_A1, (String) map.get(DB_COLUMN_APVL_BY_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlFullPsnNm_A1, (String) map.get(DB_COLUMN_APVL_FULL_PSN_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlHistActTpDescTxt_A1, (String) map.get(DB_COLUMN_APVL_HIST_ACT_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_A1, (String) map.get(DB_COLUMN_APVL_HIST_INFO_TS));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).apvlHistTxt_A1, (String) map.get(DB_COLUMN_APVL_HIST_TXT));
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.apvlHistSrcTpDescTxt, (String) map.get(DB_COLUMN_APVL_HIST_SRC_TP_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, (String) map.get(DB_COLUMN_TRX_REF_NUM));
                }
            }
            sMsg.A.setValidCount(list.size());

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            int index = 1;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).seqNumber_A1, new BigDecimal(index));
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                index++;
            }
            cMsg.A.setValidCount(i);

        } else {
            cMsg.setMessageInfo(NMAM0038I);

        }
    }
}
