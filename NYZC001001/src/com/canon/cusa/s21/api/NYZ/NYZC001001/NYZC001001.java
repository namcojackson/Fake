package com.canon.cusa.s21.api.NYZ.NYZC001001;

import parts.common.EZDDebugOutput;
import business.parts.NYZC001001PMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * upload file from temporary folder on AP/Batch server
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu            T.Tsuji         Create          N/A
 * </pre>
 */
public class NYZC001001 extends S21ApiCommonBase {
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NYZC001001() {
        super();
    }

    /**
     * upload file from temporary folder on AP/Batch server
     * @param pmsg NYZC001001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return pmsg NYZC001001PMsg
     */
    public NYZC001001PMsg execute(final NYZC001001PMsg pmsg, final ONBATCH_TYPE onBatchType){
        EZDDebugOutput.println(1, "NYZC001001 [ execute ] start", this);
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
        int fileId;

        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setBusinessId(pmsg.ezBusinessID.getValue());
        params.setAttDataGrp(pmsg.attDataGrpTxt.getValue());
        params.setAttDataNm(pmsg.attDataNm.getValue());
        params.setAttDataSrcPath(pmsg.xxFilePathTxt.getValue());

        fileId = ZYPFileUpDown.uploadFile(params);

        pmsg.attDataPk.setValue(fileId);

        msgMap.flush();
        EZDDebugOutput.println(1, "NYZC001001 [ execute ] end", this);
        return pmsg;
    }
}
