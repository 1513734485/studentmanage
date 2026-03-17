package com.example.xinji2023student.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map; public class JwtUtils {
    // 密钥，应该是一个足够复杂且保密的字符串
    private static final String SECRET_KEY = "your-secret-key"; // 令牌过期时间，单位为毫秒（这里设置为 1 小时）
    private static final long EXPIRATION_TIME = 3600 * 1000; // 生成令牌
    public static String generateToken(String username)
    { Map<String, Object> claims = new HashMap<>(); claims.put("sub", username); // 将用户名作为 JWT 的主题（subject）
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact(); }
    // 解析令牌并获取其声明（claims）
    public static Claims getClaimsByToken(String token) { return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody(); }
    // 验证令牌是否有效（例如，未过期）
// 注意：这个方法在您的代码示例中没有被直接使用，但通常是有用的
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
                        return true; } catch (Exception e) {

            return false; } }}