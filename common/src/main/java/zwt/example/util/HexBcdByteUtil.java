/**
 * 
 */
package zwt.example.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static zwt.example.util.HexCodec.hexEncode;


/**
 * @author xiongrl
 *
 */
public class HexBcdByteUtil {

	static final char[] HEX = new char[] { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' }; // !<HEX字节数组
	
	private static final String HEX_CHARS = "0123456789ABCDEF";
	
	/**
	 * 16进制转二进制
	 * @param hexString
	 * @return
	 */
	public static String hexString2binaryString(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	/**
	 * 高低位互换
	 * 
	 * @param hexString
	 * @return
	 */
	public static String heighToLow(String hexString) {
		int tmp = hexString.length() % 2;
		hexString = tmp == 0 ? hexString : "0" + hexString;
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte[] hexData = HexCodec.hexDecode(hexString);
		int i = 0;
		for (byte b : bytes) {
			len--;
			bytes[len] = hexData[i];
			i++;

		}
		return hexEncode(bytes);
	}

	/**
	 * 二进制转16进制
	 * 
	 * @param bString
	 * @return
	 */
	public static String binaryString2hexString(String bString) {
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)
			return null;
		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < bString.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return tmp.toString().toUpperCase();
	}
	
	/**
	 * 左补零
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String fillLeftZero(String str, int length) {
		int str_length = str.length();
		for (int i = 0; i < (length - str_length); i = i + 1) {
			str = '0' + str;
		}
		return str;
	}

	/**
	 * 右补零
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String fillRightZero(String str, int length) {
		int str_length = str.length();
		for (int i = 0; i < (length - str_length); i = i + 1) {
			str = str + '0';
		}
		return str;
	}

	/**
	 * 右补字符
	 * 
	 * @param str
	 * @param length
	 * @param lpad
	 * @return
	 */
	public static String addRightChar(String str, int length, String lpad) {
		if (str != null) {
			int str_length = str.length();
			for (int i = 0; i < (length - str_length); i = i + 1) {
				str = str + lpad;
			}
		}
		return str;
	}

	/**
	 * 左补字符
	 * 
	 * @param str
	 * @param length
	 * @param lpad
	 * @return
	 */
	public static String addLeftChar(String str, int length, String lpad) {
		if (str != null) {
			int str_length = str.length();
			for (int i = 0; i < (length - str_length); i = i + 1) {
				str = lpad + str;
			}
		}
		return str;
	}
	
	public static String removeLeftChar(String str, char c) {
		
		if (str == null) {
			return null;
		}
		
		int i=0;
		for(; i<str.length(); i++) {
			char value = str.charAt(i);
			if(value != c) {
				break;
			}
		}
				
		return str.substring(i);
	}

	/**
	 * 左补字符,按字节长度计算
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String addLeftChar(String str, int length, char c, String encode) {
		if (str == null) {
			str = "";
		}
		int str_length = 0;
		try {
			str_length = str.getBytes(encode).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < (length - str_length); i = i + 1) {
			str = c + str;
		}
		return str;
	}

    /**
     * 左补字符,按字节长度计算
     *
     * @param str
     * @param length
     * @return
     */
    public static String addLeftCharWithoutEncode(String str, int length, char c) {
        if (str == null) {
            str = "";
        }
        int str_length = 0;
        str_length = str.getBytes().length;
        for (int i = 0; i < (length * 2 - str_length); i = i + 1) {
            str = c + str;
        }
        return str;
    }

	/**
	 * 右补字符,按字节长度计算
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String addRightChar(String str, int length, char c, String encode) {
		if (str == null) {
			str = "";
		}
		int str_length = 0;
		try {
			str_length = str.getBytes(encode).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < (length - str_length); i = i + 1) {
			str = str + c;
		}
		return str;
	}

    /**
     * 右补字符,按字节长度计算
     *
     * @param str
     * @param length
     * @return
     */
    public static String addRightCharWithoutEncode(String str, int length, char c) {
        if (str == null) {
            str = "";
        }
        int str_length = 0;
        str_length = str.getBytes().length;
        for (int i = 0; i < (length * 2 - str_length); i = i + 1) {
            str = str + c;
        }
        return str;
    }

	/**
	 * 获取特定字节长度的字符串
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getByteLengthStr(String str, int length, String encode) {
		if (str == null) {
			str = "";
		}
		int str_length = 0;
		try {
			str_length = str.getBytes(encode).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (str_length <= length) {
			return str;
		} else {
			byte[] byteTmp = new byte[length];
			String strTmp = "";
			try {
				byte[] strByte = str.getBytes(encode);

				for (int i = 0; i < length; i++) {
					byteTmp[i] = strByte[i];
				}
				strTmp = new String(byteTmp, encode);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return strTmp;
		}
	}



	public static String getStringByByteNumber(String dealString, int startIndex, int byteLength, String charset) {
		String result = "";
		try {
			byte[] stringBytes = dealString.getBytes(charset);
			if (stringBytes.length < byteLength) {
				return result;
			}
			byte[] bytes = new byte[byteLength];
			for (int i = 0; i < byteLength; i++) {
				bytes[i] = stringBytes[startIndex + i];
			}
			result = new String(bytes, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static byte bswap(byte a) {
		byte b = 0;
		for (int i = 0; i < 8; ++i)
			b |= ((a & (1 << i)) == 0 ? 0 : 1) << (7 - i);
		return b;
	}

	/**
	 * 字节数据相加
	 * 
	 * @param sByte
	 * @param aByte
	 * @return
	 */
	public static byte[] byteArrayAdd(byte[] sByte, byte[] aByte) {
		if(sByte == null && aByte == null) {
			return null;
		}else if(sByte == null) {
			return aByte;
		}else if(aByte == null) {
			return sByte;
		}
		
		byte[] tByte = new byte[sByte.length + aByte.length];
		for (int i = 0; i < sByte.length; i++) {
			tByte[i] = sByte[i];
		}
		for (int i = 0; i < aByte.length; i++) {
			tByte[sByte.length + i] = aByte[i];
		}
		return tByte;
	}

	/**
	 * 在字节数组获取，从第index开始，length长度的字节数
	 * 
	 * @param byteArray
	 * @param index
	 *            从0开始
	 * @param length
	 * @return
	 */
	public static byte[] getBytes(byte[] byteArray, int index, int length) {
		byte[] getByteArray = new byte[length];

		for (int i = 0; i < length; i++) {
			getByteArray[i] = byteArray[index + i];
		}

		return getByteArray;
	}

	/**
	 * 根据每段length长度分隔字符串
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static List<String> splitString(String str, int length) {
		String strHex = "";
		length = length * 2;
		try {
			strHex = bytesToHexString(str.getBytes("UTF-8"));
			str = strHex;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		List<String> stringList = new ArrayList<String>();
		int strLength = str.length();
		int num = strLength / length;

		try {
			for (int i = 0; i < num; i++) {
				String strtmp = str.substring(i * length, (i + 1) * length);
				stringList.add(new String(hexStringToBytes(strtmp), "UTF-8"));
			}

			if (strLength % length != 0) {
				String strtmp = str.substring(num * length);
				stringList.add(new String(hexStringToBytes(strtmp), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return stringList;
	}

	/**
	 * 左补字符串
	 * 
	 * @param str
	 * @param length
	 * @param padStr
	 * @return
	 */
	public static String lpadStr(String str, int length, String padStr) {
		if (str == null) {
			str = "";
		}
		String lpadStr = str;
		while (true) {
			int strLength = lpadStr.length();
			if (strLength >= length) {
				break;
			} else {
				lpadStr = padStr + lpadStr;
			}
		}
		return lpadStr;
	}

	/**
	 * 右补字符串
	 * 
	 * @param str
	 * @param length
	 * @param padStr
	 * @return
	 */
	public static String rpadStr(String str, int length, String padStr) {
		if (str == null) {
			str = "";
		}
		String lpadStr = str;
		while (true) {
			int strLength = lpadStr.length();
			if (strLength >= length) {
				break;
			} else {
				lpadStr = lpadStr + padStr;
			}
		}
		return lpadStr;
	}
	
	/**
	 * 字节转换二进制
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteToBinary(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			byte b = byteArray[i];
			int z = b;
			z |= 256;
			String str = Integer.toBinaryString(z);
			int len = str.length();
			sb.append(str.substring(len - 8, len));
		}
		return sb.toString();
	}

	/**
	 * 二进制转换字节
	 * 
	 * @param byteArray
	 * @return
	 */
	public static byte[] binaryToByte(String bString) {
		byte[] bByte = new byte[0];
		for (int k = 0; k < bString.length() / 8; k++) {
			String bStringTmp = bString.substring(k * 8, (k + 1) * 8);
			byte result = 0;
			for (int i = bStringTmp.length() - 1, j = 0; i >= 0; i--, j++) {
				result += (Byte.parseByte(bStringTmp.charAt(i) + "") * Math.pow(2, j));
			}

			byte[] aByte = new byte[1];
			aByte[0] = result;

			bByte = byteArrayAdd(bByte, aByte);
		}

		return bByte;

	}
	
	/**
	 * Convert byte[] to hex string.
	 * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src
	 *            byte[] data
	 * @return hex string
	 */

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return "";
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}
	
