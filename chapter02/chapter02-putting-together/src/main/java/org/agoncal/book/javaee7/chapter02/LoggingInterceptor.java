package org.agoncal.book.javaee7.chapter02;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves APress Book 
 *         - Beginning Java EE 7 with Glassfish 4
 *           http://www.apress.com/ http://www.antoniogoncalves.org --
 */
@Interceptor
@Loggable
public class LoggingInterceptor {

  // ======================================
  // =             Attributes             =
  // ======================================
  @Inject
  private Logger logger;

  // ======================================
  // =          Business methods          =
  // ======================================
  @AroundInvoke
  public Object logMethod(InvocationContext ic) throws Exception {
    logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
    logger.log(Level.SEVERE, ">>>{0} - {1}", new Object[]{ic.getTarget().toString(), ic.getMethod().getName()});
    try {
      return ic.proceed();
    } 
    finally {
      logger.log(Level.SEVERE, "<<<{0} - {1}", new Object[]{ic.getTarget().toString(), ic.getMethod().getName()});
      logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
    }
  }

}
