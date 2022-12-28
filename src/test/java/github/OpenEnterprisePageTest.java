package github;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class OpenEnterprisePageTest {
    @BeforeAll
    public static void setUp() {
        baseUrl = "https://github.com";
    }
    @Test
    public void enterprisePageOpening() {
        open("");
        $$(".HeaderMenu-link").findBy(text("Solutions")).hover();
        $$(".HeaderMenu-dropdown").findBy(visible).$(Selectors.byText("Enterprise")).click();
        $(".h6-mktg").shouldHave(exactText("GitHub for enterprises"));
        $(".h1-mktg").shouldHave(text("Build like the best"));
        webdriver().shouldHave(currentFrameUrl(baseUrl + "/enterprise"));
    }
}
