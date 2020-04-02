package cn.dbdj1201.sc.sms.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tyz1201
 * @datetime 2020-04-02 13:31
 **/
@ConfigurationProperties(prefix = "sc.sms")
public class SmsProperties {
    String accessKeyId;             //阿里云账户权限id

    String accessKeySecret;         //阿里云账户密码

    String signName;                //短信签名

    String verifyCodeTemplate;      //短信验证码模板

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getVerifyCodeTemplate() {
        return verifyCodeTemplate;
    }

    public void setVerifyCodeTemplate(String verifyCodeTemplate) {
        this.verifyCodeTemplate = verifyCodeTemplate;
    }
}
