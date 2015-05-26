package org.agoncal.book.javaee7.chapter02.ex27;

import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ProfileInterceptor27 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private Logger logger;

  // ======================================
  // =          Lifecycle methods         =
  // ======================================

  @PostConstruct
  public void logMethod(InvocationContext ic) throws Exception {
    logger.fine(ic.getTarget().toString());
    logger.log(Level.SEVERE, ">>>{0}", ic.getTarget().toString());
    try {
      ic.proceed();
    } finally {
      logger.log(Level.SEVERE, "<<<{0}", ic.getTarget().toString());
      logger.fine(ic.getTarget().toString());
    }
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  @AroundInvoke
  public Object profile(InvocationContext ic) throws Exception {
    long initTime = System.currentTimeMillis();
    try {
      return ic.proceed();
    } finally {
      long diffTime = System.currentTimeMillis() - initTime;
      logger.log(Level.SEVERE, "###{0} took {1} millis", new Object[]{ic.getMethod(), diffTime});
      logger.log(Level.FINE, "{0} took {1} millis", new Object[]{ic.getMethod(), diffTime});
    }
  }
  
}
