// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  SearchController_0: controllers.SearchController,
  // @LINE:9
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    SearchController_0: controllers.SearchController,
    // @LINE:9
    Assets_1: controllers.Assets
  ) = this(errorHandler, SearchController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, SearchController_0, Assets_1, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.SearchController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """search/searchVideos""", """controllers.SearchController.searchVideos(query:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """search/MoreStats""", """controllers.SearchController.MoreStats(query:String)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:6
  private lazy val controllers_SearchController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_SearchController_index0_invoker = createInvoker(
    SearchController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SearchController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:9
  private lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:10
  private lazy val controllers_SearchController_searchVideos2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("search/searchVideos")))
  )
  private lazy val controllers_SearchController_searchVideos2_invoker = createInvoker(
    SearchController_0.searchVideos(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SearchController",
      "searchVideos",
      Seq(classOf[String]),
      "GET",
      this.prefix + """search/searchVideos""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private lazy val controllers_SearchController_MoreStats3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("search/MoreStats")))
  )
  private lazy val controllers_SearchController_MoreStats3_invoker = createInvoker(
    SearchController_0.MoreStats(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SearchController",
      "MoreStats",
      Seq(classOf[String]),
      "GET",
      this.prefix + """search/MoreStats""",
      """ Route for Individual Parts""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_SearchController_index0_route(params@_) =>
      call { 
        controllers_SearchController_index0_invoker.call(SearchController_0.index())
      }
  
    // @LINE:9
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:10
    case controllers_SearchController_searchVideos2_route(params@_) =>
      call(params.fromQuery[String]("query", None)) { (query) =>
        controllers_SearchController_searchVideos2_invoker.call(SearchController_0.searchVideos(query))
      }
  
    // @LINE:13
    case controllers_SearchController_MoreStats3_route(params@_) =>
      call(params.fromQuery[String]("query", None)) { (query) =>
        controllers_SearchController_MoreStats3_invoker.call(SearchController_0.MoreStats(query))
      }
  }
}
