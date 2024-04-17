/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.INOUT_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MODE_REFERENCE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0270E;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0674E;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0675E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/12   Fujitsu         Y.Murai         Update          S21_NA#7861
 * 2017/03/02   Fujitsu         Y.Kanefusa      Update          S21_NA#13584
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1600_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg, ZYPConstant.FLG_OFF_N);

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && (params.length == 5 || params.length == 6)) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            EZDBStringItem param02 = (EZDBStringItem) params[1];
            EZDBStringItem param03 = (EZDBStringItem) params[2];
            EZDBStringItem param04 = (EZDBStringItem) params[3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, param01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, param02);
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, param03);
            String dsOrdPosnNumTxt = param03.getValue();
            List<String> dsOrdPosnNumList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(dsOrdPosnNumTxt)) {
                dsOrdPosnNumList = Arrays.asList(dsOrdPosnNumTxt.split(","));
            }
            if (dsOrdPosnNumList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, dsOrdPosnNumList.get(0));
            } else {
                scrnMsg.dsOrdPosnNum.clear();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt, dsOrdPosnNumTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCnt, BigDecimal.valueOf(dsOrdPosnNumList.size()));
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSfxKeyTxt, param04);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, scrnMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_BK, scrnMsg.dsOrdPosnNum);
            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_BK, scrnMsg.xxComnScrColValTxt);
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

            // Sales Rep List
            if ((EZDMsgArray) params[4] != null) {
                EZDMsgArray details = (EZDMsgArray) params[4];
                EZDMsg.copy(details, scrnMsg.xxSfxKeyTxt.getValue(), scrnMsg.O, INOUT_SFX);
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
            scrnMsg.setMessageInfo(NWAM0674E);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            return null;
        } else if (!NWAL1600CommonLogic.checkMandantory(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0675E);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        NWAL1600CMsg bizMsg = new NWAL1600CMsg();
        NWAL1600CommonLogic.copyParamToScrn(scrnMsg);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        scrnMsg.xxDealSlsPct.setValue(NWAL1600CommonLogic.calcTotalPercent(scrnMsg));
        scrnMsg.xxDealSlsPct.setAppFracDigit(2);

        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxErrFlg.getValue()))
        // {
        // NWAL1600CommonLogic.protectAllInput(this, scrnMsg);
        // scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        // return;
        // }

        NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1600CommonLogic.setLabelItem(scrnMsg);
        NWAL1600CommonLogic.setRowsBGWithClear(scrnMsg);
        NWAL1600CommonLogic.initCmnBtnProp(this);

        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {

            NWAL1600CommonLogic.protectAllInput(this, scrnMsg);
        } else {

            NWAL1600CommonLogic.protectInputForInit(this, scrnMsg);
            NWAL1600CommonLogic.protectInput(this, scrnMsg);
        }

        scrnMsg.xxDealSlsPct.setValue(NWAL1600CommonLogic.calcTotalPercent(scrnMsg)); // QC#13584 2017/03/02 Add
        NWAL1600CommonLogic.setDefaultForcus(scrnMsg);
        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        setNameForMessage(scrnMsg);
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        // Header
        scrnMsg.cpoOrdNum.setNameForMessage("Transaction Number");
        // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        //scrnMsg.dsOrdPosnNum.setNameForMessage("Configuration Number");
        scrnMsg.xxComnScrColValTxt.setNameForMessage("Configuration Number");
        // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        scrnMsg.xxDealSlsPct.setNameForMessage("Total Percentage");

        // Sales Credit
        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).lineBizRoleTpCd_A.setNameForMessage("Role Type");
            scrnMsg.A.no(i).psnNum_A.setNameForMessage("Resource Number"); //2016/05/12 S21_NA#7861 Mod psnCd_A -> psnNum_A
            scrnMsg.A.no(i).tocNm_A.setNameForMessage("Employee Name");
            scrnMsg.A.no(i).slsRepCrPct_A.setNameForMessage("Percentage");
        }

        // Non Quote
        for (int i = 0; i < scrnMsg.B.length(); i++) {

            scrnMsg.B.no(i).lineBizRoleTpCd_B.setNameForMessage("Role Type");
            scrnMsg.B.no(i).psnNum_B.setNameForMessage("Resource Number"); //2016/05/12 S21_NA#7861 Mod psnCd_B -> psnNum_B
            scrnMsg.B.no(i).tocNm_B.setNameForMessage("Employee Name");
        }
    }
}
