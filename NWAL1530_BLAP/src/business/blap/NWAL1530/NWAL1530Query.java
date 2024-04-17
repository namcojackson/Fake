/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1530;

import static business.blap.NWAL1530.constant.NWAL1530Constant.LINE_LVL_NM_ORD;
import static business.blap.NWAL1530.constant.NWAL1530Constant.LINE_LVL_NM_RMA;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * DSBL0190Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/12   Fujitsu         Y.Taoka         Update          QC#2764
 * 2016/02/17   Fujitsu         M.Suzuki        Update          QC#3555
 * 2016/08/05   Fujitsu         T.Yoshida       Update          QC#11057
 * 2016/08/30   Fujitsu         Y.Taoka         Update          QC#12484
 * 2016/12/27   Fujitsu         M.Ohno          Update          QC#15689
 * 2017/08/03   Fujitsu         S.Takami        Update          S21_NA#20016
 * 2018/03/23   Fujitsu         M.Ohno          Update          QC#22805
 *</pre>
 */
public final class NWAL1530Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1530Query MY_INSTANCE = new NWAL1530Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NWAL1530Query() {
        super();
    }

    /**
     * <pre>
     * Get the NWAL1530Query instance.
     * </pre>
     * @return NWAL1530Query instance
     */
    public static NWAL1530Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Get Order Detail. 
     * </pre>
     * @param bizMsg NWAL1530CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderDetail(NWAL1530CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", bizMsg.A.length() + 1);
        // S21_NA#11057 Mod Start
        // ssmParam.put("msg", bizMsg);
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        // S21_NA#11057 Mod End
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("lineLvlNmOrd", LINE_LVL_NM_ORD);
        ssmParam.put("lineLvlNmRma", LINE_LVL_NM_RMA);
        // 2018/03/16 QC#22805 add start
        if (bizMsg.F.getValidCount() > 0) {
            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd.getValue())) {
                ssmParam.put("isOutbound", ZYPConstant.FLG_ON_Y);
            } else if (CONFIG_CATG.INBOUND.equals(bizMsg.configCatgCd.getValue())) {
                ssmParam.put("isInbound", ZYPConstant.FLG_ON_Y);
            }
            ssmParam.put("msgF", bizMsg.F);
        } else {
            ssmParam.put("isOutbound", ZYPConstant.FLG_ON_Y);
            ssmParam.put("isInbound", ZYPConstant.FLG_ON_Y);
            ssmParam.put("isAll", ZYPConstant.FLG_ON_Y);
            ssmParam.put("msgF", null);
        }
        // 2018/03/16 QC#22805 add end

        return getSsmEZDClient().queryEZDMsgArray("getOrderDetail", ssmParam, bizMsg.A);
    }

    /**
     * <pre>
     * Get SO Detail. 
     * </pre>
     * @param bizMsg NWAL1530CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoInfo(NWAL1530CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", bizMsg.B.length() + 1);
        ssmParam.put("msg", bizMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        // 2017/08/03 S21_NA#20016 Add Start
        ssmParam.put("inbdOtbdCdOtbd", INBD_OTBD.OUTBOUND);
        // 2017/08/03 S21_NA#20016 Add End
        // 2018/03/16 QC#22805 add start
        ssmParam.put("msgF", bizMsg.F);
        // 2018/03/16 QC#22805 add start

        S21SsmEZDResult res = getSsmEZDClient().queryEZDMsgArray("getSoInfo", ssmParam, bizMsg.B);
        return res;
    }

    /**
     * <pre>
     * Get PO Detail. 
     * </pre>
     * @param bizMsg NWAL1530CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoInfo(NWAL1530CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", BigDecimal.ONE); // Mod 2016/12/27 M.Ohno S21_NA#15689
        ssmParam.put("msg", bizMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        // 2018/03/16 QC#22805 add start
        ssmParam.put("msgF", bizMsg.F);
        // 2018/03/16 QC#22805 add start

        return getSsmEZDClient().queryEZDMsgArray("getPoInfo", ssmParam, bizMsg.C);
    }

    /**
     * <pre>
     * Get RWS Detail. 
     * </pre>
     * @param bizMsg NWAL1530CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsInfo(NWAL1530CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", bizMsg.D.length() + 1);
        ssmParam.put("msg", bizMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        // 2018/03/16 QC#22805 add start
        ssmParam.put("msgF", bizMsg.F);
        // 2018/03/16 QC#22805 add start

        return getSsmEZDClient().queryEZDMsgArray("getRwsInfo", ssmParam, bizMsg.D);
    }

    /**
     * <pre>
     * Get Invoice Detail. 
     * </pre>
     * @param bizMsg NWAL1530CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceInfo(NWAL1530CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", bizMsg.E.length() + 1);
        ssmParam.put("msg", bizMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("invTpCreditMemo", INV_TP.CREDIT_MEMO); // QC#12484 ADD

        return getSsmEZDClient().queryEZDMsgArray("getInvoiceInfo", ssmParam, bizMsg.E);
    }

    /**
     * Get Serial Number.
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerialNumber(Map<String, Object> map) {
        // 2016/02/17 QC#3555 Mod Start
        map.put("shpgStsCd", SHPG_STS.SHIPPED);
        // 2016/02/17 QC#3555 Mod End

        return getSsmEZDClient().queryObjectList("getSerialNumber", map);
    }

}
