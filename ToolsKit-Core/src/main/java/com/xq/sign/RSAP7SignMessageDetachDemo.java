package com.xq.sign;


import java.io.FileInputStream;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * 
 * @Author funny
 * @Description RSAP7分离式签名demo
 * @CodeReviewer
 * 对外提供加签验签样例
 */
public class RSAP7SignMessageDetachDemo {
    public static void main(String[] args) throws Exception {
        
        //步骤1 加密库初始化
        String deviceName = JCrypto.JSOFT_LIB;
        JCrypto.getInstance().initialize(deviceName, null);
        Session session = JCrypto.getInstance().openSession(deviceName);

        //步骤2 构建证书对象
        
        String pfxFilePath="D:\\XQ\\works\\ToolsKit\\src\\main\\java\\com\\tools\\sign\\test.pfx"; //pfx证书文件
        String pfxFilePwd = "cfca1234"; //pfx密码
        X509Cert signCert = CertUtil.getCertFromPFX(new FileInputStream(pfxFilePath), pfxFilePwd);
        
       
        //步骤3 获得私钥对象
        PrivateKey privateKey = KeyUtil.getPrivateKeyFromPFX(new FileInputStream(pfxFilePath), pfxFilePwd);

        //步骤4 P7消息签名
        Signature signKit = new Signature();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId","szfl3ca3f0efd3-d0a100536412513");
        params.put("sourceNo","194221659");
        params.put("timestamp","1556454149879");
//        params.put("accountType", "AC02");
//        params.put("corpNo","33");
//        params.put("outCustomerId","5242513546135492393");
//        params.put("frontNotifyUrl","http://www.baidu.com");
//        params.put("notifyUrl","http://www.baidu.com");
//        params.put("certNo","51343620000428773X");
//        params.put("userMobile","13888888888");
//        params.put("realName","葛枝");
//        params.put("mobile","13888888888");
//        params.put("bankCardNo","6215581813000338466");
//        params.put("bankCardName","葛枝");
//        params.put("bankNo","12534565689");
//        params.put("bankName","中国工商银行");
    	params.put("queryType","1");
//    	params.put("idcardNo","230502199001083420");
//    	params.put("outCustomerId","WW003");
//    	params.put("accountId","PER00000000000000228");
//    	params.put("companyCreditCode","915100007118817777");
//    	params.put("corpNo","QY00045005");
//    	params.put("outCustomerId","ABCDFEG001255");
//    	params.put("notifyUrl","/sucess");
//    	params.put("certNo","440982199301065879");
//    	params.put("userMobile","18899988824");
//    	params.put("realName","测试001");
//    	params.put("email","15711999999@163.com");
//    	params.put("tradeMemBerGrade","1");
//    	params.put("isMessager","2");
		String sourData = JSONObject.toJSONString(sortMapByKey(params)); // 签名原文
		System.out.println("json请求字符串:"+sourData);
		
        byte[] message = sourData.getBytes("UTF8");
        String signAlg = Mechanism.SHA256_RSA;
        byte[] signedData = signKit.p7SignMessageDetach(signAlg, message, privateKey, signCert, session);
        Map<String, Object> showMap = sortMapByKey(params);
        
        String signMsg = new String(signedData);
        System.out.println("签名结果值[Base64]: " + signMsg);
        showMap.put("signature", signMsg);
        String showJson = JSONObject.toJSONString(showMap);
        System.out.println("请求参数： " + showJson);
        boolean result = checkSignMsg(signMsg,sourData);
		System.out.println("验签结果："+result);
    }
    
    /**
	 * 验签
	 * @param signMsg 加签字符串
	 * @param singedMsg 请求的json字符串
	 * @return
	 */
	public static boolean checkSignMsg(String signMsg, String JsonRquestParam) {
		boolean result = false;
		try {
	        Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
			Signature signKit = new Signature();
			byte[] P7SignedData = signMsg.getBytes();
			String busiJson = JsonRquestParam;
			byte[] sourceData = busiJson.getBytes("UTF8");
			result = signKit.p7VerifyMessageDetach(sourceData, P7SignedData, session);
		} catch (Exception e) {
			throw new RuntimeException("验签失败：", e);
		}
		return result;
	}
	
	public static Map<String, Object> sortMapByKey(Map<String, Object> params) {
		return new TreeMap<String, Object>(params);
	}
    
}
