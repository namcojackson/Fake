/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460_NMAL2630 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        if (BTN_CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_01)) {
            return null;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.asgTrtyAttrbCd)) {
            seReturnDataforDtl(scrnMsg, scrnMsg.xxNum_EV.getValueInt());

            NMAL2460CMsg bizMsg = new NMAL2460CMsg();
            bizMsg.setBusinessID("NMAL2460");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_02);
            scrnMsg.setFocusItem(scrnMsg.orgNm_H1);
            return null;
        }

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        try {
            if (cMsg == null) {
                scrnMsg.setFocusItem(scrnMsg.orgNm_H1);
            } else {
                int eventLine = scrnMsg.xxNum_EV.getValueInt();

                NMAL2460CMsg bizMsg = (NMAL2460CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                setFocusItemForDtl(scrnMsg, eventLine);
            }
        } finally {
            scrnMsg.asgTrtyAttrbCd.clear();
        }
    }

    private void seReturnDataforDtl(NMAL2460BMsg scrnMsg, int index) {

        NMAL2460_BBMsg bbMsg = scrnMsg.B.no(index);

        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B1, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B2, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B3, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B4, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B5, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B6, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B7, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B8, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_B9, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BA, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BB, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BC, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BD, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BE, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BF, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BG, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BH, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BI, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BJ, scrnMsg.xxPopPrm_02);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(bbMsg.orgNm_BK, scrnMsg.xxPopPrm_02);
        }
    }

    private void setFocusItemForDtl(NMAL2460BMsg scrnMsg, int index) {

        NMAL2460_BBMsg bbMsg = scrnMsg.B.no(index);

        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B1);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B2);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B3);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B4);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B5);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B6);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B7);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B8);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_B9);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BA);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BB);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BC);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BD);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BE);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BF);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BG);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BH);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BI);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BJ);
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals(scrnMsg.asgTrtyAttrbCd.getValue())) {
            scrnMsg.setFocusItem(bbMsg.orgNm_BK);
        }
    }
}
