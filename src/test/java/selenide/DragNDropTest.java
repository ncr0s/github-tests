package selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragNDropTest {
    // Test doesn't work if using actions-chain
    @Test
    public void checkDragNDrop() {
        SelenideElement a = $("#column-a");
        SelenideElement b = $("#column-b");
        open("https://the-internet.herokuapp.com/drag_and_drop");
        a.dragAndDropTo(b);
        b.shouldHave(text("A"));
        a.shouldHave(text("B"));
    }
}
