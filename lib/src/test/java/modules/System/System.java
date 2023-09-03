package modules.System;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Infos.SONames;

public class System {
  private SystemUtils genericSystem = SystemUtils.getInstance();

  @Test
  void checkSystemName() {
    assertTrue(genericSystem.getSOName().equals(SONames.LINUX));
  }

  @Test
  void checkIfSVNIsInstalled() {
    assertEquals(genericSystem.hasProgramInstalled("svn"), 0);
  }

  @Test
  void shouldNotInstalledProgramReturn1() {
    assertEquals(genericSystem.hasProgramInstalled("notInstalledProgram"), 1);
  }
}
