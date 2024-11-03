
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
        <h2>Search Terms: """"),_display_(/*27.29*/searchResult/*27.41*/.query),format.raw/*27.47*/("""" <a href=""""),_display_(/*27.59*/routes/*27.65*/.SearchController.MoreStats(searchResult.query)),format.raw/*27.112*/(""""> morestats </a>
</h2>
        <ul>
        """),_display_(/*30.10*/for(video <- searchResult.videos) yield /*30.43*/ {_display_(Seq[Any](format.raw/*30.45*/("""
          """),format.raw/*31.11*/("""<li>
            <a href="https://www.youtube.com/watch?v="""),_display_(/*32.55*/video/*32.60*/.videoId),format.raw/*32.68*/("""">
            """),_display_(/*33.14*/video/*33.19*/.title),format.raw/*33.25*/("""
            """),format.raw/*34.13*/("""</a>
            <br>
            <a href="/youtube/channel/"""),_display_(/*36.40*/video/*36.45*/.channelId),format.raw/*36.55*/("""">
                Channel: """),_display_(/*37.27*/video/*37.32*/.channelTitle),format.raw/*37.45*/("""
              """),format.raw/*38.15*/("""</a>
          </li>
        """)))}),format.raw/*40.10*/("""
        """),format.raw/*41.9*/("""</ul>
      </div>
      <hr>
      """)))}),format.raw/*44.8*/("""

  """),format.raw/*46.3*/("""</body>
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
                  HASH: 77192d9b51053df7be6655e3fd4c55313680d19d
                  MATRIX: 610->1|637->23|669->50|1018->81|1154->122|1186->128|1319->235|1356->251|1391->259|1674->516|1724->550|1764->552|1799->560|1861->595|1882->607|1909->613|1948->625|1963->631|2032->678|2108->727|2157->760|2197->762|2237->774|2324->834|2338->839|2367->847|2411->864|2425->869|2452->875|2494->889|2584->952|2598->957|2629->967|2686->997|2700->1002|2734->1015|2778->1031|2841->1063|2878->1073|2948->1113|2981->1119
                  LINES: 23->1|24->2|25->3|30->5|35->5|38->8|45->15|45->15|47->17|55->25|55->25|55->25|56->26|57->27|57->27|57->27|57->27|57->27|57->27|60->30|60->30|60->30|61->31|62->32|62->32|62->32|63->33|63->33|63->33|64->34|66->36|66->36|66->36|67->37|67->37|67->37|68->38|70->40|71->41|74->44|76->46
                  -- GENERATED --
              */
          