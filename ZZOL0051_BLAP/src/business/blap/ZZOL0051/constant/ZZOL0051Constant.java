package business.blap.ZZOL0051.constant;

public interface ZZOL0051Constant {

    // search aprameter
    String PM_GLBL_CMPY_CD  = "glblCmpyCd";
    String PM_UPLD_CSV_ID   = "upldCsvId";

    // search query
    String QUERY_MSTR       = "getCsvMstr";
    String QUERY_HEADERLIST = "getCsvHeaderList";
    String QUERY_APPIDLIST  = "getCsvAppIdList";
    String QUERY_PROCIDLIST = "getCsvProcIdList";

    // EDZMsg classpath
    String EZDFMSG_FILE = "business.file.@FMsg";
    String EZDFMSG_DB   = "business.db.@FMsg";
    String EZDTMSG_DB   = "business.db.@TMsg";
    String REPLACE_STR      = "@";

    // TAB
    String TAB_HEADER = "Header";
    String TAB_BIZAPP = "BizAppId";
    String TAB_PROCID = "ProcessId";

    // screen message
    enum SCRMSG{
        INSERT("Create", "ZZZM9012E"),
        UPDATE("Update", "ZZZM9013E");
        
        private String msgParam;
        private String msgId;

        private SCRMSG(String p, String i) {
            this.msgParam = p;
            this.msgId = i;
        }
        
        public String getParam() {
            return this.msgParam;
        }

        public String getMsgId() {
            return this.msgId;
        }
    }

}
