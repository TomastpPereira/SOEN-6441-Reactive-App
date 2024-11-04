
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._
/*1.2*/import models.Video
/*2.2*/import views.html.navbar
/*3.2*/import models.SearchResult

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[LinkedList[SearchResult],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*5.2*/(searchHistory: LinkedList[SearchResult]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.43*/("""


"""),format.raw/*8.1*/("""<!DOCTYPE html>
<html lang="en">
  <head>
    <title>YouTube Video</title>
  </head>
  <body>

    """),_display_(/*15.6*/navbar("search")),format.raw/*15.22*/("""

    """),format.raw/*17.5*/("""<h1>YouTube Video</h1>
    <h2>Enter Search Terms</h2>

    <form action="/search/searchVideos" method="get">
      <input type="text" name="query" placeholder="Enter keywords" required>
      <button type="submit">Search</button>
    </form>

    """),_display_(/*25.6*/for(searchResult <- searchHistory) yield /*25.40*/ {_display_(Seq[Any](format.raw/*25.42*/("""
      """),format.raw/*26.7*/("""<div>
        <h2>
            Search Terms: """"),_display_(/*28.29*/searchResult/*28.41*/.query),format.raw/*28.47*/("""" <a href=""""),_display_(/*28.59*/routes/*28.65*/.SearchController.MoreStats(searchResult.query)),format.raw/*28.112*/(""""> morestats </a>
        </h2>
        <ol>
        """),_display_(/*31.10*/for(video <- searchResult.videos) yield /*31.43*/ {_display_(Seq[Any](format.raw/*31.45*/("""
          """),format.raw/*32.11*/("""<li>
              <b>Title: </b>
              <a href="https://www.youtube.com/watch?v="""),_display_(/*34.57*/video/*34.62*/.videoId),format.raw/*34.70*/("""">
                """),_display_(/*35.18*/video/*35.23*/.title),format.raw/*35.29*/("""
              """),format.raw/*36.15*/("""</a>
              <b>Channel: </b>
              <a href="/youtube/channel/"""),_display_(/*38.42*/video/*38.47*/.channelId),format.raw/*38.57*/("""">
                """),_display_(/*39.18*/video/*39.23*/.channelTitle),format.raw/*39.36*/("""
              """),format.raw/*40.15*/("""</a>
              <b>Description: </b>
                """"),_display_(/*42.19*/video/*42.24*/.description),format.raw/*42.36*/(""""
              <a href="">  Tags </a>
          </li>
            <img src=""""),_display_(/*45.24*/video/*45.29*/.thumbnail),format.raw/*45.39*/("""" alt="Video's Thumbnail">

        """)))}),format.raw/*47.10*/("""
        """),format.raw/*48.9*/("""</ol>
      </div>
      <hr>
      """)))}),format.raw/*51.8*/("""

  """),format.raw/*53.3*/("""</body>
</html>
"""))
      }
    }
  }

  def render(searchHistory:LinkedList[SearchResult]): play.twirl.api.HtmlFormat.Appendable = apply(searchHistory)

  def f:((LinkedList[SearchResult]) => play.twirl.api.HtmlFormat.Appendable) = (searchHistory) => apply(searchHistory)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: da3e4d75319be9b2d27fd487a52b6128a13f9c71
                  MATRIX: 610->1|637->23|669->50|1018->81|1154->122|1186->128|1319->235|1356->251|1391->259|1674->516|1724->550|1764->552|1799->560|1875->609|1896->621|1923->627|1962->639|1977->645|2046->692|2130->749|2179->782|2219->784|2259->796|2378->888|2392->893|2421->901|2469->922|2483->927|2510->933|2554->949|2660->1028|2674->1033|2705->1043|2753->1064|2767->1069|2801->1082|2845->1098|2932->1158|2946->1163|2979->1175|3087->1256|3101->1261|3132->1271|3202->1310|3239->1320|3309->1360|3342->1366
                  LINES: 23->1|24->2|25->3|30->5|35->5|38->8|45->15|45->15|47->17|55->25|55->25|55->25|56->26|58->28|58->28|58->28|58->28|58->28|58->28|61->31|61->31|61->31|62->32|64->34|64->34|64->34|65->35|65->35|65->35|66->36|68->38|68->38|68->38|69->39|69->39|69->39|70->40|72->42|72->42|72->42|75->45|75->45|75->45|77->47|78->48|81->51|83->53
                  -- GENERATED --
              */
          