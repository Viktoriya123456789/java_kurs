package ru.stga.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NavigationHelper extends HelperBase {

    private WebDriver wd;
    private WebDriverWait wait;

    public NavigationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
        wait = new WebDriverWait(wd, 5);
    }


    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void manage() {
        click(By.cssSelector(String.format("a[href=\"/mantisbt-1.2.19/manage_overview_page.php\"]"))); //используется, заменена ссылка


    }

    public void usersManage() {
        click(By.cssSelector(String.format("a[href=\"/mantisbt-1.2.19/manage_user_page.php\"]"))); //используется, заменена ссылка


    }

    public void login() {
        wd.get ( app.getProperty("web.baseUrl") +  "/login.php" );
       wd.manage().window().maximize();
        type (By.name("username"),app.getProperty("web.adminLogin"));
        type (By.name("password"),app.getProperty("web.adminPassword"));
        //click ( By.cssSelector ( "input[value='Войти'" ));
        wd.findElement(By.cssSelector("input[type=submit]")).click();


    }

    // public void login(String username, String password) {
    //  wd.manage().window().maximize();
    //  wd.get("http://localhost/mantisbt-1.2.19/login_page.php");
    //  By user = By.cssSelector("input[name=username]");
    // By pass = By.cssSelector("input[name=password]");
    // wd.findElement(user);
    // click(user);
    // type(user, "administrator");
    //  wd.findElement(By.cssSelector("input[type=submit]")).click();
    //wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //wd.findElement(pass);
    //click(pass);
    //type(pass, "root");
    //wd.findElement(By.cssSelector("input[type=submit]")).click();






}