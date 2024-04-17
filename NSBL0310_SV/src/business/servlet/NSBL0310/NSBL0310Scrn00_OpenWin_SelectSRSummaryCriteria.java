/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310;

import static business.servlet.NSBL0310.constant.NSBL0310Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_DOWN_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 * 2016/04/01   Hitachi         K.Yamada        Update          CSA QC#5857
 * 2016/10/18   Hitachi         A.Kohinata      Update          CSA QC#11030
 * 2019/03/28   Hitachi         K.Kitamura      Update          CSA QC#30906
 *</pre>
 */
public class NSBL0310Scrn00_OpenWin_SelectSRSummaryCriteria extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        int idx = getButtonSelectNumber();

        Object[] params = new Object[NSBL0320_PRM_LENGTH];
        int i = 0;

        /** Parameter[0] : ORG_CD */
        // mod start 2016/10/18 CSA Defect#11030
//        if (idx >= 0) {
//            params[i++] = scrnMsg.A.no(idx).orgCd_A;
//        } else {
//            params[i++] = scrnMsg.orgCd_HL;
//        }
        params[i++] = scrnMsg.orgCd_HL;
        // mod end 2016/10/18 CSA Defect#11030

        /** Parameter[1] : ORG_LAYER_NUM */
        if (idx >= 0) {
            // mod start 2016/10/18 CSA Defect#11030
            params[i++] = scrnMsg.orgLayerNum;
            // mod end 2016/10/18 CSA Defect#11030
        } else {
            // mod start 2016/04/01 CSA Defect#5857
            params[i++] = null;
            // mod end 2016/04/01 CSA Defect#5857
        }

        /** Parameter[2] : SVC_MGR_TP_CD */
        params[i++] = scrnMsg.svcMgrTpCd_HD;

        /** Parameter[3] : SVC_RQST_DOWN_TP_CD */
        String svcRqstDownTpCd = convertToSvcRqstDownTp(scrnMsg.xxScrEventNm.getValue());
        setValue(scrnMsg.svcRqstDownTpCd, svcRqstDownTpCd);
        params[i++] = scrnMsg.svcRqstDownTpCd;

        /** Parameter[4] : SVC_MGR_PSN_CD */
        String svcMgrPsnCd = null;
        if (idx >= 0) {
            params[i++] = scrnMsg.A.no(idx).svcMgrPsnCd_A;
        } else {
            params[i++] = svcMgrPsnCd;
        }
        setArgForSubScreen(params);
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0320);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]
    }

    private String convertToSvcRqstDownTp(String colNm) {
        Map<String, String> convMap = new HashMap<String, String>();
        convMap.put(IN_PROC_COL_NM, SVC_RQST_DOWN_TP.IN_PROCESS);
        convMap.put(CLOSE_COL_NM, SVC_RQST_DOWN_TP.CLOSED);
        convMap.put(CANCEL_COL_NM, SVC_RQST_DOWN_TP.CANCEL);
        convMap.put(REJECT_COL_NM, SVC_RQST_DOWN_TP.AUTO_REJECT);
        convMap.put(RESP_COL_NM, SVC_RQST_DOWN_TP.RESPONSE_TIME);
        // START 2019/03/28 K.Kitamura [QC#30233, ADD]
        convMap.put(TBO_COL_NM, SVC_RQST_DOWN_TP.TBO);
        convMap.put(SCHD_COL_NM, SVC_RQST_DOWN_TP.SHEDULED);
        // END 2019/03/28 K.Kitamura [QC#30233, ADD]
        convMap.put(ASSIGN_COL_NM, SVC_RQST_DOWN_TP.ASSIGN);
        convMap.put(OPEN_COL_NM, SVC_RQST_DOWN_TP.OPEN);
        convMap.put(HOLD_COL_NM, SVC_RQST_DOWN_TP.ON_HOLD);
        convMap.put(ESCL_COL_NM, SVC_RQST_DOWN_TP.ESCALATION);
        convMap.put(VIP_COL_NM, SVC_RQST_DOWN_TP.VIP);
        convMap.put(ERR_COL_NM, SVC_RQST_DOWN_TP.ERROR_OR_NEED_ATTENTION);

        return convMap.get(colNm);
    }
}
