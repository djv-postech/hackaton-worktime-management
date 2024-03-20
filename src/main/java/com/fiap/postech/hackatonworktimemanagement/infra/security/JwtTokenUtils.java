package com.fiap.postech.hackatonworktimemanagement.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtils implements Serializable {
    public static final long JWT_TOKEN_VALIDADE = 30 * 60;
    private static final String TOKEN_PREFIXO = "Bearer ";


    @Value("${jwt.secret}")
    private String secret;

    public String buscarUsuarioToken(String token) {
        return buscarReinvindicacaoToken(token, Claims::getSubject);
    }

    public Date buscarDataDeExpiracaoToken(String token) {
        return buscarReinvindicacaoToken(token, Claims::getExpiration);
    }

    public <T> T buscarReinvindicacaoToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = buscarTodasReinivindicacoesToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims buscarTodasReinivindicacoesToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean tokenEstaExpirado(String token) {
        final Date expiracao = buscarDataDeExpiracaoToken(token);
        return expiracao.before(new Date());
    }

    public String gerarToken(String nomeFuncionario) {

        Map<String, Object> reivindicacoes = new HashMap<>();
        return gerarToken(reivindicacoes, nomeFuncionario);
    }

    private String gerarToken(Map<String, Object> reivindicacoes, String subject) {

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDADE * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public Boolean validarToken(String autorizacao, String nomeUsuario) {
        if (prefixoTokenValido(autorizacao)) {
            return false;
        }
        final String token = autorizacao.split(" ")[1].trim();
        final String username = buscarUsuarioToken(token);
        return (username.equals(nomeUsuario) && !tokenEstaExpirado(token));
    }

    public Boolean validarToken(String autorizacao) {
        if (prefixoTokenValido(autorizacao)) {
            return false;
        }
        final String token = autorizacao.split(" ")[1].trim();

        try {
            buscarUsuarioToken(token);
            return true;
        } catch (IllegalArgumentException e) {
            log.error("Erro ao validar token.");
        } catch (ExpiredJwtException e) {
            log.error("JWT Token está expirado.");
        }

        return false;
    }

    private boolean prefixoTokenValido(final String tokenWithPrefix) {
        return !tokenWithPrefix.startsWith(TOKEN_PREFIXO);
    }
}