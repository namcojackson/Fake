/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0010.common;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.blap.ZZIL0010.ZZIL0010CMsg;
import business.servlet.ZZIL0010.ZZIL0010BMsg;
import business.servlet.ZZIL0010.ZZIL0010Bean;
import business.servlet.ZZIL0010.constant.ZZIL0010Constant;

public class ZZIL0010CommonLogic implements ZZIL0010Constant {

    /**
     * 画面の「一覧表：A」の行の背景色を、「行単位に反転させます。 ならびに項目を画面表示項目に変換させます。
     * @param scrnMsg ZZIL0010BMsg
     * @param bizMsg ZZIL0010CMsg
     */
    public static void setTableColor(ZZIL0010BMsg scrnMsg, ZZIL0010CMsg bizMsg) {

        S21TableColorController tblColor = new S21TableColorController("ZZIL0010Scrn00", scrnMsg);
        tblColor.clearRowsBG(ZZIL0010Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {

            String styleClass = "";
            String procVal = null;
            String dispVal = null;

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                // processed_flag name setting
                procVal = bizMsg.A.no(i).processedFlag_A.getValue();
                dispVal = procFlg.get(procVal);

                if (dispVal != null) {
                    scrnMsg.A.no(i).xxProcFlgNm_A.setValue(dispVal);
                } else {
                    scrnMsg.A.no(i).xxProcFlgNm_A.setValue(procVal);
                }

                // register date setting
                scrnMsg.A.no(i).xxDtTm_AR.setValue(convYmd(bizMsg.A.no(i).ezInTime_A.getValue()));
                // updated date setting
                scrnMsg.A.no(i).xxDtTm_AU.setValue(convYmd(bizMsg.A.no(i).ezUpTime_A.getValue()));

                // processed_flag listbox setting
                for (int j = 0; j < PROC_LIST.length; j++) {
                    scrnMsg.A.no(i).processedFlag_AU.no(j).setValue(PROC_LIST[j][COL_PROC_CD]);
                    scrnMsg.A.no(i).xxProcFlgNm_AU.no(j).setValue(PROC_LIST[j][COL_PROC_NM]);
                }

                if ("pEvenNumberBGcolor".equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = "pEvenNumberBGcolor";
                }
                tblColor.setRowStyle(ZZIL0010Bean.A, i, styleClass);
            }

        }
    }

    /**
     * リストボックスを設定する
     * @param scrnMsg
     */
    public static void setListBox(ZZIL0010BMsg scrnMsg) {

        String hmCd = null;
        String hmDisp = null;

        // time listbox setting
        for (int i = 0; i < HH24; i++) {
            hmCd = String.format("%02d", i);
            hmDisp = String.format("%02d:00", i);

            scrnMsg.xxHrs_RF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_RF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_RT.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_RT.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_UF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_UF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_UT.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_UT.no(i).setValue(hmDisp);
        }

        // processed_flag listbox setting
        for (int i = 0; i < PROC_LIST.length; i++) {
            scrnMsg.processedFlag_PC.no(i).setValue(PROC_LIST[i][COL_PROC_CD]);
            scrnMsg.xxProcFlgNm_PC.no(i).setValue(PROC_LIST[i][COL_PROC_NM]);
        }
    }

    /**
     * 日付文字列に変換
     * @param ymd String
     * @return 日付文字列
     */
    private static String convYmd(String ymd) {
        if (ymd.length() > 0) {
            return ymd.substring(4, 6) + "/" + ymd.substring(6, 8) + "/" + ymd.substring(0, 4) + " " + ymd.substring(8, 10) + ":" + ymd.substring(10, 12);
        }
        return ymd;
    }

    /** processed flag value */
    private static final Map<String, String> procFlg;
    static {
        procFlg = new HashMap<String, String>();
        for (int i = 0; i < PROC_LIST.length; i++) {
            procFlg.put(PROC_LIST[i][COL_PROC_CD], PROC_LIST[i][COL_PROC_NM]);
        }
    }
}
