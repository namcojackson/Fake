/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500;

import java.util.HashMap;
import java.util.Map;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/07/22   Fujitsu         M.Hara          Update          S21_NA#8412
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2017/07/21   Fujitsu         S.Takami        Update          S21_NA#19847
 * 2018/07/17   Fujitsu         A.Kosai         Update          S21_NA#27073
 * </pre>
 */
public final class NWAL1500QueryForAddlHeader extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1500QueryForAddlHeader MY_INSTANCE = new NWAL1500QueryForAddlHeader();

    /**
     * Constructor.
     */
    private NWAL1500QueryForAddlHeader() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForAddlHeader getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Freight Term Information List
     * @param bizMsg NWAL1500CMsg
     * @return Freight Term Information List
     */
    public S21SsmEZDResult getFreightTermInfoList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("frtCondDescTxt", bizMsg.frtCondDescTxt.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getFreightTermInfoList", params);
    }

    /**
     * get Payment Term Information List
     * @param bizMsg NWAL1500CMsg
     * @return Payment Term Information List
     */
    public S21SsmEZDResult getPaymentTermInfoList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("pmtTermCashDiscDescTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());

        return getSsmEZDClient().queryObjectList("getPaymentTermInfoList", params);
    }

    /**
     * get Carrier Service Level Information List
     * @param bizMsg NWAL1500CMsg
     * @return Carrier Service Level Information List
     */
    public S21SsmEZDResult getCarrSvcLvlInfoList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("frtCondCd", bizMsg.frtCondCd.getValue());
        params.put("carrSvcLvlDescTxt", bizMsg.carrSvcLvlDescTxt.getValue());
        params.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());

        return getSsmEZDClient().queryObjectList("getCarrSvcLvlInfoList", params);
    }

    /**
     * get CSMP Contract Information List
     * @param bizMsg NWAL1500CMsg
     * @param isReqSettingParam is Required Setting Parameter
     * @param isCallCsmpNum Called CSMP Number Field
     * @return CSMP Contract Information List
     */
    public S21SsmEZDResult getCsmpContrInfoList(NWAL1500CMsg bizMsg, boolean isReqSettingParam, boolean isCallCsmpNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("sellToCustCd", bizMsg.sellToCustCd.getValue());
        params.put("csmpContrActvFlg", ZYPConstant.FLG_ON_Y);

        // 2016/08/26 S21_NA#9806 Add Start
        int cellIdx = bizMsg.xxCellIdx.getValueInt();
        String csmpContrNum = null;
        String dlrRefNum = null;
        if (cellIdx < 0) {
            csmpContrNum = bizMsg.csmpContrNum.getValue();
            dlrRefNum = bizMsg.dlrRefNum.getValue();
        } else {
            String dplyTab = bizMsg.xxDplyTab.getValue();
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                csmpContrNum = bizMsg.A.no(cellIdx).csmpContrNum_LC.getValue();
                dlrRefNum = bizMsg.A.no(cellIdx).dlrRefNum_LC.getValue();
            } else if (TAB_RMA.equals(dplyTab)) {
                csmpContrNum = bizMsg.C.no(cellIdx).csmpContrNum_RC.getValue();
                dlrRefNum = bizMsg.C.no(cellIdx).dlrRefNum_RC.getValue();
            }
        }
        // 2016/08/26 S21_NA#9806 Add End
        if (isReqSettingParam) {
            if (isCallCsmpNum) {
//                params.put("csmpContrNum", bizMsg.csmpContrNum.getValue()); 2016/08/26 S21_NA#9806 Del
                params.put("csmpContrNum", csmpContrNum); // 2016/08/26 S21_NA#9806 Add
            } else {
//                params.put("dlrRefNum", bizMsg.dlrRefNum.getValue()); 2016/08/26 S21_NA#9806 Del
                params.put("dlrRefNum", dlrRefNum); // 2016/08/26 S21_NA#9806 Add
            }
        }

        return getSsmEZDClient().queryObjectList("getCsmpContrInfoList", params);
    }

    /**
     * get Price Contract Information List
     * @param bizMsg NWAL1500CMsg
     * @param isCallAssnProgramNm Called Association Program Name Field
     * @return Price Contract Information List
     */
    // 2018/07/17 S21_NA#27073 Mod Start
//    public S21SsmEZDResult getPrcContrInfoList(NWAL1500CMsg bizMsg, boolean isCallAssnProgramNm) {
    public S21SsmEZDResult getCustPrcContrInfoList(NWAL1500CMsg bizMsg, boolean isCallAssnProgramNm) {
    // 2018/07/17 S21_NA#27073 Mod Start

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // S21_NA#8412
        params.put("dsAcctNum", bizMsg.sellToCustCd.getValue());

        if (isCallAssnProgramNm) {
            params.put("prcContrNm", bizMsg.prcContrNm.getValue());
        } else {
            params.put("prcContrNum", bizMsg.prcContrNum.getValue());
        }

        // 2018/07/17 S21_NA#27073 Mod Start
//        return getSsmEZDClient().queryObjectList("getPrcContrInfoList", params);
        return getSsmEZDClient().queryObjectList("getCustPrcContrInfoList", params);
        // 2018/07/17 S21_NA#27073 Mod End
    }

    // 2017/07/21 S21_NA#19847 Add Start
    /**
     * get Association Program Info
     * @param bizMsg NWAL1500 Business Message
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcContrInfoListByPrcContr(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("sellToCustCd", bizMsg.sellToCustCd.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum)) {
            params.put("csmpContrNum", bizMsg.csmpContrNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dlrRefNum)) {
            params.put("dlrRefNum", bizMsg.dlrRefNum.getValue());
        }

        return getSsmEZDClient().queryObjectList("getPrcContrInfoListByPrcContr", params);
    }
    // 2017/07/21 S21_NA#19847 Add End

    // 2018/07/17 S21_NA#27073 Add Start
    public S21SsmEZDResult getPrcContrInfoList(NWAL1500CMsg bizMsg, boolean isCallAssnProgramNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);

        if (isCallAssnProgramNm) {
            params.put("prcContrNm", bizMsg.prcContrNm.getValue());
        } else {
            params.put("prcContrNum", bizMsg.prcContrNum.getValue());
        }

        return getSsmEZDClient().queryObjectList("getPrcContrInfoList", params);
    }
    // 2018/07/17 S21_NA#27073 Add End
}
