package models;

public interface VCS {
  // implements basic svn functionalities
  public boolean checkout(String url, String path);

  public boolean update(String path);

  public boolean log(String path);

  public boolean add(String path);
}