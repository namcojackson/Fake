package business.blap.ZZIL0020.constant;

import com.canon.cusa.s21.framework.integration.S21IntegrationUtil;

import business.db.INTERFACE_MQCONFIGTMsg;
import business.db.INTERFACE_MQCONFIG_JNDITMsg;
import business.db.INTERFACE_RECEIVERTMsg;
import business.db.INTERFACE_SENDERTMsg;
import business.db.INTERFACE_SETTINGTMsg;
import business.file.ZZIL0020F00FMsg;
import business.file.ZZIL0020F01FMsg;
import business.file.ZZIL0020F02FMsg;
import business.file.ZZIL0020F03FMsg;
import business.file.ZZIL0020F04FMsg;
import parts.common.EZDFMsg;
import parts.common.EZDTMsg;

public interface ZZIL0020Constant {

    /** A file reading end cord */
    static final int FILE_END = 1;

    /** INTERFACE_MQCONFIG TRNSP_TP */
    static final String TRNSP_TP_BINDING = "BINDING";
    static final String TRNSP_TP_CLIENT  = "CLIENT";
    static final String TRNSP_TP_CHN_TBL = "CHN_TBL";

    /** INTERFACE_SENDER MSG_SEND_CLS */
    static final String MSG_SEND_CLS_2WAY = "com.canon.cusa.s21.framework.integration.S21MQMessageSender";
    static final String MSG_SEND_CLS_1WAY = "com.canon.cusa.s21.framework.integration.S21MQASyncMessageSender";
    static final String MSG_SEND_CLS_TEST = "com.canon.cusa.s21.framework.integration.S21TestMessageSenderSuccess";

    /** INTERFACE_SETTING DRCTN_TP */
    static final String DRCTN_TP_INBOUND =  "INBOUND";
    static final String DRCTN_TP_OUTBOUND = "OUTBOUND";
    static final String DRCTN_TP_PROVIDER = "PROVIDER";
    static final String DRCTN_TP_COMMON =   "COMMON"; 
    
    /** INTERFACE_SETTING COMM_TOOL */
    static final String COMM_TOOL_FTP   = "FTP";
    static final String COMM_TOOL_MQ    = "MQ";
    static final String COMM_TOOL_NONE  = "";

    /** CSV file format error info */
    static final String ERR_1000 = "item number injustice error";
    static final String ERR_1999 = "number of the item figures over error (item number @)";
    static final String ERR_2999 = "item classification error (item number @)";
    static final String ERR_SRC  = "@";
    
    /** Table infomation */
    enum TBL {
        INTERFACE_SETTING {
            @Override
            public EZDTMsg getEZDTMsg() {
                return new INTERFACE_SETTINGTMsg();
            }
            @Override
            public EZDFMsg getEZDFMsg() {
                return new ZZIL0020F00FMsg();
            }
        },
        INTERFACE_SENDER {
            @Override
            public EZDTMsg getEZDTMsg() {
                return new INTERFACE_SENDERTMsg();
            }
            @Override
            public EZDFMsg getEZDFMsg() {
                return new ZZIL0020F01FMsg();
            }
        },
        INTERFACE_RECEIVER {
            @Override
            public EZDTMsg getEZDTMsg() {
                return new INTERFACE_RECEIVERTMsg();
            }
            @Override
            public EZDFMsg getEZDFMsg() {
                return new ZZIL0020F02FMsg();
            }
        },
        INTERFACE_MQCONFIG {
            @Override
            public EZDTMsg getEZDTMsg() {
                return new INTERFACE_MQCONFIGTMsg();
            }
            @Override
            public EZDFMsg getEZDFMsg() {
                return new ZZIL0020F03FMsg();
            }
        },
        INTERFACE_MQCONFIG_JNDI {
            @Override
            public EZDTMsg getEZDTMsg() {
                return new INTERFACE_MQCONFIG_JNDITMsg();
            }
            @Override
            public EZDFMsg getEZDFMsg() {
                return new ZZIL0020F04FMsg();
            }
        };

        public abstract EZDTMsg getEZDTMsg();
        public abstract EZDFMsg getEZDFMsg();

        public static EZDTMsg getEZDTMsg(TBL targerTbl) {
            for (TBL tbl : values() ) {
                if ( tbl == targerTbl ) {
                    return tbl.getEZDTMsg();
                }
            }
            return null;
        }

        public static EZDFMsg getEZDFMsg(TBL targerTbl) {
            for (TBL tbl : values() ) {
                if ( tbl == targerTbl ) {
                    return tbl.getEZDFMsg();
                }
            }
            return null;
        }
    }

    /** INTERFACE_RECEIVER csv header column */
    String[] HEAD_INTERFACE_RECEIVER = {
            "INTFC_RCV_ID",
            "EZBUSINESSID",
            "EZFUNCTIONCODE",
            "EZUSERID",
            "EZPASSWORD",
            "SEC_CONFIG_NM"
    };
    
    S21IntegrationUtil Integutil = new S21IntegrationUtil();
    
}
