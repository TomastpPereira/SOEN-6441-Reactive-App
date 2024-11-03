
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
/*1.2*/import views.html.navbar

object submissionSentiment extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.4*/("""

"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Submission Sentiment Analysis</title>
    </head>
    <body>

        """),_display_(/*11.10*/navbar("submission-sentiment")),format.raw/*11.40*/("""

        """),format.raw/*13.9*/("""<h1>Submission Sentiment Analysis (Part D)</h1>

        <form action="/analyze-sentiment" method="post">
            <button type="submit">Analyze Sentiment</button>
        </form>
    </body>
</html>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/submissionSentiment.scala.html
                  HASH: c797199be85a20f74d61d63e7cb4177e662aab7e
                  MATRIX: 610->1|946->28|1042->30|1072->34|1226->161|1277->191|1316->203
                  LINES: 23->1|28->2|33->2|35->4|42->11|42->11|44->13
                  -- GENERATED --
              */
          