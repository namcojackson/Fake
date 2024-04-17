/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.GET_ADDR_BTN;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.MODE_REFERENCE;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.BIZ_ID;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.NWAM0270E;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.NWAM0298E;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;
import business.servlet.NWAL1640.common.NWAL1640CommonLogic;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1640_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2015/11/09   Fujitsu         S.Yamamoto      Update          QC#488
 * 2015/02/22   Fujitsu         M.Suzuki        Update          S21_NA#2140
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        // 2016/02/22 S21_NA#2140 Mod Start ------------
        if (params != null && params.length == 25) {
        // 2016/02/22 S21_NA#2140 Mod End --------------      
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, (EZDBBigDecimalItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineSubNum, (EZDBBigDecimalItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.soldToCustLocCd, (EZDBStringItem) params[5]);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustLocNm, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustAcctNm, ((EZDBStringItem) params[6]));
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_SE, (EZDBStringItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_SE, (EZDBStringItem) params[8]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_SE, (EZDBStringItem) params[9]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_SE, (EZDBStringItem) params[10]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, (EZDBStringItem) params[11]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, (EZDBStringItem) params[12]);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocNm, (EZDBStringItem) params[13]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctNm, (EZDBStringItem) params[13]);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_BI, (EZDBStringItem) params[14]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_BI, (EZDBStringItem) params[15]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_BI, (EZDBStringItem) params[16]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_BI, (EZDBStringItem) params[17]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyTpCd, (EZDBStringItem) params[18]);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.splyNm, (EZDBStringItem) params[19]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, (EZDBStringItem) params[19]);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyFirstAddr, (EZDBStringItem) params[20]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyCtyAddr, (EZDBStringItem) params[21]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyStCd, (EZDBStringItem) params[22]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyPostCd, (EZDBStringItem) params[23]);
            // 2016/02/22 S21_NA#2140 Add Start ------------
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[24]);
            // 2016/02/22 S21_NA#2140 Add End --------------

            // Set Back Up
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyTpCd_BK, scrnMsg.splyTpCd);
            // 2019/10/04 S21_NA#51372 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.splyNm_BK, scrnMsg.splyNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_BK, scrnMsg.prntVndNm);
            // 2019/10/04 S21_NA#51372 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyFirstAddr_BK, scrnMsg.splyFirstAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyStCd_BK, scrnMsg.splyStCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyCtyAddr_BK, scrnMsg.splyCtyAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyPostCd_BK, scrnMsg.splyPostCd);

            if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
                scrnMsg.setMessageInfo(NWAM0298E, new String[] {scrnMsg.dsOrdPosnNum.getNameForMessage() });
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
                scrnMsg.setMessageInfo(NWAM0298E, new String[] {scrnMsg.dsCpoLineNum.getNameForMessage() });
                return null;
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            return null;
        }

        // Set Line Num
        String dsOrdPosnNum = scrnMsg.dsOrdPosnNum.getValue();
        BigDecimal dsCpoLineNum = scrnMsg.dsCpoLineNum.getValue();
        BigDecimal dsCpoLineSubNum = scrnMsg.dsCpoLineSubNum.getValue();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlLineNum, NWXC150001DsCheck.editDtlLineNum(dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum));

        NWAL1640CMsg bizMsg = new NWAL1640CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            NWAL1640CommonLogic.protectCmnBtnProp(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.splyTpCd);
            return;
        }

        NWAL1640CMsg bizMsg = (NWAL1640CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1640CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL1640CommonLogic.activeLink(scrnMsg);

        scrnMsg.stNm.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.splyTpCd);
        // 2016/02/22 S21_NA#2140 Add Start ------------
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            protectAllInput(scrnMsg);
        }
        // 2016/02/22 S21_NA#2140 End Start ------------
    }
    // 2016/02/22 S21_NA#2140 Add Start ------------
    private void protectAllInput(NWAL1640BMsg scrnMsg) {
        scrnMsg.setInputProtected(true);
        this.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        // 2016/03/03 S21_NA#2140 Mod Start ----------
        //this.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
        // 2016/03/03 S21_NA#2140 Mod End ------------
        // QC#4505
        this.setButtonEnabled(GET_ADDR_BTN, false);
    }
    // 2016/02/22 S21_NA#2140 End Start ------------

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        scrnMsg.splyTpCd.setNameForMessage("Supplier Type");
        // 2019/10/04 S21_NA#51372 Mod Start
        //scrnMsg.splyNm.setNameForMessage("Name");
        scrnMsg.prntVndNm.setNameForMessage("Name");
        // 2019/10/04 S21_NA#51372 Mod End
        scrnMsg.splyFirstAddr.setNameForMessage("Address 1");
        scrnMsg.splyCtyAddr.setNameForMessage("City");
        scrnMsg.splyStCd.setNameForMessage("State");
        // 2019/10/04 S21_NA#51372 Mod Start
        //scrnMsg.splyNm.setNameForMessage("State Name");
        scrnMsg.prntVndNm.setNameForMessage("State Name");
        // 2019/10/04 S21_NA#51372 Mod End
        scrnMsg.splyPostCd.setNameForMessage("Postal");
    }
}
