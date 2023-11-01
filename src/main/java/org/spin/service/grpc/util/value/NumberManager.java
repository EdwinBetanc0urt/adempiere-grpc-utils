/************************************************************************************
 * Copyright (C) 2018-present E.R.P. Consultores y Asociados, C.A.                  *
 * Contributor(s): Edwin Betancourt, EdwinBetanc0urt@outlook.com                    *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program. If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.spin.service.grpc.util.value;

import java.math.BigDecimal;

import org.compiere.util.Util;

/**
 * Class for handle Number (BigDecimal, Integer, Double) values
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, https://github.com/EdwinBetanc0urt
 */
public class NumberManager {


	/**
	 * Validate if is numeric
	 * @param value
	 * @return
	 */
	public static boolean isNumeric(String value) {
		if(Util.isEmpty(value, true)) {
			return false;
		}
		//	
		return value.matches("[+-]?\\d*(\\.\\d+)?");
	}


	/**
	 * Is BigDecimal
	 * @param value
	 * @return
	 */
	public static boolean isBigDecimal(String value) {
		return getBigDecimalFromString(value) != null;
	}


	public static BigDecimal getBigDecimalFromObject(Object value) {
		BigDecimal numberValue = null;
		if (value == null) {
			return numberValue;
		}

		if (value instanceof String) {
			numberValue = getBigDecimalFromString(
				(String) value
			);
		} else if (value instanceof Integer) {
			Integer intValue = (Integer) value;
			numberValue = BigDecimal.valueOf(intValue);
		} else if (value instanceof Double) {
			numberValue = getBigDecimalFromDouble(
				(Double) value
			);
		} else if (value instanceof Long) {
			long longValue = (long) value;
			numberValue = BigDecimal.valueOf(longValue);
		} else {
			numberValue = (BigDecimal) value;
		}

		if (numberValue != null && numberValue.scale() <= 0) {
			numberValue = numberValue.setScale(2);
		}

		return numberValue;
	}


	/**
	 * Get BigDecimal number from String
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimalFromDouble(Double doubleValue) {
		BigDecimal numberValue = null;
		if (doubleValue == null) {
			return numberValue;
		}
		//	
		try {
			numberValue = BigDecimal.valueOf(doubleValue);
		} catch (Exception e) {
			
		}
		return numberValue;
	}

	/**
	 * Get BigDecimal number from String
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimalFromString(String stringValue) {
		BigDecimal numberValue = null;
		if (Util.isEmpty(stringValue, true)) {
			return numberValue;
		}
		//	
		try {
			numberValue = new BigDecimal(stringValue);
		} catch (Exception e) {
			
		}
		return numberValue;
	}



	public static Integer getIntegerFromObject(Object value) {
		Integer integerValue = null;
		if (value == null) {
			return integerValue;
		}
		if (value instanceof Integer) {
			integerValue = (Integer) value;
		} else if (value instanceof Long) {
			getIntegerFromLong(
				(Long) value
			);
		} else if(value instanceof BigDecimal) {
			integerValue = getIntegerFromBigDecimal(
				(BigDecimal) value
			);
		} else if (value instanceof String) {
			try {
				integerValue = Integer.valueOf(
					(String) value
				);
			} catch (Exception e) {
				integerValue = null;
			}
		}
		return integerValue;
	}


	public static Integer getIntegerFromLong(Long longValue) {
		Integer numberValue = null;
		if (longValue == null) {
			return numberValue;
		}
		numberValue = Math.toIntExact(longValue);
		return numberValue;
	}


	public static Integer getIntegerFromBigDecimal(BigDecimal bigDecimalValue) {
		Integer numberValue = null;
		if (bigDecimalValue == null) {
			return numberValue;
		}
		numberValue = bigDecimalValue.intValue();
		return numberValue;
	}

	/**
	 * Get Int value from String
	 * @param stringValue
	 * @return
	 */
	public static int getIntFromString(String stringValue) {
		int integerValue = 0;
		if (Util.isEmpty(stringValue, true)) {
			return 0;
		}
		try {
			integerValue = Integer.parseInt(stringValue);
		} catch (Exception e) {
			// log.severe(e.getLocalizedMessage());
		}
		return integerValue;
	}

}