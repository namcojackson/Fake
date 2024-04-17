package business.blap.NWAL1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          n/a
 * 2016/02/15   Fujitsu         T.Murai         Update          S21_NA#2176
 * 2016/06/10   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2016/08/09   Fujitsu         S.Iidaka        Update          S21_NA#11592
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2018/01/29   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2019/08/01   Fujitsu         M.Ohno          Update          S21_NA#52156
 *</pre>
 */

public final class NWAL1500QueryForSearch extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForSearch MY_INSTANCE = new NWAL1500QueryForSearch();

    /**
     * Constructor.
     */
    private NWAL1500QueryForSearch() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForSearch getInstance() {
        return MY_INSTANCE;
    }

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getCpoViewInfo(Map<String, String> queryParam, NWAL1500CMsg bizMsg) {
    public S21SsmEZDResult getCpoViewInfo(Map<String, Object> queryParam, NWAL1500CMsg bizMsg) {
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsg("getCpoViewInfo", queryParam, bizMsg);
    }

    // 2017/08/07 Sol#249 Mod Start
    // For Performance QC#8166 Mod Start
    // public S21SsmEZDResult getConfigInfo(Map<String, String> queryParam, NWAL1500_ACMsgArray msgArray) {
//    public S21SsmEZDResult getConfigInfo(Map<String, String> queryParam, NWAL1500_JCMsgArray msgArray) {
    public S21SsmEZDResult getConfigInfo(Map<String, Object> queryParam, NWAL1500_JSMsgArray msgArray) { // 2018/01/29 S21_NA#19808 Mod
    // For Performance QC#8166 Mod End
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getConfigInfo", queryParam, msgArray);
    }

    // For Performance QC#8166 Del Start
    // public S21SsmEZDResult getConfigRtrnInfo(Map<String, String> queryParam, NWAL1500_CCMsgArray msgArray) {
    //     return getSsmEZDClient().queryEZDMsgArray("getConfigRtrnInfo", queryParam, msgArray);
    // }
    // For Performance QC#8166 Del End

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getCpoDtlViewInfo(Map<String, String> queryParam, NWAL1500_BCMsgArray msgArray) {
    public S21SsmEZDResult getCpoDtlViewInfo(Map<String, Object> queryParam, NWAL1500_BSMsgArray msgArray) { // 2018/01/29 S21_NA#19808 Mod
    // 2017/08/07 Sol#249 Mod Start
        return getSsmEZDClient().queryEZDMsgArray("getCpoDtlViewInfo", queryParam, msgArray);
    }

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getCpoRtrnDtlViewInfo(Map<String, String> queryParam, NWAL1500_DCMsgArray msgArray) {
    public S21SsmEZDResult getCpoRtrnDtlViewInfo(Map<String, Object> queryParam, NWAL1500_DSMsgArray msgArray) { // 2018/01/29 S21_NA#19808 Mod
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getCpoRtrnDtlViewInfo", queryParam, msgArray);
    }

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getPriceInfo(Map<String, String> queryParam, NWAL1500_ISMsgArray msgArray) {
    public S21SsmEZDResult getPriceInfo(Map<String, Object> queryParam, NWAL1500_ISMsgArray msgArray) { // 2018/01/29 S21_NA#19808 Mod    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getPriceInfo", queryParam, msgArray);
    }

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getShippingPlanInfo(Map<String, String> queryParam, NWAL1500_SSMsgArray msgArray) {
    public S21SsmEZDResult getShippingPlanInfo(Map<String, Object> queryParam, NWAL1500_SSMsgArray msgArray) {
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getShippingPlanInfo", queryParam, msgArray);
    }

    // 2017/08/07 Sol#249 Mod Start
//    public S21SsmEZDResult getSalesCreditForHeader(Map<String, String> queryParam, NWAL1500_FCMsgArray msgArray) {
    public S21SsmEZDResult getSalesCreditForHeader(Map<String, Object> queryParam, NWAL1500_FCMsgArray msgArray) {
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getSalesCreditForHeader", queryParam, msgArray);
    }

    // 2017/08/07 Sol#249 Mod Start
    // For Performance QC#8166 Add Start
//    public S21SsmEZDResult getSalesCreditForConfig(Map<String, String> queryParam, NWAL1500_KCMsgArray msgArray) {
    public S21SsmEZDResult getSalesCreditForConfig(Map<String, Object> queryParam, NWAL1500_KSMsgArray msgArray) { // 2018/01/29 S21_NA#19808 Mod
    // 2017/08/07 Sol#249 Mod End
        return getSsmEZDClient().queryEZDMsgArray("getSalesCreditForConfig", queryParam, msgArray);
    }
    // For Performance QC#8166 Add End

    // For Performance QC#8166 Del Start
    // public S21SsmEZDResult getSalesCreditForConfigOut(Map<String, String> queryParam, NWAL1500_GCMsgArray msgArray) {
    //     return getSsmEZDClient().queryEZDMsgArray("getSalesCreditForConfigOut", queryParam, msgArray);
    // }

    // public S21SsmEZDResult getSalesCreditForConfigIn(Map<String, String> queryParam, NWAL1500_HCMsgArray msgArray) {
    //     return getSsmEZDClient().queryEZDMsgArray("getSalesCreditForConfigIn", queryParam, msgArray);
    // }
    // For Performance QC#8166 Del End

    public S21SsmEZDResult getOrderEntryEditFlag(NWAL1500CMsg bizMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSrcTpCd", bizMsg.cpoSrcTpCd.getValue());
        return getSsmEZDClient().queryObjectList("getOrderEntryEditFlag", params);
    }

    /**
     * Check Exist Order
     * @param bizMsg NWAL1500CMsg
     * @return result S21SsmEZDResult
     */
    public S21SsmEZDResult isExistOrder(NWAL1500CMsg bizMsg) {
        // QC#2176
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistOrder", params);

        return result;
    }
    // mod start 2016/07/29 CSA S21_NA#12607
    /**
     * Check Credit Rebill Order
     * @param bizMsg NWAL1500CMsg
     * @return result S21SsmEZDResult
     */
    public S21SsmEZDResult cntRebillOrder(NWAL1500CMsg bizMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("crRebilCdCredit", CR_REBIL.CREDIT);
        params.put("crRebilCdRebill", CR_REBIL.REBILL);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("cntCreditRebillOrder", params);

        return result;
    }
    // mod end 2016/07/29 CSA S21_NA#12607

    // START 2016/08/08 S21_NA#11592 Add
    /**
     * Get Order Line Val Set for WH textbox control
     * @param bizMsg NWAL1500CMsg
     * @return result S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdLineValSetInfo(String glblCmpyCd, NWAL1500_PCMsgArray msgArray) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordLineCtxTpCd", ORD_LINE_CTX_TP.FORCE_DUMMY_WH);

        return getSsmEZDClient().queryEZDMsgArray("getOrdLineValSetInfo", params, msgArray);

    }
    // END   2016/08/08 S21_NA#11592 Add

    // 2016/08/26 S21_NA#9806 Add Start
    /**
     * <pre>
     * get csmp info for out bound config.
     * @param bizMsg Business Message
     * @param configMsg config message for out bound
     * @return ssm object
     * </pre>
     */
    public S21SsmEZDResult getOutboundConfigCsmpInfo(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configMsg) { // 2018/01/29 S21_NA#19808 Mod

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", configMsg.dsOrdPosnNum_LC.getValue());

        return getSsmEZDClient().queryObject("getOutboundConfigCsmpInfo", params);
    }

    /**
     * <pre>
     * get csmp info for in bound config.
     * @param bizMsg bizMsg Business Message
     * @param configMsg configMsg config message for out bound
     * @return ssm object
     * </pre>
     */
    public S21SsmEZDResult getInboundConfigCsmpInfo(NWAL1500CMsg bizMsg, NWAL1500_CSMsg configMsg) { // 2018/01/25 S21_NA#19808 Mod

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", configMsg.dsOrdPosnNum_RC.getValue());

        return getSsmEZDClient().queryObject("getInboundConfigCsmpInfo", params);
    }
    // 2016/08/26 S21_NA#9806 Add End
    // 2019/08/01 S21_NA#52156 Add Start
    /**
     * <pre>
     * get line num approved PR
     * @param bizMsg bizMsg Business Message
     * @return ssm object
     * </pre>
     */
    public S21SsmEZDResult getApprovedPRLineNum(NWAL1500CMsg bizMsg) { // 2018/01/25 S21_NA#19808 Mod

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("shpgStsCd", SHPG_STS.VALIDATED);
        params.put("prchReqStsCancel", PRCH_REQ_STS.CANCELLED);
        params.put("prchReqLineStsCancel", PRCH_REQ_LINE_STS.CANCELLED);
        params.put("poStsCancel", PO_STS.CANCELLED);
        List<String> stsList = new ArrayList<String>();
        stsList.add(ORD_LINE_DPLY_STS.PENDING_ALLOCATION);
        stsList.add(ORD_LINE_DPLY_STS.BACK_ORDER);
        stsList.add(ORD_LINE_DPLY_STS.WAITING_PICK);
        stsList.add(ORD_LINE_DPLY_STS.WAITING_RECEIPT); //CD:200 AWAITING DROP SHIP

        params.put("stsList", stsList);

        return getSsmEZDClient().queryObjectList("getApprovedPRLineNum", params);
    }
    // 2019/08/01 S21_NA#52156 Add End
    // 2020/01/07 S21_NA#54996 Add Start
    /**
     * <pre>
     * get Open SO Count
     * @param bizMsg bizMsg Business Message
     * @return ssm object
     * </pre>
     */
    public S21SsmEZDResult getOpenSO(NWAL1500CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("trxHdrNum", bizMsg.cpoOrdNum.getValue());
        List<String> stsList = new ArrayList<String>();
        stsList.add(SHPG_STS.CANCELLED);
        stsList.add(SHPG_STS.S_OR_O_CANCELLED);
        stsList.add(SHPG_STS.P_OR_O_CANCELLED);
        params.put("stsList", stsList);

        return getSsmEZDClient().queryObject("getOpenSO", params);
    }
    // 2020/01/07 S21_NA#54996 Add End
}
