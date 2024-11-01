
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
/*1.2*/import Models.Video

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,java.util.List[Video],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(query: String, videos:java.util.List[Video]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.47*/("""

"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html>
  <head>
    <title>YouTube Video</title>
  </head>
  <body>
    <h1>YouTube Video</h1>
    <h2>Plz type keywords to find a list of videos max 10</h2>
    <form action="/search/searchVideos" method="get">
      <input type="text" name="query" placeholder="Enter keywords" required>
      <button type="submit">Search</button>
    </form>

      """),_display_(if(query != null && !query.isEmpty())/*17.45*/ {_display_(Seq[Any](format.raw/*17.47*/("""
        """),format.raw/*18.9*/("""<h2>Search Results for """"),_display_(/*18.34*/query),format.raw/*18.39*/(""""</h2>
        <ul>
        """),_display_(/*20.10*/for(i <- 0 until videos.size()) yield /*20.41*/ {_display_(Seq[Any](format.raw/*20.43*/("""
          """),_display_(/*21.12*/defining(videos.get(i))/*21.35*/ { video =>_display_(Seq[Any](format.raw/*21.46*/("""
            """),format.raw/*22.13*/("""<li>
              <a href="https://www.youtube.com/watch?v="""),_display_(/*23.57*/video/*23.62*/.videoId),format.raw/*23.70*/("""">
              """),_display_(/*24.16*/video/*24.21*/.title),format.raw/*24.27*/("""
              """),format.raw/*25.15*/("""</a>
              <br>
              <a href="/youtube/channel/"""),_display_(/*27.42*/video/*27.47*/.channelId),format.raw/*27.57*/("""">
                Channel: """),_display_(/*28.27*/video/*28.32*/.channelTitle),format.raw/*28.45*/("""
              """),format.raw/*29.15*/("""</a>
            </li>
          """)))}),format.raw/*31.12*/("""
        """)))}),format.raw/*32.10*/("""
        """),format.raw/*33.9*/("""</ul>
      """)))} else {null} ),format.raw/*34.8*/("""

  """),format.raw/*36.3*/("""</body>
</html>
"""))
      }
    }
  }

  def render(query:String,videos:java.util.List[Video]): play.twirl.api.HtmlFormat.Appendable = apply(query,videos)

  def f:((String,java.util.List[Video]) => play.twirl.api.HtmlFormat.Appendable) = (query,videos) => apply(query,videos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 99c49614c607a5a410925ef2090835d58ca02b21
                  MATRIX: 610->1|956->23|1096->68|1126->72|1572->491|1612->493|1649->503|1701->528|1727->533|1785->564|1832->595|1872->597|1912->610|1944->633|1993->644|2035->658|2124->720|2138->725|2167->733|2213->752|2227->757|2254->763|2298->779|2392->846|2406->851|2437->861|2494->891|2508->896|2542->909|2586->925|2653->961|2695->972|2732->982|2789->996|2822->1002
                  LINES: 23->1|28->2|33->2|35->4|48->17|48->17|49->18|49->18|49->18|51->20|51->20|51->20|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|56->25|58->27|58->27|58->27|59->28|59->28|59->28|60->29|62->31|63->32|64->33|65->34|67->36
                  -- GENERATED --
              */
          