	/**
	 * Convert byte[] to hex string.   小端模式（低位在前，高位在后）
	 * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src
	 *            byte[] data
	 * @return hex string
	 */

	public static String bytesToHexStringLowToHigh(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return "";
		}
		for (int i = src.length-1; i >= 0; i--) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	/**
	 * 将int转为低字节在前，高字节在后的byte数组
	 * 
	 * @param n
	 *            int
	 * @return byte[]
	 */
	public static byte[] lowToHigh(int n) {
		byte[] b = new byte[7];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * 将int转为高字节在前，低字节在后的byte数组
	 * 
	 * @param n
	 *            int
	 * @return byte[]
	 */
	public static byte[] highToLow(int n) {
		byte[] b = new byte[7];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}
	
	/**
	 * 将16进制字符串转换为字节数组
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	
	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) HEX_CHARS.indexOf(c);
	}
	
	 /**
     * Converts a byte array to hex string.
     * 
     * @param b -
     *            the input byte array
     * @return hex string representation of b.
     */
    
    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }
    
    public static String bcdToHexString(int value) {
    	StringBuffer sb = new StringBuffer();
    	if(value > 0xFFFF) {
    		sb.append(HEX_CHARS.charAt(value >>> 12 & 0x0F));
    		sb.append(HEX_CHARS.charAt(value >>> 8 & 0x0F));
    		sb.append(HEX_CHARS.charAt(value >>> 4 & 0x0F));
            sb.append(HEX_CHARS.charAt(value & 0x0F));
    	}else {
    		sb.append(HEX_CHARS.charAt(value >>> 4 & 0x0F));
            sb.append(HEX_CHARS.charAt(value & 0x0F));
    	}
    	return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     * 
     * @param s -
     *            string to be converted
     * @return byte array converted from s
     */
    public static byte[] hexToByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }
    
