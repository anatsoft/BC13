package framework.engine.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SeleniumWrapper {
git /*
    private final WebDriver driver;

    //Constructor Base
    public SeleniumWrapper(WebDriver driver){
        this.driver = driver;
    }

    //Wrappers Selenium
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator){
        return driver.findElements(locator);
    }

    public String getText (By locator){
        return driver.findElement(locator).getText();
    }

    public void write(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }
    public void sendKeys(Keys key, By locator){
        driver.findElement(locator).sendKeys(key);
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public Boolean isEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Boolean isSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void navigateTo(String url){
        driver.navigate().to(url);
    }

    public String getUrlTitle(){
        return driver.getTitle();
    }

    //Añdadimos más métodos útiles
*/

    protected WebDriver driver;
    protected WebDriverWait espera; //ME PERMITIRA MANEJAR LAS ESPERAS
    protected JavascriptExecutor js; //manipular eventos como el scroll


    //métodos
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getEspera() {
        return espera;
    }

    public void setEspera(WebDriverWait espera) {
        this.espera = espera;
    }

    // GENERAR EL WRAPPER DE LOS COMANDOS DE SELENIUM
    //
    public void clickear(By localizador){
        this.driver.findElement(localizador).click();
    }

    public void clickear(WebElement elemento){
        elemento.click();
    }

    public WebElement buscarElementoWeb(By localizador){
        return this.driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return this.driver.findElements(localizador);
    }

    public WebElement esperarPorElemento(By localizador){
        espera = new WebDriverWait(this.driver,30);
        return espera.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public void esperarXSegundos(int milisegs){
        try {
            Thread.sleep(milisegs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String obtenerTexto(By localizador){ //xpath,id,name
        return this.driver.findElement(localizador).getText();
    }

    public String obtenerTexto(WebElement elemento){
        return elemento.getText();
    }

    public void agregarTexto(By localizador,String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarConbinacionTeclasEnTexto(By localizador, Keys keys){
        this.driver.findElement(localizador).sendKeys(keys);

    }

    public void agregarTexto(WebElement elemento,String texto){
        elemento.sendKeys(texto);
    }

    public boolean estaDesplegado(By localizador){
        try {
            return this.driver.findElement(localizador).isDisplayed();
        }catch (Exception e){
            System.out.println("no se pudo localizar el elemento con el locator: "+localizador);
            return false;
        }
    }


    public SeleniumWrapper(WebDriver driver) {
        this.driver = driver;
    }

    //Método para manejar el comboBox
    public void seleccionarComboBoxPortextoVisible(By localizador,String textoVisible){
        WebElement ddlMes = driver.findElement(localizador);
        Select selectorMes = new Select(ddlMes);
        selectorMes.selectByVisibleText(textoVisible);
    }

    public void seleccionarComboBoxPorValue(By localizador,String value){
        WebElement ddlMes = driver.findElement(localizador);
        Select selectorMes = new Select(ddlMes);
        selectorMes.selectByValue(value);
    }
    public void cargarUrl(String url){
        driver.get(url);
    }

    public void maximizarBrowser(){
        this.driver.manage().window().maximize();
    }

    public void scrollingDownElement(WebElement elemento){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", elemento);
    }





}
