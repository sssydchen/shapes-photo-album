package modelTest;

import org.junit.jupiter.api.Test;

import model.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the color class.
 */
class ColorTest {

  /**
   * Test the conversion method in the Color class.
   */
  @Test
  void testColorConversionToAWT() {
    Color color1 = new Color(1.0, 0.0, 0.0);
    java.awt.Color expect1 = new java.awt.Color(1.0f, 0.0f, 0.0f);
    assertEquals(expect1, color1.toAWTColor());

    Color color2 = new Color(0.0, 1.0, 1.0);
    java.awt.Color expect2 = new java.awt.Color(0.0f, 1.0f, 1.0f);
    assertEquals(expect2, color2.toAWTColor());

  }
}