package simple_Programs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics_Date_Capture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date Dobj = new Date();
		
		System.out.println(Dobj);
		
		//how to capture the date
		//MM should be in the upper case always
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = simpledate.format(Dobj);
		System.out.println(simpledate.format(Dobj));
		System.out.println(actualDate);
		
		//how to capture date for the previous or the next 30 days 
		Calendar cal = simpledate.getCalendar();
		
		
		//from today if i want before 30 days 
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String dateRequired = simpledate.format(cal.getTime());
		System.out.println(dateRequired);
		
		//from today if i want after 30 days
		cal.add(Calendar.DAY_OF_MONTH, +30);
		String dateRequired1 = simpledate.format(cal.getTime());
		System.out.println(dateRequired1);
	}

}
