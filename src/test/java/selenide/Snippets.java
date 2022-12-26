package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// most common selenide commands
public class Snippets {
    void browserCommandExamples() {
        Configuration.baseUrl="https://amazon.com";
        open("https:/google.com");
        open("/customer/orders");
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password"));

        back();
        refresh();

        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();");   // because there is no special selenide command

        confirm();  // OK in alert dialog
        dismiss();  // Cancel in alert dialog

        closeWebDriver();   // close browser
        closeWindow();  // close active tab

        switchTo().frame("new");
        switchTo().defaultContent();    // return from frame back to the main DOM

        switchTo().window("The Internet");

        var cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);  // how to use cookies
    }

    void selectionExamples() {
        $("div").click();
        element("div").click();

        $("div", 2).click();    // getting the third div

        $x("//h1/div").click();
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click();
        $(withText("part text")).click();

        $(byTagAndText("div", "full text"));
        $(withTagAndText("div", "part text"));

        $("").parent();
        $("").sibling(0);   // next at the same level
        $("").preceding(0); // previous at the same level
        $("").closest("div");
        $("").ancestor("div");  // the same as closest
        $("div:last-child");

        // optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();

        $(byId("id")).click();
        $("#id").click();

        $(byClassName("class")).click();
        $(".class").click();
    }

    void actionsExamples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick();   // right click

        $("").hover();

        $("").setValue("text");
        $("").append("add text");
        $("").clear();
        $("").setValue(""); // clear

        $("div").sendKeys("a"); // hotkey c on element
        actions().sendKeys("c").perform();  // hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform();    // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        // complex actions with keyboard and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // old html actions don't work with many modern frameworks
        $("").selectOption("Option 1");
        $("").selectRadio("Button");
    }

    void assertionsExamples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abs"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        // longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    void conditionExamples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("Abc"));
        $("").shouldHave(exactTextCaseSensitive("Abc"));
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldHave(value("42"));
        $("").shouldHave(exactValue("42"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "value"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked);

        // Warning! Only checks if it is in DOM, not if it is visible!
        $("").should(exist);

        // Warning! Checks only rhe "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collectionsExamples() {
        $$("div");

        $$x("//div");

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(3));

        // $("div").click();
        $$("div").first().click();
        elements("div").first().click();
        $$("div").last().click();
        $$("div").get(1).click();
        $("div", 1).click();
        $$("div").findBy(text("abc")).click();

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(empty);

        $$("").shouldHave(texts("Alpha", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alpha", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Alpha", "Beta", "Gamma"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Alpha", "Beta", "Gamma"));

        $$("").shouldHave(itemWithText("Gamma"));   // only one text

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(2));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));
    }

    void fileOperationsExamples() throws FileNotFoundException {
        File file1 = $("a.fileLink").download();    //only for <a href="..."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); //more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit
        $("uploadButton").click();
    }

    void javascriptExamples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1]", "abc", 123);
        long fortyTwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
    }
}
