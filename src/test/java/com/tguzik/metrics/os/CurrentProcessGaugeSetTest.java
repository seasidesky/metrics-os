package com.tguzik.metrics.os;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Map;

import com.codahale.metrics.Metric;
import com.tguzik.metrics.os.gauges.ProcessCpuTime;
import com.tguzik.metrics.os.gauges.ProcessUptime;
import org.junit.Before;
import org.junit.Test;

public class CurrentProcessGaugeSetTest {
    private CurrentProcessGaugeSet gaugeSet;

    @Before
    public void setUp() throws Exception {
        this.gaugeSet = new CurrentProcessGaugeSet( mock( RuntimeMXBean.class ), mock( ThreadMXBean.class ) );
    }

    @Test
    public void default_constructor_bootstraps_the_class_without_exceptions() {
        new CurrentProcessGaugeSet().getMetrics();
    }

    @Test
    public void getMetrics_returns_map_with_process_uptime() {
        final Map<String, Metric> actual = gaugeSet.getMetrics();

        assertThat( actual ).containsKey( "process.uptime" );
        assertThat( actual.get( "process.uptime" ) ).isExactlyInstanceOf( ProcessUptime.class );
    }

    @Test
    public void getMetrics_returns_map_with_process_cpu_time() {
        final Map<String, Metric> actual = gaugeSet.getMetrics();

        assertThat( actual ).containsKey( "process.cpu.time" );
        assertThat( actual.get( "process.cpu.time" ) ).isExactlyInstanceOf( ProcessCpuTime.class );
    }
}
