/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.CTAC_PNT;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.EXT_MAX_LENGTH;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_UPD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.HYPHEN;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.MESSAGE_KIND_ERR;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.MESSAGE_KIND_WARN;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM0185E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM0803E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8113E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8132E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8347E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8348E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8698E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NZZM0002I;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.PERIOD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.PRIM_CTAC_TP;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.SLASH;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;
import business.servlet.NMAL6750.common.NMAL6750CommonLogic;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/12   Fujitsu         S.Ohki          Update          QC#2497
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 * 2016/03/02   Fujitsu         C.Tanaka        Update          QC#4779
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 *</pre>
 */
public class NMAL6750Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        errCheck(scrnMsg);

        NMAL6750CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_WARN)) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
            scrnMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_0);
            //QC#8598 add Start
            NMAL6750CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
            //QC#8598 add End
        }
    }

    private void errCheck(NMAL6750BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String slsDt = ZYPDateUtil.getSalesDate();
            String fromDt1 = scrnMsg.A.no(i).effFromDt_A1.getValue();
            String thruDt1 = scrnMsg.A.no(i).effThruDt_A1.getValue();
            if (fromDt1.isEmpty()) {
                fromDt1 = "99991231";
            }
            if (thruDt1.isEmpty()) {
                thruDt1 = "99991231";
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsCtacPsnRelnPk_A1)) {
                if (0 < ZYPDateUtil.compare(slsDt, fromDt1)) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0185E);
                    return;
                }
            }

            if (0 <= ZYPDateUtil.compare(fromDt1, thruDt1)) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0803E);
                scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0803E);
                return;
            }

            for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                String fromDt2 = scrnMsg.A.no(j).effFromDt_A1.getValue();
                String thruDt2 = scrnMsg.A.no(j).effThruDt_A1.getValue();

                if (fromDt2.isEmpty()) {
                    fromDt2 = "99991231";
                }
                if (thruDt2.isEmpty()) {
                    thruDt2 = "99991231";
                }
                if (i != j && !chkDateOverlap(scrnMsg.A.no(i).dsAcctNum_A1.getValue(), scrnMsg.A.no(j).dsAcctNum_A1.getValue(), scrnMsg.A.no(i).locNum_A1.getValue(), scrnMsg.A.no(j).locNum_A1.getValue(), fromDt1, fromDt2, thruDt1, thruDt2)) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                }
            }
        }

        boolean chkCtacPntCdFlg = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.dsCtacPntTpCd_H2) && scrnMsg.dsCtacPntTpCd_H2.getValue().equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue())) {
                chkCtacPntCdFlg = true;
                break;
            }
        }
        if (!chkCtacPntCdFlg) {
            scrnMsg.dsCtacPntTpCd_H2.setErrorInfo(1, NMAM8132E, new String[] {PRIM_CTAC_TP, CTAC_PNT });
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            // START 2016/02/12 S.Ohki [QC#2497, MOD]
            // if
            // (DS_CTAC_PNT_TP.EMAIL_WORK.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue()))
            // {
            // String val =
            // scrnMsg.B.no(i).dsCtacPntValTxt_B1.getValue();
            // if (!val.contains(AT)) {
            // scrnMsg.B.no(i).dsCtacPntValTxt_B1.setErrorInfo(1,
            // NMAM8290E);
            // } else if (!val.endsWith(COM) && !val.endsWith(NET)) {
            // scrnMsg.B.no(i).dsCtacPntValTxt_B1.setErrorInfo(1,
            // NMAM8290E);
            // }
            // }

            if (DS_CTAC_PNT_TP.PHONE_ASSISTANT.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue()) || DS_CTAC_PNT_TP.PHONE_MOBILE.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue())
                    || DS_CTAC_PNT_TP.PHONE_WORK.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue()) || DS_CTAC_PNT_TP.FAX_WORK.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue())) {

                checkTelFormat(scrnMsg.B.no(i).dsCtacPntValTxt_B1);

            } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(scrnMsg.B.no(i).dsCtacPntTpCd_B1.getValue())) {
                checkEmailFormat(scrnMsg.B.no(i).dsCtacPntValTxt_B1);
            }
            checkExtFormat(scrnMsg.B.no(i).dsCtacPsnExtnNum_B1);
            // START 2016/02/12 S.Ohki [QC#2497, MOD]
        }

        // QC#8598 add Start
        // Del Start 2017/12/18 QC#22286
        ////contact role check
        //int roleCnt = 0;
        //for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
        //    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.R.no(i).xxChkBox_R1.getValue())) {
        //        roleCnt++;
        //    }
        //}
        //if (roleCnt == 0) {
        //    for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
        //        scrnMsg.R.no(i).xxChkBox_R1.setErrorInfo(1, NMAM8130E, new String[] {"Contact Role" });
        //    }
        //}
        // Del End 2017/12/18 QC#22286
        // QC#8598 add End
    }

    // START 2016/02/12 S.Ohki [QC#2497, ADD]
    private void checkTelFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String telNum = item.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                item.setErrorInfo(1, NMAM8348E);
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(item, telNum);
            }
        }
    }

    private void checkExtFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String extNum = item.getValue();
            // Mod Start 2018/12/14 QC#24022
            //if (extNum.length() > 4) {
            //    item.setErrorInfo(1, NMAM8349E);
            if (extNum.length() > EXT_MAX_LENGTH) {
                item.setErrorInfo(1, NMAM8698E);
                // Mod End 2018/12/14 QC#24022
            }
        }
    }

    private void checkEmailFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            // START 2016/10/95 Mz.Takahashi [QC#14431, ADD]
            boolean ret =NMXC106001CommonCheckUtil.checkEmailFormat(item.getValue());

            if (!ret){
                item.setErrorInfo(1, NMAM8347E);
            }

            //if (!item.getValue().matches(CHK_EMAIL_PATTERN)) {
            //    item.setErrorInfo(1, NMAM8347E);
            //}
            // END 2016/10/95 Mz.Takahashi [QC#14431, ADD]
        }
    }

    // START 2016/02/12 S.Ohki [QC#2497, ADD]

    private boolean chkDateOverlap(String chkDsAcctNum, String tgtDsAcctNum, String chkLocNum, String tgtLocNum, String fromDt1, String fromDt2, String thruDt1, String thruDt2) {
        boolean chkFlg = false;
        if (ZYPCommonFunc.hasValue(chkLocNum) && ZYPCommonFunc.hasValue(tgtLocNum)) {
            if (chkLocNum.equals(tgtLocNum)) {
                chkFlg = true;
            }
        } else if (chkDsAcctNum.equals(tgtDsAcctNum)) {
            chkFlg = true;
        }
        if (chkFlg) {
            if (0 <= ZYPDateUtil.compare(fromDt1, fromDt2) && 0 >= ZYPDateUtil.compare(fromDt1, thruDt2)) {
                return false;
            } else if (0 <= ZYPDateUtil.compare(thruDt1, fromDt2) && 0 >= ZYPDateUtil.compare(thruDt1, thruDt2)) {
                return false;
            }
        }
        return true;
    }
}
