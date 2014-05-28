package invio.util;

import java.io.File;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author invio
 */
public class Utilitario {

    private static final String CHAVE = "donazione";
    private static final String ALGORITMO = "DES";
    private static final String CAMINHOARQUIVOS = System.getProperty("user.home") + "/arquivos";

    public static String getCaminhoArquivos() {
        File f = new File(CAMINHOARQUIVOS);
        if (!f.exists()) {
            f.mkdir();
        }
        return CAMINHOARQUIVOS;
    }

    public static String encriptarSHA256(String senha) {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        String senhaCripto = sha.encodePassword(senha, null);
        return senhaCripto;
    }

    private static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public static String criptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(senha.getBytes("UTF-8"));
            return asHex(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String descriptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(fromHexString(senha));
            return new String(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String gerarSenhaAscii(int caracteres) {
        return RandomStringUtils.randomAlphanumeric(caracteres);
    }

    public static void main(String[] args) {
        String s = "a";
        String r = criptografarPrivado(s);
        String d = descriptografarPrivado(r);
        System.out.println(r + " - " + d);
        System.out.println("sha " + s + ": " + encriptarSHA256(s));
    }
}
