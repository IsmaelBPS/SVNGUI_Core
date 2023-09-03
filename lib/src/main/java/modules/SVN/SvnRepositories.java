package modules.SVN;

import java.io.IOException;
import java.util.Optional;

import Infos.SONames;
import models.Repositories;
import modules.System.SystemUtils;

public class SvnRepositories implements Repositories {
  private SONames SOname;

  public SvnRepositories() {
    this.SOname = SystemUtils.getInstance().getSOName();
  }

  /**
   * Create a repository with given name on specified directory
   * 
   * @param path Path of the repository
   * @param name Name of the repository
   * @return true if the repository was created, otherwise false
   */
  @Override
  public boolean createRepository(String path, String name) {
    try {
      ProcessBuilder processBuilder;
      Process process;
      if (this.SOname.equals(SONames.LINUX)) {
        processBuilder = new ProcessBuilder("svnadmin", "create", "--fs-type", "fsfs", path);
        process = processBuilder.start();

        int exitCode = process.waitFor();

        return exitCode == 0;
      }
    } catch (IOException | InterruptedException e) {
      return false;
    }
    return false;
  }

  /**
   * Delete repository with or without backup
   * 
   * @param path       Path of the repository
   * @param keepFiles  Keep repository files
   * @param backupPath Path to store backup of repository
   */
  @Override
  public boolean deleteRepository(String path, Optional<Boolean> keepFiles,
      Optional<String> backupPath) {
    ProcessBuilder processBuilder;
    Process process;
    try {

      if (this.SOname.equals(SONames.LINUX)) {
        if (keepFiles.isPresent() == true) {

          processBuilder = new ProcessBuilder("svnadmin", "dump", path, backupPath.get());
        }

        processBuilder = new ProcessBuilder("svnadmin", "delete", path);
        process = processBuilder.start();
        int exitCode = process.waitFor();

        return exitCode == 0;
      }
    } catch (IOException | InterruptedException e) {
      return false;
    }

    return false;

  }

  /**
   * Change repository name on given path from old name to new name
   * 
   * @param path    Path of the repository
   * @param oldName Old name of the repository
   * @param newName New name of the repository
   */
  @Override
  public boolean changeRepositoryName(String path, String oldName, String newName) {
    ProcessBuilder processBuilder;
    Process process;

    String oldPath = path + "/" + oldName;
    String newPath = path + "/" + newName;

    try {
      processBuilder = new ProcessBuilder("svn", "mv", oldPath, newPath);
      process = processBuilder.start();
      int exitCode = process.waitFor();

      return exitCode == 0;
    } catch (IOException | InterruptedException e) {
      return false;
    }

  }

  /**
   * Relocate repository from old path to new path
   * 
   * @param oldPath Old path of the repository
   * @param newPath New path of the repository
   */
  @Override
  public boolean moveRepository(String oldPath, String newPath) {
    ProcessBuilder processBuilder;
    Process process;

    try {
      processBuilder = new ProcessBuilder("svn", "relocate", oldPath, newPath);
      process = processBuilder.start();
      int exitCode = process.waitFor();

      return exitCode == 0;
    } catch (IOException | InterruptedException e) {
      return false;
    }

  }
}
