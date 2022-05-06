package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockedPrimary = mock(TorpedoStore.class);
   private  TorpedoStore mockedSecondary = mock(TorpedoStore.class);


  @BeforeEach
  public void init(){
        this.ship = new GT4500(mockedPrimary, mockedSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockedPrimary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success_Custom(){
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(true, result); 
    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);
  }

 @Test
 public void fireTorpedo_Single_Success_Custom(){
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedPrimary.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(0)).fire(1);

  } 

  @Test
  public void fireTorpedo_All_Failure_Custom(){
    when(mockedPrimary.fire(1)).thenReturn(false);
    when(mockedSecondary.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(false, result); 
    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_Single_Failure_Custom(){
    when(mockedPrimary.fire(1)).thenReturn(false);
    when(mockedSecondary.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result); 
    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure_Custom2(){
    when(mockedPrimary.fire(1)).thenReturn(false);
    when(mockedSecondary.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result); 
    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(0)).fire(1);
  }
}
