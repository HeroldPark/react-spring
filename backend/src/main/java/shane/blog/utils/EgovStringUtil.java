package shane.blog.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//아래 lib 를 이용할 경우 검토 필요함.
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public final class EgovStringUtil
{
  private static final Logger LOGGER = LoggerFactory.getLogger(EgovStringUtil.class);
  private static final char WHITE_SPACE = ' ';

  public static boolean isNull(String str)
  {
    if (str != null) {
      str = str.trim();
    }

    return (str == null) || ("".equals(str));
  }

  public static boolean isAlpha(String str)
  {
    if (str == null) {
      return false;
    }

    int size = str.length();

    if (size == 0) {
      return false;
    }

    for (int i = 0; i < size; i++) {
      if (!Character.isLetter(str.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  public static boolean isAlphaNumeric(String str)
  {
    if (str == null) {
      return false;
    }

    int size = str.length();

    if (size == 0) {
      return false;
    }

    for (int i = 0; i < size; i++) {
      if (!Character.isLetterOrDigit(str.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  public static String integer2string(int integer)
  {
    return "" + integer;
  }

  public static String long2string(long longdata)
  {
    return String.valueOf(longdata);
  }

  public static String float2string(float floatdata)
  {
    return String.valueOf(floatdata);
  }

  public static String double2string(double doubledata)
  {
    return String.valueOf(doubledata);
  }

  public static String null2void(String str)
  {
    if (isNull(str)) {
      str = "";
    }

    return str;
  }

  public static int string2integer(String str)
  {
    if (isNull(str)) {
      return 0;
    }

    return Integer.parseInt(str);
  }

  public static float string2float(String str)
  {
    if (isNull(str)) {
      return 0.0F;
    }

    return Float.parseFloat(str);
  }

  public static double string2double(String str)
  {
    if (isNull(str)) {
      return 0.0D;
    }

    return Double.parseDouble(str);
  }

  public static long string2long(String str)
  {
    if (isNull(str)) {
      return 0L;
    }

    return Long.parseLong(str);
  }

  public static String null2string(String str, String defaultValue)
  {
    if (isNull(str)) {
      return defaultValue;
    }

    return str;
  }

  public static int string2integer(String str, int defaultValue)
  {
    if (isNull(str)) {
      return defaultValue;
    }

    return Integer.parseInt(str);
  }

  public static float string2float(String str, float defaultValue)
  {
    if (isNull(str)) {
      return defaultValue;
    }

    return Float.parseFloat(str);
  }

  public static double string2double(String str, double defaultValue)
  {
    if (isNull(str)) {
      return defaultValue;
    }

    return Double.parseDouble(str);
  }

  public static long string2long(String str, long defaultValue)
  {
    if (isNull(str)) {
      return defaultValue;
    }

    return Long.parseLong(str);
  }

  public static boolean equals(String source, String target)
  {
    return null2void(source).equals(null2void(target));
  }

  public static String toSubString(String str, int beginIndex, int endIndex)
  {
    if (equals(str, ""))
      return str;
    if (str.length() < beginIndex)
      return "";
    if (str.length() < endIndex) {
      return str.substring(beginIndex);
    }
    return str.substring(beginIndex, endIndex);
  }

  public static String toSubString(String source, int beginIndex)
  {
    if (equals(source, ""))
      return source;
    if (source.length() < beginIndex) {
      return "";
    }
    return source.substring(beginIndex);
  }

  public static int search(String source, String target)
  {
    int result = 0;
    String strCheck = new String(source);
    for (int i = 0; i < source.length(); ) {
      int loc = strCheck.indexOf(target);
      if (loc == -1) {
        break;
      }
      result++;
      i = loc + target.length();
      strCheck = strCheck.substring(i);
    }

    return result;
  }

  public static String trim(String str)
  {
    return str.trim();
  }

  public static String ltrim(String str)
  {
    int index = 0;

    while (' ' == str.charAt(index++));
    if (index > 0) {
      str = str.substring(index - 1);
    }

    return str;
  }

  public static String rtrim(String str)
  {
    int index = str.length();

    while (' ' == str.charAt(--index));
    if (index < str.length()) {
      str = str.substring(0, index + 1);
    }

    return str;
  }

  public static String concat(String str1, String str2)
  {
    StringBuffer sb = new StringBuffer(str1);
    sb.append(str2);

    return sb.toString();
  }

  public static String lPad(String str, int len, char pad)
  {
    return lPad(str, len, pad, false);
  }

  public static String lPad(String str, int len, char pad, boolean isTrim)
  {
    if (isNull(str)) {
      return null;
    }

    if (isTrim) {
      str = str.trim();
    }

    for (int i = str.length(); i < len; i++) {
      str = pad + str;
    }

    return str;
  }

  public static String rPad(String str, int len, char pad)
  {
    return rPad(str, len, pad, false);
  }

  public static String rPad(String str, int len, char pad, boolean isTrim)
  {
    if (isNull(str)) {
      return null;
    }

    if (isTrim) {
      str = str.trim();
    }

    for (int i = str.length(); i < len; i++) {
      str = str + pad;
    }

    return str;
  }

  public static String alignLeft(String str, int length)
  {
    return alignLeft(str, length, false);
  }

  public static String alignLeft(String str, int length, boolean isEllipsis)
  {
    if (str.length() <= length)
    {
      StringBuffer temp = new StringBuffer(str);
      for (int i = 0; i < length - str.length(); i++) {
        temp.append(' ');
      }
      return temp.toString();
    }
    if (isEllipsis)
    {
      StringBuffer temp = new StringBuffer(length);

      temp.append(str.substring(0, length - 3));

      temp.append("...");

      return temp.toString();
    }
    return str.substring(0, length);
  }

  public static String alignRight(String str, int length)
  {
    return alignRight(str, length, false);
  }

  public static String alignRight(String str, int length, boolean isEllipsis)
  {
    if (str.length() <= length)
    {
      StringBuffer temp = new StringBuffer(length);
      for (int i = 0; i < length - str.length(); i++) {
        temp.append(' ');
      }
      temp.append(str);
      return temp.toString();
    }
    if (isEllipsis)
    {
      StringBuffer temp = new StringBuffer(length);

      temp.append(str.substring(0, length - 3));

      temp.append("...");

      return temp.toString();
    }
    return str.substring(0, length);
  }

  public static String alignCenter(String str, int length)
  {
    return alignCenter(str, length, false);
  }

  public static String alignCenter(String str, int length, boolean isEllipsis)
  {
    if (str.length() <= length)
    {
      StringBuffer temp = new StringBuffer(length);
      int leftMargin = (length - str.length()) / 2;
      int rightMargin;
      if (leftMargin * 2 == length - str.length())
        rightMargin = leftMargin;
      else {
        rightMargin = leftMargin + 1;
      }

      for (int i = 0; i < leftMargin; i++) {
        temp.append(' ');
      }

      temp.append(str);

      for (int i = 0; i < rightMargin; i++) {
        temp.append(' ');
      }

      return temp.toString();
    }
    if (isEllipsis)
    {
      StringBuffer temp = new StringBuffer(length);

      temp.append(str.substring(0, length - 3));

      temp.append("...");

      return temp.toString();
    }
    return str.substring(0, length);
  }

  public static String capitalize(String str)
  {
    return !isNull(str) ? str.substring(0, 1).toUpperCase() + str
      .substring(1)
      .toLowerCase() : str;
  }

  public static boolean isPatternMatch(String str, String pattern)
    throws Exception
  {
    Matcher matcher = Pattern.compile(pattern).matcher(str);
    LOGGER.debug("{}", matcher);

    return matcher.matches();
  }

  public static String toEng(String kor)
    throws UnsupportedEncodingException
  {
    if (isNull(kor)) {
      return null;
    }

    return new String(kor.getBytes("KSC5601"), "8859_1");
  }

  public static String toKor(String en)
    throws UnsupportedEncodingException
  {
    if (isNull(en)) {
      return null;
    }

    return new String(en.getBytes("8859_1"), "euc-kr");
  }

  public static int countOf(String str, String charToFind)
  {
    int findLength = charToFind.length();
    int count = 0;

    for (int idx = str.indexOf(charToFind); idx >= 0; idx = str.indexOf(charToFind, idx + findLength)) {
      count++;
    }

    return count;
  }

  public static String encodePassword(String password, String algorithm)
  {
    byte[] unencodedPassword = password.getBytes();

    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance(algorithm);
    }
    catch (Exception e)
    {
      LOGGER.error("[" + e.getClass() + "] Try/Catch... Running : " + e.getMessage());

      return password;
    }

    md.reset();

    md.update(unencodedPassword);

    byte[] encodedPassword = md.digest();

    StringBuffer buf = new StringBuffer();

    for (int i = 0; i < encodedPassword.length; i++)
    {
      if ((encodedPassword[i] & 0xFF) < 16) {
        buf.append("0");
      }

      buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
    }

    return buf.toString();
  }

//  @Deprecated
//  public static String encodeString(String str)
//  {
//    BASE64Encoder encoder = new BASE64Encoder();
//    return new String(encoder.encodeBuffer(str.getBytes())).trim();
//  }
//
//  @Deprecated
//  public static String decodeString(String str)
//  {
//    BASE64Decoder dec = new BASE64Decoder();
//    try {
//      return new String(dec.decodeBuffer(str));
//    } catch (IOException io) {
//      throw new RuntimeException(io.getMessage(), io.getCause());
//    }
//  }

  public static String swapFirstLetterCase(String str)
  {
    StringBuffer sbuf = new StringBuffer(str);
    sbuf.deleteCharAt(0);
    if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0]))
      sbuf.insert(0, str.substring(0, 1).toUpperCase());
    else {
      sbuf.insert(0, str.substring(0, 1).toLowerCase());
    }
    return sbuf.toString();
  }

  public static String trim(String origString, String trimString)
  {
    int startPosit = origString.indexOf(trimString);
    if (startPosit != -1) {
      int endPosit = trimString.length() + startPosit;
      return origString.substring(0, startPosit) + origString.substring(endPosit);
    }
    return origString;
  }

  public static String getLastString(String origStr, String strToken)
  {
    StringTokenizer str = new StringTokenizer(origStr, strToken);
    String lastStr = "";
    while (str.hasMoreTokens()) {
      lastStr = str.nextToken();
    }
    return lastStr;
  }

  public static String[] getStringArray(String str, String strToken)
  {
    if (str.indexOf(strToken) != -1) {
      StringTokenizer st = new StringTokenizer(str, strToken);
      String[] stringArray = new String[st.countTokens()];
      for (int i = 0; st.hasMoreTokens(); i++) {
        stringArray[i] = st.nextToken();
      }
      return stringArray;
    }
    return new String[] { str };
  }

  public static boolean isNotEmpty(String str)
  {
    return !isEmpty(str);
  }

  public static boolean isEmpty(String str)
  {
    return (str == null) || (str.length() == 0);
  }

  public static String replace(String str, String replacedStr, String replaceStr)
  {
    String newStr = "";
    if (str.indexOf(replacedStr) != -1) {
      String s1 = str.substring(0, str.indexOf(replacedStr));
      String s2 = str.substring(str.indexOf(replacedStr) + 1);
      newStr = s1 + replaceStr + s2;
    }
    return newStr;
  }

  public static boolean isPatternMatching(String str, String pattern)
    throws Exception
  {
    if (pattern.indexOf(42) >= 0) {
      pattern = pattern.replaceAll("\\*", ".*");
    }

    pattern = "^" + pattern + "$";

    return Pattern.matches(pattern, str);
  }

  public static boolean containsMaxSequence(String str, String maxSeqNumber)
  {
    int occurence = 1;
    int max = string2integer(maxSeqNumber);
    if (str == null) {
      return false;
    }

    int sz = str.length();
    for (int i = 0; i < sz - 1; i++) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        occurence++;

        if (occurence == max)
          return true;
      }
      else {
        occurence = 1;
      }
    }
    return false;
  }

  public static boolean containsInvalidChars(String str, char[] invalidChars)
  {
    if ((str == null) || (invalidChars == null)) {
      return false;
    }
    int strSize = str.length();
    int validSize = invalidChars.length;
    for (int i = 0; i < strSize; i++) {
      char ch = str.charAt(i);
      for (int j = 0; j < validSize; j++) {
        if (invalidChars[j] == ch) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean containsInvalidChars(String str, String invalidChars)
  {
    if ((str == null) || (invalidChars == null)) {
      return true;
    }
    return containsInvalidChars(str, invalidChars.toCharArray());
  }

  public static boolean isNumeric(String str)
  {
    if (str == null) {
      return false;
    }
    int sz = str.length();
    if (sz == 0) {
      return false;
    }
    for (int i = 0; i < sz; i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static String reverse(String str)
  {
    if (str == null) {
      return null;
    }
    return new StringBuffer(str).reverse().toString();
  }

  public static String fillString(String originalStr, char ch, int cipers)
  {
    int originalStrLength = originalStr.length();

    if (cipers < originalStrLength) {
      return null;
    }

    int difference = cipers - originalStrLength;

    StringBuffer strBuf = new StringBuffer();
    for (int i = 0; i < difference; i++) {
      strBuf.append(ch);
    }

    strBuf.append(originalStr);
    return strBuf.toString();
  }

  public static final boolean isEmptyTrimmed(String foo)
  {
    return (foo == null) || (foo.trim().length() == 0);
  }

  public static List<String> getTokens(String lst, String separator)
  {
    List tokens = new ArrayList();

    if (lst != null) {
      StringTokenizer st = new StringTokenizer(lst, separator);
      while (st.hasMoreTokens()) {
        try {
          String en = st.nextToken().trim();
          tokens.add(en);
        }
        catch (IllegalArgumentException e)
        {
          LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Operation Running : " + e.getMessage());
        }
        catch (Exception e)
        {
          LOGGER.error("[" + e.getClass() + "] Try/Catch...usingParameters Operation Running : " + e.getMessage());
        }
      }
    }

    return tokens;
  }

  public static List<?> getTokens(String lst)
  {
    return getTokens(lst, ",");
  }

  public static String convertToCamelCase(String targetString, char posChar)
  {
    StringBuffer result = new StringBuffer();
    boolean nextUpper = false;
    String allLower = targetString.toLowerCase();

    for (int i = 0; i < allLower.length(); i++) {
      char currentChar = allLower.charAt(i);
      if (currentChar == posChar) {
        nextUpper = true;
      } else {
        if (nextUpper) {
          currentChar = Character.toUpperCase(currentChar);
          nextUpper = false;
        }
        result.append(currentChar);
      }
    }
    return result.toString();
  }

  public static String convertToCamelCase(String underScore)
  {
    return convertToCamelCase(underScore, '_');
  }

  public static String convertToUnderScore(String camelCase)
  {
    String result = "";
    for (int i = 0; i < camelCase.length(); i++) {
      char currentChar = camelCase.charAt(i);

      if ((i > 0) && (Character.isUpperCase(currentChar))) {
        result = result.concat("_");
      }
      result = result.concat(Character.toString(currentChar).toLowerCase());
    }
    return result;
  }
}