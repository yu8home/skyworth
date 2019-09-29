package com.neusoft.base.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import cn.hutool.core.date.DatePattern;

/**
 * poi
 *
 * @author：yu8home
 * @date：2018年3月6日 上午11:19:57
 */
public class PoiUtils {

    // 年与日
    public static Calendar getCellCalendarValue(Cell cell) throws ParseException {
        return PoiUtils.getCellCalendarValue(cell, DatePattern.NORM_DATE_PATTERN);
    }

    public static Calendar getCellCalendarValue(Cell cell, String df) throws ParseException {
        Calendar rs = null;

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.STRING) {
            SimpleDateFormat sdf = new SimpleDateFormat(df);
            Date date = sdf.parse(cell.getStringCellValue().trim());
            rs = Calendar.getInstance();
            rs.setTime(date);
        } else if (cell.getCellType() == CellType.NUMERIC) {
            rs = Calendar.getInstance();
            rs.setTime(cell.getDateCellValue());
        }
        return rs;
    }

    public static String getCellStringValue(Cell cell) {
        String rs = "";

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            NumberFormat nf = NumberFormat.getInstance();
            // nf.setGroupingUsed(false);// 千分位
            rs = nf.format(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            rs = cell.getStringCellValue().trim();
        }
        return rs;
    }

    public static BigDecimal getCellBigDecimalValue(Cell cell) {
        BigDecimal rs = null;

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            rs = BigDecimal.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue().trim();

            if (str.contains(".")) {
                str = str.substring(0, str.indexOf("."));
            }
            if (!"".equals(str)) {
                rs = BigDecimal.valueOf(Double.parseDouble(str));
            }
        }
        return rs;
    }

    public static Double getCellDoubleValue(Cell cell) {
        Double rs = null;

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            rs = (Double) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue().trim();

            if (str.contains(".")) {
                str = str.substring(0, str.indexOf("."));
            }
            if (!"".equals(str)) {
                rs = Double.parseDouble(str);
            }
        }
        return rs;
    }

    public static Long getCellLongValue(Cell cell) {
        Long rs = null;

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            rs = (long) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue().trim();

            if (str.contains(".")) {
                str = str.substring(0, str.indexOf("."));
            }
            if (!"".equals(str)) {
                rs = Long.parseLong(str);
            }
        }
        return rs;
    }

    public static Integer getCellIntegerValue(Cell cell) {
        Integer rs = null;

        if (null == cell) {
            return rs;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            rs = (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue().trim();

            if (str.contains(".")) {
                str = str.substring(0, str.indexOf("."));
            }
            if (!"".equals(str)) {
                rs = Integer.parseInt(str);
            }
        }
        return rs;
    }

    ///////////////////////////////////////////////////////////////// 校验：是否为空、转换是否有错/////////////////////////////////////////////////

    public static Calendar getCellCalendarValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellCalendarValue(cell, errList, text, false);
    }

    public static Calendar getCellCalendarNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellCalendarValue(cell, errList, text, true);
    }

    public static Calendar getCellCalendarValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        Calendar rs = null;
        try {
            rs = PoiUtils.getCellCalendarValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    public static Calendar getCellCalendarSEC(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellCalendarValue(cell, errList, text, false);
    }

    public static Calendar getCellCalendarSECNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellCalendarValue(cell, errList, text, true);
    }

    public static Calendar getCellCalendarSEC(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        Calendar rs = null;
        try {
            rs = PoiUtils.getCellCalendarValue(cell, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////// String/////////////////////////////////////////////////////////////////////////

    public static String getCellStringValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellStringValue(cell, errList, text, false);
    }

    public static String getCellStringNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellStringValue(cell, errList, text, true);
    }

    public static String getCellStringValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        String rs = null;
        try {
            rs = PoiUtils.getCellStringValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (StringUtils.isBlank(rs) && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////// BigDecimal/////////////////////////////////////////////////////////////////////////

    public static BigDecimal getCellBigDecimalValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellBigDecimalValue(cell, errList, text, false);
    }

    public static BigDecimal getCellBigDecimalNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellBigDecimalValue(cell, errList, text, true);
    }

    public static BigDecimal getCellBigDecimalValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        BigDecimal rs = null;
        try {
            rs = PoiUtils.getCellBigDecimalValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////// Double/////////////////////////////////////////////////////////////////////////

    public static Double getCellDoubleValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellDoubleValue(cell, errList, text, false);
    }

    public static Double getCellDoubleNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellDoubleValue(cell, errList, text, true);
    }

    public static Double getCellDoubleValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        Double rs = null;
        try {
            rs = PoiUtils.getCellDoubleValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////// Long/////////////////////////////////////////////////////////////////////////

    public static String getCellLongValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellStringValue(cell, errList, text, false);
    }

    public static String getCellLongNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellStringValue(cell, errList, text, true);
    }

    public static Long getCellLongValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        Long rs = null;
        try {
            rs = PoiUtils.getCellLongValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////// Integer/////////////////////////////////////////////////////////////////////////

    public static Integer getCellIntegerValue(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellIntegerValue(cell, errList, text, false);
    }

    public static Integer getCellIntegerNull(Cell cell, List<String> errList, String text) {
        return PoiUtils.getCellIntegerValue(cell, errList, text, true);
    }

    public static Integer getCellIntegerValue(Cell cell, List<String> errList, String text, boolean isAllowNull) {
        Integer rs = null;
        try {
            rs = PoiUtils.getCellIntegerValue(cell);
        } catch (Exception e) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
            e.printStackTrace();
        }

        if (rs == null && !isAllowNull) {
            if (!errList.contains(text)) {
                errList.add(text);
            }
        }
        return rs;
    }

    /**
     * xlsx下拉框
     */
    public static void setDataValidation(XSSFSheet sheet, String[] datas, int firstRow, int endRow, int firstCol, int endCol) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(datas);
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(constraint, regions);
        validation.setShowErrorBox(true);
        sheet.addValidationData(validation);
    }

}