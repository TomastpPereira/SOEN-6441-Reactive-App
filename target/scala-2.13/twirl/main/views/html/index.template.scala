
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
/*2.2*/import models.SearchResult

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[LinkedList[SearchResult],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(searchHistory: LinkedList[SearchResult]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.43*/("""


"""),format.raw/*7.1*/("""<!DOCTYPE html>
<html lang="en">
  <head>
    <title>YouTube Video</title>
  </head>
  <body>

    <h1>Welcome to YT Lytics</h1>
    <h2>Enter Search Terms</h2>

    <form action="/search/searchVideos" method="get">
      <input type="text" name="query" placeholder="Enter keywords" required>
      <button type="submit">Search</button>
    </form>

    """),_display_(/*22.6*/for(searchResult <- searchHistory) yield /*22.40*/ {_display_(Seq[Any](format.raw/*22.42*/("""
      """),format.raw/*23.7*/("""<div>
        <h2>
            Search Terms: """"),_display_(/*25.29*/searchResult/*25.41*/.query),format.raw/*25.47*/("""" """),_display_(/*25.50*/searchResult/*25.62*/.sentiment),format.raw/*25.72*/(""" """),format.raw/*25.73*/("""<a href=""""),_display_(/*25.83*/routes/*25.89*/.SearchController.MoreStats(searchResult.query)),format.raw/*25.136*/(""""> morestats </a>
        </h2>
        <ol>
        """),_display_(/*28.10*/for(video <- searchResult.videos.take(10)) yield /*28.52*/ {_display_(Seq[Any](format.raw/*28.54*/("""
          """),format.raw/*29.11*/("""<li>
              <b>Title: </b>
              <a href="https://www.youtube.com/watch?v="""),_display_(/*31.57*/video/*31.62*/.getVideoId),format.raw/*31.73*/("""">
                """),_display_(/*32.18*/video/*32.23*/.getTitle),format.raw/*32.32*/("""
              """),format.raw/*33.15*/("""</a>
              <b>Channel: </b>
              <a href="/youtube/channel/"""),_display_(/*35.42*/video/*35.47*/.getChannelId),format.raw/*35.60*/("""">
                """),_display_(/*36.18*/video/*36.23*/.getChannelTitle),format.raw/*36.39*/("""
              """),format.raw/*37.15*/("""</a>
              <b>Description: </b>
                """"),_display_(/*39.19*/video/*39.24*/.getDescription),format.raw/*39.39*/(""""
              <a href="">  Tags </a>
          </li>
            <img src=""""),_display_(/*42.24*/video/*42.29*/.getThumbnail),format.raw/*42.42*/("""" alt="Video's Thumbnail">

        """)))}),format.raw/*44.10*/("""
        """),format.raw/*45.9*/("""</ol>
      </div>
      <hr>
      """)))}),format.raw/*48.8*/("""

  """),format.raw/*50.3*/("""</body>
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
                  HASH: 255950468143d341dbcb54427d6149b5e82ed0b7
                  MATRIX: 610->1|637->23|986->54|1122->95|1154->101|1550->471|1600->505|1640->507|1675->515|1751->564|1772->576|1799->582|1829->585|1850->597|1881->607|1910->608|1947->618|1962->624|2031->671|2115->728|2173->770|2213->772|2253->784|2372->876|2386->881|2418->892|2466->913|2480->918|2510->927|2554->943|2660->1022|2674->1027|2708->1040|2756->1061|2770->1066|2807->1082|2851->1098|2938->1158|2952->1163|2988->1178|3096->1259|3110->1264|3144->1277|3214->1316|3251->1326|3321->1366|3354->1372
                  LINES: 23->1|24->2|29->4|34->4|37->7|52->22|52->22|52->22|53->23|55->25|55->25|55->25|55->25|55->25|55->25|55->25|55->25|55->25|55->25|58->28|58->28|58->28|59->29|61->31|61->31|61->31|62->32|62->32|62->32|63->33|65->35|65->35|65->35|66->36|66->36|66->36|67->37|69->39|69->39|69->39|72->42|72->42|72->42|74->44|75->45|78->48|80->50
                  -- GENERATED --
              */
          