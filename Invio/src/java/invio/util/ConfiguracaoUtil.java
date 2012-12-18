/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author Dir de Armas Marinha
 */
public class ConfiguracaoUtil {

    public enum TipoProducao {PERIODICOS, LIVROS, ORIENTACOES};
    
    private static Properties abrir(File arquivo) {
        if (arquivo == null) {
            return null;
        }
        Properties resposta = null;
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            resposta = new Properties();
            resposta.load(fis);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resposta;
    }
    
    public static String getPath(TipoProducao tipo) {
        String resposta = null;
        ClassLoader classLoader = ConfiguracaoUtil.class.getClassLoader();
        URL resource = classLoader.getResource("./configuracao.properties");
        Properties propriedades = abrir(new File(resource.getFile()));
        switch (tipo) {
            case PERIODICOS:
                resposta = propriedades.getProperty("pasta.periodicos");
                break;
            case LIVROS:
                resposta = propriedades.getProperty("pasta.livros");
                break;
            case ORIENTACOES:
                resposta = propriedades.getProperty("pasta.orientacoes");
                break;
        }
        return resposta;
    }
}
