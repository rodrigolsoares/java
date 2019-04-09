package br.com.petruber.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class StringUtilites {

	private static String VIRGULA = ",";
	private static String PONTO = ".";
	private static String PT = "pt";
	private static String BR = "br";

	public static BigDecimal stringToBigDecimal(String value) {

		BigDecimal bValues = null;

		if (StringUtils.isNotEmpty(value)) {
			bValues = new BigDecimal(StringUtils.replace(value, VIRGULA, PONTO));
		}
		return bValues;
	}

	public static String bigDecimalToStringMaskMoney(BigDecimal value) {

		String valor = "";

		if (value != null) {
			DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00",
					new DecimalFormatSymbols(new Locale(PT, BR)));
			formatoDois.setMinimumFractionDigits(2);
			formatoDois.setParseBigDecimal(true);
			valor = formatoDois.format(value);
		}

		return valor;
	}

	public static String dataToStringMaskddMMyyyy(Date value) {

		String dateText = null;

		if (value != null) {
			dateText = new SimpleDateFormat("dd/MM/yyyy").format(value);
		}

		return dateText;
	}

	public static boolean stringValidate(String value) {
		return StringUtils.isEmpty(value);
	}

	public static boolean stringNotEmpty(String value) {
		return StringUtils.isNotEmpty(value);
	}

	public static Integer stringToInteger(String value) {
		return Integer.parseInt(value);
	}

	public static Boolean stringToBoolean(String value) {
		return value.equals("1")? true : false;
	}

}
