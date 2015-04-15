package org.kingtop.service;

import java.util.List;
import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;

public abstract interface IBaseService<T>
{
  public abstract Object getObject(String paramString)
    throws BaseException;

  public abstract void addObject(T paramObject)
    throws BaseException;

  public abstract void saveObject(T paramObject)
    throws BaseException;

  public abstract void updateObject(T paramObject)
    throws BaseException;

  public abstract void deleteObject(String paramString)
    throws BaseException;

  public abstract List getObjects()
    throws BaseException;

  public abstract Page findPage(int paramInt1, int paramInt2)
    throws BaseException;

  public abstract void deleteObject(String[] paramArrayOfString)
    throws BaseException;
}

/* Location:           D:\TDDOWNLOAD\joyone-1.0.9.jar
 * Qualified Name:     org.joyone.service.IBaseService
 * JD-Core Version:    0.6.2
 */