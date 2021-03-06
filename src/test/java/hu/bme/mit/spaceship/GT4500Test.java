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

  @Test
  public void fireTorpedo_First_PrimarySuccess(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

    @Test
  public void fireTorpedo_SecondaryEmpty(){
    // Arrange
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // Primary was before but secondary empty
    assertEquals(true, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

      @Test
  public void fireTorpedo_PrimaryEmpty(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(0)).fire(1);
    verify(mocksecond, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_Failure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(0)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

    @Test
  public void fireTorpedo_SINGLE_Failure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(0)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

     @Test
  public void fireTorpedo_ALL_Failure_OneEmpty(){
    // Arrange
     when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

   @Test
  public void fireTorpedo_PrimaryFiredLast(){
    // Arrange
     when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_PrimaryFiredLast_SecEmpty(){
    // Arrange
     when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockprimary, times(2)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_PrimaryFiredLast_SecEmptyFirstEmpty(){
    // Arrange
     when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    when (mockprimary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_PrimaryFailure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(false);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(0)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_SecondaryFailure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(false);
    when (mockprimary.isEmpty()).thenReturn(true);
    when (mocksecond.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(0)).fire(1);
    verify(mocksecond, times(0)).fire(1);
  }

    @Test
  public void fireTorpedo_ALL_PrimaryEmptyFailure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(false);
    when (mocksecond.fire(1)).thenReturn(true);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_SecondaryEmptyFailure(){
    // Arrange
    when (mockprimary.fire(1)).thenReturn(true);
    when (mocksecond.fire(1)).thenReturn(false);
    when (mockprimary.isEmpty()).thenReturn(false);
    when (mocksecond.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockprimary, times(1)).fire(1);
    verify(mocksecond, times(1)).fire(1);
  }





}
