package modules.System;

import java.io.IOException;

import Infos.SONames;

public class SystemUtils {
  private SONames _SOname;
  private static SystemUtils instance;

  // Private constructor to use singleton
  private SystemUtils() {
    name();
  }

  public static SystemUtils getInstance() {
    if (instance == null) {
      instance = new SystemUtils();
    }
    return instance;
  }

  /**
   * Get name of current operating system
   * 
   * @return name of the operating system
   */
  public SONames getSOName() {
    return _SOname;
  }

  /**
   * Get name of current operating system
   * 
   * 
   * @return name of the operating system
   */
  private String name() {
    String osName = System.getProperty("os.name");
    if (osName.contains("win")) {
      this._SOname = SONames.WINDOWS;
    } else if (osName.contains("nux")) {
      this._SOname = SONames.LINUX;
    } else {
      this._SOname = SONames.MAC;
    }

    System.out.println("Nome do sistema: " + osName);
    return osName;
  }

  /**
   * Determines if the specified program is installed.
   *
   * @param programName the name of the program to check
   * @return the exit code of the process if the program is installed,
   *         otherwise 1
   */
  public int hasProgramInstalled(String programName) {
    try {
      ProcessBuilder processBuilder;
      Process process;
      if (this._SOname.equals(SONames.LINUX)) {
        processBuilder = new ProcessBuilder("which", programName);
        // Redirect output to inherit (system console)
        // processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        process = processBuilder.start();

        int exitCode = process.waitFor();

        return exitCode;
      }
    } catch (IOException | InterruptedException e) {
      return 1;
    }
    return 0;
  }

}