    /**
	 * @功能: BCD码转为10进制串(阿拉伯数据)
	 * @参数: BCD码
	 * @结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}
	
	public static String str2BcdStr(String asc) {
		
		byte[] bytes = str2Bcd(asc);
		
		return toHexString(bytes);
	}

	/**
	 * @功能: 10进制串转为BCD码
	 * @参数: 10进制串
	 * @结果: BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		
		return bbt;
	}
	
	/**
	 * 将字符串转为指定字节长度的HEX字符串，不足右补0
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String stringtoHexAndFillZeroRight(String str, int length) throws UnsupportedEncodingException {
		String StringGBK = bytesToHexString(str.getBytes("GBK"));		
		StringGBK = fillRightZeroForByte(StringGBK, length);
		return StringGBK;
	}

    /**
     * 将字符串转为指定字节长度的HEX字符串，不足右补空格
     * @param str
     * @param length
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String stringtoHexAndFillSpaceRight(String str, int length) throws UnsupportedEncodingException {
        String StringGBK = addRightChar(str, length, " ");
        StringGBK = bytesToHexString(StringGBK.getBytes("GBK"));
        return StringGBK;
    }
	/**
	 * 将字符串转为指定字节长度的HEX字符串，不足左补0
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String stringToHexAndFillZeroLeft(String str, int length) throws UnsupportedEncodingException {
		String StringGBK = bytesToHexString(str.getBytes("GBK"));		
		StringGBK = fillLeftZeroForByte(StringGBK, length);
		return StringGBK;
	}
	
	/**
	 * 将BCD字符串转为指定字节长度的HEX字符串，不足左补0
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String bcdToHexAndFillZeroRight(String str, int length) throws UnsupportedEncodingException {
		String hexString = Integer.toHexString(Integer.parseInt(str));
		hexString = fillRightZeroForByte(hexString, length);
		return hexString;
	}
	
	/**
	 * 将BCD字符串转为指定字节长度的HEX字符串，不足左补0
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String bcdToHexAndFillZeroLeft(String str, int length) throws UnsupportedEncodingException {
		if (StringUtil.isBlank(str)) {
		    str = "0";
        }
	    String hexString = Integer.toHexString(Integer.parseInt(str));
		hexString = fillLeftZeroForByte(hexString, length);
		return hexString;
	}

	public static String checkInputStr (String str, int length) {
	    if (str == null) {
	        str = " ";
        }
        if (str.length() > length * 2) {
	        str = str.substring(0, length * 2);
        }
        return str;
    }
	
	/**
	 * 左补零按字节长度
	 * 
	 * @param str
	 * @param length字节长度
	 * @return
	 */
	public static String fillLeftZeroForByte(String str, int length) {
	    str = checkInputStr(str, length);
		int str_length = str.length();
		for (int i = 0; i < (length*2 - str_length); i = i + 1) {
			str = '0' + str;
		}
		return str;
	}

