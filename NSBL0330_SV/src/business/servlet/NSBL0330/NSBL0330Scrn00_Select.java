/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0330;

import static business.servlet.NSBL0330.constant.NSBL0330Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 * 2016/10/18   Hitachi         A.Kohinata      Update          CSA QC#11030
 *</pre>
 */
public class NSBL0330Scrn00_Select extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0330BMsg scrnMsg = (NSBL0330BMsg) bMsg;
        int idx = getButtonSelectNumber();
        NSBL0330_ABMsg aBMsg = scrnMsg.A.no(idx);
        String sfx = UNDER_BAR.concat(scrnMsg.xxSfxKeyTxt.getValue());
        Object[] params = new Object[PARAM_LENGTH_NSBL0340];
        int i = 0;
        // mod start 2016/10/18 CSA Defect#11030
        params[i++] = scrnMsg.orgCd_I;
        params[i++] = scrnMsg.orgLayerNum_I;
        // mod end 2016/10/18 CSA Defect#11030
        params[i++] = scrnMsg.svcMgrTpCd_I;
        params[i++] = scrnMsg.svcRqstDownTpCd_I;
        params[i++] = aBMsg.svcMgrPsnCd;
        params[i++] = scrnMsg.svcRqstCritTpCd_I;
        if (scrnMsg.svcRqstCritTpCd_I.getValue().equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
            setValue(scrnMsg.dsSvcCallTpCd_O, aBMsg.getValueString(DS_SVC_CALL_TP_CD.concat(sfx), -1));
            params[PARAM_IDX_DS_SVC_CALL_TP_CD] = scrnMsg.dsSvcCallTpCd_O;
        } else if (scrnMsg.svcRqstCritTpCd_I.getValue().equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
            setValue(scrnMsg.fsrSvcTaskStsRelnPk_O, aBMsg.getValueBigDecimal(FSR_SVC_TASK_STS_RELN_PK.concat(sfx), -1));
            params[PARAM_IDX_FSR_SVC_TASK_STS_RELN_PK] = scrnMsg.fsrSvcTaskStsRelnPk_O;
        } else if (scrnMsg.svcRqstCritTpCd_I.getValue().equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
            setValue(scrnMsg.techCd_O, aBMsg.getValueString(TECH_CD.concat(sfx), -1));
            params[PARAM_IDX_TECH_CD] = scrnMsg.techCd_O;
        } else if (scrnMsg.svcRqstCritTpCd_I.getValue().equals(SVC_RQST_CRIT_TP.CANNEL)) {
            setValue(scrnMsg.svcCallSrcTpCd_O, aBMsg.getValueString(SVC_CALL_SRC_TP_CD.concat(sfx), -1));
            params[PARAM_IDX_SVC_CALL_SRC_TP_CD] = scrnMsg.svcCallSrcTpCd_O;
        }
        setArgForSubScreen(params);
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0340);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]
    }
}
