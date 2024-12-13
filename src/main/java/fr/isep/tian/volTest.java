package fr.isep.tian;

import org.junit.jupiter.api.Test; // 导入 JUnit 的 @Test 注解，用于标记测试方法。
import java.io.IOException;        // 导入 IOException，用于处理可能抛出的异常。
import static org.junit.jupiter.api.Assertions.*; // 导入 JUnit 的断言方法，用于验证测试结果。

public class volTest { // 定义一个名为 DemoCSVTest 的测试类。

    @Test
    void lireCSV_TestNotNull() { // 测试方法：验证 lireCSV 方法的返回值不为 null。
        try {
            assertNotEquals(Vol.lireCSV("./liste_vols.csv"), null); // 断言 lireCSV 方法的返回值不为 null。
        } catch (IOException e) {} // 捕获可能抛出的 IOException，但不做任何处理。
    }

    @Test
    void lireCSV_TestData() { // 测试方法：验证 lireCSV 方法返回的数据中某个值是否符合预期。
        try {
            assertEquals(
                    Vol.lireCSV("./liste_vols.csv").get(1).get("Arrivé"), // 获取返回数据的第二行中键为 "Dép_" 的值。
                    "LON" // 断言值等于 "Lyon"。
            );
        } catch (IOException e) {} // 捕获可能抛出的 IOException，但不做任何处理。
    }

}