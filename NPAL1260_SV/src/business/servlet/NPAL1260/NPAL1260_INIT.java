/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1260.NPAL1260CMsg;
import business.servlet.NPAL1260.common.NPAL1260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura       Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 03/17/2020   Fujitsu         R.Nakamura      Update          QC#56104
 *</pre>
 */
public class NPAL1260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        NPAL1260CMsg bizMsg = new NPAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        NPAL1260CMsg bizMsg = (NPAL1260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);

        NPAL1260CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        // Search Options
        scrnMsg.srchOptNm_TX.setNameForMessage("Search Option Name");

        // Search Condition
        // Tech Request#
        scrnMsg.prchReqNum_H1.setNameForMessage("Tech Request#");
        // Tech Request Type
        scrnMsg.prchReqTpCd_SE.setNameForMessage("Tech Request Type");
        // Item Number
        scrnMsg.mdseCd_H1.setNameForMessage("Item Number");
        // Line Type
        scrnMsg.prchReqLineTpCd_SE.setNameForMessage("Line Type");
        // Line Status
        scrnMsg.prchReqLineStsCd_SE.setNameForMessage("Line Status");
        // Date Created From/To
        scrnMsg.prchReqCratDt_HF.setNameForMessage("Date Created From");
        scrnMsg.prchReqCratDt_HT.setNameForMessage("Date Created To");
        // Date Needed From/To
        scrnMsg.rqstRcvDt_HF.setNameForMessage("Date Needed From");
        scrnMsg.rqstRcvDt_HT.setNameForMessage("Date Needed To");
        // Ship Date From/To
        scrnMsg.shipDt_HF.setNameForMessage("Ship Date From");
        scrnMsg.shipDt_HT.setNameForMessage("Ship Date To");
        // Tech Territory
        scrnMsg.lineBizTpCd_SE.setNameForMessage("Tech Territory");
        // Document Source Type
        scrnMsg.prchReqSrcTpCd_SE.setNameForMessage("Document Source Type");
        // Service Request#
        scrnMsg.fsrNum_H1.setNameForMessage("Service Request#");
        // Service Request Task#
        scrnMsg.svcTaskNum_H1.setNameForMessage("Service Request Task#");
        // Service Request Serial#
        scrnMsg.svcMachSerNum_H1.setNameForMessage("Service Request Serial#");
        // Purchase Order#
        scrnMsg.poOrdNum_H1.setNameForMessage("Purchase Order#");
        // Shipping Order#
        // Mod Start 2020/03/17 QC#56104
//        scrnMsg.soNum_H1.setNameForMessage("Shipping Order#");
        scrnMsg.rwsRefNum_H1.setNameForMessage("Shipping Order#");
        // Mod End 2020/03/17 QC#56104
        // Requisition#
        scrnMsg.prchReqNum_H2.setNameForMessage("Requisition#");
        // Source WH / SWH
        scrnMsg.rtlWhCd_H1.setNameForMessage("Source WH / SWH");
        scrnMsg.rtlSwhCd_H1.setNameForMessage("Source WH / SWH");
        // PO Supplier / Site Name
        scrnMsg.prntVndNm_H1.setNameForMessage("PO Supplier / Site Name");
        scrnMsg.locNm_H1.setNameForMessage("PO Supplier / Site Name");
        // Destination WH / SWH
        scrnMsg.destRtlWhCd_H1.setNameForMessage("Destination WH / SWH");
        scrnMsg.destRtlSwhCd_H1.setNameForMessage("Destination WH / SWH");
        // Technician Name
        scrnMsg.techNm_H1.setNameForMessage("Technician Name");
        // Customer Name
        scrnMsg.dsAcctNm_H1.setNameForMessage("Customer Name");
        // Requested Carrier
        scrnMsg.carrNm_H1.setNameForMessage("Requested Carrier");
        // Actual Service Level
        scrnMsg.shpgSvcLvlCd_SE.setNameForMessage("Actual Service Level");
        // Tracking Number
        scrnMsg.proNum_H1.setNameForMessage("Tracking Number");
        // Shipment Line View
        scrnMsg.xxChkBox_H1.setNameForMessage("Shipment Line View");
        // Freeze Flag
        scrnMsg.xxChkBox_H2.setNameForMessage("Freeze Flag");
        // Insourced Flag
        scrnMsg.xxChkBox_H3.setNameForMessage("Insourced Flag");
        // Purchased Flag
        scrnMsg.xxChkBox_H4.setNameForMessage("Purchased Flag");
    }
}
