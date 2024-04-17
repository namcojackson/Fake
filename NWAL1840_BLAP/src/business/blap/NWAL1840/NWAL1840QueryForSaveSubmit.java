/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         T.Murai         Create          N/A
 * 2016/10/07   Fujitsu         T.Murai         Update          S21_NA#11883
 * 2016/11/09   Fujitsu         T.Murai         Update          S21_NA#15851
 * 2017/05/21   Hitachi         T.Tomita        Update          S21_NA:RS#8385
 * 2019/12/07   Fujitsu         M.Ohno          Update          QC#54670
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public final class NWAL1840QueryForSaveSubmit extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1840QueryForSaveSubmit MY_INSTANCE = new NWAL1840QueryForSaveSubmit();

    /**
     * Constructor.
     */
    private NWAL1840QueryForSaveSubmit() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1840Query
     */
    public static NWAL1840QueryForSaveSubmit getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Check Duplication PO Number For Schedule Order
     * @param bizMsg NWAL1840CMsg
     * @return true: Duplication
     */
    public boolean isDuplicationPoNumForSchd(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum);
        params.put("canceled", ORD_HDR_STS.CANCELLED);
        params.put("custIssPoNum", bizMsg.custIssPoNum.getValue());
        params.put("sellToCustCd", bizMsg.sellToCustCd.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isDuplicationPoNumForSchd", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Check Configuration Param
     * @param bizMsg NWAL1840CMsg
     * @return false :Error
     */
    public boolean checkConfigParam(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("contrNum", bizMsg.dsContrNum.getValue());
        params.put("confId", bizMsg.svcConfigMstrPk.getValue());
        params.put("serNum", bizMsg.serNum.getValue());
        params.put("mdlId", bizMsg.mdlId.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkConfigParam", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Check Model Component - Merchandise
     * @param bizMsg NWAL1840CMsg
     * @param index int
     * @return false :Error
     */
    public boolean checkMdlComponent(NWAL1840CMsg bizMsg, int index) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("contrNum", bizMsg.dsContrNum.getValue());
        params.put("contrSqNum", bizMsg.dsContrSqNum.getValue());
        params.put("itemCd", bizMsg.A.no(index).mdseCd_A.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkMdlComponent", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Check substitute Item - Merchandise
     * @param bizMsg NWAL1840CMsg
     * @param index int
     * @return false :Error
     */
    public boolean checkSbstItem(NWAL1840CMsg bizMsg, int index) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("itemCd", bizMsg.A.no(index).mdseCd_A.getValue());
        params.put("sbstItemCd", bizMsg.A.no(index).sbstMdseCd_A.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("checkSbstItem", params);

        return 0 < (Integer) result.getResultObject();
    }

    // DEL Start 2016/11/09 S21_NA#15851
    // Add Start 2016/10/06 S21_NA#11883
//    /**
//     * Count DsOrdCatg - DsOrdTp Relation
//     * @param bizMsg NWAL1840CMsg
//     * @return false :Error
//     */
//    public boolean countDsOrdCatgRelnTp(NWAL1840CMsg bizMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
//
//        S21SsmEZDResult result = getSsmEZDClient().queryObject("countDsOrdCatgRelnTp", params);
//
//        return 0 < (Integer) result.getResultObject();
//    }
    // Add End 2016/10/06 S21_NA#11883
    // DEL End 2016/11/09 S21_NA#15851

    // Add Start 2016/11/09 S21_NA#15851
    /**
     * Count DsOrdTp FlgY
     * @param bizMsg NWAL1840CMsg
     * @return false : not Mandatory
     */
    public boolean countDsOrdTpFlgY(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());   
        params.put("splyAbuseFlgY", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("countDsOrdTpFlgY", params);

        return 0 < (Integer) result.getResultObject();
    }
    // Add End 2016/11/09 S21_NA#15851

    /**
     * Count DsContrCtrlSts
     * @param bizMsg NWAL1840CMsg
     * @return false : not Mandatory
     */
    public boolean countDsContrCtrlSts(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrNum", bizMsg.dsContrNum.getValue());
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.DRAFT);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ORDER);
        params.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("countDsContrCtrlSts", params);

        return 0 < (Integer) result.getResultObject();
    }

    // 2019/12/07 QC#54670 Add Start
    /**
     * cntOrdTpCd.
     * @param bizMsg NWAL1840CMsg
     * @return BigDecimal
     */
    public BigDecimal cntOrdTpCd(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("ordTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("trtyGrpTpIS", TRTY_GRP_TP.IS);

        return (BigDecimal) getSsmEZDClient().queryObject("cntOrdTpCd", params).getResultObject();
    }

    /**
     * cntAssignedISGroup.
     * @param bizMsg NWAL1840CMsg
     * @return BigDecimal
     */
    public BigDecimal cntAssignedISGroup(NWAL1840CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("psnNum", bizMsg.psnNum.getValue());
        params.put("trtyGrpTpIS", TRTY_GRP_TP.IS);
        params.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        params.put("gnrnTpFuture", GNRN_TP.FUTURE);

        return (BigDecimal) getSsmEZDClient().queryObject("cntAssignedISGroup", params).getResultObject();
    }
    // 2019/12/07 QC#54670 Add End

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * Count Registered Notes.
     * @param bizMsg NWAL1840CMsg
     * @return Integer
     */
    public Integer cntRegisteredNotes(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("svcMemoCatgCd", SVC_MEMO_CATG.SCHD_AGNT_MEMO);
        params.put("svcMemoTrxNm", "SCHD_AGMT_NUM");
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());
        params.put("startTs", bizMsg.startTs_NT.getValue());

        return (Integer) getSsmEZDClient().queryObject("cntRegisteredNotes", params).getResultObject();
    }

    /**
     * Get Multiple Registerd Scheduling Agreement For Machine.
     * @param bizMsg NWAL1840CMsg
     * @return SA Search Result
     */
    public S21SsmEZDResult getMultiRegSchdAgmtForMach(NWAL1840CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("schdAgmtNum", bizMsg.schdAgmtNum.getValue());
        params.put("schdAgmtStsCd", SCHD_AGMT_STS.ACTIVE);
        params.put("serNum", bizMsg.serNum.getValue());

        return getSsmEZDClient().queryObjectList("getMultiRegSchdAgmtForMach", params);
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
