/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0320;

import static business.servlet.NSBL0320.constant.NSBL0320Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0320.NSBL0320CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_CRIT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 * 2016/03/25   Hitachi         K.Yamada        Update          CSA QC#6026
 *</pre>
 */
public class NSBL0320Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);

        if (selectedList == null || selectedList.size() < 1) {
            scrnMsg.setMessageInfo("NSAM0015E");
            throw new EZDFieldErrorException();
        }

        Object[] params = new Object[NSBL0330_PRM_LENGTH];
        int i = 0;

        /** Parameter[0] : ORG_CD */
        params[i++] = scrnMsg.orgCd;

        /** Parameter[1] : ORG_LAYER_NUM */
        params[i++] = scrnMsg.orgLayerNum;

        /** Parameter[2] : SVC_MGR_TP_CD */
        params[i++] = scrnMsg.svcMgrTpCd;

        /** Parameter[3] : SVC_RQST_DOWN_TP_CD */
        params[i++] = scrnMsg.svcRqstDownTpCd;

        /** Parameter[4] : SVC_MGR_PSN_CD */
        params[i++] = scrnMsg.svcMgrPsnCd;

        /** Parameter[5] : SVC_RQST_CRIT_TP_CD */
        params[i++] = scrnMsg.svcRqstCritTpCd;

        /** Parameter[6] : DS_SVC_CALL_TP_CD or FSR_SVC_TASK_STS_RELN_PK or TECH_TOC_CD or SVC_CALL_SRC_TP_CD */
        if (selectedList.size() > 0) {
            scrnMsg.I.setValidCount(selectedList.size());
            int j = 0;
            for (Integer target : selectedList) {
                if (scrnMsg.svcRqstCritTpCd.getValue().equals(SVC_RQST_CRIT_TP.TASK_TYPES)) {
                    setValue(scrnMsg.I.no(j).dsSvcCallTpCd_I, scrnMsg.A.no(target).xxDtlCd);
                } else if (scrnMsg.svcRqstCritTpCd.getValue().equals(SVC_RQST_CRIT_TP.SR_OR_TASK_STATUS)) {
                    // mod start 2016/03/25 CSA Defect#6026
                    String selectedItem = scrnMsg.A.no(target).xxDtlCd.getValue();
                    setValue(scrnMsg.I.no(j).fsrSvcTaskStsRelnPk_I , new BigDecimal(selectedItem));
                    // mod end 2016/03/25 CSA Defect#6026
                } else if (scrnMsg.svcRqstCritTpCd.getValue().equals(SVC_RQST_CRIT_TP.ASSIGNEE)) {
                    setValue(scrnMsg.I.no(j).techCd_I, scrnMsg.A.no(target).xxDtlCd);
                } else if (scrnMsg.svcRqstCritTpCd.getValue().equals(SVC_RQST_CRIT_TP.CANNEL)) {
                    setValue(scrnMsg.I.no(j).svcCallSrcTpCd_I, scrnMsg.A.no(target).xxDtlCd);
                }
                j++;
            }
            params[i++] = scrnMsg.I;
        }
        setArgForSubScreen(params);
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0330);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]
    }
}
