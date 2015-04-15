/*     */ package org.kingtop.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import org.kingtop.lang.BaseException;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.util.CollectionUtils;
/*     */ import org.springframework.util.ObjectUtils;
/*     */ 
/*     */ public abstract class AssertUtil
/*     */ {
/*     */   public static void isTrue(boolean expression, String errorCode, Logger log)
/*     */   {
/*  43 */     if (!expression)
/*  44 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void isFalse(boolean expression, String errorCode, Logger log)
/*     */   {
/*  55 */     if (expression)
/*  56 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void isNull(Object object, String errorCode, Logger log)
/*     */   {
/*  67 */     if (object != null)
/*  68 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void notNull(Object object, String errorCode, Logger log)
/*     */   {
/*  79 */     if (object == null)
/*  80 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void notEmpty(String text, String errorCode, Logger log)
/*     */   {
/*  91 */     if ((text == null) || (text.length() == 0))
/*  92 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void notEmpty(Object[] array, String errorCode, Logger log)
/*     */   {
/* 103 */     if (ObjectUtils.isEmpty(array))
/* 104 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void notEmpty(Collection collection, String errorCode, Logger log)
/*     */   {
/* 115 */     if (CollectionUtils.isEmpty(collection))
/* 116 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ 
/*     */   public static void notEmpty(Map map, String errorCode, Logger log)
/*     */   {
/* 127 */     if (CollectionUtils.isEmpty(map))
/* 128 */       throw new BaseException(errorCode, log);
/*     */   }
/*     */ }

/* Location:           D:\TDDOWNLOAD\joyone-1.0.9.jar
 * Qualified Name:     org.joyone.util.AssertUtil
 * JD-Core Version:    0.6.2
 */