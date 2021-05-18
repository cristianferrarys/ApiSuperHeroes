package com.api.superheroes.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author CristianF
 *
 */
@Component("tiempoTranscurrido")
public class TiempoTranscurridoImpl implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(TiempoTranscurridoImpl.class);

  Long tiempoInicio = 0L;
  Long tiempoFinal = 0L;
  Long tiempoResultado = 0L;
  boolean banderaTiempo = false;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (contieneTiempoTranscurrido(handler)) {
      tiempoInicio = System.currentTimeMillis();
      banderaTiempo = true;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception {

    if (contieneTiempoTranscurrido(handler)) {
      tiempoFinal = System.currentTimeMillis();
      tiempoResultado = tiempoFinal - tiempoInicio;
      log.info("tiempo que tardo: {} ms", tiempoResultado);
    }
  }


  public boolean contieneTiempoTranscurrido(final Object handler) {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      TiempoTranscurrido methodTiempo = method.getAnnotation(TiempoTranscurrido.class);
      if (Objects.nonNull(methodTiempo)) {
        if (!methodTiempo.name().equals("") && banderaTiempo) {
          log.info("Method: {}", methodTiempo.name());
        }
        return true;
      }
    }
    return false;
  }

}
