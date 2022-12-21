package githubselenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FindSoftAssertionsWikiPage {
    @Test
    void junitFiveShouldBePresentFindAssertionViaSearchBar() {
        // Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        // Перейдите в раздел Wiki проекта
        $("[data-tab-item=i5wiki-tab").click();
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $$(".Box-row").findBy(visible).shouldHave(text("SoftAssertions"));
        $$(".Box-row").findBy(visible).click();
        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(".gollum-markdown-content").shouldHave(text("Using JUnit5 extend test class"));
    }

    @Test
    void junitFiveShouldBePresentFindAssertionByWebElement() {
        // Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        // Перейдите в раздел Wiki проекта
        $("[data-tab-item=i5wiki-tab").click();
        $(".wiki-rightbar li [type=button]").click();
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$(".Box-row a").findBy(text("SoftAssertions")).click();
        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(".gollum-markdown-content").shouldHave(text("Using JUnit5 extend test class"));
    }
}
