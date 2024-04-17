/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1350.common;

import static business.blap.NSAL1350.constant.NSAL1350Constant.NZZM0000E;
import static business.blap.NSAL1350.constant.NSAL1350Constant.NZZM0001W;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import business.blap.NSAL1350.NSAL1350CMsg;
import business.blap.NSAL1350.NSAL1350Query;
import business.blap.NSAL1350.NSAL1350_ACMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NSAL1350CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 * 2018/07/04   Hitachi         T.Tomita        Update          QC#27099
 * 2024/03/21   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1350CommonLogic {

    /**
     * search
     * @param bizMsg Business Component Interface Message
     */
    public static void search(NSAL1350CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NSAL1350Query.getInstance().search(bizMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);
            return;

        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<String> omitLineList = getOmitLineList(bizMsg);
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            if (i == bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            if (isOmitLine(rsltMap, omitLineList)) {
                continue;
            }
            NSAL1350_ACMsg aBizMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsOrdPosnNum_A, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdlId_A, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.t_MdlNm_A, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dplyLineNum_A, (String) rsltMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.cpoDtlLineNum_A, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.cpoDtlLineSubNum_A, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseCd_A, (String) rsltMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseDescShortTxt_A, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseItemTpCd_A, (String) rsltMap.get("MDSE_ITEM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseItemTpNm_A, (String) rsltMap.get("MDSE_ITEM_TP_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_A, (String) rsltMap.get("XX_GENL_FLD_AREA_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocCd_A, (String) rsltMap.get("BILL_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.shipToCustLocCd_A, (String) rsltMap.get("SHIP_TO_CUST_LOC_CD"));
            // Add Start 2024/03/21 QC#63638
            ZYPEZDItemValueSetter.setValue(aBizMsg.shpgStsCd_A, (String) rsltMap.get("SHPG_STS_CD"));
            // Add End 2024/03/21 QC#63638

            i++;
        }
        bizMsg.A.setValidCount(i);
        if (i == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
    }

    private static boolean isOmitLine(Map<String, Object> rsltMap, List<String> omitLineList) {
        return omitLineList.contains(//
                S21StringUtil.concatStrings(//
                        (String) rsltMap.get("CPO_DTL_LINE_NUM") //
                        , "," //
                        , (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM")));
    }

    private static List<String> getOmitLineList(NSAL1350CMsg bizMsg) {
        List<String> list = new ArrayList<String>(100);
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            list.add(S21StringUtil.concatStrings(//
                    bizMsg.I.no(i).cpoDtlLineNum_I.getValue() //
                    , "," //
                    , bizMsg.I.no(i).cpoDtlLineSubNum_I.getValue()));
        }
        return list;
    }

    /**
     * <pre>
     * @param bizMsg    NSAL1350CMsg
     * </pre>
     */
    public static void createPulldownFromSearchRslt(NSAL1350CMsg bizMsg) {
        Map<String, String> itemTpMap = new TreeMap<String, String>();
        Map<BigDecimal, String> mdlMap = new TreeMap<BigDecimal, String>();
        List<String> configList = new ArrayList<String>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1350_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!itemTpMap.containsKey(aBizMsg.mdseItemTpCd_A.getValue())) {
                itemTpMap.put(aBizMsg.mdseItemTpCd_A.getValue(), aBizMsg.mdseItemTpNm_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {
                if (!mdlMap.containsKey(aBizMsg.mdlId_A.getValue())) {
                    mdlMap.put(aBizMsg.mdlId_A.getValue(), aBizMsg.t_MdlNm_A.getValue());
                }
            }
            if (!configList.contains(aBizMsg.dsOrdPosnNum_A.getValue())) {
                configList.add(aBizMsg.dsOrdPosnNum_A.getValue());
            }
        }

        int i = 0;
        bizMsg.mdseItemTpCd_LC.clear();
        bizMsg.mdseItemTpNm_LD.clear();
        for (Map.Entry<String, String> itemTpE : itemTpMap.entrySet()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_LC.no(i), itemTpE.getKey());
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpNm_LD.no(i), itemTpE.getValue());
            i++;
        }

        i = 0;
        bizMsg.mdlId_LC.clear();
        bizMsg.t_MdlNm_LD.clear();
        for (Map.Entry<BigDecimal, String> mdlMapE : mdlMap.entrySet()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_LC.no(i), mdlMapE.getKey());
            ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm_LD.no(i), mdlMapE.getValue());
            i++;
        }

        // Del Start 2018/07/03 QC#27099
        // i = 0;
        // bizMsg.dsOrdPosnNum_LC.clear();
        // bizMsg.dsOrdPosnNum_LD.clear();
        // for (String configNum : configList) {
        // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_LC.no(i),
        // configNum);
        // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_LD.no(i),
        // configNum);
        // i++;
        // }
        // Del End 2018/07/03 QC#27099
    }

}
