package business.blap.ZZZL0030.constant;

public interface ZZZL0030Constant {
    /**
     * Chart Title Name
     */
    static final String TITLE = "ONLINE BUSINESS PERFORMANCE";
    /**
     * Chart Title Font-size
     */
    static final int TITLE_FONT_SIZE = 15;
    /**
     * Chart Font Color
     */
    static final String TITLE_FONT_COLOR = "#6482B9";
    /**
     * Chart Title Position
     */
    static final String TEXT_ALIGN_CENTER = "center";
    static final String TEXT_ALIGN_LEFT = "left";
    static final String TEXT_ALIGN_RIGHT = "right";
    /**
     * Chart Background Color
     */
    static final String BACKGROUND_COLOR = "#FFFFFF";
    /**
     * Line Color
     */
    static final String[] COLOR = {"#1E90FF", "#DC6093", "#FFB400", "#006400", "#906D3B",
                                   "#828282", "#803232", "#0000FF", "#464646", "#A0A0FF",
                                   "#FF8C8C", "#FF4646", "#54BD54", "#00E1FF", "#FF0000",
                                   "#FF8C0A", "#9F814F", "#787878", "#C32E61", "#28288C",};
    
    /**
     * Y-axle contents
     */
    static final String[] Y_axle = {"ProcessingTime",
                                    "GlobalAreaSize",
                                    "Throughput"};
    
    /**
     * Y-axle name
     */
    static final String[] Y_LEGEND = {"Business processing time (msec)",
                                      "Usage of Global area size (bytes)",
                                      "Business throughput (SMsg size/Proc time)"};
    /**
     * X-axle name
     */
    static final String X_LEGEND = "Operation Date";
    
}
