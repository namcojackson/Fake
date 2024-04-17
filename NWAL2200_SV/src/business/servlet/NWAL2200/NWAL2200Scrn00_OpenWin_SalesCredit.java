/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0666E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0667E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NWAL1600_MODE_REFERENCE;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_SalesCredit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#9078
 * 2017/02/21   Fujitsu         H.Nagashima     Update          QC#17425
 * 2017/07/25   Fujitsu         R.Nakamura      Update          QC#20114
 * 2017/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_SalesCredit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<Integer> checkList = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {

            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(dplyTab)) {

            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
        }

        if (checkList != null) {

            if (checkList.size() == 1) {

                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else if (checkList.size() == 0) {

                scrnMsg.setMessageInfo(NWAM0667E, new String[] {"Config Line" });
                throw new EZDFieldErrorException();
            } else if (checkList.size() > 1) {

                scrnMsg.setMessageInfo(NWAM0666E, new String[] {"Config Line" });
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        Object[] params = getParamNWAL1600(scrnMsg);
        setArgForSubScreen(params);
    }

    private static Object[] getParamNWAL1600(NWAL2200BMsg scrnMsg) {

        // clear POPUP parameters
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.O);
        EZDBMsgArray slsCrList = null;

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        // Mod Start 2016/08/04 QC#9048
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, NWAL1600Constant.MODE_REFERENCE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, NWAL1600_MODE_REFERENCE);
        // Mod End 2016/08/04 QC#9048
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.cpoOrdNum);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {

            // line configuration
            int slctConfNum = scrnMsg.xxCellIdx.getValueInt();

            NWAL2200_ABMsg configMsg = scrnMsg.A.no(slctConfNum);
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, configMsg.dsOrdPosnNum_LC);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, "O");
            scrnMsg.O.clear();

            int prmIdx = 0;
            for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {

                NWAL2200_GBMsg slsCrMsg = scrnMsg.G.no(i);
                if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_GS.getValue())) {

                    EZDMsg.copy(slsCrMsg, "GS", scrnMsg.O.no(prmIdx), "O");
                    scrnMsg.O.no(prmIdx).configCatgCd_O.setValue(CONFIG_CATG.OUTBOUND);
                    scrnMsg.O.no(prmIdx).lineBizRoleTpCd_O.setValue(slsCrMsg.slsRepRoleTpCd_GS.getValue());
                    prmIdx++;
                }
            }
            scrnMsg.O.setValidCount(prmIdx);
            slsCrList = scrnMsg.O;
        } else if (TAB_RMA.equals(dplyTab)) {

            // RMA
            int slctConfNum = scrnMsg.xxCellIdx.getValueInt();

            NWAL2200_CBMsg rmaConfigMsg = scrnMsg.C.no(slctConfNum);
            String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, rmaConfigMsg.dsOrdPosnNum_RC);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, "O");
            scrnMsg.O.clear();

            int prmIdx = 0;
            for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {

                NWAL2200_HBMsg slsCrMsg = scrnMsg.H.no(i);
                if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_HS.getValue())) {

                    EZDMsg.copy(slsCrMsg, "HS", scrnMsg.O.no(prmIdx), "O");
                    scrnMsg.O.no(prmIdx).configCatgCd_O.setValue(CONFIG_CATG.INBOUND);
                    scrnMsg.O.no(prmIdx).lineBizRoleTpCd_O.setValue(slsCrMsg.slsRepRoleTpCd_HS.getValue());
                    prmIdx++;
                }
            }
            scrnMsg.O.setValidCount(prmIdx);
            slsCrList = scrnMsg.O;
        } else {

            // header
            scrnMsg.P.no(2).xxPopPrm.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, "O");

            int prmIdx = 0;
            //QC#17425 mod Start
//            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
//
//                NWAL2200_FBMsg slsCrMsg = scrnMsg.F.no(i);
//                EZDMsg.copy(slsCrMsg, "FS", scrnMsg.O.no(prmIdx), "O");
//                scrnMsg.O.no(prmIdx).lineBizRoleTpCd_O.setValue(slsCrMsg.slsRepRoleTpCd_FS.getValue());
//                prmIdx++;
//            }
//            scrnMsg.O.setValidCount(prmIdx);
            String hdrSlsRepTocCd = scrnMsg.slsRepTocCd.getValue();
            if (scrnMsg.F.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                    NWAL2200_FBMsg slsRepMsg = scrnMsg.F.no(i);
                    String lineBizRoleTpCd = slsRepMsg.slsRepRoleTpCd_FS.getValue();
                    // Add Start 2017/07/25 QC#20114
                    hdrSlsRepTocCd = slsRepMsg.slsRepTocCd_FS.getValue();
                    // Add End 2017/07/25 QC#20114

                    // 2017/01/23 QC#18798 Mod Start
                    // Mod Start 2017/07/25 QC#20114
                    if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)
                            || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTpCd)) {
                        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_FS, hdrSlsRepTocCd);
                        String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
                        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
                            String fstBizCtxAttbTxt = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, fstBizCtxAttbTxt);
                            }
                        }
                        EZDMsg.copy(slsRepMsg, "FS", scrnMsg.O.no(prmIdx), "O");
                        scrnMsg.O.no(prmIdx).lineBizRoleTpCd_O.setValue(slsRepMsg.slsRepRoleTpCd_FS.getValue());
                    } else if (LINE_BIZ_ROLE_TP.ESS_INSTALLER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_INSTALLER.equals(lineBizRoleTpCd)
                            || LINE_BIZ_ROLE_TP.LFS_INSTALLER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.EMSD.equals(lineBizRoleTpCd)) {
                        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_FS, hdrSlsRepTocCd);
                        String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_INSTALLER);
                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_INSTALLER);
                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_INSTALLER);
                        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.EMSD);
                        }
                        EZDMsg.copy(slsRepMsg, "FS", scrnMsg.O.no(prmIdx), "O");
                        scrnMsg.O.no(prmIdx).lineBizRoleTpCd_O.setValue(slsRepMsg.slsRepRoleTpCd_FS.getValue());
                    }
                    // Mod End 2017/07/25 QC#20114
                    // 2017/01/23 QC#18798 Mod End
                    prmIdx++;
                }
                scrnMsg.O.setValidCount(prmIdx);
            }
            if (scrnMsg.O.getValidCount() == 0) {
                NWAL2200_OBMsg paramSlsRepMsg = scrnMsg.O.no(0);
                ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.slsRepTocCd_O, hdrSlsRepTocCd);
                ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.slsCrQuotFlg_O, ZYPConstant.FLG_ON_Y);
                paramSlsRepMsg.slsRepCrPct_O.setValue(BigDecimal.valueOf(100));

                // 2017/01/23 QC#18798 Mod Start
                String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.ESS_WRITER);
                } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.PPS_WRITER);
                } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.LFS_WRITER);
                } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
                    String fstBizCtxAttbTxt = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                    if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                        ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, fstBizCtxAttbTxt);
                    }
                }
                // 2017/01/23 QC#18798 Mod End
                scrnMsg.O.setValidCount(1);
            }
            //QC#17425 mod End
            slsCrList = scrnMsg.O;
        }

        Object[] params = new Object[6];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = slsCrList;
        params[5] = scrnMsg.P.no(4).xxPopPrm;
        return params;
    }
}
