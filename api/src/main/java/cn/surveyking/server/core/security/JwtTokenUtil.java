package cn.surveyking.server.core.security;

import cn.surveyking.server.api.domain.dto.UserView;
import cn.surveyking.server.api.domain.mapper.UserViewMapper;
import cn.surveyking.server.api.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * @author javahuang
 * @date 2021/8/23
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

	private final ObjectMapper objectMapper;

	private final UserViewMapper userViewMapper;

	// 每次重启，所有客户端的 token 将失效
	private final String jwtSecret = generateSecurityKey();

	public String generateAccessToken(User user) {
		UserView userView = userViewMapper.toUserView(user);
		return Jwts.builder().serializeToJsonWith(new JacksonSerializer(objectMapper)).claim("user", userView)
				.setIssuedAt(new Date()).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token);
			return true;
		}
		catch (SignatureException ex) {
			log.error("Invalid JWT signature - {}", ex.getMessage());
		}
		catch (MalformedJwtException ex) {
			log.error("Invalid JWT token - {}", ex.getMessage());
		}
		catch (ExpiredJwtException ex) {
			log.error("Expired JWT token - {}", ex.getMessage());
		}
		catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token - {}", ex.getMessage());
		}
		catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty - {}", ex.getMessage());
		}
		return false;
	}

	public User getUser(String token) {
		return Jwts.parserBuilder().deserializeJsonWith(new JacksonDeserializer(Maps.of("user", User.class).build()))
				.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token).getBody()
				.get("user", User.class);
	}

	private static String generateSecurityKey() {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String secretString = Encoders.BASE64.encode(key.getEncoded());
		return secretString;
	}

}