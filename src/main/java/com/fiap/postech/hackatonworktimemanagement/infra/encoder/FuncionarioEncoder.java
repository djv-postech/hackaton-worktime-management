package com.fiap.postech.hackatonworktimemanagement.infra.encoder;

import com.fiap.postech.hackatonworktimemanagement.infra.entity.FuncionarioEntity;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class FuncionarioEncoder {

    private static final String CHAVE = "tech-challenge-k";
    private static final Key chave = new SecretKeySpec(CHAVE.getBytes(), "AES");

    public static FuncionarioEntity encode(FuncionarioEntity funcionarioEntity) {
       String nome = criptografar(funcionarioEntity.getNome());
       String matricula = criptografar(funcionarioEntity.getMatricula());
       String senha = criptografar(funcionarioEntity.getSenha());
       return new FuncionarioEntity(nome, matricula, funcionarioEntity.getCargo(), senha);
    }

    public static FuncionarioEntity decode(FuncionarioEntity funcionarioEntity) {
        String nome = descriptografar(funcionarioEntity.getNome());
        String matricula = descriptografar(funcionarioEntity.getMatricula());
        String senha = descriptografar(funcionarioEntity.getSenha());
        return new FuncionarioEntity(nome, matricula, funcionarioEntity.getCargo(), senha);
    }

    public static String criptografar(String texto) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(textoCriptografado);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criptografar texto", e);
        }
    }

    private static String descriptografar(String textoCriptografado){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder()
                    .decode(textoCriptografado));
            return new String(textoDescriptografado);
        }catch (Exception e){
            throw new RuntimeException("Erro ao descriptografar texto", e);
        }
    }
}