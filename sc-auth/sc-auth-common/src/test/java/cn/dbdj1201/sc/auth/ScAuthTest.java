package cn.dbdj1201.sc.auth;

import cn.dbdj1201.sc.auth.entity.UserInfo;
import cn.dbdj1201.sc.auth.utils.JwtUtils;
import cn.dbdj1201.sc.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author tyz1201
 * @datetime 2020-04-02 23:59
 **/

public class ScAuthTest {

    private static final String pubKeyPath = "D:\\test\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\test\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NTg0NDQxM30." +
                "WEuqnCP75e647dOoqyg0ut8hoX8J_TOaLCpEbNqVMdzh9u9w2JHpMX6Bg-" +
                "0ZiLXXZI3cTFxwdNDmIR0gtdq1iMfEyX02RYkG82OWPzs0g2cxWhaFjizh5lPthwbSHO1z-4wjHgR7T0JiH1oo0LSYHKl9HU3LcbS6EGP-UZjTz_M";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
