/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7180.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NMAL7180.NMAL7180CMsg;
import business.blap.NMAL7180.NMAL7180_ACMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7180CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#8282
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2020/02/19   Fujitsu         C.Hara          Update          QC#55203
 *</pre>
 */
public class NMAL7180CommonLogic {

    /**
     * setPrcGrp
     * @param ssmResult S21SsmEZDResult
     * @param bizMsg NMAL7180CMsg
     */
    public static void setPrcGrp(S21SsmEZDResult ssmResult, NMAL7180CMsg bizMsg) {

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7180_ACMsg bizMsgALine = bizMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpPk_A1, (BigDecimal) resultMap.get("PRC_GRP_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpNm_A1, (String) resultMap.get("PRC_GRP_NM"));
            // Add Start 2016/09/02 QC#8282
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpDescTxt_A1, (String) resultMap.get("PRC_GRP_DESC_TXT"));
            // Add End 2016/09/02 QC#8282
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpTpDescTxt_A1, (String) resultMap.get("PRC_GRP_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsgALine.actvFlg_A1, (String) resultMap.get("ACTV_FLG"));
            // 2020/02/19 QC#55203 Del Start
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpTrgtTpDescTxt_A1, (String) resultMap.get("PRC_GRP_TRGT_TP_NM"));
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpTrgtAttrbDescTxt_A1, (String) resultMap.get("PRC_GRP_TRGT_ATTRB_NM"));
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpDescTxt_A1, (String) resultMap.get("PRC_GRP_OP_NM"));
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpFromTxt_A1, (String) resultMap.get("PRC_GRP_FROM_TXT"));
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpThruTxt_A1, (String) resultMap.get("PRC_GRP_THRU_TXT"));
            // 2020/02/19 QC#55203 Del End
            ZYPEZDItemValueSetter.setValue(bizMsgALine.effFromDt_A1, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(bizMsgALine.effThruDt_A1, (String) resultMap.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpTrxTpDescTxt_A1, (String) resultMap.get("PRC_GRP_TRX_TP_DESC_TXT")); // QC#18785
            //ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpPrcdNum_A1, (BigDecimal) resultMap.get("PRC_GRP_PRCD_NUM")); // QC#18785 // 2020/02/19 QC#55203 Del
        }
    }
}
