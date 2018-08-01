package edu.jennifer.check.service;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Khalid Saeed <khalid@jennifersoft.com>
 * @Created 2/13/18 11:26 AM.
 */
public class CheckTaskTest {

    @Test
    public void testCheckDigits() {
        assertThat(CheckTask.checkDigits("4599000000000000")).isEqualTo(true);
        assertThat(CheckTask.checkDigits("500459900000000")).isEqualTo(false);
        assertThat(CheckTask.checkDigits("4000")).isEqualTo(false);
    }

}
