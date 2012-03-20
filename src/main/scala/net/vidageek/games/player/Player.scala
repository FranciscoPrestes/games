package net.vidageek.games.player

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.SessionScoped
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.AuthorizationVerifier

@Component
@SessionScoped
class Player(val name : String) {
  var provider : AuthProvider = _

  def authorize(authorization : AuthorizationVerifier) = authorization.authorized match {
    case true => provider.accessToken(authorization.verifier)
    case false => provider = null
  }

  def getAuthorized = {
    provider != null
  }

  def getUserName = provider.userName

  def logout = {
    if (getAuthorized) {
      provider.logout
      provider = null
    }
  }
}