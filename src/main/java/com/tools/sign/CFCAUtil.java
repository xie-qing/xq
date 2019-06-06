package com.tools.sign;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Title: CFCAUtil.java
 * @Package com.yinglian.common.cfca
 * @Description: TODO
 * @author funny
 * @date 2018年11月1日 上午9:23:07
 */
public class CFCAUtil {
//	private static final Logger log = (Logger) LoggerFactory.getLogger(CFCAUtil.class);

	private static X509Cert signCert = null;// 获取证书对象

	private static PrivateKey privateKey = null;// 获得私钥对象

	public static final String SIG_NALG = Mechanism.SHA256_RSA; // 签名算法

	private static Session session;

	private static final String CFCA_PATH = "conf/";

	private static final String PFX_FILE_PATH = "test.pfx";// pfx证书文件

	private static final String PFX_FILE_PWD = "cfca1234";// pfx密码

	static {
		try {
			// 步骤1 加密库初始化
			String deviceName = JCrypto.JSOFT_LIB;
			JCrypto.getInstance().initialize(deviceName, null);
			session = JCrypto.getInstance().openSession(deviceName);

			// 步骤2 构建证书对象
			signCert = CertUtil.getCertFromPFX(new FileInputStream(getRealPath(CFCA_PATH) + PFX_FILE_PATH),PFX_FILE_PWD);

			// 步骤3 获得私钥对象
			privateKey = KeyUtil.getPrivateKeyFromPFX(new FileInputStream(getRealPath(CFCA_PATH) + PFX_FILE_PATH),PFX_FILE_PWD);
		} catch (Exception e) {
//			log.info("Read key error");
			e.printStackTrace();
			throw new RuntimeException("获得私钥对象发生错误[" + e.getMessage() + "]");
		}
	}

	public static String getRealPath(String cerPath) {
		String url = CFCAUtil.class.getClassLoader().getResource(cerPath).getFile();
		return url;
	}

	/**
	 * 加签 P7消息签名(签名结果值[Base64])
	 * 
	 * @param msg
	 */
	public static String signMsg(String sourData) throws Exception {
		try {
			Signature signKit = new Signature();
			byte[] message = sourData.getBytes("UTF8");
			byte[] signedData = signKit.p7SignMessageDetach(SIG_NALG, message, privateKey, signCert, session);
			return new String(signedData);
		} catch (Exception e) {
			throw new RuntimeException("签名失败：", e);
		}
	}

	/**
	 * 验签
	 * @param signMsg 加签字符串
	 * @param singedMsg 请求的json字符串
	 * @return
	 */
	public static boolean checkSignMsg(String signMsg, Object JsonRquestParam) {
		boolean result = false;
		try {
			Signature signKit = new Signature();
			byte[] P7SignedData = signMsg.getBytes();
			String busiJson="";
			if(JsonRquestParam instanceof String) {
				busiJson= JsonRquestParam.toString();
			}else {
				busiJson= JSONObject.toJSONString(JsonRquestParam);
			}
			byte[] sourceData = busiJson.getBytes("UTF8");
			result = signKit.p7VerifyMessageDetach(sourceData, P7SignedData, session);
		} catch (Exception e) {
			throw new RuntimeException("验签失败：", e);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountType", "null");
		params.put("appId", "3a58c658e41242719a494677c8682632");
		params.put("companyCreditCode", "AA0123456789012345");
		params.put("sourceNo","1000342649");
		params.put("timestamp","1541048015935");
		String sourData = JSONObject.toJSONString(params); // 签名原文
//		log.info("json请求字符串:"+sourData);
		//加签 
		String signMsg = signMsg(sourData);
		//验签
		boolean result = checkSignMsg(signMsg,sourData);
		System.out.println("验签结果："+result);
	}
}
