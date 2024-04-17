/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import java.util.HashMap;
import java.util.Map;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForMeterEntry;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONV_PROC_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * </pre>
 */
public class NWAL1500CommonLogicForMeterEntry {

    public static boolean isProceccorbleLine(NWAL1500CMsg bizMsg, String dsOrdLineCatgCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        queryParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        queryParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        queryParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult ssmResult = NWAL1500QueryForMeterEntry.getInstance().getConversionProcessTypeTxt(queryParam);
        String cnvProcTpTxt = (String) ssmResult.getResultObject();
        if (S21StringUtil.isEmpty(cnvProcTpTxt)) {
            return false;
        }
        return S21StringUtil.isEquals(cnvProcTpTxt, CONV_PROC_TP.RENTAL_TO_SALES);
    }
}