	/**
	 * 右补零按字节长度
	 * 
	 * @param str
	 * @param length 字节长度
	 * @return
	 */
	public static String fillRightZeroForByte(String str, int length) {
		int str_length = str.length();
		for (int i = 0; i < (length*2 - str_length); i = i + 1) {
			str = str + '0';
		}
		return str;
	}
	
	/**
	 * 计算出字符串*100的整形
	 * @param str
	 * @return
	 */
	public static int stringMultiplyOneHundred(String str){
		BigDecimal b1 = new BigDecimal(str);   
		BigDecimal b2 = new BigDecimal("100");   
		return b1.multiply(b2).intValue();   
	}   
	
	   /**
	    * 
	   * @Title: judgeExistFlag
	   * @Description: 判断字符串是否在数组队列中存在，效果等同于oracle的in
	   * @param @param judgeStr 待判断的字符串
	   * @param @param strArray 字符串数组
	   * @param @return    设定文件
	   * @return boolean    返回类型
	   * Copyright (c) 2015 福建索天信息科技有限公司 版权所有
	   * Sys-tech Co. Ltd. All rights reserved.
	   * @throws
	    */
	   public static boolean judgeExistFlag(String judgeStr,String [] strArray){
		   boolean flag = false;
		   for (String string : strArray) {
			   if(judgeStr.equals(string)){
				   flag = true;
				   break;
			   }
		   }
		   return flag; 
	   }
	   
	/**
	 * 将HEX转中文字符显示
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String hexToStringGBK(String str) throws UnsupportedEncodingException {
		return new String(hexToByteArray(str), "GBK");
	}


    /**
     * 字符串转16进制表示的ASCII码，按字节计算，不足右补空格，超过截取前length字节数
     * @param s
     * @param length
     * @return
     */
	public static String stringToHexAsciiRightWhiteSpace(String s, int length){
	    s = checkInputStr(s, length);
        byte[] b = s.getBytes();
        int[] in = new int[b.length];
        for (int i = 0; i < in.length; i++) {
            in[i] = b[i]&0xff;
        }
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < in.length; j++) {
            sb.append(Integer.toString(in[j], 0x10));
        }
        Integer tempLen = sb.length();
        Integer numOfSpace = (length * 2 - tempLen)/2;
        for (int k = 0; k < numOfSpace; k ++) {
            sb.append("  ");
        }
        if (sb.length() > length * 2) {
            return sb.toString().substring(0, length * 2);
        }
        return sb.toString();
    }

    /**
     * 含中文的字符串转ASCII码，并用空格补足长度
     * @param str
     * @param length
     * @return
     */
    public static String encodeChineseToAsciiRightPadWhiteSpace(String str, int length){
	    str = checkInputStr(str, length);
        String temp = null;
        try {
            temp = bytesToHexString(str.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        while (temp.length() < length * 2) {
            temp = temp + " ";
        }
        return temp;
    }


	public static void main(String[] args) throws UnsupportedEncodingException {
		String a = "255";
		/*System.out.println(bcdToHexAndFillZeroLeft(a,2));
		System.out.println(fillLeftZeroForByte(a,2));
        byte [] b = HexCodec.hexDecode(bcdToHexAndFillZeroLeft(a,2));
        System.out.println(hexEncode(b));
        String aa = "116.4171998         40.06497701         ";
        String tmp = hexEncode(a.getBytes());
        System.out.println(tmp);
        System.out.println(HexCodec.asciiTOHex(tmp));*/

        //System.out.println(stringtoHexAndFillSpaceRight(a, 4));;

        //System.out.println(stringToHexAsciiRightWhiteSpace("01011000", 11));

    }
}
