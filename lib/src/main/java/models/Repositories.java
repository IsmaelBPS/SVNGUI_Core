package models;

import java.util.Optional;

public interface Repositories {
  public boolean createRepository(String path, String name);

  public boolean deleteRepository(String path, Optional<Boolean> keepFiles, Optional<String> backupPath);

  public boolean changeRepositoryName(String path, String oldName, String newName);

  public boolean moveRepository(String oldPath, String newPath);
}
