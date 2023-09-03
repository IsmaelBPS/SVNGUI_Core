package models.GenericClasses;

import models.VCS;

public abstract class VCSCore implements VCS {

  @Override
  public abstract boolean checkout(String url, String path);

  @Override
  public abstract boolean update(String path);

  @Override
  public abstract boolean log(String path);

  @Override
  public abstract boolean add(String path);
}
