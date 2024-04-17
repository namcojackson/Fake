/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2410.NWAL2410CMsg;
import business.servlet.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2410_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = new NWAL2410CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.docMgtOrgCd);
        NWAL2410CommonLogic.initCmnBtnProp(this);
        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;

        // Header
        scrnMsg.docMgtOrgCd.setNameForMessage("LOB");
        scrnMsg.docMgtScanBrCd.setNameForMessage("Branch Code");
        scrnMsg.docMgtScanBrNm.setNameForMessage("Branch Name");
        scrnMsg.docMgtScanRgNm.setNameForMessage("Region");
        scrnMsg.docMgtScanZnNm.setNameForMessage("Zone");
        scrnMsg.defOrdProcPsnCd.setNameForMessage("Order Processor");
        scrnMsg.defBrAdminPsnCd.setNameForMessage("Branch Admin");
        scrnMsg.leaseCmpyProcPsnCd.setNameForMessage("CFS Processor");
        scrnMsg.actvFlg.setNameForMessage("Active Only");

        // Details
        scrnMsg.A.setNameForMessage("xxChkBox_A", "Subsc");
        scrnMsg.A.setNameForMessage("docMgtOrgCd_A", "LOB");
        scrnMsg.A.setNameForMessage("docMgtScanBrCd_A", "Branch Code");
        scrnMsg.A.setNameForMessage("docMgtScanBrNm_A", "Branch");
        scrnMsg.A.setNameForMessage("docMgtScanRgNm_A", "Region");
        scrnMsg.A.setNameForMessage("docMgtScanZnNm_A", "Zone");
        scrnMsg.A.setNameForMessage("defOrdProcPsnCd_A", "Default Order Processor");
        scrnMsg.A.setNameForMessage("defBrAdminPsnCd_A", "Default Branch Admin");
        scrnMsg.A.setNameForMessage("leaseCmpyProcPsnCd_A", "CFS Processor");
        scrnMsg.A.setNameForMessage("poPendEmlAddr_A", "Notify Email For PO Pend");
        scrnMsg.A.setNameForMessage("poIssEmlAddr_A", "Notify Email For PO Issue");
        scrnMsg.A.setNameForMessage("invPkgPendEmlAddr_A", "Notify Email For Invoice Package Pend");
        scrnMsg.A.setNameForMessage("actvFlg_A", "Active");

    }

}
