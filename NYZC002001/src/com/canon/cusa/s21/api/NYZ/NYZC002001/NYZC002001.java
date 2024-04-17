package com.canon.cusa.s21.api.NYZ.NYZC002001;

import parts.common.EZDDebugOutput;

import business.parts.NYZC002001PMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * download file to temporary folder on AP/Batch server
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu            T.Tsuji         Create          N/A
 * </pre>
 */
public class NYZC002001 extends S21ApiCommonBase {
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NYZC002001() {
        super();
    }

    /**
     * download file to temporary folder
     * @param pmsg NYZC001001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return pmsg NYZC001001PMsg
     */
    public NYZC002001PMsg execute(final NYZC002001PMsg pmsg, final ONBATCH_TYPE onBatchType){
        EZDDebugOutput.println(1, "NYZC002001 [ execute ] start", this);
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);

        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setAttDataPk(pmsg.attDataPk.getValueInt());
        pmsg.xxFilePathTxt.setValue(ZYPFileUpDown.downloadFile(params));

        msgMap.flush();
        EZDDebugOutput.println(1, "NYZC002001 [ execute ] end", this);
        return pmsg;
    }

}
