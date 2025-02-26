package de.telran.onlineshop;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class PerformanceTest {
    @Test
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(100, 10,
                        httpSampler("http://localhost:18088/products/filter?category=1&min_price=1&is_discount=false&sort=price,desc")
                ),
                //this is just to log details of each request stats
                jtlWriter("target/jtls")
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }
}
