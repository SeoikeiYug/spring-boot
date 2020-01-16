package com.genius.spring.boot.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.genius.spring.boot.security.common.Constants;
import com.genius.spring.boot.security.common.Status;
import com.genius.spring.boot.security.config.JwtConfig;
import com.genius.spring.boot.security.expection.SecurityException;
import com.genius.spring.boot.security.vo.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @类名 JwtUtil
 * @描述 Jwt 工具类
 * @作者 shuaiqi
 * @创建日期 2020/1/13 11:58
 * @版本号 1.0
 * @参考地址
 **/
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil {

	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 创建JWT
	 *
	 * @param rememberMe   记住我
	 * @param id           用户Id
	 * @param subject      用户名
	 * @param roles        用户角色
	 * @param authorities  用户权限
	 * @return JWT
	 */
	public String createJWT(boolean rememberMe, Long id, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities) {
		final Date now = new Date();
		final JwtBuilder builder = Jwts.builder().setId(id.toString()).setSubject(subject).setIssuedAt(now).
				signWith(SignatureAlgorithm.HS256, jwtConfig.getKey()).
				claim("roles", roles).claim("authorities", authorities);

		//设置过期时间
		Long ttl = rememberMe ? jwtConfig.getRemember() : jwtConfig.getTtl();
		if (ttl > 0) {
			builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
		}

		final String jwt = builder.compact();
		//将生成的JWT保存到redis
		stringRedisTemplate.opsForValue().set(Constants.REDIS_JWT_KEY_PREFIX + subject, jwt, ttl, TimeUnit.MILLISECONDS);

		return jwt;
	}

	public String createJWT(Authentication authentication, Boolean rememberMe) {
		final UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
		return createJWT(rememberMe, principal.getId(), principal.getUsername(), principal.getRoles(), principal.getAuthorities());
	}

	/**
	 * 解析jwt
	 *
	 * @param jwt JWT
	 * @return {@link Claims}
	 */
	public Claims parseJWT(String jwt) {
		final Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt).getBody();

		final String username = claims.getSubject();
		final String redisKey = Constants.REDIS_JWT_KEY_PREFIX + username;

		//检验redis中的JWT是否存在
		final Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
		if (Objects.isNull(expire) || expire <= 0) {
			throw new SecurityException(Status.TOKEN_EXPIRED);
		}
		return null;
	}

	/**
	 * 从request的header中获取JWT
	 *
	 * @param request request请求
	 * @return JWT
	 */
	public String getJwtFromRequest(HttpServletRequest request) {
		final String bearerToken = request.getHeader("Authorization");
		if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}