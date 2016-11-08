/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteWS.util;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
/**
 *
 * @author Ana Gonçalo
 */
@ApplicationPath("rest")
public class MyApplicationResourceConfig extends ResourceConfig{
    
    public MyApplicationResourceConfig() {
        packages("MyApplicationService");
    }   
}
