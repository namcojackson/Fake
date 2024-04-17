package com.canon.cusa.s21.api.NYZ.NYZC003001;

import parts.common.EZDDebugOutput;
import business.parts.NYZC003001PMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Detach file description from attachment data Table
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/10   Fujitsu         M.Yaguchi       Create          N/A
 * </pre>
 */
public class NYZC003001 extends S21ApiCommonBase {
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NYZC003001() {
        super();
    }

    /**
     * Remove file description on ATT_DATA Table
     * @param pmsg NYZC003001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return pmsg NYZC003001PMsg
     */
    public NYZC003001PMsg execute(final NYZC003001PMsg pmsg, final ONBATCH_TYPE onBatchType){
        EZDDebugOutput.println(1, "NYZC003001 [ execute ] start", this);
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);

        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setAttDataPk(pmsg.attDataPk.getValueInt());
        pmsg.attDataNm.setValue(ZYPFileUpDown.detachFile(params));

        msgMap.flush();
        EZDDebugOutput.println(1, "NYZC003001 [ execute ] end", this);
        return pmsg;
    }
}
