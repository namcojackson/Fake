/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_0;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_1;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_3;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import business.blap.NWAL1570.NWAL1570CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/09/27   Fujitsu         K.Sato          Update          QC#13415
 *</pre>
 */
public class NWAL1570_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_SalesRep".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_SubWH".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_Model".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_AssnProgram".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        // QC#13415 ADD Start
        } else if ("OpenWin_POVendor".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        // QC#13415 ADD End
        } else if ("OpenWin_Team".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_PLGroup".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_PL1".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_PL2".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_PL3".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_PL4".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        }

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg  = (NWAL1570CMsg) cMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_SalesRep".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxSlsRepTocSrchTxt);
        } else if ("OpenWin_SubWH".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxRtlSwhSrchTxt);
        } else if ("OpenWin_Model".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxMdlSrchTxt);
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxCsmpContrNumSrchTxt);
        } else if ("OpenWin_AssnProgram".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxPrcContrNumSrchTxt);
        }
    }
}
