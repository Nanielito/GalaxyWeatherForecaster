package galaxy.weather.forecaster.Util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import galaxy.weather.forecaster.Application;
import galaxy.weather.forecaster.domain.Point;
import galaxy.weather.forecaster.util.GeometricFunctions;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class GeometricFunctionsTest {

  @Test
  public void whenTwoIdenticalPoints_thenDistanceBetweenThemMustBeZero() {
    // Given
    Point p1 = new Point(0, 1);
    Point p2 = new Point(0, 1);

    // When
    double distance = GeometricFunctions.getDistanceBetweenTwoPoints(p1, p2);

    // Then
    assertEquals(distance, 0, 0);
  }

  @Test
  public void whenTwoDistinctPoints_thenDistanceBetweenThemMustBeNonZero() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 1);

    // When
    double distance = GeometricFunctions.getDistanceBetweenTwoPoints(p1, p2);

    // Then
    assertNotEquals(distance, 0);
  }

  @Test
  public void whenTwoDistinctPointsAreOnXAxis_thenSlopeMustBeZero() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 0);

    // When
    double slope = GeometricFunctions.getSlope(p1, p2);

    // Then
    assertEquals(slope, 0, 0);
  }

  @Test
  public void whenTwoDistinctPointsAreOnYAxis_thenSlopeMustBeInfinity() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 1);

    // When
    double slope = GeometricFunctions.getSlope(p1, p2);

    // Then
    assertEquals(slope, Double.POSITIVE_INFINITY, 0);
  }

  @Test
  public void whenTwoDistinctPoints_thenSlopeMustBeNonZero() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 5);

    // When
    double slope = GeometricFunctions.getSlope(p1, p2);

    // Then
    assertNotEquals(slope, 0);
  }

  @Test
  public void whenThreeDistinctPointsAreOnXAxis_thenTheyMustBeAligned() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 1);
    Point p3 = new Point(0, 2);

    // When
    boolean areAligned = GeometricFunctions.arePointsAligned(p1, p2, p3);

    // Then
    assertTrue(areAligned);
  }

  @Test
  public void whenThreeDistinctPointsAreOnYAxis_thenTheyMustBeAligned() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 0);
    Point p3 = new Point(2, 0);

    // When
    boolean areAligned = GeometricFunctions.arePointsAligned(p1, p2, p3);

    // Then
    assertTrue(areAligned);
  }

  @Test
  public void whenThreeDistinctPoints_thenTheyMustBeNotAligned_IfTheirsSlopeAreNotEquals() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 2);
    Point p3 = new Point(2, 2);

    // When
    double m1 = GeometricFunctions.getSlope(p1, p2);
    double m2 = GeometricFunctions.getSlope(p2, p3);
    boolean areAligned = GeometricFunctions.arePointsAligned(p1, p2, p3);

    // Then
    assertNotEquals(m1, m2);
    assertFalse(areAligned);
  }

  @Test
  public void whenThreeDistinctPoints_thenTheyMustBeAligned_IfTheirsSlopeAreEquals() {
    // Given
    Point p1 = new Point(0, 0);
    Point p2 = new Point(1, 1);
    Point p3 = new Point(2, 2);

    // When
    double m1 = GeometricFunctions.getSlope(p1, p2);
    double m2 = GeometricFunctions.getSlope(p2, p3);
    boolean areAligned = GeometricFunctions.arePointsAligned(p1, p2, p3);

    // Then
    assertEquals(m1, m2, 0);
    assertTrue(areAligned);
  }

  @Test
  public void whenPointIsInsideTriangle_thenPointMustBeInsideThreeDistinctVertices() {
    // Given
    Point p  = new Point(1, 1);
    Point v1 = new Point(0, 0);
    Point v2 = new Point(2, 0);
    Point v3 = new Point(1, 2);

    // When
    boolean isInside = GeometricFunctions.isPointInsideTriangle(p, v1, v2, v3);

    // Then
    assertTrue(isInside);
  }

  @Test
  public void whenPointIsOutsideTriangle_thenPointMustBeOutsideThreeDistinctVertices() {
    // Given
    Point p  = new Point(1, 5);
    Point v1 = new Point(0, 0);
    Point v2 = new Point(2, 0);
    Point v3 = new Point(1, 2);

    // When
    boolean isInside = GeometricFunctions.isPointInsideTriangle(p, v1, v2, v3);

    // Then
    assertFalse(isInside);
  }
}
