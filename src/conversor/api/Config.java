package conversor.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String get(String chave) {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            return props.getProperty(chave);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configuração: " + chave, e);
        }
    }
}
