
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

object navbar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.23*/("""

"""),format.raw/*3.1*/("""<div class="navbar">
    <a href="/" class=""""),_display_(if(currentPage == "home")/*4.50*/ {_display_(Seq[Any](format.raw/*4.52*/("""active""")))} else {null} ),format.raw/*4.59*/("""">Home</a>
    <a href="/channelProfile" class=""""),_display_(if(currentPage == "Channel Profile")/*5.75*/ {_display_(Seq[Any](format.raw/*5.77*/("""active""")))} else {null} ),format.raw/*5.84*/("""">Channel Profile</a>
    <a href="/wordStats" class=""""),_display_(if(currentPage == "Words Stats")/*6.66*/ {_display_(Seq[Any](format.raw/*6.68*/("""active""")))} else {null} ),format.raw/*6.75*/("""">Word Stats</a>
    <a href="/tags" class=""""),_display_(if(currentPage == "Tags")/*7.54*/ {_display_(Seq[Any](format.raw/*7.56*/("""active""")))} else {null} ),format.raw/*7.63*/("""">Tags</a>
    <a href="/sentiment" class=""""),_display_(if(currentPage == "Submission Sentiment")/*8.75*/ {_display_(Seq[Any](format.raw/*8.77*/("""active""")))} else {null} ),format.raw/*8.84*/("""">Submission Sentiment</a>
</div>

<style>
        .navbar """),format.raw/*12.17*/("""{"""),format.raw/*12.18*/("""
            """),format.raw/*13.13*/("""display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #333;
            padding: 1em;
        """),format.raw/*18.9*/("""}"""),format.raw/*18.10*/("""

        """),format.raw/*20.9*/(""".navbar a """),format.raw/*20.19*/("""{"""),format.raw/*20.20*/("""
            """),format.raw/*21.13*/("""color: white;
            padding: 0.5em 1em;
            text-decoration: none;
        """),format.raw/*24.9*/("""}"""),format.raw/*24.10*/("""

        """),format.raw/*26.9*/(""".navbar a.active """),format.raw/*26.26*/("""{"""),format.raw/*26.27*/("""
            """),format.raw/*27.13*/("""background-color: #555;
        """),format.raw/*28.9*/("""}"""),format.raw/*28.10*/("""

        """),format.raw/*30.9*/(""".navbar a:hover """),format.raw/*30.25*/("""{"""),format.raw/*30.26*/("""
            """),format.raw/*31.13*/("""background-color: #555;
        """),format.raw/*32.9*/("""}"""),format.raw/*32.10*/("""
"""),format.raw/*33.1*/("""</style>"""))
      }
    }
  }

  def render(currentPage:String): play.twirl.api.HtmlFormat.Appendable = apply(currentPage)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (currentPage) => apply(currentPage)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/navbar.scala.html
                  HASH: 1126c8e74b69095d494119c4ecc07153f591945d
                  MATRIX: 908->1|1024->22|1054->26|1151->97|1190->99|1240->106|1352->192|1391->194|1441->201|1555->289|1594->291|1644->298|1741->369|1780->371|1830->378|1942->464|1981->466|2031->473|2122->536|2151->537|2193->551|2387->718|2416->719|2455->731|2493->741|2522->742|2564->756|2683->848|2712->849|2751->861|2796->878|2825->879|2867->893|2927->926|2956->927|2995->939|3039->955|3068->956|3110->970|3170->1003|3199->1004|3228->1006
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|36->5|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|43->12|43->12|44->13|49->18|49->18|51->20|51->20|51->20|52->21|55->24|55->24|57->26|57->26|57->26|58->27|59->28|59->28|61->30|61->30|61->30|62->31|63->32|63->32|64->33
                  -- GENERATED --
              */
          