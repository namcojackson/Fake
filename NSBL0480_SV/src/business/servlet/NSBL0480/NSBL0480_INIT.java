/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0480;

import static business.servlet.NSBL0480.constant.NSBL0480Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0480.NSBL0480CMsg;
import business.servlet.NSBL0480.common.NSBL0480CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;

        NSBL0480CMsg bizMsg = NSBL0480CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;
        NSBL0480CMsg bizMsg = (NSBL0480CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0480CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;

        scrnMsg.techCd_A1.setNameForMessage("Resource");
        scrnMsg.fullPsnNm_A3.setNameForMessage("Resource Name");
        scrnMsg.psnTpDescTxt.setNameForMessage("Resource Type");
        scrnMsg.svcAccsPmitNum.setNameForMessage("Access Permits");
        scrnMsg.svcAccsPmitDescTxt.setNameForMessage("Access Permits Name");
        // START 2017/02/01 K.Kitachi [QC#16629, ADD]
        scrnMsg.svcPmitLvlTpCd.setNameForMessage("Access Permits Level Type");
        scrnMsg.xxScrItem30Txt.setNameForMessage("Access Permits Level Value");
        // END 2017/02/01 K.Kitachi [QC#16629, ADD]

        scrnMsg.xxRadioBtn_A.setNameForMessage("#");
        scrnMsg.xxRadioBtn_B.setNameForMessage("#");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSBL0480_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.svcAccsPmitNum_A.setNameForMessage("Access Permits");
            abMsg.svcAccsPmitDescTxt_A.setNameForMessage("Permit Description");
            // START 2017/02/01 K.Kitachi [QC#16629, ADD]
            abMsg.svcPmitLvlTpCd_A.setNameForMessage("Access Permits Level Type");
            abMsg.xxScrItem30Txt_A.setNameForMessage("Access Permits Level Value");
            // END 2017/02/01 K.Kitachi [QC#16629, ADD]
            abMsg.effFromDt_A.setNameForMessage("Effective From");
            abMsg.effThruDt_A.setNameForMessage("Effective Thru");
        }

        for (int j = 0; j < scrnMsg.B.length(); j++) {
            NSBL0480_BBMsg bbMsg = scrnMsg.B.no(j);
            bbMsg.techCd_B.setNameForMessage("Person Code");
            bbMsg.fullPsnNm_B.setNameForMessage("Person Name");
            bbMsg.effFromDt_B.setNameForMessage("Effective From");
            bbMsg.effThruDt_B.setNameForMessage("Effective Thru");
        }

    }
}
