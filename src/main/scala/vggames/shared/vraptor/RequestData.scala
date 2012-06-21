package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest

@Component
class RequestData(request : HttpServletRequest) {

  private val gameRegex = "/play/([^/]+)/?.*".r
  val gameRegex(game) = request.getRequestURI;

}