/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.CONTEXT_PROCESS_DOCUMENT_ID;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.CONTEXT_PROCESS_WORK_FLOW_NM;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDAbendException;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1550.NWAL1550CMsg;

import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcessFactory;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1550Scrn00_MoveWin_ProcessStatus
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1550Scrn00_MoveWin_ProcessStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

        //TODO Move After WorkFlow Screen is Made
        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        scrnMsg.setMessageInfo("ZZXM0001E", new String[] {"This event is not available." });
        throw new EZDFieldErrorException();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        
        NWAL1550CMsg bizMsg = new NWAL1550CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        NWAL1550CMsg bizMsg  = (NWAL1550CMsg) cMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // TODO TBD:Parameter is undetermined
        // Run the WorkFlow call
        S21NwfProcessFactory procFactory = S21NwfUtilProcessFactory.getInstance();

        if (procFactory != null) {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            List<S21NwfProcess> processes;
            
            try {
                S21NwfContext context = factory.getContex();
                context.setProcessFactory(procFactory);
                processes = context.getProcess(CONTEXT_PROCESS_WORK_FLOW_NM, CONTEXT_PROCESS_DOCUMENT_ID);
            } catch (S21NwfSystemException e) {

                throw new EZDAbendException(e);
            }

            if ((processes != null) && (processes.size() > 0)) {

                S21NwfProcess process = processes.get(0);
                
                BigDecimal procId = process.getProcessId();
                Object[] params = new Object[1];
                params[0] = procId;
                setArgForSubScreen(params);
            }
        }
    }
}
