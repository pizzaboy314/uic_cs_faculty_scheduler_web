package data;

import java.lang.reflect.Field;

import org.springframework.ui.Model;

public abstract class FullcalendarConstants {
	//HTML Hex values for colors
	public static final String PRE200_COL_BG = "#E598F5";
	public static final String PRE200_COL_TX = "#000000";
	public static final String PRE300_COL_BG = "#FAFA48";
	public static final String PRE300_COL_TX = "#000000";
	public static final String PRE400_COL_BG = "#F74A4A";
	public static final String PRE400_COL_TX = "#ffffff";
	public static final String PRE500_COL_BG = "#6363FF";
	public static final String PRE500_COL_TX = "#ffffff";
	public static final String DEFAULT_COL_BG = "#63EB7E";
	public static final String DEFAULT_COL_TX = "#000000";
	
	//DEFUALT Length for date
	public static final String DEFAULT_EVENT_LENGTH = "01:00:00";//HH:MM:SS
				//Hardcoded to DEFAULT_EVENT_LENGTH in JSP hidden element
	

	public static void addColorAttributes(Model model) throws Exception {
		Field[] fds = FullcalendarConstants.class.getDeclaredFields();
		for (Field f : fds){
			model.addAttribute(f.getName(), f.get(null));
		}
	}
	
}
