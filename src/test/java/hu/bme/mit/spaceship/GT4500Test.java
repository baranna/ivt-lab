package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockprimary;
  private TorpedoStore mocksecond;

  @BeforeEach
  public void init(){
    mockprimary = mock(TorpedoStore.class);
    mocksecond = mock(TorpedoStore.class);
    this.ship = new GT4500(mockprimary, mocksecond);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(1)).fire(1);
  }

}
