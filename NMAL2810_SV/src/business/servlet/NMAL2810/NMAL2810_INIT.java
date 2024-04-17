/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2810;

import static business.servlet.NMAL2810.constant.NMAL2810Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2810.NMAL2810CMsg;
import business.servlet.NMAL2810.common.NMAL2810CommonLogic;
import business.servlet.NMAL2810.constant.NMAL2810Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2810_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 * 2016/09/16   SRAA            Y.Chen          Update          QC#14223
 *</pre>
 */
public class NMAL2810_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;
        NMAL2810CMsg bizMsg = new NMAL2810CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        NMAL2810CommonLogic.setInParams(scrnMsg, params);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;
        NMAL2810CMsg bizMsg = (NMAL2810CMsg) cMsg;

        NMAL2810CommonLogic.initCmnBtnProp(this);

// QC#14223
//        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            return;
//        }
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        NMAL2810CommonLogic.setRadioFields(scrnMsg, NMAL2810Constant.RADIO_MRG_TO);

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            NMAL2810CommonLogic.setRadioFields(scrnMsg, NMAL2810Constant.RADIO_PROS);
        } else {
            NMAL2810CommonLogic.setRadioFields(scrnMsg, NMAL2810Constant.RADIO_MRG_TO);
        }
        NMAL2810CommonLogic.controlScreenFields(this, scrnMsg);
        NMAL2810CommonLogic.setInitBgColor(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;

        // Hidden
        scrnMsg.xxModeCd.setNameForMessage("Mode");

        // Header(Label)
        scrnMsg.rvwProsNum_L.setNameForMessage("PROSPECT");
        scrnMsg.locNum_R.setNameForMessage("MERGE TO LOCATION ID");

        // Detail(Left Label)
        scrnMsg.firstLineAddr_L.setNameForMessage("Address 1");
        scrnMsg.scdLineAddr_L.setNameForMessage("Address 2");
        scrnMsg.thirdLineAddr_L.setNameForMessage("Address 3");
        scrnMsg.frthLineAddr_L.setNameForMessage("Address 4");
        scrnMsg.ctyAddr_L.setNameForMessage("City");
        scrnMsg.stCd_L.setNameForMessage("State");
        scrnMsg.postCd_L.setNameForMessage("Postal Code");
        scrnMsg.telNum_L.setNameForMessage("Phone");
        scrnMsg.dsAcctDunsNm_L.setNameForMessage("DNB Name");
        scrnMsg.dunsNum_L.setNameForMessage("DUNS Number");
        scrnMsg.dsUltDunsNum_L.setNameForMessage("Ultimate DUNS Number");
        scrnMsg.dsCustSicCd_L.setNameForMessage("SIC Code");
        scrnMsg.dsCustSicDescTxt_L.setNameForMessage("Line Of Business");
        scrnMsg.dsLocRevAmt_L.setNameForMessage("Annual Revenue");
        scrnMsg.dsLocEmpNum_L.setNameForMessage("# of Employees");
        scrnMsg.glnNum_L.setNameForMessage("GLN");
        scrnMsg.dsPrntDunsNum_L.setNameForMessage("Parent DUNS Number");
        scrnMsg.hqDunsNum_L.setNameForMessage("HQ DUNS Number");
        scrnMsg.dsCustSicCd_LC.setNameForMessage("Company SIC");
        scrnMsg.dsCustSicDescTxt_LC.setNameForMessage("Company SIC Description");

    }
}
