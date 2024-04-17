/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

public interface NWDL0260Constant {

    String BIZ_ID = NWDL0260Constant.class.getSimpleName().substring(0, 8);

    String Y = ZYPConstant.FLG_ON_Y;
    String N = ZYPConstant.FLG_OFF_N;

    enum FunctionId {

        Modification(BIZ_ID + "T010");

        private String id;

        private FunctionId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
    
    enum BizBtn_Scrn00 {

        SearchAvailableQty("SearchAvailableQty", "SearchAvailableQty"),

        Allocate("Allocate", "Allocate"),

        Search("Search", "Search"),

        Cancel("Cancel", "Cancel");

        private String htmlNm;

        private String eventNm;

        private BizBtn_Scrn00(String htmlNm, String eventNm) {
            this.htmlNm = htmlNm;
            this.eventNm = eventNm;
        }

        public String getEventNm() {
            return eventNm;
        }

        public String getHtmlNm() {
            return htmlNm;
        }
    }

    enum CmnBtn {

        Save("btn1", "CMN_Save", "Save"),

        Submit("btn2", "CMN_Submit", "Submit"),

        Apply("btn3", "CMN_Apply", "Apply"),

        Approve("btn4", "CMN_Approve", "Approve"),

        Reject("btn5", "CMN_Reject", "Reject"),

        Download("btn6", "CMN_Download", "Download"),

        Delete("btn7", "CMN_Delete", "Delete"),

        Clear("btn8", "CMN_Clear", "Clear"),

        Reset("btn9", "CMN_Reset", "Reset"),

        Return("btn10", "CMN_Return", "Return");

        private String htmlNm;

        private String eventNm;

        private String label;

        private CmnBtn(String htmlNm, String eventNm, String label) {
            this.htmlNm = htmlNm;
            this.eventNm = eventNm;
            this.label = label;
        }

        public String getEventNm() {
            return eventNm;
        }

        public String getHtmlNm() {
            return htmlNm;
        }

        public String getLabel() {
            return label;
        }
    }

    enum FunctionCd {

        Search("20"),

        Update("40");

        private String cd;

        private FunctionCd(String functionCd) {
            this.cd = functionCd;
        }

        public String getCd() {
            return cd;
        }
    }

    enum ScrnId {

        Scrn00(BIZ_ID + "Scrn00");

        private String id;

        private ScrnId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    enum ColorCd {
        
        CancelLine("FFE3E3"),

        AllocatedLine("#FFFF99");

        private String cd;
        
        private ColorCd(String cd) {
            this.cd = cd;
        }
        
        public String getCd() {
            return cd;
        }
    }
    
    enum MsgId {

        /**
         * [@] field is mandatory.
         */
        ZZM9000E,
        
        /**
         * [@] field exceeded maximum value.
         */
        ZZM9002E,
        
        /**
         * [@] field is less than minimum value.
         */
        ZZM9003E,
    }

}
