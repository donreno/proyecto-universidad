package cl.envaflex.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final String FORMATO_DD_MM_YYYY  = "dd/MM/yyyy";
	public static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	private static GregorianCalendar calendar = new GregorianCalendar();
	
	public static Date sumarDias(Date fecha,int dias){
		calendar.setGregorianChange(fecha);
		calendar.set(GregorianCalendar.DAY_OF_YEAR,calendar.get(GregorianCalendar.DAY_OF_YEAR)+dias);
		return calendar.getTime();
	}
	
	public static String fechaToString(Date fecha, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String fec = sdf.format(fecha);
		return fec;
	}
	
	public static Date formatearFecha(Date fecha, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		try {
			fecha = sdf.parse(sdf.format(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

}
