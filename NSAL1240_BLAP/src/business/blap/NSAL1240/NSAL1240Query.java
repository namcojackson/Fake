/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1240;

import static business.blap.NSAL1240.constant.NSAL1240Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/07/14   Hitachi         K.Yamada        Update          CSA QC#11368
 * 2017/07/26   Fujitsu         S.Takami        Update          CSA QC#20004
 *</pre>
 */
public final class NSAL1240Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1240Query INSTANCE = new NSAL1240Query();

    /**
     * Constructor.
     */
    private NSAL1240Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1240Query
     */
    public static NSAL1240Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg, int cnt) {
        // del start 2016/07/14 CSA Defect#11368
        //if (hasValue(cMsg.dsContrNum_H)) {
        //    return getSsmEZDClient().queryEZDMsgArray("getSearchDataByContr", getSsmParam(cMsg, sMsg, cnt), sMsg.B);
        //}
        // del end 2016/07/14 CSA Defect#11368
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.B);
    }

    /**
     * getSsmParam
     * @param cMsg NSAL1240CMsg
     * @param sMsg NSAL1240SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizMsg", cMsg);
        params.put("svcMachMstrStsCdList", getSvcMachMstrStsCdList(cMsg));
        if (hasValue(cMsg.xxChkBox_HA) && !hasValue(cMsg.xxChkBox_HB)) {
            params.put("svcMachMaintAvalFlg", ZYPConstant.FLG_OFF_N);
        } else if (!hasValue(cMsg.xxChkBox_HA) && hasValue(cMsg.xxChkBox_HB)) {
            params.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);
        }
        params.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        if (hasValue(cMsg.stkStsCd_H)) {
            params.put("stkStsCd", cMsg.stkStsCd_H.getValue());
        }
        if (hasValue(cMsg.svcMachUsgStsCd_H)) {
            params.put("svcMachUsgStsCd", cMsg.svcMachUsgStsCd_H.getValue());
        }
        if (hasValue(cMsg.curLocNum_H1)) {
            params.put("curLocNum_H1", cMsg.curLocNum_H1.getValue().concat(CONST_PERCENT));
        }
        if (hasValue(cMsg.curLocNum_H2)) {
            params.put("curLocNum_H2", CONST_PERCENT.concat(cMsg.curLocNum_H2.getValue()));
        }
        // mod start 2016/07/14 CSA Defect#11368
        params.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("maxDate", MAX_DATE);
        params.put("warranty", DS_CONTR_CATG.WARRANTY);
        // mod end 2016/07/14 CSA Defect#11368
        params.put("rowNum", cnt);

        // 2017/07/26 CSA QC#20004 Add Start
        List<BigDecimal> configIdList = new ArrayList<BigDecimal>(0);

        for (int i = 0; i < cMsg.Q.getValidCount(); i++) {
            configIdList.add(cMsg.Q.no(i).svcConfigMstrPk_Q.getValue());
        }
        if (configIdList.isEmpty()) {
            params.remove("configIdList");
        } else {
            params.put("configIdList", configIdList);
        }
        // 2017/07/26 CSA QC#20004 Add End

        return params;
    }

    private static Set<String> getSvcMachMstrStsCdList(NSAL1240CMsg cMsg) {
        Set<String> stsCdSet = new LinkedHashSet<String>();

        if (hasValue(cMsg.xxChkBox_H1)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.CREATED);
        }
        if (hasValue(cMsg.xxChkBox_H2)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        }
        if (hasValue(cMsg.xxChkBox_H3)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.INSTALLED);
        }
        if (hasValue(cMsg.xxChkBox_H4)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        }
        if (hasValue(cMsg.xxChkBox_H5)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.REMOVED);
        }
        if (hasValue(cMsg.xxChkBox_H6)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.TERMINATED);
        }
        if (hasValue(cMsg.xxChkBox_H7)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.DUPLICATE);
        }
        if (hasValue(cMsg.xxChkBox_H8)) {
            stsCdSet.add(SVC_MACH_MSTR_STS.DEALER_SERVICE);
        }
        return stsCdSet;
    }

    // add start 2016/05/16 CSA Defect#4578
    /**
     * getIndBillToLocNum
     * @param String glblCmpyCd
     * @param String billToLocNum
     * @return String
     */
    public static String getIndBillToLocNum(String glblCmpyCd, String billToLocNum) {
        if (!ZYPCommonFunc.hasValue(billToLocNum)) {
            return null;
        }

        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("billToCustCd01", billToLocNum);

        BILL_TO_CUSTTMsgArray resultArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (resultArray.getValidCount() == 0) {
            return null;
        }

        return resultArray.no(0).locNum.getValue();
    }

    /**
     * getIndCurLocNum
     * @param String glblCmpyCd
     * @param String curLocNum
     * @return String
     */
    public static String getIndCurLocNum(String glblCmpyCd, String curLocNum) {
        if (!ZYPCommonFunc.hasValue(curLocNum)) {
            return null;
        }

        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", curLocNum);

        SHIP_TO_CUSTTMsgArray resultArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (resultArray.getValidCount() == 0) {
            return null;
        }

        return resultArray.no(0).locNum.getValue();
    }
    // add end 2016/05/16 CSA Defect#4578
}
