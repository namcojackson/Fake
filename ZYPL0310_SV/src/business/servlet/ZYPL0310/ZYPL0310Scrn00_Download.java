/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZYPL0310;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.servlet.ZYPL0310.common.ZYPL0310CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZYPL0310Scrn00_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        scrnMsg.xxAttDataCmntTxt_O.clear();
        scrnMsg.xxCellIdx_DL.setValue(getButtonSelectNumber());

        return ZYPL0310CommonLogic.createCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;
        ZYPL0310CMsg bizMsg = (ZYPL0310CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPL0310CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0310CommonLogic.setAlternateRowsBG(ZYPL0310Constant.MY_SCRN_ID, ZYPL0310Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(ZYPL0310Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if ("E".equals(bizMsg.getMessageKind())) {

            scrnMsg.setFocusItem(scrnMsg.xxFileData);

        } else {

            scrnMsg.xxAttDataCmntTxt_O.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxAttDataCmntTxt.getValue());

            String tempFilePath = scrnMsg.xxFileData_DL.getTempFilePath();
         // [START] -MOD- Change to use Therefore Desktop client viewer 2018.06.01
            if (scrnMsg.A.no(getButtonSelectNumber()).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)) {
                executeDownload(tempFilePath, true, ZYPFileNameUtil.getFileNm(tempFilePath).toString());
            } else {
            	executeDownload(tempFilePath, true, scrnMsg.A.no(getButtonSelectNumber()).attDataNm.getValue());
            }
         // [START] -MOD- Change to use Therefore Desktop client viewer 2018.06.01
        }
    }

}
