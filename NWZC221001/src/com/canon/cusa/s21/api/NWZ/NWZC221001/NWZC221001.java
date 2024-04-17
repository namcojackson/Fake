package com.canon.cusa.s21.api.NWZ.NWZC221001;

import static com.canon.cusa.s21.api.NWZ.NWZC221001.constant.NWZC221001Constant.RETURN_CD_NORMAL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.List;
import business.db.UPD_ORD_RPSTTMsg;
import business.parts.NWZC221001PMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * Order Interface Processed Status Update API
 * (Web Service for Order Tracking)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   CSA             K.Aratani       Create          N/A
 *</pre>
 */

public class NWZC221001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /**
     * Constructor
     */
    public NWZC221001() {
        super();
    }

    /**
     * execute
     * @param pMsg NWZC221001PMsg
     * @param type ONBATCH_TYPE
     */
    public void execute(final NWZC221001PMsg pMsg, final ONBATCH_TYPE type) {
        this.msgMap = new S21ApiMessageMap(pMsg);

        doProcess(pMsg);
        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC221001PMsg>
     * @param type ONBATCH_TYPE
     */
    public void execute(final List<NWZC221001PMsg> params, final ONBATCH_TYPE type) {
        for (NWZC221001PMsg param : params) {
            execute(param, type);
        }
    }

    /**
     * doProcess
     * @param msgMap NWZC221001PMsg
     */
    private void doProcess(NWZC221001PMsg pMsg) {

        // Parameter Check
        if (!checkInputParameter(pMsg)) {
            return;
        }

        // update
        update(pMsg);
        
    }

    /**
     * checkInputParameter
     * @param pMsg NWZC221001PMsg
     * @return boolean
     */
    private boolean checkInputParameter(NWZC221001PMsg pMsg) {
        // NWZM0473E=0,"Global Company Code" is not set.
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId("NWZM0473E");
        }
        // NWZM1913E=0,"Upload Order Repository PK" is not set.
        if (!ZYPCommonFunc.hasValue(pMsg.updOrdRpstPk)) {
            msgMap.addXxMsgId("NWZM1913E");
        }
        
        return !isError(pMsg);
    }

    /**
     * registDsCpoDelyInfo
     * @param pMsg NWZC221001PMsg
     * @return boolean
     */
    private boolean update(NWZC221001PMsg pMsg) {
    	
        //Exists Check
        UPD_ORD_RPSTTMsg tmsg = new UPD_ORD_RPSTTMsg();
        tmsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        tmsg.updOrdRpstPk.setValue(pMsg.updOrdRpstPk.getValue());
        tmsg = (UPD_ORD_RPSTTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        if (tmsg == null) {
        	//NWZM1914E=0,"Upload Order Repository" does not exists in Upload Order Repository Table.
            msgMap.addXxMsgId("NWZM1914E");
            return false;
        }
        tmsg.procFlg.setValue(FLG_ON_Y);
        S21FastTBLAccessor.update(tmsg);
        if (!RETURN_CD_NORMAL.equals(tmsg.getReturnCode())) {
        	//NWZM1915E=0,Update process has been failed in Upload Order Repository Table.
            msgMap.addXxMsgId("NWZM1915E");
            return false;
        }
        
        return true;
    }
    /**
     * isError
     * @param pMsg NWZC22100101PMsg
     * @return boolean
     */
    private boolean isError(NWZC221001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }
}
