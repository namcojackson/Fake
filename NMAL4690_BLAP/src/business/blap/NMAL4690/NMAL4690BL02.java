/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4690;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL4690.common.NMAL4690CommonLogic;
import business.blap.NMAL4690.constant.NMAL4690Constant;
import business.db.MSTR_ATT_DATATMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NMAL4690BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NMAL4690BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NMAL4690BL02 extends S21BusinessHandler implements NMAL4690Constant {

    /** Singleton instance. */
    private NMAL4690CommonLogic common = new NMAL4690CommonLogic();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            // NMAL4690Scrn00_AddRow
            if ("NMAL4690_INIT".equals(screenAplID)) {
                doProcess_NMAL4690_INIT(cMsg, sMsg);
            } else if ("NMAL4690Scrn00_SelectBtn".equals(screenAplID)) {
                doProcess_NMAL4690Scrn00_SelectBtn(cMsg, sMsg);
            } else if ("NMAL4690Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL4690Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NMAL4690Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL4690Scrn00_PageNext(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL4690Scrn00_SelectBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL4690Scrn00_SelectBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NMAL4690Scrn00_SelectBtn================================START", this);

        NMAL4690CMsg bizMsg = (NMAL4690CMsg) cMsg;
        downloadFile(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NMAL4690Scrn00_SelectBtn================================END", this);
    }

    /**
     * business logic to [Download attachment file] on NWAL0370Scrn00.
     * @param bizMsg NMAL4690CMsg
     */
    private void downloadFile(NMAL4690CMsg bizMsg) {

        BigDecimal mstrAttDataPk = bizMsg.mstrAttDataPk.getValue();

        MSTR_ATT_DATATMsg tMsg = new MSTR_ATT_DATATMsg();
        tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsg.mstrAttDataPk.setValue(mstrAttDataPk);

        tMsg = (MSTR_ATT_DATATMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.setMessageInfo(MSG_ID_DOWNLOADFILE_NOT_FOUND);
            return;
        } else {

            ZYPBLOBAccessor blobAccessor = new ZYPBLOBAccessor(tMsg);
            byte[] downloadData = blobAccessor.findBLOB("MSTR_ATT_DATA_BLOD");

            // No data to download.
            if (downloadData == null) {
                bizMsg.setMessageInfo(MSG_ID_DOWNLOADFILE_NOT_FOUND);
                return;
            } else {
                bizMsg.xxFileData.setTempFilePath(null, tMsg.mstrAttDataNm.getValue(), "");
                ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), downloadData);
            }
        }
    }

    /**
     * Method name: doProcess_NMAL4690_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL4690_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NMAL4690_INIT================================START", this);

        NMAL4690SMsg globalMsg = (NMAL4690SMsg) sMsg;

        common.getRecord(cMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NMAL4690_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NMAL4690Scrn00_PagePrev
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL4690Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NMAL4690_Prev================================START", this);

        NMAL4690CMsg bizMsg = (NMAL4690CMsg) cMsg;
        NMAL4690SMsg globalMsg = (NMAL4690SMsg) sMsg;

        // copy data from SMsg onto CMsg
        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(5, "doProcess_NMAL4690_Prev================================END", this);
    }

    /**
     * Method name: doProcess_NMAL4690Scrn00_PageNext
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL4690Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NMAL4690_Next================================START", this);

        NMAL4690CMsg bizMsg = (NMAL4690CMsg) cMsg;
        NMAL4690SMsg globalMsg = (NMAL4690SMsg) sMsg;

        // copy data from SMsg onto CMsg
        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "doProcess_NMAL4690_Next================================END", this);
    }
}
