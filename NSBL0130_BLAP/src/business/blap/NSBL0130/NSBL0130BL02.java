/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0130;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0130.constant.NSBL0130Constant.NZZM0000E;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.db.SVC_TASKTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Hold Detail Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/07   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSBL0130BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0130_INIT".equals(screenAplID)) {
                doProcess_NSBL0130Scrn00_init((NSBL0130CMsg) cMsg, (NSBL0130SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0130Scrn00_init(NSBL0130CMsg cMsg, NSBL0130SMsg sMsg) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        //MOD START 11/19/2015 for CSA
        //setValue(inMsg.svcTaskNum, cMsg.svcTaskNum);
        //SVC_TASKTMsg outMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKey(inMsg);
        //if (outMsg == null) {
        //    cMsg.setMessageInfo(NZZM0000E);
        //    return;
        //}
        //setValue(cMsg.serNum, outMsg.serNum);
        //setValue(cMsg.serNum, outMsg.serNum);
        setValue(inMsg.fsrNum, cMsg.fsrNum);
        //MOD END 11/19/2015 for CSA

        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put("glblCmpyCd", getGlobalCompanyCode());
        //MOD START 11/19/2015 for CSA
        //sc.put("svcTaskNum", cMsg.svcTaskNum.getValue());
        sc.put("fsrNum", cMsg.fsrNum.getValue());
        //MOD END 11/19/2015 for CSA
        S21SsmEZDResult ssmResult = NSBL0130Query.getInstance().searchList(sc, sMsg);
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }
        //ADD START 11/19/2015 for CSA
        setValue(cMsg.serNum, sMsg.A.no(0).serNum);
        setValue(cMsg.custMachCtrlNum, sMsg.A.no(0).custMachCtrlNum);
        //ADD END 11/19/2015 for CSA
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
    }
}
