/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CTAC_CUST_REF_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         T.Murai         Create          N/A
 * 2016/06/13   Fujitsu         Y.Kanefusa      Update          QC#6480
 * 2016/08/02   Fujitsu         T.Murai         Update          S21_NA#11883
 * 2017/08/04   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/12/13   Fujitsu         C.Hara          Update          QC#29547
 * </pre>
 */
public final class NWAL1840QueryForSearch extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForSearch MY_INSTANCE = new NWAL1840QueryForSearch();

    /**
     * Constructor.
     */
    private NWAL1840QueryForSearch() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840QueryForSearch getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Schedule Agreement Header Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmtHdr(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryEZDMsg("getSchdAgmtHdr", params, bizMsg);

    }

    /**
     * Schedule Agreement Line Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmtLine(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());
        params.put("ordHdrStsCd_Canceled", ORD_HDR_STS.CANCELLED);
        params.put("PrcCondTp_Base", PRC_COND_TP.BASE_PRICE); // QC#6480 2016/06/13
        params.put("PrcCondTp_Mnpr", PRC_COND_TP.MANUAL_PRICE); // QC#6480 2016/06/13

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmtLine", params, bizMsg.A);

    }

    /**
     * Schedule Agreement Plan Search
     * @param bizMsg NWAL1840CMsg
     * @param afterShippedStsList List<String>
     * @return S21SsmEZDResult
     */
    //2018/12/13 QC#29547 Mod Start
    //public S21SsmEZDResult getSchdAgmtPln(NWAL1840CMsg bizMsg, List<String> afterShippedStsList) {
    public S21SsmEZDResult getSchdAgmtPln(NWAL1840CMsg bizMsg) {
    //2018/12/13 QC#29547 Mod End
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());
        params.put("ordHdrStsCd_Canceled", ORD_HDR_STS.CANCELLED);
        // Add 2016/08/02 S21_NA#11883
        params.put("ordLineStsCd_Canceled", ORD_LINE_STS.CANCELLED);

        //params.put("afterShippedStsList", afterShippedStsList); //2018/12/13 QC#29547 Del

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmtPln", params, bizMsg.B);

    }

    /**
     * Schedule Agreement Sales Credit Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmtSalesCredit(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmtSalesCredit", params, bizMsg.C);

    }

    /**
     * Schedule Agreement Contact Person Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmtCtacPsn(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmtCtacPsn", params, bizMsg.D);

    }
    //QC#16452 add Start
    /**
     * Contact Customer Reference Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacCustRefTp(NWAL1840CMsg bizMsg, String ctacTpCd, String locNum) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        NWAL1840_DCMsg ctacMsg = bizMsg.D.no(idx);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("locNum", locNum);
        params.put("ctacPsnPk", ctacMsg.ctacPsnPk_D.getValue());
        params.put("ctacTpCd", ctacTpCd);

        return getSsmEZDClient().queryObject("getCtacCustRefTp", params);

    }
    //QC#16452 add End

    /**
     * Schedule Agreement CAdditiionalData Search
     * 
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdAgmtData(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSchdAgmtData", params, bizMsg.F);

    }

    /**
     * Schedule Agreement CalcBase Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceInfo(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getPriceInfo", params, bizMsg.E);

    }

    /**
     * Schedule Agreement Create Order Time stamp Search
     * @param bizMsg NWAL1840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlnTsTxtList(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());

        return getSsmEZDClient().queryObjectList("getPlnTsTxtList", params);

    }
}
