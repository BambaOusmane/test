package com.school.school.security.Config;

import com.school.school.security.Entity.Jwt;
import com.school.school.security.Entity.RefreshToken;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Repository.JwtRepository;
import com.school.school.security.Service.UtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@AllArgsConstructor
@Service
public class JwtService {
    public static final String BEARER = "bearer";
    public static final String REFRESH = "refresh";
    public static final String TOKEN_INVALIDE = "Token invalide";
    private final String ENCRYPTION_KEY = "8840a3d54f3304438e23c4b3bf8c0148aa04e34af9c42b8e0125e1ff7b81ce9c";
    private UtilisateurService utilisateurService;
    private JwtRepository jwtRepository;

    public Jwt tokenByValue(String value) {
        return this.jwtRepository.findByValeurAndDesactiveAndExpire(
                value,
                        false,
                        false)
                .orElseThrow(()-> new RuntimeException("okdfdsfdsgdsgfdgfv"));
    }
    public Map<String ,String> generete(String username){
    Utilisateur utilisateur =  this.utilisateurService.loadUserByUsername(username);
    this.disableTokens(utilisateur);
        final Map<String, String> jwtMap = new java.util.HashMap<>(this.generateJwt(utilisateur));
        RefreshToken refreshToken = RefreshToken.builder()
                .valeur(UUID.randomUUID().toString())
                .expire(false)
                .creation(Instant.now())
                .expiration(Instant.now().plusMillis(60 * 60 * 3600))
                .build();
        final Jwt jwt = Jwt
                .builder()
                .valeur(jwtMap.get(BEARER))
                .desactive(false)
                .expire(false)
                .utilisateur(utilisateur)
                .refreshToken(refreshToken)
                .build();
        this.jwtRepository.save(jwt);
        jwtMap.put("refresh-token",refreshToken.getValeur());
        return jwtMap;
    }
    private void  disableTokens(Utilisateur utilisateur){
        final List<Jwt> jwtList = this.jwtRepository.findUtilisateur(utilisateur.getEmail()).peek(
                jwt -> {
                    jwt.setDesactive(true);
                    jwt.setExpire(true);
                }
        ).collect(Collectors.toList());
        this.jwtRepository.saveAll(jwtList);
    }
    public String extractUsername(String token) {
        return this.getClaims(token,Claims::getSubject);
    }
    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaims(token,Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaims(String token , Function<Claims,T> function) {
        Claims claims = getAllClaims((token));
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        final    long currentTime = System.currentTimeMillis();
        final    long expirationTime = currentTime + 60 * 1000;
        final Map<String, Object> claims = Map.of(
                "nom", utilisateur.getNom(),
                Claims.EXPIRATION,new Date(expirationTime),
                Claims.SUBJECT,utilisateur.getEmail()
        );


        final String bearer  = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setIssuedAt(new Date(expirationTime))
                .setSubject(utilisateur.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER, bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }


    public void deconnexion() {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Jwt jwt =   this.jwtRepository.findUtilisateurValideToken(
              utilisateur.getEmail(),
              false,
              false
      ).orElseThrow(()-> new  RuntimeException("Token Invalide"));
      jwt.setExpire(true);
      jwt.setDesactive(true);
        this.jwtRepository.save(jwt);
    }
    @Scheduled(cron =  "@daily")
//    @Scheduled(cron =  "0 */1 * * * *")
    public void removeUselessJwt(){
        log.info("Suppression des token a {}", Instant.now());
        this.jwtRepository.deleteAllByExpireAndDesactive(true,true);
    }

    public Map<String, String> refreshToken(Map<String, String> refreshTokenRequest) {
        final Jwt jwt = this.jwtRepository.findByRefreshToken(refreshTokenRequest.get(REFRESH)).orElseThrow(() ->
                new RuntimeException("Token Invalide"));
        if (jwt.getRefreshToken().isExpire() || jwt.getRefreshToken().getExpiration().isBefore(Instant.now())){
            throw new RuntimeException(TOKEN_INVALIDE);
        }
        this.disableTokens(jwt.getUtilisateur());
      Map<String , String> tokens = this.generete(jwt.getUtilisateur().getEmail());
        return tokens;
    }
}
