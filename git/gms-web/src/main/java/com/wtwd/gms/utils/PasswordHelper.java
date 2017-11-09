package com.wtwd.gms.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.wtwd.gms.entity.WtUserInfo;

/**
 * 生成密码工具类
 *
 * @author: liufeng
 *
 * @date: 2017-6-29
 */
public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";

	private static String hashIteration = "2";

	private static int hashIterations = Integer.valueOf(hashIteration);

	public static void encryptPassword(WtUserInfo userInfo) {

//		pmsOperator.setsalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, userInfo.getUserPwd(), ByteSource.Util.bytes(userInfo.getCredentialsSalt()), hashIterations).toHex();

		userInfo.setUserPwd(newPassword);
	}

	/**
	 * 加密密码
	 * 
	 * @param loginPwd
	 *            明文密码
	 * @param salt
	 * @return
	 */
	public static String getPwd(String loginPwd, String salt) {
		String newPassword = new SimpleHash(algorithmName, loginPwd, ByteSource.Util.bytes(salt), hashIterations).toHex();
		return newPassword;
	}

}
