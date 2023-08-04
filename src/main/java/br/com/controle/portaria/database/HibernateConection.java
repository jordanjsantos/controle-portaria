/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle.portaria.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConection {

    private static SessionFactory fabricaSessao = null;

    static {
        
    	Boolean exibiSql = true;
    	
        try {         

            Configuration cfg = new Configuration().
            		setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect").
                    setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver"). 
                    setProperty("hibernate.connection.url","jdbc:mysql://" + 
                    		DadosConfigBanco.HOST_DEV + 
                    		":" + 
                    		DadosConfigBanco.PORT_DEV + "/" +
                    		DadosConfigBanco.NAME_DEV).
                    setProperty("hibernate.connection.username", DadosConfigBanco.USER_DEV).
                    setProperty("hibernate.connection.password", DadosConfigBanco.PASS_DEV).
                    setProperty("hibernate.connection.CharSet", "utf8").
                    setProperty("hibernate.connection.characterEncoding", "utf8").
                    setProperty("hibernate.connection.useUnicode", "true").
                    setProperty("jdbc.batch_size", "0").
                    setProperty("hibernate.enable_lazy_load_no_trans", "true").
                    setProperty("hibernate.show_sql", exibiSql.toString()).
                    setProperty("hibernate.format_sql", exibiSql.toString()).
                    setProperty("use_sql_comments", "true");
            
                        
            fabricaSessao = cfg.configure().buildSessionFactory();

        } catch (Exception e) {
        	e.printStackTrace();           
            fabricaSessao = null;
        }
    }

    public static Session getSessao() {
        return fabricaSessao.openSession();
    }
}
