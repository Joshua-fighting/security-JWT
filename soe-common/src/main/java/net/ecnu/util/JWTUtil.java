package net.ecnu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import net.ecnu.model.LoginUser;

import java.util.Date;

@Slf4j
public class JWTUtil {

    /**
     * 主题
     */
    private static final String SUBJECT = "smart-oral-evaluation";

    /**
     * 加密密钥
     */
    private static final String SECRET = "smart-oral-evaluation";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "soe-token-";

    /**
     * token过期时间，7天
     */
    private static final long EXPIRED = 1000 * 60 * 60 * 24 * 7;

    /**
     * 生成JWT
     */
    public static String geneJsonWebToken(LoginUser loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("loginUser can't be null when geneJsonWebToken");
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("loginUser", loginUser)
                .setIssuedAt(new Date())
                .setExpiration(new Date(CommonUtil.getCurrentTimestamp() + EXPIRED))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    /**
     * 解密JWT
     */
    public static LoginUser checkJWT(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            String loginUserJson = JsonUtil.obj2Json(claims.get("loginUser"));
            return JsonUtil.json2Obj(loginUserJson, LoginUser.class);
        } catch (Exception e) {
            log.error("JWT解密失败");
            return null;
        }
    }

    public static void main(String[] args) {
        LoginUser build = LoginUser.builder().accountNo("user_1589156313248698368")
                    .phone("13511462467").build();
        String s = geneJsonWebToken(build);
        log.info("加密token = {}",s);
    }
}
