/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/10/07   Hitachi         Y.Takeno        Update          CSA-QC#13431
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue())) {
            if (PSN_TP.EMPLOYEE.equals(scrnMsg.psnTpCd_P1.getValue())) {
                scrnMsg.psnNum_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM });
                scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
            }
        }

        // 2016/03/04 S21_NA#4539 Mod Start ------------
        //scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        // First Name
        if (scrnMsg.psnFirstNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnFirstNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnFirstNm_H1.clearErrorInfo();
            }
        }

        // Last Name
        if (scrnMsg.psnLastNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnLastNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnLastNm_H1.clearErrorInfo();
            }
        }

        // Type
        if (scrnMsg.psnTpCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnTpCd_P1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnTpCd_P1.clearErrorInfo();
            }
        }

        // Employment Date From
        if (scrnMsg.effFromDt_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt_H1.clearErrorInfo();
            }
        }
        NMAL2510CommonLogic.addCheckHeaderItems(scrnMsg);
        // 2016/03/04 S21_NA#4539 Mod End --------------

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2510CommonLogic.setProtectEffectiveFrom(scrnMsg);
        NMAL2510CommonLogic.controlBusinessAreaFields(scrnMsg);
        // if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1.getValue()) &&
        // (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue()))) {
        NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
        // }
        // QC#13431
        NMAL2510CommonLogic.controlAttachmentButton(this, scrnMsg);
        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2510CommonLogic.setAddDelButton(this, scrnMsg);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}
