package business.blap.ZZML0040.constant;

public interface ZZML0040Constant {

    // ----- ZZML0040Query -----
    String FLG_SUBMIT = "S";

    String PM_GLBL_CMPY_CD = "glblCmpyCd";
    String PM_ML_TMPL_ID = "mlTmplId";
    String PM_ML_LOC_ID     = "mlLocId";
    String PM_ML_SUBJ_TMPL_TXT = "mlSubjTxt";
    String PM_ML_BODY_TMPL_TXT = "mlBodyTmplTxt";

    String DB_CHUNK_SIZE = "CHUNK_SIZE";
    String DB_ML_BODY_TMPL_TXT = "ML_BODY_TMPL_TXT";

    String QUERY_LIST = "getList";
    String QUERY_DETAIL = "getDetail";
    String QUERY_DETAIL_MLBODY = "selMlBody";
    String QUERY_LANGLIST = "getLangList";
    String QUERY_UPDATE = "updMlBody";
    
    String LANG = "en";
    
    /** Language list */
    static enum Language {
        
        // START 2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        // en("Engilish"),
        // fr("French"),
        // pt("Portuguese"),
        // es("Spanish"),
        // ja("Japanese");
        en("English general"),
        es("Spanish general"),
        es_MX("Spanish Mexico"),
        es_PA("Spanish Panama"),
        es_PE("Spanish Peru"),
        pt("Portuguese general");
        // END   2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        
        private String languageNm;
        
        private Language(String arg) {
            this.languageNm = arg;
        }
        
        public String getLangName() {
            return languageNm;
        }
    }

}
