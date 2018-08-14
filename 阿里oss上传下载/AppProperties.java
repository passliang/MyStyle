package com.zgqb.loan.app.properties;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 自定义参数
 * 
 * @author 秦瑞华
 *
 */
@Data
@ConfigurationProperties(prefix = "appConfig")
public class AppProperties {
	private String appName;
	private String appNameCn;
	/**
	 * 核心任务调用密钥
	 */
	private String secretOfLoanCoreTask;
	/**
	 * 核心服务调用密钥
	 */
	private String secretOfCoreService;
	/**
	 * 黑名单核心服务调用密钥
	 */
	private String secretOfListService;
	/**
	 * 加密秘钥
	 */
	private String tokenKey;
	/**
	 * 版本更新地址
	 */
	private String updateUrl;
	/**
	 * 连连签约 user_id前缀
	 */
	private String userIdPrefix;
	/**
	 * //短信模板
	 */
	private Map<String, String> smsTemplate;
	/**
	 * 	短信模板
	 */
	private Map<String, String> pmTemplate;
	/**
	 * 聚合支付商户号
	 */
	private String ebjMerid;
	/**
	 * 聚合支付秘钥
	 */
	private String ebjKey;
	/**
	 * 短信加密秘钥
	 */
	private String codeKey;
	/**
	 * 服务器签署文件保存路径
	 */
	private String signedFolderPrefix;
	/**
	 * 服务器保存图片路径
	 */
	private String sealDataImagePath;
    @NotNull
	private CreditWeb creditWeb;
	@NotNull
	private LianLian lianLian;
	@NotNull
	private Linkface linkface;
	@NotNull
	private MobileData mobileData;

    /**
     * 阿里OSS上传配置
     */
    @NotNull
    private AliOssConfig aliOssConfig;

    @Data
    public static class AliOssConfig {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
        private String key;

    }

	@Data
	public static class CreditWeb{
		private String rootPath;
		private String limitCode;
		private String currentCode;
		private String updatePath;
	}

	@Data
	public static class LianLian{
        /**
         * 签约申请
         */
		private String signApplicationUrl;
        /**
         * 查询卡bin信息
         */
		private String bankcardbinUrl;
        /**
         * 连连商户参数
         */
		private LianlianPartner lianlianPartner;
	}
	
	/**
	 * 连连商户参数
	 * 
	 * @author 秦瑞华
	 *
	 */
	@Data
	public static class LianlianPartner {
        /**
         * 公钥
         */
		private String pubKey;
        /**
         * 商户私钥
         */
		private String priKey;
        /**
         * MD5 KEY
         */
		private String md5Key;
        /**
         * 商户编号-收款账号
         */
		private String oidPartenerIn;
        /**
         * 商户编号-放款账号
         */
		private String oidPartenerOut;
        /**
         * 签名方式 RSA或MD5
         */
		private String signType;
	}

	@Data
	public static class Linkface{
		private String apiId;
		private String apiSecret;
		private String selfieIdnumberVerification;
		private String livenessIdnumberVerification;
		private String livenessWatermarkVerification;
		private String livenessSelfieVerification;
	}

	@Data
	public static class MobileData{
		private String partnerCode;
		private String partnerKey;
		private String createTaskUrl;
		private String queryTaskResultUrl;
		private String initTaskUrl;
		private String retryUrl;
	}
	
}

