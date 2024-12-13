package fr.isep.tian;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class passagerTest {

    @Test
    void lireCSV_TestNotNull() {
        try {
            assertNotEquals(Passager.lireCSV("./liste_passagers.csv"), null);
        } catch (IOException e) {}
    }

    @Test
    void lireCSV_TestData() { // 测试方法：验证 lireCSV 方法返回的数据中某个值是否符合预期。
        try {
            assertEquals(
                    Passager.lireCSV("./liste_passagers.csv").get(3).get("Nom"), // 获取返回数据的第二行中键为 "Dép_" 的值。
                    "Mia" // 断言值等于 "Lyon"。
            );
        } catch (IOException e) {} // 捕获可能抛出的 IOException，但不做任何处理。
    }
}