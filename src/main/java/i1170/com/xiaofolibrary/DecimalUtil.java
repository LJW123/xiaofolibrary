package i1170.com.xiaofolibrary;

import android.text.TextUtils;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author LXR
 * @date 2017/6/13 0013
 */

public class DecimalUtil {

    private static final String POINT = ".";
    private static final String PREFIX_0 = "0";

    private static DecimalFormat decimalFormat;

    /**
     * 限制输入框内为小数点后四位
     *
     * @param s
     */
    public static void keep4Decimal(CharSequence s, EditText etMoney) {
        if (s.toString().contains(POINT)) {
            if (s.length() - 1 - s.toString().indexOf(POINT) > 4) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(POINT) + 5);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
        }
        if (POINT.equals(s.toString().trim())) {
            s = PREFIX_0 + s;
            etMoney.setText(s);
            etMoney.setSelection(2);
        }

        if (s.toString().startsWith(PREFIX_0)
                && s.toString().trim().length() > 1) {
            if (!POINT.equals(s.toString().substring(1, 2))) {
                etMoney.setText(s.subSequence(0, 1));
                etMoney.setSelection(1);
            }
        }
    }

    /**
     * 限制输入框内为小数点后两位
     *
     * @param s
     * @param leng 小数点后的长度
     */
    public static void keepDecimal(CharSequence s, EditText etMoney, int leng) {
        //若输入数字包括小数点，则保留小数点后两位
        if (s.toString().contains(POINT)) {
            if (s.length() - 1 - s.toString().indexOf(POINT) > leng) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(POINT) + leng + 1);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
        }
        //若输入以小数点开头则前面补0
        if (POINT.equals(s.toString().trim())) {
            s = PREFIX_0 + s;
            etMoney.setText(s);
            etMoney.setSelection(2);
        }
//若输入以0开头，第二位不是小数点，则删除第二位，只保留输入0
        if (s.toString().startsWith(PREFIX_0)
                && s.toString().trim().length() > 1) {
            if (!POINT.equals(s.toString().substring(1, 2))) {
                etMoney.setText(s.subSequence(0, 1));
                etMoney.setSelection(1);
            }
        }
    }

    /**
     * 防止数据相乘会出现计算机计数法的数据
     *
     * @param value
     * @param cardinalNumber
     * @return
     */
    public static String convertDoubleToString(double value, double cardinalNumber) {
        BigDecimal baseValue = new BigDecimal(Double.toString(value));
        BigDecimal cardinalNumbers = new BigDecimal(Double.toString(cardinalNumber));
        BigDecimal result = baseValue.multiply(cardinalNumbers);
        return String.valueOf(result);
    }

    /**
     * 限制输入小数点后digit位
     *
     * @param s
     * @param etMoney
     * @param digit
     */
    public static void keepDigitDecimal(CharSequence s, EditText etMoney, int digit) {

        if (s.toString().contains(POINT)) {
            if (s.length() - 1 - s.toString().indexOf(POINT) > digit) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(POINT) + digit + 1);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
        }
        if (POINT.equals(s.toString().trim())) {
            s = PREFIX_0 + s;
            etMoney.setText(s);
            etMoney.setSelection(2);
        }

        if (s.toString().startsWith(PREFIX_0)
                && s.toString().trim().length() > 1) {
            if (!POINT.equals(s.toString().substring(1, 2))) {
                etMoney.setText(s.subSequence(0, 1));
                etMoney.setSelection(1);
            }
        }
    }

    /**
     * 限制输入框内为小数点后digit位
     *
     * @param s
     * @param etMoney
     * @param digit   小数点后位数
     * @return 是否符合规范
     */
    public static boolean keep2DigitDecimal(CharSequence s, EditText etMoney, int digit) {
        if (s.toString().contains(POINT)) {
            if (s.length() - 1 - s.toString().indexOf(POINT) > digit) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(POINT) + digit + 1);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
                return false;
            }
        }
        if (POINT.equals(s.toString().trim())) {
            s = PREFIX_0 + s;
            etMoney.setText(s);
            etMoney.setSelection(2);
            return false;
        }

        if (s.toString().startsWith(PREFIX_0)
                && s.toString().trim().length() > 1) {
            if (!POINT.equals(s.toString().substring(1, 2))) {
                etMoney.setText(s.subSequence(0, 1));
                etMoney.setSelection(1);
                return false;
            }
        }
        return true;
    }

    /**
     * 截取小数点后digits位数
     *
     * @param digits
     */
    public static String subDigitDecimal(String iconNum, int digits) {
        if (TextUtils.isEmpty(iconNum) || iconNum.equals(POINT)) {
            iconNum = PREFIX_0;
        }
        BigDecimal bigDecimal = new BigDecimal(iconNum);
        if (decimalFormat == null) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < digits; i++) {
                buffer.append(PREFIX_0);
            }
            decimalFormat = new DecimalFormat("#" + PREFIX_0 + POINT + buffer.toString());
        }
        return decimalFormat.format(bigDecimal.setScale(digits, BigDecimal.ROUND_FLOOR));
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(POINT) > 0) {
            //去掉多余的0
            s = s.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }
}
