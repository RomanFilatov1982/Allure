import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {
    @BeforeEach
    void setUp() {
        Configuration.pageLoadTimeout = 100000;
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void testIssueSearch() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("RomanFilatov1982/qa_guru_files");
        $("#query-builder-test").submit();
        $(linkText("RomanFilatov1982/qa_guru_files")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);

    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}
