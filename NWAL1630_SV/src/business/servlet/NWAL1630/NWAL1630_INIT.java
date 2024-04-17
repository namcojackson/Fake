/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import static business.servlet.NWAL1630.constant.NWAL1630Constant.BIZ_ID;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.NWAM0270E;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.BTN_PRICE;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.MODE_REFERENCE;


import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1630.NWAL1630CMsg;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1630_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/22   Fujitsu         M.Suzuki        Update          S21_NA#2140
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 * 2019/01/22   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        // 2016/02/22 S21_NA#2140 Mod Start ------------
        // 2017/09/22 QC#18859 Mod Start
//        if (params != null && params.length == 21) {
        // if (params != null && params.length == 22) {
        if (params != null && params.length == 17) { // S21_NA#29446 Mod 22→17
        // 2017/09/22 QC#18859 Mod End
        // 2016/02/22 S21_NA#2140 Mod End --------------
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordDt, (EZDBDateItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, (EZDBBigDecimalItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineSubNum, (EZDBBigDecimalItem) params[8]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, (EZDBStringItem) params[9]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, (EZDBStringItem) params[10]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpPrcListCd, (EZDBStringItem) params[11]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyAbuseNodePrflCd, (EZDBStringItem) params[12]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, (EZDBStringItem) params[13]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, (EZDBStringItem) params[14]);
            // 2019/01/22 S21_NA#29446 Del Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.dlrFleetNum, (EZDBStringItem) params[15]);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.splyCdTxt, (EZDBStringItem) params[16]);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.annTermCapNum_BW, (EZDBBigDecimalItem) params[17]);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.annTermCapNum_CL, (EZDBBigDecimalItem) params[18]);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.rntlTrmnDt, (EZDBDateItem) params[19]);
            // 2019/01/22 S21_NA#29446 Del End
            // 2016/02/22 S21_NA#2140 Add Start ------------
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[15]); // S21_NA#29446 Mod 20→15
            // 2016/02/22 S21_NA#2140 Add End --------------
            // 2017/09/22 QC#18859 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, (EZDBBigDecimalItem) params[16]); // S21_NA#29446 Mod 21→16
            // 2017/09/22 QC#18859 Add End
            NWAL1630CommonLogic.chkInputParam(scrnMsg);

            // Set Backup
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum_BK, scrnMsg.csmpContrNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum_BK, scrnMsg.dlrRefNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpPrcListCd_BK, scrnMsg.csmpPrcListCd);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.rntlTrmnDt_BK, scrnMsg.rntlTrmnDt); // S21_NA#29446 Del
            // 2017/09/22 QC#18859 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_BK, scrnMsg.dsContrNum);
            // 2017/09/22 QC#18859 Add End

            // Set Line Num
            String dsOrdPosnNum = scrnMsg.dsOrdPosnNum.getValue();
            BigDecimal dsCpoLineNum = scrnMsg.dsCpoLineNum.getValue();
            BigDecimal dsCpoLineSubNum = scrnMsg.dsCpoLineSubNum.getValue();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlLineNum, NWXC150001DsCheck.editDtlLineNum(dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum));

        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
        }

        // 2017/09/22 QC#18859 Mod Start
//        return null;
        NWAL1630CMsg bizMsg = new NWAL1630CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2017/09/22 QC#18859 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        // 2017/09/22 QC#18859 Add Start
        NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2017/09/22 QC#18859 Add End

        NWAL1630CommonLogic.initCmnBtnProp(this, scrnMsg);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            NWAL1630CommonLogic.protectCmnBtnProp(this, scrnMsg);
            scrnMsg.setInputProtected(true);
            scrnMsg.setFocusItem(scrnMsg.csmpContrNum);
            return;
        }
        // 2016/02/22 S21_NA#2140 Add Start -----------
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            protectAllInput(scrnMsg);
        }
        // 2016/02/22 S21_NA#2140 Add End  ------------

        scrnMsg.setFocusItem(scrnMsg.csmpContrNum);
    }

    // 2016/02/22 S21_NA#2140 Add Start ------------
    private void protectAllInput(NWAL1630BMsg scrnMsg) {
        scrnMsg.setInputProtected(true);
        this.setButtonEnabled(BTN_PRICE, false);
        this.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        // 2016/03/03 S21_NA#2140 Mod Start ------------
        //this.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
        // 2016/03/03 S21_NA#2140 Mod End   ------------
    }
    // 2016/02/22 S21_NA#2140 End Start ------------

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;

        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Configuration Number");
        scrnMsg.dsOrdCatgCd.setNameForMessage("Order Category Code");
        scrnMsg.dsOrdTpCd.setNameForMessage("Order Type Code");
        scrnMsg.lineBizTpCd.setNameForMessage("Line Business Type Code");
        scrnMsg.ordDt.setNameForMessage("Order Date");
        scrnMsg.dsCpoLineNum.setNameForMessage("Line Number");
        scrnMsg.dsCpoLineSubNum.setNameForMessage("Line Number");
        scrnMsg.csmpContrNum.setNameForMessage("CSMP#");
        scrnMsg.dlrRefNum.setNameForMessage("CSA Dealer Ref#");
        scrnMsg.csmpPrcListCd.setNameForMessage("CSMP Price List");
        // 2018/07/05 QC#25786 mod start
//        scrnMsg.splyAbuseNodePrflCd.setNameForMessage("Supply Abuse");
        scrnMsg.splyAbuseNodePrflCd.setNameForMessage("Supply Enforcement");
        // 2018/07/05 QC#25786 mod end
        scrnMsg.serNum.setNameForMessage("Serial #");
        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        // scrnMsg.dlrFleetNum.setNameForMessage("Fleet#"); // S21_NA#29446 Del
        scrnMsg.splyCdTxt.setNameForMessage("Capped Supllies");
        scrnMsg.annTermCapNum_BW.setNameForMessage("B/W Cap");
        scrnMsg.annTermCapNum_CL.setNameForMessage("Color Cap");
        scrnMsg.annTermCapNum_TT.setNameForMessage("Total Cap"); // S21_NA#29446 Add
        // scrnMsg.rntlTrmnDt.setNameForMessage("Rental Termination Date"); // S21_NA#29446 Del
        scrnMsg.sellToCustCd.setNameForMessage("Sell To Customer Code");
    }
}
