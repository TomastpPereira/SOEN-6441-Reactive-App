
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
        <h2>Search Terms: """"),_display_(/*27.29*/searchResult/*27.41*/.query),format.raw/*27.47*/(""""</h2>
        <ul>
        """),_display_(/*29.10*/for(video <- searchResult.videos) yield /*29.43*/ {_display_(Seq[Any](format.raw/*29.45*/("""
          """),format.raw/*30.11*/("""<li>
            <a href="https://www.youtube.com/watch?v="""),_display_(/*31.55*/video/*31.60*/.videoId),format.raw/*31.68*/("""">
            """),_display_(/*32.14*/video/*32.19*/.title),format.raw/*32.25*/("""
            """),format.raw/*33.13*/("""</a>
            <br>
            <a href="/youtube/channel/"""),_display_(/*35.40*/video/*35.45*/.channelId),format.raw/*35.55*/("""">
                Channel: """),_display_(/*36.27*/video/*36.32*/.channelTitle),format.raw/*36.45*/("""
              """),format.raw/*37.15*/("""</a>
          </li>
        """)))}),format.raw/*39.10*/("""
        """),format.raw/*40.9*/("""</ul>
      </div>
      <hr>
      """)))}),format.raw/*43.8*/("""

  """),format.raw/*45.3*/("""</body>
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
                  HASH: e2456666812efae1bb90d12f50f514d3fb25514b
                  MATRIX: 610->1|637->23|669->50|1018->81|1154->122|1186->128|1319->235|1356->251|1391->259|1674->516|1724->550|1764->552|1799->560|1861->595|1882->607|1909->613|1967->644|2016->677|2056->679|2096->691|2183->751|2197->756|2226->764|2270->781|2284->786|2311->792|2353->806|2443->869|2457->874|2488->884|2545->914|2559->919|2593->932|2637->948|2700->980|2737->990|2807->1030|2840->1036
                  LINES: 23->1|24->2|25->3|30->5|35->5|38->8|45->15|45->15|47->17|55->25|55->25|55->25|56->26|57->27|57->27|57->27|59->29|59->29|59->29|60->30|61->31|61->31|61->31|62->32|62->32|62->32|63->33|65->35|65->35|65->35|66->36|66->36|66->36|67->37|69->39|70->40|73->43|75->45
                  -- GENERATED --
              */
          