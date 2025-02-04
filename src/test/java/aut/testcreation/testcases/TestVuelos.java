package aut.testcreation.testcases;

import aut.testcreation.pages.*;
import framework.engine.selenium.DriverFactory;
import framework.engine.selenium.SeleniumTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestVuelos extends SeleniumTestBase {

    private HomePage homepage;
    private VuelosPage vuelospage;
    private VuelosResults vuelosresults;
    private VuelosFlex vuelosFlex;
    private VuelosCheckout vuelosCheckout;
    private Multidestino multidestino;

    @BeforeEach
    public void preTest(){
        homepage = new HomePage(DriverFactory.getDriver());
        vuelospage = new VuelosPage(homepage.getDriver());
        vuelosresults = new VuelosResults(homepage.getDriver());
        vuelosFlex = new VuelosFlex(homepage.getDriver());
        vuelosCheckout = new VuelosCheckout(homepage.getDriver());
        multidestino = new Multidestino(homepage.getDriver());
        homepage.navigateTo("https://www.rumbo.es");
        homepage.noCookies();
    }

    //Búsqueda de viajes de menor duración
    @Test
    public void RV001 () {
        homepage.irAVuelos();
        vuelospage.completarOrigenDestino("Barcelona", "Roma");
        vuelosresults.MasBarato();
    }
    //Búsqueda con cantidad máxima pasajes
    @Test
    public void RV002 () throws InterruptedException {
        homepage.irAVuelos();
        vuelosresults.pasajesMaximos("Madrid", "Buenos Aires");
    }

    //Reserva de pasaje fallida por falta de datos de facturación
    @Test
    public void RV003 (){
        homepage.irAVuelos();
        vuelospage.completarOrigenDestino("Barcelona", "Roma");
        vuelosresults.Unresultado();
        vuelosFlex.Flexible();
        vuelosCheckout.completarDatos("Cristian" , "Vargas" , "cristian.vargas@gmail.com" , "3804556677","Callao","350","5300","La Rioja"," Javier", " Fernandez","14", "1990");
        vuelosCheckout.Facturacion("Cristian Vargas", "03", "25", "666");
    }

    //Reserva de pasajes fallida por usuario menor de edad
    @Test
    public void RV004 (){
        homepage.irAVuelos();
        vuelospage.completarOrigenDestino("Barcelona", "Roma");
        vuelosresults.Unresultado();
        vuelosFlex.Flexible();
        vuelosCheckout.completarDatos("Cristian" , "Vargas" , "cristian.vargas@gmail.com" , "3804556677","Callao","350","5300","La Rioja"," Javier", " Fernandez","14", "2013");
    }

    //Multidestino
    @Test
    public void RV005 (){
        homepage.irAVuelos();
        vuelospage.irAMultidestino();
        multidestino.CompletarMultidestino();
        multidestino.Pasajero1("Tsoft", "Bidone", "Argentina", "2", "Hombre","1998","33444555");
        multidestino.Pasajero2("Webiwi","Jack","Bolivia","Mujer","2","1998","44555333");
    }


    //Reserva de pasajes fallida por e-mail inválido
    @Test
    public void RV006 (){
        homepage.irAVuelos();
        vuelospage.BusquedaSoloIda("Barcelona" , "Roma");
        vuelosresults.Unresultado();
        vuelosFlex.Flexible();
        vuelosCheckout.completarDatos("Cristian" , "Vargas" , "##%%@gmail.com" , "3804556677","Callao","350","5300","La Rioja"," Javier", " Fernandez","14", "1998");
    }


}