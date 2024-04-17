package business.blap.ZYPL0220;

import java.lang.reflect.Field;

import parts.common.EZDCMsg;
import parts.common.EZDFMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSchemaItemDefines;
import business.blap.ZYPL0220.common.ZYPL0220CommonLogic;
import business.blap.ZYPL0220.constant.ZYPL0220Constant;
import business.db.UPLD_CSV_HDRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZYPL0220BL02 extends S21BusinessHandler implements ZYPL0220Constant {

    /** 結果コード */
    public static enum DATA_TYPE implements EZDSchemaItemDefines {
        /**
         * 正常終了
         */
        NUMBERS("Numbers"), TWO_BYTES_CHARACTER("2Bytes Character"), TWO_BYTES_KANA_CHARACTER("2Bytes Kana Character"), ALPHABET_NUMERIC_CHARACTER("Alphabet Numeric Character"), ALPHABET_CHARACTER("Alphabet Character"), NUMERIC_CHARACTER(
                "Numeric Character"), MIXED_BYTE_CHARACTER("Mixed Byte Character"), DATE_YEAR_MONTH_DAY("Date (Year Month Day)"), DATE_YEAR_MONTH("Date (Year Month)"), DATE_YEAR("Date (Year)");

        /** 結果コードのコード値 */
        private final String dataTypeName;

        /**
         * コンストラクタ
         * @param code 結果コードのコード値
         */
        DATA_TYPE(String dataTypeName) {
            this.dataTypeName = dataTypeName;
        }

        /**
         * 結果コードのコード値を返す。
         * @return 結果コードのコード値
         */
        public String getDataTypeName() {
            return this.dataTypeName;
        }

        /**
         * 結果コードのコード値からオブジェクトを生成する。
         * @param returnCode 結果コードのコード値
         * @return 結果コードのオブジェクト
         */
        public static DATA_TYPE valueFromEZDCode(String ezdCode) {

            if (TYPE_SEISU_SYOSU.equals(ezdCode)) {
                return NUMBERS;

            } else if (TYPE_ZENKAKU.equals(ezdCode)) {
                return TWO_BYTES_CHARACTER;

            } else if (TYPE_ZENKAKUKANA.equals(ezdCode)) {
                return TWO_BYTES_KANA_CHARACTER;

            } else if (TYPE_HANKAKUEISU.equals(ezdCode)) {
                return ALPHABET_NUMERIC_CHARACTER;

            } else if (TYPE_HANKAKUEIJI.equals(ezdCode)) {
                return ALPHABET_CHARACTER;

            } else if (TYPE_HANKAKUSUJI.equals(ezdCode)) {
                return NUMERIC_CHARACTER;

            } else if (TYPE_KONZAI.equals(ezdCode)) {
                return MIXED_BYTE_CHARACTER;

            } else if (TYPE_NENTSUKIHI.equals(ezdCode)) {
                return DATE_YEAR_MONTH_DAY;

            } else if (TYPE_NENTSUKI.equals(ezdCode)) {
                return DATE_YEAR_MONTH;

            } else if (TYPE_NEN.equals(ezdCode)) {
                return DATE_YEAR;

            } else {
                throw new S21AbendException("");
            }

        }
    }

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Dispatch event
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (SCRN_SEARCH.equals(screenAplID)) {
                // 2.Search Event
                doProcess_ZYPL0220Scrn00_Search((ZYPL0220CMsg) cMsg, sMsg);

            } else if (SCRN_LOAD_NAMES.equals(screenAplID)) {
                // 2.Search Event
                doProcess_ZYPL0220Scrn00_LoadNames((ZYPL0220CMsg) cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Search Event
     * @param msg
     * @param msg2
     */
    private void doProcess_ZYPL0220Scrn00_Search(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // -----------------------------------------------------
        // 2) do Search
        // -----------------------------------------------------
        ZYPL0220CommonLogic.doSearch(cMsg, sMsg);

    }

    /**
     * Load Names Event
     * @param msg
     * @param msg2
     */
    private void doProcess_ZYPL0220Scrn00_LoadNames(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {
        // -----------------------------------------------------
        // 2) check timestamp
        // -----------------------------------------------------
        if (!chechTimeStampUpldCsvHdr(cMsg, sMsg)) {
            return;
        }

        // -----------------------------------------------------
        // 3) reload file layout
        // -----------------------------------------------------
        loadNamesFromFMsg(cMsg, sMsg);
    }


    /**
     * UPLD_CSV_Hdrのタイムスタンプ排他チェックを行う。
     * @param msg
     * @param msg2
     * @return
     */
    private boolean chechTimeStampUpldCsvHdr(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {
        UPLD_CSV_HDRTMsgArray findHdrTMsgArray = ZYPL0220CommonLogic.findUpldCsvHdrByUpldCsvId(cMsg, true);

        for (int i = 0; i < findHdrTMsgArray.length(); i++) {
            if (cMsg.A.no(i).ezUpTime_0D.isClear()) {
                return true;
            }

            // comp timestamp
            String scrnUpTime = cMsg.A.no(i).ezUpTime_0D.getValue();
            String scrnEzUpTimeZone = cMsg.A.no(i).ezUpTimeZone_0D.getValue();
            String dbEzUpTime = findHdrTMsgArray.no(i).ezUpTime.getValue();
            String dbEzUpTimeZone = findHdrTMsgArray.no(i).ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(scrnUpTime, scrnEzUpTimeZone, dbEzUpTime, dbEzUpTimeZone)) {
                cMsg.setMessageInfo(MSG_CD_NOT_SAME_TIMESTAMP);
                cMsg.A.no(i).upldCsvHdrNm.setErrorInfo(1, MSG_CD_NOT_SAME_TIMESTAMP);
                return false;
            }

        }

        return true;
    }

    /**
     * FMsgからHeader名を取得します。
     * @param msg
     * @param msg2
     * @return 取得件数
     */
    private void loadNamesFromFMsg(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // 1.find FMsg class
        EZDFMsg fmsg = ZYPL0220CommonLogic.newUpldEZDFMsg(cMsg);
        if (fmsg == null) {
            return;
        }

        // 2.get field info
        String[][] baseContents = null;
        String[][] dispContents = null;
        try {
            Field baseContentsField = fmsg.getClass().getDeclaredField("BASE_CONTENTS");
            baseContentsField.setAccessible(true);
            baseContents = (String[][]) baseContentsField.get(fmsg);

            Field dispContentsField = fmsg.getClass().getDeclaredField("DISP_CONTENTS");
            dispContentsField.setAccessible(true);
            dispContents = (String[][]) dispContentsField.get(fmsg);

        } catch (SecurityException e) {
            e.printStackTrace();
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_FAILED_REFRECT_FMSG);
            return;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_FAILED_REFRECT_FMSG);
            return;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_FAILED_REFRECT_FMSG);
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_FAILED_REFRECT_FMSG);
            return;
        }

        if (dispContents.length == 0) {
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_FAILED_REFRECT_FMSG);
            return;
        }

        // 3.set biz message
        cMsg.A.clear();
        int i = 0;
        for (; i < dispContents.length; i++) {
            String fieldName = dispContents[i][0];
            String fieldType = baseContents[i][4];
            String fieldNumber = baseContents[i][5];
            String fieldDecimal = baseContents[i][6];

            cMsg.A.no(i).upldCsvHdrNum.setValue(i + 1);
            cMsg.A.no(i).upldCsvHdrDefNm.setValue(fieldName);
            cMsg.A.no(i).upldCsvHdrDataTpNm.setValue(DATA_TYPE.valueFromEZDCode(fieldType).getDataTypeName());
            if (fieldDecimal == null || "".equals(fieldDecimal)) {
                cMsg.A.no(i).upldCsvHdrDataLg.setValue(fieldNumber);
            } else {
                cMsg.A.no(i).upldCsvHdrDataLg.setValue(fieldNumber + "," + fieldDecimal);
            }
            cMsg.A.no(i).upldCsvHdrNm.setValue(fieldName);
        }
        cMsg.A.setValidCount(i);

    }
}
